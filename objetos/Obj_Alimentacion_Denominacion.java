package objetos;

import SQL.ActualizarSQL;
import SQL.GuardarSQL;

public class Obj_Alimentacion_Denominacion {
	String asignacion;
	String empleado;
	String fecha;
	String establecimiento;
	
	public Obj_Alimentacion_Denominacion(){
		this.asignacion=""; this.empleado=""; 
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
}
