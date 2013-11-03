package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.BuscarTablasModel;
import SQL.Cargar_Combo;
import SQL.GuardarSQL;

public class Obj_Departamento {
	private int folio;
	private String departamento;
	private String abreviatura;
	private boolean status;
	
	public Obj_Departamento(){
		this.folio=0; departamento=""; abreviatura="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

//	public Obj_Horario_Empleado buscar_tur(String nombre){
//		try{
//			return new BuscarSQL().Turn_buscar(nombre); 
//		} catch(SQLException e){
//			
//		}
//		return null;
//	}
//	
//	public Obj_Horario_Empleado buscar_tur(int folio){
//		try{
//			return new BuscarSQL().Turn_buscar(folio); 
//		} catch(SQLException e){
//			
//		}
//		return null;
//	}
	
	public Obj_Departamento buscar_nuevo() throws SQLException{ return new BuscarSQL().Departamento_Nuevo(); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Departamento(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Departamento(this,folio); }
	
	public Obj_Departamento buscar(int folio) {
		try {
			return new BuscarSQL().Departamento(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
//	para asignar lista a cmbDepartamento en empleado
//	public String[] Combo_Turno(){ 
//		try {
//			return new Cargar_Combo().Turno("tb_horarios");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null; 
//	}
	
	public Obj_Departamento buscar_departamento(int folio){
		try{
				return new BuscarSQL().Departamento_buscar(folio); 
			} catch(SQLException e){
				e.printStackTrace();
			}
		return null;
		}
	
	public Obj_Departamento buscar_departamento(String departamento){
		try{
				return new BuscarSQL().Departamento_buscar_nombre(departamento); 
			} catch(SQLException e){
				e.printStackTrace();
			}
		return null;
		}
	
	public Object[][] get_tabla_model_departamento(){
		return new BuscarTablasModel().tabla_model_departamento();
	}
	
	public String[] Combo_Departamento(){
		try {
			return new Cargar_Combo().Departamento("tb_departamento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
}
