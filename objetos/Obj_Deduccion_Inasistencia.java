package objetos;

import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Deduccion_Inasistencia {
	Object[][] tabla_model;
	
	public Obj_Deduccion_Inasistencia() {
		this.tabla_model = null;
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_deduccion_inasistencia();
	}
	
	public boolean guardar(Object[][] tabla){
		return new GuardarTablasModel().tabla_model_inasistencia(tabla);
	}

}
