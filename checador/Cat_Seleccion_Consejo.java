package checador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import ObjetoChecador.Obj_Solicitud_De_Empleados;

@SuppressWarnings("serial")
public class Cat_Seleccion_Consejo extends Cat_Seleccion_Solicitudes_Empleados {
	
	String seleccion[] = {"Seleccione un estado","Todos","Vigente","Pasar a consejo","Autorizado J. de Operaciones","Autorizado Consejo","Rechazado J. de Operaciones","Rechazado Consejo","Cancelado"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbSeleccion = new JComboBox(seleccion);
	
	JButton btnAutorizar = new JButton("Autorizar");
	JButton btnRechazar = new JButton("Rechazar");
	
	public Cat_Seleccion_Consejo(){
		setTitle("Revisión de solicitudes (Consejo)");
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		filtro.add(cmbSeleccion).setBounds(455,18,160,20);
		llenado_permisos.add(btnRechazar).setBounds(600, 240, 85, 20);
		llenado_permisos.add(btnAutorizar).setBounds(685, 240, 85, 20);
		
		cmbSeleccion.addActionListener(opSeleccion);
		btnAutorizar.addActionListener(opAutorizar);
		btnRechazar.addActionListener(opRechazar);
	}
	
	ActionListener opAutorizar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					actualizar(4);
			}
	};
	
	ActionListener opRechazar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					actualizar(6);
			}
	};
	
	ActionListener opSeleccion = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			if(cmbSeleccion.getSelectedIndex()==0){
				while(tabla.getRowCount()>0){
	                tabla_model.removeRow(0);
				}
			}else{
				while(tabla.getRowCount()>0){
	                tabla_model.removeRow(0);
				}
				
		        Object [][] lista_tabla = get_tabla_model(cmbSeleccion.getSelectedItem()+"");
		        String[] fila = new String[4];
		                for(int i=0; i<lista_tabla.length; i++){
		                        fila[0] = lista_tabla[i][0]+"";
		                        fila[1] = lista_tabla[i][1]+"";
		                        fila[2] = lista_tabla[i][2]+"";
		                        fila[3] = lista_tabla[i][3]+"";
		                        tabla_model.addRow(fila);
		                }
			}
		}
	};
	
	public void actualizar(int valor){
		Obj_Solicitud_De_Empleados solicitud = new Obj_Solicitud_De_Empleados();
		if(txtFolioSolicitud.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Debe seleccionar un empleado","Aviso",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else{
				if(solicitud.actualizar(Integer.valueOf(txtFolioSolicitud.getText()),valor)){
					limpiar();
					while(tabla.getRowCount()>0){tabla_model.removeRow(0);}
			           
					Object [][] lista_tabla = get_tabla_model(cmbSeleccion.getSelectedItem()+"");
					String[] fila = new String[4];
	                   for(int i=0; i<lista_tabla.length; i++){
	                           fila[0] = lista_tabla[i][0]+"";
	                           fila[1] = lista_tabla[i][1]+"";
	                           fila[2] = lista_tabla[i][2]+"";
	                           fila[3] = lista_tabla[i][3]+"";
	                           tabla_model.addRow(fila);
	                    }
					JOptionPane.showMessageDialog(null,"Solicitud autorizada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
			new Cat_Seleccion_Consejo().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
