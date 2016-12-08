package es.upm.dit.apsv.bot;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class FAQBD {

	private Connection con;
	
	public FAQBD() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Método para recoger los posibles valores de WS
	 */
	public List<String> recuperaWS() throws SQLException{
		
		Statement st = con.createStatement();
		List<String> ws = new ArrayList<String>();
		
		ResultSet rs = st.executeQuery("SELECT DISTINCT WS FROM FAQ");
		while(rs.next()){
			ws.add(rs.getString("WS"));
		}
		return ws;
	}
	
	/*
	 * Método para recoger los posibles valores de accion principal.
	 */
	public List<String> recuperaAccionPrincipal() throws SQLException{
		
		Statement st = con.createStatement();
		List<String> accionPrimaria = new ArrayList<String>();
		
		ResultSet rs = st.executeQuery("SELECT DISTINCT ACCIONPRIMARIA FROM FAQ");
		while(rs.next()){
			accionPrimaria.add(rs.getString("ACCIONPRIMARIA"));
		}
		return accionPrimaria;
	}
	
	
	/*
	 * Método para recoger los posibles valores de accion principal.
	 */
	public List<String> recuperaAccionSecundaria() throws SQLException{
		
		Statement st = con.createStatement();
		List<String> accionSecundaria = new ArrayList<String>();
		
		ResultSet rs = st.executeQuery("SELECT DISTINCT ACCIONSECUNDARIA FROM FAQ");
		while(rs.next()){
			accionSecundaria.add(rs.getString("ACCIONSECUNDARIA"));
		}
		return accionSecundaria;
	}
	
	
	/*
	 * Metodo para recuperar la respuesta a una FAQ.
	 */
	public String recuperarRespuesta (String ws, String accionPrincipal, 
			String accionSecundaria) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM FAQ "
				+ "WHERE WS LIKE '%" + ws + "%'"
				+ " AND ACCIONPRIMARIA LIKE '%" + accionPrincipal + "%'"
				+ " AND ACCIONSECUNDARIA LIKE '%" + accionSecundaria + "%'");
		rs.next();
		return rs.getString("RESPUESTA");
	}
	
	public void cierraConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
