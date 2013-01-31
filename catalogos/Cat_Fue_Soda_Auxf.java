package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import SQL.Connexion;

import objetos.Obj_Empleado;
import objetos.Obj_fuente_sodas_auxf;

@SuppressWarnings("serial")
public class Cat_Fue_Soda_Auxf extends JDialog{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JLabel txtFolio_Empleado = new JLabel();
	JLabel txtNombre_Completo = new JLabel();
	JTextField txtCantidad = new JTextField();
	JTextField txtFecha = new JTextField();
	
	JLabel btnCalendario = new JLabel(new ImageIcon("imagen//Calendar.png"));
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro = new JButton(new ImageIcon("imagen/Text preview.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnEliminar = new JButton(new ImageIcon("imagen/Delete.png"));
		
	public Cat_Fue_Soda_Auxf(String algo) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Pesos.png"));
		this.setTitle("Fuente de Sodas AuxF");
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
		panel.add(txtFecha).setBounds(x+ancho,y,ancho-15,20);
		panel.add(btnCalendario).setBounds(x+ancho+ancho-27,y,50,20);
		
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
		btnCalendario.addMouseListener(OpCalendario);
		btnEliminar.addActionListener(opEliminar);
		
		txtCantidad.addKeyListener(validaNumericoConPunto);
	
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
			for(int j=0; j<3; j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		suma();
		panelLimpiar();

		this.setModal(true);
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
	        		Obj_fuente_sodas_auxf fuente_sodas = new Obj_fuente_sodas_auxf().buscar(id);
	        		if(fuente_sodas.getStatus_ticket() != 1){
	        			txtFecha.setText(modelo.getValueAt(fila,1)+"");
	        			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
	        			suma();
	        			txtCantidad.setEditable(true);
	        			btnEliminar.setEnabled(true);
	        		}else{
	        			txtFecha.setText(modelo.getValueAt(fila,1)+"");
	        			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
	        			suma();
	        			txtCantidad.setEditable(false);
	        			btnEliminar.setEnabled(false);
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
					fsauxf.setFecha(txtFecha.getText());
					fsauxf.guardar();
					
					Object[] fila = new Object[tabla.getColumnCount()]; 
					Obj_fuente_sodas_auxf maximo = new Obj_fuente_sodas_auxf().maximo();
					fila[0]=maximo.getFolio();
					fila[1]=txtFecha.getText();
					fila[2]=txtCantidad.getText();
					modelo.addRow(fila); 
					suma();
					panelLimpiar();
				}else{
					if(JOptionPane.showConfirmDialog(null, "Seguro que quiere Actualizar el registro "+ modelo.getValueAt(nroFila,0) +" ?") == JOptionPane.YES_OPTION){
						Obj_fuente_sodas_auxf fsauxf = new Obj_fuente_sodas_auxf();
					
						fsauxf.setFecha(txtFecha.getText());
						fsauxf.setCantidad(Double.parseDouble(txtCantidad.getText()));
						fsauxf.actualizar(Integer.parseInt(modelo.getValueAt(nroFila,0)+""));
						modelo.setValueAt(txtFecha.getText(),nroFila,1);
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
		
		if(txtNombre_Completo.getText().equals(""))error+= "Nombre Completo\n";
		if(txtCantidad.getText().equals(""))error+= "Cantidad\n";
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
		String qry = "select folio,fecha,cantidad from tb_fuente_sodas_auxf where nombre_completo='"+NombreCompleto+"' and status='1'";
		
		String[][] Matriz = new String[getFilas(qry)][3];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
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
			float datos= Float.parseFloat(modelo.getValueAt(i,2).toString());
			suma=(suma+datos); 
		} 
		
		lblTotal.setText("$ "+String.valueOf(suma));
	
	}
	
}