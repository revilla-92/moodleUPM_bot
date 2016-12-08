package es.upm.dit.apsv.bot;

public class Asignatura {

	private String codigo;
	private String nombre;
	private String acronimo;
	private String ects;
	private String planestudio;
	private String curso;
	private String semestre;
	private String guiaalumno;
	private int departamento;
	private String optativa;
	private String especialidad;
	
	public Asignatura(String codigo, String nombre, String acronimo, String ects, String planestudio, String curso,
			String semestre, String guiaalumno, int departamento, String optativa, String especialidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.acronimo = acronimo;
		this.ects = ects;
		this.planestudio = planestudio;
		this.curso = curso;
		this.semestre = semestre;
		this.guiaalumno = guiaalumno;
		this.departamento = departamento;
		this.optativa = optativa;
		this.especialidad = especialidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getEcts() {
		return ects;
	}

	public void setEcts(String ects) {
		this.ects = ects;
	}

	public String getPlanestudio() {
		return planestudio;
	}

	public void setPlanestudio(String planestudio) {
		this.planestudio = planestudio;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getGuiaalumno() {
		return guiaalumno;
	}

	public void setGuiaalumno(String guiaalumno) {
		this.guiaalumno = guiaalumno;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public String getOptativa() {
		return optativa;
	}

	public void setOptativa(String optativa) {
		this.optativa = optativa;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public String toString(){
		
		String[] departamentos = {"DIT","SSR","DIE", "TFB", "MAT", "IES", "IOR", "LIA"};
		
		String a = "La asignatura: " + nombre + " (" + acronimo + ") tiene como código " + codigo + ".";
		String b = " Se imparte en el plan de estudio " + planestudio + " en el " + curso + "º curso, " + semestre + "º semestre";
		String c = ".";
		String d = "";
		String e = " Pertenece al departamento " + departamentos[departamento-1];
		String f = ". Y se puede consultar la guía del alumno en: " + guiaalumno;
		
		// Especialidad
		if((planestudio == "GITST" && curso == "4") || (planestudio == "MUIT" && curso == "2")){
			c = " y se imparte en la especialidad de " + especialidad;
		}
		
		// Optativa
		if(optativa == "SI"){
			d = " Y se trata de una optativa.";
		}else{
			d = " Y se trata de una asignatura obligatoria.";
		}
		
		return a + b + c + d + e + f;
	}
	
	public String toStringCorto(){
		
		String[] departamentos = {"DIT","SSR","DIE", "TFB", "MAT", "IES", "IOR", "LIA"};
		
		String a = "La asignatura: " + nombre + " (" + acronimo + "),";
		String b = " se imparte en el " + planestudio + " en el " + curso + "º curso, " + semestre + "º semestre";
		String c = ".";
		String d = "";
		String e = " Pertenece al departamento " + departamentos[departamento-1];
		String f = ". Y la guía del alumno es: " + guiaalumno;
		
		// Especialidad
		if((planestudio == "GITST" && curso == "4") || (planestudio == "MUIT" && curso == "2")){
			c = " y se imparte en la especialidad de " + especialidad;
		}
		
		// Optativa
		if(optativa == "SI"){
			d = " (Optativa).";
		}else{
			d = " (Obligatoria).";
		}
		
		return a + b + c + d + e + f;
	}

}
