package objetos;

import SQL.BuscarSQL;

public class Obj_Nomina {
	int numero_listaraya;
	
	public Obj_Nomina(){
		this.numero_listaraya=0;
	}

	public int getNumero_listaraya() {
		return numero_listaraya;
	}

	public void setNumero_listaraya(int numero_listaraya) {
		this.numero_listaraya = numero_listaraya;
	}
	
	public String[][] MatrizNomina(int Folio){
		return new BuscarSQL().getNomina(Folio);
	}
	
	public int MaxListaRaya(){
		return new BuscarSQL().MaximoListaRaya();
	}
	
	public String[] getTotales(int Folio){
		return new BuscarSQL().getTotalesNomina(Folio);
	}
	
	public String[] getCheque1(int Folio){
		return new BuscarSQL().getTotalesCheque1(Folio);
	}
	
	public String[] getCheque1_ferre(int Folio){
		return new BuscarSQL().getTotalesCheque1Ferre(Folio);
	}
	
	public String[] getCheque1_izacel(int Folio){
		return new BuscarSQL().getTotalesCheque1Izacel(Folio);
	}
	
	public String[] getChequeABC(int Folio){
		return new BuscarSQL().getNominaChequeABC(Folio);
	}
}
