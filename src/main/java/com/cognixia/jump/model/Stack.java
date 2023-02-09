package com.cognixia.jump.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
	
@Entity
public class Stack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "stack", cascade = CascadeType.ALL)
    private List<Card> listCard = new ArrayList<>();
	
	@Column(nullable = false)
	private int userId;
	
	@Column(nullable = false)
	private boolean publiclyVisible;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String subject;
	
	public Stack() {
		this(-1, -1, false,"N/A","N/A");
	}
	
	public Stack(int id, int userId, boolean publiclyVisible, String title, String subject) {
		super();
		this.id = id;
		this.userId = userId;
		this.publiclyVisible = publiclyVisible;
		this.title = title;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isPubliclyVisible() {
		return publiclyVisible;
	}

	public void setPubliclyVisible(boolean publiclyVisible) {
		this.publiclyVisible = publiclyVisible;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
