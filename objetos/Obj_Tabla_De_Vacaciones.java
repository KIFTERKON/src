package objetos;

import java.sql.SQLException;

import SQL.BuscarTablasModel;
import SQL.Cargar_Combo;

public class Obj_Tabla_De_Vacaciones {
	
	String grupo;
	int anios_trabajados;
	int dias_correspondientes;
	int prima_vacacional;
	int status;
	
	public Obj_Tabla_De_Vacaciones(){
		this.grupo="";
		this.anios_trabajados=0;
		this.dias_correspondientes=0;
		this.prima_vacacional=0;
		this.status=0;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public int getAnios_trabajados() {
		return anios_trabajados;
	}

	public void setAnios_trabajados(int anios_trabajados) {
		this.anios_trabajados = anios_trabajados;
	}

	public int getDias_correspondientes() {
		return dias_correspondientes;
	}

	public void setDias_correspondientes(int dias_correspondientes) {
		this.dias_correspondientes = dias_correspondientes;
	}

	public int getPrima_vacacional() {
		return prima_vacacional;
	}

	public void setPrima_vacacional(int prima_vacacional) {
		this.prima_vacacional = prima_vacacional;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String[] Combo_Grupo(){
		try {
			return new Cargar_Combo().Grupo_De_Vacaciones("tb_grupo_de_vacaciones");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
}
