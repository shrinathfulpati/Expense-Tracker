package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ServiceClass {
	
	public String today=(LocalDate.now()).toString();

	public String endpoint() {
		// TODO Auto-generated method stub
		Calendar calender=Calendar.getInstance();
		int date =calender.getActualMaximum(Calendar.DATE);
		
		Date d=new Date();
		int year=d.getYear()+1900;
		int month=d.getMonth()+1;
		
		String endpoint=year+"-"+month+"-"+date;
		return endpoint;
		
	}

	public String startPoint() {
		// TODO Auto-generated method stub
		Calendar calender=Calendar.getInstance();
		int date =calender.getActualMinimum(Calendar.DATE);
		
		Date d=new Date();
		int year=d.getYear()+1900;
		int month=d.getMonth()+1;
		
		String startpoint=year+"-"+month+"-"+date;
		return startpoint;
	}
	
	public Transaction weekdetails() {
		LocalDate today=LocalDate.now();
		System.out.println(today);
		LocalDate startpoint=today;
		Transaction t=new Transaction();
		
		while(startpoint.getDayOfWeek() != DayOfWeek.MONDAY) {
			startpoint=startpoint.minusDays(1);
		}
		t.setStartpoint(startpoint.toString());
		System.out.println(startpoint.toString());
		
		LocalDate endpoint=today;
		while(endpoint.getDayOfWeek() != DayOfWeek.SUNDAY) {
			endpoint=endpoint.plusDays(1);
		}
		t.setEndpoint(endpoint.toString());
		System.out.println(endpoint.toString());
		return t;
	}
}
