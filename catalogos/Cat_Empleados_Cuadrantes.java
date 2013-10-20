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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.BuscarTablasModel;
import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Empleados_Cuadrantes;

@SuppressWarnings("serial")
public class Cat_Empleados_Cuadrantes extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio_Cuadrante = new JTextField();
	JTextField txtCuadrantes = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status",true);
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltorEmpleado = new JButton(new ImageIcon("Iconos/users_icon&16.png"));
	JButton btnCuadrante = new JButton("Cuadrante");
	JButton btnEmpleado = new JButton("Empleados");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar  = new JButton("Guardar");
	

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
	
	public Cat_Empleados_Cuadrantes() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Empleados en Cuadrantes"));	
		
		this.setTitle("Empleados en Cuadrantes");
		
		txtCuadrantes.setEditable(false);

		this.panel.add(new JLabel("Folio:")).setBounds(30,30,50,20);
		this.panel.add(txtFolio_Cuadrante).setBounds(90,30,90,20);
		this.panel.add(btnBuscar).setBounds(190,30,30,20);
		this.panel.add(btnFiltorEmpleado).setBounds(230,30,30,20);
		this.panel.add(btnNuevo).setBounds(290,30,80,20);
		this.panel.add(chStatus).setBounds(380,30,60,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(30,55,70,20);
		this.panel.add(txtCuadrantes).setBounds(90,55,280,20);
		this.panel.add(btnCuadrante).setBounds(380,55,90,20);
		
		this.panel.add(btnEmpleado).setBounds(90,80,90,20);
		
		this.panel.add(btnRemover).setBounds(290,80,80,20);
		this.panel.add(panelScroll).setBounds(30,110,440,270);
		
		this.panel.add(btnSalir).setBounds(30,400,90,20);
		this.panel.add(btnLimpiar).setBounds(200,400,90,20);
		this.panel.add(btnGuardar).setBounds(380,400,90,20);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo de Empleado");
		tabla.getColumnModel().getColumn(1).setMinWidth(370);
		tabla.getColumnModel().getColumn(1).setMaxWidth(370);
		
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
				
				switch(column){
					case 0: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 1: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					
				}
					
				return componente;
			} 
		}; 
		
		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		
		btnCuadrante.addActionListener(opBuscarCuadrante);
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnNuevo.addActionListener(opNuevo);
		btnEmpleado.addActionListener(opBuscarEmpleado);
		btnBuscar.addActionListener(opBuscar);
		btnFiltorEmpleado.addActionListener(opFiltro);
		
			
		btnRemover.addActionListener(opQuitar);
		
		txtFolio_Cuadrante.addKeyListener(valida);
		txtFolio_Cuadrante.addKeyListener(buscaAction);
		btnGuardar.addActionListener(guardar);
		
		chStatus.setEnabled(false);
		this.setSize(500,460);
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	
	
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio_Cuadrante.getText().equals("")){
				
				new Cat_Filtro_Empleados_Cuadrantes().setVisible(true);
				
//				JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
//				return;
			}else {
				Obj_Empleados_Cuadrantes empleado_cuadrante = new Obj_Empleados_Cuadrantes().buscar(Integer.parseInt(txtFolio_Cuadrante.getText()));
				if(empleado_cuadrante.getCuadrante().equals("")){
					JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+txtFolio_Cuadrante.getText(),"Error",JOptionPane.WARNING_MESSAGE);
					limpia();
					return;
				}else{
					chStatus.setEnabled(true);	
					txtCuadrantes.setText(empleado_cuadrante.getCuadrante());
					chStatus.setSelected(empleado_cuadrante.isStatus());
					while(tabla.getRowCount()>0){
						modelo.removeRow(0);
						
					}
					String[][] lista_tabla = Obj_Empleados_Cuadrantes.getTablaCuadrante(Integer.parseInt(txtFolio_Cuadrante.getText()));
					String[] fila = new String[2];
					for(int i=0; i<lista_tabla.length; i++){
						fila[0] = lista_tabla[i][0]+"  ";
						fila[1] = "   "+lista_tabla[i][1];
						modelo.addRow(fila);
					}
				}
			}
		}
	};
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Empleado_Con_Cuadrante().setVisible(true);
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			limpia();
			txtFolio_Cuadrante.setText(new Obj_Empleados_Cuadrantes().nuevoEmpleadoCuadrante()+"");
			txtFolio_Cuadrante.setEditable(false);
			chStatus.setEnabled(true);
			chStatus.setSelected(true);
						
		}
	};
	
	public String ValidaCampos(){
		String error ="";
		if(txtFolio_Cuadrante.getText().equals("")) error+= "Folio\n";
		if(txtCuadrantes.getText().equals("")) error+= "Cuadrante\n";
		if(!(tabla.getRowCount() > 0)) error += "No hay datos en la tabla\n";
		
		return error;
	}
	
	ActionListener guardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(ValidaCampos().equals("")){
				if(new Obj_Empleados_Cuadrantes().existe(Integer.parseInt(txtFolio_Cuadrante.getText()))){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						Obj_Empleados_Cuadrantes empleados_cuadrantes = new Obj_Empleados_Cuadrantes();
						
						empleados_cuadrantes.setFolio(Integer.parseInt(txtFolio_Cuadrante.getText()));
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
					
					empleados_cuadrantes.setFolio(Integer.parseInt(txtFolio_Cuadrante.getText()));
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
	
	public String[] lista_tabla(){
		String[] lista = new String[tabla.getRowCount()];
		for(int i=0; i<tabla.getRowCount(); i++){
			lista[i] = modelo.getValueAt(i,0).toString().trim();
		}
	
		return lista;
	}
	
	ActionListener opBuscarEmpleado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Empleado_Cuadrantes().setVisible(true);
		}
	};
	
	ActionListener opBuscarCuadrante = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
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
	
	
	public void limpia() {
		txtFolio_Cuadrante.setText("");
		txtCuadrantes.setText("");
		chStatus.setEnabled(false);
		chStatus.setSelected(false);
		
		while(modelo.getRowCount() > 0){
			modelo.removeRow(0);
		}
		txtFolio_Cuadrante.requestFocus();
		txtFolio_Cuadrante.setEditable(true);
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
				if (txtFolio_Cuadrante.getText().length()== limite)
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

	class Cat_Filtro_Empleado_Cuadrantes extends JDialog {

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
			this.setModal(true);
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
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
			
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					
					Component componente = null;
					
					switch(column){
						case 0: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
							break;
						case 1: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 2: 
							componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(model1.getValueAt(row,2).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
							break;
						
					}
						
					return componente;
				} 
			}; 
			
			for(int i= 0; i<tabla1.getColumnCount(); i++){
				tabla1.getColumnModel().getColumn(i).setCellRenderer(render); 
			}
			
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
					Matriz[i][0] = rs.getString(1)+"  ";
					Matriz[i][1] = "   "+rs.getString(2);
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
	
	class Cat_Filtro_Cuadrantes extends JDialog {
		
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
			this.setModal(true);
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
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
						case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
						case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					}
				return lbl; 
				} 
			}; 
			
			for(int i= 0; i<tabla2.getColumnCount(); i++){
				tabla2.getColumnModel().getColumn(i).setCellRenderer(render); 
			}
			
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
			String todos = "exec sp_comparar_cuadrante_asignados";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				Matriz = new Object[getFilas(todos)][2];
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1)+"  ";
					Matriz[i][1] = "   "+rs.getString(2);
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
		    			txtCuadrantes.setText(folio.toString().trim());
		        	}
		        }
	        });
	    }
	}
	
//	filtro cuadrante
	public class Cat_Filtro_Empleados_Cuadrantes extends JDialog{
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtNombre = new JTextField();
		JTextField txtFolio = new JTextField();
		
		String columnNames[] = { "Moneda", "Cantidad"};
		
		public Object[][] get_tabla_modificar(){
			return new BuscarTablasModel().tabla_model_filtro_cuadrante();
		}
		
		DefaultTableModel modelo_cuadrante = new DefaultTableModel(this.get_tabla_modificar(), columnNames) {
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
	        	 	case 1 : return false; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla_cuadrantes = new JTable(modelo_cuadrante);
		JScrollPane panelScroll_cuadrante = new JScrollPane(tabla_cuadrantes);
		
		
		DefaultTableModel modelo_empleados = new DefaultTableModel(0,2)	{
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla_empleados = new JTable(modelo_empleados);
		JScrollPane panelScroll_empleado = new JScrollPane(tabla_empleados);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Cat_Filtro_Empleados_Cuadrantes() {
			
			this.setModal(true);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Filtro Cuadrante y Empleados"));	
			
			this.setTitle("Filtro Cuadrante y Empleados");
			
			int x=20; int y=40;
			
			this.panel.add(txtFolio).setBounds(x, 18, 50, 20);
			this.panel.add(txtNombre).setBounds(70, 18, 350, 20);
			this.panel.add(panelScroll_cuadrante).setBounds(x, y, 400, 350);
			
			x=430;
			this.panel.add(panelScroll_empleado).setBounds(x, y, 400, 350);
			
			txtFolio.setDocument(new JTextFieldLimit(9));
			
			
			trsfiltro = new TableRowSorter(modelo_cuadrante); 
			tabla_cuadrantes.setRowSorter(trsfiltro);
			
			txtNombre.addKeyListener(opFiltroNombre);
			txtFolio.addKeyListener(opFiltroFolio);
			
			buscarEmpleado(tabla_cuadrantes);
			filtro_cuadrante(tabla_cuadrantes);
			
			this.cont.add(panel);
			this.setSize(870, 440);
			
			tabla_cuadrantes.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla_cuadrantes.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_cuadrantes.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_cuadrantes.getColumnModel().getColumn(1).setHeaderValue("Cuadrante");
			tabla_cuadrantes.getColumnModel().getColumn(1).setMinWidth(370);
			tabla_cuadrantes.getColumnModel().getColumn(1).setMaxWidth(370);
			
			tabla_empleados.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla_empleados.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_empleados.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_empleados.getColumnModel().getColumn(1).setHeaderValue("Empleado");
			tabla_empleados.getColumnModel().getColumn(1).setMinWidth(370);
			tabla_empleados.getColumnModel().getColumn(1).setMaxWidth(370);
			
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					
					Component componente = null;
					
					switch(column){
						case 0: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
							break;
						case 1: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						
					}
						
					return componente;
				} 
			}; 
			
			this.tabla_cuadrantes.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla_cuadrantes.getColumnModel().getColumn(1).setCellRenderer(render); 
			
			this.tabla_empleados.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla_empleados.getColumnModel().getColumn(1).setCellRenderer(render);
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
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		 private void buscarEmpleado(final JTable tbl) {
		        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			        public void mouseClicked(MouseEvent e) {
			        	if(e.getClickCount() == 1){
			    			int fila_cuadrante = tabla_cuadrantes.getSelectedRow();
			    			int folio =  Integer.parseInt(tabla_cuadrantes.getValueAt(fila_cuadrante, 0).toString().trim());
			    			
								while(tabla_empleados.getRowCount()>0){
									modelo_empleados.removeRow(0);
								}
								String[][] lista_tabla = Obj_Empleados_Cuadrantes.getTablaCuadrante(folio);
								String[] fila = new String[2];
								for(int i=0; i<lista_tabla.length; i++){
									fila[0] = lista_tabla[i][0]+"  ";
									fila[1] = "   "+lista_tabla[i][1];
									modelo_empleados.addRow(fila);
							}
			        	}
			        }
		        });
		    }
		 
		 private void filtro_cuadrante(final JTable tbl) {
		        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			        public void mouseClicked(MouseEvent e) {
			        	if(e.getClickCount() == 2){
			    			int fila_cuadrante = tabla_cuadrantes.getSelectedRow();
			    			int folio =  Integer.parseInt(tabla_cuadrantes.getValueAt(fila_cuadrante, 0).toString().trim());

			    			dispose();
			    			
			    			txtFolio_Cuadrante.setText(folio+"");
			    			btnBuscar.doClick();
			        	}
			        }
		        });
		    }
	}

	public class Cat_Filtro_Empleado_Con_Cuadrante extends JDialog{
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre = new JTextField();
		
		String columnNames[] = { "folio", "Nombre"};
		
		public Object[][] get_tabla_filtro(){
			return new BuscarTablasModel().tabla_filtro_empleados_en_cuadrantes();
		}
		
		DefaultTableModel modelo_empleado = new DefaultTableModel(this.get_tabla_filtro(), columnNames) {
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
	        	 	case 1 : return false; 
	        	 } 				
	 			return false;
	 		}
		};
		
		JTable tabla_empleado = new JTable(modelo_empleado);
		JScrollPane panelScroll_cuadrante = new JScrollPane(tabla_empleado);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Filtro_Empleado_Con_Cuadrante() {
			
			this.setModal(true);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Seleccion de Cuadrante Con Empleado"));	
			
			this.setTitle("Seleccion de Cuadrante Con Empleado");
			
			int x=20; int y=40;
			
			this.panel.add(txtFolio).setBounds(x, 18, 50, 20);
			this.panel.add(txtNombre).setBounds(70, 18, 350, 20);
			
			this.panel.add(panelScroll_cuadrante).setBounds(x, y, 400, 350);
			
			buscarEmpleado(tabla_empleado);
			
			trsfiltro = new TableRowSorter(modelo_empleado); 
			tabla_empleado.setRowSorter(trsfiltro);
			
			txtNombre.addKeyListener(opFiltroNombre);
			txtFolio.addKeyListener(opFiltroFolio);
			
			this.cont.add(panel);
			this.setSize(460, 440);
			
			tabla_empleado.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla_empleado.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_empleado.getColumnModel().getColumn(0).setMinWidth(50);
			tabla_empleado.getColumnModel().getColumn(1).setHeaderValue("Cuadrante");
			tabla_empleado.getColumnModel().getColumn(1).setMinWidth(370);
			tabla_empleado.getColumnModel().getColumn(1).setMaxWidth(370);
			
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					
					Component componente = null;
					
					switch(column){
						case 0: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
							break;
						case 1: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						
					}
						
					return componente;
				} 
			}; 
			
			this.tabla_empleado.getColumnModel().getColumn(0).setCellRenderer(render); 
			this.tabla_empleado.getColumnModel().getColumn(1).setCellRenderer(render); 
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
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		 private void buscarEmpleado(final JTable tbl) {
		        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			        public void mouseClicked(MouseEvent e) {
			        	if(e.getClickCount() == 2){
			    			int fila = tabla_empleado.getSelectedRow();
			    			int folio =  Integer.parseInt(tabla_empleado.getValueAt(fila, 0).toString().trim());
			    			
			    			Obj_Empleados_Cuadrantes empleado_cuadrante = new Obj_Empleados_Cuadrantes().buscar_cuadrante(folio);
			    			
			    			txtFolio_Cuadrante.setText(empleado_cuadrante.getFolio()+"");
			    			btnBuscar.doClick();
			    			dispose();
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






