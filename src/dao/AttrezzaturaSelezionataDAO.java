package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import bean.Attrezzatura;
import control.ControllerDB;

public class AttrezzaturaSelezionataDAO {
	public static final String AGGIUNGI_ATTR = "INSERT INTO attr_sel (attr, min, max) VALUES (?, ?, ?)";
	public static final String RETURN_ATTR_SEL = "SELECT * FROM attr_sel";
	public static final String ELIMINA_ATTR = "DELETE FROM attr_sel WHERE attr = ?";
	public static final String RESET_ATTR_SEL = "DELETE FROM attr_sel";
	public static final String CERCA_ATTR = "SELECT attr FROM attr_sel WHERE attr = ?";
	public static final String UPDATE_ATTR = "UPDATE attr_sel SET min = ?, max = ? WHERE attr = ?";

	private static AttrezzaturaSelezionataDAO instance = null;

	private PreparedStatement pstmn = null;
	private Statement stmn = null;
	private ResultSet rs = null;

	private AttrezzaturaSelezionataDAO() {
	}

	public synchronized static final AttrezzaturaSelezionataDAO getInstance() {
		if (AttrezzaturaSelezionataDAO.instance == null) {
			AttrezzaturaSelezionataDAO.instance = new AttrezzaturaSelezionataDAO();
		}
		return instance;
	}

	// reset della tabella
	public synchronized void resetAttrSel() throws ClassNotFoundException, SQLException {

		try {
			Connection conn = ControllerDB.getInstance().connect();
			stmn = conn.createStatement();

			stmn.executeUpdate(RESET_ATTR_SEL);
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!stmn.isClosed()) {
				stmn.close();
			}
		}
	}

	// controllo che l'attrezzatura non sia già inserita
	public synchronized boolean trovaAttrSel(String attrSel) throws ClassNotFoundException, SQLException {
		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(CERCA_ATTR);
			pstmn.setString(1, attrSel);

			rs = pstmn.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!rs.isClosed()) {
				rs.close();
			}
			if (!pstmn.isClosed()) {
				pstmn.close();
			}
		}
		return false;
	}

	// Aggiornamento riga
	public synchronized void aggiornaAttrSel(String nome, int min, int max)
			throws ClassNotFoundException, SQLException {
		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(UPDATE_ATTR);

			pstmn.setInt(1, min);
			pstmn.setInt(2, max);
			pstmn.setString(3, nome);

			pstmn.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!pstmn.isClosed()) {
				pstmn.close();
			}
		}
		return;
	}

	// aggiunge la riga dell'attrezzatura selezionata
	public synchronized void aggiungiAttrSel(String nome, int min, int max)
			throws ClassNotFoundException, SQLException {
		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(AGGIUNGI_ATTR);
			pstmn.setString(1, nome);
			pstmn.setInt(2, min);
			pstmn.setInt(3, max);

			pstmn.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!pstmn.isClosed()) {
				pstmn.close();
			}
		}
	}

	// estrarre tutte le attrezzature selezionate
	public synchronized ArrayList<Attrezzatura> estraiAttrSel() throws SQLException, ClassNotFoundException {
		ArrayList<Attrezzatura> a = new ArrayList<Attrezzatura>();

		try {
			Connection conn = ControllerDB.getInstance().connect();
			stmn = conn.createStatement();
			rs = stmn.executeQuery(RETURN_ATTR_SEL);

			while (rs.next()) {
				a.add(new Attrezzatura(rs.getString("attr"), rs.getInt("min"), rs.getInt("max")));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!stmn.isClosed()) {
				stmn.close();
			}
		}
		return a;
	}

	// eliminare una riga
	public synchronized void eliminaAttrSel(String attrSel) throws ClassNotFoundException, SQLException {

		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(ELIMINA_ATTR);
			pstmn.setString(1, attrSel);

			pstmn.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!pstmn.isClosed()) {
				pstmn.close();
			}
		}
	}

}
