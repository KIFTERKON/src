package objetos;

import SQL.BuscarTablasModel;
import SQL.GuardarTablasModel;

public class Obj_Revision_Lista_Raya {
	Object[][] tabla_model;
	String fecha;

	public Obj_Revision_Lista_Raya(){
		this.tabla_model = null;
		this.fecha = "";
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(){
		new GuardarTablasModel().tabla_model_lista_raya_update();
		return new BuscarTablasModel().tabla_model_lista_raya();
	}
	
	public boolean guardar(Object[][] tabla, String fecha){
		return new GuardarTablasModel().tabla_model_lista_raya(tabla,fecha);
	}
	
	public String get_fecha(){
		return new BuscarTablasModel().tabla_model_lista_raya_fecha();
	}
	
	public boolean init_revision_totales(){
		return new BuscarTablasModel().tabla_model_lista_raya_revision_totales();
	}
	
	public boolean init_autorizacion(){
		return new BuscarTablasModel().tabla_model_lista_raya_autorizacion();
	}
	
	public boolean generar(Object[][] tabla, String fecha){
		return new GuardarTablasModel().tabla_model_lista_raya_generar(tabla,fecha);
	}
	
	public boolean init_totales_nomina(){
		return new BuscarTablasModel().tabla_model_lista_raya_init_totales_nomina();
	}
}
