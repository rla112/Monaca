package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.LectureCategoryDto;
import com.dw.Monaca.dto.LectureDto;
import com.dw.Monaca.dto.LectureListDto;
import com.dw.Monaca.dto.ProfessorDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.LectureCategory;
import com.dw.Monaca.repository.LectureCategoryRepository;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.service.LectureService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {

	private final LectureRepository lectureRepository;
	private final LectureCategoryRepository lectureCategoryRepository;
	private final UserRepository userRepository;

	@Autowired
	public LectureServiceImpl(LectureRepository lectureRepository, LectureCategoryRepository lectureCategoryRepository,
			UserRepository userRepository) {
		this.lectureRepository = lectureRepository;
		this.lectureCategoryRepository = lectureCategoryRepository;
		this.userRepository = userRepository;
	}

	private User getAuthenticatedUser() {
		String currentLoginId = SecurityUtil.getCurrentLoginId()
				.orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
		return userRepository.findByLoginId(currentLoginId)
				.orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 모든 Lecture 불러오기
	@Override
	public ResponseDto<List<LectureListDto>> getAllLecture() {
		List<Lecture> lectures = lectureRepository.findAll();
		if (lectures.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "강의가 존재하지 않습니다.");
		}
		
		List<LectureListDto> lectureDtos = new ArrayList<>();
		lectures.stream().forEach(data -> {
			LectureListDto lectureDto = new LectureListDto();
			lectureDto.setLectureCategory(data.getLectureCategory().getCategoryName());
			lectureDto.setProfessor(data.getProfessor().getName());
			lectureDto.setSubTitle(data.getSubTitle());
			lectureDto.setPrice(data.getPrice());
			lectureDto.setId(data.getId());
			lectureDto.setImage(data.getImage());
			lectureDtos.add(lectureDto);
		});
		
		
		List<LectureCategory> lecture_category = lectureCategoryRepository.findAll();
		if (lecture_category.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "강의가 존재하지 않습니다.");
		}
		
		List<LectureCategoryDto> lectureCategoryDtos = new ArrayList<>();
		lecture_category.stream().forEach(data -> {
			LectureCategoryDto lectureCategoryDto = new LectureCategoryDto();
			lectureCategoryDto.setId(data.getId());
			lectureCategoryDto.setCategoryName(data.getCategoryName());
			lectureCategoryDto.setCategoryDescription1(data.getCategoryDescription1());
			lectureCategoryDto.setCategoryDescription2(data.getCategoryDescription2());
			lectureCategoryDtos.add(lectureCategoryDto);
		});
		
		LectureListDto tmpDto = new LectureListDto();
		tmpDto.setLectureCategoryList(lectureCategoryDtos);
		lectureDtos.add(tmpDto);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtos, ResultCode.SUCCESS.getMsg());
	}

	// Lecture 카테고리 별 불러오기
	@Override
	public ResponseDto<List<LectureListDto>> getAllLectureByCategoryName(String categoryName) {
		LectureCategory category = lectureCategoryRepository.findByCategoryName(categoryName);
		List<Lecture> lectures = lectureRepository.findByLectureCategory(category);

		if (lectures.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "해당 카테고리의 강의가 존재하지 않습니다.");
		}

		List<LectureListDto> lectureDtos = new ArrayList<>();
		lectures.stream().forEach(data -> {
			LectureListDto lectureDto = new LectureListDto();
			lectureDto.setLectureCategory(data.getLectureCategory().getCategoryName());
			lectureDto.setProfessor(data.getProfessor().getName());
			lectureDto.setLectureName(data.getLectureName());
			lectureDto.setSubTitle(data.getSubTitle());
			lectureDto.setPrice(data.getPrice());
			lectureDto.setId(data.getId());
			lectureDto.setImage(data.getImage());
			lectureDtos.add(lectureDto);

		});

		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtos, ResultCode.SUCCESS.getMsg());
	}

	// Professor 별 불러오기
	@Override
	public ResponseDto<List<LectureListDto>> getAllLectureByProfessor(String professorName) {
		Optional<User> professorOptional = userRepository.findByName(professorName);
		User professor = professorOptional.get();
		List<Lecture> lectures = lectureRepository.findByProfessor(professor);

		if (lectures.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "해당 교수님의 강의가 존재하지 않습니다.");
		}

		List<LectureListDto> lectureDtos = new ArrayList<>();
		lectures.stream().forEach(data -> {
			LectureListDto lectureDto = new LectureListDto();
			lectureDto.setLectureCategory(data.getLectureCategory().getCategoryName());
			lectureDto.setProfessor(data.getProfessor().getLoginId());
			lectureDto.setLectureName(data.getLectureName());
			lectureDto.setId(data.getId());
			lectureDto.setImage(data.getImage());
			lectureDto.setPrice(data.getPrice());
			lectureDtos.add(lectureDto);

		});
		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtos, ResultCode.SUCCESS.getMsg());
	}

	// 유료강의만 불러오기
	@Override
	public ResponseDto<List<LectureListDto>> getAllLectureByPaidLectures() {
		List<Lecture> paidLectures = lectureRepository.findByPriceGreaterThan(0);
		if (paidLectures.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "유료 강의가 존재하지 않습니다.");
		}

		List<LectureListDto> lectureDtos = new ArrayList<>();
		paidLectures.stream().forEach(data -> {
			LectureListDto lectureDto = new LectureListDto();
			lectureDto.setLectureCategory(data.getLectureCategory().getCategoryName());
			lectureDto.setProfessor(data.getProfessor().getName());
			lectureDto.setLectureName(data.getLectureName());
			lectureDto.setId(data.getId());
			lectureDto.setImage(data.getImage());
			lectureDto.setSubTitle(data.getSubTitle());
			lectureDto.setPrice(data.getPrice());
			lectureDtos.add(lectureDto);

		});

		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtos, ResultCode.SUCCESS.getMsg());
	}

	// 무료 강의만 불러오기
	@Override
	public ResponseDto<List<LectureListDto>> getAllLectureByFreeLectures() {
		List<Lecture> freeLectures = lectureRepository.findByPrice(0);
		if (freeLectures.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty", "무료 강의가 존재하지 않습니다.");
		}

		List<LectureListDto> lectureDtos = new ArrayList<>();
		freeLectures.stream().forEach(data -> {
			LectureListDto lectureDto = new LectureListDto();
			lectureDto.setLectureCategory(data.getLectureCategory().getCategoryName());
			lectureDto.setLectureName(data.getLectureName());
			lectureDto.setId(data.getId());
			lectureDto.setImage(data.getImage());
			lectureDto.setSubTitle(data.getSubTitle());
			lectureDto.setPrice(data.getPrice());
			lectureDtos.add(lectureDto);

		});

		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtos, ResultCode.SUCCESS.getMsg());
	}

	// LecureID로 특정 강의 불러오기
	@Override
	public ResponseDto<LectureDto> getLectureById(Long id) {

		Lecture lecture = lectureRepository.findById(id)
				.orElseThrow(() -> new InvalidRequestException("Lecture Empty", "강의가 존재하지 않습니다."));

		LectureDto lectureDto = new LectureDto();
		lectureDto.setLectureCategory(lecture.getLectureCategory().getCategoryName());
		lectureDto.setProfessor(lecture.getProfessor().getName());
		lectureDto.setProfessorLoginId(lecture.getProfessor().getLoginId());
		lectureDto.setLectureName(lecture.getLectureName());
		lectureDto.setSubTitle(lecture.getSubTitle());
		lectureDto.setLectureDescription1(lecture.getLectureDescription1());
		lectureDto.setLectureDescription2(lecture.getLectureDescription2());
		lectureDto.setLectureDescription3(lecture.getLectureDescription3());
		lectureDto.setLectureDescription4(lecture.getLectureDescription4());
		lectureDto.setLearnigObjectives(lecture.getLearnigObjectives());
		lectureDto.setLectureStructure(lecture.getLectureStructure());
		lectureDto.setCourseTarget(lecture.getCourseTarget());
		lectureDto.setBeforeListening(lecture.getBeforeListening());
		lectureDto.setId(lecture.getId());
		lectureDto.setAuthor(lecture.getAuthor().getLoginId());
		lectureDto.setLecturePlayTime(lecture.getLecturePlayTime());
		lectureDto.setImage(lecture.getImage());
		lectureDto.setPrice(lecture.getPrice());
		lectureDto.setVideo(lecture.getVideo());
		lectureDto.setCreateAt(lecture.getCreateAt().toString());

		
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDto, ResultCode.SUCCESS.getMsg());
	}

	// Lecture 업로드
	@Override
	public ResponseDto<Lecture> createLecture(LectureDto lectureDto) {
		User author = getAuthenticatedUser();
		String professorName = lectureDto.getProfessor();
		Optional<User> professorOptional = userRepository.findByName(professorName);
		User professor = professorOptional.get();
		LectureCategory lectureCategory = lectureCategoryRepository.findByCategoryName(lectureDto.getLectureCategory());

		// Lecture 객체 생성
		Lecture lecture = new Lecture();
		lecture.setCreateAt(LocalDateTime.now());
		lecture.setAuthor(author);
		lecture.setProfessor(professor);
		lecture.setLectureCategory(lectureCategory);
		lecture.setLectureName(lectureDto.getLectureName());
		lecture.setPrice(lectureDto.getPrice());
		lecture.setLecturePlayTime(lectureDto.getLecturePlayTime());
		lecture.setVideo(lectureDto.getVideo());
		lecture.setImage(lectureDto.getImage());
		lecture.setSubTitle(lectureDto.getSubTitle());
		lecture.setLectureDescription1(lectureDto.getLectureDescription1());
		lecture.setLectureDescription2(lectureDto.getLectureDescription2());
		lecture.setLectureDescription3(lectureDto.getLectureDescription3());
		lecture.setLectureDescription4(lectureDto.getLectureDescription4());
		lecture.setLearnigObjectives(lectureDto.getLearnigObjectives());
		lecture.setLectureStructure(lectureDto.getLectureStructure());
		lecture.setCourseTarget(lectureDto.getCourseTarget());
		lecture.setBeforeListening(lectureDto.getBeforeListening());

		lectureRepository.save(lecture);
//	    // 해당 강의를 가르치는 교수의 '가르치는 강의' 목록에 이 강의 추가
		professor.getLecture().add(lecture);

//	    // 변경 사항 데이터베이스에 저장
		userRepository.save(professor);

		return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "강의 등록이 완료되었습니다.");
	}

	// LectureID로 특정 강의 삭제
	@Override
	public ResponseDto<String> deleteLectureById(Long id) {

		Lecture lecture = lectureRepository.findById(id)
				.orElseThrow(() -> new InvalidRequestException("Lecture Empty", "강의가 존재하지 않습니다."));

		List<User> users = userRepository.findByLectureContaining(lecture);

		for (User user : users) {
			user.getLecture().remove(lecture);
			userRepository.save(user);
		}

		lectureRepository.delete(lecture);

		return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "강의가 성공적으로 삭제되었습니다.");
	}

	
	// LectureID로 특정 강의의 교수 불러오기
	@Override
	public ResponseDto<ProfessorDto> getProfessorById(Long id){
		Optional<Lecture> lectureOptional = lectureRepository.findById(id);
		
		if(lectureOptional.isEmpty()) {
			throw new InvalidRequestException("Lecture Empty","해당 강의가 존재하지 않습니다.");
		}
		
		Lecture lecture = lectureOptional.get();
		User professorIntro = lecture.getProfessor();
		
		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setName(professorIntro.getName());
		professorDto.setProfessorIntro(professorIntro.getProfessorIntro());
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),professorDto,ResultCode.SUCCESS.getMsg());
		
		
	}
	
	// LectureCategory로 특정 교수 리스트 불러오기
	@Override
	public ResponseDto<List<ProfessorDto>> getProfessorByLectureCategoryName(String lectureCategoryName){
		 LectureCategory category = lectureCategoryRepository.findByCategoryName(lectureCategoryName);
	        if(category == null) {
	            throw new InvalidRequestException ("Category not found","해당 카테고리를 찾을 수 없습니다.");
	        }
	        
	        List<User> professors = lectureRepository.findDistinctProfessorsByLectureCategory(category);
	        List<ProfessorDto> professorDtos = new ArrayList<>();
	        for(User professor : professors) {
	            ProfessorDto dto = new ProfessorDto();
	            dto.setEmail(professor.getEmail());
	            dto.setProfessorResume(professor.getProfessorResume());
	            dto.setName(professor.getName());
	            dto.setImage(professor.getImage());
	            // Add other professor attributes here
	            professorDtos.add(dto);
	        }
	        
	        return new ResponseDto<>(ResultCode.SUCCESS.name(),professorDtos,ResultCode.SUCCESS.getMsg());
	    }
	}
	
	
//	// LectureID로 특정 강의 수정
//	@Override
//	public ResponseDto<Lecture> updateLectureById(Lecture updateLecture, Long id) {
//	}

