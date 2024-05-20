package Controllers;

import java.io.IOException;

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

@WebServlet("/displayData")
public class DisplayDetails extends HttpServlet {
	private static TransactionDTO op=null;
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
	
		if(session==null || session.getAttribute("userId")==null ) {
			resp.sendRedirect("login.html");
		}
		
		
		String startpoint=req.getParameter("startpoint");
		String endpoint=req.getParameter("endpoint");
		ServiceClass s1=new ServiceClass();
		if(startpoint==null) {
			startpoint=s1.startPoint();
		}
		if(endpoint==null) {
			endpoint=s1.endpoint();
		}
		
		Transaction t=new Transaction();
		t.setEndpoint(endpoint);
		t.setStartpoint(startpoint);
		
		try {
		int id=(int) session.getAttribute("userId");
		t.getUser().setId(id);
		}catch(Exception e) {
			resp.sendRedirect("login.html");
		}
		Transaction data=op.getDetail(t);
		req.setAttribute("Data", data);
		req.getRequestDispatcher("ExpenseManger.jsp").forward(req, resp);
	}
}
