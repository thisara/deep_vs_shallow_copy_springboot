package com.thisara.object.copy.dto;

import com.thisara.object.copy.domain.ShoppingCart;

public class ObjectCopyResponseDto {
	
	private ShoppingCart sourceShoppingCart;
	private ShoppingCart copyShoppingCart;
	
	public ObjectCopyResponseDto(ShoppingCart sourceShoppingCart,ShoppingCart copyShoppingCart) {
		this.sourceShoppingCart = sourceShoppingCart;
		this.copyShoppingCart = copyShoppingCart;
	}
	
	public ShoppingCart getSourceShoppingCart() {
		return sourceShoppingCart;
	}
	public void setSourceShoppingCart(ShoppingCart sourceShoppingCart) {
		this.sourceShoppingCart = sourceShoppingCart;
	}
	public ShoppingCart getCopyShoppingCart() {
		return copyShoppingCart;
	}
	public void setCopyShoppingCart(ShoppingCart copyShoppingCart) {
		this.copyShoppingCart = copyShoppingCart;
	}
}
