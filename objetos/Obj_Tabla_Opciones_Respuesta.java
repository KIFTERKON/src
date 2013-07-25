package objetos;

import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Tabla_Opciones_Respuesta {
	
	Object[][] tabla_model;
	
	public Obj_Tabla_Opciones_Respuesta(){
		this.tabla_model = null;
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(String opcion){
		return new BuscarTablasModel().tabla_model_respuesta(opcion);
	}
	
	public boolean guardar(String nombre, Object[][] tabla){
		return new GuardarTablasModel().tabla_model_respuesta(nombre, tabla);
	}
	
}
