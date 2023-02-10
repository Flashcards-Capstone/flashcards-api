package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
	
//import com.cognixia.jump.model.Card;

@Entity
public class Stack implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "stack", cascade = CascadeType.ALL)
    private List<Card> listCard = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
	private User user;
	
	
	@Column(nullable = false, insertable=false, updatable=false)
	private Integer user_id;
	@Column(nullable = false)
	private boolean publiclyVisible;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String subject;
	
	public Stack() {
		this(-1, -1, false,"N/A","N/A");
	}
	
	public Stack(Integer id, Integer user_id, boolean publiclyVisible, String title, String subject) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.publiclyVisible = publiclyVisible;
		this.title = title;
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(Integer user_id) {
		this.user_id = user_id;
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
