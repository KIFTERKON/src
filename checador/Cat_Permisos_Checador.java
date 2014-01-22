package checador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import objetos.JTextFieldLimit;
import ObjetoChecador.Obj_Permisos_Checador;
import SQL.Connexion;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Permisos_Checador extends JFrame {
	
	Container cont  = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JLabel lblFolio = new JLabel("Folio:");
	JLabel lblFolioEmpleado = new JLabel("Folio de Empleado:");
	JLabel lblFecha = new JLabel("Fecha de Permiso");
	JLabel lblMotivo = new JLabel("Motivo:");
	
	JLabel lblUsuario = new JLabel("Usuario: ");
	JLabel lblEmpleado = new JLabel("Empleado: ");
	
	JTextField txtFolio =new JTextField();
	JTextField txtFolioEmpleado = new JTextField();
	
	JDateChooser txtFechaPermiso = new JDateChooser();
	
	JCheckBox chb_status = new JCheckBox("status",true);
	
	ButtonGroup grupo = new ButtonGroup();
	
	JCheckBox chbP_trabajarCorrido = new JCheckBox("1.- Permiso Para Trabajar Corrido");
	JCheckBox chbP_salirTemprano = new JCheckBox("2.- Permiso Para Salir Temprano");
	JCheckBox chbP_entrarTarde = new JCheckBox("3.- Permiso Para Entrar Tarde");
	JCheckBox chbP_noAsistir = new JCheckBox("4.- Permiso Para No Asistir (con goce de sueldo)");
	JCheckBox chbP_noAsistir2 = new JCheckBox("5.- Permiso Para No Asistir (sin goce de sueldo)");
	
	JTextArea txaMotivo = new JTextArea();
	JScrollPane Observasiones = new JScrollPane(txaMotivo);
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton(new ImageIcon("Iconos/filter_iconBlue&16.png"));
	JButton btnFiltroEmpleado = new JButton(new ImageIcon("Iconos/users_icon&16.png"));
	JButton btnNuevo = new JButton("Nuevo");//new ImageIcon("Iconos/generar_icon&16.png")
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnEditar = new JButton("Editar");
	JButton btnSalir = new JButton("Salir");
	
	Border border = LineBorder.createGrayLineBorder();
	
//	almacena el numero de permisos que se le asignara al empleado
	int permiso=0;
	
//	almacena el folio del usuario que entro al sistema para mandarsela guardar a la tabla de permisos
	int folio_usuario=0;
	
	public void getConstructor(){

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Permisos Checador"));
		this.setTitle("Permisos Checador");
		txaMotivo.setBorder(border);
		
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
		
		panel.add(lblMotivo).setBounds(10,y+=30,80,20);
		panel.add(Observasiones).setBounds(10,y+=20,500,130);
		
		panel.add(btnNuevo).setBounds(70,y+=150,80,20);
		panel.add(btnGuardar).setBounds(160,y,80,20);
		panel.add(btnEditar).setBounds(250,y,80,20);
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
		btnEditar.addActionListener(opEditar);
		btnFiltro.addActionListener(opFiltro);
		btnFiltroEmpleado.addActionListener(opFiltroEmpleados);	
		
		txtFolio.addKeyListener(buscaAction);
		
		Campos_False();
		txtFolioEmpleado.setEditable(false);
		txtFolio.setEditable(true);
		btnGuardar.setEnabled(false);
		btnEditar.setEnabled(false);
		CargarCajero();
		
		cont.add(panel);
		this.setSize(540,465);
		this.setLocationRelativeTo(null);
	}
	
	public Cat_Permisos_Checador(){
		 getConstructor();
	}
	
	KeyListener buscaAction = new KeyListener() {
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
	
	public String ValidaCampos(){
		String error ="";
		String fechaNull= txtFechaPermiso.getDate()+"";
		
		if(txtFolio.getText().equals("")) 
			error+= "Folio\n";
		
		if(txtFolioEmpleado.getText().equals("")) 
			error+= "Empleado\n";
		
		if(fechaNull.equals("null"))
			error+= "Fecha de Permiso\n";	
		
		if(chbP_trabajarCorrido.isSelected()==false && chbP_salirTemprano.isSelected()==false 
				&& chbP_entrarTarde.isSelected()==false && chbP_noAsistir.isSelected()==false 
				&& chbP_noAsistir2.isSelected()==false) 
			error+="Seleccione un Permiso\n";
		
		if(txaMotivo.getText().equals("")) 
			error+= "Motivo\n";

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
					
					Obj_Permisos_Checador conpararFecha = new Obj_Permisos_Checador().ComparacionFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
					
					if(conpararFecha.getFecha().trim().equals("FECHA_PASADA")){
						JOptionPane.showMessageDialog(null, "No Puede Asignar Permiso A Una Fecha Que Ya Paso", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
							Obj_Permisos_Checador Permiso = new Obj_Permisos_Checador().buscar(Integer.parseInt(txtFolio.getText()));
							 permisoChecador();
							if(Permiso.getFolio() == Integer.parseInt(txtFolio.getText())){
								if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
								{
									
									Permiso.setFolio(Integer.parseInt(txtFolio.getText()));
									Permiso.setFolio_empleado(Integer.parseInt(txtFolioEmpleado.getText()));
									Permiso.setFolio_usuario(folio_usuario);
									Permiso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
									
									Permiso.setTipo_de_permiso(permiso);
									Permiso.setStatus(chb_status.isSelected());
									
									Permiso.setMotivo(txaMotivo.getText().toUpperCase());
									
		
									if(Permiso.actualizar(Integer.parseInt(txtFolio.getText()))){
										btnGuardar.setEnabled(false);
										btnEditar.setEnabled(true);
										txtFolio.setText("");
										txtFolioEmpleado.setText("");
										txtFechaPermiso.setDate(null);
										txaMotivo.setText("");
										
										Campos_False();
										txtFolio.setEditable(true);
										txtFolio.requestFocus();
											JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
											return;
									}else{
										
									}
								}
							}else{
								
								Permiso.setFolio(Integer.parseInt(txtFolio.getText()));
								Permiso.setFolio_empleado(Integer.parseInt(txtFolioEmpleado.getText()));
								Permiso.setFolio_usuario(folio_usuario);
								Permiso.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));
								
								Permiso.setTipo_de_permiso(permiso);
								Permiso.setStatus(chb_status.isSelected());
								
								Permiso.setMotivo(txaMotivo.getText().toUpperCase());
								
								if(Permiso.guardar_permiso()){
									btnGuardar.setEnabled(false);
									btnEditar.setEnabled(true);
									Campos_False();
									txtFolio.setText("");
									txtFolioEmpleado.setText("");
									txtFechaPermiso.setDate(null);
									txaMotivo.setText("");
									txtFolio.setEditable(true);
									txtFolio.requestFocus();
											JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
											return;
										}else{
									JOptionPane.showMessageDialog(null,"El Registro no se a guardado!","Error",JOptionPane.ERROR_MESSAGE);
									return;
								}
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
			
			Campos_False();
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
					
					chb_status.setSelected(permisoEmp.isStatus());
					
					lblEmpleado.setText("Empleado: "+permisoEmp.getNombre_empleado());
					
					btnEditar.setEnabled(true);
					txaMotivo.requestFocus();
				}
			}
		}
	};
	
	ActionListener opEditar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			Obj_Permisos_Checador conpararFecha = new Obj_Permisos_Checador().ComparacionFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaPermiso.getDate()));

			if(conpararFecha.getFecha().trim().equals("FECHA_PASADA")){
				JOptionPane.showMessageDialog(null, "           Solo Puede Editar Un Permiso \n             Con Fecha Actual o Futura", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				Campos_True();
				btnGuardar.setEnabled(true);
				btnEditar.setEnabled(false);
				txaMotivo.requestFocus();
			}
		}
	};
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Filtro_Permisos_Checador().setVisible(true);
		}
	};
	
	ActionListener opFiltroEmpleados = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Filtro_Permiso_Empleado().setVisible(true);
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			txtFolio.setText(new Obj_Permisos_Checador().nuevoPermiso()+"");
			btnBuscar.setEnabled(false);
			btnFiltro.setEnabled(false);
			btnGuardar.setEnabled(true);
			Campos_True();
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
			txaMotivo.setText("");
			
			btnBuscar.setEnabled(true);
			btnFiltro.setEnabled(true);
			Campos_False();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
		}
	};
	
	public void Campos_False()
	{
		txtFolio.setEditable(false);
		btnFiltroEmpleado.setEnabled(false);
		txtFechaPermiso.setEnabled(false);
		chb_status.setEnabled(false);
		txaMotivo.setEditable(false);
		chbP_trabajarCorrido.setEnabled(false);
		chbP_salirTemprano.setEnabled(false);
		chbP_entrarTarde.setEnabled(false);
		chbP_noAsistir.setEnabled(false);
		chbP_noAsistir2.setEnabled(false);
	}
	
	public void Campos_True()
	{
		btnFiltroEmpleado.setEnabled(true);
		txtFechaPermiso.setEnabled(true);
		chb_status.setEnabled(true);
		txaMotivo.setEditable(true);
		chbP_trabajarCorrido.setEnabled(true);
		chbP_salirTemprano.setEnabled(true);
		chbP_entrarTarde.setEnabled(true);
		chbP_noAsistir.setEnabled(true);
		chbP_noAsistir2.setEnabled(true);
	}
	
	public void CargarCajero()
	{
		  File archivo = null;
 	      FileReader fr = null;
 	      BufferedReader br = null;
		 try {
 	         archivo = new File ("Config/users");
 	         fr = new FileReader (archivo);
 	         br = new BufferedReader(fr);
 	         String linea;
 	         
 	        folio_usuario=Integer.parseInt(br.readLine());
 	         while((linea=br.readLine())!=null){
 	        	lblUsuario.setText("Usuario: "+linea);
 	         }
 	      }
 	      catch(Exception e){
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
	
//Filtro Permisos Asignados
public class Filtro_Permisos_Checador extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel model = new DefaultTableModel(0,4){
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
	public Filtro_Permisos_Checador()	{
		this.setTitle("Filtro Permisos");
		txtBuscar.setDocument(new JTextFieldLimit(10));
		
		txtBuscar.addKeyListener(new KeyAdapter() { 
			public void keyReleased(final KeyEvent e) { 
                filtro(); 
            } 
        });
	
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		campo.add(getPanelTabla()).setBounds(10,70,570,450);
		
		agregar(tabla);
		
		campo.add(lblBuscar).setBounds(30,30,70,20);
		campo.add(txtBuscar).setBounds(95,30,215,20);
		
		cont.add(campo);
		
		this.setSize(600,570);
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
	    			
	    			txtFolio.setText(folio+"");
	    			btnBuscar.doClick();
	        	}
	        }
        });
    }
	
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
		tabla.getColumnModel().getColumn(0).setMaxWidth(40);
		tabla.getColumnModel().getColumn(0).setMinWidth(40);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Empleado");
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(1).setMinWidth(230);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Fecha de Permiso");
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Capturo Permiso");
		tabla.getColumnModel().getColumn(3).setMaxWidth(200);
		tabla.getColumnModel().getColumn(3).setMinWidth(200);
		
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
						tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("sp_select_permiso_checador_filtro" );
			
			while (rs.next())
			{ 
			   String [] fila = new String[4];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim();
			   fila[3] = rs.getString(4).trim();
			  
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
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
	
//Filtro Empleado
public class Filtro_Permiso_Empleado extends JFrame{
	
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
	public Filtro_Permiso_Empleado()	{
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
	    			String nombre =  tabla.getValueAt(fila, 1).toString().trim();
	    			dispose();
	    			
	    			txtFolioEmpleado.setText(folio);
	    			lblEmpleado.setText(nombre);
	        	}
	        }
        });
    }
	
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
						tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("sp_select_permiso_checador_filtro_empleados" );
			
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
public static void main(String [] args){
	try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Cat_Permisos_Checador().setVisible(true);
	}catch(Exception e){
		System.err.println("Error :"+ e.getMessage());
	}
}
}
