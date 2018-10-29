package com.sas.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.sas.util.ValidateString;

public class StringValidator implements ConstraintValidator<ValidateString, String>{

    private List<String> valueList;

    @Override
    public void initialize(ValidateString constraintAnnotation) {
        valueList = new ArrayList<String>();
        for(String val : constraintAnnotation.acceptedValues()) {
            valueList.add(val);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String check : valueList) {
			if (StringUtils.equalsIgnoreCase(value, check))
				return true;
		}
        return false;
    }

}
