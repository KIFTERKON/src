package objetos;

import SQL.BuscarTablasModel;

public class  Obj_Imprimir_Cuadrante {
	
	
	public Object[][] Obj_Obtener_Cuadrantes(){

		return new BuscarTablasModel().tabla_model_filtro_imprimir_cuadrantes();
	}
	
	
}
