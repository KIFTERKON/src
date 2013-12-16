package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
import objetos.Reporte_de_Cuadrantes;

import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

						//INDICE
								//Cat_Reporte_Cuadrantes

										//Cat_Condiciones_Equipo_Trabajo
												//Cat_Filtro_Tipo_De_Respuesta

										//Cat_Condiciones_Empleados
												//Cat_Filtro_Empleados_Con_Cuadrante
								
										//Cat_Condiciones_Establecimientos
												//Cat_Filtro_Establecimiento
								
										//Cat_Condiciones_Puesto
												//Cat_Filtro_Puesto
								
										// Cat_Condiciones_Departamento
												//Cat_Filtro_Departamento
								
										//Cat_Condiciones_Nivel_Critico
												//Cat_Filtro_Nivel_Critico
								
										//Cat_Condiciones_Tipo_De_Respuesta
												//Cat_Filtro_Tipo_De_Respuesta


// SELECCION DE TIPO DE REPORTE-------------------------------------------------------------------------------------
@SuppressWarnings("serial")
public class Cat_Reporte_Cuadrantes extends JFrame {

	Container cont  = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JButton btnGenerar = new JButton("Generar Reporte");
	
	String presentado[] = {"Establecimiento a detalle","Establecimiento concentrado","Equipo de trabajo a detalle","Equipo de trabajo concentrado"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbPresentado = new JComboBox(presentado);
	
	JDateChooser txtFechaInicial = new JDateChooser();
	JDateChooser txtFechaFinal = new JDateChooser();
	
	JTextField txtFiltroEqTrabajo = new JTextField("Equipo De Trabajo Todos");
	JButton btnFiltroEqTrabajo = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroEqTrabajo = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroEmpleados = new JTextField("Empleado Todos");
	JButton btnFiltroEmpleados = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroEmpleados = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroEstablecimiento = new JTextField("Establecimiento Todos");
	JButton btnFiltroEstablecimiento = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroEstablecimiento = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroPuesto = new JTextField("Puesto Todos");
	JButton btnFiltroPuesto = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroPuesto = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroDepartamento = new JTextField("Departamento Todos");
	JButton btnFiltroDepartamento = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroDepartamento = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroNivelCritico = new JTextField("Nivel Critico Todos");
	JButton btnFiltroNivelCritico = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroNivelCritico = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	JTextField txtFiltroRespuesta = new JTextField("Tipo De Respuesta Todas");
	JButton btnFiltroRespuesta = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnLimpiarFiltroRespuesta = new JButton(new ImageIcon("Iconos/limpiar.png"));
	
	Border blackline;
	JLabel lblFiltros = new JLabel(); 
	JLabel lblPresentado = new JLabel();
	
//	VARIABLE GLOBALES  -------------------------------------------------------------------------------------
	
	int operadorEqTrabajo=0;		String cadenaEqTrabajo="";
	
	int operadorEmpleado=0;			String cadenaEmpleados="";
	
	int operadorEstablecimiento=0;	String cadenaEstablecimiento="";
	
	int operadorPuesto=0;			String cadenaPuesto="";
	
	int operadorDepartamento=0;		String cadenaDepartamento="";
	
	int operadorNivelCritico=0;		String cadenaNivelCritico="";
	
	int operadorRespuesta=0;		String cadenaRespuesta="";
//	--------------------------------------------------------------------------------------------------------
	
	public void getConstructor(){

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
		this.setTitle("Filtro De Reporte");
				
		this.txtFechaInicial.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtFechaFinal.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		this.lblFiltros.setBorder(BorderFactory.createTitledBorder(blackline,"Filtros"));
		this.lblPresentado.setBorder(BorderFactory.createTitledBorder(blackline,"Presentado por: "));
		
		btnFiltroEqTrabajo.setToolTipText("Seleccion de equipo de trabajo");
		btnLimpiarFiltroEqTrabajo.setToolTipText("Limpiar campo equipo de trabajo");
		
		btnFiltroEmpleados.setToolTipText("Seleccion de empleados");
		btnLimpiarFiltroEmpleados.setToolTipText("Limpiar campo empleado");
		
		btnFiltroEstablecimiento.setToolTipText("Seleccion de establecimiento");
		btnLimpiarFiltroEstablecimiento.setToolTipText("Limpiar campo establecimiento");
		
		btnFiltroPuesto.setToolTipText("Seleccion de puesto");
		btnLimpiarFiltroPuesto.setToolTipText("Limpiar campo puesto");
		
		btnFiltroDepartamento.setToolTipText("Seleccion de departamento");
		btnLimpiarFiltroDepartamento.setToolTipText("Limpiar campo departamento");
		
		btnFiltroNivelCritico.setToolTipText("Seleccion de nivel critico");
		btnLimpiarFiltroNivelCritico.setToolTipText("Limpiar campo nivel critico");
		
		btnFiltroRespuesta.setToolTipText("Seleccion de tipo de respuesta");
		btnLimpiarFiltroRespuesta.setToolTipText("Limpiar campo tipo de respuesta");
		
		int y=20;
		
		panel.add(btnGenerar).setBounds(360,y+=5,115,20);
		panel.add(lblPresentado).setBounds(480,50,180,220);
		panel.add(cmbPresentado).setBounds(490, 70, 160, 20);		
		
		panel.add(new JLabel("De: ")).setBounds(90,y,30,20);
		panel.add(txtFechaInicial).setBounds(120,y,100,20);
		panel.add(new JLabel("A: ")).setBounds(230,y,30,20);
		panel.add(txtFechaFinal).setBounds(250,y,100,20);
		
		panel.add(lblFiltros).setBounds(5,y+=25,475,220);
		
		panel.add(txtFiltroEqTrabajo).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroEqTrabajo).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroEqTrabajo).setBounds(450,y,20,20);
		
		panel.add(txtFiltroEmpleados).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroEmpleados).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroEmpleados).setBounds(450,y,20,20);
		
		panel.add(txtFiltroEstablecimiento).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroEstablecimiento).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroEstablecimiento).setBounds(450,y,20,20);
		
		panel.add(txtFiltroPuesto).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroPuesto).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroPuesto).setBounds(450,y,20,20);
		
		panel.add(txtFiltroDepartamento).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroDepartamento).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroDepartamento).setBounds(450,y,20,20);
		
		panel.add(txtFiltroNivelCritico).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroNivelCritico).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroNivelCritico).setBounds(450,y,20,20);
		
		panel.add(txtFiltroRespuesta).setBounds(15, y+=25, 410, 20);
		panel.add(btnFiltroRespuesta).setBounds(430,y,20,20);
		panel.add(btnLimpiarFiltroRespuesta).setBounds(450,y,20,20);
		
		btnGenerar.addActionListener(opGenerarReporte);
		
		
		btnFiltroEqTrabajo.addActionListener(opCondicionEqTrabajo);
		btnLimpiarFiltroEqTrabajo.addActionListener(opLimpiarFiltroEqTrabajo);
		
		btnFiltroEmpleados.addActionListener(opCondicionEmpleado);
		btnLimpiarFiltroEmpleados.addActionListener(opLimpiarFiltroEmpleados);
		
		btnFiltroEstablecimiento.addActionListener(opCondicionEstablecimiento);
		btnLimpiarFiltroEstablecimiento.addActionListener(opLimpiarFiltroEstablecimiento);
		
		btnFiltroPuesto.addActionListener(opCondicionPuesto);
		btnLimpiarFiltroPuesto.addActionListener(opLimpiarFiltroPuesto);
		
		btnFiltroDepartamento.addActionListener(opCondicionDepartamento);
		btnLimpiarFiltroDepartamento.addActionListener(opLimpiarFiltroDepartamento);
		
		btnFiltroNivelCritico.addActionListener(opCondicionNivelCritico);
		btnLimpiarFiltroNivelCritico.addActionListener(opLimpiarFiltroNivelCritico);
		
		btnFiltroRespuesta.addActionListener(opCondicionRespuesta);
		btnLimpiarFiltroRespuesta.addActionListener(opLimpiarFiltroRespuesta);
		
		txtFiltroEqTrabajo.setEditable(false);
		txtFiltroEmpleados.setEditable(false);
		txtFiltroEstablecimiento.setEditable(false);
		txtFiltroPuesto.setEditable(false);
		txtFiltroDepartamento.setEditable(false);
		txtFiltroNivelCritico.setEditable(false);
		txtFiltroRespuesta.setEditable(false);
		
		cont.add(panel);
		this.setSize(670, 320);
//		this.setSize(800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	int reportePresentado=0;
	ActionListener opGenerarReporte = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validar_fechas().equals("")){
				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(txtFechaInicial.getDate())+" 00:00:00";
				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(txtFechaFinal.getDate())+" 23:59:59";
				
					if(txtFechaInicial.getDate().before(txtFechaFinal.getDate())){
						
						
						reportePresentado=cmbPresentado.getSelectedIndex();
						
						
						System.out.println(reportePresentado);
						System.out.println(fecha_inicio);
						System.out.println(fecha_final);
						
						System.out.println(operadorEqTrabajo+"");
						System.out.println(cadenaEqTrabajo);
						
						System.out.println(operadorEmpleado+"");
						System.out.println(cadenaEmpleados);
						System.out.println(operadorEstablecimiento+"");
						System.out.println(cadenaEstablecimiento);
						
						System.out.println(operadorPuesto+"");
						System.out.println(cadenaPuesto);
						System.out.println(operadorDepartamento+"");
						System.out.println(cadenaDepartamento);
						System.out.println(operadorNivelCritico+"");
						System.out.println(cadenaNivelCritico);
						System.out.println(operadorRespuesta+"");
						System.out.println(cadenaRespuesta);
						
						new Reporte_de_Cuadrantes(fecha_inicio, fecha_final, operadorEqTrabajo, cadenaEqTrabajo,
																			operadorEmpleado, cadenaEmpleados,
																			operadorEstablecimiento, cadenaEstablecimiento,
																			operadorPuesto,cadenaPuesto,
																			operadorDepartamento,cadenaDepartamento,
																			operadorNivelCritico,cadenaNivelCritico,
																			operadorRespuesta,cadenaRespuesta,
																			reportePresentado
																			);
					}else{
						JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: \n"+validar_fechas(),"Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	public String validar_fechas(){
		String error = "";
		String fechainicioNull = txtFechaInicial.getDate()+"";
		String fechafinalNull = txtFechaFinal.getDate()+"";
	    if(fechainicioNull.equals("null"))error+= "Fecha  inicial\n";
		if(fechafinalNull.equals("null"))error+= "Fecha Final\n";
		
		return error;
	}

	ActionListener opCondicionEqTrabajo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Equipo_De_Trabajo().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroEqTrabajo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroEqTrabajo.setText("Equipo De Trabajo Todos");
			cadenaEqTrabajo="";
			operadorEqTrabajo=0;
		}
	};
	
	ActionListener opCondicionEmpleado = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Empleados().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroEmpleados = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroEmpleados.setText("Empleado Todos");
			cadenaEmpleados="";
			operadorEmpleado=0;
		}
	};
	
	ActionListener opCondicionEstablecimiento = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Establecimientos().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroEstablecimiento = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroEstablecimiento.setText("Establecimiento Todos");
			cadenaEstablecimiento="";
			operadorEstablecimiento=0;
		}
	};
	
	ActionListener opCondicionPuesto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Puesto().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroPuesto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroPuesto.setText("Puesto Todos");
			cadenaPuesto="";
			operadorPuesto=0;
		}
	};
	
	ActionListener opCondicionDepartamento = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Departamento().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroDepartamento = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroDepartamento.setText("Departamento Todos");
			cadenaDepartamento="";
			operadorDepartamento=0;
		}
	};
	
	ActionListener opCondicionNivelCritico = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Nivel_Critico().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroNivelCritico = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroNivelCritico.setText("Nivel Critico Todos");
			cadenaNivelCritico="";
			operadorNivelCritico=0;
		}
	};
	
	ActionListener opCondicionRespuesta = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Condiciones_Tipo_De_Respuesta().setVisible(true);
		}
	};
	
	ActionListener opLimpiarFiltroRespuesta = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFiltroRespuesta.setText("Tipo De Respuesta Todas");
			cadenaRespuesta="";
			operadorRespuesta=0;
		}
	};
	
	public Cat_Reporte_Cuadrantes(){
		 getConstructor();
	}
	
//	FILTRO POR EQUIPO DE TRABAJO---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE EQUIPO DE TRABAJO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Equipo_De_Trabajo extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorEqTrabajo = new JComboBox(operador);
		
		JTextField txtComparacionEqTrabajo = new JTextField();
		JButton btnSeleccionEqTrabajo = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarEqTrabajo = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarEqTrabajo = new JButton("Enviar");
		
		JTextArea txaArmadoEqTrabajo = new JTextArea();
		JScrollPane armadoEqTrabajo = new JScrollPane(txaArmadoEqTrabajo);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionEqTrabajo.setToolTipText("Seleccion de equipo de trabajo");
			btnLimpiarEqTrabajo.setToolTipText("Limpiar");

			txaArmadoEqTrabajo.setBorder(border);
			txaArmadoEqTrabajo.setLineWrap(true);
			txaArmadoEqTrabajo.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Equipo de trabajo ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorEqTrabajo).setBounds(80,y,100,20);
			
			panel.add(txtComparacionEqTrabajo).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionEqTrabajo).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarEqTrabajo).setBounds(390, y, 30, 20);
			
			panel.add(armadoEqTrabajo).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarEqTrabajo).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorEqTrabajo.addActionListener(opCompararEqTrabajo);
			btnLimpiarEqTrabajo.addActionListener(opLimpiarEqTrabajo);
			btnSeleccionEqTrabajo.addActionListener(opFiltroEqTrabajo);
			btnEnviarEqTrabajo.addActionListener(opEnviarEqTrabajo);
			
			txtComparacionEqTrabajo.setEditable(false);
			txaArmadoEqTrabajo.setEditable(false);
			btnSeleccionEqTrabajo.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Equipo_De_Trabajo(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararEqTrabajo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEqTrabajo.getSelectedIndex() == 0){
					btnSeleccionEqTrabajo.setEnabled(false);
				}else{
					btnSeleccionEqTrabajo.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarEqTrabajo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionEqTrabajo.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarEqTrabajo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEqTrabajo.getSelectedIndex() == 0){
					txtFiltroEmpleados.setText("Equipo De Trabajo Todos");
				}else{
					if(txtComparacionEqTrabajo.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroEqTrabajo.setText(txaArmadoEqTrabajo.getText()+"");
						
						cadenaEqTrabajo=txtComparacionEqTrabajo.getText();
						operadorEqTrabajo=cmbOperadorEqTrabajo.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorEqTrabajo.getSelectedIndex()){
					case 0:  txaArmadoEqTrabajo.setText("");break;
					case 1:  txaArmadoEqTrabajo.setText("folio_equipo_trabajo = "+txtComparacionEqTrabajo.getText()); break;
					case 2:  txaArmadoEqTrabajo.setText("folio_equipo_trabajo IN "+txtComparacionEqTrabajo.getText()); break;
					case 3:  txaArmadoEqTrabajo.setText("folio_equipo_trabajo < "+txtComparacionEqTrabajo.getText()); break;
					case 4:  txaArmadoEqTrabajo.setText("folio_equipo_trabajo > "+txtComparacionEqTrabajo.getText()); break;
					case 5:  txaArmadoEqTrabajo.setText("folio_equipo_trabajo <> "+txtComparacionEqTrabajo.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroEqTrabajo = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Equipo_De_Trabajo().setVisible(true);
				}
		};
		
// FILTRO DE EQUIPO DE TRABAJO CON CUADRNATE PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Equipo_De_Trabajo extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Empleados",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Equipo_De_Trabajo() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro De Equipo De Trabajo");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Equipo De Trabajo"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,450);
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
					
					int contador=0;
			 		String ListaEqTrabajo="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorEqTrabajo.getSelectedIndex() != 2 && contador == 1){
			 						ListaEqTrabajo=ListaEqTrabajo+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorEqTrabajo.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador seleccionado solo permite seleccionar un equipo de trabajo", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaEqTrabajo=ListaEqTrabajo+"'"+posicion+"'";
					 					}else{
					 						ListaEqTrabajo=ListaEqTrabajo+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaEqTrabajo=ListaEqTrabajo+"')";

			 			if(ListaEqTrabajo.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionEqTrabajo.setText(ListaEqTrabajo);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "select folio,descripcion from tb_equipo_trabajo where status = 1";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
//	FILTRO POR ENPLEADOS---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE ENPLEADO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Empleados extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorEmpleado = new JComboBox(operador);
		
		JTextField txtComparacionEmpleado = new JTextField();
		JButton btnSeleccionEmpleado = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarEmpleado = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarEmpleado = new JButton("Enviar");
		
		JTextArea txaArmadoEmpleado = new JTextArea();
		JScrollPane armadoEmpleado = new JScrollPane(txaArmadoEmpleado);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionEmpleado.setToolTipText("Seleccion de empleados");
			btnLimpiarEmpleado.setToolTipText("Limpiar");

			txaArmadoEmpleado.setBorder(border);
			txaArmadoEmpleado.setLineWrap(true);
			txaArmadoEmpleado.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Empleado ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorEmpleado).setBounds(80,y,100,20);
			
			panel.add(txtComparacionEmpleado).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionEmpleado).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarEmpleado).setBounds(390, y, 30, 20);
			
			panel.add(armadoEmpleado).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarEmpleado).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorEmpleado.addActionListener(opCompararEmpleado);
			btnLimpiarEmpleado.addActionListener(opLimpiarEmpleado);
			btnSeleccionEmpleado.addActionListener(opFiltroEmpleado);
			btnEnviarEmpleado.addActionListener(opEnviarEmpleado);
			
			txtComparacionEmpleado.setEditable(false);
			txaArmadoEmpleado.setEditable(false);
			btnSeleccionEmpleado.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Empleados(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararEmpleado = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEmpleado.getSelectedIndex() == 0){
					btnSeleccionEmpleado.setEnabled(false);
				}else{
					btnSeleccionEmpleado.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarEmpleado = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionEmpleado.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarEmpleado = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEmpleado.getSelectedIndex() == 0){
					txtFiltroEmpleados.setText("Empleado Todos");
				}else{
					if(txtComparacionEmpleado.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroEmpleados.setText(txaArmadoEmpleado.getText()+"");
						
						cadenaEmpleados=txtComparacionEmpleado.getText();
						operadorEmpleado=cmbOperadorEmpleado.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorEmpleado.getSelectedIndex()){
					case 0:  txaArmadoEmpleado.setText("");break;
					case 1:  txaArmadoEmpleado.setText("folio_empleado = "+txtComparacionEmpleado.getText()); break;
					case 2:  txaArmadoEmpleado.setText("folio_empleado IN "+txtComparacionEmpleado.getText()); break;
					case 3:  txaArmadoEmpleado.setText("folio_empleado < "+txtComparacionEmpleado.getText()); break;
					case 4:  txaArmadoEmpleado.setText("folio_empleado > "+txtComparacionEmpleado.getText()); break;
					case 5:  txaArmadoEmpleado.setText("folio_empleado <> "+txtComparacionEmpleado.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroEmpleado = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Empleados_Con_Cuadrante().setVisible(true);
				}
		};
		
// FILTRO DE EMPLEADOS CON CUADRNATE PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Empleados_Con_Cuadrante extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Empleados",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Empleados_Con_Cuadrante() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro De Empleados En Cuadrantes");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Empleados En Cuadrantes"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,450);
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
					
					int contador=0;
			 		String ListaEmpleados="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorEmpleado.getSelectedIndex() != 2 && contador == 1){
			 						ListaEmpleados=ListaEmpleados+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorEmpleado.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador seleccionado solo permite seleccionar un empleado", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaEmpleados=ListaEmpleados+"'"+posicion+"'";
					 					}else{
					 						ListaEmpleados=ListaEmpleados+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaEmpleados=ListaEmpleados+"')";

			 			if(ListaEmpleados.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionEmpleado.setText(ListaEmpleados);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_select_filtro_empleados_con_cuadrante_asignado";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
//	FILTRO POR ESTABLECIMIENTO---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE ENPLEADO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Establecimientos extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorEstablecimiento = new JComboBox(operador);
		
		JTextField txtComparacionEstablecimiento = new JTextField();
		JButton btnSeleccionListaEstablecimientos = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarEstablecimiento = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarEstablecimiento = new JButton("Enviar");
		
		JTextArea txaArmadoEstablecimiento = new JTextArea();
		JScrollPane armadoEstablecimiento = new JScrollPane(txaArmadoEstablecimiento);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionListaEstablecimientos.setToolTipText("Seleccion de establecimientos");
			btnLimpiarEstablecimiento.setToolTipText("Limpiar");

			txaArmadoEstablecimiento.setBorder(border);
			txaArmadoEstablecimiento.setLineWrap(true);
			txaArmadoEstablecimiento.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Establecimiento ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorEstablecimiento).setBounds(80,y,100,20);
			
			panel.add(txtComparacionEstablecimiento).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionListaEstablecimientos).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarEstablecimiento).setBounds(390, y, 30, 20);
			
			panel.add(armadoEstablecimiento).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarEstablecimiento).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorEstablecimiento.addActionListener(opCompararEstablecimiento);
			btnLimpiarEstablecimiento.addActionListener(opLimpiarEstablecimiento);
			btnSeleccionListaEstablecimientos.addActionListener(opFiltroEstablecimiento);
			btnEnviarEstablecimiento.addActionListener(opEnviarEstablecimiento);
			
			txtComparacionEstablecimiento.setEditable(false);
			txaArmadoEstablecimiento.setEditable(false);
			btnSeleccionListaEstablecimientos.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Establecimientos(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararEstablecimiento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEstablecimiento.getSelectedIndex() == 0){
					btnSeleccionListaEstablecimientos.setEnabled(false);
				}else{
					btnSeleccionListaEstablecimientos.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarEstablecimiento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionEstablecimiento.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarEstablecimiento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorEstablecimiento.getSelectedIndex() == 0){
					txtFiltroEstablecimiento.setText("Empleados Todos");
				}else{
					if(txtComparacionEstablecimiento.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroEstablecimiento.setText(txaArmadoEstablecimiento.getText()+"");
						
						cadenaEstablecimiento=txtComparacionEstablecimiento.getText();
						operadorEstablecimiento=cmbOperadorEstablecimiento.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorEstablecimiento.getSelectedIndex()){
					case 0:  txaArmadoEstablecimiento.setText("");break;
					case 1:  txaArmadoEstablecimiento.setText("folio_Establecimiento = "+txtComparacionEstablecimiento.getText()); break;
					case 2:  txaArmadoEstablecimiento.setText("folio_Establecimiento IN "+txtComparacionEstablecimiento.getText()); break;
					case 3:  txaArmadoEstablecimiento.setText("folio_Establecimiento < "+txtComparacionEstablecimiento.getText()); break;
					case 4:  txaArmadoEstablecimiento.setText("folio_Establecimiento > "+txtComparacionEstablecimiento.getText()); break;
					case 5:  txaArmadoEstablecimiento.setText("folio_Establecimiento <> "+txtComparacionEstablecimiento.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroEstablecimiento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Establecimiento().setVisible(true);
				}
		};
		
// FILTRO DE EMPLEADOS CON CUADRNATE PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Establecimiento extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Establecimiento",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Establecimiento() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro Por Establecimiento");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Establecimiento"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,450);
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
					
					int contador=0;
			 		String ListaEstablecimientos="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorEstablecimiento.getSelectedIndex() != 2 && contador == 1){
			 						ListaEstablecimientos=ListaEstablecimientos+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorEstablecimiento.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador asignado solo permite seleccionar un establecimientos", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaEstablecimientos=ListaEstablecimientos+"'"+posicion+"'";
					 					}else{
					 						ListaEstablecimientos=ListaEstablecimientos+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaEstablecimientos=ListaEstablecimientos+"')";
			 			
			 			if(ListaEstablecimientos.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionEstablecimiento.setText(ListaEstablecimientos);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_filtro_establecimiento_para_reporte";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
//	FILTRO POR PUESTO---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE ENPLEADO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Puesto extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorPuesto = new JComboBox(operador);
		
		JTextField txtComparacionPuesto = new JTextField();
		JButton btnSeleccionListaPuesto = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarPuesto = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarPuesto = new JButton("Enviar");
		
		JTextArea txaArmadoPuesto = new JTextArea();
		JScrollPane armadoPuesto = new JScrollPane(txaArmadoPuesto);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionListaPuesto.setToolTipText("Seleccion de puesto");
			btnLimpiarPuesto.setToolTipText("Limpiar");

			txaArmadoPuesto.setBorder(border);
			txaArmadoPuesto.setLineWrap(true);
			txaArmadoPuesto.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Puesto ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorPuesto).setBounds(80,y,100,20);
			
			panel.add(txtComparacionPuesto).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionListaPuesto).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarPuesto).setBounds(390, y, 30, 20);
			
			panel.add(armadoPuesto).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarPuesto).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorPuesto.addActionListener(opCompararPuesto);
			btnLimpiarPuesto.addActionListener(opLimpiarPuesto);
			btnSeleccionListaPuesto.addActionListener(opFiltroPuesto);
			btnEnviarPuesto.addActionListener(opEnviarPuesto);
			
			txtComparacionPuesto.setEditable(false);
			txaArmadoPuesto.setEditable(false);
			btnSeleccionListaPuesto.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Puesto(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararPuesto = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorPuesto.getSelectedIndex() == 0){
					btnSeleccionListaPuesto.setEnabled(false);
				}else{
					btnSeleccionListaPuesto.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarPuesto = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionPuesto.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarPuesto = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorPuesto.getSelectedIndex() == 0){
					txtFiltroEstablecimiento.setText("Empleados Todos");
				}else{
					if(txtComparacionPuesto.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroPuesto.setText(txaArmadoPuesto.getText()+"");
						
						cadenaPuesto=txtComparacionPuesto.getText();
						operadorPuesto=cmbOperadorPuesto.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorPuesto.getSelectedIndex()){
					case 0:  txaArmadoPuesto.setText("");break;
					case 1:  txaArmadoPuesto.setText("folio_puesto = "+txtComparacionPuesto.getText()); break;
					case 2:  txaArmadoPuesto.setText("folio_puesto IN "+txtComparacionPuesto.getText()); break;
					case 3:  txaArmadoPuesto.setText("folio_puesto < "+txtComparacionPuesto.getText()); break;
					case 4:  txaArmadoPuesto.setText("folio_puesto > "+txtComparacionPuesto.getText()); break;
					case 5:  txaArmadoPuesto.setText("folio_puesto <> "+txtComparacionPuesto.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroPuesto = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Puesto().setVisible(true);
				}
		};
		
// FILTRO DE PUESTOS PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Puesto extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Puesto",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Puesto() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro Por Puestos");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Puesto"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,450);
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
					
					int contador=0;
			 		String ListaPuestos="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorPuesto.getSelectedIndex() != 2 && contador == 1){
			 						ListaPuestos=ListaPuestos+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorPuesto.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador asignado solo permite seleccionar un puesto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaPuestos=ListaPuestos+"'"+posicion+"'";
					 					}else{
					 						ListaPuestos=ListaPuestos+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaPuestos=ListaPuestos+"')";

			 			if(ListaPuestos.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionPuesto.setText(ListaPuestos);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_filtro_puesto_para_reporte";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
//	FILTRO POR DEPARTAMENTO---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE DEPARTAMENTO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Departamento extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorDepartamento = new JComboBox(operador);
		
		JTextField txtComparacionDepartamento = new JTextField();
		JButton btnSeleccionListaDepartamento = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarDepartamento = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarDepartamento = new JButton("Enviar");
		
		JTextArea txaArmadoDepartamento = new JTextArea();
		JScrollPane armadoDepartamento = new JScrollPane(txaArmadoDepartamento);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionListaDepartamento.setToolTipText("Seleccion de Departamentos");
			btnLimpiarDepartamento.setToolTipText("Limpiar");

			txaArmadoDepartamento.setBorder(border);
			txaArmadoDepartamento.setLineWrap(true);
			txaArmadoDepartamento.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Departamento ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorDepartamento).setBounds(80,y,100,20);
			
			panel.add(txtComparacionDepartamento).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionListaDepartamento).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarDepartamento).setBounds(390, y, 30, 20);
			
			panel.add(armadoDepartamento).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarDepartamento).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorDepartamento.addActionListener(opCompararDepartamento);
			btnLimpiarDepartamento.addActionListener(opLimpiarDepartamento);
			btnSeleccionListaDepartamento.addActionListener(opFiltroDepartamento);
			btnEnviarDepartamento.addActionListener(opEnviarDepartamento);
			
			txtComparacionDepartamento.setEditable(false);
			txaArmadoDepartamento.setEditable(false);
			btnSeleccionListaDepartamento.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Departamento(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararDepartamento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorDepartamento.getSelectedIndex() == 0){
					btnSeleccionListaDepartamento.setEnabled(false);
				}else{
					btnSeleccionListaDepartamento.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarDepartamento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionDepartamento.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarDepartamento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorDepartamento.getSelectedIndex() == 0){
					txtFiltroDepartamento.setText("departamento Todos");
				}else{
					if(txtComparacionDepartamento.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroDepartamento.setText(txaArmadoDepartamento.getText()+"");
						
						cadenaDepartamento=txtComparacionDepartamento.getText();
						operadorDepartamento=cmbOperadorDepartamento.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorDepartamento.getSelectedIndex()){
					case 0:  txaArmadoDepartamento.setText("");break;
					case 1:  txaArmadoDepartamento.setText("folio_departamento = "+txtComparacionDepartamento.getText()); break;
					case 2:  txaArmadoDepartamento.setText("folio_departamento IN "+txtComparacionDepartamento.getText()); break;
					case 3:  txaArmadoDepartamento.setText("folio_departamento < "+txtComparacionDepartamento.getText()); break;
					case 4:  txaArmadoDepartamento.setText("folio_departamento > "+txtComparacionDepartamento.getText()); break;
					case 5:  txaArmadoDepartamento.setText("folio_departamento <> "+txtComparacionDepartamento.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroDepartamento = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Departamento().setVisible(true);
				}
		};
		
// FILTRO DE PUESTOS PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Departamento extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Departamento",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Departamento() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro Por Departamentos");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Departamento"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,360);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,450);
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
					
					int contador=0;
			 		String ListaDepartamentos="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorDepartamento.getSelectedIndex() != 2 && contador == 1){
			 						ListaDepartamentos=ListaDepartamentos+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorDepartamento.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "SEl operador asignado solo permite seleccionar un departamento", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaDepartamentos=ListaDepartamentos+"'"+posicion+"'";
					 					}else{
					 						ListaDepartamentos=ListaDepartamentos+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaDepartamentos=ListaDepartamentos+"')";
			 			
			 			if(ListaDepartamentos.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionDepartamento.setText(ListaDepartamentos);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_filtro_departamento_para_reporte";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
	
//	FILTRO POR NIVEL CRITICO---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE NIVEL CRITICO CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Nivel_Critico extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorNivelCritico = new JComboBox(operador);
		
		JTextField txtComparacionNivelCritico = new JTextField();
		JButton btnSeleccionListaNivelCritico = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarNivelCritico = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarNivelCritico = new JButton("Enviar");
		
		JTextArea txaArmadoNivelCritico = new JTextArea();
		JScrollPane armadoNivelCritico = new JScrollPane(txaArmadoNivelCritico);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionListaNivelCritico.setToolTipText("Seleccion de nivel critico");
			btnLimpiarNivelCritico.setToolTipText("Limpiar");

			txaArmadoNivelCritico.setBorder(border);
			txaArmadoNivelCritico.setLineWrap(true);
			txaArmadoNivelCritico.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Nivel Critico ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorNivelCritico).setBounds(80,y,100,20);
			
			panel.add(txtComparacionNivelCritico).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionListaNivelCritico).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarNivelCritico).setBounds(390, y, 30, 20);
			
			panel.add(armadoNivelCritico).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarNivelCritico).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorNivelCritico.addActionListener(opCompararNivelCritico);
			btnLimpiarNivelCritico.addActionListener(opLimpiarNivelCritico);
			btnSeleccionListaNivelCritico.addActionListener(opFiltroNivelCritico);
			btnEnviarNivelCritico.addActionListener(opEnviarNivelCritico);
			
			txtComparacionNivelCritico.setEditable(false);
			txaArmadoNivelCritico.setEditable(false);
			btnSeleccionListaNivelCritico.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Nivel_Critico(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararNivelCritico = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorNivelCritico.getSelectedIndex() == 0){
					btnSeleccionListaNivelCritico.setEnabled(false);
				}else{
					btnSeleccionListaNivelCritico.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarNivelCritico = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionNivelCritico.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarNivelCritico = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorNivelCritico.getSelectedIndex() == 0){
					txtFiltroNivelCritico.setText("Nivel Critico Todos");
				}else{
					if(txtComparacionNivelCritico.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroNivelCritico.setText(txaArmadoNivelCritico.getText()+"");
						
						cadenaNivelCritico=txtComparacionNivelCritico.getText();
						operadorNivelCritico=cmbOperadorNivelCritico.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorNivelCritico.getSelectedIndex()){
					case 0:  txaArmadoNivelCritico.setText("");break;
					case 1:  txaArmadoNivelCritico.setText("folio_nivel_critico = "+txtComparacionNivelCritico.getText()); break;
					case 2:  txaArmadoNivelCritico.setText("folio_nivel_critico IN "+txtComparacionNivelCritico.getText()); break;
					case 3:  txaArmadoNivelCritico.setText("folio_nivel_critico < "+txtComparacionNivelCritico.getText()); break;
					case 4:  txaArmadoNivelCritico.setText("folio_nivel_critico > "+txtComparacionNivelCritico.getText()); break;
					case 5:  txaArmadoNivelCritico.setText("folio_nivel_critico <> "+txtComparacionNivelCritico.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroNivelCritico = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Departamento().setVisible(true);
				}
		};
		
// FILTRO DE PUESTOS PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Departamento extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Departamento",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Departamento() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro Por Niveles Criticos");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Nivel Critico"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);  

				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,90);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
				
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,180);
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
					
					int contador=0;
					
			 		String ListaNivelCritico="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorNivelCritico.getSelectedIndex() != 2 && contador == 1){
			 						ListaNivelCritico=ListaNivelCritico+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorNivelCritico.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador asignado solo permite seleccionar un Nivel Critico", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaNivelCritico=ListaNivelCritico+"'"+posicion+"'";
					 					}else{
					 						ListaNivelCritico=ListaNivelCritico+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaNivelCritico=ListaNivelCritico+"')";
			 			
			 			if(ListaNivelCritico.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionNivelCritico.setText(ListaNivelCritico);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_filtro_nivel_critico_para_reporte";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
	
	
	
	
	
	
	
	
//	FILTRO POR OPCIONES DE RESPUESTA---------------------------------------------------------------------------------------------------------------------
	
// ARMADO DE QUERY DE SELECCION DE OPCION DE RESPUESTA CON OPERADOR-------------------------------------------------------------------------------------
	public class Cat_Condiciones_Tipo_De_Respuesta extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String operador[] = {"Todos","Igual","Esta en lista","Menor que","Mayor que","Diferente"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOperadorRespuesta = new JComboBox(operador);
		
		JTextField txtComparacionRespuesta = new JTextField();
		JButton btnSeleccionListaRespuesta = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
		JButton btnLimpiarRespuesta = new JButton(new ImageIcon("Iconos/limpiar.png"));
		JButton btnEnviarRespuesta = new JButton("Enviar");
		
		JTextArea txaArmadoRespuesta = new JTextArea();
		JScrollPane armadoRespuesta = new JScrollPane(txaArmadoRespuesta);
		Border border = LineBorder.createGrayLineBorder();
		
		public void getConstructor(){

			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Reportes De Cuadrantes"));
			this.setTitle("Seleccion Tipo De Reporte");
			
			btnSeleccionListaRespuesta.setToolTipText("Seleccion de Tipo De Respuesta");
			btnLimpiarRespuesta.setToolTipText("Limpiar");

			txaArmadoRespuesta.setBorder(border);
			txaArmadoRespuesta.setLineWrap(true);
			txaArmadoRespuesta.setDocument(new JTextFieldLimit(240));
			
			int y=20;
			
			panel.add(new JLabel("Tipo De Respuesta ")).setBounds(20,y+=35,60,20);
			panel.add(cmbOperadorRespuesta).setBounds(80,y,100,20);
			
			panel.add(txtComparacionRespuesta).setBounds(200, y, 140, 20);
			panel.add(btnSeleccionListaRespuesta).setBounds(350, y, 30, 20);
			panel.add(btnLimpiarRespuesta).setBounds(390, y, 30, 20);
			
			panel.add(armadoRespuesta).setBounds(20, y+=35, 400, 100);
			
			panel.add(btnEnviarRespuesta).setBounds(350, y+=110, 70, 20);
			
			cmbOperadorRespuesta.addActionListener(opCompararRespuesta);
			btnLimpiarRespuesta.addActionListener(opLimpiarRespuesta);
			btnSeleccionListaRespuesta.addActionListener(opFiltroRespuesta);
			btnEnviarRespuesta.addActionListener(opEnviarRespuesta);
			
			txtComparacionRespuesta.setEditable(false);
			txaArmadoRespuesta.setEditable(false);
			btnSeleccionListaRespuesta.setEnabled(false);

			cont.add(panel);
			this.setSize(460,280);
			this.setLocationRelativeTo(null);
		}
		
		public Cat_Condiciones_Tipo_De_Respuesta(){
			this.setModal(true);
			 getConstructor();
		}
		
		ActionListener opCompararRespuesta = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorRespuesta.getSelectedIndex() == 0){
					btnSeleccionListaRespuesta.setEnabled(false);
				}else{
					btnSeleccionListaRespuesta.setEnabled(true);
				}
				
				actionAplicar();
			}
		};
		
		ActionListener opLimpiarRespuesta = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				txtComparacionRespuesta.setText("");
				actionAplicar();
			}
		};
		
		ActionListener opEnviarRespuesta = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cmbOperadorRespuesta.getSelectedIndex() == 0){
					txtFiltroRespuesta.setText("Tipo De Respuesta Todas");
				}else{
					if(txtComparacionRespuesta.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Seleccione un parametro", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						
						txtFiltroRespuesta.setText(txaArmadoRespuesta.getText()+"");
						
						cadenaRespuesta=txtComparacionRespuesta.getText();
						operadorRespuesta=cmbOperadorRespuesta.getSelectedIndex();
					}
				}
				
				dispose();
			}
		};
		
		public void actionAplicar(){
				switch(cmbOperadorRespuesta.getSelectedIndex()){
					case 0:  txaArmadoRespuesta.setText("");break;
					case 1:  txaArmadoRespuesta.setText("folio_respuesta = "+txtComparacionRespuesta.getText()); break;
					case 2:  txaArmadoRespuesta.setText("folio_respuesta IN "+txtComparacionRespuesta.getText()); break;
					case 3:  txaArmadoRespuesta.setText("folio_respuesta < "+txtComparacionRespuesta.getText()); break;
					case 4:  txaArmadoRespuesta.setText("folio_respuesta > "+txtComparacionRespuesta.getText()); break;
					case 5:  txaArmadoRespuesta.setText("folio_respuesta <> "+txtComparacionRespuesta.getText()); break;
				}
		}
		
//		Cat_Filtro_Actividades
		ActionListener opFiltroRespuesta = new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
					new Cat_Filtro_Departamento().setVisible(true);
				}
		};
		
// FILTRO DE OPCIONES DE RESPUESTA PARA ASIGNAR EN LA CONDICION DEL QUERY	
	 	public class Cat_Filtro_Departamento extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			String dia = "";
			
			Object[][] MatrizFiltro ;
			
			Object[][] getTablaFiltro = getTablaFiltro();
			DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
		            new String[]{"Folio", "Departamento",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.Integer.class,
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
		        	 	case 2 : return true;
		        	 		
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
			
			JButton btnAgregar = new JButton(new ImageIcon("Iconos/agregar.png"));
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			
			public Cat_Filtro_Departamento() {
				
				this.setModal(true);
				setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
				setTitle("Filtro Por Tipos De Respuesta");
				campo.setBorder(BorderFactory.createTitledBorder("Seleccion De Tipo de Respuesta"));
				trsfiltro = new TableRowSorter(modeloFiltro); 
				tablaFiltro.setRowSorter(trsfiltro);
				
				btnAgregar.setToolTipText("Agregar");
				
				campo.add(scroll).setBounds(15,43,374,90);
				
				campo.add(txtFolio).setBounds(15,20,40,20);
				campo.add(txtNombre_Completo).setBounds(56,20,280,20);
				campo.add(btnAgregar).setBounds(340,20,50,20);
				
				cont.add(campo);
				
				tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
				tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(280);
				tablaFiltro.getColumnModel().getColumn(1).setMinWidth(280);
				tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(40);
				tablaFiltro.getColumnModel().getColumn(2).setMinWidth(40);
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
								componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
								if(row%2==0){
									((JComponent) componente).setOpaque(true); 
									componente.setBackground(new java.awt.Color(177,177,177));	
								}
								if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,2).toString())){
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
				
				txtFolio.addKeyListener(opFiltroFolio);
				txtNombre_Completo.addKeyListener(opFiltroNombre);
				
				btnAgregar.addActionListener(opAgregar);
				
				setSize(415,180);
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
					
					int contador=0;
			 		String ListaRespuesta="('";	
			 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
			 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 2).toString()) == true){
			 					
			 					int posicion = Integer.parseInt(modeloFiltro.getValueAt(i, 0).toString().trim());
			 					
			 					contador=contador+=1;
			 					
			 					if(cmbOperadorRespuesta.getSelectedIndex() != 2 && contador == 1){
			 						ListaRespuesta=ListaRespuesta+"'"+posicion+"'";
			 					}else{
			 						
			 						if(cmbOperadorRespuesta.getSelectedIndex() != 2 && contador != 1){
			 							JOptionPane.showMessageDialog(null, "El operador asignado solo permite seleccionar una opcion de respuesta", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			 							return;
			 						}else{
			 							if(contador == 1){
			 								ListaRespuesta=ListaRespuesta+"'"+posicion+"'";
					 					}else{
					 						ListaRespuesta=ListaRespuesta+"'"+","+"'"+"'"+posicion+"'";
					 					}
			 						}
			 					}
			 				}
			 			}
			 			
			 			ListaRespuesta=ListaRespuesta+"')";
			 			
			 			if(ListaRespuesta.equals("('')")){
			 				JOptionPane.showMessageDialog(null, "Es necesario seleccionar un argunemto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
 							return;
			 			}else{
			 				txtComparacionRespuesta.setText(ListaRespuesta);
				 			actionAplicar();
				 			dispose();
			 			}
				}
			};
			
			
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
				String todos = "exec sp_filtro_respuesta_para_reporte";
				Statement s;
				ResultSet rs;
				try {
					s = new Connexion().conexion().createStatement();
					rs = s.executeQuery(todos);
					
					MatrizFiltro = new Object[getFilas(todos)][3];
					int i=0;
					while(rs.next()){
						int folio = rs.getInt(1);
						MatrizFiltro[i][0] = folio+"  ";
						MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
						MatrizFiltro[i][2] = false;
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    return MatrizFiltro; 
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
	}
}
