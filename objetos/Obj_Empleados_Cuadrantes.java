package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Empleados_Cuadrantes 
{
	private int folio;
	private String nombre;
	private String cuadrante;
	private boolean status;

	
	public Obj_Empleados_Cuadrantes(){
		this.folio=0; this.nombre=""; this.cuadrante=""; this.status=false;
	}
	
	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuadrante() {
		return cuadrante;
	}

	public void setCuadrante(String cuadrante) {
		this.cuadrante = cuadrante;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Obj_Empleados_Cuadrantes buscar(int folio){
		try {
			return new BuscarSQL().buscar_empleado_cuadrante(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public int nuevoEmpleadoCuadrante(){
		try {
			return new BuscarSQL().NuevoEmpleadoCuadrante();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public boolean existe(String nombre){
		try {
			return new BuscarSQL().existeEmpleadoCuadrante(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean guardar(String[][] tabla){
		return new GuardarSQL().EmpleadoCuadrante(this,tabla);
	}
	
	public boolean actualizar(String[][] tabla){
		return new ActualizarSQL().EmpleadoCuadrante(this,tabla);
	}
	
	public static String[][] getTablaCuadrante(String nombre){
		return new BuscarSQL().getTablaEmpleadoCuadrante(nombre);
	}
}