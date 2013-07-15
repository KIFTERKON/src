package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_Persecciones_Extra {
	
	private int folio_empleado;
	private String nombre_completo;
	private String establecimiento;
	private float bono;
	private String dia_extra;
	private int dias;
	private float cantidad_dias;
	
	public Obj_Persecciones_Extra(){
		this.folio_empleado=0; this.nombre_completo=""; this.establecimiento=""; this.bono=0; this.dia_extra="";
		this.dias=0; this.cantidad_dias=0;
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
	
	public float getBono() {
		return bono;
	}
	
	public void setBono(float bono) {
		this.bono = bono;
	}
	
	public String getDia_extra() {
		return dia_extra;
	}
	
	public void setDia_extra(String diaExtra) {
		dia_extra = diaExtra;
	}
	
	public int getDias() {
		return dias;
	}
	
	public void setDias(int dias) {
		this.dias = dias;
	}

	public float getCantidad_dias() {
		return cantidad_dias;
	}

	public void setCantidad_dias(float cantidad_dias) {
		this.cantidad_dias = cantidad_dias;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Actualizar(this,folio); }
	
	public Obj_Persecciones_Extra buscar(int folio){ 
		try {
			return new BuscarSQL().PerseccionExiste(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
}
