package objetos;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Empleado {
	
	private int folio;
	private int no_checador;
	private String nombre;
	private String ap_paterno;
	private String ap_materno;
	private int establecimiento;
	private int puesto;
	private int sueldo;
	private int bono;
	private boolean fuente_sodas;
	private boolean gafete;
	private int status;
	private String fecha;
	
	public Obj_Empleado(){
		folio=0; no_checador=0; nombre=""; ap_paterno=""; ap_materno=""; establecimiento=0;
		puesto=0; sueldo=0; bono=0; status=0; fecha=""; fuente_sodas=false;
	}

	public boolean getFuente_sodas() {
		return fuente_sodas;
	}

	public void setFuente_sodas(boolean fuenteSodas) {
		fuente_sodas = fuenteSodas;
	}

	public boolean getGafete() {
		return gafete;
	}

	public void setGafete(boolean gafete) {
		this.gafete = gafete;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNo_checador() {
		return no_checador;
	}

	public void setNo_checador(int noChecador) {
		no_checador = noChecador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String apPaterno) {
		ap_paterno = apPaterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String apMaterno) {
		ap_materno = apMaterno;
	}

	public int getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(int establecimiento) {
		this.establecimiento = establecimiento;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getBono() {
		return bono;
	}

	public void setBono(int bono) {
		this.bono = bono;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

	
	// Funcion Guardar Empleado
	public boolean guardar(){ return new GuardarSQL().Guardar_Empleado(this); }
	
	public Obj_Empleado buscar(int folio){ return new BuscarSQL().Empleado(folio); }
	
	// METODO PARA ACTUALIZAR UN EMPLEADO
	public boolean actualizar(int folio){ return new ActualizarSQL().Empleado(this,folio); }
	
	// METODO BUSCAR EMPLEADO
	//public ObjEmpleado buscar(int Id){ return new BusquedaSQL().buscarEmpleado(Id); }
	
	// METODO BUSCAR EL NUMERO ID MAXIMO
	public Obj_Empleado buscar_nuevo(){ return new BuscarSQL().Empleado_Nuevo(); }
	
	// METODO BORRAR EMPLEADO
	//public boolean borrar(int Id){ return new ActualizarSQL().eliminarEmpleado(Id);	}
	
	
	
}
