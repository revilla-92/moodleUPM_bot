package es.upm.dit.apsv.bot;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class CalendarioBD {

	private Connection con;

	public CalendarioBD () {
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
	 * Crea un evento en la BBDD de los alumnos.
	 */
	public boolean createEventoAlumnos (String fecha, String asignatura, String lugar,
			String descripcion, String autor) throws SQLException{
		Statement st = con.createStatement();
		st.execute("INSERT INTO CALENDARIOALUMNOS(FECHA, ASIGNATURA, LUGAR, DESCRIPCION, AUTOR) "
				+ "VALUES('"+fecha+"', '"+asignatura+"', '"+lugar+ "', '"+descripcion+"', '" + autor + "');" );
		
		int i = st.getUpdateCount();
		
		return i>0;
	
	}
	
	
	/*
	 * Devuelve todos los eventos de la misma asignatura
	 */
	public List<Calendario> recuperarEventosPorAsignaturaOficial (String asignatura) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CALENDARIOOFICIAL "
				+ "WHERE ASIGNATURA LIKE '%" + asignatura + "%'");		
		
		List<Calendario> eventosEncontradosAsignatura = new ArrayList<Calendario>();

		while(rs.next()){
			
			Calendario evento = new Calendario(rs.getString("FECHA"), rs.getString("ASIGNATURA"),
					rs.getString("LUGAR"), rs.getString("DESCRIPCION"), rs.getString("AUTOR"));
			
			eventosEncontradosAsignatura.add(evento);
		}
		
		return eventosEncontradosAsignatura;
	}
	
	
	/*
	 * Devuelve todos los eventos con la misma fecha.
	 */
	public List<Calendario> recuperarEventosPorFechaOficial (String fecha) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CALENDARIOOFICIAL "
				+ "WHERE FECHA LIKE '%" + fecha + "%'");		
		
		List<Calendario> eventosEncontradosAsignatura = new ArrayList<Calendario>();

		while(rs.next()){
			
			Calendario evento = new Calendario(rs.getString("FECHA"), rs.getString("ASIGNATURA"),
					rs.getString("LUGAR"), rs.getString("DESCRIPCION"), rs.getString("AUTOR"));
			
			eventosEncontradosAsignatura.add(evento);
		}
		
		return eventosEncontradosAsignatura;
	}
	
	/*
	 * Devuelve todos los eventos de la misma asignatura
	 */
	public List<Calendario> recuperarEventosPorAsignaturaAlumnos (String asignatura) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CALENDARIOALUMNOS "
				+ "WHERE ASIGNATURA LIKE '%" + asignatura + "%'");		
		
		List<Calendario> eventosEncontradosAsignatura = new ArrayList<Calendario>();

		while(rs.next()){
			
			Calendario evento = new Calendario(rs.getString("FECHA"), rs.getString("ASIGNATURA"),
					rs.getString("LUGAR"), rs.getString("DESCRIPCION"), rs.getString("AUTOR"));
			
			eventosEncontradosAsignatura.add(evento);
		}
		
		return eventosEncontradosAsignatura;
	}
	
	
	/*
	 * Devuelve todos los eventos con la misma fecha.
	 */
	public List<Calendario> recuperarEventosPorFechaAlumnos (String fecha) throws SQLException{
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CALENDARIOALUMNOS "
				+ "WHERE FECHA LIKE '%" + fecha + "%'");		
		
		List<Calendario> eventosEncontradosAsignatura = new ArrayList<Calendario>();

		while(rs.next()){
			
			Calendario evento = new Calendario(rs.getString("FECHA"), rs.getString("ASIGNATURA"),
					rs.getString("LUGAR"), rs.getString("DESCRIPCION"), rs.getString("AUTOR"));
			
			eventosEncontradosAsignatura.add(evento);
		}
		
		return eventosEncontradosAsignatura;
	}
	
	public void cierraConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
