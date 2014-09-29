package com.gtcafe.sdna.core.util;

public final class Logger {

	public static void info(Object o, String method) {
		System.out.println("[INFO] " + new java.util.Date() + ": " + o.getClass().getName() + "." + method);
	}

	public static void message(String msg) {
		System.out.println("[MSG] " + new java.util.Date() + ": " + msg);
	}
}
