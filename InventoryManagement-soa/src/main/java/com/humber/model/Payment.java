package com.humber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {

	@Id
	private String id;

	@Column
	private String stockId;

	@Column
	private double paidAmount;

	@Column
	private double totalAmount;

	@Column
	private Date paymentDate;

	@Column
	private String paymentType;
}
