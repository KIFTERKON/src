package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.GuardarSQL;



public class Obj_Actividad {
	int folio;
	String actividad;
	String descripcion;
	String respuesta;
	String atributos;
	String nivel_critico;
	String temporada;
	boolean carga;
	int repetir;
	boolean status;

	public Obj_Actividad(){
		this.folio=0; this.actividad=""; this.descripcion=""; this.respuesta=""; this.atributos=""; this.nivel_critico="";
		this.temporada=""; this.carga=false; this.repetir=0; this.status = false;
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
	
	public Object[][] filtro(){
		return new BuscarTablasModel().filtro_actividad();
	}

}
