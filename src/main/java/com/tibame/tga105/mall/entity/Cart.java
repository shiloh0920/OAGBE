package com.tibame.tga105.mall.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ogabe.mall.entity.Cart;
import com.ogabe.mall.entity.Role;
import com.ogabe.user.entity.UserVO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy="cart")
	private UserVO user;
	
	@ManyToMany
	@JoinTable(name="carts_products",
			joinColumns= {@JoinColumn(name="cart_id", referencedColumnName="id")},
			inverseJoinColumns= {@JoinColumn(name="product_id", referencedColumnName="id")})
	private List<Product> products = new ArrayList<>();

}
