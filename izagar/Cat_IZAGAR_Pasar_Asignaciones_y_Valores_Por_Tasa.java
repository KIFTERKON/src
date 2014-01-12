package izagar;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import objetos.Obj_IZAGAR_Asignaciones_Liquidadas;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_IZAGAR_Pasar_Asignaciones_y_Valores_Por_Tasa extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Object[][] MatrizFiltro ;
	Object[][] MatrizFiltrotasas ;
	
	Object[][] getTablaFiltro = getTablaFiltro();
	DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
            new String[]{"Asignacion", "F. Cajero(a)","Nombre Cajera(o)","Total","Cod Estab","Establecimiento","Fecha de Asignacion","Fecha de Liquidacion"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 6 : return false;
        	 	case 7 : return false;
        	 	
        	 	} 				
 			return false;
 		}
	};
	
	JTable tablaFiltro = new JTable(modeloFiltro);
    JScrollPane scroll = new JScrollPane(tablaFiltro);
    
    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
    JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
     
	Object[][] getTablaAsignada = getTablavalores_por_tasa();
	DefaultTableModel modelotasas = new DefaultTableModel(getTablaAsignada,
            new String[]{"Asignacion", "Valor_Por_Tasa","Importe","Iva","Ieps"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
        	 } 				
 			return false;
 		}
	};
	
    JTable tablatasas = new JTable(modelotasas);
    JScrollPane scrollAsignado = new JScrollPane(tablatasas);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltroAsignado;
	
	JTextField txtFolio_Asignado = new JTextField();
	JTextField txtNombre_Completo_Asignado = new JTextField();
	
	JButton btnAgregar = new JButton("Click Aqui Para Continuar");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cat_IZAGAR_Pasar_Asignaciones_y_Valores_Por_Tasa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		setTitle("Traspaso de Asignaciones Liquidadas");
		campo.setBorder(BorderFactory.createTitledBorder("Asignaciones Liquidadas"));
		
		trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		trsfiltroAsignado = new TableRowSorter(modelotasas); 
		tablatasas.setRowSorter(trsfiltroAsignado); 
		
		campo.add(scroll).setBounds(15,43,890,280);
		campo.add(btnAgregar).setBounds(15,330,200,20);
		campo.add(scrollAsignado).setBounds(15,355,890,250);
		
		cont.add(campo);
		copiavalores_por_tasa_temp();
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(70);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(70);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(70);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(70);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(230);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(230);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(80);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(80);
		tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(60);
		tablaFiltro.getColumnModel().getColumn(4).setMinWidth(60);
		tablaFiltro.getColumnModel().getColumn(5).setMaxWidth(120);
		tablaFiltro.getColumnModel().getColumn(5).setMinWidth(120);
		tablaFiltro.getColumnModel().getColumn(6).setMaxWidth(120);
		tablaFiltro.getColumnModel().getColumn(6).setMinWidth(120);
		tablaFiltro.getColumnModel().getColumn(7).setMaxWidth(120);
		tablaFiltro.getColumnModel().getColumn(7).setMinWidth(120);

		
		tablatasas.getColumnModel().getColumn(0).setMaxWidth(90);
		tablatasas.getColumnModel().getColumn(0).setMinWidth(90);
		tablatasas.getColumnModel().getColumn(1).setMaxWidth(300);
		tablatasas.getColumnModel().getColumn(1).setMinWidth(300);
		tablatasas.getColumnModel().getColumn(2).setMaxWidth(90);
		tablatasas.getColumnModel().getColumn(2).setMinWidth(90);
		tablatasas.getColumnModel().getColumn(3).setMaxWidth(150);
		tablatasas.getColumnModel().getColumn(3).setMinWidth(150);
		tablatasas.getColumnModel().getColumn(4).setMaxWidth(150);
		tablatasas.getColumnModel().getColumn(4).setMinWidth(150);
	


		
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
				
				switch(column){
					case 0: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 1: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 2:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 3:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 4:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 5:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 6:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 7:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 8:
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
				
				}
				return componente;
			} 
		}; 
		tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(4).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(5).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(6).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(7).setCellRenderer(render);

		
//		tablatasas.getColumnModel().getColumn(0).setCellRenderer(render); 
//		tablatasas.getColumnModel().getColumn(1).setCellRenderer(render); 
//		tablatasas.getColumnModel().getColumn(2).setCellRenderer(render);
//		tablatasas.getColumnModel().getColumn(3).setCellRenderer(render);
//		tablatasas.getColumnModel().getColumn(4).setCellRenderer(render);
			
		btnAgregar.addActionListener(opAgregar);
		
		
		setSize(920,650);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	ActionListener opAgregar = new ActionListener() {
	
		public void actionPerformed(ActionEvent arg0) {
			
			if(tablaFiltro.isEditing()){
	 			tablaFiltro.getCellEditor().stopCellEditing();
			}
			
			Obj_IZAGAR_Asignaciones_Liquidadas guardar_liquidacion = new Obj_IZAGAR_Asignaciones_Liquidadas();

						if(guardar_liquidacion.guardar_liquidaciones(tabla_guardar())){
							
							if(guardar_liquidacion.guardar_valores_por_tasa(tabla_guardartasas())){
								  dispose();
						  		new Cat_IZAGAR_Asignaciones().setVisible(true);
							}else{	JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla valores por tasa","Error",JOptionPane.ERROR_MESSAGE);
							
							return;
								
							}
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla liquidaciones","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
		}
	};
	
	private Object[][] tabla_guardar(){

		Object[][] matriz = new Object[tablaFiltro.getRowCount()][8];
		for(int i=0; i<tablaFiltro.getRowCount(); i++){
			
				matriz[i][0] = modeloFiltro.getValueAt(i,0).toString().trim();
				matriz[i][1] = modeloFiltro.getValueAt(i,1).toString().trim();
				matriz[i][2] = modeloFiltro.getValueAt(i,2).toString().trim();
				matriz[i][3] = modeloFiltro.getValueAt(i,3).toString().trim();
				matriz[i][4] = modeloFiltro.getValueAt(i,4).toString().trim();
				matriz[i][5] = modeloFiltro.getValueAt(i,5).toString().trim();
				matriz[i][6] = modeloFiltro.getValueAt(i,6).toString().trim();
				matriz[i][7] = modeloFiltro.getValueAt(i,7).toString().trim();	
				
		}
		return matriz;
	}
	private Object[][] tabla_guardartasas(){

		Object[][] matriz = new Object[tablatasas.getRowCount()][5];
		for(int i=0; i<tablatasas.getRowCount(); i++){
			
				matriz[i][0] = modelotasas.getValueAt(i,0).toString().trim();
				matriz[i][1] = modelotasas.getValueAt(i,1).toString().trim();
				matriz[i][2] = modelotasas.getValueAt(i,2).toString().trim();
				matriz[i][3] = modelotasas.getValueAt(i,3).toString().trim();
				matriz[i][4] = modelotasas.getValueAt(i,4).toString().trim();
		}
		return matriz;
	}
	
	/////////////////EMPIEZAN LAS CONECCIONES A LA BASE DE DATOS
	public void copiavalores_por_tasa_temp(){
		String todos = "exec sp_Reporte_IZAGAR_de_Valores_por_Tasa_por_asignacion ";
		PreparedStatement pstmt = null;
		Connection con = new Connexion().conexionDB_DOS();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(todos);
		
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	 	public Object[][] getTablaFiltro(){
		String todos = "exec sp_Reporte_IZAGAR_de_Asignaciones_del_dia";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexionDB_DOS().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilasIZAGAR(todos)][8];
			int i=0;
			while(rs.next()){
				MatrizFiltro[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltro[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltro[i][5] = "   "+rs.getString(6).trim();
				MatrizFiltro[i][6] = "   "+rs.getString(7).trim()+"";
				MatrizFiltro[i][7] = "   "+rs.getString(8).trim()+"";
					i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
   	
   	public Object[][] getTablavalores_por_tasa(){
		String todos = "select * from valores_por_tasa_temp ";
		Statement s;
		ResultSet rs;

		try {
			s = new Connexion().conexionDB_DOS().createStatement();
	
			rs = s.executeQuery(todos);
			
			MatrizFiltrotasas = new Object[getFilasSCOI(todos)][5];
			int i=0;
			while(rs.next()){
				MatrizFiltrotasas[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltrotasas[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltrotasas[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltrotasas[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltrotasas[i][4] = "   "+rs.getString(5).trim();

								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltrotasas; 
	}

   	public int getFilasIZAGAR(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			stmt = new Connexion().conexionDB_DOS().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	public int getFilasSCOI(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			stmt = new Connexion().conexionDB_DOS().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_IZAGAR_Pasar_Asignaciones_y_Valores_Por_Tasa().setVisible(true);
		}catch(Exception e){
			
		}
	}
}