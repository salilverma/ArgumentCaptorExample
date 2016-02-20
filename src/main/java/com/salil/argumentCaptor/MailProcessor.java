package com.salil.argumentCaptor;

import com.salil.argumentCaptor.domain.EmpMailDTO;
import com.salil.argumentCaptor.domain.Employee;

public class MailProcessor {

	private final EmployeeDao employeeDao;
	private final MailingService mailingService;
	
	public MailProcessor(EmployeeDao employeeDao, MailingService mailer) {
		this.employeeDao = employeeDao;
		this.mailingService = mailer;
	}
	
	public void sendEmailToEmployee(int employeeId)
	{
		Employee employee = employeeDao.getEmployee(employeeId);
		if(isEmployeeMailValid(employee.getEmail()))
		{
		EmpMailDTO empMailDTO = new EmpMailDTO(employee.getName(), employee.getEmail(), employee.getSalarry());
		mailingService.sendEmail(empMailDTO);
		}
	}
	
	private boolean isEmployeeMailValid(String email)
	{
		return email!=null;
	}
}
