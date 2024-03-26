package com.dw.Monaca.model;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;

@EnableScheduling
@Entity
@Table(name = "received_message_bin")
public class ReceivedMessageBin {

	@Id
	@Column(name = "received_message_bin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Temporal(TemporalType.TIMESTAMP)
	// @Temporal(TemporalType.TIMESTAMP) 어노테이션은 java.util.Date와 java.util.Calendar 타입의 필드에 적용되는 어노테이션입니다. 
	// java.time.LocalDateTime 타입의 필드에는 @Temporal 어노테이션이 필요하지 않음.
	@Column(name = "created_at")
	private LocalDateTime createAt;

	@ManyToOne
	private ReceivedMessage receivedMessage;

	public ReceivedMessageBin() {
		super();
	}

	public ReceivedMessageBin(Long id, LocalDateTime createAt, ReceivedMessage receivedMessage) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.receivedMessage = receivedMessage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public ReceivedMessage getReceivedMessage() {
		return receivedMessage;
	}

	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		this.receivedMessage = receivedMessage;
	}
	
	
}
