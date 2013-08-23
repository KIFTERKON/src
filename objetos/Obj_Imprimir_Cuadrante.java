package objetos;

import SQL.BuscarTablasModel;

public class  Obj_Imprimir_Cuadrante {
	
	
	     public Object[][] Obj_Obtener_Empleados_Cuadrantes(){

	    	return new BuscarTablasModel().tabla_model_filtro_Obtener_Emp_imprimir_cuadrantes();
	                                                }
		

        public boolean  Obj_Imprimir_Cuadrante_Update_Folio (int Folio){
	                     
	     return new BuscarTablasModel().Guardar_Folio_de_Empleado_Imprimir_Cuadrante(Folio);
	   		    	                                                     }
}
	
	

	

