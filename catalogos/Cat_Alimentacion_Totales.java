package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import SQL.Connexion;

import objetos.Obj_Alimentacion_Totales;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Totales extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String[][] Tabla = new Obj_Alimentacion_Totales().EstablecimientoMatriz();
	DefaultTableModel model = new DefaultTableModel(Tabla,
            new String[]{"Establecimiento", "Nomina"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class
         };
	     
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return true; 
        	 } 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla);
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnSalir   = new JButton("Salir");
	
	public Cat_Alimentacion_Totales(){
		this.setTitle("Alimentación de Totales de Nomina");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		
		panel.add(scroll).setBounds(20,20,400,343);
		
		panel.add(btnSalir).setBounds(20,400,80,20);
		panel.add(btnLimpiar).setBounds(185,400,80,20);
		panel.add(btnGuardar).setBounds(340,400,80,20);
		
		cont.add(panel);
		
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnGuardar.addActionListener(opGuardar);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(190);
		tabla.getColumnModel().getColumn(0).setMinWidth(190);
		
		this.setSize(450,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener opSalir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	};
	
	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			for(int i=0; i<model.getRowCount(); i++){
				model.setValueAt("", i, 1);
			}
		}
		
	};
	
	ActionListener opGuardar = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			int numero = getNumeroLista();
			Vector miVector = new Vector();
			
			if(getExisteLista(numero) == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j)+"");
					}
					Obj_Alimentacion_Totales costos = new Obj_Alimentacion_Totales();
					
					costos.setFolio_raya(numero);
					costos.setEstablecimiento(miVector.get(0).toString());
					if(miVector.get(1).toString().length() == 0){
						costos.setNomina(Float.parseFloat(0.0+""));
					}else{
						costos.setNomina(Float.parseFloat(miVector.get(1)+""));
					}

					costos.guardar();
					miVector.clear();
					
					
				}
				JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
				
			}else{
				if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							miVector.add(model.getValueAt(i,j)+"");
						}
						Obj_Alimentacion_Totales costos = new Obj_Alimentacion_Totales();
						
						costos.setFolio_raya(numero);
						costos.setEstablecimiento(miVector.get(0).toString());
						if(miVector.get(1).toString().length() == 0){
							costos.setNomina(Float.parseFloat(0.0+""));
						}else{
							costos.setNomina(Float.parseFloat(miVector.get(1)+""));
						}

						costos.actualizar();
						miVector.clear();
						
					}
				}else{
					return;
				}
				
			}
		}
	};
	
	public int getExisteLista(int lista_raya){
		int valor = 0;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select max(lista_raya) as Lista from tb_captura_totales_nomina where lista_raya ="+lista_raya);
			while(rs.next()){
				valor = rs.getInt(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public int getNumeroLista(){
		int valor = 0;
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_max_folio_lista_raya");
			while(rs.next()){
				valor = rs.getInt(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public static void main(String args[]){
		try	{  
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_Totales().setVisible(true);
		} catch (Exception e){
		   e.printStackTrace();
		}
		
	}

}
