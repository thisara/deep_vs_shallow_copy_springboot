package com.thisara.object.copy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thisara.object.copy.domain.ShoppingCart;
import com.thisara.object.copy.exception.InvalidCopyTypeException;
import com.thisara.object.copy.factory.ObjectCopyServiceFactory;
import com.thisara.object.copy.service.ShoppingCartService;

@SpringBootTest
class ObjectCopyUnitTests {

	@Autowired
	ObjectCopyServiceFactory objectCopyServiceFactory;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Test
	void givenAnObject_whenDoShallowCopy_thenReturnNewReferenceOfObject() throws InvalidCopyTypeException {
		ShoppingCart shoppingCart = getShoppingCart("MyCart", new ArrayList<String>(List.of("Bread")));
		ShoppingCart shallowCopy = objectCopyServiceFactory.getObjectCopyService("shallow").copy(shoppingCart,
				"AfterShallowCopyShoppingCart");

		shallowCopy.getItems().add("Butter");

		Assert.assertFalse(shallowCopy.getCartName().equals(shoppingCart.getCartName()));
		Assert.assertTrue(shoppingCart.getItems().equals(shallowCopy.getItems()));
	}

	@Test
	void givenAnObject_whenDoDeepCopy_thenReturnNewCopyOfObject() throws InvalidCopyTypeException {
		ShoppingCart shoppingCart = getShoppingCart("MyCart", new ArrayList<String>(List.of("Bread")));
		ShoppingCart deepCopy = objectCopyServiceFactory.getObjectCopyService("deep").copy(shoppingCart,
				"AfterShallowCopyShoppingCart");

		deepCopy.setCartName("AfterDeepCopyShoppingCart");
		deepCopy.getItems().add("Cheese");

		Assert.assertFalse(deepCopy.getCartName().equals(shoppingCart.getCartName()));
		Assert.assertFalse(shoppingCart.getItems().equals(deepCopy.getItems()));
	}

	@Test
	void givenAnObject_whenDoCopyByCloning_thenReturnNewCopyOfObject()
			throws CloneNotSupportedException, InvalidCopyTypeException {
		ShoppingCart shoppingCart = getShoppingCart("MyCart", new ArrayList<String>(List.of("Bread")));
		ShoppingCart deepCopyByCloning = objectCopyServiceFactory.getObjectCopyService("clone").copy(shoppingCart,
				"AfterShallowCopyShoppingCart");

		deepCopyByCloning.setCartName("AfterCopyShoppingCart");
		deepCopyByCloning.getItems().add("Apple");

		Assert.assertFalse(deepCopyByCloning.getCartName().equals(shoppingCart.getCartName()));
		Assert.assertTrue(shoppingCart.getItems().equals(deepCopyByCloning.getItems()));
	}

	@Test
	void givenAnObject_whenDoDeepCopyByCloning_thenReturnNewCopyOfObject()
			throws CloneNotSupportedException, InvalidCopyTypeException {
		ShoppingCart shoppingCart = getShoppingCart("MyCart", new ArrayList<String>(List.of("Bread")));
		ShoppingCart deepCopyByCloning = objectCopyServiceFactory.getObjectCopyService("deepclone").copy(shoppingCart,
				"AfterShallowCopyShoppingCart");

		deepCopyByCloning.setCartName("AfterDeepCopyShoppingCart");
		deepCopyByCloning.getItems().add("Apple");

		Assert.assertFalse(deepCopyByCloning.getCartName().equals(shoppingCart.getCartName()));
		Assert.assertFalse(shoppingCart.getItems().equals(deepCopyByCloning.getItems()));
	}

	ShoppingCart getShoppingCart(String cartName, List<String> items) {
        return new ShoppingCart(cartName, items);
    }
}
