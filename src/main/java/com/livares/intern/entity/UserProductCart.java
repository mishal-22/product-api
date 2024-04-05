package com.livares.intern.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductCart extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	Users userId;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	Product productId;
}
