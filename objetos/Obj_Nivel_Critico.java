package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Nivel_Critico {
	
	private int folio;
	private String descripcion;
	private int valor;
	private boolean status;

	public Obj_Nivel_Critico(){
		this.folio=0;  this.descripcion=""; this.valor=0; this.status=false;
	}

			public int getFolio() {
				return folio;
			}
			
			public void setFolio(int folio) {
				this.folio = folio;
			}
			
			public String getDescripcion() {
				return descripcion;
			}
			
			public void setDescripcion(String descripcion) {
				this.descripcion = descripcion;
			}
			
			public int getValor() {
				return valor;
			}
			
			public void setValor(int valor) {
				this.valor = valor;
			}
			
			public boolean getStatus() {
				return status;
			}
			
			public void setStatus(boolean status) {
				this.status = status;
			}
			public Obj_Nivel_Critico buscar(int folio){
				try {
					return new BuscarSQL().Nivel(folio);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
			
			public boolean guardar(){ return new GuardarSQL().Guardar_Nivel(this); }
			
			public boolean actualizar(int folio){ return new ActualizarSQL().Nivel(this,folio); }
			
			public Obj_Nivel_Critico buscar_nuevo(){
				try {
					return new BuscarSQL().Nivel_Nuevo();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
			public String[] Combo_Nivel_Critico() {
				try {
					return new Cargar_Combo().Nivel_Critico("tb_nivel_critico");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null; 
			}
}
