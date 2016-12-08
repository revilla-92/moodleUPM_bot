package es.upm.dit.apsv.bot;

public class Calendario {

	private String fecha;
	private String asignatura;
	private String lugar;
	private String descripcion;
	private String autor;
	
	public Calendario(String fecha, String asignatura, String lugar, String descripcion, String autor) {
		this.fecha = fecha;
		this.asignatura = asignatura;
		this.lugar = lugar;
		this.descripcion = descripcion;
		this.autor = autor;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String toString(){
		return "El " + fecha + " en " + lugar + " tiene un evento sobre la asignatura: " + 
				asignatura + " y trata sobre: " + descripcion + " y ha sido creado por: " + autor;
	}
	
}