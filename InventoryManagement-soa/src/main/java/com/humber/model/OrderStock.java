package com.humber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orderStock")
@Data
public class OrderStock {

	@Id
	private String id;

	@Column
	private String productId;

	@Column
	private int quantity;

	@Column
	private double unitPrice;

	@Column
	private double totalPrice;

	@Column
	private Date purchasedDate;

	@Column
	private String billingType;

	@Column
	private boolean isPaid;


}
