package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Jerarquico;
import objetos.Obj_Puesto;
import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Nivel_Jerarquico extends JFrame {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status",true);
	
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnModificar = new JButton("Modificar");
	JButton btnSalir = new JButton("Salir");
	JButton btnAgregar = new JButton("Agregar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEliminar = new JButton("Remover");
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	
	JButton btnAltaPuesto = new JButton("Puesto");
	JButton btnFiltro = new JButton("Filtro");

	String lista[] = new Obj_Puesto().Combo_Puesto();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbP_Principal = new JComboBox(lista);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbP_Dependiente = new JComboBox(lista);
	
	String lista3[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_Establecimiento = new JComboBox(lista3);
	
	DefaultTableModel modelo = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
//valor para trabajar con el guardado desde la tabla
	int valor_referencia=0;
	
	public void getContenedor(){
				
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/nivel_jerarquico_icon&16.png"));
		this.panel.setBorder(BorderFactory.createTitledBorder("Nivel Jerarquico"));	
		this.setTitle("Nivel Jerarquico");
		
		this.panel.add(new JLabel("Folio:")).setBounds(20,30,50,20);
		this.panel.add(txtFolio).setBounds(140,30,100,20);
		this.panel.add(chStatus).setBounds(240,30,60,20);
		this.panel.add(btnBuscar).setBounds(300,30,32,20);
		
		this.panel.add(btnNuevo).setBounds(350,30,87,20);
		this.panel.add(btnAltaPuesto).setBounds(450,30,87,20);
		
		this.panel.add(btnModificar).setBounds(350,90,87,20);
		this.panel.add(btnFiltro).setBounds(450,90,87,20);
		
		this.panel.add(new JLabel("Descripcion:")).setBounds(20,60,100,20);
		this.panel.add(txtDescripcion).setBounds(140,60,190,20);
		
		this.panel.add(new JLabel("Puesto Pricipal:")).setBounds(20,90,120,20);
		this.panel.add(cmbP_Principal).setBounds(140,90,190,20);
		
		this.panel.add(new JLabel("Puesto Dependiente:")).setBounds(20,120,120,20);
		this.panel.add(cmbP_Dependiente).setBounds(140,120,190,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(20,150,120,20);
		this.panel.add(cmb_Establecimiento).setBounds(140,150,190,20);
		
		this.panel.add(btnAgregar).setBounds(350,150,85,20);
		this.panel.add(btnEliminar).setBounds(450,150,85,20);
		
		this.panel.add(panelScroll).setBounds(20,180,520,195);
		
		this.panel.add(btnSalir).setBounds(20,380,80,20);
		this.panel.add(btnLimpiar).setBounds(220,380,80,20);
		this.panel.add(btnGuardar).setBounds(430,380,80,20);
		
		this.tabla.getColumnModel().getColumn(0).setHeaderValue("Puesto Dependiente");
		this.tabla.getColumnModel().getColumn(0).setMinWidth(150);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(150);
		this.tabla.getColumnModel().getColumn(1).setHeaderValue("Establecimiento");
		this.tabla.getColumnModel().getColumn(1).setMinWidth(150);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(150);
//		this.tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
//		this.tabla.getColumnModel().getColumn(2).setMinWidth(150);
//		this.tabla.getColumnModel().getColumn(2).setMaxWidth(150);   
		
		this.btnSalir.addActionListener(salir);
		this.txtFolio.addKeyListener(guardaAction);
		this.btnEliminar.addActionListener(opRemover);
		this.btnNuevo.addActionListener(opNuevo);
		this.btnLimpiar.addActionListener(opLimpiar);
		this.txtFolio.addKeyListener(valida);
		this.btnAgregar.addActionListener(opAgregar);
		
		this.btnGuardar.addActionListener(guardar);
		this.btnBuscar.addActionListener(buscar);
		this.btnModificar.addActionListener(modifica);
		
		this.btnAltaPuesto.addActionListener(puesto);
		this.btnFiltro.addActionListener(filtro);
		
		this.txtDescripcion.setEditable(false);
		this.cmbP_Dependiente.setEnabled(false);
		this.cmbP_Principal.setEnabled(false);
		this.cmb_Establecimiento.setEnabled(false);
		this.chStatus.setEnabled(false);
		
		this.cont.add(panel);
		
		this.setSize(560,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public Cat_Nivel_Jerarquico(){
		getContenedor();
	}
	
	public Cat_Nivel_Jerarquico(String folio){
		
		getContenedor();
		
		Obj_Nivel_Jerarquico nivelbuscar = new Obj_Nivel_Jerarquico().buscar(Integer.parseInt(folio));
			txtFolio.setText(nivelbuscar.getFolio()+"");
			txtDescripcion.setText(nivelbuscar.getDescripcion()+"");
			cmbP_Principal.setSelectedItem(nivelbuscar.getPuesto_principal());
		
		getTabla(Integer.parseInt(folio));
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){

			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(validacampos().equals("")){
					Obj_Nivel_Jerarquico nivelgerarquico = new Obj_Nivel_Jerarquico().buscar(Integer.parseInt(txtFolio.getText()));
					
					if(nivelgerarquico.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
						{
							Obj_Nivel_Jerarquico gerarquico = new Obj_Nivel_Jerarquico();
								
							gerarquico.setFolio(Integer.parseInt(txtFolio.getText()));
							gerarquico.setDescripcion(txtDescripcion.getText().toUpperCase());
							
							gerarquico.setPuesto_principal(cmbP_Principal.getSelectedItem().toString());
							gerarquico.setPuesto_dependiente(cmbP_Dependiente.getSelectedItem().toString());
							gerarquico.setEstablecimiento(cmb_Establecimiento.getSelectedItem().toString());
							
							String[] arreglo = new String[2];
							
//							arreglo[0] =cmbP_Principal.getSelectedItem()+"";
							arreglo[0] =cmbP_Dependiente.getSelectedItem()+"";
							arreglo[1] = cmb_Establecimiento.getSelectedItem()+"";
							
//							modelo.addRow(arreglo);
							
							if(valor_referencia==0){
										gerarquico.actualizar(Integer.parseInt(txtFolio.getText()));
										limpiaGuardar();
										////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
										/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
										/**/	   		 getTabla(Integer.parseInt(txtFolio.getText()));			/**/
										////////////////////////////////////////////////////////////////////////////////
										JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
										return;
							}else{
								
								if(valor_referencia>0){
										gerarquico.actualizar2(listadatos());
										limpiaGuardar();
										////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
										/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
										/**/	   		 getTabla(Integer.parseInt(txtFolio.getText()));			/**/
										////////////////////////////////////////////////////////////////////////////////
										JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
										return;
								}else{
										JOptionPane.showMessageDialog(null,"Ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
										return;
									}
								}
						}
					}else{
						Obj_Nivel_Jerarquico gerarquico = new Obj_Nivel_Jerarquico();
						
						gerarquico.setFolio(Integer.parseInt(txtFolio.getText()));
						gerarquico.setDescripcion(txtDescripcion.getText().toUpperCase());
						
						gerarquico.setPuesto_principal(cmbP_Principal.getSelectedItem().toString());
						gerarquico.setPuesto_dependiente(cmbP_Dependiente.getSelectedItem().toString());
						gerarquico.setEstablecimiento(cmb_Establecimiento.getSelectedItem().toString());
						
						String[] arreglo = new String[2];
						
//						arreglo[0] =cmbP_Principal.getSelectedItem()+"";
						arreglo[0] =cmbP_Dependiente.getSelectedItem()+"";
						arreglo[1] = cmb_Establecimiento.getSelectedItem()+"";
						
						
//							modelo.addRow(arreglo);
						
						if(valor_referencia==0){
									gerarquico.guardar_multiple();
									limpiaGuardar();
									////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
									/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
									/**/	   		 getTabla(Integer.parseInt(txtFolio.getText()));			/**/
									////////////////////////////////////////////////////////////////////////////////
									JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
						}else{
							
							if(valor_referencia>0){
									gerarquico.guardar_multiple2(listadatos());
									limpiaGuardar();
									////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
									/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
									/**/	   		 getTabla(Integer.parseInt(txtFolio.getText()));			/**/
									////////////////////////////////////////////////////////////////////////////////
									JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
							}else{
									JOptionPane.showMessageDialog(null,"Ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
									return;
								}
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos: \n"+validacampos(),"Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
				
			}
			//valor para trabajar con el guardado desde la tabla
						valor_referencia=0;
		}
	};
	
	public  String[][] listadatos()
	{
		String[][] matriz=new String[tabla.getRowCount()][2];
		for (int i = 0; i < tabla.getRowCount(); i++) {
			for (int j = 0; j < 2; j++) {
				matriz[i][j]=modelo.getValueAt(i, j).toString();
			}
		}
		return matriz;
	}
	
	public String validacampos(){
		String error="";
		if (txtDescripcion.getText().equals("")){error+="Descripcion\n";}
		if (cmbP_Principal.getSelectedIndex()==0) {error+="Puesto Principal\n";}
//		if (cmbP_Dependiente.getSelectedIndex()==0) {error+="Puesto Dependiente\n";}
//		if (cmb_Establecimiento.getSelectedIndex()==0) {error+="Establecimielto\n";}
//		if (!(tabla.getRowCount()>0)) {error+="No hay ningun valor agregado en la tabla";}
		return error;
	}
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			 limpia();
			 
			 while(modelo.getRowCount() > 0){
	    	  modelo.removeRow(0);
			 }
		}
	};
	
	ActionListener opRemover = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.isRowSelected(tabla.getSelectedRow())){
				int fila = tabla.getSelectedRow();
				String nombre =  tabla.getValueAt(fila, 0).toString().trim();
				String establecimiento =  tabla.getValueAt(fila, 1).toString().trim();
				
				if(JOptionPane.showConfirmDialog(null, "¿desea eliminar el puesto dependiente seleccionado?","aviso",JOptionPane.YES_NO_OPTION) == 0){
					if(new Obj_Nivel_Jerarquico().buscarYborraPuestoDependiente(nombre, Integer.parseInt(txtFolio.getText()),establecimiento)){
						JOptionPane.showMessageDialog(null,"Se eliminó exitosamente","Exito", JOptionPane.INFORMATION_MESSAGE);
						modelo.removeRow(fila);
					}else{
						JOptionPane.showMessageDialog(null,"No se pudo eliminar el registro","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}else{
				JOptionPane.showMessageDialog(null,"No ha seleccionado ningún renglón", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		}
	};
	
	ActionListener opAgregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(cmbP_Dependiente.getSelectedItem().equals("Selecciona un Puesto"))
			{
				JOptionPane.showMessageDialog(null, "Favor de seleccionar un puesto","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				if (cmb_Establecimiento.getSelectedItem().equals("Todos")) {
				JOptionPane.showMessageDialog(null, "Favor de seleccionar un establecimiento","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
				
				}else{
					
//valor para trabajar con el guardado desde la tabla
					valor_referencia++;
					
					if(valor_referencia==1){
						while(modelo.getRowCount() > 0){
				    	  modelo.removeRow(0);
						 }
					}
					
					 
					
					String[] arreglo = new String[2];
					
//					arreglo[0] =cmbP_Principal.getSelectedItem()+"";
					arreglo[0] =cmbP_Dependiente.getSelectedItem()+"";
					arreglo[1] = cmb_Establecimiento.getSelectedItem()+"";

					
					modelo.addRow(arreglo);
					
					cmbP_Dependiente.setSelectedIndex(0);
					cmb_Establecimiento.setSelectedIndex(0);
				}
			}
		}
	};

	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			txtFolio.setText(new Obj_Nivel_Jerarquico().Nuevo());
			
			txtDescripcion.requestFocus();
			cmbP_Principal.setEnabled(true);
			paneltrue();
			panelselectrue();
		}
	};
	
	ActionListener buscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(txtFolio.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				Obj_Nivel_Jerarquico nivelbuscar = new Obj_Nivel_Jerarquico().buscar(Integer.parseInt(txtFolio.getText()));
				if(nivelbuscar.getFolio()!=0)
				{
					txtFolio.setText(nivelbuscar.getFolio()+"");
					txtDescripcion.setText(nivelbuscar.getDescripcion()+"");
					cmbP_Principal.setSelectedItem(nivelbuscar.getPuesto_principal());
					
					if(nivelbuscar.isStatus() == true){chStatus.setSelected(true);}
					
					txtDescripcion.setEditable(true);
					cmbP_Dependiente.setEnabled(true);
					cmbP_Principal.setEnabled(true);
					cmb_Establecimiento.setEnabled(true);
				    chStatus.setEnabled(true);
				    chStatus.setSelected(true);
				    
				    cmbP_Dependiente.setSelectedIndex(0);
				    cmb_Establecimiento.setSelectedIndex(0);
				    
			////////////////  limpia la tabla antes de acer otra busqueda   ////////////////
			/**/	    while(modelo.getRowCount() > 0){modelo.removeRow(0);}			/**/
			/**/	   		 getTabla(Integer.parseInt(txtFolio.getText()));			/**/
			////////////////////////////////////////////////////////////////////////////////
			
					panelfalse();
					
				}
				else{
					
				JOptionPane.showMessageDialog(null,"El Folio no existe","Aviso",JOptionPane.ERROR_MESSAGE);
					
				}
			}     
		}
	};
	
	public void getTabla(int folio){
		String todos1 = "exec sp_select_nivel_jerarquico "+folio;
		Statement stmt = null;
		ResultSet rs;
		Connexion con = new Connexion();
		try {
			stmt = con.conexion().createStatement();
			rs = stmt.executeQuery(todos1);
			Object[] vector = new Object[2];
			while(rs.next()){
				vector[0] = (rs.getString(2));
				vector[1] = (rs.getString(3));
//				vector[2] = (rs.getString(3));
				modelo.addRow(vector);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void panelselectrue()
	{
		cmbP_Principal.setSelectedIndex(0);
		cmbP_Dependiente.setSelectedIndex(0);
		cmb_Establecimiento.setSelectedIndex(0);
		txtDescripcion.setText("");
		chStatus.setEnabled(false);
		 while(modelo.getRowCount() > 0){
	    	  modelo.removeRow(0);
		}

	}
	
	public void panelfalse()
	{
		txtDescripcion.setEditable(false);
		cmbP_Principal.setEnabled(false);
		cmbP_Dependiente.setEnabled(false);
		cmb_Establecimiento.setEnabled(false);
		
	}
	
	public void panelfalseGuardar()
	{
		txtFolio.setEditable(false);
		txtDescripcion.setEditable(false);
		cmbP_Principal.setEnabled(false);
		cmbP_Dependiente.setEnabled(true);
		cmb_Establecimiento.setEnabled(true);
		
	}
	
	public void paneltrue()
	{
		txtDescripcion.setEditable(true);
		cmbP_Dependiente.setEnabled(true);
		cmb_Establecimiento.setEnabled(true);
		txtFolio.setEditable(false);
		
	}
	
	ActionListener modifica = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			paneltrue();
			cmbP_Principal.setEnabled(false);
			txtDescripcion.setEnabled(true);
		}
	};
	
	ActionListener puesto = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			new Cat_Puesto().setVisible(true);
		}
	};
	
	ActionListener filtro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			dispose();
			new Cat_Filtro_Nivel_Jerarquico().setVisible(true);
		}
	};
	
	ActionListener salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
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
	
	KeyListener guardaAction = new KeyListener() {
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
				txtFolio.requestFocus();
			}
		}
	};
	
	public void limpia() {
		txtFolio.setText("");
		txtDescripcion.setText("");
		cmbP_Dependiente.setSelectedIndex(0);
		cmbP_Principal.setSelectedIndex(0);
		cmb_Establecimiento.setSelectedIndex(0);
	    chStatus.setEnabled(false);
	    
	    txtFolio.requestFocus();
	    txtFolio.setEditable(true);
	    panelfalse();
	}
	
	public void limpiaGuardar() {
		cmbP_Dependiente.setSelectedIndex(0);
		cmb_Establecimiento.setSelectedIndex(0);
	    chStatus.setEnabled(false);
	    
	    cmbP_Dependiente.requestFocus();
	    txtFolio.setEditable(true);
	    panelfalseGuardar();
	}
	
	public static void main(String[]a)
	{
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Nivel_Jerarquico().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
