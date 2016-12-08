package es.upm.dit.apsv.bot;

public class Profesor {

	private String nombre;
	private String despacho;
	private String email;
	private int departamento;
	
	
	public Profesor(String nombre, String despacho, String email, int departamento) {
		this.nombre = nombre;
		this.despacho = despacho;
		this.email = email;
		this.departamento = departamento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDespacho() {
		return despacho;
	}


	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getDepartamento() {
		return departamento;
	}


	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	
	public String toString(){
		String[] departamentos = {"DIT","SSR","DIE", "TFB", "MAT", "IES", "IOR", "LIA"};
		return "El profesor " + nombre + " con email " + email + " tiene su despacho en " + despacho + " y pertenece al " + departamentos[departamento-1];
	}
	
	
	

}
