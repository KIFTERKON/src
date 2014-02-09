package checador;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import ObjetoChecador.Obj_Encargado_De_Solicitudes;
import ObjetoChecador.Obj_Solicitud_De_Empleados;

import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Seleccion_Jefe_De_Operaciones extends JFrame {
	
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
	
	JLabel lblEncargado = new JLabel("Solicito: ");
	JLabel lblNombre_Encargado = new JLabel("edgar eduardo jimenez molina".toUpperCase());
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
	JLabel lblMovimiento = new JLabel();
	JDateChooser txtFechaSolicitada = new JDateChooser();
	JButton btnRegresar = new JButton("Regresar");
	JButton btnGuardar = new JButton("Guardar");
	
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
	public Cat_Seleccion_Jefe_De_Operaciones(){
		setTitle("Solicitud de empleados");
		lblFoto.setBorder(LineBorder.createGrayLineBorder());
		
		this.filtro.setBorder(BorderFactory.createTitledBorder(blackline,"Filtro"));
		this.botones.setBorder(BorderFactory.createTitledBorder(blackline,"Peticiones"));
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
		pBotones();
		
		llenadoBajo();
		
		
		ImageIcon tmpIconDefault = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.JPG");
        Icon iconoDefault = new ImageIcon(tmpIconDefault.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setIcon(iconoDefault);

		txtStatus.setEditable(false);
		txtEmpleado.setEditable(false);
		txtEstablecimiento.setEditable(false);
		txtTelefono_Propio.setEditable(false);
		txtPuesto.setEditable(false);
		txtDescanso.setEditable(false);
		txtDobla.setEditable(false);
		
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
	
	public void pfiltor(){
	
	}
	
	public void pBotones(){
		int y=10;
		
		botones.add(lblEncargado).setBounds(15,10,100,20);
		botones.add(lblNombre_Encargado).setBounds(115,10,350,20);
		
		botones.add(lblFoto).setBounds(630,15,150,150);
		
//		botones.add(lblFolio).setBounds(60,y,80,20);
//		botones.add(txtFolio_Empleado).setBounds(160,y,70,20);

		botones.add(lblEmpleado).setBounds(15,y+=25,90,20);
		botones.add(txtEmpleado).setBounds(115,y,335,20);
		
		botones.add(lblStatus).setBounds(465,y,70,20);
		botones.add(txtStatus).setBounds(505,y,70,20);
		
		botones.add(lblEstablecimiento).setBounds(15,y+=25,150,20);
		botones.add(txtEstablecimiento).setBounds(115,y,150,20);
		
		botones.add(lblTelefono_Propio).setBounds(290,y,70,20);
		botones.add(txtTelefono_Propio).setBounds(350,y,100,20);
		
		botones.add(lblDobla).setBounds(465,y,85,20);
		botones.add(txtDobla).setBounds(505,y,70,20);
		
		botones.add(lblPuesto).setBounds(15,y+=25,90,20);
		botones.add(txtPuesto).setBounds(115,y,220,20);
		
		botones.add(lblDescanso).setBounds(370,y,70,20);
		botones.add(txtDescanso).setBounds(430,y,145,20);
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
		
//		llenado_permisos.add(cmbEstablecimiento).setBounds(80,45,180,20);
//		llenado_permisos.add(cmbPuesto).setBounds(80,45,320,20);
//		llenado_permisos.add(cmbDepartamento).setBounds(80,45,220,20);
//		llenado_permisos.add(cmbTurno).setBounds(80,45,140,20);
//		llenado_permisos.add(cmbDescanso).setBounds(80,45,140,20);
//		llenado_permisos.add(cmbDobla).setBounds(80,45,140,20);
		
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
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
					guardar();
			}
	};
	
	public void guardar(){
		Obj_Solicitud_De_Empleados solicitud = new Obj_Solicitud_De_Empleados();
		
		
		if(solicitud.guardar()){
				JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
		}else{
			JOptionPane.showMessageDialog(null,"El Registro no se a guardo!","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
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
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Seleccion_Jefe_De_Operaciones().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
