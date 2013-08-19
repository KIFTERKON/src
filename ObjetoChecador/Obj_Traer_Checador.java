package ObjetoChecador;

import SQL.BuscarTablasModel;

public class Obj_Traer_Checador {
	Object[][] tabla_model;
	String fecha;

	public Obj_Traer_Checador(){
		this.tabla_model = null;
		this.fecha = "";
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_checador();
	}
	
}
