package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Mensajes
{
	int folio;
	String mensaje;
	public Obj_Mensajes()
	{
		this.folio=0;
		this.mensaje="";
	}
	
	
	
	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Obj_Mensajes buscar(int folio){ 
		try {
			return new BuscarSQL().mensaje(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public Obj_Mensajes buscar_nuevo() throws SQLException{ return new BuscarSQL().Mensaje_Nuevo(); }
	public boolean actualizar(int folio){ return new ActualizarSQL().ActualizarMensajes(this,folio); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Mensajes(this); }
}
