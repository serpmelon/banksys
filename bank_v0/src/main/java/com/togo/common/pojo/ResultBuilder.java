package com.togo.common.pojo;

public final class ResultBuilder {

	private ResultBuilder() {
	}

	public static Result build() {

		return new Result();
	}

	public static Result build(boolean success, String msg) {

		return new Result(success, msg);
	}

	public static Result buildByException(Throwable e) {

		String msg = e.getMessage();
		return new Result(false, msg);
	}

	public static void set(Result result, boolean success) {

		result.success = success;
	}

	public static void set(Result result, String msg) {

		result.msg = msg;
	}

	public static class Result {

		private boolean success = true;
		private String msg = "";

		private Result() {
		}

		private Result(boolean success, String msg) {

			this.success = success;
			this.msg = msg;
		}
	}
}
