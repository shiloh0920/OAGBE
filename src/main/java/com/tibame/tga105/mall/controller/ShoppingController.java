package com.tibame.tga105.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tibame.tga105.mall.entity.Cart;
import com.tibame.tga105.mall.entity.Product;
import com.tibame.tga105.mall.repository.CartRepository;
import com.tibame.tga105.mall.repository.ProductRepository;
import com.tibame.tga105.user.service.UserService;
import com.tibame.tga105.user.security.UserPrincipal;
import com.tibame.tga105.user.entity.UserVO;

@Controller
@RequestMapping("/products")
public class ShoppingController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/beans")
	public ModelAndView showBeans() {
	    ModelAndView mav = new ModelAndView("show-beans");
	    UserVO user = null;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserPrincipal) {
	        UserPrincipal userPrincipal = (UserPrincipal) principal;
	        user = userPrincipal.getUservo();
	        String email = user.getUseremail();
//	        UserVO user = userService.getUserByEmail(email);
	        if (user != null) {
	            boolean isAdmin = userService.checkAdmin();
	            mav.addObject("isadmin", isAdmin);
	        }
	        List<Product> allProducts = productRepository.findAll();
	        mav.addObject("allProducts", allProducts);
	        mav.addObject("user", user);
	    }
	    return mav;
	}

	
//	@GetMapping("/beans")
//	public ModelAndView showBeans() {
//		ModelAndView mav = new ModelAndView("show-beans");
//		UserVO uservo = null;
//		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		uservo = principal1.getUservo();
////		String email = SecurityUtil.getSessionUser();
//		String email = principal1.getUservo().getUseremail();
//		UserVO user = userService.getUserByEmail(email);
//		if( user != null) {
//			boolean isAdmin = userService.checkAdmin();
//			mav.addObject("isadmin", isAdmin);
//		}
//		List<Product> allProducts = productRepository.findAll();
//		mav.addObject("allProducts", allProducts);
//		mav.addObject("user", user);
//		return mav;
//	}
	
	
	// 購物車
	@GetMapping("/cart")
	public String showCart(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof UserPrincipal) {
			UserPrincipal userPrincipal = (UserPrincipal) principal;
			UserVO user = userPrincipal.getUservo();
			email = user.getUseremail();
		} else if (principal instanceof String) {
			email = (String) principal;
		}
		UserVO user = userService.getUserByEmail(email);
		model.addAttribute("user", user);
	
//		UserVO uservo = null;
//		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		uservo = principal1.getUservo();
//		String email = principal1.getUservo().getUseremail();
////		String email = SecurityUtil.getSessionUser();
//		UserVO user = userService.getUserByEmail(email);
//		model.addAttribute("user", user);
		
		// 計算總價
		double totalCost = 0;
		for(Product product: user.getCart().getProducts()) {
			totalCost += product.getPrice();
		}
		model.addAttribute("totalcost", totalCost);
		return "cart";
	}
	
	// 購物車新增商品
	@GetMapping("/cart/add")
	public String addCart(@RequestParam Long productid) {
		UserVO user = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		user = principal1.getUservo();
		String email = principal1.getUservo().getUseremail();
//		String email = SecurityUtil.getSessionUser();
		Cart cart = userService.getUserByEmail(email).getCart();
		Product product = productRepository.findById(productid).get();
		List<Product> newProductList = cart.getProducts();
		newProductList.add(product);
		cart.setProducts(newProductList);
		cartRepository.save(cart);
		return "redirect:/products/cart?success";
	}
	
	
	// 購物車移除商品
	@GetMapping("/cart/remove")
	public String removeCart(@RequestParam Long productid) {
		UserVO user = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		user = principal1.getUservo();
		String email = principal1.getUservo().getUseremail();
		Cart cart = userService.getUserByEmail(email).getCart();
		Product product = productRepository.findById(productid).get();
		List<Product> newProductList = cart.getProducts();
		newProductList.remove(product);
		cart.setProducts(newProductList);
		cartRepository.save(cart);
		return "redirect:/products/cart?removed";
	}
	
	
	// 支付方式
	@GetMapping("/cart/payment")
	public String paymentForm(Model model) {
		UserVO user = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		user = principal1.getUservo();
		String email = principal1.getUservo().getUseremail();
//		UserVO user = userService.getUserByEmail(email);
		model.addAttribute("user", user);
		//calculate total price
		int totalCost = 0;
		for(Product product: user.getCart().getProducts()) {
			totalCost += product.getPrice();
		}
		model.addAttribute("amount", totalCost);
		return "payment";
	}
	
	
}
