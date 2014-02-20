package ObjetoChecador;

import java.io.File;
import java.sql.SQLException;

import SQL.BuscarSQL;

public class Obj_Seleccion_Operaciones {
	
	int folio_solicitud;
	int folio_empleado;
	String nombre_empleado;
	
	String establecimiento;
	String puesto;
	String descanso;
	String dobla;
	String status;
	String telefono;
	
	String nombre_encargado;
	String sopeca;
	String cambio;
	String fecha_solicitada;
	String tipo_solicitud;
	int puntualidad;
	int cumplimiento;
	int diciplina;
	int respeto;
	String motivo;
	File foto;
	
	public Obj_Seleccion_Operaciones(){
		this.folio_solicitud=0;		this.folio_empleado=0;		this.nombre_empleado="";	
		this.establecimiento="";	this.puesto="";				this.descanso="";
		this.status="";				this.telefono="";
		this.dobla="";				this.nombre_encargado="";	this.sopeca="";			
		this.cambio="";				this.fecha_solicitada="";	this.tipo_solicitud="";		
		this.puntualidad=0;			this.cumplimiento=0;		this.diciplina=0;
		this.respeto=0;				this.motivo="";				this.foto=null;
	}

	public int getFolio_solicitud() {
		return folio_solicitud;
	}

	public void setFolio_solicitud(int folio_solicitud) {
		this.folio_solicitud = folio_solicitud;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getNombre_empleado() {
		return nombre_empleado;
	}

	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = nombre_empleado;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}

	public String getDobla() {
		return dobla;
	}

	public void setDobla(String dobla) {
		this.dobla = dobla;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre_encargado() {
		return nombre_encargado;
	}

	public void setNombre_encargado(String nombre_encargado) {
		this.nombre_encargado = nombre_encargado;
	}

	public String getSopeca() {
		return sopeca;
	}

	public void setSopeca(String sopeca) {
		this.sopeca = sopeca;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getFecha_solicitada() {
		return fecha_solicitada;
	}

	public void setFecha_solicitada(String fecha_solicitada) {
		this.fecha_solicitada = fecha_solicitada;
	}

	public String getTipo_solicitud() {
		return tipo_solicitud;
	}

	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}

	public int getPuntualidad() {
		return puntualidad;
	}

	public void setPuntualidad(int puntualidad) {
		this.puntualidad = puntualidad;
	}

	public int getCumplimiento() {
		return cumplimiento;
	}

	public void setCumplimiento(int cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public int getDiciplina() {
		return diciplina;
	}

	public void setDiciplina(int diciplina) {
		this.diciplina = diciplina;
	}

	public int getRespeto() {
		return respeto;
	}

	public void setRespeto(int respeto) {
		this.respeto = respeto;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}
	
	public Obj_Seleccion_Operaciones buscar(int folio){ 
		try {
			return new BuscarSQL().Solicitud_empleado(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
}
