package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.ServiceClass;
import Model.Transaction;
import Model.TransactionDTO;
import Model.TransactionDTOImplementation;
import Model.User;

@WebServlet(urlPatterns = { "/transactions" , "/month","/week","/today"})
public class AllTransactionsServlet extends HttpServlet{
	private static TransactionDTO op=null;
	private static HttpSession session=null;
	
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session=req.getSession(false);
		if(session.getAttribute("userId")==null || session==null) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String path=req.getRequestURI();
		System.out.println(path);
		switch(path) {
		case "/ExpenseTracker/transactions" :
			display(req,resp);
			break;
		case "/ExpenseTracker/month" :
			month(req,resp);
			break;
		case "/ExpenseTracker/week" :
			week(req,resp);
			break;
		case "/ExpenseTracker/today" :
			today(req,resp);
			break;
		}
	}
	
	
	private void month(HttpServletRequest req, HttpServletResponse resp) {
		if(session==null || session.getAttribute("userId")==null ) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServiceClass s1=new ServiceClass();
		String startpoint=s1.startPoint();
		String endpoint=s1.endpoint();
		
		Transaction t=new Transaction();
		t.setEndpoint(endpoint);
		t.setStartpoint(startpoint);
		
		
		int id=(int) session.getAttribute("userId");
		t.getUser().setId(id);
		
	
		ArrayList<Transaction> transactionsList=op.month(t);
		
		RequestDispatcher rd=req.getRequestDispatcher("Transactions.jsp");
		req.setAttribute("transactionsList", transactionsList);
		try {
			rd.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void week(HttpServletRequest req, HttpServletResponse resp) {
		if(session==null || session.getAttribute("userId")==null ) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServiceClass s1=new ServiceClass();
		Transaction t=s1.weekdetails();
		int id=(int) session.getAttribute("userId");
		t.getUser().setId(id);
		ArrayList<Transaction> transactionsList=op.month(t);
		RequestDispatcher rd=req.getRequestDispatcher("Transactions.jsp");
		req.setAttribute("transactionsList", transactionsList);
		try {
			rd.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void display(HttpServletRequest req, HttpServletResponse resp) {
		if(session==null || session.getAttribute("userId")==null ) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int id=(int)session.getAttribute("userId");
		User user=new User();
		user.setId(id);
		ArrayList<Transaction> transactionsList=op.display(user);
		RequestDispatcher rd=req.getRequestDispatcher("Transactions.jsp");
		req.setAttribute("transactionsList", transactionsList);
		try {
			rd.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void today(HttpServletRequest req, HttpServletResponse resp) {
		if(session==null || session.getAttribute("userId")==null ) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServiceClass s1=new ServiceClass();
		Transaction t=new Transaction();
		t.setStartpoint(s1.today);
		int id=(int) session.getAttribute("userId");
		t.getUser().setId(id);
		ArrayList<Transaction> transactionsList=op.today(t);
		RequestDispatcher rd=req.getRequestDispatcher("Transactions.jsp");
		req.setAttribute("transactionsList", transactionsList);
		try {
			rd.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
