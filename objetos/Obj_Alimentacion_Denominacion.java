package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Alimentacion_Denominacion {
	String asignacion;
	int folio_empleado;
	int folio_denominacion;
	String denominacion;
	float valor;
	float cantidad;
	String fecha;
	
	public Obj_Alimentacion_Denominacion(){
		this.asignacion=""; this.folio_empleado=0; this.folio_denominacion=0; this.denominacion="";
		this.valor=0; this.cantidad=0; this.fecha="";
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folioEmpleado) {
		folio_empleado = folioEmpleado;
	}

	public int getFolio_denominacion() {
		return folio_denominacion;
	}

	public void setFolio_denominacion(int folioDenominacion) {
		folio_denominacion = folioDenominacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public boolean guardar(){ return new GuardarSQL().Guardar_Alimentacion_denominacio(this); }
	
	public boolean actualizar(String asignacion, int folioDenom){ return new ActualizarSQL().Denom(this,asignacion,folioDenom); }
	
	public Obj_Alimentacion_Denominacion buscar(String asignacion){ 
		try {
			return new BuscarSQL().Denom(asignacion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
}
