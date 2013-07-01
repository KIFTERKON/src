package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class Cat_OpRespuesta extends JFrame
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
	
	public Cat_OpRespuesta() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		init();
	}
	
	public Cat_OpRespuesta(int folio){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/How-to.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Opcion de Respuesta"));
		
		this.setTitle("Opciones de Respuesta");
		
		init();
		
		Obj_OpRespuesta respuesta = new Obj_OpRespuesta().buscar(folio);
		
		txtFolio.setText(folio+"");
		cmbRespuesta.setSelectedItem(respuesta.getOpcion());
		txtNombre.setText(respuesta.getNombre());
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
		
		panel.add(panelScroll).setBounds(20,150,440,195);
		
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
		
		cont.add(panel);
		
		this.setSize(490,425);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener opGuardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			switch(cmbRespuesta.getSelectedIndex()){
				case 0: 
					JOptionPane.showMessageDialog(null,"Necesita tener una opción de respuesta!","Aviso",JOptionPane.INFORMATION_MESSAGE);
				break;

				case 1: 
					if(new Obj_OpRespuesta().existe(txtNombre.getText()) == true){
						if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
							Obj_OpRespuesta respuesta = new Obj_OpRespuesta();
							respuesta.setNumero(Integer.parseInt(txtFolio.getText()));
							respuesta.setOpcion(cmbRespuesta.getSelectedItem().toString());
							respuesta.setNombre(txtNombre.getText());
							respuesta.setStatus(chStatus.isSelected());
							respuesta.actualizar(Integer.parseInt(txtFolio.getText()));
						}else{
							return;
						}
					}else{
						Obj_OpRespuesta respuesta = new Obj_OpRespuesta();
						
						respuesta.setNumero(Integer.parseInt(txtFolio.getText()));
						respuesta.setOpcion(cmbRespuesta.getSelectedItem().toString());
						respuesta.setNombre(txtNombre.getText());
												
						respuesta.guardar_libre();
					}
				break;

				case 2: 
					System.out.println("3");
				break;
			
			}
		}
		
	};
	
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
	
	ActionListener opFiltro = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_Opciones_Respuesta().setVisible(true);
		}
	};
	
	public static void main(String[]a) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_OpRespuesta().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
}
