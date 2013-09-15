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

import objetos.Obj_Nivel_Jerarquico;

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
	
	JCheckBox chbP_trabajarCorrido = new JCheckBox("1.- Permiso Para Trabajar Corrido");
	JCheckBox chbP_salirTemprano = new JCheckBox("2.- Permiso Para Salir Temprano");
	JCheckBox chbP_entrarTarde = new JCheckBox("3.- Permiso Para Entrar Tarde");
	JCheckBox chbP_noAsistir = new JCheckBox("4.- Permiso Para No Asistir");
	
	JTextArea txaMotivo = new JTextArea();
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton(new ImageIcon("Iconos/users_icon&16.png"));
	JButton btnNuevo = new JButton(new ImageIcon("Iconos/generar_icon&16.png"));
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnQuitar = new JButton("Quitar");
	JButton btnSalir = new JButton("Salir");
	
	public Cat_Permisos_Checador(){
		
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
		panel.add(txtFolio).setBounds(50,y,60,20);
		panel.add(btnNuevo).setBounds(110,y,20,20);
		
		panel.add(lblFolioEmpleado).setBounds(150,y,110,20);
		panel.add(txtFolioEmpleado).setBounds(250,y,60,20);
		panel.add(btnBuscar).setBounds(310,y,20,20);
		panel.add(btnFiltro).setBounds(330,y,20,20);
		
		panel.add(txtFechaPermiso).setBounds(390,y,100,20);
		
		panel.add(lblEmpleado).setBounds(20,y+=35,400,20);
		
		panel.add(chbP_trabajarCorrido).setBounds(20,y+=35,300,20);
		panel.add(chbP_salirTemprano).setBounds(20,y+=30,300,20);
		panel.add(chbP_entrarTarde).setBounds(20,y+=30,300,20);
		panel.add(chbP_noAsistir).setBounds(20,y+=30,300,20);
		
		panel.add(txaMotivo).setBounds(10,y+=30,500,130);
		
		panel.add(btnGuardar).setBounds(160,y+=150,80,20);
		panel.add(btnQuitar).setBounds(250,y,80,20);
		panel.add(btnLimpiar).setBounds(340,y,80,20);
		panel.add(btnSalir).setBounds(430,y,80,20);
		
		btnGuardar.addActionListener(guardar);
		btnBuscar.addActionListener(opBuscar);
		btnNuevo.addActionListener(opNuevo);
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnQuitar.addActionListener(opQuitar);
		
		txtFolio.setEditable(false);
		
		cont.add(panel);
		this.setSize(540,465);
		this.setLocationRelativeTo(null);
	}
	
	public String ValidaCampos(){
		String error ="";
		if(txtFolio.getText().equals("")) error+= "Folio\n";
		if(txtFolioEmpleado.getText().equals("")) error+= "Empleado\n";
		if(txtFechaPermiso.getDate().equals("")) error+= "Fecha De Permiso\n";
		if(chbP_trabajarCorrido.isSelected() || chbP_salirTemprano.isSelected() || chbP_entrarTarde.isSelected() || chbP_noAsistir.isSelected()) error+="Seleccione un Permiso\n";
		if(txaMotivo.getText().equals("")) error+= "Motivo\n";

		return error;
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){

			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(ValidaCampos().equals("")){
					Obj_Permisos_Checador Permiso = new Obj_Permisos_Checador().buscar(Integer.parseInt(txtFolio.getText()));
					
					if(Permiso.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
						{
							
							Permiso.setFolio(Integer.parseInt(txtFolio.getText()));
							Permiso.setFolio_empleado(Integer.parseInt(txtFolioEmpleado.getText()));
							Permiso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
							
							Permiso.setP_travajarCorrido(chbP_trabajarCorrido.isSelected());
							Permiso.setP_travajarCorrido(chbP_salirTemprano.isSelected());
							Permiso.setP_travajarCorrido(chbP_entrarTarde.isSelected());
							Permiso.setP_travajarCorrido(chbP_noAsistir.isSelected());
//							Permiso.setP_travajarCorrido(chb_status.isSelected());
							
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
						
						Permiso.setP_travajarCorrido(chbP_trabajarCorrido.isSelected());
						Permiso.setP_travajarCorrido(chbP_salirTemprano.isSelected());
						Permiso.setP_travajarCorrido(chbP_entrarTarde.isSelected());
						Permiso.setP_travajarCorrido(chbP_noAsistir.isSelected());
//						Permiso.setP_travajarCorrido(chb_status.isSelected());
						
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
			if(txtFolioEmpleado.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				Obj_Permisos_Checador permisoEmp = new Obj_Permisos_Checador().buscar(Integer.parseInt(txtFolioEmpleado.getText()));
				if(permisoEmp.getFecha().equals("")){
					JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+txtFolioEmpleado.getText()+"","Error",JOptionPane.WARNING_MESSAGE);
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
					
					chbP_trabajarCorrido.setSelected(permisoEmp.isP_travajarCorrido());
					chbP_salirTemprano.setSelected(permisoEmp.isP_salirTemprano());
					chbP_entrarTarde.setSelected(permisoEmp.isP_entrarTarde());
					chbP_noAsistir.setSelected(permisoEmp.isP_noAsistir());
					
					txaMotivo.setEditable(false);
				}
			}
		}
	};
	
	ActionListener opQuitar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(JOptionPane.showConfirmDialog(null, "¿desea eliminar el puesto dependiente seleccionado?","aviso",JOptionPane.YES_NO_OPTION) == 0){
				
				if(new Obj_Permisos_Checador().buscarYborraPermiso(Integer.parseInt(txtFolio.getText()))){
					JOptionPane.showMessageDialog(null,"Se eliminó exitosamente","Exito", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"No se pudo eliminar el registro","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
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
