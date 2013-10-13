package objetos;

import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Alimentacion_Por_Denominacion {

	Object[][] tabla_model;
	
	public Obj_Alimentacion_Por_Denominacion(){
		this.tabla_model = null;
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_alimentacion_denominaciones();
	}
	
	public Object[][] get_tabla_model_modificar(){
		return new BuscarTablasModel().tabla_model_alimentacion_denominaciones_modificar();
	}
	
	public boolean guardar(Object[][] tabla){
		return new GuardarTablasModel().tabla_model_alimentacion_totales(tabla);
	}
}
