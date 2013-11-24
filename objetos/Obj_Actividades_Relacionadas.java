package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Actividades_Relacionadas {
	
	private int folio;
	private String proyecto;
	private String descripcion;
	private String nivel_critico;
	private int status;
	
	public Obj_Actividades_Relacionadas(){
		this.folio=0;	this.proyecto="";	this.descripcion="";	this.nivel_critico="";	this.status=0;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivel_critico() {
		return nivel_critico;
	}

	public void setNivel_critico(String nivel_critico) {
		this.nivel_critico = nivel_critico;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int nuevo(){
		try {
			return new BuscarSQL().Relacion_Actividad_Nueva();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	public boolean existe(int folio){
		try {
			return new BuscarSQL().existeActividadRelacionada(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean existe(String proyecto){
		try {
			return new BuscarSQL().existeProyecto(proyecto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean guardar(String[][] tabla){
		boolean registro = new GuardarSQL().Guardar_Relacion_Actividades(this); 
		boolean tabla1 = new GuardarSQL().Guardar_Relacion_Tabla(this, tabla);
		
		if(registro == true && tabla1 == true){
			return true;
		}else{
			return false;
		}
	
	}
	
	public Obj_Actividades_Relacionadas buscarActividadRelacionada(int folio){
		try {
			return new BuscarSQL().Actividades_Relacionadas(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[][] tabla(int proyecto_cuadrante) throws SQLException{
		return new BuscarSQL().getTablaActividadesRelacionadas(proyecto_cuadrante);
	}
	
	public boolean actualizar(int folio, String[][] tabla){ return new ActualizarSQL().Relacion_Actividad(this,tabla); }
}