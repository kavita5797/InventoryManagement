package com.humber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "receivingstock")
@Data
public class RecievingStock {
	@Id
	private String id;

	@Column
	private String productId;
	
	@Column
	private String productName;
	
	@Column
	private String merchantName;

	@Column
	private String merchantId;

	@Column
	private int quantity;

	@Column
	private double unitPrice;

	@Column
	private double totalPrice;
	
	@Column
	private double totalPaidAmount = 0;

	@Column
	private Date purchasedDate;

	@Column
	private int pendingPayment = 1;
}
