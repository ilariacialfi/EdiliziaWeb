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

import control.CercaController;

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
			try {
				CercaController.resetAttrSel();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}

		// Click "Crea/Modifica Stanza"
		if (request.getParameter("creaStanza") != null) {
			try {
				CercaController.resetAttrSel();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
			rd.forward(request, response);
		}

		// Click "Crea/Modifica Modello"
		if (request.getParameter("creaModello") != null) {
			try {
				CercaController.resetAttrSel();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
			rd.forward(request, response);
		}

		// Click "Aggiungi"
		if (request.getParameter("aggAttr") != null) {
			// prendo i valori dell'attrezzatura selezionata e min e max
			String nome = request.getParameter("attrezzatura");
			String min = request.getParameter("minimo");
			String max = request.getParameter("massimo");

			if (nome.trim().isEmpty() || min.trim().isEmpty() || max.trim().isEmpty()) {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Selezionare un'attrezzatura e il minimo e massimo richiesti');");
				pw.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
				rd.include(request, response);
			} else if (Integer.valueOf(max) < Integer.valueOf(min)) {
				// Controllo che il minimo non sia maggiore del massimo
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Valori di min e max non validi');");
				pw.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
				rd.include(request, response);
			} else {
				try {
					if (CercaController.trovaAttrSel(nome) == true) {
						CercaController.aggiornaAttrSel(nome, Integer.valueOf(min), Integer.valueOf(max));
					} else {
						CercaController.aggiungiAttrSel(nome, Integer.valueOf(min), Integer.valueOf(max));
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher req = request.getRequestDispatcher("Cerca.jsp");
				req.forward(request, response);
			}
		}

		// Click "Elimina"
		if (request.getParameter("elAttr") != null) {
			String nome = request.getParameter("attrezzatura");

			if (nome.trim().isEmpty()) {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Selezionare l'attrezzatura da eliminare dal menu a cascata');");
				pw.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
				rd.include(request, response);
			} else
				try {
					if (CercaController.trovaAttrSel(nome) == true) {
						CercaController.eliminaAttrSel(nome);
						response.sendRedirect("Cerca.jsp");
					} else {
						response.setContentType("text/html");
						PrintWriter pw = response.getWriter();
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('L'attrezzatura selezionata non è in lista');");
						pw.println("</script>");
						RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
						rd.include(request, response);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		}

		// Click "Ok"
		if (request.getParameter("Ok") != null) {
			String stanza = request.getParameter("stanzeSel");
			try {
				if (stanza.trim().isEmpty())
					response.sendRedirect("Cerca.jsp");
				else {
					request.setAttribute("AttrezzaturaStanza", CercaController.estraiAttrezzaturaStanza(stanza));
					RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// riempie il menu a discesa delle attrezzature
	public void listaAttr(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		request.setAttribute("listaAttrezzatura", CercaController.estraiAttrezzatura());
	}

	// aggiorna la tabella delle attrezzature selezionate
	public void aggiornaAttrSel(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		request.setAttribute("AttrSelezionata", CercaController.estraiAttrSel());
	}

	// aggiorna la tabella delle stanze
	public void aggiornaStanze(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		if (CercaController.estraiAttrSel().size() == 0) {
			return;
		} else {
			request.setAttribute("Stanze", CercaController.estraiStanze(CercaController.estraiAttrSel()));
			request.setAttribute("StanzeSelezionate",
					CercaController.estraiNomiStanze(CercaController.estraiStanze(CercaController.estraiAttrSel())));
		}
	}


}
