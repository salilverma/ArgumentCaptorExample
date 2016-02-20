package com.salil.argumentCaptor.domain;

public class EmpMailDTO {

	private final String reciepientEmail;
	private final String sender="admin@salilstock.blogspot.com";
	private final String subject="Salary notification";
	private final static String templateMessage="Dear %s\n\n we are processing your salary amount %s. \n\n Regards\nAdmin";
	private final String message;
	
	public EmpMailDTO(final String name,final String reciepientEmail,final int salary)
	{
		this.reciepientEmail=reciepientEmail;
		this.message=String.format(templateMessage, name,salary);
	}
	
	public String getReciepientEmail() {
		return reciepientEmail;
	}

	public String getSender() {
		return sender;
	}
	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}
}
