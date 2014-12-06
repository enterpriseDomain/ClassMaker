package org.classupplier.install;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ClassSupplierUtil;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IInstallableUnitPatch;
import org.eclipse.equinox.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.IRequirementChange;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitPatchDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.operations.InstallOperation;
import org.eclipse.equinox.p2.operations.ProvisioningJob;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

@SuppressWarnings("restriction")
public class P2Installer extends Installer {

	private FeatureExportInfo featureInfo;

	private ProvisioningSession session;

	public P2Installer(FeatureExportInfo info) {
		super();
		this.featureInfo = info;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		return install(monitor);
	}

	private IInstallableUnitPatch createInstallableUnitPatch(
			IInstallableUnit existingIU, Version newVersion, IProfile profile,
			IProgressMonitor monitor) {
		InstallableUnitPatchDescription iuPatchDescription = new MetadataFactory.InstallableUnitPatchDescription();
		String id = existingIU.getId();
		iuPatchDescription.setId(id + ".patch"); //$NON-NLS-1$
		iuPatchDescription.setProperty(IInstallableUnit.PROP_NAME,
				NLS.bind("{0} Install Patch", id));
		iuPatchDescription
				.setProperty(
						IInstallableUnit.PROP_DESCRIPTION,
						"This patch was created as part of Contribution produce operation to modify the contents of the current application.");
		Version patchVersion = Version.createOSGi(1, 0, 0,
				ResourceUtil.formatQualifier(new Date()));
		iuPatchDescription.setVersion(patchVersion);
		iuPatchDescription.setUpdateDescriptor(MetadataFactory
				.createUpdateDescriptor(iuPatchDescription.getId(),
						new VersionRange(Version.createOSGi(0, 0, 0), true,
								patchVersion, false), 0, null));

		ArrayList<IProvidedCapability> list = new ArrayList<IProvidedCapability>(
				1);
		list.add(MetadataFactory.createProvidedCapability(
				IInstallableUnit.NAMESPACE_IU_ID, iuPatchDescription.getId(),
				iuPatchDescription.getVersion()));
		iuPatchDescription.addProvidedCapabilities(list);

		IRequirement applyTo = MetadataFactory.createRequirement(
				IInstallableUnit.NAMESPACE_IU_ID, id, null, null, false, false);
		IRequirement newValue = MetadataFactory
				.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, id,
						new VersionRange(newVersion, true, newVersion, true),
						null, false, false);
		iuPatchDescription
				.setRequirementChanges(new IRequirementChange[] { MetadataFactory
						.createRequirementChange(applyTo, newValue) });

		iuPatchDescription.setApplicabilityScope(new IRequirement[0][0]);

		IQueryResult<?> queryMatches = profile
				.query(QueryUtil
						.createMatchQuery(
								"requirements.exists(rc | $0 ~= rc)", new Object[] { existingIU }), monitor); //$NON-NLS-1$
		if (!queryMatches.isEmpty()) {
			IInstallableUnit lifecycleUnit = (IInstallableUnit) queryMatches
					.iterator().next();
			iuPatchDescription.setLifeCycle(MetadataFactory.createRequirement(
					IInstallableUnit.NAMESPACE_IU_ID, lifecycleUnit.getId(),
					new VersionRange(lifecycleUnit.getVersion(), true,
							lifecycleUnit.getVersion(), true), null, false,
					false, false));
		}

		iuPatchDescription.setProperty(
				InstallableUnitDescription.PROP_TYPE_PATCH,
				Boolean.TRUE.toString());

		return MetadataFactory.createInstallableUnitPatch(iuPatchDescription);
	}

	private IMetadataRepository loadMetadataRepository(
			IProgressMonitor monitor, URI destination)
			throws ProvisionException {
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager) session
				.getProvisioningAgent().getService(
						IMetadataRepositoryManager.SERVICE_NAME);
		return manager.loadRepository(destination, monitor);
	}

	private IArtifactRepository loadArtifactRepository(
			IProgressMonitor monitor, URI destination)
			throws ProvisionException {
		IArtifactRepositoryManager manager = (IArtifactRepositoryManager) session
				.getProvisioningAgent().getService(
						IArtifactRepositoryManager.SERVICE_NAME);
		return manager.loadRepository(destination, monitor);
	}

	@Override
	public IStatus install(IProgressMonitor monitor) {
		try {
			IProvisioningAgent agent = (IProvisioningAgent) ClassSupplierOSGi
					.getService(IProvisioningAgent.class.getName());
			session = new ProvisioningSession(agent);
			session.rememberJob(this);
			URI destination = new File(featureInfo.destinationDirectory)
					.toURI();
			loadArtifactRepository(monitor, destination);

			IMetadataRepository metaRepo = loadMetadataRepository(monitor,
					destination);

			IProfileRegistry profileRegistry = (IProfileRegistry) session
					.getProvisioningAgent().getService(
							IProfileRegistry.SERVICE_NAME);
			if (profileRegistry == null) {
				return ClassSupplierOSGi
						.createErrorStatus("Could not open profile");
			}
			IProfile profile = profileRegistry
					.getProfile(IProfileRegistry.SELF);
			if (profile == null) {
				return ClassSupplierOSGi
						.createErrorStatus("Could not open profile");
			}

			List<IInstallableUnit> toInstall = new ArrayList<IInstallableUnit>();
			for (int i = 0; i < featureInfo.items.length; i++) {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}

				String id = null;
				String version = null;
				if (featureInfo.items[i] instanceof IPluginModelBase) {
					id = ((IPluginModelBase) featureInfo.items[i])
							.getPluginBase().getId();
					version = ((IPluginModelBase) featureInfo.items[i])
							.getPluginBase().getVersion();
				} else if (featureInfo.items[i] instanceof IFeatureModel) {
					id = ((IFeatureModel) featureInfo.items[i]).getFeature()
							.getId() + ".feature.group"; //$NON-NLS-1$
					version = ((IFeatureModel) featureInfo.items[i])
							.getFeature().getVersion();
				}

				if (id == null && version == null) {
					return ClassSupplierOSGi.createErrorStatus(NLS.bind(
							"Could not get id or version of {0}",
							featureInfo.items[i].toString()));
				}

				State state = ClassSupplierUtil.getState(id);
				version = state.getVersion().toString();

				// Check if the right version exists in the new meta repo
				Version newVersion = Version.parseVersion(version);
				IQueryResult<?> queryMatches = metaRepo.query(
						QueryUtil.createIUQuery(id, newVersion), monitor);
				if (queryMatches.isEmpty()) {
					return ClassSupplierOSGi.createErrorStatus(NLS.bind(
							"Could not find unit {0} in repo", new String[] {
									id, version }));
				}

				IInstallableUnit iuToInstall = (IInstallableUnit) queryMatches
						.iterator().next();

				queryMatches = profile.query(QueryUtil.createIUQuery(id),
						new SubProgressMonitor(monitor, 0));
				if (queryMatches.isEmpty()) {
					toInstall.add(iuToInstall);
				} else {
					IInstallableUnit existingIU = (IInstallableUnit) queryMatches
							.iterator().next();
					toInstall.add(createInstallableUnitPatch(existingIU,
							newVersion, profile, monitor));
				}
				monitor.worked(2);

			}

			if (toInstall.size() > 0) {
				InstallOperation operation = new InstallOperation(session,
						toInstall);
				operation.setProfileId(profile.getProfileId());
				ProvisioningContext context = new ProvisioningContext(
						session.getProvisioningAgent());
				URI[] repos = new URI[] { destination };
				context.setMetadataRepositories(repos);
				context.setArtifactRepositories(repos);
				operation.setProvisioningContext(context);
				operation.resolveModal(new SubProgressMonitor(monitor, 5));
				IStatus status = operation.getResolutionResult();
				if (status.getSeverity() == IStatus.CANCEL
						|| !(status.isOK() || status.getSeverity() == IStatus.INFO)) {
					return status;
				}
				ProvisioningJob job = operation.getProvisioningJob(null);
				status = job.runModal(new SubProgressMonitor(monitor, 5));
				return status;
			}

			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
			return Status.OK_STATUS;
		} catch (ProvisionException e) {
			return e.getStatus();
		}
	}

}
