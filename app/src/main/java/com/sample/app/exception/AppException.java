package com.sample.app.exception;

public class AppException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8214850602010960384L;
	
	public static final String API_DESC_NULL = "Application description is lacking. Please check API_DESC table";
    public static final String PRODUCT_NULL = "There is no any product with such id: ";
    
    private static final String MESSAGE = "Action forbidden";
    
    public AppException() {
        super(MESSAGE);
    }
    
    public AppException(String message) {
        super(message);
    }

}
