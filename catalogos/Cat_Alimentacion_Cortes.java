package catalogos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import camara.MainCamara;

import frames.WholeNumberField;

import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Empleado;
import objetos.Obj_Establecimiento;
import objetos.Obj_Puesto;
import objetos.Obj_Tipo_Banco;
import objetos.Obj_Turno;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cortes extends JDialog{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JLabel lblFolio_Empleado = new JLabel();
	JLabel lblNombre_Completo = new JLabel();
	JLabel lblPuesto = new JLabel();
	JTextField txtAsignacion = new JTextField();
	JTextField txtDeposito = new JTextField();
//	JButton btnDeposito = new JButton("dep");
	
	JLabel lblFolio_Corte = new JLabel();
	JLabel lblEstablecimineto = new JLabel();
	JTextField txtFecha1 = new JTextField("");
	JTextField txtCorteSistema = new JTextField();
	JTextField txtEfectivo = new JTextField();
	JCheckBox chStatus = new JCheckBox("Status");
	
	JTextArea txaObservaciones = new JTextArea(4,4);
	JScrollPane Observasiones = new JScrollPane(txaObservaciones);
	
	JButton btnEfectivo = new JButton("efe");
	
	JLabel lblDiferenciaCorte = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnGuardar = new JButton("Guardar");
	
	JButton btnFoto = new JButton();
	
	public String img = "";
	String file = "X:\\Empleados\\Un.JPG";
	
	public Cat_Alimentacion_Cortes(int folio, String establecimiento_corte) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Alimentacion Cortes");
		int x = 30, y=60, ancho=140, x2=400;
		txtCorteSistema.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Alimentacion Cortes"));
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x,y,ancho,20);
		panel.add(lblFolio_Empleado).setBounds(x+ancho,y,ancho,20);
		panel.add(new JLabel("Nombre Completo:")).setBounds(x,y+=25,ancho,20);
		panel.add(lblNombre_Completo).setBounds(x+ancho,y,ancho+80,20);
		panel.add(new JLabel("Puesto:")).setBounds(x,y+=25,ancho+40,20);
		panel.add(lblPuesto).setBounds(x+ancho,y,ancho+80,20);
		panel.add(new JLabel("Asignacion:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtAsignacion).setBounds(x+ancho,y,ancho+80,20);
		panel.add(new JLabel("Deposito:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDeposito).setBounds(x+ancho,y,ancho-40,20);
//		panel.add(btnDeposito).setBounds(x+ancho*2-40,y,29,20);
		
		panel.add(btnFoto).setBounds(x2+ancho*2,10,ancho+95,200);
		
		y=60;
		panel.add(new JLabel("Folio Corte:")).setBounds(x2,y,ancho,20);
		panel.add(lblFolio_Corte).setBounds(ancho+x2,y,ancho*2-150,20);
		panel.add(new JLabel("Establecimineto:")).setBounds(x2,y+=25,ancho,20);
		panel.add(lblEstablecimineto).setBounds(ancho+x2,y,ancho+80,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtFecha1).setBounds(ancho+x2,y,ancho-40,20);
		
		
		panel.add(new JLabel("Corte del Sistema:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtCorteSistema).setBounds(ancho+x2,y,ancho*2-150,20);
		panel.add(chStatus).setBounds(x2+ancho+70,60,70,20);
		panel.add(new JLabel("Efectivo:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtEfectivo).setBounds(ancho+x2,y,ancho-40,20);
		panel.add(btnEfectivo).setBounds(x2+ancho*2-40,y,29,20);
		panel.add(lblDiferenciaCorte).setBounds(x2,y+=25,ancho+x2,20);
		
		panel.add(txaObservaciones).setBounds(x,y+=35,x2*2+79,65);
		
		panel.add(btnGuardar).setBounds(x,25,ancho-20,20);
		panel.add(btnFiltro).setBounds(x2-60,60,32,20);

		txtAsignacion.setEnabled(true);
		lblEstablecimineto.setText(establecimiento_corte);
		btnGuardar.addActionListener(guardar);
		btnFiltro.addActionListener(filtro);
		
		
		txaObservaciones.setLineWrap(true); 
		txaObservaciones.setWrapStyleWord(true);
		txaObservaciones.setDocument(new JTextFieldLimit(580));
		
//		btnFoto.addActionListener(opFoto);
		btnEfectivo.addActionListener(opAlimentarDenominacion);
		txtCorteSistema.addKeyListener(validaNumericoConPunto);
		
		String file = "X:\\"+lblFolio_Empleado.getText()+"\\Un.JPG";
		ImageIcon tmpIconAux = new ImageIcon(file);
		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		Obj_Puesto puesto = new Obj_Puesto();
		
		re = re.buscar(folio);
		puesto= puesto.buscar(re.getPuesto());
		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes().buscar_nuevo();

		lblFolio_Empleado.setText(re.getFolio()+"");
		lblNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");
		lblPuesto.setText(puesto.getPuesto());
		lblFolio_Corte.setText(corte.getFolio_corte()+1+"");

		chStatus.setSelected(true);
		
		txtFecha1.setEditable(false);
		txtEfectivo.setEditable(false);
		chStatus.setEnabled(false);
		
		this.setModal(true);
		this.setSize(925,330);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	
	ActionListener opAlimentarDenominacion = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			String asignacion=txtAsignacion.getText();
			int folio_emp = Integer.parseInt(lblFolio_Empleado.getText());
			if(txtEfectivo.getText().equals("")){
				new Cat_Alimentacion_Por_Denominacion(asignacion,folio_emp).setVisible(true);
			}else{
				new Cat_Alimentacion_Por_Denominacion2(asignacion,folio_emp).setVisible(true);
					}
				
		}
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
					
					corte.setFolio_empleado(Integer.parseInt(lblFolio_Empleado.getText()+""));
					corte.setNombre(lblNombre_Completo.getText()+"");
					corte.setPuesto(lblPuesto.getText()+"");
					corte.setEstablecimiento(lblEstablecimineto.getText()+"");
					corte.setAsignacion(txtAsignacion.getText()+"");
					
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
					corte.setFecha(txtFecha1.getText()+"");
					corte.setStatus(chStatus.isSelected());
					corte.guardar();
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
				if (texto.indexOf(".")>0) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	private String validaCampos(){
		String error="";
		
		if(txtAsignacion.getText().equals(""))error+= "Asignacion\n";
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
	
	@SuppressWarnings({ "serial", "unchecked" })
	public class Cat_Alimentacion_Por_Denominacion extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		@SuppressWarnings("rawtypes")
		TableRowSorter trsfiltro;
		
		JTextField txtAsignacion = new JTextField();
		JTextField txtFecha = new JTextField();
		JButton btnTotal = new JButton("TOTAL:");
		JLabel lblEmpleadoId = new JLabel("");
		JLabel lblEmpleado = new JLabel("");
		JLabel lblTotal = new JLabel("");
		
		JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
			
		boolean bandera = false;
		
		Object[][] Matriz ;
		
		Object[][] Tabla = getTabla();
		DefaultTableModel model = new DefaultTableModel(Tabla,
	            new String[]{"Folio", "Denominacion", "Valor", "$ Cantidad" }
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
		    	java.lang.Object.class, 
		    	java.lang.Integer.class, 
		    	java.lang.Integer.class 
	         };
		     @SuppressWarnings("rawtypes")
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
		JButton btnGuardar = new JButton(new ImageIcon("imagen/Guardar.png"));
		
		@SuppressWarnings("rawtypes")
		public Cat_Alimentacion_Por_Denominacion(String asignacion,int folio_emp){
			
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
			this.setTitle("Alimentacion por Denominacion");
				
			panel.add(new JLabel("Asignacion: ")).setBounds(20,40,100,20);
			panel.add(txtAsignacion).setBounds(20,60,100,20);
			panel.add(new JLabel("Fecha: ")).setBounds(20,90,100,20);
			panel.add(txtFecha).setBounds(20,110,80,20);
			panel.add(btnCalendario).setBounds(90,110,40,20);

			panel.add(lblEmpleadoId).setBounds(140, 23, 60, 20);
			panel.add(lblEmpleado).setBounds(200, 23, 280, 20);
			panel.add(lblTotal).setBounds(20, 230, 100, 20);
			panel.add(scroll).setBounds(140,40,425,240);
			
			menu.add(btnGuardar);
			menu.setBounds(0,0,150,25);
			panel.add(menu);
			cont.add(panel);

			 setUpIntegerEditor(tabla);
			
			tabla.getColumnModel().getColumn(0).setMaxWidth(72);
			tabla.getColumnModel().getColumn(0).setMinWidth(72);		
			tabla.getColumnModel().getColumn(1).setMaxWidth(220);
			tabla.getColumnModel().getColumn(1).setMinWidth(220);
			tabla.getColumnModel().getColumn(2).setMaxWidth(50);
			tabla.getColumnModel().getColumn(2).setMinWidth(50);
			tabla.getColumnModel().getColumn(3).setMaxWidth(80);
			tabla.getColumnModel().getColumn(3).setMinWidth(80);

			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
			btnGuardar.addActionListener(opGuardar);
			btnTotal.addActionListener(opTotal);
			btnCalendario.addMouseListener(OpCalendario);

			tabla.addKeyListener(buscar_action);
			
			txtFecha.setEditable(false);
			this.setModal(true);
			this.setSize(585, 320);
			this.setLocationRelativeTo(null);
			
			txtAsignacion.setText(asignacion);
			
			Obj_Empleado re = new Obj_Empleado();
			re=re.buscar(folio_emp);
			lblEmpleadoId.setText(folio_emp+"");
			lblEmpleado.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
		}
		
		 private void setUpIntegerEditor(JTable table) {
		        final WholeNumberField integerField = new WholeNumberField(0, 3);
		        integerField.setHorizontalAlignment(WholeNumberField.RIGHT);

		        DefaultCellEditor integerEditor = 
		            new DefaultCellEditor(integerField) {
		                public Object getCellEditorValue() {
		                    return new Integer(integerField.getValue());
		                }
		            };
		        table.setDefaultEditor(Integer.class, integerEditor);
		}
		 
			MouseListener OpCalendario = new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					ejecutar();
				}
				public void mouseReleased(MouseEvent e) {}		
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			};
			public void ejecutar(){
				final Display display = new Display ();
				Shell shell = new Shell (display);
				shell.setLayout (new RowLayout ());
				
				DateTime calendar = new DateTime (shell, SWT.CALENDAR);
				calendar.addSelectionListener (new SelectionAdapter () {
					public void widgetSelected (SelectionEvent e) {
						String fecha = e.toString().substring(25,35);
						fecha = fecha.replace("}", "");
						String[] splits = fecha.split("/");				
							String diaInicial  = splits[1];
							String mesInicial  = splits[0];	
							String anioInicial = splits[2];
							
							txtFecha.setText(diaInicial+"/"+mesInicial+"/"+anioInicial);
					}
				});
				shell.pack ();
				shell.open ();
				while (!shell.isDisposed ()) {
					if (!display.readAndDispatch ()) display.sleep ();
				}
				display.dispose();
			}

			
		ActionListener opGuardar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				guardar();
			}
		};
		

		@SuppressWarnings("rawtypes")
		public void guardar(){
			Vector miVector = new Vector();
			if(txtFecha.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ingrese Fecha!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				if(lblTotal.getText()==""){
					JOptionPane.showMessageDialog(null, "Verifique Total de Alimentacion!","Aviso",JOptionPane.WARNING_MESSAGE);
			}
			else{
				
				txtFecha1.setText(txtFecha.getText()+"");
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						model.isCellEditable(i,j);
						miVector.add(model.getValueAt(i,j).toString());
					}
					Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
					
					Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
					Alim_Denom.setFolio_empleado(Integer.parseInt(lblEmpleadoId.getText()));
					Alim_Denom.setFolio_denominacion(Integer.parseInt(miVector.get(0).toString().trim()));
					Alim_Denom.setDenominacion(miVector.get(1).toString().trim());
					Alim_Denom.setValor(Float.parseFloat(miVector.get(2).toString().trim()));
					if(miVector.get(3) != ""){
						Alim_Denom.setCantidad(Float.parseFloat(miVector.get(3).toString().trim()));
					}else{
						miVector.set(3,0);
						Alim_Denom.setCantidad(Integer.parseInt(miVector.get(3).toString().trim()));
					}
					Alim_Denom.setFecha(txtFecha1.getText().trim());
					Alim_Denom.guardar();
					miVector.clear();
					
					}
						JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}
			}
		
		
		public Object[][] getTabla(){
			new Progress_Bar_Abrir().setVisible(true);
		    return Matriz; 
		}
		
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
	//TOTAL DE ALIMENTACION--------------------------------------------------------------------	
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
					btnTotal.doClick();
				}
			}
		};
		ActionListener opTotal = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
					suma();
				}
			}
		};
		public void suma(){
			float suma = 0;
			int NoFilas=model.getRowCount();
			
			for(int i=0;i<NoFilas; i++) {
				
				float valor= Float.parseFloat(model.getValueAt(i,2).toString());
				
				if(model.getValueAt(i,3).toString()==""){
					
					float cantidad = 0;
					suma=(suma+(cantidad*valor));
				}else{
					
					float cantidad= Float.parseFloat(model.getValueAt(i,3).toString());
					suma=(suma+(cantidad*valor));
				}
				lblTotal.setText("$ "+suma);
				txtEfectivo.setText(suma+"");
				
				float corteSistema=Float.parseFloat(txtCorteSistema.getText());
				float deposito=Float.parseFloat(txtDeposito.getText());
				float efectivo=Float.parseFloat(txtEfectivo.getText());
				
				float DiferenciaCorte=corteSistema-(deposito+efectivo);
				
				lblDiferenciaCorte.setText("Diferencia de Corte:          "+DiferenciaCorte);
			} 
		}
	//TERMINA TOTAL DE ALIMENTACION---------------------------------------------------------------------------------
		
		public class Progress_Bar_Abrir extends JDialog {
			Container cont = getContentPane();
			JLayeredPane panel = new JLayeredPane();
			JProgressBar barra = new JProgressBar();
			
			JLabel lblNombre = new JLabel("");
			
			public Progress_Bar_Abrir() {
				barra.setStringPainted(true);
				Thread hilo = new Thread(new Hilo());
				hilo.start();
				panel.setBorder(BorderFactory.createTitledBorder("Espere un momento..."));
				
				panel.add(barra).setBounds(20,25,350,20);
				panel.add(lblNombre).setBounds(30,45,350,20);
				
				cont.add(panel);
				
				this.setUndecorated(true);
				this.setSize(400,100);
				this.setModal(true);
				this.setLocationRelativeTo(null);
				this.setResizable(false);
			
			}
				class Hilo implements Runnable {
					public void run() {
						String todos = "select tb_denominaciones.folio as [Folio],"+
						 "  tb_denominaciones.nombre as [Nombre], "+
						 "  tb_divisas_tipo_de_cambio.valor as [Valor]," +
						 "	 tb_denominaciones.status as [Status] "+
						
			"  from tb_denominaciones,tb_divisas_tipo_de_cambio" +
			" where " +
						"tb_denominaciones.status=1 and " +
						"tb_divisas_tipo_de_cambio.nombre_divisas=tb_denominaciones.moneda";
						
			Statement stmt = null;
			ResultSet rs;
			Connexion con = new Connexion();
			try {
					stmt = con.conexion().createStatement();
					rs = stmt.executeQuery(todos);
					Matriz = new Object[getFilas(todos)][7];
					int i=0;
					int total = getFilas(todos);
					while(rs.next()){
						Matriz[i][0] = rs.getString(1).trim();
						Matriz[i][1] = rs.getString(2).trim();
						Matriz[i][2] = rs.getString(3).trim();
						Matriz[i][3] = "";
						i++;
						int porcent = (i*100)/total;
						barra.setValue(porcent);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}dispose();
				}
			}
		}
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	public class Cat_Alimentacion_Por_Denominacion2 extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		@SuppressWarnings("rawtypes")
		TableRowSorter trsfiltro;
		
		JTextField txtAsignacion = new JTextField();
		JTextField txtFecha = new JTextField();
		JButton btnTotal = new JButton("TOTAL:");
		JLabel lblEmpleadoId = new JLabel("");
		JLabel lblEmpleado = new JLabel("");
		JLabel lblTotal = new JLabel("");
		
		JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
		
		boolean bandera = false;
		String variable= "";
		
		Object[][] Matriz ;
	
		DefaultTableModel model = new DefaultTableModel(0, 4){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Object.class,
		    	java.lang.Object.class, 
		    	java.lang.Integer.class, 
		    	java.lang.Integer.class 
	         };
		     @SuppressWarnings("rawtypes")
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
		
		@SuppressWarnings("rawtypes")
		public Cat_Alimentacion_Por_Denominacion2(String asignacion,int folio_emp){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dollar.png"));
			this.setTitle("Alimentacion por Denominacion");
				
			panel.add(new JLabel("Asignacion: ")).setBounds(20,40,100,20);
			panel.add(txtAsignacion).setBounds(20,60,100,20);
			panel.add(new JLabel("Fecha: ")).setBounds(20,90,100,20);
			panel.add(txtFecha).setBounds(20,110,80,20);
			panel.add(btnCalendario).setBounds(90,110,40,20);

			panel.add(lblEmpleadoId).setBounds(140, 23, 60, 20);
			panel.add(lblEmpleado).setBounds(200, 23, 280, 20);
			panel.add(lblTotal).setBounds(20, 230, 100, 20);
			panel.add(scroll).setBounds(140,40,425,240);
			
			menu.add(btnModificar);
			menu.setBounds(0,0,150,25);
			panel.add(menu);
			cont.add(panel);

			 setUpIntegerEditor(tabla);
			
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
			btnTotal.addActionListener(opTotal);
			btnCalendario.addMouseListener(OpCalendario);

			tabla.addKeyListener(buscar_action);
					
			txtFecha.setEditable(false);
			this.setModal(true);
			this.setSize(585, 320);
			this.setLocationRelativeTo(null);
			
			Obj_Alimentacion_Denominacion denom = new Obj_Alimentacion_Denominacion();
			denom=denom.buscar(asignacion);
			
			txtAsignacion.setText(denom.getAsignacion());
			txtFecha.setText(denom.getFecha());
			lblEmpleadoId.setText(folio_emp+"");
			
			Obj_Empleado re = new Obj_Empleado();
			re=re.buscar(folio_emp);
			lblEmpleadoId.setText(folio_emp+"");
			lblEmpleado.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno());
			
			variable = asignacion;
			
			Object[][] TablaAux = getTabla(asignacion);
			Object[] fila= new Object[tabla.getColumnCount()]; 
			for(int i=0; i<TablaAux.length; i++){
				model.addRow(fila); 
				for(int j=0; j<4; j++){
					model.setValueAt(TablaAux[i][j]+"", i,j);
				}
			}
		}
		
		 private void setUpIntegerEditor(JTable table) {
		        final WholeNumberField integerField = new WholeNumberField(0, 3);
		        integerField.setHorizontalAlignment(WholeNumberField.RIGHT);

		        DefaultCellEditor integerEditor = 
		            new DefaultCellEditor(integerField) {
		                public Object getCellEditorValue() {
		                    return new Integer(integerField.getValue());
		                }
		            };
		        table.setDefaultEditor(Integer.class, integerEditor);
		}
		
			MouseListener OpCalendario = new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					ejecutar();
				}
				public void mouseReleased(MouseEvent e) {}		
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			};
			
			public void ejecutar(){
				final Display display = new Display ();
				Shell shell = new Shell (display);
				shell.setLayout (new RowLayout ());
				
				DateTime calendar = new DateTime (shell, SWT.CALENDAR);
				calendar.addSelectionListener (new SelectionAdapter () {
					public void widgetSelected (SelectionEvent e) {
						String fecha = e.toString().substring(25,35);
						fecha = fecha.replace("}", "");
						String[] splits = fecha.split("/");				
							String diaInicial  = splits[1];
							String mesInicial  = splits[0];	
							String anioInicial = splits[2];
							
							txtFecha.setText(diaInicial+"/"+mesInicial+"/"+anioInicial);
					}
				});
				shell.pack ();
				shell.open ();
				while (!shell.isDisposed ()) {
					if (!display.readAndDispatch ()) display.sleep ();
				}
				display.dispose();
			}
			
		ActionListener opModificar = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				modificar();
			}
		};
		

		@SuppressWarnings("rawtypes")
		public void modificar(){
			Vector miVector = new Vector();
			if(txtFecha.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ingrese Fecha!","Aviso",JOptionPane.WARNING_MESSAGE);
			}else{
				if(lblTotal.getText()==""){
					JOptionPane.showMessageDialog(null, "Verifique Total de Alimentacion!","Aviso",JOptionPane.WARNING_MESSAGE);
			}
			else{
				for(int i=0; i<model.getRowCount(); i++){
					for(int j=0; j<model.getColumnCount(); j++){
						model.isCellEditable(i,j);
						miVector.add(model.getValueAt(i,j).toString());
					}
					Obj_Alimentacion_Denominacion Alim_Denom = new Obj_Alimentacion_Denominacion();
					
					Alim_Denom.setAsignacion(txtAsignacion.getText().trim());
					Alim_Denom.setFolio_empleado(Integer.parseInt(lblEmpleadoId.getText()));
					Alim_Denom.setFolio_denominacion(Integer.parseInt(miVector.get(0).toString().trim()));
					Alim_Denom.setDenominacion(miVector.get(1).toString().trim());
					Alim_Denom.setValor(Float.parseFloat(miVector.get(2).toString().trim()));
					if(miVector.get(3) != ""){
						Alim_Denom.setCantidad(Float.parseFloat(miVector.get(3).toString().trim()));
					}else{
						miVector.set(3,0);
						Alim_Denom.setCantidad(Integer.parseInt(miVector.get(3).toString().trim()));
					}
					Alim_Denom.setFecha(txtFecha.getText().trim());
					Alim_Denom.actualizar(txtAsignacion.getText().trim());
					miVector.clear();
					
					}
						JOptionPane.showMessageDialog(null, "La lista se guardó exitosamente!","Aviso",JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}
			}
			
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
	//TOTAL DE ALIMENTACION--------------------------------------------------------------------	
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
					btnTotal.doClick();
				}
			}
		};
		ActionListener opTotal = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
					suma();
				}
			}
		};
		public void suma(){
			float suma = 0;
			int NoFilas=model.getRowCount();
			
			for(int i=0;i<NoFilas; i++) {
				
				float valor= Float.parseFloat(model.getValueAt(i,2).toString());
				
				if(model.getValueAt(i,3).toString()==""){
					
					float cantidad = 0;
					suma=(suma+(cantidad*valor));
				}else{
					
					float cantidad= Float.parseFloat(model.getValueAt(i,3).toString());
					suma=(suma+(cantidad*valor));
				}
				lblTotal.setText("$ "+suma);
				txtEfectivo.setText(suma+"");
				
				float corteSistema=Float.parseFloat(txtCorteSistema.getText());
				float deposito=Float.parseFloat(txtDeposito.getText());
				float efectivo=Float.parseFloat(txtEfectivo.getText());
				
				float DiferenciaCorte=corteSistema-(deposito+efectivo);
				
				lblDiferenciaCorte.setText("Diferencia de Corte:          "+DiferenciaCorte);
			} 
		}
	//TERMINA TOTAL DE ALIMENTACION---------------------------------------------------------------------------------
		
		
		
		private Object[][] getTabla(String variable){
			String datos = "select * from tb_alimentacion_denominaciones where asignacion='"+variable+"'";
			System.out.println(datos);
			Statement s;
			ResultSet rs;
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
				lblTotal.setText(suma+"");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return Matriz; 
		}
	
	}
	
	public static void main (String [] arg){
		new Cat_Alimentacion_Cortes(1,"CEDIS").setVisible(true);
	}
}
