package com.dw.Monaca.dto;

public class StudentProgressDto {

    private String studentName;
    private String lectureName;
    private double progressRate;
	

    public StudentProgressDto() {
		super();
	}


	public StudentProgressDto(String studentName, String lectureName, double progressRate) {
		super();
		this.studentName = studentName;
		this.lectureName = lectureName;
		this.progressRate = progressRate;
	}
	
    
    public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public double getProgressRate() {
		return progressRate;
	}
	public void setProgressRate(double progressRate) {
		this.progressRate = progressRate;
	}
    
    
	
}
