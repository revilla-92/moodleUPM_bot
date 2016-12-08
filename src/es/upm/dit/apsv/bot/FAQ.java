package es.upm.dit.apsv.bot;

public class FAQ {

	private String ws;
	private String accionprimaria;
	private String accionsecundaria;
	private String respuesta;
	
	
	public FAQ(String ws, String accionprimaria, String accionsecundaria, String respuesta) {
		this.ws = ws;
		this.accionprimaria = accionprimaria;
		this.accionsecundaria = accionsecundaria;
		this.respuesta = respuesta;
	}


	public String getWs() {
		return ws;
	}


	public void setWs(String ws) {
		this.ws = ws;
	}


	public String getAccionprimaria() {
		return accionprimaria;
	}


	public void setAccionprimaria(String accionprimaria) {
		this.accionprimaria = accionprimaria;
	}


	public String getAccionsecundaria() {
		return accionsecundaria;
	}


	public void setAccionsecundaria(String accionsecundaria) {
		this.accionsecundaria = accionsecundaria;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	
	public String toString() {
		return "FAQ [ws=" + ws + ", accionprimaria=" + accionprimaria + ", accionsecundaria=" + accionsecundaria
				+ ", respuesta=" + respuesta + "]";
	}
	
}