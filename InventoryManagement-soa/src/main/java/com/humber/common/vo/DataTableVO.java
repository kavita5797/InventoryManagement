package com.humber.common.vo;

import java.util.List;

import lombok.Data;

@Data
public class DataTableVO<T> {
	public int first;

	public int rows;

	public int totalRecords;

	public List<T> result;
}
