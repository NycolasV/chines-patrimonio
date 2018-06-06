package br.info.pweb.chines.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {

	public static Map<String, String> mapFrom(BindingResult bindingResult) {
		Map<String, String> mapErrors = new HashMap<>();
		
		for (FieldError error : bindingResult.getFieldErrors()) {
			mapErrors.put(error.getField(), error.getDefaultMessage());
		}
		
		return mapErrors;
	}
	
}
