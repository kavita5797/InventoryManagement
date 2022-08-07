package com.humber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

	@Id
	private String id;

	@Column
	private String productname;
		
	@Column
	private String productlabel;
	
	@Column
	private String productprice;
	
	@Column
	private String productdescription;


}
