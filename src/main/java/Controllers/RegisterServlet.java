package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.TransactionDTO;
import Model.TransactionDTOImplementation;
import Model.User;

@WebServlet("/registerLink")
public class RegisterServlet extends HttpServlet {
	private static TransactionDTO op;
	@Override
	public void init() throws ServletException {
		op=TransactionDTOImplementation.getInstance();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		registerUser(req, resp);
	}

	private void registerUser(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String mobileNumber=req.getParameter("mobile") ;
		String password=req.getParameter("password");
		User user=new User(name, email, mobileNumber, password);
		if(!(name.isBlank() || email.isBlank() || mobileNumber.isBlank() || password.isBlank() )) {
		int count=op.register(user);
		try {
			req.getRequestDispatcher("login.html").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			try {
				resp.sendRedirect("register.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
