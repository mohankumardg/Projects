package com.erevmax.utils;

@SuppressWarnings("serial")
public class CustomException extends Exception {
	
	String message;
	public CustomException(){
		
	}
	
	public CustomException (String message)
    {
    super (message);
    }

}