package objetos;

public class Obj_Prestamo {
	private int folio;
	private String ticket;
	private String nombre_completo;
    private double cantidad;
    private String fecha;
    
    public Obj_Prestamo(){
    	this.ticket = "";
    	this.nombre_completo = "";
    	this.cantidad =0.0;
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

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String folio) {
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
	//public boolean guardar(){ return new GuardarSQL().Guardar_prestamo(this); }
	
	//public Obj_Empleado buscar(int folio){ return new BuscarSQL().FuenteSodasRh(folio); }
	
	// METODO PARA ACTUALIZAR UN EMPLEADO
	//public boolean actualizar(int folio){ return new ActualizarSQL().prestamo(this,folio); }
	
	// METODO BUSCAR EMPLEADO
	//public ObjEmpleado buscar(int Id){ return new BusquedaSQL().buscarEmpleado(Id); }
	
	//public Obj_Prestamo maximo(){ return new BuscarSQL().MaximoPuesto(); }
//--------------------------------->>>>>>>	
	//public boolean borrar(int Id){ return new ActualizarSQL().eliminarListaFuenteSodas(Id); }
	
	

}
