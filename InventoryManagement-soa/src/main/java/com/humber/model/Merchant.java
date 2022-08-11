package com.humber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "merchant")
@Data
public class Merchant {
	@Id
	private String id;

	@Column
	private String name;

	@Column
	private String phone;

	@Column
	private String email;

	@Column
	private String address;

	@Column
	private String country;

}
