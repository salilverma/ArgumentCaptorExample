package com.salil.argumentCaptor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.salil.argumentCaptor.domain.EmpMailDTO;
import com.salil.argumentCaptor.domain.Employee;

@RunWith(MockitoJUnitRunner.class)
public class MailProcessorTest {

	@Mock
	private EmployeeDao employeeDao;
	@Mock
	private MailingService mailingService;
	
	private int employeeId=1;
	private String name="Salil";
	private String email="admin@salilstock.blogspot.com";
	private int salarry=5000;
	private Employee employee;
	
	@Before
	public void setup() {
		employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setName(name);
		employee.setEmail(email);
		employee.setSalarry(salarry);
	}
	
	
	@Test
	public void testSendEmailToEmployee() {
		MailProcessor mailProcessor = new MailProcessor(employeeDao, mailingService);
		Mockito.when(employeeDao.getEmployee(1)).thenReturn(employee);
		
//		Instantiate argument captor to capture the argument of EmployeeMail type		
		ArgumentCaptor<EmpMailDTO>argumentCaptor =ArgumentCaptor.forClass(EmpMailDTO.class);
		
//		Perform operation
		mailProcessor.sendEmailToEmployee(1);
		
//		Capture the argument
		Mockito.verify(mailingService).sendEmail(argumentCaptor.capture());
		
//		verify the correctness of the receiving object
		EmpMailDTO empMailDTO=argumentCaptor.getValue();
		assertEquals(empMailDTO.getReciepientEmail(), this.email);
		assertEquals(empMailDTO.getMessage(),"Dear Salil\n\n we are processing your salary amount 5000. \n\n Regards\nAdmin");
	}

}
