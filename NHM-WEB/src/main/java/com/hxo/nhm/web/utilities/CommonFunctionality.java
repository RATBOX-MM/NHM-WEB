package com.hxo.nhm.web.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CommonFunctionality {
	
	public static String generateID (String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
		Random random = new Random();
		String id = LocalDateTime.now().format(formatter) + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
		return value+"-"+id;
	}

}
