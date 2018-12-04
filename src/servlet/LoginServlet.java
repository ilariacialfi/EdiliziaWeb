package servlet;

import java.io.IOException;
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
				RequestDispatcher rd = request.getRequestDispatcher("ErroreLogin.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
