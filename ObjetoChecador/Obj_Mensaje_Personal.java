package ObjetoChecador;

import java.sql.SQLException;

import objetos.Obj_Empleados_Cuadrantes;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Mensaje_Personal {
	
	int folioMensaje;
	String fechaInicial;
	String fechaFin;
	String asunto;
	String mensaje;
	boolean status;
	
	public Obj_Mensaje_Personal(){
		folioMensaje=0;		fechaInicial="";	fechaFin="";	asunto="";		mensaje="";		status=false;
	}

	public int getFolioMensaje() {
		return folioMensaje;
	}

	public void setFolioMensaje(int folioMensaje) {
		this.folioMensaje = folioMensaje;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean guardar_mensaje(){ return new GuardarSQL().Guardar_Mensaje_Personal(this); }
	
	public boolean guardar_Empleado_Mensaje(String[] tabla){ return new GuardarSQL().Guardar_Empleado_Msj(this,tabla); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().mensajePersonal(this,folio); }
	
	public boolean actualizar2(String[] tabla){ return new ActualizarSQL().mensajePersonal2(this,tabla); }
	
	public int nuevoMensaje(){
		try {
			return new BuscarSQL().NuevoMensajePersonal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public Obj_Mensaje_Personal buscar(int folio){
		try {
			return new BuscarSQL().buscar_mensaje(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
