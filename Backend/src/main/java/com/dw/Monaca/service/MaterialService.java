package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.MaterialDto;
import com.dw.Monaca.dto.MaterialListDto;
import com.dw.Monaca.dto.ResponseDto;

// 강의 자료

public interface MaterialService {
	// 강의 자료 미리 저장 후 교수가 원하는 때에 공개되도록 hidden이 false일 떄만 학생들의 classroom에 보여지도록
	// hidden여부에 상관없이 교수에게는 모든 강의 자료가 보임


	// 전체 강의 자료 불러오기(관리자용)
	ResponseDto<List<MaterialListDto>> getAllMaterial();
	
	// 강의 별 강의 자료 불러오기(is_reservation false만)
	ResponseDto<List<MaterialListDto>> getMaterialByLecture(String lectureName);

	// 강의 별 강의 자료 불러오기(is_reservation ture만 가능)
	ResponseDto<List<MaterialListDto>> getReservationMaterialByLectureForProfessor(String lectureName);
	
	// 강의 별 강의 자료 불러오기(is_reservation ture, false 모두 가능)
	ResponseDto<List<MaterialListDto>> getMaterialByLectureForProfessor(String lectureName);

	// MaterialID로 특정 강의자료 불러오기
	ResponseDto<MaterialDto> getMaterialById(Long id);
	
	// 강의 자료 임시 업로드(초기엔 isReservation이 ture임. 강의 자료를 다 완성하고 난 뒤에 정식 업로드를 해야함. 초기엔 임시저장)
	ResponseDto<MaterialDto> reservationMaterial(MaterialDto materialDto);
	
	// 강의자료 업로드(작성자인 교수와 관리자만 가능, isReservation을 false로 바꿔 정식 업로드)
	ResponseDto<MaterialDto> uploadMaterial(Long id);
	
	// 강의 자료 수정(작성자인 교수와 관리자만 가능, 임시저장으로 업데이트)
	ResponseDto<MaterialDto> updateMaterial(Long id, MaterialDto updateMaterial);
	
	// 강의 자료 삭제(작성자인 교수와 관리자만 가능)
	ResponseDto<String> deleteMaterial(Long id);
	
//	// 강의 자료 수정(관리자용)
//	ResponseDto<Material> updateMaterialForAdmin(MaterialDto updateMaterial);
//	// 강의 자료 삭제(관리자용)
//	ResponseDto<Material> deleteMaterialForAdmin(Long id);
	
}
