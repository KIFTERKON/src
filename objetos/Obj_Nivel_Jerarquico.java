package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;



public class Obj_Nivel_Jerarquico 
{
	private int folio;
	private String descripcion;
	private int puesto_principal;
	private String puesto_dependiente;
	private String puesto;
	private String establecimiento;
	private boolean status;
	
	public Obj_Nivel_Jerarquico()
	{
		this.folio=0; this.descripcion=""; this.puesto_principal=0; this.puesto_dependiente="";
		this.puesto=""; this.status=false;
		
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


	public int getPuesto_principal() {
		return puesto_principal;
	}

	public void setPuesto_principal(int puesto_principal) {
		this.puesto_principal = puesto_principal;
	}

	public String getPuesto_dependiente() {
		return puesto_dependiente;
	}

	public void setPuesto_dependiente(String puesto_dependiente) {
		this.puesto_dependiente = puesto_dependiente;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String Nuevo(){
		try {
			return new BuscarSQL().OpNivel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*guarda la segunda parte del catalogo*/
	public boolean guardar_multiple(String[][] tabla){ return new GuardarSQL().Guardar_Tabla_Nivel(this,tabla); }
	
	/*buscamos la primer parte del catalogo*/
	public Obj_Nivel_Jerarquico buscar(int folio){
		try {
			return new BuscarSQL().buscarnivel(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	/*buscamos la segunda parte del catalogo*/
	public Obj_Nivel_Jerarquico buscartabla(int folio){
		try {
			return new BuscarSQL().buscartablanivel(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	/*Actualizacion de la segunda tabla*/
	public boolean actualizar(String[][] tabla){ return new ActualizarSQL().nivelGerarquico(this,tabla); }
	
	public String[] combo_nivel_jerarquico() {
		try {
			return new Cargar_Combo().niveljerarquico("tb_nivel_jerarquico");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
