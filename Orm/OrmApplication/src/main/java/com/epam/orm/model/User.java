package com.epam.orm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6501726478909925311L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String surname;
	private String email;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_permissions", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id") })
	private List<Permissions> permissions;
	@OneToMany
	private List<Car> cars;
	@OneToOne(optional = true, cascade = CascadeType.REMOVE)
	private AuthorizationToken authorizationToken;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Permissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permissions> permissions) {
		this.permissions = permissions;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public AuthorizationToken getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(AuthorizationToken authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

}
