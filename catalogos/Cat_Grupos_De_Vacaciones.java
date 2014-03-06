package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
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

import objetos.JTextFieldLimit;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Grupo_De_Vacaciones;

@SuppressWarnings("serial")
public class Cat_Grupos_De_Vacaciones extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chbStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	 public static DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Grupo_De_Vacaciones().get_tabla_model_grupo_de_vacaciones(),
	            new String[]{"Folio", "Descripcion"}){
	                    
	            @SuppressWarnings("rawtypes")
	            Class[] types = new Class[]{
	                       java.lang.Object.class,
	                       java.lang.Object.class                        
	        };
	            @SuppressWarnings({ "rawtypes", "unchecked" })
	            public Class getColumnClass(int columnIndex) {
	                    return types[columnIndex];
	            }
	        public boolean isCellEditable(int fila, int columna){
	                    switch(columna){
	                            case 0  : return false; 
	                            case 1  : return false; 
	                    }
	                     return false;
	             }
	    };
	    
		JTable tabla = new JTable(tabla_model);
		JScrollPane panelScroll = new JScrollPane(tabla);
	
		JTextField txtFolioFiltro = new JTextField();
		JTextField txtDescripcionFiltro = new JTextField();
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Grupos_De_Vacaciones(){
		
		this.init_tabla();
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Grupos de vacaciones"));
		
		this.setTitle("Grupo de vacaciones");
		
		trsfiltro = new TableRowSorter(tabla_model); 
		tabla.setRowSorter(trsfiltro);
		
		txtFolioFiltro.setToolTipText("Filtro Por Folio");
		txtDescripcionFiltro.setToolTipText("Filtro Por Departamento");
		
		int x = 45, y=30, ancho=100;
		
		chbStatus.setSelected(true);
		
		panel.add(new JLabel("Folio:")).setBounds(x-25,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho,y,ancho+40,20);
		panel.add(btnBuscar).setBounds(x+(ancho*2),y,32,20);
		
		panel.add(chbStatus).setBounds(x+(ancho*2)+30,y,ancho-25,20);
		
		panel.add(new JLabel("Descripcion:")).setBounds(x-25,y+=30,80,20);
		panel.add(txtDescripcion).setBounds(ancho,y,ancho+70,20);
		panel.add(btnNuevo).setBounds(x+(ancho*2)+30,y,ancho-10,20);
		
		panel.add(btnEditar).setBounds(x+(ancho*2)+30,y,ancho-10,20);
		panel.add(btnDeshacer).setBounds(x+ancho+20,y+=30,ancho-10,20);
		panel.add(btnSalir).setBounds(x+10,y,ancho-10,20);
		panel.add(btnGuardar).setBounds(x+ancho*2+30,y,ancho-10,20);
		
		panel.add(txtFolioFiltro).setBounds((x*2)+(ancho*3)-10,15,50,20);
		panel.add(txtDescripcionFiltro).setBounds((x*2)+(ancho*3)+40,15,200,20);
		panel.add(panelScroll).setBounds((x*2)+(ancho*3)-10,35,ancho+255,130);

		botonNuevoDepartamento();
		
		chbStatus.setEnabled(false);
		btnEditar.setEnabled(false);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtDescripcion.setDocument(new JTextFieldLimit(50));
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		
		txtFolioFiltro.addKeyListener(opFiltroFolio);
		txtDescripcionFiltro.addKeyListener(opFiltroDepartamento);
		
		agregar(tabla);
		
		cont.add(panel);
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    public void init_tabla(){
            this.tabla.getTableHeader().setReorderingAllowed(false) ;
            
            		int x=50;
                    int y=300;

                    this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    
                    this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
                    this.tabla.getColumnModel().getColumn(0).setMinWidth(x);
                    this.tabla.getColumnModel().getColumn(1).setMaxWidth(y);
                    this.tabla.getColumnModel().getColumn(1).setMinWidth(y);
            
            TableCellRenderer render = new TableCellRenderer() { 
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) { 
                            
                            Component componente = null;
                            switch(column){
                                    case 0: 
                                            componente = new JLabel(value == null? "": value.toString());
                                            if(row%2==0){
                                                    ((JComponent) componente).setOpaque(true); 
                                                    componente.setBackground(new java.awt.Color(177,177,177));
                                            }
                                            ((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
                                            break;
                                    case 1:
                                            componente = new JLabel(value == null? "": value.toString());
                                            if(row%2==0){
                                                    ((JComponent) componente).setOpaque(true); 
                                                    componente.setBackground(new java.awt.Color(177,177,177));        
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
}
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFiltro.getText(), 0));
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
	
	KeyListener opFiltroDepartamento = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtDescripcionFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	public void botonNuevoDepartamento(){
		Obj_Configuracion_Sistema configs2 = new Obj_Configuracion_Sistema().buscar2();
		if(configs2.isGuardar_departamento()==true){
			btnNuevo.setEnabled(true);
		}else{
			btnNuevo.setEnabled(false);
		}
	}
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No hay registro que Editar","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				panelEnabledTrue();
				txtFolio.setEditable(false);
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(true);
				chbStatus.setEnabled(true);
				btnEditar.setEnabled(false);
			}
		}
	};
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try {
					Obj_Grupo_De_Vacaciones grupo = new Obj_Grupo_De_Vacaciones().buscar(Integer.parseInt(txtFolio.getText()));
					if(grupo.getFolio() != 0){
						
						txtFolio.setText(grupo.getFolio()+"");
						txtDescripcion.setText(grupo.getDescripcion()+"");
						if(grupo.isStatus() == true){chbStatus.setSelected(true);}
						else{chbStatus.setSelected(false);}
						btnNuevo.setEnabled(false);
						btnEditar.setEnabled(true);
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						
					} else{
						JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
						return;
					}
				
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 			
			}
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				
				Obj_Grupo_De_Vacaciones grupo = new Obj_Grupo_De_Vacaciones().buscar_nuevo(); 
				
				if(grupo.getFolio() != 0){
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(grupo.getFolio()+1+"");
					txtFolio.setEditable(false);
					txtDescripcion.requestFocus();
				}else{
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(1+"");
					txtFolio.setEditable(false);
					txtDescripcion.requestFocus();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	};
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		
	        		int fila = tabla.getSelectedRow();
	        		Object id = tabla.getValueAt(fila,0).toString().substring(3,tabla.getValueAt(fila,0).toString().length());
	        
						txtFolio.setText(id+"");
						txtDescripcion.setText(tabla.getValueAt(fila,1).toString().substring(3,tabla.getValueAt(fila,1).toString().length()));

						btnEditar.setEnabled(true);
						chbStatus.setSelected(true);
						btnEditar.setEnabled(true);
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				try {
					Obj_Grupo_De_Vacaciones grupo = new Obj_Grupo_De_Vacaciones().buscar(Integer.valueOf(txtFolio.getText()));
					
					if(grupo.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
							if(validaCampos()!="") {
								JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
							}else{
								grupo.setDescripcion(txtDescripcion.getText());
								grupo.setStatus(chbStatus.isSelected());
								
								if(grupo.actualizar(Integer.parseInt(txtFolio.getText()))){
									
									while(tabla.getRowCount()>0){
                                        tabla_model.removeRow(0);
									}

									 Object [][] lista_tabla = new Obj_Grupo_De_Vacaciones().get_tabla_model_grupo_de_vacaciones();
				                        String[] fila = new String[2];
				                                        for(int i=0; i<lista_tabla.length; i++){
				                                                fila[0] = lista_tabla[i][0]+"";
				                                                fila[1] = lista_tabla[i][1]+"";
				                                                tabla_model.addRow(fila);
				                                        }
				                                        
									JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
									panelLimpiar();
									panelEnabledFalse();
									txtFolio.setEditable(true);
									txtFolio.requestFocus();
									return;
									
								}else{
									JOptionPane.showMessageDialog(null, "El registro no se actualizó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
									return;
								}
							}
						}else{
							return;
						}
					}else{
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							grupo.setFolio(Integer.parseInt(txtFolio.getText()));
							grupo.setDescripcion(txtDescripcion.getText());
							grupo.setStatus(chbStatus.isSelected());
							
								if(grupo.guardar()){
									
									while(tabla.getRowCount()>0){
                                        tabla_model.removeRow(0);
									}
									
									 Object [][] lista_tabla = new Obj_Grupo_De_Vacaciones().get_tabla_model_grupo_de_vacaciones();
				                        String[] fila = new String[2];
				                                        for(int i=0; i<lista_tabla.length; i++){
				                                                fila[0] = lista_tabla[i][0]+"";
				                                                fila[1] = lista_tabla[i][1]+"";
				                                                tabla_model.addRow(fila);
                                    }
									
									JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
									panelLimpiar();
									panelEnabledFalse();
									txtFolio.setEditable(true);
									txtFolio.requestFocus();
									return;
									
								}else{
									JOptionPane.showMessageDialog(null, "El registro no se guardó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
									return;
								}
							}
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 				
			}			
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(true);
		}
	};
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtDescripcion.setText("");
	}
	
	public void panelEnabledFalse(){
		txtFolio.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
	}
	
	private String validaCampos(){
		String error="";
		if(txtDescripcion.getText().equals("")) 		error+= "Nombre\n";
				
		return error;
	}
	
	KeyListener buscar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	KeyListener numerico_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();

		   if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }			
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	public static void main(String [] arg){
		new Cat_Grupos_De_Vacaciones().setVisible(true);
	}
}
