package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import control.ModelloController;

@WebServlet("/ModelloServlet")
public class ModelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ArrayList<String> attrSel = new ArrayList<String>();
	protected String modelloSel = null;

	public ModelloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Click "Indietro"
		if (request.getParameter("indietro") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
			rd.forward(request, response);
		}

		// Click "visualizza"
		if (request.getParameter("visualizza") != null) {
			String modello = request.getParameter("modelli");
			try {
				if (modello.trim().isEmpty()){
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}else if (modello.trim().equals("Crea Nuovo Modello")) {
					modelloSel = null;
					attrSel = null;
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					modelloSel = modello;
					attrSel = ModelloController.estraiAttr(modello);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Elimina Modello"
		if (request.getParameter("elModello") != null) {
			String modello = request.getParameter("modelli");
			try {
				if (modello.trim().isEmpty()) {
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else if (modello.equals("Crea Nuovo Modello")) {
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					ModelloController.eliminaModello(modello);
					modelloSel = null;
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Rinomina"
		if (request.getParameter("rinomina") != null) {
			String nome = request.getParameter("nome");
			try {
				if (nome.trim().isEmpty()) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Inserire un nome valido');");
					pw.println("</script>");
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else if (ModelloController.cercaModello(nome) == true) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Un modello con questo nome già esiste');");
					pw.println("</script>");
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else if (modelloSel == null) {
					modelloSel = nome;
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					ModelloController.rinominaModello(modelloSel, nome);
					modelloSel = nome;
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Rinominato!');");
					pw.println("</script>");
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Aggiungi"
		if (request.getParameter("aggiungi") != null) {
			String attr = request.getParameter("attrezzatura");
			try {
				if (attr.trim().isEmpty()) {
					aggiornaForm(request, response, attrSel, modelloSel);
				} else if (attrSel == null) {

					attrSel = new ArrayList<String>();
					attrSel.add(attr);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else if (attrSel.contains(attr)) {
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					attrSel.add(attrSel.size(), attr);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Elimina Attrezzatura"
		if (request.getParameter("elimina") != null) {
			String attr = request.getParameter("attrezzatura");
			try {
				if (attr.trim().isEmpty()) {
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					attrSel.remove(attr);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Salva"
		if (request.getParameter("salva") != null) {
			try {
				if (ModelloController.cercaModello(modelloSel)) {
					ModelloController.aggiornaModello(modelloSel, attrSel);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
					rd.forward(request, response);
				} else {
					ModelloController.salvaModello(modelloSel, attrSel);
					aggiornaForm(request, response, attrSel, modelloSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
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

	// aggiorna select modelli
	public void aggiornaListaModelli(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		ArrayList<String> modelli = ModelloController.estraiModelli();
		modelli.add(modelli.size(), "Crea Nuovo Modello");
		request.setAttribute("listaModelli", modelli);
	}

	public void aggiornaForm(HttpServletRequest request, HttpServletResponse response, ArrayList<String> attrSel,
			String modelloSel) throws ClassNotFoundException, SQLException, ServletException, IOException {
		request.setAttribute("nomeModello", modelloSel);
		request.setAttribute("listaAttrezzatura", ModelloController.estraiAttrezzatura());
		request.setAttribute("attrezzaturaModello", attrSel);
	}

}
