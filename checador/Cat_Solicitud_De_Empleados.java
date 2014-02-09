package checador;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import ObjetoChecador.Obj_Encargado_De_Solicitudes;
import ObjetoChecador.Obj_Solicitud_De_Empleados;
import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Departamento;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Solicitud_De_Empleados extends JFrame {
	
	Container cont = getContentPane();
//	panel de datos del empleado solicitante
	JLayeredPane filtro = new JLayeredPane();
//	panel de botones a opciones de solicitudes disponibles
	JLayeredPane botones = new JLayeredPane();
//	panel de alimentacion de permisoso
	JLayeredPane llenado_permisos = new JLayeredPane();
	
	final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
//filtro-------------------------------------------------------------------------------------------
	JLabel lblFolio = new JLabel("Folio: ");
	JTextField txtFolio_Empleado = new JTextField();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton(new ImageIcon("Iconos/filter_iconBlue&16.png"));
	JButton btnLimpiar = new JButton("Limpiar");
	
	JLabel lblEncargado = new JLabel("Encargado: ");
	JLabel lblNombre_Encargado = new JLabel();
	JLabel lblFoto = new 	JLabel();
	
	JLabel lblEmpleado = new JLabel("Empleado: ");
	JTextField txtEmpleado = new JTextField();
	JLabel lblEstablecimiento = new JLabel("Establecimiento: ");
	JTextField txtEstablecimiento = new JTextField();
	JLabel lblDepartamento = new JLabel("Departamento: ");
	JTextField txtDepartamento = new JTextField();
	JLabel lblPuesto = new JLabel("Puesto: ");
	JTextField txtPuesto = new JTextField();

	JLabel lblFuente_Sodas = new JLabel("Fuente de sodas: ");
	JTextField txtFuente_Sodas = new JTextField();
	JLabel lblTelefono_Propio = new JLabel("Tel. Propio: ");
	JTextField txtTelefono_Propio = new JTextField();
	JLabel lblDescanso = new JLabel("Descanso: ");
	JTextField txtDescanso = new JTextField();
	JLabel lblDobla = new JLabel("Dobla: ");
	JTextField txtDobla = new JTextField();
	
	JLabel lblStatus = new JLabel("Status: ");
	JTextField txtStatus = new JTextField();

//botones---------------------------------------------------------------------------------------------------------   
	JButton[] arrBtnPermisos = new JButton[6];//arreglo de botones Permisos
	JButton[] arrBtnSolicitudes = new JButton[6];//arreglo de botones Solicitudes
	JButton[] arrBtnCambios = new JButton[6];//arreglo de botones Cambios
	
//llenado-----------------------------------------------------------------------------------------------
	JLabel lblMovimiento = new JLabel();
	JDateChooser txtFechaSolicitada = new JDateChooser();
	JButton btnRegresar = new JButton("Regresar");
	JButton btnGuardar = new JButton("Guardar");
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbPuesto = new JComboBox(puesto);
	
	String Departamentos[] = new Obj_Departamento().Combo_Departamento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbDepartamento = new JComboBox(Departamentos);  
	
	String turno[] = {"SELECCIONE UN TURNO","MATUTINO","VASPERTINO","NOCTURNO","ESPECIAL"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbTurno = new JComboBox(turno);
	
	String descanso[] = {"SELECCIONE UN DIA","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbDescanso = new JComboBox(descanso);
	
	String dobla[] = {"SELECCIONE UN DIA","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO","DOMINGO"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbDobla = new JComboBox(dobla);
	
//	JLabel lblMotivo = new JLabel("Motivo: ");
	JTextArea txaMotivo = new JTextArea("");
	JScrollPane Observasiones = new JScrollPane(txaMotivo);
	
	JLabel lblPrestamo = new JLabel("Prestamo solicitado: ");
	JLabel lblAumento = new JLabel("Sueldo solicitado:");
	
	JLabel lblCantidad = new JLabel("Cantidad solicitada: ");
	JTextField txtCantidad = new JTextField();
	
	JRadioButton rbTemporal = new JRadioButton("Termporal",true);
	JRadioButton rbFijo = new JRadioButton("Fijo");
	ButtonGroup tipo_de_solicitud = new ButtonGroup();
	
//CUESTIONARIO DE CALIFICACION DETERMINADA DE ACUERDO A LAS POLITICAS DE LA EMPRESA PARA TRABAJADOR	
		JLabel lblMarcoEvaluacion = new JLabel();
		
		JLabel lblPolitica1 = new JLabel("CUESTIONARIO DE CALIFICACION");
		JLabel lblPolitica2 = new JLabel("DETERMINADA DE ACUERDO A LAS");
		JLabel lblPolitica3 = new JLabel("POLITICAS DE LA EMPRESA PARA");
		JLabel lblPolitica4 = new JLabel("TRABAJADOR.");
								
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
	public Cat_Solicitud_De_Empleados(){
		setTitle("Solicitud de empleados");
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		this.filtro.setBorder(BorderFactory.createTitledBorder(blackline,"Filtro"));
		this.botones.setBorder(BorderFactory.createTitledBorder(blackline,"Solicitud"));
		this.llenado_permisos.setBorder(BorderFactory.createTitledBorder(blackline,"Solicitud"));

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
		arraybuttons();
		
		llenadoBajo();
		CargarCajero();
		
//      pone el foco en el txtFolio al presionar la tecla scape
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
        
        getRootPane().getActionMap().put("foco", new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {
            		btnLimpiar.doClick();
            }
        });
        
//     abre filtro de empleados al presionar (f2)
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "filtro_empleados");
        
        getRootPane().getActionMap().put("filtro_empleados", new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {	btnFiltro.doClick();	} });
		
		ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setIcon(iconoDefault);

		txtStatus.setEditable(false);
		txtEmpleado.setEditable(false);
		txtEstablecimiento.setEditable(false);
		txtDepartamento.setEditable(false);
		txtTelefono_Propio.setEditable(false);
		txtDepartamento.setEditable(false);
		txtPuesto.setEditable(false);
		txtFuente_Sodas.setEditable(false);
		txtDescanso.setEditable(false);
		txtDobla.setEditable(false);
		
		txtFolio_Empleado.addKeyListener(validaNumerico);
		txtCantidad.addKeyListener(validaNumericoConPunto);
		
		btnLimpiar.addActionListener(opLimpiar);
		btnFiltro.addActionListener(opFiltro);
		btnBuscar.addActionListener(buscar);
		txtFolio_Empleado.addActionListener(buscar);
		btnGuardar.addActionListener(opGuardar);
		
		splitPane.setSize(800,520);
		splitPane.setTopComponent(filtro);
		splitPane.setBottomComponent(botones);
		
	    cont.add(splitPane, BorderLayout.CENTER);
	    cont.setVisible(true);

		splitPane.setDividerLocation(0.42);
		
		//ancho de espacio de divicion
		splitPane.setDividerSize(1);
		
		this.setSize(800,520);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			btnBuscar.setEnabled(true);
			btnFiltro.setEnabled(true);
			
			limpiar_pantallaCompleta();
		}
	};
	public void limpiar_pantallaCompleta(){
		 txtFolio_Empleado.setText("");
         txtFolio_Empleado.requestFocus();
         txtEmpleado.setText("");
		
		ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setIcon(iconoDefault);
		
    	txtTelefono_Propio.setText("");
    	txtDescanso.setText("");
    	txtDobla.setText("");
    	txtStatus.setText("");        					
		txtEstablecimiento.setText("");
		txtPuesto.setText("");
		txtDepartamento.setText("");
		txtFuente_Sodas.setText("");
		
		txtFolio_Empleado.setEnabled(true);
		txtFechaSolicitada.requestFocus();
		
		btnRegresar.doClick();
		
		for(int i=0; i<=arrBtnPermisos.length-1; i++){
			arrBtnPermisos[i].setEnabled(false);
			arrBtnSolicitudes[i].setEnabled(false);
			arrBtnCambios[i].setEnabled(false);
		}
	}
	
	public void pfiltor(){
	int y=60;
		
		filtro.add(lblEncargado).setBounds(60,20,100,20);
		filtro.add(lblNombre_Encargado).setBounds(145,20,350,20);
		
		filtro.add(lblFoto).setBounds(528,15,190,190);
		
		filtro.add(lblFolio).setBounds(60,y,80,20);
		filtro.add(txtFolio_Empleado).setBounds(160,y,70,20);
		filtro.add(btnBuscar).setBounds(235,y,20,20);
		filtro.add(btnFiltro).setBounds(255,y,25,20);
		filtro.add(btnLimpiar).setBounds(280,y,70,20);

		filtro.add(lblStatus).setBounds(385,y,70,20);
		filtro.add(txtStatus).setBounds(425,y,70,20);
		
		filtro.add(lblEmpleado).setBounds(60,y+=25,90,20);
		filtro.add(txtEmpleado).setBounds(160,y,335,20);
		
		filtro.add(lblEstablecimiento).setBounds(60,y+=25,150,20);
		filtro.add(txtEstablecimiento).setBounds(160,y,150,20);
		
		filtro.add(lblTelefono_Propio).setBounds(320,y,70,20);
		filtro.add(txtTelefono_Propio).setBounds(400,y,95,20);
		
		filtro.add(lblDepartamento).setBounds(60,y+=25,90,20);
		filtro.add(txtDepartamento).setBounds(160,y,220,20);
		
		filtro.add(lblPuesto).setBounds(60,y+=25,70,20);
		filtro.add(txtPuesto).setBounds(160,y,220,20);
		
		filtro.add(lblDobla).setBounds(395,y,85,20);
		filtro.add(txtDobla).setBounds(435,y,60,20);
		filtro.add(lblFuente_Sodas).setBounds(60,y+=25,120,20);
		filtro.add(txtFuente_Sodas).setBounds(160,y,55,20);
		filtro.add(lblDescanso).setBounds(290,y,70,20);
		filtro.add(txtDescanso).setBounds(350,y,145,20);
	}
	
	public void arraybuttons(){
		int x,y,ancho;
		x=65;
		y=30;
		ancho=210;
		
		int aux = 0;
		for(int i = 0; i<arrBtnPermisos.length; i++){
			
			if(aux == 3){	x=65; y+=30; aux=0;	}

			switch(i){
				 case 0:
					 arrBtnPermisos[i] = (new JButton("1.-Trabajar corrido")); break;
			 	 case 1:
			 		 arrBtnPermisos[i] = (new JButton("2.-Salir temprano")); break;
			 	 case 2:
			 		 arrBtnPermisos[i] = (new JButton("3.-Entrar tarde")); break;
			 	 case 3:
			 		 arrBtnPermisos[i] = (new JButton("4.-No asistir con goce de sueldo")); break;
			 	 case 4:
			 		arrBtnPermisos[i] = (new JButton("5.-No asistir sin goce de sueldo")); break;
			 	 case 5:
			 		arrBtnPermisos[i] = (new JButton("6.-Tiempo de comida ilimitado")); break;
			 	 default:
			 		arrBtnPermisos[i] = (new JButton("No aplica")); break;
			}
			
			if(aux==0){
				 botones.add(arrBtnPermisos[i]).setBounds(x, y, ancho, 20);
				 aux++;
			}else{
				 botones.add(arrBtnPermisos[i]).setBounds(x+=(ancho+10), y, ancho, 20);
				 aux++;
			}
			
			if( i==arrBtnPermisos.length-1)
			
			{	x=65; y+=50; aux=0;	}
			
			arrBtnPermisos[i].setEnabled(false);
			arrBtnPermisos[i].addActionListener(opArreglo);
		}
		
		for(int i = 0; i<arrBtnSolicitudes.length; i++){
			
			if(aux == 3){	x=65; y+=30; aux=0;	}
			
			switch(i){
				 case 0:
					 arrBtnSolicitudes[i] = (new JButton("Aumento de sueldo")); break;
			 	 case 1:
			 		arrBtnSolicitudes[i] = (new JButton("Prestamo")); break;
			 	 case 2:
			 		arrBtnSolicitudes[i] = (new JButton("Vacaciones")); break;
			 	 case 3:
			 		arrBtnSolicitudes[i] = (new JButton("Gafete")); break;
			 	 case 4:
			 		arrBtnSolicitudes[i] = (new JButton("Uniforme")); break;
			 	 case 5:
			 		arrBtnSolicitudes[i] = (new JButton("Renuncia")); break;
			 	 default:
			 		arrBtnSolicitudes[i] = (new JButton("No aplica")); break;
			}
			
			if(aux==0){
				 botones.add(arrBtnSolicitudes[i]).setBounds(x, y, ancho, 20);
				 aux++;
			}else{
				 botones.add(arrBtnSolicitudes[i]).setBounds(x+=(ancho+10), y, ancho, 20);
				 aux++;
			}
			
			if( i==arrBtnSolicitudes.length-1)
			
			{	x=65; y+=50; aux=0;	}
			
			arrBtnSolicitudes[i].setEnabled(false);
			arrBtnSolicitudes[i].addActionListener(opArreglo);
		}
		
		for(int i = 0; i<arrBtnCambios.length; i++){
			
			if(aux == 3){	x=65; y+=30; aux=0;	}
			
			switch(i){
				 case 0:
					 arrBtnCambios[i] = (new JButton("Cambio de sucursal")); break;
			 	 case 1:
			 		arrBtnCambios[i] = (new JButton("Cambio de puesto")); break;
			 	 case 2:
			 		arrBtnCambios[i] = (new JButton("Cambio de departamento")); break;
			 	 case 3:
			 		arrBtnCambios[i] = (new JButton("Cambio de turno")); break;
			 	 case 4:
			 		arrBtnCambios[i] = (new JButton("Cambio de descanso")); break;
			 	 case 5:
			 		arrBtnCambios[i] = (new JButton("Cambio de doblada")); break;
			 	 default:
			 		arrBtnCambios[i] = (new JButton("No aplica")); break;
			}
			
			if(aux==0){
				 botones.add(arrBtnCambios[i]).setBounds(x, y, ancho, 20);
				 aux++;
			}else{
				 botones.add(arrBtnCambios[i]).setBounds(x+=(ancho+10), y, ancho, 20);
				 aux++;
			}
			
			if( i==arrBtnCambios.length-1)
			
			{	x=65; y+=50; aux=0;	}
			
			arrBtnCambios[i].setEnabled(false);
			arrBtnCambios[i].addActionListener(opArreglo);
		}
	}
	
	public void llenadoBajo(){
		txaMotivo.setBorder(border);
		this.txtFechaSolicitada.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.lblMarcoEvaluacion.setBorder(BorderFactory.createTitledBorder(blackline,"Calificar Empleado"));
		
		lblPolitica1.setForeground(new java.awt.Color(169,93,53));
		lblPolitica2.setForeground(new java.awt.Color(169,93,53));
		lblPolitica3.setForeground(new java.awt.Color(169,93,53));
		lblPolitica4.setForeground(new java.awt.Color(169,93,53));
		
		lblMovimiento.setFont(new Font("arial", Font.BOLD, 20));	
		lblMovimiento.setForeground(new java.awt.Color(105,105,105));
		
		llenado_permisos.add(lblMovimiento).setBounds(20, 15, 310, 25);
		llenado_permisos.add(btnRegresar).setBounds(705, 15, 80, 20);
		llenado_permisos.add(btnGuardar).setBounds(705, 240, 80, 20);
			
		llenado_permisos.add(lblCantidad).setBounds(80,45,140,20);
		llenado_permisos.add(txtCantidad).setBounds(180,45,100,20);
		
		llenado_permisos.add(cmbEstablecimiento).setBounds(80,45,180,20);
		llenado_permisos.add(cmbPuesto).setBounds(80,45,320,20);
		llenado_permisos.add(cmbDepartamento).setBounds(80,45,220,20);
		llenado_permisos.add(cmbTurno).setBounds(80,45,140,20);
		llenado_permisos.add(cmbDescanso).setBounds(80,45,140,20);
		llenado_permisos.add(cmbDobla).setBounds(80,45,140,20);
		
		llenado_permisos.add(new JLabel("Fecha solicitada: ")).setBounds(515,45,100,20);
		llenado_permisos.add(txtFechaSolicitada).setBounds(600,45,100,20);
		llenado_permisos.add(Observasiones).setBounds(80, 70, 620, 100);
		
//		llenado_permisos.add(new JLabel("fjsfkjskljslkjdslfjdksdjfkldjskldfjklsdjfklsjklsjfdssfdsfsddsds")).setBounds(80, 185, 450, 20);
		llenado_permisos.add(rbTemporal).setBounds(80, 200, 80, 20);
		llenado_permisos.add(rbFijo).setBounds(170, 200, 60, 20);

//	cuestionario ------------------------------------------------------------------------------------	
		
		llenado_permisos.add(lblEtiquetaMB).setBounds(435, 177, 70, 20);
		llenado_permisos.add(lblEtiquetaB).setBounds(520, 177, 70, 20);
		llenado_permisos.add(lblEtiquetaR).setBounds(590, 177, 70, 20);
		
		int x=85;
		int y=158;
		
		llenado_permisos.add(lblMarcoEvaluacion).setBounds(x-5, 172, 620, 95);
		llenado_permisos.add(lblPolitica1).setBounds(x, y, 270, 90);
		llenado_permisos.add(lblPolitica2).setBounds(x, y+=17, 270, 90);
		llenado_permisos.add(lblPolitica3).setBounds(x, y+=17, 270, 90);
		llenado_permisos.add(lblPolitica4).setBounds(x, y+=17, 270, 90);
		x=275;
		y=192;
		llenado_permisos.add(lblEvaluacion1).setBounds(x, y, 170, 20);
		llenado_permisos.add(rbMBueno1).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno1).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular1).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset1).setBounds(x+=75, y, 20, 20);
		x=275;
		llenado_permisos.add(lblEvaluacion2).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno2).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno2).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular2).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset2).setBounds(x+=75, y, 20, 20);
		x=275;
		llenado_permisos.add(lblEvaluacion3).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno3).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno3).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular3).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset3).setBounds(x+=75, y, 20, 20);
		x=275;
		llenado_permisos.add(lblEvaluacion4).setBounds(x, y+=17, 170, 20);
		llenado_permisos.add(rbMBueno4).setBounds(x+=175, y, 20, 20);
		llenado_permisos.add(rbBueno4).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbRegular4).setBounds(x+=75, y, 20, 20);
		llenado_permisos.add(rbReset4).setBounds(x+=75, y, 20, 20);
//	------------------------------------------------------------------------------------------------------
		
		txaMotivo.setLineWrap(true);
		txaMotivo.setDocument(new JTextFieldLimit(400));
		
		txaMotivo.setLineWrap(true);
		txaMotivo.setDocument(new JTextFieldLimit(400));
		btnRegresar.addActionListener(opRegresar);
	}
	
	ActionListener opArreglo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			lblMovimiento.setText(e.getActionCommand());
			txaMotivo.setText("Motivo:");
			
			if(e.getActionCommand().equals("1.-Trabajar corrido") || e.getActionCommand().equals("2.-Salir temprano") ||
					e.getActionCommand().equals("3.-Entrar tarde") || e.getActionCommand().equals("4.-No asistir con goce de sueldo") ||
					e.getActionCommand().equals("5.-No asistir sin goce de sueldo") || e.getActionCommand().equals("6.-Tiempo de comida ilimitado") ||
					e.getActionCommand().equals("Vacaciones") || e.getActionCommand().equals("Renuncia") || e.getActionCommand().equals("Gafete") || 
					e.getActionCommand().equals("Uniforme")){
				
				lblCantidad.setVisible(false);
				txtCantidad.setVisible(false);
				
				cmbEstablecimiento.setVisible(false);
				cmbPuesto.setVisible(false);
				cmbDepartamento.setVisible(false);
				cmbTurno.setVisible(false);
				cmbDescanso.setVisible(false);
				cmbDobla.setVisible(false);
//tipo de cambio				
				rbTemporal.setVisible(false);
				rbFijo.setVisible(false);
				
//cuestionario
				lblMarcoEvaluacion.setVisible(false);
				lblPolitica1.setVisible(false);
				lblPolitica2.setVisible(false);
				lblPolitica3.setVisible(false);
				lblPolitica4.setVisible(false);
				
				lblEtiquetaMB.setVisible(false);
				lblEtiquetaB.setVisible(false);
				lblEtiquetaR.setVisible(false);
				
				lblEvaluacion1.setVisible(false);
				rbMBueno1.setVisible(false);
				rbBueno1.setVisible(false);
				rbRegular1.setVisible(false);
				rbReset1.setVisible(false);

				lblEvaluacion2.setVisible(false);
				rbMBueno2.setVisible(false);
				rbBueno2.setVisible(false);
				rbRegular2.setVisible(false);
				rbReset2.setVisible(false);

				lblEvaluacion3.setVisible(false);
				rbMBueno3.setVisible(false);
				rbBueno3.setVisible(false);
				rbRegular3.setVisible(false);
				rbReset3.setVisible(false);

				lblEvaluacion4.setVisible(false);
				rbMBueno4.setVisible(false);
				rbBueno4.setVisible(false);
				rbRegular4.setVisible(false);
				rbReset4.setVisible(false);
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
			
			if(e.getActionCommand().equals("Prestamo") || e.getActionCommand().equals("Aumento de sueldo")){
				
				lblCantidad.setVisible(true);
				txtCantidad.setVisible(true);
//cambios				
				cmbEstablecimiento.setVisible(false);
				cmbPuesto.setVisible(false);
				cmbDepartamento.setVisible(false);
				cmbTurno.setVisible(false);
				cmbDescanso.setVisible(false);
				cmbDobla.setVisible(false);
				
//tipo de cambio				
				rbTemporal.setVisible(false);
				rbFijo.setVisible(false);
				
//cuestionario
				lblMarcoEvaluacion.setVisible(true);
				lblPolitica1.setVisible(true);
				lblPolitica2.setVisible(true);
				lblPolitica3.setVisible(true);
				lblPolitica4.setVisible(true);
				
				lblEtiquetaMB.setVisible(true);
				lblEtiquetaB.setVisible(true);
				lblEtiquetaR.setVisible(true);
				
				lblEvaluacion1.setVisible(true);
				rbMBueno1.setVisible(true);
				rbBueno1.setVisible(true);
				rbRegular1.setVisible(true);
				rbReset1.setVisible(true);

				lblEvaluacion2.setVisible(true);
				rbMBueno2.setVisible(true);
				rbBueno2.setVisible(true);
				rbRegular2.setVisible(true);
				rbReset2.setVisible(true);

				lblEvaluacion3.setVisible(true);
				rbMBueno3.setVisible(true);
				rbBueno3.setVisible(true);
				rbRegular3.setVisible(true);
				rbReset3.setVisible(true);

				lblEvaluacion4.setVisible(true);
				rbMBueno4.setVisible(true);
				rbBueno4.setVisible(true);
				rbRegular4.setVisible(true);
				rbReset4.setVisible(true);
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
	 		
			if(e.getActionCommand().equals("Cambio de sucursal") || e.getActionCommand().equals("Cambio de puesto") ||
					e.getActionCommand().equals("Cambio de departamento") || e.getActionCommand().equals("Cambio de turno") ||
					e.getActionCommand().equals("Cambio de descanso") || e.getActionCommand().equals("Cambio de doblada")){
				
				lblCantidad.setVisible(false);
				txtCantidad.setVisible(false);
//cambios				
				switch(e.getActionCommand()){
					case "Cambio de sucursal":	cmbEstablecimiento.setVisible(true);
												cmbPuesto.setVisible(false);
												cmbDepartamento.setVisible(false);
												cmbTurno.setVisible(false);
												cmbDescanso.setVisible(false);
												cmbDobla.setVisible(false);
					break;
					case "Cambio de puesto":	cmbEstablecimiento.setVisible(false);
												cmbPuesto.setVisible(true);
												cmbDepartamento.setVisible(false);
												cmbTurno.setVisible(false);
												cmbDescanso.setVisible(false);
												cmbDobla.setVisible(false);
					break;
					case "Cambio de departamento":	cmbEstablecimiento.setVisible(false);
													cmbPuesto.setVisible(false);
													cmbDepartamento.setVisible(true);
													cmbTurno.setVisible(false);
													cmbDescanso.setVisible(false);
													cmbDobla.setVisible(false);
					break;
					case "Cambio de turno":	cmbEstablecimiento.setVisible(false);
											cmbPuesto.setVisible(false);
											cmbDepartamento.setVisible(false);
											cmbTurno.setVisible(true);
											cmbDescanso.setVisible(false);
											cmbDobla.setVisible(false);
					break;
					case "Cambio de descanso":	cmbEstablecimiento.setVisible(false);
												cmbPuesto.setVisible(false);
												cmbDepartamento.setVisible(false);
												cmbTurno.setVisible(false);
												cmbDescanso.setVisible(true);
												cmbDobla.setVisible(false);
					break;
					case "Cambio de doblada":	cmbEstablecimiento.setVisible(false);
												cmbPuesto.setVisible(false);
												cmbDepartamento.setVisible(false);
												cmbTurno.setVisible(false);
												cmbDescanso.setVisible(false);
												cmbDobla.setVisible(true);
					break;
				}
//tipo de cambio				
				rbTemporal.setVisible(true);
				rbFijo.setVisible(true);
				
//cuestionario
				lblMarcoEvaluacion.setVisible(false);
				lblPolitica1.setVisible(false);
				lblPolitica2.setVisible(false);
				lblPolitica3.setVisible(false);
				lblPolitica4.setVisible(false);
				
				lblEtiquetaMB.setVisible(false);
				lblEtiquetaB.setVisible(false);
				lblEtiquetaR.setVisible(false);
				
				lblEvaluacion1.setVisible(false);
				rbMBueno1.setVisible(false);
				rbBueno1.setVisible(false);
				rbRegular1.setVisible(false);
				rbReset1.setVisible(false);

				lblEvaluacion2.setVisible(false);
				rbMBueno2.setVisible(false);
				rbBueno2.setVisible(false);
				rbRegular2.setVisible(false);
				rbReset2.setVisible(false);

				lblEvaluacion3.setVisible(false);
				rbMBueno3.setVisible(false);
				rbBueno3.setVisible(false);
				rbRegular3.setVisible(false);
				rbReset3.setVisible(false);

				lblEvaluacion4.setVisible(false);
				rbMBueno4.setVisible(false);
				rbBueno4.setVisible(false);
				rbRegular4.setVisible(false);
				rbReset4.setVisible(false);
				
				splitPane.setBottomComponent(llenado_permisos);
				splitPane.setDividerLocation(0.45);
			}
		}	
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(lblMovimiento.getText().equals("1.-Trabajar corrido") || lblMovimiento.getText().equals("2.-Salir temprano") ||
					lblMovimiento.getText().equals("3.-Entrar tarde") || lblMovimiento.getText().equals("4.-No asistir con goce de sueldo") ||
					lblMovimiento.getText().equals("5.-No asistir sin goce de sueldo") || lblMovimiento.getText().equals("6.-Tiempo de comida ilimitado") ||
					lblMovimiento.getText().equals("Vacaciones") || lblMovimiento.getText().equals("Renuncia") || lblMovimiento.getText().equals("Gafete") || 
					lblMovimiento.getText().equals("Uniforme")){

				if(ValidaCampos(1).equals("")){
					guardar();
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+ValidaCampos(1),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
			}
			
			if(lblMovimiento.getText().equals("Prestamo") || lblMovimiento.getText().equals("Aumento de sueldo")){
				
				if(ValidaCampos(2).equals("")){
					guardar();
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+ValidaCampos(2),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
			}
	 		
			if(lblMovimiento.getText().equals("Cambio de sucursal") || lblMovimiento.getText().equals("Cambio de puesto") ||
					lblMovimiento.getText().equals("Cambio de departamento") || lblMovimiento.getText().equals("Cambio de turno") ||
					lblMovimiento.getText().equals("Cambio de descanso") || lblMovimiento.getText().equals("Cambio de doblada")){
				
				if(ValidaCampos(3).equals("")){
					guardar();
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+ValidaCampos(3),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
			}
		}	
	};
	
	public void guardar(){
		Obj_Solicitud_De_Empleados solicitud = new Obj_Solicitud_De_Empleados();
		
		solicitud.setFolio_empleado(Integer.valueOf(txtFolio_Empleado.getText()));
		solicitud.setUsuario(lblNombre_Encargado.getText());
		
		switch(lblMovimiento.getText()){
			case "1.-Trabajar corrido":
				solicitud.setFolio_permiso(1);
			break;
			case "2.-Salir temprano":
				solicitud.setFolio_permiso(2);
			break;
			case "3.-Entrar tarde":
				solicitud.setFolio_permiso(3);
			break;
			case "4.-No asistir con goce de sueldo":
				solicitud.setFolio_permiso(4);
			break;
			case "5.-No asistir sin goce de sueldo":
				solicitud.setFolio_permiso(5);
			break;
			case "6.-Tiempo de comida ilimitado":
				solicitud.setFolio_permiso(6);
			break;
			case "Aumento de sueldo":
				solicitud.setFolio_solicitud(1);
			break;
			case "Prestamo":
				solicitud.setFolio_solicitud(2);
			break;
			case "Vacaciones":
				solicitud.setFolio_solicitud(3);
			break;
			
			case "Gafete":
				solicitud.setFolio_solicitud(4);
			break;
			case "Uniforme":
				solicitud.setFolio_solicitud(5);
			break;
			case "Renuncia":
				solicitud.setFolio_solicitud(6);
			break;
			case "Cambio de sucursal":
				solicitud.setFolio_cambio(1);
				solicitud.setCambio_a(cmbEstablecimiento.getSelectedItem()+"");
			break;
			case "Cambio de puesto":
				solicitud.setFolio_cambio(2);
				solicitud.setCambio_a(cmbPuesto.getSelectedItem()+"");
			break;
			case "Cambio de departamento":
				solicitud.setFolio_cambio(3);
				solicitud.setCambio_a(cmbDepartamento.getSelectedItem()+"");
			break;
			case "Cambio de turno":
				solicitud.setFolio_cambio(4);
				solicitud.setCambio_a(cmbTurno.getSelectedItem()+"");
			break;
			case "Cambio de descanso":
				solicitud.setFolio_cambio(5);
				solicitud.setCambio_a(cmbDescanso.getSelectedItem()+"");
			break;
			case "Cambio de doblada":
				solicitud.setFolio_cambio(6);
				solicitud.setCambio_a(cmbDobla.getSelectedItem()+"");
			break;
		}
		
		if(txtFechaSolicitada.getDate()==null){
			solicitud.setFecha_solicitada("01/01/1900");
		}else{
			solicitud.setFecha_solicitada(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaSolicitada.getDate()));
		}
		
		if(rbTemporal.isSelected()){
			solicitud.setTemp_fijo(0);
		}else{
			solicitud.setTemp_fijo(1);
		}
		
		if(txtCantidad.getText().equals("")){
			solicitud.setCantidad_solicitada(0);
		}else{
			solicitud.setCantidad_solicitada(Float.valueOf(txtCantidad.getText()));
		}
			
		
		
		if(rbMBueno1.isSelected()){solicitud.setPuntualidad_y_asistencia(3);}
		if(rbBueno1.isSelected()){solicitud.setPuntualidad_y_asistencia(2);}
		if(rbRegular1.isSelected()){solicitud.setPuntualidad_y_asistencia(1);}
		
		if(rbMBueno2.isSelected()){solicitud.setCumplimiento_de_tareas(3);}
		if(rbBueno2.isSelected()){solicitud.setCumplimiento_de_tareas(2);}
		if(rbRegular2.isSelected()){solicitud.setCumplimiento_de_tareas(1);}
		
		if(rbMBueno3.isSelected()){solicitud.setDiciplina(3);}
		if(rbBueno3.isSelected()){solicitud.setDiciplina(2);}
		if(rbRegular3.isSelected()){solicitud.setDiciplina(1);}
		
		if(rbMBueno4.isSelected()){solicitud.setRespeto_y_trato_general(3);}
		if(rbBueno4.isSelected()){solicitud.setRespeto_y_trato_general(2);}
		if(rbRegular4.isSelected()){solicitud.setRespeto_y_trato_general(1);}
		
		solicitud.setMotivo(txaMotivo.getText());
		
		if(solicitud.guardar()){
			btnLimpiar.doClick();
				JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
		}else{
			JOptionPane.showMessageDialog(null,"El Registro no se a guardo!","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Filtro_Solicitud_Permisos_Checador().setVisible(true);
		}
	};
	
	ActionListener opRegresar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			splitPane.setTopComponent(filtro);
			splitPane.setBottomComponent(botones);
			splitPane.setDividerLocation(0.45);
			
			limpiarSolicitud();
		}
	};
	
	public void limpiarSolicitud(){
		
		lblMovimiento.setText("");
		
		cmbDepartamento.setSelectedIndex(0);
		cmbPuesto.setSelectedIndex(0);
		cmbDepartamento.setSelectedIndex(0);
		cmbTurno.setSelectedIndex(0);
		cmbDescanso.setSelectedIndex(0);
		cmbDobla.setSelectedIndex(0);
		
		txaMotivo.setText("");
		txtFechaSolicitada.setDate(null);
		
		txtCantidad.setText("");
		
		rbTemporal.setSelected(true);
		
		rbReset1.setSelected(true);
		rbReset2.setSelected(true);
		rbReset3.setSelected(true);
		rbReset4.setSelected(true);
	}
	
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
						
						btnBuscar.setEnabled(false);
						btnFiltro.setEnabled(false);
						
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
						
						Obj_Departamento depart = new Obj_Departamento().buscar(re.getDepartameto());
						txtDepartamento.setText(depart.getDepartamento());
						
						if(re.isFuente_sodas() == true){txtFuente_Sodas.setText("ACTIVA");}
						else{txtFuente_Sodas.setText("INACTIVA");}
						
						for(int i=0; i<=arrBtnPermisos.length-1; i++){
							arrBtnPermisos[i].setEnabled(true);
							arrBtnSolicitudes[i].setEnabled(true);
							arrBtnCambios[i].setEnabled(true);
						}
					
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
	
	public void CargarCajero(){
		  File archivo = null;
 	      FileReader fr = null;
 	      BufferedReader br = null;
		 try {
 	         archivo = new File ("Config/users");
 	         fr = new FileReader (archivo);
 	         br = new BufferedReader(fr);
 	         String linea;
 	         while((linea=br.readLine())!=null)
 	        	lblNombre_Encargado.setText(linea);
 	      }catch(Exception e){
 	         e.printStackTrace();
 	      }finally{
 	         try{                   
 	            if( null != fr ){  
 	               fr.close();    
 	            }                 
 	         }catch (Exception e2){
 	            e2.printStackTrace();
 	         }
 	      }
	}
	
	public String ValidaCampos(int validador){
		String error ="";
		String fechaNull= txtFechaSolicitada.getDate()+"";
		
		switch(validador){
			case 1:	if(lblMovimiento.getText().equals("1.-Trabajar corrido") || lblMovimiento.getText().equals("2.-Salir temprano")
					|| lblMovimiento.getText().equals("3.-Entrar tarde") || lblMovimiento.getText().equals("4.-No asistir con goce de sueldo")
					|| lblMovimiento.getText().equals("5.-No asistir sin goce de sueldo") || lblMovimiento.getText().equals("6.-Tiempo de comida ilimitado")
					|| lblMovimiento.getText().equals("Vacaciones") || lblMovimiento.getText().equals("Renuncia")){if(fechaNull.equals("null")){error+= "Fecha solicitada\n";}}
				
					if(txaMotivo.getText().equals("") || txaMotivo.getText().equals("Motivo:")){error+= "Motivo\n";}
			break;
			case 2:	if(txtCantidad.getText().equals("")||txtCantidad.getText().equals(".")){error+= "Cantidad solicitada\n";}
			
					if(lblMovimiento.getText().equals("Prestamo")){
						if(fechaNull.equals("null")){error+= "Fecha solicitada\n";}
					}
			
					if(txaMotivo.getText().equals("") || txaMotivo.getText().equals("Motivo:")){error+= "Motivo\n";}
					if(rbReset1.isSelected() || rbReset2.isSelected() ||
					   rbReset3.isSelected() || rbReset4.isSelected()){error+= "Faltan preguntas de contestar\n";}
			break;
			case 3:	if(cmbEstablecimiento.getSelectedIndex()+cmbPuesto.getSelectedIndex()+cmbDepartamento.getSelectedIndex()+
					cmbTurno.getSelectedIndex()+cmbDepartamento.getSelectedIndex()+cmbDescanso.getSelectedIndex()==0){error+= "Seleccione un lugar\n";}
					if(txaMotivo.getText().equals("") || txaMotivo.getText().equals("Motivo:")){error+= "Motivo\n";}
			break;
			}
		return error;
	}
	
 	KeyListener validaNumerico = new KeyListener() {
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
		}
		public void keyReleased(KeyEvent e) {	
		}
		public void keyPressed(KeyEvent e) {}
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.')){
		    	e.consume();
		    	}
		    if (caracter==KeyEvent.VK_PERIOD){
		    	String texto = txtCantidad.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			}
		}
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
	};
	
	//Filtro Empleado
	public class Filtro_Solicitud_Permisos_Checador extends JDialog{
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		DefaultTableModel model = new DefaultTableModel(0,3){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JLabel lblBuscar = new JLabel("BUSCAR : ");
		JTextField txtBuscar = new JTextField();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Filtro_Solicitud_Permisos_Checador()	{
			this.setModal(true);
			this.setTitle("Filtro Empleados");
			txtBuscar.setDocument(new JTextFieldLimit(130));
			
			txtBuscar.addKeyListener(new KeyAdapter() { 
				public void keyReleased(final KeyEvent e) { 
	                filtro(); 
	            } 
	        });
			
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			campo.add(getPanelTabla()).setBounds(10,70,365,450);
			
			agregar(tabla);
			
			campo.add(lblBuscar).setBounds(30,30,70,20);
			campo.add(txtBuscar).setBounds(95,30,215,20);
			cont.add(campo);

//		     regresa el foco al filtro de reduccion de busqueda ()
	        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "tap");
	        
	        getRootPane().getActionMap().put("tap", new AbstractAction(){
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	            	txtBuscar.setText("");
	            	txtBuscar.requestFocus();
	            }
	        });
	        
			tabla.addKeyListener(seleccion_con_teclado);
			
			this.setSize(390,570);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		
		        		int fila = tabla.getSelectedRow();
		    			String folio =  tabla.getValueAt(fila, 0).toString().trim();
		    			dispose();
		    			
		    			txtFolio_Empleado.setText(folio);
		    			btnBuscar.doClick();
		    			txtFolio_Empleado.requestFocus();
		        	}
		        }
	        });
	    }
		
		KeyListener seleccion_con_teclado = new KeyListener() {
			@SuppressWarnings("static-access")
			public void keyTyped(KeyEvent e){
		    	
				char caracter = e.getKeyChar();				
				if((caracter == e.VK_ENTER) ){
	            	int fila = tabla.getSelectedRow()-1;
	    			String folio =  tabla.getValueAt(fila, 0).toString().trim();
	    			dispose();
	    			
	    			txtFolio_Empleado.setText(folio);
	    			btnBuscar.doClick();
	    			txtFolio_Empleado.requestFocus();
				 }
			}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent arg0) {}	
		};
		
		@SuppressWarnings("unchecked")
		public void filtro() { 
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
		}  
		private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(70);
			tabla.getColumnModel().getColumn(0).setMinWidth(70);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Empleado");
			tabla.getColumnModel().getColumn(1).setMaxWidth(185);
			tabla.getColumnModel().getColumn(1).setMinWidth(185);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
			tabla.getColumnModel().getColumn(2).setMaxWidth(100);
			tabla.getColumnModel().getColumn(2).setMinWidth(100);
			
			TableCellRenderer render = new TableCellRenderer() 
			{ 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					Component componente = null;
					componente= new JLabel(value == null? "": value.toString());
			
					if(row%2==0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));
					} 
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
				return componente; 
				} 
			}; 
							tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
							tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
							tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
			Statement s;
			ResultSet rs;
			try {
				s = con.conexion().createStatement();
				rs = s.executeQuery("exec sp_select_filtro_empleados_solicitudes '"+lblNombre_Encargado.getText()+"';");
				
				while (rs.next())
				{ 
				   String [] fila = new String[3];
				   fila[0] = rs.getString(1).trim();
				   fila[1] = rs.getString(2).trim();
				   fila[2] = rs.getString(3).trim();
				   
				   model.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
		    return scrol; 
		}
    	
		KeyListener validaCantidad = new KeyListener() {
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();				
				if(((caracter < '0') ||	
				    	(caracter > '9')) && 
				    	(caracter != '.' )){
				    	e.consume();
				    	}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent arg0) {}	
		};
		
		KeyListener validaNumericoConPunto = new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
			    if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.')){
			    	e.consume();
			    	}
			}
			public void keyPressed(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
		};
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Solicitud_De_Empleados().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
