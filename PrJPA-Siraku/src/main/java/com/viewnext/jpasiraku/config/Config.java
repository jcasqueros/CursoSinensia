package com.viewnext.jpasiraku.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuración del proyecto JPA Siraku.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Configuration
@Aspect
@Slf4j
public class Config {

	/**
	 * Configuración básica del {@link ModelMapper}.
	 * 
	 * @return
	 */

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Configuración del {@link Aspect} para hacer log en capa de presentación.
	 * 
	 * @param joinPoint - {@link JoinPoint}
	 */

	@Before(value = "execution(* com.viewnext.jpasiraku.presentation.controller.*.* (..))")
	public void controllerLog(JoinPoint joinPoint) {
		String clase = joinPoint.getTarget().getClass().getSimpleName();
		String signature = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		String argumentos = "";

		for (Object arg : args) {
			if (arg != null) {
				argumentos += "\nTipo: " + arg.getClass().getName() + " | Valor: " + arg.toString() + ".\n";
			} else {
				argumentos += "\nArgumento nulo.\n";
			}
		}

		log.info("Capa de presentación: [{}] en la clase [{}] con argumentos[{}]", signature, clase, argumentos);
	}

	/**
	 * Configuración del {@link Aspect} para hacer log en capa de negocio.
	 * 
	 * @param joinPoint - {@link JoinPoint}
	 */

	@Before(value = "execution(* com.viewnext.jpasiraku.business.services.impl.*.* (..))")
	public void servicesLog(JoinPoint joinPoint) {
		String clase = joinPoint.getTarget().getClass().getSimpleName();
		String signature = joinPoint.getSignature().getName();

		log.info("Capa de negocio: [{}] en la clase [{}]", signature, clase);
	}

}
