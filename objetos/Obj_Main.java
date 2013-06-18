package objetos;

import java.sql.SQLException;
import SQL.BuscarSQL;

public class Obj_Main {
	
	public Object[] Permisos(String nombre_completo) throws SQLException{
		return new BuscarSQL().Permisos(nombre_completo);
	}
}
