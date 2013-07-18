package catalogos;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Deduccion_Inasistencia;

@SuppressWarnings("serial")
public class Cat_Deduccion_Inasistencia extends Cat_Root{
	
	private JCheckBox chb_inpuntualidad = new JCheckBox("Inpuntualidad");
	private JCheckBox chb_falta = new JCheckBox("Falta");
	private JCheckBox chb_gafete = new JCheckBox("Gafete");
	private JCheckBox chb_habilitar = new JCheckBox("Habilitar");
	
	private String lista[] = {"      S/N","1","2","3","4","5","6","7"};
	private String lista1[] = {"","1","2","3","4","5","6","7"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox cmb_layaout_dias = new JComboBox(lista);
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComboBox cmb_tabla_dias = new JComboBox(lista1);
  
    @SuppressWarnings({ "rawtypes", "unchecked", })
    private JComboBox cmb_layaout_gafete = new JComboBox(lista);
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComboBox cmb_tabla_gafete = new JComboBox(lista1);
    	
    private DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Deduccion_Inasistencia().get_tabla_model(),
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Inpuntualidad", "Falta", "Días Falta", "Asistencia", "Gafete", "Días Gafete","Extra" }
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class,
	    	java.lang.Boolean.class,
	    	java.lang.Boolean.class,
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
        	 	case 3 : return true; 
        	 	case 4 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) != true){
        	 			tabla_model.setValueAt(1, fila, 5);
        	 			return true; 
        	 		}else {
        	 			tabla_model.setValueAt("", fila, 5);
        	 			tabla_model.setValueAt(false, fila, 6);
        	 			return true; 
        	 		}
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 6 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}else{
        	 			return false;
        	 		}
        	 	case 7 :
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,7).toString()) != true){
        	 			tabla_model.setValueAt(1, fila, 8);
        	 			return true;
        	 		}else {
        	 			tabla_model.setValueAt("", fila, 8);
        	 			return true;
        	 		}
        	 	case 8 : return true;
        	 	case 9 :
        	 		if(chb_habilitar.isSelected()){
        	 			return true;
        	 		}else{
        	 			return false;
        	 		}
        	 		
        	 } 				
 			return false;
 		}
		
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
    
	public TableColumn columna_dia_falta = tabla.getColumnModel().getColumn(5);
	public TableColumn columna_dia_gafete = tabla.getColumnModel().getColumn(8);
    
	public Cat_Deduccion_Inasistencia(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/hand_contra_icon&16.png"));
		this.setTitle("Deducción por Inasistencia");
		
		this.panel.add(cmbEstablecimientos).setBounds(463,35,150,20);
		this.panel.add(chb_inpuntualidad).setBounds(615,35,100,20);
		this.panel.add(chb_falta).setBounds(715,35,60,20);
		this.panel.add(cmb_layaout_dias).setBounds(775,35,65,20);
		this.panel.add(chb_gafete).setBounds(905,35,65,20);
		this.panel.add(cmb_layaout_gafete).setBounds(975,35,65,20);
		this.panel.add(chb_habilitar).setBounds(1050,35,65,20);
		
		this.columna_dia_falta.setCellEditor(new javax.swing.DefaultCellEditor(cmb_tabla_dias));
		this.columna_dia_gafete.setCellEditor(new javax.swing.DefaultCellEditor(cmb_tabla_gafete));
		
		this.panel.add(scroll_tabla).setBounds(30,60,1125,615);
		
		this.cont.add(panel);
		
		this.init_tabla();
		
		this.btn_guardar.addActionListener(op_guardar);
			this.btn_guardar.setToolTipText("Guardar");
		this.btn_refrescar.setVisible(false);
		this.chb_inpuntualidad.addActionListener(op_inpuntualidad);
		this.chb_falta.addActionListener(op_faltas);
		this.cmb_layaout_dias.addActionListener(op_layaout_dia);
		this.chb_gafete.addActionListener(op_gafete);
		this.cmb_layaout_gafete.addActionListener(op_layaout_gafete);
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener op_guardar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de bancos?") == 0){
					Obj_Deduccion_Inasistencia inasistencia = new Obj_Deduccion_Inasistencia();
					if(inasistencia.guardar(tabla_guardar())){
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
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][11];
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
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
							matriz[i][j] = Boolean.parseBoolean("false");
						}else{
							matriz[i][j] = Boolean.parseBoolean(tabla_model.getValueAt(i,j).toString());
						}
						break;
					case 4: 
						if(tabla_model.getValueAt(i,j).toString().equals("")){
							matriz[i][j] = Boolean.parseBoolean("false");
						}else{
							matriz[i][j] = Boolean.parseBoolean(tabla_model.getValueAt(i,j).toString());
						}
						break;
					case 5: 
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Integer.parseInt("0"); 
						}else{
							matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 6: 
						if(tabla_model.getValueAt(i,j).toString().equals("")){
							matriz[i][j] = Boolean.parseBoolean("false");
						}else{
							matriz[i][j] = Boolean.parseBoolean(tabla_model.getValueAt(i,j).toString());
						}
						break;
					case 7: 
						if(tabla_model.getValueAt(i,j).toString().equals("")){
							matriz[i][j] = Boolean.parseBoolean("false");
						}else{
							matriz[i][j] = Boolean.parseBoolean(tabla_model.getValueAt(i,j).toString());
						}
						break;
					case 8: 
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Integer.parseInt("0"); 
						}else{
							matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 9: 
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Float.parseFloat("0"); 
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
						
				}
			}
		}
		return matriz;
	}
	
	private String valida_tabla(){
		String error = "";
		for(int i=0; i<tabla.getRowCount(); i++){
			try{
				if(!isNumeric(tabla_model.getValueAt(i,9).toString())){
					error += "   La celda de la columna Extra no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
				}
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		return error;
	}
	
	@SuppressWarnings("unchecked")
	public void init_tabla(){
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(72);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(360);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(150);
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(100);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(70);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(70);
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(7).setMaxWidth(70);
		this.tabla.getColumnModel().getColumn(7).setMinWidth(70);
		this.tabla.getColumnModel().getColumn(8).setMaxWidth(70);
		this.tabla.getColumnModel().getColumn(8).setMinWidth(70);
		this.tabla.getColumnModel().getColumn(9).setMaxWidth(90);
		this.tabla.getColumnModel().getColumn(9).setMinWidth(90);
    	
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
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 1: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 2:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 5: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 6: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 7: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 8: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 9: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;

				}
					
				return componente;
			} 
		}; 
	

		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(5).setCellRenderer(render);
		this.tabla.getColumnModel().getColumn(6).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(7).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(8).setCellRenderer(render);
		this.tabla.getColumnModel().getColumn(9).setCellRenderer(render);
		
		this.tabla.setRowSorter(trsfiltro);  
		
	}
	
	 private static boolean isNumeric(String cadena){
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
	
	ActionListener op_inpuntualidad = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chb_inpuntualidad.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("true"), j,3);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("false"), j,3);					
				}
			}
			
		}
	};
	
	ActionListener op_faltas = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chb_falta.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("true"), j,4);
					tabla_model.setValueAt(1, j,5);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("false"), j,4);
					tabla_model.setValueAt("", j,5);
				}
				cmb_layaout_dias.setSelectedIndex(0);
			}
			
		}
	};
	
	ActionListener op_layaout_dia = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(tabla_model.getValueAt(i,4).toString()) != true){
					tabla_model.setValueAt("", i,5);
				}else{
					if(Integer.parseInt(cmb_layaout_dias.getSelectedItem()+"") == 0){
						tabla_model.setValueAt("", i,5);
					}else{
						tabla_model.setValueAt(cmb_layaout_dias.getSelectedIndex(), i,5);
					}
				}
			}			
		}
	};
	
	ActionListener op_gafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chb_gafete.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("true"), j,7);
					tabla_model.setValueAt(1, j, 8);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("false"), j,7);
					tabla_model.setValueAt("", j, 8);
				}
				cmb_layaout_gafete.setSelectedIndex(0);
			}
			
		}
	};
	
	ActionListener op_layaout_gafete = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(tabla_model.getValueAt(i,7).toString()) != true){
					tabla_model.setValueAt("", i,8);
				}else{
					if(cmb_layaout_gafete.getSelectedIndex() == 0){
						tabla_model.setValueAt("", i,8);
					}else{
						tabla_model.setValueAt(cmb_layaout_gafete.getSelectedIndex(), i,8);
					}
					
				}
			}			
		}
	};
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Deduccion_Inasistencia().setVisible(true);
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
