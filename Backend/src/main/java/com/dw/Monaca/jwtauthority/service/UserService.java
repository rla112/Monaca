package com.dw.Monaca.jwtauthority.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.Monaca.dto.LectureDto;
import com.dw.Monaca.dto.LectureListDto;
import com.dw.Monaca.dto.ProfessorDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.UserAuthorityDto;
import com.dw.Monaca.dto.UserDeleteRequestDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.dto.AuthorityDto;
import com.dw.Monaca.jwtauthority.dto.UserDto;
import com.dw.Monaca.jwtauthority.model.Authority;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.AuthorityRepository;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.Rating;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.RatingRepository;

@Transactional
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthorityRepository authorityRepository;
	private final RatingRepository ratingRepository;
	private final LectureRepository lectureRepository;
	

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthorityRepository authorityRepository, RatingRepository ratingRepository,
			LectureRepository lectureRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepository = authorityRepository;
		this.ratingRepository = ratingRepository;
		this.lectureRepository = lectureRepository;
	}

	public User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	//  Authority 객체 하나를 받아서 해당 객체를 AuthorityDto로 변환하는 역할
	private AuthorityDto convertToAuthorityDto(Authority authority) {
		AuthorityDto authorityDto = new AuthorityDto();
		authorityDto.setAuthorityName(authority.getAuthorityName());
		return authorityDto;
	}
	// Authority 객체들의 집합을 받아서 각각의 객체를 AuthorityDto로 변환하고, 그 결과들을 다시 집합으로 만드는 역할
	private Set<AuthorityDto> convertToAuthorityDtoSet(Set<Authority> authorities) {
	    return authorities.stream()
	            .map(this::convertToAuthorityDto)
	            .collect(Collectors.toSet());
	}

	// Lecture 객체 하나를 받아서 해당 객체를 LectureDto로 변환하는 역할
	private LectureDto convertToLectureDto(Lecture lecture) {
		LectureDto lectureDto = new LectureDto();
		lectureDto.setId(lecture.getId());
		lectureDto.setLectureCategory(lecture.getLectureCategory().getCategoryName());
		lectureDto.setLectureName(lecture.getLectureName());
		lectureDto.setImage(lecture.getImage());
		lectureDto.setSubTitle(lecture.getSubTitle());
		return lectureDto;
	}
	
	private Set<LectureDto> convertToLectureDtoSet(Set<Lecture> lectures) {
		return lectures.stream()
				.map(this::convertToLectureDto)
				.collect(Collectors.toSet());
	}
    
    // User를 UserDto로 변환하는 함수
    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLoginId(user.getLoginId());
        userDto.setName(user.getName());
        userDto.setNickname(user.getNickname());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNum(user.getPhoneNum());
        userDto.setCreateAt(user.getCreateAt());
//        Set<AuthorityDto> authorityDtoSet = user.getAuthorities().stream()
//                .map(this::convertAuthorityToDto)
//                .collect(Collectors.toSet());
     // Authority를 AuthorityDto로 변환하는 부분을 수정
        Set<AuthorityDto> authorityDtoSet = convertToAuthorityDtoSet(user.getAuthorities());
        userDto.setAuthorityDtoSet(authorityDtoSet);

            return userDto;
    }
	
    // ProfeesorDto로 변환하는 함수
    public ProfessorDto convertUserToProfessorDto(User user) {
    	ProfessorDto professorDto = new ProfessorDto();
    	professorDto.setLoginId(user.getLoginId());
    	professorDto.setName(user.getName());
    	professorDto.setNickname(user.getNickname());
    	professorDto.setBirthDate(user.getBirthDate());
    	professorDto.setGender(user.getGender());
    	professorDto.setEmail(user.getEmail());
    	professorDto.setPhoneNum(user.getPhoneNum());
    	professorDto.setImage(user.getImage());
    	professorDto.setProfessorIntro(user.getProfessorIntro());
    	professorDto.setProfessorResume(user.getProfessorResume());
    	// Authority를 AuthorityDto로 변환하는 부분을 수정
        Set<AuthorityDto> authorityDtoSet = convertToAuthorityDtoSet(user.getAuthorities());
        professorDto.setAuthorityDtoSet(authorityDtoSet);
        Set<LectureDto> lectureDtoSet = convertToLectureDtoSet(user.getLecture());
        professorDto.setLecture(lectureDtoSet);
		return professorDto;
    }
    
	@Transactional
	// 유저(일반 학생회원) 가입하기
	public ResponseDto<UserDto> signupForUser(UserDto userDto) {
	    if (userRepository.findOneWithAuthoritiesByLoginId(userDto.getLoginId()).orElse(null) != null) {
	        throw new InvalidRequestException("Duplicated member", "이미 가입되어 있는 유저입니다."); 
	    }

	    Authority authority = authorityRepository.findByAuthorityName("ROLE_USER")
	            .orElseGet(() -> {
	                Authority newAuthority = new Authority();
	                newAuthority.setAuthorityName("ROLE_USER");
	                return authorityRepository.save(newAuthority);
	            });

	    Rating rating = ratingRepository.findByCandyGrade("RED_CANDY")
	            .orElseGet(() -> {
	                Rating newRating = new Rating("RED_CANDY", "image_path");
	                return ratingRepository.save(newRating);
	            });
	    
	    User user = new User();
	    user.setCreateAt(LocalDateTime.now());
	    user.setLoginId(userDto.getLoginId());
	    user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
	    user.setName(userDto.getName());
	    user.setNickname(userDto.getNickname());
	    user.setBirthDate(userDto.getBirthDate());
	    user.setGender(userDto.getGender());
	    user.setEmail(userDto.getEmail());
	    user.setPhoneNum(userDto.getPhoneNum());
	    user.setAuthorities(Collections.singleton(authority));
	    user.setRatings(Collections.singleton(rating));
	    user.setActivated(true);

	    UserDto savedUserDto = UserDto.from(userRepository.save(user));

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedUserDto, ResultCode.SUCCESS.getMsg());
	}

	@Transactional
	// 유저(교수) 가입하기
	public ResponseDto<User> signupForProfessor(ProfessorDto professorDto) {
	    if (userRepository.findOneWithAuthoritiesByLoginId(professorDto.getLoginId()).orElse(null) != null) {
	        throw new InvalidRequestException("Duplicated member", "이미 가입되어 있는 유저입니다."); 
	    }

	    Authority authority = authorityRepository.findByAuthorityName("ROLE_USER")
	            .orElseGet(() -> {
	                Authority newAuthority = new Authority();
	                newAuthority.setAuthorityName("ROLE_USER");
	                return authorityRepository.save(newAuthority);
	            }); // 유저 권한

	    Rating rating = ratingRepository.findByCandyGrade("RED_CANDY")
	            .orElseGet(() -> {
	                Rating newRating = new Rating("RED_CANDY", "image_path");
	                return ratingRepository.save(newRating);
	            });
	    
		User professor = new User();
		professor.setCreateAt(LocalDateTime.now());
		professor.setLoginId(professorDto.getLoginId());
		professor.setPassword(passwordEncoder.encode(professorDto.getPassword())); // 암호화
		professor.setName(professorDto.getName());
		professor.setNickname(professorDto.getNickname());
		professor.setBirthDate(professorDto.getBirthDate());
		professor.setGender(professorDto.getGender());
		professor.setEmail(professorDto.getEmail());
		professor.setPhoneNum(professorDto.getPhoneNum());
		professor.setProfessorIntro(professorDto.getProfessorIntro());
		professor.setProfessorResume(professorDto.getProfessorResume());
		professor.setImage(professorDto.getImage());
		professor.setAuthorities(Collections.singleton(authority)); // 권한
	    professor.setRatings(Collections.singleton(rating));
		professor.setActivated(true);
		
		User savedProfessor = userRepository.save(professor);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(), savedProfessor, ResultCode.SUCCESS.getMsg());
	}


	private boolean isRegularUser(User user) {
	    return user.getAuthorities().stream()
	            .noneMatch(authority -> authority.getAuthorityName().equals("ROLE_ADMIN") 
	                                 || authority.getAuthorityName().equals("ROLE_PROFESSOR"));
	}
//	권한이 'ROLE_ADMIN'이나 'ROLE_PROFESSOR'를 가지고 있지 않은 유저를 찾기
	@Transactional(readOnly = true)
	public ResponseDto<List<UserDto>> findUsersWithRoleUserOnly() {
	    // 모든 유저를 가져옴
	    List<User> allUsers = userRepository.findAll();
	    
	    // 'ROLE_ADMIN'이나 'ROLE_PROFESSOR' 권한이 없는 유저만 필터링
	    // 'ROLE_ADMIN'이나 'ROLE_PROFESSOR' 권한이 없는 유저만 필터링
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), allUsers.stream()
	            .filter(this::isRegularUser)
	            .map(this::convertUserToDto)  // User를 UserDto로 변환
	            .collect(Collectors.toList()),ResultCode.SUCCESS.getMsg());
	}



	
	// 교수가입유저 불러오기
	@Transactional
	public ResponseDto<List<ProfessorDto>> getUserByProfessorIntro() {
	List<User> users = userRepository.findByProfessorIntroIsNotNullAndProfessorResumeIsNotNull();
		if(users.isEmpty()) {
		 throw new InvalidRequestException("user Empty","Professor SignUp List is empty");
		 }
		List<ProfessorDto> professorDtoList = users.stream()
	            .map(this::convertUserToProfessorDto)
	            .collect(Collectors.toList());
	    
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), professorDtoList, ResultCode.SUCCESS.getMsg());
		}
	
	// 유저에게 교수권한 부여하기
	public ResponseDto<UserDto> addAuthenticateByUser(String loginId){
	    getAuthenticatedUser();
	    Optional<User> userOptional = userRepository.findByLoginId(loginId);
	    if (!userOptional.isPresent()) {
	        throw new InvalidRequestException("No such user", "해당 사용자를 찾을 수 없습니다.");
	    }

	    User user = userOptional.get();
	    Authority authority = authorityRepository.findByAuthorityName("ROLE_PROFESSOR")
	            .orElseGet(() -> {
	                Authority newAuthority = new Authority();
	                newAuthority.setAuthorityName("ROLE_PROFESSOR");
	                return authorityRepository.save(newAuthority);
	            }); 
	    Rating rating = ratingRepository.findByCandyGrade("RAINBOW_CANDY")
	            .orElseGet(() -> {
	                Rating newRating = new Rating("RAINBOW_CANDY", "image_path");
	                return ratingRepository.save(newRating);
	            });

	    // 기존 권한에 새로운 권한 추가
	    Set<Authority> authorities = new HashSet<>(user.getAuthorities());
	    authorities.add(authority);
	    user.setAuthorities(authorities);

	    // 기존 등급에 새로운 등급으로 변경
	    Set<Rating> ratings = new HashSet<>();
	    ratings.add(rating);
	    user.setRatings(ratings);

	    User savedUser = userRepository.save(user);
	    UserDto userDto = new UserDto();
	    userDto.setLoginId(savedUser.getLoginId());
	    userDto.setName(savedUser.getName());
	    userDto.setBirthDate(savedUser.getBirthDate());
	    userDto.setEmail(savedUser.getEmail());
	    userDto.setGender(savedUser.getGender());
	    userDto.setNickname(savedUser.getNickname());
	    Set<AuthorityDto> authorityDtos = savedUser.getAuthorities().stream()
	    	    .map(auth -> new AuthorityDto(authority.getAuthorityName())) // Authority를 AuthorityDto로 변환
	    	    .collect(Collectors.toSet());

	    	userDto.setAuthorityDtoSet(authorityDtos);

	    
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userDto, "교수 권한이 성공적으로 부여되었습니다.");
	}

	
	// LoginId를 이용해 특정 유저 정보 받아오기(관리자용)
	@Transactional(readOnly = true)
	public ResponseDto<UserDto> getUserWithAuthorities(String loginId) {
	    UserDto userDto = UserDto.from(userRepository.findOneWithAuthoritiesByLoginId(loginId)
	            .orElseThrow(() -> new InvalidRequestException(loginId, "user not found")));
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userDto, ResultCode.SUCCESS.getMsg());
	}
	
	// LoginId를 이용해 유저 정보 일부분을 받아오기(lectureInfo용)
	@Transactional(readOnly = true)
	public ResponseDto<ProfessorDto> getPartOfUserInfo(String loginId){
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User Empty", "해당 로그인 아이디를 가진 유저가 존재하지 않습니다.");
		}
		
		User user = userOptional.get();
		// ProfeesorDto로 변환하는 함수
	    ProfessorDto professorDto = new ProfessorDto();
	    professorDto.setLoginId(user.getLoginId());
	    professorDto.setName(user.getName());
	    professorDto.setBirthDate(user.getBirthDate());
	    professorDto.setEmail(user.getEmail());
	    professorDto.setImage(user.getImage());
	    professorDto.setProfessorIntro(user.getProfessorIntro());
	    professorDto.setProfessorResume(user.getProfessorResume());
	    Set<LectureDto> lectureDtoSet = user.getLecture().stream()
	    	    .map(this::convertToLectureDto)
	    	    .collect(Collectors.toSet());

	    	professorDto.setLecture(lectureDtoSet);
			
		return new ResponseDto<>(ResultCode.SUCCESS.name(),professorDto,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 권한 조회하기
	@Transactional(readOnly = true)
	public ResponseDto<UserDto> getCurrentUserWithAuthorities() {
	    UserDto userDto = UserDto.from(SecurityUtil.getCurrentLoginId().flatMap(userRepository::findOneWithAuthoritiesByLoginId)
	            .orElseThrow(() -> new InvalidRequestException("No current user", "Current user not found")));
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userDto, ResultCode.SUCCESS.getMsg());
	}

	
	// 모든 유저의 권한 조회하기
	@Transactional(readOnly = true)
	public ResponseDto<List<UserAuthorityDto>> getAuthorities() {
	    getAuthenticatedUser();
	    List<UserAuthorityDto> users = userRepository.findAll()
	        .stream()
	        .map(user -> new UserAuthorityDto(user.getName(), user.getLoginId(), user.getAuthorities()))
	        .collect(Collectors.toList());
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), users, ResultCode.SUCCESS.getMsg());
	}

	
	// 회원 탈퇴(본인이 직접) 기능
//	 아래 코드는 현재 물리적 삭제로만 구현됨. 유저를 논리적 삭제 후 데이터 수명주기 관리로 일정 기간이 지나 물리적 삭제 진행
//	 관련 데이터는 유저 데이터를 논리적 삭제로 진행 시에 연쇄삭제를 논리삭제로만 진행되도록 코드 작성하기!
//	 회원 탈퇴 시 현재 가입한 유저의 loginId 비교 + 프론트에서 비밀번호를 한번 더 입력해서 탈퇴하려는 유저의 비밀번호와 일치하면 삭제 진행하도록 하기
	@Transactional
	public ResponseDto<String> deleteByUserForSelf(UserDeleteRequestDto userDeleteRequestDto) {

	    String loginId = getAuthenticatedUser().getLoginId();
	    User user = userRepository.findByLoginId(loginId)
	            .orElseThrow(() -> new InvalidRequestException("User Not Found", "해당 사용자를 찾을 수 없습니다."));

	    if (!passwordEncoder.matches(userDeleteRequestDto.getPassword(), user.getPassword())) {
	        throw new InvalidRequestException("Invalid Password", "비밀번호가 일치하지 않습니다.");
	    }

	    userRepository.delete(user);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"회원 탈퇴가 성공적으로 이루어졌습니다.");
	}

	// 회원 박탈(관리자가 진행)
	@Transactional
	public ResponseDto<String> deleteByUser(String loginId) {
	    getAuthenticatedUser();
	    User user = userRepository.findByLoginId(loginId)
	            .orElseThrow(() -> new InvalidRequestException("User Not Found", "해당 사용자를 찾을 수 없습니다."));

	    userRepository.delete(user);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"회원박탈이 성공적으로 이루어졌습니다.");
	}
	
	//회원가입을 교수로 잘못 한 유저의 정보를 수정하기
	@Transactional
	public ResponseDto<User> changeInfoByUser(String loginId){
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("USER is empty","해당 loginId를 가진 User가 존재하지 않습니다.");
		}
		User user = userOptional.get();
		user.setProfessorIntro(null);
		user.setProfessorResume(null);
		user.setImage(null);
		User savedUser = userRepository.save(user);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedUser,ResultCode.SUCCESS.getMsg());
	}
	

	// 유저별로 가입날짜 조회하기
	public ResponseDto<String> getJoinDateByLoginId(String loginId) {
	    Optional<User> userOptional = userRepository.findByLoginId(loginId);
	    if(!userOptional.isPresent()) {
	        throw new InvalidRequestException("Invalid user LoginId","사용자 정보를 찾을 수 없습니다.");
	    }
	    User user = userOptional.get();
	    LocalDateTime joinDate = user.getCreateAt();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedJoinDate = joinDate.format(formatter);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(),formattedJoinDate, ResultCode.SUCCESS.getMsg());
	}
	
	// 모든 유저 가입날짜 조회하기
	public ResponseDto<Map<String, LocalDateTime>> getJoinDateForAllUsers() {
	    List<User> users = userRepository.findAll();
	    if(users.isEmpty()) {
	        throw new InvalidRequestException("User Empty","유저를 찾을 수 없습니다.");
	    }

	    Map<String, LocalDateTime> joinDates = new HashMap<>();
	    for(User user : users) {
	        LocalDateTime joinDate = user.getCreateAt();
	        joinDates.put(user.getLoginId(), joinDate);
	    }

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), joinDates, ResultCode.SUCCESS.getMsg());
	}
	
	// 현재 로그인한 사용자 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기
	public ResponseDto<Long> getDaysJoinedForSelf() {
	    User user = getAuthenticatedUser();
	    LocalDateTime joinDate = user.getCreateAt();
        LocalDateTime now = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);

	    long daysSinceJoined = ChronoUnit.DAYS.between(joinDate, now);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), daysSinceJoined, ResultCode.SUCCESS.getMsg());
	}

	
	
	//유저별로 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기(유저의 가입일과 현재 날짜 사이의 일수를 계산)
	public ResponseDto<Long> getDaysSinceJoined(String loginId) {
	    Optional<User> userOptional = userRepository.findByLoginId(loginId);
	    if(!userOptional.isPresent()) {
	        throw new InvalidRequestException("Invalid user LoginId","사용자 정보를 찾을 수 없습니다.");
	    }
	    User user = userOptional.get();
	    LocalDateTime joinDate = user.getCreateAt();
	    LocalDateTime now = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);

	    
	    long days = ChronoUnit.DAYS.between(joinDate, now);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), days, ResultCode.SUCCESS.getMsg());
	}

	    
		// 모든 유저 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기(유저의 가입일과 현재 날짜 사이의 일수를 계산)
	    public ResponseDto<Map<String, Long>> getDaysSinceJoinedForAllUsers() {
	        List<User> users = userRepository.findAll();
	        Map<String, Long> daysSinceJoined = new HashMap<>();

	        for(User user : users) {
	            LocalDateTime joinDate = user.getCreateAt();
	            LocalDateTime now = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);
	            long days = ChronoUnit.DAYS.between(joinDate, now);
	            daysSinceJoined.put(user.getLoginId(), days);
	        }

	        return new ResponseDto<>(ResultCode.SUCCESS.name(), daysSinceJoined, ResultCode.SUCCESS.getMsg());
	    }
	
	 
	    
	 // 현재 로그인한 사용자의 user_rating 조회
	    public ResponseDto<Set<Rating>> getCurrentUserRatings() {
	        User user = getAuthenticatedUser();
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), user.getRatings(),ResultCode.SUCCESS.getMsg());
	    }

	    // 모든 사용자의 user_rating 조회
	    public ResponseDto<Map<String, Set<Rating>>> getAllUsersRatings() {
	        List<User> users = userRepository.findAll();
	        Map<String, Set<Rating>> ratingsMap = users.stream()
	                .collect(Collectors.toMap(User::getLoginId, User::getRatings));
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), ratingsMap, ResultCode.SUCCESS.getMsg());
	    }

	    // 특정 loginId를 가진 사용자의 user_rating 조회
	    public ResponseDto<Set<Rating>> getUserRatingsByLoginId(String loginId) {
	        User user = userRepository.findByLoginId(loginId)
	                .orElseThrow(() -> new InvalidRequestException("User Not Found","해당 사용자가 없습니다."));
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), user.getRatings(), ResultCode.SUCCESS.getMsg());
	    }

	    
	    // LectureList를 LectureListDto로 변환하는 함수
	    public LectureListDto convertLectureListToDto(Lecture lecture) {
	        LectureListDto lectureListDto = new LectureListDto();
	        lectureListDto.setId(lecture.getId());
	        lectureListDto.setLectureCategory(lecture.getLectureCategory().getCategoryName());
	        lectureListDto.setLectureName(lecture.getLectureName());
	        lectureListDto.setSubTitle(lecture.getSubTitle());
	        lectureListDto.setProfessor(lecture.getProfessor().getName()); 
	        lectureListDto.setImage(lecture.getImage());
	        lectureListDto.setPrice(lecture.getPrice());

	        return lectureListDto;
	    }
	    
	 // 찜 목록 보기
	    public ResponseDto<List<LectureListDto>> getWishLectures() {
	        User user = getAuthenticatedUser();
	        Set<Lecture> wishLectures = user.getWishLecture();
	        
	        List<LectureListDto> lectureDtoList = wishLectures.stream()
	                .map(this::convertLectureListToDto) // convertLectureToDto를 convertLectureListToDto로 변경
	                .collect(Collectors.toList());
	        
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtoList, "찜 목록을 성공적으로 가져왔습니다.");
	    }

	    // 강의 찜하기
	    public ResponseDto<List<LectureListDto>> saveWishLecture(Long lectureId) { // 반환 타입을 List<LectureDto>에서 List<LectureListDto>로 변경
	       User user = getAuthenticatedUser();
	       Lecture lecture = lectureRepository.findById(lectureId)
	                .orElseThrow(() -> new InvalidRequestException("Lecture Not Found","해당 강의가 없습니다."));
	        user.getWishLecture().add(lecture);
	        List<LectureListDto> lectureDtoList = user.getWishLecture().stream()
	                .map(this::convertLectureListToDto) // convertLectureToDto를 convertLectureListToDto로 변경
	                .collect(Collectors.toList());
	        
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtoList, "찜 목록에 강의를 성공적으로 추가했습니다.");
	    }
	    // userRepository.save(user); 
	    // JPA가 영속성 컨텍스트를 관리하고 있으므로, 사실상 필요하지 않음. 
	    // PA가 '영속 상태'에 있는 엔티티의 변경을 추적(이를 '더티 체킹'이라고 함)하고,
	    // 트랜잭션 종료 시점에 이를 데이터베이스에 반영하기 때문임.
//	        UserDto userDto = convertUserToDto(user);
	    

	    // 강의 찜 해제하기
	    @Transactional
	    public ResponseDto<String> removeWishLecture(Long lectureId) {
	        User user = getAuthenticatedUser();
	        Lecture lecture = lectureRepository.findById(lectureId)
	                .orElseThrow(() -> new InvalidRequestException("Lectrue Not Found","해당 강의가 없습니다."));

	    user.getWishLecture().remove(lecture); // User가 찜한 강의 목록에서 특정 강의를 제거하는 것, delete와는 다름
//	    UserDto userDto = convertUserToDto(user);
	      return new ResponseDto<>("WishLecture Remove",null,"강의 찜해제가 정상적으로 처리되었습니다.");
	    
	    }

	    
	 // 현재 로그인한 교수가 가르치는 강의 조회하기
	    public ResponseDto<List<LectureListDto>> getTeachingLecturesByCurrentProfessor() {
	        User currentUser = getAuthenticatedUser(); // 현재 인증된 사용자 가져오기
	        List<Lecture> lectures = new ArrayList<>(currentUser.getLecture()); // 현재 사용자가 가르치는 강의 리스트 반환
	        if(lectures.isEmpty()) {
	            throw new InvalidRequestException("Lecture Empty","강의가 존재하지 않습니다.");
	        }
	        List<LectureListDto> lectureDtoList = lectures.stream()
	                .map(this::convertLectureListToDto) // convertLectureToDto를 convertLectureListToDto로 변경
	                .collect(Collectors.toList());
	        
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtoList, ResultCode.SUCCESS.getMsg());
	    }
	    
	 // 특정 교수 이름으로 강의 조회하기
	    public ResponseDto<List<LectureListDto>> getTeachingLecturesByProfessorName(String professorName) {
	        Optional<User> professorOptional = userRepository.findByName(professorName); // 교수 이름으로 사용자 조회
	        if(professorOptional.isEmpty()) {
	            throw new InvalidRequestException("Professor Empty","해당 교수가 존재하지 않습니다.");
	        }
	        User professor = professorOptional.get();
	        List<Lecture> lectures = new ArrayList<>(professor.getLecture()); // 해당 교수가 가르치는 강의 리스트 반환
	        if(lectures.isEmpty()) {
	            throw new InvalidRequestException("Lectures Empty","해당 교수가 가르치는 강의가 존재하지 않습니다.");
	        }
	        List<LectureListDto> lectureDtoList = lectures.stream()
	                .map(this::convertLectureListToDto) // convertLectureToDto를 convertLectureListToDto로 변경
	                .collect(Collectors.toList());
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureDtoList, ResultCode.SUCCESS.getMsg());
	    }
	    
//	 // 모든 교수와 그들이 가르치는 강의 조회하기
//	    public ResponseDto<Map<String, List<String>>> getAllProfessorsAndTheirLectures() {
//	        Map<String, List<String>> professorsAndLectures = new HashMap<>();
//	        List<User> professors = userRepository.findAll(); // 교수 권한을 가진 유저뿐만 아니라 일반 유저까지 조회됨..
//	        // 모든 교수 조회
//	        for (User professor : professors) {
//	            List<String> lectureNames = professor.getLecture().stream()
//	                .map(Lecture::getLectureName) // 강의 이름만 추출
//	                .collect(Collectors.toList());
//	            professorsAndLectures.put(professor.getName(), lectureNames); // 교수명과 강의명 리스트를 맵에 추가
//	        }
//	        return new ResponseDto<>(ResultCode.SUCCESS.name(), professorsAndLectures, ResultCode.SUCCESS.getMsg());
//	    }
}

