package catalogos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Gerarquico;

@SuppressWarnings("serial")
public class Cat_Cuadrante extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JCheckBox  chbStatus = new JCheckBox("Status");
	
	JTextField txtCuadrante = new JTextField();
	
	JTextArea txaDescripcion = new JTextArea(4,4);
	JScrollPane Descripcion = new JScrollPane(txaDescripcion);
	
	String jefatura[] = new Obj_Establecimiento().Combo_Jefatura();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbJefatura = new JComboBox(jefatura);
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String nivel_gerarquico[] = new Obj_Nivel_Gerarquico().Combo_Nivel_Gerarquico();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbnivel_gerarquico = new JComboBox(nivel_gerarquico);
	
	String equipo_trabajo[] = new Obj_Establecimiento().Combo_Eq_Trabajo();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEquipo_Trabajo = new JComboBox(equipo_trabajo);
	
	JCheckBox chTodos 		= new JCheckBox("Todos");
	JCheckBox chDomingo		= new JCheckBox("Domingo");
	JCheckBox chLunes 		= new JCheckBox("Lunes");
	JCheckBox chMartes 		= new JCheckBox("Martes");
	JCheckBox chMiercoles	= new JCheckBox("Miercoles");
	JCheckBox chJueves 		= new JCheckBox("Jueves");
	JCheckBox chViernes 	= new JCheckBox("Viernes");
	JCheckBox chSabado 		= new JCheckBox("Sabado");

	JTabbedPane pestanas = new JTabbedPane();
		JLayeredPane pDomingo = new JLayeredPane(); 
		JLayeredPane pLunes = new JLayeredPane(); 
		JLayeredPane pMarte = new JLayeredPane();
		JLayeredPane pMiercoles = new JLayeredPane(); 
		JLayeredPane pJueves = new JLayeredPane(); 
		JLayeredPane pViernes = new JLayeredPane(); 
		JLayeredPane pSabado = new JLayeredPane(); 
		
	DefaultTableModel modelDomingo = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelLunes = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelMartes = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelMiercoles = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelJueves = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelViernes = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	DefaultTableModel modelSabado = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
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
	
	JTextField txtActividadDomingo = new JTextField();
	JTextField txtActividadLunes = new JTextField();
	JTextField txtActividadMartes = new JTextField();
	JTextField txtActividadMiercoles = new JTextField();
	JTextField txtActividadJueves = new JTextField();
	JTextField txtActividadViernes = new JTextField();
	JTextField txtActividadSabado = new JTextField();
	
	JButton btnAgregarDomingo = new JButton("Agregar");
	JButton btnAgregarLunes = new JButton("Agregar");
	JButton btnAgregarMartes = new JButton("Agregar");
	JButton btnAgregarMiercoles = new JButton("Agregar");
	JButton btnAgregarJueves = new JButton("Agregar");
	JButton btnAgregarViernes = new JButton("Agregar");
	JButton btnAgregarSabado = new JButton("Agregar");
	
	JButton btnSubirDomingo = new JButton("˄");
	JButton btnSubirLunes = new JButton("˄");
	JButton btnSubirMartes = new JButton("˄");
	JButton btnSubirMiercoles = new JButton("˄");
	JButton btnSubirJueves = new JButton("˄");
	JButton btnSubirViernes = new JButton("˄");
	JButton btnSubirSabado = new JButton("˄");
	
	JButton btnBajarDomingo = new JButton("˅");
	JButton btnBajarLunes = new JButton("˅");
	JButton btnBajarMartes = new JButton("˅");
	JButton btnBajarMiercoles = new JButton("˅");
	JButton btnBajarJueves = new JButton("˅");
	JButton btnBajarViernes = new JButton("˅");
	JButton btnBajarSabado = new JButton("˅");
	
	public Cat_Cuadrante(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
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
		this.panel.add(cmbnivel_gerarquico).setBounds(130, y, 300,20);
		
		this.panel.add(new JLabel("Equipo de Trabajo:")).setBounds(x,y+=25,100,20);
		this.panel.add(cmbEquipo_Trabajo).setBounds(130, y, 300,20);
		
		this.panel.add(new JLabel("Establecimiento:")).setBounds(x,y+=25,100,20);
		this.panel.add(cmbEstablecimiento).setBounds(130, y,300,20);

		this.panel.add(new JLabel("Días:")).setBounds(x,y+=30,100,20);
			this.panel.add(chTodos).setBounds(130,y,60,20);
			this.panel.add(chDomingo).setBounds(210,y,80,20);
			this.panel.add(chLunes).setBounds(300,y,60,20);
			this.panel.add(chMartes).setBounds(375,y,70,20);
			this.panel.add(chMiercoles).setBounds(130,y+=25,80,20);
			this.panel.add(chJueves).setBounds(210,y,70,20);
			this.panel.add(chViernes).setBounds(300,y,70,20);
			this.panel.add(chSabado).setBounds(375,y,70,20);
		
		this.panel.add(pestanas).setBounds(450,30,600,450);
		
		this.pestanas.addTab("Domingo", pDomingo);
		this.domingo();
		this.pestanas.addTab("Lunes", pLunes);
		this.lunes();
		this.pestanas.addTab("Martes", pMarte);
		this.martes();
		this.pestanas.addTab("Miercoles", pMiercoles);
		this.miercoles();
		this.pestanas.addTab("Jueves", pJueves);
		this.jueves();
		this.pestanas.addTab("Viernes", pViernes);
		this.viernes();
		this.pestanas.addTab("Sábado", pSabado);
		this.sabado();
		
	    
//		panel.add(btnEditar).setBounds(x+300,y,ancho,20);
//		panel.add(btnDeshacer).setBounds(x+ancho+90,y+=30,ancho,20);
//		panel.add(btnSalir).setBounds(x+80,y,ancho,20);
//		panel.add(btnGuardar).setBounds(x+300,y,ancho,20);
		

		
//		txtFolio.setDocument(new JTextFieldLimit(9));
//		txaNombre.setDocument(new JTextFieldLimit(100));
//		
//		txaNombre.setLineWrap(true); 
//		txaNombre.setWrapStyleWord(true);
//		txaNombre.setDocument(new JTextFieldLimit(250));
//		
//		txaDescripcion.setLineWrap(true); 
//		txaDescripcion.setWrapStyleWord(true);
//		txaDescripcion.setDocument(new JTextFieldLimit(500));
//		
//		chStatus.setEnabled(false);
//		cmbEstablecimiento.setEditable(false);
//		
//		panel.add(tablapane).setBounds(5,y+=30,ancho*7+50,300);
//		  pDomingo.setOpaque(true); 
//		  pDomingo.setBackground(Color.white);
//		 panel.add(tablapane).setBounds(5,y+=30,ancho*7+50,300);
//		  
//		  pLunes.add("Tabla",getPanelTabla()).setBounds(5000000,0,0,0);
//		 
//		  pLunes.setOpaque(true); 
//		  pLunes.setBackground(Color.gray);
//		  tablapane.addTab("Lunes", pLunes);
//		  
//		  pMartes.setOpaque(true); 
//		  pMartes.setBackground(Color.white);
//		  tablapane.addTab("Martes", pMartes);
//		  
//		  pMiercoles.setOpaque(true); 
//		  pMiercoles.setBackground(Color.gray);
//		  tablapane.addTab("Miercoles", pMiercoles);
//		  
//		  pJueves.setOpaque(true); 
//		  pJueves.setBackground(Color.white);
//		  tablapane.addTab("Jueves", pJueves);
//		  
//		  pViernes.setOpaque(true); 
//		  pViernes.setBackground(Color.gray);
//		  tablapane.addTab("Viernes", pViernes);
//		  
//		  pSabado.setOpaque(true); 
//		  pSabado.setBackground(Color.white);
//		  tablapane.addTab("Sabado", pSabado);
		  

		txtFolio.requestFocus();

		
		cont.add(panel);
		
		this.setSize(1080,730);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void domingo(){
		this.pDomingo.setBorder(BorderFactory.createTitledBorder("Domingo"));
		this.pDomingo.setOpaque(true); 
		this.pDomingo.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pDomingo.add(scrollDomingo).setBounds(15,50,560,350);

		this.pDomingo.add(txtActividadDomingo).setBounds(15, 20, 300, 20);
		
		this.pDomingo.add(btnAgregarDomingo).setBounds(320,20,75,20);
		
		this.pDomingo.add(btnBajarDomingo).setBounds(480,20,40,20);
		this.pDomingo.add(btnSubirDomingo).setBounds(535,20,40,20);
		
		this.tablaDomingo.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaDomingo.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(1).setHeaderValue("Actividad");
		
	}
	
	public void lunes(){
		this.pLunes.setBorder(BorderFactory.createTitledBorder("Lunes"));
		this.pLunes.setOpaque(true); 
		this.pLunes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pLunes.add(scrollLunes).setBounds(15,50,560,350);
		
		this.pLunes.add(txtActividadLunes).setBounds(15, 20, 300, 20);
		
		this.pLunes.add(btnAgregarLunes).setBounds(320,20,75,20);
		
		this.pLunes.add(btnBajarLunes).setBounds(480,20,40,20);
		this.pLunes.add(btnSubirLunes).setBounds(535,20,40,20);
		
		this.tablaLunes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaLunes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaLunes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaLunes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void martes(){
		this.pMarte.setBorder(BorderFactory.createTitledBorder("Martes"));
		this.pMarte.setOpaque(true); 
		this.pMarte.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMarte.add(scrollMartes).setBounds(15,50,560,350);
		
		this.pMarte.add(txtActividadMartes).setBounds(15, 20, 300, 20);
		
		this.pMarte.add(btnAgregarMartes).setBounds(320,20,75,20);
		
		this.pMarte.add(btnBajarMartes).setBounds(480,20,40,20);
		this.pMarte.add(btnSubirMartes).setBounds(535,20,40,20);
		
		this.tablaMartes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaMartes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaMartes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaMartes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void miercoles(){
		this.pMiercoles.setBorder(BorderFactory.createTitledBorder("Miercoles"));
		this.pMiercoles.setOpaque(true); 
		this.pMiercoles.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMiercoles.add(scrollMiercoles).setBounds(15,50,560,350);
		
		this.pMiercoles.add(txtActividadMiercoles).setBounds(15, 20, 300, 20);
		
		this.pMiercoles.add(btnAgregarMiercoles).setBounds(320,20,75,20);
		
		this.pMiercoles.add(btnBajarMiercoles).setBounds(480,20,40,20);
		this.pMiercoles.add(btnSubirMiercoles).setBounds(535,20,40,20);
		
		this.tablaMiercoles.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaMiercoles.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void jueves(){
		this.pJueves.setBorder(BorderFactory.createTitledBorder("Jueves"));
		this.pJueves.setOpaque(true); 
		this.pJueves.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pJueves.add(scrollJueves).setBounds(15,50,560,350);
		
		this.pJueves.add(txtActividadJueves).setBounds(15, 20, 300, 20);
		
		this.pJueves.add(btnAgregarJueves).setBounds(320,20,75,20);
		
		this.pJueves.add(btnBajarJueves).setBounds(480,20,40,20);
		this.pJueves.add(btnSubirJueves).setBounds(535,20,40,20);
		
		this.tablaJueves.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaJueves.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaJueves.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaJueves.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void viernes(){
		this.pViernes.setBorder(BorderFactory.createTitledBorder("Viernes"));
		this.pViernes.setOpaque(true); 
		this.pViernes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pViernes.add(scrollViernes).setBounds(15,50,560,350);
		
		this.pViernes.add(txtActividadViernes).setBounds(15, 20, 300, 20);
		
		this.pViernes.add(btnAgregarViernes).setBounds(320,20,75,20);
		
		this.pViernes.add(btnBajarViernes).setBounds(480,20,40,20);
		this.pViernes.add(btnSubirViernes).setBounds(535,20,40,20);
		
		this.tablaViernes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaViernes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaViernes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaViernes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void sabado(){
		this.pSabado.setBorder(BorderFactory.createTitledBorder("Sabado"));
		this.pSabado.setOpaque(true); 
		this.pSabado.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pSabado.add(scrollSabado).setBounds(15,50,560,350);
		
		this.pSabado.add(txtActividadSabado).setBounds(15, 20, 300, 20);
		
		this.pSabado.add(btnAgregarSabado).setBounds(320,20,75,20);
		
		this.pSabado.add(btnBajarSabado).setBounds(480,20,40,20);
		this.pSabado.add(btnSubirSabado).setBounds(535,20,40,20);
		
		this.tablaSabado.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaSabado.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaSabado.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaSabado.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	ActionListener opAgregarDomingo = new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent arg0) {
			String[] addModeloDomingo = new String[2];
			txtActividadDomingo.getText();
			
		}
	};	
	
	ActionListener opAgregarLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	ActionListener opAgregarMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	ActionListener opAgregarMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	ActionListener opAgregarJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	ActionListener opAgregarViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	ActionListener opAgregarSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};	
	
	public static void main (String arg []){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}