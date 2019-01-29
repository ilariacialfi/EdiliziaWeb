package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import control.ControllerDB;
import bean.Modello;

public class ModelloDAO {

	private static final String ESTRAI_MODELLI = "SELECT DISTINCT modello FROM attrezzatura_modello ORDER BY modello";
	private static final String CERCA_MODELLO = "SELECT modello FROM attrezzatura_modello WHERE modello = ?";

	private static ModelloDAO instance = null;
	
	private ResultSet rs = null;
	private Statement stmn = null;
	private PreparedStatement pstmn = null;

	private ModelloDAO() {
	}

	public synchronized static final ModelloDAO getInstance() {
		if (ModelloDAO.instance == null) {
			ModelloDAO.instance = new ModelloDAO();
		}
		return instance;
	}

	public ArrayList<String> getModello() throws ClassNotFoundException, SQLException {
		ArrayList<String> listMod = null;

		try {
			Connection conn = ControllerDB.getInstance().connect();
			stmn = conn.createStatement();
			rs = stmn.executeQuery(ESTRAI_MODELLI);

			listMod = new ArrayList<>();
			while (rs.next()) {
				listMod.add(new Modello(rs.getString("modello")).getNome());
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!rs.isClosed()) {
				rs.close();
			}
			if (!stmn.isClosed()) {
				stmn.close();
			}
		}
		return listMod;
	}

	public boolean cercaModello(String mod) throws SQLException, ClassNotFoundException {

		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(CERCA_MODELLO);
			pstmn.setString(1, mod);
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

}
