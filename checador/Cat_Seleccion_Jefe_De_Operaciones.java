package checador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import ObjetoChecador.Obj_Solicitud_De_Empleados;

@SuppressWarnings("serial")
public class Cat_Seleccion_Jefe_De_Operaciones extends Cat_Seleccion_Solicitudes_Empleados {
	
	JButton btnAutorizar = new JButton("Autorizar");
	JButton btnRechazar = new JButton("Rechazar");
	JButton btnConsejo = new JButton("A Consejo");
	
	public Cat_Seleccion_Jefe_De_Operaciones(){
		setTitle("Revisión de solicitudes (Jefe de operaciones)");
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		 Object [][] lista_tabla = get_tabla_model("Vigente");
         String[] fila = new String[4];
                 for(int i=0; i<lista_tabla.length; i++){
                         fila[0] = lista_tabla[i][0]+"";
                         fila[1] = lista_tabla[i][1]+"";
                         fila[2] = lista_tabla[i][2]+"";
                         fila[3] = lista_tabla[i][3]+"";
                         tabla_model.addRow(fila);
                 }
        
        llenado_permisos.add(btnAutorizar).setBounds(685, 100, 85, 20);
		llenado_permisos.add(btnRechazar).setBounds(600, 240, 85, 20);
		llenado_permisos.add(btnConsejo).setBounds(685, 240, 85, 20);
		
		btnConsejo.addActionListener(opConsejo);
		btnAutorizar.addActionListener(opAutorizar);
		btnRechazar.addActionListener(opRechazar);
	}
	
	ActionListener opConsejo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					actualizar(2);
			}
	};
	
	ActionListener opAutorizar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					actualizar(3);
			}
	};
	
	ActionListener opRechazar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					actualizar(5);
			}
	};
	
// actualizar estatus  aki esta pendiente /*-**/*-/*-*/*-*/*-/*/*-/*-/*-/*-/*-/*-/*-/*-/*-/*-*-/*-/-//*/*-/
	public void actualizar(int valor){
		
		Obj_Solicitud_De_Empleados solicitud = new Obj_Solicitud_De_Empleados();
		if(txtFolioSolicitud.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Debe seleccionar un empleado","Aviso",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else{
			
			if(solicitud.actualizar(Integer.valueOf(txtFolioSolicitud.getText()),valor)){
					limpiar();
					while(tabla.getRowCount()>0){tabla_model.removeRow(0);}
	           
					Object [][] lista_tabla = get_tabla_model("Vigente");
					String[] fila = new String[4];
	                   for(int i=0; i<lista_tabla.length; i++){
	                           fila[0] = lista_tabla[i][0]+"";
	                           fila[1] = lista_tabla[i][1]+"";
	                           fila[2] = lista_tabla[i][2]+"";
	                           fila[3] = lista_tabla[i][3]+"";
	                           tabla_model.addRow(fila);
	                   }
					JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
			}else{
				JOptionPane.showMessageDialog(null,"El Registro no se a guardo!","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Seleccion_Jefe_De_Operaciones().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
