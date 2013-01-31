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
import objetos.Obj_Diferencia_Cortes;

@SuppressWarnings("serial")
public class Cat_Diferencia_Cortes extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel	 modelo       = new DefaultTableModel(0,7)	{
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
	
	JTextField txtCantidad = new JTextField();
	JTextField txtDescuento = new JTextField();
	JTextField txtFecha = new JTextField();
	
	String status[] = {"Vigente","Cancelado Temporal"};
	@SuppressWarnings("unchecked")
	JComboBox cmbStatus = new JComboBox(status);
	
	JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JLabel btnEditar = new JLabel(new ImageIcon("imagen//Modify.png"));
	JLabel btnSalir = new JLabel(new ImageIcon("imagen//Delete.png"));
	JLabel btnGuardar = new JLabel(new ImageIcon("imagen//Guardar.png"));
	
	public Cat_Diferencia_Cortes(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Diferencia de Cortes");
		int x = 40, y=50, ancho=140;
		txtCantidad.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Cortes"));		
		
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
		tabla.getColumnModel().getColumn(5).setHeaderValue("Abono");
		tabla.getColumnModel().getColumn(5).setMinWidth(80);
		tabla.getColumnModel().getColumn(5).setMaxWidth(80);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(6).setMinWidth(100);
		tabla.getColumnModel().getColumn(6).setMaxWidth(100);
		
		agregar(tabla);
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio_Empleado).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre Completo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre_Completo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtFecha).setBounds(x+ancho,y,ancho-15,20);
		panel.add(btnCalendario).setBounds(x+ancho+ancho-27,y,50,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCantidad).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(new JLabel("Descuento:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtDescuento).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(new JLabel("Status:")).setBounds(x,y+=25,ancho,20);
		panel.add(cmbStatus).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(panelScroll).setBounds(x,y+=25+10,ancho+420,80);
		
		panel.add(btnFiltro).setBounds(20,15,16,16);
		panel.add(btnEditar).setBounds(46,15,16,16);
		panel.add(btnGuardar).setBounds(73,15,16,16);
		panel.add(lblTotal).setBounds(ancho-30,y-30, 400, 200);
		panel.add(btnSalir).setBounds(620,15,16,16);
		
		lblTotal.setFont(new java.awt.Font("Algerian",0,60));
		
		btnSalir.addMouseListener(salir);
		btnFiltro.addActionListener(filtro);
		btnEditar.addMouseListener(ValidarCampos);
		btnCalendario.addMouseListener(OpCalendario);
		btnGuardar.addMouseListener(guardar);
		
		txtCantidad.addKeyListener(validaNumericoConPunto);
		txtDescuento.addKeyListener(validaNumericoConPunto);
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		re = re.buscar(Integer.parseInt(algo));
	
		txtFolio_Empleado.setText(re.getFolio()+"");
		txtNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");	
		panelEnabledTrue();
		txtFecha.setEditable(false);
		
								
		String[][] Tabla = getMatriz(txtNombre_Completo.getText());
		Object[] fila = new Object[tabla.getColumnCount()];
		for(int i=0; i<Tabla.length; i++){
			modelo.addRow(fila); 
			for(int j=0; j<tabla.getColumnCount(); j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		
		if(tabla.getRowCount() != 0){
			
			txtFecha.setText(modelo.getValueAt(0,1)+"");
			txtCantidad.setText(modelo.getValueAt(0, 2)+"");
			txtDescuento.setText(modelo.getValueAt(0, 3)+"");
			if(modelo.getValueAt(0, 6).equals("Vigente")){
				cmbStatus.setSelectedIndex(0);
			}else{
				cmbStatus.setSelectedIndex(1);
			}
			panelEnabledFalse();
			btnGuardar.setEnabled(false);
		}
				
		this.setModal(true);
		this.setSize(650,390);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
        		panelEnabledFalse();
        		btnGuardar.setEnabled(false);
        		int fila = tabla.getSelectedRow();
        		
    			txtFecha.setText(modelo.getValueAt(fila,1)+"");
    			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
    			txtDescuento.setText(modelo.getValueAt(fila, 3)+"");
    			if(modelo.getValueAt(fila, 6).equals("Vigente")){
    				cmbStatus.setSelectedIndex(0);
    			}else{
    				cmbStatus.setSelectedIndex(1);
    			}
	        }
        });
    }
	
	MouseListener guardar = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				Obj_Diferencia_Cortes pres = new Obj_Diferencia_Cortes();
				
				switch(tabla.getRowCount()){
					case 0: 
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
						
						JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente", "Aviso se guardo el registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						
						if(pres.getStatus_descuento()==1){
							Object[] fila = new Object[tabla.getColumnCount()]; 
							Obj_Diferencia_Cortes maximo = new Obj_Diferencia_Cortes().maximo();
							fila[0]=maximo.getFolio();
							fila[1]=txtFecha.getText();
							fila[2]=txtCantidad.getText();
							fila[3]=txtDescuento.getText();
							fila[4]=txtCantidad.getText();
							fila[5]=0.00;
							
							switch(cmbStatus.getSelectedIndex()){
								case 0: fila[6]="Vigente";break;	
								case 1: fila[6]="Cancelado Temporal";break;
							}
							modelo.addRow(fila); 						
						}
						
						break;
					case 1: 
						
							if(JOptionPane.showConfirmDialog(null, "Desea Actualizar el registro existente ?") == JOptionPane.YES_OPTION) {
								
								pres.setFecha(txtFecha.getText());
								pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
								pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
								pres.setStatus(cmbStatus.getSelectedIndex()+1);
								pres.actualizar(Integer.parseInt(modelo.getValueAt(0,0)+""));
										
								int filas=  tabla.getRowCount();
								while(filas > 0){
									modelo.removeRow(0);
									filas--;
								}
										
								String[][] Tabla = getMatriz(txtNombre_Completo.getText());
								Object[] fila = new Object[tabla.getColumnCount()]; 
								for(int i=0; i<Tabla.length; i++){
									modelo.addRow(fila); 
									for(int j=0; j<7; j++){
										modelo.setValueAt(Tabla[i][j]+"", i,j);
									}
								}
							}
							
						break;
				}
				panelEnabledFalse();
			}
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
			
		}
	};	
	MouseListener ValidarCampos = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			panelEnabledTrue();
			btnGuardar.setEnabled(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	public void panelEnabledTrue(){	
		txtCantidad.setEditable(true);
		txtDescuento.setEditable(true);
		cmbStatus.setEnabled(true);
		
	}
	
	public void panelEnabledFalse(){	
		txtCantidad.setEditable(false);
		txtDescuento.setEditable(false);
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
	
	MouseListener salir = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			dispose();
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
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
		String qry = "select folio,fecha,cantidad,descuento,saldo,abonos,status,status_descuento from tb_diferencia_cortes where nombre_completo='"+NombreCompleto+"' and status_descuento=1 and saldo>0";
		
		String[][] Matriz = new String[getFilas(qry)][7];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");

				Matriz[i][0] = rs.getString(1).trim();
				Matriz[i][1] = rs.getString(2).trim();
				Matriz[i][2] = decimalFormat.format(Double.parseDouble(rs.getString(3)));
				Matriz[i][3] = decimalFormat.format(Double.parseDouble(rs.getString(4)));
				Matriz[i][4] = decimalFormat.format(Double.parseDouble(rs.getString(5)));
				Matriz[i][5] = decimalFormat.format(Double.parseDouble(rs.getString(6)));
				if(rs.getInt(7)==1){
					Matriz[i][6]= "Vigente";
				}else{
					Matriz[i][6]="Cancelado Temporal";
				}
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

}
