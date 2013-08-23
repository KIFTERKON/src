package objetos;

import SQL.BuscarTablasModel;


public  class Obj_Gafete{
		
	public Object[][] Obtener_Gafete(){
//		retorna la Variable instancia llena con la matriz
		return new BuscarTablasModel().tabla_model_filtro_gafetes();
	}
	
//	public boolean generar_reporte(int[] lista){
////		return new GuardarSQL().generar_gafete(lista);
//	}
			 
}


