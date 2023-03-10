package com.tibame.tga105.mall.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; // 商品編號
	
	@Column(name = "prodname")
	private String name; // 商品名稱
	
	@Column(name = "prodprice")
	private Integer price; // 商品價格
	
	@Column(name = "prodstock")
	private Integer stock; // 商品庫存
		
	@Lob
	@Column(name = "description")
	private String description; // 商品描述
	
	@Lob
	@Column(name = "prodimage")
	private String image; // 商品圖片
		
//	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
//	private List<Review> reviews = new ArrayList<>(); // 商品評論
	

}
