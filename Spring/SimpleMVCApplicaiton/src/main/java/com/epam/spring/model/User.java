package com.epam.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.epam.spring.app.enums.UserRole;

public class User {

	@Id
	private Long id;

	private String firstName;

	@Size(min = 3, max = 10)
	private String lastName;

	private boolean processed;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate birthday;

	private boolean enabled;
	private String password;
	private List<UserRole> roles;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
