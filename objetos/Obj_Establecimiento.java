package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Establecimiento {
	private int folio;
	private String nombre;
	private String abreviatura;
	private boolean status;
	
	public Obj_Establecimiento(){
		folio=0;
		nombre="";
		abreviatura="";
		status=false;
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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		this.status = b;
	}
	
	public String[] Combo_Establecimiento_Empleados() {
		try {
			return new Cargar_Combo().Establecimiento_Empleado("tb_establecimiento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Combo_Establecimiento(){
		try {
			return new Cargar_Combo().Establecimiento("tb_establecimiento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
	

	public Obj_Establecimiento buscar(int folio) { 
		try {
			return new BuscarSQL().Establecimiento(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Establecimiento(this,folio); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Establecimiento(this); }
	
	public Obj_Establecimiento buscar_nuevo() { 
		try {
			return new BuscarSQL().Establecimiento_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}	

	public Obj_Establecimiento buscar_estab(String nombre){
		try{
			return new BuscarSQL().Establ_buscar(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public Obj_Establecimiento buscar_estab(int folio){
		try{
			return new BuscarSQL().Establ_buscar_folio(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}
}
