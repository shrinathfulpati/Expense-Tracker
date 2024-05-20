package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutLink")
public class NavbarServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logout(req, resp);
	}

//	private void navbar(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//			req.getRequestDispatcher("logout.jsp").forward(req, resp);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session=req.getSession(false);
		if(session==null) {
			try {
				resp.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		session.invalidate();
		try {
			resp.sendRedirect("login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
