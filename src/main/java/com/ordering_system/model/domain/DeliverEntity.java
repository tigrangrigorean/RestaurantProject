package com.ordering_system.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliver")
public class DeliverEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private UserEntity userEntity;
	@OneToOne
	private OrderEntity orderEntity;
	
	public DeliverEntity() {
		
	}

	/**
	 * @param id
	 * @param userEntity
	 * @param orderEntity
	 */
	public DeliverEntity(long id, UserEntity userEntity, OrderEntity orderEntity) {
		super();
		this.id = id;
		this.userEntity = userEntity;
		this.orderEntity = orderEntity;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userEntity
	 */
	public UserEntity getUserEntity() {
		return userEntity;
	}

	/**
	 * @param userEntity the userEntity to set
	 */
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	/**
	 * @return the orderEntity
	 */
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	/**
	 * @param orderEntity the orderEntity to set
	 */
	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}
	
	

}
