///**
// *
// */
//package edu.ics499.fumeappapi.services;
//
//
//import org.springframework.stereotype.Service;
//
///**
// * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
// *
// */
//
//@Service
//public class ValidationService {
//
//	/**
//	 *
//	 */
//	public static boolean validationCheck(String name, String pinNumber) {
//		Result result = new Result();
//		boolean flag = false;
//
//		try {
//			if(nameValidation(name) && pinValidation(pinNumber) ) flag = true;
//			result.setResultCode(Result.USER_REGISTRATION_COMPLETED);
//		} catch (Exception e) {
//			result.setResultCode(Result.OPERATION_FAILED);
//		}
//		return flag;
//	}
//
//	private static boolean nameValidation(String object) {
//		boolean flag = false;
//		String convert = object;
//		char[] nameArray = new char[object.length()];
//
//		for(int i = 0; i <= nameArray.length; i++) nameArray[i] = convert.charAt(i);
//		if(nameArray.length < 9) flag = true;
//		else {
//			System.out.println("Maximum of 10 characters allowed");
//		}
//
//		return flag;
//	}
//
//	// Pin will be 6 in length
//
//	private static boolean pinValidation(String pinNumber) {
//		boolean flag = false;
//		String convert = pinNumber;
//		char[] pinArray = new char[3];
//
//		for(int i = 0; i <= pinArray.length; i++) pinArray[i] = convert.charAt(i);
//		if(pinArray.length > 3)  flag = true;
//		else {
//			System.out.println("Maximum of 4 characters allowed");
//		}
//		return flag;
//	}
//
//
//}
