package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;

public class Obj_Reporte_Impresion_Gafetes {
	
	public void buscar_masivo(String lista){
		try {
			new BuscarSQL().Gafetes_masivos(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
