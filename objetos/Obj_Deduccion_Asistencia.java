package objetos;

import SQL.ActualizarSQL;
import SQL.GuardarSQL;

public class Obj_Deduccion_Asistencia {
	
	private int folio_empleado;
	private String nombre_completo;
	private String establecimiento;
	private String puntualidad;
	private String falta;
	private int dia_faltas;
	private String asistencia;
	private int status;
	
	public Obj_Deduccion_Asistencia(){
		this.folio_empleado=0; this.nombre_completo=""; this.establecimiento=""; this.puntualidad=""; this.falta="";
		this.dia_faltas=0; this.asistencia=""; this.status=0;
	}

	public int getFolioEmpleado() {
		return folio_empleado;
	}

	public void setFolioEmpleado(int folio) {
		this.folio_empleado = folio;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getPuntualidad() {
		return puntualidad;
	}

	public void setPuntualidad(String puntualidad) {
		this.puntualidad = puntualidad;
	}

	public String getFalta() {
		return falta;
	}

	public void setFalta(String falta) {
		this.falta = falta;
	}

	public int getDia_faltas() {
		return dia_faltas;
	}

	public void setDia_faltas(int diaFaltas) {
		dia_faltas = diaFaltas;
	}

	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Deduccion_Asistencia(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Actualizar_Deduccion_Asistencia(this,folio); }
}
