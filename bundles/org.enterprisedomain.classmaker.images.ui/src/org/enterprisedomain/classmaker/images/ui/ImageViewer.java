package org.enterprisedomain.classmaker.images.ui;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.imagesUI.Measurement;

public class ImageViewer extends EditorPart {

	public static final String ID = "org.enterprisedomain.classmaker.images.ui.imageViewer";

	private Canvas canvas;
	private Image sourceImage;
	private Image screenImage;
	private AffineTransform transform;
	private ImageData data;
	private List<Point> measurementPoints;
	private List<Measurement> measurements;
	private EObject targetObject;
	private Point lastPoint;
	private double rate = 1.0;
	private KeyListener keyListener = new KeyListener() {

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.character == '+')
				zoom(1.1);
			if (e.character == '-')
				zoom(0.9);
//			if (e.character == 27) {
//				canvas.getHorizontalBar().setSelection(canvas.getHorizontalBar().getSelection() - 10);
//				scrollHorizontally(canvas.getHorizontalBar());
//			}
//			if (e.character == 26) {
//				canvas.getHorizontalBar().setSelection(canvas.getHorizontalBar().getSelection() + 10);
//				scrollHorizontally(canvas.getHorizontalBar());
//			}
//			if (e.character == 24) {
//				canvas.getVerticalBar().setSelection(canvas.getVerticalBar().getSelection() - 10);
//				scrollVertically(canvas.getVerticalBar());
//			}
//			if (e.character == 25) {
//				canvas.getVerticalBar().setSelection(canvas.getVerticalBar().getSelection() + 10);
//				scrollVertically(canvas.getVerticalBar());
//			}
		}
	};

	private double srcY;

	private double srcX;

	private double destWidth;

	private double destHeight;

	@Override
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.NO_BACKGROUND);
		canvas.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent event) {
				syncScrollBars();
			}
		});
		canvas.addPaintListener(e -> paint(e.gc));
		canvas.addKeyListener(keyListener);
		initScrollBars();
		sourceImage = new Image(parent.getDisplay(), data);
		transform = new AffineTransform();
		syncScrollBars();
	}

	private void initScrollBars() {
		ScrollBar horizontal = canvas.getHorizontalBar();
		horizontal.setEnabled(false);
		horizontal.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				scrollHorizontally((ScrollBar) event.widget);
			}
		});
		ScrollBar vertical = canvas.getVerticalBar();
		vertical.setEnabled(false);
		vertical.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				scrollVertically((ScrollBar) event.widget);
			}
		});
	}

	public void syncScrollBars() {
		if (sourceImage == null) {
			canvas.redraw();
			return;
		}

		AffineTransform af = transform;
		double sx = af.getScaleX(), sy = af.getScaleY();
		double tx = af.getTranslateX(), ty = af.getTranslateY();
		if (tx > 0)
			tx = 0;
		if (ty > 0)
			ty = 0;

		ScrollBar horizontal = canvas.getHorizontalBar();
		horizontal.setIncrement((int) (canvas.getClientArea().width / 100));
		horizontal.setPageIncrement(canvas.getClientArea().width);
		Rectangle imageBound = sourceImage.getBounds();
		int cw = canvas.getClientArea().width, ch = canvas.getClientArea().height;
		if (imageBound.width * sx > cw) {
			horizontal.setMaximum((int) (imageBound.width * sx));
			horizontal.setEnabled(true);
			if (((int) -tx) > horizontal.getMaximum() - cw)
				tx = -horizontal.getMaximum() + cw;
		} else {
			horizontal.setEnabled(false);
			tx = (cw - imageBound.width * sx) / 2;
		}
		horizontal.setSelection((int) (-tx));
		horizontal.setThumb((int) (canvas.getClientArea().width));

		ScrollBar vertical = canvas.getVerticalBar();
		vertical.setIncrement((int) (canvas.getClientArea().height / 100));
		vertical.setPageIncrement((int) (canvas.getClientArea().height));
		if (imageBound.height * sy > ch) {
			vertical.setMaximum((int) (imageBound.height * sy));
			vertical.setEnabled(true);
			if (((int) -ty) > vertical.getMaximum() - ch)
				ty = -vertical.getMaximum() + ch;
		} else {
			vertical.setEnabled(false);
			ty = (ch - imageBound.height * sy) / 2;
		}
		vertical.setSelection((int) (-ty));
		vertical.setThumb((int) (canvas.getClientArea().height));

		af = AffineTransform.getScaleInstance(sx, sy);
		af.preConcatenate(AffineTransform.getTranslateInstance(tx, ty));
		transform = af;

		canvas.redraw();
	}

	private void scrollHorizontally(ScrollBar scrollBar) {
		if (sourceImage == null)
			return;

		AffineTransform af = transform;
		double tx = af.getTranslateX();
		double select = -scrollBar.getSelection();
		af.preConcatenate(AffineTransform.getTranslateInstance(select - tx, 0));
		transform = af;
		syncScrollBars();
	}

	private void scrollVertically(ScrollBar scrollBar) {
		if (sourceImage == null)
			return;

		AffineTransform af = transform;
		double ty = af.getTranslateY();
		double select = -scrollBar.getSelection();
		af.preConcatenate(AffineTransform.getTranslateInstance(0, select - ty));
		transform = af;
		syncScrollBars();
	}

	public void addMouseListener(MouseListener listener) {
		if (canvas != null && !canvas.isDisposed())
			canvas.addMouseListener(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		if (canvas != null && !canvas.isDisposed())
			canvas.removeMouseListener(listener);
	}

	public void addMouseMoveListener(MouseMoveListener listener) {
		if (canvas != null && !canvas.isDisposed())
			canvas.addMouseMoveListener(listener);
	}

	public void removeMouseMoveListener(MouseMoveListener listener) {
		if (canvas != null && !canvas.isDisposed())
			canvas.removeMouseMoveListener(listener);
	}

	private void paint(GC gc) {
		Rectangle clientRect = canvas.getClientArea();
		if (sourceImage != null) {
			Rectangle imageRect = inverseTransformRect(transform, clientRect);
			int gap = 2;
			imageRect.x -= gap;
			imageRect.y -= gap;
			imageRect.width += 2 * gap;
			imageRect.height += 2 * gap;

			Rectangle imageBound = sourceImage.getBounds();
			imageRect = imageRect.intersection(imageBound);
			Rectangle destRect = transformRect(transform, imageRect);

			if (screenImage != null)
				screenImage.dispose();
			screenImage = new Image(canvas.getDisplay(), clientRect.width, clientRect.height);
			GC newGC = new GC(screenImage);
			newGC.setClipping(clientRect);
			newGC.drawImage(sourceImage, imageRect.x, imageRect.y, imageRect.width, imageRect.height, destRect.x,
					destRect.y, destRect.width, destRect.height);
			newGC.dispose();

			gc.drawImage(screenImage, 0, 0);

			Rectangle pointRect = transformRect(transform, sourceImage.getBounds());

			if (measurements != null)
				for (Measurement m : measurements) {
					gc.setForeground(canvas.getDisplay().getSystemColor(SWT.COLOR_BLACK));
					gc.drawLine((int) Math.floor(doubleX((m.getFrom().x) * this.rate, srcX, destRect.width, gc)),
							(int) Math.floor(doubleY((m.getFrom().y) * this.rate, destRect.y, destRect.height, gc)),
							(int) Math.floor(doubleX((m.getTo().x) * this.rate, srcX, destRect.width, gc)),
							(int) Math.floor(doubleY((m.getTo().y) * this.rate, destRect.y, destRect.height, gc)));
				}
			if (measurementPoints != null)
				for (Point m : measurementPoints) {
					gc.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_GREEN));
					gc.fillOval((int) Math.floor(doubleX((m.x) * this.rate, srcX, destRect.width, gc)) - 8,
							(int) Math.floor(doubleY((m.y) * this.rate, destRect.y, destRect.height, gc)) - 8, 16, 16);
				}
			gc.drawRectangle(pointRect);
		} else {
			gc.setClipping(clientRect);
			gc.fillRectangle(clientRect);
			initScrollBars();
		}
	}

	private double doubleX(double srcX, double destX, double destWidth, GC gc) {
		double center = destX + destWidth / 2;
		this.srcX = srcX;
		this.destWidth = destWidth;
		gc.drawRectangle(new Rectangle((int) srcX, (int) srcY, (int) destWidth, (int) destHeight));
		if (srcX <= center)
			return srcX;
		else
			return doubleX(srcX, center, destWidth * this.rate, gc);
	}

	private double doubleY(double srcY, double destY, double destHeight, GC gc) {
		double center = destY + destHeight / 2;
		this.srcY = srcY;
		this.destHeight = destHeight;
		gc.drawRectangle(new Rectangle((int) srcX, (int) srcY, (int) destWidth, (int) destHeight));
		if (srcY <= center)
			return srcY;
		else
			return doubleY(srcY, center, destHeight * this.rate, gc);
	}

	public void redraw() {
		if (canvas != null && !canvas.isDisposed())
			canvas.redraw();
	}

	public void centerZoom(double dx, double dy, double scale, AffineTransform af) {
		af.preConcatenate(AffineTransform.getTranslateInstance(-dx, -dy));
		af.preConcatenate(AffineTransform.getScaleInstance(scale, scale));
		af.preConcatenate(AffineTransform.getTranslateInstance(dx, dy));
		transform = af;
		syncScrollBars();
	}

	public void zoom(double rate) {
		if (sourceImage == null)
			return;
		Rectangle rect = canvas.getClientArea();
		int w = rect.width, h = rect.height;
		double dx = ((double) w) / 2;
		double dy = ((double) h) / 2;
		setRate(rate);
		centerZoom(dx, dy, rate, transform);
	}

	private void setRate(double rate) {
		this.rate *= rate;
	}

	public static Rectangle transformRect(AffineTransform af, Rectangle src) {
		Rectangle dest = new Rectangle(0, 0, 0, 0);
		src = absRect(src);
		Point p1 = new Point(src.x, src.y);
		p1 = transformPoint(af, p1);
		dest.x = p1.x;
		dest.y = p1.y;
		dest.width = (int) (src.width * af.getScaleX());
		dest.height = (int) (src.height * af.getScaleY());
		return dest;
	}

	public static Rectangle inverseTransformRect(AffineTransform af, Rectangle src) {
		Rectangle dest = new Rectangle(0, 0, 0, 0);
		src = absRect(src);
		Point p1 = new Point(src.x, src.y);
		p1 = inverseTransformPoint(af, p1);
		dest.x = p1.x;
		dest.y = p1.y;
		dest.width = (int) (src.width / af.getScaleX());
		dest.height = (int) (src.height / af.getScaleY());
		return dest;
	}

	public static Point transformPoint(AffineTransform af, Point pt) {
		Point2D src = new Point2D.Float(pt.x, pt.y);
		Point2D dest = af.transform(src, null);
		Point point = new Point((int) Math.floor(dest.getX()), (int) Math.floor(dest.getY()));
		return point;
	}

	public static Point inverseTransformPoint(AffineTransform af, Point pt) {
		Point2D src = new Point2D.Float(pt.x, pt.y);
		try {
			Point2D dest = af.inverseTransform(src, null);
			return new Point((int) Math.floor(dest.getX()), (int) Math.floor(dest.getY()));
		} catch (Exception e) {
			e.printStackTrace();
			return new Point(0, 0);
		}
	}

	public static Rectangle absRect(Rectangle src) {
		Rectangle dest = new Rectangle(0, 0, 0, 0);
		if (src.width < 0) {
			dest.x = src.x + src.width + 1;
			dest.width = -src.width;
		} else {
			dest.x = src.x;
			dest.width = src.width;
		}
		if (src.height < 0) {
			dest.y = src.y + src.height + 1;
			dest.height = -src.height;
		} else {
			dest.y = src.y;
			dest.height = src.height;
		}
		return dest;
	}

	@Override
	public void setFocus() {
		if (canvas != null && !canvas.isDisposed())
			canvas.setFocus();
	}

	public void dispose() {
		if (screenImage != null && !screenImage.isDisposed())
			screenImage.dispose();
		if (canvas != null && !canvas.isDisposed())
			canvas.dispose();
		if (sourceImage != null && !sourceImage.isDisposed())
			sourceImage.dispose();
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		try {
			IFileStore file = EFS.getLocalFileSystem().getStore(((IURIEditorInput) input).getURI());
			data = new ImageData(file.openInputStream(EFS.NONE, ClassMakerPlugin.getProgressMonitor()));
		} catch (CoreException e) {
			throw new PartInitException(e.getStatus());
		}
		setInput(input);
		setSite(site);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public void setMeasurementPoints(List<Point> measurementPoints) {
		this.measurementPoints = measurementPoints;
	}

	public List<Measurement> getMeasurements() {
		if (targetObject != null) {
			return (EList<Measurement>) targetObject.eGet(targetObject.eClass().getEStructuralFeature("measurements"));
		}
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		if (targetObject != null) {
			EList<Measurement> ms = (EList<Measurement>) targetObject
					.eGet(targetObject.eClass().getEStructuralFeature("measurements"));
			ms.clear();
			if (measurements != null)
				ms.addAll(measurements);
		}
		this.measurements = measurements;
	}

	public Point getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Point lastPoint) {
		this.lastPoint = lastPoint;
	}

	public void setTargetObject(EObject targetObject) {
		this.targetObject = targetObject;
	}

}
