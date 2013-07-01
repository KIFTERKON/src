package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.toedter.calendar.JDateChooser;

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

	JDateChooser txtCalendario = new JDateChooser();
	
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
		"D Puntualidad", "D Faltas", "D Asistencia", "D Gafere", "D Cortes", 
		"D Infonavit", "Pension", "D Banamex", "D Banorte", "D Extra", 
		"P Día Extras", "P Bono Extra", "A Pagar", "Observasiones I", "Observasiones II" }){
		
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
        	 	case 22 : return false; 
        	 	case 23 : return true; 
        	 	case 24 : return true; 
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
	JButton btnActualizar = new JButton("Actualizar");
	
	JLabel lblAuditoria = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblFinanzas = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblNumeroLista = new JLabel("Número de Lista: "+numero_lista);
	
	JButton btnGenerarLista = new JButton("Generar Lista Raya");
	JButton btnImprir = new JButton("Imprimir");
	JButton btnRevisionTotal = new JButton("Revision Totales");
	
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
		tabla.getColumnModel().getColumn(22).setMaxWidth(80);
		tabla.getColumnModel().getColumn(22).setMinWidth(80);
		tabla.getColumnModel().getColumn(23).setMaxWidth(230);
		tabla.getColumnModel().getColumn(23).setMinWidth(230);
		tabla.getColumnModel().getColumn(24).setMaxWidth(230);
		tabla.getColumnModel().getColumn(24).setMinWidth(230);
		
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
		campo.add(btnGenerarLista).setBounds(920,27,80,20);
		campo.add(btnActualizar).setBounds(1180,27,80,20);
		
		campo.add(btnRevisionTotal).setBounds(337,0,148,25);
		
		lblAuditoria.setEnabled(auto_auditoria);
		lblFinanzas.setEnabled(auto_finanza);
		
		menu.add(btnGuardar);
		menu.add(new JLabel("   Fecha Final:  "));
		menu.add(txtCalendario);
		menu.setBounds(0,0,240,25);
		campo.add(menu);
		
		cont.add(campo);
		
		if(auto_auditoria == false || auto_finanza == false){
			btnGenerarLista.setEnabled(false);
		}
		
		if(getFechaFinal() != ""){
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(getFechaFinal());
				txtCalendario.setDate(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
			
		btnGuardar.addActionListener(opGuardar);
		btnGenerarLista.addActionListener(opGuardarListaRaya);
		btnImprir.addActionListener(opImprimirListaRaya);
		btnActualizar.addActionListener(opActualizar);
		btnRevisionTotal.addActionListener(opRevisarTotal);
		tabla.addFocusListener(OpFocus);
	
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		opBono10_12();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
	}
	
	FocusListener OpFocus = new FocusListener(){
		public void focusGained(FocusEvent arg0) {
			btnImprir.setEnabled(false);
			btnRevisionTotal.setEnabled(false);
		}
		
		public void focusLost(FocusEvent arg0) {}
		
	};
	ActionListener opRevisarTotal = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Nomina().setVisible(true);
		}
		
	};
	
	
	ActionListener opActualizar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			int numero = tabla.getRowCount();
			while(numero > 0){
				model.removeRow(0);
				numero --;
			}
			
			Object[][] Tabla = getTabla();
			Object[] fila = new Object[tabla.getColumnCount()];
			for(int i=0; i<Tabla.length; i++){
				model.addRow(fila); 
				for(int j=0; j<tabla.getColumnCount(); j++){
					model.setValueAt(Tabla[i][j], i,j);
				}
			}
			
		}	
	};
	
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
			if(validaCampos() != ""){
				JOptionPane.showMessageDialog(null, "El Siguiente campo es requerído:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				new Progress_Bar_Guardar().setVisible(true);
			}
		}
	};
	
	ActionListener opImprimirListaRaya = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			
//			lista_raya.Imprimir();
			
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
	
	public String getFechaFinal(){
		String valor = "";
		try {
			Connexion con = new Connexion();
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select distinct fecha_final from tb_pre_listaraya");
			while(rs.next()){
				valor = rs.getString(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
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
			Matriz = new Object[getFilas(datos)][25];
			int i=0;
			while(rs.next()){
				DecimalFormat formato = new DecimalFormat("#0.0");
				float sueldo = rs.getFloat(4);     float bono_comp = rs.getFloat(5);
				float salPresIni = rs.getFloat(6); float descPrest = rs.getFloat(7);  
				float salPresFin = rs.getFloat(8); float descFuent = rs.getFloat(9);
				float descPuntu = rs.getFloat(10); float descFalta = rs.getFloat(11);
				float descAsist = rs.getFloat(12); float descGafet = rs.getFloat(13);
				float descCorte = rs.getFloat(14); float descInfon = rs.getFloat(15); 
				float descPensi = rs.getFloat(16);
				float descBanam	= rs.getFloat(17); float descBanor = rs.getFloat(18);
				float percExtra = rs.getFloat(19); float percDiasE = rs.getFloat(20);
				float percBonoE = rs.getFloat(21);
				
				Matriz[i][0] = rs.getBoolean(24);
				Matriz[i][1] = rs.getInt(1);
				Matriz[i][2] = "  "+rs.getString(2).trim();
				Matriz[i][3] = "  "+rs.getString(3).trim();
				Matriz[i][4] = sueldo;
				
				if(bono_comp == 0.0){
					Matriz[i][5] ="";
				}else{
					Matriz[i][5] = bono_comp;
				}
				
				if(salPresIni == 0.0){
					Matriz[i][6] ="";
				}else{
					Matriz[i][6] = salPresIni;
				}
				if(descPrest == 0.0){
					Matriz[i][7] ="";
				}else{
					Matriz[i][7] = descPrest;
				}
				if(salPresFin == 0.0){
					Matriz[i][8] ="";
				}else{
					Matriz[i][8] = salPresFin;
				}

				if(descFuent == 0.0){
					Matriz[i][9] ="";
				}else{
					Matriz[i][9] = descFuent;
				}
				
				if(descPuntu == 0.0){
					Matriz[i][10] ="";
				}else{
					Matriz[i][10] = descPuntu;
				}
				if(descFalta == 0.0){
					Matriz[i][11] ="";
				}else{
					Matriz[i][11] = descFalta;
				}
				if(descAsist == 0.0){
					Matriz[i][12] ="";
				}else{
					Matriz[i][12] = descAsist;
				}
				if(descGafet == 0.0){
					Matriz[i][13] ="";
				}else{
					Matriz[i][13] = descGafet;
				}
				
				if(descCorte == 0.0){
					Matriz[i][14] ="";
				}else{
					Matriz[i][14] = descCorte;
				}
				if(descInfon == 0.0){
					Matriz[i][15] ="";
				}else{
					Matriz[i][15] = descInfon;
				}
				if(descPensi == 0.0){
					Matriz[i][16] ="";
				}else{
					Matriz[i][16] = descPensi;
				}
				if(descBanam == 0.0){
					Matriz[i][17] ="";
				}else{
					Matriz[i][17] = descBanam;
				}
				if(descBanor == 0.0){
					Matriz[i][18] ="";
				}else{
					Matriz[i][18] = descBanor;
				}
				if(percExtra == 0.0){
					Matriz[i][19] ="";
				}else{
					Matriz[i][19] = percExtra;
				}
				if(percDiasE == 0.0){
					Matriz[i][20] ="";
				}else{
					Matriz[i][20] = percDiasE;
				}
				if(percBonoE == 0.0){
					Matriz[i][21] ="";
				}else{
					Matriz[i][21] = percBonoE;
				}
				Matriz[i][22] = Decimal(Float.parseFloat(formato.format(sueldo+bono_comp-descPrest-descFuent-descPuntu-descFalta-descAsist-descGafet-descCorte-
						        descInfon-descPensi-descBanam-descBanor+ percExtra+percDiasE+percBonoE)));
				Matriz[i][23] = rs.getString(22);
				Matriz[i][24] = rs.getString(23);
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public double Decimal(double x){
		String numeroString = x+"";
		String[] dec = numeroString.split("\\.");
	
		double valor = Integer.parseInt(dec[0]);
		double decimal = Double.parseDouble("."+dec[1]);
		
		if(decimal <= 0.25){
			return valor;
		}
		if(decimal > 0.25 && decimal <= 0.74){
			return valor + 0.5;
		}
		if(decimal >= 0.75 && decimal <= .9){
			return valor + 1;
		}
		return valor;
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
								if(model.getValueAt(i,j) != null){
									miVector.add(model.getValueAt(i,j));
								}else{
									miVector.add("");
								}
							}
							Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
											
							// Error de boolean en la tb_listaraya
							if(miVector.get(0).toString() != ""){
								lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString().trim()));
							}else{
								lis_raya.setChecado(false);
							}
							
							
							int foli_emple = Integer.parseInt(miVector.get(1)+"");

							Obj_Revision_Lista_Raya lis_foli = new Obj_Revision_Lista_Raya().buscar_folio(foli_emple);
							lis_raya.setFolio_empleado(foli_emple);
							
							lis_raya.setEstablecimiento(miVector.get(3).toString().trim());
							
							lis_raya.setA_pagar(Float.parseFloat(miVector.get(22)+""));
							if(miVector.get(23)!= null){
								lis_raya.setObservasion_i(miVector.get(23)+"");
							}else{
								lis_raya.setObservasion_i("");
							}
							if(miVector.get(24) != null){
								lis_raya.setObservasion_ii(miVector.get(24)+"");
							}else{
								lis_raya.setObservasion_ii("");
							}
							lis_raya.setFecha_final(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							
							Obj_Revision_Lista_Raya existe = new Obj_Revision_Lista_Raya().buscarExis(foli_emple);
							
							if(existe.getFolio_empleado() == foli_emple){
								lis_raya.actualizar(lis_foli.getFolio());
							}else{
								lis_raya.guardar();
							}
							miVector.clear();
							
							int porcent = (i*100)/total;
							barra.setValue(porcent+1);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();	
							}
						}
						JOptionPane.showMessageDialog(null, "La lista se Actualizó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						
						btnImprir.setEnabled(true);
						btnRevisionTotal.setEnabled(true);
						
						dispose();
					}else{
						dispose();
						return;
					}
					
				}else{
					panel.setBorder(BorderFactory.createTitledBorder("Guardando Pre-Lista de raya..."));
					for(int i=0; i<model.getRowCount(); i++){
						for(int j=0; j<model.getColumnCount(); j++){
							if(model.getValueAt(i,j) != null){
								miVector.add(model.getValueAt(i,j));
							}else{
								miVector.add("");
							}
						}
						Obj_Revision_Lista_Raya lis_raya = new Obj_Revision_Lista_Raya();
						
						if(miVector.get(0).toString() != ""){
							lis_raya.setChecado(Boolean.parseBoolean(miVector.get(0).toString().trim()));
						}else{
							lis_raya.setChecado(false);
						}
						
						lis_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+""));
						
						lis_raya.setEstablecimiento(miVector.get(3).toString().trim());
						
						lis_raya.setA_pagar(Float.parseFloat(miVector.get(22)+""));
						
						if(miVector.get(23)!= null){
							lis_raya.setObservasion_i(miVector.get(23)+"");
						}else{
							lis_raya.setObservasion_i("");
						}
						if(miVector.get(24) != null){
							lis_raya.setObservasion_ii(miVector.get(24)+"");
						}else{
							lis_raya.setObservasion_ii("");
						}
					
						lis_raya.setFecha_final(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
						
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
					btnImprir.setEnabled(true);
					btnRevisionTotal.setEnabled(true);
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
						miVector.add(model.getValueAt(i,j)+"");
					}
					Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
						
					lista_raya.setNumero_lista(numero_lista);
					lista_raya.setFolio_empleado(Integer.parseInt(miVector.get(1)+"".trim()));
					lista_raya.setNombre_completo(miVector.get(2)+"".trim());
					lista_raya.setEstablecimiento(miVector.get(3)+"".trim());
					lista_raya.setSueldo(Float.parseFloat(miVector.get(4)+"".trim()));
					
					
					if(miVector.get(5).toString().length() == 0){
						lista_raya.setP_bono_complementario(Float.parseFloat(0+""));
					}else{
						lista_raya.setP_bono_complementario(Float.parseFloat(miVector.get(5)+"".trim()));
					}
					if(miVector.get(6).toString().length() == 0){
						lista_raya.setSaldo_prestamo_inicial(Float.parseFloat(0+""));
					}else{
						lista_raya.setSaldo_prestamo_inicial(Float.parseFloat(miVector.get(6)+"".trim()));
					}
					if(miVector.get(7).toString().length() == 0){
						lista_raya.setD_prestamo(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_prestamo(Float.parseFloat(miVector.get(7)+"".trim()));
					}
					if(miVector.get(8).toString().length() == 0){
						lista_raya.setSaldo_final(Float.parseFloat(0+""));
					}else{
						lista_raya.setSaldo_final(Float.parseFloat(miVector.get(8)+"".trim()));
					}
					if(miVector.get(9).toString().length() == 0){
						lista_raya.setD_fuente_sodas(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_fuente_sodas(Float.parseFloat(miVector.get(9)+"".trim()));						
					}
					if(miVector.get(10).toString().length() == 0){
						lista_raya.setD_puntualidad(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_puntualidad(Float.parseFloat(miVector.get(10)+""));
					}
					if(miVector.get(11).toString().length() == 0){
						lista_raya.setD_faltas(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_faltas(Float.parseFloat(miVector.get(11)+"".trim()));
					}
					if(miVector.get(12).toString().length() == 0){
						lista_raya.setD_asistencia(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_asistencia(Float.parseFloat(miVector.get(12)+"".trim()));
					}
					if(miVector.get(13).toString().length() == 0){
						lista_raya.setD_gafete(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_gafete(Float.parseFloat(miVector.get(13)+"".trim()));
					}
					if(miVector.get(14).toString().length() == 0){
						lista_raya.setD_cortes(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_cortes(Float.parseFloat(miVector.get(14)+"".trim()));
					}
					if(miVector.get(15).toString().length() == 0){
						lista_raya.setD_infonavit(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_infonavit(Float.parseFloat(miVector.get(15)+"".trim()));
					}
					if(miVector.get(16).toString().length() == 0){
						lista_raya.setPension(Float.parseFloat(0+""));
					}else{
						lista_raya.setPension(Float.parseFloat(miVector.get(16)+"".trim()));
					}
					if(miVector.get(17).toString().length() == 0){
						lista_raya.setD_banamex(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_banamex(Float.parseFloat(miVector.get(17)+"".trim()));
					}
					if(miVector.get(18).toString().length() == 0){
						lista_raya.setD_banorte(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_banorte(Float.parseFloat(miVector.get(18)+"".trim()));
					}
					if(miVector.get(19).toString().length() == 0){
						lista_raya.setD_extra(Float.parseFloat(0+""));
					}else{
						lista_raya.setD_extra(Float.parseFloat(miVector.get(19)+"".trim()));
					}
					if(miVector.get(20).toString().length() == 0){
						lista_raya.setP_dias_extra(Float.parseFloat(0+""));
					}else{
						lista_raya.setP_dias_extra(Float.parseFloat(miVector.get(20)+"".trim()));
					}
					if(miVector.get(21).toString().length() == 0){
						lista_raya.setP_bono_extra(Float.parseFloat(0+""));
					}else{
						lista_raya.setP_bono_extra(Float.parseFloat(miVector.get(21)+"".trim()));
					}
					lista_raya.setA_pagar(Float.parseFloat(miVector.get(22)+"".trim()));
					lista_raya.setObservasion_i(miVector.get(23)+"".trim());
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
	
	private String validaCampos(){
		String error="";
		String fechaNull = txtCalendario.getDate()+"";
		if(fechaNull.equals("null"))error+= "Fecha Final de la lista de raya\n";	
		
		return error;
	}
	
}
