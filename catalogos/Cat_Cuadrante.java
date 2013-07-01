package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;

import objetos.JTextFieldLimit;
import objetos.Obj_Atributos;
import objetos.Obj_Cuadrante;
import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Cat_Cuadrante extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	

JTabbedPane tablapane = new JTabbedPane();
	 JPanel pLunes = new JPanel(); 
	 JPanel pMartes = new JPanel();
	 JPanel pMiercoles = new JPanel(); 
	 JPanel pJueves = new JPanel(); 
	 JPanel pViernes = new JPanel(); 
	 JPanel pSabado = new JPanel(); 
	 JPanel pDomingo = new JPanel(); 
	 JButton sNivel = new JButton(new ImageIcon("imagen/Arriva.jpg"));
	 JButton bNivel = new JButton(new ImageIcon("imagen/Abajo.jpg"));
	
Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	
	JTextArea txaNombre = new JTextArea(4,4);
//	JScrollPane Nombre = new JScrollPane(txaNombre);
	
	JTextArea txaDescripcion = new JTextArea(4,4);
//	JScrollPane Descripcion = new JScrollPane(txaDescripcion);
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String dias[] = {"Seleccione un dia","Lunes","Martes","Miercoles","Jueves","Viernes","Savado","Domingo"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDias = new JComboBox(dias);
	
	String eqTrabajo[] = new Obj_Establecimiento().Combo_Eq_Trabajo();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEqTrabajo = new JComboBox(eqTrabajo);
	
	String jefatura[] = new Obj_Establecimiento().Combo_Jefatura();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbJefatura = new JComboBox(jefatura);
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JCheckBox chTodos = new JCheckBox("Todos");
	JCheckBox chLunes = new JCheckBox("Lunes");
	JCheckBox chMartes = new JCheckBox("Martes");
	JCheckBox chMiercoles = new JCheckBox("Miercoles");
	JCheckBox chJueves = new JCheckBox("Jueves");
	JCheckBox chViernes = new JCheckBox("Viernes");
	JCheckBox chSabado = new JCheckBox("Sabado");
	JCheckBox chDomingo = new JCheckBox("Domingo");
	
	JSpinner spnNGerarquico = new JSpinner(new SpinnerNumberModel(10,0,20,1));
	
//	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
//	JButton btnSalir = new JButton("Salir");
//	JButton btnDeshacer = new JButton("Deshacer");
//	JButton btnGuardar = new JButton("Guardar");
//	JButton btnEditar = new JButton("Editar");
//	JButton btnNuevo = new JButton("Nuevo Cuadrante");
	
	public Cat_Cuadrante(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Cuadrante"));
		
		this.setTitle("Cuadrante");
		
		int x = 15, y=30, ancho=100;
		
		Border border = BorderFactory.createLineBorder(new Color(80,80,100));
		txaNombre.setBorder(border);
		txaDescripcion.setBorder(border);
		
		panelEnabledFalse();
		txtFolio.setEditable(true);
		
		panel.add(new JLabel("Folio:")).setBounds(5,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho+10,y,ancho,20);
//		panel.add(btnBuscar).setBounds(x+ancho+ancho,y,32,20);
		
		panel.add(chStatus).setBounds(x+33+(ancho*2),y,60,20);
//		panel.add(btnNuevo).setBounds(x+295,y,ancho+20,20);
		
		panel.add(new JLabel("Cuadrante:")).setBounds(5,y+=30,ancho+40,20);
		panel.add(txaNombre).setBounds(ancho+10,y,ancho*3+20,100);
		
		panel.add(new JLabel("Perfil del Cuadrante: ")).setBounds(ancho*4+50,30,ancho+ancho,20);
		panel.add(txaDescripcion).setBounds(ancho*4+50,60,ancho*3,200);
		
		panel.add(new JLabel("Establecimiento:")).setBounds(5,y+=110,ancho,20);
		panel.add(cmbEstablecimiento).setBounds(ancho+10,y,ancho+ancho,20);
		panel.add(new JLabel("Nivel Gerarquico: ")).setBounds(5,y+=30,ancho,20);
		panel.add(spnNGerarquico).setBounds(ancho+10,y,50,20);
		
		panel.add(new JLabel("Dia:")).setBounds(5,y+=90,ancho,20);
		
		panel.add(chTodos).setBounds(75,y,60,20);
		panel.add(chDomingo).setBounds(140,y,80,20);
		panel.add(chLunes).setBounds(220,y,60,20);
		panel.add(chMartes).setBounds(285,y,70,20);
		panel.add(chMiercoles).setBounds(355,y,85,20);
		panel.add(chJueves).setBounds(440,y,70,20);
		panel.add(chViernes).setBounds(510,y,70,20);
		panel.add(chSabado).setBounds(585,y,80,20);
		
		panel.add(new JLabel("Eq. Trabajo:")).setBounds(5,y+=30,ancho,20);
		panel.add(cmbEqTrabajo).setBounds(ancho+10,y,ancho+ancho,20);
		panel.add(new JLabel("Jefatura:")).setBounds(5,y+=30,ancho,20);
		panel.add(cmbJefatura).setBounds(ancho+10,y,ancho+ancho,20);
		
//		panel.add(btnEditar).setBounds(x+300,y,ancho,20);
//		panel.add(btnDeshacer).setBounds(x+ancho+90,y+=30,ancho,20);
//		panel.add(btnSalir).setBounds(x+80,y,ancho,20);
//		panel.add(btnGuardar).setBounds(x+300,y,ancho,20);
		
//		panel.add(getPanelTabla()).setBounds(5,y+=30,ancho*7+50,300);
		
		txtFolio.setDocument(new JTextFieldLimit(9));
		txaNombre.setDocument(new JTextFieldLimit(100));
		
		txaNombre.setLineWrap(true); 
		txaNombre.setWrapStyleWord(true);
		txaNombre.setDocument(new JTextFieldLimit(250));
		
		txaDescripcion.setLineWrap(true); 
		txaDescripcion.setWrapStyleWord(true);
		txaDescripcion.setDocument(new JTextFieldLimit(500));
		
		chStatus.setEnabled(false);
		cmbEstablecimiento.setEditable(false);
		
		panel.add(tablapane).setBounds(5,y+=30,ancho*7+50,300);
			
		
		  pDomingo.setOpaque(true); 
		  pDomingo.setBackground(Color.white);
		  tablapane.addTab("Domingo", pDomingo);
		  
		  pLunes.add("Tabla",getPanelTabla()).setBounds(5000000,0,0,0);
		 
		  pLunes.setOpaque(true); 
		  pLunes.setBackground(Color.gray);
		  tablapane.addTab("Lunes", pLunes);
		  
		  pMartes.setOpaque(true); 
		  pMartes.setBackground(Color.white);
		  tablapane.addTab("Martes", pMartes);
		  
		  pMiercoles.setOpaque(true); 
		  pMiercoles.setBackground(Color.gray);
		  tablapane.addTab("Miercoles", pMiercoles);
		  
		  pJueves.setOpaque(true); 
		  pJueves.setBackground(Color.white);
		  tablapane.addTab("Jueves", pJueves);
		  
		  pViernes.setOpaque(true); 
		  pViernes.setBackground(Color.gray);
		  tablapane.addTab("Viernes", pViernes);
		  
		  pSabado.setOpaque(true); 
		  pSabado.setBackground(Color.white);
		  tablapane.addTab("Sabado", pSabado);
		  
		  panel.add(sNivel).setBounds(550,360,20,20);
		  panel.add(new JLabel("Subir Nivel")).setBounds(575,360,70,20);
		  
		  panel.add(bNivel).setBounds(640,360,20,20);
		  panel.add(new JLabel("Bajar Nivel")).setBounds(665,360,70,20);
		  
//		btnGuardar.setEnabled(false);
//		btnDeshacer.setEnabled(false);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
		txtFolio.addKeyListener(numerico_action);
		
//		btnGuardar.addActionListener(guardar);
//		btnSalir.addActionListener(cerrar);
//		btnBuscar.addActionListener(buscar);
//		btnDeshacer.addActionListener(deshacer);
//		btnNuevo.addActionListener(nuevo);
//		btnEditar.addActionListener(editar);
//		btnEditar.setEnabled(false);
		
		cont.add(panel);
		
		agregar(tabla);
		
		this.setSize(765,750);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(40);
		tabla.getColumnModel().getColumn(0).setMinWidth(40);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Actividad");
		tabla.getColumnModel().getColumn(1).setMinWidth(665);
		tabla.getColumnModel().getColumn(1).setMaxWidth(665);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(2).setMinWidth(40);
		tabla.getColumnModel().getColumn(2).setMaxWidth(40);
		
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
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select * from tb_cuadrante");
			

			while (rs.next())
			{ 
			   String [] fila = new String[8];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   
			   fila[2] = rs.getString(8).trim();
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
					Obj_Cuadrante cuadrante = new Obj_Cuadrante().buscar(id);
						
					txtFolio.setText(id+"");
					txaNombre.setText(modelo.getValueAt(fila,1)+"");
					cmbEstablecimiento.setSelectedIndex(cuadrante.getEstablecimiento());
					spnNGerarquico.setValue(cuadrante.getNivel_gerarquico());
					cmbDias.setSelectedIndex(cuadrante.getDia());
					cmbEqTrabajo.setSelectedIndex(cuadrante.getEq_trabajo());
					cmbJefatura.setSelectedIndex(cuadrante.getJefatura());
					chStatus.setSelected(true);
					txaDescripcion.setText(cuadrante.getDescripcion().trim());
						
					txtFolio.setEditable(false);
					txaNombre.setEditable(false);
					cmbEstablecimiento.setEnabled(false);
					
					spnNGerarquico.setEnabled(false);
						
					cmbDias.setEnabled(false);
					cmbEqTrabajo.setEnabled(false);
					cmbJefatura.setEnabled(false);
					chStatus.setEnabled(false);
					txaDescripcion.setEditable(false);
						
//					btnEditar.setEnabled(true);
//					btnGuardar.setEnabled(false);
					
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			System.out.println(Integer.parseInt(spnNGerarquico.getValue().toString()));
			if(validaCamposCmb()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCamposCmb(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{

			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Cuadrante cuadrante = new Obj_Cuadrante().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(cuadrante.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							cuadrante.setNombre(txaNombre.getText().toUpperCase());
							cuadrante.setEstablecimiento(cmbEstablecimiento.getSelectedIndex());
							cuadrante.setNivel_gerarquico(Integer.parseInt(spnNGerarquico.getValue().toString()));
							cuadrante.setDia(cmbDias.getSelectedIndex());
							cuadrante.setEq_trabajo(cmbEqTrabajo.getSelectedIndex());
							cuadrante.setJefatura(cmbJefatura.getSelectedIndex());
							cuadrante.setStatus(chStatus.isSelected());
							cuadrante.setDescripcion(txaDescripcion.getText().toUpperCase());
							
							cuadrante.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txaNombre.getText().toUpperCase(),nroFila,1);
							modelo.setValueAt(cmbEstablecimiento.getSelectedItem(), nroFila, 2);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						cuadrante.setNombre(txaNombre.getText().toUpperCase());
						cuadrante.setEstablecimiento(cmbEstablecimiento.getSelectedIndex());
						cuadrante.setNivel_gerarquico(Integer.parseInt(spnNGerarquico.getValue().toString()));
						cuadrante.setDia(cmbDias.getSelectedIndex());
						cuadrante.setEq_trabajo(cmbEqTrabajo.getSelectedIndex());
						cuadrante.setJefatura(cmbJefatura.getSelectedIndex());
						cuadrante.setStatus(chStatus.isSelected());
						cuadrante.setDescripcion(txaDescripcion.getText().toUpperCase());
						cuadrante.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txaNombre.getText().toUpperCase();
						fila[2]=chStatus.isSelected();
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}
		}
		}
	};
	
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
//				btnBuscar.doClick();
			}
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
	
	ActionListener buscar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			Obj_Atributos atrib = new Obj_Atributos();
			atrib = atrib.buscar(Integer.parseInt(txtFolio.getText()));
			
			if(atrib.getFolio() != 0){
			
			txtFolio.setText(atrib.getFolio()+"");
			txaNombre.setText(atrib.getDescripcion()+"");
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			cmbEstablecimiento.setSelectedIndex(Integer.parseInt(atrib.getValor()+""));
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
			System.out.println(atrib.getStatus());
			if(atrib.getStatus() == true){chStatus.setSelected(true);}
			else{chStatus.setSelected(false);}
			
//			btnNuevo.setEnabled(false);
//			btnEditar.setEnabled(false);
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			
			}
			else{
				JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
				return;
				}
			}
		}
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	private String validaCampos(){
		String error="";
		if(txaNombre.getText().equals("")) 			error+= "Bono\n";
				
		return error;
	}
	private String validaCamposCmb(){
		String error="";
		if(cmbEstablecimiento.getSelectedIndex()==0) 			error+= " Establecimiento\n";
		if(cmbDias.getSelectedIndex()==0)						error+= " Dias\n";
		if(cmbEqTrabajo.getSelectedIndex()==0)					error+= " Equipo de Trabajo\n";
		if(cmbJefatura.getSelectedIndex()==0)					error+= " Jefatura\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Cuadrante cuadrante = new Obj_Cuadrante().buscar_nuevo();
		
			panelLimpiar();
			panelEnabledTrue();
			txtFolio.setEditable(false);
			txaNombre.requestFocus();
			
//			btnDeshacer.setEnabled(true);
//			btnGuardar.setEnabled(true);
			
			if(cuadrante.getFolio() != 0){
				txtFolio.setText(cuadrante.getFolio()+1+"");
			}else{
				txtFolio.setText(1+"");
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
//			btnNuevo.setEnabled(true);
//			btnEditar.setEnabled(false);
			chStatus.setSelected(false);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
//			btnEditar.setEnabled(false);
//			btnNuevo.setEnabled(true);
//			btnDeshacer.setEnabled(true);
//			btnGuardar.setEnabled(true);
		}		
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txaNombre.setEditable(false);
		txaDescripcion.setEditable(false);
		cmbEstablecimiento.setEnabled(false);
		spnNGerarquico.setEnabled(false);
		cmbDias.setEnabled(false);
		cmbEqTrabajo.setEnabled(false);
		cmbJefatura.setEnabled(false);
		chStatus.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txaNombre.setEditable(true);
		cmbEstablecimiento.setEnabled(true);
		
		spnNGerarquico.setEnabled(true);
		
		cmbDias.setEnabled(true);
		cmbEqTrabajo.setEnabled(true);
		cmbJefatura.setEnabled(true);
		chStatus.setEnabled(true);
		txaDescripcion.setEditable(true);	
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txaNombre.setText("");
		cmbEstablecimiento.setSelectedIndex(0);
		spnNGerarquico.setValue(10);
		cmbDias.setSelectedIndex(0);
		cmbEqTrabajo.setSelectedIndex(0);
		cmbJefatura.setSelectedIndex(0);
		chStatus.setSelected(true);
		txaDescripcion.setText("");
	}
	
	public static void main (String arg []){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}