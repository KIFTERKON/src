package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Auto_Auditoria;
import objetos.Obj_Auto_Finanzas;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Establecimiento;
import objetos.Obj_Revision_Lista_Raya;
import SQL.Connexion;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Revision_Lista_Raya extends JFrame {
	
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
   
	int numero_lista = getNumeroLista();
	
	Object[][] Matriz;
	Object[][] Tabla = getTabla();
	DefaultTableModel model = new DefaultTableModel(Tabla,
		new String[]{"","Folio", "Nombre Completo", "Establecimiento", "Sueldo",
		"P Bono complementario", "Saldo Prestamo Inicial", "Descuento Prestamo", "Saldo Final", "D Fuente Sodas",
		"D Puntualidad", "D Faltas", "D Asistencia", "D Cortes", "D Infonavit", 
		"Pension" ,	"D Banamex", "D Banorte", "D Extra", "P Día Extras", 
		"P Bono Extra",	"A Pagar", "Observasiones I", "Observasiones II" }
													){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Boolean.class,
	    	java.lang.Integer.class, 
	    	java.lang.String.class, 
	    	java.lang.String.class, 
	    	java.lang.Float.class, 
	    	
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class,  
	    	java.lang.Float.class,  
	    	java.lang.Float.class, 
	    	
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class,  
	    	java.lang.Float.class,  
	    	java.lang.Float.class, 
	    	
	    	java.lang.Float.class, 
	    	java.lang.Float.class, 
	    	java.lang.Float.class,  
	    	java.lang.Float.class,  
	    	java.lang.Float.class,
	    	
	    	java.lang.Float.class,  
	    	java.lang.Float.class,  
	    	java.lang.String.class,
	    	java.lang.String.class
	    	
         };
		@SuppressWarnings("rawtypes")
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0  : return true; 
        	 	case 1  : return false; 
        	 	case 2  : return false;
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
        	 	case 13 : return false; 
        	 	case 14 : return false; 
        	 	
        	 	case 15 : return false; 
        	 	case 16 : return false; 
        	 	case 17 : return false; 
        	 	case 18 : return false; 
        	 	case 19 : return false;
        	 	
        	 	case 20 : return false; 
        	 	case 21 : return false; 
        	 	case 22 : return true; 
        	 	case 23 : return true; 
        	 }
 			return false;
 		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;

	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	JToolBar menu = new JToolBar();
	 
	JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
	
	JLabel lblAuditoria = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblFinanzas = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblNumeroLista = new JLabel("Número de Lista: "+numero_lista);
	
	JButton btnGenerarLista = new JButton("Generar Lista Raya");
	JButton btnImprir = new JButton("Imprimir");
	
	@SuppressWarnings("rawtypes")
	public Cat_Revision_Lista_Raya() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lista.png"));
		this.setTitle("Revisión lista raya ["+numero_lista+"]");

		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);
		
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
		tabla.getColumnModel().getColumn(1).setMaxWidth(70);
		tabla.getColumnModel().getColumn(1).setMinWidth(70);
		tabla.getColumnModel().getColumn(2).setMaxWidth(240);
		tabla.getColumnModel().getColumn(2).setMinWidth(240);
		tabla.getColumnModel().getColumn(3).setMaxWidth(148);
		tabla.getColumnModel().getColumn(3).setMinWidth(148);
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
		tabla.getColumnModel().getColumn(21).setMaxWidth(80);
		tabla.getColumnModel().getColumn(21).setMinWidth(80);
		tabla.getColumnModel().getColumn(22).setMaxWidth(230);
		tabla.getColumnModel().getColumn(22).setMinWidth(230);
		tabla.getColumnModel().getColumn(23).setMaxWidth(230);
		tabla.getColumnModel().getColumn(23).setMinWidth(230);
		
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
		tabla.getColumnModel().getColumn(23).setCellRenderer(render);
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(scroll).setBounds(10,48,1250,650);
		lblNumeroLista.setFont(new java.awt.Font("Dialog",Font.BOLD,16));
		
        campo.add(txtFolio).setBounds(27,27,68,20);
        campo.add(txtNombre_Completo).setBounds(96,27,239,20);
        campo.add(cmbEstablecimientos).setBounds(337,27, 148, 20);
		
		campo.add(new JLabel("Autorizado por auditoria:")).setBounds(550,27,125,20);
		campo.add(lblAuditoria).setBounds(675,27,20,20);
		
		campo.add(new JLabel("Autorizado por finanzas:")).setBounds(755,27,125,20);
		campo.add(lblFinanzas).setBounds(880,27,20,20);
		
		campo.add(btnImprir).setBounds(1092, 27, 80, 20);
		campo.add(btnGenerarLista).setBounds(920,27,130,20);
		
		lblAuditoria.setEnabled(auto_auditoria);
		lblFinanzas.setEnabled(auto_finanza);
		
		menu.add(btnGuardar);
		menu.setBounds(0,0,150,25);
		campo.add(menu);
		
		cont.add(campo);
		
		if(auto_auditoria == false || auto_finanza == false){
			btnGenerarLista.setEnabled(false);
		}
		
		btnGuardar.addActionListener(opGuardar);
		btnGenerarLista.addActionListener(opGuardarListaRaya);
		btnImprir.addActionListener(opImprimirListaRaya);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
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
			new Progress_Bar_Guardar().setVisible(true);
		}
	};
	
	ActionListener opImprimirListaRaya = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
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
		if(JOptionPane.showConfirmDialog(null, "¿Desea Guardar la Lista de Raya?") == 0){
			new Progress_Bar_Generar().setVisible(true);
			lblAuditoria.setEnabled(false);
			lblFinanzas.setEnabled(false);
			btnGenerarLista.setEnabled(false);
			
		}else{
			return;
		}

	}
	
	public int getNumeroLista(){
		int valor = 0;
	try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("exec sp_max_folio_lista_raya");
			while(rs.next()){
				valor = rs.getInt(1)+1;			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	@SuppressWarnings("rawtypes")
	public void Imprimir_lista_raya(){
		Vector miVector = new Vector();
		for(int i=0; i<model.getRowCount(); i++){
			for(int j=0; j<model.getColumnCount(); j++){
				miVector.add(model.getValueAt(i,j));
			}
			Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
			
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
	
	ActionListener agregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			int fila = tabla.getSelectedRow();
			Object folio =  tabla.getValueAt(fila, 0);
			new Cat_Empleado(folio+"").setVisible(true);	
			
		}	
	};
	
	KeyListener opFiltroFolio = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	KeyListener opFiltroNombre = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 2));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 3));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	private Object[][] getTabla(){
		String datos = "exec sp_lista_raya";
		
		Statement s;
		ResultSet rs;
		try {			
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			Matriz = new Object[getFilas(datos)][24];
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getBoolean(1);
				Matriz[i][1] = rs.getInt(2);
				Matriz[i][2] = "  "+rs.getString(3).trim();
				Matriz[i][3] = "  "+rs.getString(4).trim();
				float sueldo = rs.getFloat(5);     float bono_comp = rs.getFloat(6);
				float descPrest = rs.getFloat(8);  float descFuent = rs.getFloat(10);
				float descPuntu = rs.getFloat(11); float descFalta = rs.getFloat(12);
				float descAsist = rs.getFloat(13); float descCorte = rs.getFloat(14);
				float descInfon = rs.getFloat(15); float descPensi = rs.getFloat(16);
				float descBanam	= rs.getFloat(17); float descBanor = rs.getFloat(18);
				float percExtra = rs.getFloat(19); float percDiasE = rs.getFloat(20);
				float percBonoE = rs.getFloat(21);
				Matriz[i][4] = sueldo;
				Matriz[i][5] = bono_comp;
				Matriz[i][6] = rs.getFloat(7);
				Matriz[i][7] = descPrest;
				Matriz[i][8] = rs.getFloat(9);
				Matriz[i][9] = descFuent;
				Matriz[i][10] = descPuntu;
				Matriz[i][11] = descFalta;
				Matriz[i][12] = descAsist;
				Matriz[i][13] = descCorte;
				Matriz[i][14] = descInfon;
				Matriz[i][15] = descPensi;
				Matriz[i][16] = descBanam;
				Matriz[i][17] = descBanor;
				Matriz[i][18] = percExtra;
				Matriz[i][19] = percDiasE;
				Matriz[i][20] = percBonoE;
				Matriz[i][21] = sueldo+bono_comp-descPrest-descFuent-descPuntu-descFalta-descAsist-descCorte-
						        descInfon-descPensi-descBanam-descBanor+ percExtra+percDiasE+percBonoE;
				
				Matriz[i][22] = rs.getString(22).trim();
				Matriz[i][23] = rs.getString(23).trim();
				
				i++;
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
	
	public class Progress_Bar_Guardar extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		public Progress_Bar_Guardar() {
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
			public void run() {
				int total = model.getRowCount();
					
				@SuppressWarnings("rawtypes")
				Vector miVector = new Vector();
				if(getFilas("exec sp_status_pre_listaraya") > 1){
					panel.setBorder(BorderFactory.createTitledBorder("Actulizando Pre-Lista de raya..."));
					if(JOptionPane.showConfirmDialog(null, "La lista ya existe, ¿desea actualizarla?") == 0){
						for(int i=0; i<model.getRowCount(); i++){
							for(int j=0; j<model.getColumnCount(); j++){
								miVector.add(model.getValueAt(i,j));
							}
							Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
											
							
							lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
							int foli_emple = Integer.parseInt(miVector.get(1)+"");
							Obj_Revision_Lista_Raya lis_foli = new Obj_Revision_Lista_Raya().buscar_folio(foli_emple);
							lis_raya.setFolio_empleado(foli_emple);
							lis_raya.setA_pagar(Float.parseFloat(miVector.get(21)+""));
							lis_raya.setObservasion_i(miVector.get(22)+"");
							lis_raya.setObservasion_ii(miVector.get(23)+"");
										
							lis_raya.actualizar(lis_foli.getFolio());
							
							miVector.clear();
							
							int porcent = (i*100)/total;
							barra.setValue(porcent+1);
							try {
								Thread.sleep(0);
							} catch (InterruptedException e) {
								e.printStackTrace();	
							}
						}
						JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						dispose();
					}else{
						dispose();
						return;
					}
					
				}else{
					panel.setBorder(BorderFactory.createTitledBorder("Guardando Pre-Lista de raya..."));
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							miVector.add(model.getValueAt(i,j));
						}
						Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
						
						lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString()));
						lis_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+""));
						lis_raya.setA_pagar(Float.parseFloat(miVector.get(21)+""));
						lis_raya.setObservasion_i(miVector.get(22)+"");
						lis_raya.setObservasion_ii(miVector.get(23)+"");
					
						lis_raya.guardar();
						
						miVector.clear();
						
						int porcent = (i*100)/total;
						barra.setValue(porcent+1);
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							e.printStackTrace();	
						}
					}
					JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			}
		}
	}
	
	public class Progress_Bar_Generar extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		JProgressBar barra = new JProgressBar();
		
		public Progress_Bar_Generar() {
			barra.setStringPainted(true);
			Thread hilo = new Thread(new Hilo());
			hilo.start();
			panel.setBorder(BorderFactory.createTitledBorder("Generando Lista de raya..."));
			
			panel.add(barra).setBounds(20,25,350,20);
			
			cont.add(panel);
			
			this.setUndecorated(true);
			this.setSize(400,100);
			this.setModal(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		
		}

		class Hilo implements Runnable {
			public void run() {
				int total = model.getRowCount();
				@SuppressWarnings("rawtypes")
				Vector miVector = new Vector();
				Calendar c = new GregorianCalendar();
					
				String dia = c.get(Calendar.DATE)+"";
				String mes = (c.get(Calendar.MONTH)+1)+"";
				String anio = c.get(Calendar.YEAR)+"";
					
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						miVector.add(model.getValueAt(i,j));
					}
					Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
						
					lista_raya.setNumero_lista(numero_lista);
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
					lista_raya.setPension(Float.parseFloat(miVector.get(15)+"".trim()));
					lista_raya.setD_banamex(Float.parseFloat(miVector.get(16)+"".trim()));
					lista_raya.setD_banorte(Float.parseFloat(miVector.get(17)+"".trim()));
					lista_raya.setD_extra(Float.parseFloat(miVector.get(18)+"".trim()));
					lista_raya.setP_dias_extra(Float.parseFloat(miVector.get(19)+"".trim()));
					lista_raya.setP_bono_extra(Float.parseFloat(miVector.get(20)+"".trim()));
					lista_raya.setA_pagar(Float.parseFloat(miVector.get(21)+"".trim()));
					lista_raya.setObservasion_i(miVector.get(22)+"".trim());
					lista_raya.setFecha(dia+"/"+mes+"/"+anio);
					lista_raya.guardar_lista();
					
					miVector.clear();
					int porcent = (i*100)/total;
					barra.setValue(porcent);
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
							
					}
						
				} dispose();
			}
		}
	}
}
