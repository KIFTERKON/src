package catalogos;

import java.awt.Component;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import camara.MainCamara;

import SQL.Connexion;

import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cortes extends JDialog{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JLabel lblFolio_Empleado = new JLabel();
	JLabel lblNombre_Completo = new JLabel();
	JLabel lblPuesto = new JLabel();
	JTextField txtAsisgnacion = new JTextField("abc");
	JTextField txtDeposito = new JTextField("150");
	JButton btnDeposito = new JButton("dep");
	
	JLabel lblFolio_Corte = new JLabel();
	JLabel lblEstablecimineto = new JLabel();
	JTextField txtFecha = new JTextField("");
	JTextField txtCorteSistema = new JTextField("1000");
	JTextField txtEfectivo = new JTextField("300");
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnEfectivo = new JButton("efe");
	JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnGuardar = new JButton("Guardar");
//	JButton btnDeshacer = new JButton("Deshacer");
	
	JButton btnFoto = new JButton();
	
	public String img = "";
	
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
		panel.add(txtAsisgnacion).setBounds(x+ancho,y,ancho+80,20);
		panel.add(new JLabel("Deposito:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDeposito).setBounds(x+ancho,y,ancho-40,20);
		panel.add(btnDeposito).setBounds(x+ancho*2-40,y,29,20);
		
		panel.add(btnFoto).setBounds(x2+ancho*2,10,ancho+95,200);
		
		y=60;
		panel.add(new JLabel("Folio Corte:")).setBounds(x2,y,ancho,20);
		panel.add(lblFolio_Corte).setBounds(ancho+x2,y,ancho*2-150,20);
		panel.add(new JLabel("Establecimineto:")).setBounds(x2,y+=25,ancho,20);
		panel.add(lblEstablecimineto).setBounds(ancho+x2,y,ancho+80,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(ancho+x2,y,ancho-40,20);
		panel.add(btnCalendario).setBounds(x2+ancho*2-45,y,40,20);
		
		panel.add(new JLabel("Corte del Sistema:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtCorteSistema).setBounds(ancho+x2,y,ancho*2-150,20);
		panel.add(chStatus).setBounds(x2+ancho+70,60,70,20);
		panel.add(new JLabel("Efectivo:")).setBounds(x2,y+=25,ancho,20);
		panel.add(txtEfectivo).setBounds(ancho+x2,y,ancho-40,20);
		panel.add(btnEfectivo).setBounds(x2+ancho*2-40,y,29,20);
		
		
//		panel.add(btnDeshacer).setBounds(x,25,ancho-20,20);
		panel.add(btnGuardar).setBounds(x,25,ancho-20,20);
		panel.add(btnFiltro).setBounds(x2-60,60,32,20);

		lblEstablecimineto.setText(establecimiento_corte);
		btnGuardar.addActionListener(guardar);
//		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		btnCalendario.addMouseListener(OpCalendario);
		
//		btnFoto.addActionListener(opFoto);
		btnEfectivo.addActionListener(opAlimentarDenominacion);
		txtCorteSistema.addKeyListener(validaNumericoConPunto);
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		Obj_Puesto puesto = new Obj_Puesto();
		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
		
		re = re.buscar(folio);
		puesto= puesto.buscar(re.getPuesto());
//		corte.buscar_nuevo();
		lblFolio_Empleado.setText(re.getFolio()+"");
		lblNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");
		lblPuesto.setText(puesto.getPuesto());
		lblFolio_Corte.setText(corte.getFolio_corte()+"");

		chStatus.setSelected(true);
		
		txtFecha.setEditable(false);
		txtDeposito.setEditable(false);
		txtEfectivo.setEditable(false);
		chStatus.setEnabled(false);
		
		this.setModal(true);
		this.setSize(925,250);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	ActionListener opFoto = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(lblFolio_Empleado.getText().length() != 0){
				String file = "X:\\Empleados\\"+lblFolio_Empleado.getText()+".JPG";
				img = file;
				File fichero = new File(file);
				if(fichero.exists()){
					ImageIcon tmpIconAux = new ImageIcon(file);
				    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
				}else {
					new MainCamara(lblFolio_Empleado.getText()).setVisible(true);
				}				
			}else {
				  JOptionPane.showMessageDialog(null, "Cree un nuevo empleado, que contenga un folio.");				
			}
			
		}
	};
	
	ActionListener opAlimentarDenominacion = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			int folio_emp = Integer.parseInt(lblFolio_Empleado.getText());
			String fecha = txtFecha.getText();
			if(!txtFecha.getText().equals("")){
				new Cat_Alimentacion_Por_Denominacion(folio_emp,fecha).setVisible(true);
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
					corte.setAsignacion(txtAsisgnacion.getText()+"");
					
					float corteSistema=Float.parseFloat(txtCorteSistema.getText()+"");
					float deposito =Float.parseFloat(txtDeposito.getText()+"");
					float efectivo =Float.parseFloat(txtEfectivo.getText()+"");
					
					corte.setCorte_sistema(corteSistema);
					corte.setDeposito(deposito);
					corte.setEfectivo(efectivo);
					corte.setDiferencia_corte(corteSistema-(deposito+efectivo));
					
					corte.setFecha(txtFecha.getText()+"");
					corte.setStatus(chStatus.isSelected());
					corte.guardar();
					
				}	
			}
//		}			
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
	
//	ActionListener deshacer = new ActionListener(){
//		public void actionPerformed(ActionEvent e){
//			txtAsisgnacion.setText("");
//			txtDeposito.setText("");
//			txtCorteSistema.setText("");
//			txtFecha.setText("");
//			txtEfectivo.setText("");
//			txtCorteSistema.requestFocus();
//			chStatus.setSelected(false);
//		}
//	};
	
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
		
		if(txtAsisgnacion.getText().equals(""))error+= "Asignacion\n";
		if(txtCorteSistema.getText().equals(""))error+= "Corte del Sistema\n";
		if(txtDeposito.getText().equals(""))error+= "Depocito\n";
		if(txtEfectivo.getText().equals(""))error+= "Efectivo\n";
		if(txtFecha.getText().equals(""))error+= "Fecha\n";
				
		return error;
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
	public String[][] getMatriz(String NombreCompleto){
		String qry = "select folio,fecha,cantidad from tb_fuente_sodas_rh where nombre_completo='"+NombreCompleto+"' and status='1'";
		
		String[][] Matriz = new String[getFilas(qry)][3];
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getString(1).trim();
				Matriz[i][1] = rs.getString(2).trim();
				Matriz[i][2] = rs.getString(3).trim();

				i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
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
	public static void main (String [] arg){
		new Cat_Alimentacion_Cortes(1,"CEDIS").setVisible(true);
	}
}
