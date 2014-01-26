package cuadrantes;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import objetos.Obj_Tabla_Opciones_Respuesta;

@SuppressWarnings("serial")
public class Cat_Tabla_Opciones_Respuesta extends JFrame {
	
	public Container cont = getContentPane();
	public JLayeredPane panel = new JLayeredPane();
	
	public JTextField txt_opcion_multiple = new JTextField();
	
	public JCheckBox chb_status = new JCheckBox("Status",true);
	
	public JTextField txt_descripcion = new JTextField();

	public JButton btn_salir = new JButton("Salir");
	public JButton btn_deshacer = new JButton("Deshacer");
	public JButton btn_editar = new JButton("Editar");
	public JButton btn_guardar = new JButton("Guardar");

	private DefaultTableModel tabla_model = new DefaultTableModel(null,
            new String[]{"Folio", "Descripción"}
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
        	 } 				
 			return false;
 		}
                
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane panelScroll = new JScrollPane(tabla);
	
	public JButton btn_buscar_multiple = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	public JButton btn_agregar = new JButton("Agregar");
	public JButton btn_subir = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	public JButton btn_bajar = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	public JButton btn_remover = new JButton("Remover");

	public Cat_Tabla_Opciones_Respuesta() {
		this.init();
	}
	
	public Cat_Tabla_Opciones_Respuesta(String opcion_multiple){
		this.init();
		this.txt_opcion_multiple.setText(opcion_multiple.trim());
		
		Object[][] lista_datos = new Obj_Tabla_Opciones_Respuesta().get_tabla_model(opcion_multiple.trim());
		
		Object[] fila = new Object[tabla.getColumnCount()];
		for(int i=0; i<lista_datos.length; i++){
			fila[0] = lista_datos[i][0];
			fila[1] = lista_datos[i][1];
			
			tabla_model.addRow(fila);
		}
		
		if(lista_datos.length > 0){
			txt_descripcion.setEditable(false);
			btn_agregar.setEnabled(false);
			btn_remover.setEnabled(false);
			btn_subir.setEnabled(false);
			btn_bajar.setEnabled(false);
			btn_guardar.setEnabled(false);
			tabla.setEnabled(false);
		}
	}
	
	public void init(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/opciones_respuesta_icon&16.png"));
		this.setTitle("Opciones múltiples de Respuesta");
		this.panel.setBorder(BorderFactory.createTitledBorder("Opcion múltiples de Respuesta"));
		
		int y=30;
		
		this.panel.add(new JLabel("Opciones Múltiples:")).setBounds(20,y,100,20);
		this.panel.add(txt_opcion_multiple).setBounds(120, y, 210, 20);
		
		this.panel.add(btn_buscar_multiple).setBounds(335,y,32,20);
		
		this.panel.add(chb_status).setBounds(370,y,75,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(20,y+=25,70,20);
		this.panel.add(txt_descripcion).setBounds(120,y,210,20);
		
		this.panel.add(btn_agregar).setBounds(335,y,90,20);
		
		this.panel.add(btn_bajar).setBounds(260,y+=25,30,20);
		this.panel.add(btn_subir).setBounds(300,y,30,20);
		this.panel.add(btn_remover).setBounds(335,y,90,20);
		
		this.panel.add(panelScroll).setBounds(20,y+=25,405,250);
		
		this.txt_opcion_multiple.setEditable(false);

		this.panel.add(btn_salir).setBounds(20,y+=260,90,20);
		this.panel.add(btn_deshacer).setBounds(125,y,90,20);
		this.panel.add(btn_editar).setBounds(230,y,90,20);
		this.panel.add(btn_guardar).setBounds(335,y,90,20);
		
		this.cont.add(panel);
		
		this.tabla.getColumnModel().getColumn(0).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(100);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(370);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(370);
		
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
				}
			return lbl; 
			} 
		}; 
		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 

		this.btn_buscar_multiple.addActionListener(op_buscar_multiple);
		this.btn_agregar.addActionListener(op_agregar);
		this.btn_subir.addActionListener(op_desplzar);
		this.btn_bajar.addActionListener(op_desplzar);
		this.btn_remover.addActionListener(op_remover);
				
		this.btn_salir.addActionListener(op_salir);
		this.btn_deshacer.addActionListener(op_deshacer);
		this.btn_editar.addActionListener(op_editar);
		this.btn_guardar.addActionListener(op_guardar);
		
		this.txt_descripcion.addKeyListener(agregar_action);
		
		this.setSize(460,425);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener op_buscar_multiple = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Opciones_Multiples().setVisible(true);
			dispose();
		}
	};
	
	ActionListener op_agregar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txt_descripcion.getText().equals("")){
				String[] row = new String[2];
				row[0] = tabla.getRowCount()+1+" ";
				row[1] = "   "+txt_descripcion.getText().toUpperCase();
				txt_descripcion.setText("");
				txt_descripcion.requestFocus();
				tabla_model.addRow(row);
				
			}else{
				JOptionPane.showMessageDialog(null,"El campo de texto descripción está vacío","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_desplzar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.getRowCount()>1){
				if(tabla.isRowSelected(tabla.getSelectedRow())){
					if(arg0.getSource().equals(btn_subir)){
						if(tabla.getSelectedRow() != 0){
							Object primero = tabla_model.getValueAt(tabla.getSelectedRow(),1);
							Object segundo = tabla_model.getValueAt(tabla.getSelectedRow()-1,1);
							
							tabla_model.setValueAt(primero,tabla.getSelectedRow()-1,1);
							tabla_model.setValueAt(segundo,tabla.getSelectedRow(),1);	
							tabla.setRowSelectionInterval(tabla.getSelectedRow()-1,tabla.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btn_bajar)){
						if(tabla.getSelectedRow()+1 < tabla.getRowCount()){
							Object primero = tabla_model.getValueAt(tabla.getSelectedRow(),1);
							Object segundo = tabla_model.getValueAt(tabla.getSelectedRow()+1,1);
							
							tabla_model.setValueAt(primero,tabla.getSelectedRow()+1,1);
							tabla_model.setValueAt(segundo,tabla.getSelectedRow(),1);	
							tabla.setRowSelectionInterval(tabla.getSelectedRow()+1,tabla.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}		
				}else{
					JOptionPane.showMessageDialog(null,"No esta seleccionada ninguna fila!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			
			}else{
				JOptionPane.showMessageDialog(null,"No hay filas que desplazar!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_editar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txt_descripcion.setEditable(true);
			btn_agregar.setEnabled(true);
			btn_remover.setEnabled(true);
			btn_subir.setEnabled(true);
			btn_bajar.setEnabled(true);
			btn_guardar.setEnabled(true);
			tabla.setEnabled(true);
		}
		
	};
	
	ActionListener op_guardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(tabla.getRowCount() == 0){
				JOptionPane.showMessageDialog(null, "La tabla no tiene registros","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de respuestas?") == 0){
					Obj_Tabla_Opciones_Respuesta respuesta = new Obj_Tabla_Opciones_Respuesta();
					if(respuesta.guardar(txt_opcion_multiple.getText(), registros_tabla())){
						limpiar();
						JOptionPane.showMessageDialog(null, "La tabla de respuesta se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	public String[][] registros_tabla(){
		String[][] registros = new String[tabla.getRowCount()][2];
		for(int i=0; i<tabla.getRowCount(); i++){
			registros[i][0] = tabla_model.getValueAt(i, 0)+"".trim(); 
			registros[i][1] = tabla_model.getValueAt(i, 1)+"".toUpperCase().trim(); 
		}
		return registros; 
	}
	
	ActionListener op_remover = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			if(tabla.isRowSelected(tabla.getSelectedRow())){
				tabla_model.removeRow(tabla.getSelectedRow());
				for(int i=0; i<tabla.getRowCount(); i ++){
					tabla_model.setValueAt(i+1,i,0);
				}
			}else{
				JOptionPane.showMessageDialog(null,"No ha seleccionado ningúna fila para remover!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	public void limpiar() {
		txt_opcion_multiple.setText("");
		txt_descripcion.setText("");
		while(tabla_model.getRowCount() > 0){
			tabla_model.removeRow(0);
		}
	}
	
	ActionListener op_deshacer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
		}
	};
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_Opciones_Respuesta().setVisible(true);
		}
	};
	
	KeyListener agregar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btn_agregar.doClick();
				txt_descripcion.requestFocus();
			}
		}
	};
	
}
