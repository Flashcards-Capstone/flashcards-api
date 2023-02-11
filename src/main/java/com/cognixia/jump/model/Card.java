package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity	
public class Card implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	//@Column(nullable = false)
	@NotBlank
	private String question;
	
	//@Column(nullable = false)
	@NotBlank
	private String answer;
	
	
	@ManyToOne
	@JoinColumn( name = "stack_id", referencedColumnName = "id")
	private Stack stack;
	
	
//	public Card() {
//		this(-1, -1, "N/A","N/A");
//	}
//	
//	public Card(Integer id, Integer stack_id, String question, String answer) {
//		super();
//		this.id = id;
//		this.stack_id = stack_id;
//		this.question = question;
//		this.answer = answer;
//	}
	
	public Card() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Integer getStackId() {
//		return stack_id;
//	}
//
//	public void setStackId(Integer stack_id) {
//		this.stack_id = stack_id;
//	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", question=" + question + ", answer=" + answer + ", stack=" + stack + "]";
	}
	
	
	
}
