package com.ordering_system.model.dto;

public class Deliver {
	
	private long userId;
	private long orderId;
	
	public Deliver() {
		
	}

	/**
	 * @param userId
	 * @param orderId
	 */
	public Deliver(long userId, long orderId) {
		super();
		this.userId = userId;
		this.orderId = orderId;
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

}
