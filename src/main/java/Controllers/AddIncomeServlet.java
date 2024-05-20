package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Transaction;
import Model.TransactionDTO;
import Model.TransactionDTOImplementation;

@WebServlet("/AddIncomeLink")
public class AddIncomeServlet extends HttpServlet{
	private static TransactionDTO op=null;

	
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addIncome(req, resp);
	}

	private void addIncome(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session=req.getSession(false);
		if(session==null) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Transaction t=new Transaction();
		t.getUser().setId(Integer.parseInt(req.getParameter("userId")));
		t.setIncome(Double.parseDouble(req.getParameter("income")));
		t.setCategory(req.getParameter("category"));
		t.setPaymentMethod(req.getParameter("paymentMethod"));
		t.setNote(req.getParameter("note"));
		
		String dateString=req.getParameter("date");
		SimpleDateFormat dataFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date=dataFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setDate(new java.sql.Date(date.getTime()));
		
		String timeString=req.getParameter("time");
		LocalTime time=LocalTime.parse(timeString);
		t.setTime(java.sql.Time.valueOf(time));

		int count=op.addIncome(t);
		try {
			resp.sendRedirect("displayData");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
