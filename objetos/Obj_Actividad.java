package objetos;

import SQL.BuscarSQL;

public class Obj_Actividad {
	int folio;
	int numero_actividad;
	int subnumero_actividad;
	String actividad;
	int respuesta;
	int atributos;
	int nivel_critico;
	int dia;
	String hora_inicio;
	String hora_final;
	int temporada;
	boolean asignacion;
	int repetir;
	String descripcion;
	boolean status;
	
	public Obj_Actividad(){
		this.folio=0; this.numero_actividad=0; this.subnumero_actividad=0; this.actividad="";
		this.respuesta=0; this.atributos=0; this.nivel_critico=0; this.dia=0; this.hora_inicio="";
		this.hora_final=""; this.temporada=0; this.asignacion=false; this.repetir=0; this.descripcion="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNumero_actividad() {
		return numero_actividad;
	}

	public void setNumero_actividad(int numero_actividad) {
		this.numero_actividad = numero_actividad;
	}

	public int getSubnumero_actividad() {
		return subnumero_actividad;
	}

	public void setSubnumero_actividad(int subnumero_actividad) {
		this.subnumero_actividad = subnumero_actividad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	public int getAtributos() {
		return atributos;
	}

	public void setAtributos(int atributos) {
		this.atributos = atributos;
	}

	public int getNivel_critico() {
		return nivel_critico;
	}

	public void setNivel_critico(int nivel_critico) {
		this.nivel_critico = nivel_critico;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_final() {
		return hora_final;
	}

	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public boolean isAsignacion() {
		return asignacion;
	}

	public void setAsignacion(boolean asignacion) {
		this.asignacion = asignacion;
	}

	public int getRepetir() {
		return repetir;
	}

	public void setRepetir(int repetir) {
		this.repetir = repetir;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int Nuevo(){
		return new BuscarSQL().Nueva_Actividad();
	}
	
	public boolean Existe(int folio){ 
		return new BuscarSQL().ActividadExiste(folio);
	}

}
