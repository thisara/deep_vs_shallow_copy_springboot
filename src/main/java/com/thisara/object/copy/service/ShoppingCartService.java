package com.thisara.object.copy.service;

import java.util.ArrayList;
import java.util.List;

import com.thisara.object.copy.domain.ShoppingCart;

public class ShoppingCartService {

	public ShoppingCart createShoppingCart(ArrayList<String> cartItems, String cartName) {
		ShoppingCart shoppingCart = new ShoppingCart(cartName, cartItems);
		List<String> items = new ArrayList<String>();

		cartItems.stream().forEach(item -> items.add(item));

		shoppingCart.setItems(items);
		shoppingCart.setCartName(cartName);

		return shoppingCart;
	}
}
