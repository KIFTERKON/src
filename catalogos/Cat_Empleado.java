package catalogos;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import camara.MainCamara;


import objetos.JTextFieldLimit;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Turno;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Empleado extends JFrame{

	int forma;
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtChecador = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtApPaterno = new JTextField();
	JTextField txtApMaterno = new JTextField();
	JTextField txtFecha = new JTextField(new Date().toString());
	JTextField txtPensionAli = new JTextField();
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String puesto[] = new Obj_Puesto().Combo_Puesto();
	JComboBox cmbPuesto = new JComboBox(puesto);
	
	String turno[] = new Obj_Turno().Combo_Turno();
	JComboBox cmbTurno = new JComboBox(turno);
	
	String dias1[] = {"Selecciona un Día","Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo"};
	JComboBox cmbDescanso = new JComboBox(dias1);
	
	String dias[] = {"Selecciona un Día","Ninguno","Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo"};
	JComboBox cmbDobla = new JComboBox(dias);
	
	String sueldo[] = new Obj_Sueldo().Combo_Sueldo();
	JComboBox cmbSueldo = new JComboBox(sueldo);
	
	String bono[] = new Obj_Bono_Complemento_Sueldo().Combo_Bono();
	JComboBox cmbBono = new JComboBox(bono);
	
	String rango_prestamo[] = new Obj_Rango_Prestamos().Combo_Prestamos();
	JComboBox cmbPrestamos = new JComboBox(rango_prestamo);
	
	JTextField txtInfonavit = new JTextField();
	
	JCheckBox chbFuente_Sodas = new JCheckBox("Fnt de Sodas");
	JCheckBox chbGafete = new JCheckBox("Gafete");
	
	String status[] = {"Vigente","Vacaciones","Incapacidad","Baja"};
	JComboBox cmbStatus = new JComboBox(status);
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	
	JButton btnFoto = new JButton();
	JButton btnStatus = new JButton();
	JButton btnExaminar = new JButton("Examinar");
	
	JTextArea txaObservaciones = new JTextArea();
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	public String img = "";
	
	public Cat_Empleado() {
		getContenedor();
	}
	
	public void getContenedor(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alta de Empleados");
		
		int x = 40, y=30, ancho=140;
		
		panel.setBorder(BorderFactory.createTitledBorder("Alta de Empleados"));
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(btnBuscar).setBounds(x+ancho+ancho-12,y,32,20);
		panel.add(btnFiltro).setBounds(x+ancho+ancho+20,y,32,20);
		panel.add(btnEditar).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		btnEditar.setVisible(false);
		
		panel.add(btnNuevo).setBounds(x+ancho+ancho+51,y,ancho-49,20);
		
		panel.add(btnFoto).setBounds(x*2+ancho*3-20,y,ancho+95,200);
		panel.add(Observasiones).setBounds(x*2+ancho*3-20+ancho+110,y,ancho+90,445);
		panel.add(btnExaminar).setBounds(x*2+ancho*3-20, y+205,80,25);
		panel.add(btnStatus).setBounds(x*2+ancho*3-20,y+235,ancho+95,210);
		
		panel.add(new JLabel("No Checador:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtChecador).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Paterno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApPaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Apellido Materno:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtApMaterno).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbPuesto).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Turno:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbTurno).setBounds(x+ancho,y,ancho*2,20);
		
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
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-10,20);
		
		panel.add(chbFuente_Sodas).setBounds(x+ancho+130,y,90,20);
		panel.add(chbGafete).setBounds((x*3)+(ancho*2)+5,y,70,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,ancho+50,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=25,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txtChecador.setDocument(new JTextFieldLimit(9));
		txtNombre.setDocument(new JTextFieldLimit(20));
		txtApPaterno.setDocument(new JTextFieldLimit(20));
		txtApMaterno.setDocument(new JTextFieldLimit(20));
		
		btnEditar.addActionListener(editar);
		btnBuscar.addActionListener(buscar);
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		btnFoto.addActionListener(opFoto);
		btnExaminar.addActionListener(opExaminar);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		txtChecador.addKeyListener(numerico_action);
		txtInfonavit.addKeyListener(validaNumericoConPunto);
		
		cont.add(panel);
		
		txtFecha.setEditable(false);
		panelEnabledFalse();
		txtFolio.setEditable(true);
		
		String file = "X:\\Empleados\\Un.JPG";
		ImageIcon tmpIconAux = new ImageIcon(file);
		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		
		this.setSize(980,530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	ActionListener opFoto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().length() != 0){
				String file = "X:\\Empleados\\"+txtFolio.getText()+".JPG";
				img = file;
				File fichero = new File(file);
				if(fichero.exists()){
					ImageIcon tmpIconAux = new ImageIcon(file);
				    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
				}else {
					new MainCamara(txtFolio.getText()).setVisible(true);
				}				
			}else {
				  JOptionPane.showMessageDialog(null, "Cree un nuevo empleado, que contenga un folio.");				
			}
			
		}
	};
	
	ActionListener opExaminar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			FileDialog f = new FileDialog(new Frame());
			f.setTitle("Selecciona una Imagen");
			f.setMode(FileDialog.LOAD);// or FileDialog.SAVE OR LOAD
			f.setVisible(true);
			String ruta = f.getDirectory();
			
			if(ruta == null){
				img = "X:\\Empleados\\Un.JPG";
				return;
			}
			String nombre = f.getFile();
			
			if(nombre != null){
				ruta += nombre;
				
			img = ruta;
			ImageIcon tmpIconAux = new ImageIcon(img);
			btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));			
			
			try{
				FileInputStream fis = new FileInputStream(img);
				img = "X:\\Empleados\\"+txtFolio.getText()+".JPG";
				FileOutputStream fos = new FileOutputStream(img); 
				FileChannel inChannel = fis.getChannel(); 
				FileChannel outChannel = fos.getChannel(); 
				inChannel.transferTo(0, inChannel.size(), outChannel); 
				fis.close(); 
				fos.close();
				}catch (IOException e) {
				System.err.println("Error al Generar Copia");
				}
			
			}
		
		}
	};
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Empleado re = new Obj_Empleado();
				re = re.buscar(Integer.parseInt(txtFolio.getText()));
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
					cmbBono.setSelectedIndex(re.getBono());
					cmbPrestamos.setSelectedIndex(re.getPrestamo());
					txtInfonavit.setText(re.getInfonavit()+"");					
					if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
					else{chbFuente_Sodas.setSelected(false);}
					if(re.getGafete() == true){chbGafete.setSelected(true);}
					else{chbGafete.setSelected(false);}
					cmbStatus.setSelectedIndex(re.getStatus()-1);
					txtFecha.setText(re.getFecha());
					txaObservaciones.setText(re.getObservasiones());
					img = re.getFoto();
					ImageIcon tmpIconAux = new ImageIcon(re.getFoto());
				    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
					
				    btnNuevo.setVisible(false);
					btnEditar.setVisible(true);
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
							txtFecha.setText(new Date().toString());
							empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
							empleado.setNombre(txtNombre.getText());
							empleado.setAp_paterno(txtApPaterno.getText());
							if(txtApMaterno.getText().length() != 0){
								empleado.setAp_materno(txtApMaterno.getText());
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
							if(img != ""){
								empleado.setFoto(img);
							}else{
								empleado.setFoto("X:\\Empleados\\Un.JPG");
							}
							empleado.actualizar(Integer.parseInt(txtFolio.getText()));
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
							JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						}
						
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						txtFecha.setText(new Date().toString());
						empleado.setNo_checador(Integer.parseInt(txtChecador.getText()));
						empleado.setNombre(txtNombre.getText());
						empleado.setAp_paterno(txtApPaterno.getText());
						if(txtApMaterno.getText().length() != 0){
							empleado.setAp_materno(txtApMaterno.getText());
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
						empleado.setFuente_sodas(chbFuente_Sodas.isSelected());
						empleado.setGafete(chbGafete.isSelected());
						empleado.setStatus(cmbStatus.getSelectedIndex()+1);
						empleado.setFecha(txtFecha.getText());
						if(txaObservaciones.getText().length() != 0){
							empleado.setObservasiones(txaObservaciones.getText());
						}else{
							empleado.setObservasiones("");
						}
						if(img != ""){
							empleado.setFoto(img);
						}else{
							empleado.setFoto("X:\\Empleados\\Un.JPG");
						}
						empleado.guardar();	
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registro se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
		}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_Emp().setVisible(true);
			
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
		chbFuente_Sodas.setEnabled(true);
		chbGafete.setEnabled(true);
		cmbStatus.setEnabled(true);
		txaObservaciones.setEditable(true);
		
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
		chbFuente_Sodas.setEnabled(false);
		chbGafete.setEnabled(false);
		cmbStatus.setEnabled(false);
		txaObservaciones.setEditable(false);
		
	}
	
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
		chbFuente_Sodas.setSelected(false);
		chbGafete.setSelected(false);
		cmbStatus.setSelectedIndex(0);
		txaObservaciones.setText("");
	    btnFoto.setIcon(new ImageIcon(""));	
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				Obj_Empleado empleado = new Obj_Empleado().buscar_nuevo();
				if(empleado.getFolio() != 0){
					panelLimpiar();
					panelEnabledTrue();
					txtFolio.setText(empleado.getFolio()+1+"");
					txtFolio.setEditable(false);
					txtChecador.requestFocus();
					txtFecha.setText(new Date().toString());
					String file = "X:\\Empleados\\Un.JPG";
					ImageIcon tmpIconAux = new ImageIcon(file);
					btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
				}else{
					panelEnabledTrue();
					txtFolio.setText(1+"");
					txtFolio.setEditable(false);
					txtChecador.requestFocus();
					txtFecha.setText(new Date().toString());
					String file = "X:\\Empleados\\Un.JPG";
					ImageIcon tmpIconAux = new ImageIcon(file);
					btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
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
		if(cmbBono.getSelectedItem().equals("Selecciona un Bono")) error += "Bono\n";
		if(cmbPrestamos.getSelectedItem().equals("Selecciona un Rango de Prestamo")) error += "Rango de Prestamo\n";
		
		return error;
	}
	
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
			cmbBono.setSelectedIndex(re.getBono());
			cmbPrestamos.setSelectedIndex(re.getPrestamo());
			txtInfonavit.setText(re.getInfonavit()+"");					
			if(re.getFuente_sodas() == true){chbFuente_Sodas.setSelected(true);}
			else{chbFuente_Sodas.setSelected(false);}
			if(re.getGafete() == true){chbGafete.setSelected(true);}
			else{chbGafete.setSelected(false);}
			cmbStatus.setSelectedIndex(re.getStatus()-1);
			txtFecha.setText(re.getFecha());
			txaObservaciones.setText(re.getObservasiones());
			img = re.getFoto();
			ImageIcon tmpIconAux = new ImageIcon(re.getFoto());
		    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
			
		    btnNuevo.setVisible(false);
			btnEditar.setVisible(true);
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			
		}
		else{
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			String file = "X:\\Empleados\\Un.JPG";
			ImageIcon tmpIconAux = new ImageIcon(file);
			btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
		}
		
		panelEnabledFalse();
		txtFolio.setEnabled(true);
		
		this.setSize(980,530);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}
	
}