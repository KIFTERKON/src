package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Captura_Fuente_Sodas 
{
	String usuario;
	int empleado;
	String ticket;
	double importe;
	
	public Obj_Captura_Fuente_Sodas()
	{
		this.usuario="";	this.empleado=0;	this.ticket="";		this.importe=0;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getEmpleado() {
		return empleado;
	}

	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public boolean Guardar(){
		return new GuardarSQL().Guardar_Fuente_Sodas(this);
	}
	
	public Obj_Captura_Fuente_Sodas buscar_folio(int folio){ 
		try {
			return new BuscarSQL().fuente(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
}
