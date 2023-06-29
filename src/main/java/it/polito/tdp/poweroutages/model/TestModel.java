package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
//		LocalDate now= LocalDate.now();
//		
//		LocalDate then= now.plusYears(3);
//		
//		
//		Double ris;
//		
//		
//		if (now.isBefore(then)) {
//			System.out.println(true);
//
//		}
//		System.out.println(now);
		String s= "100.00";
		
		if(s.matches("[0-9]+")) {
			Double i= Double.parseDouble(s);
			System.out.println(i);
		}
			
	
	}

}
