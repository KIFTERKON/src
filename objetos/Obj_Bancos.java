package objetos;

import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Bancos {

	Object[][] tabla_model;
	
	public Obj_Bancos(){
		this.tabla_model = null;
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_bancos();
	}
	
	public boolean guardar(Object[][] tabla){
		return new GuardarTablasModel().tabla_model_bancos(tabla);
	}
}
