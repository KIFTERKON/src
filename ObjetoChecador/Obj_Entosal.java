package ObjetoChecador;

import java.sql.SQLException;
import java.util.Vector;

import SQL.BuscarSQL;

import SQL.BuscarTablasModel;
import SQL.GuardarSQL;

@SuppressWarnings("unused")
public class Obj_Entosal {
	private String clave;
	
	
	public String getClave() {
		return clave;
	}



	public void setClave(String clave) {
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

	
	
	public Obj_Entosal buscar(String clave){ 
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
	public boolean checa_salida_comer(int folio){ 
		try {
			return new BuscarSQL().valida_si_dobla_y_esta_saliendo_a_comer(folio);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	
}
