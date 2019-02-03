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
import control.ModelloController;
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
	protected Boolean attribStanza = true;

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

		// Click "Ok"
		if (request.getParameter("ok") != null) {
			String stanza = request.getParameter("stanze");
			try {
				if (stanza.trim().isEmpty()) {
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (stanza.trim().equals("Crea Nuova Stanza")) {
					stanzaSel = null;
					attrSel = null;
					modelloSel = null;
					attribStanza = true;
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					stanzaSel = stanza;
					modelloSel = null;
					attrSel = StanzaController.estraiAttrStanza(stanzaSel);
					attribStanza = false;
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
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Seleziona una stanza da eliminare');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (stanza.equals("Crea Nuova Stanza")) {
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					StanzaController.eliminaStanza(stanza);
					stanzaSel = null;
					modelloSel = null;
					attribStanza = true;
					attrSel = null;

					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Click "Importa Modello"
		if (request.getParameter("importa") != null) {
			String modello = request.getParameter("modelli");
			try {
				if (modello.trim().isEmpty()) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Selezionare un modello');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					modelloSel = modello;
					attrSel = StanzaController.aggiungiModello(stanzaSel, ModelloController.estraiAttr(modelloSel));
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
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
				} else if (stanzaSel == null || attribStanza == true) {
					stanzaSel = nome;
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Nome temporaneo, ricordati di salvare!');");
					pw.println("</script>");
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
					attrSel = StanzaController.aggiornaAttrezzatura(attr, Integer.valueOf(quantita), attrSel);
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					attrSel.add(attrSel.size(),
							StanzaController.creaStanza(stanzaSel, attr, Integer.valueOf(quantita)));
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
			edificio = request.getParameter("edificio");
			piano = request.getParameter("piano");
			tipo = request.getParameter("tipo");
			stanzaSel = request.getParameter("nome");

			try {
				if (stanzaSel.trim().isEmpty() || edificio.trim().isEmpty() || piano.trim().isEmpty()
						|| tipo.trim().isEmpty()) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Inserire un nome, edificio, piano e tipo per la stanza');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (attrSel == null) {
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Aggiungere almeno una attrezzatura!');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else if (StanzaController.cercaStanza(stanzaSel)) {
					StanzaController.aggiornaStanza(stanzaSel, attrSel);
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Stanza aggiornata con successo');");
					pw.println("</script>");
					aggiornaForm(request, response, stanzaSel, modelloSel, attrSel);
					RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
					rd.include(request, response);
				} else {
					attribStanza = false;
					StanzaController.salvaStanza(stanzaSel, edificio, piano, tipo, attrSel);
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Stanza salvata con successo!');");
					pw.println("</script>");
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

		if (attribStanza == true) {
			sbloccaAttributi(request, response);
		} else {
			bloccaAttributi(request, response, stanzaSel);
		}
		
		if (modelloSel == null) {
			request.setAttribute("listaModelli", StanzaController.estraiModello());
		} else {
			request.setAttribute("listaModelli", modelloSel);
		}
	}

	private void sbloccaAttributi(HttpServletRequest request, HttpServletResponse response) {
		attribStanza = true;

		edificio = null;
		piano = null;
		tipo = null;

		String[] edifici = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "L", "M", "N" };
		String[] piani = { "T", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		String[] tipi = { "aula", "ufficio", "laboratorio", "sala convegni" };

		request.setAttribute("listaEdifici", edifici);
		request.setAttribute("listaPiani", piani);
		request.setAttribute("listaTipi", tipi);
	}

	private void bloccaAttributi(HttpServletRequest request, HttpServletResponse response, String stanzaSel)
			throws ClassNotFoundException, SQLException {
		attribStanza = false;

		edificio = StanzaController.estraiEdificio(stanzaSel);
		piano = StanzaController.estraiPiano(stanzaSel);
		tipo = StanzaController.estraiTipo(stanzaSel);

		request.setAttribute("listaEdifici", edificio);
		request.setAttribute("listaPiani", piano);
		request.setAttribute("listaTipi", tipo);

	}

}
