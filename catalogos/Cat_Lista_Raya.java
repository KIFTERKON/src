package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;


import objetos.Obj_Auto_Auditoria;
import objetos.Obj_Auto_Finanzas;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Establecimiento;
import objetos.Obj_Lista_Raya;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Lista_Raya extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();

	Obj_Auto_Auditoria autoriza_auditoria = new Obj_Auto_Auditoria().buscar();
	boolean auto_auditoria = autoriza_auditoria.isAutorizar();
	
	Obj_Auto_Finanzas autoriza_finanza = new Obj_Auto_Finanzas().buscar();
	boolean auto_finanza = autoriza_finanza.isAutorizar();
	
	Connexion con = new Connexion();
	Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar2();
	boolean bono_10_12 = configs.isBono_10_12();
	boolean bono_dia_extra = configs.isBono_dia_extra();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    
	Object[][] Matriz;
	Object[][] Tabla = getTabla(cmbEstablecimientos.getSelectedItem()+"");
	DefaultTableModel model = new DefaultTableModel(Tabla,
		new String[]{"","Folio", "Nombre Completo", "Establecimiento", "Sueldo",
		"P Bono complementario", "Saldo Prestamo Inicial", "Descuento Prestamo", "Saldo Final", "D Fuente Sodas",
		"D Puntualidad", "D Faltas", "D Asistencia", "D Cortes", "D Infonavit",
		"D Banamex", "D Banorte", "D Extra", "P Día Extras", "P Bono Extra",
		"A Pagar", "Observasiones I", "Observasiones II" }
													){
		Class[] types = new Class[]{
	    	java.lang.Boolean.class,
	    	java.lang.Integer.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class,
	    	
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class,
	    	
	    	java.lang.String.class,
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class,
	    	
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class
	    	
         };
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0  : return true; 
        	 	case 1  : return false; 
        	 	case 3  : return false; 
        	 	case 4  : return false; 
        	 	
        	 	case 5  : return true;

        	 	case 6  : return false; 
        	 	case 7  : return false; 
        	 	case 8  : return false; 
        	 	case 9  : return false; 
        	 	
        	 	case 10 : return false; 
        	 	case 11 : return false; 
        	 	case 12 : return false; 
        	 	case 13 : return false; 
        	 	case 14 : return false; 
        	 	
        	 	case 15 : return false; 
        	 	case 16 : return false; 
        	 	case 17 : return false; 
        	 	case 18 : return false; 
        	 	case 19 : return false;
        	 	
        	 	case 20 : return false; 
        	 	case 21 : return true; 
        	 	case 22 : return true; 
        	 }
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	private TableRowSorter trsfiltro;
	
	 JToolBar menu = new JToolBar();
	 
	JLabel lblBuscar = new JLabel("BUSCAR : ");
	JTextField txtBuscar = new JTextField();
	
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	JLabel lblAuditoria = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblFinanzas = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	
	JButton btnGenerarLista = new JButton("Generar Lista Raya");
	JButton btnImprir = new JButton("Imprimir");
	
	public Cat_Lista_Raya()	{
		this.setTitle("Revisión lista raya");
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		System.out.println(bono_dia_extra);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer tcrR = new DefaultTableCellRenderer();
		tcrR.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer tcrL = new DefaultTableCellRenderer();
		tcrL.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcrL);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcrL);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(6).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(7).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(8).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(9).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(10).setCellRenderer(tcrR);
		tabla.getColumnModel().getColumn(11).setCellRenderer(tcrR);
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(16);
		tabla.getColumnModel().getColumn(0).setMinWidth(16);
		tabla.getColumnModel().getColumn(1).setMaxWidth(50);
		tabla.getColumnModel().getColumn(1).setMinWidth(50);
		tabla.getColumnModel().getColumn(2).setMaxWidth(235);
		tabla.getColumnModel().getColumn(2).setMinWidth(235);
		tabla.getColumnModel().getColumn(3).setMaxWidth(110);
		tabla.getColumnModel().getColumn(3).setMinWidth(110);
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		tabla.getColumnModel().getColumn(5).setMaxWidth(130);
		tabla.getColumnModel().getColumn(5).setMinWidth(130);
		tabla.getColumnModel().getColumn(6).setMaxWidth(130);
		tabla.getColumnModel().getColumn(6).setMinWidth(130);
		tabla.getColumnModel().getColumn(7).setMaxWidth(120);
		tabla.getColumnModel().getColumn(7).setMinWidth(120);
		tabla.getColumnModel().getColumn(8).setMaxWidth(80);
		tabla.getColumnModel().getColumn(8).setMinWidth(80);
		tabla.getColumnModel().getColumn(9).setMaxWidth(90);
		tabla.getColumnModel().getColumn(9).setMinWidth(90);
		tabla.getColumnModel().getColumn(10).setMaxWidth(90);
		tabla.getColumnModel().getColumn(10).setMinWidth(90);
		tabla.getColumnModel().getColumn(11).setMaxWidth(60);
		tabla.getColumnModel().getColumn(11).setMinWidth(60);
		tabla.getColumnModel().getColumn(12).setMaxWidth(80);
		tabla.getColumnModel().getColumn(12).setMinWidth(80);
		tabla.getColumnModel().getColumn(13).setMaxWidth(80);
		tabla.getColumnModel().getColumn(13).setMinWidth(80);
		tabla.getColumnModel().getColumn(14).setMaxWidth(80);
		tabla.getColumnModel().getColumn(14).setMinWidth(80);
		tabla.getColumnModel().getColumn(15).setMaxWidth(80);
		tabla.getColumnModel().getColumn(15).setMinWidth(80);
		tabla.getColumnModel().getColumn(16).setMaxWidth(80);
		tabla.getColumnModel().getColumn(16).setMinWidth(80);
		tabla.getColumnModel().getColumn(17).setMaxWidth(85);
		tabla.getColumnModel().getColumn(17).setMinWidth(85);
		tabla.getColumnModel().getColumn(18).setMaxWidth(80);
		tabla.getColumnModel().getColumn(18).setMinWidth(80);
		tabla.getColumnModel().getColumn(19).setMaxWidth(80);
		tabla.getColumnModel().getColumn(19).setMinWidth(80);
		
		tabla.getColumnModel().getColumn(20).setMaxWidth(90);
		tabla.getColumnModel().getColumn(20).setMinWidth(90);
		tabla.getColumnModel().getColumn(21).setMaxWidth(230);
		tabla.getColumnModel().getColumn(21).setMinWidth(230);
		tabla.getColumnModel().getColumn(22).setMaxWidth(230);
		tabla.getColumnModel().getColumn(22).setMinWidth(230);
		
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
		tabla.getColumnModel().getColumn(13).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(14).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(15).setCellRenderer(render);
		tabla.getColumnModel().getColumn(16).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(17).setCellRenderer(render);
		tabla.getColumnModel().getColumn(18).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(19).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(20).setCellRenderer(render);
		tabla.getColumnModel().getColumn(21).setCellRenderer(render); 
		tabla.getColumnModel().getColumn(22).setCellRenderer(render); 
		
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(scroll).setBounds(10,70,1250,600);
        
		campo.add(lblBuscar).setBounds(10,30,70,20);
		campo.add(txtBuscar).setBounds(90,30,220,20);
		
		campo.add(cmbEstablecimientos).setBounds(400, 30, 160, 20);
		
		campo.add(new JLabel("Autorizado por auditoria:")).setBounds(600,30,125,20);
		campo.add(lblAuditoria).setBounds(725,30,20,20);
		
		campo.add(new JLabel("Autorizado por finanzas:")).setBounds(755,30,125,20);
		campo.add(lblFinanzas).setBounds(880,30,20,20);
		
		campo.add(btnImprir).setBounds(1180, 20, 80, 45);
		campo.add(btnGenerarLista).setBounds(920,20,130,45);
		
		lblAuditoria.setEnabled(auto_auditoria);
		lblFinanzas.setEnabled(auto_finanza);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		campo.add(menu);
		
		cont.add(campo);
		
		if(auto_auditoria == false || auto_finanza == false){
			btnGenerarLista.setEnabled(false);
		}
		
		cmbEstablecimientos.addActionListener(opFiltrar);
		btnGuardar.addActionListener(opGuardar);
		btnGenerarLista.addActionListener(opGuardarListaRaya);
		btnImprir.addActionListener(opImprimirListaRaya);
		
		opBono10_12();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
	}
	
	public void opBono10_12(){
		if(bono_10_12 == true){
			for(int i=0; i<model.getRowCount(); i++){
				float suma = Float.parseFloat(model.getValueAt(i, 10)+"")+ Float.parseFloat(model.getValueAt(i, 12)+"");
				if(suma > 0){
					model.setValueAt(0.0, i, 5);
				}				
			}
		}
	}
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			guardar();
		}
	};
	
	ActionListener opImprimirListaRaya = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			System.out.println("guardar");
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			Obj_Lista_Raya lista_raya = new Obj_Lista_Raya();
			lista_raya.borrar();
			
			Imprimir_lista_raya();
			new Cat_Imprimir_LR().setVisible(true);
		}
	};
	
	ActionListener opGuardarListaRaya = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			guardar_lista_raya();
		}
	};
	
	public void guardar_lista_raya(){
		Vector miVector = new Vector();
		for(int i=0; i<model.getRowCount(); i++){
			for(int j=0; j<model.getColumnCount(); j++){
				miVector.add(model.getValueAt(i,j));
			}
			Obj_Lista_Raya lista_raya = new Obj_Lista_Raya();
			
			lista_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+"".trim()));
			lista_raya.setNombre_completo(miVector.get(2)+"".trim());
			lista_raya.setEstablecimiento(miVector.get(3)+"".trim());
			lista_raya.setSueldo(Float.parseFloat(miVector.get(4)+"".trim()));
			lista_raya.setP_bono_complementario(Float.parseFloat(miVector.get(5)+"".trim()));
			lista_raya.setSaldo_prestamo_inicial(Float.parseFloat(miVector.get(6)+"".trim()));
			lista_raya.setD_prestamo(Float.parseFloat(miVector.get(7)+"".trim()));
			lista_raya.setSaldo_final(Float.parseFloat(miVector.get(8)+"".trim()));
			lista_raya.setD_fuente_sodas(Float.parseFloat(miVector.get(9)+"".trim()));
			lista_raya.setD_puntualidad(Float.parseFloat(miVector.get(10)+"".trim()));
			lista_raya.setD_faltas(Float.parseFloat(miVector.get(11)+"".trim()));
			lista_raya.setD_asistencia(Float.parseFloat(miVector.get(12)+"".trim()));
			lista_raya.setD_cortes(Float.parseFloat(miVector.get(13)+"".trim()));
			lista_raya.setD_infonavit(Float.parseFloat(miVector.get(14)+"".trim()));
			lista_raya.setD_banamex(Float.parseFloat(miVector.get(15)+"".trim()));
			lista_raya.setD_banorte(Float.parseFloat(miVector.get(16)+"".trim()));
			lista_raya.setD_extra(Float.parseFloat(miVector.get(17)+"".trim()));
			lista_raya.setP_dias_extra(Float.parseFloat(miVector.get(18)+"".trim()));
			lista_raya.setP_bono_extra(Float.parseFloat(miVector.get(19)+"".trim()));
			lista_raya.setA_pagar(Float.parseFloat(miVector.get(20)+"".trim()));
			lista_raya.setObservasion_i(miVector.get(21)+"".trim());
			
			lista_raya.guardar_lista();
			miVector.clear();
		}
	}
	
	public void Imprimir_lista_raya(){
		Vector miVector = new Vector();
		for(int i=0; i<model.getRowCount(); i++){
			for(int j=0; j<model.getColumnCount(); j++){
				miVector.add(model.getValueAt(i,j));
			}
			Obj_Lista_Raya lista_raya = new Obj_Lista_Raya();
			
			lista_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+"".trim()));
			lista_raya.setNombre_completo(miVector.get(2)+"".trim());
			lista_raya.setEstablecimiento(miVector.get(3)+"".trim());
			lista_raya.setSueldo(Float.parseFloat(miVector.get(4)+"".trim()));
			lista_raya.setP_bono_complementario(Float.parseFloat(miVector.get(5)+"".trim()));
			lista_raya.setSaldo_prestamo_inicial(Float.parseFloat(miVector.get(6)+"".trim()));
			lista_raya.setD_prestamo(Float.parseFloat(miVector.get(7)+"".trim()));
			lista_raya.setSaldo_final(Float.parseFloat(miVector.get(8)+"".trim()));
			lista_raya.setD_fuente_sodas(Float.parseFloat(miVector.get(9)+"".trim()));
			lista_raya.setD_puntualidad(Float.parseFloat(miVector.get(10)+"".trim()));
			lista_raya.setD_faltas(Float.parseFloat(miVector.get(11)+"".trim()));
			lista_raya.setD_asistencia(Float.parseFloat(miVector.get(12)+"".trim()));
			lista_raya.setD_cortes(Float.parseFloat(miVector.get(13)+"".trim()));
			lista_raya.setD_infonavit(Float.parseFloat(miVector.get(14)+"".trim()));
			lista_raya.setD_banamex(Float.parseFloat(miVector.get(15)+"".trim()));
			lista_raya.setD_banorte(Float.parseFloat(miVector.get(16)+"".trim()));
			lista_raya.setD_extra(Float.parseFloat(miVector.get(17)+"".trim()));
			lista_raya.setP_dias_extra(Float.parseFloat(miVector.get(18)+"".trim()));
			lista_raya.setP_bono_extra(Float.parseFloat(miVector.get(19)+"".trim()));
			lista_raya.setA_pagar(Float.parseFloat(miVector.get(20)+"".trim()));
			lista_raya.setObservasion_i(miVector.get(21)+"".trim());
			
			lista_raya.imprimir_lista();
			miVector.clear();
		}
	}
	
	public void guardar(){
		Vector miVector = new Vector();
		if(getFilas("select * from tb_pre_listaraya where status = 1") > 1){
			if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j));
					}
					
					Obj_Lista_Raya lis_raya = new Obj_Lista_Raya();
									
					int foli_emple = Integer.parseInt(miVector.get(1)+"");
					Obj_Lista_Raya lis_foli = new Obj_Lista_Raya().buscar_folio(foli_emple);
			
					lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
					lis_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+""));
					lis_raya.setObservasion_i(miVector.get(21)+"");
					lis_raya.setObservasion_ii(miVector.get(22)+"");
								
					lis_raya.actualizar(lis_foli.getFolio());
					
					miVector.clear();
				}
				JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				return;
			}
			
		}else{
			for(int i=0; i<model.getRowCount(); i++){
				for(int j=0; j<model.getColumnCount(); j++){
					miVector.add(model.getValueAt(i,j).toString());
				}
				Obj_Lista_Raya lis_raya = new Obj_Lista_Raya();
				
				lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
				lis_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+""));
				lis_raya.setObservasion_i(miVector.get(21)+"");
				lis_raya.setObservasion_ii(miVector.get(22)+"");
			
				lis_raya.guardar();
				
				miVector.clear();
			}
			JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	ActionListener agregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			int fila = tabla.getSelectedRow();
			Object folio =  tabla.getValueAt(fila, 0);
			new Cat_Empleado(folio+"").setVisible(true);	
			
		}	
	};
	
	ActionListener opFiltrar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			int numero = tabla.getRowCount();
			while(numero > 0){
				model.removeRow(0);
				numero --;
			}
		
			Object[][] Tabla = getTabla(cmbEstablecimientos.getSelectedItem()+"");
			Object[] fila = new Object[tabla.getColumnCount()]; 
			for(int i=0; i<Tabla.length; i++){
				model.addRow(fila); 
				for(int j=0; j<tabla.getColumnCount(); j++){
					model.setValueAt(Tabla[i][j], i,j);
				}
			}
		}
	};
	
	private Object[][] getTabla(String establecimiento){
		String datos = "select tb_empleado.folio, "+
						    "tb_empleado.nombre, "+
					        "tb_empleado.ap_paterno, "+
					        "tb_empleado.ap_materno, "+
					        "tb_establecimiento.nombre as establecimiento, "+
					        "tb_sueldo.sueldo, "+
					        "tb_bono.bono, "+
					        "tb_empleado.infonavit "+
					     
						"from tb_empleado, tb_establecimiento, tb_sueldo, tb_bono "+ 
						"where tb_empleado.establecimiento_id = tb_establecimiento.folio and  "+
						 	   "tb_empleado.sueldo_id = tb_sueldo.folio and  "+
						 	   "tb_empleado.bono_id = tb_bono.folio;";
		
		String filtro = "select tb_empleado.folio, "+
	    					"tb_empleado.nombre, "+
	    					"tb_empleado.ap_paterno, "+
	    					"tb_empleado.ap_materno, "+
	    					"tb_establecimiento.nombre as establecimiento, "+
	    					"tb_sueldo.sueldo, "+
	    					"tb_bono.bono, "+
	    					"tb_empleado.infonavit "+
     
	    				"from tb_empleado, tb_establecimiento, tb_sueldo, tb_bono "+ 
	    				"where tb_empleado.establecimiento_id = tb_establecimiento.folio and  "+
	    					"tb_empleado.sueldo_id = tb_sueldo.folio and  "+
	    					"tb_empleado.bono_id = tb_bono.folio and " +
	    					"tb_establecimiento.nombre = '"+establecimiento+"'; ";
		
		String datos1 = "select tb_empleado.folio, "+
				"tb_empleado.nombre, "+
				"tb_empleado.ap_paterno, "+
				"tb_empleado.ap_materno, "+
				"tb_establecimiento.nombre as establecimiento, "+
				"tb_sueldo.sueldo, "+
				"tb_bono.bono, "+
				"tb_empleado.infonavit "+
     
			"from tb_empleado, tb_establecimiento, tb_sueldo, tb_bono "+ 
			"where tb_empleado.establecimiento_id = tb_establecimiento.folio and  "+
				"tb_empleado.sueldo_id = tb_sueldo.folio and  "+
				"tb_empleado.bono_id = tb_bono.folio;";
		
		String filtro1 ="select tb_empleado.folio,"+ 
			"tb_empleado.nombre, "+
			"tb_empleado.ap_paterno, "+
			"tb_empleado.ap_materno, "+
			"tb_establecimiento.nombre as establecimiento, "+
			"tb_sueldo.sueldo, "+
			"tb_bono.bono, "+
			"tb_empleado.infonavit "+
 
		"from tb_empleado, tb_establecimiento, tb_sueldo, tb_bono "+
		"where tb_empleado.establecimiento_id = tb_establecimiento.folio and "+ 
			"tb_empleado.sueldo_id = tb_sueldo.folio and  "+
			"tb_empleado.bono_id = tb_bono.folio and "+
			"tb_establecimiento.nombre = '"+establecimiento+"'";
		
		Statement s;
		ResultSet rs;
		
		float[] valoresAsistencia = getValoresPuntualidad();
		float puntualidad = valoresAsistencia[0];
		float asistencia = valoresAsistencia[1];
				
		try {
			if(getFilas("select * from tb_pre_listaraya where status = 1") > 1 ){
				if(establecimiento.equals("Todos")){
					s = con.conexion().createStatement();
					rs = s.executeQuery(datos1);
					Matriz = new Object[getFilas(datos1)][23];
					int i=0;
					while(rs.next()){
						int folio_empleado =  Integer.parseInt(rs.getString(1));
						String[] preList = getPreLista(folio_empleado);		
						Matriz[i][0] = Boolean.parseBoolean(preList[0].toString().trim());
						Matriz[i][1] = folio_empleado;
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						float sueldo = Float.parseFloat(rs.getString(6).trim());
							Matriz[i][4] = sueldo;
						float bono_complemento = Float.parseFloat(rs.getString(7).trim());
						Matriz[i][5] = bono_complemento;
						float[] prestamos = getPrestamos(folio_empleado);
						float prestamoInicial = prestamos[0];
							Matriz[i][6] = prestamoInicial;
						float DescuentoPrestamo = prestamos[1];
						float SumasDescuentos = getDescuentoPrest(folio_empleado); 
							Matriz[i][7] = DescuentoPrestamo;
							Matriz[i][8] = prestamoInicial-SumasDescuentos;
						float DescuentoFuenteSodas = getFuenteSodas(folio_empleado);
							Matriz[i][9] = DescuentoFuenteSodas;
							
						String[] puntualidades = getPuntualidad(folio_empleado);
						
						boolean punt = Boolean.parseBoolean(puntualidades[0].trim());
						if(punt != true){
							Matriz[i][10] = 0;
						}else{
							Matriz[i][10] = puntualidad;
						}
						
						float descuentoDias = Math.round(((sueldo + bono_complemento)/7) * Integer.parseInt(puntualidades[2].trim()));
						boolean dias = Boolean.parseBoolean(puntualidades[1].trim());
						if(dias != true){
							Matriz[i][11] = 0;
						}else{
							Matriz[i][11] = descuentoDias;
						}
						
						boolean asis = Boolean.parseBoolean(puntualidades[3].trim());
						if(asis != true){
							Matriz[i][12] = 0;
						}else{
							Matriz[i][12] = asistencia;
						}
						
						float cortes = getDescuento_Cortes(folio_empleado);
						Matriz[i][13] = cortes;
						
						float infonavit = Float.parseFloat(rs.getString(8).trim());
						Matriz[i][14] = infonavit;
						
						int[] bancos = getBancos(folio_empleado);
						int banamex = bancos[0];
						int banorte = bancos[1];
						
						Matriz[i][15] = banamex;
						Matriz[i][16] = banorte;
						String extra = puntualidades[4];
						if(extra != null){
							Matriz[i][17] = Float.parseFloat(extra);
						}else{
							extra="0";
							Matriz[i][17] = Float.parseFloat(extra);
						}
						
						Object[] persecciones = getPersecciones(folio_empleado);
						float dias_extras = Float.parseFloat(persecciones[0]+"");
						float diasPerseccion = 0;
						
						if(bono_dia_extra != true){
							diasPerseccion = Math.round((sueldo/7) * dias_extras);
						}else{
							diasPerseccion = Math.round(((sueldo + bono_complemento)/7) * dias_extras);
						}
						
						if(getDiasExtra(folio_empleado) != true){
							Matriz[i][18] = 0;	
						} else {
							Matriz[i][18] = diasPerseccion;
						}				
						
						float bono = Float.parseFloat(persecciones[1]+"");
						Matriz[i][19] = bono;
						
						float otro = Float.parseFloat(extra);
						
						float puns = Float.parseFloat(Matriz[i][10]+"");
						float asss = Float.parseFloat(Matriz[i][12]+"");
						
						float suma = sueldo + bono_complemento - DescuentoPrestamo - DescuentoFuenteSodas - puns - descuentoDias -
						asss - cortes - infonavit - banamex - banorte + (otro) + diasPerseccion + bono;
						
						Matriz[i][20] = suma;
						Matriz[i][21] = preList[1];
						Matriz[i][22] = preList[2];
						
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(filtro1);
					Matriz = new Object[getFilas(filtro1)][23];
					int i=0;
					while(rs.next()){
						int folio_empleado =  Integer.parseInt(rs.getString(1));
						String[] preList = getPreLista(folio_empleado);
						Matriz[i][0] = Boolean.parseBoolean(preList[0].toString().trim());
						Matriz[i][1] = folio_empleado;
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						float sueldo = Float.parseFloat(rs.getString(6).trim());
							Matriz[i][4] = sueldo;
						float bono_complemento = Float.parseFloat(rs.getString(7).trim());
						Matriz[i][5] = bono_complemento;
						float[] prestamos = getPrestamos(folio_empleado);
						float prestamoInicial = prestamos[0];
							Matriz[i][6] = prestamoInicial;
						float DescuentoPrestamo = prestamos[1];
						float SumasDescuentos = getDescuentoPrest(folio_empleado); 
							Matriz[i][7] = DescuentoPrestamo;
							Matriz[i][8] = prestamoInicial-SumasDescuentos;
						float DescuentoFuenteSodas = getFuenteSodas(folio_empleado);
							Matriz[i][9] = DescuentoFuenteSodas;
							
						String[] puntualidades = getPuntualidad(folio_empleado);
						
						boolean punt = Boolean.parseBoolean(puntualidades[0].trim());
						if(punt != true){
							Matriz[i][10] = 0;
						}else{
							Matriz[i][10] = puntualidad;
						}
						
						float descuentoDias = Math.round(((sueldo + bono_complemento)/7) * Integer.parseInt(puntualidades[2].trim()));
						boolean dias = Boolean.parseBoolean(puntualidades[1].trim());
						if(dias != true){
							Matriz[i][11] = 0;
						}else{
							Matriz[i][11] = descuentoDias;
						}
						
						boolean asis = Boolean.parseBoolean(puntualidades[3].trim());
						if(asis != true){
							Matriz[i][12] = 0;
						}else{
							Matriz[i][12] = asistencia;
						}
						
						float cortes = getDescuento_Cortes(folio_empleado);
						Matriz[i][13] = cortes;
						
						float infonavit = Float.parseFloat(rs.getString(8).trim());
						Matriz[i][14] = infonavit;
						
						int[] bancos = getBancos(folio_empleado);
						int banamex = bancos[0];
						int banorte = bancos[1];
						
						Matriz[i][15] = banamex;
						Matriz[i][16] = banorte;
						String extra = puntualidades[4];
						if(extra != null){
							Matriz[i][17] = Float.parseFloat(extra);
						}else{
							extra="0";
							Matriz[i][17] = Float.parseFloat(extra);
						}
						
						Object[] persecciones = getPersecciones(folio_empleado);
						float dias_extras = Float.parseFloat(persecciones[0]+"");
						float diasPerseccion = 0;
						
						if(bono_dia_extra != true){
							diasPerseccion = Math.round((sueldo/7) * dias_extras);
						}else{
							diasPerseccion = Math.round(((sueldo + bono_complemento)/7) * dias_extras);
						}
						
						if(getDiasExtra(folio_empleado) != true){
							Matriz[i][18] = 0;	
						} else {
							Matriz[i][18] = diasPerseccion;
						}				
						
						float bono = Float.parseFloat(persecciones[1]+"");
						Matriz[i][19] = bono;
						
						float otro = Float.parseFloat(extra);
						
						float puns = Float.parseFloat(Matriz[i][10]+"");
						float asss = Float.parseFloat(Matriz[i][12]+"");
						
						float suma = sueldo + bono_complemento - DescuentoPrestamo - DescuentoFuenteSodas - puns - descuentoDias -
						asss - cortes - infonavit - banamex - banorte + (otro) + diasPerseccion + bono;
						
						Matriz[i][20] = suma;
						Matriz[i][21] = preList[1];
						Matriz[i][22] = preList[2];
						
						i++;
					}
				}
			}else{
				if(establecimiento.equals("Todos")){
					s = con.conexion().createStatement();
					rs = s.executeQuery(datos);
					Matriz = new Object[getFilas(datos)][23];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						int folio_empleado =  Integer.parseInt(rs.getString(1));
							Matriz[i][1] = folio_empleado;
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						float sueldo = Float.parseFloat(rs.getString(6).trim());
							Matriz[i][4] = sueldo;
						float bono_complemento = Float.parseFloat(rs.getString(7).trim());
						Matriz[i][5] = bono_complemento;
						float[] prestamos = getPrestamos(folio_empleado);
						float prestamoInicial = prestamos[0];
							Matriz[i][6] = prestamoInicial;
						float DescuentoPrestamo = prestamos[1];
						float SumasDescuentos = getDescuentoPrest(folio_empleado); 
							Matriz[i][7] = DescuentoPrestamo;
							Matriz[i][8] = prestamoInicial-SumasDescuentos;
						float DescuentoFuenteSodas = getFuenteSodas(folio_empleado);
							Matriz[i][9] = DescuentoFuenteSodas;
							
						String[] puntualidades = getPuntualidad(folio_empleado);
						
						boolean punt = Boolean.parseBoolean(puntualidades[0].trim());
						if(punt != true){
							Matriz[i][10] = 0;
						}else{
							Matriz[i][10] = puntualidad;
						}
						
						float descuentoDias = Math.round(((sueldo + bono_complemento)/7) * Integer.parseInt(puntualidades[2].trim()));
						boolean dias = Boolean.parseBoolean(puntualidades[1].trim());
						if(dias != true){
							Matriz[i][11] = 0;
						}else{
							Matriz[i][11] = descuentoDias;
						}
						
						boolean asis = Boolean.parseBoolean(puntualidades[3].trim());
						if(asis != true){
							Matriz[i][12] = 0;
						}else{
							Matriz[i][12] = asistencia;
						}
						
						float cortes = getDescuento_Cortes(folio_empleado);
						Matriz[i][13] = cortes;
						
						float infonavit = Float.parseFloat(rs.getString(8).trim());
						Matriz[i][14] = infonavit;
						
						int[] bancos = getBancos(folio_empleado);
						int banamex = bancos[0];
						int banorte = bancos[1];
						
						Matriz[i][15] = banamex;
						Matriz[i][16] = banorte;
						String extra = puntualidades[4];
						if(extra != null){
							Matriz[i][17] = Float.parseFloat(extra);
						}else{
							extra="0";
							Matriz[i][17] = Float.parseFloat(extra);
						}
						
						Object[] persecciones = getPersecciones(folio_empleado);
						float dias_extras = Float.parseFloat(persecciones[0]+"");
						float diasPerseccion = 0;
						
						if(bono_dia_extra != true){
							diasPerseccion = Math.round((sueldo/7) * dias_extras);
						}else{
							diasPerseccion = Math.round(((sueldo + bono_complemento)/7) * dias_extras);
						}
						
						if(getDiasExtra(folio_empleado) != true){
							Matriz[i][18] = 0;	
						} else {
							Matriz[i][18] = diasPerseccion;
						}				
						
						float bono = Float.parseFloat(persecciones[1]+"");
						Matriz[i][19] = bono;
						
						float otro = Float.parseFloat(extra);
						
						float puns = Float.parseFloat(Matriz[i][10]+"");
						float asss = Float.parseFloat(Matriz[i][12]+"");
						
						float suma = sueldo + bono_complemento - DescuentoPrestamo - DescuentoFuenteSodas - puns - descuentoDias -
						asss - cortes - infonavit - banamex - banorte + (otro) + diasPerseccion + bono;
						
						Matriz[i][20] = suma;
						Matriz[i][21] = "";
						Matriz[i][22] = "";
						i++;
					}
				}else{
					s = con.conexion().createStatement();
					rs = s.executeQuery(filtro);
					Matriz = new Object[getFilas(filtro)][23];
					int i=0;
					while(rs.next()){
						Matriz[i][0] = false;
						int folio_empleado =  Integer.parseInt(rs.getString(1));
							Matriz[i][1] = folio_empleado;
						Matriz[i][2] = "   "+rs.getString(2).trim()+" "+ rs.getString(3).trim()+" "+ rs.getString(4).trim();
						Matriz[i][3] = "   "+rs.getString(5).trim();
						float sueldo = Float.parseFloat(rs.getString(6).trim());
							Matriz[i][4] = sueldo;
						float bono_complemento = Float.parseFloat(rs.getString(7).trim());
						Matriz[i][5] = bono_complemento;
						float[] prestamos = getPrestamos(folio_empleado);
						float prestamoInicial = prestamos[0];
							Matriz[i][6] = prestamoInicial;
						float DescuentoPrestamo = prestamos[1];
						float SumasDescuentos = getDescuentoPrest(folio_empleado); 
							Matriz[i][7] = DescuentoPrestamo;
							Matriz[i][8] = prestamoInicial-SumasDescuentos;
						float DescuentoFuenteSodas = getFuenteSodas(folio_empleado);
							Matriz[i][9] = DescuentoFuenteSodas;
							
						String[] puntualidades = getPuntualidad(folio_empleado);
						
						boolean punt = Boolean.parseBoolean(puntualidades[0].trim());
						if(punt != true){
							Matriz[i][10] = 0;
						}else{
							Matriz[i][10] = puntualidad;
						}
						
						float descuentoDias = Math.round(((sueldo + bono_complemento)/7) * Integer.parseInt(puntualidades[2].trim()));
						boolean dias = Boolean.parseBoolean(puntualidades[1].trim());
						if(dias != true){
							Matriz[i][11] = 0;
						}else{
							Matriz[i][11] = descuentoDias;
						}
						
						boolean asis = Boolean.parseBoolean(puntualidades[3].trim());
						if(asis != true){
							Matriz[i][12] = 0;
						}else{
							Matriz[i][12] = asistencia;
						}
						
						float cortes = getDescuento_Cortes(folio_empleado);
						Matriz[i][13] = cortes;
						
						float infonavit = Float.parseFloat(rs.getString(8).trim());
						Matriz[i][14] = infonavit;
						
						int[] bancos = getBancos(folio_empleado);
						int banamex = bancos[0];
						int banorte = bancos[1];
						
						Matriz[i][15] = banamex;
						Matriz[i][16] = banorte;
						String extra = puntualidades[4];
						if(extra != null){
							Matriz[i][17] = Float.parseFloat(extra);
						}else{
							extra="0";
							Matriz[i][17] = Float.parseFloat(extra);
						}
						
						Object[] persecciones = getPersecciones(folio_empleado);
						float dias_extras = Float.parseFloat(persecciones[0]+"");
						float diasPerseccion = 0;
						
						if(bono_dia_extra != true){
							diasPerseccion = Math.round((sueldo/7) * dias_extras);
						}else{
							diasPerseccion = Math.round(((sueldo + bono_complemento)/7) * dias_extras);
						}
						
						if(getDiasExtra(folio_empleado) != true){
							Matriz[i][18] = 0;	
						} else {
							Matriz[i][18] = diasPerseccion;
						}				
						
						float bono = Float.parseFloat(persecciones[1]+"");
						Matriz[i][19] = bono;
						
						float otro = Float.parseFloat(extra);
						
						float puns = Float.parseFloat(Matriz[i][10]+"");
						float asss = Float.parseFloat(Matriz[i][12]+"");
						
						float suma = sueldo + bono_complemento - DescuentoPrestamo - DescuentoFuenteSodas - puns - descuentoDias -
						asss - cortes - infonavit - banamex - banorte + (otro) + diasPerseccion + bono;
						
						Matriz[i][20] = suma;
						Matriz[i][21] = "";
						Matriz[i][22] = "";
						i++;
					}
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 

	}
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	public float[] getValoresPuntualidad(){
		float[] valores= new float[2];
		valores[0] = 0;
		valores[1] = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select puntualidad, asistencia from tb_asistencia_puntualidad");
			while(rs.next()){
				valores[0] = Float.parseFloat(rs.getString(1));
				valores[1] = Float.parseFloat(rs.getString(2));
			} 
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public int[] getBancos(int folio){
		int[] valores= new int[2];
		valores[0] = 0;
		valores[1] = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select banamex, banorte from tb_bancos where folio_empleado = "+folio);
			while(rs.next()){
				valores[0] = rs.getInt(1);
				valores[1] = rs.getInt(2);
			} 
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}	
	
	public float[] getPrestamos(int folio){
		float[] valores= new float[3];
		valores[0] = 0;
		valores[1] = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select cantidad, descuento from tb_prestamo where saldo > 0 and  folio_empleado="+folio);
			while(rs.next()){
				valores[0] = Float.parseFloat(rs.getString(1));
				valores[1] = Float.parseFloat(rs.getString(2));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public float getFuenteSodas(int folio){
		float valor = 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select " +
											     "sum(tb_fuente_sodas_rh.cantidad)as Total " +
										  "from  tb_fuente_sodas_rh " +
										  "where tb_fuente_sodas_rh.status_ticket = '1' and " +
										  		 "tb_fuente_sodas_rh.status ='1' and " +
										  		 "tb_fuente_sodas_rh.folio_empleado ="+folio +" " + 
										  "group by tb_fuente_sodas_rh.nombre_completo,tb_fuente_sodas_rh.folio_empleado");
			while(rs.next()){
				valor = Float.parseFloat(rs.getString(1));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public String[] getPuntualidad(int folio){
		String[] valores= new String[5];
		valores[0]= "false";	valores[1]= "false";
		valores[2]= "0";	valores[3]= "false";
		valores[4]= "0";
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select puntualidad, falta, dia_faltas, asistencia , extra from tb_deduccion_inasistencia where status = '1' and folio_empleado="+folio);
			while(rs.next()){
				
				valores[0] = rs.getString(1);
				valores[1] = rs.getString(2);
				valores[2] = rs.getString(3);
				valores[3] = rs.getString(4);
				valores[4] = rs.getString(5);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public String[] getPreLista(int folio){
		String[] valores= new String[3];
		valores[0]= "false";	
		valores[1]= "";	
		valores[2]= "";
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select boolean, observasion_i, observasion_ii from tb_pre_listaraya where status = '1' and folio_empleado="+folio);
			while(rs.next()){
				valores[0] = rs.getString(1);
				valores[1] = rs.getString(2);
				valores[2] = rs.getString(3);
			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	
	public float getDescuento_Cortes(int folio){
		float valor= 0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select descuento from tb_diferencia_cortes where saldo > 0 and folio_empleado="+folio);
			while(rs.next()){
				valor = Float.parseFloat(rs.getString(1));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public Object[] getPersecciones(int folio){
		String[] valores= new String[2];
		valores[0]="0";
		valores[1]="0";
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select dias, bono from tb_persecciones_extra where folio_empleado="+folio);
			while(rs.next()){
				valores[0] = rs.getString(1);			
				valores[1] = rs.getString(2);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valores;
	}
	
	public boolean getDiasExtra(int folio){
		boolean valor = false;
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select dia_extra from tb_persecciones_extra where folio_empleado="+folio);
			while(rs.next()){
				valor = rs.getBoolean(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public float getDescuentoPrest(int folio){
		float valor = 0;
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select sum(descuento)as 'descuento' from tb_abono where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getFloat(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public int getFolio_prestamo(int folio){
		int valor = 0;
	try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select folio from tb_prestamo where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getInt(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}

}