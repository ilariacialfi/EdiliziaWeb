package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bean.Attrezzatura;
import control.ControllerDB;

public class AttrezzaturaSelezionataDAO {
	public static final String AGGIUNGI_ATTR = "INSERT INTO attr_sel (attr, min, max) VALUES (?, ?, ?)";
	public static final String ELIMINA_ATTR = "DELETE FROM attr_sel WHERE attr = ?";
	public static final String RESET_ATTR_SEL = "DELETE FROM attr_sel";
	public static final String CERCA_ATTR = "SELECT attr FROM attr_sel WHERE attr = ?";
	
	private static AttrezzaturaSelezionataDAO instance = null;
	
	private PreparedStatement pstmn = null;
	private Statement stmn = null;
	private ResultSet rs = null;

	protected AttrezzaturaSelezionataDAO() {
	}

	public synchronized static final AttrezzaturaSelezionataDAO getInstance() {
		if (AttrezzaturaSelezionataDAO.instance == null) {
			AttrezzaturaSelezionataDAO.instance = new AttrezzaturaSelezionataDAO();
		}
		return instance;
	}
	
	//aggiungere una riga alla tabella delle attrezzature selezionate
	public synchronized void addAttrSel(Attrezzatura attrSel) throws ClassNotFoundException, SQLException {
		
		try {
			Connection conn = ControllerDB.getInstance().connect();
			pstmn = conn.prepareStatement(AGGIUNGI_ATTR);
			pstmn.setString(1, attrSel.getNome());
			pstmn.setInt(2, attrSel.getMin());
			pstmn.setInt(3, attrSel.getMax());
			
			pstmn.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (!pstmn.isClosed()) {
				pstmn.close();
			}
		}
	}
	
	//eliminare una riga
public synchronized void delAttrSel(String attrSel) throws ClassNotFoundException, SQLException {
		
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

	//reset della tabella
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


	//controllo che l'attrezzatura non sia già inserita
public boolean searchAttrSel(String attrSel) throws ClassNotFoundException, SQLException {
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
	
}
