package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_OpRespuesta {
	
	private int folio;
	private String nombre;
	private String tipo_opcion;
	private boolean status;
	
	public Obj_OpRespuesta(){
		this.folio=0; this.nombre=""; this.tipo_opcion=""; this.status=false;
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

	public String getTipo_opcion() {
		return tipo_opcion;
	}

	public void setTipo_opcion(String tipo_opcion) {
		this.tipo_opcion = tipo_opcion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int Nuevo(){
		try {
			return new BuscarSQL().OpRespuesta_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean guardar(){
		return new GuardarSQL().opcion_respuesta(this);
	}
	
	public boolean actualizar(int folio){
		return new ActualizarSQL().opcion_respuesta(this,folio);
	}
	
	public Obj_OpRespuesta buscar(int folio){
		try {
			return new BuscarSQL().buscar_opcion_respuesta(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean status_buscar(int folio){
		try {
			return new BuscarSQL().buscar_respuesta_folio(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String[] Combo_Respuesta(){
		try {
			return new Cargar_Combo().opRespuesta("tb_opciones_respuesta");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}