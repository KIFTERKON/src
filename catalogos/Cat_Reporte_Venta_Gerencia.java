package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Establecimiento;

import SQL.Connexion;

import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class Cat_Reporte_Venta_Gerencia extends JFrame {

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Object[][] Matriz;
//	Object[][] Tabla = getTabla();
	
	DefaultTableModel model = new DefaultTableModel(null,
			new String[]{"Venta","Departamento", "Unidades", "Venta Total", "Costo Total",
			"Markup", "Establecimiento", "Fecha", "Día", "Semana del Año",
			"Mes", "Año", "Semana" }){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.Integer.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	
	    	java.lang.Float.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class,  
	    	java.lang.String.class,  
	    	java.lang.Integer.class, 
	    	
	    	java.lang.String.class, 
	    	java.lang.Integer.class, 
	    	java.lang.String.class
	    	
         };
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0  : return false; 
        	 	case 1  : return false; 
        	 	case 3  : return false; 
        	 	case 4  : return false; 
        	 	
        	 	case 5  : return false;
        	 	case 6  : return false; 
        	 	case 7  : return false; 
        	 	case 8  : return false; 
        	 	case 9  : return false; 
        	 	
        	 	case 10 : return false; 
        	 	case 11 : return false; 
        	 	case 12 : return false; 
        	 	
        	 }
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JDateChooser txtCalendario =  new JDateChooser();
	JDateChooser txtCalendario1 = new JDateChooser();
	
	String lista[] = new Obj_Establecimiento().Combo_Establecimiento_Entysal();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(lista);
	
	JButton btnBuscar = new JButton("Buscar");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Reporte_Venta_Gerencia(){
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);
		
		panel.add(new JLabel("De:")).setBounds(30,10,40,20);
		panel.add(txtCalendario).setBounds(50,10,150,20);
		
		panel.add(new JLabel("Al:")).setBounds(250,10,40,20);
		panel.add(txtCalendario1).setBounds(270,10,150,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(450,10,100,20);
		panel.add(cmbEstablecimientos).setBounds(550,10,200,20);
		
		panel.add(btnBuscar).setBounds(770,10,100,20);
		
		panel.add(scroll).setBounds(10,48,1250,650);
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableCellRenderer render = new TableCellRenderer()	{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(177,177,177));
					
				} 
				return lbl; 
			} 
		}; 
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		tabla.getColumnModel().getColumn(6).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(7).setCellRenderer(render);
		tabla.getColumnModel().getColumn(8).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(9).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(10).setCellRenderer(render);
		tabla.getColumnModel().getColumn(11).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(12).setCellRenderer(render);

		cmbEstablecimientos.addActionListener(opFiltro);
		btnBuscar.addActionListener(opBuscar);
		
		cont.add(panel);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	ActionListener opBuscar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			while(tabla.getRowCount()>0){
				model.removeRow(0);
			}
			fillTabla(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario1.getDate()),cmbEstablecimientos.getSelectedItem().toString());
		}
	};
	
	public void fillTabla(String calendario, String calendario1, String establecimiento){
		String datos ="exec sp_prueba_fill_tabla '"+ calendario+"','"+calendario1+"','"+establecimiento+"','','',''";
		System.out.println(datos);
		Statement s;
		ResultSet rs;
		try {			
			s = new Connexion().conexionDB_DOS().createStatement();
			rs = s.executeQuery(datos);
			Object[] MatrizArray = new Object[13];
			while(rs.next()){
				MatrizArray[0] = rs.getString(1);
				MatrizArray[1] = rs.getString(2).trim();
				MatrizArray[2] = rs.getInt(3);
				MatrizArray[3] = rs.getFloat(4);
				MatrizArray[4] = rs.getFloat(5);
				MatrizArray[5] = rs.getFloat(6);
				MatrizArray[6] = rs.getString(7).trim().toUpperCase();
				MatrizArray[7] = rs.getString(8).trim();
				MatrizArray[8] = rs.getString(9).trim();
				MatrizArray[9] = rs.getInt(10);
				MatrizArray[10] = rs.getString(11).trim();
				MatrizArray[11] = rs.getInt(12);
				MatrizArray[12] = rs.getString(13).trim();
				model.addRow(MatrizArray);
			}
				
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
				
	}
	
	ActionListener opFiltro = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem().toString().toUpperCase().trim(), 6));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("",6));
			}
		}
	};
	
//	private Object[][] getTabla(){
//		String datos = "exec Sp_Prueba_Reporte_Venta_Gerencia";
////		String datos ="exec sp_prueba_nula";
//		
//		Statement s;
//		ResultSet rs;
//		try {			
//			s = new Connexion().conexionDB_DOS().createStatement();
//			rs = s.executeQuery(datos);
//			Matriz = new Object[getFilas(datos)][13];
//			int i=0;
//			while(rs.next()){
//				
////				Matriz[i][0] ="";
////				Matriz[i][1] ="";
////				Matriz[i][2] ="";
////				Matriz[i][3] ="";
////				Matriz[i][4] ="";
////				Matriz[i][5] ="";
////				Matriz[i][6] ="";
////				Matriz[i][7] ="";
////				Matriz[i][8] ="";
////				Matriz[i][9] ="";
////				Matriz[i][10] ="";
////				Matriz[i][11] ="";
////				Matriz[i][12] ="";
//				
////				Matriz[i][0] = rs.getString(1);
////				Matriz[i][1] = rs.getString(2).trim();
////				Matriz[i][2] = rs.getInt(3);
////				Matriz[i][3] = rs.getFloat(4);
////				Matriz[i][4] = rs.getFloat(5);
////				Matriz[i][5] = rs.getFloat(6);
////				Matriz[i][6] = rs.getString(7).trim().toUpperCase();
////				Matriz[i][7] = rs.getString(8).trim();
////				Matriz[i][8] = rs.getString(9).trim();
////				Matriz[i][9] = rs.getInt(10);
////				Matriz[i][10] = rs.getString(11).trim();
////				Matriz[i][11] = rs.getInt(12);
////				Matriz[i][12] = rs.getString(13).trim();
//				
//				i++;
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		return Matriz; 
//	}
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = new Connexion().conexionDB_DOS().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
}
