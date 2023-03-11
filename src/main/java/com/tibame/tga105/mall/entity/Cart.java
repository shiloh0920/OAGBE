package com.tibame.tga105.mall.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.tibame.tga105.mall.entity.Cart;
import com.tibame.tga105.mall.entity.Role;
import com.tibame.tga105.user.entity.UserVO;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


}
