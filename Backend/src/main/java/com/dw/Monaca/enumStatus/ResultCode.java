package com.dw.Monaca.enumStatus;

public enum ResultCode {
SUCCESS("정상 처리되었습니다"),
ERROR("에러가 발생했습니다. 다시 시도해주세요."),
UNAUTHORIZED("권한이 없습니다.");
	
	private final String msg;
	
	private ResultCode(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	
	
}
