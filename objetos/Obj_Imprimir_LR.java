package objetos;

import SQL.GuardarSQL;

public class Obj_Imprimir_LR {
	
	public Obj_Imprimir_LR(){
		
	}
	public boolean Imprimir(){ return new GuardarSQL().lista_Imprimir(); }

}
