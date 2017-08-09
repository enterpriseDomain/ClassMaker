package org.enterprisedomain.classmaker.util;

import java.util.List;

public class ListUtil {

	public static int lastIndex(int size) {
		return size - 1;
	}

	public static <T> T getLast(List<T> list) {
		return list.get(lastIndex(list.size()));
	}

}
