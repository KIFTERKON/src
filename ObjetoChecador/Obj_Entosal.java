package ObjetoChecador;

import java.sql.SQLException;
import java.util.Vector;

import SQL.BuscarSQL;
import SQL.BuscarTablasModel;

public class Obj_Entosal {
	private int clave;
	
	public Obj_Entosal(){
		this.clave=0;
	}
	
	public int getClave() {
		return clave;
	}


	public void setClave(int clave) {
		this.clave = clave;
	}

	
	@SuppressWarnings("rawtypes")
	public Vector buscar_hora_entosal(int folio){
		try {
			
			return new BuscarSQL().buscar_entosal(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	
	
	public Obj_Entosal buscar(int clave){ 
		try {
			return new BuscarSQL().Entosal(clave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean buscar_colicion(int folio){ 
		try {
			return new BuscarSQL().existeColisionTiempo(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean checar_dia_descanso(int folio){ 
		try {
			return new BuscarSQL().IntentaChecarDiaDescanso(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checadas_dia_dobla(int folio){ 
		try {
			return new BuscarSQL().obtener_checadas_dia_dobla(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
