package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Actividad {
	int folio;
	String actividad;
	String descripcion;
	String respuesta;
	String atributos;
	String nivel_critico;
	int domingo;
	int lunes;
	int martes;
	int miercoles;
	int jueves;
	int viernes;
	int sabado;
	String hora_inicio;
	String hora_final;
	String temporada;
	boolean carga;
	int repetir;
	boolean status;

	public Obj_Actividad(){
		this.folio=0; this.actividad=""; this.descripcion=""; this.respuesta=""; this.atributos=""; this.nivel_critico="";
		this.domingo=0; this.lunes=0; this.martes=0; this.miercoles=0; this.jueves=0; this.viernes=0; this.sabado=0; this.hora_inicio="";
		this.hora_final=""; this.temporada=""; this.carga=false; this.repetir=0; this.status = false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getAtributos() {
		return atributos;
	}

	public void setAtributos(String atributos) {
		this.atributos = atributos;
	}

	public String getNivel_critico() {
		return nivel_critico;
	}

	public void setNivel_critico(String nivel_critico) {
		this.nivel_critico = nivel_critico;
	}

	public int getDomingo() {
		return domingo;
	}

	public void setDomingo(int domingo) {
		this.domingo = domingo;
	}

	public int getLunes() {
		return lunes;
	}

	public void setLunes(int lunes) {
		this.lunes = lunes;
	}

	public int getMartes() {
		return martes;
	}

	public void setMartes(int martes) {
		this.martes = martes;
	}

	public int getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}

	public int getJueves() {
		return jueves;
	}

	public void setJueves(int jueves) {
		this.jueves = jueves;
	}

	public int getViernes() {
		return viernes;
	}

	public void setViernes(int viernes) {
		this.viernes = viernes;
	}

	public int getSabado() {
		return sabado;
	}

	public void setSabado(int sabado) {
		this.sabado = sabado;
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

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public boolean isCarga() {
		return carga;
	}

	public void setCarga(boolean carga) {
		this.carga = carga;
	}

	public int getRepetir() {
		return repetir;
	}

	public void setRepetir(int repetir) {
		this.repetir = repetir;
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
	
	public boolean Guardar(){
		return new GuardarSQL().Guardar_Actividad(this);
	}
	
	public boolean Actualizar(int folio){
		return new ActualizarSQL().Actualizar_Actividad(this, folio);
	}
	
	public Obj_Actividad Buscar(int folio){
		try {
			return new BuscarSQL().Buscar_Actividad(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}

}
