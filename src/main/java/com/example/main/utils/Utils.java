package com.example.main.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	 private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	 private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	  public static String toSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-").replaceAll("đ", "d").replaceAll("Đ", "D");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	  }
	  
	  public static boolean isDate(String date) {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		  try {
		        LocalDate dateTime = LocalDate.parse(date, formatter);
		       return true;
		    } catch (DateTimeParseException ex) {
		        return false;
		    }
	  }
}
