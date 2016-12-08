package es.upm.dit.apsv.bot;

public class Departamento {

	private String nombre;
	private String acronimo;
	
	
	public Departamento(String nombre, String acronimo) {
		this.nombre = nombre;
		this.acronimo = acronimo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAcronimo() {
		return acronimo;
	}


	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	
	public String toString(){
		return "departamento " + nombre + " (" + acronimo + ").";
	}
	
	

}
