package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_fuente_sodas_rh {
	private int folio;
	private char ticket;
	private String nombre_completo;
    private double cantidad;
    private String fecha;
    
    public Obj_fuente_sodas_rh(){
    	this.ticket = ' ';
    	this.nombre_completo = "";
    	this.cantidad = 0.0;
    	this.fecha = "";

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
	// Funcion Guardar Empleado
	public boolean guardar(){ return new GuardarSQL().Guardar_fuente_sodas_rh(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().fuente_sodas(this,folio); }
	
	public boolean actualizar_status_ticket(){ return new ActualizarSQL().fuente_sodas_Rh(); }
	
	public Obj_fuente_sodas_rh maximo(){ return new BuscarSQL().MaximoFuente(); }
	
	public boolean borrar(int Id){ return new ActualizarSQL().eliminarListaFuenteSodas(Id); }
	
	

}
