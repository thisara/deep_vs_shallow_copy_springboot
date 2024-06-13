package com.thisara.object.copy.service;

import java.util.ArrayList;
import java.util.List;

import com.thisara.object.copy.domain.ShoppingCart;

public class DeepCopyService implements ObjectCopyService {

	public ShoppingCart copy(ShoppingCart sourceShoppingCart, String copyShoppingCartName) {

		ShoppingCart deepCopy = new ShoppingCart(copyShoppingCartName, sourceShoppingCart.getItems());

        ArrayList<String> itemList = new ArrayList<String>();

        sourceShoppingCart.getItems()
            .stream()
            .forEach(t -> itemList.add(t));

        deepCopy.setItems(itemList);

		return deepCopy;
	}
}
