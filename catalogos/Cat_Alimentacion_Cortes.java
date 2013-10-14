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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.lang.reflect.Method;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

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
	
	JTextField txtTiempoAire = new JTextField();
	JTextField txtReciboLuz = new JTextField();

	//	JButton btnDeposito = new JButton("dep");
	
	JLabel lblFolio_Corte = new JLabel();
	JLabel lblEstablecimineto = new JLabel();
	JTextField txtFechaCorte = new JTextField();
	
	JTextField txtAsignacionCorte = new JTextField("dftytsf");
	JTextField txtDeposito = new JTextField("105");
	JTextField txtCorteSistema = new JTextField("135");
	JTextField txtEfectivo = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JTextArea txaObservaciones = new JTextArea(4,4);
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	JButton btnEfectivo = new JButton("efe");
	
	JLabel lblDiferenciaCorte = new JLabel();
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnGuardarCorte = new JButton("Guardar");
	
	 Border border = LineBorder.createGrayLineBorder();
	    Icon warnIcon = MetalIconFactory.getTreeComputerIcon();

	JLabel lblFoto = new JLabel();
	
	String Efectivo = "";
//	public String img = "";
//	String file = "X:\\Empleados\\Un.JPG";
	
	int folio_usuario;
	JLabel lblUsuario = new JLabel("USUARIO: ");
	
	public Cat_Alimentacion_Cortes(int folio, String folio_corte) {
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

//		panel.add(btnDeposito).setBounds(x+ancho*2-40,y,29,20);
		
		panel.add(lblFoto).setBounds(x+ancho*2+10,40,ancho+55,195);
		
		y=30;
		
		panel.add(new JLabel("Folio Corte:")).setBounds(x,y,ancho,20);
		panel.add(lblFolio_Corte).setBounds(ancho+x,y,ancho*2-150,20);
		panel.add(chStatus).setBounds(x+ancho+70,y,70,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFechaCorte).setBounds(ancho+x,y,ancho-40,20);
		
		panel.add(new JLabel("Asignacion:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtAsignacionCorte).setBounds(x+ancho,y,ancho*2-150,20);
		panel.add(new JLabel("Deposito:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDeposito).setBounds(x+ancho,y,ancho-40,20);
		
		panel.add(new JLabel("Corte del Sistema:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCorteSistema).setBounds(ancho+x,y,ancho*2-150,20);
		panel.add(new JLabel("Efectivo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtEfectivo).setBounds(ancho+x,y,ancho-40,20);
		panel.add(btnEfectivo).setBounds(x+ancho*2-40,y,29,20);
		panel.add(new JLabel("Diferencia de corte: ")).setBounds(x,y+=25,ancho,20);
		panel.add(lblDiferenciaCorte).setBounds(x+ancho,y,ancho,20);
		
		panel.add(new JLabel("Tiempo Aire: ")).setBounds(x,y+=25,ancho,20);
		panel.add(txtTiempoAire).setBounds(x+ancho,y,ancho,20);
		panel.add(new JLabel("Recibo de luz: ")).setBounds(x,y+=25,ancho,20);
		panel.add(txtReciboLuz).setBounds(x+ancho,y,ancho,20);
		
		panel.add(txaObservaciones).setBounds(x,y+=35,870,165);
		
		panel.add(btnGuardarCorte).setBounds(x,525,ancho-20,20);

		txtAsignacionCorte.setEnabled(true);
		lblEstablecimineto.setText(folio_corte);
		
		btnGuardarCorte.addActionListener(guardar);
		
		btnFiltro.addActionListener(filtro);
		
		txaObservaciones.setLineWrap(true); 
		txaObservaciones.setWrapStyleWord(true);
		txaObservaciones.setDocument(new JTextFieldLimit(580));
		
		btnEfectivo.addActionListener(opAlimentarDenominacion);
		txtCorteSistema.addKeyListener(validaNumericoConPunto);
		txtDeposito.addKeyListener(validaNumericoConPunto2);
		
		lblFoto.setBorder(border);
		
		String file = "X:\\Empleados\\Un.JPG";
		ImageIcon tmpIconAux = new ImageIcon(file);
		lblFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		Obj_Puesto puesto = new Obj_Puesto();
		
		CargarUsuario();
		
		re = re.buscar(folio);
		puesto= puesto.buscar(re.getPuesto());
//		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes().buscar_nuevo();

		lblFolio_Empleado.setText(re.getFolio()+"");
		lblNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");
		lblPuesto.setText(puesto.getPuesto());
		lblFolio_Corte.setText(folio_corte);

		chStatus.setSelected(true);
		
		txtFechaCorte.setEditable(false);
		txtEfectivo.setEditable(false);
		chStatus.setEnabled(false);
		
		this.setSize(940,630);
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
	
	ActionListener opAlimentarDenominacion = new ActionListener(){
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e){
			
			btnFiltro.setEnabled(false);
			String asignacion=txtAsignacionCorte.getText();
			int folio_emp = Integer.parseInt(lblFolio_Empleado.getText());
			
	
			
			if(validacion()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validacion(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				Obj_Alimentacion_Cortes folio_corte = new Obj_Alimentacion_Cortes().buscar_folio_corte(lblFolio_Corte.getText());
				
				if(folio_corte.getFolio_corte().equals("")){
					new Cat_Alimentacion_Por_Denominacion().setVisible(true);
				}else{
					new Cat_Alimentacion_Por_Denominacion_Modificar().setVisible(true);
				}
				
				
				
				
				
				
				
				
				
//				if(txtEfectivo.getText().equals("")){
//					
//					new Cat_Alimentacion_Por_Denominacion().setVisible(true);
//				}else{
//						Efectivo = txtEfectivo.getText()+"";
//						new Cat_Alimentacion_Por_Denominacion2(asignacion,folio_emp).setVisible(true);
//					}
			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
//TICKET--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
				try{

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
					t.guardar();

					
					new LoadingBar2().setVisible(true);
					
//GUARDAR CORTE--------------------------------------------------------------------------------------------	
					
					Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
						
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
						corte.setStatus(chStatus.isSelected());
						corte.guardar();
						dispose();
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
		
		JLabel lblEmpleado = new JLabel("MARCO ANTONIO BODART GUZMAN");
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
		    					suma += (Float.parseFloat(tabla_model.getValueAt(i,4).toString()))*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
		    				}
		    			}
		    			txtTotal.setText("$  "+suma);
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
			this.setTitle("Alimentaci�n de Denominaciones");
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
			this.btn_guardar.addActionListener(op_guardar);
			
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
							JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  est�n mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 4);
							return;
						}
					}
				}
				txtTotal.setText("$  "+suma);
				txtEfectivo.setText(suma+"");
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
					txtTotal.setText("$  0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas est�n mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					
						if(txtFecha.getDate()==null){
							JOptionPane.showMessageDialog(null, "La Fecha es Requerida","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}else{
						
						if(JOptionPane.showConfirmDialog(null, "�Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.guardar(tabla_guardar())){
								txtFechaCorte.setText(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
								lblDiferenciaCorte.setText((Float.parseFloat(txtEfectivo.getText())-(Float.parseFloat(txtCorteSistema.getText())+Float.parseFloat(txtDeposito.getText())))+"");
								JOptionPane.showMessageDialog(null, "La tabla Denominaciones se guard� exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("$  0.0");
								JOptionPane.showMessageDialog(null, "Ocurri� un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
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
						error += "   La celda de la columna Cantidad no es un n�mero en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 4);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor num�rico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
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
			txtTotal.setText("$  "+suma);
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
//		JTextField txtNombre_Completo = new JTextField();
//		JButton btn_refrescar= new JButton("Refrescar");
		
		public JToolBar menu_toolbar = new JToolBar();
		JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
		
		JLabel lblEmpleado = new JLabel("MARCO ANTONIO BODART GUZMAN");
		JDateChooser txtFecha = new JDateChooser();
		JTextField txtTotal = new JTextField();
		
		DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Alimentacion_Por_Denominacion().get_tabla_model_modificar(), new String[]{"Folio", "Denominacion","# Denominacion", "Valor", "$ Cantidad"}) {
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
		    					suma += (Float.parseFloat(tabla_model.getValueAt(i,4).toString()))*(Float.parseFloat(tabla_model.getValueAt(i,2).toString())*Float.parseFloat(tabla_model.getValueAt(i,3).toString()));
		    				}
		    			}
		    			txtTotal.setText("$  "+suma);
	        	 		return true; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane scroll_tabla = new JScrollPane(tabla);
		
		public Cat_Alimentacion_Por_Denominacion_Modificar(){
			
			Constructor();
		}
		
		public void Constructor(){
			this.setModal(true);
			this.setTitle("Alimentaci�n de Denominaciones");
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
			this.btn_guardar.addActionListener(op_guardar);
			
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
							JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla_model.getValueAt(i,0).toString()+"  est�n mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla_model.setValueAt("", i, 4);
							return;
						}
					}
				}
				txtTotal.setText("$  "+suma);
				txtEfectivo.setText(suma+"");
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
					txtTotal.setText("$  0.0");
					JOptionPane.showMessageDialog(null, "Las siguientes celdas est�n mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					
						if(txtFecha.getDate()==null){
							JOptionPane.showMessageDialog(null, "La Fecha es Requerida","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}else{
						
						if(JOptionPane.showConfirmDialog(null, "�Desea guardar la lista de Denominaciones?") == 0){
							Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
							
							Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
							Alim_Denom.setEmpleado(lblEmpleado.getText());
							Alim_Denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
							Alim_Denom.setEstablecimiento(lblFolio_Corte.getText());

							if(Alim_Denom.guardar(tabla_guardar())){
								txtFechaCorte.setText(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
								lblDiferenciaCorte.setText((Float.parseFloat(txtEfectivo.getText())-(Float.parseFloat(txtCorteSistema.getText())+Float.parseFloat(txtDeposito.getText())))+"");
								JOptionPane.showMessageDialog(null, "La tabla Denominaciones se guard� exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								txtTotal.setText("$  0.0");
								JOptionPane.showMessageDialog(null, "Ocurri� un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
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
						error += "   La celda de la columna Cantidad no es un n�mero en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
						tabla_model.setValueAt("",i, 4);
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor num�rico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
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
			txtTotal.setText("$  "+suma);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	JDateChooser txtCalendario = new JDateChooser();
	public class Cat_Alimentacion_Por_Denominacion2 extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		@SuppressWarnings("rawtypes")
		TableRowSorter trsfiltro;
		
		JTextField txtAsignacion = new JTextField();

		JButton btnTotal = new JButton("TOTAL:");
		JLabel lblEmpleadoId = new JLabel("");
		JLabel lblEmpleado = new JLabel("");
		JTextField txtTotal = new JTextField("");
		
		boolean bandera = false;
		
		Object[][] Matriz ;
	
		DefaultTableModel model = new DefaultTableModel(0, 4){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
		    	java.lang.Object.class, 
		    	java.lang.Float.class, 
		    	java.lang.Float.class 
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
	        	 	case 3 : return true;
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla = new JTable(model);
	    JScrollPane scroll = new JScrollPane(tabla);
		
	    JToolBar menu = new JToolBar();
		JButton btnModificar = new JButton(new ImageIcon("imagen/Guardar.png"));
		
		public Cat_Alimentacion_Por_Denominacion2(String asignacion,int folio_emp){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
			this.setTitle("Alimentacion por Denominacion");
			
			panel.add(new JLabel("Asignacion: ")).setBounds(20,40,100,20);
			panel.add(txtAsignacion).setBounds(20,60,100,20);
			panel.add(new JLabel("Fecha: ")).setBounds(20,90,100,20);
			panel.add(txtCalendario).setBounds(20,110,100,20);

			panel.add(lblEmpleadoId).setBounds(140, 23, 60, 20);
			panel.add(lblEmpleado).setBounds(200, 23, 280, 20);
			panel.add(txtTotal).setBounds(20, 230, 100, 20);
			panel.add(scroll).setBounds(140,40,425,240);
			
			for(int i=0; i<tabla.getColumnCount(); i++){
				this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
			}
			
			txtTotal.setEditable(false);
			this.tabla.addKeyListener(op_key);
			menu.add(btnModificar);
			menu.setBounds(0,0,150,25);
			panel.add(menu);
			cont.add(panel);

			tabla.addKeyListener(validaNumericoConPunto);
			
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(72);
			tabla.getColumnModel().getColumn(0).setMinWidth(72);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Denominacion");
			tabla.getColumnModel().getColumn(1).setMaxWidth(220);
			tabla.getColumnModel().getColumn(1).setMinWidth(220);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Valor");
			tabla.getColumnModel().getColumn(2).setMaxWidth(50);
			tabla.getColumnModel().getColumn(2).setMinWidth(50);
			tabla.getColumnModel().getColumn(3).setHeaderValue("$ Cantidad");
			tabla.getColumnModel().getColumn(3).setMaxWidth(80);
			tabla.getColumnModel().getColumn(3).setMinWidth(80);

			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
			btnModificar.addActionListener(opModificar);
			
			this.setModal(true);
			this.setSize(585, 320);
			this.setLocationRelativeTo(null);
			
			txtAsignacion.setText(asignacion);
			
				Obj_Alimentacion_Denominacion denom = new Obj_Alimentacion_Denominacion();
//				denom=denom.buscar(asignacion);
				
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(denom.getFecha());
					txtCalendario.setDate(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				lblEmpleadoId.setText(folio_emp+"");
				
				Obj_Empleado re = new Obj_Empleado();
				re=re.buscar(folio_emp);
				lblEmpleadoId.setText(folio_emp+"");
				lblEmpleado.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
				
				Object[][] TablaAux = getTabla2(asignacion);
				Object[] fila= new Object[tabla.getColumnCount()]; 
				for(int i=0; i<TablaAux.length; i++){
					model.addRow(fila); 
					for(int j=0; j<4; j++){
						model.setValueAt(TablaAux[i][j]+"", i,j);
					}
				}
		}
		
			
		//TOTAL DE ALIMENTACION--------------------------------------------------------------------	
		float suma;
		KeyListener op_key = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				 suma = 0;
				for(int i=0; i<tabla.getRowCount(); i++){
					
					if(tabla.getValueAt(i,3).toString().equals("")){
						suma = suma + 0;
					}else{
						
						if(isNumeric(tabla.getValueAt(i,3).toString().trim())){
							suma = suma + (Float.parseFloat(tabla.getValueAt(i,3).toString().trim())*Float.parseFloat(tabla.getValueAt(i,2).toString().trim()));
						}else{
							JOptionPane.showMessageDialog(null, "La nomina en el establecimiento "+tabla.getValueAt(i,0).toString()+"  est�n mal en su formato:\n","Error",JOptionPane.ERROR_MESSAGE);
							tabla.setValueAt("", i, 3);
							return;
							
						}
					}
				}
				txtTotal.setText("$  "+suma);
			}
			public void keyPressed(KeyEvent e) {
			}
		};
		
	    private boolean isNumeric(String cadena){
	    	try {
	    			Float.parseFloat(cadena);
	        		return true;
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	    }
			
		ActionListener opModificar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				modificar();
			}
		};

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void modificar(){
			Vector miVector = new Vector();
			
			String fechaNull = txtCalendario.getDate()+"";
			
			if(fechaNull.equals("null")){
					JOptionPane.showMessageDialog(null, "Ingrese Fecha!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				if(txtTotal.getText()==""){
					JOptionPane.showMessageDialog(null, "Verifique Total de Alimentacion!","Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					
//					Obj_Alimentacion_Denominacion denom = new Obj_Alimentacion_Denominacion().buscar(txtAsignacion.getText());
					
						if(JOptionPane.showConfirmDialog(null, "El registro existe, �desea actualizarlo?") == 0){
							if(validaCampos()!="") {
									JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
									return;
							}else{
							
									for(int i=0; i<model.getRowCount(); i++){
										for(int j=0; j<model.getColumnCount(); j++){
											model.isCellEditable(i,j);
											miVector.add(model.getValueAt(i,j).toString());
										}
		
//										txtFecha1.setText(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
//										denom.setAsignacion(txtAsignacion.getText().trim());
//										denom.setFolio_empleado(Integer.parseInt(lblEmpleadoId.getText()));
//										denom.setFolio_denominacion(Integer.parseInt(miVector.get(0).toString().trim()));
//										denom.setDenominacion(miVector.get(1).toString().trim());
//										denom.setValor(Float.parseFloat(miVector.get(2).toString().trim()));
//										if(miVector.get(3) != ""){
//											denom.setCantidad(Float.parseFloat(miVector.get(3).toString().trim()));
//										}else{
//											miVector.set(3,0);
//											denom.setCantidad(Integer.parseInt(miVector.get(3).toString().trim()));
//										}
//										denom.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
//										denom.actualizar(txtAsignacion.getText(),Integer.parseInt(miVector.get(0).toString().trim()));
										miVector.clear();
									}
									
										txtEfectivo.setText(suma+"");
							}
							JOptionPane.showMessageDialog(null, "La lista se actualizo exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
							dispose();
						}else{
							return;
						}
			}
		}
	}
		
		KeyListener validaNumericoConPunto = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
			    if(((caracter < '0') || (caracter > '9')) && (caracter != '.' )){
			    		e.consume();
			    	}
			    	
			   if (caracter==KeyEvent.VK_PERIOD){
			    	String texto = tabla.getColumnName(3);
					if (texto.indexOf(".")>-1) e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
			
		public int getFilas(String qry){
			int filas=0;
			Statement stmt = null;
			try {
				Connexion con = new Connexion();
				stmt = con.conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					filas++;
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return filas;
		}	
		
		private Object[][] getTabla2(String variable){

			Statement s;
			ResultSet rs;
			
				String datos = "select * from tb_alimentacion_denominaciones where asignacion='"+variable+"'";
				
				try {			
					s = con.conexion().createStatement();
					rs = s.executeQuery(datos);				
					Matriz = new Object[getFilas(datos)][4];
					
					int i=0;
					float suma=0;
					
					while(rs.next()){
						
						float valor = Float.parseFloat(rs.getString(5));
						float cantidad = Float.parseFloat(rs.getString(6));
						
						Matriz[i][0] = rs.getString(3).trim();
						Matriz[i][1] = rs.getString(4).trim();
						Matriz[i][2] = valor+"";
						Matriz[i][3] = cantidad+"";
						
						suma = suma+(valor*cantidad);
						i++;	
					}
					txtTotal.setText(suma+"");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		
			return Matriz; 
		}
	}
	
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
					((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
					break;
					
				case 1:
					componente = new JLabel(value == null? "": value.toString());
					if(row%2==0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
					
				case 2: 
					componente = new JLabel(value == null? "": value.toString());
					if(row%2==0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
					break;
					
				case 3: 
					componente = new JLabel(value == null? "": value.toString());
					if(row%2==0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
					break;
			}
			return componente;
		} 
	}; 
	
	public static void main (String [] arg){
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		Cat_Filtro_Cortes thisClass = new Cat_Filtro_Cortes();
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
