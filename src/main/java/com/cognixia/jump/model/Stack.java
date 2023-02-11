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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Stack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Column(nullable = false)
	@NotBlank
	private String title;

	// @Column(nullable = false)
	@NotBlank
	private String subject;

	@Column(columnDefinition = "boolean default false")
	private boolean publiclyVisible;

	// @JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "stack", cascade = CascadeType.ALL)
	private List<Card> listCard;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id") // nullable = true
	private User user;

	public Stack() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isPubliclyVisible() {
		return publiclyVisible;
	}

	public void setPubliclyVisible(boolean publiclyVisible) {
		this.publiclyVisible = publiclyVisible;
	}

	public List<Card> getCards() {
		return listCard;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Stack [id=" + id + ", listCard=" + listCard + ", user=" + user + ", publiclyVisible=" + publiclyVisible
				+ ", title=" + title + ", subject=" + subject + "]";
	}

}
