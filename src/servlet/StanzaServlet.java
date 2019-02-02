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

import bean.AttrezzaturaStanza;
import control.StanzaController;

@WebServlet("/StanzaServlet")
public class StanzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ArrayList<AttrezzaturaStanza> attrSel = new ArrayList<>();
	protected String stanzaSel = null;
	protected String modelloSel = null;
	protected String edificio = null;
	protected String piano = null;
	protected String tipo = null;

	public StanzaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Click "Indietro"
		if (request.getParameter("indietro") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
			rd.forward(request, response);
		}

		// Click "Visualizza"
		if (request.getParameter("visualizza") != null) {
			String stanza = request.getParameter("stanze");
			try {
				if (stanza.trim().isEmpty())
					response.sendRedirect("CreaStanza.jsp");
				else if (stanza.trim().equals("Crea Nuova Stanza")) {
					stanzaSel = null;
					attrSel = null;
					modelloSel = null;
					sbloccaAttributi(request, response);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					stanzaSel = stanza;
					modelloSel = null;
					attrSel = StanzaController.estraiAttrStanza(stanzaSel);
					bloccaAttributi(request, response, stanzaSel);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Elimina Stanza"
		if (request.getParameter("elStanza") != null) {
			String stanza = request.getParameter("stanze");
			try {
				if (stanza.trim().isEmpty()) {
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (stanza.equals("Crea Nuova Stanza")) {
					stanzaSel = null;
					modelloSel = null;
					attrSel = null;
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					StanzaController.eliminaStanza(stanza);
					modelloSel = null;
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
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
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (StanzaController.cercaStanza(nome) == true) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Un modello con questo nome già esiste');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (stanzaSel == null) {
					stanzaSel = nome;
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					StanzaController.rinominaStanza(stanzaSel, nome);
					stanzaSel = nome;
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Rinominato!');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Aggiungi"
		if (request.getParameter("aggiungi") != null) {
			String attr = request.getParameter("attrezzatura");
			String quantita = request.getParameter("quantita");

			try {
				if (attr.trim().isEmpty() || quantita.trim().isEmpty()) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Inserire attrezzatura e quantità');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (attrSel == null) {
					attrSel = new ArrayList<>();
					attrSel.add(StanzaController.creaStanza(stanzaSel, attr, Integer.valueOf(quantita)));
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (StanzaController.trovaAttr(attr, attrSel) == true) {
					System.out.println("script");
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Attrezzatura già presente!');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					attrSel.add(attrSel.size(), StanzaController.creaStanza(stanzaSel, attr, Integer.valueOf(quantita)));
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
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
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					int i = attrSel.indexOf(attr);
					attrSel.remove(i);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Salva"
		if (request.getParameter("salva") != null) {
			try {
				if (StanzaController.cercaStanza(stanzaSel)) {
					StanzaController.aggiornaStanza(stanzaSel, attrSel);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					// StanzaController.salvaStanza(stanzaSel, edificio, piano,
					// tipo, attrSel);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
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

	public void aggiornaListaStanze(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		ArrayList<String> stanze = StanzaController.estraiStanza();
		stanze.add(stanze.size(), "Crea Nuova Stanza");
		request.setAttribute("listaStanze", stanze);
	}

	public void aggiornaForm(HttpServletRequest request, HttpServletResponse response, String stanzaSel,
			String modelloSel, ArrayList<AttrezzaturaStanza> attrSel)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		request.setAttribute("nomeStanza", stanzaSel);
		request.setAttribute("listaAttrezzatura", StanzaController.estraiAttrezzatura());
		request.setAttribute("attrezzaturaStanza", attrSel);
	}
	
	private void sbloccaAttributi(HttpServletRequest request, HttpServletResponse response) {
		String[] edifici = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "L", "M", "N"};
		String[] piani = {"T", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		String[] tipi = {"aula", "ufficio", "laboratorio"};
		
		request.setAttribute("listaEdifici", edifici);
		request.setAttribute("listaPiani", piani);
		request.setAttribute("listaTipi", tipi);
	}
	

	private void bloccaAttributi(HttpServletRequest request, HttpServletResponse response, String stanzaSel) throws ClassNotFoundException, SQLException {
		
		System.out.println(StanzaController.estraiEdificio(stanzaSel));
		request.setAttribute("listaEdifici", StanzaController.estraiEdificio(stanzaSel));
		request.setAttribute("listaPiani", StanzaController.estraiPiano(stanzaSel));
		request.setAttribute("listaTipi", StanzaController.estraiTipo(stanzaSel));
	}

}
