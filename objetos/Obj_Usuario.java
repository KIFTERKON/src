package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Usuario {
	private int folio;
	private String nombre_completo;
	private String contrasena;
	private int permiso_id;
	private String fecha_alta;
	private String fecha_actua;
	private int status;
		
	public Obj_Usuario(){
		this.folio=0; nombre_completo=""; contrasena=""; permiso_id=0; status=0; fecha_alta=""; fecha_actua="";
	}


	public String getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(String fechaAlta) {
		this.fecha_alta = fechaAlta;
	}


	public String getFecha_actua() {
		return fecha_actua;
	}


	public void setFecha_actua(String fechaActua) {
		this.fecha_actua = fechaActua;
	}


	public int getFolio() {
		return folio;
	}


	public void setFolio(int folio) {
		this.folio = folio;
	}


	public String getNombre_completo() {
		return nombre_completo;
	}


	public void setNombre_completo(String nombreCompleto) {
		nombre_completo = nombreCompleto;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public int getPermiso_id() {
		return permiso_id;
	}


	public void setPermiso_id(int permisoId) {
		permiso_id = permisoId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Usuario(this); }
	
	public Obj_Usuario buscar(int folio){ 
		try {
			return new BuscarSQL().Usuario(folio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Usuario(this,folio); }
	
	//public ObjEmpleado buscar(int Id){ return new BusquedaSQL().buscarEmpleado(Id); }
	
	public Obj_Usuario buscarMaximo() {
		try {
			return new BuscarSQL().Maximo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// METODO BORRAR EMPLEADO
	//public boolean borrar(int Id){ return new ActualizarSQL().eliminarEmpleado(Id);	}
	
	
	
}
