package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class IdRequest {
	
	private
	@NotNull(message="no id given")
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdRequest [id=" + id + "]";
	}
	
	
	
}
