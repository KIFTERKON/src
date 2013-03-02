package objetos;

public class Obj_Abonos {
	private int folio_prestamo;
	private int folio_empleado;
	private float descuento;
	
	public Obj_Abonos(){
		this.folio_prestamo =0; this.folio_empleado=0; this.descuento=0;
	}

	public int getFolio_prestamo() {
		return folio_prestamo;
	}

	public void setFolio_prestamo(int folioPrestamo) {
		folio_prestamo = folioPrestamo;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folioEmpleado) {
		folio_empleado = folioEmpleado;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	
	
}
