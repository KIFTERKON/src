package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;

import objetos.Obj_Empleado;
import objetos.Obj_fuente_sodas_auxf;

@SuppressWarnings("serial")
public class Cat_Fue_Soda_Auxf extends JFrame {

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JLabel txtFolio_Empleado = new JLabel();
	JLabel txtNombre_Completo = new JLabel();
	JTextField txtCantidad = new JTextField();

	com.toedter.calendar.JDateChooser txtCalendario = new com.toedter.calendar.JDateChooser();
	
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnEliminar = new JButton(new ImageIcon("imagen/Delete.png"));
		
	public Cat_Fue_Soda_Auxf(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Accounting.png"));
		this.setTitle("Fuente de Sodas Auxiliar y Finanzas");
		int x = 40, y=30, ancho=140;
		txtCantidad.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Fuente de Sodas AuxF"));
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio_Empleado).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(new JLabel("Nombre Completo:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtNombre_Completo).setBounds(x+ancho,y,ancho*2,20);
		
		panel.add(btnFiltro).setBounds(x+ancho+ancho+90,y,32,20);	
		panel.add(panelScroll).setBounds(x+ancho+x+40+ancho+ancho-80+30,y,ancho+130,260);
		panel.add(btnEliminar).setBounds(x+ancho+x+40+ancho+ancho-80+30+ancho+130,y,32,20);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Fecha");
		tabla.getColumnModel().getColumn(1).setMinWidth(80);
		tabla.getColumnModel().getColumn(1).setMaxWidth(80);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Cantidad");
		agregar(tabla);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
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
		
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCalendario).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x,y+=25,ancho,20);
		panel.add(txtCantidad).setBounds(x+ancho,y,ancho-15,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=35,ancho-20,20);
		panel.add(btnSalir).setBounds(x+ancho+10,y,ancho-20,20);
		panel.add(btnGuardar).setBounds(x+ancho+ancho+20,y,ancho-20,20);
		
		lblTotal.setFont(new java.awt.Font("Algerian",0,60));
		panel.add(lblTotal).setBounds(ancho-30,y, 400, 200);
		
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(salir);
		btnDeshacer.addActionListener(deshacer);
		btnFiltro.addActionListener(filtro);
		btnEliminar.addActionListener(opEliminar);
		
		txtCantidad.addKeyListener(validaNumericoConPunto);
	
		cont.add(panel);
		
		Obj_Empleado re = new Obj_Empleado();
		
		re = re.buscar(Integer.parseInt(algo));
		txtFolio_Empleado.setText(re.getFolio()+"");
		txtNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");	
		
		panelEnabledTrue();
		
		String[][] Tabla = getMatriz(txtNombre_Completo.getText());
		Object[] fila = new Object[tabla.getColumnCount()]; 
		for(int i=0; i<Tabla.length; i++){
			modelo.addRow(fila); 
			for(int j=0; j<3; j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		suma();
		panelLimpiar();

		this.setSize(805,390);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

	}
	
	ActionListener opEliminar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			int cantidadFilasSeleccionadas = tabla.getSelectedRowCount();
	 		if(cantidadFilasSeleccionadas > 0) {
	 			int nroFila = tabla.getSelectedRow();
				if(JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar el registro "+ modelo.getValueAt(nroFila,0) +" ?") == JOptionPane.YES_OPTION){
					Obj_fuente_sodas_auxf fuente_sodas = new Obj_fuente_sodas_auxf();
	 				fuente_sodas.borrar(Integer.parseInt(modelo.getValueAt(nroFila,0)+""));
	 				modelo.removeRow(nroFila);
	 				suma();
	 				panelLimpiar();
				}else{
					return;
				}
            }else {
            	JOptionPane.showMessageDialog(null, "Seleccione un renglon para eliminar", "Aviso!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
            }
		}
	};
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        		try {
						Obj_fuente_sodas_auxf fuente_sodas = new Obj_fuente_sodas_auxf().buscar(id);
						
						if(fuente_sodas.getStatus_ticket() != 1){
							try {
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(fila,1)+"");
								txtCalendario.setDate(date);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							
		        			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
		        			suma();
		        			btnEliminar.setEnabled(true);
		        		}else{
		        			try {
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(fila,1)+"");
								txtCalendario.setDate(date);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
		        			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
		        			suma();
		        			btnEliminar.setEnabled(true);
		        		}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	        	
	    			
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				int cantidadFilasSeleccionadas = tabla.getSelectedRowCount();
				int nroFila = tabla.getSelectedRow();
				if(cantidadFilasSeleccionadas == 0){
					Obj_fuente_sodas_auxf fsauxf = new Obj_fuente_sodas_auxf();
					
					fsauxf.setFolio(Integer.parseInt(txtFolio_Empleado.getText()));
					fsauxf.setNombre_Completo(txtNombre_Completo.getText());
					fsauxf.setCantidad(Double.parseDouble(txtCantidad.getText()));
					fsauxf.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
					fsauxf.guardar();
					
					Object[] fila = new Object[tabla.getColumnCount()]; 
					try {
						Obj_fuente_sodas_auxf maximo = new Obj_fuente_sodas_auxf().maximo();
						
						fila[0]=maximo.getFolio();
						fila[1]=new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate());
						fila[2]=txtCantidad.getText();
						modelo.addRow(fila); 
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					suma();
					panelLimpiar();
				}else{
					if(JOptionPane.showConfirmDialog(null, "Seguro que quiere Actualizar el registro "+ modelo.getValueAt(nroFila,0) +" ?") == JOptionPane.YES_OPTION){
						Obj_fuente_sodas_auxf fsauxf = new Obj_fuente_sodas_auxf();
					
						fsauxf.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
						fsauxf.setCantidad(Double.parseDouble(txtCantidad.getText()));
						fsauxf.actualizar(Integer.parseInt(modelo.getValueAt(nroFila,0)+""));
						modelo.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()),nroFila,1);
						modelo.setValueAt(txtCantidad.getText(),nroFila,2);
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
			new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
			
		}
	};	
	
	public void panelEnabledTrue(){	
		txtCantidad.setEditable(true);
		
	}
	
	public void panelEnabledFalse(){	
		txtCantidad.setEditable(false);
		
	}
	
	public void panelLimpiar(){	
		panelEnabledFalse();
		panelEnabledTrue();
		txtCantidad.setText("");
		txtCantidad.requestFocus();
		tabla.setSelectionMode(0);
		
	}
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelLimpiar();
			panelEnabledTrue();
			txtCantidad.setText("");
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
		String fechaNull = txtCalendario.getDate()+"";
		if(txtNombre_Completo.getText().equals(""))error+= "Nombre Completo\n";
		if(txtCantidad.getText().equals(""))error+= "Cantidad\n";
		if(fechaNull.equals("null"))error+= "Fecha\n";
				
		return error;
	}

	public String[][] getMatriz(String NombreCompleto){
		String qry = "select folio,fecha,cantidad from tb_fuente_sodas_auxf where nombre_completo='"+NombreCompleto+"' and status='1'";
		
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
				Matriz[i][2] = decimalFormat.format(Float.parseFloat(rs.getString(3).trim()));

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
			float datos= Float.parseFloat(modelo.getValueAt(i,2).toString());
			suma=(suma+datos); 
		} 
		
		lblTotal.setText("$ "+String.valueOf(suma));
	
	}
	
}