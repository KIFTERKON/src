package catalogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import SQL.Connexion;

import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Jerarquico;

@SuppressWarnings("serial")
public class Cat_Cuadrante_Base extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JCheckBox  chbStatus = new JCheckBox("Status",true);
	
	JTextField txtCuadrante = new JTextField();
	
	JTextArea txaDescripcion = new JTextArea(4,4);
	JScrollPane Descripcion = new JScrollPane(txaDescripcion);
	
	String jefatura[] = new Obj_Establecimiento().Combo_Jefatura();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbJefatura = new JComboBox(jefatura);
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String nivel_jerarquico[] = new Obj_Nivel_Jerarquico().combo_nivel_jerarquico();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbnivel_jerarquico = new JComboBox(nivel_jerarquico);
	
	String equipo_trabajo[] = new Obj_Establecimiento().Combo_Eq_Trabajo();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEquipo_Trabajo = new JComboBox(equipo_trabajo);
	
	JCheckBox chTodos 		= new JCheckBox("Todos");
	JCheckBox chDomingo		= new JCheckBox("Domingo");
	JCheckBox chLunes 		= new JCheckBox("Lunes");
	JCheckBox chMartes 		= new JCheckBox("Martes");
	JCheckBox chMiercoles	= new JCheckBox("Miércoles");
	JCheckBox chJueves 		= new JCheckBox("Jueves");
	JCheckBox chViernes 	= new JCheckBox("Viernes");
	JCheckBox chSabado 		= new JCheckBox("Sábado");

	JTabbedPane pestanas = new JTabbedPane();
		JLayeredPane pDomingo = new JLayeredPane(); 
		JLayeredPane pLunes = new JLayeredPane(); 
		JLayeredPane pMarte = new JLayeredPane();
		JLayeredPane pMiercoles = new JLayeredPane(); 
		JLayeredPane pJueves = new JLayeredPane(); 
		JLayeredPane pViernes = new JLayeredPane(); 
		JLayeredPane pSabado = new JLayeredPane(); 
	
	DefaultTableModel modelDomingo = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelDomingo.getValueAt(fila,3).toString()) == true){
        	 			modelDomingo.setValueAt("00:00",fila,4);
        	 			modelDomingo.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelDomingo.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelDomingo.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelLunes = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelLunes.getValueAt(fila,3).toString()) == true){
        	 			modelLunes.setValueAt("00:00",fila,4);
        	 			modelLunes.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelLunes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelLunes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelMartes = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelMartes.getValueAt(fila,3).toString()) == true){
        	 			modelMartes.setValueAt("00:00",fila,4);
        	 			modelMartes.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelMartes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelMartes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelMiercoles = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelMiercoles.getValueAt(fila,3).toString()) == true){
        	 			modelMiercoles.setValueAt("00:00",fila,4);
        	 			modelMiercoles.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelMiercoles.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelMiercoles.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelJueves = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelJueves.getValueAt(fila,3).toString()) == true){
        	 			modelJueves.setValueAt("00:00",fila,4);
        	 			modelJueves.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelJueves.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelJueves.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelViernes = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelViernes.getValueAt(fila,3).toString()) == true){
        	 			modelViernes.setValueAt("00:00",fila,4);
        	 			modelViernes.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelViernes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelViernes.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	DefaultTableModel modelSabado = new DefaultTableModel(null,
            new String[]{"Folio", "Actividad","Nivel Crítico","","Hora Inicio","Hora Final"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Integer.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false;
        	 	case 3 : 
        	 		if(Boolean.parseBoolean(modelSabado.getValueAt(fila,3).toString()) == true){
        	 			modelSabado.setValueAt("00:00",fila,4);
        	 			modelSabado.setValueAt("00:00",fila,5);
        	 			return true;
        	 		}else{
        	 			return true;
        	 		}
        	 	case 4 :
        	 		if(Boolean.parseBoolean(modelSabado.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(modelSabado.getValueAt(fila,3).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	JTable tablaDomingo = new JTable(modelDomingo);
	JTable tablaLunes = new JTable(modelLunes);
	JTable tablaMartes = new JTable(modelMartes);
	JTable tablaMiercoles = new JTable(modelMiercoles);
	JTable tablaJueves = new JTable(modelJueves);
	JTable tablaViernes = new JTable(modelViernes);
	JTable tablaSabado = new JTable(modelSabado);
	
	JScrollPane scrollDomingo = new JScrollPane(tablaDomingo);
	JScrollPane scrollLunes = new JScrollPane(tablaLunes);
	JScrollPane scrollMartes = new JScrollPane(tablaMartes);
	JScrollPane scrollMiercoles = new JScrollPane(tablaMiercoles);
	JScrollPane scrollJueves = new JScrollPane(tablaJueves);
	JScrollPane scrollViernes = new JScrollPane(tablaViernes);
	JScrollPane scrollSabado = new JScrollPane(tablaSabado);
	
	JButton btnAgregarDomingo = new JButton("Agregar");
	JButton btnAgregarLunes = new JButton("Agregar");
	JButton btnAgregarMartes = new JButton("Agregar");
	JButton btnAgregarMiercoles = new JButton("Agregar");
	JButton btnAgregarJueves = new JButton("Agregar");
	JButton btnAgregarViernes = new JButton("Agregar");
	JButton btnAgregarSabado = new JButton("Agregar");
	
	JButton btnSubirDomingo = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirLunes = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirMartes = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirMiercoles = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirJueves = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirViernes = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	JButton btnSubirSabado = new JButton(new ImageIcon("Iconos/up_icon&16.png"));
	
	JButton btnBajarDomingo = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarLunes = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarMartes = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarMiercoles = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarJueves = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarViernes = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	JButton btnBajarSabado = new JButton(new ImageIcon("Iconos/down_icon&16.png"));
	
	JButton btnRemoverDomingo = new JButton("Quitar");
	JButton btnRemoverLunes = new JButton("Quitar");
	JButton btnRemoverMartes = new JButton("Quitar");
	JButton btnRemoverMiercoles = new JButton("Quitar");
	JButton btnRemoverJueves = new JButton("Quitar");
	JButton btnRemoverViernes = new JButton("Quitar");
	JButton btnRemoverSabado = new JButton("Quitar");
	
	JButton btn_copiar_domingo_al_lunes = new JButton("Copiar al Lunes");
	JButton btn_copiar_lunes_al_martes = new JButton("Copiar al Martes");
	JButton btn_copiar_martes_al_miercoles = new JButton("Copiar al Miércoles");
	JButton btn_copiar_miercoles_al_jueves = new JButton("Copiar al Jueves");
	JButton btn_copiar_jueves_al_viernes = new JButton("Copiar al Viernes");
	JButton btn_copiar_vienres_al_sabado = new JButton("Copiar al Sábado");
	JButton btn_copiar_sabado_al_domingo = new JButton("Copiar al Domingo");
	
	JButton btn_copiar_domingo_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_lunes_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_martes_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_miercoles_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_jueves_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_vienres_todos = new JButton("Copiar a Todos");
	JButton btn_copiar_sabado_todos = new JButton("Copiar a Todos");
	
	public Cat_Cuadrante_Base(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cuadrante_icon&16.png"));
		this.panel.setBorder(BorderFactory.createTitledBorder("Cuadrantes"));
		
		this.setTitle("Cuadrantes");
		
		int x = 30, y=30;
		
		this.panel.add(new JLabel("Folio:")).setBounds(x,y,100,20);
		this.panel.add(txtFolio).setBounds(130,y,130,20);
		this.panel.add(chbStatus).setBounds(270,y,60,20);
		
		this.panel.add(new JLabel("Cuadrante:")).setBounds(x,y+=25,100,20);
		this.panel.add(txtCuadrante).setBounds(130, y, 300,20);
		
		this.panel.add(new JLabel("Pefil:")).setBounds(x,y+=25,100,20);
		this.panel.add(Descripcion).setBounds(130, y, 300,250);	
		
		this.panel.add(new JLabel("Jefatura:")).setBounds(x,y+=255,100,20);
		this.panel.add(cmbJefatura).setBounds(130, y, 300,20);
		
		this.panel.add(new JLabel("Nivel Gerarquico: ")).setBounds(x,y+=25,100,20);
		this.panel.add(cmbnivel_jerarquico).setBounds(130, y, 300,20);
				
		this.panel.add(new JLabel("Equipo de Trabajo:")).setBounds(x,y+=25,100,20);
		this.panel.add(cmbEquipo_Trabajo).setBounds(130, y, 300,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,100,20);
		this.panel.add(cmbEstablecimiento).setBounds(130, y,300,20);

		this.panel.add(new JLabel("Días:")).setBounds(x,y+=30,100,20);
			this.panel.add(chTodos).setBounds(125,y+=10,60,20);
			this.panel.add(chDomingo).setBounds(205,y,80,20);
			this.panel.add(chLunes).setBounds(295,y,60,20);
			this.panel.add(chMartes).setBounds(368,y,70,20);
			this.panel.add(chMiercoles).setBounds(125,y+=25,80,20);
			this.panel.add(chJueves).setBounds(205,y,70,20);
			this.panel.add(chViernes).setBounds(295,y,70,20);
			this.panel.add(chSabado).setBounds(368,y,70,20);
		
		this.panel.add(pestanas).setBounds(450,30,730, 500);
		
		this.pestanas.addTab("Domingo", pDomingo);
		this.domingo();
		this.pestanas.addTab("Lunes", pLunes);
		this.lunes();
		this.pestanas.addTab("Martes", pMarte);
		this.martes();
		this.pestanas.addTab("Miércoles", pMiercoles);
		this.miercoles();
		this.pestanas.addTab("Jueves", pJueves);
		this.jueves();
		this.pestanas.addTab("Viernes", pViernes);
		this.viernes();
		this.pestanas.addTab("Sábado", pSabado);
		this.sabado();

		this.cont.add(panel);
		
		this.btnAgregarDomingo.addActionListener(opAgregarDomingo);
		this.btnAgregarLunes.addActionListener(opAgregarLunes);
		this.btnAgregarMartes.addActionListener(opAgregarMartes);
		this.btnAgregarMiercoles.addActionListener(opAgregarMiercoles);
		this.btnAgregarJueves.addActionListener(opAgregarJueves);
		this.btnAgregarViernes.addActionListener(opAgregarViernes);
		this.btnAgregarSabado.addActionListener(opAgregarSabado);
		
		this.btnSubirDomingo.addActionListener(opDomingo);
		this.btnBajarDomingo.addActionListener(opDomingo);	
		this.btnRemoverDomingo.addActionListener(opQuitarDomingo);	
				
		this.btnSubirLunes.addActionListener(opLunes);
		this.btnBajarLunes.addActionListener(opLunes);	
		this.btnRemoverLunes.addActionListener(opQuitarLunes);
		
		this.btnSubirMartes.addActionListener(opMartes);
		this.btnBajarMartes.addActionListener(opMartes);	
		this.btnRemoverMartes.addActionListener(opQuitarMartes);
		
		this.btnSubirMiercoles.addActionListener(opMiercoles);
		this.btnBajarMiercoles.addActionListener(opMiercoles);	
		this.btnRemoverMiercoles.addActionListener(opQuitarMiercoles);
		
		this.btnSubirJueves.addActionListener(opJueves);
		this.btnBajarJueves.addActionListener(opJueves);	
		this.btnRemoverJueves.addActionListener(opQuitarJueves);
		
		this.btnSubirViernes.addActionListener(opViernes);
		this.btnBajarViernes.addActionListener(opViernes);	
		this.btnRemoverViernes.addActionListener(opQuitarViernes);
		
		this.btnSubirSabado.addActionListener(opSabado);
		this.btnBajarSabado.addActionListener(opSabado);	
		this.btnRemoverSabado.addActionListener(opQuitarSabado);
		
		this.chDomingo.addActionListener(opDiaDomingo);
		this.chLunes.addActionListener(opDiaLunes);
		this.chMartes.addActionListener(opDiaMartes);
		this.chMiercoles.addActionListener(opDiaMiercoles);
		this.chJueves.addActionListener(opDiaJueves);
		this.chViernes.addActionListener(opDiaViernes);
		this.chSabado.addActionListener(opDiaSabado);
		this.chTodos.addActionListener(opDiaTodos);
		
		btn_copiar_domingo_al_lunes.addActionListener(opcion_copiar_next);
		btn_copiar_lunes_al_martes.addActionListener(opcion_copiar_next);
		btn_copiar_martes_al_miercoles.addActionListener(opcion_copiar_next);
		btn_copiar_miercoles_al_jueves.addActionListener(opcion_copiar_next);
		btn_copiar_jueves_al_viernes.addActionListener(opcion_copiar_next);
		btn_copiar_vienres_al_sabado.addActionListener(opcion_copiar_next);
		btn_copiar_sabado_al_domingo.addActionListener(opcion_copiar_next);
		
		btn_copiar_domingo_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_lunes_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_martes_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_miercoles_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_jueves_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_vienres_todos.addActionListener(opcion_copiar_todos);
		btn_copiar_sabado_todos.addActionListener(opcion_copiar_todos);
		
		this.todoFalse();
		
		this.setSize(1200,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public enum Dias{
		Domingo,
		Lunes,
		Martes,
		Miércoles,
		Jueves,
		Viernes,
		Sábado
	}
	
	public void domingo(){
		this.pDomingo.setBorder(BorderFactory.createTitledBorder("Domingo"));
		this.pDomingo.setOpaque(true); 
		this.pDomingo.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pDomingo.add(scrollDomingo).setBounds(15,50,690,405);

		this.pDomingo.add(btnAgregarDomingo).setBounds(120,20,75,20);
		
		this.pDomingo.add(btnBajarDomingo).setBounds(200,20,40,20);
		this.pDomingo.add(btnSubirDomingo).setBounds(255,20,40,20);
		
		this.pDomingo.add(btnRemoverDomingo).setBounds(300,20,75,20);
		
		this.pDomingo.add(btn_copiar_domingo_al_lunes).setBounds(380,20,125,20);
		
		this.pDomingo.add(btn_copiar_domingo_todos).setBounds(520,20,120,20);
		
		this.tablaDomingo.getTableHeader().setReorderingAllowed(false) ;
		this.tablaDomingo.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaDomingo.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaDomingo.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaDomingo.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaDomingo.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaDomingo.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaDomingo.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaDomingo.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaDomingo.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaDomingo.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaDomingo.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaDomingo.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaDomingo.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaDomingo.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaDomingo.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaDomingo.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	public void lunes(){
		this.pLunes.setBorder(BorderFactory.createTitledBorder("Lunes"));
		this.pLunes.setOpaque(true); 
		this.pLunes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pLunes.add(scrollLunes).setBounds(15,50,690,405);
		
		this.pLunes.add(btnAgregarLunes).setBounds(120,20,75,20);
		
		this.pLunes.add(btnBajarLunes).setBounds(200,20,40,20);
		this.pLunes.add(btnSubirLunes).setBounds(255,20,40,20);
		
		this.pLunes.add(btnRemoverLunes).setBounds(300,20,75,20);
		
		this.pLunes.add(btn_copiar_lunes_al_martes).setBounds(380,20,125,20);
		
		this.pLunes.add(btn_copiar_lunes_todos).setBounds(520,20,120,20);
		
		this.tablaLunes.getTableHeader().setReorderingAllowed(false) ;
		this.tablaLunes.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaLunes.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaLunes.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaLunes.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaLunes.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaLunes.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaLunes.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaLunes.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaLunes.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaLunes.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaLunes.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaLunes.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaLunes.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaLunes.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaLunes.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaLunes.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaLunes.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaLunes.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	public void martes(){
		this.pMarte.setBorder(BorderFactory.createTitledBorder("Martes"));
		this.pMarte.setOpaque(true); 
		this.pMarte.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMarte.add(scrollMartes).setBounds(15,50,690,405);
		
		this.pMarte.add(btnAgregarMartes).setBounds(120,20,75,20);
		
		this.pMarte.add(btnBajarMartes).setBounds(200,20,40,20);
		this.pMarte.add(btnSubirMartes).setBounds(255,20,40,20);
		
		this.pMarte.add(btnRemoverMartes).setBounds(300,20,75,20);
		
		this.pMarte.add(btn_copiar_martes_al_miercoles).setBounds(380,20,125,20);
		
		this.pMarte.add(btn_copiar_martes_todos).setBounds(520,20,120,20);
		
		this.tablaMartes.getTableHeader().setReorderingAllowed(false) ;
		this.tablaMartes.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaMartes.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaMartes.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaMartes.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaMartes.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaMartes.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaMartes.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaMartes.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaMartes.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaMartes.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaMartes.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaMartes.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaMartes.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaMartes.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaMartes.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaMartes.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaMartes.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaMartes.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	public void miercoles(){
		this.pMiercoles.setBorder(BorderFactory.createTitledBorder("Miércoles"));
		this.pMiercoles.setOpaque(true); 
		this.pMiercoles.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMiercoles.add(scrollMiercoles).setBounds(15,50,690,405);
		
		this.pMiercoles.add(btnAgregarMiercoles).setBounds(120,20,75,20);
		
		this.pMiercoles.add(btnBajarMiercoles).setBounds(200,20,40,20);
		this.pMiercoles.add(btnSubirMiercoles).setBounds(255,20,40,20);
		
		this.pMiercoles.add(btnRemoverMiercoles).setBounds(300,20,75,20);
		
		this.pMiercoles.add(btn_copiar_miercoles_al_jueves).setBounds(380,20,125,20);
		
		this.pMiercoles.add(btn_copiar_miercoles_todos).setBounds(520,20,120,20);
		
		this.tablaMiercoles.getTableHeader().setReorderingAllowed(false) ;
		this.tablaMiercoles.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaMiercoles.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaMiercoles.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaMiercoles.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaMiercoles.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaMiercoles.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaMiercoles.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaMiercoles.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaMiercoles.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaMiercoles.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaMiercoles.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaMiercoles.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaMiercoles.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaMiercoles.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaMiercoles.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaMiercoles.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	public void jueves(){
		this.pJueves.setBorder(BorderFactory.createTitledBorder("Jueves"));
		this.pJueves.setOpaque(true); 
		this.pJueves.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pJueves.add(scrollJueves).setBounds(15,50,690,405);
		
		this.pJueves.add(btnAgregarJueves).setBounds(120,20,75,20);
		
		this.pJueves.add(btnBajarJueves).setBounds(200,20,40,20);
		this.pJueves.add(btnSubirJueves).setBounds(255,20,40,20);
		
		this.pJueves.add(btnRemoverJueves).setBounds(300,20,75,20);
		
		this.pJueves.add(btn_copiar_jueves_al_viernes).setBounds(380,20,125,20);

		this.pJueves.add(btn_copiar_jueves_todos).setBounds(520,20,120,20);
		
		this.tablaJueves.getTableHeader().setReorderingAllowed(false) ;
		this.tablaJueves.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaJueves.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaJueves.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaJueves.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaJueves.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaJueves.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaJueves.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaJueves.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaJueves.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaJueves.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaJueves.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaJueves.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaJueves.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaJueves.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaJueves.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaJueves.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaJueves.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaJueves.getColumnModel().getColumn(5).setCellRenderer(render);

	}
	
	public void viernes(){
		this.pViernes.setBorder(BorderFactory.createTitledBorder("Viernes"));
		this.pViernes.setOpaque(true); 
		this.pViernes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pViernes.add(scrollViernes).setBounds(15,50,690,405);
		
		this.pViernes.add(btnAgregarViernes).setBounds(120,20,75,20);
		
		this.pViernes.add(btnBajarViernes).setBounds(200,20,40,20);
		this.pViernes.add(btnSubirViernes).setBounds(255,20,40,20);
		
		this.pViernes.add(btnRemoverViernes).setBounds(300,20,75,20);
		
		this.pViernes.add(btn_copiar_vienres_al_sabado).setBounds(380,20,125,20);
		
		this.pViernes.add(btn_copiar_vienres_todos).setBounds(520,20,120,20);
		
		this.tablaViernes.getTableHeader().setReorderingAllowed(false) ;
		this.tablaViernes.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaViernes.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaViernes.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaViernes.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaViernes.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaViernes.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaViernes.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaViernes.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaViernes.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaViernes.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaViernes.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaViernes.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaViernes.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaViernes.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaViernes.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaViernes.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaViernes.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaViernes.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	public void sabado(){
		this.pSabado.setBorder(BorderFactory.createTitledBorder("Sábado"));
		this.pSabado.setOpaque(true); 
		this.pSabado.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pSabado.add(scrollSabado).setBounds(15,50,690,405);
		
		this.pSabado.add(btnAgregarSabado).setBounds(120,20,75,20);
		
		this.pSabado.add(btnBajarSabado).setBounds(200,20,40,20);
		this.pSabado.add(btnSubirSabado).setBounds(255,20,40,20);
		
		this.pSabado.add(btnRemoverSabado).setBounds(300,20,75,20);
		
		this.pSabado.add(btn_copiar_sabado_al_domingo).setBounds(380,20,125,20);
		
		this.pSabado.add(btn_copiar_sabado_todos).setBounds(520,20,120,20);
		
		this.tablaSabado.getTableHeader().setReorderingAllowed(false) ;
		this.tablaSabado.getColumnModel().getColumn(0).setMaxWidth(60);
		this.tablaSabado.getColumnModel().getColumn(0).setMinWidth(60);
		this.tablaSabado.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tablaSabado.getColumnModel().getColumn(1).setMinWidth(270);
		this.tablaSabado.getColumnModel().getColumn(2).setMaxWidth(145);
		this.tablaSabado.getColumnModel().getColumn(2).setMinWidth(145);
		this.tablaSabado.getColumnModel().getColumn(3).setMaxWidth(50);
		this.tablaSabado.getColumnModel().getColumn(3).setMinWidth(50);
		this.tablaSabado.getColumnModel().getColumn(4).setMaxWidth(80);
		this.tablaSabado.getColumnModel().getColumn(4).setMinWidth(80);
		this.tablaSabado.getColumnModel().getColumn(5).setMaxWidth(80);
		this.tablaSabado.getColumnModel().getColumn(5).setMinWidth(80);
		
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
					case 2:
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
					case 3: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 4: 
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
					case 5: 
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
					
				}
					
				return componente;
			} 
		}; 
	
		this.tablaSabado.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tablaSabado.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tablaSabado.getColumnModel().getColumn(2).setCellRenderer(render);
		this.tablaSabado.getColumnModel().getColumn(3).setCellRenderer(render); 
		this.tablaSabado.getColumnModel().getColumn(4).setCellRenderer(render); 
		this.tablaSabado.getColumnModel().getColumn(5).setCellRenderer(render);
		
	}
	
	ActionListener opAgregarDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Domingo").setVisible(true);
		}
	};	
	
	ActionListener opAgregarLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Lunes").setVisible(true);
		}
	};	
	
	ActionListener opAgregarMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Martes").setVisible(true);
		}
	};	
	
	ActionListener opAgregarMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Miércoles").setVisible(true);
		}
	};	
	
	ActionListener opAgregarJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Jueves").setVisible(true);
		}
	};	
	
	ActionListener opAgregarViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Viernes").setVisible(true);
		}
	};	
	
	ActionListener opAgregarSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Filtro_Actividades("Sábado").setVisible(true);
		}
	};	
	
	ActionListener opDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaDomingo.getRowCount()>1){
				if(tablaDomingo.isRowSelected(tablaDomingo.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirDomingo)){
						if(tablaDomingo.getSelectedRow() != 0){
							Object primero = modelDomingo.getValueAt(tablaDomingo.getSelectedRow(),1);
							Object segundo = modelDomingo.getValueAt(tablaDomingo.getSelectedRow()-1,1);
							
							modelDomingo.setValueAt(primero,tablaDomingo.getSelectedRow()-1,1);
							modelDomingo.setValueAt(segundo,tablaDomingo.getSelectedRow(),1);	
							tablaDomingo.setRowSelectionInterval(tablaDomingo.getSelectedRow()-1,tablaDomingo.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarDomingo)){
						if(tablaDomingo.getSelectedRow()+1 < tablaDomingo.getRowCount()){
							Object primero = modelDomingo.getValueAt(tablaDomingo.getSelectedRow(),1);
							Object segundo = modelDomingo.getValueAt(tablaDomingo.getSelectedRow()+1,1);
							
							modelDomingo.setValueAt(primero,tablaDomingo.getSelectedRow()+1,1);
							modelDomingo.setValueAt(segundo,tablaDomingo.getSelectedRow(),1);	
							tablaDomingo.setRowSelectionInterval(tablaDomingo.getSelectedRow()+1,tablaDomingo.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaLunes.getRowCount()>1){
				if(tablaLunes.isRowSelected(tablaLunes.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirLunes)){
						if(tablaLunes.getSelectedRow() != 0){
							Object primero = modelLunes.getValueAt(tablaLunes.getSelectedRow(),1);
							Object segundo = modelLunes.getValueAt(tablaLunes.getSelectedRow()-1,1);
							
							modelLunes.setValueAt(primero,tablaLunes.getSelectedRow()-1,1);
							modelLunes.setValueAt(segundo,tablaLunes.getSelectedRow(),1);	
							tablaLunes.setRowSelectionInterval(tablaLunes.getSelectedRow()-1,tablaLunes.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarLunes)){
						if(tablaLunes.getSelectedRow()+1 < tablaLunes.getRowCount()){
							Object primero = modelLunes.getValueAt(tablaLunes.getSelectedRow(),1);
							Object segundo = modelLunes.getValueAt(tablaLunes.getSelectedRow()+1,1);
							
							modelLunes.setValueAt(primero,tablaLunes.getSelectedRow()+1,1);
							modelLunes.setValueAt(segundo,tablaLunes.getSelectedRow(),1);	
							tablaLunes.setRowSelectionInterval(tablaLunes.getSelectedRow()+1,tablaLunes.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaMartes.getRowCount()>1){
				if(tablaMartes.isRowSelected(tablaMartes.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirMartes)){
						if(tablaMartes.getSelectedRow() != 0){
							Object primero = modelMartes.getValueAt(tablaMartes.getSelectedRow(),1);
							Object segundo = modelMartes.getValueAt(tablaMartes.getSelectedRow()-1,1);
							
							modelMartes.setValueAt(primero,tablaMartes.getSelectedRow()-1,1);
							modelMartes.setValueAt(segundo,tablaMartes.getSelectedRow(),1);	
							tablaMartes.setRowSelectionInterval(tablaMartes.getSelectedRow()-1,tablaMartes.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarMartes)){
						if(tablaMartes.getSelectedRow()+1 < tablaMartes.getRowCount()){
							Object primero = modelMartes.getValueAt(tablaMartes.getSelectedRow(),1);
							Object segundo = modelMartes.getValueAt(tablaMartes.getSelectedRow()+1,1);
							
							modelMartes.setValueAt(primero,tablaMartes.getSelectedRow()+1,1);
							modelMartes.setValueAt(segundo,tablaMartes.getSelectedRow(),1);	
							tablaMartes.setRowSelectionInterval(tablaMartes.getSelectedRow()+1,tablaMartes.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaMiercoles.getRowCount()>1){
				if(tablaMiercoles.isRowSelected(tablaMiercoles.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirMiercoles)){
						if(tablaMiercoles.getSelectedRow() != 0){
							Object primero = modelMiercoles.getValueAt(tablaMiercoles.getSelectedRow(),1);
							Object segundo = modelMiercoles.getValueAt(tablaMiercoles.getSelectedRow()-1,1);
							
							modelMiercoles.setValueAt(primero,tablaMiercoles.getSelectedRow()-1,1);
							modelMiercoles.setValueAt(segundo,tablaMiercoles.getSelectedRow(),1);	
							tablaMiercoles.setRowSelectionInterval(tablaMiercoles.getSelectedRow()-1,tablaMiercoles.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarMiercoles)){
						if(tablaMiercoles.getSelectedRow()+1 < tablaMiercoles.getRowCount()){
							Object primero = modelMiercoles.getValueAt(tablaMiercoles.getSelectedRow(),1);
							Object segundo = modelMiercoles.getValueAt(tablaMiercoles.getSelectedRow()+1,1);
							
							modelMiercoles.setValueAt(primero,tablaMiercoles.getSelectedRow()+1,1);
							modelMiercoles.setValueAt(segundo,tablaMiercoles.getSelectedRow(),1);	
							tablaMiercoles.setRowSelectionInterval(tablaMiercoles.getSelectedRow()+1,tablaMiercoles.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaJueves.getRowCount()>1){
				if(tablaJueves.isRowSelected(tablaJueves.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirJueves)){
						if(tablaJueves.getSelectedRow() != 0){
							Object primero = modelJueves.getValueAt(tablaJueves.getSelectedRow(),1);
							Object segundo = modelJueves.getValueAt(tablaJueves.getSelectedRow()-1,1);
							
							modelJueves.setValueAt(primero,tablaJueves.getSelectedRow()-1,1);
							modelJueves.setValueAt(segundo,tablaJueves.getSelectedRow(),1);	
							tablaJueves.setRowSelectionInterval(tablaJueves.getSelectedRow()-1,tablaJueves.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarJueves)){
						if(tablaJueves.getSelectedRow()+1 < tablaJueves.getRowCount()){
							Object primero = modelJueves.getValueAt(tablaJueves.getSelectedRow(),1);
							Object segundo = modelJueves.getValueAt(tablaJueves.getSelectedRow()+1,1);
							
							modelJueves.setValueAt(primero,tablaJueves.getSelectedRow()+1,1);
							modelJueves.setValueAt(segundo,tablaJueves.getSelectedRow(),1);	
							tablaJueves.setRowSelectionInterval(tablaJueves.getSelectedRow()+1,tablaJueves.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaViernes.getRowCount()>1){
				if(tablaViernes.isRowSelected(tablaViernes.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirViernes)){
						if(tablaViernes.getSelectedRow() != 0){
							Object primero = modelViernes.getValueAt(tablaViernes.getSelectedRow(),1);
							Object segundo = modelViernes.getValueAt(tablaViernes.getSelectedRow()-1,1);
							
							modelViernes.setValueAt(primero,tablaViernes.getSelectedRow()-1,1);
							modelViernes.setValueAt(segundo,tablaViernes.getSelectedRow(),1);	
							tablaViernes.setRowSelectionInterval(tablaViernes.getSelectedRow()-1,tablaViernes.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarViernes)){
						if(tablaViernes.getSelectedRow()+1 < tablaViernes.getRowCount()){
							Object primero = modelViernes.getValueAt(tablaViernes.getSelectedRow(),1);
							Object segundo = modelViernes.getValueAt(tablaViernes.getSelectedRow()+1,1);
							
							modelViernes.setValueAt(primero,tablaViernes.getSelectedRow()+1,1);
							modelViernes.setValueAt(segundo,tablaViernes.getSelectedRow(),1);	
							tablaViernes.setRowSelectionInterval(tablaViernes.getSelectedRow()+1,tablaViernes.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaSabado.getRowCount()>1){
				if(tablaSabado.isRowSelected(tablaSabado.getSelectedRow())){
					if(arg0.getSource().equals(btnSubirSabado)){
						if(tablaSabado.getSelectedRow() != 0){
							Object primero = modelSabado.getValueAt(tablaSabado.getSelectedRow(),1);
							Object segundo = modelSabado.getValueAt(tablaSabado.getSelectedRow()-1,1);
							
							modelSabado.setValueAt(primero,tablaSabado.getSelectedRow()-1,1);
							modelSabado.setValueAt(segundo,tablaSabado.getSelectedRow(),1);	
							tablaSabado.setRowSelectionInterval(tablaSabado.getSelectedRow()-1,tablaSabado.getSelectedRow()-1);
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
								
					}
					if(arg0.getSource().equals(btnBajarSabado)){
						if(tablaSabado.getSelectedRow()+1 < tablaSabado.getRowCount()){
							Object primero = modelSabado.getValueAt(tablaSabado.getSelectedRow(),1);
							Object segundo = modelSabado.getValueAt(tablaSabado.getSelectedRow()+1,1);
							
							modelSabado.setValueAt(primero,tablaSabado.getSelectedRow()+1,1);
							modelSabado.setValueAt(segundo,tablaSabado.getSelectedRow(),1);	
							tablaSabado.setRowSelectionInterval(tablaSabado.getSelectedRow()+1,tablaSabado.getSelectedRow()+1);
						
						}else{
							JOptionPane.showMessageDialog(null,"No mÃ¡s filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opQuitarDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaDomingo.getRowCount()>0){
				if(tablaDomingo.isRowSelected(tablaDomingo.getSelectedRow())){
					modelDomingo.removeRow(tablaDomingo.getSelectedRow());
					for(int i=0; i<tablaSabado.getRowCount(); i ++){
						modelDomingo.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaLunes.getRowCount()>0){
				if(tablaLunes.isRowSelected(tablaLunes.getSelectedRow())){
					modelLunes.removeRow(tablaLunes.getSelectedRow());
					for(int i=0; i<tablaLunes.getRowCount(); i ++){
						modelLunes.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaMartes.getRowCount()>0){
				if(tablaMartes.isRowSelected(tablaMartes.getSelectedRow())){
					modelMartes.removeRow(tablaMartes.getSelectedRow());
					for(int i=0; i<tablaMartes.getRowCount(); i ++){
						modelMartes.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaMiercoles.getRowCount()>0){
				if(tablaMiercoles.isRowSelected(tablaMiercoles.getSelectedRow())){
					modelMiercoles.removeRow(tablaMiercoles.getSelectedRow());
					for(int i=0; i<tablaMiercoles.getRowCount(); i ++){
						modelMiercoles.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaJueves.getRowCount()>0){
				if(tablaJueves.isRowSelected(tablaJueves.getSelectedRow())){
					modelJueves.removeRow(tablaJueves.getSelectedRow());
					for(int i=0; i<tablaJueves.getRowCount(); i ++){
						modelJueves.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaViernes.getRowCount()>0){
				if(tablaViernes.isRowSelected(tablaViernes.getSelectedRow())){
					modelViernes.removeRow(tablaViernes.getSelectedRow());
					for(int i=0; i<tablaViernes.getRowCount(); i ++){
						modelViernes.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opQuitarSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaSabado.getRowCount()>0){
				if(tablaSabado.isRowSelected(tablaSabado.getSelectedRow())){
					modelSabado.removeRow(tablaSabado.getSelectedRow());
					for(int i=0; i<tablaSabado.getRowCount(); i ++){
						modelSabado.setValueAt((i+1)+"  ",i,0);
					}
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
	
	ActionListener opcion_copiar_next = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(btn_copiar_domingo_al_lunes)){
				if(modelDomingo.getRowCount() > 0){
					while(tablaLunes.getRowCount() > 0){
						modelLunes.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelDomingo.getRowCount(); i++){
						modelLunes.addRow(fila);
						modelLunes.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
						modelLunes.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
						modelLunes.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
						modelLunes.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
						modelLunes.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
						modelLunes.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
						
					}
					chLunes.setSelected(true);
					btnAgregarLunes.setEnabled(true);
					btnSubirLunes.setEnabled(true);
					btnBajarLunes.setEnabled(true);
					btnRemoverLunes.setEnabled(true);
					btn_copiar_lunes_al_martes.setEnabled(true);
					tablaLunes.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_lunes_al_martes)){
				if(modelLunes.getRowCount() > 0){
					while(tablaMartes.getRowCount() > 0){
						modelMartes.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelLunes.getRowCount(); i++){
						modelMartes.addRow(fila);
						modelMartes.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
						modelMartes.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
						modelMartes.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
						modelMartes.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
						modelMartes.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
						modelMartes.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
						
					}
					chMartes.setSelected(true);
					btnAgregarMartes.setEnabled(true);
					btnSubirMartes.setEnabled(true);
					btnBajarMartes.setEnabled(true);
					btnRemoverMartes.setEnabled(true);
					btn_copiar_martes_al_miercoles.setEnabled(true);
					tablaMartes.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_martes_al_miercoles)){
				if(modelMartes.getRowCount() > 0){
					while(tablaMiercoles.getRowCount() > 0){
						modelMiercoles.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelMartes.getRowCount(); i++){
						modelMiercoles.addRow(fila);
						modelMiercoles.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
						modelMiercoles.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
						modelMiercoles.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
						modelMiercoles.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
						modelMiercoles.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
						modelMiercoles.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
						
					}
					chMiercoles.setSelected(true);
					btnAgregarMiercoles.setEnabled(true);
					btnSubirMiercoles.setEnabled(true);
					btnBajarMiercoles.setEnabled(true);
					btnRemoverMiercoles.setEnabled(true);
					btn_copiar_miercoles_al_jueves.setEnabled(true);
					tablaMiercoles.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_miercoles_al_jueves)){
				if(modelMiercoles.getRowCount() > 0){
					while(tablaJueves.getRowCount() > 0){
						modelJueves.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelMiercoles.getRowCount(); i++){
						modelJueves.addRow(fila);
						modelJueves.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
						modelJueves.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
						modelJueves.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
						modelJueves.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
						modelJueves.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
						modelJueves.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
						
					}
					chJueves.setSelected(true);
					btnAgregarJueves.setEnabled(true);
					btnSubirJueves.setEnabled(true);
					btnBajarJueves.setEnabled(true);
					btnRemoverJueves.setEnabled(true);
					btn_copiar_jueves_al_viernes.setEnabled(true);
					tablaJueves.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_jueves_al_viernes)){
				if(modelJueves.getRowCount() > 0){
					while(tablaViernes.getRowCount() > 0){
						modelViernes.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelJueves.getRowCount(); i++){
						modelViernes.addRow(fila);
						modelViernes.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
						modelViernes.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
						modelViernes.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
						modelViernes.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
						modelViernes.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
						modelViernes.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
						
					}
					chViernes.setSelected(true);
					btnAgregarViernes.setEnabled(true);
					btnSubirViernes.setEnabled(true);
					btnBajarViernes.setEnabled(true);
					btnRemoverViernes.setEnabled(true);
					btn_copiar_vienres_al_sabado.setEnabled(true);
					tablaViernes.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_vienres_al_sabado)){
				if(modelViernes.getRowCount() > 0){
					while(tablaSabado.getRowCount() > 0){
						modelSabado.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelViernes.getRowCount(); i++){
						modelSabado.addRow(fila);
						modelSabado.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
						modelSabado.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
						modelSabado.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
						modelSabado.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
						modelSabado.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
						modelSabado.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
						
					}
					chSabado.setSelected(true);
					btnAgregarSabado.setEnabled(true);
					btnSubirSabado.setEnabled(true);
					btnBajarSabado.setEnabled(true);
					btnRemoverSabado.setEnabled(true);
					btn_copiar_sabado_al_domingo.setEnabled(true);
					tablaSabado.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
			if(arg0.getSource().equals(btn_copiar_sabado_al_domingo)){
				if(modelSabado.getRowCount() > 0){
					while(tablaDomingo.getRowCount() > 0){
						modelDomingo.removeRow(0);
					}
					Object[] fila = {"","","","false","",""};
					for(int i=0; i<modelSabado.getRowCount(); i++){
						modelDomingo.addRow(fila);
						modelDomingo.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
						modelDomingo.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
						modelDomingo.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
						modelDomingo.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
						modelDomingo.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
						modelDomingo.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
						
					}
					chDomingo.setSelected(true);
					btnAgregarDomingo.setEnabled(true);
					btnSubirDomingo.setEnabled(true);
					btnBajarDomingo.setEnabled(true);
					btnRemoverDomingo.setEnabled(true);
					btn_copiar_domingo_al_lunes.setEnabled(true);
					tablaDomingo.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	};
	
	ActionListener opcion_copiar_todos = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(btn_copiar_domingo_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Domingo a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_domingo();
				}
			}
			if(arg0.getSource().equals(btn_copiar_lunes_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Lunes a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_lunes();
				}
			}
			if(arg0.getSource().equals(btn_copiar_martes_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Martes a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_martes();
				}
			}
			if(arg0.getSource().equals(btn_copiar_miercoles_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Miércoles a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_miercoles();
				}
			}
			if(arg0.getSource().equals(btn_copiar_jueves_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Jueves a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_jueves();
				}
			}
			if(arg0.getSource().equals(btn_copiar_vienres_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Viernes a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_viernes();
				}
			}
			if(arg0.getSource().equals(btn_copiar_sabado_todos)){
				if(JOptionPane.showConfirmDialog(null, "¿Desea copiar el contenido del Sábado a todos los días?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					copiar_todos_sabado();
				}
			}
		}
	};
	
	/** DOMINGO **/
	public void copiar_todos_domingo(){
		if(modelDomingo.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			Object[] fila = {"","","","false","",""};
					
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelDomingo.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelDomingo.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelDomingo.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelDomingo.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelDomingo.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelDomingo.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelDomingo.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** LUNES **/
	public void copiar_todos_lunes(){
		if(modelLunes.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelLunes.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelLunes.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelLunes.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelLunes.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelLunes.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelLunes.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelLunes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** MARTES **/
	public void copiar_todos_martes(){
		if(modelMartes.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMartes.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelMartes.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelMartes.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelMartes.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelMartes.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelMartes.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelMartes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** MIERCOLES **/
	public void copiar_todos_miercoles(){
		if(modelMiercoles.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelMiercoles.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelMiercoles.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelMiercoles.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelMiercoles.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelMiercoles.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelMiercoles.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelMiercoles.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** JUEVES **/
	public void copiar_todos_jueves(){
		if(modelJueves.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelJueves.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelJueves.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelJueves.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelJueves.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelJueves.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelJueves.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelJueves.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** VIERNES **/
	public void copiar_todos_viernes(){
		if(modelViernes.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaSabado.getRowCount() > 0){ modelSabado.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR SABADO CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelViernes.getRowCount(); i++){
				modelSabado.addRow(fila);
				modelSabado.setValueAt(modelViernes.getValueAt(i,0).toString(), i,0);
				modelSabado.setValueAt(modelViernes.getValueAt(i,1).toString(), i,1);
				modelSabado.setValueAt(modelViernes.getValueAt(i,2).toString(), i,2);
				modelSabado.setValueAt(Boolean.parseBoolean(modelViernes.getValueAt(i,3).toString()), i,3);
				modelSabado.setValueAt(modelViernes.getValueAt(i,4).toString(), i,4);
				modelSabado.setValueAt(modelViernes.getValueAt(i,5).toString(), i,5);
				
			}
			
			chSabado.setSelected(true);
			btnAgregarSabado.setEnabled(true);
			btnSubirSabado.setEnabled(true);
			btnBajarSabado.setEnabled(true);
			btnRemoverSabado.setEnabled(true);
			btn_copiar_sabado_al_domingo.setEnabled(true);
			tablaSabado.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/** SABADO **/
	public void copiar_todos_sabado(){
		if(modelSabado.getRowCount() > 0){
			
			/** RESETEAR LAS TABLAS A LAS QUE SE LES VAN A AGREGAR DATOS **/
			while(tablaDomingo.getRowCount() > 0){ modelDomingo.removeRow(0); }
			while(tablaLunes.getRowCount() > 0){ modelLunes.removeRow(0); }
			while(tablaMartes.getRowCount() > 0){ modelMartes.removeRow(0); }
			while(tablaMiercoles.getRowCount() > 0){ modelMiercoles.removeRow(0); }
			while(tablaJueves.getRowCount() > 0){ modelJueves.removeRow(0); }
			while(tablaViernes.getRowCount() > 0){ modelViernes.removeRow(0); }
			
			/** CARGAR DOMINGO CON DATOS DEL ANFITRION **/
			Object[] fila = {"","","","false","",""};
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelDomingo.addRow(fila);
				modelDomingo.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelDomingo.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelDomingo.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelDomingo.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelDomingo.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelDomingo.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chDomingo.setSelected(true);
			btnAgregarDomingo.setEnabled(true);
			btnSubirDomingo.setEnabled(true);
			btnBajarDomingo.setEnabled(true);
			btnRemoverDomingo.setEnabled(true);
			btn_copiar_domingo_al_lunes.setEnabled(true);
			tablaDomingo.setEnabled(true);
			
			/** CARGAR LUNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelLunes.addRow(fila);
				modelLunes.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelLunes.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelLunes.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelLunes.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelLunes.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelLunes.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chLunes.setSelected(true);
			btnAgregarLunes.setEnabled(true);
			btnSubirLunes.setEnabled(true);
			btnBajarLunes.setEnabled(true);
			btnRemoverLunes.setEnabled(true);
			btn_copiar_lunes_al_martes.setEnabled(true);
			tablaLunes.setEnabled(true);
			
			/** CARGAR MARTES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelMartes.addRow(fila);
				modelMartes.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelMartes.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelMartes.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelMartes.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelMartes.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelMartes.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMartes.setSelected(true);
			btnAgregarMartes.setEnabled(true);
			btnSubirMartes.setEnabled(true);
			btnBajarMartes.setEnabled(true);
			btnRemoverMartes.setEnabled(true);
			btn_copiar_martes_al_miercoles.setEnabled(true);
			tablaMartes.setEnabled(true);
			
			/** CARGAR MIERCOLES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelMiercoles.addRow(fila);
				modelMiercoles.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelMiercoles.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelMiercoles.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelMiercoles.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelMiercoles.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelMiercoles.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chMiercoles.setSelected(true);
			btnAgregarMiercoles.setEnabled(true);
			btnSubirMiercoles.setEnabled(true);
			btnBajarMiercoles.setEnabled(true);
			btnRemoverMiercoles.setEnabled(true);
			btn_copiar_miercoles_al_jueves.setEnabled(true);
			tablaMiercoles.setEnabled(true);
			
			/** CARGAR JUEVES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelJueves.addRow(fila);
				modelJueves.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelJueves.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelJueves.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelJueves.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelJueves.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelJueves.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chJueves.setSelected(true);
			btnAgregarJueves.setEnabled(true);
			btnSubirJueves.setEnabled(true);
			btnBajarJueves.setEnabled(true);
			btnRemoverJueves.setEnabled(true);
			btn_copiar_jueves_al_viernes.setEnabled(true);
			tablaJueves.setEnabled(true);
			
			/** CARGAR VIERNES CON DATOS DEL ANFITRION **/
			for(int i=0; i<modelSabado.getRowCount(); i++){
				modelViernes.addRow(fila);
				modelViernes.setValueAt(modelSabado.getValueAt(i,0).toString(), i,0);
				modelViernes.setValueAt(modelSabado.getValueAt(i,1).toString(), i,1);
				modelViernes.setValueAt(modelSabado.getValueAt(i,2).toString(), i,2);
				modelViernes.setValueAt(Boolean.parseBoolean(modelSabado.getValueAt(i,3).toString()), i,3);
				modelViernes.setValueAt(modelSabado.getValueAt(i,4).toString(), i,4);
				modelViernes.setValueAt(modelSabado.getValueAt(i,5).toString(), i,5);
				
			}
			
			chViernes.setSelected(true);
			btnAgregarViernes.setEnabled(true);
			btnSubirViernes.setEnabled(true);
			btnBajarViernes.setEnabled(true);
			btnRemoverViernes.setEnabled(true);
			btn_copiar_vienres_al_sabado.setEnabled(true);
			tablaViernes.setEnabled(true);
			
		}else{
			JOptionPane.showMessageDialog(null,"No hay filas en la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	ActionListener opDiaDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chDomingo.isSelected()){
				btnAgregarDomingo.setEnabled(true);
				btnSubirDomingo.setEnabled(true);
				btnBajarDomingo.setEnabled(true);
				btnRemoverDomingo.setEnabled(true);
				btn_copiar_domingo_al_lunes.setEnabled(true);
				btn_copiar_domingo_todos.setEnabled(true);
				tablaDomingo.setEnabled(true);
			}else{
				btnAgregarDomingo.setEnabled(false);
				btnSubirDomingo.setEnabled(false);
				btnBajarDomingo.setEnabled(false);
				btnRemoverDomingo.setEnabled(false);
				btn_copiar_domingo_al_lunes.setEnabled(false);
				btn_copiar_domingo_todos.setEnabled(false);
				tablaDomingo.setEnabled(false);
				
				while(tablaDomingo.getRowCount() > 0){
					modelDomingo.removeRow(0);
				}
			}
		}
	};

	ActionListener opDiaLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chLunes.isSelected()){
				btnAgregarLunes.setEnabled(true);
				btnSubirLunes.setEnabled(true);
				btnBajarLunes.setEnabled(true);
				btnRemoverLunes.setEnabled(true);
				btn_copiar_lunes_al_martes.setEnabled(true);
				btn_copiar_lunes_todos.setEnabled(true);
				tablaLunes.setEnabled(true);
			}else{
				btnAgregarLunes.setEnabled(false);
				btnSubirLunes.setEnabled(false);
				btnBajarLunes.setEnabled(false);
				btnRemoverLunes.setEnabled(false);
				btn_copiar_lunes_al_martes.setEnabled(false);
				btn_copiar_lunes_todos.setEnabled(false);
				tablaLunes.setEnabled(false);
				
				while(tablaLunes.getRowCount() > 0){
					modelLunes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chMartes.isSelected()){
				btnAgregarMartes.setEnabled(true);
				btnSubirMartes.setEnabled(true);
				btnBajarMartes.setEnabled(true);
				btnRemoverMartes.setEnabled(true);
				btn_copiar_martes_al_miercoles.setEnabled(true);
				btn_copiar_martes_todos.setEnabled(true);
				tablaMartes.setEnabled(true);
			}else{
				btnAgregarMartes.setEnabled(false);
				btnSubirMartes.setEnabled(false);
				btnBajarMartes.setEnabled(false);
				btnRemoverMartes.setEnabled(false);
				btn_copiar_martes_al_miercoles.setEnabled(false);
				btn_copiar_martes_todos.setEnabled(false);
				tablaMartes.setEnabled(false);
				
				while(tablaMartes.getRowCount() > 0){
					modelMartes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chMiercoles.isSelected()){
				btnAgregarMiercoles.setEnabled(true);
				btnSubirMiercoles.setEnabled(true);
				btnBajarMiercoles.setEnabled(true);
				btnRemoverMiercoles.setEnabled(true);
				btn_copiar_miercoles_al_jueves.setEnabled(true);
				btn_copiar_miercoles_todos.setEnabled(true);
				tablaMiercoles.setEnabled(true);
			}else{
				btnAgregarMiercoles.setEnabled(false);
				btnSubirMiercoles.setEnabled(false);
				btnBajarMiercoles.setEnabled(false);
				btnRemoverMiercoles.setEnabled(false);
				btn_copiar_miercoles_al_jueves.setEnabled(false);
				btn_copiar_miercoles_todos.setEnabled(false);
				tablaMiercoles.setEnabled(false);
				
				while(tablaMiercoles.getRowCount() > 0){
					modelMiercoles.removeRow(0);
				}
			}
			
		}
	};
	
	ActionListener opDiaJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chJueves.isSelected()){
				btnAgregarJueves.setEnabled(true);
				btnSubirJueves.setEnabled(true);
				btnBajarJueves.setEnabled(true);
				btnRemoverJueves.setEnabled(true);
				btn_copiar_jueves_al_viernes.setEnabled(true);
				btn_copiar_jueves_todos.setEnabled(true);
				tablaJueves.setEnabled(true);
			}else{
				btnAgregarJueves.setEnabled(false);
				btnSubirJueves.setEnabled(false);
				btnBajarJueves.setEnabled(false);
				btnRemoverJueves.setEnabled(false);
				btn_copiar_jueves_al_viernes.setEnabled(false);
				btn_copiar_jueves_todos.setEnabled(false);
				tablaJueves.setEnabled(false);
				
				while(tablaJueves.getRowCount() > 0){
					modelJueves.removeRow(0);
				}
			}
			
		}
	};
	
	ActionListener opDiaViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chViernes.isSelected()){
				btnAgregarViernes.setEnabled(true);
				btnSubirViernes.setEnabled(true);
				btnBajarViernes.setEnabled(true);
				btnRemoverViernes.setEnabled(true);
				btn_copiar_vienres_al_sabado.setEnabled(true);
				btn_copiar_vienres_todos.setEnabled(true);
				tablaViernes.setEnabled(true);
			}else{
				btnAgregarViernes.setEnabled(false);
				btnSubirViernes.setEnabled(false);
				btnBajarViernes.setEnabled(false);
				btnRemoverViernes.setEnabled(false);
				btn_copiar_vienres_al_sabado.setEnabled(false);
				btn_copiar_vienres_todos.setEnabled(false);
				tablaViernes.setEnabled(false);
				
				while(tablaViernes.getRowCount() > 0){
					modelViernes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chSabado.isSelected()){
				btnAgregarSabado.setEnabled(true);
				btnSubirSabado.setEnabled(true);
				btnBajarSabado.setEnabled(true);
				btnRemoverSabado.setEnabled(true);
				btn_copiar_sabado_al_domingo.setEnabled(true);
				btn_copiar_sabado_todos.setEnabled(true);
				tablaSabado.setEnabled(true);
			}else{
				btnAgregarSabado.setEnabled(false);
				btnSubirSabado.setEnabled(false);
				btnBajarSabado.setEnabled(false);
				btnRemoverSabado.setEnabled(false);
				btn_copiar_sabado_al_domingo.setEnabled(false);
				btn_copiar_sabado_todos.setEnabled(false);
				tablaSabado.setEnabled(false);
				
				while(tablaSabado.getRowCount() > 0){
					modelSabado.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaTodos = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chTodos.isSelected()){
				chDomingo.setSelected(true);
				chLunes.setSelected(true);
				chMartes.setSelected(true);
				chMiercoles.setSelected(true);
				chJueves.setSelected(true);
				chViernes.setSelected(true);
				chSabado.setSelected(true);
				btnAgregarDomingo.setEnabled(true);
				btnSubirDomingo.setEnabled(true);
				btnBajarDomingo.setEnabled(true);
				btnRemoverDomingo.setEnabled(true);
				btnAgregarLunes.setEnabled(true);
				btnSubirLunes.setEnabled(true);
				btnBajarLunes.setEnabled(true);
				btnRemoverLunes.setEnabled(true);
				btnAgregarMartes.setEnabled(true);
				btnSubirMartes.setEnabled(true);
				btnBajarMartes.setEnabled(true);
				btnRemoverMartes.setEnabled(true);
				btnAgregarMiercoles.setEnabled(true);
				btnSubirMiercoles.setEnabled(true);
				btnBajarMiercoles.setEnabled(true);
				btnRemoverMiercoles.setEnabled(true);
				btnAgregarJueves.setEnabled(true);
				btnSubirJueves.setEnabled(true);
				btnBajarJueves.setEnabled(true);
				btnRemoverJueves.setEnabled(true);
				btnAgregarViernes.setEnabled(true);
				btnSubirViernes.setEnabled(true);
				btnBajarViernes.setEnabled(true);
				btnRemoverViernes.setEnabled(true);
				btnAgregarSabado.setEnabled(true);
				btnSubirSabado.setEnabled(true);
				btnBajarSabado.setEnabled(true);
				btnRemoverSabado.setEnabled(true);
				btn_copiar_domingo_al_lunes.setEnabled(true);
				btn_copiar_lunes_al_martes.setEnabled(true);
				btn_copiar_martes_al_miercoles.setEnabled(true);
				btn_copiar_miercoles_al_jueves.setEnabled(true);
				btn_copiar_jueves_al_viernes.setEnabled(true);
				btn_copiar_vienres_al_sabado.setEnabled(true);
				btn_copiar_sabado_al_domingo.setEnabled(true);
				btn_copiar_domingo_todos.setEnabled(true);
				btn_copiar_lunes_todos.setEnabled(true);
				btn_copiar_martes_todos.setEnabled(true);
				btn_copiar_miercoles_todos.setEnabled(true);
				btn_copiar_jueves_todos.setEnabled(true);
				btn_copiar_vienres_todos.setEnabled(true);
				btn_copiar_sabado_todos.setEnabled(true);
				tablaDomingo.setEnabled(true);
				tablaLunes.setEnabled(true);
				tablaMartes.setEnabled(true);
				tablaMiercoles.setEnabled(true);
				tablaJueves.setEnabled(true);
				tablaViernes.setEnabled(true);
				tablaSabado.setEnabled(true);
				
			}else{
				chDomingo.setSelected(false);
				chLunes.setSelected(false);
				chMartes.setSelected(false);
				chMiercoles.setSelected(false);
				chJueves.setSelected(false);
				chViernes.setSelected(false);
				chSabado.setSelected(false);
				btnAgregarDomingo.setEnabled(false);
				btnSubirDomingo.setEnabled(false);
				btnBajarDomingo.setEnabled(false);
				btnRemoverDomingo.setEnabled(false);
				btnAgregarLunes.setEnabled(false);
				btnSubirLunes.setEnabled(false);
				btnBajarLunes.setEnabled(false);
				btnRemoverLunes.setEnabled(false);
				btnAgregarMartes.setEnabled(false);
				btnSubirMartes.setEnabled(false);
				btnBajarMartes.setEnabled(false);
				btnRemoverMartes.setEnabled(false);
				btnAgregarMiercoles.setEnabled(false);
				btnSubirMiercoles.setEnabled(false);
				btnBajarMiercoles.setEnabled(false);
				btnRemoverMiercoles.setEnabled(false);
				btnAgregarJueves.setEnabled(false);
				btnSubirJueves.setEnabled(false);
				btnBajarJueves.setEnabled(false);
				btnRemoverJueves.setEnabled(false);
				btnAgregarViernes.setEnabled(false);
				btnSubirViernes.setEnabled(false);
				btnBajarViernes.setEnabled(false);
				btnRemoverViernes.setEnabled(false);
				btnAgregarSabado.setEnabled(false);
				btnSubirSabado.setEnabled(false);
				btnBajarSabado.setEnabled(false);
				btnRemoverSabado.setEnabled(false);
				btn_copiar_domingo_al_lunes.setEnabled(false);
				btn_copiar_lunes_al_martes.setEnabled(false);
				btn_copiar_martes_al_miercoles.setEnabled(false);
				btn_copiar_miercoles_al_jueves.setEnabled(false);
				btn_copiar_jueves_al_viernes.setEnabled(false);
				btn_copiar_vienres_al_sabado.setEnabled(false);
				btn_copiar_sabado_al_domingo.setEnabled(false);
				btn_copiar_domingo_todos.setEnabled(false);
				btn_copiar_lunes_todos.setEnabled(false);
				btn_copiar_martes_todos.setEnabled(false);
				btn_copiar_miercoles_todos.setEnabled(false);
				btn_copiar_jueves_todos.setEnabled(false);
				btn_copiar_vienres_todos.setEnabled(false);
				btn_copiar_sabado_todos.setEnabled(false);
				tablaDomingo.setEnabled(false);
				tablaLunes.setEnabled(false);
				tablaMartes.setEnabled(false);
				tablaMiercoles.setEnabled(false);
				tablaJueves.setEnabled(false);
				tablaViernes.setEnabled(false);
				tablaSabado.setEnabled(false);
			}
	
		}
	};
	public void todoFalse(){
		btnAgregarDomingo.setEnabled(false);
		btnSubirDomingo.setEnabled(false);
		btnBajarDomingo.setEnabled(false);
		btnRemoverDomingo.setEnabled(false);
		btnAgregarLunes.setEnabled(false);
		btnSubirLunes.setEnabled(false);
		btnBajarLunes.setEnabled(false);
		btnRemoverLunes.setEnabled(false);
		btnAgregarMartes.setEnabled(false);
		btnSubirMartes.setEnabled(false);
		btnBajarMartes.setEnabled(false);
		btnRemoverMartes.setEnabled(false);
		btnAgregarMiercoles.setEnabled(false);
		btnSubirMiercoles.setEnabled(false);
		btnBajarMiercoles.setEnabled(false);
		btnRemoverMiercoles.setEnabled(false);
		btnAgregarJueves.setEnabled(false);
		btnSubirJueves.setEnabled(false);
		btnBajarJueves.setEnabled(false);
		btnRemoverJueves.setEnabled(false);
		btnAgregarViernes.setEnabled(false);
		btnSubirViernes.setEnabled(false);
		btnBajarViernes.setEnabled(false);
		btnRemoverViernes.setEnabled(false);
		btnAgregarSabado.setEnabled(false);
		btnSubirSabado.setEnabled(false);
		btnBajarSabado.setEnabled(false);
		btnRemoverSabado.setEnabled(false);
		btn_copiar_domingo_al_lunes.setEnabled(false);
		btn_copiar_lunes_al_martes.setEnabled(false);
		btn_copiar_martes_al_miercoles.setEnabled(false);
		btn_copiar_miercoles_al_jueves.setEnabled(false);
		btn_copiar_jueves_al_viernes.setEnabled(false);
		btn_copiar_vienres_al_sabado.setEnabled(false);
		btn_copiar_sabado_al_domingo.setEnabled(false);
		btn_copiar_domingo_todos.setEnabled(false);
		btn_copiar_lunes_todos.setEnabled(false);
		btn_copiar_martes_todos.setEnabled(false);
		btn_copiar_miercoles_todos.setEnabled(false);
		btn_copiar_jueves_todos.setEnabled(false);
		btn_copiar_vienres_todos.setEnabled(false);
		btn_copiar_sabado_todos.setEnabled(false);
		tablaDomingo.setEnabled(false);
		tablaLunes.setEnabled(false);
		tablaMartes.setEnabled(false);
		tablaMiercoles.setEnabled(false);
		tablaJueves.setEnabled(false);
		tablaViernes.setEnabled(false);
		tablaSabado.setEnabled(false);
	}
	
	public class Cat_Filtro_Actividades extends JFrame {
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		String dia = "";
		
		Object[][] MatrizFiltro ;
		
		Object[][] getTablaFiltro = getTablaFiltro();
		DefaultTableModel modeloFiltro = new DefaultTableModel(getTablaFiltro,
	            new String[]{"Folio", "Actividad","Nivel Crítico",""}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.Integer.class,
		    	java.lang.String.class,
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
	        	 	case 2 : return false;
	        	 	case 3 : return true;
	        	 } 				
	 			return false;
	 		}
			
		};
		
		JTable tablaFiltro = new JTable(modeloFiltro);
	    JScrollPane scroll = new JScrollPane(tablaFiltro);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnAgregar = new JButton("Agregar");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Actividades(String Dia)	{
			setTitle("Filtro de Actividades");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Actividades"));
			trsfiltro = new TableRowSorter(modeloFiltro); 
			tablaFiltro.setRowSorter(trsfiltro);  
			
			dia = Dia;
			
			campo.add(scroll).setBounds(15,43,550,360);
			
			campo.add(txtFolio).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,340,20);
			campo.add(btnAgregar).setBounds(485,20,80,20);
			
			cont.add(campo);
			
			tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(50);
			tablaFiltro.getColumnModel().getColumn(0).setMinWidth(50);
			tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(340);
			tablaFiltro.getColumnModel().getColumn(1).setMinWidth(340);
			tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(50);
			tablaFiltro.getColumnModel().getColumn(3).setMinWidth(50);
			
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
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 3: 
							componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
							if(row%2==0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(Boolean.parseBoolean(modeloFiltro.getValueAt(row,3).toString())){
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
		
			tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
			tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
			tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
			tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			
			btnAgregar.addActionListener(opAgregar);
			
			setSize(600,450);
			setResizable(false);
			setLocationRelativeTo(null);
			
		}
		
		ActionListener opAgregar = new ActionListener() {
			@SuppressWarnings({ "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				Dias diaswitch = Dias.valueOf(dia);
				
				if(tablaFiltro.isEditing()){
		 			tablaFiltro.getCellEditor().stopCellEditing();
				}
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				
				switch (diaswitch) {
				 	case Domingo:
				 		Object[] filaDom = new Object[6];
				 		if(modelDomingo.getRowCount()>0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaDom[0] = modeloFiltro.getValueAt(i, 0);
 									filaDom[1] = modeloFiltro.getValueAt(i, 1);
 									filaDom[2] = modeloFiltro.getValueAt(i, 2);
 									filaDom[3] = Boolean.parseBoolean("false");
 									filaDom[4] = "00:00";
 									filaDom[5] = "00:00";
				 					for(int j=0; j<modelDomingo.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelDomingo.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelDomingo.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelDomingo.addRow(filaDom);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelDomingo.getRowCount()-1 && repetido == 0){
			 									modelDomingo.addRow(filaDom);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelDomingo.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelDomingo.addRow(filaDom);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelDomingo.getRowCount()-1 && repetido == 0){
			 									modelDomingo.addRow(filaDom);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaDom[0] = modeloFiltro.getValueAt(i, 0);
				 					filaDom[1] = modeloFiltro.getValueAt(i, 1);
				 					filaDom[2] = modeloFiltro.getValueAt(i, 2);
				 					filaDom[3] = Boolean.parseBoolean("false");
				 					filaDom[4] = "00:00";
				 					filaDom[5] = "00:00";
						 			
						 			modelDomingo.addRow(filaDom);
				 				}
				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Lunes:
				 		Object[] filaLun = new Object[6];
				 		if(modelLunes.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaLun[0] = modeloFiltro.getValueAt(i, 0);
 									filaLun[1] = modeloFiltro.getValueAt(i, 1);
 									filaLun[2] = modeloFiltro.getValueAt(i, 2);
 									filaLun[3] = Boolean.parseBoolean("false");
 									filaLun[4] = "00:00";
 									filaLun[5] = "00:00";
				 					for(int j=0; j<modelLunes.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelLunes.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelLunes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelLunes.addRow(filaLun);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelLunes.getRowCount()-1 && repetido == 0){
			 									modelLunes.addRow(filaLun);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelLunes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelLunes.addRow(filaLun);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelLunes.getRowCount()-1 && repetido == 0){
			 									modelLunes.addRow(filaLun);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaLun[0] = modeloFiltro.getValueAt(i, 0);
				 					filaLun[1] = modeloFiltro.getValueAt(i, 1);
				 					filaLun[2] = modeloFiltro.getValueAt(i, 2);
				 					filaLun[3] = Boolean.parseBoolean("false");
				 					filaLun[4] = "00:00";
				 					filaLun[5] = "00:00";
						 			
						 			modelLunes.addRow(filaLun);
				 				}
				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Martes:
				 		Object[] filaMar = new Object[6];
				 		if(modelMartes.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaMar[0] = modeloFiltro.getValueAt(i, 0);
 									filaMar[1] = modeloFiltro.getValueAt(i, 1);
 									filaMar[2] = modeloFiltro.getValueAt(i, 2);
 									filaMar[3] = Boolean.parseBoolean("false");
 									filaMar[4] = "00:00";
 									filaMar[5] = "00:00";
				 					for(int j=0; j<modelMartes.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelMartes.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelMartes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelMartes.addRow(filaMar);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelMartes.getRowCount()-1 && repetido == 0){
			 									modelMartes.addRow(filaMar);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelMartes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelMartes.addRow(filaMar);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelMartes.getRowCount()-1 && repetido == 0){
			 									modelMartes.addRow(filaMar);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaMar[0] = modeloFiltro.getValueAt(i, 0);
				 					filaMar[1] = modeloFiltro.getValueAt(i, 1);
				 					filaMar[2] = modeloFiltro.getValueAt(i, 2);
				 					filaMar[3] = Boolean.parseBoolean("false");
				 					filaMar[4] = "00:00";
				 					filaMar[5] = "00:00";
						 			
						 			modelMartes.addRow(filaMar);
				 				}

				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Miércoles:
				 		Object[] filaMie = new Object[6];
				 		if(modelMiercoles.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaMie[0] = modeloFiltro.getValueAt(i, 0);
 									filaMie[1] = modeloFiltro.getValueAt(i, 1);
 									filaMie[2] = modeloFiltro.getValueAt(i, 2);
 									filaMie[3] = Boolean.parseBoolean("false");
 									filaMie[4] = "00:00";
 									filaMie[5] = "00:00";
				 					for(int j=0; j<modelMiercoles.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelMiercoles.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelMiercoles.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelMiercoles.addRow(filaMie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelMiercoles.getRowCount()-1 && repetido == 0){
			 									modelMiercoles.addRow(filaMie);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelMiercoles.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelMiercoles.addRow(filaMie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelMiercoles.getRowCount()-1 && repetido == 0){
			 									modelMiercoles.addRow(filaMie);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaMie[0] = modeloFiltro.getValueAt(i, 0);
				 					filaMie[1] = modeloFiltro.getValueAt(i, 1);
				 					filaMie[2] = modeloFiltro.getValueAt(i, 2);
				 					filaMie[3] = Boolean.parseBoolean("false");
				 					filaMie[4] = "00:00";
				 					filaMie[5] = "00:00";
						 			
						 			modelMiercoles.addRow(filaMie);
				 				}

				 			}
				 		}
				 		dispose();
				 		 break;
		            case Jueves:
		            	Object[] filaJue = new Object[6];
				 		if(modelJueves.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaJue[0] = modeloFiltro.getValueAt(i, 0);
 									filaJue[1] = modeloFiltro.getValueAt(i, 1);
 									filaJue[2] = modeloFiltro.getValueAt(i, 2);
 									filaJue[3] = Boolean.parseBoolean("false");
 									filaJue[4] = "00:00";
 									filaJue[5] = "00:00";
				 					for(int j=0; j<modelJueves.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelJueves.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelJueves.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelJueves.addRow(filaJue);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelJueves.getRowCount()-1 && repetido == 0){
			 									modelJueves.addRow(filaJue);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelJueves.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelJueves.addRow(filaJue);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelJueves.getRowCount()-1 && repetido == 0){
			 									modelJueves.addRow(filaJue);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaJue[0] = modeloFiltro.getValueAt(i, 0);
				 					filaJue[1] = modeloFiltro.getValueAt(i, 1);
				 					filaJue[2] = modeloFiltro.getValueAt(i, 2);
				 					filaJue[3] = Boolean.parseBoolean("false");
				 					filaJue[4] = "00:00";
				 					filaJue[5] = "00:00";
						 			
						 			modelJueves.addRow(filaJue);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
		            case Viernes:
		            	Object[] filaVie = new Object[6];
				 		if(modelViernes.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaVie[0] = modeloFiltro.getValueAt(i, 0);
 									filaVie[1] = modeloFiltro.getValueAt(i, 1);
 									filaVie[2] = modeloFiltro.getValueAt(i, 2);
 									filaVie[3] = Boolean.parseBoolean("false");
 									filaVie[4] = "00:00";
 									filaVie[5] = "00:00";
				 					for(int j=0; j<modelViernes.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelViernes.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelViernes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelViernes.addRow(filaVie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelViernes.getRowCount()-1 && repetido == 0){
			 									modelViernes.addRow(filaVie);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelViernes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelViernes.addRow(filaVie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelViernes.getRowCount()-1 && repetido == 0){
			 									modelViernes.addRow(filaVie);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaVie[0] = modeloFiltro.getValueAt(i, 0);
				 					filaVie[1] = modeloFiltro.getValueAt(i, 1);
				 					filaVie[2] = modeloFiltro.getValueAt(i, 2);
				 					filaVie[3] = Boolean.parseBoolean("false");
				 					filaVie[4] = "00:00";
				 					filaVie[5] = "00:00";
						 			
						 			modelViernes.addRow(filaVie);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
		            case Sábado:
		            	Object[] filaSab = new Object[6];
				 		if(modelSabado.getRowCount() > 0){
				 			for(int i=0; i<modeloFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaSab[0] = modeloFiltro.getValueAt(i, 0);
 									filaSab[1] = modeloFiltro.getValueAt(i, 1);
 									filaSab[2] = modeloFiltro.getValueAt(i, 2);
 									filaSab[3] = Boolean.parseBoolean("false");
 									filaSab[4] = "00:00";
 									filaSab[5] = "00:00";
				 					for(int j=0; j<modelViernes.getRowCount();){
				 						if(Integer.parseInt(modeloFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(modelViernes.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==modelViernes.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelSabado.addRow(filaSab);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelSabado.getRowCount()-1 && repetido == 0){
			 									modelSabado.addRow(filaSab);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==modelSabado.getRowCount()-1 && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									modelSabado.addRow(filaSab);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==modelSabado.getRowCount()-1 && repetido == 0){
			 									modelSabado.addRow(filaSab);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloFiltro.getValueAt(i, 3).toString()) == true){
				 					filaSab[0] = modeloFiltro.getValueAt(i, 0);
				 					filaSab[1] = modeloFiltro.getValueAt(i, 1);
				 					filaSab[2] = modeloFiltro.getValueAt(i, 2);
				 					filaSab[3] = Boolean.parseBoolean("false");
				 					filaSab[4] = "00:00";
				 					filaSab[5] = "00:00";
						 			
						 			modelSabado.addRow(filaSab);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
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
		
		
	   	public Object[][] getTablaFiltro(){
			String todos = "exec sp_select_tabla_actidad_cuadrante";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				MatrizFiltro = new Object[getFilas(todos)][4];
				int i=0;
				while(rs.next()){
					MatrizFiltro[i][0] = rs.getString(1)+"  ";
					MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
					MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
					MatrizFiltro[i][3] = false;
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    return MatrizFiltro; 
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
	
}