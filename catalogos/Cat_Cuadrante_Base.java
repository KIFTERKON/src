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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import objetos.Obj_Establecimiento;
import objetos.Obj_Nivel_Gerarquico;

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
	
	JButton btnRemoverDomingo = new JButton("Quitar");
	JButton btnRemoverLunes = new JButton("Quitar");
	JButton btnRemoverMartes = new JButton("Quitar");
	JButton btnRemoverMiercoles = new JButton("Quitar");
	JButton btnRemoverJueves = new JButton("Quitar");
	JButton btnRemoverViernes = new JButton("Quitar");
	JButton btnRemoverSabado = new JButton("Quitar");
	
	public Cat_Cuadrante_Base(){
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
			this.panel.add(chTodos).setBounds(125,y+=10,60,20);
			this.panel.add(chDomingo).setBounds(205,y,80,20);
			this.panel.add(chLunes).setBounds(295,y,60,20);
			this.panel.add(chMartes).setBounds(368,y,70,20);
			this.panel.add(chMiercoles).setBounds(125,y+=25,80,20);
			this.panel.add(chJueves).setBounds(205,y,70,20);
			this.panel.add(chViernes).setBounds(295,y,70,20);
			this.panel.add(chSabado).setBounds(368,y,70,20);
		
		this.panel.add(pestanas).setBounds(450,30,600, 500);
		
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
		
		this.todoFalse();
		
		this.setSize(1080,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void domingo(){
		this.pDomingo.setBorder(BorderFactory.createTitledBorder("Domingo"));
		this.pDomingo.setOpaque(true); 
		this.pDomingo.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pDomingo.add(scrollDomingo).setBounds(15,50,560,405);

		this.pDomingo.add(txtActividadDomingo).setBounds(15, 20, 300, 20);
		
		this.pDomingo.add(btnAgregarDomingo).setBounds(320,20,75,20);
		
		this.pDomingo.add(btnBajarDomingo).setBounds(400,20,40,20);
		this.pDomingo.add(btnSubirDomingo).setBounds(455,20,40,20);
		
		this.pDomingo.add(btnRemoverDomingo).setBounds(500,20,75,20);
		
		this.tablaDomingo.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaDomingo.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaDomingo.getColumnModel().getColumn(1).setHeaderValue("Actividad");
		
	}
	
	public void lunes(){
		this.pLunes.setBorder(BorderFactory.createTitledBorder("Lunes"));
		this.pLunes.setOpaque(true); 
		this.pLunes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pLunes.add(scrollLunes).setBounds(15,50,560,405);
		
		this.pLunes.add(txtActividadLunes).setBounds(15, 20, 300, 20);
		
		this.pLunes.add(btnAgregarLunes).setBounds(320,20,75,20);
		
		this.pLunes.add(btnBajarLunes).setBounds(400,20,40,20);
		this.pLunes.add(btnSubirLunes).setBounds(455,20,40,20);
		
		this.pLunes.add(btnRemoverLunes).setBounds(500,20,75,20);
		
		this.tablaLunes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaLunes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaLunes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaLunes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void martes(){
		this.pMarte.setBorder(BorderFactory.createTitledBorder("Martes"));
		this.pMarte.setOpaque(true); 
		this.pMarte.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMarte.add(scrollMartes).setBounds(15,50,560,405);
		
		this.pMarte.add(txtActividadMartes).setBounds(15, 20, 300, 20);
		
		this.pMarte.add(btnAgregarMartes).setBounds(320,20,75,20);
		
		this.pMarte.add(btnBajarMartes).setBounds(400,20,40,20);
		this.pMarte.add(btnSubirMartes).setBounds(455,20,40,20);
		
		this.pMarte.add(btnRemoverMartes).setBounds(500,20,75,20);
		
		this.tablaMartes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaMartes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaMartes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaMartes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void miercoles(){
		this.pMiercoles.setBorder(BorderFactory.createTitledBorder("Miercoles"));
		this.pMiercoles.setOpaque(true); 
		this.pMiercoles.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pMiercoles.add(scrollMiercoles).setBounds(15,50,560,405);
		
		this.pMiercoles.add(txtActividadMiercoles).setBounds(15, 20, 300, 20);
		
		this.pMiercoles.add(btnAgregarMiercoles).setBounds(320,20,75,20);
		
		this.pMiercoles.add(btnBajarMiercoles).setBounds(400,20,40,20);
		this.pMiercoles.add(btnSubirMiercoles).setBounds(455,20,40,20);
		
		this.pMiercoles.add(btnRemoverMiercoles).setBounds(500,20,75,20);
		
		this.tablaMiercoles.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaMiercoles.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaMiercoles.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void jueves(){
		this.pJueves.setBorder(BorderFactory.createTitledBorder("Jueves"));
		this.pJueves.setOpaque(true); 
		this.pJueves.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pJueves.add(scrollJueves).setBounds(15,50,560,405);
		
		this.pJueves.add(txtActividadJueves).setBounds(15, 20, 300, 20);
		
		this.pJueves.add(btnAgregarJueves).setBounds(320,20,75,20);
		
		this.pJueves.add(btnBajarJueves).setBounds(400,20,40,20);
		this.pJueves.add(btnSubirJueves).setBounds(455,20,40,20);
		
		this.pJueves.add(btnRemoverJueves).setBounds(500,20,75,20);
		
		this.tablaJueves.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaJueves.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaJueves.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaJueves.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void viernes(){
		this.pViernes.setBorder(BorderFactory.createTitledBorder("Viernes"));
		this.pViernes.setOpaque(true); 
		this.pViernes.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pViernes.add(scrollViernes).setBounds(15,50,560,405);
		
		this.pViernes.add(txtActividadViernes).setBounds(15, 20, 300, 20);
		
		this.pViernes.add(btnAgregarViernes).setBounds(320,20,75,20);
		
		this.pViernes.add(btnBajarViernes).setBounds(400,20,40,20);
		this.pViernes.add(btnSubirViernes).setBounds(455,20,40,20);
		
		this.pViernes.add(btnRemoverViernes).setBounds(500,20,75,20);
		
		this.tablaViernes.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaViernes.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaViernes.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaViernes.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	public void sabado(){
		this.pSabado.setBorder(BorderFactory.createTitledBorder("Sabado"));
		this.pSabado.setOpaque(true); 
		this.pSabado.setBackground(new Color(Integer.parseInt("EBEBEB",16)));
		
		this.pSabado.add(scrollSabado).setBounds(15,50,560,405);
		
		this.pSabado.add(txtActividadSabado).setBounds(15, 20, 300, 20);
		
		this.pSabado.add(btnAgregarSabado).setBounds(320,20,75,20);
		
		this.pSabado.add(btnBajarSabado).setBounds(400,20,40,20);
		this.pSabado.add(btnSubirSabado).setBounds(455,20,40,20);
		
		this.pSabado.add(btnRemoverSabado).setBounds(500,20,75,20);
		
		this.tablaSabado.getColumnModel().getColumn(0).setHeaderValue("Folio");
		this.tablaSabado.getColumnModel().getColumn(0).setMaxWidth(50);
		this.tablaSabado.getColumnModel().getColumn(0).setMinWidth(50);
		this.tablaSabado.getColumnModel().getColumn(1).setHeaderValue("Actividad");
	}
	
	ActionListener opAgregarDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadDomingo.getText().equals("")){
				String[] addModeloDomingo = new String[2];
				
				addModeloDomingo[0] = tablaDomingo.getRowCount()+1+"";
				addModeloDomingo[1] = txtActividadDomingo.getText();
				
				modelDomingo.addRow(addModeloDomingo);
				
				txtActividadDomingo.setText("");
				txtActividadDomingo.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadLunes.getText().equals("")){
				String[] addModeloLunes = new String[2];
				
				addModeloLunes[0] = tablaLunes.getRowCount()+1+"";
				addModeloLunes[1] = txtActividadLunes.getText();
				
				modelLunes.addRow(addModeloLunes);
				
				txtActividadLunes.setText("");
				txtActividadLunes.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadMartes.getText().equals("")){
				String[] addModeloMartes = new String[2];
				
				addModeloMartes[0] = tablaMartes.getRowCount()+1+"";
				addModeloMartes[1] = txtActividadMartes.getText();
				
				modelMartes.addRow(addModeloMartes);
				
				txtActividadMartes.setText("");
				txtActividadMartes.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadMiercoles.getText().equals("")){
				String[] addModeloMiercoles = new String[2];
				
				addModeloMiercoles[0] = tablaMiercoles.getRowCount()+1+"";
				addModeloMiercoles[1] = txtActividadMiercoles.getText();
				
				modelMiercoles.addRow(addModeloMiercoles);
				
				txtActividadMiercoles.setText("");
				txtActividadMiercoles.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadJueves.getText().equals("")){
				String[] addModeloJueves = new String[2];
				
				addModeloJueves[0] = tablaJueves.getRowCount()+1+"";
				addModeloJueves[1] = txtActividadJueves.getText();
				
				modelJueves.addRow(addModeloJueves);
				
				txtActividadJueves.setText("");
				txtActividadJueves.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadViernes.getText().equals("")){
				String[] addModeloViernes = new String[2];
				
				addModeloViernes[0] = tablaViernes.getRowCount()+1+"";
				addModeloViernes[1] = txtActividadViernes.getText();
				
				modelViernes.addRow(addModeloViernes);
				
				txtActividadViernes.setText("");
				txtActividadViernes.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};	
	
	ActionListener opAgregarSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtActividadSabado.getText().equals("")){
				String[] addModeloSabado = new String[2];
				
				addModeloSabado[0] = tablaSabado.getRowCount()+1+"";
				addModeloSabado[1] = txtActividadSabado.getText();
				
				modelSabado.addRow(addModeloSabado);
				
				txtActividadSabado.setText("");
				txtActividadSabado.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "El campo de actividad está vació","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
	
	ActionListener opQuitarDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(tablaDomingo.getRowCount()>0){
				if(tablaDomingo.isRowSelected(tablaDomingo.getSelectedRow())){
					modelDomingo.removeRow(tablaDomingo.getSelectedRow());
					for(int i=0; i<tablaSabado.getRowCount(); i ++){
						modelDomingo.setValueAt(i+1,i,0);
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
						modelLunes.setValueAt(i+1,i,0);
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
						modelMartes.setValueAt(i+1,i,0);
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
						modelMiercoles.setValueAt(i+1,i,0);
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
						modelJueves.setValueAt(i+1,i,0);
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
						modelViernes.setValueAt(i+1,i,0);
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
						modelSabado.setValueAt(i+1,i,0);
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
	
	ActionListener opDiaDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chDomingo.isSelected()){
				txtActividadDomingo.setEditable(true);
				btnAgregarDomingo.setEnabled(true);
				btnSubirDomingo.setEnabled(true);
				btnBajarDomingo.setEnabled(true);
				btnRemoverDomingo.setEnabled(true);
			}else{
				txtActividadDomingo.setEditable(false);
				btnAgregarDomingo.setEnabled(false);
				btnSubirDomingo.setEnabled(false);
				btnBajarDomingo.setEnabled(false);
				btnRemoverDomingo.setEnabled(false);
				
				while(tablaDomingo.getRowCount() > 0){
					modelDomingo.removeRow(0);
				}
			}
		}
	};

	ActionListener opDiaLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chLunes.isSelected()){
				txtActividadLunes.setEditable(true);
				btnAgregarLunes.setEnabled(true);
				btnSubirLunes.setEnabled(true);
				btnBajarLunes.setEnabled(true);
				btnRemoverLunes.setEnabled(true);
			}else{
				txtActividadLunes.setEditable(false);
				btnAgregarLunes.setEnabled(false);
				btnSubirLunes.setEnabled(false);
				btnBajarLunes.setEnabled(false);
				btnRemoverLunes.setEnabled(false);
				
				while(tablaLunes.getRowCount() > 0){
					modelLunes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chMartes.isSelected()){
				txtActividadMartes.setEditable(true);
				btnAgregarMartes.setEnabled(true);
				btnSubirMartes.setEnabled(true);
				btnBajarMartes.setEnabled(true);
				btnRemoverMartes.setEnabled(true);
			}else{
				txtActividadMartes.setEditable(false);
				btnAgregarMartes.setEnabled(false);
				btnSubirMartes.setEnabled(false);
				btnBajarMartes.setEnabled(false);
				btnRemoverMartes.setEnabled(false);
				
				while(tablaMartes.getRowCount() > 0){
					modelMartes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chMiercoles.isSelected()){
				txtActividadMiercoles.setEditable(true);
				btnAgregarMiercoles.setEnabled(true);
				btnSubirMiercoles.setEnabled(true);
				btnBajarMiercoles.setEnabled(true);
				btnRemoverMiercoles.setEnabled(true);
			}else{
				txtActividadMiercoles.setEditable(false);
				btnAgregarMiercoles.setEnabled(false);
				btnSubirMiercoles.setEnabled(false);
				btnBajarMiercoles.setEnabled(false);
				btnRemoverMiercoles.setEnabled(false);
				
				while(tablaMiercoles.getRowCount() > 0){
					modelMiercoles.removeRow(0);
				}
			}
			
		}
	};
	
	ActionListener opDiaJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chJueves.isSelected()){
				txtActividadJueves.setEditable(true);
				btnAgregarJueves.setEnabled(true);
				btnSubirJueves.setEnabled(true);
				btnBajarJueves.setEnabled(true);
				btnRemoverJueves.setEnabled(true);
			}else{
				txtActividadJueves.setEditable(false);
				btnAgregarJueves.setEnabled(false);
				btnSubirJueves.setEnabled(false);
				btnBajarJueves.setEnabled(false);
				btnRemoverJueves.setEnabled(false);
				
				while(tablaJueves.getRowCount() > 0){
					modelJueves.removeRow(0);
				}
			}
			
		}
	};
	
	ActionListener opDiaViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chViernes.isSelected()){
				txtActividadViernes.setEditable(true);
				btnAgregarViernes.setEnabled(true);
				btnSubirViernes.setEnabled(true);
				btnBajarViernes.setEnabled(true);
				btnRemoverViernes.setEnabled(true);
			}else{
				txtActividadViernes.setEditable(false);
				btnAgregarViernes.setEnabled(false);
				btnSubirViernes.setEnabled(false);
				btnBajarViernes.setEnabled(false);
				btnRemoverViernes.setEnabled(false);
				
				while(tablaViernes.getRowCount() > 0){
					modelViernes.removeRow(0);
				}
			}
		
		}
	};
	
	ActionListener opDiaSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chSabado.isSelected()){
				txtActividadSabado.setEditable(true);
				btnAgregarSabado.setEnabled(true);
				btnSubirSabado.setEnabled(true);
				btnBajarSabado.setEnabled(true);
				btnRemoverSabado.setEnabled(true);
			}else{
				txtActividadSabado.setEditable(false);
				btnAgregarSabado.setEnabled(false);
				btnSubirSabado.setEnabled(false);
				btnBajarSabado.setEnabled(false);
				btnRemoverSabado.setEnabled(false);
				
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
				txtActividadDomingo.setEditable(true);
				btnAgregarDomingo.setEnabled(true);
				btnSubirDomingo.setEnabled(true);
				btnBajarDomingo.setEnabled(true);
				btnRemoverDomingo.setEnabled(true);
				txtActividadLunes.setEditable(true);
				btnAgregarLunes.setEnabled(true);
				btnSubirLunes.setEnabled(true);
				btnBajarLunes.setEnabled(true);
				btnRemoverLunes.setEnabled(true);
				txtActividadMartes.setEditable(true);
				btnAgregarMartes.setEnabled(true);
				btnSubirMartes.setEnabled(true);
				btnBajarMartes.setEnabled(true);
				btnRemoverMartes.setEnabled(true);
				txtActividadMiercoles.setEditable(true);
				btnAgregarMiercoles.setEnabled(true);
				btnSubirMiercoles.setEnabled(true);
				btnBajarMiercoles.setEnabled(true);
				btnRemoverMiercoles.setEnabled(true);
				txtActividadJueves.setEditable(true);
				btnAgregarJueves.setEnabled(true);
				btnSubirJueves.setEnabled(true);
				btnBajarJueves.setEnabled(true);
				btnRemoverJueves.setEnabled(true);
				txtActividadViernes.setEditable(true);
				btnAgregarViernes.setEnabled(true);
				btnSubirViernes.setEnabled(true);
				btnBajarViernes.setEnabled(true);
				btnRemoverViernes.setEnabled(true);
				txtActividadSabado.setEditable(true);
				btnAgregarSabado.setEnabled(true);
				btnSubirSabado.setEnabled(true);
				btnBajarSabado.setEnabled(true);
				btnRemoverSabado.setEnabled(true);
			}else{
				chDomingo.setSelected(false);
				chLunes.setSelected(false);
				chMartes.setSelected(false);
				chMiercoles.setSelected(false);
				chJueves.setSelected(false);
				chViernes.setSelected(false);
				chSabado.setSelected(false);
				txtActividadDomingo.setEditable(false);
				btnAgregarDomingo.setEnabled(false);
				btnSubirDomingo.setEnabled(false);
				btnBajarDomingo.setEnabled(false);
				btnRemoverDomingo.setEnabled(false);
				txtActividadLunes.setEditable(false);
				btnAgregarLunes.setEnabled(false);
				btnSubirLunes.setEnabled(false);
				btnBajarLunes.setEnabled(false);
				btnRemoverLunes.setEnabled(false);
				txtActividadMartes.setEditable(false);
				btnAgregarMartes.setEnabled(false);
				btnSubirMartes.setEnabled(false);
				btnBajarMartes.setEnabled(false);
				btnRemoverMartes.setEnabled(false);
				txtActividadMiercoles.setEditable(false);
				btnAgregarMiercoles.setEnabled(false);
				btnSubirMiercoles.setEnabled(false);
				btnBajarMiercoles.setEnabled(false);
				btnRemoverMiercoles.setEnabled(false);
				txtActividadJueves.setEditable(false);
				btnAgregarJueves.setEnabled(false);
				btnSubirJueves.setEnabled(false);
				btnBajarJueves.setEnabled(false);
				btnRemoverJueves.setEnabled(false);
				txtActividadViernes.setEditable(false);
				btnAgregarViernes.setEnabled(false);
				btnSubirViernes.setEnabled(false);
				btnBajarViernes.setEnabled(false);
				btnRemoverViernes.setEnabled(false);
				txtActividadSabado.setEditable(false);
				btnAgregarSabado.setEnabled(false);
				btnSubirSabado.setEnabled(false);
				btnBajarSabado.setEnabled(false);
				btnRemoverSabado.setEnabled(false);
			}
	
		}
	};
	public void todoFalse(){
		txtActividadDomingo.setEditable(false);
		btnAgregarDomingo.setEnabled(false);
		btnSubirDomingo.setEnabled(false);
		btnBajarDomingo.setEnabled(false);
		btnRemoverDomingo.setEnabled(false);
		txtActividadLunes.setEditable(false);
		btnAgregarLunes.setEnabled(false);
		btnSubirLunes.setEnabled(false);
		btnBajarLunes.setEnabled(false);
		btnRemoverLunes.setEnabled(false);
		txtActividadMartes.setEditable(false);
		btnAgregarMartes.setEnabled(false);
		btnSubirMartes.setEnabled(false);
		btnBajarMartes.setEnabled(false);
		btnRemoverMartes.setEnabled(false);
		txtActividadMiercoles.setEditable(false);
		btnAgregarMiercoles.setEnabled(false);
		btnSubirMiercoles.setEnabled(false);
		btnBajarMiercoles.setEnabled(false);
		btnRemoverMiercoles.setEnabled(false);
		txtActividadJueves.setEditable(false);
		btnAgregarJueves.setEnabled(false);
		btnSubirJueves.setEnabled(false);
		btnBajarJueves.setEnabled(false);
		btnRemoverJueves.setEnabled(false);
		txtActividadViernes.setEditable(false);
		btnAgregarViernes.setEnabled(false);
		btnSubirViernes.setEnabled(false);
		btnBajarViernes.setEnabled(false);
		btnRemoverViernes.setEnabled(false);
		txtActividadSabado.setEditable(false);
		btnAgregarSabado.setEnabled(false);
		btnSubirSabado.setEnabled(false);
		btnBajarSabado.setEnabled(false);
		btnRemoverSabado.setEnabled(false);
	}
	

	
}