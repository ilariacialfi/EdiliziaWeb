package control;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.AttrezzaturaDAO;
import dao.AttrezzaturaStanzaDAO;
import dao.ModelloDAO;
import dao.StanzaDAO;
import bean.AttrezzaturaStanza;
import bean.Stanza;

public class StanzaController {

	public static ArrayList<String> estraiAttrezzatura() throws ClassNotFoundException, SQLException {
		return AttrezzaturaDAO.getInstance().estraiAttrezzatura();
	}

	public static ArrayList<String> estraiModello() throws ClassNotFoundException, SQLException {
		return ModelloDAO.getInstance().getModello();
	}

	public static ArrayList<String> estraiStanza() throws ClassNotFoundException, SQLException {
		return StanzaDAO.getInstance().getStanza();
	}

	public static boolean cercaStanza(String nomeStanza) throws SQLException, ClassNotFoundException {
		// true se la stanza già esiste
		if (StanzaDAO.getInstance().cercaStanza(nomeStanza) == null) {
			return false;
		}
		return true;
	}

	public static AttrezzaturaStanza aggiungiRiga(String attrSel, int quantita) {
		return new AttrezzaturaStanza(attrSel, null, quantita);
	}

	public static Stanza trovaStanza(String stanzaImp) throws ClassNotFoundException, SQLException {
		return StanzaDAO.getInstance().getStanzaByName(stanzaImp);
	}

	public static ArrayList<AttrezzaturaStanza> estraiAttrStanza(String stanzaImp)
			throws ClassNotFoundException, SQLException {
		return AttrezzaturaStanzaDAO.getInstance().getAttrSt(stanzaImp);
	}

	public static void eliminaStanza(String stanzaSel) throws ClassNotFoundException, SQLException {
		StanzaDAO.getInstance().eliminaStanza(stanzaSel);
		AttrezzaturaStanzaDAO.getInstance().eliminaStanza(stanzaSel);
		return;
	}

	public static void salvaStanza(String stanza, String edificio, String piano, String tipo,
			ArrayList<AttrezzaturaStanza> attrSt) throws ClassNotFoundException, SQLException {
		// faccio salvare la stanza nelle due diverse tabelle del db
		AttrezzaturaStanzaDAO.getInstance().salvaStanza(stanza, attrSt);
		StanzaDAO.getInstance().salvaStanza(stanza, edificio, piano, tipo);
		return;
	}

	public static void aggiornaStanza(String stanza, ArrayList<AttrezzaturaStanza> attrSt)
			throws ClassNotFoundException, SQLException {
		AttrezzaturaStanzaDAO.getInstance().aggiornaStanza(stanza, attrSt);
		return;
	}

	public static void rinominaStanza(String prevName, String nextName) throws ClassNotFoundException, SQLException {
		AttrezzaturaStanzaDAO.getInstance().rinominaStanza(prevName, nextName);
		StanzaDAO.getInstance().rinominaStanza(prevName, nextName);
		return;
	}

	public static AttrezzaturaStanza creaStanza(String stanzaSel, String attr, int quantita) {
		return new AttrezzaturaStanza(attr, stanzaSel, quantita);
	}

	public static boolean trovaAttr(String attr, ArrayList<AttrezzaturaStanza> attrSel) {
		ArrayList<String> nomi = new ArrayList<>();
		
		for (AttrezzaturaStanza a : attrSel){
			nomi.add(a.getAttr());
		}
		if (nomi.contains(attr)){
			return true;
		}
		return false;
	}

	public static String estraiEdificio(String stanzaSel) throws ClassNotFoundException, SQLException {
		Stanza stanza = StanzaDAO.getInstance().cercaStanza(stanzaSel);
		return stanza.getEdificio();
	}

	public static String estraiTipo(String stanzaSel) throws ClassNotFoundException, SQLException {
		return StanzaDAO.getInstance().cercaStanza(stanzaSel).getTipo();
	}

	public static String estraiPiano(String stanzaSel) throws ClassNotFoundException, SQLException {
		return StanzaDAO.getInstance().cercaStanza(stanzaSel).getPiano();
	}

	public static ArrayList<AttrezzaturaStanza> aggiungiModello(String stanzaSel, ArrayList<String> attrModello) {
		ArrayList<AttrezzaturaStanza> as = new ArrayList<>();
		for (String m : attrModello){
			as.add(new AttrezzaturaStanza(m, stanzaSel, 0));
		}
		return as;
	}

	public static ArrayList<AttrezzaturaStanza> aggiornaAttrezzatura(String attr, Integer quantita,
			ArrayList<AttrezzaturaStanza> attrSel) {
		for (AttrezzaturaStanza aStanza : attrSel){
			if (aStanza.getAttr().equals(attr)){
				aStanza.setQuantita(quantita);
			}
		}
		return attrSel;
	}

}
