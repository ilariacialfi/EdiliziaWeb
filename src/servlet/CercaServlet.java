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

import org.apache.catalina.ant.ReloadTask;

import bean.Attrezzatura;
import control.CercaController;
import dao.AttrezzaturaSelezionataDAO;

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
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}

		// Click "Crea/Modifica Stanza"
		if (request.getParameter("creaStanza") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("CreaStanza.jsp");
			rd.forward(request, response);
		}

		// Click "Crea/Modifica Modello"
		if (request.getParameter("creaModello") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("CreaModello.jsp");
			rd.forward(request, response);
		}

//		// Click "Aggiungi"
//		if (request.getParameter("aggAttr") != null) {
//			// prendo i valori dell'attrezzatura selezionata e min e max
//			String nome = request.getParameter("attrezzatura");
//			int min = Integer.parseInt(request.getParameter("minimo"));
//			int max = Integer.parseInt(request.getParameter("massimo"));
//
//			Attrezzatura attrSel = new Attrezzatura(nome, min, max);
//			
//			// Controllo che il minimo non sia maggiore del massimo
//			if (max < min) {
//				response.setContentType("text/html");
//				PrintWriter pw = response.getWriter();
//				pw.println("<script type=\"text/javascript\">");
//				pw.println("alert('Valori di min e max non validi');");
//				pw.println("</script>");
//				RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
//				rd.include(request, response);
//			}
//			
//			// Controllo che l'attrezzatura selezionata non sia già presente
//			try {
//				if (CercaController.searchAttrSel(nome) == true) { 
//					//true = l'attrezzatura già esiste
//					response.setContentType("text/html");
//					PrintWriter pw = response.getWriter();
//					pw.println("<script type=\"text/javascript\">");
//					pw.println("alert('Questa attrezzatura è già nella lista');");
//					pw.println("</script>");
//					RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
//					rd.include(request, response);
//				} else {
//					//false aggiungo la riga alla tabella del db
//					CercaController.addAttrSel(attrSel);
//					
//					//DEVO FAR RICARICARE LA PAGINA PER AGGIORNARE LA TABELLA
//					request.setAttribute("AttrSelezionata", attrSel);
//					//LA RIGA SOPRA DOVREBBE ESSERE CORRETTA MA NON RICARICA LA PAGINA
//					
//				
//				}
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//				
//		}

		// Click "Elimina"
		if (request.getParameter("elAttr") != null) {
			// elimina attrezzatura dalla tabella
			
		}

		// Click "Cerca"
		if (request.getParameter("cerca") != null) {
			// cerca stanze
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Click della Select Attrezzatura
	public void listaAttr(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		request.setAttribute("listaAttrezzatura", CercaController.estraiAttrezzatura());
	}
	
	// Alla prima apertura reset della tabella del db attr_sel
	public void resetAttrSel(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		CercaController.resetAttrSel();
	}
	//QUESTA PROBABIEMTE E' DA TOGLIERE..O QUESTA O QUELLA DEL PULSANTE AGGIUNGI..CONTROLLARE..NON FUNZIONA NESSUNA DELLE DUE
	public void addAttrSel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("attrezzatura");
		//problema nelle seguenti due righe
		int min = Integer.parseInt(request.getParameter("minimo"));
		int max = Integer.parseInt(request.getParameter("massimo"));

		Attrezzatura attrSel = new Attrezzatura(nome, min, max);
		
		request.setAttribute("AttrSelezionata", attrSel);
		
		RequestDispatcher rd = request.getRequestDispatcher("Cerca.jsp");
				rd.forward(request, response);

	}
	
}
