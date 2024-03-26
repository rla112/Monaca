package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Grade;

public interface GradeService {

	
	// Grade를 생성
	ResponseDto<Grade> createGrade(Grade grade);
	
	// Grade를 수정
	ResponseDto<Grade> updateGrade(Long id, Grade updateGrade);
	
	// Grade를 삭제
	ResponseDto<String> deleteGradeById(Long id);
	
	// Grade를 불러오기
	ResponseDto<List<Grade>> getAllGrade();
	
	// Grade에 미리 gradedName과 standard 정보가 있어야 이를 바탕으로 계산 후 등급이 결정 됨.
	// Examination의 isCorrect결과를 바탕으로 총점 계산, 그 계산을 바탕으로 총점에 따른 등급 결정
	ResponseDto<Grade> calculateAndAssignGrade(String lectureName);

}
