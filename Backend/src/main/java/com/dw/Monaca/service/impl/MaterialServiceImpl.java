package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.MaterialDto;
import com.dw.Monaca.dto.MaterialListDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.Material;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.MaterialRepository;
import com.dw.Monaca.service.MaterialService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService{

	private final MaterialRepository materialRepository;
	private final UserRepository userRepository;
	private final LectureRepository lectureRepository;
	
	
	@Autowired
	public MaterialServiceImpl(MaterialRepository materialRepository, UserRepository userRepository,
			LectureRepository lectureRepository) {
		this.materialRepository = materialRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	// 전체 강의 자료 불러오기(관리자용)
	@Override
	public ResponseDto<List<MaterialListDto>> getAllMaterial() {
		getAuthenticatedUser();
		List<Material> materials = materialRepository.findAll();
		
		if(materials.isEmpty()) {
			throw new InvalidRequestException("material Empty", "강의 자료가 존재하지 않습니다.");
		}
		
		List<MaterialListDto> materialListDtos = new ArrayList<>();
		materials.stream().forEach(data -> {
			MaterialListDto materialListDto = new MaterialListDto();
			materialListDto.setAuthor(data.getAuther().getName());
			materialListDto.setLectureName(data.getLecture().getLectureName());
			materialListDto.setProfessor(data.getProfessor().getName());
			materialListDto.setTitle(data.getTitle());
			materialListDto.setReservation(data.isReservation());
			materialListDtos.add(materialListDto);
		});
		return new ResponseDto<>(ResultCode.SUCCESS.name(),materialListDtos,ResultCode.SUCCESS.getMsg());
	}


	// 강의 별 강의 자료 불러오기(is_reservation false만)
	@Override
	public ResponseDto<List<MaterialListDto>> getMaterialByLecture(String lectureName) {
	    Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
	    if(lectureOptional.isEmpty()) {
	    	throw new InvalidRequestException("lecture Empty","해당 강의명을 가진 강의가 존재하지 않습니다.");
	    }
	    Lecture lecture = lectureOptional.get();
	    
	    // Lecture 객체와 is_reservation 필드의 값이 false인 Material 객체를 찾는 메소드
	    List<Material> materials = materialRepository.findByLectureAndIsReservation(lecture, false);
	    
	    List<MaterialListDto> materialListDtos = new ArrayList<>();
		materials.stream().forEach(data -> {
			MaterialListDto materialListDto = new MaterialListDto();
			materialListDto.setAuthor(data.getAuther().getName());
			materialListDto.setLectureName(data.getLecture().getLectureName());
			materialListDto.setProfessor(data.getProfessor().getName());
			materialListDto.setTitle(data.getTitle());
			materialListDto.setReservation(data.isReservation());
			materialListDto.setCreateAt(data.getCreateAt().toString());
			materialListDtos.add(materialListDto);
		});
	    
	    return new ResponseDto<>(ResultCode.SUCCESS.name(),materialListDtos,ResultCode.SUCCESS.getMsg());
	}

	// 강의 별 강의 자료 불러오기(is_reservation ture만 가능)
	@Override
	public ResponseDto<List<MaterialListDto>> getReservationMaterialByLectureForProfessor(String lectureName) {
		  Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		    if(lectureOptional.isEmpty()) {
		    	throw new InvalidRequestException("lecture Empty","해당 강의명을 가진 강의가 존재하지 않습니다.");
		    }
		    Lecture lecture = lectureOptional.get();
		    
		    // Lecture 객체와 is_reservation 필드의 값이 true인 Material 객체를 찾는 메소드
		    List<Material> materials = materialRepository.findByLectureAndIsReservation(lecture, true);
		    List<MaterialListDto> materialListDtos = new ArrayList<>();
			materials.stream().forEach(data -> {
				MaterialListDto materialListDto = new MaterialListDto();
				materialListDto.setAuthor(data.getAuther().getName());
				materialListDto.setLectureName(data.getLecture().getLectureName());
				materialListDto.setProfessor(data.getProfessor().getName());
				materialListDto.setTitle(data.getTitle());
				materialListDto.setReservation(data.isReservation());
				materialListDto.setCreateAt(data.getCreateAt().toString());
				materialListDtos.add(materialListDto);
			});
		    
		    return new ResponseDto<>(ResultCode.SUCCESS.name(),materialListDtos,ResultCode.SUCCESS.getMsg());
		}
	
	// 강의 별 강의 자료 불러오기(is_reservation ture, false 모두 가능)
	@Override
	public ResponseDto<List<MaterialListDto>> getMaterialByLectureForProfessor(String lectureName) {
		 Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		    if(lectureOptional.isEmpty()) {
		    	throw new InvalidRequestException("lecture Empty","해당 강의명을 가진 강의가 존재하지 않습니다.");
		    }
		    Lecture lecture = lectureOptional.get();
		    List<Material> materials = materialRepository.findByLecture(lecture);
		    
		    List<MaterialListDto> materialListDtos = new ArrayList<>();
			materials.stream().forEach(data -> {
				MaterialListDto materialListDto = new MaterialListDto();
				materialListDto.setAuthor(data.getAuther().getName());
				materialListDto.setLectureName(data.getLecture().getLectureName());
				materialListDto.setProfessor(data.getProfessor().getName());
				materialListDto.setTitle(data.getTitle());
				materialListDto.setReservation(data.isReservation());
				materialListDto.setCreateAt(data.getCreateAt().toString());
				materialListDtos.add(materialListDto);
			});
			return new ResponseDto<>(ResultCode.SUCCESS.name(), materialListDtos, ResultCode.SUCCESS.getMsg());
	}
	
	// MaterialID로 특정 강의자료 불러오기
	@Override
	public ResponseDto<MaterialDto> getMaterialById(Long id) {
		getAuthenticatedUser();
		Optional<Material> materialOptional = materialRepository.findById(id);
		if(materialOptional.isEmpty()) {
			throw new InvalidRequestException("Material Empty","해당 강의 자료가 존재하지 않습니다.");
		}
		Material material = materialOptional.get();
		
		MaterialDto materialDtos = new MaterialDto();
		materialDtos.setAuthor(material.getAuther().getName());
		materialDtos.setLectureName(material.getLecture().getLectureName());
		materialDtos.setTitle(material.getTitle());
		materialDtos.setText(material.getText());
		materialDtos.setImage(material.getImage());
		materialDtos.setReservation(material.isReservation());
		materialDtos.setCreateAt(material.getCreateAt().toString());
		materialDtos.setProfessor(material.getProfessor().getName());
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),materialDtos,ResultCode.SUCCESS.getMsg());
	}

	// 강의 자료 임시 업로드(초기엔 isReservation이 ture임. 강의 자료를 다 완성하고 난 뒤에 정식 업로드를 해야함. 초기엔 임시저장)
	@Override
	public ResponseDto<MaterialDto> reservationMaterial(MaterialDto materialDto) {
	
		User author = getAuthenticatedUser();
		Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(materialDto.getLectureName());
		Lecture lecture = lectureOptional.get();
		
		Optional<User> professorOptional = userRepository.findByName(materialDto.getProfessor());
		User professor = professorOptional.get();
		Material material = new Material();
		material.setCreateAt(LocalDateTime.now());
		material.setLecture(lecture);
		material.setAuther(author);
		material.setProfessor(professor);
		material.setTitle(materialDto.getTitle());
		material.setText(materialDto.getText());
		material.setImage(materialDto.getImage());
		material.setReservation(true);
		// 처음엔 예약 상태로 올림. 강의 자료가 업로드 할 준비가 되면 나중에 false 상태로 변경해야 함.
		materialRepository.save(material);
		
		MaterialDto materialDtos = new MaterialDto();
		materialDtos.setAuthor(material.getAuther().getName());
		materialDtos.setLectureName(material.getLecture().getLectureName());
		materialDtos.setTitle(material.getTitle());
		materialDtos.setText(material.getText());
		materialDtos.setImage(material.getImage());
		materialDtos.setReservation(material.isReservation());
		materialDtos.setCreateAt(material.getCreateAt().toString());
		materialDtos.setProfessor(material.getProfessor().getName());
		
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),materialDtos,ResultCode.SUCCESS.getMsg());
	}

	// 강의자료 업로드(isReservation을 false로 바꿔 정식 업로드)
	@Override
	public ResponseDto<MaterialDto> uploadMaterial(Long id) {
		User author = getAuthenticatedUser();
		Optional<Material> materialOptional = materialRepository.findById(id);
		if(materialOptional.isEmpty()) {
			throw new InvalidRequestException("Material Not Found","해당 강의자료를 찾을 수 없습니다.");
		}
		Material material = materialOptional.get();
		boolean isAuthor = author.equals(material.getProfessor());
		boolean isAdmin = author.getAuthorities().stream().anyMatch(auth->auth.getAuthorityName().equals("ROLE_ADMIN"));
		
		if(!(isAuthor||isAdmin)) {
			throw new InvalidRequestException("Unauthorized","Material를 업로드할 권한이 없습니다.");
		}
		material.setReservation(false);
		material.setCreateAt(LocalDateTime.now());
		materialRepository.save(material);
		
		MaterialDto materialDtos = new MaterialDto();
		materialDtos.setAuthor(material.getAuther().getName());
		materialDtos.setLectureName(material.getLecture().getLectureName());
		materialDtos.setTitle(material.getTitle());
		materialDtos.setText(material.getText());
		materialDtos.setImage(material.getImage());
		materialDtos.setReservation(material.isReservation());
		materialDtos.setCreateAt(material.getCreateAt().toString());
		materialDtos.setProfessor(material.getProfessor().getName());
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),materialDtos,ResultCode.SUCCESS.getMsg());
	}

	// 강의 자료 수정(작성자인 교수와 관리자만 가능)
	@Override
	public ResponseDto<MaterialDto> updateMaterial(Long id, MaterialDto updateMaterial) {
		User author = getAuthenticatedUser();
		Optional<Material> materialOptional = materialRepository.findById(id);
		if(materialOptional.isEmpty()) {
			throw new InvalidRequestException("Material Not Found","해당 강의자료를 찾을 수 없습니다.");
		}
		Material material = materialOptional.get();
		boolean isAuthor = author.equals(material.getProfessor());
		boolean isAdmin = author.getAuthorities().stream().anyMatch(auth->auth.getAuthorityName().equals("ROLE_ADMIN"));
		
		if(!(isAuthor||isAdmin)) {
			throw new InvalidRequestException("Unauthorized","Material를 업로드할 권한이 없습니다.");
		}
		
		material.setTitle(updateMaterial.getTitle());
		material.setText(updateMaterial.getText());
		material.setImage(updateMaterial.getImage());
		materialRepository.save(material);
		
		MaterialDto materialDtos = new MaterialDto();
		materialDtos.setAuthor(material.getAuther().getName());
		materialDtos.setLectureName(material.getLecture().getLectureName());
		materialDtos.setTitle(material.getTitle());
		materialDtos.setText(material.getText());
		materialDtos.setImage(material.getImage());
		materialDtos.setReservation(material.isReservation());
		materialDtos.setCreateAt(material.getCreateAt().toString());
		materialDtos.setProfessor(material.getProfessor().getName());
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),materialDtos,ResultCode.SUCCESS.getMsg());
	}

	// 강의 자료 삭제(작성자인 교수와 관리자만 가능)
	@Override
	public ResponseDto<String> deleteMaterial(Long id) {
		User author = getAuthenticatedUser();
		Optional<Material> materialOptional = materialRepository.findById(id);
		if(materialOptional.isEmpty()) {
			throw new InvalidRequestException("Material Not Found","해당 강의자료를 찾을 수 없습니다.");
		}
		Material material = materialOptional.get();
		boolean isAuthor = author.equals(material.getProfessor());
		boolean isAdmin = author.getAuthorities().stream().anyMatch(auth->auth.getAuthorityName().equals("ADMIN"));
		
		if(!(isAuthor||isAdmin)) {
			throw new InvalidRequestException("Unauthorized","Material를 업로드할 권한이 없습니다.");
		}
		materialRepository.deleteById(id);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"강의자료 삭제가 완료되었습니다.");
	}


}
