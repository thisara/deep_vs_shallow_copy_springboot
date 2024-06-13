package com.thisara.object.copy.rest;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thisara.object.copy.domain.ShoppingCart;
import com.thisara.object.copy.dto.ObjectCopyRequestDto;
import com.thisara.object.copy.dto.ObjectCopyResponseDto;
import com.thisara.object.copy.dto.ObjectCreateRequestDto;
import com.thisara.object.copy.exception.InvalidCopyTypeException;
import com.thisara.object.copy.factory.ObjectCopyServiceFactory;
import com.thisara.object.copy.service.ShoppingCartService;

@RestController
@RequestMapping(path = "/object")
public class ObjectCopyController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public ShoppingCartService shoppingCartService;
	public ObjectCopyServiceFactory objectCopyServiceFactory;

	public ObjectCopyController(ShoppingCartService shoppingCartService,
			ObjectCopyServiceFactory objectCopyServiceFactory) {
		this.shoppingCartService = shoppingCartService;
		this.objectCopyServiceFactory = objectCopyServiceFactory;
	}

	@PostMapping(path = "/create")
	public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ObjectCreateRequestDto objectCreateRequestDto) {

		return new ResponseEntity<ShoppingCart>(
				shoppingCartService.createShoppingCart(objectCreateRequestDto.getSourceCartItems(),
						objectCreateRequestDto.getSourceCartName()),
				HttpStatus.OK);
	}

	@PostMapping(path = "/{copyType}/copy")
	public ResponseEntity<ObjectCopyResponseDto> copyShoppingCart(
			@RequestBody ObjectCopyRequestDto objectCopyRequestDto, @PathVariable("copyType") String copyType) throws InvalidCopyTypeException {

		log.debug("Object Copy Type is : " + copyType);

		ShoppingCart sourceShoppingCart = shoppingCartService.createShoppingCart(
				objectCopyRequestDto.getSourceCartItems(), objectCopyRequestDto.getSourceCartName());

		ShoppingCart copyShoppingCart = objectCopyServiceFactory.getObjectCopyService(copyType).copy(sourceShoppingCart,
				objectCopyRequestDto.getCopyCartName());

		addCartItems(copyShoppingCart, objectCopyRequestDto.getCopyCartItems());

		return new ResponseEntity<ObjectCopyResponseDto>(
				new ObjectCopyResponseDto(sourceShoppingCart, copyShoppingCart), HttpStatus.OK);
	}

	private void addCartItems(ShoppingCart shoppingCart, ArrayList<String> items) {
		shoppingCart.getItems().addAll(items);
	}
}
