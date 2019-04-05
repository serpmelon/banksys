package com.togo.common.util;

import java.util.UUID;

public final class UuidUtil {

	private UuidUtil() {
	}

	public static String uuid() {

		return UUID.randomUUID().toString();
	}

	public static String uuidWithoutLine() {

		return uuid().replace("-", "");
	}
}
