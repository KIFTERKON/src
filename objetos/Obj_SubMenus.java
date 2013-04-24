package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.SubMenusSQL;

public class Obj_SubMenus {
	String nombre;
	public Obj_SubMenus(){
		this.nombre="";
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre.toString();
	}
	
	public String[] SubMenuAlimentacion() {
		try {
			return new SubMenusSQL().Alimentacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public String[] SubMenuCatalogos() {
		try {
			return new SubMenusSQL().Catalogos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public String[] SubMenuListas() {
		try {
			return new SubMenusSQL().Listas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public String[] SubMenuConfiguracion() {
		try {
			return new SubMenusSQL().Configuracion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuAutorizaciones() {
		try {
			return new SubMenusSQL().Autorizaciones();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuReportes() {
		try {
			return new SubMenusSQL().Reportes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuCortes() {
		try {
			return new SubMenusSQL().Cortes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[][] UsuarioMatriz(){
		return new BuscarSQL().getUsuarioPermisos();
	}
}
