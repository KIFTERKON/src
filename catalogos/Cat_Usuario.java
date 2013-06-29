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
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Usuario extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	String[] Sub_Catalogo = new Obj_SubMenus().SubMenuCatalogo();
	Obj_CheckBoxNode Catalogo[] = {
		new Obj_CheckBoxNode(Sub_Catalogo[0], false),
		new Obj_CheckBoxNode(Sub_Catalogo[1], false),
		new Obj_CheckBoxNode(Sub_Catalogo[2], false),
		new Obj_CheckBoxNode(Sub_Catalogo[3], false),
		new Obj_CheckBoxNode(Sub_Catalogo[4], false),
		new Obj_CheckBoxNode(Sub_Catalogo[5], false),
		new Obj_CheckBoxNode(Sub_Catalogo[6], false),
	};
	
	String[] Sub_Configuracion = new Obj_SubMenus().SubMenuConfiguracion();
	Obj_CheckBoxNode Configuracion[] = {
		new Obj_CheckBoxNode(Sub_Configuracion[0], false),
	    new Obj_CheckBoxNode(Sub_Configuracion[1], false),
	    new Obj_CheckBoxNode(Sub_Configuracion[2], false),
	    new Obj_CheckBoxNode(Sub_Configuracion[3], false),
		new Obj_CheckBoxNode(Sub_Configuracion[4], false),
		new Obj_CheckBoxNode(Sub_Configuracion[5], false),
		new Obj_CheckBoxNode(Sub_Configuracion[6], false),
	};
	
	String[] Sub_Contabilidad_Conciliacion_Auxiliar_Finanzas = new Obj_SubMenus().SubMenuContabilidad_Conciliacion_Auxiliar_Finanzas();
	Obj_CheckBoxNode Contabilidad_Conciliacion_Auxiliar_Finanzas[] = {
		new Obj_CheckBoxNode(Sub_Contabilidad_Conciliacion_Auxiliar_Finanzas[0], false),
	    new Obj_CheckBoxNode(Sub_Contabilidad_Conciliacion_Auxiliar_Finanzas[1], false),
	    new Obj_CheckBoxNode(Sub_Contabilidad_Conciliacion_Auxiliar_Finanzas[2], false),
	    new Obj_CheckBoxNode(Sub_Contabilidad_Conciliacion_Auxiliar_Finanzas[3], false),
	};
	
	String[] Sub_Cuadrantes_Alimentacion = new Obj_SubMenus().SubMenuCuadrantes_Alimentacion();
	Obj_CheckBoxNode Cuadrantes_Alimentacion[] = {
		new Obj_CheckBoxNode(Sub_Cuadrantes_Alimentacion[0], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Alimentacion[1], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Alimentacion[2], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Alimentacion[3], false),
	};
	
	String[] Sub_Cuadrantes_Catalogo = new Obj_SubMenus().SubMenuCuadrantes_Catalogo();
	Obj_CheckBoxNode Cuadrantes_Catalogo[] = {
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[0], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[1], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[2], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[3], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[4], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Catalogo[5], false),
	};
	
	String[] Sub_Cuadrantes_Reportes = new Obj_SubMenus().SubMenuCuadrantes_Reportes();
	Obj_CheckBoxNode Cuadrantes_Reportes[] = {
		new Obj_CheckBoxNode(Sub_Cuadrantes_Reportes[0], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Reportes[1], false),
		new Obj_CheckBoxNode(Sub_Cuadrantes_Reportes[2], false),
	};
	
	String[] Sub_Lista_Raya_Alimentacion = new Obj_SubMenus().SubMenuLista_Raya_Alimentacion ();
	Obj_CheckBoxNode Lista_Raya_Alimentacion[] = {
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[0], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[1], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[2], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[3], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[4], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[5], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[6], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Alimentacion[7], false),
	};	
	
	String[] Sub_Lista_Raya_Comparaciones = new Obj_SubMenus().SubMenuLista_Raya_Comparaciones();
	Obj_CheckBoxNode Lista_Raya_Comparaciones[] = {
		new Obj_CheckBoxNode(Sub_Lista_Raya_Comparaciones[0], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Comparaciones[1], false),
	};
	
	String[] Sub_Lista_Raya_Autorizaciones = new Obj_SubMenus().SubMenuLista_Raya_Autorizaciones();
	Obj_CheckBoxNode Lista_Raya_Autorizaciones[] = {
		new Obj_CheckBoxNode(Sub_Lista_Raya_Autorizaciones[0], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Autorizaciones[1], false),
	};
	
	String[] Sub_Lista_Raya_Departamento_Cortes = new Obj_SubMenus().SubMenuLista_Raya_Departamento_Cortes();
	Obj_CheckBoxNode Lista_Raya_Departamento_Cortes[] = {
		new Obj_CheckBoxNode(Sub_Lista_Raya_Departamento_Cortes[0], false),
	};
	
	String[] Sub_Lista_Raya_Reportes = new Obj_SubMenus().SubMenuLista_Raya_Reportes();
	Obj_CheckBoxNode Lista_Raya_Reportes[] = {
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[0], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[1], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[2], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[3], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[4], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[5], false),
		new Obj_CheckBoxNode(Sub_Lista_Raya_Reportes[6], false),
	};	
	
	@SuppressWarnings("rawtypes")
	Vector CatalogoVector = new Obj_NombreVector("Catalogos", Catalogo);
	    
	@SuppressWarnings("rawtypes")
	Vector ConfiguracionVector = new Obj_NombreVector("Configuración", Configuracion);
	
	@SuppressWarnings("rawtypes")
	Vector ContabilidadVector = new Obj_NombreVector("Contabilidad Conciliación Aux F.", Contabilidad_Conciliacion_Auxiliar_Finanzas);
	
	@SuppressWarnings("rawtypes")
	Vector Alimentacion_CuadrantesVector = new Obj_NombreVector("Alimentacion de Cuadrantes", Cuadrantes_Alimentacion);
	
	@SuppressWarnings("rawtypes")
	Vector Catalogo_CuadrantesVector = new Obj_NombreVector("Catalogo de Cuadrantes", Cuadrantes_Catalogo);
	
	@SuppressWarnings("rawtypes")
	Vector Reportes_CuadrantesVector = new Obj_NombreVector("Reportes de Cuadrantes", Cuadrantes_Reportes);
	
	@SuppressWarnings("rawtypes")
	Vector Lista_Raya_AlimentacionVector = new Obj_NombreVector("Alimentación de Lista Raya", Lista_Raya_Alimentacion);
	
	@SuppressWarnings("rawtypes")
	Vector Lista_Raya_ComparacionVector = new Obj_NombreVector("Comparaciones de Lista Raya", Lista_Raya_Comparaciones);
	
	@SuppressWarnings("rawtypes")
	Vector Lista_Raya_AutorizacionesVector = new Obj_NombreVector("Autorizaciones de Lista Raya", Lista_Raya_Autorizaciones);
	
	@SuppressWarnings("rawtypes")
	Vector Lista_Raya_Departemento_CortesVector = new Obj_NombreVector("Departamentos de Cortes de Lista Raya", Lista_Raya_Departamento_Cortes);
	
	@SuppressWarnings("rawtypes")
	Vector Lista_Raya_ReporteVector = new Obj_NombreVector("Reportes de Lista Raya", Lista_Raya_Reportes);
	
	Object rootNodos[] = { CatalogoVector, ConfiguracionVector, ContabilidadVector, Alimentacion_CuadrantesVector, Catalogo_CuadrantesVector, 
						   Reportes_CuadrantesVector, Lista_Raya_AlimentacionVector, Lista_Raya_ComparacionVector, Lista_Raya_AutorizacionesVector,
						   Lista_Raya_Departemento_CortesVector, Lista_Raya_ReporteVector};
	    
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
	public Cat_Usuario(){
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
		btnLimpiar.addActionListener(opLimpiar);
		
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
	
	ActionListener opLimpiar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			txtContrasena.setText("");
			txtContrasena1.setText("");
    		txtContrasena.setEnabled(true);
    		
    		for(int i = 0; i<Catalogo.length; i++){
    			Catalogo[i].setSelected(false);
    		}
    		tree.collapseRow(0);
    		for(int i=0; i <Configuracion.length; i ++){
    			Configuracion[i].setSelected(false);
    		}
    		tree.collapseRow(1);
    		for(int i = 0; i<Contabilidad_Conciliacion_Auxiliar_Finanzas.length; i ++){
    			Contabilidad_Conciliacion_Auxiliar_Finanzas[i].setSelected(false);
    		}
    		tree.collapseRow(2);
    		for(int i = 0; i<Cuadrantes_Alimentacion.length; i ++){
    			Cuadrantes_Alimentacion[i].setSelected(false);
    		}
    		tree.collapseRow(3);
    		for(int i = 0; i<Cuadrantes_Catalogo.length; i ++){
    			Cuadrantes_Catalogo[i].setSelected(false);
    		}
    		tree.collapseRow(4);
    		for(int i = 0; i<Cuadrantes_Reportes.length; i ++){
    			Cuadrantes_Reportes[i].setSelected(false);
    		}
    		tree.collapseRow(5);
    		for(int i = 0; i<Lista_Raya_Alimentacion.length; i ++){
    			Lista_Raya_Alimentacion[i].setSelected(false);
    		}
    		tree.collapseRow(6);
    		for(int i = 0; i<Lista_Raya_Comparaciones.length; i ++){
    			Lista_Raya_Comparaciones[i].setSelected(false);
    		}
    		tree.collapseRow(7);
    		for(int i = 0; i<Lista_Raya_Autorizaciones.length; i ++){
    			Lista_Raya_Autorizaciones[i].setSelected(false);
    		}
    		tree.collapseRow(8);
    		for(int i = 0; i<Lista_Raya_Departamento_Cortes.length; i ++){
    			Lista_Raya_Departamento_Cortes[i].setSelected(false);
    		}
    		tree.collapseRow(9);
    		for(int i = 0; i<Lista_Raya_Reportes.length; i ++){
    			Lista_Raya_Reportes[i].setSelected(false);
    		}
    		tree.collapseRow(10);
  		}
		
	};
	
	MouseAdapter opMouse = new MouseAdapter(){
		@SuppressWarnings("rawtypes")
		public void mouseClicked(MouseEvent arg0){
			if(arg0.getClickCount() == 1){
				int fila = tabla.getSelectedRow();
    			Object folio =  tabla.getValueAt(fila, 0);
    			Object Nombre_Completo = tabla.getValueAt(fila, 1);
    			
				txtFolio.setText(folio.toString());
        		txtNombre_Completo.setText(Nombre_Completo.toString());
        		txtContrasena1.setText("");
        		
				if(new Obj_Usuario().ExisteUsuario(Nombre_Completo.toString()) == true){
					
					txtFolio.setText(folio.toString());
	        		txtNombre_Completo.setText(Nombre_Completo.toString());

	        		Obj_Usuario usuario = new Obj_Usuario().BuscarUsuario(Nombre_Completo.toString());
	        		txtContrasena.setText(usuario.getContrasena());
	        		txtContrasena.setEnabled(false);
	        		
	        		Vector catalogo = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(),1);
	        		for(int i = 0; i<Catalogo.length; i++){
	        			Catalogo[i].setSelected(Boolean.parseBoolean(catalogo.get(i).toString()));
	        		}
	        		tree.collapseRow(0);
	        		
	        		Vector configuracion = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 2);
	        		for(int i=0; i <Configuracion.length; i ++){
	        			Configuracion[i].setSelected(Boolean.parseBoolean(configuracion.get(i).toString()));
	        		}
	        		tree.collapseRow(1);
	        		
	        		Vector contabilidad_conciliacion_auxiliar_finanzas = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 3);
	        		for(int i = 0; i<Contabilidad_Conciliacion_Auxiliar_Finanzas.length; i ++){
	        			Contabilidad_Conciliacion_Auxiliar_Finanzas[i].setSelected(Boolean.parseBoolean(contabilidad_conciliacion_auxiliar_finanzas.get(i).toString()));
	        		}
	        		tree.collapseRow(2);
	        		
	        		Vector cuadrantes_alimentacion = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 4);
	        		for(int i = 0; i<Cuadrantes_Alimentacion.length; i ++){
	        			Cuadrantes_Alimentacion[i].setSelected(Boolean.parseBoolean(cuadrantes_alimentacion.get(i).toString()));
	        		}
	        		tree.collapseRow(3);
	        		
	        		Vector cuadrantes_catalogo = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 5);
	        		for(int i = 0; i<Cuadrantes_Catalogo.length; i ++){
	        			Cuadrantes_Catalogo[i].setSelected(Boolean.parseBoolean(cuadrantes_catalogo.get(i).toString()));
	        		}
	        		tree.collapseRow(4);
	        		
	        		Vector cuadrantes_reportes = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 6);
	        		for(int i = 0; i<Cuadrantes_Reportes.length; i ++){
	        			Cuadrantes_Reportes[i].setSelected(Boolean.parseBoolean(cuadrantes_reportes.get(i).toString()));
	        		}
	        		tree.collapseRow(5);
	        		
	        		Vector lista_raya_alimentacion = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 7);
	        		for(int i = 0; i<Lista_Raya_Alimentacion.length; i ++){
	        			Lista_Raya_Alimentacion[i].setSelected(Boolean.parseBoolean(lista_raya_alimentacion.get(i).toString()));
	        		}
	        		tree.collapseRow(6);
	        		
	        		Vector lista_raya_comparaciones = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 8);
	        		for(int i = 0; i<Lista_Raya_Comparaciones.length; i ++){
	        			Lista_Raya_Comparaciones[i].setSelected(Boolean.parseBoolean(lista_raya_comparaciones.get(i).toString()));
	        		}
	        		tree.collapseRow(7);
	        		
	        		Vector lista_raya_autorizaciones = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 9);
	        		for(int i = 0; i<Lista_Raya_Autorizaciones.length; i ++){
	        			Lista_Raya_Autorizaciones[i].setSelected(Boolean.parseBoolean(lista_raya_autorizaciones.get(i).toString()));
	        		}
	        		tree.collapseRow(8);
	        		
	        		Vector lista_raya_departamento_cortes = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 10);
	        		for(int i = 0; i<Lista_Raya_Departamento_Cortes.length; i ++){
	        			Lista_Raya_Departamento_Cortes[i].setSelected(Boolean.parseBoolean(lista_raya_departamento_cortes.get(i).toString()));
	        		}
	        		tree.collapseRow(9);
	        		
	        		Vector lista_raya_reportes = new Obj_Usuario().returnPermisos(txtNombre_Completo.getText(), 11);
	        		for(int i = 0; i<Lista_Raya_Reportes.length; i ++){
	        			Lista_Raya_Reportes[i].setSelected(Boolean.parseBoolean(lista_raya_reportes.get(i).toString()));
	        		}
	        		tree.collapseRow(10);
	        
	        		
				}else{
					txtContrasena.setText("");
	        		txtContrasena.setEnabled(true);
	        		
	        		for(int i = 0; i<Catalogo.length; i++){
	        			Catalogo[i].setSelected(false);
	        		}
	        		tree.collapseRow(0);
	        		
	        		for(int i=0; i <Configuracion.length; i ++){
	        			Configuracion[i].setSelected(false);
	        		}
	        		tree.collapseRow(1);
	        		
	        		for(int i = 0; i<Contabilidad_Conciliacion_Auxiliar_Finanzas.length; i ++){
	        			Contabilidad_Conciliacion_Auxiliar_Finanzas[i].setSelected(false);
	        		}
	        		tree.collapseRow(2);
	        		
	        		for(int i = 0; i<Cuadrantes_Alimentacion.length; i ++){
	        			Cuadrantes_Alimentacion[i].setSelected(false);
	        		}
	        		tree.collapseRow(3);
	        		
	        		for(int i = 0; i<Cuadrantes_Catalogo.length; i ++){
	        			Cuadrantes_Catalogo[i].setSelected(false);
	        		}
	        		tree.collapseRow(4);
	        		
	        		for(int i = 0; i<Cuadrantes_Reportes.length; i ++){
	        			Cuadrantes_Reportes[i].setSelected(false);
	        		}
	        		tree.collapseRow(5);
	        		
	        		for(int i = 0; i<Lista_Raya_Alimentacion.length; i ++){
	        			Lista_Raya_Alimentacion[i].setSelected(false);
	        		}
	        		tree.collapseRow(6);
	        		for(int i = 0; i<Lista_Raya_Comparaciones.length; i ++){
	        			Lista_Raya_Comparaciones[i].setSelected(false);
	        		}
	        		tree.collapseRow(7);
	        		for(int i = 0; i<Lista_Raya_Autorizaciones.length; i ++){
	        			Lista_Raya_Autorizaciones[i].setSelected(false);
	        		}
	        		tree.collapseRow(8);
	        		for(int i = 0; i<Lista_Raya_Departamento_Cortes.length; i ++){
	        			Lista_Raya_Departamento_Cortes[i].setSelected(false);
	        		}
	        		tree.collapseRow(9);
	        		for(int i = 0; i<Lista_Raya_Reportes.length; i ++){
	        			Lista_Raya_Reportes[i].setSelected(false);
	        		}
	        		tree.collapseRow(10);
	        		
				}        		
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
				Obj_MD5 algoritmo = new Obj_MD5();
				
				if(txtContrasena.isEnabled()){
					if(txtContrasena.getText().equals(txtContrasena1.getText())){
						Obj_Usuario usuario = new Obj_Usuario().BuscarUsuario(txtNombre_Completo.getText());
						Vector subMenus = vectorComponentes(tree);
						if(usuario.getContrasena().equals(algoritmo.cryptMD5(txtContrasena.getText(), "izagar"))){
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;	
						}else{
							usuario.setFolio(Integer.parseInt(txtFolio.getText()));
							usuario.setNombre_completo(txtNombre_Completo.getText());
							usuario.setContrasena(algoritmo.cryptMD5(txtContrasena1.getText(),"izagar"));
							usuario.guardarPermisos(subMenus);
							JOptionPane.showMessageDialog(null,"El registro se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						}
						
					} else{
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;	
					}
					
				}else{
					if(txtContrasena.getText().equals(algoritmo.cryptMD5(txtContrasena1.getText(), "izagar"))){
						Obj_Usuario usuario = new Obj_Usuario().BuscarUsuario(txtNombre_Completo.getText());
						Vector subMenus = vectorComponentes(tree);
						if(usuario.getContrasena().equals(algoritmo.cryptMD5(txtContrasena.getText(), "izagar"))){
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;	
						}else{
							if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
								usuario.actualizar(txtNombre_Completo.getText(), subMenus);
								JOptionPane.showMessageDialog(null,"El registro se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
							}else{
								return;
							}
						}
						
					} else{
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;	
					}
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
		try	{  
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Usuario().setVisible(true);
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
