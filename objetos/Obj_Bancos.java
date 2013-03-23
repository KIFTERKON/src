package objetos;

import SQL.ActualizarSQL;
import SQL.GuardarSQL;

public class Obj_Bancos {
	
	private int folio_empleado;
	private String nombre_completo;
	private String establecimiento;
	private float banamex;
	private float banorte;
	private int status;
	
	public Obj_Bancos(){
		this.folio_empleado=0; this.nombre_completo=""; this.establecimiento=""; this.banamex=0;
		this.banorte=0; this.status=0;
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

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public float getBanamex() {
		return banamex;
	}

	public void setBanamex(float banamex) {
		this.banamex = banamex;
	}

	public float getBanorte() {
		return banorte;
	}

	public void setBanorte(float banorte) {
		this.banorte = banorte;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Bancos(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Actualizar_Bancos(this,folio); }
//	
//	public Obj_Bancos buscar(int folio){ return new BuscarSQL().Deduccion(folio); }
}
