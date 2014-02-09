package cuadrantes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;


import SQL.Connexion;

import objetos.Obj_Alimentacion_Cuadrante;

@SuppressWarnings("serial")
public class Cat_Alimentacion_Cuadrante extends JFrame {

	private final String NOMBRECOMPLETO;
	
	JLabel lblCuadranteLleno = new JLabel("<Cuadrante Concluido>");
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JButton btnGuardarLibre = new JButton("Guardar");
	JButton btnGuardarLibreConts = new JButton("Guardar");
	JButton btnEditarLibreConts = new JButton("Editar");
	JButton btnGuardarMultiple = new JButton("Guardar");
	JButton btnGuardarMultipleConts = new JButton("Guardar");
	JButton btnEditarMultipleConts = new JButton("Editar");
	
	/* OPCION MEDIA MULTIPLE JERARQUICA */
	Cat_Plantilla_Tabla_Cuadrante plantillaMultipleJerarquica = new Cat_Plantilla_Tabla_Cuadrante();
	Cat_Plantilla_Tabla_Cuadrante plantillaMultipleJerarquicaConts = new Cat_Plantilla_Tabla_Cuadrante();
	
	/* OPCION MULTIPLE */
	Cat_Plantilla_Tabla_Cuadrante plantillaMultiple = new Cat_Plantilla_Tabla_Cuadrante();
	Cat_Plantilla_Tabla_Cuadrante plantillaMultipleContestada = new Cat_Plantilla_Tabla_Cuadrante();
	
	/* OPCION LIBRE */
	Cat_Plantilla_Tabla_Cuadrante plantillaLibreJerarquica = new Cat_Plantilla_Tabla_Cuadrante();
	Cat_Plantilla_Tabla_Cuadrante plantillaLibreJerarquicaConts = new Cat_Plantilla_Tabla_Cuadrante();
	
	Cat_Plantilla_Tabla_Cuadrante plantillaLibre = new Cat_Plantilla_Tabla_Cuadrante();
	Cat_Plantilla_Tabla_Cuadrante plantillaLibreContestada = new Cat_Plantilla_Tabla_Cuadrante();
	
	JTextField txtNombre_Completo 	= new JTextField();
	JTextField txtPuesto 			= new JTextField();
	JTextField txtEstablecimiento	= new JTextField();
	JTextField txtEquipo_Trabajo 	= new JTextField();
	JTextField txtJefatura 			= new JTextField();
	JTextField txtDia 				= new JTextField();
	JTextField txtFecha 			= new JTextField();
	JTextField txtCuadrante 		= new JTextField();
	
	@SuppressWarnings("rawtypes")
	JComboBox cmbMultiple = new JComboBox();
	
	JButton btnSalir = new JButton("Salir");
	JButton btnTerminarCaptura = new JButton("Terminar Captura del Cuadrante");
	JButton btnFoto = new JButton();
	
	JLayeredPane panelMultiple = new JLayeredPane();
	JLayeredPane panelMultipleContes = new JLayeredPane();
	JLayeredPane panelLibre    = new JLayeredPane();
	JLayeredPane panelLibreContes = new JLayeredPane();
	
	JTabbedPane paneles = new JTabbedPane();
	
	public Cat_Alimentacion_Cuadrante(String Nombre_Usuario) {
		NOMBRECOMPLETO = Nombre_Usuario;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_captura_icon&16.png"));
		this.setTitle("Alimentación de Cuadrante");
		this.panel.setBorder(BorderFactory.createTitledBorder("Alimentación de Cuadrante"));

		this.plantillaMultipleJerarquica.tablaPlantillaMultiple.getColumnModel().getColumn(1).setHeaderValue("Actividades Asignadas por Nivel Jerarquico");
		this.plantillaMultiple.tablaPlantillaMultiple.getColumnModel().getColumn(1).setHeaderValue("Actividades Asignadas por Cuadrante");
		
		this.plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getColumnModel().getColumn(1).setHeaderValue("Actividades Contestadas por Nivel Jerarquico");
		this.plantillaMultipleContestada.tablaPlantillaMultiple.getColumnModel().getColumn(1).setHeaderValue("Actividades Contestadas por Cuadrante");
		
		this.plantillaLibreJerarquica.tablaPlantillaLibre.getColumnModel().getColumn(1).setHeaderValue("Actividades por Avance por Nivel Jerarquico");
		this.plantillaLibreJerarquica.tablaPlantillaLibre.getColumnModel().getColumn(1).setMaxWidth(300);
		this.plantillaLibreJerarquica.tablaPlantillaLibre.getColumnModel().getColumn(1).setMinWidth(300);
		this.plantillaLibre.tablaPlantillaLibre.getColumnModel().getColumn(1).setHeaderValue("Actividades por Avance por Cuadrante");
		this.plantillaLibre.tablaPlantillaLibre.getColumnModel().getColumn(1).setMaxWidth(300);
		this.plantillaLibre.tablaPlantillaLibre.getColumnModel().getColumn(1).setMinWidth(300);
		
		this.plantillaLibreJerarquicaConts.tablaPlantillaLibre.getColumnModel().getColumn(1).setHeaderValue("Actividades Contestadas de Avance por Nivel Jerarquico");
		this.plantillaLibreJerarquicaConts.tablaPlantillaLibre.getColumnModel().getColumn(1).setMaxWidth(300);
		this.plantillaLibreJerarquicaConts.tablaPlantillaLibre.getColumnModel().getColumn(1).setMinWidth(300);
		this.plantillaLibreContestada.tablaPlantillaLibre.getColumnModel().getColumn(1).setHeaderValue("Actividades Contestadas de Avance por Cuadrante");
		this.plantillaLibreContestada.tablaPlantillaLibre.getColumnModel().getColumn(1).setMaxWidth(300);
		this.plantillaLibreContestada.tablaPlantillaLibre.getColumnModel().getColumn(1).setMinWidth(300);
		
		this.panel.add(new JLabel("Nombre:")).setBounds(40,30,50,20);
		this.panel.add(txtNombre_Completo).setBounds(150,30,250,20);
		
		this.panel.add(btnTerminarCaptura).setBounds(750,30,200,20);
		this.panel.add(btnSalir).setBounds(750,60,100,20);
		
		this.panel.add(new JLabel("Puesto:")).setBounds(40,60,50,20);
		this.panel.add(txtPuesto).setBounds(150,60,250,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(40,90,80,20);
		this.panel.add(txtEstablecimiento).setBounds(150,90,250,20);
		
		this.panel.add(new JLabel("Equipo De Trabajo:")).setBounds(40,120,100,20);
		this.panel.add(txtEquipo_Trabajo).setBounds(150,120,250,20);
		
		this.panel.add(new JLabel("Jefatura:")).setBounds(40,150,50,20);
		this.panel.add(txtJefatura).setBounds(150,150,250,20);
		
		this.panel.add(new JLabel("Fecha:")).setBounds(40,180,40,20);
		this.panel.add(txtFecha).setBounds(150,180,100,20);
		
		this.panel.add(new JLabel("Dia:")).setBounds(260,180,40,20);
		this.panel.add(txtDia).setBounds(300,180,100,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(40,210,60,20);
		this.panel.add(txtCuadrante).setBounds(150,210,250,20);
	
		this.panel.add(btnFoto).setBounds(470,30,235,200);
		
		this.panel.add(lblCuadranteLleno).setBounds(530,230,235,20);
	
		this.paneles.addTab("Actividades de Rutina", panelMultiple);
		this.paneles.addTab("Actividades de Rutina Contestadas", panelMultipleContes);
		this.paneles.addTab("Actividades por Avance", panelLibre);
		this.paneles.addTab("Actividades por Avance Contestadas", panelLibreContes);
		
		this.panelMultiple.add(btnGuardarMultiple).setBounds(10,10,115,20);
		this.panelMultipleContes.add(btnGuardarMultipleConts).setBounds(10,10,75,20);
		this.panelMultipleContes.add(btnEditarMultipleConts).setBounds(90,10,75,20);
		this.panelLibre.add(btnGuardarLibre).setBounds(10,10,115,20);
		this.panelLibreContes.add(btnGuardarLibreConts).setBounds(10,10,75,20);
		this.panelLibreContes.add(btnEditarLibreConts).setBounds(90,10,75,20);
		
		this.panelMultiple.add(plantillaMultipleJerarquica.scrollMultiple).setBounds(5,35,905,120);
		this.panelMultiple.add(plantillaMultiple.scrollMultiple).setBounds(5,170,905,245);
		
		this.panelMultipleContes.add(plantillaMultipleJerarquicaConts.scrollMultiple).setBounds(5,35,905,120);
		this.panelMultipleContes.add(plantillaMultipleContestada.scrollMultiple).setBounds(5,170,905,245);
		
		this.panelLibre.add(plantillaLibreJerarquica.scrollLibre).setBounds(5,35,905,120);
		this.panelLibre.add(plantillaLibre.scrollLibre).setBounds(5,170,905,245);
		
		this.panelLibreContes.add(plantillaLibreJerarquicaConts.scrollLibre).setBounds(5,35,905,120);
		this.panelLibreContes.add(plantillaLibreContestada.scrollLibre).setBounds(5,170,905,245);
		
		this.panel.add(paneles).setBounds(35,240,920,450);
		
		this.lblCuadranteLleno.setVisible(false);
		this.lblCuadranteLleno.setForeground(Color.BLUE);
		
		this.btnSalir.addActionListener(salir);
		this.btnGuardarMultiple.addActionListener(op_guardar_multiple);
		this.btnGuardarMultipleConts.addActionListener(op_guardar_multiple_conts);
		this.btnEditarMultipleConts.addActionListener(op_editar_multiple_conts);
		this.btnTerminarCaptura.addActionListener(op_terminar_captura);
		
		/** BOTON GUARDAR PARA OPCIONES LIBRE **/
		this.btnGuardarLibre.addActionListener(op_guardar_libre);
		this.btnGuardarLibreConts.addActionListener(op_guardar_libre_contestada);
		this.btnEditarLibreConts.addActionListener(op_editar_libre_conts);
		
		this.CamposEnabled(false);
		
		this.init(Nombre_Usuario);
		this.init_tabla_multiple_conts();

		this.cont.add(panel);
		this.setResizable(false);
		
		this.setSize(1000,(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener op_terminar_captura = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(null, "Al terminar la edición del CUADRANTE tenga en cuenta que \n No podrá hacer ninguna otra modificación. \n\n Si algúna actividad no está contestada esta se agregará por defult No. \n\n \n\n  ¿Desea terminar la edición del cuadrante?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				if(new Obj_Alimentacion_Cuadrante().terminar_captura(NOMBRECOMPLETO)){
					tablas_procesar();
					JOptionPane.showMessageDialog(null,"Se ha teminado la edición del cuadrante", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "No se pudo terminar la edición del cuadrante", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
	
	public void init_tabla_multiple_conts(){
		this.plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.setEnabled(false);
		this.plantillaMultipleContestada.tablaPlantillaMultiple.setEnabled(false);
		this.btnGuardarMultipleConts.setEnabled(false);
		
		this.plantillaLibreContestada.tablaPlantillaLibre.setEnabled(false);
		this.plantillaLibreJerarquicaConts.tablaPlantillaLibre.setEnabled(false);
		this.btnGuardarLibreConts.setEnabled(false);
	}
	
	ActionListener op_editar_multiple_conts = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.setEnabled(true);
			plantillaMultipleContestada.tablaPlantillaMultiple.setEnabled(true);
			btnGuardarMultipleConts.setEnabled(true);
		}
	};
	
	ActionListener op_editar_libre_conts = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			plantillaLibreContestada.tablaPlantillaLibre.setEnabled(true);
			plantillaLibreJerarquicaConts.tablaPlantillaLibre.setEnabled(true);
			btnGuardarLibreConts.setEnabled(true);
		}
	};
	
	public void init(String Nombre_Usuario){
		Obj_Alimentacion_Cuadrante datos_cuadrante = new Obj_Alimentacion_Cuadrante().buscarEmpleado(Nombre_Usuario);

		txtNombre_Completo.setText(datos_cuadrante.getNombre());
		txtPuesto.setText(datos_cuadrante.getPuesto());
		txtEstablecimiento.setText(datos_cuadrante.getEstablecimiento());
		txtEquipo_Trabajo.setText(datos_cuadrante.getEquipo_trabajo());
		txtJefatura.setText(datos_cuadrante.getJefatura());
		txtFecha.setText(datos_cuadrante.getFecha());
		txtDia.setText(datos_cuadrante.getDia());
		txtCuadrante.setText(datos_cuadrante.getCuadrante());
		
		ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp_cuadrante/tmp.jpg");
	    btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)));	
	    
	    tablas_procesar();
		
	}
	
	public void tablas_procesar(){
		if(new Obj_Alimentacion_Cuadrante().status_llanado_tabla(NOMBRECOMPLETO)){
			JOptionPane.showMessageDialog(null, "En hora Buena! \n Ya llenó su cuadrante!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			lblCuadranteLleno.setVisible(true);
		}else{
			/* SE RESETEAN LAS TABLAS */
			while(plantillaMultipleJerarquica.tablaPlantillaMultiple.getRowCount() > 0)
				plantillaMultipleJerarquica.modelPlantillaMultiple.removeRow(0);
			while(plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getRowCount() > 0)
				plantillaMultipleJerarquicaConts.modelPlantillaMultiple.removeRow(0);
			
			while(plantillaMultiple.tablaPlantillaMultiple.getRowCount() > 0)
				plantillaMultiple.modelPlantillaMultiple.removeRow(0);
			while(plantillaMultipleContestada.tablaPlantillaMultiple.getRowCount() > 0)
				plantillaMultipleContestada.modelPlantillaMultiple.removeRow(0);

			while(plantillaLibreJerarquica.tablaPlantillaLibre.getRowCount() > 0)
				plantillaLibreJerarquica.modelPlantillaLibre.removeRow(0);
			while(plantillaLibreJerarquicaConts.tablaPlantillaLibre.getRowCount() > 0)
				plantillaLibreJerarquicaConts.modelPlantillaLibre.removeRow(0);
			
			while(plantillaLibre.tablaPlantillaLibre.getRowCount() > 0)
				plantillaLibre.modelPlantillaLibre.removeRow(0);
			while(plantillaLibreContestada.tablaPlantillaLibre.getRowCount() > 0)
				plantillaLibreContestada.modelPlantillaLibre.removeRow(0);
			
			String[][] info_tabla_multiple_jerarquico = new Obj_Alimentacion_Cuadrante().buscarTablaMultipleJerarquico(NOMBRECOMPLETO);
	    	
			String [] fila_multiple_jerarquico = new String[4];
			
			List<String[]> lista_jerarquico = new ArrayList<String[]>();
			
			for(int i=0; i<info_tabla_multiple_jerarquico.length; i++){
		    
				lista_jerarquico.add(new Obj_Alimentacion_Cuadrante().ComboBox(Integer.parseInt(info_tabla_multiple_jerarquico[i][0].toString())));
	            	            
	            TableColumn col = plantillaMultipleJerarquica.tablaPlantillaMultiple.getColumnModel().getColumn(2);
	            
	            col.setCellEditor(new MyComboEditor(lista_jerarquico));
	            
	            fila_multiple_jerarquico[0]= info_tabla_multiple_jerarquico[i][1]+"  ";
	            fila_multiple_jerarquico[1]= "   "+info_tabla_multiple_jerarquico[i][2];
	            fila_multiple_jerarquico[2]= "   Respuestas";
	            fila_multiple_jerarquico[3]= "   ";
				
				plantillaMultipleJerarquica.modelPlantillaMultiple.addRow(fila_multiple_jerarquico);
			}
			
			String[][] info_tabla_multiple = new Obj_Alimentacion_Cuadrante().buscarTablaMultiple(NOMBRECOMPLETO);
	    	
			String [] fila_multiple = new String[4];
			
			List<String[]> lista = new ArrayList<String[]>();
			
			for(int i=0; i<info_tabla_multiple.length; i++){
		    
	            lista.add(new Obj_Alimentacion_Cuadrante().ComboBox(Integer.parseInt(info_tabla_multiple[i][0].toString())));
	            	            
	            TableColumn col = plantillaMultiple.tablaPlantillaMultiple.getColumnModel().getColumn(2);
	            
	            col.setCellEditor(new MyComboEditor(lista));
	            
				fila_multiple[0]= info_tabla_multiple[i][1]+"  ";
				fila_multiple[1]= "   "+info_tabla_multiple[i][2];
				fila_multiple[2]= "   Respuestas";
				fila_multiple[3]= "   ";
				
				plantillaMultiple.modelPlantillaMultiple.addRow(fila_multiple);
			}
			
			String[][] info_tabla_multiple_capturada_jerarquico = new Obj_Alimentacion_Cuadrante().buscarTablaMultipleCapturadaJerarquico(NOMBRECOMPLETO);
			String [] fila_multiple_capturada_jerarquico = new String[6];
			List<String[]> listaCapturadaJerarquico = new ArrayList<String[]>();			
			
			for(int i=0; i<info_tabla_multiple_capturada_jerarquico.length; i++){
			    
				listaCapturadaJerarquico.add(new Obj_Alimentacion_Cuadrante().ComboBox(Integer.parseInt(info_tabla_multiple_capturada_jerarquico[i][0].toString())));
	            	            
	            TableColumn col = plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getColumnModel().getColumn(2);
	            
	            col.setCellEditor(new MyComboEditor(listaCapturadaJerarquico));
	            
	            fila_multiple_capturada_jerarquico[0]= info_tabla_multiple_capturada_jerarquico[i][1]+"  ";
	            fila_multiple_capturada_jerarquico[1]= "   "+info_tabla_multiple_capturada_jerarquico[i][2];
	            fila_multiple_capturada_jerarquico[2]= "   "+info_tabla_multiple_capturada_jerarquico[i][3];
	            fila_multiple_capturada_jerarquico[3]= "   "+info_tabla_multiple_capturada_jerarquico[i][4];
				
	            plantillaMultipleJerarquicaConts.modelPlantillaMultiple.addRow(fila_multiple_capturada_jerarquico);
			}
			
			String[][] info_tabla_multiple_capturada = new Obj_Alimentacion_Cuadrante().buscarTablaMultipleCapturada(NOMBRECOMPLETO);
			String [] fila_multiple_capturada = new String[6];
			List<String[]> listaCapturada = new ArrayList<String[]>();			
			
			for(int i=0; i<info_tabla_multiple_capturada.length; i++){
			    
				listaCapturada.add(new Obj_Alimentacion_Cuadrante().ComboBox(Integer.parseInt(info_tabla_multiple_capturada[i][0].toString())));
	            	            
	            TableColumn col = plantillaMultipleContestada.tablaPlantillaMultiple.getColumnModel().getColumn(2);
	            
	            col.setCellEditor(new MyComboEditor(listaCapturada));
	            
	            fila_multiple_capturada[0]= info_tabla_multiple_capturada[i][1]+"  ";
	            fila_multiple_capturada[1]= "   "+info_tabla_multiple_capturada[i][2];
	            fila_multiple_capturada[2]= "   "+info_tabla_multiple_capturada[i][3];
	            fila_multiple_capturada[3]= "   "+info_tabla_multiple_capturada[i][4];
				
				plantillaMultipleContestada.modelPlantillaMultiple.addRow(fila_multiple_capturada);
			}
			
			String[][] info_tabla_libre_jerarquico = new Obj_Alimentacion_Cuadrante().buscarTablaLibreJerarquico(NOMBRECOMPLETO);
			for(String[] tmpArray : info_tabla_libre_jerarquico)
				plantillaLibreJerarquica.modelPlantillaLibre.addRow(tmpArray);
			
			String[][] info_tabla_libre_jerarquico_contestada = new Obj_Alimentacion_Cuadrante().buscarTablaLibreJerarquicoContestada(NOMBRECOMPLETO);
			for(String[] tmpArray : info_tabla_libre_jerarquico_contestada)
				plantillaLibreJerarquicaConts.modelPlantillaLibre.addRow(tmpArray);
				
			String[][] info_tabla_libre = new Obj_Alimentacion_Cuadrante().buscarTablaLibre(NOMBRECOMPLETO);
			for(String[] tmpArray : info_tabla_libre)
				plantillaLibre.modelPlantillaLibre.addRow(tmpArray);
			
			String[][] info_tabla_libre_contestada = new Obj_Alimentacion_Cuadrante().buscarTablaLibreContestada(NOMBRECOMPLETO);
			for(String[] tmpArray : info_tabla_libre_contestada)
				plantillaLibreContestada.modelPlantillaLibre.addRow(tmpArray);
			
			
			init_tabla_multiple_conts();
		}
	}
	
	public String procesa_texto(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens())
	    	texto += " "+tokens.nextToken();

	    texto = texto.toString();
	    texto = texto.trim().toUpperCase();
	    return texto;
	}
	
	private class MyComboEditor extends DefaultCellEditor{
        List<String[]> values;
        @SuppressWarnings("rawtypes")
		public MyComboEditor(List<String[]> values){
        	super(new JComboBox());
            this.values = values;
        }
         
        @SuppressWarnings({ "rawtypes", "unchecked" })
		public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
        	JComboBox combo = (JComboBox)getComponent();
        	
        	combo.removeAllItems();
            String[] valores = values.get(row);
                
            for(int i=0; i<valores.length; i++)
            	combo.addItem(valores[i]);

            return combo;          
        }
    }
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next())
				filas++;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	ActionListener op_guardar_multiple = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaMultiple.tablaPlantillaMultiple.isEditing())
				plantillaMultiple.tablaPlantillaMultiple.getCellEditor().stopCellEditing();
			if(plantillaMultipleJerarquica.tablaPlantillaMultiple.isEditing())
				plantillaMultipleJerarquica.tablaPlantillaMultiple.getCellEditor().stopCellEditing();
			
			if(new Obj_Alimentacion_Cuadrante().guardar(tabla_multiple(), tabla_multiple_jerarquico())){
				JOptionPane.showMessageDialog(null, "El registro se guardó con exito!" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				tablas_procesar();
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de almacenar el registro" , "Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_guardar_multiple_conts = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.isEditing())
				plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getCellEditor().stopCellEditing();
			if(plantillaMultipleContestada.tablaPlantillaMultiple.isEditing())
				plantillaMultipleContestada.tablaPlantillaMultiple.getCellEditor().stopCellEditing();
		
			if(new Obj_Alimentacion_Cuadrante().guardar(tabla_multiple_conts(), tabla_multiple_jerarquico_const())){
				plantillaMultipleContestada.tablaPlantillaMultiple.setEnabled(false);
				plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.setEnabled(false);
				btnGuardarMultipleConts.setEnabled(false);
				JOptionPane.showMessageDialog(null, "El registro se guardó con exito!" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de almacenar el registro" , "Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_guardar_libre = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaLibreJerarquica.tablaPlantillaLibre.isEditing())
				plantillaLibreJerarquica.tablaPlantillaLibre.getCellEditor().stopCellEditing();
			if(plantillaLibre.tablaPlantillaLibre.isEditing())
				plantillaLibre.tablaPlantillaLibre.getCellEditor().stopCellEditing();
			
			if(new Obj_Alimentacion_Cuadrante().guardarLibre(tabla_LIBRE(), tabla_LIBRE_jerarquico())){
				JOptionPane.showMessageDialog(null, "El registro se guardó con exito!" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				tablas_procesar();
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de almacenar el registro" , "Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener op_guardar_libre_contestada = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaLibreContestada.tablaPlantillaLibre.isEditing())
				plantillaLibreContestada.tablaPlantillaLibre.getCellEditor().stopCellEditing();
			if(plantillaLibreJerarquicaConts.tablaPlantillaLibre.isEditing())
				plantillaLibreJerarquicaConts.tablaPlantillaLibre.getCellEditor().stopCellEditing();
			
			if(new Obj_Alimentacion_Cuadrante().guardarLibre(tabla_LIBRE_Contestada(), tabla_LIBRE_jerarquico_contestada())){
				JOptionPane.showMessageDialog(null, "El registro se guardó con exito!" , "Aviso", JOptionPane.INFORMATION_MESSAGE);
				tablas_procesar();
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de almacenar el registro" , "Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};	
	
	public Object[][] tabla_multiple_jerarquico(){
		Object[][] multiple = new Object[plantillaMultipleJerarquica.tablaPlantillaMultiple.getRowCount()][11];
		try{
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i=0; i<plantillaMultipleJerarquica.tablaPlantillaMultiple.getRowCount(); i++){
				multiple[i][0] = txtCuadrante.getText().toString().trim();
				multiple[i][1] = txtNombre_Completo.getText().toString().trim();
				multiple[i][2] = txtEstablecimiento.getText().toString().trim();
				multiple[i][3] = txtPuesto.getText().toString().trim();
				multiple[i][4] = HOST; 
				multiple[i][5] = IP;
				multiple[i][6] = plantillaMultipleJerarquica.modelPlantillaMultiple.getValueAt(i,0).toString().trim();
				multiple[i][7] = plantillaMultipleJerarquica.modelPlantillaMultiple.getValueAt(i,1).toString().trim();
				multiple[i][8] = plantillaMultipleJerarquica.modelPlantillaMultiple.getValueAt(i,2).toString().trim();
				multiple[i][9] = plantillaMultipleJerarquica.modelPlantillaMultiple.getValueAt(i,3).toString().trim();
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		return multiple;
	}
	
	public Object[][] tabla_multiple(){
		Object[][] multiple = new Object[plantillaMultiple.tablaPlantillaMultiple.getRowCount()][12];
		try {
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i = 0; i<plantillaMultiple.tablaPlantillaMultiple.getRowCount(); i++){
				multiple[i][0] = txtCuadrante.getText().toString().trim();
				multiple[i][1] = txtNombre_Completo.getText().toString().trim();
				multiple[i][2] = txtEstablecimiento.getText().toString().trim();
				multiple[i][3] = txtPuesto.getText().toString().trim();
				multiple[i][4] = HOST; 
				multiple[i][5] = IP;
				multiple[i][6] = plantillaMultiple.modelPlantillaMultiple.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? "0" : plantillaMultiple.modelPlantillaMultiple.getValueAt(i,0).toString().trim();
				multiple[i][7] = plantillaMultiple.modelPlantillaMultiple.getValueAt(i,1).toString().trim();
				multiple[i][8] = plantillaMultiple.modelPlantillaMultiple.getValueAt(i,2).toString().trim();
				multiple[i][9] = plantillaMultiple.modelPlantillaMultiple.getValueAt(i,3).toString().trim();
				multiple[i][10] = plantillaMultiple.modelPlantillaMultiple.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? 'B' : 'A';
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return multiple;
	}
	
	public Object[][] tabla_multiple_jerarquico_const(){
		Object[][] multiple = new Object[plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getRowCount()][11];
		try{
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i=0; i<plantillaMultipleJerarquicaConts.tablaPlantillaMultiple.getRowCount(); i++){
				multiple[i][0] = txtCuadrante.getText().toString().trim();
				multiple[i][1] = txtNombre_Completo.getText().toString().trim();
				multiple[i][2] = txtEstablecimiento.getText().toString().trim();
				multiple[i][3] = txtPuesto.getText().toString().trim();
				multiple[i][4] = HOST; 
				multiple[i][5] = IP;
				multiple[i][6] = plantillaMultipleJerarquicaConts.modelPlantillaMultiple.getValueAt(i,0).toString().trim();
				multiple[i][7] = plantillaMultipleJerarquicaConts.modelPlantillaMultiple.getValueAt(i,1).toString().trim();
				multiple[i][8] = plantillaMultipleJerarquicaConts.modelPlantillaMultiple.getValueAt(i,2).toString().trim();
				multiple[i][9] = plantillaMultipleJerarquicaConts.modelPlantillaMultiple.getValueAt(i,3).toString().trim();
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		return multiple;
	}
	
	public Object[][] tabla_multiple_conts(){
		Object[][] multiple = new Object[plantillaMultipleContestada.tablaPlantillaMultiple.getRowCount()][11];
		try {
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i = 0; i<plantillaMultipleContestada.tablaPlantillaMultiple.getRowCount(); i++){
				multiple[i][0] = txtCuadrante.getText().toString().trim();
				multiple[i][1] = txtNombre_Completo.getText().toString().trim();
				multiple[i][2] = txtEstablecimiento.getText().toString().trim();
				multiple[i][3] = txtPuesto.getText().toString().trim();
				multiple[i][4] = HOST; 
				multiple[i][5] = IP;
				multiple[i][6] = plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? "0" : plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,0).toString().trim();
				multiple[i][7] = plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,1).toString().trim();
				multiple[i][8] = plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,2).toString().trim();
				multiple[i][9] = plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,3).toString().trim();
				multiple[i][10] = plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? 'B' : 'A';
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return multiple;
	}
	
	public Object[][] tabla_LIBRE_jerarquico(){
		Object[][] libre_jerarquico = new Object[plantillaLibreJerarquica.tablaPlantillaLibre.getRowCount()][10];
		try{
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i=0; i<plantillaLibreJerarquica.tablaPlantillaLibre.getRowCount(); i++){
				libre_jerarquico[i][0] = txtCuadrante.getText().toString().trim();
				libre_jerarquico[i][1] = txtNombre_Completo.getText().toString().trim();
				libre_jerarquico[i][2] = txtEstablecimiento.getText().toString().trim();
				libre_jerarquico[i][3] = txtPuesto.getText().toString().trim();
				libre_jerarquico[i][4] = HOST; 
				libre_jerarquico[i][5] = IP;
				libre_jerarquico[i][6] = plantillaLibreJerarquica.tablaPlantillaLibre.getValueAt(i,0).toString().trim();
				libre_jerarquico[i][7] = plantillaLibreJerarquica.tablaPlantillaLibre.getValueAt(i,1).toString().trim();
				libre_jerarquico[i][8] = plantillaLibreJerarquica.tablaPlantillaLibre.getValueAt(i,2).toString().trim();
				libre_jerarquico[i][9] = plantillaLibreJerarquica.tablaPlantillaLibre.getValueAt(i,3).toString().trim();
			}

		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		return libre_jerarquico;
	}
	
	public Object[][] tabla_LIBRE_jerarquico_contestada(){
		Object[][] libre_jerarquico = new Object[plantillaLibreJerarquicaConts.tablaPlantillaLibre.getRowCount()][10];
		try{
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i=0; i<plantillaLibreJerarquicaConts.tablaPlantillaLibre.getRowCount(); i++){
				libre_jerarquico[i][0] = txtCuadrante.getText().toString().trim();
				libre_jerarquico[i][1] = txtNombre_Completo.getText().toString().trim();
				libre_jerarquico[i][2] = txtEstablecimiento.getText().toString().trim();
				libre_jerarquico[i][3] = txtPuesto.getText().toString().trim();
				libre_jerarquico[i][4] = HOST; 
				libre_jerarquico[i][5] = IP;
				libre_jerarquico[i][6] = plantillaLibreJerarquicaConts.tablaPlantillaLibre.getValueAt(i,0).toString().trim();
				libre_jerarquico[i][7] = plantillaLibreJerarquicaConts.tablaPlantillaLibre.getValueAt(i,1).toString().trim();
				libre_jerarquico[i][8] = plantillaLibreJerarquicaConts.tablaPlantillaLibre.getValueAt(i,2).toString().trim();
				libre_jerarquico[i][9] = plantillaLibreJerarquicaConts.tablaPlantillaLibre.getValueAt(i,3).toString().trim();
			}

		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		return libre_jerarquico;
	}
	
	public Object[][] tabla_LIBRE(){
		Object[][] libre = new Object[plantillaLibre.tablaPlantillaLibre.getRowCount()][11];
		try {
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i = 0; i<plantillaLibre.tablaPlantillaLibre.getRowCount(); i++){
				libre[i][0] = txtCuadrante.getText().toString().trim();
				libre[i][1] = txtNombre_Completo.getText().toString().trim();
				libre[i][2] = txtEstablecimiento.getText().toString().trim();
				libre[i][3] = txtPuesto.getText().toString().trim();
				libre[i][4] = HOST; 
				libre[i][5] = IP;
				libre[i][6] = plantillaLibre.tablaPlantillaLibre.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? "0" : plantillaLibre.tablaPlantillaLibre.getValueAt(i,0).toString().trim();
				libre[i][7] = plantillaLibre.tablaPlantillaLibre.getValueAt(i,1).toString().trim();
				libre[i][8] = plantillaLibre.tablaPlantillaLibre.getValueAt(i,2).toString().trim();
				libre[i][9] = plantillaLibre.tablaPlantillaLibre.getValueAt(i,3).toString().trim();
				libre[i][10] = plantillaLibre.tablaPlantillaLibre.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? 'B' : 'A';
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return libre;
	}
	
	public Object[][] tabla_LIBRE_Contestada(){
		Object[][] libre = new Object[plantillaLibreContestada.tablaPlantillaLibre.getRowCount()][11];
		try {
			String HOST  = InetAddress.getLocalHost().getHostName();
			String IP = InetAddress.getLocalHost().getHostAddress();
			for(int i = 0; i<plantillaLibreContestada.tablaPlantillaLibre.getRowCount(); i++){
				libre[i][0] = txtCuadrante.getText().toString().trim();
				libre[i][1] = txtNombre_Completo.getText().toString().trim();
				libre[i][2] = txtEstablecimiento.getText().toString().trim();
				libre[i][3] = txtPuesto.getText().toString().trim();
				libre[i][4] = HOST; 
				libre[i][5] = IP;
				libre[i][6] = plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? "0" : plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,0).toString().trim();
				libre[i][7] = plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,1).toString().trim();
				libre[i][8] = plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,2).toString().trim();
				libre[i][9] = plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,3).toString().trim();
				libre[i][10] = plantillaLibreContestada.tablaPlantillaLibre.getValueAt(i,0).toString().trim().equalsIgnoreCase("N/A") ? 'B' : 'A';
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return libre;
	}
	
	public Object[][] tabla_libre(){
		Object[][] libre = new Object[plantillaLibre.tablaPlantillaLibre.getRowCount()][3];
		
		for(int i = 0; i<plantillaLibre.tablaPlantillaLibre.getRowCount(); i++){
			libre[i][0] = plantillaLibre.modelPlantillaLibre.getValueAt(i,0).toString().trim();
			libre[i][1] = plantillaLibre.modelPlantillaLibre.getValueAt(i,2).toString().trim();
			libre[i][2] = plantillaLibre.modelPlantillaLibre.getValueAt(i,3).toString().trim();
		}
		return libre;
	}
	
	public String celdasVaciasMultipleConts(){
		String error = "";
		
		for(int i=0; i<plantillaMultipleContestada.tablaPlantillaMultiple.getRowCount(); i++){
			if(plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i, 2).toString().trim().equals("Respuestas")){
				error += "   La actividad con el folio: [ "+plantillaMultipleContestada.modelPlantillaMultiple.getValueAt(i,0).toString().trim()+" ] en actividades de rutina no tiene una respuesta asignada.        \n";
			}
		}		
		return error;
	}
	
	public String celdasVaciasLibre(){
		String error="";
		for(int i=0; i<plantillaLibre.tablaPlantillaLibre.getRowCount(); i++){
			if(plantillaLibre.modelPlantillaLibre.getValueAt(i,2).toString().trim().equals("")){
				error += "   La actividad con el folio: [ "+plantillaLibre.modelPlantillaLibre.getValueAt(i,0).toString().trim()+" ] en actividades por avance no tiene una respuesta asignada.        \n";
			}
		}
		
		return error;
	}
	
	public void CamposLimpiar()	{
		txtNombre_Completo.setText("");
		txtPuesto.setText("");
		txtEstablecimiento.setText("");
		txtEquipo_Trabajo.setText("");
		txtJefatura.setText("");
		txtFecha.setText("");
		txtDia.setText("");
		txtCuadrante.setText("");
	}
	
	public void CamposEnabled(boolean variable){
		txtNombre_Completo.setEditable(variable);
		txtPuesto.setEditable(variable);
		txtEstablecimiento.setEditable(variable);
		txtEquipo_Trabajo.setEditable(variable);
		txtJefatura.setEditable(variable);
		txtFecha.setEditable(variable);
		txtDia.setEditable(variable);
		txtCuadrante.setEditable(variable);
	}
	
	ActionListener salir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0)  {
			dispose();
		}
	};
}
