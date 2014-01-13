            package catalogos;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Revision_Lista_Raya;
import objetos.Obj_fuente_sodas_rh;

@SuppressWarnings("serial")
/** CTRL EN CAT_ROOT_LISTA_RAYA PARA AGREGAR BOTON **/
public class Cat_Revision_Lista_Raya extends Cat_Root_Lista_Raya {
	
	private DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Revision_Lista_Raya().get_tabla_model(),
		new String[]{"","Folio", "Nombre Completo", "Establecimiento", "Sueldo",
			"P Bono complementario", "Saldo Prestamo Inicial", "Descuento Prestamo", "Saldo Final", "D Fuente Sodas",
			"D Puntualidad", "D Faltas", "D Asistencia", "D Gafete", "D Cortes", 
			"D Infonavit", "Pension", "D Banamex", "D Banorte", "D Extra", 
			"P Día Extras", "P Bono Extra", "A Pagar", "Observasiones I", "Observasiones II" }){
			
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
		   	java.lang.Boolean.class,
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		    	
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class, 
		    	
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class, 
		    	
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class,
		   	
		   	java.lang.Object.class,
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class,
		   	java.lang.Object.class
		    	
	    };
		@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
	private JTable tabla = new JTable(tabla_model);
	private JScrollPane scroll_tabla = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
	/* EL CONSTRUCTOR TIENE EL NOMBRE PUBLIC Y SEGUIDO DEL NOMBRE DE LA CLASE */
	public Cat_Revision_Lista_Raya(){
		this.setTitle("Revisión lista raya");
		
		this.panel.add(scroll_tabla).setBounds(30,60,1250,615);
		cont.add(panel);
		
		this.init_tabla();
		this.init_component();
		txtCalendario.setEnabled(false);
		
		this.btn_guardar.addActionListener(op_guardar);
		
		
		if((new Obj_fuente_sodas_rh().busquedaautoizacionfs().isStatus_autorizacion()))
		{
		btn_imprimir.setEnabled(false);
		} else { 
			btn_imprimir.setEnabled(true);
					}
		
		this.btn_imprimir.addActionListener(op_imprimir);
		this.btn_nomina.addActionListener(op_nomina);
		this.btn_refrescar.addActionListener(op_refrescar);
		this.btn_generar.addActionListener(op_generar);

		/*
		 * LE AGREGA AL BOTON UN ACCION LISTENER QUE CARGARA EL EVENTO MOSTRADO ENTRE PARENTESIS
		*/
		this.btn_lista_raya_pasadas.addActionListener(op_consulta_lista);
		
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.addWindowListener(op_cerrar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 *  SE DIO DE ALTA LA ACCION LISTENER OP_CONSULTA LISTA
	 *  SE LE DARA CLICK ALA OPERACION Y MOSTRARA LA OPCION ADD METOD
	**/
	ActionListener op_consulta_lista = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Consulta_Lista_de_Raya_Pasadas().setVisible(true);	
		}
		
	};
	
	
	WindowListener op_cerrar = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		@SuppressWarnings("unchecked")
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				cmbEstablecimientos.setSelectedIndex(0);
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				new Obj_Revision_Lista_Raya().guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
	};
	
	ActionListener op_generar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			
			if(valida_error() != ""){
				JOptionPane.showMessageDialog(null, "Los siguientes datos son requeridos:\n"+valida_error(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(new Obj_Revision_Lista_Raya().init_autorizacion()){
					if(new Obj_Revision_Lista_Raya().init_totales_nomina()){
					
						Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
						if(lista_raya.generar(tabla_generar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){

							JOptionPane.showMessageDialog(null, "La lista de raya se generó con éxito","Aviso",JOptionPane.INFORMATION_MESSAGE);
							dispose();
							new Cat_Revision_Lista_Raya().setVisible(true);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "La lista de raya no se pudo generar","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else{
						JOptionPane.showMessageDialog(null, "Antes de generar la lista de raya tiene que guardar la Nomina","Aviso",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "La lista de raya no está autorizada","Aviso",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}
	};
	
	private Object[][] tabla_generar(){
		Object[][] matriz = new Object[tabla.getRowCount()][tabla.getColumnCount()];
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<=tabla.getColumnCount(); j++){
				switch(j){
					case 1 : matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());		break;
					case 2 : matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();							break;
					case 3 : matriz[i][j] =tabla_model.getValueAt(i,j).toString().trim();							break;
					case 4 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 5 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 6 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 7 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 8 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 9 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 10 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 11 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 12 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 13 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 14 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 15 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 16 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 17 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 18 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 19 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 20 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 21 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 22 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 23 : matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();			break;
					
				}

			}

		}
		return matriz;
	}
	
	ActionListener op_refrescar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			actualizar();
			JOptionPane.showMessageDialog(null, "Se guardó y se refrescaron los datos correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
		}
	};
	
	@SuppressWarnings("unchecked")
	public void actualizar(){
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
		
		txtFolio.setText("");
		txtNombre_Completo.setText("");
		cmbEstablecimientos.setSelectedIndex(0);
		
		if(tabla.isEditing()){
			tabla.getCellEditor().stopCellEditing();
		}
		
		Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
		
		if(lista_raya.guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){
			while(tabla.getRowCount() > 0){
				tabla_model.removeRow(0);
			}
			
			Object[][] Tabla = new Obj_Revision_Lista_Raya().get_tabla_model();
			Object[] fila = new Object[tabla.getColumnCount()];
			for(int i=0; i<Tabla.length; i++){
				tabla_model.addRow(fila); 
				for(int j=0; j<tabla.getColumnCount(); j++){
					tabla_model.setValueAt(Tabla[i][j], i,j);
				}
			}
		}		
	}
	
	ActionListener op_guardar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			
			if(valida_error() != ""){
				JOptionPane.showMessageDialog(null, "Los siguientes datos son requeridos:\n"+valida_error(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista pre-raya?") == 0){
					Obj_Revision_Lista_Raya lista_raya = new Obj_Revision_Lista_Raya();
					if(lista_raya.guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){
						JOptionPane.showMessageDialog(null, "La tabla pre-raya se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener op_imprimir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			
			new Cat_Imprimir_LR().setVisible(true);
		}
	};
	
	ActionListener op_nomina = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(new Obj_Revision_Lista_Raya().init_revision_totales()){
				new Cat_Nomina().setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "Se necesita que alimente los totales de nómina", "Error al visualizar la revisión de totales", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][6];
		for(int i=0; i<tabla.getRowCount(); i++){
			matriz[i][0] = Boolean.parseBoolean(tabla_model.getValueAt(i,0).toString().trim());
			matriz[i][1] = Integer.parseInt(tabla_model.getValueAt(i,1).toString().trim());
			matriz[i][2] = tabla_model.getValueAt(i,3).toString().trim();
			if(tabla_model.getValueAt(i,22).toString().trim().equals("")){
				matriz[i][3] = Float.parseFloat("0");
			}else{
				matriz[i][3] = Float.parseFloat(tabla_model.getValueAt(i,22).toString().trim());
			}
			matriz[i][4] = tabla_model.getValueAt(i,23).toString().trim();
			matriz[i][5] = tabla_model.getValueAt(i,24).toString().trim();
		}
		return matriz;
	}
	
	public String valida_error(){
		String error="";
			String fechaNull = txtCalendario.getDate()+"";
			if(fechaNull.equals("null"))error+= "Fecha de la lista de raya\n";
		return error;
	}
	
	public void init_component(){
		String fecha = new Obj_Revision_Lista_Raya().get_fecha();
		
		if(fecha != ""){
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
				txtCalendario.setDate(date);
				txtCalendario.setEnabled(false);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}				
			}
	}
	
	@SuppressWarnings("unchecked")
	public void init_tabla(){
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(16);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(16);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(70);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(70);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(310);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(310);
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(160);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(160);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(91);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(91);
		
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(130);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(130);
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(130);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(130);
		this.tabla.getColumnModel().getColumn(7).setMaxWidth(120);
		this.tabla.getColumnModel().getColumn(7).setMinWidth(120);
		this.tabla.getColumnModel().getColumn(8).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(8).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(9).setMaxWidth(90);
		this.tabla.getColumnModel().getColumn(9).setMinWidth(90);
		
		this.tabla.getColumnModel().getColumn(10).setMaxWidth(90);
		this.tabla.getColumnModel().getColumn(10).setMinWidth(90);
		this.tabla.getColumnModel().getColumn(11).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(11).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(12).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(12).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(13).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(13).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(14).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(14).setMinWidth(80);
		
		this.tabla.getColumnModel().getColumn(15).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(15).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(16).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(16).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(17).setMaxWidth(85);
		this.tabla.getColumnModel().getColumn(17).setMinWidth(85);
		this.tabla.getColumnModel().getColumn(18).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(18).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(19).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(19).setMinWidth(80);
		
		this.tabla.getColumnModel().getColumn(20).setMaxWidth(90);
		this.tabla.getColumnModel().getColumn(20).setMinWidth(90);
		this.tabla.getColumnModel().getColumn(21).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(21).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(22).setMaxWidth(80);
		this.tabla.getColumnModel().getColumn(22).setMinWidth(80);
		this.tabla.getColumnModel().getColumn(23).setMaxWidth(230);
		this.tabla.getColumnModel().getColumn(23).setMinWidth(230);
		this.tabla.getColumnModel().getColumn(24).setMaxWidth(230);
		this.tabla.getColumnModel().getColumn(24).setMinWidth(230);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
			
				switch(column){
					case 0: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 1: 
						
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 2:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 3: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 4: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 5: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 6: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 7: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 8: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 9: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 10: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 11:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 12: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 13: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 14: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 15: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 16: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 17: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 18: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 19: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 20:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 21: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 22: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 23: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 24: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
						
				}
								
				return componente;
			} 
		}; 
		for(int i=0; i<tabla.getColumnCount(); i++){
			this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
		}
		
		this.tabla.setRowSorter(trsfiltro);  
				
    }
	
	KeyListener op_filtro_folio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 1));
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 2));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener op_filtro_establecimiento = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 3));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};

}
