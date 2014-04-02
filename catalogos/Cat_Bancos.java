package catalogos;

import izagar.Cat_IZAGAR_Selecionar_Nomina_Para_Netos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Objetos_IZAGAR.Obj_IZAGAR_Netos_Nominas;
import SQL.Connexion;




import objetos.Obj_Bancos;

@SuppressWarnings("serial")
public class Cat_Bancos extends Cat_Root {
	public JCheckBox chbHabilitarBanamex = new JCheckBox("Habilitar");	
	public JCheckBox chbHabilitarBanorte = new JCheckBox("Habilitar");
	public JCheckBox chbNegativos = new JCheckBox("Valores Negativos");
	
	public JTextField txtBanamex = new JTextField();
	public JTextField txtBanorte = new JTextField();
	public JTextField txtTotales = new JTextField();
	    
	public DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Bancos().get_tabla_model(),
            new String[]{"Folio", "Nombre Completo", "Establecimientos", "Banamex", "Banorte", "Total a Pagar" }
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class
         };
	     
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
        public boolean isCellEditable(int fila, int columna){
        	switch(columna){
        		case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 :
    	 	if(chbHabilitarBanamex.isSelected()){
    	 				if(tabla_model.getValueAt(fila,4).toString().length() != 0){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}        	 				
    	 			 }else{
    	 				 return false;
    	 			}        	 			
        	 	case 4 : 
    	 			if(chbHabilitarBanorte.isSelected()){
    	 				if(tabla_model.getValueAt(fila,3).toString().length() != 0){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}
    	 			 }else{
    	 				 return false;
    	 			 }
        	 	case 5 : 
        	 		return false;

        	 } 				
 			return false;
 		}
                
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	JButton btn_lay_out = new JButton(new ImageIcon("Iconos/PAG.png"));
	JButton btn_123 = new JButton(new ImageIcon("Iconos/calendar_icon&16.png"));
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
		
    public Cat_Bancos(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/money_icon&16.png"));
		this.setTitle("Bancos");
			
		this.panel.add(cmbEstablecimientos).setBounds(463,35,210,20);
		this.panel.add(chbHabilitarBanamex).setBounds(700,35,90,20);
		this.panel.add(chbHabilitarBanorte).setBounds(820,35,90,20);
		this.panel.add(chbNegativos).setBounds(920,35,120,20);
		
		this.panel.add(scroll_tabla).setBounds(30,60,1035,615);
		this.panel.add(btn_lay_out).setBounds(1015,0,25,25);
		this.panel.add(btn_123).setBounds(1040,0,25,25);
		
		this.panel.add(btn_salir).setBounds(1140,0,25,25);
		
		this.panel.add(new JLabel("Total Banamex:")).setBounds(1080,70,100,20);
		this.panel.add(txtBanamex).setBounds(1160,70,120,20);
		this.txtBanamex.setEditable(false);
		this.txtBanamex.setFont(new Font("",0,14));
		
		this.panel.add(new JLabel("Total Banorte:")).setBounds(1080,95,250,20);
		this.panel.add(txtBanorte).setBounds(1160,95,120,20);
		this.txtBanorte.setEditable(false);
		this.txtBanorte.setFont(new Font("",0,14));
		
		this.panel.add(new JLabel("Totales:")).setBounds(1080,120,250,20);
		this.panel.add(txtTotales).setBounds(1160,120,120,20);
		this.txtTotales.setEditable(false);
		this.txtTotales.setFont(new Font("",0,14));
		
		this.cont.add(panel);
		
		this.init_tabla();
		
		this.btn_guardar.addActionListener(op_guardar);
		this.btn_lay_out.addActionListener(op_lay_out);
		this.btn_123.addActionListener(op_123);
		this.btn_salir.addActionListener(op_salir);
		
		this.btn_refrescar.setVisible(false);
			
		btn_lay_out.setToolTipText("Generar Lay Out");
		btn_123.setToolTipText("Comparacion");		
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		this.chbNegativos.addActionListener(op_negativos);
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.addWindowListener(op_cerrar);
//		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
    
    WindowListener op_cerrar = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				new Obj_Bancos().guardar(tabla_guardar());
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
	};
	
	 ActionListener op_salir = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
	 };
    ActionListener op_guardar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			chbNegativos.setSelected(false);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de bancos?") == 0){
					Obj_Bancos banco = new Obj_Bancos();
					if(banco.guardar(tabla_guardar())){
						txtBanamex.setText("$ "+returnBanamex());
						txtBanorte.setText("$ "+returnBanorte());
						txtTotales.setText("$ "+returnTotales());
						JOptionPane.showMessageDialog(null, "La tabla bancos se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					return;
				}
			}
		}
	};
	
    ActionListener op_lay_out = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Lay_Out().setVisible(true);
		}
	};
	
	   ActionListener op_123 = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Cat_IZAGAR_Selecionar_Nomina_Para_Netos().setVisible(true);
			}
		};
	
		public Object[][] tabla_guardar(){
			Object[][] matriz = new Object[tabla.getRowCount()][6];
			for(int i=0; i<tabla.getRowCount(); i++){
				for(int j=0; j<tabla.getColumnCount()-1; j++){
					switch(j){
						case 0: 
							matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());
							break;
						case 1: 
							matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();
							break;
						case 2: 
							matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();
							break;
						case 3: 
							if(tabla_model.getValueAt(i,j).toString().equals("")){
								matriz[i][j] = Float.parseFloat("0.0");
							}else{
								matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString());
							}
							break;
						case 4: 
							if(tabla_model.getValueAt(i,j).toString().equals("")){
								matriz[i][j] = Float.parseFloat("0.0");
							}else{
								matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString());
							}
							break;
					}
				}
			}
			return matriz;
		}
	
		public String valida_tabla(){
			String error = "";
			for(int i=0; i<tabla.getRowCount(); i++){
				for(int j=3; j<tabla.getColumnCount()-1; j++){
					try{
						if(!isNumeric(tabla_model.getValueAt(i,j).toString())){
							switch(j){
								case 3: 
									error += "   La celda de la columna Banamex no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
								break;
								case 4:
									error += "   La celda de la columna Banorte no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
								break;
							}
						}
					} catch(Exception e){
						JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			return error;
		}
		
    @SuppressWarnings("unchecked")
	public void init_tabla(){
    	this.tabla.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(72);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(72);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(360);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(360);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(210);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(210);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(120);		
    	this.tabla.getColumnModel().getColumn(5).setMaxWidth(130);
    	this.tabla.getColumnModel().getColumn(5).setMinWidth(130);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
					
					if(table.getSelectedRow() == row){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(186,143,73));
					}
					
					switch(column){
						case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
						case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
						case 3 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
						case 4 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
						case 5 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
					}
				return lbl; 
				} 
			}; 

		for(int x = 0; x<tabla.getColumnCount(); x++){
			this.tabla.getColumnModel().getColumn(x).setCellRenderer(render); 
		}
		
		this.tabla.setRowSorter(trsfiltro);  
		
		this.txtBanamex.setText("$ "+returnBanamex());
		this.txtBanorte.setText("$ "+returnBanorte());
		this.txtTotales.setText("$ "+returnTotales());
				
    }
    
    public static boolean isNumeric(String cadena){
    	try {
    		if(cadena.equals("")){
    			return true;
    		}else{
    			Float.parseFloat(cadena);
        		return true;
    		}
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
    
    KeyListener op_filtro_folio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {
			char caracter = arg0.getKeyChar();
			if(((caracter < '0') ||
				(caracter > '9')) &&
			    (caracter != KeyEvent.VK_BACK_SPACE)){
				arg0.consume(); 
			}	
		}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	KeyListener op_filtro_nombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener op_filtro_establecimiento = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	ActionListener op_negativos = new ActionListener(){
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(chbNegativos.isSelected()){
				trsfiltro.setRowFilter(RowFilter.regexFilter("-", 5));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 5));
			}
		}
		
	};
    
	public float returnBanamex(){
		float valor = 0;
		
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i, 3).toString().length() != 0){
				valor = valor + Float.parseFloat(tabla_model.getValueAt(i,3)+"");
			}				
		}
		return valor;
	}
	
	public float returnBanorte(){
		float valor = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,4).toString().length() != 0){
				valor = valor + Float.parseFloat(tabla_model.getValueAt(i,4)+"");
			}				
		}
		return valor;
	}
	
	public float returnTotales(){
		float valor = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,3).toString().length() != 0){
				valor = valor + Float.parseFloat(tabla_model.getValueAt(i,3)+"");
			}	
			if(tabla_model.getValueAt(i,4).toString().length() != 0){
				valor = valor + Float.parseFloat(tabla_model.getValueAt(i,4)+"");
			}		
		}
		return valor;
	}
	
	public static void main (String [] arg) {
		new Cat_Bancos().setVisible(true);
	}
	
	
	
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
//			limpiar tablanomina
			while(tablanomina.getRowCount()>0){	modelonomina.removeRow(0);	}
//			llenar arreglo desde funcion
			Object[][] getTablaNomina = getTablanetosnominaBMS(folio_nomina);
			Object[] fila = new Object[4];
//			 llenar tablanomina
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
			
//			btnAplicar.setEnabled(false);
			
			cont.add(campo);
			setSize(935,735);
			setResizable(false);
			setLocationRelativeTo(null);
			btnAgregar.addActionListener(OpAgregar);
			btnAplicar.addActionListener(OpAplicar);
			
			guarda_auto_netos_nomina_po_empleado_temp(folio_nomina);

//			funcion para seleccionar solo un registro a la ves en la tabla de SCOI-----------------------------------------------------------
	        tablaFiltro.addMouseListener(opTablaFiltroSeleccion);
//			funcion para seleccionar solo un registro a la ves en la tabla de NOMINA--------------------------------------------------------- 
	        tablanomina.addMouseListener(opTablaNominaSeleccion);
//			funcion para seleccionar solo un registro a la ves en la tabla CONCILIADOS-------------------------------------------------------
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
								
								
//								llenado tabla exportacion de bm completa despues de guardarlos en scoi
								Object[][] getTablaNomina = getTablanetosnomina_guardados_scoi(folio_nomina);
								Object[] fila = new Object[4];
//								 vuelve a llenar tablanomina desde el scoi
						         for(int i=0; i<getTablaNomina.length; i++){
						                 fila[0] = getTablaNomina[i][0]+"";
						                 fila[1] = getTablaNomina[i][1]+"";
						                 fila[2] = getTablaNomina[i][2]+"";
//						                 fila[3] = Boolean.valueOf(getTablaNomina[i][3].toString().trim());
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
			
//	 		llenado tabla conciliados
	     	Object[][] getTablaconciliados = getTablaconciliados(folio_nom);
	     	Object[] fila2 = new Object[5];
//			 llenar tabla conciliados
	        for(int i=0; i<getTablaconciliados.length; i++){
	                fila2[0] = getTablaconciliados[i][0]+"";
	                fila2[1] = getTablaconciliados[i][1]+"";
	                fila2[2] = getTablaconciliados[i][2]+"";
	                fila2[3] = getTablaconciliados[i][3]+"";
	                fila2[4] = getTablaconciliados[i][4]+"";
//	                fila2[5] = Boolean.valueOf(getTablaNomina[i][5].toString().trim());
	                modeloconciliados.addRow(fila2);} 
	        
//	 		llenado tabla faltantes de conciliar
	     	Object[][] getTablaFiltro = getTablaempleadoscoi(folio_nom);
	     	Object[] fila1 = new Object[4];
//			 llenar tabla empleado scoi
	        for(int i=0; i<getTablaFiltro.length; i++){
	                fila1[0] = getTablaFiltro[i][0]+"";
	                fila1[1] = getTablaFiltro[i][1]+"";
	                fila1[2] = getTablaFiltro[i][2]+"";
//	                fila1[3] = Boolean.valueOf(getTablaNomina[i][3].toString().trim());
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
		
		
//		private Object[][] tabla_guardar(){
	//
//			Object[][] matriz = new Object[tablaFiltro.getRowCount()][8];
//			for(int i=0; i<tablaFiltro.getRowCount(); i++){
//				
//					matriz[i][0] = modeloFiltro.getValueAt(i,0).toString().trim();
//					matriz[i][1] = modeloFiltro.getValueAt(i,1).toString().trim();
//					matriz[i][2] = modeloFiltro.getValueAt(i,2).toString().trim();
//					matriz[i][3] = modeloFiltro.getValueAt(i,3).toString().trim();
	//
//					
//			}
//			return matriz;
//		}
		
		
		

		
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
//					Matriz_nomina_neto_bms[i][3] = Boolean.valueOf(rs2.getString(4).trim());
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
//					MatrizFiltro[i][3] = Boolean.valueOf(rs.getString(4).trim());
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
//						Matriz_Conciliados[i][5] = Boolean.valueOf(rs.getString(6).trim());
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
		 	

		   	
		 	
//		 	PRE CONCILIACION AUTOMATICA
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

					
					
					
//					System.out.println("count tabla desde Izagar "+new Cat_Bancos().tabla.getRowCount());
//					new Cat_Bancos().dispose();
//					dispose();
					new Cat_Bancos().btn_salir.doClick();
					
					
					
					
					
//					if(new Obj_IZAGAR_Netos_Nominas().guardar_totales_deposito_nomina_bancos()){
////						dispose();
////						REFRESCAR TABLA DE BANCOS AUTOMATICO
//						
//						JOptionPane.showMessageDialog(null,"El Transpaso Se a Realizado Exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//						return;
//					}else{
//						JOptionPane.showMessageDialog(null,"No Se A Realizado El Transpaso", "Error", JOptionPane.WARNING_MESSAGE);
//						return;
//					}
				}
			};
	   	
		
//		public static void main(String args[]){
//			try{
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				new Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos(title).setVisible(true);
//			}catch(Exception e){
//				
//			}
//		}
	}
}