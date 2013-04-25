package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;

import objetos.Obj_CheckBoxNode;
import objetos.Obj_Establecimiento;
import objetos.Obj_MD5;
import objetos.Obj_NombreVector;
import objetos.Obj_SubMenus;
import objetos.Obj_Usuario3;

@SuppressWarnings("serial")
public class Cat_Usuario3 extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	String[] Sub_Alimentacion = new Obj_SubMenus().SubMenuAlimentacion();
	Obj_CheckBoxNode Alimentacion[] = {
		new Obj_CheckBoxNode(Sub_Alimentacion[0], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[1], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[2], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[3], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[4], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[5], true),
		new Obj_CheckBoxNode(Sub_Alimentacion[6], true),
	};
	
	String[] Sub_Catalogos = new Obj_SubMenus().SubMenuCatalogos();
	Obj_CheckBoxNode Catalogos[] = {
		new Obj_CheckBoxNode(Sub_Catalogos[0], true),
	    new Obj_CheckBoxNode(Sub_Catalogos[1], true),
	    new Obj_CheckBoxNode(Sub_Catalogos[2], true),
	    new Obj_CheckBoxNode(Sub_Catalogos[3], true),
	};
	
	String[] Sub_Listas = new Obj_SubMenus().SubMenuListas();
	Obj_CheckBoxNode Listas[] = {
		new Obj_CheckBoxNode(Sub_Listas[0], true),
	    new Obj_CheckBoxNode(Sub_Listas[1], true),
	    new Obj_CheckBoxNode(Sub_Listas[2], true),
	    new Obj_CheckBoxNode(Sub_Listas[3], true),
	    new Obj_CheckBoxNode(Sub_Listas[4], true),
	};
	
	String[] Sub_Configuracion = new Obj_SubMenus().SubMenuConfiguracion();
	Obj_CheckBoxNode Configuracion[] = {
		new Obj_CheckBoxNode(Sub_Configuracion[0], true),
		new Obj_CheckBoxNode(Sub_Configuracion[1], true),
		new Obj_CheckBoxNode(Sub_Configuracion[2], true),
		new Obj_CheckBoxNode(Sub_Configuracion[3], true),
		new Obj_CheckBoxNode(Sub_Configuracion[4], true),
		new Obj_CheckBoxNode(Sub_Configuracion[5], true),
		new Obj_CheckBoxNode(Sub_Configuracion[6], true),
		new Obj_CheckBoxNode(Sub_Configuracion[7], true),
	};
	
	String[] Sub_Autorizaciones = new Obj_SubMenus().SubMenuAutorizaciones();
	Obj_CheckBoxNode Autorizaciones[] = {
		new Obj_CheckBoxNode(Sub_Autorizaciones[0], true),
		new Obj_CheckBoxNode(Sub_Autorizaciones[1], true),
	};
	
	String[] Sub_Reportes = new Obj_SubMenus().SubMenuReportes();
	Obj_CheckBoxNode Reportes[] = {
		new Obj_CheckBoxNode(Sub_Reportes[0], true),
		new Obj_CheckBoxNode(Sub_Reportes[1], true),
	};
	
	String[] Sub_Cortes = new Obj_SubMenus().SubMenuCortes();
	Obj_CheckBoxNode Cortes[] = {
		new Obj_CheckBoxNode(Sub_Cortes[0], true),
		new Obj_CheckBoxNode(Sub_Cortes[1], true),
		new Obj_CheckBoxNode(Sub_Cortes[2], true),
	};	
	
	@SuppressWarnings("rawtypes")
	Vector AlimentacionVector = new Obj_NombreVector("Alimentación", Alimentacion);
	    
	@SuppressWarnings("rawtypes")
	Vector CatalogosVector = new Obj_NombreVector("Catálogos", Catalogos);
	
	@SuppressWarnings("rawtypes")
	Vector ListasVector = new Obj_NombreVector("Listas", Listas);
	
	@SuppressWarnings("rawtypes")
	Vector ConfiguracionVector = new Obj_NombreVector("Configuracion", Configuracion);
	
	@SuppressWarnings("rawtypes")
	Vector AutorizacionVector = new Obj_NombreVector("Autorizaciones", Autorizaciones);
	
	@SuppressWarnings("rawtypes")
	Vector ReportesVector = new Obj_NombreVector("Reportes", Reportes);
	
	@SuppressWarnings("rawtypes")
	Vector CortesVector = new Obj_NombreVector("Cortes", Cortes);
	
	Object rootNodos[] = { AlimentacionVector, AutorizacionVector, CatalogosVector, ListasVector, ConfiguracionVector, ReportesVector, CortesVector};
	    
	@SuppressWarnings("rawtypes")
	Vector rootVector = new Obj_NombreVector("Permisos", rootNodos);
	 
	JTree tree = new JTree(rootVector);

	JScrollPane scrolltree = new JScrollPane(tree);
	
	objetos.CheckBoxNodeRenderer renderer = new objetos.CheckBoxNodeRenderer();	
	
	String[][] Matriz = new Obj_SubMenus().UsuarioMatriz();
	
	DefaultTableModel model = new DefaultTableModel(Matriz,
			new String[]{"Folio", "Nombre", "Establecimiento"}){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scrolltable = new JScrollPane(tabla);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	TableRowSorter trsfiltro = new TableRowSorter(model); 
	
	JTextField txtFolioFiltro = new JTextField();
	JTextField txtNombre_CompletoFiltro = new JTextField();
	
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
    
    JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	JPasswordField txtContrasena = new JPasswordField();
	JPasswordField txtContrasena1 = new JPasswordField();
    
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnSalir   = new JButton("Salir");
	JButton btnQuitar  = new JButton("Quitar Permisos");
	
	@SuppressWarnings("unchecked")
	public Cat_Usuario3(){
		this.setTitle("Usuarios y Permisos");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lock.png"));
		
		tabla.setRowSorter(trsfiltro);  
		
		campo.setBorder(BorderFactory.createTitledBorder("Usuarios y Permisos"));
		
		campo.add(scrolltree).setBounds(10,20,220,275);
				
		campo.add(txtFolioFiltro).setBounds(241,20,68,20);
		campo.add(txtNombre_CompletoFiltro).setBounds(310,20,208,20);
		campo.add(cmbEstablecimientos).setBounds(519,20,120,20);
		
		campo.add(scrolltable).setBounds(240,42,400,100);	
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setMaxWidth(210);
		tabla.getColumnModel().getColumn(1).setMinWidth(210);
		
		int y = 170;
		campo.add(new JLabel("Folio:")).setBounds(240,y,90,20);
		campo.add(txtFolio).setBounds(310,y,100,20);
		
		campo.add(new JLabel("Usuario:")).setBounds(240,y+=25,90,20);
		campo.add(txtNombre_Completo).setBounds(310,y,210,20);
		
		campo.add(new JLabel("Contraseña:")).setBounds(240,y+=25,90,20);
		campo.add(txtContrasena).setBounds(310,y,210,20);
		campo.add(btnGuardar).setBounds(535,y,100,20);
		
		campo.add(new JLabel("Confirmar:")).setBounds(240,y+=25,90,20);
		campo.add(txtContrasena1).setBounds(310,y,210,20);
		campo.add(btnLimpiar).setBounds(535,y,100,20);
		campo.add(btnSalir).setBounds(455,y+=30,110,20);
		campo.add(btnQuitar).setBounds(310,y,110,20);
		
		cont.add(campo);
		
		btnGuardar.addActionListener(opExtraer);
		txtFolioFiltro.addKeyListener(opFiltroFolio);
		txtNombre_CompletoFiltro.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltroEstable);
		
		tabla.addMouseListener(opMouse);
		
		tree.setCellRenderer(renderer);
		tree.setCellEditor(new objetos.CheckBoxNodeEditor(tree));
		tree.setEditable(true);
		
		txtFolio.setEditable(false);
		txtNombre_Completo.setEditable(false);
		
		this.setSize(660,340);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
	}
	
	MouseAdapter opMouse = new MouseAdapter(){
		@SuppressWarnings("rawtypes")
		public void mouseClicked(MouseEvent arg0){
			if(arg0.getClickCount() == 1){
        		int id = Integer.parseInt(model.getValueAt(tabla.getSelectedRow(),0).toString());
        		txtFolio.setText(model.getValueAt(tabla.getSelectedRow(), 0).toString());
        		txtNombre_Completo.setText(model.getValueAt(tabla.getSelectedRow(), 1).toString());
        		System.out.println(id);
        	
        		Vector arreglos = new Obj_Usuario3().returnPermisos(txtNombre_Completo.getText());
        		int a = 0;
        		for(int i = 0; i<Alimentacion.length; i++){
        			Alimentacion[i].setSelected(Boolean.parseBoolean(arreglos.get(i).toString()));
        			a++;
        		}
        		
        		for(a=a; a<Catalogos.length; a++){
        			Catalogos[a].setSelected(Boolean.parseBoolean(arreglos.get(a).toString()));
        		}
//        		if(Catalogos[1].isSelected() == false){
//        			Catalogos[1].setSelected(true);
//        			
//        			
//        			tree.collapseRow(0);
//        			tree.collapseRow(1);
//        			tree.collapseRow(2);
//        		}else{
//        			tree.expandRow(0);
//        			Catalogos[1].setSelected(false);
//        			tree.clearSelection();
//        		}
        		
			}
		}
	};
	
	ActionListener opExtraer = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "deprecation", "static-access" })
		public void actionPerformed(ActionEvent e){
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			} else{
				if(txtContrasena.getText().equals(txtContrasena1.getText())){
					Obj_Usuario3 usuario = new Obj_Usuario3().BuscarUsuario(txtNombre_Completo.getText());
					Obj_MD5 algoritmo = new Obj_MD5();
					Vector subMenus = vectorComponentes(tree);
					if(!usuario.getContrasena().equals(algoritmo.cryptMD5(txtContrasena.getText(), "izagar"))){
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;	
					}else{
						if(usuario.ExisteUsuario(txtNombre_Completo.getText()) == true){
							if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
								usuario.actualizar(txtNombre_Completo.getText(), subMenus);
								JOptionPane.showMessageDialog(null,"El registro se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							}else{
								return;
							}
						}else{
							usuario.setFolio(Integer.parseInt(txtFolio.getText()));
							usuario.setNombre_completo(txtNombre_Completo.getText());
							usuario.setContrasena(algoritmo.cryptMD5(txtContrasena1.getText(),"izagar"));
							usuario.guardar(subMenus);
							JOptionPane.showMessageDialog(null,"El registro se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						}
					}
					
				} else{
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;	
				}

			}
		}
	};
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFiltro.getText(), 0));
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_CompletoFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	ActionListener opFiltroEstable = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	public static void main(String args[]){
		System.out.print(System.getProperty("user.dir")+"\\icon\\icon_key.gif");
		try	{  
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Usuario3().setVisible(true);
		} catch (Exception e){
		   e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector vectorComponentes(JTree tree) {
		Vector lista = new Vector();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)(tree.getModel().getRoot());
		Enumeration enumeration = root.depthFirstEnumeration();

		while (enumeration.hasMoreElements()){
			String input = enumeration.nextElement()+"", extracted;
			extracted = input.substring(input.indexOf('[')+1,input.length()-1);
			int indice = extracted.indexOf('/');        

			if(indice != -1){
				lista.add(extracted);
			}			
		}
		return lista;
	}

	@SuppressWarnings("deprecation")
	private String validaCampos(){
		String error="";
		
		if(txtFolio.getText().equals("")) 			error+= "Folio\n";
		if(txtNombre_Completo.getText().equals("")) error+= "Nombre Completo\n";
		if(txtContrasena.getText().equals("")) 		error+= "Contraseña\n";
		if(txtContrasena1.getText().equals(""))	error+= "Confirmar la Contraseña\n";
				
		return error;
	}
	
}
