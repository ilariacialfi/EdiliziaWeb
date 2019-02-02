package control;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.AttrezzaturaDAO;
import dao.AttrezzaturaModelloDAO;
import dao.ModelloDAO;

public class ModelloController {

	public static ArrayList<String> estraiModelli() throws ClassNotFoundException, SQLException {
		return ModelloDAO.getInstance().getModello();
	}

	public static ArrayList<String> estraiAttrezzatura() throws ClassNotFoundException, SQLException {
		return AttrezzaturaDAO.getInstance().estraiAttrezzatura();
	}

	public static boolean cercaModello(String mod) throws ClassNotFoundException, SQLException {
		// true se il modello gia esiste
		return ModelloDAO.getInstance().cercaModello(mod);
	}

	public static ArrayList<String> estraiAttr(String mod) throws ClassNotFoundException, SQLException {
		return AttrezzaturaModelloDAO.getInstance().getAttrezzatura(mod);
	}

	public static void eliminaModello(String mod) throws ClassNotFoundException, SQLException {
		AttrezzaturaModelloDAO.getInstance().eliminaModello(mod);
	}

	public static void aggiornaModello(String mod, ArrayList<String> attrMod)
			throws ClassNotFoundException, SQLException {
		AttrezzaturaModelloDAO.getInstance().aggiornaModello(mod, attrMod);
	}

	public static void salvaModello(String mod, ArrayList<String> attrMod)
			throws ClassNotFoundException, SQLException {
		AttrezzaturaModelloDAO.getInstance().salvaModello(mod, attrMod);
	}

	public static void rinominaModello(String prevName, String nextName) throws ClassNotFoundException, SQLException {
		AttrezzaturaModelloDAO.getInstance().rinominaModello(prevName, nextName);
		return;
	}

}
