package catalogos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objetos.Obj_Actividad_Asignadas_Nivel_Jerarquico;

@SuppressWarnings("serial")
public class Cat_Filtro_Actividades_Nivel_Jerarquico_cuadrante extends Cat_Filtro_Actividades {
	public Cat_Filtro_Actividades_Nivel_Jerarquico_cuadrante(){
		while(tabla.getRowCount() >0)
			this.tabla_model.removeRow(0);
		
		Object[][] filtro = new Obj_Actividad_Asignadas_Nivel_Jerarquico().filtro();
		
		Object[] registro = new Object[tabla.getColumnCount()];
		for(int i=0; i<filtro.length; i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
				registro[j] = filtro[i][j];
			}	
			tabla_model.addRow(registro);
		}
		
		this.tabla.removeMouseListener(opAgregar);
		this.tabla.addMouseListener(opAgregar1);
	}
	
	MouseListener opAgregar1 = new MouseListener() {
		public void mouseReleased(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() == 2){
    			int fila = tabla.getSelectedRow();
    			Object folio =  tabla.getValueAt(fila, 0);
    			dispose();
    			new Cat_Actividad_Asignadas_Nivel_Jerarquico(Integer.parseInt(folio.toString().trim())).setVisible(true);
        	}
		}
	};	
}
