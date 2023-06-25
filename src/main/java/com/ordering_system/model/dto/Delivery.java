package com.ordering_system.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Delivery {
	
	private long userId;
	private long orderId;
	@Schema(hidden = true)
	@JsonFormat(pattern = "yyyy-MMM-dd hh:mm:ss")
	private LocalDateTime startDate;

	public Delivery() {
		
	}

	/**
	 * @param userId
	 * @param orderId
	 * @param startDate
	 */
	public Delivery(long userId, long orderId, LocalDateTime startDate) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.startDate = startDate;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}



}
