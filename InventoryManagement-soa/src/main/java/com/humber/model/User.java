package com.humber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	private String id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String city;

	@Column
	private String country;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private int isActive;

	

}
