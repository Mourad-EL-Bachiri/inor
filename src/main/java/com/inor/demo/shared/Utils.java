package com.inor.demo.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	 private final Random RANDOM = new SecureRandom();
	 private final String AlfaNumerique = "0123456789ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuxyz";
	 
	 public String generatId(int length) {
		 StringBuilder returnValue = new StringBuilder(length);
		 
		 for (int i = 0 ; i<length ; i++) {
			 returnValue.append(AlfaNumerique.charAt(RANDOM.nextInt(AlfaNumerique.length())));
		 }
		 
		 return new String(returnValue);
		 
	 }

}
