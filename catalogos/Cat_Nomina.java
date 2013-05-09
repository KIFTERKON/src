package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Nomina;

@SuppressWarnings("serial")
public class Cat_Nomina extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JButton btnImprimir = new JButton("Imprimir");
	
	int folio = new Obj_Nomina().MaxListaRaya()+1;
	String[] espacio = {"","","","","",""};
//	String[] totales = new Obj_Nomina().getTotales(folio);
//	String[] cheque_1_super = new Obj_Nomina().getCheque1(folio);
//	String[] cheque_1_ferre = new Obj_Nomina().getCheque1_ferre(folio);
//	String[] cheque_1_izacel = new Obj_Nomina().getCheque1_izacel(folio);
//	String[] chequeABC = new Obj_Nomina().getChequeABC(folio);
	String[][] Tabla = new Obj_Nomina().MatrizNomina(folio);
	
	DefaultTableModel model = new DefaultTableModel(Tabla, new String[]{"", "", "", "", "", ""}){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : return false; 
        	 	case 4 : return false;
        	 	case 5 : return false;
        	 } 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla);
	
	public Cat_Nomina(){
		System.out.println(folio);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Accounting.png"));
		this.setTitle("Totales de Cheque");
		
		panel.setBorder(BorderFactory.createTitledBorder("Totales De Cheque"));
		
		panel.add(scroll).setBounds(15,50,460,503);
		panel.add(btnImprimir).setBounds(370,15,100,20);
		
		btnImprimir.addActionListener(opImprimir);
		
		cont.add(panel);
		
		model.addRow(espacio);
		
		model.addRow(espacio);
		
		model.setValueAt("TOTALES", model.getRowCount()-1, 0);
		model.setValueAt(retunrNomina()+"",model.getRowCount()-1, 1);
		model.setValueAt(retunrPagoOnline()+"",model.getRowCount()-1, 2);
		model.setValueAt(retunrDiferenciaBancos()+"",model.getRowCount()-1, 3);
		model.setValueAt(retunrLista_apagar()+"",model.getRowCount()-1, 4);
		model.setValueAt(retunrDiferencia()+"",model.getRowCount()-1, 5);
		
		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("CHEQUE SUPER (1)", model.getRowCount()-1, 0);
		model.setValueAt(retunrNomina()-returnNominaEstablecimiento("IZACEL")-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA")+"",model.getRowCount()-1, 1);
		model.setValueAt(retunrPagoOnline()-returnBancosEstablecimiento("IZACEL")-returnBancosEstablecimiento("REFACCIONARIA")-returnBancosEstablecimiento("FERRETERIA")+"",model.getRowCount()-1, 2);
		model.setValueAt((retunrNomina()-returnNominaEstablecimiento("IZACEL")-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA"))-(retunrPagoOnline()-returnBancosEstablecimiento("IZACEL")-returnBancosEstablecimiento("REFACCIONARIA")-returnBancosEstablecimiento("FERRETERIA"))+"", model.getRowCount()-1, 3);
		model.setValueAt("CHEQUE SUPER (2)", model.getRowCount()-1, 4);
		model.setValueAt((retunrLista_apagar()-returnListaRayaEstablecimiento("IZACEL")-returnListaRayaEstablecimiento("REFACCIONARIA")-returnListaRayaEstablecimiento("FERRETERIA"))-((retunrNomina()-returnNominaEstablecimiento("IZACEL")-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA"))-(retunrPagoOnline()-returnBancosEstablecimiento("IZACEL")-returnBancosEstablecimiento("REFACCIONARIA")-returnBancosEstablecimiento("FERRETERIA"))), model.getRowCount()-1, 5);
		
		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("CHEQUE FERRE Y REFA (1)", model.getRowCount()-1, 0);
		model.setValueAt(retunrNomina()-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA")+"",model.getRowCount()-1, 1);
		model.setValueAt(returnBancosEstablecimiento("REFACCIONARIA")+returnBancosEstablecimiento("FERRETERIA")+"",model.getRowCount()-1, 2);
		model.setValueAt((retunrNomina()-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA"))-(returnBancosEstablecimiento("REFACCIONARIA")+returnBancosEstablecimiento("FERRETERIA")), model.getRowCount()-1, 3);
		model.setValueAt("CHEQUE FERRE Y REFA (2)", model.getRowCount()-1, 4);
		model.setValueAt(returnNominaEstablecimiento("REFACCIONARIA")+returnNominaEstablecimiento("FERRETERIA"), model.getRowCount()-1, 5);
		
//		model.addRow(cheque_1_super); 
//		model.addRow(espacio);
//		model.addRow(cheque_1_ferre);
//		model.addRow(espacio);
//		model.addRow(cheque_1_izacel);
//		model.addRow(espacio);
//		model.addRow(chequeABC);
//		model.addRow(espacio);
		
		tabla.getColumnModel().getColumn(0).setMinWidth(80);
		tabla.getColumnModel().getColumn(0).setMinWidth(80);
		
		tabla.getColumnModel().getColumn(1).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setMinWidth(70);
		
		tabla.getColumnModel().getColumn(2).setMinWidth(60);
		tabla.getColumnModel().getColumn(2).setMinWidth(60);
		
		tabla.getColumnModel().getColumn(3).setMinWidth(70);
		tabla.getColumnModel().getColumn(3).setMinWidth(70);
		
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		
		tabla.getColumnModel().getColumn(5).setMinWidth(50);
		tabla.getColumnModel().getColumn(5).setMinWidth(50);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				lbl.setFont(new java.awt.Font("",0,7));
				if(row == 0){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(214,214,214));
				}
				return lbl; 
			} 
		}; 
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(1).setCellRenderer(render);
		tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(4).setCellRenderer(render);
		tabla.getColumnModel().getColumn(5).setCellRenderer(render); 
		
		this.setSize(505,620);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
	}
	
	public float retunrNomina(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(i!=0){
				if(model.getValueAt(i,1).toString() != ""){
					valor = Float.parseFloat(valor+"") + Float.parseFloat(model.getValueAt(i,1)+"");
				}
			}
							
		}
		return valor;
	}
	
	public float retunrPagoOnline(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(i!=0){
				if(model.getValueAt(i,2).toString() != ""){
					valor = Float.parseFloat(valor+"") + Float.parseFloat(model.getValueAt(i,2)+"");
				}
			}
							
		}
		return valor;
	}
	public float retunrDiferenciaBancos(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(i!=0){
				if(model.getValueAt(i,3).toString() != ""){
					valor = Float.parseFloat(valor+"") + Float.parseFloat(model.getValueAt(i,3)+"");
				}
			}
							
		}
		return valor;
	}
	
	public float retunrLista_apagar(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(i!=0){
				if(model.getValueAt(i,4).toString() != ""){
					valor = Float.parseFloat(valor+"") + Float.parseFloat(model.getValueAt(i,4)+"");
				}
			}
							
		}
		return valor;
	}
	
	public float retunrDiferencia(){
		float valor = 0;
		for(int i=0; i<Tabla.length; i++){
			if(i!=0){
				if(model.getValueAt(i,5).toString() != ""){
					valor = Float.parseFloat(valor+"") + Float.parseFloat(model.getValueAt(i,5)+"");
				}
			}
							
		}
		return valor;
	}
	
	public float returnNominaEstablecimiento(String Establecimiento){
		return new Obj_Nomina().getNominaIndividual(Establecimiento,folio);
		
	}
	
	public float returnBancosEstablecimiento(String Establecimiento){
		return new Obj_Nomina().getBancosIndividual(Establecimiento,folio);
		
	}
	
	public float returnListaRayaEstablecimiento(String Establecimiento){
		return new Obj_Nomina().getListaRayaIndividual(Establecimiento,folio);
		
	}
	
	ActionListener opImprimir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			MessageFormat encabezado = new MessageFormat("Totales De Cheque pag.[{0,number,integer}]");
			try {
				tabla.print(JTable.PrintMode.NORMAL, encabezado, null);
			} catch (java.awt.print.PrinterException e1) {
				JOptionPane.showMessageDialog(null, "No se encontro la impresora!","Aviso",JOptionPane.WARNING_MESSAGE);
			}
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(JOptionPane.showConfirmDialog(null, "¿Desea Guardar la Lista de Raya?") == 0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				new Guardar().setVisible(true);
			}else{
				return;
			}
		}
	};
	
	public class Guardar extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		public Guardar() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("- ..."));
			
			panel.add(barra).setBounds(20,25,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}

		class Hilo implements Runnable {
			@SuppressWarnings("rawtypes")
			public void run() {
				int total = model.getRowCount();
				Vector miVector = new Vector();
//				
//				if(){
//					
//				}
				
				
				
				
//				int total = model.getRowCount();
//				Vector miVector = new Vector();
//				
//				if(getFilas("exec sp_status_pre_listaraya") > 1){
//					panel.setBorder(BorderFactory.createTitledBorder("Actulizando Pre-Lista de raya..."));
//					if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
//						for(int i=0; i<model.getRowCount(); i++){
//							for(int j=0; j<model.getColumnCount(); j++){
//								miVector.add(model.getValueAt(i,j));
//							}
//							Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
//											
//							
//							lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
//							int foli_emple = Integer.parseInt(miVector.get(1)+"");
//							Obj_Revision_Lista_Raya lis_foli = new Obj_Revision_Lista_Raya().buscar_folio(foli_emple);
//							lis_raya.setFolio_empleado(foli_emple);
//							lis_raya.setA_pagar(Float.parseFloat(miVector.get(21)+""));
//							if(miVector.get(22)!= null){
//								lis_raya.setObservasion_i(miVector.get(22)+"");
//							}else{
//								lis_raya.setObservasion_i("");
//							}
//							if(miVector.get(23) != null){
//								lis_raya.setObservasion_ii(miVector.get(23)+"");
//							}else{
//								lis_raya.setObservasion_ii("");
//							}
//							
//							Obj_Revision_Lista_Raya existe = new Obj_Revision_Lista_Raya().buscarExis(foli_emple);
//							
//							if(existe.getFolio_empleado() == foli_emple){
//								lis_raya.actualizar(lis_foli.getFolio());
//							}else{
//								lis_raya.guardar();
//							}
//							
//							
//							miVector.clear();
//							
//							int porcent = (i*100)/total;
//							barra.setValue(porcent+1);
//							try {
//								Thread.sleep(0);
//							} catch (InterruptedException e) {
//								e.printStackTrace();	
//							}
//						}
//						JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
//						dispose();
//					}else{
//						dispose();
//						return;
//					}
//					
//				}else{
//					panel.setBorder(BorderFactory.createTitledBorder("Guardando Pre-Lista de raya..."));
//					for(int i=0; i<model.getRowCount(); i++){
//						for(int j=0; j<model.getColumnCount(); j++){
//							miVector.add(model.getValueAt(i,j));
//						}
//						Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
//						
//						lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
//						lis_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+""));
//						lis_raya.setA_pagar(Float.parseFloat(miVector.get(21)+""));
//						
//						if(miVector.get(22)!= null){
//							lis_raya.setObservasion_i(miVector.get(22)+"");
//						}else{
//							lis_raya.setObservasion_i("");
//						}
//						if(miVector.get(23) != null){
//							lis_raya.setObservasion_ii(miVector.get(23)+"");
//						}else{
//							lis_raya.setObservasion_ii("");
//						}
//					
//						lis_raya.guardar();
//						
//						miVector.clear();
//						
//						int porcent = (i*100)/total;
//						barra.setValue(porcent+1);
//						try {
//							Thread.sleep(0);
//						} catch (InterruptedException e) {
//							e.printStackTrace();	
//						}
//					}
//					JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
//					dispose();
//				}
			}
		}
	}
	
	
	public static void main (String arg []){
		new Cat_Nomina().setVisible(true);
	}

}
