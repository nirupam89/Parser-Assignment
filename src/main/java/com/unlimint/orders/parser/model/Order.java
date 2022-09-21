package com.unlimint.orders.parser.model;

import java.math.BigDecimal;

import com.google.gson.Gson;
import com.unlimint.orders.parser.enums.Result;

import lombok.Data;

@Data
public class Order {

	private int id;
	private int orderId;
	private BigDecimal amount;
	private String currency;
	private String comment;
	private String filename;
	private int line;
	private Result result;
	
	public String toString() {
	    return new Gson().toJson(this);
	}
}
