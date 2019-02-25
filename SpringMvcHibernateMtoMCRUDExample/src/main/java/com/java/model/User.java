package com.java.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="APP_USER")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Column(name="PASSWORD",nullable=false)
	private String password;
	@NotEmpty
	@Column(name="SSO_ID",nullable=false,unique=true)
	private String ssoId;
	@NotEmpty
	@Column(name="FIRST_NAME",nullable=false)
	private String firstName;@NotEmpty
	@Column(name="LAST_NAME",nullable=false)
	private String lastName;
	@NotEmpty
	@Column(name="EMAIL",nullable=false)
	private String email;
	@NotEmpty
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="APP_USER_USER_PROFILE",
				joinColumns= {@JoinColumn(name="USER_ID")},
				inverseJoinColumns= {@JoinColumn(name="USER_PROFILE_ID")})
	private Set<UserProfile> userProfiles;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
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
	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}
	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	

}
