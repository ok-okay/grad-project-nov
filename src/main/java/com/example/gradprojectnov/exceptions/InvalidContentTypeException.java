package com.example.gradprojectnov.exceptions;

import java.io.Serializable;

public class InvalidContentTypeException
		extends RuntimeException
		implements Serializable 
{
	    private static final long serialVersionUID = 1L;
	
	    public InvalidContentTypeException(String message) {
	        super(message);
	    }
}