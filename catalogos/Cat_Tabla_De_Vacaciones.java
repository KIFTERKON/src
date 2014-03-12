package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
import objetos.Obj_Tabla_De_Vacaciones;

@SuppressWarnings("serial")
public class Cat_Tabla_De_Vacaciones extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String[] grupo = new Obj_Tabla_De_Vacaciones().Combo_Grupo();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbGrupo = new JComboBox(grupo);
	
	JTextField txtPrima = new JTextField();
	
	JSpinner spAniosT;
	JSpinner spDiasC;
	
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	JButton btnActualizar = new JButton("Modificar");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	
	JButton btnRemover = new JButton("Remover");
	
	 public static DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Tabla_De_Vacaciones().get_tabla_rango_vacaciones(),
	            new String[]{"Grupo", "Años Trab.", "Tiempo (Dias)", "Prima Vac. (%)"}){
	                    
	            @SuppressWarnings("rawtypes")
	            Class[] types = new Class[]{
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
	                            case 0  : return false; 
	                            case 1  : return false; 
	                            case 2  : return false; 
	                            case 3  : return false; 
	                    }
	                     return false;
	             }
	    };
	    
		JTable tabla = new JTable(tabla_model);
		JScrollPane panelScroll = new JScrollPane(tabla);
	
		JTextField txtGrupoFiltro = new JTextField();
		JTextField txtAniosFiltro = new JTextField();
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
//		variables para actualizar
		String grupo_vacaciones;
		int dias;
		int anios;
		int prima;
		
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Tabla_De_Vacaciones(){
		
		this.init_tabla();
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Grupo de vacaciones"));
		this.setTitle("Tabla De Vacaciones");
		
		trsfiltro = new TableRowSorter(tabla_model); 
		tabla.setRowSorter(trsfiltro);
		
		txtGrupoFiltro.setToolTipText("Filtro Por grupo");
		txtAniosFiltro.setToolTipText("Filtro Por Año");
		
		spAniosT = new JSpinner(new SpinnerNumberModel( 1, 1, 80, 1 ));
		spDiasC = new JSpinner( new SpinnerNumberModel( 7, 7, 80, 1 )); 
		
		int x = 45, y=30, ancho=100;
		
		
		panel.add(new JLabel("Grupo:")).setBounds(x-25,y,ancho,20);
		panel.add(cmbGrupo).setBounds(ancho-15,y,ancho+50,20);
		panel.add(btnEditar).setBounds(x+ancho+93,y,70,20);
		panel.add(btnNuevo).setBounds(x+(ancho*2)+65,y,70,20);
		
		panel.add(new JLabel("Años Trab:")).setBounds(x-25,y+=30,ancho,20);
		panel.add(spAniosT).setBounds(ancho-15,y,60,20);
		
		panel.add(new JLabel("Dias Correspondientes:")).setBounds((ancho*2)-20,y,ancho+50,20);
		panel.add(spDiasC).setBounds((ancho*3)+20,y,60,20);
		
		panel.add(new JLabel("Prima:")).setBounds(x-25,y+=30,80,20);
		panel.add(txtPrima).setBounds(ancho-15,y,ancho-5,20);
		panel.add(new JLabel("%")).setBounds((ancho*2)-17,y,80,20);
		
		panel.add(btnGuardar).setBounds(x+ancho+60,y,80,20);
		panel.add(btnActualizar).setBounds(x+(ancho*2)+45,y,90,20);
		
		panel.add(btnLimpiar).setBounds(x-25,y+=30,80,20);
		
		panel.add(txtGrupoFiltro).setBounds(x-25,150,81,20);
		panel.add(txtAniosFiltro).setBounds(x+56,150,70,20);
		panel.add(btnRemover).setBounds(x+247,150,90,20);
		panel.add(panelScroll).setBounds(x-25,150+20,ancho+263,135);
		
		panelEnabledFalse();
		
		txtPrima.setEnabled(false);
		btnEditar.setEnabled(false);
		
		txtPrima.setDocument(new JTextFieldLimit(50));
		
		btnGuardar.addActionListener(guardar);
		btnActualizar.addActionListener(modificar);
		btnRemover.addActionListener(remover);
		
		btnLimpiar.addActionListener(opLimpiar);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		
		txtGrupoFiltro.addKeyListener(opFiltroGrupo);
		txtAniosFiltro.addKeyListener(opFiltroAnios);
		
		txtPrima.addKeyListener(valida_numerico);
		
		agregar(tabla);
		
		cont.add(panel);
		
		this.setSize(405,350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
    public void init_tabla(){
            this.tabla.getTableHeader().setReorderingAllowed(false) ;
            
            		int x=80;
                    int y=70;
                    int z=90;
                    this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    
                    this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
                    this.tabla.getColumnModel().getColumn(0).setMinWidth(x);
                    this.tabla.getColumnModel().getColumn(1).setMaxWidth(y);
                    this.tabla.getColumnModel().getColumn(1).setMinWidth(y);
                    this.tabla.getColumnModel().getColumn(2).setMaxWidth(z);
                    this.tabla.getColumnModel().getColumn(2).setMinWidth(z);
                    this.tabla.getColumnModel().getColumn(3).setMaxWidth(z+15);
                    this.tabla.getColumnModel().getColumn(3).setMinWidth(z+15);
            
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
                                            if(table.getSelectedRow() == row){
                    							((JComponent) componente).setOpaque(true); 
                    							componente.setBackground(new java.awt.Color(186,143,73));
                    						}
                                            ((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
                                            break;
                                            
                                    case 1:
                                            componente = new JLabel(value == null? "": value.toString());
                                            if(row%2==0){
                                                    ((JComponent) componente).setOpaque(true); 
                                                    componente.setBackground(new java.awt.Color(177,177,177));        
                                            }
                                            if(table.getSelectedRow() == row){
                    							((JComponent) componente).setOpaque(true); 
                    							componente.setBackground(new java.awt.Color(186,143,73));
                    						}
                                            ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                            break;
                                            
                                    case 2: 
                                            componente = new JLabel(value == null? "": value.toString());
                                            if(row%2==0){
                                                    ((JComponent) componente).setOpaque(true); 
                                                    componente.setBackground(new java.awt.Color(177,177,177));        
                                            }
                                            if(table.getSelectedRow() == row){
                    							((JComponent) componente).setOpaque(true); 
                    							componente.setBackground(new java.awt.Color(186,143,73));
                    						}
                                            ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                            break;
                                    case 3: 
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));        
                                        }
                                        if(table.getSelectedRow() == row){
                							((JComponent) componente).setOpaque(true); 
                							componente.setBackground(new java.awt.Color(186,143,73));
                						}
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
	                    		}
	                    		return componente;
                    } 
            	}; 
	            for(int i=0; i<tabla.getColumnCount(); i++){
	                    this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
	            }
    		}
	KeyListener opFiltroGrupo = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtGrupoFiltro.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroAnios = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtAniosFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			btnEditar.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnGuardar.setEnabled(false);
			btnRemover.setEnabled(false);
			
			cmbGrupo.setEnabled(true);
			spAniosT.setEnabled(true);
			spDiasC.setEnabled(true);
			txtPrima.setEnabled(true);
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
			panelLimpiar();
			btnNuevo.setEnabled(false);
			btnEditar.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnRemover.setEnabled(false);
			
			cmbGrupo.setEnabled(true);
			spAniosT.setEnabled(true);
			spDiasC.setEnabled(true);
			txtPrima.setEnabled(true);
		}
	};
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		
	        		int fila = tabla.getSelectedRow();
	        		
//	        		se cargan valores en las variables de la seleccion de la tabla
	        		grupo_vacaciones = tabla.getValueAt(fila,0).toString().trim();
	        		anios = Integer.valueOf(tabla.getValueAt(fila, 1).toString().trim());
	        		dias = Integer.valueOf(tabla.getValueAt(fila, 2).toString().trim());
	        		prima =  Integer.valueOf(tabla.getValueAt(fila, 3).toString().trim());
	        		
//	        		se cargan los valores en los campos de la fila seleccionada
	        		cmbGrupo.setSelectedItem(tabla.getValueAt(fila,0).toString().trim());
	        		spAniosT.setValue(Integer.valueOf(tabla.getValueAt(fila,1).toString().trim()));
	        		spDiasC.setValue(Integer.valueOf(tabla.getValueAt(fila,2).toString().trim()));
	        		txtPrima.setText(tabla.getValueAt(fila,3).toString().trim());
	        		
	        			panelEnabledFalse();
						btnEditar.setEnabled(true);
						btnRemover.setEnabled(true);
						btnNuevo.setEnabled(true);
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				Obj_Tabla_De_Vacaciones tabla_vacaciones = new Obj_Tabla_De_Vacaciones();
				
				tabla_vacaciones.setGrupo(cmbGrupo.getSelectedItem().toString());
				tabla_vacaciones.setAnios_trabajados( (int) spAniosT.getValue());
				tabla_vacaciones.setDias_correspondientes( (int) spDiasC.getValue());
				tabla_vacaciones.setPrima_vacacional( Integer.valueOf(txtPrima.getText()));
				
				if(tabla_vacaciones.buscar()){
					
						JOptionPane.showMessageDialog(null, "El registro ya existe", "Aviso !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
						
				}else{
						if(tabla_vacaciones.guardar()){
							
							while(tabla.getRowCount()>0){
		                        tabla_model.removeRow(0);
							}
							
			                Object [][] lista_tabla = new Obj_Tabla_De_Vacaciones().get_tabla_rango_vacaciones();
		                    String[] fila = new String[4];
		                    for(int i=0; i<lista_tabla.length; i++){
		                            fila[0] = lista_tabla[i][0]+"";
		                            fila[1] = lista_tabla[i][1]+"";
		                            fila[2] = lista_tabla[i][2]+"";
		                            fila[3] = lista_tabla[i][3]+"";
		                            tabla_model.addRow(fila);
		                    }
							JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							panelLimpiar();
							panelEnabledFalse();
							btnNuevo.setEnabled(true);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "El registro no se guardó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}
				}
			}
		}
	};
	
	ActionListener modificar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				Obj_Tabla_De_Vacaciones tabla_vacaciones = new Obj_Tabla_De_Vacaciones();
				
				tabla_vacaciones.setGrupo(cmbGrupo.getSelectedItem().toString());
				tabla_vacaciones.setAnios_trabajados( (int) spAniosT.getValue());
				tabla_vacaciones.setDias_correspondientes( (int) spDiasC.getValue());
				tabla_vacaciones.setPrima_vacacional( Integer.valueOf(txtPrima.getText()));
				
				
				if(tabla_vacaciones.buscar()){
						
						JOptionPane.showMessageDialog(null, "El registro ya existe", "Aviso !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
						
				}else{
						if(tabla_vacaciones.actualizar(grupo_vacaciones,anios,dias,prima)){
							
							while(tabla.getRowCount()>0){
		                        tabla_model.removeRow(0);
							}
							
			                Object [][] lista_tabla = new Obj_Tabla_De_Vacaciones().get_tabla_rango_vacaciones();
		                    String[] fila = new String[4];
		                    for(int i=0; i<lista_tabla.length; i++){
		                            fila[0] = lista_tabla[i][0]+"";
		                            fila[1] = lista_tabla[i][1]+"";
		                            fila[2] = lista_tabla[i][2]+"";
		                            fila[3] = lista_tabla[i][3]+"";
		                            tabla_model.addRow(fila);
		                    }
							JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							panelLimpiar();
							panelEnabledFalse();
							btnNuevo.setEnabled(true);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "El registro no se guardó,\nverifique si el registro existe en la tabla\nde lo contrario avise a Desarrollo Humano", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}
				}
			}
		}
	};
	
	ActionListener remover = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar le registro?") == 0){

				Obj_Tabla_De_Vacaciones tabla_vacaciones = new Obj_Tabla_De_Vacaciones();
				
				tabla_vacaciones.setGrupo(cmbGrupo.getSelectedItem().toString());
				tabla_vacaciones.setAnios_trabajados( (int) spAniosT.getValue());
				tabla_vacaciones.setDias_correspondientes( (int) spDiasC.getValue());
				tabla_vacaciones.setPrima_vacacional( Integer.valueOf(txtPrima.getText()));
				
				if(tabla_vacaciones.remover()){
					
					while(tabla.getRowCount()>0){
                        tabla_model.removeRow(0);
					}
					
	                Object [][] lista_tabla = new Obj_Tabla_De_Vacaciones().get_tabla_rango_vacaciones();
                    String[] fila = new String[4];
                    for(int i=0; i<lista_tabla.length; i++){
                            fila[0] = lista_tabla[i][0]+"";
                            fila[1] = lista_tabla[i][1]+"";
                            fila[2] = lista_tabla[i][2]+"";
                            fila[3] = lista_tabla[i][3]+"";
                            tabla_model.addRow(fila);
                    }
					JOptionPane.showMessageDialog(null,"El registró a sido eliminado","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					panelLimpiar();
					panelEnabledFalse();
					btnNuevo.setEnabled(true);
					return;
				}
			}else{
				return;
			}
			
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				Obj_Tabla_De_Vacaciones tabla_vacaciones = new Obj_Tabla_De_Vacaciones();
				
				tabla_vacaciones.setGrupo(cmbGrupo.getSelectedItem().toString());
				tabla_vacaciones.setAnios_trabajados( (int) spAniosT.getValue());
				tabla_vacaciones.setDias_correspondientes( (int) spDiasC.getValue());
				tabla_vacaciones.setPrima_vacacional( Integer.valueOf(txtPrima.getText()));
				
				if(tabla_vacaciones.guardar()){
					
					while(tabla.getRowCount()>0){
                        tabla_model.removeRow(0);
					}
					
	                Object [][] lista_tabla = new Obj_Tabla_De_Vacaciones().get_tabla_rango_vacaciones();
                    String[] fila = new String[4];
                    for(int i=0; i<lista_tabla.length; i++){
                            fila[0] = lista_tabla[i][0]+"";
                            fila[1] = lista_tabla[i][1]+"";
                            fila[2] = lista_tabla[i][2]+"";
                            fila[3] = lista_tabla[i][3]+"";
                            tabla_model.addRow(fila);
                    }
					JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					panelLimpiar();
					panelEnabledFalse();
					btnNuevo.setEnabled(true);
					return;
//					
				}else{
					JOptionPane.showMessageDialog(null, "El registro no se guardó,\nverifique si el registro existe en la tabla\nde lo contrario avise a Desarrollo Humano", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
			}
		}
	};
	
	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledFalse();
			btnNuevo.setEnabled(true);
		}
	};
	
	public void panelLimpiar(){	
		cmbGrupo.setSelectedIndex(0);
		spAniosT.setValue(1);
		spDiasC.setValue(7);
		txtPrima.setText("");
	}
	
	public void panelEnabledFalse(){
		
		cmbGrupo.setEnabled(false);
		spDiasC.setEnabled(false);
		spAniosT.setEnabled(false);
		txtPrima.setEnabled(false);
		
		btnEditar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnRemover.setEnabled(false);
		
	}		
	
	public void panelEnabledTrue(){	
		cmbGrupo.setEditable(true);
		txtPrima.setEnabled(true);
	}
	
	private String validaCampos(){
		String error="";
		if(cmbGrupo.getSelectedIndex()==0)		error+= "Seleccione un grupo\n";
		if(txtPrima.getText().equals("")) 		error+= "Prima vacacional\n";
				
		return error;
	}
	
	KeyListener valida_numerico = new KeyListener() {
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
		try{
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Tabla_De_Vacaciones().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
