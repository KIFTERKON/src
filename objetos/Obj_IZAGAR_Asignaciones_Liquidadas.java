package objetos;

import SQL.GuardarTablasModel;

public class Obj_IZAGAR_Asignaciones_Liquidadas {

		
		Object[][] tabla_model;
		String fecha_inicial;
		String fecha_final;
		
		public Obj_IZAGAR_Asignaciones_Liquidadas(){
			this.tabla_model = null;	this.fecha_inicial="";	this.fecha_final="";
		}

		public Object[][] getTabla_model() {
			return tabla_model;
		}

		public void setTabla_model(Object[][] tabla_model) {
			this.tabla_model = tabla_model;
		}

		public String getFecha_inicial() {
			return fecha_inicial;
		}

		public void setFecha_inicial(String fecha_inicial) {
			this.fecha_inicial = fecha_inicial;
		}

		public String getFecha_final() {
			return fecha_final;
		}

		public void setFecha_final(String fecha_final) {
			this.fecha_final = fecha_final;
		}
		public boolean guardar_asignaciones(Object[][] tabla){
			return new GuardarTablasModel().tabla_IZAGAR_asignaciones_liquidadas(tabla );
		}
		public boolean guardar_liquidaciones(Object[][] tabla){
			return new GuardarTablasModel().IZAGAR_asignaciones_liquidadas_todas(tabla );
		}
		
		public boolean guardar_valores_por_tasa(Object[][] tabla){
			return new GuardarTablasModel().IZAGAR_insert_valores_por_tasa_todas(tabla );
		}
		
		public boolean borrar(String asignacion){
			return new GuardarTablasModel().borrar_IZAGAR_asignacion_liquidada(asignacion);
		}
	

	}


