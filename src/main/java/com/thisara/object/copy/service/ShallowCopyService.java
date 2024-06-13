package com.thisara.object.copy.service;

import com.thisara.object.copy.domain.ShoppingCart;

public class ShallowCopyService implements ObjectCopyService {

	public ShoppingCart copy(ShoppingCart sourceShoppingCart, String copyShoppingCartName) {

		ShoppingCart shallowCopy = new ShoppingCart(copyShoppingCartName, sourceShoppingCart.getItems());

		shallowCopy.setCartName(copyShoppingCartName);

		return shallowCopy;
	}
}
