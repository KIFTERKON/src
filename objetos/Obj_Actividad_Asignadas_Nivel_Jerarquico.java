package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.GuardarSQL;

public class Obj_Actividad_Asignadas_Nivel_Jerarquico {
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

	public Obj_Actividad_Asignadas_Nivel_Jerarquico(){
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
		return new BuscarSQL().Nueva_Actividad_Jerarquico();
	}
	
	public boolean Existe(int folio){ 
		return new BuscarSQL().ActividadExisteJerarquico(folio);
	}
	
	public boolean Existe_Nombre(String actividad){ 
		return new BuscarSQL().ActividadExisteJerarquico(actividad);
	}
	
	public String NombreOld(int folio){ 
		return new BuscarSQL().ActividadExisteJerarquicoNameOld(folio);
	}
	
	public boolean Guardar(String nombre){
		return new GuardarSQL().Guardar_Actividad_Nivel_Jerarquico(this, nombre);
	}
	
	public boolean Actualizar(int folio, String nombre){
		return new ActualizarSQL().Actualizar_Actividad_Nivel_Jerarquico(this, folio, nombre);
	}
	
	public Obj_Actividad_Asignadas_Nivel_Jerarquico Buscar(int folio){
		try {
			return new BuscarSQL().Buscar_Actividad_Nivel_Jerarquico(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public Object[][] filtro(){
		return new BuscarTablasModel().filtro_actividad_nivel_jerarquico();
	}
	
	public String getPuestosDependientes(){
		return new BuscarSQL().puestosDependientes();
	}
	
}
