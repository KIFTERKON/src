package izagar;


import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import catalogos.Cat_Bancos;

import Objetos_IZAGAR.Obj_IZAGAR_Netos_Nominas;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos  extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	Object[][] MatrizFiltro ;
	Object[][] Matriz_nomina_neto_bms ;
	Object[][] Matriz_Conciliados;
	JButton btnAgregar = new JButton("Traspaso Por Nombre");
	JButton btnAplicar = new JButton("Aplicar");

	
//TABLA PENDIENTES DE CONCILIAR SCOI-----------------------------------------------------------------------------------------
	DefaultTableModel modeloFiltro = new DefaultTableModel(null,
            new String[]{"Folio E", "Nombre Empleado SCOI", "Establecimiento","" }
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
    
  //TABLA NETOS NOMINA BM-----------------------------------------------------------------------------------------
	DefaultTableModel modelonomina = new DefaultTableModel(null,
            new String[]{"Nomina", "Nombre Empleado Nomina","Neto",""}
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
	
//TABLA CONCILIADOS------------------------------------------------------------------------------
	DefaultTableModel modeloconciliados= new DefaultTableModel(null,
            new String[]{"Folio Empleado", "Nombre Empleado Nomina","Neto","Establecimiento","Departamento",""}
			){
	
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class
                                    };
	    
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
        	 	case 5 : return true;
        	 } 				
 			return false;
 		}
	};
	
    JTable tablaconciliados = new JTable(modeloconciliados);
    JScrollPane scrollconciliados = new JScrollPane(tablaconciliados);
    @SuppressWarnings("rawtypes")
	private TableRowSorter trsconciliados;
	
	JLabel lblTablaSCOI = new JLabel("Tabla SCOI");
	JLabel lblTablaNomina = new JLabel("Tabla Nomina");
	JLabel lblTablaConciliados = new JLabel("Tabla Conciliados");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos(String folio_nomina) {
		
		lblTablaSCOI.setFont(new Font("arial", Font.BOLD, 16));	
		lblTablaNomina.setFont(new Font("arial", Font.BOLD, 16));	
		lblTablaConciliados.setFont(new Font("arial", Font.BOLD, 16));	
		
		lblTablaSCOI.setForeground(new java.awt.Color(0,57,163));
		lblTablaNomina.setForeground(new java.awt.Color(0,57,163));
		lblTablaConciliados.setForeground(new java.awt.Color(0,57,163));

		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		setTitle("Traspaso de Netos de Nomina a Bancos");
		campo.setBorder(BorderFactory.createTitledBorder("Traspaso de Netos a Bancos"));
		
/////////////////LLENADO DE TABLAS/////////////////////////////////////////////////////////////////////////////
//		limpiar tablanomina
		while(tablanomina.getRowCount()>0){	modelonomina.removeRow(0);	}
//		llenar arreglo desde funcion
		Object[][] getTablaNomina = getTablanetosnominaBMS(folio_nomina);
		Object[] fila = new Object[4];
//		 llenar tablanomina
         for(int i=0; i<getTablaNomina.length; i++){
                 fila[0] = getTablaNomina[i][0]+"";
                 fila[1] = getTablaNomina[i][1]+"";
                 fila[2] = getTablaNomina[i][2]+"";
                 fila[3] = getTablaNomina[i][3];
                 modelonomina.addRow(fila); }

        trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(45);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(45);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(230);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(230);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(105);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(105);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
		
		
		trsfiltroAsignado = new TableRowSorter(modelonomina ); 
		tablanomina.setRowSorter(trsfiltroAsignado);
		
		tablanomina.getColumnModel().getColumn(0).setMaxWidth(45);
		tablanomina.getColumnModel().getColumn(0).setMinWidth(45);
		tablanomina.getColumnModel().getColumn(1).setMaxWidth(230);
		tablanomina.getColumnModel().getColumn(1).setMinWidth(230);
		tablanomina.getColumnModel().getColumn(2).setMaxWidth(105);
		tablanomina.getColumnModel().getColumn(2).setMinWidth(105);
		tablanomina.getColumnModel().getColumn(3).setMaxWidth(40);
		tablanomina.getColumnModel().getColumn(3).setMinWidth(40);
		
				
		trsconciliados = new TableRowSorter(modeloconciliados ); 
		tablaconciliados.setRowSorter(trsconciliados);
		
		tablaconciliados.getColumnModel().getColumn(0).setMaxWidth(105);
		tablaconciliados.getColumnModel().getColumn(0).setMinWidth(105);
		tablaconciliados.getColumnModel().getColumn(1).setMaxWidth(300);
		tablaconciliados.getColumnModel().getColumn(1).setMinWidth(300);
		tablaconciliados.getColumnModel().getColumn(2).setMaxWidth(115);
		tablaconciliados.getColumnModel().getColumn(2).setMinWidth(115);
		tablaconciliados.getColumnModel().getColumn(3).setMaxWidth(115);
		tablaconciliados.getColumnModel().getColumn(3).setMinWidth(115);
		tablaconciliados.getColumnModel().getColumn(4).setMaxWidth(200);
		tablaconciliados.getColumnModel().getColumn(4).setMinWidth(200);
		tablaconciliados.getColumnModel().getColumn(5).setMaxWidth(40);
		tablaconciliados.getColumnModel().getColumn(5).setMinWidth(40);
		
		campo.add(lblTablaSCOI).setBounds(15, 45, 150, 35);
    	campo.add(scroll).setBounds(15,70,440,300);
    	campo.add(lblTablaNomina).setBounds(470, 45, 150, 35);
		campo.add(scrollAsignado).setBounds(470,70,440,300);
		campo.add(lblTablaConciliados).setBounds(15, 375, 150, 35);
		campo.add(scrollconciliados).setBounds(15,400,895,300);
	
     	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		campo.add(btnAgregar).setBounds(225,30,140,20);
		campo.add(btnAplicar).setBounds(375,30,80,20);
		
//		btnAplicar.setEnabled(false);
		
		cont.add(campo);
		setSize(935,735);
		setResizable(false);
		setLocationRelativeTo(null);
		btnAgregar.addActionListener(OpAgregar);
		btnAplicar.addActionListener(OpAplicar);
		
		guarda_auto_netos_nomina_po_empleado_temp(folio_nomina);

//		funcion para seleccionar solo un registro a la ves en la tabla de SCOI-----------------------------------------------------------
        tablaFiltro.addMouseListener(opTablaFiltroSeleccion);
//		funcion para seleccionar solo un registro a la ves en la tabla de NOMINA--------------------------------------------------------- 
        tablanomina.addMouseListener(opTablaNominaSeleccion);
//		funcion para seleccionar solo un registro a la ves en la tabla CONCILIADOS-------------------------------------------------------
        tablaconciliados.addMouseListener(opTablaConciliadosSeleccion);
	}
	
	MouseListener opTablaFiltroSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			int fila = tablaFiltro.getSelectedRow();
			int columna = tablaFiltro.getSelectedColumn();
			
			if(columna==3){
				for(int i=0; i<=tablaFiltro.getRowCount()-1; i++){
					tablaFiltro.setValueAt(false, i, 3);
				}
				tablaFiltro.setValueAt(true, fila, columna);
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};
	
	MouseListener opTablaNominaSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			int fila = tablanomina.getSelectedRow();
			int columna = tablanomina.getSelectedColumn();
			
			if(columna==3){
				for(int i=0; i<=tablanomina.getRowCount()-1; i++){
					tablanomina.setValueAt(false, i, 3);
				}
				tablanomina.setValueAt(true, fila, columna);
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};
	
	MouseListener opTablaConciliadosSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			int fila = tablaconciliados.getSelectedRow();
			int columna = tablaconciliados.getSelectedColumn();
			
			if(columna==5){
				for(int i=0; i<=tablaconciliados.getRowCount()-1; i++){
					tablaconciliados.setValueAt(false, i, 5);
				}
				tablaconciliados.setValueAt(true, fila, columna);
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};
	
  //GUARDADO AUTOMATICO DE LA NOMINA TECLEADA//  
  public void guarda_auto_netos_nomina_po_empleado_temp(String folio_nomina){
				
			if(tablaFiltro.isEditing()){
	 			tablaFiltro.getCellEditor().stopCellEditing();
			}
			
			Obj_IZAGAR_Netos_Nominas guardar_netos_nomina = new Obj_IZAGAR_Netos_Nominas();
							if(guardar_netos_nomina.guardar_netos_nominas_temp(tabla_guardar_nomina_temp())){
							System.out.println("se guardo AUTO");
							
							while(tablanomina.getRowCount()>0){
								modelonomina.removeRow(0); }
							preconciliacion_automatica( folio_nomina);
							
							
//							llenado tabla exportacion de bm completa despues de guardarlos en scoi
							Object[][] getTablaNomina = getTablanetosnomina_guardados_scoi(folio_nomina);
							Object[] fila = new Object[4];
//							 vuelve a llenar tablanomina desde el scoi
					         for(int i=0; i<getTablaNomina.length; i++){
					                 fila[0] = getTablaNomina[i][0]+"";
					                 fila[1] = getTablaNomina[i][1]+"";
					                 fila[2] = getTablaNomina[i][2]+"";
//					                 fila[3] = Boolean.valueOf(getTablaNomina[i][3].toString().trim());
					                 fila[3] = false;
					                 modelonomina.addRow(fila);}
					         
					        RefreshTablas(folio_nomina);

						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla liquidaciones","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
	};
	
	public void RefreshTablas(String folio_nom){
        
		while(tablaFiltro.getRowCount()>0){
			modeloFiltro.removeRow(0); }
		
		while(tablaconciliados.getRowCount()>0){
			modeloconciliados.removeRow(0); }
		
// 		llenado tabla conciliados
     	Object[][] getTablaconciliados = getTablaconciliados(folio_nom);
     	Object[] fila2 = new Object[5];
//		 llenar tabla conciliados
        for(int i=0; i<getTablaconciliados.length; i++){
                fila2[0] = getTablaconciliados[i][0]+"";
                fila2[1] = getTablaconciliados[i][1]+"";
                fila2[2] = getTablaconciliados[i][2]+"";
                fila2[3] = getTablaconciliados[i][3]+"";
                fila2[4] = getTablaconciliados[i][4]+"";
//                fila2[5] = Boolean.valueOf(getTablaNomina[i][5].toString().trim());
                modeloconciliados.addRow(fila2);} 
        
// 		llenado tabla faltantes de conciliar
     	Object[][] getTablaFiltro = getTablaempleadoscoi(folio_nom);
     	Object[] fila1 = new Object[4];
//		 llenar tabla empleado scoi
        for(int i=0; i<getTablaFiltro.length; i++){
                fila1[0] = getTablaFiltro[i][0]+"";
                fila1[1] = getTablaFiltro[i][1]+"";
                fila1[2] = getTablaFiltro[i][2]+"";
//                fila1[3] = Boolean.valueOf(getTablaNomina[i][3].toString().trim());
                fila1[3] = false;
                modeloFiltro.addRow(fila1);    }
        
        if(getTablaFiltro.length == 0){
        	btnAplicar.setEnabled(true);
        }
	}
	
	private Object[][] tabla_guardar_nomina_temp(){

		Object[][] matriz = new Object[tablanomina.getRowCount()][3];
		for(int i=0; i<tablanomina.getRowCount(); i++){
			
				matriz[i][0] = modelonomina.getValueAt(i,0).toString().trim();
				matriz[i][1] = modelonomina.getValueAt(i,1).toString().trim();
				matriz[i][2] = modelonomina.getValueAt(i,2).toString().trim();
		}
		return matriz;
	}
	
	
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
	
	
	

	
	/////////////////EMPIEZAN LAS CONECCIONES A LA BASE DE DATOS
	
	
	 	public Object[][] getTablaempleadoscoi(String folio_nomina){
		String todos = "exec IZAGAR_select_empleados_scoi_traspaso_depositos_bancos_de_nomina '"+folio_nomina+"'";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			System.out.println("Consulta Scoi empleados_preconcialiados:"+folio_nomina);
			
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
	 	
	 	
   	
   	public Object[][] getTablanetosnominaBMS(String folio_nomina){
   		
		String todos = "exec sp_Reporte_IZAGAR_de_Netos_por_nomina '" +folio_nomina+"'";
		Statement s;
		ResultSet rs2;

		try {
			System.out.println("coneccion bm con nomina:"+folio_nomina);
			
			s = new Connexion().conexionDB_DOS().createStatement();
			rs2 = s.executeQuery(todos);
			Matriz_nomina_neto_bms = new Object[getFilasIZAGAR(todos)][4];
			int i=0;
			while(rs2.next()){
				Matriz_nomina_neto_bms[i][0] = "   "+rs2.getString(1).trim();
				Matriz_nomina_neto_bms[i][1] = "   "+rs2.getString(2).trim();
				Matriz_nomina_neto_bms[i][2] = "   "+rs2.getString(3).trim();
//				Matriz_nomina_neto_bms[i][3] = Boolean.valueOf(rs2.getString(4).trim());
				Matriz_nomina_neto_bms[i][3] = false;
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
			ResultSet rs2 = stmt.executeQuery(qry);
			while(rs2.next()){filas++;}
		} catch (SQLException e1) {	e1.printStackTrace();}
		return filas;
	}
   	
  	
   	
   	public Object[][] getTablanetosnomina_guardados_scoi(String folio_nomina){
		String todos = "exec IZAGAR_select_empleados_netos_nomina_scoi_temp '"+folio_nomina+"'";
		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion().createStatement();
			rs = s.executeQuery(todos);
			System.out.println("Carga de netos a Tabla temp:"+folio_nomina);
			
			MatrizFiltro = new Object[getFilasSCOI_temp(todos)][4];
			int i=0;
			while(rs.next()){
				MatrizFiltro[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
//				MatrizFiltro[i][3] = Boolean.valueOf(rs.getString(4).trim());
				MatrizFiltro[i][3] = false;
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
	 	public int getFilasSCOI_temp(String qry){
			int filas=0;
			Statement stmt = null;
			try {stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){filas++;}
			} catch (SQLException e1) {	e1.printStackTrace();}
			return filas;
			}
	 	
/////////DATOS TABLA CONCILIADOS
	   	public Object[][] getTablaconciliados(String folio_nomina){
	   		
			String todos = "exec IZAGAR_select_empleados_scoi_pre_conciliados '" +folio_nomina+"'";
			Statement s;
			ResultSet rs;

			try {
				System.out.println("carga de conciliados :"+folio_nomina);
				
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				Matriz_Conciliados = new Object[getFilasConciliados(todos)][6];
				int i=0;
				while(rs.next()){
					Matriz_Conciliados[i][0] = "   "+rs.getString(1).trim();
					Matriz_Conciliados[i][1] = "   "+rs.getString(2).trim();
					Matriz_Conciliados[i][2] = "   "+rs.getString(3).trim();
					Matriz_Conciliados[i][3] = "   "+rs.getString(4).trim();
					Matriz_Conciliados[i][4] = "   "+rs.getString(5).trim();
//					Matriz_Conciliados[i][5] = Boolean.valueOf(rs.getString(6).trim());
					Matriz_Conciliados[i][5] = false;
									i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    return Matriz_Conciliados; 
		}
	 	public int getFilasConciliados(String qry){
			int filas=0;
			Statement stmt = null;
			try {stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){filas++;}
			} catch (SQLException e1) {	e1.printStackTrace();}
			return filas;
			}
	 	

	   	
	 	
//	 	PRE CONCILIACION AUTOMATICA
	 	public void preconciliacion_automatica(String folio_nomina){
			String todos = "exec IZAGAR_traspaso_automatico_a_empleados_pre_conciliados_los_netos_de_nomina '"+folio_nomina+"'";
			PreparedStatement pstmt = null;
			Connection con = new Connexion().conexion();
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
	 	
	 	ActionListener OpAgregar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean filaSeleccionadaSCOI=false;
				int numeroFilaSCOI=0;
				
				boolean filaSeleccionadaNOMINA=false;
				int numeroFilaNOMINA=0;
				
				for(int i=0; i<tablaFiltro.getRowCount(); i++){
					if(tablaFiltro.getValueAt(i, 3).toString().trim().equals("true")){
						filaSeleccionadaSCOI=true;
						numeroFilaSCOI=i;
						break;
					}
				}
				
				for(int i=0; i<tablanomina.getRowCount(); i++){
					if(tablanomina.getValueAt(i, 3).toString().trim().equals("true")){
						filaSeleccionadaNOMINA=true;
						numeroFilaNOMINA=i;
						break;
					}
				}
				
				if(filaSeleccionadaSCOI && filaSeleccionadaNOMINA){
					
					int folio_empleado = Integer.valueOf(tablaFiltro.getValueAt(numeroFilaSCOI, 0).toString().trim());
					int nomina = Integer.valueOf(tablanomina.getValueAt(numeroFilaNOMINA, 0).toString().trim());
					float neto = Float.valueOf(tablanomina.getValueAt(numeroFilaNOMINA, 2).toString().trim());

					if(new Obj_IZAGAR_Netos_Nominas().guardar_netos_nominas_temp_individual(folio_empleado, nomina, neto)){
						RefreshTablas(nomina+"");
						for(int i=0; i<=tablanomina.getRowCount()-1; i++){
							tablanomina.setValueAt(false, i, 3);
						}
						JOptionPane.showMessageDialog(null,"El Registro Se Guardo Exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null,"El Registro No Se Guardo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null,"Debe Seleccionar Un Registro En Tabla SCOI\ny Uno De La Tabla NOMINA", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		};
		
	 	ActionListener OpAplicar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				
//				System.out.println("count tabla desde Izagar "+new Cat_Bancos().tabla.getRowCount());
				new Cat_Bancos().dispose();
//				dispose();
//				new Cat_Bancos().btn_salir.doClick();
//				new Cat_Bancos().setVisible(true);
				

				
				
				
				
				
				
				
//				if(new Obj_IZAGAR_Netos_Nominas().guardar_totales_deposito_nomina_bancos()){
////					dispose();
////					REFRESCAR TABLA DE BANCOS AUTOMATICO
//					
//					JOptionPane.showMessageDialog(null,"El Transpaso Se a Realizado Exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}else{
//					JOptionPane.showMessageDialog(null,"No Se A Realizado El Transpaso", "Error", JOptionPane.WARNING_MESSAGE);
//					return;
//				}
			}
		};
   	
	
//	public static void main(String args[]){
//		try{
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			new Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos(title).setVisible(true);
//		}catch(Exception e){
//			
//		}
//	}
}