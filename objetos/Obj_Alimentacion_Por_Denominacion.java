package objetos;

import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Alimentacion_Por_Denominacion {

	Object[][] tabla_model;
	String folio_corte;
	String asignacion;
	String empleado;
	String fecha;
	
	
	public Obj_Alimentacion_Por_Denominacion(){
		this.tabla_model = null;
		this.folio_corte="";
		this.asignacion="";
		this.empleado="";
		this.fecha="";
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public String getFolio_corte() {
		return folio_corte;
	}

	public void setFolio_corte(String folio_corte) {
		this.folio_corte = folio_corte;
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

	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_alimentacion_denominaciones();
	}
	
	public Object[][] get_tabla_model_modificar(String folio_corte){
		return new BuscarTablasModel().tabla_model_alimentacion_denominaciones_modificar(folio_corte);
	}
	
	public boolean guardar(Object[][] tabla){
		return new GuardarTablasModel().tabla_model_alimentacion_totales(tabla);
	}

	public Obj_Alimentacion_Por_Denominacion buscar_folio_corte_modificar(String folio_corte) {
		return new BuscarSQL().Datos_Denominacion(folio_corte);
	}
}
