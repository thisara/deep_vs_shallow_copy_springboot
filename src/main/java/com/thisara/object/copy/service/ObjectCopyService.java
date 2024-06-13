package com.thisara.object.copy.service;

import com.thisara.object.copy.domain.ShoppingCart;

public interface ObjectCopyService {

	ShoppingCart copy(ShoppingCart sourceShoppingCart, String copyShoppingCartName);

}
