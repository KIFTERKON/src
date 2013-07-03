package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_OpRespuesta {
	
	private int folio;
	private int numero;
	private String opcion;
	private String descripcion;
	private String nombre;
	private boolean status;
	
	public Obj_OpRespuesta(){
		this.folio=0; this.numero=0; this.opcion="";
		this.descripcion=""; this.status=false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String[] Combo_OpRespuesta() {
		try {
			return new Cargar_Combo().opRespuesta("tb_op_respuesta");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
			
	public Obj_OpRespuesta buscar(int folio){
		try {
			return new BuscarSQL().OpRespuesta(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
			
	public boolean guardar_libre(){ return new GuardarSQL().Guardar_OpRespuesta_Libre(this); }
	
	public boolean guardar_multiple(String[] tabla){ return new GuardarSQL().Guardar_OpRespuesta_Multiple(this,tabla); }
			
	public boolean actualizar(int folio){ return new ActualizarSQL().OpRespuesta(this,folio); }
	
	public boolean actualizar_multiple(String[] tabla, String nombre){ return new ActualizarSQL().OpRespuestaTabla(tabla, nombre); }
	
	public boolean existe(String Nombre, String opcion){
		try {
			return new BuscarSQL().OpRespuesta_Existe(Nombre, opcion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String Nuevo(){
		try {
			return new BuscarSQL().OpRespuesta_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
			
	public String[] Combo_Respuesta() {
		try {
			return new Cargar_Combo().Respuesta("tb_opciones_respuesta");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Tabla_Respuesta(String nombre) {
		try {
			return new BuscarSQL().TablaRespuesta(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}