package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import SQL.BuscarTablasModel;
import SQL.Connexion;

import datos.LoadingBar2;

import objetos.JTextFieldLimit;
import objetos.ObjTicket;
import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Alimentacion_Por_Denominacion;
import objetos.Obj_Empleado;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cortes extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JLabel lblFolio_Empleado = new JLabel();
	JLabel lblNombre_Completo = new JLabel();
	JLabel lblPuesto = new JLabel();
	
	JButton btnDeposito = new JButton("dep");
	
	JLabel lblFolio_Corte = new JLabel();
	JLabel lblEstablecimineto = new JLabel();
	
	JTextField txtFechaCorte = new JTextField("");
	JTextField txtAsignacionCorte = new JTextField("");
	JTextField txtCorteSistema = new JTextField("");
	JTextField txtDeposito = new JTextField("");
	JTextField txtEfectivo = new JTextField("");
	
	JLabel lblDiferenciaCorte = new JLabel("");
	
	JTextField txtTiempoAire = new JTextField("");
	JTextField txtReciboLuz = new JTextField("");
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JTextArea txaObservaciones = new JTextArea(4,4);
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	JButton btnEfectivo = new JButton("efe");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnGuardarCorte = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnSalir = new JButton("Salir");
	
	 Border border = LineBorder.createGrayLineBorder();
//	    Icon warnIcon = MetalIconFactory.getTreeComputerIcon();

	JLabel lblFoto = new JLabel();
	
	String Efectivo = "";
	public String img = "";
	String file = "X:\\Empleados\\Un.JPG";
	
	int folio_usuario;
	JLabel lblUsuario = new JLabel("USUARIO: ");
	
	public Cat_Alimentacion_Cortes(int folio, String estab, String folio_corte) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alimentacion Cortes");
		
		txaObservaciones.setBorder(border);
		
		Font font = new Font("Verdana", Font.BOLD, 14);
		lblUsuario.setFont(font);
		
		int x = 30, y=65, ancho=140, x2=530;
		txtCorteSistema.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Alimentacion Cortes"));
		
		panel.add(lblUsuario).setBounds(x2,y,ancho*2+90,20);
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x2,y+=30,ancho,20);
		panel.add(lblFolio_Empleado).setBounds(x2+ancho,y,ancho,20);
		panel.add(btnFiltro).setBounds(x2+ancho+40,y,30,20);
		
		panel.add(new JLabel("Nombre Completo:")).setBounds(x2,y+=30,ancho,20);
		panel.add(lblNombre_Completo).setBounds(x2+ancho,y,ancho+80,20);
		panel.add(new JLabel("Establecimineto:")).setBounds(x2,y+=30,ancho,20);
		panel.add(lblEstablecimineto).setBounds(ancho+x2,y,ancho+80,20);
		panel.add(new JLabel("Puesto:")).setBounds(x2,y+=30,ancho+40,20);
		panel.add(lblPuesto).setBounds(x2+ancho,y,ancho+80,20);

		
		panel.add(lblFoto).setBounds(x+ancho*2+10,40,ancho+55,195);
		
		y=30;
		
		panel.add(new JLabel("Folio Corte:")).setBounds(x,y,ancho,20);
		panel.add(lblFolio_Corte).setBounds(ancho+x,y,ancho*2-150,20);
		panel.add(chStatus).setBounds(x+ancho+70,y,70,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFechaCorte).setBounds(ancho+x,y,ancho-40,20);
		
		panel.add(new JLabel("Asignacion:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtAsignacionCorte).setBounds(x+ancho,y,ancho*2-150,20);
		
		panel.add(new JLabel("Corte del Sistema:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCorteSistema).setBounds(ancho+x,y,ancho*2-150,20);
		
		panel.add(new JLabel("Deposito:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDeposito).setBounds(x+ancho,y,ancho-40,20);
		panel.add(btnDeposito).setBounds(x+ancho*2-40,y,29,20);
		
		panel.add(new JLabel("Efectivo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtEfectivo).setBounds(ancho+x,y,ancho-40,20);
		panel.add(btnEfectivo).setBounds(x+ancho*2-40,y,29,20);
		
		panel.add(new JLabel("Diferencia de corte: ")).setBounds(x,y+=25,ancho,20);
		panel.add(lblDiferenciaCorte).setBounds(x+ancho,y,ancho,20);
		
		panel.add(new JLabel("Tiempo Aire: ")).setBounds(x,y+=25,ancho,20);
		panel.add(txtTiempoAire).setBounds(x+ancho,y,ancho,20);
		panel.add(new JLabel("Recibo de luz: ")).setBounds(x,y+=25,ancho,20);
		panel.add(txtReciboLuz).setBounds(x+ancho,y,ancho,20);
		panel.add(new JLabel("Comentario: ")).setBounds(x,y+=25,100,20);
		panel.add(Observasiones).setBounds(x,y+=20,870,100);
		
		panel.add(btnGuardarCorte).setBounds(x,380,ancho-40,20);
		panel.add(btnCancelar).setBounds(x+100,380,ancho-40,20);
		panel.add(btnSalir).setBounds(x+200,380,ancho-40,20);

		txtAsignacionCorte.setEnabled(true);
		lblEstablecimineto.setText(estab);
		
		btnGuardarCorte.addActionListener(guardar);
		btnCancelar.addActionListener(cancelar);
		btnSalir.addActionListener(salir);
		
		btnFiltro.addActionListener(filtro);
		btnCancelar.setEnabled(false);
		
		txaObservaciones.setLineWrap(true); 
		txaObservaciones.setWrapStyleWord(true);
		txaObservaciones.setDocument(new JTextFieldLimit(500));
		
		btnEfectivo.addActionListener(opAlimentarDenominacion);
		btnDeposito.addActionListener(opAlimentarDeposito);
		txtCorteSistema.addKeyListener(validaNumericoConPunto);
		txtDeposito.addKeyListener(validaNumericoConPunto2);
		
		lblFoto.setBorder(border);
		
//		Blokear funcion de cerrar ventana de windows
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado().buscar(folio);
		
		if(re.getFolio()!=0){
			
			ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp.jpg");
			Icon icono = new ImageIcon(tmpIconAux.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
			lblFoto.setIcon(icono);	
			
			lblFolio_Empleado.setText(re.getFolio()+"");
			lblNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");
		}
		
		Obj_Puesto puesto = new Obj_Puesto().buscar(re.getPuesto());
		lblPuesto.setText(puesto.getPuesto());
		
		lblFolio_Corte.setText(folio_corte);

		CargarUsuario();
		
		chStatus.setSelected(true);
		
		txtFechaCorte.setEditable(false);
		txtCorteSistema.setEditable(false);
		txtDeposito.setEditable(false);
		txtEfectivo.setEditable(false);
		chStatus.setEnabled(false);
		
		this.setSize(940,450);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	public void CargarUsuario()
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
	
	
	
//	btnEfectivo
	ActionListener opAlimentarDenominacion = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validacion()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validacion(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				btnFiltro.setEnabled(false);
				
				Obj_Alimentacion_Cortes folio_corte = new Obj_Alimentacion_Cortes().buscar_folio_corte(lblFolio_Corte.getText());
				
				if(folio_corte.getFolio_corte().equals("")){
					new Cat_Alimentacion_Por_Denominacion().setVisible(true);
				}else{
					 Obj_Alimentacion_Por_Denominacion alimentacion = new Obj_Alimentacion_Por_Denominacion().buscar_folio_corte_modificar(lblFolio_Corte.getText());
					String fecha = (alimentacion.getFecha());
					
					new Cat_Alimentacion_Por_Denominacion_Modificar(fecha).setVisible(true);
				}
			}
		}
	};
	
//	btnDeposito
	ActionListener opAlimentarDeposito = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
				btnFiltro.setEnabled(false);
				
				Obj_Alimentacion_Cortes folio_corte = new Obj_Alimentacion_Cortes().buscar_folio_corte_deposito(lblFolio_Corte.getText());
				
				if(folio_corte.getFolio_corte().equals("")){
					new Cat_Alimentacion_Deposito().setVisible(true);
				}else{
					new Cat_Alimentacion_Deposito_Modificar().setVisible(true);
				}
//			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				try{
					Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
						
						corte.setFolio_corte(lblFolio_Corte.getText());
						corte.setFolio_empleado(Integer.parseInt(lblFolio_Empleado.getText()+""));
						corte.setNombre(lblNombre_Completo.getText()+"");
						corte.setPuesto(lblPuesto.getText()+"");
						corte.setEstablecimiento(lblEstablecimineto.getText()+"");
						corte.setAsignacion(txtAsignacionCorte.getText()+"");
						
						float corteSistema=Float.parseFloat(txtCorteSistema.getText()+"");
						float deposito =Float.parseFloat(txtDeposito.getText()+"");
						float efectivo =Float.parseFloat(txtEfectivo.getText()+"");
						
						corte.setCorte_sistema(corteSistema);
						corte.setDeposito(deposito);
						corte.setEfectivo(efectivo);
						corte.setDiferencia_corte(corteSistema-(deposito+efectivo));
						
						if(txaObservaciones.getText().length()!=0){
							corte.setComentario(txaObservaciones.getText());
						}else{
							corte.setComentario("");
						}
						corte.setFecha(txtFechaCorte.getText()+"");
						
						if(chStatus.isSelected()){
							corte.setStatus(true);
						}else{
							corte.setStatus(false);
						}
						
						corte.setTiempo_aire(Float.parseFloat(txtTiempoAire.getText()));
						corte.setRecibo_luz(Float.parseFloat(txtReciboLuz.getText()));
						
						if(corte.guardar()){
//TICKET--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
							ObjTicket t = new ObjTicket();
							
							//Ticket
							t.setIzagar					("     SUPERMERCADO LA COMPETIDORA S.A DE C.V");
							t.setTalon					("                                  TALON DE CORTE");
							t.setFolio_emp				("  FOLIO EMPLEADO:    " +lblFolio_Empleado.getText());
							t.setEmpleado				("  EMPLEADO:   " +lblNombre_Completo.getText() );
							t.setPuesto					("  PUESTO:   " +lblPuesto.getText());
							t.setFolio_corte			("  FOLIO DE CORTE:       " +lblFolio_Corte.getText());
							t.setEstablecimineto		("  ESTABLECIMIENTO:   " +lblEstablecimineto.getText() );
							t.setFecha					("  FECHA: " + txtFechaCorte.getText());
							t.setAsignacion				("  ASIGNACION:            " +txtAsignacionCorte.getText());
							t.setTabla					("  CORTE DEL SISTEMA    DEPOSITO    EFECTIVO");
							t.setCorte_sistema			("   " + txtCorteSistema.getText());
							t.setDeposito				("    " + txtDeposito.getText());
							t.setEfectivo				(txtEfectivo.getText());
							t.setDiferencia				("  DIFERENCIA DE CORTE:      " + lblDiferenciaCorte.getText());
							t.setEfectivo				(txtEfectivo.getText());
							t.setDiferencia				("  DIFERENCIA DE CORTE:      " + lblDiferenciaCorte.getText());
							t.setTiempo_aire			("  Tiempo Aire:      "+txtTiempoAire.getText());
							t.setResivo_luz				("  Recibo de Luz:      "+txtReciboLuz.getText());
							t.guardar();

							dispose();
							new LoadingBar2().setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar el corte","Error",JOptionPane.WARNING_MESSAGE);
							return;
						}
//FIN DE GUARDAR CORTE-------------------------------------------------------------------------------------
					}catch(Exception ee)
					{
						JOptionPane.showMessageDialog(null,"Ha ocurrido un error\n Verifique los campos ");
						return;
					}
//FIN DE TICKET-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------				
				}	
			}
	};
	
	ActionListener cancelar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
					Obj_Alimentacion_Cortes folio_corte_denominacion = new Obj_Alimentacion_Cortes().buscar_folio_corte(lblFolio_Corte.getText());
					
					if(folio_corte_denominacion.getFolio_corte().equals("")){
						txtFechaCorte.setText("");
						txtEfectivo.setText("");
						lblDiferenciaCorte.setText("");
						btnFiltro.setEnabled(true);
						dispose();
						JOptionPane.showMessageDialog(null, "El registro no existe","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						
						if(folio_corte_denominacion.eliminar(lblFolio_Corte.getText())){
							txtFechaCorte.setText("");
							txtEfectivo.setText("");
							lblDiferenciaCorte.setText("");
							btnFiltro.setEnabled(true);
							btnSalir.setEnabled(true);
							dispose();
							JOptionPane.showMessageDialog(null, "La registro se elimino exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar el registro","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_Cortes().setVisible(true);
			
		}
	};	
	
	public void panelEnabledFalse(){	
		txtCorteSistema.setEditable(false);
	}

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
		    		    	
		    	String texto = txtCorteSistema.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	KeyListener validaNumericoConPunto2 = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){
		    		    	
		    	String texto = txtDeposito.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
				
			}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};

	private String validacion(){
		String error="";
		
		if(txtAsignacionCorte.getText().equals(""))error+= "Asignacion\n";
		if(txtCorteSistema.getText().equals(""))error+= "Corte del Sistema\n";
		if(txtDeposito.getText().equals(""))error+= "Depocito\n";
				
		return error;
	}
	
	private String validaCampos(){
		String error="";
		
		if(txtAsignacionCorte.getText().equals(""))error+= "Asignacion\n";
		if(txtCorteSistema.getText().equals(""))error+= "Corte del Sistema\n";
		if(txtDeposito.getText().equals(""))error+= "Depocito\n";
		if(txtEfectivo.getText().equals(""))error+= "Efectivo\n";
				
		return error;
	}
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}
	

	//guardar denominaciones
	public class Cat_Alimentacion_Por_Denominacion extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtAsignacion = new JTextField("as");
//		JTextField txtNombre_Completo = new JTextField();
//		JButton btn_refrescar= new JButton("Refrescar");
		
		public JToolBar menu_toolbar = new JToolBar();
		JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
		
		JLabel lblEmpleado = new JLabel();
		JDateChooser txtFecha = new JDateChooser();
		JTextField txtTotal = new JTextField();
		
		DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Alimentacion_Por_Denominacion().get_tabla_model(), new String[]{"Folio", "Denominacion","# Denominacion", "Valor", "$ Cantidad"}) {
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
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
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 	case 2 : return false; 
	        	 	case 3 : return false; 
	        	 	case 4 :
	        	 		float suma = 0;
		    			for(int i=0; i<tabla.getRowCount(); i++){
		    				if(tabla_model.getValueAt(i,4).toString().length() == 0){
		    					suma = suma + 0;
		    				}else{
		    					if(isNumeric(tabla_model.getValueAt(i,4).toString().trim())){
			    					suma += (Float.parseFloat(tabla_model.getValueAt(i,4).toString()))*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
								}else{
									JOptionPane.showMessageDialog(null, "La cantidad en el Folio "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
									tabla_model.setValueAt("", i, 4);
								}
		    				}
		    			}
		    			txtTotal.setText(suma+"");
	        	 		return true; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane scroll_tabla = new JScrollPane(tabla);
		
		public Cat_Alimentacion_Por_Denominacion(){
			
			Constructor();
		}
		
		public void Constructor(){
			this.setModal(true);
			this.setTitle("Alimentación de Denominaciones");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
			this.txtFecha.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
			
			lblEmpleado.setForeground(Color.GRAY);
			
			this.panel.add(menu_toolbar).setBounds(0,0,150,25);
			this.panel.add(lblEmpleado).setBounds(30,35,350,20);
			this.panel.add(new JLabel("Fecha: ")).setBounds(420,35,100,20);
			this.panel.add(txtFecha).setBounds(460,35,90,20);
			this.panel.add(txtAsignacion).setBounds(560,35,110,20);
			
			this.panel.add(scroll_tabla).setBounds(20,60,730,420);
			
			this.panel.add(new JLabel("Total de Cantidades:")).setBounds(470,485,100,20);
			this.panel.add(txtTotal).setBounds(580,485,90,20);
			
			this.menu_toolbar.add(btn_guardar);
			this.menu_toolbar.setEnabled(false);
			this.txtAsignacion.setEditable(false);
			this.txtTotal.setEditable(false);
			
			this.init_tabla();
			
			this.cont.add(panel);
			
			this.lblEmpleado.setText(lblNombre_Completo.getText());
			this.btn_guardar.addActionListener(op_guardar);
			this.tabla.addKeyListener(op_key);
			this.addWindowListener(op_limpiar);
			
			this.setSize(780,550);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		WindowListener op_limpiar = new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
					txtEfectivo.setText("");
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		};
		
		KeyListener op_key = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				float suma = 0;
				for(int i=0; i<tabla.getRowCount(); i++){
					
					if(tabla_model.getValueAt(i,4).toString().equals("")){
						suma = suma + 0;
					}else{
						
						if(isNumeric(tabla_model.getValueAt(i,4).toString().trim())){
	    					suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
						}else{
							JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 4);
							return;
						}
					}
				}
				txtTotal.setText(suma+"");
				txtEfectivo.setText(suma+"");
				lblDiferenciaCorte.setText((Float.parseFloat(txtCorteSistema.getText())-(suma+Float.parseFloat(txtDeposito.getText())))+"");

			}
			public void keyPressed(KeyEvent e) {
			}
		};
		
		ActionListener op_guardar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				if(valida_tabla() != ""){
					txtTotal.setText("0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					
						if(txtFecha.getDate()==null){
							JOptionPane.showMessageDialog(null, "La Fecha es Requerida","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}else{
						
						if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.guardar(tabla_guardar())){
								txtFechaCorte.setText(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
								
								if(!txtEfectivo.getText().equals("")){
									lblDiferenciaCorte.setText((Float.parseFloat(txtCorteSistema.getText())-(Float.parseFloat(txtEfectivo.getText())+Float.parseFloat(txtDeposito.getText())))+"");
								}else{
									txtEfectivo.setText("0.0");
									lblDiferenciaCorte.setText(Float.parseFloat(txtCorteSistema.getText())-(Float.parseFloat(txtDeposito.getText()))+"");
								}
								btnCancelar.setEnabled(true);
								btnSalir.setEnabled(false);
								dispose();
								JOptionPane.showMessageDialog(null, "La tabla Denominaciones se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("0.0");
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}else{
							return;
						}
					}
				}
			}
		};
		
		private Object[][] tabla_guardar(){
			Object[][] matriz = new Object[tabla.getRowCount()][5];
			for(int i=0; i<tabla.getRowCount(); i++){
				
				matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
				matriz[i][1] = tabla_model.getValueAt(i, 1).toString().trim();
				matriz[i][2] = tabla_model.getValueAt(i, 2).toString().trim();
				matriz[i][3] = tabla_model.getValueAt(i, 3).toString().trim();
				
				if(tabla_model.getValueAt(i,4).toString().trim().length() == 0){
					matriz[i][4] = Float.parseFloat("0"); 
				}else{
					matriz[i][4] = Float.parseFloat(tabla_model.getValueAt(i,4).toString().trim());
				}
			}
			return matriz;
		}
		
		private String valida_tabla(){
			String error = "";
			for(int i=0; i<tabla.getRowCount(); i++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,4).toString())){
						error += "   La celda de la columna Cantidad no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 4);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			return error;
		}
		
		public void init_tabla(){
			this.tabla.getTableHeader().setReorderingAllowed(false) ;
			
	    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(0).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(290);
	    	this.tabla.getColumnModel().getColumn(1).setMinWidth(290);
	    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(2).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
	    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
	    	this.tabla.getColumnModel().getColumn(4).setMinWidth(100);
	    	
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
					if(table.getSelectedRow() == row){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(186,143,73));
					}
					switch(column){
						case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
						case 2 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 3 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 4 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					}
				return lbl; 
				} 
			}; 

			this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
			
			float suma = 0;
			for(int i=0; i<tabla.getRowCount(); i++){
				if(tabla_model.getValueAt(i,4).toString().length() == 0){
					suma = suma + 0;
				}else{
					suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
				}
			}
			txtTotal.setText(suma+"");
	    }
		
	    private boolean isNumeric(String cadena){
	    	try {
	    		if(cadena.equals("")){
	        		return true;
	    		}else{
	    			Float.parseFloat(cadena);
	        		return true;
	    		}
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	    }

	}
	
	//modificar denominaciones
	public class Cat_Alimentacion_Por_Denominacion_Modificar extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtAsignacion = new JTextField("as");
		
		public JToolBar menu_toolbar = new JToolBar();
		JButton btn_actualizar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
		
		JLabel lblEmpleado = new JLabel();
		JDateChooser txtFecha = new JDateChooser();
		JTextField txtTotal = new JTextField();
		
		DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Alimentacion_Por_Denominacion().get_tabla_model_modificar(lblFolio_Corte.getText()), new String[]{"Folio", "Denominacion","# Denominacion", "Valor", "$ Cantidad"}) {
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
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
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 	case 2 : return false; 
	        	 	case 3 : return false; 
	        	 	case 4 :
	        	 		float suma = 0;
		    			for(int i=0; i<tabla.getRowCount(); i++){
		    				if(tabla_model.getValueAt(i,4).toString().length() == 0){
		    					suma = suma + 0;
		    				}else{
		    					if(isNumeric(tabla_model.getValueAt(i,4).toString().trim())){
			    					suma += (Float.parseFloat(tabla_model.getValueAt(i,4).toString()))*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
								}else{
									JOptionPane.showMessageDialog(null, "La cantidad en el Folio "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
									tabla_model.setValueAt("", i, 4);
								}
		    				}
		    			}
		    			txtTotal.setText(suma+"");
	        	 		return true; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane scroll_tabla = new JScrollPane(tabla);
		
		public Cat_Alimentacion_Por_Denominacion_Modificar(String fecha){
			Constructor();
			
			this.lblEmpleado.setText(lblNombre_Completo.getText());
			this.txtAsignacion.setText(txtAsignacionCorte.getText());
			
			try {
				Date date_fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
				this.txtFecha.setDate(date_fecha);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		public void Constructor(){
			this.setModal(true);
			this.setTitle("Alimentación de Denominaciones");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
			this.txtFecha.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
			
			lblEmpleado.setForeground(Color.GRAY);
			
			this.panel.add(menu_toolbar).setBounds(0,0,250,25);
			this.panel.add(lblEmpleado).setBounds(30,35,350,20);
			this.panel.add(new JLabel("Fecha: ")).setBounds(420,35,100,20);
			this.panel.add(txtFecha).setBounds(460,35,90,20);
			this.panel.add(txtAsignacion).setBounds(560,35,110,20);
			
			this.panel.add(scroll_tabla).setBounds(20,60,730,420);
			
			this.panel.add(new JLabel("Total de Cantidades:")).setBounds(470,485,100,20);
			this.panel.add(txtTotal).setBounds(580,485,90,20);
			
			this.menu_toolbar.add(btn_actualizar);
			this.menu_toolbar.setEnabled(true);
			this.txtAsignacion.setEditable(false);
			this.txtTotal.setEditable(false);
			
			this.init_tabla();
			
			this.cont.add(panel);
			this.btn_actualizar.addActionListener(op_actualizar);
			
			this.tabla.addKeyListener(op_key);
			
			this.setSize(780,550);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		KeyListener op_key = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				float suma = 0;
				for(int i=0; i<tabla.getRowCount(); i++){
					
					if(tabla_model.getValueAt(i,4).toString().equals("")){
						suma = suma + 0;
					}else{
						
						if(isNumeric(tabla_model.getValueAt(i,4).toString().trim())){
	    					suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
						}else{
							JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 4);
							return;
						}
					}
				}
				txtTotal.setText(suma+"");
				txtEfectivo.setText(suma+"");
				lblDiferenciaCorte.setText((Float.parseFloat(txtCorteSistema.getText())-(suma+Float.parseFloat(txtDeposito.getText())))+"");
			}
			public void keyPressed(KeyEvent e) {
			}
		};
		
		ActionListener op_actualizar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				if(valida_tabla() != ""){
					txtTotal.setText("0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					
						if(txtFecha.getDate()==null){
							JOptionPane.showMessageDialog(null, "La Fecha es Requerida","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}else{
						
						if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.actualizar(tabla_guardar())){
								txtFechaCorte.setText(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
								
								if(!txtEfectivo.getText().equals("")){
									lblDiferenciaCorte.setText((Float.parseFloat(txtCorteSistema.getText())-(Float.parseFloat(txtEfectivo.getText())+Float.parseFloat(txtDeposito.getText())))+"");
								}else{
									txtEfectivo.setText("0.0");
									lblDiferenciaCorte.setText(Float.parseFloat(txtDeposito.getText())-(Float.parseFloat(txtDeposito.getText()))+"");
								}
								btnCancelar.setEnabled(true);
								btnSalir.setEnabled(false);
								dispose();
								JOptionPane.showMessageDialog(null, "La tabla Denominaciones se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("0.0");
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar modificar la tabla","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}else{
							return;
						}
					}
				}
			}
		};
		
		private Object[][] tabla_guardar(){
			Object[][] matriz = new Object[tabla.getRowCount()][5];
			for(int i=0; i<tabla.getRowCount(); i++){
				
				matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
				matriz[i][1] = tabla_model.getValueAt(i, 1).toString().trim();
				matriz[i][2] = tabla_model.getValueAt(i, 2).toString().trim();
				matriz[i][3] = tabla_model.getValueAt(i, 3).toString().trim();
				
				if(tabla_model.getValueAt(i,4).toString().trim().length() == 0){
					matriz[i][4] = Float.parseFloat("0"); 
				}else{
					matriz[i][4] = Float.parseFloat(tabla_model.getValueAt(i,4).toString().trim());
				}
			}
			return matriz;
		}
		
		private String valida_tabla(){
			String error = "";
			for(int i=0; i<tabla.getRowCount(); i++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,4).toString())){
						error += "   La celda de la columna Cantidad no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 4);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			return error;
		}
		
		public void init_tabla(){
			this.tabla.getTableHeader().setReorderingAllowed(false) ;
			
	    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(0).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(290);
	    	this.tabla.getColumnModel().getColumn(1).setMinWidth(290);
	    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(2).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
	    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
	    	this.tabla.getColumnModel().getColumn(4).setMinWidth(100);
	    	
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
					if(table.getSelectedRow() == row){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(186,143,73));
					}
					switch(column){
						case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
						case 2 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 3 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 4 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					}
				return lbl; 
				} 
			}; 

			this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(2).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(3).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(4).setCellRenderer(render); 
			
			float suma = 0;
			for(int i=0; i<tabla.getRowCount(); i++){
				if(tabla_model.getValueAt(i,4).toString().length() == 0){
					suma = suma + 0;
				}else{
					suma += Float.parseFloat(tabla_model.getValueAt(i,4).toString())*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
				}
			}
			txtTotal.setText(suma+"");
	    }
		
	    private boolean isNumeric(String cadena){
	    	try {
	    		if(cadena.equals("")){
	        		return true;
	    		}else{
	    			Float.parseFloat(cadena);
	        		return true;
	    		}
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	    }

	}
//	---------------------------------------------------------------------------------------------------------------------------------------------------------
	//guardar deposito
	public class Cat_Alimentacion_Deposito extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		public JToolBar menu_toolbar = new JToolBar();
		JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
		
		JLabel lblEmpleado = new JLabel();
		JTextField txtTotal = new JTextField();
		
		String columnNames[] = { "Moneda", "Cantidad"};
		
		String dataValues[][] ={{ "0.10", ""},
								{ "0.20", ""},
								{ "0.50", ""},
								{ "1.00", ""},
								{ "2.00", ""},
								{ "5.00", ""},
								{ "10.00", ""},
								{ "20.00", ""},
								{ "EFECTIVO EN CAJA", ""}
							};
		
		DefaultTableModel tabla_model = new DefaultTableModel(dataValues, columnNames) {
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
		    	java.lang.Object.class
	         };
		     
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : 
//	        	 		float suma = 0;
//		    			for(int i=0; i<tabla.getRowCount(); i++){
//		    				if(tabla_model.getValueAt(i,1).toString().length() == 0){
//		    					suma = suma + 0;
//		    				}else{
//		    					if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
//		    						suma +=Float.parseFloat(tabla_model.getValueAt(i,1).toString());
//		    					}else{
//		    						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
//				    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
//									}else{
//										JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
//										tabla_model.setValueAt("", i, 1);
//									}
//		    					}
//		    				}
//		    			}
//		    			txtTotal.setText("$  "+suma);
	        	 		return true; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane scroll_tabla = new JScrollPane(tabla);
		
		public Cat_Alimentacion_Deposito(){
			Constructor();
			
			this.lblEmpleado.setText(lblNombre_Completo.getText());
			
		}
		
		public void Constructor(){
			this.setModal(true);
			this.setTitle("Alimentación de Denominaciones");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
			
			lblEmpleado.setForeground(Color.GRAY);
			
			this.panel.add(menu_toolbar).setBounds(0,0,250,25);
			this.panel.add(lblEmpleado).setBounds(30,35,350,20);
			
			this.panel.add(scroll_tabla).setBounds(20,60,400,420);
			
			this.panel.add(new JLabel("Total de Cantidades:")).setBounds(220,485,100,20);
			this.panel.add(txtTotal).setBounds(330,485,90,20);
			
			this.menu_toolbar.add(btn_guardar);
			this.menu_toolbar.setEnabled(true);
			this.txtTotal.setEditable(false);
			
			this.init_tabla();
			
			this.cont.add(panel);
			this.btn_guardar.addActionListener(op_guardar);
			
			this.tabla.addKeyListener(op_key);
			this.addWindowListener(op_limpiar);
			
			this.setSize(450,550);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		WindowListener op_limpiar = new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
					txtDeposito.setText("");
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		};
		
		KeyListener op_key = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				float suma = 0;
				for(int i=0; i<tabla.getRowCount(); i++){
					
					if(tabla_model.getValueAt(i,1).toString().equals("")){
						suma = suma + 0;
					}else{
						if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
    						suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
    					}else{
    						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
		    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
							}else{
								JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
								tabla_model.setValueAt("", i, 1);
							}
    					}
					}
				}
				txtTotal.setText(suma+"");
				txtDeposito.setText(suma+"");
			}
			public void keyPressed(KeyEvent e) {
			}
		};
		
		ActionListener op_guardar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				if(valida_tabla() != ""){
					txtTotal.setText("0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
						
						if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.guardar_deposito(tabla_guardar())){
								
								txtDeposito.setText(txtTotal.getText());
								
								btnCancelar.setEnabled(true);
								btnSalir.setEnabled(false);
								dispose();
								
								JOptionPane.showMessageDialog(null, "La tabla Depodito se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("0.0");
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar modificar la tabla","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}else{
							return;
						}
				}
			}
		};
		
		private Object[][] tabla_guardar(){
			Object[][] matriz = new Object[tabla.getRowCount()][2];
			for(int i=0; i<tabla.getRowCount(); i++){
				
				matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
				
				if(tabla_model.getValueAt(i,1).toString().trim().length() == 0){
					matriz[i][1] = Float.parseFloat("0"); 
				}else{
					matriz[i][1] = Float.parseFloat(tabla_model.getValueAt(i,1).toString().trim());
				}
			}
			return matriz;
		}
		
		private String valida_tabla(){
			String error = "";
			for(int i=0; i<tabla.getRowCount(); i++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,1).toString())){
						error += "   La celda de la columna Cantidad no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 1);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			return error;
		}
		
		public void init_tabla(){
			this.tabla.getTableHeader().setReorderingAllowed(false) ;
			
	    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(0).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(290);
	    	this.tabla.getColumnModel().getColumn(1).setMinWidth(290);
	    	
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
					if(table.getSelectedRow() == row){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(186,143,73));
					}
					switch(column){
						case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					}
				return lbl; 
				} 
			}; 

			this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			
			float suma = 0;
			for(int i=0; i<tabla.getRowCount(); i++){
				if(tabla_model.getValueAt(i,1).toString().length() == 0){
					suma = suma + 0;
				}else{
					if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
						suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
					}else{
						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
	    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
						}else{
							JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 1);
						}
					}				}
			}
			txtTotal.setText(suma+"");
	    }
		
	    private boolean isNumeric(String cadena){
	    	try {
	    		if(cadena.equals("")){
	        		return true;
	    		}else{
	    			Float.parseFloat(cadena);
	        		return true;
	    		}
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	    }

	}
	
//  ---------------------------------------------------------------------------------------------------------------------------------------------------------	
	//modificar deposito
	public class Cat_Alimentacion_Deposito_Modificar extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		public JToolBar menu_toolbar = new JToolBar();
		JButton btn_modificar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
		
		JLabel lblEmpleado = new JLabel();
		JTextField txtTotal = new JTextField();
		
		String columnNames[] = { "Moneda", "Cantidad"};
		
		public Object[][] get_tabla_modificar(String folio_corte){
			return new BuscarTablasModel().tabla_model_alimentacion_deposito_modificar(folio_corte);
		}
		
		DefaultTableModel tabla_model = new DefaultTableModel(this.get_tabla_modificar(lblFolio_Corte.getText()), columnNames) {
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
		    	java.lang.Object.class
	         };
		     
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : 
//	        	 		float suma = 0;
//		    			for(int i=0; i<tabla.getRowCount(); i++){
//		    				if(tabla_model.getValueAt(i,1).toString().length() == 0){
//		    					suma = suma + 0;
//		    				}else{
//		    					System.out.println(tabla_model.getValueAt(i,0).toString());
//		    					System.out.println(tabla_model.getValueAt(i,1).toString());
//		    					
//		    					if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
//		    						suma +=Float.parseFloat(tabla_model.getValueAt(i,1).toString());
//		    					}else{
//		    						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
//				    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
//									}else{
//										JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
//										tabla_model.setValueAt("", i, 1);
//									}
//		    					}
//		    				}
//		    			}
//		    			txtTotal.setText("$  "+suma);
	        	 		return true; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane scroll_tabla = new JScrollPane(tabla);
		
		public Cat_Alimentacion_Deposito_Modificar(){
			Constructor();
			
			this.lblEmpleado.setText(lblNombre_Completo.getText());
			
		}
		
		public void Constructor(){
			this.setModal(true);
			this.setTitle("Alimentación de Denominaciones");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/captura_nomina_icon&16.png"));
			
			lblEmpleado.setForeground(Color.GRAY);
			
			this.panel.add(menu_toolbar).setBounds(0,0,250,25);
			this.panel.add(lblEmpleado).setBounds(30,35,350,20);
			
			this.panel.add(scroll_tabla).setBounds(20,60,400,420);
			
			this.panel.add(new JLabel("Total de Cantidades:")).setBounds(220,485,100,20);
			this.panel.add(txtTotal).setBounds(330,485,90,20);
			
			this.menu_toolbar.add(btn_modificar);
			this.menu_toolbar.setEnabled(true);
			this.txtTotal.setEditable(false);
			
			this.init_tabla();
			
			this.cont.add(panel);
			this.btn_modificar.addActionListener(op_modificar);
			
			this.tabla.addKeyListener(op_key);
			
			this.setSize(450,550);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		KeyListener op_key = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				float suma = 0;
				for(int i=0; i<tabla.getRowCount(); i++){
					
					if(tabla_model.getValueAt(i,1).toString().equals("")){
						suma = suma + 0;
					}else{
						if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
    						suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
    					}else{
    						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
		    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
							}else{
								JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
								tabla_model.setValueAt("", i, 1);
							}
    					}
					}
				}
				txtTotal.setText(suma+"");
				txtDeposito.setText(suma+"");
			}
			public void keyPressed(KeyEvent e) {
			}
		};
		
		ActionListener op_modificar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				if(valida_tabla() != ""){
					txtTotal.setText("0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
						
						if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.actualizar_deposito(tabla_guardar())){
								
								txtDeposito.setText(txtTotal.getText());
								
								btnCancelar.setEnabled(true);
								btnSalir.setEnabled(false);
								dispose();
								
								JOptionPane.showMessageDialog(null, "La tabla Depodito se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("0.0");
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar modificar la tabla","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}else{
							return;
						}
				}
			}
		};
		
		private Object[][] tabla_guardar(){
			Object[][] matriz = new Object[tabla.getRowCount()][2];
			for(int i=0; i<tabla.getRowCount(); i++){
				
				matriz[i][0] = tabla_model.getValueAt(i,0).toString().trim();
				
				if(tabla_model.getValueAt(i,1).toString().trim().length() == 0){
					matriz[i][1] = Float.parseFloat("0"); 
				}else{
					matriz[i][1] = Float.parseFloat(tabla_model.getValueAt(i,1).toString().trim());
				}
			}
			return matriz;
		}
		
		private String valida_tabla(){
			String error = "";
			for(int i=0; i<tabla.getRowCount(); i++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,1).toString())){
						error += "   La celda de la columna Cantidad no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 1);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			return error;
		}
		
		public void init_tabla(){
			this.tabla.getTableHeader().setReorderingAllowed(false) ;
			
	    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(120);
	    	this.tabla.getColumnModel().getColumn(0).setMinWidth(120);		
	    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(290);
	    	this.tabla.getColumnModel().getColumn(1).setMinWidth(290);
	    	
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					JLabel lbl = new JLabel(value == null? "": value.toString());
					if(row%2==0){
							lbl.setOpaque(true); 
							lbl.setBackground(new java.awt.Color(177,177,177));
					} 
					if(table.getSelectedRow() == row){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(186,143,73));
					}
					switch(column){
						case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					}
				return lbl; 
				} 
			}; 

			this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			
			float suma = 0;
			for(int i=0; i<tabla.getRowCount(); i++){
				if(tabla_model.getValueAt(i,1).toString().length() == 0){
					suma = suma + 0;
				}else{
					System.out.println("-"+tabla_model.getValueAt(i,0).toString()+"-");
					if(tabla_model.getValueAt(i,0).toString().equals("EFECTIVO EN CAJA")){
						suma += Float.parseFloat(tabla_model.getValueAt(i,1).toString());
					}else{
						if(isNumeric(tabla_model.getValueAt(i,1).toString().trim())){
	    					suma += Float.parseFloat(tabla_model.getValueAt(i,0).toString())*Float.parseFloat(tabla_model.getValueAt(i,1).toString());
						}else{
							JOptionPane.showMessageDialog(null, "La cantidad en la Moneda "+tabla_model.getValueAt(i,0).toString()+"  están mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 1);
						}
					}				
				}
			}
			txtTotal.setText(suma+"");
	    }
		
	    private boolean isNumeric(String cadena){
	    	try {
	    		if(cadena.equals("")){
	        		return true;
	    		}else{
	    			Float.parseFloat(cadena);
	        		return true;
	    		}
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	    }

	}
	
}
