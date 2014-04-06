package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.Obj_Alimentacion_De_Vacaciones;
import objetos.Obj_Establecimiento;

import SQL.BuscarTablasModel;
import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Alimentacion_De_Ultimas_Vacaciones extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	static JTextField txtFolioEmpleado = new JTextField("");
	static JTextField txtEmpleado = new JTextField("");
	static JTextField txtEstablecimiento = new JTextField("");
	static JTextField txtPuesto = new JTextField("");
	
	static JTextField txtVacacionesProximas = new JTextField("");
	
	JDateChooser fechaInicio = new JDateChooser();
	JDateChooser fechaFinal = new JDateChooser();
	
	static JButton btnFiltro = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	
	public Cat_Alimentacion_De_Ultimas_Vacaciones(){
		this.setTitle("Alimentacion De Ultimas Vacaciones");
		
		this.fechaInicio.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.fechaFinal.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		int y=25;
		panel.add(new JLabel("Folio Empleado: ")).setBounds(20, y, 100, 20);
		panel.add(txtFolioEmpleado).setBounds(110, y, 80, 20);
		panel.add(btnFiltro).setBounds(190, y, 30, 20);
		
		panel.add(new JLabel("Empleado: ")).setBounds(20, y+=25, 100, 20);
		panel.add(txtEmpleado).setBounds(110, y, 320, 20);
		
		panel.add(new JLabel("Establecimiento: ")).setBounds(20, y+=25, 100, 20);
		panel.add(txtEstablecimiento).setBounds(110, y, 160, 20);
		
		panel.add(new JLabel("Puesto: ")).setBounds(20, y+=25, 100, 20);
		panel.add(txtPuesto).setBounds(110, y, 320, 20);
		
		panel.add(new JLabel("Fecha Inicial: ")).setBounds(20, y+=25, 100, 20);
		panel.add(fechaInicio).setBounds(110, y, 100, 20);
		
		panel.add(new JLabel("Fecha Final: ")).setBounds(245, y, 100, 20);
		panel.add(fechaFinal).setBounds(330, y, 100, 20);
		
		panel.add(new JLabel("Vacaciones Correspondientes Al Año: ")).setBounds(20, y+=25, 185, 20);
		panel.add(txtVacacionesProximas).setBounds(210, y, 40, 20);
		
		panel.add(btnLimpiar).setBounds(265, y+=25, 80, 20);
		panel.add(btnGuardar).setBounds(350, y, 80, 20);
		
		txtEmpleado.setEditable(false);
		txtEstablecimiento.setEditable(false);
		txtPuesto.setEditable(false);
		
		txtFolioEmpleado.addActionListener(opFiltro);
		btnFiltro.addActionListener(opFiltro);
		
		btnGuardar.addActionListener(opGuardar);
		
		btnLimpiar.addActionListener(opLimpiar);
		txtFolioEmpleado.addKeyListener(valida_numerico);
		txtVacacionesProximas.addKeyListener(valida_numerico);
		
		//  abre el filtro de busqueda de empleado al presionar la tecla f2
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	       KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "foco");
	    
	    getRootPane().getActionMap().put("foco", new AbstractAction(){
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	new Cat_Filtro_Empleados_Vacaciones().setVisible(true);   	
	        }
	    });
		
		cont.add(panel);
		this.setSize(455, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			if(txtFolioEmpleado.getText().equals("")){
					new Cat_Filtro_Empleados_Vacaciones().setVisible(true);
			}else{
					Obj_Alimentacion_De_Vacaciones alimentacion = new Obj_Alimentacion_De_Vacaciones().buscar_empleado_para_vacaciones(Integer.valueOf(txtFolioEmpleado.getText()));
            	
					if(alimentacion.getFolio_empleado() != 0){
						
						txtFolioEmpleado.setText(alimentacion.getFolio_empleado()+"");
						txtEmpleado.setText(alimentacion.getEmpleado());
						txtEstablecimiento.setText(alimentacion.getEstablecimiento());
						txtPuesto.setText(alimentacion.getPuesto());
						
						fechaInicio.requestFocus();
					}else{
						txtFolioEmpleado.setText("");
						txtFolioEmpleado.requestFocus();
						JOptionPane.showMessageDialog(null, "Solo Puede Alimentar Vacaciones Del Personal VIGENTE", "Mensaje",JOptionPane.WARNING_MESSAGE);
						return;
					}
			}
		}
	};
	
	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
		}
	};
	
	public void limpiar(){
		txtFolioEmpleado.setText("");
		txtEmpleado.setText("");
		txtEstablecimiento.setText("");
		txtPuesto.setText("");
		txtVacacionesProximas.setText("");
		fechaInicio.setDate(null);
		fechaFinal.setDate(null);
	}
	
	ActionListener opGuardar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				cambiar por valida campos
				if(txtEmpleado.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Seleccione Un Empleado", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}else{	
					
					Obj_Alimentacion_De_Vacaciones alimentacion = new Obj_Alimentacion_De_Vacaciones().buscar_empleado_para_vacaciones(Integer.valueOf(txtFolioEmpleado.getText()));
					if(alimentacion.getFolio_empleado() == Integer.parseInt(txtFolioEmpleado.getText())){
						
//						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
//							
//							if(validaCampos()!="") {
//								JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
//								return;
//							}else{
////								codigo para actualizar
//								
//								alimentacion.setFolio_empleado(Integer.valueOf(txtFolioEmpleado.getText()));
//								alimentacion.setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio.getDate()));
//								alimentacion.setFecha_final(new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal.getDate()));
//								alimentacion.setAnios_a_disfrutar(Integer.valueOf(txtVacacionesProximas.getText()));
////								actializar			
////									if(departamento.actualizar()){
////										
//										
////										JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
////										panelLimpiar();
////										panelEnabledFalse();
////										txtFolio.setEditable(true);
////										txtFolio.requestFocus();
////										return;
//								
////									}else{
////										JOptionPane.showMessageDialog(null, "El registro no se actualizó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
////										return;
////									}
//							}
//							
//						}else{
//							return;
//						}
					}else{
						
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
//							codigo para guardar
							
							alimentacion.setFolio_empleado(Integer.valueOf(txtFolioEmpleado.getText()));
							alimentacion.setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio.getDate()));
							alimentacion.setFecha_final(new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal.getDate()));
							alimentacion.setAnios_a_disfrutar(Integer.valueOf(txtVacacionesProximas.getText()));
//							guardar			
							if(alimentacion.guardar_ultimas_vacaciones()){
							
								limpiar();
								txtFolioEmpleado.requestFocus();
								JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
								return;
					
							}else{
								JOptionPane.showMessageDialog(null, "El registro no se guardó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
								return;
							}
						}

					}
				}		
			}
		};
		
		private String validaCampos(){
			String error="";
			String fechaInicioNull = fechaInicio.getDate()+"";
			String fechaFinNull = fechaFinal.getDate()+"";
			
			if(txtEmpleado.getText().equals(""))error += "Seleccione Un Empleado\n";
			if(fechaInicioNull.equals("null"))error += "Fecha Inicial\n";	
			if(fechaFinNull.equals("null"))error += "Fecha Final\n";
			
			return error;
		}
	
	KeyListener valida_numerico = new KeyListener() {
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
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Alimentacion_De_Ultimas_Vacaciones().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
	
	public static class Cat_Filtro_Empleados_Vacaciones extends JFrame {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		public static Object[][] get_tabla(){
			return new BuscarTablasModel().tabla_model_empleados_vacaciones();
		}
		
	    public static DefaultTableModel tabla_model = new DefaultTableModel(
	    		get_tabla(),	new String[]{	"Folio",	"Nombre Completo", "Establecimiento", "Puesto"}){
	                    @SuppressWarnings("rawtypes")
	                    Class[] types = new Class[]{
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
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Filtro_Empleados_Vacaciones()	{
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
			this.setTitle("Filtro De Empleado Vigentes");
			panel.setBorder(BorderFactory.createTitledBorder("Seleccionar Un Empleado"));

			this.init_tabla();
			
			trsfiltro = new TableRowSorter(tabla_model); 
			tabla.setRowSorter(trsfiltro);  
			
			panel.add(panelScroll).setBounds(15,42,730,327);
			
			panel.add(txtFolio).setBounds(15,20,58,20);
			panel.add(txtNombre_Completo).setBounds(75,20,310,20);
			panel.add(cmbEstablecimientos).setBounds(387,20, 180, 20);
			
			agregar(tabla);
			
			cont.add(panel);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			cmbEstablecimientos.addActionListener(opFiltro);
			
			this.setSize(780,415);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		    			
		        		int fila = tabla.getSelectedRow();
		    			int folio =  Integer.parseInt(tabla.getValueAt(fila, 0)+"");
		    			
		    			txtFolioEmpleado.setText(folio+"");
		    			btnFiltro.doClick();
		    			dispose();
		        	}
		        }
	        });
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
		
		public void init_tabla(){
	        this.tabla.getTableHeader().setReorderingAllowed(false) ;
	        
	        	   int w=60;
	               int x=330;
	               int y=160;
	               
	                this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	                
	                this.tabla.getColumnModel().getColumn(0).setMaxWidth(w);
	                this.tabla.getColumnModel().getColumn(0).setMinWidth(w);
	                this.tabla.getColumnModel().getColumn(1).setMaxWidth(x);
	                this.tabla.getColumnModel().getColumn(1).setMinWidth(x);
	                this.tabla.getColumnModel().getColumn(2).setMaxWidth(y);
	                this.tabla.getColumnModel().getColumn(2).setMinWidth(y);
	                this.tabla.getColumnModel().getColumn(3).setMaxWidth(y);
	                this.tabla.getColumnModel().getColumn(3).setMinWidth(y);
	                
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
	                                case 2: 
	                                        componente = new JLabel(value == null? "": value.toString());
	                                        if(row%2==0){
	                                                ((JComponent) componente).setOpaque(true); 
	                                                componente.setBackground(new java.awt.Color(177,177,177));        
	                                        }
	                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
	                                        break;
	                                case 3: 
	                                        componente = new JLabel(value == null? "": value.toString());
	                                        if(row%2==0){
	                                                ((JComponent) componente).setOpaque(true); 
	                                                componente.setBackground(new java.awt.Color(177,177,177));        
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

}

