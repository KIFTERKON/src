package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;


public class Obj_Main {
	
	public Object[] Permisos(int folio) throws SQLException{
		return new BuscarSQL().Permisos(folio);
	}
}
