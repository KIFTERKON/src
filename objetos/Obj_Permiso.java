package objetos;

import java.sql.SQLException;

import SQL.Cargar_Combo;



public class Obj_Permiso {
	private int folio;
	private String nombre;
	private int status;
	
	public Obj_Permiso(){
		this.folio=0; nombre=""; status=0;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String[] Combo_Permiso() {
		try {
			return new Cargar_Combo().Permiso("tb_permiso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
