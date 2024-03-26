package com.dw.Monaca.jwtauthority.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.dw.Monaca.model.Characters;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.Rating;

//import com.dw.Monaca.model.Characters;
//import com.dw.Monaca.model.Lecture;
//import com.dw.Monaca.model.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "`user`")
public class User {

	@Id // 자체적으로 붙는 ID
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 회원 정보의 ID
	@Column(name = "loginId", length = 15, unique = true)
	private String loginId;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "nickname", length = 50)
	private String nickname;

	@Column(name = "birthDate")
	private String birthDate;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "phoneNum", length = 30)
	private String phoneNum;

	@Column(name = "professorIntro", length = 500)
	private String professorIntro;

	@Column(name = "professorResume", length = 50)
	private String professorResume;

	@Column(name = "image")
	private String image;
	
	@Column(name = "JoinDate")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;
	
//	// 활성화 / 비활성화
//	@Column(name = "activated")
//	private boolean activated = false;
	@Column(name = "activated")
	private Boolean activated;

	@ManyToOne
	private Characters characters;

	@ManyToMany // user의 권한
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
	private Set<Authority> authorities;

	
	@ManyToMany // user의 혜택부여등급
	@JoinTable(name = "user_rating", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "candy_grade", referencedColumnName = "candy_grade") })
	private Set<Rating> ratings;
	
	@ManyToMany // user가 원하는 강의를 찜 // 영구적
	@JoinTable(name = "wishlist", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "lecture_id)") })
	private Set<Lecture> wishLecture;

	@ManyToMany // user가 가르치는 강의(과목)
	@JoinTable(name = "teaching_lectures", joinColumns = {@JoinColumn(name = "user", referencedColumnName = "user_id")}, inverseJoinColumns = {
			@JoinColumn(name = "lecture", referencedColumnName = "lecture_id" )})
	private Set<Lecture> lecture;
	
	
	public User() {
		super();
	}


	public User(Long id, String loginId, String password, String name, String nickname, String birthDate, String gender,
			String email, String phoneNum, String professorIntro, String professorResume, String image,
			LocalDateTime createAt, Boolean activated, Characters characters, Set<Authority> authorities,
			Set<Rating> ratings, Set<Lecture> wishLecture, Set<Lecture> lecture) {
		super();
		this.id = id;
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
		this.characters = characters;
		this.authorities = authorities;
		this.ratings = ratings;
		this.wishLecture = wishLecture;
		this.lecture = lecture;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Characters getCharacters() {
		return characters;
	}


	public void setCharacters(Characters characters) {
		this.characters = characters;
	}


	public Set<Authority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}


	public Set<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}


	public Set<Lecture> getWishLecture() {
		return wishLecture;
	}


	public void setWishLecture(Set<Lecture> wishLecture) {
		this.wishLecture = wishLecture;
	}


	public Set<Lecture> getLecture() {
		return lecture;
	}


	public void setLecture(Set<Lecture> lecture) {
		this.lecture = lecture;
	}



	
	
}
