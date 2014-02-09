package ObjetoChecador;

import SQL.GuardarSQL;

public class Obj_Solicitud_De_Empleados {

	int folio_empleado;
	String usuario;
	int folio_permiso;
	int folio_solicitud;
	int folio_cambio;
	String cambio_a;
	String fecha_solicitada;
	int temp_fijo;	
	float cantidad_solicitada;
	int puntualidad_y_asistencia;
	int cumplimiento_de_tareas;	
	int diciplina;
	int respeto_y_trato_general;	
	String motivo;
	
	public Obj_Solicitud_De_Empleados(){
		this.folio_empleado=0;			this.usuario="";				this.folio_permiso=0;		  this.folio_solicitud=0;	
		this.folio_cambio=0;			this.cambio_a="";				this.fecha_solicitada="";	  this.temp_fijo=0;
		this.cantidad_solicitada=0;		this.puntualidad_y_asistencia=0;this.cumplimiento_de_tareas=0;this.diciplina=0;
		this.respeto_y_trato_general=0;	this.motivo="";
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getFolio_permiso() {
		return folio_permiso;
	}

	public void setFolio_permiso(int folio_permiso) {
		this.folio_permiso = folio_permiso;
	}

	public int getFolio_solicitud() {
		return folio_solicitud;
	}

	public void setFolio_solicitud(int folio_solicitud) {
		this.folio_solicitud = folio_solicitud;
	}

	public int getFolio_cambio() {
		return folio_cambio;
	}

	public void setFolio_cambio(int folio_cambio) {
		this.folio_cambio = folio_cambio;
	}

	public String getCambio_a() {
		return cambio_a;
	}

	public void setCambio_a(String cambio_a) {
		this.cambio_a = cambio_a;
	}

	public String getFecha_solicitada() {
		return fecha_solicitada;
	}

	public void setFecha_solicitada(String fecha_solicitada) {
		this.fecha_solicitada = fecha_solicitada;
	}

	public int getTemp_fijo() {
		return temp_fijo;
	}

	public void setTemp_fijo(int temp_fijo) {
		this.temp_fijo = temp_fijo;
	}

	public float getCantidad_solicitada() {
		return cantidad_solicitada;
	}

	public void setCantidad_solicitada(float cantidad_solicitada) {
		this.cantidad_solicitada = cantidad_solicitada;
	}

	public int getPuntualidad_y_asistencia() {
		return puntualidad_y_asistencia;
	}

	public void setPuntualidad_y_asistencia(int puntualidad_y_asistencia) {
		this.puntualidad_y_asistencia = puntualidad_y_asistencia;
	}

	public int getCumplimiento_de_tareas() {
		return cumplimiento_de_tareas;
	}

	public void setCumplimiento_de_tareas(int cumplimiento_de_tareas) {
		this.cumplimiento_de_tareas = cumplimiento_de_tareas;
	}

	public int getDiciplina() {
		return diciplina;
	}

	public void setDiciplina(int diciplina) {
		this.diciplina = diciplina;
	}

	public int getRespeto_y_trato_general() {
		return respeto_y_trato_general;
	}

	public void setRespeto_y_trato_general(int respeto_y_trato_general) {
		this.respeto_y_trato_general = respeto_y_trato_general;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Solicitud(this); }
}
