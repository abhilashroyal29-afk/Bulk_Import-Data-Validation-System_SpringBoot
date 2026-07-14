package com.example.demo.exception;

public class DuplicateFileException extends RuntimeException{
	
	public  DuplicateFileException(String message) {
		super(message);
	}

}
