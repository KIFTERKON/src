package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Agregar_Submenus_Nuevos {
     String menu;
     String nombre;
     String catalogo;
     
     public Obj_Agregar_Submenus_Nuevos (){
   	  this.menu="";
   	  this.nombre="";
   	  this.catalogo="";
                                         }
	public String [] Combo_Menus(){
		
		try {
			return 	new Cargar_Combo().menus();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}

	public boolean existe (String nombre){
		//retornara si existe o no el campo
		return new BuscarSQL().existe_submenu(nombre);
		
		
	}
	public boolean guardar() {
		
		return new GuardarSQL().guardar(this);
	}
}
