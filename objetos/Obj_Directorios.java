package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Directorios {
	int folio;
	int folio_empleado;
	String Nombre;
	String Telefono;
	
	public Obj_Directorios(){
		this.folio=0; this.Nombre=""; this.Telefono="";
		this.folio_empleado=0;
	}
	
	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	public Obj_Directorios buscar(int folio){
		try {
			return new BuscarSQL().BucarDirectorios(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().ActualizarTelefono(this,folio); }

	public boolean guardar(){ return new GuardarSQL().Guardar_Telefono(this); }
}