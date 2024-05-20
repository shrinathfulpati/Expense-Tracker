package Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

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

@WebServlet("/AddExpenseLink")
public class AddExpenseServlet extends HttpServlet {
	private static TransactionDTO op;
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		if(session==null) {
			resp.sendRedirect("login.html");
		}
		Transaction t=new Transaction();
		t.getUser().setId(Integer.parseInt(req.getParameter("userId")));
		t.setExpense(Double.parseDouble(req.getParameter("expense")));
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

		int count=op.addExpense(t);
		resp.sendRedirect("displayData");
	}
}
