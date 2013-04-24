package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
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
	
	int folio = new Obj_Nomina().MaxListaRaya();
	String[] espacio = {"","","","","",""};
	String[] totales = new Obj_Nomina().getTotales(folio);
	String[] cheque_1_super = new Obj_Nomina().getCheque1(folio);
	String[] cheque_1_ferre = new Obj_Nomina().getCheque1_ferre(folio);
	String[] cheque_1_izacel = new Obj_Nomina().getCheque1_izacel(folio);
	String[] chequeABC = new Obj_Nomina().getChequeABC(folio);
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Accounting.png"));
		this.setTitle("Totales de Cheque");
		
		panel.setBorder(BorderFactory.createTitledBorder("Totales De Cheque"));
		
		panel.add(scroll).setBounds(15,50,460,503);
		panel.add(btnImprimir).setBounds(370,15,100,20);
		
		btnImprimir.addActionListener(opImprimir);
		
		cont.add(panel);
		
		model.addRow(espacio);
		model.addRow(totales);  
		model.addRow(espacio); 
		model.addRow(cheque_1_super); 
		model.addRow(espacio);
		model.addRow(cheque_1_ferre);
		model.addRow(espacio);
		model.addRow(cheque_1_izacel);
		model.addRow(espacio);
		model.addRow(chequeABC);
		model.addRow(espacio);
		
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
	public static void main (String arg []){
		new Cat_Nomina().setVisible(true);
	}

}
