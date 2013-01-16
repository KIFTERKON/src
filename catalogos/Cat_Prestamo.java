package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import SQL.Connexion;

import objetos.Obj_Empleado;
import objetos.Obj_Prestamo;
import objetos.Obj_Rango_Prestamos;

@SuppressWarnings("serial")
public class Cat_Prestamo extends JDialog{
	
	Double rangoIn;
	Double rangoFin;

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel	 modelo       = new DefaultTableModel(0,6)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla                   = new JTable(modelo);
	JScrollPane panelScroll        = new JScrollPane(tabla);
	
	JLabel txtFolio_Empleado = new JLabel();
	JLabel txtNombre_Completo = new JLabel();
	
	String rango_prestamo[] = new Obj_Rango_Prestamos().Combo_Prestamos();
	JLabel lblRango = new JLabel();
	JLabel lblEtiquetaRango = new JLabel("Limite de Prestamo:");
	
	JTextField txtCantidad = new JTextField();
	JTextField txtDescuento = new JTextField();
	JTextField txtFecha = new JTextField();
	
	String status[] = {"Vigente","Cancelado Temporal"};
	@SuppressWarnings("unchecked")
	JComboBox cmbStatus = new JComboBox(status);
	
	JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JLabel btnEditar = new JLabel(new ImageIcon("imagen//Report.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	
	public Cat_Prestamo(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Prestamos");
		int x = 40, y=30, ancho=140;
		txtCantidad.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Prestamo"));
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio_Empleado).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre Completo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre_Completo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(btnFiltro).setBounds(x+ancho+ancho+90,y,32,20);
		
		panel.add(btnEditar).setBounds(x+ancho+ancho+150,y+=25,55,46);
		
		panel.add(panelScroll).setBounds(x+ancho+x+40+ancho+ancho-80+50,y,ancho+330,260);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Fecha");
		tabla.getColumnModel().getColumn(1).setMinWidth(80);
		tabla.getColumnModel().getColumn(1).setMaxWidth(80);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Cantidad");
		tabla.getColumnModel().getColumn(2).setMinWidth(80);
		tabla.getColumnModel().getColumn(2).setMaxWidth(80);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Descuento");
		tabla.getColumnModel().getColumn(3).setMinWidth(80);
		tabla.getColumnModel().getColumn(3).setMaxWidth(80);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Saldo");
		tabla.getColumnModel().getColumn(4).setMinWidth(80);
		tabla.getColumnModel().getColumn(4).setMaxWidth(80);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(5).setMinWidth(100);
		tabla.getColumnModel().getColumn(5).setMaxWidth(100);
		
		agregar(tabla);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,ancho-15,20);
		panel.add(btnCalendario).setBounds(x+ancho+ancho-27,y,50,20);
		
		panel.add(lblEtiquetaRango).setBounds(x,y+=25,ancho,20);
		panel.add(lblRango).setBounds(x+ancho,y,ancho,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCantidad).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(new JLabel("Descuento:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDescuento).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=35,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		panel.add(lblTotal).setBounds(ancho-30,y-30, 400, 200);
		
		lblTotal.setFont(new java.awt.Font("Algerian",0,60));
		lblRango.setFont(new java.awt.Font("SansSerif",0,12));
		lblEtiquetaRango.setFont(new java.awt.Font("SansSerif",0,12));		
		
		btnSalir.addActionListener(salir);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		btnEditar.addMouseListener(ValidarCampos);
		btnCalendario.addMouseListener(OpCalendario);
		btnGuardar.addActionListener(guardar);
		
		txtCantidad.addKeyListener(validaNumericoConPunto);
		txtDescuento.addKeyListener(validaNumericoConPunto);
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
				
		re = re.buscar(Integer.parseInt(algo));
	
		txtFolio_Empleado.setText(re.getFolio()+"");
		txtNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");	
		lblRango.setText(rango_prestamo[re.getPrestamo()-1]);
		panelEnabledTrue();
		txtFecha.setEnabled(false);
		
		String Rango =rango_prestamo[re.getPrestamo()-1];
		Rango = Rango.replace(" - ", "-");
		String[] splits = Rango.split("-");
		System.out.println(splits.length);
		
			rangoIn  = Double.parseDouble(splits[0]);
			rangoFin = Double.parseDouble(splits[1]);	
			
			System.out.println(rangoIn);
			System.out.println(rangoFin);
						
		String[][] Tabla = getMatriz(txtNombre_Completo.getText());
		Object[] fila = new Object[tabla.getColumnCount()]; 
		for(int i=0; i<Tabla.length; i++){
			modelo.addRow(fila); 
			for(int j=0; j<6; j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
				
		suma();
		panelLimpiar();
		
		this.setModal(true);
		this.setSize(1005,390);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	
	        	if(e.getClickCount()==1){	
	        		panelEnabledFalse();
	        		int fila = tabla.getSelectedRow();
	        		
	    			txtFecha.setText(modelo.getValueAt(fila,1)+"");
	    			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
	    			txtDescuento.setText(modelo.getValueAt(fila, 3)+"");
	    			
	    			if(modelo.getValueAt(fila, 5).equals("Vigente")){
	    				cmbStatus.setSelectedIndex(0);
	    			}else{
	    				cmbStatus.setSelectedIndex(1);
	    				 }
	    			suma();
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){			
				
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
			if(Double.parseDouble(txtCantidad.getText())> rangoFin){
				JOptionPane.showMessageDialog(null,"Solo se le puede autorizar la cantidad de $"+rangoFin, "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
			else{
				int cantidadFilasSeleccionadas = tabla.getSelectedRowCount();
				int nroFila = tabla.getSelectedRow();
				if(cantidadFilasSeleccionadas == 0){
					Obj_Prestamo pres = new Obj_Prestamo();
					
					pres.setFolio(Integer.parseInt(txtFolio_Empleado.getText()));
					pres.setFolio_empleado(Integer.parseInt(txtFolio_Empleado.getText()));
					pres.setNombre_Completo(txtNombre_Completo.getText());
					pres.setFecha(txtFecha.getText());
					pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
					pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
					
					pres.setSaldo(Double.parseDouble(txtCantidad.getText()));
					pres.setStatus(cmbStatus.getSelectedIndex()+1);
					pres.setStatus_descuento(cmbStatus.getSelectedIndex()+1);
					
					pres.guardar();
					
					if(pres.getStatus_descuento()==1){
							Object[] fila = new Object[tabla.getColumnCount()]; 
							Obj_Prestamo maximo = new Obj_Prestamo().maximo();
							fila[0]=maximo.getFolio();
							fila[1]=txtFecha.getText();
							fila[2]=txtCantidad.getText();
							fila[3]=txtDescuento.getText();
							fila[4]=txtCantidad.getText();
					
						switch(cmbStatus.getSelectedIndex()){
								case 0: fila[5]="Vigente";break;	
								case 1: fila[5]="Cancelado Temporal";break;
					}
						
					modelo.addRow(fila); 
					suma();
					panelLimpiar();
				}
				}else{
					if(JOptionPane.showConfirmDialog(null, "Seguro que quiere Actualizar el registro "+ modelo.getValueAt(nroFila,0) +" ?") == JOptionPane.YES_OPTION){
						Obj_Prestamo pres = new Obj_Prestamo();
					
						pres.setFecha(txtFecha.getText());
						pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
						pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
						pres.setStatus(cmbStatus.getSelectedIndex()+1);
						pres.setStatus_descuento(cmbStatus.getSelectedIndex()+1);
						pres.actualizar(Integer.parseInt(modelo.getValueAt(nroFila,0)+""));
						
						int filas=  tabla.getRowCount();
						while(filas > 0){
							modelo.removeRow(0);
							filas--;
						}
						
						String[][] Tabla = getMatriz(txtNombre_Completo.getText());
						Object[] fila = new Object[tabla.getColumnCount()]; 
						for(int i=0; i<Tabla.length; i++){
							modelo.addRow(fila); 
							for(int j=0; j<6; j++){
								modelo.setValueAt(Tabla[i][j]+"", i,j);
							}
						}
						
						suma();
						panelLimpiar();
					}
				}	
			}
		}			
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_Prestamo().setVisible(true);
			
		}
	};	
	MouseListener ValidarCampos = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			panelEnabledTrue();
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	public void panelEnabledTrue(){	
		txtCantidad.setEnabled(true);
		txtDescuento.setEnabled(true);
		cmbStatus.setEnabled(true);
		
	}
	
	public void panelEnabledFalse(){	
		txtCantidad.setEnabled(false);
		txtDescuento.setEnabled(false);
		cmbStatus.setEnabled(false);
		
	}
	
	public void panelLimpiar(){	
		panelEnabledFalse();
		panelEnabledTrue();
		txtCantidad.setText("");
		txtDescuento.setText("");
		txtCantidad.requestFocus();
		tabla.setSelectionMode(0);
		
	}
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledTrue();
			txtCantidad.setText("");
			txtDescuento.setText("");
			txtCantidad.requestFocus();
			tabla.setSelectionMode(0);
		}
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
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
		    	String texto = txtCantidad.getText().toString();
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
		
		if(txtNombre_Completo.getText().equals(""))error+= "Nombre Completo\n";
		if(txtCantidad.getText().equals(""))error+= "Cantidad\n";
		if(txtDescuento.getText().equals(""))error+= "Descuento\n";
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
				System.out.println(splits.length);
				
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
		String qry = "select folio,fecha,cantidad,descuento,saldo,status,status_descuento from tb_prestamo where nombre_completo='"+NombreCompleto+"' and status_descuento=1";
		
		String[][] Matriz = new String[getFilas(qry)][6];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			
			int desc=0;
			while(rs.next()){
				
					DecimalFormat decimalFormat = new DecimalFormat("#0.00");

					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim();
					Matriz[i][2] = decimalFormat.format(Double.parseDouble(rs.getString(3)+""));
					Matriz[i][3] = decimalFormat.format(Double.parseDouble(rs.getString(4)+""));
					Matriz[i][4] = decimalFormat.format(Double.parseDouble(rs.getString(5)+""));
				
					if(rs.getInt(6)==1){
						Matriz[i][5]= "Vigente";
					}else{
						Matriz[i][5]="Cancelado Temporal";
					}
					System.out.println("=====NO APLICA===="+Integer.parseInt(rs.getString(7)));
					i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public static int getFilas(String qry){
		int filas=0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}
	
	public void suma(){
		float suma = 0;
		
		for(int i=0;i<modelo.getRowCount(); i++) {
			float datos= Float.parseFloat(modelo.getValueAt(i,4).toString());
			suma=(suma+datos); 
		} 
		
		lblTotal.setText("$ "+String.valueOf(suma));
	
	}
	
}
