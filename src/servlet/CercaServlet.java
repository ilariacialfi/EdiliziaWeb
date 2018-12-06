package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CercaServlet")
public class CercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CercaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Click "Esci"
		if (request.getParameter("esci") != null) {
			esci(request, response);
		}

		// Click "Crea/Modifica Stanza"
		if (request.getParameter("creaStanza") != null) {
			creaStanza(request, response);
		}

		// Click "Crea/Modifica Modello"
		if (request.getParameter("creaModello") != null) {
			creaModello(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Click "Esci"
	private void esci(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);
	}

	// Click "Crea/Modifica Stanza"
	private void creaStanza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
		rd.forward(request, response);
	}

	// Click "Crea/Modifica Modello"
	private void creaModello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
		rd.forward(request, response);
	}
}
