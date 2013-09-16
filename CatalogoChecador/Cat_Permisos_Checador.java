package CatalogoChecador;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import objetos.JTextFieldLimit;

import ObjetoChecador.Obj_Permisos_Checador;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Permisos_Checador extends JFrame {
	
	Container cont  = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JLabel lblFolio = new JLabel("Folio:");
	JLabel lblFolioEmpleado = new JLabel("Folio de Empleado:");
	JLabel lblFecha = new JLabel("Fecha de Permis");
	JLabel lblMotivo = new JLabel("Motivo:");
	
	JLabel lblUsuario = new JLabel("ASIGNAR EL NOMBRE COMPLETO DEL USUARIO DEL SISTEMA");
	JLabel lblEmpleado = new JLabel("ASIGNAR EL NOMBRE COMPLETO DEL EMPLEADO AL BUSCAR EN TB_PERMISOS O AL BUSCA EN FILTRO AQUI");
	
	JTextField txtFolio =new JTextField();
	JTextField txtFolioEmpleado = new JTextField();
	
	JDateChooser txtFechaPermiso = new JDateChooser();
	
	JCheckBox chb_status = new JCheckBox("status");
	
	ButtonGroup grupo = new ButtonGroup();
	
	JCheckBox chbP_trabajarCorrido = new JCheckBox("1.- Permiso Para Trabajar Corrido");
	JCheckBox chbP_salirTemprano = new JCheckBox("2.- Permiso Para Salir Temprano");
	JCheckBox chbP_entrarTarde = new JCheckBox("3.- Permiso Para Entrar Tarde");
	JCheckBox chbP_noAsistir = new JCheckBox("4.- Permiso Para No Asistir (con goce de sueldo)");
	JCheckBox chbP_noAsistir2 = new JCheckBox("5.- Permiso Para No Asistir (sin goce de sueldo)");
	
	JTextArea txaMotivo = new JTextArea();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton(new ImageIcon("Iconos/filter_iconBlue&16.png"));
	JButton btnFiltroEmpleado = new JButton(new ImageIcon("Iconos/users_icon&16.png"));
	JButton btnNuevo = new JButton(new ImageIcon("Iconos/generar_icon&16.png"));
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnQuitar = new JButton("Quitar");
	JButton btnSalir = new JButton("Salir");
	
	int permiso=0;
	
	public void getConstructor(){

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Permisos Checador"));
		this.setTitle("Permisos Checador");
		
		this.txtFechaPermiso.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		btnBuscar.setToolTipText("Buscar Empleado");
		btnFiltro.setToolTipText("Filtro de Empleado");
		btnNuevo.setToolTipText("Crear Permiso");
		
		int y=20;
		
		panel.add(lblUsuario).setBounds(20,y,400,20);
		
		panel.add(lblFolio).setBounds(10,y+=35,30,20);
		panel.add(txtFolio).setBounds(40,y,60,20);
		panel.add(btnBuscar).setBounds(100,y,20,20);
		panel.add(btnFiltro).setBounds(120,y,20,20);
		
		panel.add(lblFolioEmpleado).setBounds(150,y,110,20);
		panel.add(txtFolioEmpleado).setBounds(240,y,60,20);
		panel.add(btnFiltroEmpleado).setBounds(300,y,20,20);
		
		panel.add(txtFechaPermiso).setBounds(340,y,100,20);
		panel.add(chb_status).setBounds(450,y,80,20);
		
		panel.add(lblEmpleado).setBounds(20,y+=35,400,20);
		
		panel.add(chbP_trabajarCorrido).setBounds(20,y+=35,200,20);
		panel.add(chbP_noAsistir).setBounds(240,y,300,20);
		
		panel.add(chbP_salirTemprano).setBounds(20,y+=30,200,20);
		panel.add(chbP_noAsistir2).setBounds(240,y,300,20);
		
		panel.add(chbP_entrarTarde).setBounds(20,y+=30,300,20);
		
		
		panel.add(txaMotivo).setBounds(10,y+=30,500,130);
		
		panel.add(btnNuevo).setBounds(110,y+=150,20,20);
		panel.add(btnGuardar).setBounds(160,y,80,20);
		panel.add(btnQuitar).setBounds(250,y,80,20);
		panel.add(btnLimpiar).setBounds(340,y,80,20);
		panel.add(btnSalir).setBounds(430,y,80,20);
		
		txaMotivo.setLineWrap(true);
		txaMotivo.setDocument(new JTextFieldLimit(400));
		
		grupo.add(chbP_trabajarCorrido);
		grupo.add(chbP_salirTemprano);
		grupo.add(chbP_entrarTarde);
		grupo.add(chbP_noAsistir);
		grupo.add(chbP_noAsistir2);
		
		btnGuardar.addActionListener(guardar);
		btnBuscar.addActionListener(opBuscar);
		btnNuevo.addActionListener(opNuevo);
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnQuitar.addActionListener(opQuitar);
		btnFiltro.addActionListener(opFiltro);
		
//		txtFolio.setEditable(false);
		
		cont.add(panel);
		this.setSize(540,465);
		this.setLocationRelativeTo(null);
	}
	
	public Cat_Permisos_Checador(){
		 getConstructor();
	}
	
	public Cat_Permisos_Checador(String folio){
		 getConstructor();
		 
		 txtFolio.setText(folio);
		 btnBuscar.doClick();
	}
	
	public String ValidaCampos(){
		String error ="";
		if(txtFolio.getText().equals("")) error+= "Folio\n";
		if(txtFolioEmpleado.getText().equals("")) error+= "Empleado\n";
		if(txtFechaPermiso.getDateEditor().equals("")) error+= "Fecha De Permiso\n";
		if(chbP_trabajarCorrido.isSelected()==false && chbP_salirTemprano.isSelected()==false && chbP_entrarTarde.isSelected()==false && chbP_noAsistir.isSelected()==false) error+="Seleccione un Permiso\n";
		if(txaMotivo.getText().equals("")) error+= "Motivo\n";

		return error;
	}
	
	public void permisoChecador(){
		if(chbP_trabajarCorrido.isSelected()){permiso=1;}
		if(chbP_salirTemprano.isSelected()){permiso=2;}
		if(chbP_entrarTarde.isSelected()){permiso=3;}
		if(chbP_noAsistir.isSelected()){permiso=4;}
		if(chbP_noAsistir2.isSelected()){permiso=5;}
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){

			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(ValidaCampos().equals("")){
					Obj_Permisos_Checador Permiso = new Obj_Permisos_Checador().buscar(Integer.parseInt(txtFolio.getText()));
					 permisoChecador();
					if(Permiso.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
						{
							 
							Permiso.setFolio(Integer.parseInt(txtFolio.getText()));
							Permiso.setFolio_empleado(Integer.parseInt(txtFolioEmpleado.getText()));
							Permiso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
							
							Permiso.setTipo_de_permiso(permiso);
							Permiso.setStatus(chb_status.isSelected());
							
							Permiso.setMotivo(txaMotivo.getText().toUpperCase());
							

							if(Permiso.actualizar(Integer.parseInt(txtFolio.getText()))){
									JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
							}else{
								
							}
						}
					}else{
						
						Permiso.setFolio(Integer.parseInt(txtFolio.getText()));
						Permiso.setFolio_empleado(Integer.parseInt(txtFolioEmpleado.getText()));
						Permiso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
						
						Permiso.setTipo_de_permiso(permiso);
						Permiso.setStatus(chb_status.isSelected());
						
						Permiso.setMotivo(txaMotivo.getText().toUpperCase());
						
						if(Permiso.guardar_permiso()){
									JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
								}else{
							JOptionPane.showMessageDialog(null,"El Registro no se a guardado!","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+ValidaCampos(),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
				
			}
		}
	};
	
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				Obj_Permisos_Checador permisoEmp = new Obj_Permisos_Checador().buscar(Integer.parseInt(txtFolio.getText()));
				if(permisoEmp.getFecha().equals("")){
					JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+txtFolio.getText()+"","Error",JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					
					txtFolio.setText(permisoEmp.getFolio()+"");
					txtFolioEmpleado.setText(permisoEmp.getFolio_empleado()+"");
					
					try {
						Date date_fecha = new SimpleDateFormat("dd/MM/yyyy").parse(permisoEmp.getFecha());
						txtFechaPermiso.setDate(date_fecha);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					switch(permisoEmp.getTipo_de_permiso()){
						case 1:chbP_trabajarCorrido.setSelected(true);break;
						case 2:chbP_salirTemprano.setSelected(true);break;
						case 3:chbP_entrarTarde.setSelected(true);break;
						case 4:chbP_noAsistir.setSelected(true);break;
					}
					txaMotivo.setText(permisoEmp.getMotivo());
					txaMotivo.setEditable(false);
					
					chb_status.setSelected(permisoEmp.isStatus());
				}
			}
		}
	};
	
	ActionListener opQuitar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(JOptionPane.showConfirmDialog(null, "¿desea eliminar el registro selecionado?","aviso",JOptionPane.YES_NO_OPTION) == 0){
				
				if(new Obj_Permisos_Checador().buscarYborraPermiso(Integer.parseInt(txtFolio.getText()))){
					JOptionPane.showMessageDialog(null,"Se eliminó exitosamente","Exito", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"No se pudo eliminar el registro","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Filtro_Permisos_Checador().setVisible(true);
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			txtFolio.setText(new Obj_Permisos_Checador().nuevoPermiso()+"");
			
			txtFolio.setEditable(false);
			txaMotivo.setEditable(true);
		}
	};
	
	ActionListener opSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			txtFolio.setText("");
			txtFolioEmpleado.setText("");
			txtFechaPermiso.setDate(null);
			txtFolio.setEditable(true);
			chbP_trabajarCorrido.setSelected(false);
			chbP_salirTemprano.setSelected(false);
			chbP_entrarTarde.setSelected(false);
			chbP_noAsistir.setSelected(false);
			
			txaMotivo.setText("");
		}
	};
	
	
	
	public static void main (String [] arg){
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		Cat_Permisos_Checador thisClass = new Cat_Permisos_Checador();
		thisClass.setVisible(true);

		//utilizacion del AWTUtilities con el metodo opaque
		try {
			   @SuppressWarnings("rawtypes")
			Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
			   @SuppressWarnings("unchecked")
			Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
			   method.invoke(clazz,thisClass , false);
			   } catch (Exception e) 
			   { }	
	}
}
