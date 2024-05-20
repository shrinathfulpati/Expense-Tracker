package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.TransactionDTO;
import Model.TransactionDTOImplementation;
import Model.User;

@WebServlet("/loginLink")
public class LoginServlet extends HttpServlet {
	private static TransactionDTO op;
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		validate(req, resp);
	}
	private void validate(HttpServletRequest req, HttpServletResponse resp) {
		String mobileNumber=req.getParameter("mobile") ;
		String password=req.getParameter("password");
		
		User user=new User(mobileNumber,password);
		int id=op.login(user);
		System.out.println(id);
		HttpSession session=null;
		if(id>0) {
			session=req.getSession(true);
			System.out.println(session);
			session.setAttribute("userId",id);
			System.out.println(session.getAttribute("userId"));
			try {
				resp.sendRedirect("displayData");
			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				resp.sendRedirect("login.html?error=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
