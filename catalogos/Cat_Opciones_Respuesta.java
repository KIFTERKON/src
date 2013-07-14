package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

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

import objetos.Obj_OpRespuesta;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Opciones_Respuesta extends JFrame
{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtDescripcion = new JTextField();
	
	JCheckBox chStatus = new JCheckBox("Status",true);
	
	JButton btnAgregar = new JButton("Agregar");
	JButton btnRemover = new JButton("Remover");
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnSubir = new JButton(new ImageIcon("Imagen/Up.png"));
	JButton btnBajar = new JButton(new ImageIcon("Imagen/Down.png"));
	
	DefaultTableModel modelo       = new DefaultTableModel(0,2)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	String lista[] = {"Seleccione Una Opción","Opción Libre","Opción Múltiple"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbRespuesta = new JComboBox(lista);
	
	public Cat_Opciones_Respuesta() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		init();
	}
	
	public Cat_Opciones_Respuesta(int folio){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		init();
		
		Obj_OpRespuesta respuesta = new Obj_OpRespuesta().buscar(folio);
		if(respuesta.getOpcion().equals("Opción Múltiple")){
			txtFolio.setText(folio+"");
			cmbRespuesta.setSelectedItem(respuesta.getOpcion());
			txtNombre.setText(respuesta.getNombre());
			
			String[] lista = new Obj_OpRespuesta().Tabla_Respuesta(respuesta.getNombre().trim().toUpperCase());
			Object[] fila = new Object[tabla.getColumnCount()];
			for(int i=0; i<lista.length; i++){
				modelo.addRow(fila);
				modelo.setValueAt(i+1,i,0);
				modelo.setValueAt(lista[i],i,1);
			}
			
		}else{
			txtFolio.setText(folio+"");
			cmbRespuesta.setSelectedItem(respuesta.getOpcion());
			txtNombre.setText(respuesta.getNombre());
			txtNombre.setEditable(false);
		}
		
		
	}
	
	public void init(){

		int y=30, ancho=100;
		
		this.panel.add(new JLabel("Folio:")).setBounds(20,y,ancho,20);
		this.panel.add(txtFolio).setBounds(ancho-5,y,ancho,20);
		this.panel.add(btnBuscar).setBounds(ancho+ancho,y,32,20);
		this.panel.add(chStatus).setBounds(235,y,70,20);
		this.panel.add(btnNuevo).setBounds(305,y,75,20);
		this.panel.add(btnEditar).setBounds(385,y,75,20);
		
		this.panel.add(new JLabel("Opciones:")).setBounds(20,60,70,20);
		this.panel.add(cmbRespuesta).setBounds(95,60,190,20);
		
		this.panel.add(new JLabel("Nombre:")).setBounds(20,90,70,20);
		this.panel.add(txtNombre).setBounds(95,90,190,20);
		
		this.panel.add(new JLabel("Descripción:")).setBounds(20,115,70,20);
		this.panel.add(txtDescripcion).setBounds(95,115,190,20);
		this.panel.add(btnAgregar).setBounds(305,115,75,20);
		this.panel.add(btnRemover).setBounds(385,115,75,20);
		this.panel.add(btnBajar).setBounds(320,140,40,20);
		this.panel.add(btnSubir).setBounds(400,140,40,20);
		
		panel.add(panelScroll).setBounds(20,165,440,190);
		
		panel.add(btnSalir).setBounds(20,360,90,20);
		panel.add(btnDeshacer).setBounds(200,360,90,20);
		panel.add(btnGuardar).setBounds(370,360,90,20);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Descripcion");
		tabla.getColumnModel().getColumn(1).setMinWidth(370);
		tabla.getColumnModel().getColumn(1).setMaxWidth(370);
		
		Editing(false);
		txtFolio.setEditable(true);
		txtFolio.requestFocus();
		btnNuevo.setEnabled(true);
		btnBuscar.setEnabled(true);
		
		txtDescripcion.addKeyListener(Agregar);
		
		btnAgregar.addActionListener(opAgregar);
		btnRemover.addActionListener(opRemover);
		btnSalir.addActionListener(OpSalir);
		btnDeshacer.addActionListener(limpia);
		btnNuevo.addActionListener(opNuevo);
		cmbRespuesta.addActionListener(opLibre);
		btnGuardar.addActionListener(opGuardar);
		btnBuscar.addActionListener(opFiltro);
		btnEditar.addActionListener(opEditar);
		btnBajar.addActionListener(opDesplazar);
		btnSubir.addActionListener(opDesplazar);
		
		txtFolio.addKeyListener(validaEnter);
		
		agregar(tabla);
		
		cont.add(panel);
		
		this.setSize(490,425);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener opDesplazar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tabla.getRowCount()>1){
				if(tabla.isRowSelected(tabla.getSelectedRow())){
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
				}else{
					JOptionPane.showMessageDialog(null,"No esta seleccionada ninguna fila!","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			
			}else{
				JOptionPane.showMessageDialog(null,"No hay filas que desplazar!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 1){
	    			int fila = tabla.getSelectedRow();
	    			txtDescripcion.setText(tabla.getValueAt(fila, 1).toString());
	    			
	        	}
	        }
        });
    }
	ActionListener opEditar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			switch(cmbRespuesta.getSelectedIndex()){
				case 0: break;
				case 1:
					txtNombre.setEditable(true);
					break;
				case 2: 
					Editing(true);
					break;
			}
			
		}
		
	};
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			switch(cmbRespuesta.getSelectedIndex()){
				case 0: 
					JOptionPane.showMessageDialog(null,"Necesita tener una opción de respuesta!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				break;

				case 1: 
					if(new Obj_OpRespuesta().existe(txtNombre.getText().toUpperCase(),cmbRespuesta.getSelectedItem().toString()) == true){
						JOptionPane.showMessageDialog(null,"El registro ya existe","Aviso",JOptionPane.INFORMATION_MESSAGE);
					}else{
						if(txtNombre.getText().equals("")){
							JOptionPane.showMessageDialog(null,"El campo nombre es requerido","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							Obj_OpRespuesta respuesta = new Obj_OpRespuesta();
							
							respuesta.setNumero(Integer.parseInt(txtFolio.getText()));
							respuesta.setOpcion(cmbRespuesta.getSelectedItem().toString());
							respuesta.setNombre(txtNombre.getText().toUpperCase());
													
							if(respuesta.guardar_libre()){
								JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								JOptionPane.showMessageDialog(null,"El ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
					}
				break;

				case 2:
					if(tabla.getRowCount() >0){
						if(new Obj_OpRespuesta().existe(txtNombre.getText().toUpperCase(),cmbRespuesta.getSelectedItem().toString()) == true){
							if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
								String[] registros_tabla = registros_tabla();
								if(new Obj_OpRespuesta().actualizar_multiple(registros_tabla,txtNombre.getText())){
									JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
								}else{
									JOptionPane.showMessageDialog(null,"El ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
									return;
								}
							}else{
								return;
							}
						}else{
							String[] registros_tabla = registros_tabla();
							
							Obj_OpRespuesta respuesta = new Obj_OpRespuesta();
							
							respuesta.setNumero(Integer.parseInt(txtFolio.getText()));
							respuesta.setOpcion(cmbRespuesta.getSelectedItem().toString());
							respuesta.setNombre(txtNombre.getText().toUpperCase());
													
							if(respuesta.guardar_multiple(registros_tabla)){
								JOptionPane.showMessageDialog(null,"El registro se guardó exitosamente!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								JOptionPane.showMessageDialog(null,"El ocurrió un problema al intentar guardar el registro!","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
												
					}else{
						JOptionPane.showMessageDialog(null,"Debe ingresar argumentos a la tabla!","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				break;
			
			}
		}
		
	};
	
	public String[] registros_tabla(){
		String[] registros = new String[tabla.getRowCount()];
		for(int i=0; i<tabla.getRowCount(); i++){
			registros[i] = modelo.getValueAt(i, 1)+""; 
		}
		return registros; 
	}
	
	ActionListener opLibre = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			switch (cmbRespuesta.getSelectedIndex()) {
			case 0:	
				  Editing(false);
				  cmbRespuesta.setEnabled(true);
				  btnNuevo.setEnabled(true);
				  btnEditar.setEnabled(true);
				  btnBuscar.setEnabled(true);
				  chStatus.setEnabled(true);
				  while(modelo.getRowCount() > 0){
						modelo.removeRow(0);
					}
				break;

			case 1:		
				  Editing(false);
				  cmbRespuesta.setEnabled(true);
				  txtNombre.setEditable(true);
				  btnNuevo.setEnabled(true);
				  btnEditar.setEnabled(true);
				  btnBuscar.setEnabled(true);
				  chStatus.setEnabled(true);
				  while(modelo.getRowCount() > 0){
						modelo.removeRow(0);
					}
				break;

			case 2:	
				  Editing(false);
				  cmbRespuesta.setEnabled(true);
				  txtNombre.setEditable(true);
				  txtDescripcion.setEditable(true);
				  btnNuevo.setEnabled(true);
				  btnEditar.setEnabled(true);
				  btnAgregar.setEnabled(true);
				  btnRemover.setEnabled(true);
				  btnSubir.setEnabled(true);
				  btnBajar.setEnabled(true);
				  btnBuscar.setEnabled(true);
				  chStatus.setEnabled(true);
				  tabla.setEnabled(true);
				break;
			}
		}
	};
	
	ActionListener opNuevo = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setText(new Obj_OpRespuesta().Nuevo());
		    Editing(false);
		    cmbRespuesta.setEnabled(true);
		    txtDescripcion.setEnabled(true);
		    btnNuevo.setEnabled(true);
		    btnEditar.setEnabled(true);
		    btnBuscar.setEnabled(true);
		    chStatus.setEnabled(true);
		}
	};
	
	ActionListener opAgregar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(txtDescripcion.getText().equals("")){
				JOptionPane.showMessageDialog(null,"El campo Descripción es necesario!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				String[] arreglo = new String[2];
				
				arreglo[0] = (modelo.getRowCount()+1+"");
				arreglo[1] = txtDescripcion.getText();
				
				modelo.addRow(arreglo);
				txtDescripcion.setText("");
			}
		}
	};
	
	ActionListener opRemover = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			
			if(tabla.isRowSelected(tabla.getSelectedRow())){
				modelo.removeRow(tabla.getSelectedRow());
				for(int i=0; i<tabla.getRowCount(); i ++){
					modelo.setValueAt(i+1,i,0);
				}
			}else{
				JOptionPane.showMessageDialog(null,"No ha seleccionado ningúna fila para remover!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener OpSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	public void Editing(Boolean condicion){
		txtFolio.setEditable(condicion);
		txtNombre.setEditable(condicion);
		txtDescripcion.setEditable(condicion);
		chStatus.setEnabled(condicion);
		cmbRespuesta.setEnabled(condicion);
		btnBuscar.setEnabled(condicion);
		btnNuevo.setEnabled(condicion);
		btnEditar.setEnabled(condicion);
		btnAgregar.setEnabled(condicion);
		btnRemover.setEnabled(condicion);
		btnSubir.setEnabled(condicion);
		btnBajar.setEnabled(condicion);
		
	}
	
	public void limpiar() {
		txtFolio.setText("");
		txtNombre.setText("");
		txtDescripcion.setText("");
		cmbRespuesta.setSelectedIndex(0);
		Editing(false);
		txtFolio.setEditable(true);
		btnBuscar.setEnabled(true);
		btnNuevo.setEnabled(true);
		btnEditar.setEnabled(true);
		btnBuscar.setEnabled(true);
	    chStatus.setEnabled(true);
		
		txtFolio.requestFocus();
		while(modelo.getRowCount() > 0){
			modelo.removeRow(0);
		}
	}
	
	ActionListener limpia = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
		}
	};
	
	ActionListener opciones = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(cmbRespuesta.equals("Opción Libre")){
				dispose();
			}
		}
	};
	
	KeyListener Agregar = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAgregar.doClick();
				txtDescripcion.requestFocus();
			}
		}
	};
	
	
	KeyListener validaEnter = new KeyListener() {
		public void keyTyped(KeyEvent arg0) {
		}
		public void keyReleased(KeyEvent arg0) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
				txtFolio.requestFocus();
			}
		}
	};
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_Opciones_Respuesta().setVisible(true);
		}
	};
	
	public static void main(String[]a) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Opciones_Respuesta().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
}
