package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.GuardarSQL;

public class Obj_Grupo_De_Vacaciones {

	int folio;
	String descripcion;
	private boolean status;
	
	public Obj_Grupo_De_Vacaciones(){
		this.folio=0;	this.descripcion="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object[][] get_tabla_model_grupo_de_vacaciones(){
		return new BuscarTablasModel().tabla_grupo_vacaciones();
	}
	
	public Obj_Grupo_De_Vacaciones buscar_nuevo() throws SQLException{ return new BuscarSQL().Grupo_Nuevo(); }
	
	public Obj_Grupo_De_Vacaciones buscar(int folio) {
		try {
			return new BuscarSQL().Grupo(folio);
		} catch (SQLException e) {e.printStackTrace();}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Grupo_Vacaciones(this,folio); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Grupo_De_Vacaciones(this); }
}
