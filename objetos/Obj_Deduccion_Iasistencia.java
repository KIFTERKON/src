package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Deduccion_Iasistencia {
	
	private int folio_empleado;
	private String nombre_completo;
	private String establecimiento;
	private String puntualidad;
	private String falta;
	private int dia_faltas;
	private String asistencia;
	private String gafete;
	private int dia_gafete;
	private float extra;
	private float cantidad_faltas;
	private int status;
	
	public Obj_Deduccion_Iasistencia(){
		this.folio_empleado=0; this.nombre_completo=""; this.establecimiento=""; this.puntualidad=""; this.falta="";
		this.dia_faltas=0; this.asistencia=""; this.gafete=""; this.dia_gafete=0; this.cantidad_faltas=0; this.status=0;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folioEmpleado) {
		folio_empleado = folioEmpleado;
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

	public String getGafete() {
		return gafete;
	}

	public void setGafete(String gafete) {
		this.gafete = gafete;
	}

	public int getDia_gafete() {
		return dia_gafete;
	}

	public void setDia_gafete(int diaGafete) {
		dia_gafete = diaGafete;
	}

	public float getCantidad_faltas() {
		return cantidad_faltas;
	}

	public void setCantidad_faltas(float cantidad_faltas) {
		this.cantidad_faltas = cantidad_faltas;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public float getExtra() {
		return extra;
	}

	public void setExtra(float extra) {
		this.extra = extra;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Deduccion_Asistencia(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Actualizar_Deduccion_Asistencia(this,folio); }
	
	public Obj_Deduccion_Iasistencia buscar(int folio) throws SQLException{ return new BuscarSQL().Deduccion(folio); }
	
	public Obj_Deduccion_Iasistencia buscarExis(int folio){ 
		try {
			return new BuscarSQL().DeduccionExiste(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
}
