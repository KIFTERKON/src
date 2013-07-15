package catalogos;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;

import SQL.Connexion;

import objetos.Obj_Empleados_Cuadrantes;

@SuppressWarnings("serial")
public class Cat_Empleados_Cuadrantes extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtCuadrantes = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status",true);
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnCuadrante = new JButton("Cuadrante");
	JButton btnEmpleado = new JButton("Empleados");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar  = new JButton("Guardar");
	
	JButton btnSubir = new JButton(new ImageIcon("Imagen/Up.png"));
	JButton btnBajar = new JButton(new ImageIcon("Imagen/Down.png"));

	JButton btnRemover = new JButton("Quitar");
	
	DefaultTableModel modelo = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};

	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	public Cat_Empleados_Cuadrantes()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Empleados en Cuadrantes"));	
		
		this.setTitle("Empleados en Cuadrantes");
		
		txtCuadrantes.setEditable(false);
		
		int x=30, y=30;
		
		
		this.panel.add(new JLabel("Folio:")).setBounds(30,30,50,20);
		this.panel.add(txtFolio).setBounds(90,30,90,20);
		this.panel.add(chStatus).setBounds(190,30,60,20);
		this.panel.add(btnBuscar).setBounds(250,30,30,20);
		this.panel.add(btnNuevo).setBounds(290,30,80,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(30,55,70,20);
		this.panel.add(txtCuadrantes).setBounds(90,55,280,20);
		this.panel.add(btnCuadrante).setBounds(380,55,90,20);
		
		this.panel.add(btnEmpleado).setBounds(90,80,90,20);
		this.panel.add(btnBajar).setBounds(190,80,40,20);
		this.panel.add(btnSubir).setBounds(240,80,40,20);
		
		this.panel.add(btnRemover).setBounds(290,80,80,20);
		this.panel.add(panelScroll).setBounds(30,110,440,270);
		
		this.panel.add(btnSalir).setBounds(30,400,90,20);
		this.panel.add(btnLimpiar).setBounds(200,400,90,20);
		this.panel.add(btnGuardar).setBounds(380,400,90,20);
		
		
//		this.panel.add(new JLabel("Folio:")).setBounds(x,y,50,20);
//		this.panel.add(txtFolio).setBounds(x+=60,30,90,20);
//		this.panel.add(chStatus).setBounds(x+=100,30,60,20);
//		this.panel.add(btnBuscar).setBounds(x+=60,30,32,20);
//		this.panel.add(btnNuevo).setBounds(x+=40,30,80,20);
//		
//		this.panel.add(new JLabel("Nombre:")).setBounds(x-=260,y+=25,50,20);
//		this.panel.add(txtNombre).setBounds(x+=60,y,280,20);
//		
//		this.panel.add(new JLabel("Cuadrante:")).setBounds(x-=60,y+=25,65,20);
//		this.panel.add(txtCuadrantes).setBounds(x+=60,y,280,20);
//		this.panel.add(btnCuadrante).setBounds(x+=290,y,90,20);
//		this.panel.add(btnEmpleado).setBounds(x-=290,y+=25,90,20);
//		
//		this.panel.add(btnBajar).setBounds(x+=100,y,40,20);
//		this.panel.add(btnSubir).setBounds(x+=50,y,40,20);
//		this.panel.add(btnRemover).setBounds(x+=50,y,80,20);
//		
//		this.panel.add(panelScroll).setBounds(x-=260,y+=25,440,270);
//		
//		this.panel.add(btnSalir).setBounds(x,y+=300,90,20);
//		this.panel.add(btnLimpiar).setBounds(x+=175,y,90,20);
//		this.panel.add(btnGuardar).setBounds(x+=175,y,90,20);
		btnSubir.setToolTipText("Boton de subir");
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo de Empleado");
		tabla.getColumnModel().getColumn(1).setMinWidth(370);
		tabla.getColumnModel().getColumn(1).setMaxWidth(370);
		
		
		btnCuadrante.addActionListener(opBuscarCuadrante);
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnNuevo.addActionListener(opNuevo);
		btnEmpleado.addActionListener(opBuscarEmpleado);
		btnBuscar.addActionListener(opBuscar);
		
		btnSubir.addActionListener(opMover);
		btnBajar.addActionListener(opMover);
		
		btnRemover.addActionListener(opQuitar);
		
		txtFolio.addKeyListener(valida);
		txtFolio.addKeyListener(buscaAction);
		btnGuardar.addActionListener(guardar);
		
		
		
		chStatus.setEnabled(false);
		this.setSize(500,460);
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		cont.add(panel);
	}
	ActionListener opQuitar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.getRowCount()>0){
				if(tabla.isRowSelected(tabla.getSelectedRow())){
					modelo.removeRow(tabla.getSelectedRow());
					
				}else{
					JOptionPane.showMessageDialog(null,"No esta seleccionada ninguna fila!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null,"No hay filas que remover!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opMover = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.getRowCount()>1){
				if(arg0.getSource().equals(btnSubir)){
					if(tabla.getSelectedRow() != 0){
						Object primero = modelo.getValueAt(tabla.getSelectedRow(),1);
						Object segundo = modelo.getValueAt(tabla.getSelectedRow()-1,1);
						
						modelo.setValueAt(primero,tabla.getSelectedRow()-1,1);
						modelo.setValueAt(segundo,tabla.getSelectedRow(),1);	
						tabla.setRowSelectionInterval(tabla.getSelectedRow()-1,tabla.getSelectedRow()-1);
					}else{
						JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
							
				}
				if(arg0.getSource().equals(btnBajar)){
					if(tabla.getSelectedRow()+1 < tabla.getRowCount()){
						Object primero = modelo.getValueAt(tabla.getSelectedRow(),1);
						Object segundo = modelo.getValueAt(tabla.getSelectedRow()+1,1);
						
						modelo.setValueAt(primero,tabla.getSelectedRow()+1,1);
						modelo.setValueAt(segundo,tabla.getSelectedRow(),1);	
						tabla.setRowSelectionInterval(tabla.getSelectedRow()+1,tabla.getSelectedRow()+1);
					
					}else{
						JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
			}
		}
	};
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				Obj_Empleados_Cuadrantes empleado_cuadrante = new Obj_Empleados_Cuadrantes().buscar(Integer.parseInt(txtFolio.getText()));
				if(empleado_cuadrante.getNombre().equals("")){
					JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+txtFolio.getText(),"Error",JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					txtNombre.setText(empleado_cuadrante.getNombre());
					txtCuadrantes.setText(empleado_cuadrante.getCuadrante());
					chStatus.setSelected(empleado_cuadrante.isStatus());
					
					String[][] lista_tabla = Obj_Empleados_Cuadrantes.getTablaCuadrante(empleado_cuadrante.getNombre());
					String[] fila = new String[2];
					for(int i=0; i<lista_tabla.length; i++){
						fila[0] = lista_tabla[i][0];
						fila[1] = lista_tabla[i][1];
						modelo.addRow(fila);
					}
				}
			}
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setText(new Obj_Empleados_Cuadrantes().nuevoEmpleadoCuadrante()+"");
			txtFolio.setEditable(false);
			txtNombre.requestFocus();
		}
	};
	
	public String ValidaCampos(){
		String error ="";
		if(txtFolio.getText().equals("")) error+= "Folio\n";
//		if(txtNombre.getText().equals("")) error+= "Nombre\n";
		if(txtCuadrantes.getText().equals("")) error+= "Cuadrante\n";
		if(!(tabla.getRowCount() > 0)) error += "No hay datos en la tabla\n";
		
		return error;
	}
	
	ActionListener guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(ValidaCampos().equals("")){
				if(new Obj_Empleados_Cuadrantes().existe(txtNombre.getText().toUpperCase())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						Obj_Empleados_Cuadrantes empleados_cuadrantes = new Obj_Empleados_Cuadrantes();
						
						empleados_cuadrantes.setFolio(Integer.parseInt(txtFolio.getText()));
						empleados_cuadrantes.setNombre(txtNombre.getText());
						empleados_cuadrantes.setCuadrante(txtCuadrantes.getText());
						empleados_cuadrantes.setStatus(chStatus.isSelected());
						
						if(empleados_cuadrantes.actualizar(lista_tabla())){
							JOptionPane.showMessageDialog(null,"El registro se actualizo exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							limpia();
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ocurrio un problema al intentar actualizar el registro!","Aviso",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
					}else{
						return;
					}
				}else{
					Obj_Empleados_Cuadrantes empleados_cuadrantes = new Obj_Empleados_Cuadrantes();
					
					empleados_cuadrantes.setFolio(Integer.parseInt(txtFolio.getText()));
					empleados_cuadrantes.setNombre(txtNombre.getText());
					empleados_cuadrantes.setCuadrante(txtCuadrantes.getText());
					empleados_cuadrantes.setStatus(chStatus.isSelected());
					
					if(empleados_cuadrantes.guardar(lista_tabla())){
						JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
						limpia();
						return;
					}else{
						JOptionPane.showMessageDialog(null,"Ocurró un problema al intentar guardar el registro!","Aviso",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos son necesario\n"+ValidaCampos(),"Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	public String[][] lista_tabla(){
		String[][] lista = new String[tabla.getRowCount()][2];
		for(int i=0; i<tabla.getRowCount(); i++){
			lista[i][0] = modelo.getValueAt(i,0).toString();
			lista[i][1] = modelo.getValueAt(i,1).toString();
		}
	
		return lista;
	}
	
	ActionListener opBuscarEmpleado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			new Cat_Filtro_Empleado_Cuadrantes().setVisible(true);
		}
	};
	
	ActionListener opBuscarCuadrante = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			new Cat_Filtro_Cuadrantes().setVisible(true);
		}
	};
	
	ActionListener opSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
		}
	};
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			limpia();
			
		}
	};
	
	
	public void limpia()
	{
		txtFolio.setText("");
		txtCuadrantes.setText("");
		txtNombre.setText("");
		chStatus.setEnabled(false);
		chStatus.setSelected(false);
		
		 while(modelo.getRowCount() > 0){
	    	  modelo.removeRow(0);
		}
		 txtFolio.requestFocus();
		 txtFolio.setEditable(true);
	}
	
	KeyListener valida = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();
			int limite=10;

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
				if (txtFolio.getText().length()== limite)
			     e.consume();
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {}
	};
	
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

	class Cat_Filtro_Empleado_Cuadrantes extends JFrame {

		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Object[][] Matriz ;
		
		Object[][] Tabla = getTabla();
		DefaultTableModel model1 = new DefaultTableModel(Tabla,
	            new String[]{"Folio", "Nombre Completo", "Selección"}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Integer.class,
		    	java.lang.String.class, 
		    	java.lang.Boolean.class	    	
	         };
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 	case 2 : return true; 
	        	 } 				
	 			return false;
	 		}
			
		};
		
		JTable tabla1 = new JTable(model1);
	    JScrollPane scroll = new JScrollPane(tabla1);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnAgregar = new JButton("Agregar");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Empleado_Cuadrantes()	{
			setTitle("Filtro de Empleados");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
			trsfiltro = new TableRowSorter(model1); 
			tabla1.setRowSorter(trsfiltro);  
			
			campo.add(scroll).setBounds(15,42,390,360);
			
			campo.add(txtFolio).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,259,20);
			campo.add(btnAgregar).setBounds(324,20, 80, 20);
			
			
			cont.add(campo);
			
			tabla1.getColumnModel().getColumn(0).setMaxWidth(40);
			tabla1.getColumnModel().getColumn(0).setMinWidth(40);
			tabla1.getColumnModel().getColumn(1).setMaxWidth(250);
			tabla1.getColumnModel().getColumn(1).setMinWidth(250);
			tabla1.getColumnModel().getColumn(2).setMaxWidth(85);
			tabla1.getColumnModel().getColumn(2).setMinWidth(85);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			btnAgregar.addActionListener(Agregar);
			
			setSize(425,450);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		ActionListener Agregar = new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				for(int i=0; i<tabla1.getRowCount(); i++){
					if(Boolean.parseBoolean(model1.getValueAt(i, 2).toString()) == true){
						String[] arreglo = new String[2];
						
						arreglo[0] = model1.getValueAt(i,0).toString();
						arreglo[1] = model1.getValueAt(i,1).toString();
						
						modelo.addRow(arreglo);
						dispose();
					}
				}
			}
		};
		
		KeyListener opFiltroFolio = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
			}
			public void keyTyped(KeyEvent arg0) {
				char caracter = arg0.getKeyChar();
				if(((caracter < '0') ||
					(caracter > '9')) &&
				    (caracter != KeyEvent.VK_BACK_SPACE)){
					arg0.consume(); 
				}	
			}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener opFiltroNombre = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		
	   	public Object[][] getTabla(){
			String todos = "exec sp_compara_empleados_con_cuadrante";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				Matriz = new Object[getFilas(todos)][10];
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim();
					Matriz[i][2] = false;
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    return Matriz; 
		}
	   	
	   	public int getFilas(String qry){
			int filas=0;
			Statement stmt = null;
			try {
				stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					filas++;
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return filas;
		}	
	   	
	   	public void getTabla(int folio){
			String todos1 = "exec sp_select_empleado_cuadrante "+folio;
			Statement stmt = null;
			ResultSet rs;
			Connexion con = new Connexion();
			try {
				stmt = con.conexion().createStatement();
				rs = stmt.executeQuery(todos1);
				Object[] vector = new Object[2];
				while(rs.next()){
					vector[0] = (rs.getString(1));
					vector[1] = (rs.getString(2));
					modelo.addRow(vector);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	
class Cat_Filtro_Cuadrantes extends JFrame {
		
		
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Object[][] Matriz ;
		
		Object[][] Tabla = getTabla();
		DefaultTableModel model2 = new DefaultTableModel(Tabla,
	            new String[]{"Folio", "Cuadrante"}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Integer.class,
		    	java.lang.String.class  	
	         };
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 } 				
	 			return false;
	 		}
			
		};
		
		JTable tabla2 = new JTable(model2);
	    JScrollPane scroll = new JScrollPane(tabla2);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Cuadrantes()	{
			setTitle("Filtro de Cuadrantes");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Cuadrantes"));
			trsfiltro = new TableRowSorter(model2); 
			tabla2.setRowSorter(trsfiltro);  
			
			campo.add(scroll).setBounds(15,42,390,360);
			
			campo.add(txtFolio).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,340,20);
			
			cont.add(campo);
			
			tabla2.getColumnModel().getColumn(0).setMaxWidth(50);
			tabla2.getColumnModel().getColumn(0).setMinWidth(50);
			tabla2.getColumnModel().getColumn(1).setMaxWidth(340);
			tabla2.getColumnModel().getColumn(1).setMinWidth(340);
			agregar(tabla2);
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			setSize(425,450);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		KeyListener opFiltroFolio = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
			}
			public void keyTyped(KeyEvent arg0) {
				char caracter = arg0.getKeyChar();
				if(((caracter < '0') ||
					(caracter > '9')) &&
				    (caracter != KeyEvent.VK_BACK_SPACE)){
					arg0.consume(); 
				}	
			}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener opFiltroNombre = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		
	   	public Object[][] getTabla(){
			String todos = "select tb_cuadrante.folio as [Folio], "+
						         " tb_cuadrante.cuadrante as [Cuadrante] "+ 
						   "from tb_cuadrante";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				Matriz = new Object[getFilas(todos)][2];
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1).trim();
					Matriz[i][1] = rs.getString(2).trim();
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    return Matriz; 
		}
	   	
	   	public int getFilas(String qry){
			int filas=0;
			Statement stmt = null;
			try {
				stmt = new Connexion().conexion().createStatement();
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					filas++;
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return filas;
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
		
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		    			int fila = tabla2.getSelectedRow();
		    			Object folio =  tabla2.getValueAt(fila, 1);
		    			dispose();
		    			txtCuadrantes.setText(folio.toString());
		        	}
		        }
	        });
	    }
	}
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Empleados_Cuadrantes().setVisible(true);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}






