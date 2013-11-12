package ObjetoChecador;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class ObjHorario 
{
	int folio;
	String nombre ;
	String diaD,diaL,diaM,diaMI,diaJ,diaV,diaS;
	String domingo1,domingo2,domingo3,domingo4,domingo5;
	String lunes1,lunes2,lunes3,lunes4,lunes5;
	String martes1,martes2,martes3,martes4,martes5;
	String miercoles1,miercoles2,miercoles3,miercoles4,miercoles5;
	String jueves1,jueves2,jueves3,jueves4,jueves5;
	String viernes1,viernes2,viernes3,viernes4,viernes5;
	String sabado1,sabado2,sabado3,sabado4,sabado5;
	int descanso;
	int diaDobla;
	int diaDobla2;
	int diaDobla3;
	
	int horarioDeposito;
	int recesoDiarioExtra;
	
	public ObjHorario()
	{
		this.folio=0;
		this.nombre="";
		
		this.diaD="";
		this.diaL="";
		this.diaM="";
		this.diaMI="";
		this.diaJ="";
		this.diaV="";
		this.diaS="";
		
		this.domingo1="";
		this.domingo2="";
		this.domingo3="";
		this.domingo4="";
		this.domingo5="";
		
		this.lunes1="";
		this.lunes2="";
		this.lunes3="";
		this.lunes4="";
		this.lunes5="";
		
		this.martes1="";
		this.martes2="";
		this.martes3="";
		this.martes4="";
		this.martes5="";
		
		this.miercoles1="";
		this.miercoles2="";
		this.miercoles3="";
		this.miercoles4="";
		this.miercoles5="";
		
		this.jueves1="";
		this.jueves2="";
		this.jueves3="";
		this.jueves4="";
		this.jueves5="";
		
		this.viernes1="";
		this.viernes2="";
		this.viernes3="";
		this.viernes4="";
		this.viernes5="";
		
		this.sabado1="";
		this.sabado2="";
		this.sabado3="";
		this.sabado4="";
		this.sabado5="";
		
		this.descanso=0;
		this.diaDobla=0;
		this.diaDobla2=0;
		this.diaDobla3=0;
		
		this.horarioDeposito=0;
		this.recesoDiarioExtra=0;
	}
	

	public int getFolio() {
		return folio;
	}


	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDiaD() {
		return diaD;
	}


	public void setDiaD(String diaD) {
		this.diaD = diaD;
	}


	public String getDiaL() {
		return diaL;
	}


	public void setDiaL(String diaL) {
		this.diaL = diaL;
	}


	public String getDiaM() {
		return diaM;
	}


	public void setDiaM(String diaM) {
		this.diaM = diaM;
	}


	public String getDiaMI() {
		return diaMI;
	}


	public void setDiaMI(String diaMI) {
		this.diaMI = diaMI;
	}


	public String getDiaJ() {
		return diaJ;
	}


	public void setDiaJ(String diaJ) {
		this.diaJ = diaJ;
	}


	public String getDiaV() {
		return diaV;
	}


	public void setDiaV(String diaV) {
		this.diaV = diaV;
	}


	public String getDiaS() {
		return diaS;
	}


	public void setDiaS(String diaS) {
		this.diaS = diaS;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomingo1() {
		return domingo1;
	}

	public void setDomingo1(String domingo1) {
		this.domingo1 = domingo1;
	}

	public String getDomingo2() {
		return domingo2;
	}

	public void setDomingo2(String domingo2) {
		this.domingo2 = domingo2;
	}

	public String getDomingo3() {
		return domingo3;
	}

	public void setDomingo3(String domingo3) {
		this.domingo3 = domingo3;
	}

	public String getDomingo4() {
		return domingo4;
	}

	public void setDomingo4(String domingo4) {
		this.domingo4 = domingo4;
	}

	public String getDomingo5() {
		return domingo5;
	}

	public void setDomingo5(String domingo5) {
		this.domingo5 = domingo5;
	}
	
	public String getLunes1() {
		return lunes1;
	}

	public void setLunes1(String lunes1) {
		this.lunes1 = lunes1;
	}

	public String getLunes2() {
		return lunes2;
	}

	public void setLunes2(String lunes2) {
		this.lunes2 = lunes2;
	}

	public String getLunes3() {
		return lunes3;
	}

	public void setLunes3(String lunes3) {
		this.lunes3 = lunes3;
	}

	public String getLunes4() {
		return lunes4;
	}

	public void setLunes4(String lunes4) {
		this.lunes4 = lunes4;
	}

	public String getLunes5() {
		return lunes5;
	}

	public void setLunes5(String lunes5) {
		this.lunes5 = lunes5;
	}

	public String getMartes1() {
		return martes1;
	}

	public void setMartes1(String martes1) {
		this.martes1 = martes1;
	}

	public String getMartes2() {
		return martes2;
	}

	public void setMartes2(String martes2) {
		this.martes2 = martes2;
	}

	public String getMartes3() {
		return martes3;
	}

	public void setMartes3(String martes3) {
		this.martes3 = martes3;
	}

	public String getMartes4() {
		return martes4;
	}

	public void setMartes4(String martes4) {
		this.martes4 = martes4;
	}

	public String getMartes5() {
		return martes5;
	}

	public void setMartes5(String martes5) {
		this.martes5 = martes5;
	}

	public String getMiercoles1() {
		return miercoles1;
	}

	public void setMiercoles1(String miercoles1) {
		this.miercoles1 = miercoles1;
	}

	public String getMiercoles2() {
		return miercoles2;
	}

	public void setMiercoles2(String miercoles2) {
		this.miercoles2 = miercoles2;
	}

	public String getMiercoles3() {
		return miercoles3;
	}

	public void setMiercoles3(String miercoles3) {
		this.miercoles3 = miercoles3;
	}

	public String getMiercoles4() {
		return miercoles4;
	}

	public void setMiercoles4(String miercoles4) {
		this.miercoles4 = miercoles4;
	}

	public String getMiercoles5() {
		return miercoles5;
	}

	public void setMiercoles5(String miercoles5) {
		this.miercoles5 = miercoles5;
	}

	public String getJueves1() {
		return jueves1;
	}

	public void setJueves1(String jueves1) {
		this.jueves1 = jueves1;
	}

	public String getJueves2() {
		return jueves2;
	}

	public void setJueves2(String jueves2) {
		this.jueves2 = jueves2;
	}

	public String getJueves3() {
		return jueves3;
	}

	public void setJueves3(String jueves3) {
		this.jueves3 = jueves3;
	}

	public String getJueves4() {
		return jueves4;
	}

	public void setJueves4(String jueves4) {
		this.jueves4 = jueves4;
	}

	public String getJueves5() {
		return jueves5;
	}

	public void setJueves5(String jueves5) {
		this.jueves5 = jueves5;
	}

	public String getViernes1() {
		return viernes1;
	}

	public void setViernes1(String viernes1) {
		this.viernes1 = viernes1;
	}

	public String getViernes2() {
		return viernes2;
	}

	public void setViernes2(String viernes2) {
		this.viernes2 = viernes2;
	}

	public String getViernes3() {
		return viernes3;
	}

	public void setViernes3(String viernes3) {
		this.viernes3 = viernes3;
	}

	public String getViernes4() {
		return viernes4;
	}

	public void setViernes4(String viernes4) {
		this.viernes4 = viernes4;
	}

	public String getViernes5() {
		return viernes5;
	}

	public void setViernes5(String viernes5) {
		this.viernes5 = viernes5;
	}

	public String getSabado1() {
		return sabado1;
	}

	public void setSabado1(String sabado1) {
		this.sabado1 = sabado1;
	}

	public String getSabado2() {
		return sabado2;
	}

	public void setSabado2(String sabado2) {
		this.sabado2 = sabado2;
	}

	public String getSabado3() {
		return sabado3;
	}

	public void setSabado3(String sabado3) {
		this.sabado3 = sabado3;
	}

	public String getSabado4() {
		return sabado4;
	}

	public void setSabado4(String sabado4) {
		this.sabado4 = sabado4;
	}

	public String getSabado5() {
		return sabado5;
	}

	public void setSabado5(String sabado5) {
		this.sabado5 = sabado5;
	}
	
	public int getDescanso() {
		return descanso;
	}

	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	public int getDiaDobla() {
		return diaDobla;
	}

	public void setDiaDobla(int diaDobla) {
		this.diaDobla = diaDobla;
	}
	
	public int getDiaDobla2() {
		return diaDobla2;
	}

	public void setDiaDobla2(int diaDobla2) {
		this.diaDobla2 = diaDobla2;
	}

	public int getDiaDobla3() {
		return diaDobla3;
	}

	public void setDiaDobla3(int diaDobla3) {
		this.diaDobla3 = diaDobla3;
	}

	public int getHorarioDeposito() {
		return horarioDeposito;
	}

	public void setHorarioDeposito(int horarioDeposito) {
		this.horarioDeposito = horarioDeposito;
	}

	public int getRecesoDiarioExtra() {
		return recesoDiarioExtra;
	}

	public void setRecesoDiarioExtra(int recesoDiarioExtra) {
		this.recesoDiarioExtra = recesoDiarioExtra;
	}

	public ObjHorario buscar(int folio){
		try {
			return new BuscarSQL().buscahorario(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	public boolean Guardar(){
		return new GuardarSQL().Guardar_Horario(this);
	}
	
	public boolean Existe(int folio){ 
		return new BuscarSQL().HorarioExiste(folio);
	}
	
	public boolean Actualizar(int folio){
		return new ActualizarSQL().Horario(this,folio); 
		}
	
	public ObjHorario buscar_nuevo() throws SQLException{ return new BuscarSQL().Horario_Nuevo(); }
}
