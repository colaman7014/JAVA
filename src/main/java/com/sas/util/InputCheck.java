package com.sas.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Terry
 * 
 *  檢查前端傳入內容
 *  
 */
public class InputCheck {
	
	public static <T> String validate(T bean) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(bean);
		StringBuffer message = new StringBuffer();
		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				message.append(String.format("%s. ",
						constraintViolation.getMessage()));
			}
		}
		return message.toString();
	}
	
}
