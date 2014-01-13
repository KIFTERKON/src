package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;



public class Obj_fuente_sodas_rh {
	private int folio;
	private char ticket;
	private String nombre_completo;
    private double cantidad;
    private String fecha;
    private int status_ticket;
    private boolean status_autorizacion;
    
	public Obj_fuente_sodas_rh(){
    	this.ticket = ' ';
    	this.nombre_completo = "";
    	this.cantidad = 0.0;
    	this.fecha = "";
    	this.status_ticket =0;
    	this.status_autorizacion=false;
    }

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
	}

	public char getTicket() {
		return ticket;
	}

	public void setTicket(char folio) {
		this.ticket = folio;
	}

	public String getNombre_Completo() {
		return nombre_completo;
	}

	public void setNombre_Completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getStatus_ticket() {
		return status_ticket;
	}

	public void setStatus_ticket(int statusTicket) {
		status_ticket = statusTicket;
	}
	
	public boolean isStatus_autorizacion() {
		return status_autorizacion;
	}

	public void setStatus_autorizacion(boolean status_autorizacion) {
		this.status_autorizacion = status_autorizacion;
	}

	// Funcion Guardar Empleado
	public boolean guardar(){ return new GuardarSQL().Guardar_fuente_sodas_rh(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().fuente_sodas(this,folio); }
	
	public boolean actualizar_status_ticket(){ return new ActualizarSQL().fuente_sodas_Rh(); }
	
	public Obj_fuente_sodas_rh maximo() throws SQLException{ return new BuscarSQL().MaximoFuente(); }
	
	public boolean borrar(int Id){ return new ActualizarSQL().eliminarListaFuenteSodas(Id); }
	
	public Obj_fuente_sodas_rh buscar(int folio) throws SQLException{ return new BuscarSQL().fuente_sodas_rh(folio); }
	
   public Obj_fuente_sodas_rh busquedaautoizacionfs(){
		return new BuscarSQL().buscarautoizacionfs();
   }
   
}
