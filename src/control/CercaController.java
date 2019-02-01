package control;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.AttrezzaturaDAO;
import dao.AttrezzaturaSelezionataDAO;
import dao.AttrezzaturaStanzaDAO;
import dao.StanzaDAO;
import bean.Attrezzatura;
import bean.AttrezzaturaStanza;
import bean.Stanza;

public class CercaController {

	public static ArrayList<String> estraiAttrezzatura() throws ClassNotFoundException, SQLException {
		return AttrezzaturaDAO.getInstance().estraiAttrezzatura();
	}

	// ritorna true se l'attrezzatura selezionata è già presente
	public static boolean trovaAttrSel(String nome) throws ClassNotFoundException, SQLException {
		return AttrezzaturaSelezionataDAO.getInstance().trovaAttrSel(nome);
	}

	public static void aggiornaAttrSel(String nome, int min, int max) throws ClassNotFoundException, SQLException {
		AttrezzaturaSelezionataDAO.getInstance().aggiornaAttrSel(nome, min, max);
		return;
	}

	public static void aggiungiAttrSel(String nome, int min, int max) throws ClassNotFoundException, SQLException {
		AttrezzaturaSelezionataDAO.getInstance().aggiungiAttrSel(nome, min, max);
		return;
	}

	// estrae tutte le attrezzature selezionate
	public static ArrayList<Attrezzatura> estraiAttrSel() throws ClassNotFoundException, SQLException {
		return AttrezzaturaSelezionataDAO.getInstance().estraiAttrSel();
	}

	public static void resetAttrSel() throws ClassNotFoundException, SQLException {
		AttrezzaturaSelezionataDAO.getInstance().resetAttrSel();
		return;
	}

	public static void eliminaAttrSel(String nome) throws ClassNotFoundException, SQLException {
		AttrezzaturaSelezionataDAO.getInstance().eliminaAttrSel(nome);
		return;
	}

	public static ArrayList<Stanza> estraiStanze(ArrayList<Attrezzatura> listAttrSel) throws ClassNotFoundException, SQLException {
		return AttrezzaturaStanzaDAO.getInstance().estraiStanzaConAttr(listAttrSel);
	}

	public static ArrayList<String> estraiNomiStanze(ArrayList<Stanza> listStanzeSel) {
		ArrayList<String> listNomiStanze = new ArrayList<String>();
		for(Stanza s : listStanzeSel) {
			listNomiStanze.add(s.getNome());
		}
		return listNomiStanze;
	}

	public static ArrayList<AttrezzaturaStanza> estraiAttrezzaturaStanza(String stanza) throws ClassNotFoundException, SQLException {
		return AttrezzaturaStanzaDAO.getInstance().getAttrSt(stanza);
	}
}
