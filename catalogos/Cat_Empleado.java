package catalogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
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
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import objetos.JTextFieldLimit;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Tipo_Banco;
import objetos.Obj_Turno;
import reporte.Reporte_de_Empleados_No_Contratables;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Empleado extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JPasswordField txtChecador = new JPasswordField();
	JTextField txtNombre = new JTextField();
	JTextField txtApPaterno = new JTextField();
	JTextField txtApMaterno = new JTextField();
	JTextField txtFecha = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
	JTextField txtPensionAli = new JTextField();
	JTextField txtHorario = new JTextField();
	JTextField txtImss = new JTextField();
	JTextField txtTelefono_Familiar = new JTextField();
	JTextField txtTelefono_Propio = new JTextField();  
	
	JToggleButton btnTrueFoto = new JToggleButton("Para actualizar la foto Presiona aquí !!!");
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings("rawtypes")
	JComboBox cmbPuesto = new JComboBox(puesto);
	
	String turno[] = new Obj_Turno().Combo_Turno();
	@SuppressWarnings("rawtypes")
	JComboBox cmbTurno = new JComboBox(turno);
	
	String dias1[] = {"Selecciona un Día","Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbDescanso = new JComboBox(dias1);
	
	String dias[] = {"Selecciona un Día","Ninguno","Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbDobla = new JComboBox(dias);
	
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
	
	String status[] = {"Vigente","Vacaciones","Incapacidad","Baja","No Contratable"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbStatus = new JComboBox(status);
	
	String activo_inactivo[] = {"Activo","Inactivo"};
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
		
	JButton btnFoto = new JButton();
	JButton btnStatus = new JButton();
	JButton btnExaminar = new JButton("Examinar");
	JButton btnCamara = new JButton(new ImageIcon("Iconos/camara_icon&16.png"));
	JButton btnCumpleaños_del_Mes = new JButton("Cumpleaños del Mes");
	JButton btnIncontratables = new JButton("No contratables");
	
	JTextArea txaObservaciones = new JTextArea(5,5);
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	JDateChooser txtCalendario = new JDateChooser();
	JDateChooser txtIngreso = new JDateChooser();
	
	public String img = "";
	
	public Cat_Empleado() {
		getContenedor();
	}
	
	public void getContenedor(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/user_icon&16.png"));
		this.setTitle("Alta de Empleados");
		
		int x = 40, y=30, ancho=140;
		
		this.txtCalendario.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtIngreso.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		panel.setBorder(BorderFactory.createTitledBorder("Alta de Empleados"));
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(btnBuscar).setBounds(x+ancho+ancho-12,y,32,20);
		panel.add(btnFiltro).setBounds(x+ancho+ancho+20,y,32,20);
		panel.add(btnEditar).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		btnEditar.setVisible(false);
		
		panel.add(btnNuevo).setBounds(x+ancho+ancho+51,y,ancho-49,20);
	
		panel.add(btnFoto).setBounds(x*2+ancho*3-20,y,ancho+95,200);
	
		panel.add(Observasiones).setBounds(x*2+ancho*3-20+ancho+110,y,ancho+90+120,445);
		
		panel.add(btnCumpleaños_del_Mes).setBounds(x*2+ancho*3-20+ancho+110,y+450,130,20);
		panel.add(btnIncontratables).setBounds(x*2+ancho*3-20+ancho+330,y+450,130,20);
		
		panel.add(btnTrueFoto).setBounds(x*2+ancho*3-20, y+205,235,25);
		panel.add(btnExaminar).setBounds(x*2+ancho*3-20, y+235,80,25);
		panel.add(btnCamara).setBounds(x*2+ancho*3+135, y+235,80,25);
		panel.add(btnStatus).setBounds(x*2+ancho*3-20,y+265,ancho+95,205);
	
		panel.add(new JLabel("Clave Checador")).setBounds(x,y+=25,ancho,20);
		panel.add(txtChecador).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Paterno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApPaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Materno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApMaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(btnVerificar).setBounds(x+ancho, y+=25,150,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPuesto).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Turno:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbTurno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Horario:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtHorario).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Descanso:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbDescanso).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Día Dobla:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbDobla).setBounds(x+ancho,y,ancho*2,20);

		panel.add(new JLabel("Sueldo:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbSueldo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Bono:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbBono).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Prestamo:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPrestamos).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Pensión Alimenticia:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtPensionAli).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Infonavit:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtInfonavit).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Tarjeta de Nomina:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtTarjetaNomina).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Tipo de Bancos:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbTipoBancos).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("N° Seguro Social:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtImss).setBounds(x+ancho,y,125,20);
		
		panel.add(cmbActivo_Inactivo).setBounds(x+ancho+135,y,145,20);
		
		panel.add(new JLabel("Telefono Familiar:")).setBounds(480,y,90,20);
		panel.add(txtTelefono_Familiar).setBounds(570,y,145,20);
		
		panel.add(new JLabel("Telefono Propio")).setBounds(480,y+=25,90,20);
		panel.add(txtTelefono_Propio).setBounds(570,y,145,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(chbFuente_Sodas).setBounds(x+ancho+130,y,90,20);
		panel.add(chbGafete).setBounds((x*3)+(ancho*2)+5,y,60,20);
		
		panel.add(new JLabel("Fecha de Nacimiento:")).setBounds(x,y+=25, ancho, 20);
		panel.add(txtCalendario).setBounds(x+ancho,y,125,20);
		
		panel.add(new JLabel("Ingreso:")).setBounds(x+ancho+130,y, ancho, 20);
		panel.add(txtIngreso).setBounds(x+ancho+180,y,100,20);
		
		panel.add(chb_cuadrante_parcial).setBounds(x+ancho+130,y+=25,150,20);
		
		panel.add(new JLabel("Ultima actualización:")).setBounds(x,y,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,125,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=25,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);

		txaObservaciones.setLineWrap(true); 
		txaObservaciones.setWrapStyleWord(true);
		txaObservaciones.setDocument(new JTextFieldLimit(980));
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtChecador.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(20));
		txtApPaterno.setDocument(new JTextFieldLimit(20));
		txtApMaterno.setDocument(new JTextFieldLimit(20));
		txtTarjetaNomina.setDocument(new JTextFieldLimit(19));
		txtImss.setDocument(new JTextFieldLimit(11));
		txtTelefono_Familiar.setDocument(new JTextFieldLimit(10));
		
		cmbTurno.addActionListener(opHorario_Turno);
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

		
		txtTarjetaNomina.addKeyListener(txtlogns);
		btnExaminar.setEnabled(false);
		btnCamara.setEnabled(false);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtChecador.addKeyListener(numerico_action);
		txtInfonavit.addKeyListener(validaNumericoConPunto);
		txtPensionAli.addKeyListener(validaNumericoPension);
		
		cont.add(panel);
		txtHorario.setEditable(false);
		txtFecha.setEditable(false);
		panelEnabledFalse();
		txtFolio.setEditable(true);
		txtTelefono_Propio.setEditable(false);
		
		String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
		ImageIcon tmpIconAux = new ImageIcon(file);
		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		
		String file_status = System.getProperty("user.dir")+"/Iconos/Vigente.png";
		ImageIcon tmpIconAux_status = new ImageIcon(file_status);
		btnStatus.setIcon(new ImageIcon(tmpIconAux_status.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		
		this.setSize(1100,670);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opPresionFoto = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			btnExaminar.setEnabled(true);
			btnCamara.setEnabled(true);
		}
	};
	
	ActionListener opExaminar = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			FileDialog file = new FileDialog(new Frame());
			
			file.setTitle("Selecciona una Imagen");
			file.setMode(FileDialog.LOAD);
			file.setVisible(true);
			
			if(file.getDirectory() != null){

				File foto = new File(file.getDirectory()+file.getFile());
				
				File destino = new File(System.getProperty("user.dir")+"/tmp/tmp.jpg");
				
			    try {
			    	InputStream in = new FileInputStream(foto);
					OutputStream out = new FileOutputStream(destino);
					
				    byte[] buf = new byte[1024];
				    int len;

				    while ((len = in.read(buf)) > 0) {
				    	out.write(buf, 0, len);
				    }
				    
				    in.close();
				    out.close();
					
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
					panelEnabledFalse();
					txtChecador.setEditable(true);
					txtNombre.setEditable(true);
					txtApPaterno.setEditable(true);
					txtApMaterno.setEditable(true);
					
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
	
	ActionListener opHorario_Turno = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Obj_Turno horario = new Obj_Turno().buscar_hora(cmbTurno.getSelectedIndex());
			txtHorario.setText(horario.getHorario());
		}
	};
	
	ActionListener opFoto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().length() == 0){
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
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
				if(re.getFolio() != 0){			
					txtFolio.setText(re.getFolio()+"");
					txtChecador.setText(re.getNo_checador()+"");
					txtNombre.setText(re.getNombre()+"");
					txtApPaterno.setText(re.getAp_paterno()+"");
					txtApMaterno.setText(re.getAp_materno()+"");	
					txtPensionAli.setText(re.getPension_alimenticia()+"");
					
					Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
					cmbEstablecimiento.setSelectedItem(comboNombreEsta.getNombre());
					
					Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
					cmbPuesto.setSelectedItem(comboNombrePues.getPuesto());
					
					Obj_Turno comboNombreTurn = new Obj_Turno().buscar_tur(re.getTurno());
					cmbTurno.setSelectedItem(comboNombreTurn.getNombre());
					
					cmbDescanso.setSelectedIndex(re.getDescanso());
					cmbDobla.setSelectedIndex(re.getDobla());
					cmbSueldo.setSelectedIndex(re.getSueldo());
					
					Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo().buscar(re.getBono());
					cmbBono.setSelectedItem(bono.getBono()+"");

					cmbPrestamos.setSelectedIndex(re.getPrestamo());
					txtInfonavit.setText(re.getInfonavit()+"");	
					txtTarjetaNomina.setText(re.getTargeta_nomina()+"");
					Obj_Tipo_Banco comboNombreBanco = new Obj_Tipo_Banco().buscar_pues(re.getTipo_banco());
					cmbTipoBancos.setSelectedItem(comboNombreBanco.getBanco());
					
					txtImss.setText(re.getImss()+"");
					
					if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
					else{chbFuente_Sodas.setSelected(false);}
					if(re.getGafete() == true){chbGafete.setSelected(true);}
					else{chbGafete.setSelected(false);}
					cmbStatus.setSelectedIndex(re.getStatus()-1);

					try {
						Date date = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_nacimiento());
						Date date_ingreso = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_ingreso());
						txtCalendario.setDate(date);
						txtIngreso.setDate(date_ingreso);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					chb_cuadrante_parcial.setSelected(re.isCuadrante_parcial());
					
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
						
					txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((Date.parse(re.getFecha()))));
					txaObservaciones.setText(re.getObservasiones());
					txtTelefono_Familiar.setText(re.getTelefono_familiar());
					txtTelefono_Propio.setText(re.getTelefono_propio());
					
					ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
				    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
				    
				    cmbActivo_Inactivo.setSelectedIndex(re.getStatus_imss());
			    
				    btnNuevo.setVisible(false);
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();
					
				}
				else{
					JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
					panelEnabledFalse();
					txtFolio.setEditable(true);
					panelLimpiar();
					return;
				}
			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(empleado.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
							empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
							empleado.setNombre(procesa_texto(txtNombre.getText()));
							empleado.setAp_paterno(procesa_texto(txtApPaterno.getText()));
							if(txtApMaterno.getText().length() != 0){
								empleado.setAp_materno(procesa_texto(txtApMaterno.getText()));
							}else{
								empleado.setAp_materno("");
							}

							Obj_Establecimiento comboFolioEsta = new Obj_Establecimiento().buscar_estab(cmbEstablecimiento.getSelectedItem()+"");
							empleado.setEstablecimiento(comboFolioEsta.getFolio());
							
							Obj_Puesto comboFolioPues = new Obj_Puesto().buscar_pues(cmbPuesto.getSelectedItem()+"");
							empleado.setPuesto(comboFolioPues.getFolio());
							
							Obj_Turno comboFolioTurno = new Obj_Turno().buscar_tur(cmbTurno.getSelectedItem()+"");
							empleado.setTurno(comboFolioTurno.getFolio());
							
							empleado.setDescanso(cmbDescanso.getSelectedIndex());
							empleado.setDobla(cmbDobla.getSelectedIndex());
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
							
							if(txtTarjetaNomina.getText().length() != 0){
								empleado.setTargeta_nomina(txtTarjetaNomina.getText());
							}else{
								empleado.setTargeta_nomina("");
							}
							empleado.setTipo_banco(cmbTipoBancos.getSelectedIndex());
							empleado.setImss(txtImss.getText());
							empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
							empleado.setGafete(chbGafete.isSelected());
							empleado.setStatus(cmbStatus.getSelectedIndex()+1);
							empleado.setFecha(txtFecha.getText());
							empleado.setObservasiones(txaObservaciones.getText());
							if(txaObservaciones.getText().length() != 0){
								empleado.setObservasiones(txaObservaciones.getText());
							}else{
								empleado.setObservasiones("");
							}
							
							if(btnTrueFoto.isSelected()){
								empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
							}else{
								empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp.jpg"));
							}
							
							empleado.setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							empleado.setFecha_ingreso(new SimpleDateFormat("dd/MM/yyyy").format(txtIngreso.getDate()));
							empleado.setCuadrante_parcial(chb_cuadrante_parcial.isSelected());
							empleado.setStatus_imss(cmbActivo_Inactivo.getSelectedIndex());
							empleado.setTelefono_familiar(txtTelefono_Familiar.getText()+"");
							
							if(empleado.actualizar(Integer.parseInt(txtFolio.getText()))){
								panelLimpiar();
								panelEnabledFalse();
								txtFolio.setEditable(true);
								txtFolio.requestFocus();
								JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null,"Error al intentar actualizar los datos","Aviso",JOptionPane.WARNING_MESSAGE);
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
						txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
						empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
						empleado.setNombre(procesa_texto(txtNombre.getText()));
						empleado.setAp_paterno(procesa_texto(txtApPaterno.getText()));
						
						if(txtApMaterno.getText().length() != 0){
							empleado.setAp_materno(procesa_texto(txtApMaterno.getText()));
						}else{
							empleado.setAp_materno("");
						}
						
						Obj_Establecimiento comboFolioEsta = new Obj_Establecimiento().buscar_estab(cmbEstablecimiento.getSelectedItem()+"");
						empleado.setEstablecimiento(comboFolioEsta.getFolio());
						Obj_Puesto comboFolioPues = new Obj_Puesto().buscar_pues(cmbPuesto.getSelectedItem()+"");
						empleado.setPuesto(comboFolioPues.getFolio());
						Obj_Turno comboFolioTurno = new Obj_Turno().buscar_tur(cmbTurno.getSelectedItem()+"");
						empleado.setTurno(comboFolioTurno.getFolio());
						empleado.setDescanso(cmbDescanso.getSelectedIndex());
						empleado.setDobla(cmbDobla.getSelectedIndex());
						empleado.setSueldo(cmbSueldo.getSelectedIndex());
						empleado.setBono(cmbBono.getSelectedIndex());
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

						if(txtTarjetaNomina.getText().length() != 0){
							empleado.setTargeta_nomina(txtTarjetaNomina.getText());
						}else{
							empleado.setTargeta_nomina("");
						}
						
						empleado.setTipo_banco(cmbTipoBancos.getSelectedIndex());
						empleado.setImss(txtImss.getText());
						empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
						empleado.setGafete(chbGafete.isSelected());
						empleado.setStatus(cmbStatus.getSelectedIndex()+1);
						empleado.setFecha(txtFecha.getText());
						
						if(txaObservaciones.getText().length() != 0){
							empleado.setObservasiones(txaObservaciones.getText());
						}else{
							empleado.setObservasiones("");
						}
						 
						if(btnTrueFoto.isSelected()){
							empleado.setFoto(new File(System.getProperty("user.dir")+"/tmp/tmp_update/tmp.jpg"));
						}else{
							empleado.setFoto(new File(System.getProperty("user.dir")+"/Iconos/Un.jpg"));
						}
						
						empleado.setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
						empleado.setFecha_ingreso(new SimpleDateFormat("dd/MM/yyyy").format(txtIngreso.getDate()));
						empleado.setCuadrante_parcial(chb_cuadrante_parcial.isSelected());
						empleado.setStatus_imss(cmbActivo_Inactivo.getSelectedIndex());
						empleado.setTelefono_familiar(txtTelefono_Familiar.getText()+"");
						
						if(empleado.guardar()){
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
							JOptionPane.showMessageDialog(null,"El registro se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE);
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
			dispose();
			new Cat_Filtro_Empleado().setVisible(true);
			
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Obj_Empleado empleado = new Obj_Empleado().buscar(Integer.parseInt(txtFolio.getText()));
			if(empleado.getFolio() != 0){
				panelEnabledTrue();
				txtFolio.setEditable(false);
				btnEditar.setVisible(false);
				btnNuevo.setVisible(true);
				btnExaminar.setEnabled(true);
			
			}else{
				JOptionPane.showMessageDialog(null,"El registró que desea actualizar no existe","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				return;
			}
		}		
	};
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtChecador.setEditable(true);
		txtNombre.setEditable(true);
		txtApPaterno.setEditable(true);
		txtApMaterno.setEditable(true);
		txtPensionAli.setEditable(true);
		cmbEstablecimiento.setEnabled(true);
		cmbPuesto.setEnabled(true);
		cmbTurno.setEnabled(true);
		cmbDescanso.setEnabled(true);
		cmbDobla.setEnabled(true);
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
		
	}
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtChecador.setEditable(false);
		txtNombre.setEditable(false);
		txtApPaterno.setEditable(false);
		txtApMaterno.setEditable(false);
		txtPensionAli.setEditable(false);
		cmbEstablecimiento.setEnabled(false);
		cmbPuesto.setEnabled(false);
		cmbTurno.setEnabled(false);
		cmbDescanso.setEnabled(false);
		cmbDobla.setEnabled(false);
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
		
	}
	///boton deshacer
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtChecador.setText("");
		txtNombre.setText("");
		txtApPaterno.setText("");
		txtApMaterno.setText("");
		txtPensionAli.setText("");
		cmbEstablecimiento.setSelectedIndex(0);
		cmbPuesto.setSelectedIndex(0);
		cmbTurno.setSelectedIndex(0);
		cmbDescanso.setSelectedIndex(0);
		cmbDobla.setSelectedIndex(0);
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
	    txtTelefono_Propio.setText("");
	    chb_cuadrante_parcial.setSelected(false);
	    
		String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
		ImageIcon tmpIconAux = new ImageIcon(file);
		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		
	    
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				Obj_Empleado empleado = new Obj_Empleado().buscar_nuevo();
				if(empleado.getFolio() != 0){
					panelLimpiar();
					txtChecador.setEditable(true);
					txtNombre.setEditable(true);
					txtApPaterno.setEditable(true);
					txtApMaterno.setEditable(true);
					txtFolio.setText(empleado.getFolio()+1+"");
					txtFolio.setEditable(false);
					txtChecador.requestFocus();
					txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
					String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
					ImageIcon tmpIconAux = new ImageIcon(file);
					btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
					String file_status = System.getProperty("user.dir")+"/Iconos/Vigente.png";
					ImageIcon tmpIconAux_status = new ImageIcon(file_status);
					btnStatus.setIcon(new ImageIcon(tmpIconAux_status.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
							
				}else{
					panelEnabledTrue();
					txtFolio.setText(1+"");
					txtFolio.setEditable(false);
					txtChecador.requestFocus();
					txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((new Date())));
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
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnEditar.setVisible(false);
			btnNuevo.setVisible(true);
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
	
	@SuppressWarnings("deprecation")
	private String validaCampos(){
		String error="";
		String fechaNull = txtCalendario.getDate()+"";
		String fechaIngresoNull = txtIngreso.getDate()+"";
		
		if(txtFolio.getText().equals("")) 		error+= "Folio\n";
		if(txtChecador.getText().equals("")) 	error+= "Numero Checador\n";
		if(txtNombre.getText().equals("")) 		error+= "Nombre\n";
		if(txtApPaterno.getText().equals(""))	error+= "Ap Paterno\n";
		if(cmbEstablecimiento.getSelectedItem().equals("Selecciona un Establecimiento")) error += "Establecimiento\n";
		if(cmbPuesto.getSelectedItem().equals("Selecciona un Puesto")) error += "Puesto\n";
		if(cmbTurno.getSelectedItem().equals("Selecciona un Turno")) error += "Turno\n";
		if(cmbDescanso.getSelectedItem().equals("Selecciona un Día")) error += "Descanso\n";
		if(cmbDobla.getSelectedItem().equals("Selecciona un Día")) error += "Día Dobla\n";
		if(cmbSueldo.getSelectedItem().equals("Selecciona un Sueldo")) error += "Sueldo\n";
		if(cmbTipoBancos.getSelectedItem().equals("Selecciona un Banco")) error += "Tipo de Banco\n";
		if(cmbBono.getSelectedItem().equals("Selecciona un Bono")) error += "Bono\n";
		if(cmbPrestamos.getSelectedItem().equals("Selecciona un Rango de Prestamo")) error += "Rango de Prestamo\n";
		if(fechaNull.equals("null"))error+= "Fecha de Nacimiento\n";	
		if(fechaIngresoNull.equals("null"))error += "Fecha de ingreso\n";
		
		return error;
	}
	
	@SuppressWarnings("deprecation")
	
	//constructor del filtro
	
	public Cat_Empleado(String algo) {
		
		getContenedor();
		
		btnNuevo.setVisible(false);
		btnEditar.setVisible(true);
		
		Obj_Empleado re = new Obj_Empleado().buscar(Integer.parseInt(algo));
		
		if(re.getFolio() != 0){		
			txtFolio.setText(re.getFolio()+"");
			txtChecador.setText(re.getNo_checador()+"");
			txtNombre.setText(re.getNombre());
			txtApPaterno.setText(re.getAp_paterno());
			txtApMaterno.setText(re.getAp_materno());	
			txtPensionAli.setText(re.getPension_alimenticia()+"");
			
			Obj_Establecimiento comboNombreEsta = new Obj_Establecimiento().buscar_estab(re.getEstablecimiento());
			cmbEstablecimiento.setSelectedItem(comboNombreEsta.getNombre());
			
			Obj_Puesto comboNombrePues = new Obj_Puesto().buscar_pues(re.getPuesto());
			cmbPuesto.setSelectedItem(comboNombrePues.getPuesto());
			
			Obj_Turno comboNombreTurn = new Obj_Turno().buscar_tur(re.getTurno());
			cmbTurno.setSelectedItem(comboNombreTurn.getNombre());
			
			cmbDescanso.setSelectedIndex(re.getDescanso());
			cmbDobla.setSelectedIndex(re.getDobla());
			cmbSueldo.setSelectedIndex(re.getSueldo());
			
			Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo().buscar(re.getBono());
			cmbBono.setSelectedItem(bono.getBono()+"");
			
			cmbPrestamos.setSelectedIndex(re.getPrestamo());

			txtInfonavit.setText(re.getInfonavit()+"");				
			
			txtTarjetaNomina.setText(re.getTargeta_nomina()+"");
			Obj_Tipo_Banco comboNombreBanco = new Obj_Tipo_Banco().buscar_pues(re.getTipo_banco());
			cmbTipoBancos.setSelectedItem(comboNombreBanco.getBanco());
			
			txtImss.setText(re.getImss()+"");
			
			if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
			else{chbFuente_Sodas.setSelected(false);}
			if(re.getGafete() == true){chbGafete.setSelected(true);}
			else{chbGafete.setSelected(false);}
			cmbStatus.setSelectedIndex(re.getStatus()-1);
			txtTelefono_Familiar.setText(re.getTelefono_familiar());
			txtTelefono_Propio.setText(re.getTelefono_propio());
			
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
			
			if(re.getFecha_nacimiento() != null){
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_nacimiento());
					Date date_ingreso = new SimpleDateFormat("dd/MM/yyyy").parse(re.getFecha_ingreso());
					
					txtCalendario.setDate(date);
					txtIngreso.setDate(date_ingreso);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
			chb_cuadrante_parcial.setSelected(re.isCuadrante_parcial());
			
			txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format((Date.parse(re.getFecha()))));
			txaObservaciones.setText(re.getObservasiones());
			
			cmbActivo_Inactivo.setSelectedIndex(re.getStatus_imss());
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
		    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
			
		    
		    btnNuevo.setVisible(false);
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			
		}
		else{
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			String file = System.getProperty("user.dir")+"/Iconos/Un.JPG";
			ImageIcon tmpIconAux = new ImageIcon(file);
			btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		}
		
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Empleado().setVisible(true);
		}catch(Exception e){
			
		}
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