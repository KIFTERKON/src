package catalogos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import SQL.Connexion;

import objetos.Obj_Empleado;
import objetos.Obj_Prestamo;
import objetos.Obj_Rango_Prestamos;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Prestamo extends JDialog{
	
	Double rangoIn;
	Double rangoFin;

	int folio_empleado = 0;
	float saldo = 0;
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
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
	
	String rango_prestamo[] = new Obj_Rango_Prestamos().Combo_Prestamos();
	JLabel lblRango = new JLabel();
	JLabel lblEtiquetaRango = new JLabel("Limite de Prestamo:");
	
	JTextField txtCantidad = new JTextField();
	JTextField txtDescuento = new JTextField();
	
	String status[] = {"Vigente","Cancelado Temporal"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbStatus = new JComboBox(status);
	
	com.toedter.calendar.JDateChooser txtCalendario = new com.toedter.calendar.JDateChooser();
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JLabel btnEditar = new JLabel(new ImageIcon("imagen//Modify.png"));
	JLabel btnGuardar = new JLabel(new ImageIcon("imagen//Guardar.png"));
	
	public Cat_Prestamo(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Usuario.png"));
		this.setTitle("Prestamos");
		int x = 40, y=50, ancho=140;
		txtCantidad.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Prestamo"));		
		
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
		tabla.getColumnModel().getColumn(5).setHeaderValue("Total Abonos");
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
		panel.add(txtCalendario).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(lblEtiquetaRango).setBounds(x,y+=25,ancho+20,20);
		panel.add(lblRango).setBounds(x+ancho+35,y,ancho+40,20);
		
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
		
		lblTotal.setFont(new java.awt.Font("Algerian",0,60));
		lblRango.setFont(new java.awt.Font("SansSerif",5,18));
		lblEtiquetaRango.setFont(new java.awt.Font("SansSerif",5,18));	
		
		lblRango.setForeground(Color.blue);
		lblEtiquetaRango.setForeground(Color.blue);
		
		btnFiltro.addActionListener(filtro);
		btnEditar.addMouseListener(ValidarCampos);
		btnGuardar.addMouseListener(guardar);
		
		txtCantidad.addKeyListener(validaNumericoConPunto);
		txtDescuento.addKeyListener(validaNumericoConPunto);
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		re = re.buscar(Integer.parseInt(algo));
	
		folio_empleado= re.getFolio();
		txtFolio_Empleado.setText(folio_empleado+"");
		txtNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");	
		lblRango.setText(rango_prestamo[re.getPrestamo()]);
		panelEnabledTrue();
		
		String Rango =rango_prestamo[re.getPrestamo()];
		Rango = Rango.replace(" - ", "-");
		String[] splits = Rango.split("-");
		
		double uno = Double.parseDouble(splits[0]);
		double dos = Double.parseDouble(splits[1]);

		rangoIn  = uno;
		rangoFin = dos;
								
		String[][] Tabla = getMatriz(txtNombre_Completo.getText());
		Object[] fila = new Object[tabla.getColumnCount()];
		for(int i=0; i<Tabla.length; i++){
			modelo.addRow(fila); 
			for(int j=0; j<tabla.getColumnCount(); j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		
		if(tabla.getRowCount() != 0){
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(0,1)+"");
				txtCalendario.setDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtCantidad.setText(modelo.getValueAt(0, 2)+"");
			txtDescuento.setText(modelo.getValueAt(0, 3)+"");
			if(modelo.getValueAt(0, 6).equals("VIGENTE")){
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
	        		
	        		try {
	    				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(fila,1)+"");
	    				txtCalendario.setDate(date);
	    			} catch (ParseException e1) {
	    				e1.printStackTrace();
	    			}
	    			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
	    			txtDescuento.setText(modelo.getValueAt(fila, 3)+"");
	    			if(modelo.getValueAt(fila, 6).equals("VIGENTE")){
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
				if(Double.parseDouble(txtCantidad.getText())> rangoFin){
					JOptionPane.showMessageDialog(null,"Solo se le puede autorizar la cantidad de $"+rangoFin, "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
				if(Double.parseDouble(txtDescuento.getText())>Double.parseDouble(txtCantidad.getText())){
					JOptionPane.showMessageDialog(null,"El Descuento es mayor que el prestamo", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
				
				Obj_Prestamo pres = new Obj_Prestamo();
				
				switch(tabla.getRowCount()){
					case 0: 
						pres.setFolio(Integer.parseInt(txtFolio_Empleado.getText()));
						pres.setFolio_empleado(Integer.parseInt(txtFolio_Empleado.getText()));
						pres.setNombre_Completo(txtNombre_Completo.getText());
						pres.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
						pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
						pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
						pres.setSaldo(Double.parseDouble(txtCantidad.getText()));
						pres.setStatus(cmbStatus.getSelectedIndex()+1);
						pres.setStatus_descuento(cmbStatus.getSelectedIndex()+1);
		
						pres.guardar();
						
						JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente", "Aviso se guardo el registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						
						if(pres.getStatus_descuento()==1){
							Object[] fila = new Object[tabla.getColumnCount()]; 
							try {
								Obj_Prestamo maximo = new Obj_Prestamo().maximo();
								
								fila[0]=maximo.getFolio();
								fila[1]=new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate());
								fila[2]=txtCantidad.getText();
								fila[3]=txtDescuento.getText();
								fila[4]=txtCantidad.getText();
								fila[5]=0.00;
								
								
								switch(cmbStatus.getSelectedIndex()){
									case 0: fila[6]="VIGENTE";break;	
									case 1: fila[6]="CANCELADO TEMPORAL";break;
								}
								modelo.addRow(fila); 	
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
												
						}
						
						break;
					case 1: 
						if(Double.parseDouble(txtDescuento.getText())> saldo-getDescuentoPrest(folio_empleado)){
							JOptionPane.showMessageDialog(null,"El Descuento que quiere aplicar es mayor que con lo que salda la cuenta", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}
							if(JOptionPane.showConfirmDialog(null, "Desea Actualizar el registro existente ?") == JOptionPane.YES_OPTION) {
								
								pres.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
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
			new Cat_Filtro_Prestamo().setVisible(true);
			
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
		String fechaNull = txtCalendario.getDate()+"";
		if(txtNombre_Completo.getText().equals(""))error+= "Nombre Completo\n";
		if(txtCantidad.getText().equals(""))error+= "Cantidad\n";
		if(txtDescuento.getText().equals(""))error+= "Descuento\n";
		if(fechaNull.equals("null"))error+= "Fecha\n";
				
		return error;
	}

	public String[][] getMatriz(String NombreCompleto){
		String qry = "exec sp_select_prestamo '"+NombreCompleto+"'";
		System.out.println(qry);
		
		String[][] Matriz = new String[getFilas(qry)][7];
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				
					DecimalFormat decimalFormat = new DecimalFormat("#0.00");
										
					Matriz[i][0] = rs.getInt(1)+"";
					Matriz[i][1] = rs.getString(2).trim();
					Matriz[i][2] = decimalFormat.format(Double.parseDouble(rs.getString(3)))+"";
					Matriz[i][3] = decimalFormat.format(Double.parseDouble(rs.getString(4)))+"";
					Matriz[i][4] = decimalFormat.format(Double.parseDouble(rs.getString(5)))+"";
					Matriz[i][5] = decimalFormat.format(Double.parseDouble(rs.getString(6)))+"";
					Matriz[i][6] = rs.getString(7);
				
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
	
	public void suma(){
		float suma = 0;
		
		for(int i=0;i<modelo.getRowCount(); i++) {
			float datos= Float.parseFloat(modelo.getValueAt(i,4).toString());
			suma=(suma+datos); 
		} 
		
		lblTotal.setText("$ "+String.valueOf(suma));
	}
	public float getDescuentoPrest(int folio){
		float valor = 0;
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select sum(descuento)as 'descuento' from tb_abono where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getFloat(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
}
