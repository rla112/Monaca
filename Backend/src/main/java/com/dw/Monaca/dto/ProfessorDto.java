package com.dw.Monaca.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.dw.Monaca.jwtauthority.dto.AuthorityDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProfessorDto {

	@NotNull
	@NotBlank
	@Size(min = 6, max = 15)
	private String loginId;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "비밀번호를 입력해주세요, 공백 문자는 포함하면 안됩니다.") // 필드 값이 null이 아니고, 또한 공백 문자(스페이스, 탭, 줄바꿈 등)만으로 이루어져 있지 않은지를 검사 // 즉, 필드 값이 null이거나 공백 문자만 있는 경우에 유효성 검사에 실패
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,20}$", message = "영문 숫자 특수문자를 포함한 8~20자리로 입력해주세요")
	private String password; // 비밀번호
	
	@NotBlank(message = "성함을 입력해주세요. 공백 문자는 포함하면 안됩니다.")
	@Size(min = 1, max = 6, message = "성함은 1자 이상 6자 이내로 입력해주세요.")
	private String name; // 회원 실명
	
	@NotBlank(message = "닉네임을 입력해주세요, 공백 문자는 포함하면 안됩니다.")
	@Size(min = 3, max = 50, message = "닉네임은 3자 이상 50자 이하로 입력해주세요")
	@Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "닉네임은 한글과 영어만 가능합니다.")
	private String nickname; // 사용할 별명
	
	@NotBlank(message = "생년월일을 입력해주세요, 공백 문자는 포함하면 안됩니다.")
	@Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "날짜형식(YYYY-MM-DD)을 확인해주세요")
	private String birthDate; // 생일!
	
	@Pattern(regexp = "^(MAN|WOMAN)$", message = "MAN이나 WOMAN 중 하나를 선택해주세요")
	private String gender;

	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "아이디@사이트이름.com와 같은 형식으로 입력해주세요. / 없다면 공란허용")
	private String email;
	
	@NotBlank(message = "핸드폰 번호를 입력해주세요, 공백 문자는 포함하면 안됩니다.")
	@Size(min = 10, max = 30)
	@Pattern(regexp = "^[\\d]{12}+$", message = "'-' 기호 없이 전화번호를 입력해주세요.")
	private String phoneNum;

	@NotNull(message = "교수 소개를 입력해주세요.")
	@Size(min = 10, max = 500, message = "최소 10자 이상 50자 이내로 입력해주세요.")
	private String professorIntro;
	
	@NotNull(message = "교수 경력을 입력해주세요.")
	@Size(min = 1, max = 1000, message = "최소 1자 이상 1000자 이내로 입력해주세요.")
	private String professorResume;
	
	@NotNull(message = "교수 프로필 사진을 첨부해주세요.")
	private String image;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;
	
	private Boolean activated;
	
	private Set<AuthorityDto> authorityDtoSet;

	private Set<LectureDto> lecture;
	
	public ProfessorDto() {
		super();
	}

	public ProfessorDto(@NotNull @NotBlank @Size(min = 6, max = 15) String loginId,
			@NotBlank(message = "비밀번호를 입력해주세요, 공백 문자는 포함하면 안됩니다.") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,20}$", message = "영문 숫자 특수문자를 포함한 8~20자리로 입력해주세요") String password,
			@NotBlank(message = "성함을 입력해주세요. 공백 문자는 포함하면 안됩니다.") @Size(min = 1, max = 6, message = "성함은 1자 이상 6자 이내로 입력해주세요.") String name,
			@NotBlank(message = "닉네임을 입력해주세요, 공백 문자는 포함하면 안됩니다.") @Size(min = 3, max = 50, message = "닉네임은 3자 이상 50자 이하로 입력해주세요") @Pattern(regexp = "^[가-힣a-zA-Z]*$", message = "닉네임은 한글과 영어만 가능합니다.") String nickname,
			@NotBlank(message = "생년월일을 입력해주세요, 공백 문자는 포함하면 안됩니다.") @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "날짜형식(YYYY-MM-DD)을 확인해주세요") String birthDate,
			@Pattern(regexp = "^(MAN|WOMAN)$", message = "MAN이나 WOMAN 중 하나를 선택해주세요") String gender,
			@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "아이디@사이트이름.com와 같은 형식으로 입력해주세요. / 없다면 공란허용") String email,
			@NotBlank(message = "핸드폰 번호를 입력해주세요, 공백 문자는 포함하면 안됩니다.") @Size(min = 10, max = 30) @Pattern(regexp = "^[\\d]{12}+$", message = "'-' 기호 없이 전화번호를 입력해주세요.") String phoneNum,
			@NotNull(message = "교수 소개를 입력해주세요.") @Size(min = 10, max = 500, message = "최소 10자 이상 50자 이내로 입력해주세요.") String professorIntro,
			@NotNull(message = "교수 경력을 입력해주세요.") @Size(min = 1, max = 1000, message = "최소 1자 이상 1000자 이내로 입력해주세요.") String professorResume,
			@NotNull(message = "교수 프로필 사진을 첨부해주세요.") String image, LocalDateTime createAt, Boolean activated,
			Set<AuthorityDto> authorityDtoSet, Set<LectureDto> lecture) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.phoneNum = phoneNum;
		this.professorIntro = professorIntro;
		this.professorResume = professorResume;
		this.image = image;
		this.createAt = createAt;
		this.activated = activated;
		this.authorityDtoSet = authorityDtoSet;
		this.lecture = lecture;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getProfessorIntro() {
		return professorIntro;
	}

	public void setProfessorIntro(String professorIntro) {
		this.professorIntro = professorIntro;
	}

	public String getProfessorResume() {
		return professorResume;
	}

	public void setProfessorResume(String professorResume) {
		this.professorResume = professorResume;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Set<AuthorityDto> getAuthorityDtoSet() {
		return authorityDtoSet;
	}

	public void setAuthorityDtoSet(Set<AuthorityDto> authorityDtoSet) {
		this.authorityDtoSet = authorityDtoSet;
	}

	public Set<LectureDto> getLecture() {
		return lecture;
	}

	public void setLecture(Set<LectureDto> lecture) {
		this.lecture = lecture;
	}

	
}
