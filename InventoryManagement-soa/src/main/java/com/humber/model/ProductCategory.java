package com.humber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productcategory")
@Data
public class ProductCategory {
	@Id
	private String id;

	@Column
	private String category;
}
