package com.yashank.pwskill.watchlist.ExceptionHandle;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
