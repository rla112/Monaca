package com.dw.Monaca.enumStatus;

public enum Gender {

	MAN("남성"),
	WOMAN("여성");
	
	// final은 수정이 불가함
	private final String desc;
	
	private Gender(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
}
