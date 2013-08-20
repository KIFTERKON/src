package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Asignacion_Mensajes 
{
	int folio;
	int no_mensajes;
	String mensaje;
	
	String puesto;
	String equipo;
	String jefatura;
	String empleado;
	
	boolean rbpuesto;
	boolean rbequipo;
	boolean rbjefatura;
	boolean rbempleado;
	
	public Obj_Asignacion_Mensajes()
	{
		this.folio=0;
		this.no_mensajes=0;
		this.mensaje="";
		this.puesto="";
		this.equipo="";
		this.jefatura="";
		this.empleado="";
		
		this.rbpuesto=true;
		this.rbequipo=true;
		this.rbjefatura=true;
		this.rbempleado=true;
	}
	
	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNo_mensajes() {
		return no_mensajes;
	}

	public void setNo_mensajes(int no_mensajes) {
		this.no_mensajes = no_mensajes;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getJefatura() {
		return jefatura;
	}

	public void setJefatura(String jefatura) {
		this.jefatura = jefatura;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	
	public boolean getRbpuesto() {
		return rbpuesto;
	}

	public void setRbpuesto(boolean rbpuesto) {
		this.rbpuesto = rbpuesto;
	}

	public boolean getRbequipo() {
		return rbequipo;
	}

	public void setRbequipo(boolean rbequipo) {
		this.rbequipo = rbequipo;
	}

	public boolean getRbjefatura() {
		return rbjefatura;
	}

	public void setRbjefatura(boolean rbjefatura) {
		this.rbjefatura = rbjefatura;
	}

	public boolean getRbempleado() {
		return rbempleado;
	}

	public void setRbempleado(boolean rbempleado) {
		this.rbempleado = rbempleado;
	}

	//Buscar nuevo
	public Obj_Asignacion_Mensajes buscar_nuevo() throws SQLException{ return new BuscarSQL().Mensaje_New(); }
	//Guardar
	public boolean guardar(){ return new GuardarSQL().Guardar_Asignacion_mensajes(this); }
	
	public Obj_Asignacion_Mensajes buscar_folio(int folio){ 
		try {
			return new BuscarSQL().Asignacion(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	//Actualizar folio
	public boolean actualizar(int folio){ return new ActualizarSQL().ActualizarAsignacion(this,folio); }
}
