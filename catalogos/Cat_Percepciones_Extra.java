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

import objetos.Obj_Persecciones_Extra;

@SuppressWarnings("serial")
public class Cat_Percepciones_Extra extends Cat_Root {
	
	private JCheckBox chb_habilitar = new JCheckBox("Habilitar");
	private JCheckBox chb_todos = new JCheckBox("Seleccionar");
    
	private String lista[] = {"                   S/N","1","2","3","4","5","6","7"};
	private String lista1[] = {"","1","2","3","4","5","6","7"};
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JComboBox cmb_layaout_dia = new JComboBox(lista);
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JComboBox cmb_tabla_dia = new JComboBox(lista1);
	
	public DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Persecciones_Extra().get_tabla_model(),
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Bono", "Día Extra", "Cantidad Dias"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
	    	java.lang.Object.class
	    	
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
        	 	case 3 : 
        	 		if(chb_habilitar.isSelected()){
        	 			return true; 
        	 		}
        	 		return false;
        	 	case 4 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) == true){
        	 			tabla_model.setValueAt("", fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
	public TableColumn columna_dia = tabla.getColumnModel().getColumn(5);
    
    public Cat_Percepciones_Extra(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/hand_pro_icon&16.png"));
		this.setTitle("Percepciones Extras");
		
		this.panel.add(cmbEstablecimientos).setBounds(463,35,210,20);
		this.panel.add(chb_habilitar).setBounds(715,35,90,20);
		this.panel.add(chb_todos).setBounds(820,35,80,20);
		this.panel.add(cmb_layaout_dia).setBounds(905,35,140,20);
		
		this.columna_dia.setCellEditor(new javax.swing.DefaultCellEditor(cmb_tabla_dia));
		
		this.panel.add(scroll_tabla).setBounds(30,60,1035,615);
		
		this.cont.add(panel);
		
		this.init_tabla();
		
		this.btn_guardar.addActionListener(op_guardar);
		this.btn_refrescar.setVisible(false);
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		this.cmb_layaout_dia.addActionListener(op_dia);
		this.chb_todos.addActionListener(op_todos);
		
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
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de bancos?") == 0){
					Obj_Persecciones_Extra persecciones = new Obj_Persecciones_Extra();
					if(persecciones.guardar(tabla_guardar())){
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
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Float.parseFloat("0"); 
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
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
											
				}
			}
		}
		return matriz;
	}
	
	private String valida_tabla(){
		String error = "";
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=3; j<tabla.getColumnCount()-1; j++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,j).toString())){
						switch(j){
							case 3: 
								error += "   La celda de la columna Bono no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
							break;
							case 5:
								error += "   La celda de la columna Cantidad de días no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
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
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(72);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(360);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(210);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(210);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(150);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(150);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(80);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(80);
    	this.tabla.getColumnModel().getColumn(5).setMaxWidth(135);
    	this.tabla.getColumnModel().getColumn(5).setMinWidth(135);
    	
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
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
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
	
	ActionListener op_dia = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(tabla_model.getValueAt(i,4).toString()) != true){
					tabla_model.setValueAt("", i,5);
				}else{
					if(cmb_layaout_dia.getSelectedIndex() == 0){
						tabla_model.setValueAt("", i,5);
					}else{
						tabla_model.setValueAt(cmb_layaout_dia.getSelectedIndex(), i,5);
					}
					
				}

			}			
		}
	};
	
	ActionListener op_todos = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chb_todos.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("true"), j,4);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("false"), j,4);
					tabla_model.setValueAt("", j,5);
				}
			}
			
		}
	};
	
		
	 
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Percepciones_Extra().setVisible(true);
		}catch(Exception e){
			System.out.println(e);
		}
	}


}
