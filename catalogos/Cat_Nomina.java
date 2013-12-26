package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
public class Cat_Nomina extends Cat_Root {
	
	public JButton btn_imprimir = new JButton(new ImageIcon("Iconos/print_icon&16.png"));
	
	public int folio = new Obj_Nomina().MaxListaRaya();
	
	String[] espacio = {"","","","","",""};
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cat_nomina_icon&16.png"));
		this.setTitle("Totales de Cheque");
		
		this.txtFolio.setVisible(false);
		this.txtNombre_Completo.setVisible(false);
		this.btn_refrescar.setVisible(false);
		
		panel.add(scroll).setBounds(15,50,460,503);
		
		this.menu_toolbar.add(btn_imprimir);
			this.btn_imprimir.setToolTipText("Imprimir");
		
		btn_imprimir.addActionListener(opImprimir);
		this.btn_guardar.addActionListener(opGuardar);
		
		cont.add(panel);
		
		model.addRow(espacio);
		
		model.addRow(espacio);
		
		model.setValueAt("TOTALES", model.getRowCount()-1, 0);
		model.setValueAt("$  "+retunrNomina()+"",model.getRowCount()-1, 1);
		model.setValueAt("$  "+retunrPagoOnline()+"",model.getRowCount()-1, 2);
		model.setValueAt("$  "+retunrDiferenciaBancos()+"",model.getRowCount()-1, 3);
		model.setValueAt("$  "+retunrLista_apagar()+"",model.getRowCount()-1, 4);
		model.setValueAt("$  "+retunrDiferencia()+"",model.getRowCount()-1, 5);
		
		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("CHEQUE SUPER (1)", model.getRowCount()-1, 0);
		model.setValueAt("$  "+(retunrNomina()-returnNominaEstablecimiento("IZACEL")-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA")),model.getRowCount()-1, 1);
		model.setValueAt("$  "+(retunrPagoOnline()-returnBancosEstablecimiento("IZACEL")-returnBancosEstablecimiento("REFACCIONARIA")-returnBancosEstablecimiento("FERRETERIA")),model.getRowCount()-1, 2);
		model.setValueAt("$  "+((retunrNomina()-returnNominaEstablecimiento("IZACEL")-returnNominaEstablecimiento("REFACCIONARIA")-returnNominaEstablecimiento("FERRETERIA"))-(retunrPagoOnline()-returnBancosEstablecimiento("IZACEL")-returnBancosEstablecimiento("REFACCIONARIA")-returnBancosEstablecimiento("FERRETERIA"))), model.getRowCount()-1, 3);
		model.setValueAt("CHEQUE SUPER´S + IZACEL (2)", model.getRowCount()-1, 4);
		model.setValueAt("$  "+(retunrDiferencia()-returnDiferenciaEstablecimiento("FERRETERIA")-returnDiferenciaEstablecimiento("REFACCIONARIA")) , model.getRowCount()-1, 5);
		
		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("CHEQUE FERRE Y REFA (1)", model.getRowCount()-1, 0);
		model.setValueAt("$  "+(returnNominaEstablecimiento("REFACCIONARIA")+returnNominaEstablecimiento("FERRETERIA")),model.getRowCount()-1, 1);
		model.setValueAt("$  "+(returnBancosEstablecimiento("REFACCIONARIA")+returnBancosEstablecimiento("FERRETERIA")),model.getRowCount()-1, 2);
		model.setValueAt("$  "+((returnNominaEstablecimiento("REFACCIONARIA")+returnNominaEstablecimiento("FERRETERIA"))-(returnBancosEstablecimiento("REFACCIONARIA")+returnBancosEstablecimiento("FERRETERIA"))), model.getRowCount()-1, 3);
		model.setValueAt("CHEQUE FERRE Y REFA (2)", model.getRowCount()-1, 4);
		model.setValueAt("$  "+(returnDiferenciaEstablecimiento("REFACCIONARIA")+returnDiferenciaEstablecimiento("FERRETERIA")), model.getRowCount()-1, 5);
		
		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("CHEQUE IZACEL (1)", model.getRowCount()-1, 0);
		model.setValueAt("$  "+returnNominaEstablecimiento("IZACEL"),model.getRowCount()-1, 1);
		model.setValueAt("$  "+returnBancosEstablecimiento("IZACEL"),model.getRowCount()-1, 2);
		model.setValueAt("$  "+(returnNominaEstablecimiento("IZACEL")-returnBancosEstablecimiento("IZACEL")),model.getRowCount()-1, 3);

		model.addRow(espacio); 
		model.addRow(espacio);
		
		model.setValueAt("TOTALES", model.getRowCount()-1, 0);
		model.setValueAt("$  "+retunrNomina()+"",model.getRowCount()-1, 1);
		model.setValueAt("$  "+retunrPagoOnline()+"",model.getRowCount()-1, 2);
		model.setValueAt("$  "+retunrDiferenciaBancos()+"",model.getRowCount()-1, 3);
		model.setValueAt("TOTAL",model.getRowCount()-1, 4);
		model.setValueAt("$  "+retunrDiferencia()+"",model.getRowCount()-1, 5);
				
		tabla.getColumnModel().getColumn(0).setMinWidth(90);
		tabla.getColumnModel().getColumn(0).setMinWidth(90);
		
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
	
	public float returnDiferenciaEstablecimiento(String Establecimiento){
		return new Obj_Nomina().getDiferenciaIndividual(Establecimiento,folio);
		
	}
	
	ActionListener opImprimir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Calendar c = new GregorianCalendar();
			
			String dia = c.get(Calendar.DATE)+"";
			String mes = (c.get(Calendar.MONTH)+1)+"";
			String anio = c.get(Calendar.YEAR)+"";
			MessageFormat encabezado = new MessageFormat("Totales De Cheque pag.[{0,number,integer}] FECHA: ["+dia+"-"+mes+"-"+anio+"]");
			
			try {
				tabla.print(JTable.PrintMode.NORMAL, encabezado, null);
			} catch (java.awt.print.PrinterException e1) {
				JOptionPane.showMessageDialog(null, "No se encontro la impresora!","Aviso",JOptionPane.WARNING_MESSAGE);
			}
		}
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			new Guardar().setVisible(true);
			
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
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void run() {
				Calendar c = new GregorianCalendar();
				
				String dia = c.get(Calendar.DATE)+"";
				String mes = (c.get(Calendar.MONTH)+1)+"";
				String anio = c.get(Calendar.YEAR)+"";
				
				int total = model.getRowCount();
				
				Vector miVector = new Vector();
				Obj_Nomina maximo = new Obj_Nomina();
				
				if(folio != maximo.returnMaximo()){
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							miVector.add(model.getValueAt(i,j));							
						}
						
						Obj_Nomina nomina = new Obj_Nomina();
							
						nomina.setNumero_listaraya(folio);
						nomina.setEstablecimiento(miVector.get(0).toString());
						nomina.setNomina(miVector.get(1).toString());
						nomina.setPago_linea(miVector.get(2).toString());
						nomina.setCheque_nomina(miVector.get(3).toString());
						nomina.setLista_raya(miVector.get(4).toString());
						nomina.setDiferencia(miVector.get(5).toString());
						nomina.setFecha(dia+"-"+mes+"-"+anio);
						nomina.Guardar();
							
						int porcent = (i*100)/total;
						barra.setValue(porcent+1);
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();	
						}
							
						miVector.clear();
					}
					JOptionPane.showMessageDialog(null, "La nomina se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
					dispose();
				}else{
					if(JOptionPane.showConfirmDialog(null, "La nomina ya existe, ¿desea actualizarla?") == 0){
						for(int i=0; i<model.getRowCount(); i++){
							for(int j=0; j<model.getColumnCount(); j++){
								miVector.add(model.getValueAt(i,j));							
							}
							
							Obj_Nomina nomina = new Obj_Nomina();
								
							nomina.setNomina(miVector.get(1).toString());
							nomina.setPago_linea(miVector.get(2).toString());
							nomina.setCheque_nomina(miVector.get(3).toString());
							nomina.setLista_raya(miVector.get(4).toString());
							nomina.setDiferencia(miVector.get(5).toString());
							nomina.setFecha(dia+"-"+mes+"-"+anio);
							
							nomina.Actualizar(miVector.get(0).toString(),folio);
								
							int porcent = (i*100)/total;
							barra.setValue(porcent+1);
							try {
								Thread.sleep(0);
							} catch (InterruptedException e) {
								e.printStackTrace();	
							}
								
							miVector.clear();
						}	
						JOptionPane.showMessageDialog(null, "La nomina se actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						dispose();
					}else{
						return;
					}
				}
			}
		}
	}
	
}
