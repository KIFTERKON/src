package Objetos_IZAGAR;

import SQL.GuardarTablasModel;

public class Obj_IZAGAR_Netos_Nominas {

	public boolean guardar_netos_nominas_temp(Object[][] tabla){
		return new GuardarTablasModel().IZAGAR_insert_Netos_Nominas_Temp(tabla );
	}

	public boolean guardar_netos_nominas_temp_individual(int folio_empleado,int nomina,float neto){
		return new GuardarTablasModel().IZAGAR_insert_Netos_Nominas_Temp_individual(folio_empleado, nomina, neto );
	}
}
