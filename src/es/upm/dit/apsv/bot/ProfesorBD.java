package es.upm.dit.apsv.bot;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import es.upm.dit.apsv.bot.Profesor;

public class ProfesorBD {
	
	private Connection con;

	public ProfesorBD () {
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
	 * Metodo para recuperar los profesores que imparten una asignatura (por acronimo de asignatura)
	 */
	public List<Profesor> recuperarPorAsignaturaAcronimo(String acronimo) throws SQLException{
		
		Statement st = con.createStatement();

		List<Profesor> profesores = new ArrayList<Profesor>();
		
		ResultSet rs = st.executeQuery("SELECT Profesores.Nombre, Profesores.Despacho, Profesores.Email, Profesores.Departamento"
				+ " FROM profesores"
				+ " INNER JOIN departamentos ON departamentos.id=profesores.departamento"
				+ " INNER JOIN asignaciones ON asignaciones.profesor=profesores.id"
				+ " INNER JOIN asignaturas ON asignaturas.id=asignaciones.asignatura"
				+ " WHERE asignaturas.acronimo='" + acronimo + "'");

		while(rs.next()){
			Profesor profesor = new Profesor(rs.getString("NOMBRE"), rs.getString("DESPACHO"), rs.getString("EMAIL"), rs.getInt("DEPARTAMENTO"));
			profesores.add(profesor);
		}
		
		return profesores;
		
	}
	
	
	/*
	 * Metodo para recuperar los profesores que imparten una asignatura (por acronimo de asignatura)
	 */
	public List<Profesor> recuperarPorAsignaturaNombre(String nombre) throws SQLException{
		
		Statement st = con.createStatement();

		List<Profesor> profesores = new ArrayList<Profesor>();
		
		ResultSet rs = st.executeQuery("SELECT Profesores.Nombre, Profesores.Despacho, Profesores.Email, Profesores.Departamento"
				+ " FROM profesores"
				+ " INNER JOIN departamentos ON departamentos.id=profesores.departamento"
				+ " INNER JOIN asignaciones ON asignaciones.profesor=profesores.id"
				+ " INNER JOIN asignaturas ON asignaturas.id=asignaciones.asignatura"
				+ " WHERE asignaturas.acronimo LIKE '" + nombre + "'");

		while(rs.next()){
			Profesor profesor = new Profesor(rs.getString("NOMBRE"), rs.getString("DESPACHO"), rs.getString("EMAIL"), rs.getInt("DEPARTAMENTO"));
			profesores.add(profesor);
		}
		
		return profesores;
		
	}
	
	
	/*
	 * Metodo para recuperar profesores por su nombre.
	 */
	public List<String> recuperarPorNombre (String nombre) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PROFESORES WHERE NOMBRE LIKE '%" + nombre + "%'");
		
		List<String> profesoresEncontrados = new ArrayList<String>();
		
		while(rs.next()){
			Profesor profesor = new Profesor(rs.getString("NOMBRE"), rs.getString("DESPACHO"), 
					rs.getString("EMAIL"), rs.getInt("DEPARTAMENTO"));
			profesoresEncontrados.add(profesor.toString());
		}
		return profesoresEncontrados;

	}
	

	/*
	 * Metodo para recuperar profesores por su mail (un único profesor).
	 */
	public Profesor recuperarPorEmail (String email) throws SQLException{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PROFESORES WHERE EMAIL LIKE %'" + email + "%'");
		rs.next();
		Profesor profesor = new Profesor(rs.getString("NOMBRE"),rs.getString("DESPACHO"),rs.getString("EMAIL"),rs.getInt("DEPARTAMENTO"));
		return profesor;
	}
	
	public void cierraConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
