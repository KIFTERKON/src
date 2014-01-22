package catalogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.format.YUVFormat;
import javax.media.util.BufferToImage;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import checador.Cat_Horario;

import com.toedter.calendar.JDateChooser;

import objetos.JTextFieldLimit;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Departamento;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Horario_Empleado;
import objetos.Obj_Horario_Empleado2;
import objetos.Obj_Horario_Empleado3;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Tipo_Banco;
import reporte.Reporte_de_Empleados_No_Contratables;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Empleado extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JLabel lblDatosPersonales = new JLabel();
	JLabel lblSexo = new JLabel();
	JLabel lblLaboral = new JLabel();
	JLabel lblPercepciones = new JLabel();
	
	JLabel lblFolioHorario1 = new JLabel("");
	JLabel lblFolioHorario2 = new JLabel("");
	JLabel lblFolioHorario3 = new JLabel("");
	
	JPasswordField txtChecador = new JPasswordField();
	
	JTextField txtFolioEmpleado = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtApPaterno = new JTextField();
	JTextField txtApMaterno = new JTextField();
	JTextField txtFechaActualizacion = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
	JTextField txtPensionAli = new JTextField();
	
	JTextField txtHorario = new JTextField();
	JTextField txtHorario2 = new JTextField();
	JTextField txtHorario3 = new JTextField();
	
	JTextField txtImss = new JTextField();
	JTextField txtTelefono_Familiar = new JTextField();
	JTextField txtTelefono_Propio = new JTextField(); 
	JTextField txtTelefono_Cuadrante = new JTextField();  
	JTextField txtPoblacion = new JTextField();
	JTextField txtRFC = new JTextField();
	JTextField txtCurp = new JTextField();  
	JTextField txtBaja = new JTextField();
	JTextField txtColonia = new JTextField();  
	JTextField txtCalle = new JTextField();
	JTextField txtDescanso = new JTextField();
	JTextField txtDobla = new JTextField();
	JTextField txtFechaUltimasVacaciones = new JTextField();
	JTextField txtFechaIncapacidad = new JTextField();
	
	String Departamentos[] = new Obj_Departamento().Combo_Departamento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbDepartamento = new JComboBox(Departamentos);  
	
	JTextField txtNumeroInfonavit = new JTextField();
	
	JTextField txtSalarioDiario = new JTextField();
	JTextField txtSalarioDiarioIntegrado = new JTextField();  
	JTextField txtFormaDePago = new JTextField();
	
	JToggleButton btnTrueFoto = new JToggleButton("Para actualizar la foto Presiona aquí !!!");
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings("rawtypes")
	JComboBox cmbPuesto = new JComboBox(puesto);
	
//	 String[] tooltips = { "Javanese ", "Japanese ", "Latin " };
//	 String[] tooltips = new Obj_Turno().Combo_Turno();
//	String turno[] = new Obj_Turno().Combo_Turno();
//	@SuppressWarnings("rawtypes")
//	JComboBox cmbTurno = new JComboBox(turno);
	
	String sueldo[] = new Obj_Sueldo().Combo_Sueldo();
	@SuppressWarnings("rawtypes")
	JComboBox cmbSueldo = new JComboBox(sueldo);
	
	String bono[] = new Obj_Bono_Complemento_Sueldo().Combo_Bono();
	@SuppressWarnings("rawtypes")
	JComboBox cmbBono = new JComboBox(bono);
	
	String rango_prestamo[] = new Obj_Rango_Prestamos().Combo_Prestamos();
	@SuppressWarnings("rawtypes")
	JComboBox cmbPrestamos = new JComboBox(rango_prestamo);
	
	JTextField txtInfonavit = new JTextField();
	JTextField txtTarjetaNomina = new JTextField();
	
	String TipoBanco[] = new Obj_Tipo_Banco().Combo_Tipo_Banco();
	@SuppressWarnings("rawtypes")
	JComboBox cmbTipoBancos = new JComboBox(TipoBanco);
	
	JCheckBox chbFuente_Sodas = new JCheckBox("Fnt de Sodas");
	JCheckBox chbGafete = new JCheckBox("Gafete");
	
	String status[] = {"Vigente","Vacaciones","Incapacidad","Baja","No Contratable","Provicional Checador"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbStatus = new JComboBox(status);
	
	String activo_inactivo[] = {"Activo (IMSS)","Inactivo (IMSS)"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbActivo_Inactivo = new JComboBox(activo_inactivo);
	
	JCheckBox chb_cuadrante_parcial = new JCheckBox("Permite Cuadrante Parcial",false);
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton(new ImageIcon("Iconos/users_icon&16.png"));
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnVerificar = new JButton("Verificar Nombre");
	JButton btnHorario = new JButton(".");
	JButton btnHorario2 = new JButton(".");
	JButton btnHorario3 = new JButton(".");
	JButton btnHorarioNew = new JButton("new");
//	JButton btnQuitarHorario3 = new JButton("x");
	JButton btnFechaUltimasVacaciones = new JButton();
	JButton btnFechaIncapacidad = new JButton();

	JButton btnFoto = new JButton();
	JButton btnStatus = new JButton();
	JButton btnExaminar = new JButton("Examinar");
	JButton btnCamara = new JButton(new ImageIcon("Iconos/camara_icon&16.png"));
	JButton btnCumpleaños_del_Mes = new JButton("Cumpleaños del Mes");
	JButton btnIncontratables = new JButton("No contratables");
	
	JButton btnBaja = new JButton("No contratables");

	JTextArea txaObservaciones = new JTextArea(5,5);
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	JDateChooser txtCalendario = new JDateChooser();
	JDateChooser txtIngreso = new JDateChooser();
	JDateChooser txtIngresoImss = new JDateChooser();
	JDateChooser txtVencimientoLicencia = new JDateChooser();
	
	 private ButtonGroup bgSexo = new ButtonGroup();
	 private JRadioButton rbMasculino = new JRadioButton("Masculino",true);
	 private JRadioButton rbFemenino = new JRadioButton("Femenino",false);
	
	 private ButtonGroup bgHorarios = new ButtonGroup();
	 private JRadioButton rbHorario = new JRadioButton("",true);
	 private JRadioButton rbHorario2 = new JRadioButton("",false);
	 private JRadioButton rbHorario3 = new JRadioButton("",false);
	 
	 String[] horarioRotativo = { "Sin Horario rotativo ", "2 Horarios", "3 Horarios" };
	 @SuppressWarnings("rawtypes")
	private JComboBox cmbHorarioRotativo = new JComboBox(horarioRotativo);
	
	//declaracion de Bordes
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	TitledBorder title4;
	
	int seleccion_de_asignacion_de_Horario1Horario2Horario3;
	
	public Cat_Empleado() {
		getContenedor();
	}
	
	public void getContenedor(){
//		pendientes en funcionalidad----------------------------------------------------
		txtFechaUltimasVacaciones.setEnabled(false);
		btnFechaUltimasVacaciones.setEnabled(false);
		txtFechaIncapacidad.setEnabled(false);
		btnFechaIncapacidad.setEnabled(false);
		btnBaja.setEnabled(false);
//		------------------------------------------------------------------------------
//		efectos de bordes
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
//		etched = BorderFactory.createEtchedBorder();
//		raisedbevel = BorderFactory.createRaisedBevelBorder();
//		loweredbevel = BorderFactory.createLoweredBevelBorder();
//		empty = BorderFactory.createEmptyBorder();
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/user_icon&16.png"));
		this.setTitle("Alta de Empleados");
		
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Alta de Empleados"));
		
//		asignacion de bordes
		this.lblDatosPersonales.setBorder(BorderFactory.createTitledBorder(blackline,"Datos Personales"));
		this.btnFoto.setBorder(blackline);
		this.lblSexo.setBorder(BorderFactory.createTitledBorder(blackline, "Sexo"));
		this.lblLaboral.setBorder(BorderFactory.createTitledBorder(blackline, "Laboral"));
		this.lblPercepciones.setBorder(BorderFactory.createTitledBorder(blackline,"Percepciones y Deducciones"));
		
		this.btnVerificar.setToolTipText("Verificar Nombre");
		
		this.btnHorarioNew.setToolTipText("Generar Horario");
		this.btnHorario.setToolTipText("Asignar Horario");
		this.btnHorario2.setToolTipText("Asignar Segundo Horario");
		this.btnHorario3.setToolTipText("Asignar Tercer Horario");
		
		this.txtFechaUltimasVacaciones.setToolTipText("Fecha de vacaciones");
		this.txtFechaIncapacidad.setToolTipText("Fecha de incapacidad");
		
		this.btnFechaUltimasVacaciones.setToolTipText("Alimentacion de vacaciones");
		this.btnFechaIncapacidad.setToolTipText("Alimentacion de incapacidad");
			
		this.txtHorario.setToolTipText("Horario");
		this.txtHorario2.setToolTipText("Segundo Horario");
		this.txtHorario3.setToolTipText("Tercer Horario");
		
		this.txaObservaciones.setBorder(BorderFactory.createTitledBorder(blackline));
		
//		agregando radio_button a grupo
		this.bgSexo.add(rbMasculino);
		this.bgSexo.add(rbFemenino);
		
		this.bgHorarios.add(rbHorario);
		this.bgHorarios.add(rbHorario2);
		this.bgHorarios.add(rbHorario3);

		int x = 20, y=35, ancho=140;
		
		this.txtCalendario.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtIngreso.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtIngresoImss.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtVencimientoLicencia.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		txtHorario.setFont(new Font("ARIAL", Font.ITALIC, 9));
		txtHorario2.setFont(new Font("ARIAL", Font.ITALIC, 9));
		txtHorario3.setFont(new Font("ARIAL", Font.ITALIC, 9));
		
//Datos personales ----------------------------------------------------------------------------------------------------------------------------		
		panel.add(lblDatosPersonales).setBounds(10,y-15,ancho*7-30,215);
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolioEmpleado).setBounds(x+ancho-40,y,ancho-15,20);
		
		panel.add(btnBuscar).setBounds(x+ancho+ancho-12,y,32,20);
		panel.add(btnFiltro).setBounds(x+ancho+ancho+20,y,32,20);
		panel.add(btnEditar).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		btnEditar.setVisible(false);
		
		panel.add(btnNuevo).setBounds(x+ancho+ancho+51,y,ancho-49,20);
	
		panel.add(btnFoto).setBounds(x*2+ancho*5,y-5,ancho+55,160);
		
		panel.add(btnCumpleaños_del_Mes).setBounds(x+ancho*4+120,8,130,18);
		panel.add(btnIncontratables).setBounds(x*2+ancho*3+ancho+230,8,130,18);
		
		panel.add(btnTrueFoto).setBounds(x*2+ancho*5-10, y+155,220,20);
		
		panel.add(btnExaminar).setBounds(x*2+ancho*5-10, y+175,80,20);		
		panel.add(new JLabel("320 x 240")).setBounds(x*2+ancho*5+76, y+175,60,20);
		panel.add(btnCamara).setBounds(x*2+ancho*5+130, y+175,80,20);
		
		panel.add(new JLabel("Clave Checador")).setBounds(x+450,y,ancho,20);
		panel.add(txtChecador).setBounds(x+(ancho*3)+110,y,ancho-15,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho-40,y,ancho-15,20);
			panel.add(new JLabel("Ap. Paterno:")).setBounds(x+240,y,ancho,20);
			panel.add(txtApPaterno).setBounds(x+(ancho*2)+30,y,ancho-15,20);
				panel.add(new JLabel("Ap. Materno:")).setBounds(x+450,y,ancho,20);
				panel.add(txtApMaterno).setBounds(x+(ancho*3)+110,y,ancho-15,20);
		
		panel.add(btnVerificar).setBounds(x+(ancho*4)+100, y,25,20);
		
		panel.add(new JLabel("F. de Nacimiento:")).setBounds(x,y+=25, ancho, 20);
		panel.add(txtCalendario).setBounds(x+ancho-40,y,125,20);

		panel.add(new JLabel("Calle y N°:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCalle).setBounds(x+ancho-40,y,ancho-15,20);
		
		panel.add(new JLabel("Colonia:")).setBounds(x+240,y,ancho,20);
		panel.add(txtColonia).setBounds(x+(ancho*2)+30,y,ancho-15,20);
		
		panel.add(new JLabel("Poblacion:")).setBounds(x+450,y,ancho,20);
		panel.add(txtPoblacion).setBounds(x+(ancho*3)+110,y,ancho-15,20);

		panel.add(lblSexo).setBounds(330,y+45,125,55);
			panel.add(rbMasculino).setBounds(355,y+55,85,20);
			panel.add(rbFemenino).setBounds(355,y+75,85,20);
		
		panel.add(new JLabel("Tel. Familiar:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtTelefono_Familiar).setBounds(x+ancho-40,y,ancho-15,20);
			panel.add(new JLabel("Tel. Propio:")).setBounds(x+240,y,ancho,20);
			panel.add(txtTelefono_Propio).setBounds(x+(ancho*2)+30,y,ancho-15,20);
				panel.add(new JLabel("Tel. Cuadrante:")).setBounds(x+450,y,ancho,20);
				panel.add(txtTelefono_Cuadrante).setBounds(x+(ancho*3)+110,y,ancho-15,20);
				
		panel.add(new JLabel("RFC:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtRFC).setBounds(x+ancho-40,y,ancho-15,20);
		
		panel.add(new JLabel("Curp:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCurp).setBounds(x+ancho-40,y,ancho-15,20);
		
//Laboral ------------------------------------------------------------------------------------------------------------------------------------------		
		panel.add(lblLaboral).setBounds(10,y+=50,ancho*7-30,220);
		
		panel.add(new JLabel("Horario:")).setBounds(x,y+=15,ancho,20);
		panel.add(btnHorarioNew).setBounds(x+ancho-83,y+3,17,17);
		panel.add(btnHorario).setBounds(x+ancho-63,y+3,17,17);
		panel.add(lblFolioHorario1).setBounds(x+ancho-40,y+3,20,15);
		panel.add(txtHorario).setBounds(x+ancho-20,y,ancho*2+60,20);
		panel.add(rbHorario).setBounds(x+460,y,20,20);
		
		panel.add(btnStatus).setBounds(x+ancho*5+20,y-5,ancho+60,180);
		
		panel.add(new JLabel("Tipo de horario:")).setBounds(x+480, y, ancho+10, 20);
		panel.add(cmbHorarioRotativo).setBounds(x+ancho+420,y, ancho+10, 20);
		
		panel.add(new JLabel("Horario 2:")).setBounds(x,y+=25,ancho,20);
		panel.add(btnHorario2).setBounds(x+ancho-63,y,17,17);
		panel.add(lblFolioHorario2).setBounds(x+ancho-40,y,20,15);
		panel.add(txtHorario2).setBounds(x+ancho-20,y,ancho*2+60,20);
		panel.add(rbHorario2).setBounds(x+460,y,20,20);
		
		panel.add(new JLabel("Descanso:")).setBounds(x+500,y,ancho,20);
		panel.add(txtDescanso).setBounds(x+ancho+420,y,ancho+10,20);
		
		panel.add(new JLabel("Horario 3:")).setBounds(x,y+=25,ancho,20);
		panel.add(btnHorario3).setBounds(x+ancho-63,y,17,17);
		panel.add(lblFolioHorario3).setBounds(x+ancho-40,y,20,15);
		panel.add(txtHorario3).setBounds(x+ancho-20,y,ancho*2+60,20);
		panel.add(rbHorario3).setBounds(x+460,y,20,20);
		
		panel.add(new JLabel("Día Dobla:")).setBounds(x+500,y,ancho,20);
		panel.add(txtDobla).setBounds(x+ancho+420,y,ancho+10,20);
		
		panel.add(new JLabel("Fecha Ingreso:")).setBounds(x,y+=25, ancho, 20);
		panel.add(txtIngreso).setBounds(x+(ancho)-40,y,130,20);
		
		panel.add(chb_cuadrante_parcial).setBounds(x+ancho+175,y,150,20);
		
		
		panel.add(new JLabel("Establecimiento:")).setBounds(x+470,y,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(x+ancho+410,y,ancho+20,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho-40,y,ancho-15,20);
		
		panel.add(new JLabel("Fecha Baja:")).setBounds(x+250,y, ancho, 20);
		panel.add(txtBaja).setBounds(x+ancho+180,y,115,20);
		panel.add(btnBaja).setBounds(x+ancho+295,y,25,20);

		panel.add(new JLabel("Departamento:")).setBounds(x+470,y,ancho,20);
		panel.add(cmbDepartamento).setBounds(x+(ancho*3)+130,y,ancho+20,20);
		
		panel.add(new JLabel("N° Infonavit:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNumeroInfonavit).setBounds(x+ancho-40,y,ancho+20,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(x+290,y,ancho,20);
		panel.add(cmbPuesto).setBounds(x+330,y,ancho*2+100,20);
		
		panel.add(new JLabel("N° Seguro Social:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtImss).setBounds(x+ancho-40,y,ancho+35,20);
		panel.add(cmbActivo_Inactivo).setBounds(x+ancho+135,y,180,20);
		
		panel.add(new JLabel("Ingreso IMSS:")).setBounds(x+470,y,ancho,20);
		panel.add(txtIngresoImss).setBounds(x+(ancho*3)+130,y,ancho+20,20);
		
		panel.add(new JLabel("Vencimiento de licencia:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtVencimientoLicencia).setBounds(x+ancho-20,y,ancho-20,20);
		
		panel.add(new JLabel("Ultimas vacaciones:")).setBounds(x+ancho+105,y,ancho,20);
		panel.add(txtFechaUltimasVacaciones).setBounds(x+(ancho*2)+65,y,ancho-40,20);
		panel.add(btnFechaUltimasVacaciones).setBounds(x+(ancho*3)+25,y,25,20);
		
		panel.add(new JLabel("Ultima icapacidad:")).setBounds(x+(ancho*3)+55,y,ancho,20);
		panel.add(txtFechaIncapacidad).setBounds(x+(ancho*3)+145,y,ancho-40,20);
		panel.add(btnFechaIncapacidad).setBounds(x+(ancho*4)+105,y,25,20);
//Percepciones y Deducciones ------------------------------------------------------------------------------------------------------------------------------------------		
		panel.add(lblPercepciones).setBounds(10,y+=30,ancho*4-40,170);
		panel.add(new JLabel("Salario Diario:")).setBounds(x,y+=15,ancho,20);
		panel.add(txtSalarioDiario).setBounds(x+ancho-40,y,ancho,20);
		
		panel.add(new JLabel("S. Diario Integrado:")).setBounds(x+260,y,ancho,20);
		panel.add(txtSalarioDiarioIntegrado).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(new JLabel("Forma de Pago:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFormaDePago).setBounds(x+ancho-40,y,ancho,20);
		
		panel.add(new JLabel("Sueldo:")).setBounds(x+260,y,ancho,20);
		panel.add(cmbSueldo).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(new JLabel("Bono:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbBono).setBounds(x+ancho-40,y,ancho,20);
		
		panel.add(new JLabel("Rango de Prestamo:")).setBounds(x+260,y,ancho,20);
		panel.add(cmbPrestamos).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(new JLabel("Pensión Alimenticia:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtPensionAli).setBounds(x+ancho-40,y,ancho,20);
		
		panel.add(new JLabel("Infonavit:")).setBounds(x+260,y,ancho,20);
		panel.add(txtInfonavit).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(new JLabel("Tarjeta de Nomina:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtTarjetaNomina).setBounds(x+ancho-40,y,ancho,20);
		
		panel.add(new JLabel("Tipo de Bancos:")).setBounds(x+260,y,ancho,20);
		panel.add(cmbTipoBancos).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(chbGafete).setBounds(x,y+=25,60,20);
		panel.add(chbFuente_Sodas).setBounds((x*7),y,90,20);
		
		panel.add(new JLabel("Ultima actualización:")).setBounds(x+250,y,ancho,20);
		panel.add(txtFechaActualizacion).setBounds(x+ancho+220,y,ancho,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=30,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		
		panel.add(Observasiones).setBounds(x+ancho*3+98,y-163,ancho+280,180);
		
		txaObservaciones.setLineWrap(true); 
		txaObservaciones.setWrapStyleWord(true);
		txaObservaciones.setDocument(new JTextFieldLimit(980));
		
		txtFolioEmpleado.setDocument(new JTextFieldLimit(9));
		txtChecador.setDocument(new JTextFieldLimit(100));
		txtNombre.setDocument(new JTextFieldLimit(20));
		txtApPaterno.setDocument(new JTextFieldLimit(20));
		txtApMaterno.setDocument(new JTextFieldLimit(20));
		txtTarjetaNomina.setDocument(new JTextFieldLimit(19));
		txtImss.setDocument(new JTextFieldLimit(11));
		txtTelefono_Familiar.setDocument(new JTextFieldLimit(10));
		txtTelefono_Propio.setDocument(new JTextFieldLimit(10));
		txtCalle.setDocument(new JTextFieldLimit(30));
		txtColonia.setDocument(new JTextFieldLimit(20));
		txtPoblacion.setDocument(new JTextFieldLimit(20));
		txtNumeroInfonavit.setDocument(new JTextFieldLimit(15));
		
		txtRFC.setDocument(new JTextFieldLimit(25));
		txtCurp.setDocument(new JTextFieldLimit(25));
		
		txtSalarioDiario.setDocument(new JTextFieldLimit(15));
		txtSalarioDiarioIntegrado.setDocument(new JTextFieldLimit(15));
		
		txtFormaDePago.setDocument(new JTextFieldLimit(15));
		
		txtPensionAli.setDocument(new JTextFieldLimit(15));
		txtInfonavit.setDocument(new JTextFieldLimit(15));
		
		btnEditar.addActionListener(editar);
		btnBuscar.addActionListener(buscar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		btnCamara.addActionListener(opFoto);
		btnVerificar.addActionListener(opVerificar);
		btnTrueFoto.addActionListener(opPresionFoto);
		btnIncontratables.addActionListener(Reporte_de_Empleados_No_Contratables);
		btnExaminar.addActionListener(opExaminar);
		btnHorarioNew.addActionListener(opGenerarHorairo);
		btnHorario.addActionListener(opFiltroHorairo);
		btnHorario2.addActionListener(opFiltroHorairo2);
		btnHorario3.addActionListener(opFiltroHorairo3);
		
		txtTarjetaNomina.addKeyListener(txtlogns);
		
		rbHorario.addActionListener(opRButton);
		rbHorario2.addActionListener(opRButton);
		rbHorario3.addActionListener(opRButton);
		
		txtFolioEmpleado.requestFocus();
		txtFolioEmpleado.addKeyListener(buscar_action);
		txtFolioEmpleado.addKeyListener(numerico_action);
		txtInfonavit.addKeyListener(validaNumericoConPunto);
		txtPensionAli.addKeyListener(validaNumericoPension);
		
		cmbHorarioRotativo.addActionListener(opCmbHorarioRotarivo);
		
		txtSalarioDiario.addKeyListener(validaNumericoSD);
		txtSalarioDiarioIntegrado.addKeyListener(validaNumericoSDI);
//		cmbTurno.setRenderer(new MyComboBoxRenderer());
		
		cont.add(panel);
		
		btnExaminar.setEnabled(false);
		btnCamara.setEnabled(false);
		
		btnHorario2.setEnabled(false);
		btnHorario3.setEnabled(false);
		
		txtDescanso.setEnabled(false);
		txtDobla.setEnabled(false);
		txtChecador.setEditable(false);
		txtHorario.setEnabled(false);
		txtHorario2.setEnabled(false);
		txtHorario3.setEnabled(false);
		txtFechaActualizacion.setEditable(false);
		
		panelEnabledFalse();
		txtFolioEmpleado.setEditable(true);
		txtTelefono_Cuadrante.setEditable(false);
		
		 ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
         btnFoto.setIcon(iconoDefault);
         
		 ImageIcon file_status = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Vigente.png");
         Icon iconoStatus = new ImageIcon(file_status.getImage().getScaledInstance(btnStatus.getWidth(), btnStatus.getHeight(), Image.SCALE_DEFAULT));
         btnStatus.setIcon(iconoStatus);
        
//       asigna el foco al JTextField deseado al arrancar la ventana
         this.addWindowListener(new WindowAdapter() {
                 public void windowOpened( WindowEvent e ){
                	 txtFolioEmpleado.requestFocus();
              }
         });
         
		this.setSize(975,680);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opCmbHorarioRotarivo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			switch(cmbHorarioRotativo.getSelectedIndex()){
				case 0: btnHorario.setEnabled(true);
						btnHorario2.setEnabled(false);
						btnHorario3.setEnabled(false);
						
						rbHorario.setEnabled(true);
						rbHorario2.setEnabled(false);
						rbHorario3.setEnabled(false);
						break;
						
				case 1: btnHorario.setEnabled(true);
						btnHorario2.setEnabled(true);
						btnHorario3.setEnabled(false);
						
						rbHorario.setEnabled(true);
						rbHorario2.setEnabled(true);
						rbHorario3.setEnabled(false);
						break;
						
				case 2: btnHorario.setEnabled(true);
						btnHorario2.setEnabled(true);
						btnHorario3.setEnabled(true);
						
						rbHorario.setEnabled(true);
						rbHorario2.setEnabled(true);
						rbHorario3.setEnabled(true);
						break;
			}
		}
	};
	
	ActionListener opPresionFoto = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(btnTrueFoto.isSelected()){
				btnExaminar.setEnabled(true);
				btnCamara.setEnabled(true);
			}else{
				btnExaminar.setEnabled(false);
				btnCamara.setEnabled(false);
			}
	
		}
	};
	
	ActionListener opRButton = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			if(rbHorario.isSelected()==true){
//				buscar horario 1 y asignar dia de descanso y dobla
				Obj_Horario_Empleado descanso = new Obj_Horario_Empleado().buscar_tur(txtHorario.getText());
				txtDescanso.setText(descanso.getDescanso());
				txtDobla.setText(descanso.getDobla());
			}
			if(rbHorario2.isSelected()==true){
//				buscar horario 2 y asignar dia de descanso y dobla
				Obj_Horario_Empleado descanso = new Obj_Horario_Empleado().buscar_tur(txtHorario2.getText());
				txtDescanso.setText(descanso.getDescanso());
				txtDobla.setText(descanso.getDobla());
			}
			if(rbHorario3.isSelected()==true){
//				buscar horario 3 y asignar dia de descanso y dobla
				Obj_Horario_Empleado descanso = new Obj_Horario_Empleado().buscar_tur(txtHorario3.getText());
				txtDescanso.setText(descanso.getDescanso());
				txtDobla.setText(descanso.getDobla());
			}
		}
	};
	
	ActionListener opExaminar = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			FileDialog file = new FileDialog(new Frame());
			
			file.setTitle("Selecciona una Imagen");
			file.setMode(FileDialog.LOAD);
			file.setVisible(true);
			
			if(file.getDirectory() != null){

					try {
						String rootPicture = file.getDirectory()+file.getFile();
						
						File foto = new File(rootPicture);
						File destino = new File(System.getProperty("user.dir")+"/tmp/tmp.jpg");
				    	
				    	InputStream in = new FileInputStream(foto);
						OutputStream out = new FileOutputStream(destino);
						
					    byte[] buf = new byte[1024];
					    int len;
	
					    while ((len = in.read(buf)) > 0) {
					    	out.write(buf, 0, len);
					    }
					    
					    in.close();
					    out.close();
						
						File foto1 = new File(rootPicture);
						File destino1 = new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg");
				    	
				    	InputStream in1 = new FileInputStream(foto1);
						OutputStream out1 = new FileOutputStream(destino1);
						
					    byte[] buf1 = new byte[1024];
					    int len1;
	
					    while ((len1 = in1.read(buf1)) > 0) {
					    	out1.write(buf1, 0, len1);
					    }
					    
					    in1.close();
					    out1.close();
					    
						
					    ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
				         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
				         btnFoto.setIcon(iconoDefault);
				         
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
			}else{
				JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna imagen","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opVerificar = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(txtNombre.getText().length() == 0 || txtApPaterno.getText().length() == 0 || txtApMaterno.getText().length() == 0){
				JOptionPane.showMessageDialog(null,"Los campos nombre y apellidos deben tener texto","Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				String nombre = procesa_texto(txtNombre.getText()) + " " + procesa_texto(txtApPaterno.getText()) + " " + procesa_texto(txtApMaterno.getText());
				if(new Obj_Empleado().nombre_disponible(nombre)){
					btnVerificar.setBackground(Color.red);
					
					txtNombre.setEditable(true);
					txtApPaterno.setEditable(true);
					txtApMaterno.setEditable(true);
					
					rbHorario.setEnabled(true);
					
				}else{
					btnVerificar.setBackground(Color.blue);
					panelEnabledTrue();
				}
			}
		}
	};
	
    public String procesa_texto(String texto) {
        StringTokenizer tokens = new StringTokenizer(texto);
        texto = "";
        while(tokens.hasMoreTokens()){
            texto += " "+tokens.nextToken();
        }
        texto = texto.toString();
        texto = texto.trim().toUpperCase();
        return texto;
    }
	
	ActionListener opFoto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolioEmpleado.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "Cree un nuevo empleado, que contenga un folio.","Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try{
					new MainCamara("tmp.jpg").setVisible(true);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "Verifique si está conectada y configurada la camara", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if(txtFolioEmpleado.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				
				Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolioEmpleado.getText()));
				if(re.getFolio() != 0){			
					txtFolioEmpleado.setText(re.getFolio()+"");
					txtChecador.setText(re.getNo_checador()+"");
					txtNombre.setText(re.getNombre()+"");
					txtApPaterno.setText(re.getAp_paterno()+"");
					txtApMaterno.setText(re.getAp_materno()+"");
					
					try {
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_nacimiento());
						Date date_ingreso = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_ingreso());
						
						Date date_ingreso_imss = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_ingreso_imss());
						Date date_vencimiento_licencia = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_vencimiento_licencia());
						
						Date date_ingreso_imss_comparacion = new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1900");
						Date date_vencimiento_licencia_comparacion = new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1900");
						
						txtCalendario.setDate(date);
						txtIngreso.setDate(date_ingreso);
						
						if(date_ingreso_imss_comparacion.before(date_ingreso_imss)){
							txtIngresoImss.setDate(date_ingreso_imss);
						}else{
							txtIngresoImss.setDate(null);
						}
						
						if(date_vencimiento_licencia_comparacion.before(date_vencimiento_licencia)){
							txtVencimientoLicencia.setDate(date_vencimiento_licencia);
						}else{
							txtVencimientoLicencia.setDate(null);
						}
						
						
						
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					txtCalle.setText(re.getCalle()+"");
					txtColonia.setText(re.getColonia()+"");
					txtPoblacion.setText(re.getPoblacion()+"");
					txtTelefono_Familiar.setText(re.getTelefono_familiar()+"");
					txtTelefono_Propio.setText(re.getTelefono_propio()+"");
					
					if(re.getTelefono_cuadrante()==null){
						txtTelefono_Cuadrante.setText("");
					}else{
						txtTelefono_Cuadrante.setText(re.getTelefono_cuadrante()+"");
					}
					
					txtRFC.setText(re.getRfc()+"");
					txtCurp.setText(re.getCurp()+"");
					
					if(re.getSexo()==0){
						rbMasculino.setSelected(true);
					}else{
						rbFemenino.setSelected(true);
					}
					
					ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
			         btnFoto.setIcon(iconoDefault);
			         
					
					Obj_Horario_Empleado comboFolioHorario = new Obj_Horario_Empleado().buscar_tur(re.getHorario());
					if(re.getHorario()>0){
						lblFolioHorario1.setText(re.getHorario()+"");
					}else{
						lblFolioHorario1.setText("");
					}
					txtHorario.setText(comboFolioHorario.getNombre());
	
					
					Obj_Horario_Empleado2 comboFolioHorario2 = new Obj_Horario_Empleado2().buscar_tur2(re.getHorario2());
					if(re.getHorario2()>0){
						lblFolioHorario2.setText(re.getHorario2()+"");
					}else{
						lblFolioHorario2.setText("");
					}
					txtHorario2.setText(comboFolioHorario2.getNombre());
					
					
					Obj_Horario_Empleado3 comboFolioHorario3 = new Obj_Horario_Empleado3().buscar_tur3(re.getHorario3());
					if(re.getHorario3()>0){
						lblFolioHorario3.setText(re.getHorario3()+"");
					}else{
						lblFolioHorario3.setText("");
					}
					txtHorario3.setText(comboFolioHorario3.getNombre());

					
					if(re.getStatus_h1()==1){
						rbHorario.setSelected(true);
					}
					if(re.getStatus_h2()==1){
						rbHorario2.setSelected(true);
					}
					if(re.getStatus_h3()==1){
						rbHorario3.setSelected(true);
					}
										
					txtHorario.setToolTipText(comboFolioHorario.getNombre());
					txtHorario2.setToolTipText(comboFolioHorario2.getNombre());
					
					txtDescanso.setText(re.getDescanso()+"");
					txtDobla.setText(re.getDobla()+"");
					
					switch(re.getStatus_rotativo()){
						case 0: cmbHorarioRotativo.setSelectedIndex(0); break;
						case 1: cmbHorarioRotativo.setSelectedIndex(1); break;
						case 2: cmbHorarioRotativo.setSelectedIndex(2); break;
					}
					
					cmbStatus.setSelectedIndex(re.getStatus()-1);
					txtBaja.setText(re.getFecha_baja()+"");
					chb_cuadrante_parcial.setSelected(re.isCuadrante_parcial());
					
					Obj_Departamento depart = new Obj_Departamento().buscar(re.getDepartameto());
					cmbDepartamento.setSelectedItem(depart.getDepartamento());
					
					txtImss.setText(re.getImss()+"");
					cmbActivo_Inactivo.setSelectedIndex(re.getStatus_imss());
					txtNumeroInfonavit.setText(re.getNumero_infonavit()+"");
					
					Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
					cmbEstablecimiento.setSelectedItem(comboNombreEsta.getNombre());
					
					Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
					cmbPuesto.setSelectedItem(comboNombrePues.getPuesto());
					
					txtSalarioDiario.setText(re.getSalario_diario()+"");
					txtSalarioDiarioIntegrado.setText(re.getSalario_diario_integrado()+"");
					txtFormaDePago.setText(re.getForma_pago()+"");
					cmbSueldo.setSelectedIndex(re.getSueldo());
					
					Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo().buscar(re.getBono());
					cmbBono.setSelectedItem(bono.getBono()+"");

					cmbPrestamos.setSelectedIndex(re.getPrestamo());
					txtPensionAli.setText(re.getPension_alimenticia()+"");
					txtInfonavit.setText(re.getInfonavit()+"");	
					txtTarjetaNomina.setText(re.getTargeta_nomina()+"");
					
					Obj_Tipo_Banco comboNombreBanco = new Obj_Tipo_Banco().buscar_pues(re.getTipo_banco());
					cmbTipoBancos.setSelectedItem(comboNombreBanco.getBanco());
					
					if(re.isGafete() == true){chbGafete.setSelected(true);}
					else{chbGafete.setSelected(false);}
					
					if(re.isFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
					else{chbFuente_Sodas.setSelected(false);}
					
//					txtFechaActualizacion.setText(new SimpleDateFormat("dd/MM/yyyy").format((Date.parse(re.getFecha_actualizacion()))));
					txtFechaActualizacion.setText(re.getFecha_actualizacion());
					txaObservaciones.setText(re.getObservasiones());
					
					switch(cmbStatus.getSelectedIndex()+1){
						case 1:btnStatus.setIcon(new ImageIcon("Iconos/vigente.png")); 
							   btnEditar.setVisible(true);
							   break;
						case 2:btnStatus.setIcon(new ImageIcon("Iconos/vacaciones.png"));
							   btnEditar.setVisible(true);
							   break;
						case 3:btnStatus.setIcon(new ImageIcon("Iconos/incapacidad.png"));
							   btnEditar.setVisible(true);
							   break;
						case 4:btnStatus.setIcon(new ImageIcon("Iconos/baja.png")); 
							   btnEditar.setVisible(true);
							   break;
						case 5:btnStatus.setIcon(new ImageIcon("Iconos/baja.png")); 
							   btnEditar.setVisible(false); 
							   break;
							   
					}
						
				    btnNuevo.setVisible(false);
					panelEnabledFalse();
					txtFolioEmpleado.setEditable(true);
					txtFolioEmpleado.requestFocus();
					btnEditar.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					panelEnabledFalse();
					txtFolioEmpleado.setEditable(true);
					txtFolioEmpleado.requestFocus();
					panelLimpiar();
					return;
				}
			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			if(txtFolioEmpleado.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolioEmpleado.getText()));
				
				if(empleado.getFolio() == Integer.parseInt(txtFolioEmpleado.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{

//					datos personales	
							txtFechaActualizacion.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
							empleado.setNo_checador(txtChecador.getText());
							empleado.setNombre(procesa_texto(txtNombre.getText()));
							empleado.setAp_paterno(procesa_texto(txtApPaterno.getText()));
							
							if(txtApMaterno.getText().length() != 0){
								empleado.setAp_materno(procesa_texto(txtApMaterno.getText()));
							}else{
								empleado.setAp_materno("");
							}
							
							empleado.setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							empleado.setCalle(txtCalle.getText());
							empleado.setColonia(txtColonia.getText());
							empleado.setPoblacion(txtPoblacion.getText());
							empleado.setTelefono_familiar(txtTelefono_Familiar.getText()+"");
							empleado.setTelefono_propio(txtTelefono_Propio.getText()+"");
							empleado.setRfc(txtRFC.getText());
							empleado.setCurp(txtCurp.getText());
							
							if(rbMasculino.isSelected()==true){
								empleado.setSexo(0);
							}else{
								empleado.setSexo(1);
							}
							
							if(btnTrueFoto.isSelected()){
								empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
							}else{
								empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp.jpg"));
							}

//					laboral
							Obj_Horario_Empleado comboFolioHorario = new Obj_Horario_Empleado().buscar_tur(txtHorario.getText());
							empleado.setHorario(comboFolioHorario.getFolio());
							
							Obj_Horario_Empleado2 comboFolioHorario2 = new Obj_Horario_Empleado2().buscar_tur2(txtHorario2.getText());
							empleado.setHorario2(comboFolioHorario2.getFolio());
							
							Obj_Horario_Empleado3 comboFolioHorario3 = new Obj_Horario_Empleado3().buscar_tur3(txtHorario3.getText());
							empleado.setHorario3(comboFolioHorario3.getFolio());
							
							if(rbHorario.isSelected()==true){
								empleado.setStatus_h1(1);
								empleado.setStatus_h2(0);
								empleado.setStatus_h3(0);
							}
							if(rbHorario2.isSelected()==true){
								empleado.setStatus_h1(0);
								empleado.setStatus_h2(1);
								empleado.setStatus_h3(0);
							}
							if(rbHorario3.isSelected()==true){
								empleado.setStatus_h1(0);
								empleado.setStatus_h2(0);
								empleado.setStatus_h3(1);
							}
							
							switch(cmbHorarioRotativo.getSelectedIndex()){
								case 0: empleado.setStatus_rotativo(0); break;
								case 1: empleado.setStatus_rotativo(1); break;
								case 2: empleado.setStatus_rotativo(2); break;
							}
							
							empleado.setFecha_ingreso(new SimpleDateFormat("dd/MM/yyyy").format(txtIngreso.getDate()));
							empleado.setStatus(cmbStatus.getSelectedIndex()+1);
							empleado.setCuadrante_parcial(chb_cuadrante_parcial.isSelected());
							
							Obj_Departamento depart = new Obj_Departamento().buscar_departamento(cmbDepartamento.getSelectedItem()+"");
							empleado.setDepartameto(depart.getFolio());
							
							empleado.setImss(txtImss.getText());
							empleado.setStatus_imss(cmbActivo_Inactivo.getSelectedIndex());
							empleado.setNumero_infonavit(txtNumeroInfonavit.getText()+"");
							
							Obj_Establecimiento comboFolioEsta = new Obj_Establecimiento().buscar_estab(cmbEstablecimiento.getSelectedItem()+"");
							empleado.setEstablecimiento(comboFolioEsta.getFolio());
							
							Obj_Puesto comboFolioPues = new Obj_Puesto().buscar_pues(cmbPuesto.getSelectedItem()+"");
							empleado.setPuesto(comboFolioPues.getFolio());
							
							if(txtIngresoImss.getDate()==null){
								empleado.setFecha_ingreso_imss("1/01/1900");
							}else{
								empleado.setFecha_ingreso_imss(new SimpleDateFormat("dd/MM/yyyy").format(txtIngresoImss.getDate()));
							}
							
							if(txtVencimientoLicencia.getDate()==null){
								empleado.setFecha_vencimiento_licencia("1/01/1900");
							}else{
								empleado.setFecha_vencimiento_licencia(new SimpleDateFormat("dd/MM/yyyy").format(txtVencimientoLicencia.getDate()));
							}
							
//					percepciones y deducciones
					
							if(!txtSalarioDiario.getText().equals("")){
								empleado.setSalario_diario(Float.parseFloat(txtSalarioDiario.getText())) ;
							}else{
								empleado.setSalario_diario(Float.parseFloat(0.0+"")); }
						
							if(!txtSalarioDiarioIntegrado.getText().equals("")){
								empleado.setSalario_diario_integrado(Float.parseFloat(txtSalarioDiarioIntegrado.getText()));
							}else{
								empleado.setSalario_diario_integrado(Float.parseFloat(0.0+""));
							}
							
							empleado.setForma_pago(txtFormaDePago.getText()+"");

							empleado.setSueldo(cmbSueldo.getSelectedIndex());
							
							Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo().buscarValor(Float.parseFloat(cmbBono.getSelectedItem()+""));
							empleado.setBono(bono.getFolio());
							
							empleado.setPrestamo(cmbPrestamos.getSelectedIndex());
							
							if(txtPensionAli.getText().length() != 0){
								empleado.setPension_alimenticia(Float.parseFloat(txtPensionAli.getText()));
							}else{
								empleado.setPension_alimenticia(Float.parseFloat(0.0+""));
							}
							if(txtInfonavit.getText().length() != 0){
								empleado.setInfonavit(Float.parseFloat(txtInfonavit.getText()));
							}else{
								empleado.setInfonavit(Float.parseFloat(0.0+""));
							}
							
							empleado.setTargeta_nomina(txtTarjetaNomina.getText()+"");
//							if(txtTarjetaNomina.getText().length() != 0){
//								empleado.setTargeta_nomina(txtTarjetaNomina.getText());
//							}else{
//								empleado.setTargeta_nomina("");
//							}
							
							empleado.setTipo_banco(cmbTipoBancos.getSelectedIndex());
							empleado.setGafete(chbGafete.isSelected());
							empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
							empleado.setObservasiones(txaObservaciones.getText()+"");
							
							empleado.setFecha_actualizacion(txtFechaActualizacion.getText());
//							if(txaObservaciones.getText().length() != 0){
//								empleado.setObservasiones(txaObservaciones.getText());
//							}else{
//								empleado.setObservasiones("");
//							}
							
							if(empleado.actualizar(Integer.parseInt(txtFolioEmpleado.getText()))){
								panelLimpiar();
								panelEnabledFalse();
								rbHorario2.setEnabled(false);
								rbHorario3.setEnabled(false);
								txtFolioEmpleado.setEditable(true);
								txtFolioEmpleado.requestFocus();
								btnTrueFoto.setSelected(false);
								btnExaminar.setEnabled(false);
								btnCamara.setEnabled(false);
								txtHorario.setEnabled(false);
								JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null,"Error al intentar actualizar los datos","Aviso",JOptionPane.ERROR_MESSAGE);
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
						
//				datos personales	
						txtFechaActualizacion.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
						empleado.setNo_checador(txtFolioEmpleado.getText());
						empleado.setNombre(procesa_texto(txtNombre.getText()));
						empleado.setAp_paterno(procesa_texto(txtApPaterno.getText()));
						
						if(txtApMaterno.getText().length() != 0){
							empleado.setAp_materno(procesa_texto(txtApMaterno.getText()));
						}else{
							empleado.setAp_materno("");
						}
						
						empleado.setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
						empleado.setCalle(txtCalle.getText());
						empleado.setColonia(txtColonia.getText());
						empleado.setPoblacion(txtPoblacion.getText());
						empleado.setTelefono_familiar(txtTelefono_Familiar.getText()+"");
						empleado.setTelefono_propio(txtTelefono_Propio.getText()+"");
						empleado.setRfc(txtRFC.getText());
						empleado.setCurp(txtCurp.getText());
						
						if(rbMasculino.isSelected()==true){
							empleado.setSexo(0);
						}else{
							empleado.setSexo(1);
						}
						
						if(btnTrueFoto.isSelected()){
							empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
						}else{
							empleado.setFoto(new File(System.getProperty("user.dir")+"/Iconos/Un.jpg"));
						}

//				laboral
						Obj_Horario_Empleado comboFolioHorario = new Obj_Horario_Empleado().buscar_tur(txtHorario.getText());
						empleado.setHorario(comboFolioHorario.getFolio());
						
						Obj_Horario_Empleado2 comboFolioHorario2 = new Obj_Horario_Empleado2().buscar_tur2(txtHorario2.getText());
						empleado.setHorario2(comboFolioHorario2.getFolio());
						
						Obj_Horario_Empleado3 comboFolioHorario3 = new Obj_Horario_Empleado3().buscar_tur3(txtHorario3.getText());
						empleado.setHorario3(comboFolioHorario3.getFolio());
						
						if(rbHorario.isSelected()==true){
							empleado.setStatus_h1(1);
							empleado.setStatus_h2(0);
							empleado.setStatus_h3(0);
						}
						if(rbHorario2.isSelected()==true){
							empleado.setStatus_h1(0);
							empleado.setStatus_h2(1);
							empleado.setStatus_h3(0);
						}
						if(rbHorario3.isSelected()==true){
							empleado.setStatus_h1(0);
							empleado.setStatus_h2(0);
							empleado.setStatus_h3(1);
						}
						
						switch(cmbHorarioRotativo.getSelectedIndex()){
							case 0: empleado.setStatus_rotativo(0); break;
							case 1: empleado.setStatus_rotativo(1); break;
							case 2: empleado.setStatus_rotativo(2); break;
						}
						
						empleado.setFecha_ingreso(new SimpleDateFormat("dd/MM/yyyy").format(txtIngreso.getDate()));
						empleado.setStatus(cmbStatus.getSelectedIndex()+1);
						empleado.setCuadrante_parcial(chb_cuadrante_parcial.isSelected());

						Obj_Departamento depart = new Obj_Departamento().buscar_departamento(cmbDepartamento.getSelectedItem()+"");
						empleado.setDepartameto(depart.getFolio());
						
						empleado.setImss(txtImss.getText());
						empleado.setStatus_imss(cmbActivo_Inactivo.getSelectedIndex());
						empleado.setNumero_infonavit(txtNumeroInfonavit.getText()+"");
						
						Obj_Establecimiento comboFolioEsta = new Obj_Establecimiento().buscar_estab(cmbEstablecimiento.getSelectedItem()+"");
						empleado.setEstablecimiento(comboFolioEsta.getFolio());
						
						Obj_Puesto comboFolioPues = new Obj_Puesto().buscar_pues(cmbPuesto.getSelectedItem()+"");
						empleado.setPuesto(comboFolioPues.getFolio());
						
						if(txtIngresoImss.getDate()==null){
							empleado.setFecha_ingreso_imss("1/01/1900");
						}else{
							empleado.setFecha_ingreso_imss(new SimpleDateFormat("dd/MM/yyyy").format(txtIngresoImss.getDate()));
						}
						
						if(txtVencimientoLicencia.getDate()==null){
							empleado.setFecha_vencimiento_licencia("1/01/1900");
						}else{
							empleado.setFecha_vencimiento_licencia(new SimpleDateFormat("dd/MM/yyyy").format(txtVencimientoLicencia.getDate()));
						}

//				percepciones y deducciones
						if(!txtSalarioDiario.getText().equals("")){
							empleado.setSalario_diario(Float.parseFloat(txtSalarioDiario.getText())) ;
						}else{
							empleado.setSalario_diario(Float.parseFloat(0.0+"")); }
					
						if(!txtSalarioDiarioIntegrado.getText().equals("")){
							empleado.setSalario_diario_integrado(Float.parseFloat(txtSalarioDiarioIntegrado.getText()));
						}else{
							empleado.setSalario_diario_integrado(Float.parseFloat(0.0+""));
						}
						
						empleado.setForma_pago(txtFormaDePago.getText()+"");
						empleado.setSueldo(cmbSueldo.getSelectedIndex());
						
						Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo().buscarValor(Float.parseFloat(cmbBono.getSelectedItem()+""));
						empleado.setBono(bono.getFolio());
						
						empleado.setPrestamo(cmbPrestamos.getSelectedIndex());
						
						if(txtPensionAli.getText().length() != 0){
							empleado.setPension_alimenticia(Float.parseFloat(txtPensionAli.getText()));
						}else{
							empleado.setPension_alimenticia(Float.parseFloat(0.0+""));
						}
						if(txtInfonavit.getText().length() != 0){
							empleado.setInfonavit(Float.parseFloat(txtInfonavit.getText()));
						}else{
							empleado.setInfonavit(Float.parseFloat(0.0+""));
						}
						
						empleado.setTargeta_nomina(txtTarjetaNomina.getText()+"");
//						if(txtTarjetaNomina.getText().length() != 0){
//							empleado.setTargeta_nomina(txtTarjetaNomina.getText());
//						}else{
//							empleado.setTargeta_nomina("");
//						}
						
						empleado.setTipo_banco(cmbTipoBancos.getSelectedIndex());
						empleado.setGafete(chbGafete.isSelected());
						empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
						empleado.setObservasiones(txaObservaciones.getText()+"");
						
						empleado.setFecha_actualizacion(txtFechaActualizacion.getText());
						
//						if(txaObservaciones.getText().length() != 0){
//							empleado.setObservasiones(txaObservaciones.getText());
//						}else{
//							empleado.setObservasiones("");
//						}
						if(empleado.guardar()){
							panelLimpiar();
							panelEnabledFalse();
							rbHorario2.setEnabled(false);
							rbHorario3.setEnabled(false);
							txtFolioEmpleado.setEditable(true);
							txtFolioEmpleado.requestFocus();
							btnTrueFoto.setSelected(false);
							btnExaminar.setEnabled(false);
							btnCamara.setEnabled(false);
							txtHorario.setEnabled(false);
							
							JOptionPane.showMessageDialog(null,"El registro se guardó de forma segura","Aviso",JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un problema al almacenar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}			
		}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			new Cat_Filtro_Empleado().setVisible(true);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolioEmpleado.getText()));
			if(empleado.getFolio() != 0){
				
				switch(empleado.getStatus_rotativo()){
				
					case 0: panelEnabledTrue();
							cmbHorarioRotativo.setSelectedIndex(0);
							rbHorario.setSelected(true);
							break;
							
					case 1: panelEnabledTrue();
							cmbHorarioRotativo.setSelectedIndex(1);
							rbHorario2.setEnabled(true);
							break;
							
					case 2: panelEnabledTrue();
							cmbHorarioRotativo.setSelectedIndex(2);
							rbHorario3.setEnabled(true);
							break;
				}
				
				txtFolioEmpleado.setEditable(false);
				btnEditar.setVisible(false);
				btnNuevo.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"El registró que desea actualizar no existe","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				return;
			}
		}		
	};
	
	public void panelEnabledTrue(){	
		txtFolioEmpleado.setEditable(true);
		txtNombre.setEditable(true);
		txtApPaterno.setEditable(true);
		txtApMaterno.setEditable(true);
		txtPensionAli.setEditable(true);
		cmbEstablecimiento.setEnabled(true);
		cmbPuesto.setEnabled(true);
		cmbSueldo.setEnabled(true);
		cmbBono.setEnabled(true);
		cmbPrestamos.setEnabled(true);
		txtInfonavit.setEditable(true);
		txtTarjetaNomina.setEditable(true);
		cmbTipoBancos.setEnabled(true);
		txtImss.setEnabled(true);
		chbFuente_Sodas.setEnabled(true);
		chbGafete.setEnabled(true);
		cmbStatus.setEnabled(true);
		txaObservaciones.setEditable(true);
		txtCalendario.setEnabled(true);
		cmbActivo_Inactivo.setEnabled(true);
		txtIngreso.setEnabled(true);
		txtTelefono_Familiar.setEditable(true);
		chb_cuadrante_parcial.setEnabled(true);
		
		txtIngresoImss.setEnabled(true);
		txtVencimientoLicencia.setEnabled(true);
		
		txtCalle.setEnabled(true);
		txtColonia.setEnabled(true);
		txtPoblacion.setEnabled(true);
		txtTelefono_Propio.setEnabled(true);
		txtRFC.setEnabled(true);
		txtCurp.setEnabled(true);
		
		rbMasculino.setEnabled(true);
		rbFemenino.setEnabled(true);
		
		rbHorario.setEnabled(true);
		rbHorario2.setEnabled(true);
		cmbDepartamento.setEnabled(true);
		txtNumeroInfonavit.setEnabled(true);
		
		txtSalarioDiario.setEnabled(true);
		txtSalarioDiarioIntegrado.setEnabled(true);
		txtFormaDePago.setEnabled(true);
	}
	
	public void panelEnabledFalse(){	
		txtFolioEmpleado.setEditable(false);
		txtNombre.setEditable(false);
		txtApPaterno.setEditable(false);
		txtApMaterno.setEditable(false);
		txtPensionAli.setEditable(false);
		cmbEstablecimiento.setEnabled(false);
		cmbPuesto.setEnabled(false);
		cmbSueldo.setEnabled(false);
		cmbBono.setEnabled(false);
		cmbPrestamos.setEnabled(false);
		txtInfonavit.setEditable(false);
		txtTarjetaNomina.setEditable(false);
		cmbTipoBancos.setEnabled(false);
		txtImss.setEnabled(false);
		chbFuente_Sodas.setEnabled(false);
		chbGafete.setEnabled(false);
		cmbStatus.setEnabled(false);
		txaObservaciones.setEditable(false);
		txtCalendario.setEnabled(false);
		cmbActivo_Inactivo.setEnabled(false);
		txtIngreso.setEnabled(false);
		txtTelefono_Familiar.setEditable(false);
		chb_cuadrante_parcial.setEnabled(false);
		
		txtIngresoImss.setEnabled(false);
		txtVencimientoLicencia.setEnabled(false);
		
		txtCalle.setEnabled(false);
		txtColonia.setEnabled(false);
		txtPoblacion.setEnabled(false);
		txtTelefono_Propio.setEnabled(false);
		txtRFC.setEnabled(false);
		txtCurp.setEnabled(false);
		
		rbMasculino.setEnabled(false);
		rbFemenino.setEnabled(false);
		
		rbHorario.setEnabled(false);
		rbHorario2.setEnabled(false);
		rbHorario3.setEnabled(false);
		
		txtBaja.setEnabled(false);
		cmbDepartamento.setEnabled(false);
		txtNumeroInfonavit.setEnabled(false);
		
		txtSalarioDiario.setEnabled(false);
		txtSalarioDiarioIntegrado.setEnabled(false);
		txtFormaDePago.setEnabled(false);
		
		btnTrueFoto.setSelected(false);
		btnExaminar.setEnabled(false);
		btnCamara.setEnabled(false);
	}

	///boton deshacer
	public void panelLimpiar(){	
		txtFolioEmpleado.setText("");
		txtChecador.setText("");
		txtNombre.setText("");
		txtApPaterno.setText("");
		txtApMaterno.setText("");
		txtPensionAli.setText("");
		cmbEstablecimiento.setSelectedIndex(0);
		cmbPuesto.setSelectedIndex(0);
		txtHorario.setText("");
		txtHorario2.setText("");
		txtHorario3.setText("");
		cmbSueldo.setSelectedIndex(0);
		cmbBono.setSelectedIndex(0);
		cmbPrestamos.setSelectedIndex(0);
		txtInfonavit.setText("");
		txtTarjetaNomina.setText("");
		cmbTipoBancos.setSelectedIndex(0);
		txtImss.setText("");
		chbFuente_Sodas.setSelected(false);
		chbGafete.setSelected(false);
		cmbStatus.setSelectedIndex(0);
		txaObservaciones.setText("");
	    btnFoto.setIcon(new ImageIcon(""));	
	    btnStatus.setIcon(new ImageIcon(""));
	    cmbActivo_Inactivo.setSelectedIndex(0);
	    txtTelefono_Familiar.setText("");
	    txtTelefono_Cuadrante.setText("");
	    chb_cuadrante_parcial.setSelected(false);
	    txtCalendario.setDate(null);
	    
		txtCalle.setText("");
		txtColonia.setText("");
		txtPoblacion.setText("");
		txtTelefono_Propio.setText("");
		txtRFC.setText("");
		txtCurp.setText("");
		txtIngreso.setDate(null);
		
		txtIngresoImss.setDate(null);
		txtVencimientoLicencia.setDate(null);
		
		txtDescanso.setText("");
		txtDobla.setText("");
		
		txtBaja.setText("");
		cmbDepartamento.setSelectedIndex(0);
		txtNumeroInfonavit.setText("");
		
		txtSalarioDiario.setText("");
		txtSalarioDiarioIntegrado.setText("");
		txtFormaDePago.setText("");
		txtFechaActualizacion.setText("");
		
		lblFolioHorario1.setText("");
		lblFolioHorario2.setText("");
		lblFolioHorario3.setText("");
		
		cmbHorarioRotativo.setSelectedIndex(0);
	    
		 ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
         btnFoto.setIcon(iconoDefault);
         
		 ImageIcon file_status = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Vigente.png");
         Icon iconoStatus = new ImageIcon(file_status.getImage().getScaledInstance(btnStatus.getWidth(), btnStatus.getHeight(), Image.SCALE_DEFAULT));
         btnStatus.setIcon(iconoStatus);
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				Obj_Empleado empleado = new Obj_Empleado().buscar_nuevo();
				if(empleado.getFolio() != 0){
					panelLimpiar();
					panelEnabledFalse();
					txtNombre.setEditable(true);
					txtApPaterno.setEditable(true);
					txtApMaterno.setEditable(true);
					txtFolioEmpleado.setText(empleado.getFolio()+1+"");
					txtNombre.requestFocus();
					txtFechaActualizacion.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
					
					 ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
			         Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_DEFAULT));
			         btnFoto.setIcon(iconoDefault);
			         
					 ImageIcon file_status = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Vigente.png");
			         Icon iconoStatus = new ImageIcon(file_status.getImage().getScaledInstance(btnStatus.getWidth(), btnStatus.getHeight(), Image.SCALE_DEFAULT));
			         btnStatus.setIcon(iconoStatus);
			         
				}else{
					panelEnabledTrue();
					txtFolioEmpleado.setText(1+"");
					txtFolioEmpleado.setEditable(false);
					txtNombre.requestFocus();
					txtFechaActualizacion.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
					
					String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
					ImageIcon tmpIconAux = new ImageIcon(file);
					btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
					
					String file_status = System.getProperty("user.dir")+"/Iconos/Vigente.png";
					ImageIcon tmpIconAux_status = new ImageIcon(file_status);
					btnStatus.setIcon(new ImageIcon(tmpIconAux_status.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			rbHorario2.setEnabled(false);
			panelEnabledFalse();
			txtFolioEmpleado.setEditable(true);
			txtFolioEmpleado.requestFocus();
			btnEditar.setVisible(false);
			btnNuevo.setVisible(true);
			txtHorario.setEnabled(false);
		}
	};
	
	ActionListener Reporte_de_Empleados_No_Contratables = new ActionListener(){
		public void actionPerformed(ActionEvent e){
				new Reporte_de_Empleados_No_Contratables();
		}
	};
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	ActionListener opFiltroHorairo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			seleccion_de_asignacion_de_Horario1Horario2Horario3=1;
			new Filtro_Horario_Empleado().setVisible(true);
		}
	};
	ActionListener opFiltroHorairo2 = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			seleccion_de_asignacion_de_Horario1Horario2Horario3=2;
			new Filtro_Horario_Empleado().setVisible(true);
		}
	};
	ActionListener opFiltroHorairo3 = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			seleccion_de_asignacion_de_Horario1Horario2Horario3=3;
			new Filtro_Horario_Empleado().setVisible(true);
		}
	};
	
	ActionListener opGenerarHorairo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Cat_Horario().setVisible(true);
		}
	};
	
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
	
	KeyListener txtlogns = new KeyListener() {
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if((caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_DELETE)){
				int longitud = txtTarjetaNomina.getText().length();
				String copas = txtTarjetaNomina.getText();
				switch(longitud){
					case 4 : txtTarjetaNomina.setText(copas+"-"); break;
					case 9 : txtTarjetaNomina.setText(copas+"-"); break;
					case 14 : txtTarjetaNomina.setText(copas+"-"); break;
				}
				if(((caracter < '0') ||
						(caracter > '9'))){
						e.consume(); 
				}				
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {}
		@Override
		public void keyReleased(KeyEvent arg0) {}
	};
	
	KeyListener validaNumericoSD = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    	String texto = txtSalarioDiario.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	KeyListener validaNumericoSDI = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    	String texto = txtSalarioDiarioIntegrado.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	KeyListener validaNumericoPension = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    	String texto = txtPensionAli.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    	String texto = txtInfonavit.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	private String validaCampos(){
		String error="";
		String fechaNull = txtCalendario.getDate()+"";
		String fechaIngresoNull = txtIngreso.getDate()+"";
//		String fechaIngresoImssNull = txtIngresoImss.getDate()+"";
//		String fechaVencimientoLicenciaNull = txtVencimientoLicencia.getDate()+"";
		
		if(txtFolioEmpleado.getText().equals("")) 		error+= "Folio\n";
		if(txtNombre.getText().equals("")) 		error+= "Nombre\n";
		if(txtApPaterno.getText().equals(""))	error+= "Ap Paterno\n";
		if(txtCalle.getText().equals(""))	error+= "Calle\n";
		if(txtColonia.getText().equals(""))	error+= "Colonia\n";
		if(txtPoblacion.getText().equals(""))	error+= "Poblacion\n";
		if(txtTelefono_Familiar.getText().equals(""))	error+= "Telefono Familiar\n";
		if(txtTelefono_Propio.getText().equals(""))	error+= "txtTelefonob Propio\n";
		if(cmbDepartamento.getSelectedItem().equals("Selecciona un Departamento"))	error+= "Departamento\n";
		
		if(cmbEstablecimiento.getSelectedItem().equals("Selecciona un Establecimiento")) error += "Establecimiento\n";
		if(cmbPuesto.getSelectedItem().equals("Selecciona un Puesto")) error += "Puesto\n";
		
		switch(cmbHorarioRotativo.getSelectedIndex()){
		case 0:	if(txtHorario.getText().equals("")) 		error+= "Horario\n"; break;
		case 1: if(txtHorario.getText().equals("")) 		error+= "Horario 2\n";
				if(txtHorario2.getText().equals("")) 		error+= "Horario 3\n";break;
		case 2: if(txtHorario.getText().equals("")) 		error+= "Horario\n";
				if(txtHorario2.getText().equals("")) 		error+= "Horario 2\n";
				if(txtHorario3.getText().equals("")) 		error+= "Horario 3\n";break;
		}
		
		if(cmbSueldo.getSelectedItem().equals("Selecciona un Sueldo")) error += "Sueldo\n";
		if(cmbTipoBancos.getSelectedItem().equals("Selecciona un Banco")) error += "Tipo de Banco\n";
		if(cmbBono.getSelectedItem().equals("Selecciona un Bono")) error += "Bono\n";
		if(cmbPrestamos.getSelectedItem().equals("Selecciona un Rango de Prestamo")) error += "Rango de Prestamo\n";
		if(fechaNull.equals("null"))error+= "Fecha de Nacimiento\n";	
		if(fechaIngresoNull.equals("null"))error += "Fecha de ingreso\n";
//		if(fechaIngresoImssNull.equals("null"))error +="Fecha de ingreso IMSS\n";
//		if(fechaVencimientoLicenciaNull.equals("null"))error += "Fecha de vencimiento de licencia de conducir\n";
		
		return error;
	}
	
	public class MainCamara extends javax.swing.JFrame {
		
		
	    private Dispositivos misDispositivos;
	    String nombre;
	    JButton btnGuardar = new JButton("Guardar");
	    JButton btnSalir = new JButton("Salir");
	    JPanel jPWebCam = new JPanel();
	    JPanel jPanel1 = new JPanel();
	    JScrollPane jScrollPane1 = new JScrollPane();
	    JTextArea txtInfo = new JTextArea();
	    JTextField txtNombre = new JTextField();
	    
	    public MainCamara(String folio) {
	    	nombre=folio;
	        initComponents();
	        misDispositivos= new Dispositivos(this);
	        btnGuardar.setEnabled(false);
	        setLocationRelativeTo(null);
	        ver();
	    }

	    private void initComponents() {
	    	this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/camara_icon&16.png"));
	        this.setTitle("Captura Foto");
	 
	        jPWebCam.setBorder(javax.swing.BorderFactory.createTitledBorder("Wisky!"));
	        jPWebCam.setLayout(new java.awt.BorderLayout());
	        
	        getContentPane().add(jPWebCam, java.awt.BorderLayout.CENTER);

	        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	        jPanel1.setPreferredSize(new java.awt.Dimension(397, 160));

	        txtInfo.setColumns(20);
	        txtInfo.setRows(5);
	        jScrollPane1.setViewportView(txtInfo);
	        
	        btnSalir.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent evt){
	        		misDispositivos.salir();
	        		dispose();
	        	}
	        });
	        btnGuardar.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt){
	            	 misDispositivos.CapturaFoto(nombre);
	            }
	        });
	        
	        addWindowListener( new java.awt.event.WindowAdapter() {
	        	public void windowClosing(java.awt.event.WindowEvent e ) { 
	        		misDispositivos.salir();
	        		dispose();
	        	} 
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	        		jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                .addGap(71, 71, 71)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
	                .addComponent(btnGuardar)
	                .addGap(20, 20, 20)
	                .addComponent(btnSalir)
	                .addGap(10, 10, 10))
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
	                .addContainerGap()));
	        
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnGuardar)
	                    .addComponent(btnSalir))
	                .addContainerGap()));
	      
	        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

	        setSize(new java.awt.Dimension(430, 513));
	    }

	    public void ver(){
	    	btnGuardar.setEnabled(true);
	    	infoDispositivo();
	    	try {
				misDispositivos.MuestraWebCam(jPWebCam,"vfw:Microsoft WDM Image Capture (Win32):0","yuv");
			} catch (CannotRealizeException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    private void infoDispositivo() {
	        txtInfo.setText(misDispositivos.verInfoDispositivos());
	    }
	 
	}
	
	public class Dispositivos {

	    private MainCamara padre;
		private Player player;
		
		public Dispositivos(){
			
		}
		
		public Dispositivos(MainCamara padre){
	        this.padre=padre;
	    }

	   
		@SuppressWarnings("rawtypes")
		public String verInfoDispositivos()
	    {
	      String rpta="";
	      Vector listaDispositivos = null;
	      
	     listaDispositivos = CaptureDeviceManager.getDeviceList();
	     Iterator it = listaDispositivos.iterator();
	      while (it.hasNext())
	      {
	        CaptureDeviceInfo cdi = (CaptureDeviceInfo)it.next();
	        rpta+=cdi.getName()+"\n";
	      }
	      if(rpta.compareTo("")!=0)
	          rpta="Dispositivos detectados:\n\n"+rpta;
	      else
	          rpta="Sin Dispositivos Detectados";
	      
	      return rpta;
	    }
		public void salir(){
			player.close();
		}
		
		@SuppressWarnings("rawtypes")
		public void detectarDispositivos(JMenu dispositivos)
	    {
	      Vector listaDispositivos = null;
	      listaDispositivos = CaptureDeviceManager.getDeviceList();
	      Iterator it = listaDispositivos.iterator();

	      String nombre="";
	      while (it.hasNext())
	      {
	          CaptureDeviceInfo cdi = (CaptureDeviceInfo)it.next();
	          nombre=cdi.getName(); //cdi.getName() --> Obtiene el nombre del Dispositivo Detectado
	          
	          if(nombre.indexOf("Image")!=-1)
	          {
	              JMenu menuFormato=new JMenu(nombre);
	              JMenuFormato tamanios=null;
	              CaptureDeviceInfo dev = CaptureDeviceManager.getDevice(nombre);
	              Format[] cfmts = dev.getFormats();

	              for(int i=0; i<cfmts.length;i++)
	              {
	                  if(cfmts[i].getEncoding().compareTo("yuv")==0)
	                  {tamanios=new JMenuFormato(cfmts[i].getEncoding()+" "+
	                          ((YUVFormat)cfmts[i]).getSize().width+"x"+
	                          ((YUVFormat)cfmts[i]).getSize().height,
	                          ((YUVFormat)cfmts[i]).getSize().width,
	                          ((YUVFormat)cfmts[i]).getSize().height,
	                          padre,
	                          padre.jPWebCam);
	                  }
	                  else if(cfmts[i].getEncoding().compareTo("rgb")==0)
	                  {tamanios=new JMenuFormato(cfmts[i].getEncoding()+" "+
	                          ((RGBFormat)cfmts[i]).getSize().width+"x"+
	                          ((RGBFormat)cfmts[i]).getSize().height,
	                          ((RGBFormat)cfmts[i]).getSize().width,
	                          ((RGBFormat)cfmts[i]).getSize().height,
	                          padre,
	                          padre.jPWebCam);
	                  }
	                  menuFormato.add(tamanios);
	              }
	              dispositivos.add(menuFormato);
	          }
	      }
	    }

		public void MuestraWebCam(JPanel panelCam,String dispositivo,String FormatoColor) throws IOException, CannotRealizeException {
			if(player != null)
	            return;
	        
	        CaptureDeviceInfo dev = CaptureDeviceManager.getDevice(dispositivo);
	        MediaLocator loc = dev.getLocator();
	        try {
	                player = Manager.createRealizedPlayer(loc);
	                System.out.println(player);
	               
	            } catch (IOException ex) {
	            	System.out.println("Ponga la camara 0");
	            } catch (NoPlayerException ex) {
	            	System.out.println("Ponga la camara 1");
	            } catch (CannotRealizeException ex) { 
	            	System.out.println("Ponga la camara 3");
	            }
	          
	    
	        player.start();
	           
	        try {
	        	
	            Thread.sleep(1000);
	        } catch (InterruptedException ex) { }

	        Component comp;

	        if ((comp = player.getVisualComponent())!= null) {
	          panelCam.add(comp,BorderLayout.CENTER);
	          padre.pack();
	        }
	    }
	    
		public void CapturaFoto(String nombre) {
	    	Image img=null;
	        FrameGrabbingControl fgc = (FrameGrabbingControl)
	        player.getControl("javax.media.control.FrameGrabbingControl");
	        Buffer buf = fgc.grabFrame();
	        BufferToImage btoi = new BufferToImage((VideoFormat)buf.getFormat());
	        img = btoi.createImage(buf);

	        if (img != null) {
	            Integer i = new Integer(JFileChooser.APPROVE_OPTION);
	            if (i != null){
	            	File folder = new File(System.getProperty("user.dir")+"/tmp/tmp_update");
	            	folder.mkdirs();
					String imagen = System.getProperty("user.dir")+"/tmp/tmp_update/"+nombre;
					File imagenArch = new File(imagen);
					String formato = "JPG";
					player.close();
					padre.dispose();
					
					try{
						ImageIO.write((RenderedImage) img,formato,imagenArch);
						ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg");
					    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null,"Error al guardar la imagen", "Error!",JOptionPane.ERROR_MESSAGE);
					}
	            }
	        }
	        else
	        {
	            javax.swing.JOptionPane.showMessageDialog(padre, "A ocurrido un error!!");
	        }
	        img=null;
	    }
	}

//	filtro de horario para asignarcelo al un empleado
	public class Filtro_Horario_Empleado extends JDialog
	{
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtNombre = new JTextField();
		JTextField txtFolioHorario = new JTextField();

		DefaultTableModel modelo = new DefaultTableModel(0,2)	{
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(modelo);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		@SuppressWarnings({ "rawtypes" })
		public Filtro_Horario_Empleado()
		{
			
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/nivG.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Filtro Horario"));	
			
			panel.add(getPanelTabla()).setBounds(20,50,800,400);
			panel.add(txtFolioHorario).setBounds(20,20,80,20);
			panel.add(txtNombre).setBounds(100,20,720,20);
			
			cont.add(panel);
			txtNombre.setToolTipText("Filtro");
			
			txtFolioHorario.setDocument(new JTextFieldLimit(9));
			
			trsfiltro = new TableRowSorter(modelo); 
			tabla.setRowSorter(trsfiltro);
			
			txtNombre.addKeyListener(opFiltroNombre);
			txtFolioHorario.addKeyListener(opFiltroFolio);
			
			agregar(tabla);
			
			this.setTitle("Filtro Horario");
			this.setSize(845,500);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
		}
		int x=2;
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		
		    			int fila = tabla.getSelectedRow();
		    			Object folio =  tabla.getValueAt(fila, 0);
		    			Object horario =  tabla.getValueAt(fila, 1);
		    			
		    			
		    			switch(seleccion_de_asignacion_de_Horario1Horario2Horario3){
			    			case 1: txtHorario.setText(horario+"");
		    						lblFolioHorario1.setText(folio+"");
		    				
		    						txtHorario.setToolTipText(horario+"");
		    						rbHorario.doClick();
		    						break;
			    			case 2: txtHorario2.setText(horario+"");
				    				lblFolioHorario2.setText(folio+"");
				    				
					    			txtHorario2.setToolTipText(horario+"");
					    			rbHorario2.setEnabled(true);
					    			break;
			    			case 3: txtHorario3.setText(horario+"");
				    				lblFolioHorario3.setText(folio+"");
				    				
					    			txtHorario3.setToolTipText(horario+"");
					    			rbHorario3.setEnabled(true);
					    			break;
		    			}
		    			dispose();
		        	}
		        }
	        });
	    }
		
		KeyListener opFiltroFolio = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioHorario.getText(), 0));
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
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
	   	private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);

			
			// Creamos las columnas.
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMinWidth(80);
			tabla.getColumnModel().getColumn(0).setMinWidth(80);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
			tabla.getColumnModel().getColumn(1).setMinWidth(720);
			tabla.getColumnModel().getColumn(1).setMaxWidth(720);
			
			TableCellRenderer render = new TableCellRenderer() 
			{ 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
			
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
				return lbl; 
				} 
			}; 
			tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(1).setCellRenderer(render); 

			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery( "  select tb_horarios.folio,tb_horarios.nombre from tb_horarios");
				int folio;
				String nombre;
				
				while (rs.next())
				{ 
					folio= rs.getInt(1);
					nombre= rs.getString(2).trim();
					
				   String [] fila = new String[2];
				   fila[0] = folio+"";
				   fila[1] = nombre;
				   
				   modelo.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
			   
		    return scrol; 
		}
	}

//		setToolTipText en comboBox			---------------------------------------------------------------
//	  class MyComboBoxRenderer extends BasicComboBoxRenderer {
//	    @SuppressWarnings("rawtypes")
//		public Component getListCellRendererComponent(JList list, Object value,
//	        int index, boolean isSelected, boolean cellHasFocus) {
//	      if (isSelected) {
//	        setBackground(list.getSelectionBackground());
//	        setForeground(list.getSelectionForeground());
//	        if (-1 < index) {
//	          list.setToolTipText(tooltips[index]);
//	        }
//	      } else {
//	        setBackground(list.getBackground());
//	        setForeground(list.getForeground());
//	      }
//	      setFont(list.getFont());
//	      setText((value == null) ? "" : value.toString());
//	      return this;
//	    }
//	  }
	
	public class Cat_Filtro_Empleado extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		DefaultTableModel model = new DefaultTableModel(0,9){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolioFiltroEmpleado = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
		@SuppressWarnings("rawtypes")
		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);

		@SuppressWarnings("rawtypes")
		public Cat_Filtro_Empleado(){
			
			this.setModal(true);
			
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			this.setTitle("Filtro de Empleados");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			campo.add(getPanelTabla()).setBounds(15,42,1000,565);
			
			campo.add(txtFolioFiltroEmpleado).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,229,20);
			campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
			
			agregar(tabla);
			
			cont.add(campo);
			
			txtFolioFiltroEmpleado.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			cmbEstablecimientos.addActionListener(opFiltro);
			
			this.setSize(1040,650);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		    			int fila = tabla.getSelectedRow();
		    			Object folio =  tabla.getValueAt(fila, 0).toString().trim();
		    			dispose();
		    			txtFolioEmpleado.setText(folio+"");
		    			btnBuscar.doClick();
		        	}
		        }
	        });
	    }
		
		KeyListener opFiltroFolio = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFiltroEmpleado.getText(), 0));
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
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		ActionListener opFiltro = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(cmbEstablecimientos.getSelectedIndex() != 0){
					trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
				}else{
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
				}
			}
		};
		
	   	private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			int a=2;
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
			
			// Creamos las columnas.
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(50);
			tabla.getColumnModel().getColumn(0).setMinWidth(50);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
			tabla.getColumnModel().getColumn(1).setMaxWidth(230);
			tabla.getColumnModel().getColumn(1).setMinWidth(230);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
			tabla.getColumnModel().getColumn(2).setMaxWidth(150);
			tabla.getColumnModel().getColumn(2).setMinWidth(150);
			tabla.getColumnModel().getColumn(3).setHeaderValue("Puesto");
			tabla.getColumnModel().getColumn(3).setMaxWidth(180);
			tabla.getColumnModel().getColumn(3).setMinWidth(180);
			tabla.getColumnModel().getColumn(4).setHeaderValue("Sueldo");
			tabla.getColumnModel().getColumn(4).setMaxWidth(70);
			tabla.getColumnModel().getColumn(4).setMinWidth(70);
			tabla.getColumnModel().getColumn(5).setHeaderValue("Bono");
			tabla.getColumnModel().getColumn(5).setMaxWidth(70);
			tabla.getColumnModel().getColumn(5).setMinWidth(70);
			tabla.getColumnModel().getColumn(6).setHeaderValue("Status");
			tabla.getColumnModel().getColumn(6).setMaxWidth(120);
			tabla.getColumnModel().getColumn(6).setMinWidth(120);
			tabla.getColumnModel().getColumn(7).setHeaderValue("F Sodas");
			tabla.getColumnModel().getColumn(7).setMaxWidth(50);
			tabla.getColumnModel().getColumn(7).setMinWidth(50);
			tabla.getColumnModel().getColumn(8).setHeaderValue("Gafete");
			tabla.getColumnModel().getColumn(8).setMaxWidth(50);
			tabla.getColumnModel().getColumn(8).setMinWidth(50);
			
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
						case 4: 
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
						case 5: 
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
						case 6: 
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
						case 7: 
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
						case 8: 
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
			
			tabla.getColumnModel().getColumn(a=0).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render);
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery("exec sp_filtro_empleado");
				
				while (rs.next())
				{ 
				   String [] fila = new String[9];
				   fila[0] = rs.getString(1)+"  ";
				   fila[1] = "   "+rs.getString(2);
				   fila[2] = "   "+rs.getString(3).trim();
				   fila[3] = "   "+rs.getString(4).trim();
				   fila[4] = rs.getString(5).trim();
				   fila[5] = rs.getString(6).trim();
				   fila[6] = "   "+rs.getString(7).trim();
				   fila[7] = rs.getString(8).trim();
				   fila[8] = rs.getString(9).trim();
				
				   model.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
			   
		    return scrol; 
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

	
	public class JMenuFormato extends JMenuItem implements ActionListener{

	    private int ancho;
	    private int alto;
	    @SuppressWarnings("unused")
		private JPanel modificable;
	    private MainCamara padre;

	    public JMenuFormato(String etiqueta,int ancho,int alto,MainCamara Padre,JPanel modificable)
	    {
	        super(etiqueta);
	        this.modificable=modificable;
	        this.ancho=ancho;
	        this.alto=alto;
	        this.addActionListener(this);
	        this.padre=Padre;
	    }

	    public void actionPerformed(ActionEvent e) {
	        padre.setSize(ancho, alto+200);
	    }
	}
}