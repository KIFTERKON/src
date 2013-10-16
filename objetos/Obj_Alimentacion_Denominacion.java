package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Alimentacion_Denominacion {
	String asignacion;
	String empleado;
//	int folio_denominacion;
//	float valor;
//	float cantidad;
	String fecha;
	String establecimiento;
	
	public Obj_Alimentacion_Denominacion(){
		this.asignacion=""; this.empleado=""; 
//		this.folio_denominacion=0;
//		this.valor=0; this.cantidad=0;
		this.fecha=""; this.establecimiento="";
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	
	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

//	public int getFolio_denominacion() {
//		return folio_denominacion;
//	}
//
//	public void setFolio_denominacion(int folioDenominacion) {
//		folio_denominacion = folioDenominacion;
//	}
//
//	public float getValor() {
//		return valor;
//	}
//
//	public void setValor(float valor) {
//		this.valor = valor;
//	}
//
//	public float getCantidad() {
//		return cantidad;
//	}
//
//	public void setCantidad(float cantidad) {
//		this.cantidad = cantidad;
//	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	
//	denominacion
	public boolean guardar(Object[][] tabla){ 
		return new GuardarSQL().Guardar_Alimentacion_denominacio(this, tabla);
	}
//	denominacion	
	public boolean actualizar(Object[][] tabla){ 
		return new ActualizarSQL().Actualizar_Alimentacion_denominacio(this, tabla);
	}
	
//	deposito
	public boolean guardar_deposito(Object[][] tabla){ 
		return new GuardarSQL().Guardar_Alimentacion_deposito(this, tabla);
	}
//	deposito	
	public boolean actualizar_deposito(Object[][] tabla){ 
		return new ActualizarSQL().Actualizar_Alimentacion_deposito(this, tabla);
	}
	
//	public boolean actualizar(String asignacion, int folioDenom){ return new ActualizarSQL().Denom(this,asignacion,folioDenom); }
	
//	public Obj_Alimentacion_Denominacion buscar(String asignacion){ 
//		try {
//			return new BuscarSQL().Denom(asignacion);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	return null; 
//	}
}
