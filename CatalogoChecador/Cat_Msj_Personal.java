package CatalogoChecador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import objetos.JTextFieldLimit;
import objetos.Obj_Empleados_Cuadrantes;

import ObjetoChecador.Obj_Mensaje_Personal;
import SQL.Connexion;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Msj_Personal extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolioMsj = new JTextField();
	
	JDateChooser txtFechaInicio = new JDateChooser();
	JDateChooser txtFechaFin = new JDateChooser();
	
//	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnFiltroMSJ = new JButton("Filtro");
	JButton btnSiguiente = new JButton("Siguiente");
	JButton btnAnterior = new JButton("Anterior");
	
	JTextArea txaMensaje = new JTextArea();
	JScrollPane Mensaje = new JScrollPane(txaMensaje);
	
	JTextField txtAsunto = new JTextField();
	JCheckBox chStatus = new JCheckBox("Status",true);
	
//	-----------------------------------------------------------------------------------------------------------------------
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnFiltro = new JButton("Filtro");
	JButton btnEmpleado = new JButton("Empleados");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSalir = new JButton("Salir");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar  = new JButton("Guardar");
	
	JButton btnSubir = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnBajar = new JButton(new ImageIcon("Iconos/down_icon&16.png"));

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
	
	public Cat_Msj_Personal(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_user_icon&16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Empleados en Mensajes"));	
		
		this.setTitle("Mensaje Personal");
		
		Font font = new Font("Verdana", Font.BOLD, 14);
		txaMensaje.setFont(font);
		
		this.txtFechaInicio.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.txtFechaFin.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		txtAsunto.setEditable(false);

		this.panel.add(new JLabel("Folio:")).setBounds(30,30,50,20);
		this.panel.add(txtFolioMsj).setBounds(90,30,90,20);
		this.panel.add(chStatus).setBounds(190,30,60,20);
		this.panel.add(btnBuscar).setBounds(250,30,30,20);
		this.panel.add(btnNuevo).setBounds(290,30,80,20);
	
		this.panel.add(new JLabel("Asunto:")).setBounds(30,55,70,20);
		this.panel.add(txtAsunto).setBounds(90,55,280,20);
		this.panel.add(btnFiltro).setBounds(280,80,90,20);
	
		this.panel.add(new JLabel("Fecha Inicio:")).setBounds(30,80,90,20);
		this.panel.add(txtFechaInicio).setBounds(90,80,110,20);
		
		this.panel.add(new JLabel("Fecha Final:")).setBounds(30,105,90,20);
		this.panel.add(txtFechaFin).setBounds(90,105,110,20);

		this.panel.add(btnBajar).setBounds(210,105,40,20);
		this.panel.add(btnSubir).setBounds(210,80,40,20);
		this.panel.add(Mensaje).setBounds(30,135,440,245);
		
		int x=500;
		
		this.panel.add(btnEmpleado).setBounds(90+x,80,90,20);
		
		this.panel.add(btnRemover).setBounds(290+x,80,80,20);
		this.panel.add(panelScroll).setBounds(30+x,110,440,270);
		
		this.panel.add(btnSalir).setBounds(30+x,400,90,20);
		this.panel.add(btnLimpiar).setBounds(200+x,400,90,20);
		this.panel.add(btnGuardar).setBounds(380+x,400,90,20);
		
		btnSubir.setToolTipText("Boton de subir");
		
		txaMensaje.setLineWrap(true); 
		txaMensaje.setWrapStyleWord(true);
		txaMensaje.setDocument(new JTextFieldLimit(800));
		
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
		
		btnFiltro.addActionListener(opBuscarMensaje);
		btnSalir.addActionListener(opSalir);
		btnLimpiar.addActionListener(opLimpiar);
		btnNuevo.addActionListener(opNuevo);
		btnEmpleado.addActionListener(opBuscarEmpleado);
		btnBuscar.addActionListener(opBuscar);
		
		btnSubir.addActionListener(opMover);
		btnBajar.addActionListener(opMover);
		
		btnRemover.addActionListener(opQuitar);
		
		txtFolioMsj.addKeyListener(valida);
		txtFolioMsj.addKeyListener(buscaAction);
		btnGuardar.addActionListener(guardar);
		
		chStatus.setEnabled(true);
		this.setSize(1000,460);
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
	
	ActionListener opMover = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource().equals(btnSubir)){
					if(txtFolioMsj.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						Obj_Mensaje_Personal MsjPresonal = new Obj_Mensaje_Personal().buscar(Integer.parseInt(txtFolioMsj.getText())+1);
						if(MsjPresonal.getAsunto().equals("")){
							JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+(Integer.parseInt(txtFolioMsj.getText())+1)+"","Error",JOptionPane.WARNING_MESSAGE);
							return;
						}else{
							txtFolioMsj.setText((Integer.parseInt(txtFolioMsj.getText())+1)+"");
							txtAsunto.setText(MsjPresonal.getAsunto());
							txaMensaje.setText(MsjPresonal.getMensaje());
							
								try {
									Date date_inicial = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaInicial());
									Date date_fin = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaFin());
									txtFechaInicio.setDate(date_inicial);
									txtFechaFin.setDate(date_fin);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
							
							chStatus.setSelected(MsjPresonal.getStatus());
							
							////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
							/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
							/**/	   		 getTabla(Integer.parseInt(txtFolioMsj.getText()));			/**/
							////////////////////////////////////////////////////////////////////////////////
						}
					}
							
				}
				if(arg0.getSource().equals(btnBajar)){
					if(txtFolioMsj.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						Obj_Mensaje_Personal MsjPresonal = new Obj_Mensaje_Personal().buscar(Integer.parseInt(txtFolioMsj.getText())-1);
						if(MsjPresonal.getAsunto().equals("")){
							JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+(Integer.parseInt(txtFolioMsj.getText())-1)+"","Error",JOptionPane.WARNING_MESSAGE);
							return;
						}else{
							txtFolioMsj.setText((Integer.parseInt(txtFolioMsj.getText())-1)+"");
							txtAsunto.setText(MsjPresonal.getAsunto());
							txaMensaje.setText(MsjPresonal.getMensaje());
							
								try {
									Date date_inicial = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaInicial());
									Date date_fin = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaFin());
									txtFechaInicio.setDate(date_inicial);
									txtFechaFin.setDate(date_fin);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
							
							chStatus.setSelected(MsjPresonal.getStatus());
							
							////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
							/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
							/**/	   		 getTabla(Integer.parseInt(txtFolioMsj.getText()));			/**/
							////////////////////////////////////////////////////////////////////////////////
						}
					}
				}
			txtFolioMsj.setEnabled(false);
			txtAsunto.setEditable(true);
		}
	};
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolioMsj.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el folio para poder realizar la busqueda","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				Obj_Mensaje_Personal MsjPresonal = new Obj_Mensaje_Personal().buscar(Integer.parseInt(txtFolioMsj.getText()));
				if(MsjPresonal.getAsunto().equals("")){
					JOptionPane.showMessageDialog(null, "No existe el registro con el folio: "+txtFolioMsj.getText()+"","Error",JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					
					txtAsunto.setText(MsjPresonal.getAsunto());
					txaMensaje.setText(MsjPresonal.getMensaje());
					
						try {
							Date date_inicial = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaInicial());
							Date date_fin = new SimpleDateFormat("dd/MM/yyyy").parse(MsjPresonal.getFechaFin());
							txtFechaInicio.setDate(date_inicial);
							txtFechaFin.setDate(date_fin);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					
					chStatus.setSelected(MsjPresonal.getStatus());
					
					
					////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
					/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
					/**/	   		 getTabla(Integer.parseInt(txtFolioMsj.getText()));			/**/
					////////////////////////////////////////////////////////////////////////////////
					txtFolioMsj.setEnabled(false);
					txtAsunto.setEditable(true);
				}
			}
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			txtFolioMsj.setText(new Obj_Empleados_Cuadrantes().nuevoEmpleadoCuadrante()+"");
			txtFolioMsj.setText(new Obj_Mensaje_Personal().nuevoMensaje()+"");
			txtFolioMsj.setEditable(false);
			
			
			chStatus.setSelected(true);
			txtAsunto.setEditable(true);
			txtAsunto.requestFocus();
		}
	};
	
	public String ValidaCampos(){
		String error ="";
		if(txtFolioMsj.getText().equals("")) error+= "Folio\n";
		if(txtAsunto.getText().equals("")) error+= "Cuadrante\n";
		if(!(tabla.getRowCount() > 0)) error += "No hay datos en la tabla\n";
		
		return error;
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){

			if(txtFolioMsj.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(validacampos().equals("")){
					Obj_Mensaje_Personal MSJ = new Obj_Mensaje_Personal().buscar(Integer.parseInt(txtFolioMsj.getText()));
					
					if(MSJ.getFolioMensaje() == Integer.parseInt(txtFolioMsj.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
						{
							
							MSJ.setFolioMensaje(Integer.parseInt(txtFolioMsj.getText()));
							MSJ.setFechaInicial(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaInicio.getDate()));
							MSJ.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaFin.getDate()));
							MSJ.setAsunto(txtAsunto.getText().toUpperCase());
							MSJ.setMensaje(txaMensaje.getText().toUpperCase());
							
							MSJ.setStatus(chStatus.isSelected());
							
							if(MSJ.actualizar(Integer.parseInt(txtFolioMsj.getText()))){
									MSJ.actualizar2(listadatos());
										JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
										return;
							}
						}
					}else{
						
						MSJ.setFolioMensaje(Integer.parseInt(txtFolioMsj.getText()));
						MSJ.setFechaInicial(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaInicio.getDate()));
						MSJ.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaFin.getDate()));
						MSJ.setAsunto(txtAsunto.getText().toLowerCase());
						MSJ.setMensaje(txaMensaje.getText().toUpperCase());
						
						MSJ.setStatus(chStatus.isSelected());
						
						if(MSJ.guardar_mensaje()){
							if(modelo.getRowCount() > 0){
								MSJ.guardar_Empleado_Mensaje(listadatos());
									JOptionPane.showMessageDialog(null,"El Registro se guardo Exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
								}else{
									JOptionPane.showMessageDialog(null,"A Guardado Mensaje Sin Asignarselo a Un Empleado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
								}
						}else{
							JOptionPane.showMessageDialog(null,"El Registro no se a guardado!","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+validacampos(),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
				
			}
		}
	};
	
	public  String[] listadatos()
	{
		String[] matriz=new String[tabla.getRowCount()];
		for (int i = 0; i < tabla.getRowCount(); i++) {
				matriz[i]=modelo.getValueAt(i,0).toString();
		}
		return matriz;
	}
	
	public void getTabla(int folio){
		String todos1 = "exec sp_select_empleado_mensaje "+folio;
		Statement stmt = null;
		ResultSet rs;
		Connexion con = new Connexion();
		try {
			stmt = con.conexion().createStatement();
			rs = stmt.executeQuery(todos1);
			Object[] vector = new Object[2];
			while(rs.next()){
				vector[0] = (rs.getInt(1));
				vector[1] = (rs.getString(2));
				modelo.addRow(vector);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	ActionListener opBuscarEmpleado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Empleado_Mensajes().setVisible(true);
		}
	};
	
	ActionListener opBuscarMensaje = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Mensaje().setVisible(true);
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
		txtFolioMsj.setText("");
		txtAsunto.setText("");
		chStatus.setEnabled(false);
		chStatus.setSelected(false);
		txtFechaInicio.setDate(null);
		txtFechaFin.setDate(null);
		
		while(modelo.getRowCount() > 0){
			modelo.removeRow(0);
		}
		txtFolioMsj.requestFocus();
		txtFolioMsj.setEnabled(true);
		txtAsunto.setEditable(false);
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
				if (txtFolioMsj.getText().length()== limite)
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

	class Cat_Filtro_Empleado_Mensajes extends JFrame {

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
		
		public Cat_Filtro_Empleado_Mensajes()	{
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
	
	class Cat_Filtro_Mensaje extends JFrame {
		
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
		
		public Cat_Filtro_Mensaje()	{
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			setTitle("Filtro de Mwnsajes");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Mensajes"));
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
			String todos = "select folio_mensaje as folio_mensaje," +
							" asunto as asunto " +
							"from tb_mensaje_personal";
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
		    			Object folio =  tabla2.getValueAt(fila, 0);
		    			dispose();
		    			
		    			txtFolioMsj.setText(folio.toString().trim());
		    			txtFolioMsj.setEnabled(false);
		    			txtAsunto.setEditable(true);
		    			btnBuscar.doClick();
		        	}
		        }
	        });
	    }
	}
	
	public String validacampos(){
		String error="";
		String fechaNullInicio = txtFechaInicio.getDate()+"";
		String fechaNullFin = txtFechaInicio.getDate()+"";
		
		if (txtAsunto.getText().equals("")){error+="Asunto\n";}
		if(fechaNullInicio.equals("null"))error+= "Fecha de Inicio\n";	
		if(fechaNullFin.equals("null"))error += "Fecha de Fin\n";
		if (txaMensaje.getText().equals("")){error+="Mensaje\n";}
		return error;
	}
	
}
