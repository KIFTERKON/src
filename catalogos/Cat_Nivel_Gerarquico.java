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

import SQL.Connexion;
import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Gerarquico;
import objetos.Obj_Puesto;

@SuppressWarnings("serial")
public class Cat_Nivel_Gerarquico extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnModificar = new JButton("Modificar");
	JButton btnSalir = new JButton("Salir");
	JButton btnAgregar = new JButton("Agregar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEliminar = new JButton("Remover");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));

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
	
	public Cat_Nivel_Gerarquico()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Nivel Gerarquico"));	
		this.setTitle("Nivel Gerarquico");
		
		
		this.panel.add(new JLabel("Folio:")).setBounds(20,30,50,20);
		this.panel.add(txtFolio).setBounds(140,30,100,20);
		this.panel.add(chStatus).setBounds(240,30,60,20);
		this.panel.add(btnBuscar).setBounds(300,30,32,20);
		this.panel.add(btnNuevo).setBounds(350,30,87,20);
		this.panel.add(btnModificar).setBounds(350,90,87,20);
		
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
		
		panel.add(panelScroll).setBounds(20,180,520,195);
		
		this.panel.add(btnSalir).setBounds(20,380,80,20);
		this.panel.add(btnLimpiar).setBounds(220,380,80,20);
		this.panel.add(btnGuardar).setBounds(430,380,80,20);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Puesto");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(1).setMinWidth(370);
		tabla.getColumnModel().getColumn(1).setMaxWidth(370);
		
		btnSalir.addActionListener(salir);
		txtFolio.addKeyListener(guardaAction);
		btnEliminar.addActionListener(opRemover);
		btnNuevo.addActionListener(opNuevo);
		btnLimpiar.addActionListener(opLimpiar);
		txtFolio.addKeyListener(valida);
		btnAgregar.addActionListener(opAgregar);
		
		btnGuardar.addActionListener(guardar);
		btnBuscar.addActionListener(buscar);
		btnModificar.addActionListener(modifica);
		
		
//		txtFolio.setEditable(false);
		txtDescripcion.setEditable(false);
		cmbP_Dependiente.setEnabled(false);
		cmbP_Principal.setEnabled(false);
		cmb_Establecimiento.setEnabled(false);
	    chStatus.setEnabled(false);
	    chStatus.setSelected(false);
		
		
		cont.add(panel);
		this.setSize(560,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(validacampos().equals("")){
					Obj_Nivel_Gerarquico nivelgerarquico = new Obj_Nivel_Gerarquico().buscar(Integer.parseInt(txtFolio.getText()));
					
					
					if(nivelgerarquico.getFolio() == Integer.parseInt(txtFolio.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0)
						{
							Obj_Nivel_Gerarquico gerarquico = new Obj_Nivel_Gerarquico();
								
							gerarquico.setFolio(Integer.parseInt(txtFolio.getText()));
							gerarquico.setPuesto_principal(cmbP_Principal.getSelectedItem()+"");
							gerarquico.setDescripcion(txtDescripcion.getText().toUpperCase());
							gerarquico.setPuesto_dependiente(cmbP_Dependiente.getSelectedItem()+"");
							gerarquico.setEstablecimiento(cmb_Establecimiento.getSelectedItem()+"");
																					
							if(gerarquico.actualizar(listadatos())){
								JOptionPane.showMessageDialog(null,"El registro se actualizó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								JOptionPane.showMessageDialog(null,"El ocurrió un problema al intentar actualizar el registro!","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}else{
						Obj_Nivel_Gerarquico nivelg = new Obj_Nivel_Gerarquico();
						nivelg.setDescripcion(txtDescripcion.getText().toUpperCase());
						nivelg.setPuesto_principal(cmbP_Principal.getSelectedItem()+"");
						
						if(nivelg.guardar_multiple(listadatos())){
							JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"El ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
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
		if (cmbP_Principal.getSelectedIndex()==0) {error+="Puesto principal\n";}
		if (!(tabla.getRowCount()>0)) {error+="No hay ningun valor agregado en la tabla";}
		return error;
	}
	
	ActionListener opLimpiar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpia();
		}
	};
	
	ActionListener opRemover = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			if(tabla.isRowSelected(tabla.getSelectedRow())){
				modelo.removeRow(tabla.getSelectedRow());
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
					String[] arreglo = new String[2];
			
					arreglo[0] =cmbP_Dependiente.getSelectedItem()+"";
					arreglo[1] = cmb_Establecimiento.getSelectedItem()+"";
					
					modelo.addRow(arreglo);
				}
			}
		}
	};

	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			txtFolio.setText(new Obj_Nivel_Gerarquico().Nuevo());
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
				Obj_Nivel_Gerarquico nivelbuscar = new Obj_Nivel_Gerarquico().buscar(Integer.parseInt(txtFolio.getText()));
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
				    
				    getTabla(Integer.parseInt(txtFolio.getText()));
				    
					panelfalse();
					
				}
				else{
					
				JOptionPane.showMessageDialog(null,"El Folio no existe","Aviso",JOptionPane.ERROR_MESSAGE);
					
				}
			}
		}
	};
	
	public void getTabla(int folio){
		String todos1 = "exec sp_select_nivel_gerarquico "+folio;
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
	
	
	public void panelselectrue()
	{
		cmbP_Principal.setSelectedIndex(0);
		cmbP_Dependiente.setSelectedIndex(0);
		cmb_Establecimiento.setSelectedIndex(0);
		txtDescripcion.setText("");
		chStatus.setSelected(false);
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
	public void paneltrue()
	{
		txtDescripcion.setEditable(true);
		cmbP_Principal.setEnabled(true);
		cmbP_Dependiente.setEnabled(true);
		cmb_Establecimiento.setEnabled(true);
		txtFolio.setEditable(false);
		
	}
	
	ActionListener modifica = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			paneltrue();
			txtDescripcion.setEnabled(true);
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
	
	public void limpia()
	{
		
		txtFolio.setText("");
		txtDescripcion.setText("");
		cmbP_Dependiente.setSelectedIndex(0);
		cmbP_Principal.setSelectedIndex(0);
		cmb_Establecimiento.setSelectedIndex(0);
	    chStatus.setSelected(false);
	    chStatus.setEnabled(false);
	    
	    while(modelo.getRowCount() > 0){
	    	  modelo.removeRow(0);
		}
	    txtFolio.requestFocus();
	    txtFolio.setEditable(true);
	    panelfalse();
	}
	
	public static void main(String[]a)
	{
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Nivel_Gerarquico().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
