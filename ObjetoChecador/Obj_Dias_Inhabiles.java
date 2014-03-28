package ObjetoChecador;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Dias_Inhabiles {
	private int folio;
	private String fecha;
	private String descripcion;

	public Obj_Dias_Inhabiles() {
		this.folio = 0;
		this.fecha = "";
		this.descripcion = "";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Obj_Dias_Inhabiles buscar(int folio) {
		try {
			return new BuscarSQL().diaInA(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean guardar() {
		return new GuardarSQL().Guardar_Dia_Inhabil(this);
	}

	public Obj_Dias_Inhabiles buscar_nuevo() {
		try {
			return new BuscarSQL().DiaInA_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean actualizar(int folio) {
		return new ActualizarSQL().DiaInHabil(this, folio);
	}

	// public Obj_Dias_Inhabiles buscar_pues(String nombre){
	// try{
	// return new BuscarSQL().Pues_buscar(nombre);
	// } catch(SQLException e){
	//
	// }
	// return null;
	// }
	//
	// public Obj_Dias_Inhabiles buscar_pues(int folio){
	// try{
	// return new BuscarSQL().Pues_buscar(folio);
	// } catch(SQLException e){
	//
	// }
	// return null;
	// }
}
