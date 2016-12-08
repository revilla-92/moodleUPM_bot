package es.upm.dit.apsv.bot;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import es.upm.dit.apsv.bot.Asignatura;

public class AsignaturaBD {

	private Connection con;

	public AsignaturaBD () {
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
	 * Metodo para recuperar las asignaturas de un determinado plan de estudios, curso,
	 * semestre y si fuera necesario especialidad (NO OPTATIVAS).
	 */
	public List<Asignatura> recuperaAsignaturas (String planestudio, String curso, 
			String semestre, String especialidad) throws SQLException{
		
		Statement st = con.createStatement();
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		
		ResultSet rs = st.executeQuery("SELECT * FROM ASIGNATURAS "
				+ "WHERE Curso=" + curso + " "
				+ " AND Semestre=" + semestre + " "
				+ " AND PlanEstudio='" + planestudio + "'"
				+ " AND Especialidad='" + especialidad + "'"
				+ " AND Optativa='NO'");
		
		while(rs.next()){
			Asignatura asignatura = new Asignatura(rs.getString("CODIGO"), rs.getString("NOMBRE"), rs.getString("ACRONIMO"),
					rs.getString("ECTS"), rs.getString("PLANESTUDIO"), rs.getString("CURSO"), rs.getString("SEMESTRE"),
					rs.getString("GUIAALUMNO"), rs.getInt("DEPARTAMENTO"), rs.getString("OPTATIVA"), rs.getString("ESPECIALIDAD"));
			asignaturas.add(asignatura);
		}
		
		return asignaturas;	
	}
	
	
	/*
	 * Metodo para recuperar las optativas de un determinado plan de estudios, curso,
	 * semestre y si fuera necesario especialidad (SI OPTATIVAS).
	 */
	public List<Asignatura> recuperaOptativas (String planestudio, String curso) throws SQLException{
		
		Statement st = con.createStatement();
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		
		ResultSet rs = st.executeQuery("SELECT * FROM ASIGNATURAS "
				+ "WHERE Curso=" + curso + " "
				+ " AND PlanEstudio='" + planestudio + "'"
				+ " AND Optativa='SI'");
		
		while(rs.next()){
			Asignatura asignatura = new Asignatura(rs.getString("CODIGO"), rs.getString("NOMBRE"), rs.getString("ACRONIMO"),
					rs.getString("ECTS"), rs.getString("PLANESTUDIO"), rs.getString("CURSO"), rs.getString("SEMESTRE"),
					rs.getString("GUIAALUMNO"), rs.getInt("DEPARTAMENTO"), rs.getString("OPTATIVA"), rs.getString("ESPECIALIDAD"));
			asignaturas.add(asignatura);
		}
		
		return asignaturas;	
	}
	
	
	/*
	 * Metodo para recuperar asignaturas por su nombre (una única asignatura).
	 */
	public List<Asignatura> recuperarPorNombre (String nombre) throws SQLException{
		Statement st = con.createStatement();
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		ResultSet rs = st.executeQuery("SELECT * FROM ASIGNATURAS WHERE NOMBRE LIKE '%" + nombre + "%'");
		while(rs.next()){
			Asignatura asignatura = new Asignatura(rs.getString("CODIGO"),
					rs.getString("NOMBRE"),
					rs.getString("ACRONIMO"),
					rs.getString("ECTS"),
					rs.getString("PLANESTUDIO"),
					rs.getString("CURSO"),
					rs.getString("SEMESTRE"),
					rs.getString("GUIAALUMNO"),
					rs.getInt("DEPARTAMENTO"),
					rs.getString("OPTATIVA"),
					rs.getString("ESPECIALIDAD")
					);
			asignaturas.add(asignatura);
		}
		
		return asignaturas;
	}
	
	
	/*
	 * Metodo para recuperar asignaturas por su acronimo (una única asignatura).
	 */
	public Asignatura recuperarPorAcronimo (String acronimo) throws SQLException{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ASIGNATURAS WHERE ACRONIMO= '" + acronimo + "'");
		rs.next();
		Asignatura asignatura = new Asignatura(rs.getString("CODIGO"),
												rs.getString("NOMBRE"),
												rs.getString("ACRONIMO"),
												rs.getString("ECTS"),
												rs.getString("PLANESTUDIO"),
												rs.getString("CURSO"),
												rs.getString("SEMESTRE"),
												rs.getString("GUIAALUMNO"),
												rs.getInt("DEPARTAMENTO"),
												rs.getString("OPTATIVA"),
												rs.getString("ESPECIALIDAD")
									);
		return asignatura;
	}
	
	/*
	 * Metodo para recuperar asignaturas por su codigo (una única asignatura).
	 */
	public Asignatura recuperarPorCodigo (String codigo) throws SQLException{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ASIGNATURAS WHERE CODIGO= '" + codigo + "'");
		rs.next();
		Asignatura asignatura = new Asignatura(rs.getString("CODIGO"),
												rs.getString("NOMBRE"),
												rs.getString("ACRONIMO"),
												rs.getString("ECTS"),
												rs.getString("PLANESTUDIO"),
												rs.getString("CURSO"),
												rs.getString("SEMESTRE"),
												rs.getString("GUIAALUMNO"),
												rs.getInt("DEPARTAMENTO"),
												rs.getString("OPTATIVA"),
												rs.getString("ESPECIALIDAD")
									);
		return asignatura;
	}
	
	public void cierraConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
