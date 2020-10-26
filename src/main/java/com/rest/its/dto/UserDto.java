package com.rest.its.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.its.entities.Interview;

public class UserDto {

	@JsonIgnore
	private int userId;

	@Min(value = 5,message = "Firstname should be min of 5 characters")
	@Max(value = 25,message = "Firstname should be max of 25 characters")
	@JsonProperty(value="firstName")
	private String firstName;
	@JsonProperty(value="lastName")
	private String lastName;
	@JsonProperty(value="email")
	private String email;
	@JsonProperty(value="mobile")
	private String mobile;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ManyToMany(mappedBy = "users")
	private Set<Interview> interviews;

	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

}
