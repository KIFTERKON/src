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
	
	public String[] SubMenuCatalogo() {
		try {
			return new SubMenusSQL().Catalogo();
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
	public String[] SubMenuContabilidad_Conciliacion_Auxiliar_Finanzas() {
		try {
			return new SubMenusSQL().Contabilidad_Conciliacion_Auxiliar_Finanzas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public String[] SubMenuCuadrantes_Alimentacion() {
		try {
			return new SubMenusSQL().Cuadrantes_Alimentacion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuCuadrantes_Catalogo() {
		try {
			return new SubMenusSQL().Cuadrantes_Catalogo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuCuadrantes_Reportes() {
		try {
			return new SubMenusSQL().Cuadrantes_Reportes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuLista_Raya_Alimentacion () {
		try {
			return new SubMenusSQL().Lista_Raya_Alimentacion ();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuLista_Raya_Comparaciones() {
		try {
			return new SubMenusSQL().Lista_Raya_Comparaciones();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuLista_Raya_Autorizaciones() {
		try {
			return new SubMenusSQL().Lista_Raya_Autorizaciones();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuLista_Raya_Departamento_Cortes() {
		try {
			return new SubMenusSQL().Lista_Raya_Departamento_Cortes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] SubMenuLista_Raya_Reportes() {
		try {
			return new SubMenusSQL().Lista_Raya_Reportes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	public String[][] UsuarioMatriz(){
		return new BuscarSQL().getUsuarioPermisos();
	}
}
