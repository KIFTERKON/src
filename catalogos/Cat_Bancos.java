package catalogos;

import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Bancos;

@SuppressWarnings("serial")
public class Cat_Bancos extends Cat_Root {

	private JCheckBox chbHabilitarBanamex = new JCheckBox("Habilitar");
	private JCheckBox chbHabilitarBanorte = new JCheckBox("Habilitar");
	private JCheckBox chbNegativos = new JCheckBox("Valores Negativos");
	
	private JTextField txtBanamex = new JTextField();
	private JTextField txtBanorte = new JTextField();
	private JTextField txtTotales = new JTextField();
	    
	private DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Bancos().get_tabla_model(),
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
		this.btn_refrescar.setVisible(false);
			
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		this.chbNegativos.addActionListener(op_negativos);
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.addWindowListener(op_cerrar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
	
	private Object[][] tabla_guardar(){
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
	
	private String valida_tabla(){
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
}