package com.dw.Monaca.model;

import java.time.LocalDateTime;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// candy_points를 얻을 수 있는 방법은 출석, 우수성적(A), 회원등급(이건 테이블을 따로 만들어야 될 듯)
// candy_points를 사용하는 것은 item_shop에서 물건을 구매할 때 차감
// 그렇기 때문에 candy_points 단독적으로 관리하는 엔티티가 필요함.
@Entity
@Table(name = "candy_point")
public class CandyPoint {

	@Id
	@Column(name = "candy_point_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Candy candy;

	@ManyToOne // 어느 곳에서 포인트를 획득하고 사용했는지 출처를 볼 수 있도록 하기 위한 Entity임!!
	private CandyPointItem candyPointItem;
	
	// candyPoint를 획득한 포인트인지 사용한 포인트인지 나타냄. 
	// (양수이면 획득한 포인트, 음수이면 사용한 포인트)
	@Column(name = "point", nullable = false)
	private int point;
	
	// 특정 이벤트에 대한 설명이나 추가적인 메모
	@Column(name = "description")
	private String description;
	
	@Column(nullable = false , updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime timeStamp;

	public CandyPoint() {
		super();
	}

	public CandyPoint(Long id, User user, Candy candy, CandyPointItem candyPointItem, int point, String description,
			LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.candy = candy;
		this.candyPointItem = candyPointItem;
		this.point = point;
		this.description = description;
		this.timeStamp = timeStamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Candy getCandy() {
		return candy;
	}

	public void setCandy(Candy candy) {
		this.candy = candy;
	}

	public CandyPointItem getCandyPointItem() {
		return candyPointItem;
	}

	public void setCandyPointItem(CandyPointItem candyPointItem) {
		this.candyPointItem = candyPointItem;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	
	
}
