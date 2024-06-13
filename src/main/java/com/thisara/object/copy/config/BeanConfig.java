package com.thisara.object.copy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thisara.object.copy.factory.ObjectCopyServiceFactory;
import com.thisara.object.copy.service.CloneService;
import com.thisara.object.copy.service.DeepCloneService;
import com.thisara.object.copy.service.DeepCopyService;
import com.thisara.object.copy.service.ShallowCopyService;
import com.thisara.object.copy.service.ShoppingCartService;

@Configuration
public class BeanConfig {

	@Bean
	public ShoppingCartService getShoppingCartService() {
		return new ShoppingCartService();
	}
	
	@Bean
	public ShallowCopyService getShallowCopyService() {
		return new ShallowCopyService();
	}

	@Bean
	public DeepCopyService getDeepCopyService() {
		return new DeepCopyService();
	}

	@Bean
	public DeepCloneService getDeepCloneService() {
		return new DeepCloneService();
	}
	
	@Bean
	public CloneService getCloneService() {
		return new CloneService();
	}
	
	@Bean
	public ObjectCopyServiceFactory getObjectCopyServiceFactory(ShallowCopyService shallowCopyService,
			DeepCopyService deepCopyService, CloneService cloneService, DeepCloneService deepCloneService) {
		return new ObjectCopyServiceFactory(shallowCopyService, deepCopyService, cloneService, deepCloneService);
	}
}
