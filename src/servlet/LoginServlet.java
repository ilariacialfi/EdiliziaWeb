package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import control.LoginController;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DEVO AGGIUNGERE IL CONTROLLO DEL RUOLO
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

			try {
				if (LoginController.accedi(id, pass)) {
					RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
					rd.forward(request, response);
				} else {
					// segnala l'errore 
					response.setContentType("text/html");
					PrintWriter pw=response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Password o UserID errati');");
					pw.println("</script>");
					RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	

}
