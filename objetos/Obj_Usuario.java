package objetos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

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
	private String sesion;
	private int status;
		
	public Obj_Usuario(){
		this.folio=0; nombre_completo=""; contrasena=""; permiso_id=0; status=0; fecha_alta=""; fecha_actua=""; sesion="";
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
	
	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Usuario(this); }
	
	public Obj_Usuario buscar(int folio){ 
		try {
			return new BuscarSQL().Usuario(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Usuario(this,folio); }
		
	public Obj_Usuario buscarMaximo() {
		try {
			return new BuscarSQL().Maximo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean guardarPermisos(Vector Permisos){ 
		return new GuardarSQL().Guardar_Usuario(this, Permisos);
	}
	
	
	public boolean ExisteUsuario(String Nombre_Completo){ 
		try {
			return new BuscarSQL().existeUsuario(Nombre_Completo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public boolean actualizar(String Nombre_Completo, Vector Permisos) {
		return new ActualizarSQL().PermisoUsuario(Nombre_Completo, Permisos); 
	}
	
	
	public Obj_Usuario BuscarUsuario(String Nombre_Completo){
		try {
			return new BuscarSQL().BuscarUsuarios(Nombre_Completo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	@SuppressWarnings("rawtypes")
	public Vector returnPermisos(String Nombre_Completo, int menu){
		try {
			return new BuscarSQL().returnPermiso(Nombre_Completo,menu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public void Session(){
		new GuardarSQL().Guardar_Sesion(this);
	}
	
	public Obj_Usuario LeerSession()
    {
    	try{
    		return new BuscarSQL().getSession();
    	}catch(IOException e){
    		e.printStackTrace();
    		return null;
    	}
  }

}
