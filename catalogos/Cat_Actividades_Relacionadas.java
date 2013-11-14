package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Actividades_Relacionadas;
import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Critico;
@SuppressWarnings("serial")
public class Cat_Actividades_Relacionadas extends JFrame{
        Container con = getContentPane();
        JLayeredPane panel = new JLayeredPane();
        
//     ESTE BOTON SE CARGA DENTRO DE LA TABLA-------------------------------
        JButton button = new JButton("CUADRANTE");
//        JDateChooser txtFechaPermiso = new JDateChooser();
        
        JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
        JButton btnNuevo = new JButton("Nuevo");
        
        JButton btnderecha = new JButton(new ImageIcon("Iconos/right_icon&16.png"));
        JButton btnizquierda = new JButton(new ImageIcon("Iconos/left_icon&16.png"));
        
        JButton btnSalir = new JButton("Salir");
        JButton btnDeshacer = new JButton("Deshacer");
        JButton btnEditar = new JButton("Editar");
        JButton btnGuardar = new JButton("Guardar");
        
        JButton btnAgregar = new JButton("Agregar");
        JButton btnQuitar = new JButton("Quitar");
        
        JTextField txtFolioProyecto = new JTextField();
        JTextField txtProyecto = new JTextField();
        
        JTextArea txaDescripcion = new JTextArea(4,4);
        JScrollPane Descripcion = new JScrollPane(txaDescripcion);

        JCheckBox  chbStatus = new JCheckBox("Status",true);

        String nivel_critico[] = new Obj_Nivel_Critico().Combo_Nivel_Critico();
        @SuppressWarnings({ "rawtypes", "unchecked" })
        JComboBox cmbNivelCritico = new JComboBox(nivel_critico);
        
        DefaultTableModel tabla_model = new DefaultTableModel(null,
            new String[]{ "Folio", "Descripcion"," * ", " % ", "Cuadrante"}
                        ){
             @SuppressWarnings("rawtypes")
                Class[] types = new Class[]{
                    java.lang.Integer.class,
                    java.lang.String.class,
                    java.lang.Boolean.class,
                    java.lang.String.class,
                    javax.swing.JButton.class
         };
             
             @SuppressWarnings({ "rawtypes", "unchecked" })
                public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
             
         public boolean isCellEditable(int fila, int columna){
                 switch(columna){
                         case 0 : return false;
                         case 1 : return false; 
                         case 2 : return true;
                         case 3 : if(Boolean.parseBoolean(tabla_model.getValueAt(fila,2).toString()) != true){
			            	 			return false;
			            	 		}else {
			            	 			return true;
			            	 		}
                         case 4 : return true; 
                 }                                 
                         return false;
            }
        };
        
        JTable table = new JTable(tabla_model);
        JScrollPane panel_scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        DecimalFormat formato = new DecimalFormat("#0.00000");
        
        int filaseleccionada = 0;
		int folioseleccionado = 0;
        
        public Cat_Actividades_Relacionadas(){
        	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
        	this.setTitle("Actividades Relacionadas");
        	panel.setBorder(BorderFactory.createTitledBorder("Relacion De Actividades"));
        	
//        	txtFechaPermiso.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
        	componentes();
        	
//        	AGREGAR BOTON ALA TABLA 
        	table.getColumn("Cuadrante").setCellRenderer(new ButtonRenderer());
    		table.getColumn("Cuadrante").setCellEditor(new ButtonEditor(new JCheckBox()));
    		
//    		ACCION DEL BOTON DE LA table
    		button.addActionListener(opFiltroEntable);
    		button.setBackground(Color.BLUE);
    		
    		table.addKeyListener(op_key);
        	
        	btnSalir.addActionListener(opSalir);
        	btnNuevo.addActionListener(opNuevo);
        	btnAgregar.addActionListener(opAgregar);
        	btnQuitar.addActionListener(opQuitar);
        	btnGuardar.addActionListener(opGuardar);
        	btnBuscar.addActionListener(opBuscar);
        	btnEditar.addActionListener(opEditar);
        	btnDeshacer.addActionListener(opDeshacer);
        	
        	btnizquierda.addActionListener(opLeft);
    		btnderecha.addActionListener(opRigth);
    		
    		btnAgregar.setEnabled(false);
        	btnQuitar.setEnabled(false);
        	btnEditar.setEnabled(false);
        	btnGuardar.setEnabled(false);
        	
        	txtFolioProyecto.setDocument(new JTextFieldLimit(9));
        	txtFolioProyecto.addKeyListener(op_key_buscar);
        }
        
    	KeyListener op_key = new KeyListener() {
    		public void keyTyped(KeyEvent e) {
    		}
    		public void keyReleased(KeyEvent e) {
    			new Cat_Filtro_Actividades().porcentajeDefault();
    		}
    		public void keyPressed(KeyEvent e) {
    		}
    	};
    	
    	KeyListener op_key_buscar = new KeyListener() {
    		public void keyTyped(KeyEvent e) {
    		}
    		public void keyReleased(KeyEvent e) {
    			btnBuscar.doClick();
    		}
    		public void keyPressed(KeyEvent e) {
    		}
    	};
    	
        ActionListener opFiltroEntable = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				filaseleccionada = table.getSelectedRow();
    			folioseleccionado =  Integer.parseInt(table.getValueAt(filaseleccionada, 0).toString());
    			
    			new Cat_Filtro_Cuadrante_Actividad().setVisible(true);
			}
		};

		ActionListener opSalir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
        
        ActionListener opNuevo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				enablesTodos(true);
				txtFolioProyecto.setText(new Obj_Actividades_Relacionadas().nuevo()+"");
				txtFolioProyecto.setEditable(false);
				txtProyecto.requestFocus();
				
				chbStatus.setSelected(true);
				chbStatus.setEnabled(false);
			}
		};
        
		
		ActionListener opAgregar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Cat_Filtro_Actividades().setVisible(true);
			}
		};	
		
		ActionListener opQuitar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getRowCount()>0){
					
					if(table.isRowSelected(table.getSelectedRow())){
							tabla_model.removeRow(table.getSelectedRow());
							new Cat_Filtro_Actividades().porcentajeDefault();
					}else{
						JOptionPane.showMessageDialog(null,"No esta seleccionada ninguna fila!","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas que remover!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		};
		
		ActionListener opGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getRowCount()==0){
					JOptionPane.showMessageDialog(null,"No ha asignado ningún registro en la table", "Advertencia!", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					guardar();
				}
			}
		};
		
		public void guardar(){
			if(ValidaError().equals("")){
				if(new Obj_Actividades_Relacionadas().existe(Integer.parseInt(txtFolioProyecto.getText()))){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						Obj_Actividades_Relacionadas relacion = new Obj_Actividades_Relacionadas();
						
						relacion.setFolio(Integer.parseInt(txtFolioProyecto.getText()));
						relacion.setProyecto(txtProyecto.getText());
						relacion.setDescripcion(txaDescripcion.getText());
						relacion.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
						relacion.setStatus(chbStatus.isSelected() ? 1 : 0);
						
						if(relacion.actualizar(Integer.parseInt(txtFolioProyecto.getText()),Tabla())){
							limpiar();
							JOptionPane.showMessageDialog(null,"El registro se actualizó correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else{
						return;
					}
				}else{
					if(new Obj_Actividades_Relacionadas().existe(txtProyecto.getText().toUpperCase().trim())){
						JOptionPane.showMessageDialog(null,"EL cuadrante con el nombre ya existe debe cambiar el nombre", "Aviso!", JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						Obj_Actividades_Relacionadas ralacion = new Obj_Actividades_Relacionadas();
						
						ralacion.setFolio(Integer.parseInt(txtFolioProyecto.getText()));
						ralacion.setProyecto(txtProyecto.getText());
						ralacion.setDescripcion(txaDescripcion.getText());
						ralacion.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
						ralacion.setStatus(chbStatus.isSelected() ? 1 : 0);
															
						if(ralacion.guardar(Tabla())){
							limpiar();
							JOptionPane.showMessageDialog(null,"El registro se guardó correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "Los Siguientes Campos Son Requeridos:\n "+ValidaError(), "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		ActionListener opBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabla_limpiar();
				if(!txtFolioProyecto.getText().equals("")){
					Obj_Actividades_Relacionadas relacion = new Obj_Actividades_Relacionadas().buscarActividadRelacionada(Integer.parseInt(txtFolioProyecto.getText()));
									
					if(relacion.getProyecto().equals("")){
						JOptionPane.showMessageDialog(null,"No existe el registro: "+txtFolioProyecto.getText(),"Aviso",JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						try {
							String[][] tabla = new Obj_Actividades_Relacionadas().tabla(Integer.parseInt(txtFolioProyecto.getText()));
												
							for(int i=0; i<tabla.length; i++){
								 		Object[] dom = new Object[5];
								 		
								 		dom[0] = tabla[i][0];
								 		dom[1] = tabla[i][1];
								 		dom[2] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
								 		dom[3] = tabla[i][2];
								 		dom[4] = tabla[i][3];
								 		
								 		tabla_model.addRow(dom);
							}
							
							txtProyecto.setText(relacion.getProyecto());
							txaDescripcion.setText(relacion.getDescripcion());
							cmbNivelCritico.setSelectedItem(relacion.getNivel_critico());
							chbStatus.setSelected(relacion.getStatus() == 1 ? true : false);
							
							enablesTodos(false);
							btnEditar.setEnabled(true);
//							Orden_de_Actividades();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Ingrese Un Folio", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		};
		
		ActionListener opLeft = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!txtFolioProyecto.getText().equals("")){
					tabla_limpiar();
					Obj_Actividades_Relacionadas proyecto = new Obj_Actividades_Relacionadas().buscarActividadRelacionada(Integer.parseInt(txtFolioProyecto.getText())-1);
					
					if(proyecto.getProyecto().equals("")){
						JOptionPane.showMessageDialog(null,"No existe el registro: "+(Integer.parseInt(txtFolioProyecto.getText())-1)+"","Aviso",JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						try {
							String[][] tabla = new Obj_Actividades_Relacionadas().tabla(Integer.parseInt(txtFolioProyecto.getText())-1);
												
							for(int i=0; i<tabla.length; i++){
								 		Object[] dom = new Object[5];
								 		
								 		dom[0] = tabla[i][0];
								 		dom[1] = tabla[i][1];
								 		dom[2] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
								 		dom[3] = tabla[i][2];
								 		dom[4] = tabla[i][3];
								 		
								 		tabla_model.addRow(dom);
							}
							txtFolioProyecto.setText(Integer.parseInt(txtFolioProyecto.getText())-1+"");
							txtProyecto.setText(proyecto.getProyecto());
							txaDescripcion.setText(proyecto.getDescripcion());
							cmbNivelCritico.setSelectedItem(proyecto.getNivel_critico());
							chbStatus.setSelected(proyecto.getStatus() == 1 ? true : false);
							
							enablesTodos(false);
							btnEditar.setEnabled(true);
//							Orden_de_Actividades();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Ingrese Un Folio", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		};
		ActionListener opRigth = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!txtFolioProyecto.getText().equals("")){
					tabla_limpiar();
					Obj_Actividades_Relacionadas relacion = new Obj_Actividades_Relacionadas().buscarActividadRelacionada(Integer.parseInt(txtFolioProyecto.getText())+1);
					
					if(relacion.getProyecto().equals("")){
						JOptionPane.showMessageDialog(null,"No existe el registro: "+(Integer.parseInt(txtFolioProyecto.getText())+1)+"","Aviso",JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						try {
							String[][] tabla = new Obj_Actividades_Relacionadas().tabla(Integer.parseInt(txtFolioProyecto.getText())+1);
												
							for(int i=0; i<tabla.length; i++){
								 		Object[] dom = new Object[5];
								 		
								 		dom[0] = tabla[i][0];
								 		dom[1] = tabla[i][1];
								 		dom[2] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
								 		dom[3] = tabla[i][2];
								 		dom[4] = tabla[i][3];
								 		
								 		tabla_model.addRow(dom);
							}
							txtFolioProyecto.setText(Integer.parseInt(txtFolioProyecto.getText())+1+"");
							txtProyecto.setText(relacion.getProyecto());
							txaDescripcion.setText(relacion.getDescripcion());
							cmbNivelCritico.setSelectedItem(relacion.getNivel_critico());
							chbStatus.setSelected(relacion.getStatus() == 1 ? true : false);
							
							enablesTodos(false);
							btnEditar.setEnabled(true);
//							Orden_de_Actividades();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Ingrese Un Folio", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		};
		
		ActionListener opEditar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					enablesTodos(true);
					btnEditar.setEnabled(false);
					txtFolioProyecto.setEditable(false);
			}
		};
		
		ActionListener opDeshacer = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				enablesTodos(true);
				btnEditar.setEnabled(false);
				btnGuardar.setEnabled(false);
				btnAgregar.setEnabled(false);
				btnQuitar.setEnabled(false);
			}
		};
		
		public void tabla_limpiar(){
			while(table.getRowCount() > 0){
				tabla_model.removeRow(0);
			}
		}
		
		public String[][] Tabla(){
			
			String[][] tablas = new String[table.getRowCount()][5];
			
			int renglonesdomingo = table.getRowCount();
			
			int fila=0;
			int i=0;
			
			while(renglonesdomingo > 0){
				
					tablas[i][0] = tabla_model.getValueAt(fila, 0)+"";
					tablas[i][1] = tabla_model.getValueAt(fila, 1)+"";
					tablas[i][2] = tabla_model.getValueAt(fila, 2)+"";
					tablas[i][3] = tabla_model.getValueAt(fila, 3)+"";
					tablas[i][4] = tabla_model.getValueAt(fila, 4)+"";
				i+=1;
				fila+=1;
				renglonesdomingo--;
			}
			
			return tablas;
		}
		
		public String ValidaError(){
			String error ="";
			
				if(txtProyecto.getText().equals("")) error+="-Proyecto\n";
				if(txaDescripcion.getText().equals("")) error+="-Descripcion\n";
				if(cmbNivelCritico.getSelectedIndex()==0) error+="-Nivel Gerarquico\n";
				
			return error;
		}
		
		public void limpiar(){
			enablesTodos(false);
			txtFolioProyecto.setEditable(true);
			txtFolioProyecto.requestFocus();
			txtFolioProyecto.setText("");
			txtProyecto.setText("");
			txaDescripcion.setText("");
			cmbNivelCritico.setSelectedIndex(0);
			
//			table.setEnabled(false);
			
			while(table.getRowCount() > 0){
				tabla_model.removeRow(0);
			}
			
		}
		
		public void enablesTodos(boolean variable){
			txtFolioProyecto.setEditable(variable);
			txtProyecto.setEditable(variable);
			txaDescripcion.setEditable(variable);
			cmbNivelCritico.setEnabled(variable);
			
			btnAgregar.setEnabled(variable);
			btnQuitar.setEnabled(variable);
        	btnGuardar.setEnabled(variable);
		}
		
    	public void componentes(){
    		 int x=30;        int y=40; 
             
             panel.add(new JLabel("Folio: ")).setBounds( x, y, 60, 20);
             panel.add(txtFolioProyecto).setBounds( x+70, y, 80, 20);
             panel.add(btnBuscar).setBounds( x+150, y, 30, 20);
             panel.add(btnNuevo).setBounds( x+180, y, 70, 20);
             panel.add(chbStatus).setBounds( x+250, y, 70, 20);
             
             panel.add(btnizquierda).setBounds( x+320, y, 30, 20);
             panel.add(btnderecha).setBounds( x+360, y, 30, 20);
             
             panel.add(btnAgregar).setBounds(x+410, y, 80, 20);
             panel.add(btnQuitar).setBounds(x+500, y, 80, 20);
             
             panel.add(new JLabel("Proyecto: ")).setBounds( x, y+=25, 60, 20);
             panel.add(txtProyecto).setBounds( x+70, y, 320, 20);
             
             panel.add(new JLabel("Descripcion: ")).setBounds( x, y+=25, 80, 20);
             panel.add(Descripcion).setBounds( x+70, y, 320, 280);
             
             panel.add(new JLabel("Nivel Critico: ")).setBounds( x, y+=290, 100, 20);
             panel.add(cmbNivelCritico).setBounds( x+70, y, 320, 20);
             
             panel.add(btnSalir).setBounds( x, y+=35, 80, 20);
             panel.add(btnDeshacer).setBounds( x+=100, y, 80, 20);
             panel.add(btnEditar).setBounds( x+=100, y, 80, 20);
             panel.add(btnGuardar).setBounds( x+=100, y, 80, 20);
             
             panel.add(panel_scroll).setBounds( 440, 65, 700, 372);
             
             txaDescripcion.setLineWrap(true);
             txaDescripcion.setDocument(new JTextFieldLimit(400));
             
             this.con.add(panel);
             this.setSize(1200,500);
    		
    		this.table.getTableHeader().setReorderingAllowed(false) ;
    		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		
    		this.table.getColumnModel().getColumn(0).setMaxWidth(50);
    		this.table.getColumnModel().getColumn(0).setMinWidth(40);
    		this.table.getColumnModel().getColumn(1).setMaxWidth(620);
    		this.table.getColumnModel().getColumn(1).setMinWidth(380);
    		this.table.getColumnModel().getColumn(2).setMaxWidth(30);
    		this.table.getColumnModel().getColumn(2).setMinWidth(30);
    		this.table.getColumnModel().getColumn(3).setMaxWidth(50);
    		this.table.getColumnModel().getColumn(3).setMinWidth(50);
    		this.table.getColumnModel().getColumn(4).setMaxWidth(350);
    		this.table.getColumnModel().getColumn(4).setMinWidth(190);
    		
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
    						if(table.getSelectedRow() == row){
    							((JComponent) componente).setOpaque(true); 
    							componente.setBackground(new java.awt.Color(186,143,73));
    						}
    						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
    						break;
    					case 1: 
    						componente = new JLabel(value == null? "": value.toString());
    						if(row %2 == 0){
    							((JComponent) componente).setOpaque(true); 
    							componente.setBackground(new java.awt.Color(177,177,177));	
    						}
    						if(table.getSelectedRow() == row){
    							((JComponent) componente).setOpaque(true); 
    							componente.setBackground(new java.awt.Color(186,143,73));
    						}
    						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
    						break;
    					case 2:
    						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
    						if(row%2==0){
    							((JComponent) componente).setOpaque(true); 
    							componente.setBackground(new java.awt.Color(177,177,177));	
    						}
    						if(table.getSelectedRow() == row){
    							((JComponent) componente).setOpaque(true); 
    							componente.setBackground(new java.awt.Color(186,143,73));
    						}
    						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
    						break;
    					case 3: 
    						componente = new JLabel(value == null? "": value.toString());
    						if(row %2 == 0){
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
    						if(row %2 == 0){
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
    	
    		this.table.getColumnModel().getColumn(0).setCellRenderer(render); 
    		this.table.getColumnModel().getColumn(1).setCellRenderer(render); 
    		this.table.getColumnModel().getColumn(2).setCellRenderer(render);
    		this.table.getColumnModel().getColumn(3).setCellRenderer(render);
    		this.table.getColumnModel().getColumn(4).setCellRenderer(render);
    		
    	}
        
    	class ButtonRenderer extends JButton implements TableCellRenderer {
    		public ButtonRenderer() {
    			setOpaque(true);
    		}
    		
    		public Component getTableCellRendererComponent(JTable table, Object value,
    		boolean isSelected, boolean hasFocus, int row, int column) {
    			setText((value == null) ? "" : value.toString());
    			return this;
    		}
    	}
    	
    	class ButtonEditor extends DefaultCellEditor {
    		private String label;
    		public ButtonEditor(JCheckBox checkBox) {
    			super(checkBox);
    		}
    		
    		public Component getTableCellEditorComponent(JTable table, Object value,
    		boolean isSelected, int row, int column) {
    			label = (value == null) ? "" : value.toString();
//    			txtFechaPermiso.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
    			button.setText(label);
    			return button;
//    			return txtFechaPermiso;
    		}
    		
    		public Object getCellEditorValue() {
    			return new String(label);
    		}
    	}
    	
        public static void main (String [] arg){
                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        new Cat_Actividades_Relacionadas().setVisible(true);
                } catch(Exception e){
                        e.printStackTrace();
                }
        }

    	public class Cat_Filtro_Actividades extends JFrame {
    		
    		Container cont = getContentPane();
    		JLayeredPane campo = new JLayeredPane();
    		
    		String dia = "";
    		
    		Object[][] MatrizFiltro ;
    		
    		Object[][] getTablaFiltro = getTablaFiltro();
    		DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
    	            new String[]{"Folio", "Actividad","Nivel Crítico",""}
    				){
    		     @SuppressWarnings("rawtypes")
    			Class[] types = new Class[]{
    		    	java.lang.Integer.class,
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
    		
    		JTextField txtFolio = new JTextField();
    		JTextField txtNombre_Completo = new JTextField();
    		
    		JButton btnAgregar = new JButton("Agregar");
    		
    		@SuppressWarnings({ "rawtypes", "unchecked" })
    		
    		public Cat_Filtro_Actividades() {
    			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
    			setTitle("Filtro de Actividades");
    			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Actividades"));
    			trsfiltro = new TableRowSorter(modeloFiltro); 
    			tablaFiltro.setRowSorter(trsfiltro);  
    			
    			campo.add(scroll).setBounds(15,43,994,360);
    			
    			campo.add(txtFolio).setBounds(15,20,40,20);
    			campo.add(txtNombre_Completo).setBounds(56,20,800,20);
    			campo.add(btnAgregar).setBounds(920,20,80,20);
    			
    			cont.add(campo);
    			
    			Object[] espacio = getUltimoRow();
    			
    			modeloFiltro.addRow(espacio);
    			
    				if(table.getRowCount() > 0){
    					modeloFiltro.setValueAt(false, 0, 3);
    					modeloFiltro.setValueAt(false, tablaFiltro.getRowCount()-1, 3);
    				}
    			
    			tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
    			tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
    			tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(800);
    			tablaFiltro.getColumnModel().getColumn(1).setMinWidth(800);
    			tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
    			tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
    			
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
    							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							if(table.getSelectedRow() == row){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
    							break;
    						case 1: 
    							componente = new JLabel(value == null? "": value.toString());
    							if(row %2 == 0){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(177,177,177));	
    							}
    							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							if(table.getSelectedRow() == row){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
    							break;
    						case 2:
    							componente = new JLabel(value == null? "": value.toString());
    							if(row %2 == 0){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(177,177,177));	
    							}
    							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							if(table.getSelectedRow() == row){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
    							break;
    						case 3: 
    							componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
    							if(row%2==0){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(177,177,177));	
    							}
    							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							if(table.getSelectedRow() == row){
    								((JComponent) componente).setOpaque(true); 
    								componente.setBackground(new java.awt.Color(186,143,73));
    							}
    							((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
    							break;
    						
    					}
    						
    					return componente;
    				} 
    			}; 
    		
    			tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
    			tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
    			tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
    			tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
    			
    			txtFolio.addKeyListener(opFiltroFolio);
    			txtNombre_Completo.addKeyListener(opFiltroNombre);
    			
    			btnAgregar.addActionListener(opAgregar);
    			
    			setSize(1024,450);
    			setResizable(false);
    			setLocationRelativeTo(null);
    			
    		}
    		
    		ActionListener opAgregar = new ActionListener() {
    			@SuppressWarnings({ "unchecked" })
    			public void actionPerformed(ActionEvent arg0) {
    				
    				if(tablaFiltro.isEditing()){
    		 			tablaFiltro.getCellEditor().stopCellEditing();
    				}
    				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
    				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
    				
    				txtFolio.setText("");
    				txtNombre_Completo.setText("");
    				
    				Object[] fila = new Object[5];
			 		if(tabla_model.getRowCount()>0){
			 			
			 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
			 				int repetido = 0;
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					fila[0] = posicion;
									fila[1] = modeloFiltro.getValueAt(i, 1);
									fila[2] = false;
									fila[3] = "";
 								fila[4] = "";
									
			 					for(int j=0; j<tabla_model.getRowCount();){
			 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(tabla_model.getValueAt(j,0).toString().trim())){
			 							j++;
			 							repetido++;
			 							if(j==tabla_model.getRowCount() && repetido > 0){
			 								if(JOptionPane.showConfirmDialog(null, "La .: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
			 									
			 									tabla_model.addRow(fila);
			 									
						 						j++;
			 								}else{
			 									j++;
			 								}
			 							}
			 							if(j==tabla_model.getRowCount() && repetido == 0){
		 									tabla_model.addRow(fila);
		 									j++;
			 							}
			 						}else{
			 							j++;
			 							if(j==tabla_model.getRowCount() && repetido > 0){
			 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
			 									tabla_model.addRow(fila);
						 						j++;
			 								}else{
			 									j++;
			 								}
			 							}
			 							if(j==tabla_model.getRowCount() && repetido == 0){
		 									tabla_model.addRow(fila);
		 									j++;
			 							}
			 						}
			 					}
			 				}
			 			}
			 			tabla_model.getRowCount();
			 		}else{
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					fila[0] = posicion;
			 					fila[1] = modeloFiltro.getValueAt(i, 1);
			 					fila[2] = false;
			 					fila[3] = fila[2] = "";
			 					fila[4] = "" ;
			 										 			
			 					tabla_model.addRow(fila);
			 				}
			 			}
			 		}
			 		dispose();
//    				Orden_de_Actividades();
			 		
//			 		asigna valor de porcentaje a todas las actividades
			 		porcentajeDefault();
    			}
    		};
    		
//    		public void Orden_de_Actividades(){
//    			// REORDENA DOMINGO
//    			for(int domingo = 0; domingo<tabla.getRowCount(); domingo++){
//    				tabla.setValueAt(domingo+1+"  ", domingo,0);
//    			}
//    		}
    		
			public void porcentajeDefault(){

    			double acumulado = 0;
    			int incremento = 0;
    			double valorPorcentaje=0;
    			
    			for(int j=0; j<table.getRowCount(); j++){
    				if(tabla_model.getValueAt(j,2).toString().equals("true")){
    					
    					if(tabla_model.getValueAt(j,3).toString().equals("")){
    						acumulado=acumulado+0;
    					}else{
    							if(isNumeric(tabla_model.getValueAt(j,3).toString().trim())){
    								acumulado+=Double.parseDouble(tabla_model.getValueAt(j, 3).toString())*100;
    							}else{
    								JOptionPane.showMessageDialog(null, "El porcentaje en la actividad "+tabla_model.getValueAt(j,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
    								tabla_model.setValueAt("10", j, 3);
    								return;
    							}
    						}
    				}else{
    					incremento++;
    				}
    	        }
	        	double valor = (10000-acumulado)/incremento;
	        	
    			for(int i=0; i<table.getRowCount(); i++){
    				
    				if(!tabla_model.getValueAt(i,2).toString().equals("true")){
    					valorPorcentaje=Double.parseDouble((formato.format(valor/100)+"").substring(0,(formato.format(valor/100)+"").length()-1));
    					tabla_model.setValueAt(valorPorcentaje, i, 3);
    				}    				
    			}
    	    }
    		
		    private boolean isNumeric(String cadena){
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
		    
    		KeyListener opFiltroFolio = new KeyListener(){
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
    		
    		KeyListener opFiltroNombre = new KeyListener(){
    			@SuppressWarnings("unchecked")
    			public void keyReleased(KeyEvent arg0) {
    				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
    			}
    			public void keyTyped(KeyEvent arg0) {}
    			public void keyPressed(KeyEvent arg0) {}		
    		};
    		
    		
    	   	public Object[][] getTablaFiltro(){
    			String todos = "exec sp_select_tabla_actidad_cuadrante";
    			Statement s;
    			ResultSet rs;
    			try {
    				s = new Connexion().conexion().createStatement();
    				rs = s.executeQuery(todos);
    				
    				MatrizFiltro = new Object[getFilas(todos)][4];
    				int i=0;
    				while(rs.next()){
    					int folio = rs.getInt(1);
    					MatrizFiltro[i][0] = folio+"  ";
    					MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
    					MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
    					MatrizFiltro[i][3] = false;
    					i++;
    				}
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    		    return MatrizFiltro; 
    		}
    	   	
    		public Object[] getUltimoRow(){
    			String todos = "exec [sp_select_tabla_actidad_cuadrante_ultimo]";
    			Statement s;
    			ResultSet rs;
    			Object[] vect = new Object[4];
    			try {
    				s = new Connexion().conexion().createStatement();
    				rs = s.executeQuery(todos);

    				while(rs.next()){
    					int folio = rs.getInt(1);
    					vect[0] = folio+"  ";
    					vect[1] = "   "+rs.getString(2).trim();
    					vect[2] = "   "+rs.getString(3).trim();
    					vect[3] = false;
    				}
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    		    return vect; 
    		}
    	   	
    	   	public int getFilas(String qry){
    			int filas=0;
    			Statement stmt = null;
    			try {
    				stmt = new Connexion().conexion().createStatement();
    				ResultSet rs = stmt.executeQuery(qry);
    				while(rs.next()){
    					filas++;
    				}
    				
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    			return filas;
    		}	

    		KeyListener validaCantidad = new KeyListener() {
    			@Override
    			public void keyTyped(KeyEvent e){
    				char caracter = e.getKeyChar();				
    				if(((caracter < '0') ||	
    				    	(caracter > '9')) && 
    				    	(caracter != '.' )){
    				    	e.consume();
    				    	}
    			}
    			@Override
    			public void keyReleased(KeyEvent e) {	
    			}
    			@Override
    			public void keyPressed(KeyEvent arg0) {
    			}	
    		};
    		
    		KeyListener validaNumericoConPunto = new KeyListener() {
    			@Override
    			public void keyTyped(KeyEvent e) {
    				char caracter = e.getKeyChar();
    				
    			    if(((caracter < '0') ||	
    			    	(caracter > '9')) && 
    			    	(caracter != '.')){
    			    	e.consume();
    			    	}
    			}
    			@Override
    			public void keyPressed(KeyEvent e){}
    			@Override
    			public void keyReleased(KeyEvent e){}
    									
    		};
    		
    	}
    	
//    	filtro cuadrante con actividad de la fila seleccionada
    	public class Cat_Filtro_Cuadrante_Actividad extends JDialog {
    		Container cont = getContentPane();
    		JLayeredPane campo = new JLayeredPane();
    		
    		DefaultTableModel model = new DefaultTableModel(0,6){
    			public boolean isCellEditable(int fila, int columna){
    				if(columna < 0)
    					return true;
    				return false;
    			}
    		};
    		
    		JTable tabla = new JTable(model);
    		
    		@SuppressWarnings("rawtypes")
    		private TableRowSorter trsfiltro;
    		
    		JTextField txtFolio = new JTextField();
    		JTextField txtNombre_Cuadrante = new JTextField();
    		
    		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    	    @SuppressWarnings({ "unchecked", "rawtypes" })
    		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    	    
    	    @SuppressWarnings({ "unchecked", "rawtypes" })
    		public Cat_Filtro_Cuadrante_Actividad(){
    	    	
    	    	this.setModal(true);
    	    	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
    	    	this.setTitle("Filtro de Cuadrante");
    			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
    			trsfiltro = new TableRowSorter(model); 
    			tabla.setRowSorter(trsfiltro);  
    			
    			campo.add(getPanelTabla()).setBounds(15,42,960,550);
    			
    			campo.add(txtFolio).setBounds(15,20,48,20);
    			campo.add(txtNombre_Cuadrante).setBounds(64,20,229,20);
    			campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
    			
    			agregar(tabla);
    			
    			cont.add(campo);
    			
    			txtFolio.addKeyListener(opFiltroFolio);
    			txtNombre_Cuadrante.addKeyListener(opFiltroNombre);
    			cmbEstablecimientos.addActionListener(opFiltro);
    			
    			this.setSize(1000,650);
    			this.setResizable(false);
    			this.setLocationRelativeTo(null);
    			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	    }
    	    
    	   
    	    private void agregar(final JTable tbl) {
    	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
    		        public void mouseClicked(MouseEvent e) {
    		        	if(e.getClickCount() == 2){
    		    			
    		        		int fila = tabla.getSelectedRow();
    		    			String cuadrante =  tabla.getValueAt(fila, 1).toString().trim();
    		    			
    		    			if(table.isEditing()){
    		    				table.getCellEditor().stopCellEditing();
    		    			}
    		    			
    		    			
    		    			
    		    			
    		    			System.out.println(filaseleccionada);
    		    			tabla_model.setValueAt(cuadrante, filaseleccionada, 4);
    		    			dispose();
    		        	}
    		        }
    	        });
    	    }
    	    
    	   	private JScrollPane getPanelTabla()	{		
    	   		
    	   		this.tabla.getTableHeader().setReorderingAllowed(false) ;
    			this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    	   		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
    			tabla.getColumnModel().getColumn(0).setMaxWidth(50);
    			tabla.getColumnModel().getColumn(0).setMinWidth(50);
    			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre de Cuadrante");
    			tabla.getColumnModel().getColumn(1).setMaxWidth(320);
    			tabla.getColumnModel().getColumn(1).setMinWidth(210);
    			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
    			tabla.getColumnModel().getColumn(2).setMaxWidth(150);
    			tabla.getColumnModel().getColumn(2).setMinWidth(150);
    			tabla.getColumnModel().getColumn(3).setHeaderValue("Jefatura de Cuadrante");
    			tabla.getColumnModel().getColumn(3).setMaxWidth(380);
    			tabla.getColumnModel().getColumn(3).setMinWidth(150);
    			tabla.getColumnModel().getColumn(4).setHeaderValue("Nivel Jerarquico de Cuadrante");
    			tabla.getColumnModel().getColumn(4).setMaxWidth(280);
    			tabla.getColumnModel().getColumn(4).setMinWidth(190);
    			tabla.getColumnModel().getColumn(5).setHeaderValue("Equipo de Trabajo de Cuadrante");
    			tabla.getColumnModel().getColumn(5).setMaxWidth(320);
    			tabla.getColumnModel().getColumn(5).setMinWidth(190);

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
    						case 3 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
    						case 4 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
    						case 5 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
    					}
    				return lbl; 
    				} 
    			}; 
    			
    			for(int i= 0; i<tabla.getColumnCount(); i++){
    				tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
    			}
    			
    			Statement s;
    			ResultSet rs;
    			try {
    				s = new Connexion().conexion().createStatement();
    				String query="exec sp_select_filtro_proyecto_cuadrantes "+folioseleccionado;
    				rs = s.executeQuery(query);
    				while (rs.next()) { 
    				   String [] fila = new String[6];
    				   fila[0] = rs.getString(1)+"  ";
    				   fila[1] = "   "+rs.getString(2);
    				   fila[2] = "   "+rs.getString(3);
    				   fila[3] = "   "+rs.getString(4);
    				   fila[4] = "   "+rs.getString(5);
    				   fila[5] = "   "+rs.getString(6);

    				   model.addRow(fila); 
    				}	
    				
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    			JScrollPane scrol = new JScrollPane(tabla);
    			   
    		    return scrol; 
    		}
    	   	
    	   	KeyListener opFiltroFolio = new KeyListener(){
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
    		
    		KeyListener opFiltroNombre = new KeyListener(){
    			@SuppressWarnings("unchecked")
    			public void keyReleased(KeyEvent arg0) {
    				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Cuadrante.getText().toUpperCase().trim(), 1));
    			}
    			public void keyTyped(KeyEvent arg0) {}
    			public void keyPressed(KeyEvent arg0) {}		
    		};
    		
    		ActionListener opFiltro = new ActionListener(){
    			@SuppressWarnings("unchecked")
    			public void actionPerformed(ActionEvent arg0){
    				if(cmbEstablecimientos.getSelectedIndex() != 0){
    					trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
    				}else{
    					trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
    				}
    			}
    		};
    	}
}