package izagar;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Departamento;


import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_IZAGAR_Consulta_de_Netos_nomina extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	JTextField txtFolionomina = new JTextField();
 	JTextField txtNombre_Completo = new JTextField();
	Object[][] MatrizFiltro ;
	Object[][] Matriz_nomina_neto_bms ;
	Object[][] getTablaFiltro = getTablaFiltro();
	JButton btnAgregar = new JButton("Click Aqui Para Continuar");
	JButton btnconsultanomina = new JButton("Buscar");
	
	
	DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
            new String[]{"Folio E", "Nombre Empleado", "Establecimiento","" }
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class
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
        	 	case 3 : return true;
        	 	} 				
 			return false;
 		}
	};
	
	JTable tablaFiltro = new JTable(modeloFiltro);
    JScrollPane scroll = new JScrollPane(tablaFiltro);
    
    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
	Object[][] getTablaNomina = getTablanetosnominaBMS();
	DefaultTableModel modelonomina = new DefaultTableModel(getTablaNomina,
            new String[]{"Nomina", "Empleado","Neto",""}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class
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
        	 	case 3 : return true;
        	 } 				
 			return false;
 		}
	};
	
    JTable tablanomina = new JTable(modelonomina);
    JScrollPane scrollAsignado = new JScrollPane(tablanomina);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltroAsignado;
	@SuppressWarnings({ "rawtypes", "unchecked" })

	public Cat_IZAGAR_Consulta_de_Netos_nomina() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		setTitle("Traspaso de Netos de Nomina a Bancos");
		campo.setBorder(BorderFactory.createTitledBorder("Traspaso de Netos a Bancos"));
		
		trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		trsfiltroAsignado = new TableRowSorter(modelonomina); 
		tablanomina.setRowSorter(trsfiltroAsignado); 
		

		setSize(1024,650);
		setResizable(false);
		setLocationRelativeTo(null);
		campo.add(scroll).setBounds(15,70,480,300);
		campo.add(scrollAsignado).setBounds(510,70,480,300);
		
		campo.add(btnconsultanomina).setBounds(180,30,70,20);
		campo.add(btnAgregar).setBounds(15,530,200,20);
		
		
		campo.add(new JLabel("Folio Nomina:")).setBounds(15,30,80,20);
		campo.add(txtFolionomina).setBounds(80,30,100,20);
		txtFolionomina.addKeyListener(valida_numerico);
		
		cont.add(campo);
		
		
		
//		copiavalores_por_tasa_temp();
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(45);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(45);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(270);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(270);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(105);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(105);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
	
		tablanomina.getColumnModel().getColumn(0).setMaxWidth(45);
		tablanomina.getColumnModel().getColumn(0).setMinWidth(45);
		tablanomina.getColumnModel().getColumn(1).setMaxWidth(270);
		tablanomina.getColumnModel().getColumn(1).setMinWidth(270);
		tablanomina.getColumnModel().getColumn(2).setMaxWidth(105);
		tablanomina.getColumnModel().getColumn(2).setMinWidth(105);
		tablanomina.getColumnModel().getColumn(3).setMaxWidth(40);
		tablanomina.getColumnModel().getColumn(3).setMinWidth(40);
	
	


		
//		TableCellRenderer render = new TableCellRenderer() { 
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
//			boolean hasFocus, int row, int column) { 
//				
//				Component componente = null;
//				
//				switch(column){
//					case 0: 
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 1: 
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 2:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 3:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 4:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 5:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 6:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 7:
//						componente = new JLabel(value == null? "": value.toString());
//						if(row %2 == 0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
//						break;
//					case 8:
//						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
//						if(row%2==0){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(177,177,177));	
//						}
//						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						if(table.getSelectedRow() == row){
//							((JComponent) componente).setOpaque(true); 
//							componente.setBackground(new java.awt.Color(186,143,73));
//						}
//						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
//						break;
//				
//				}
//				return componente;
//			} 
//		}; 
//		tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
//		tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
//		tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
//		tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);



		
//		tablatasas.getColumnModel().getColumn(0).setCellRenderer(render); 
//		tablatasas.getColumnModel().getColumn(1).setCellRenderer(render); 
//		tablatasas.getColumnModel().getColumn(2).setCellRenderer(render);
//		tablatasas.getColumnModel().getColumn(3).setCellRenderer(render);

//		btnAgregar.addActionListener(opAgregar);
		
		

	}
	
//	ActionListener opAgregar = new ActionListener() {
//	
//		public void actionPerformed(ActionEvent arg0) {
//			
//			if(tablaFiltro.isEditing()){
//	 			tablaFiltro.getCellEditor().stopCellEditing();
//			}
//			
//			Obj_IZAGAR_Asignaciones_Liquidadas guardar_liquidacion = new Obj_IZAGAR_Asignaciones_Liquidadas();
//
//						if(guardar_liquidacion.guardar_liquidaciones(tabla_guardar())){
//							
//							if(guardar_liquidacion.guardar_valores_por_tasa(tabla_guardartasas())){
//								  dispose();
//						  		new Cat_IZAGAR_Asignaciones().setVisible(true);
//							}else{	JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla valores por tasa","Error",JOptionPane.ERROR_MESSAGE);
//							
//							return;
//								
//							}
//						}else{
//							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla liquidaciones","Error",JOptionPane.ERROR_MESSAGE);
//							return;
//						}
//		}
//	};
	
//	private Object[][] tabla_guardar(){
//
//		Object[][] matriz = new Object[tablaFiltro.getRowCount()][8];
//		for(int i=0; i<tablaFiltro.getRowCount(); i++){
//			
//				matriz[i][0] = modeloFiltro.getValueAt(i,0).toString().trim();
//				matriz[i][1] = modeloFiltro.getValueAt(i,1).toString().trim();
//				matriz[i][2] = modeloFiltro.getValueAt(i,2).toString().trim();
//				matriz[i][3] = modeloFiltro.getValueAt(i,3).toString().trim();
//
//				
//		}
//		return matriz;
//	}
//	private Object[][] tabla_guardartasas(){
//
//		Object[][] matriz = new Object[tablanomina.getRowCount()][5];
//		for(int i=0; i<tablanomina.getRowCount(); i++){
//			
//				matriz[i][0] = modelonomina.getValueAt(i,0).toString().trim();
//				matriz[i][1] = modelonomina.getValueAt(i,1).toString().trim();
//				matriz[i][2] = modelonomina.getValueAt(i,2).toString().trim();
//				matriz[i][3] = modelonomina.getValueAt(i,3).toString().trim();
//
//		}
//		return matriz;
//	}
//	
	/////////////////EMPIEZAN LAS CONECCIONES A LA BASE DE DATOS
//	public void copiavalores_por_tasa_temp(){
//		String todos = " ";
//		PreparedStatement pstmt = null;
//		Connection con = new Connexion().conexion();
//		try {
//			con.setAutoCommit(false);
//			pstmt = con.prepareStatement(todos);
//		
//			pstmt.executeUpdate();
//			con.commit();
//		} catch (Exception e) {
//			System.out.println("SQLException: "+e.getMessage());
//			if(con != null){
//				try{
//					System.out.println("La transacción ha sido abortada");
//					con.rollback();
//				}catch(SQLException ex){
//					System.out.println(ex.getMessage());
//				}
//			}
//		}finally{
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		
//
//	}
	 	public Object[][] getTablaFiltro(){
		String todos = "exec sp_select_empleados_scoi_traspaso_depositos_bancos_de_nomina";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilasSCOI(todos)][4];
			int i=0;
			while(rs.next()){
				MatrizFiltro[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = Boolean.valueOf(rs.getString(4).trim());
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
	 	public int getFilasSCOI(String qry){
			int filas=0;
			Statement stmt = null;
			try {stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){filas++;}
			} catch (SQLException e1) {	e1.printStackTrace();}
			return filas;
			}	
   	
   	public Object[][] getTablanetosnominaBMS(){
		String todos = "exec sp_Reporte_IZAGAR_de_Netos_por_nomina ";
		Statement s;
		ResultSet rs;

		try {
			s = new Connexion().conexionDB_DOS().createStatement();
			rs = s.executeQuery(todos);
			Matriz_nomina_neto_bms = new Object[getFilasIZAGAR(todos)][4];
			int i=0;
			while(rs.next()){
				Matriz_nomina_neto_bms[i][0] = "   "+rs.getString(1).trim();
				Matriz_nomina_neto_bms[i][1] = "   "+rs.getString(2).trim();
				Matriz_nomina_neto_bms[i][2] = "   "+rs.getString(3).trim();
				Matriz_nomina_neto_bms[i][3] = Boolean.valueOf(rs.getString(4).trim());
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz_nomina_neto_bms; 
	}

   	public int getFilasIZAGAR(String qry){
		int filas=0;
		Statement stmt = null;
		try {stmt = new Connexion().conexionDB_DOS().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){filas++;}
		} catch (SQLException e1) {	e1.printStackTrace();}
		return filas;
	}
   	
	KeyListener valida_numerico = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();

		   if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }			
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolionomina.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try {
					
					getTablanetosnominaBMS();
					
					
					
//					Obj_Departamento departamento = new Obj_Departamento().buscar(Integer.parseInt(txtFolio.getText()));
//					if(departamento.getFolio() != 0){
						
//						txtFolio.setText(departamento.getFolio()+"");
//						txtDepartamento.setText(departamento.getDepartamento()+"");
//						txtAbreviatura.setText(departamento.getAbreviatura()+"");
//						if(departamento.isStatus() == true){chbStatus.setSelected(true);}
//						else{chbStatus.setSelected(false);}
//						btnNuevo.setEnabled(false);
//						btnEditar.setEnabled(true);
//						panelEnabledFalse();
//						txtFolio.setEditable(true);
//						txtFolio.requestFocus();
						
//					} else{
//						JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
//						return;
//					}
				
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 			
			}
		}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_IZAGAR_Consulta_de_Netos_nomina().setVisible(true);
		}catch(Exception e){
			
		}
	}
}