package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;

public class Obj_Prestamo {
	private int folio;
	private int folio_empleado;
	private String nombre_completo;
    private double cantidad;
    private double descuento;
    private double saldo;
    private String fecha;
    private int status;
    private int status_descuento;
    
    public Obj_Prestamo(){
    	this.folio_empleado=0;
    	this.nombre_completo = "";
    	this.cantidad =0.0;
    	this.descuento=0.0;
    	this.fecha = "";
    	this.status=0;
    	this.saldo=0.0;
    	this.status_descuento=0;

    }

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}
	

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folioEmpleado) {
		folio_empleado = folioEmpleado;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
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

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus_descuento() {
		return status_descuento;
	}

	public void setStatus_descuento(int statusDescuento) {
		status_descuento = statusDescuento;
	}

	// Funcion Guardar Empleado
	//public boolean guardar(){ return new GuardarSQL().Guardar_prestamo(this); }
	
	//public Obj_Empleado buscar(int folio){ return new BuscarSQL().FuenteSodasRh(folio); }
	
	// METODO PARA ACTUALIZAR UN EMPLEADO
	//public boolean actualizar(int folio){ return new ActualizarSQL().prestamo(this,folio); }
	
	// METODO BUSCAR EMPLEADO
	//public ObjEmpleado buscar(int Id){ return new BusquedaSQL().buscarEmpleado(Id); }
	
	public Obj_Prestamo maximo(){ return new BuscarSQL().maximoPrestamo(); }
	public boolean borrar(int Id){ return new ActualizarSQL().eliminarPrestamo(Id); }

}
