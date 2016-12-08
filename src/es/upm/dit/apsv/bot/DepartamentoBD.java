package es.upm.dit.apsv.bot;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DepartamentoBD {

	private Connection con;
	
	public DepartamentoBD () {
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
	 * Metodo para recuperar departamento por su nombre (un único departamento).
	 */
	public Departamento recuperarPorNombre (String nombre) throws SQLException{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTOS WHERE NOMBRE='" + nombre + "'");
		rs.next();
		Departamento departamento = new Departamento(rs.getString("NOMBRE"), rs.getString("ACRONIMO"));
		return departamento;
	}
	

	/*
	 * Metodo para recuperar departamento por su nombre (un único departamento).
	 */
	public Departamento recuperarPorAcronimo (String acronimo) throws SQLException{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTOS WHERE ACRONIMO='" + acronimo + "'");
		rs.next();
		Departamento departamento = new Departamento(rs.getString("NOMBRE"), rs.getString("ACRONIMO"));
		return departamento;
	}
	
	public void cierraConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}