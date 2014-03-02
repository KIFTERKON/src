package checador;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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

import com.toedter.calendar.JDateChooser;

import ObjetoChecador.Obj_Encargado_De_Solicitudes;
import ObjetoChecador.Obj_Seleccion_Operaciones;
import SQL.BuscarTablasModel;

import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Seleccion_Solicitudes_Empleados extends JFrame {
	
	Container cont = getContentPane();
//	panel de datos del empleado solicitante
	JLayeredPane filtro = new JLayeredPane();
//	panel de alimentacion de permisoso
	JLayeredPane llenado_permisos = new JLayeredPane();
	
	public static Object[][] get_tabla_model(String status){return new BuscarTablasModel().tabla_model_filtro_solicitudes(status);}
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JTextField txtFiltro_Nombre_Completo = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
    public static DefaultTableModel tabla_model = new DefaultTableModel(
    		null,	new String[]{"Solicitud","Empleado","Establecimiento","Status Solicitud"}){
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
    
            static JTable tabla = new JTable(tabla_model);
            JScrollPane panelScroll = new JScrollPane(tabla);
	
	final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
//filtro-------------------------------------------------------------------------------------------
	JLabel lblFolio = new JLabel("Folio: ");
	JTextField txtFolio_Empleado = new JTextField();
	
	JLabel lblEncargado = new JLabel("Solicito: ");
	JLabel lblNombre_Encargado = new JLabel("");
	JLabel lblFoto = new 	JLabel();
	
	JLabel lblEmpleado = new JLabel("Para el empleado: ");
	JTextField txtEmpleado = new JTextField();
	JLabel lblEstablecimiento = new JLabel("Del establecimiento: ");
	JTextField txtEstablecimiento = new JTextField();
	JLabel lblPuesto = new JLabel("Con el puesto de: ");
	JTextField txtPuesto = new JTextField();

	JLabel lblTelefono_Propio = new JLabel("Tel. Propio: ");
	JTextField txtTelefono_Propio = new JTextField();
	JLabel lblDescanso = new JLabel("Descanso: ");
	JTextField txtDescanso = new JTextField();
	JLabel lblDobla = new JLabel("Dobla: ");
	JTextField txtDobla = new JTextField();
	
	JLabel lblStatus = new JLabel("Status: ");
	JTextField txtStatus = new JTextField();

//llenado-----------------------------------------------------------------------------------------------
	JLabel lblFolioSolicitud = new JLabel("Solicitud:");
	JTextField txtFolioSolicitud= new JTextField();
	
	JLabel lblMovimiento = new JLabel("");
	
	JDateChooser txtFechaSolicitada = new JDateChooser();
	JButton btnRegresar = new JButton("Regresar");
	
	JTextArea txaMotivo = new JTextArea("");
	JScrollPane Observasiones = new JScrollPane(txaMotivo);
	
	JLabel lblPrestamo = new JLabel("Prestamo solicitado: ");
	JLabel lblAumento = new JLabel("Sueldo solicitado:");
	
	JLabel lblCambio = new JLabel("");
	JTextField txtCambio = new JTextField();
	
	JRadioButton rbTemporal = new JRadioButton("Termporal",true);
	JRadioButton rbFijo = new JRadioButton("Fijo");
	ButtonGroup tipo_de_solicitud = new ButtonGroup();
	
//CUESTIONARIO DE CALIFICACION DETERMINADA DE ACUERDO A LAS POLITICAS DE LA EMPRESA PARA TRABAJADOR	
		JLabel lblMarcoEvaluacion = new JLabel();
		
		JLabel lblEvaluacion1 = new JLabel("1.-PUNTUALIDAD Y ASISTENCIA");
		JLabel lblEvaluacion2 = new JLabel("2.-CUMPLIMIENTO DE TAREAS");
		JLabel lblEvaluacion3 = new JLabel("3.-DICIPLINA EN EL TRABAJO");
		JLabel lblEvaluacion4 = new JLabel("4.-RESPETO Y TRABAJO GENERAL");
	
		JLabel lblEtiquetaMB = new JLabel("MUY BUENO");
		JLabel lblEtiquetaB = new JLabel("BUENO");
		JLabel lblEtiquetaR = new JLabel("REGULAR");
		
		JRadioButton rbMBueno1 = new JRadioButton("");
		JRadioButton rbBueno1 = new JRadioButton("");
		JRadioButton rbRegular1 = new JRadioButton("");
		JRadioButton rbReset1 = new JRadioButton("",true);
		ButtonGroup Cuentionario1 = new ButtonGroup();
		
		JRadioButton rbMBueno2 = new JRadioButton("");
		JRadioButton rbBueno2 = new JRadioButton("");
		JRadioButton rbRegular2 = new JRadioButton("");
		JRadioButton rbReset2 = new JRadioButton("",true);
		ButtonGroup Cuentionario2 = new ButtonGroup();
		
		JRadioButton rbMBueno3 = new JRadioButton("");
		JRadioButton rbBueno3 = new JRadioButton("");
		JRadioButton rbRegular3 = new JRadioButton("");
		JRadioButton rbReset3 = new JRadioButton("",true);
		ButtonGroup Cuentionario3 = new ButtonGroup();
		
		JRadioButton rbMBueno4 = new JRadioButton("");
		JRadioButton rbBueno4 = new JRadioButton("");
		JRadioButton rbRegular4 = new JRadioButton("");
		JRadioButton rbReset4 = new JRadioButton("",true);
		ButtonGroup Cuentionario4 = new ButtonGroup();
//---------------------------------------------------------------------------------------------------------
	Border blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
	Border border = LineBorder.createGrayLineBorder();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Seleccion_Solicitudes_Empleados(){
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		this.filtro.setBorder(BorderFactory.createTitledBorder(blackline,"Filtro"));
		this.llenado_permisos.setBorder(BorderFactory.createTitledBorder(blackline,"Solicitud"));

		trsfiltro = new TableRowSorter(tabla_model); 
		tabla.setRowSorter(trsfiltro); 
		
	//grupo tipo de cambio
		tipo_de_solicitud.add(rbTemporal);
		tipo_de_solicitud.add(rbFijo);
		
	//grupo por pregunta -------------------
		Cuentionario1.add(rbMBueno1);
		Cuentionario1.add(rbBueno1);
		Cuentionario1.add(rbRegular1);
		Cuentionario1.add(rbReset1);
		
		Cuentionario2.add(rbMBueno2);
		Cuentionario2.add(rbBueno2);
		Cuentionario2.add(rbRegular2);
		Cuentionario2.add(rbReset2);
		
		Cuentionario3.add(rbMBueno3);
		Cuentionario3.add(rbBueno3);
		Cuentionario3.add(rbRegular3);
		Cuentionario3.add(rbReset3);
		
		Cuentionario4.add(rbMBueno4);
		Cuentionario4.add(rbBueno4);
		Cuentionario4.add(rbRegular4);
		Cuentionario4.add(rbReset4);
	//--------------------------------------
		
		pfiltor();
		llenadoBajo();
		
		ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setIcon(iconoDefault);

        campos_false();
        
        agregar(tabla);
        
        txtFiltro_Nombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		splitPane.setSize(800,520);
		splitPane.setTopComponent(filtro);
		splitPane.setBottomComponent(llenado_permisos);
		
	    cont.add(splitPane, BorderLayout.CENTER);
	    cont.setVisible(true);

		splitPane.setDividerLocation(0.42);
		
		//ancho de espacio de divicion
		splitPane.setDividerSize(1);
		
		this.setSize(800,520);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void pfiltor(){
		init_tabla();
		filtro.add(txtFiltro_Nombre_Completo).setBounds(65,18,260,20);
		filtro.add(cmbEstablecimientos).setBounds(325,18,130,20);
		
		filtro.add(panelScroll).setBounds(5,40,630,150);
		filtro.add(lblFoto).setBounds(637,40,150,150);
	}
	
	public void campos_false(){
		txtStatus.setEditable(false);
		txtEmpleado.setEditable(false);
		txtEstablecimiento.setEditable(false);
		txtTelefono_Propio.setEditable(false);
		txtPuesto.setEditable(false);
		txtDescanso.setEditable(false);
		txtDobla.setEditable(false);
		
		txtFolioSolicitud.setEditable(false);
		
		txtCambio.setEditable(false);
		txaMotivo.setEditable(false);
		txtFechaSolicitada.setEnabled(false);
		
//tipo de cambio				
		rbTemporal.setEnabled(false);
		rbFijo.setEnabled(false);
		
//cuestionario
		lblMarcoEvaluacion.setEnabled(false);
		
		lblEtiquetaMB.setEnabled(false);
		lblEtiquetaB.setEnabled(false);
		lblEtiquetaR.setEnabled(false);
		
		lblEvaluacion1.setEnabled(false);
		rbMBueno1.setEnabled(false);
		rbBueno1.setEnabled(false);
		rbRegular1.setEnabled(false);
		rbReset1.setEnabled(false);

		lblEvaluacion2.setEnabled(false);
		rbMBueno2.setEnabled(false);
		rbBueno2.setEnabled(false);
		rbRegular2.setEnabled(false);
		rbReset2.setEnabled(false);

		lblEvaluacion3.setEnabled(false);
		rbMBueno3.setEnabled(false);
		rbBueno3.setEnabled(false);
		rbRegular3.setEnabled(false);
		rbReset3.setEnabled(false);

		lblEvaluacion4.setEnabled(false);
		rbMBueno4.setEnabled(false);
		rbBueno4.setEnabled(false);
		rbRegular4.setEnabled(false);
		rbReset4.setEnabled(false);
		
	}
	
	public void llenadoBajo(){
		
		txaMotivo.setBorder(border);
		this.txtFechaSolicitada.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.lblMarcoEvaluacion.setBorder(BorderFactory.createTitledBorder(blackline,"Calificar Empleado"));
		
		lblMovimiento.setFont(new Font("arial", Font.BOLD, 20));	
		lblMovimiento.setForeground(new java.awt.Color(105,105,105));
		
		int y=10;
		
		llenado_permisos.add(lblEncargado).setBounds(15,10,100,20);
		llenado_permisos.add(lblNombre_Encargado).setBounds(115,10,270,20);
		
//		llenado_permisos.add(lblFolio).setBounds(60,y,80,20);
//		llenado_permisos.add(txtFolio_Empleado).setBounds(160,y,70,20);

		llenado_permisos.add(lblEmpleado).setBounds(395,y,90,20);
		llenado_permisos.add(txtEmpleado).setBounds(495,y,270,20);
		
		llenado_permisos.add(lblEstablecimiento).setBounds(15,y+=22,150,20);
		llenado_permisos.add(txtEstablecimiento).setBounds(115,y,150,20);
		
		llenado_permisos.add(lblPuesto).setBounds(285,y,90,20);
		llenado_permisos.add(txtPuesto).setBounds(385,y,250,20);
		
		llenado_permisos.add(lblStatus).setBounds(655,y,70,20);
		llenado_permisos.add(txtStatus).setBounds(695,y,70,20);
		
		llenado_permisos.add(lblDescanso).setBounds(15,y+=22,70,20);
		llenado_permisos.add(txtDescanso).setBounds(115,y,150,20);
		
		llenado_permisos.add(lblDobla).setBounds(285,y,70,20);
		llenado_permisos.add(txtDobla).setBounds(385,y,70,20);
		
		llenado_permisos.add(lblTelefono_Propio).setBounds(470,y,70,20);
		llenado_permisos.add(txtTelefono_Propio).setBounds(535,y,100,20);
		
		llenado_permisos.add(lblFolioSolicitud).setBounds(647,y,70,20);
		llenado_permisos.add(txtFolioSolicitud).setBounds(695,y,70,20);
		
		llenado_permisos.add(lblMovimiento).setBounds(20, 75, 550, 25);
			
		llenado_permisos.add(lblCambio).setBounds(15,98,140,20);
		llenado_permisos.add(txtCambio).setBounds(115,98,215,20);
		
		llenado_permisos.add(new JLabel("Fecha solicitada: ")).setBounds(345,98,100,20);
		llenado_permisos.add(txtFechaSolicitada).setBounds(430,98,100,20);
		llenado_permisos.add(Observasiones).setBounds(10, 120, 320, 150);
//		
		llenado_permisos.add(rbTemporal).setBounds(420, 240, 80, 20);
		llenado_permisos.add(rbFijo).setBounds(530, 240, 60, 20);

		llenado_permisos.add(lblEtiquetaMB).setBounds(505, 137, 70, 20);
		llenado_permisos.add(lblEtiquetaB).setBounds(590, 137, 70, 20);
		llenado_permisos.add(lblEtiquetaR).setBounds(660, 137, 70, 20);
		
//		
		llenado_permisos.add(lblMarcoEvaluacion).setBounds(337, 132, 435, 95);
//		llenado_permisos.add(lblPolitica1).setBounds(x, y, 270, 90);
//		llenado_permisos.add(lblPolitica2).setBounds(x, y+=17, 270, 90);
//		llenado_permisos.add(lblPolitica3).setBounds(x, y+=17, 270, 90);
//		llenado_permisos.add(lblPolitica4).setBounds(x, y+=17, 270, 90);
		int x=345;
		y=152;
		llenado_permisos.add(lblEvaluacion1).setBounds(x, y, 170, 20);
		llenado_permisos.add(rbMBueno1).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno1).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular1).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset1).setBounds(x+=75, y, 20, 20);
		x=345;
		llenado_permisos.add(lblEvaluacion2).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno2).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno2).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular2).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset2).setBounds(x+=75, y, 20, 20);
		x=345;
		llenado_permisos.add(lblEvaluacion3).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno3).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno3).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular3).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset3).setBounds(x+=75, y, 20, 20);
		x=345;
		llenado_permisos.add(lblEvaluacion4).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno4).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno4).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular4).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset4).setBounds(x+=75, y, 20, 20);
////	------------------------------------------------------------------------------------------------------
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 1){
	    			int fila = tabla.getSelectedRow();
	    			Object folio =  tabla.getValueAt(fila, 0).toString().trim();

	    			Obj_Seleccion_Operaciones solicitud = new Obj_Seleccion_Operaciones().buscar(Integer.valueOf(folio+""));
	    			
	    			if(solicitud.getSopeca().equals("1.- Permiso Para Trabajar Corrido") || solicitud.getSopeca().equals("2.- Permiso Para Salir Temprano") ||
	    					solicitud.getSopeca().equals("3.- Permiso Para Entrar Tarde") || solicitud.getSopeca().equals("4.- Permiso Para No Asistir Con Goce de Sueldo") ||
	    					solicitud.getSopeca().equals("5.- Permiso Para No Asistir Sin Goce de Sueldo'") || solicitud.getSopeca().equals("6.- Permiso Para Tiempo de Comida Determinado") ||
	    					solicitud.getSopeca().equals("3.- Solicitud de Vacaciones") || solicitud.getSopeca().equals("6.- Solicitud de Renuncia") || solicitud.getSopeca().equals("4.- Solicitud de Gafete") || 
	    					solicitud.getSopeca().equals("5.- Solicitud de Uniforme")){
	    				
	    				lblCambio.setVisible(false);
	    				txtCambio.setVisible(false);
	    			}else{
	    				lblCambio.setVisible(true);
	    				txtCambio.setVisible(true);
	    			}
	    			
	    			if(solicitud.getSopeca().equals("1.- Solicitud de Aumento de Sueldo") || solicitud.getSopeca().equals("2.- Solicitud de Prestamo")){
	    				lblCambio.setText("Cantidad Solicitada: ");
	    			}else{
	    				
	    				switch(solicitud.getSopeca()){
	    					case "1.- Cambio de Sucursal":		lblCambio.setText("Sucursal: ");break;
	    					case "2.- Cambio de Puesto":		lblCambio.setText("Puesto: ");break;
	    					case "3.- Cambio de Departamento":	lblCambio.setText("Departamento: ");break;
	    					case "4.- Cambio de Turno":			lblCambio.setText("Turno: ");break;
	    					case "5.- Cambio de Descanso":		lblCambio.setText("Descanso: ");break;
	    					case "6.- Cambio de Doblada":		lblCambio.setText("Doblada: ");break;
	    				}
	    			}
	    			
	    			
	    			lblNombre_Encargado.setText(solicitud.getNombre_encargado());
	    			txtEmpleado.setText(solicitud.getNombre_empleado());
	    			txtEstablecimiento.setText(solicitud.getEstablecimiento());
	    			txtPuesto.setText(solicitud.getPuesto());
	    			txtDescanso.setText(solicitud.getDescanso());
	    			txtDobla.setText(solicitud.getDobla());
	    			txtStatus.setText(solicitud.getStatus());
	    			txtTelefono_Propio.setText(solicitud.getTelefono());
	    			
	    			txtFolioSolicitud.setText(folio.toString());
	    			
	    			lblMovimiento.setText(solicitud.getSopeca());
	    			txtCambio.setText(solicitud.getCambio());
	    			
	    			ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
			        lblFoto.setIcon(iconoDefault);
	    			
	    			try {
	    					Date date_fecha_solicitada = new SimpleDateFormat("dd/MM/yyyy").parse(solicitud.getFecha_solicitada());

			    			if(new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1900").before(date_fecha_solicitada)){
			    				txtFechaSolicitada.setDate(date_fecha_solicitada);
							}else{
								txtFechaSolicitada.setDate(null);
							}
		    			
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
	    			
					txaMotivo.setText(solicitud.getMotivo());
					
					switch(solicitud.getPuntualidad()){
						case 0:	rbReset1.setSelected(true);		break;
						case 1:	rbRegular1.setSelected(true);	break;
						case 2:	rbBueno1.setSelected(true);		break;
						case 3:	rbMBueno1.setSelected(true);	break;
					}
					
					switch(solicitud.getCumplimiento()){
						case 0:	rbReset2.setSelected(true);		break;
						case 1:	rbRegular2.setSelected(true);	break;
						case 2:	rbBueno2.setSelected(true);		break;
						case 3:	rbMBueno2.setSelected(true);	break;
					}
					
					switch(solicitud.getDiciplina()){
						case 0:	rbReset3.setSelected(true);		break;
						case 1:	rbRegular3.setSelected(true);	break;
						case 2:	rbBueno3.setSelected(true);		break;
						case 3:	rbMBueno3.setSelected(true);	break;
					}
					
					switch(solicitud.getRespeto()){
						case 0:	rbReset4.setSelected(true);		break;
						case 1:	rbRegular4.setSelected(true);	break;
						case 2:	rbBueno4.setSelected(true);		break;
						case 3:	rbMBueno4.setSelected(true);	break;
					}
					
					if(solicitud.getTipo_solicitud().equals("FIJO")){
						rbFijo.setSelected(true);
					}else{
						rbTemporal.setSelected(true);
					}
	        	}
	        }
        });
    }
	
	public void limpiar(){
		
		ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setIcon(iconoDefault);
        
		lblNombre_Encargado.setText("");
		txtEmpleado.setText("");
		txtEstablecimiento.setText("");
		txtPuesto.setText("");
		txtStatus.setText("");
		txtDescanso.setText("");
		txtDobla.setText("");
		txtTelefono_Propio.setText("");
		
		lblFolioSolicitud.setText("");
		txtFolioSolicitud.setText("");
		
		lblMovimiento.setText("");
		
		txtFechaSolicitada.setDate(null);
		
		lblCambio.setText("");
		txtCambio.setText("");
		
		rbReset1.setSelected(true);
		rbReset2.setSelected(true);
		rbReset3.setSelected(true);
		rbReset4.setSelected(true);
		
		rbTemporal.setSelected(true);
		
		txaMotivo.setText("");
	}
	
	KeyListener opFiltroNombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFiltro_Nombre_Completo.getText().toUpperCase().trim(), 1));
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
	
		@SuppressWarnings("static-access")
		public void init_tabla(){
	        this.tabla.getTableHeader().setReorderingAllowed(false) ;
	        
	                int x=60;
	                int y=260;
	                int z=130;
	                this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	                
	                this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
	                this.tabla.getColumnModel().getColumn(0).setMinWidth(x);
	                this.tabla.getColumnModel().getColumn(1).setMaxWidth(y);
	                this.tabla.getColumnModel().getColumn(1).setMinWidth(y);
	                this.tabla.getColumnModel().getColumn(2).setMaxWidth(z);
	                this.tabla.getColumnModel().getColumn(2).setMinWidth(z);
	                this.tabla.getColumnModel().getColumn(3).setMaxWidth(z+30);
	                this.tabla.getColumnModel().getColumn(3).setMinWidth(z+30);
	        
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
	                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
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
	                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
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
	
	ActionListener usar_condicion_para_buscar_los_casos_de_llenado = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(e.getActionCommand().equals("1.-Trabajar corrido") || e.getActionCommand().equals("2.-Salir temprano") ||
					e.getActionCommand().equals("3.-Entrar tarde") || e.getActionCommand().equals("4.-No asistir con goce de sueldo") ||
					e.getActionCommand().equals("5.-No asistir sin goce de sueldo") || e.getActionCommand().equals("6.-Tiempo de comida ilimitado") ||
					e.getActionCommand().equals("Vacaciones") || e.getActionCommand().equals("Renuncia") || e.getActionCommand().equals("Gafete") || 
					e.getActionCommand().equals("Uniforme")){
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
			
			if(e.getActionCommand().equals("Prestamo") || e.getActionCommand().equals("Aumento de sueldo")){
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
	 		
			if(e.getActionCommand().equals("Cambio de sucursal") || e.getActionCommand().equals("Cambio de puesto") ||
					e.getActionCommand().equals("Cambio de departamento") || e.getActionCommand().equals("Cambio de turno") ||
					e.getActionCommand().equals("Cambio de descanso") || e.getActionCommand().equals("Cambio de doblada")){
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
		}	
	};
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if(txtFolio_Empleado.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Encargado_De_Solicitudes encargado= new Obj_Encargado_De_Solicitudes().buscar(lblEncargado.getText());
				Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio_Empleado.getText()));
				if(re.getFolio() != 0){
					if(re.getEstablecimiento()==encargado.getEstablecimiento()){
						
						txtFolio_Empleado.setText(re.getFolio()+"");
						txtFolio_Empleado.setEnabled(false);
						
						txtEmpleado.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
						
						ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
				         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
				         lblFoto.setIcon(iconoDefault);
						
				    	txtTelefono_Propio.setText(re.getTelefono_propio()+"");
						
				    	txtDescanso.setText(re.getDescanso()+"");
				    	txtDobla.setText(re.getDobla()+"");
				         
						switch(re.getStatus()){
							case 1:txtStatus.setText("VIGENTE");break;
							case 2:txtStatus.setText("VACACIONES");break;
							case 3:txtStatus.setText("INCAPACIDAD");break;
							case 4:txtStatus.setText("BAJA");break;
							case 5:txtStatus.setText("NO CONTRATABLE");break;
							case 6:txtStatus.setText("PROVICIONAL CHECADOR");break;
						}
						
						Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
						txtEstablecimiento.setText(comboNombreEsta.getNombre());
						
						Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
						txtPuesto.setText(comboNombrePues.getPuesto());
						
					}else{
						JOptionPane.showMessageDialog(null, "Solo puede registrar solicitud si el empleado\ncorresponde al establecimiento del encargado\nsolicitante.\n\nsi no se localiza el empleado favor de reportarlo\nal departamento de Desarrollo Humano\npara que corrijan su establecimiento","Aviso",JOptionPane.WARNING_MESSAGE);
						txtFolio_Empleado.setText("");
						txtFolio_Empleado.requestFocus();
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					txtFolio_Empleado.setText("");
					txtFolio_Empleado.requestFocus();
					return;
				}
			}
			txtFolio_Empleado.requestFocus();
		}
	};
	
//	public static void main(String args[]){
//		try{
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			new Cat_Seleccion_Jefe_De_Operaciones().setVisible(true);
//		}catch(Exception e){
//			System.err.println("Error :"+ e.getMessage());
//		}
//	}
}
