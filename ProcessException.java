package test;

public class ProcessException extends java.lang.Exception{
	ProcessException(String message)
	{
		super(System.currentTimeMillis() +": "+message);
	//	System.out.println("In expection");
	}	
}
