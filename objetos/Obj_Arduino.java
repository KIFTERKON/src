package objetos;

import java.sql.SQLException;

import SQL.BuscarSQL;

public class Obj_Arduino
{
	String mañana;
	String mediodia;
	String tarde;
	
	public Obj_Arduino()
	{
		this.mañana="";
		this.mediodia="";
		this.tarde="";
	}

	public String getMañana() {
		return mañana;
	}

	public void setMañana(String mañana) {
		this.mañana = mañana;
	}

	public String getMediodia() {
		return mediodia;
	}

	public void setMediodia(String mediodia) {
		this.mediodia = mediodia;
	}

	public String getTarde() {
		return tarde;
	}

	public void setTarde(String tarde) {
		this.tarde = tarde;
	}
	
	public Obj_Arduino Buscar(){
		try {
			return new BuscarSQL().Buscar_Arduino();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
}
