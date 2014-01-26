package cuadrantes;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;




import SQL.Connexion;

import objetos.Obj_Cuadrante;
import objetos.Obj_Cuadrante_Actividades;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Cuadrante_Nivel_Jerarquico extends Cat_Cuadrante {

	private final String NOMBRECOMPLETO;
	private final String NOMBREENCARGADO;
	private final int FOLIO;
	
	Cat_Plantilla_Tabla plantillaDomingo 	= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaLunes 		= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaMartes 	= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaMiercoles 	= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaJueves 	= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaViernes 	= new Cat_Plantilla_Tabla();
	Cat_Plantilla_Tabla plantillaSabado 	= new Cat_Plantilla_Tabla();
	
	private JButton btnActividadNueva = new JButton("Nueva Actividad");
	
	public Cat_Cuadrante_Nivel_Jerarquico(String nombre, int folio){
		NOMBRECOMPLETO = nombre;
		FOLIO = folio;
		NOMBREENCARGADO =  new Obj_Usuario().LeerSession().getNombre_completo();
		initial();
		panel.add(btnActividadNueva).setBounds(1068,20,110,20);
	}
	
	private void initial(){
		this.setTitle("Cuadrante y Actividades: "+ NOMBRECOMPLETO);
		this.panel.setBorder(BorderFactory.createTitledBorder("Cuadrante y Actividades: "+ NOMBRECOMPLETO));
		
		// SE QUITARON LOS BOTONES QUE NO SON NECESARIOS
		this.btnBuscar.setVisible(false);
		this.btnSimilar.setVisible(false);
		this.btnizquierda.setVisible(false);
		this.btnderecha.setVisible(false);
		this.btnNuevo.setVisible(false);
		
		// RESETEAR BOTONES QUE SI SE VAN A UTILIZAR PERO CON UN ACTIONLISTENER DIFERENTE
		this.btnDeshacer.removeActionListener(opDeshacer);
		this.btnDeshacer.addActionListener(opDesahacerReal);
		this.btnEditar.removeActionListener(opEditar);
		this.btnGuardar.removeActionListener(opGuardar);
		this.btnGuardar.addActionListener(opGuardarReal);
		this.btnActividadNueva.addActionListener(opActividadNueva);
		
		this.btn_copiar_domingo_al_lunes.setVisible(false);
		this.btn_copiar_lunes_al_martes.setVisible(false);
		this.btn_copiar_martes_al_miercoles.setVisible(false);
		this.btn_copiar_miercoles_al_jueves.setVisible(false);
		this.btn_copiar_jueves_al_viernes.setVisible(false);
		this.btn_copiar_vienres_al_sabado.setVisible(false);
		this.btn_copiar_sabado_al_domingo.setVisible(false);
		
		this.btn_copiar_domingo_todos.setVisible(false);
		this.btn_copiar_lunes_todos.setVisible(false);
		this.btn_copiar_martes_todos.setVisible(false);
		this.btn_copiar_miercoles_todos.setVisible(false);
		this.btn_copiar_jueves_todos.setVisible(false);
		this.btn_copiar_vienres_todos.setVisible(false);
		this.btn_copiar_sabado_todos.setVisible(false);
		
		this.txtFolio.removeKeyListener(valida);
		this.txtFolio.setEditable(false);
		
		// ACOMODO DE TABLAS
		this.pDomingo.remove(scrollDomingo);
			this.pDomingo.add(scrollDomingo).setBounds(10,200,705,260);
			this.pDomingo.add(plantillaDomingo.scrollPlantilla).setBounds(10,45,705,150);
		this.pLunes.remove(scrollLunes);
			this.pLunes.add(scrollLunes).setBounds(10,200,705,260);
			this.pLunes.add(plantillaLunes.scrollPlantilla).setBounds(10,45,705,150);
		this.pMarte.remove(scrollMartes);
			this.pMarte.add(scrollMartes).setBounds(10,200,705,260);
			this.pMarte.add(plantillaMartes.scrollPlantilla).setBounds(10,45,705,150);
		this.pMiercoles.remove(scrollMiercoles);
			this.pMiercoles.add(scrollMiercoles).setBounds(10,200,705,260);
			this.pMiercoles.add(plantillaMiercoles.scrollPlantilla).setBounds(10,45,705,150);
		this.pJueves.remove(scrollJueves);
			this.pJueves.add(scrollJueves).setBounds(10,200,705,260);
			this.pJueves.add(plantillaJueves.scrollPlantilla).setBounds(10,45,705,150);
		this.pViernes.remove(scrollViernes);
			this.pViernes.add(scrollViernes).setBounds(10,200,705,260);
			this.pViernes.add(plantillaViernes.scrollPlantilla).setBounds(10,45,705,150);
		this.pSabado.remove(scrollSabado);
			this.pSabado.add(scrollSabado).setBounds(10,200,705,260);
			this.pSabado.add(plantillaSabado.scrollPlantilla).setBounds(10,45,705,150);
			
		BuscarActividades();
		BuscarActividadesCuadrante();
		
		if(this.chDomingo.isSelected() && this.chLunes.isSelected() && 
		   this.chMartes.isSelected() && this.chMiercoles.isSelected() &&
		   this.chJueves.isSelected() && this.chViernes.isSelected() && this.chSabado.isSelected()){
			this.chTodos.setSelected(true);
		} 
		
		if(this.chDomingo.isSelected()){
			this.btnAgregarDomingo.setEnabled(true);
			this.btnAgregarDomingo.removeActionListener(opAgregarDomingo);
			this.btnAgregarDomingo.addActionListener(opAgregar);
			
			this.btnSubirDomingo.setEnabled(true);
			this.btnSubirDomingo.removeActionListener(opDomingo);
			this.btnSubirDomingo.addActionListener(opMovDomingo);
			
			this.btnBajarDomingo.setEnabled(true);
			this.btnBajarDomingo.removeActionListener(opDomingo);
			this.btnBajarDomingo.addActionListener(opMovDomingo);
			
			this.btnRemoverDomingo.setEnabled(true);
			this.btnRemoverDomingo.removeActionListener(opQuitarDomingo);
			this.btnRemoverDomingo.addActionListener(opQuitar);
			
			this.plantillaDomingo.tablaPlantilla.setEnabled(true);
		}
		if(this.chLunes.isSelected()){
			this.btnAgregarLunes.setEnabled(true);
			this.btnAgregarLunes.removeActionListener(opAgregarLunes);
			this.btnAgregarLunes.addActionListener(opAgregar);
			
			this.btnSubirLunes.setEnabled(true);
			this.btnSubirLunes.removeActionListener(opLunes);
			this.btnSubirLunes.addActionListener(opMovLunes);
			
			this.btnBajarLunes.setEnabled(true);
			this.btnBajarLunes.removeActionListener(opLunes);
			this.btnBajarLunes.addActionListener(opMovLunes);
			
			this.btnRemoverLunes.setEnabled(true);
			this.btnRemoverLunes.removeActionListener(opQuitarLunes);
			this.btnRemoverLunes.addActionListener(opQuitar);
			
			this.plantillaLunes.tablaPlantilla.setEnabled(true);
		}
		if(this.chMartes.isSelected()){
			this.btnAgregarMartes.setEnabled(true);
			this.btnAgregarMartes.removeActionListener(opAgregarMartes);
			this.btnAgregarMartes.addActionListener(opAgregar);
			
			this.btnSubirMartes.setEnabled(true);
			this.btnSubirMartes.removeActionListener(opMartes);
			this.btnSubirMartes.addActionListener(opMovMartes);
			
			this.btnBajarMartes.setEnabled(true);
			this.btnBajarMartes.removeActionListener(opMartes);
			this.btnBajarMartes.addActionListener(opMovMartes);
			
			this.btnRemoverMartes.setEnabled(true);
			this.btnRemoverMartes.removeActionListener(opQuitarMartes);
			this.btnRemoverMartes.addActionListener(opQuitar);
			
			this.plantillaMartes.tablaPlantilla.setEnabled(true);
		}
		if(this.chMiercoles.isSelected()){
			this.btnAgregarMiercoles.setEnabled(true);
			this.btnAgregarMiercoles.removeActionListener(opAgregarMiercoles);
			this.btnAgregarMiercoles.addActionListener(opAgregar);
			
			this.btnSubirMiercoles.setEnabled(true);
			this.btnSubirMiercoles.removeActionListener(opMiercoles);
			this.btnSubirMiercoles.addActionListener(opMovMiercoles);
			
			this.btnBajarMiercoles.setEnabled(true);
			this.btnBajarMiercoles.removeActionListener(opMiercoles);
			this.btnBajarMiercoles.addActionListener(opMovMiercoles);
			
			this.btnRemoverMiercoles.setEnabled(true);
			this.btnRemoverMiercoles.removeActionListener(opQuitarMiercoles);
			this.btnRemoverMiercoles.addActionListener(opQuitar);
			
			this.plantillaMiercoles.tablaPlantilla.setEnabled(true);
		}
		if(this.chJueves.isSelected()){
			this.btnAgregarJueves.setEnabled(true);
			this.btnAgregarJueves.removeActionListener(opAgregarJueves);
			this.btnAgregarJueves.addActionListener(opAgregar);
			
			this.btnSubirJueves.setEnabled(true);
			this.btnSubirJueves.removeActionListener(opJueves);
			this.btnSubirJueves.addActionListener(opMovJueves);
			
			this.btnBajarJueves.setEnabled(true);
			this.btnBajarJueves.removeActionListener(opJueves);
			this.btnBajarJueves.addActionListener(opMovJueves);
			
			this.btnRemoverJueves.setEnabled(true);
			this.btnRemoverJueves.removeActionListener(opQuitarJueves);
			this.btnRemoverJueves.addActionListener(opQuitar);
			
			this.plantillaJueves.tablaPlantilla.setEnabled(true);
			
		}
		if(this.chViernes.isSelected()){
			this.btnAgregarViernes.setEnabled(true);
			this.btnAgregarViernes.removeActionListener(opAgregarViernes);
			this.btnAgregarViernes.addActionListener(opAgregar);
			
			this.btnSubirViernes.setEnabled(true);
			this.btnSubirViernes.removeActionListener(opViernes);
			this.btnSubirViernes.addActionListener(opMovViernes);
			
			this.btnBajarViernes.setEnabled(true);
			this.btnBajarViernes.removeActionListener(opViernes);
			this.btnBajarViernes.addActionListener(opMovViernes);
			
			this.btnRemoverViernes.setEnabled(true);
			this.btnRemoverViernes.removeActionListener(opQuitarViernes);
			this.btnRemoverViernes.addActionListener(opQuitar);
			
			this.plantillaViernes.tablaPlantilla.setEnabled(true);
		}
		if(this.chSabado.isSelected()){
			this.btnAgregarSabado.setEnabled(true);
			this.btnAgregarSabado.removeActionListener(opAgregarSabado);
			this.btnAgregarSabado.addActionListener(opAgregar);
			
			this.btnSubirSabado.setEnabled(true);
			this.btnSubirSabado.removeActionListener(opSabado);
			this.btnSubirSabado.addActionListener(opMovSabado);
			
			this.btnBajarSabado.setEnabled(true);
			this.btnBajarSabado.removeActionListener(opSabado);
			this.btnBajarSabado.addActionListener(opMovSabado);
			
			this.btnRemoverSabado.setEnabled(true);
			this.btnRemoverSabado.removeActionListener(opQuitarSabado);
			this.btnRemoverSabado.addActionListener(opQuitar);
			
			this.plantillaSabado.tablaPlantilla.setEnabled(true);
			
		}
		
		//this.addWindowListener(op_cerrar);
		this.removeWindowListener(op_cerrar);
		this.addWindowListener(op_cerra_real);
	}
	
	WindowListener op_cerra_real = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			if(ValidaError().equals("")){
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
					guardarReal();
				}
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
	};
	
	ActionListener opActividadNueva = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Cat_Actividad_Asignadas_Nivel_Jerarquico().setVisible(true);
		}
	};
		
	ActionListener opGuardarReal = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			guardarReal();
		}
	};
	
	public void guardarReal(){
		if(plantillaDomingo.tablaPlantilla.isEditing())
			plantillaDomingo.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaLunes.tablaPlantilla.isEditing())
			plantillaLunes.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaMartes.tablaPlantilla.isEditing())
			plantillaMartes.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaMiercoles.tablaPlantilla.isEditing())
			plantillaMiercoles.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaJueves.tablaPlantilla.isEditing())
			plantillaJueves.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaViernes.tablaPlantilla.isEditing())
			plantillaViernes.tablaPlantilla.getCellEditor().stopCellEditing();
		if(plantillaSabado.tablaPlantilla.isEditing())
			plantillaSabado.tablaPlantilla.getCellEditor().stopCellEditing();
			
		if(new Obj_Cuadrante_Actividades().guardar(FOLIO, Integer.valueOf(txtFolio.getText()) , NOMBREENCARGADO, DiasTablaReal())){
			JOptionPane.showMessageDialog(null,"El registro se guardó correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else{
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	public String[][] DiasTablaReal(){
		int filas = plantillaDomingo.tablaPlantilla.getRowCount()+plantillaLunes.tablaPlantilla.getRowCount()+
					plantillaMartes.tablaPlantilla.getRowCount()+plantillaMiercoles.tablaPlantilla.getRowCount()+
					plantillaJueves.tablaPlantilla.getRowCount()+plantillaViernes.tablaPlantilla.getRowCount()+
					plantillaSabado.tablaPlantilla.getRowCount();
		
		String[][] tablas = new String[filas][7];
		
		int renglonesdomingo = plantillaDomingo.tablaPlantilla.getRowCount();
		int rengloneslunes = plantillaLunes.tablaPlantilla.getRowCount();
		int renglonesMartes = plantillaMartes.tablaPlantilla.getRowCount();
		int renglonesMiercoles = plantillaMiercoles.tablaPlantilla.getRowCount();
		int renglonesJueves = plantillaJueves.tablaPlantilla.getRowCount();
		int renglonesViernes = plantillaViernes.tablaPlantilla.getRowCount();
		int renglonesSabado = plantillaSabado.tablaPlantilla.getRowCount();
		
		int fila=0;
		int i=0;
		
		while(renglonesdomingo > 0){
				tablas[i][0] = plantillaDomingo.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaDomingo.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaDomingo.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaDomingo.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaDomingo.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaDomingo.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Domingo";
				i+=1;
				fila+=1;
			renglonesdomingo--;
		}
		fila=0;
		while(rengloneslunes > 0){
				tablas[i][0] = plantillaLunes.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaLunes.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaLunes.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaLunes.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaLunes.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaLunes.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Lunes";
				i+=1;
				fila+=1;
				rengloneslunes--;
		}
		
		fila=0;
		while(renglonesMartes > 0){
				tablas[i][0] = plantillaMartes.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaMartes.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaMartes.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaMartes.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaMartes.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaMartes.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Martes";
				i+=1;
				fila+=1;
				renglonesMartes--;
		}
		
		fila=0;
		while(renglonesMiercoles > 0){
				tablas[i][0] = plantillaMiercoles.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaMiercoles.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaMiercoles.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaMiercoles.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaMiercoles.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaMiercoles.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Miércoles";
				i+=1;
				fila+=1;
				renglonesMiercoles--;
		}
		
		fila=0;
		while(renglonesJueves > 0){
				tablas[i][0] = plantillaJueves.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaJueves.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaJueves.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaJueves.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaJueves.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaJueves.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Jueves";
				i+=1;
				fila+=1;
				renglonesJueves--;
		}
		
		fila=0;
		while(renglonesViernes > 0){
				tablas[i][0] = plantillaViernes.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaViernes.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaViernes.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaViernes.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaViernes.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaViernes.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Viernes";
				i+=1;
				fila+=1;
				renglonesViernes--;
		}
		
		fila=0;
		while(renglonesSabado > 0){
				tablas[i][0] = plantillaSabado.modelPantilla.getValueAt(fila, 0)+"";
				tablas[i][1] = plantillaSabado.modelPantilla.getValueAt(fila, 1)+"";
				tablas[i][2] = plantillaSabado.modelPantilla.getValueAt(fila, 2)+"";
				tablas[i][3] = plantillaSabado.modelPantilla.getValueAt(fila, 3)+"";
				tablas[i][4] = plantillaSabado.modelPantilla.getValueAt(fila, 4)+"";
				tablas[i][5] = plantillaSabado.modelPantilla.getValueAt(fila, 5)+"";
				tablas[i][6] = "Sábado";
				i+=1;
				fila+=1;
				renglonesSabado--;
		}
		
		return tablas;
	}
	
	ActionListener opDesahacerReal = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			limpiarTablasActividades();
			BuscarActividades();
		}
	};
	
	ActionListener opAgregar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == btnAgregarDomingo)
				new Cat_Filtro_Actividades_Act("Domingo").setVisible(true);
			if(arg0.getSource() == btnAgregarLunes)
				new Cat_Filtro_Actividades_Act("Lunes").setVisible(true);
			if(arg0.getSource() == btnAgregarMartes)
				new Cat_Filtro_Actividades_Act("Martes").setVisible(true);	
			if(arg0.getSource() == btnAgregarMiercoles)
				new Cat_Filtro_Actividades_Act("Miércoles").setVisible(true);
			if(arg0.getSource() == btnAgregarJueves)
				new Cat_Filtro_Actividades_Act("Jueves").setVisible(true);
			if(arg0.getSource() == btnAgregarViernes)
				new Cat_Filtro_Actividades_Act("Viernes").setVisible(true);
			if(arg0.getSource() == btnAgregarSabado)
				new Cat_Filtro_Actividades_Act("Sábado").setVisible(true);
				
		}
	};	
	
	public void BuscarActividades(){
		limpiarTablasActividades();
		try {
			String[][] tabla = new Obj_Cuadrante_Actividades().tabla(FOLIO);
			for(int i=0; i<tabla.length; i++){
				Dias currentDia = Dias.valueOf(tabla[i][0]);
				 switch (currentDia) {
				 	case Domingo:
				 		Object[] dom = new Object[6];
				 		dom[0] = tabla[i][1];
				 		dom[1] = tabla[i][2];
				 		dom[2] = tabla[i][3];
				 		dom[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		dom[4] = tabla[i][5];
				 		dom[5] = tabla[i][6];
				 		plantillaDomingo.modelPantilla.addRow(dom);
				 		 break;
				 	case Lunes:
				 		Object[] lun = new Object[6];
				 		lun[0] = tabla[i][1];
				 		lun[1] = tabla[i][2];
				 		lun[2] = tabla[i][3];
				 		lun[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		lun[4] = tabla[i][5];
				 		lun[5] = tabla[i][6];
				 		plantillaLunes.modelPantilla.addRow(lun);
				 		 break;
				 	case Martes:
				 		Object[] mar = new Object[6];
				 		mar[0] = tabla[i][1];
				 		mar[1] = tabla[i][2];
				 		mar[2] = tabla[i][3];
				 		mar[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		mar[4] = tabla[i][5];
				 		mar[5] = tabla[i][6];
				 		plantillaMartes.modelPantilla.addRow(mar);
				 		 break;
				 	case Miércoles:
				 		Object[] mie = new Object[6];
				 		mie[0] = tabla[i][1];
				 		mie[1] = tabla[i][2];
				 		mie[2] = tabla[i][3];
				 		mie[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		mie[4] = tabla[i][5];
				 		mie[5] = tabla[i][6];
				 		plantillaMiercoles.modelPantilla.addRow(mie);
				 		 break;
		            case Jueves:
		            	Object[] jue = new Object[6];
		            	jue[0] = tabla[i][1];
		            	jue[1] = tabla[i][2];
		            	jue[2] = tabla[i][3];
		            	jue[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	jue[4] = tabla[i][5];
		            	jue[5] = tabla[i][6];
		            	plantillaJueves.modelPantilla.addRow(jue);
		            	 break;
		            case Viernes:
		            	Object[] vie = new Object[6];
		            	vie[0] = tabla[i][1];
		            	vie[1] = tabla[i][2];
		            	vie[2] = tabla[i][3];
		            	vie[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	vie[4] = tabla[i][5];
		            	vie[5] = tabla[i][6];
		            	plantillaViernes.modelPantilla.addRow(vie);
		            	 break;
		            case Sábado:
		            	Object[] sab = new Object[6];
		            	sab[0] = tabla[i][1];
		            	sab[1] = tabla[i][2];
		            	sab[2] = tabla[i][3];
		            	sab[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	sab[4] = tabla[i][5];
		            	sab[5] = tabla[i][6];
		            	plantillaSabado.modelPantilla.addRow(sab);
		            	 break;
				 }
			}
			Orden_de_Actividades();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	private int getCuadrante(){
 		int cuadrante = 0;
 		String query = "exec sp_get_folio_cuadrante_empleado "+FOLIO;
 		try{
 			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next()){
				cuadrante = rs.getInt(1);
			}
 		}catch(Exception e){
 			System.err.println("Exception " +e.getMessage());
 		}
 		return cuadrante;
 	}
	
	private void limpiarTablasActividades(){
		plantillaDomingo.limpiarPlatilla();
		plantillaLunes.limpiarPlatilla();
		plantillaMartes.limpiarPlatilla();
		plantillaMiercoles.limpiarPlatilla();
		plantillaJueves.limpiarPlatilla();
		plantillaViernes.limpiarPlatilla();
		plantillaSabado.limpiarPlatilla();
	}
	
	ActionListener opQuitar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == btnRemoverDomingo){
				if(plantillaDomingo.tablaPlantilla.getRowCount()>0){
					if(plantillaDomingo.tablaPlantilla.isRowSelected(plantillaDomingo.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaDomingo.modelPantilla.getValueAt(plantillaDomingo.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 1);
						plantillaDomingo.modelPantilla.removeRow(plantillaDomingo.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaDomingo.tablaPlantilla.getRowCount(); domingo++){
							plantillaDomingo.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverLunes){
				if(plantillaLunes.tablaPlantilla.getRowCount()>0){
					if(plantillaLunes.tablaPlantilla.isRowSelected(plantillaLunes.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaLunes.modelPantilla.getValueAt(plantillaLunes.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 2);
						plantillaLunes.modelPantilla.removeRow(plantillaLunes.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaLunes.tablaPlantilla.getRowCount(); domingo++){
							plantillaLunes.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverMartes){
				if(plantillaMartes.tablaPlantilla.getRowCount()>0){
					if(plantillaMartes.tablaPlantilla.isRowSelected(plantillaMartes.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaMartes.modelPantilla.getValueAt(plantillaMartes.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 3);
						plantillaMartes.modelPantilla.removeRow(plantillaMartes.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaMartes.tablaPlantilla.getRowCount(); domingo++){
							plantillaMartes.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverMiercoles){
				if(plantillaMiercoles.tablaPlantilla.getRowCount()>0){
					if(plantillaMiercoles.tablaPlantilla.isRowSelected(plantillaMiercoles.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaMiercoles.modelPantilla.getValueAt(plantillaMiercoles.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 4);
						plantillaMiercoles.modelPantilla.removeRow(plantillaMiercoles.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaMiercoles.tablaPlantilla.getRowCount(); domingo++){
							plantillaMiercoles.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverJueves){
				if(plantillaJueves.tablaPlantilla.getRowCount()>0){
					if(plantillaJueves.tablaPlantilla.isRowSelected(plantillaJueves.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaJueves.modelPantilla.getValueAt(plantillaJueves.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 5);
						plantillaJueves.modelPantilla.removeRow(plantillaJueves.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaJueves.tablaPlantilla.getRowCount(); domingo++){
							plantillaJueves.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverViernes){
				if(plantillaViernes.tablaPlantilla.getRowCount()>0){
					if(plantillaViernes.tablaPlantilla.isRowSelected(plantillaViernes.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaViernes.modelPantilla.getValueAt(plantillaViernes.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 6);
						plantillaViernes.modelPantilla.removeRow(plantillaViernes.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaViernes.tablaPlantilla.getRowCount(); domingo++){
							plantillaViernes.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
			if(arg0.getSource() == btnRemoverSabado){
				if(plantillaSabado.tablaPlantilla.getRowCount()>0){
					if(plantillaSabado.tablaPlantilla.isRowSelected(plantillaSabado.tablaPlantilla.getSelectedRow())){
//						new Obj_Cuadrante_Actividades().delete(FOLIO, Integer.parseInt(plantillaSabado.modelPantilla.getValueAt(plantillaSabado.tablaPlantilla.getSelectedRow(), 0).toString().trim()), 7);
						plantillaSabado.modelPantilla.removeRow(plantillaSabado.tablaPlantilla.getSelectedRow());
						for(int domingo = 0; domingo<plantillaSabado.tablaPlantilla.getRowCount(); domingo++){
							plantillaSabado.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
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
		}
	};
	
	ActionListener opMovDomingo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaDomingo.tablaPlantilla.getRowCount()>1){
				if(plantillaDomingo.tablaPlantilla.isRowSelected(plantillaDomingo.tablaPlantilla.getSelectedRow())){
					if(plantillaDomingo.tablaPlantilla.getSelectedRow() == 0 || plantillaDomingo.tablaPlantilla.getSelectedRow() == plantillaDomingo.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirDomingo)){
							if(plantillaDomingo.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaDomingo.modelPantilla.getValueAt(plantillaDomingo.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaDomingo.modelPantilla.getValueAt(plantillaDomingo.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaDomingo.modelPantilla.setValueAt(primero,plantillaDomingo.tablaPlantilla.getSelectedRow()-1,1);
								plantillaDomingo.modelPantilla.setValueAt(segundo,plantillaDomingo.tablaPlantilla.getSelectedRow(),1);	
								plantillaDomingo.tablaPlantilla.setRowSelectionInterval(plantillaDomingo.tablaPlantilla.getSelectedRow()-1,plantillaDomingo.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						
						if(arg0.getSource().equals(btnBajarDomingo)){
							if(plantillaDomingo.tablaPlantilla.getSelectedRow()+1 < plantillaDomingo.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaDomingo.modelPantilla.getValueAt(plantillaDomingo.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaDomingo.modelPantilla.getValueAt(plantillaDomingo.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaDomingo.modelPantilla.setValueAt(primero,plantillaDomingo.tablaPlantilla.getSelectedRow()+1,1);
								plantillaDomingo.modelPantilla.setValueAt(segundo,plantillaDomingo.tablaPlantilla.getSelectedRow(),1);	
								plantillaDomingo.tablaPlantilla.setRowSelectionInterval(plantillaDomingo.tablaPlantilla.getSelectedRow()+1,plantillaDomingo.tablaPlantilla.getSelectedRow()+1);
								
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int domingo = 0; domingo<plantillaDomingo.tablaPlantilla.getRowCount(); domingo++){
							plantillaDomingo.tablaPlantilla.setValueAt(domingo+1+"  ", domingo, 0);
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
	
	ActionListener opMovLunes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaLunes.tablaPlantilla.getRowCount()>1){
				if(plantillaLunes.tablaPlantilla.isRowSelected(plantillaLunes.tablaPlantilla.getSelectedRow())){
					if(plantillaLunes.tablaPlantilla.getSelectedRow() == 0 || plantillaLunes.tablaPlantilla.getSelectedRow() == plantillaLunes.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirLunes)){
							if(plantillaLunes.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaLunes.modelPantilla.getValueAt(plantillaLunes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaLunes.modelPantilla.getValueAt(plantillaLunes.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaLunes.modelPantilla.setValueAt(primero,plantillaLunes.tablaPlantilla.getSelectedRow()-1,1);
								plantillaLunes.modelPantilla.setValueAt(segundo,plantillaLunes.tablaPlantilla.getSelectedRow(),1);	
								plantillaLunes.tablaPlantilla.setRowSelectionInterval(plantillaLunes.tablaPlantilla.getSelectedRow()-1,plantillaLunes.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarLunes)){
							if(plantillaLunes.tablaPlantilla.getSelectedRow()+1 < plantillaLunes.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaLunes.modelPantilla.getValueAt(plantillaLunes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaLunes.modelPantilla.getValueAt(plantillaLunes.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaLunes.modelPantilla.setValueAt(primero,plantillaLunes.tablaPlantilla.getSelectedRow()+1,1);
								plantillaLunes.modelPantilla.setValueAt(segundo,plantillaLunes.tablaPlantilla.getSelectedRow(),1);	
								plantillaLunes.tablaPlantilla.setRowSelectionInterval(plantillaLunes.tablaPlantilla.getSelectedRow()+1,plantillaLunes.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int lunes = 0; lunes<plantillaLunes.tablaPlantilla.getRowCount(); lunes++){
							plantillaLunes.tablaPlantilla.setValueAt(lunes+1+"  ", lunes, 0);
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
	
	ActionListener opMovMartes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaMartes.tablaPlantilla.getRowCount()>1){
				if(plantillaMartes.tablaPlantilla.isRowSelected(plantillaMartes.tablaPlantilla.getSelectedRow())){
					if(plantillaMartes.tablaPlantilla.getSelectedRow() == 0 || plantillaMartes.tablaPlantilla.getSelectedRow() == plantillaMartes.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirMartes)){
							if(plantillaMartes.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaMartes.modelPantilla.getValueAt(plantillaMartes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaMartes.modelPantilla.getValueAt(plantillaMartes.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaMartes.modelPantilla.setValueAt(primero,plantillaMartes.tablaPlantilla.getSelectedRow()-1,1);
								plantillaMartes.modelPantilla.setValueAt(segundo,plantillaMartes.tablaPlantilla.getSelectedRow(),1);	
								plantillaMartes.tablaPlantilla.setRowSelectionInterval(plantillaMartes.tablaPlantilla.getSelectedRow()-1,plantillaMartes.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarMartes)){
							if(plantillaMartes.tablaPlantilla.getSelectedRow()+1 < plantillaMartes.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaMartes.modelPantilla.getValueAt(plantillaMartes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaMartes.modelPantilla.getValueAt(plantillaMartes.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaMartes.modelPantilla.setValueAt(primero,plantillaMartes.tablaPlantilla.getSelectedRow()+1,1);
								plantillaMartes.modelPantilla.setValueAt(segundo,plantillaMartes.tablaPlantilla.getSelectedRow(),1);	
								plantillaMartes.tablaPlantilla.setRowSelectionInterval(plantillaMartes.tablaPlantilla.getSelectedRow()+1,plantillaMartes.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int martes = 0; martes<plantillaMartes.tablaPlantilla.getRowCount(); martes++){
							plantillaMartes.tablaPlantilla.setValueAt(martes+1+"  ", martes, 0);
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
	
	ActionListener opMovMiercoles = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaMiercoles.tablaPlantilla.getRowCount()>1){
				if(plantillaMiercoles.tablaPlantilla.isRowSelected(plantillaMiercoles.tablaPlantilla.getSelectedRow())){
					if(plantillaMiercoles.tablaPlantilla.getSelectedRow() == 0 || plantillaMiercoles.tablaPlantilla.getSelectedRow() == plantillaMiercoles.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirMiercoles)){
							if(plantillaMiercoles.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaMiercoles.modelPantilla.getValueAt(plantillaMiercoles.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaMiercoles.modelPantilla.getValueAt(plantillaMiercoles.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaMiercoles.modelPantilla.setValueAt(primero,plantillaMiercoles.tablaPlantilla.getSelectedRow()-1,1);
								plantillaMiercoles.modelPantilla.setValueAt(segundo,plantillaMiercoles.tablaPlantilla.getSelectedRow(),1);	
								plantillaMiercoles.tablaPlantilla.setRowSelectionInterval(plantillaMiercoles.tablaPlantilla.getSelectedRow()-1,plantillaMiercoles.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarMiercoles)){
							if(plantillaMiercoles.tablaPlantilla.getSelectedRow()+1 < plantillaMiercoles.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaMiercoles.modelPantilla.getValueAt(plantillaMiercoles.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaMiercoles.modelPantilla.getValueAt(plantillaMiercoles.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaMiercoles.modelPantilla.setValueAt(primero,plantillaMiercoles.tablaPlantilla.getSelectedRow()+1,1);
								plantillaMiercoles.modelPantilla.setValueAt(segundo,plantillaMiercoles.tablaPlantilla.getSelectedRow(),1);	
								plantillaMiercoles.tablaPlantilla.setRowSelectionInterval(plantillaMiercoles.tablaPlantilla.getSelectedRow()+1,plantillaMiercoles.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int miercoles = 0; miercoles<plantillaMiercoles.tablaPlantilla.getRowCount(); miercoles++){
							plantillaMiercoles.tablaPlantilla.setValueAt(miercoles+1+"  ", miercoles, 0);
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
	
	ActionListener opMovJueves = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaJueves.tablaPlantilla.getRowCount()>1){
				if(plantillaJueves.tablaPlantilla.isRowSelected(plantillaJueves.tablaPlantilla.getSelectedRow())){
					if(plantillaJueves.tablaPlantilla.getSelectedRow() == 0 || plantillaJueves.tablaPlantilla.getSelectedRow() == plantillaJueves.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirJueves)){
							if(plantillaJueves.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaJueves.modelPantilla.getValueAt(plantillaJueves.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaJueves.modelPantilla.getValueAt(plantillaJueves.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaJueves.modelPantilla.setValueAt(primero,plantillaJueves.tablaPlantilla.getSelectedRow()-1,1);
								plantillaJueves.modelPantilla.setValueAt(segundo,plantillaJueves.tablaPlantilla.getSelectedRow(),1);	
								plantillaJueves.tablaPlantilla.setRowSelectionInterval(plantillaJueves.tablaPlantilla.getSelectedRow()-1,plantillaJueves.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarJueves)){
							if(plantillaJueves.tablaPlantilla.getSelectedRow()+1 < plantillaJueves.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaJueves.modelPantilla.getValueAt(plantillaJueves.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaJueves.modelPantilla.getValueAt(plantillaJueves.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaJueves.modelPantilla.setValueAt(primero,plantillaJueves.tablaPlantilla.getSelectedRow()+1,1);
								plantillaJueves.modelPantilla.setValueAt(segundo,plantillaJueves.tablaPlantilla.getSelectedRow(),1);	
								plantillaJueves.tablaPlantilla.setRowSelectionInterval(plantillaJueves.tablaPlantilla.getSelectedRow()+1,plantillaJueves.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int jueves = 0; jueves<plantillaJueves.tablaPlantilla.getRowCount(); jueves++){
							plantillaJueves.tablaPlantilla.setValueAt(jueves+1+"  ", jueves, 0);
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
	
	ActionListener opMovViernes = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaViernes.tablaPlantilla.getRowCount()>1){
				if(plantillaViernes.tablaPlantilla.isRowSelected(plantillaViernes.tablaPlantilla.getSelectedRow())){
					if(plantillaViernes.tablaPlantilla.getSelectedRow() == 0 || plantillaViernes.tablaPlantilla.getSelectedRow() == plantillaViernes.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirViernes)){
							if(plantillaViernes.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaViernes.modelPantilla.getValueAt(plantillaViernes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaViernes.modelPantilla.getValueAt(plantillaViernes.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaViernes.modelPantilla.setValueAt(primero,plantillaViernes.tablaPlantilla.getSelectedRow()-1,1);
								plantillaViernes.modelPantilla.setValueAt(segundo,plantillaViernes.tablaPlantilla.getSelectedRow(),1);	
								plantillaViernes.tablaPlantilla.setRowSelectionInterval(plantillaViernes.tablaPlantilla.getSelectedRow()-1,plantillaViernes.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarViernes)){
							if(plantillaViernes.tablaPlantilla.getSelectedRow()+1 < plantillaViernes.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaViernes.modelPantilla.getValueAt(plantillaViernes.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaViernes.modelPantilla.getValueAt(plantillaViernes.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaViernes.modelPantilla.setValueAt(primero,plantillaViernes.tablaPlantilla.getSelectedRow()+1,1);
								plantillaViernes.modelPantilla.setValueAt(segundo,plantillaViernes.tablaPlantilla.getSelectedRow(),1);	
								plantillaViernes.tablaPlantilla.setRowSelectionInterval(plantillaViernes.tablaPlantilla.getSelectedRow()+1,plantillaViernes.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int viernes = 0; viernes<plantillaViernes.tablaPlantilla.getRowCount(); viernes++){
							plantillaViernes.tablaPlantilla.setValueAt(viernes+1+"  ", viernes, 0);
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
	
	ActionListener opMovSabado = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(plantillaSabado.tablaPlantilla.getRowCount()>1){
				if(plantillaSabado.tablaPlantilla.isRowSelected(plantillaSabado.tablaPlantilla.getSelectedRow())){
					if(plantillaSabado.tablaPlantilla.getSelectedRow() == 0 || plantillaSabado.tablaPlantilla.getSelectedRow() == plantillaSabado.tablaPlantilla.getRowCount()-1){
						JOptionPane.showMessageDialog(null,"Esta fila no se puede desplazar","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						if(arg0.getSource().equals(btnSubirSabado)){
							if(plantillaSabado.tablaPlantilla.getSelectedRow() > 1){
								Object primero = plantillaSabado.modelPantilla.getValueAt(plantillaSabado.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaSabado.modelPantilla.getValueAt(plantillaSabado.tablaPlantilla.getSelectedRow()-1,1);
								
								plantillaSabado.modelPantilla.setValueAt(primero,plantillaSabado.tablaPlantilla.getSelectedRow()-1,1);
								plantillaSabado.modelPantilla.setValueAt(segundo,plantillaSabado.tablaPlantilla.getSelectedRow(),1);	
								plantillaSabado.tablaPlantilla.setRowSelectionInterval(plantillaSabado.tablaPlantilla.getSelectedRow()-1,plantillaSabado.tablaPlantilla.getSelectedRow()-1);
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia arriba!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
									
						}
						if(arg0.getSource().equals(btnBajarSabado)){
							if(plantillaSabado.tablaPlantilla.getSelectedRow()+1 < plantillaSabado.tablaPlantilla.getRowCount()-1){
								Object primero = plantillaSabado.modelPantilla.getValueAt(plantillaSabado.tablaPlantilla.getSelectedRow(),1);
								Object segundo = plantillaSabado.modelPantilla.getValueAt(plantillaSabado.tablaPlantilla.getSelectedRow()+1,1);
								
								plantillaSabado.modelPantilla.setValueAt(primero,plantillaSabado.tablaPlantilla.getSelectedRow()+1,1);
								plantillaSabado.modelPantilla.setValueAt(segundo,plantillaSabado.tablaPlantilla.getSelectedRow(),1);	
								plantillaSabado.tablaPlantilla.setRowSelectionInterval(plantillaSabado.tablaPlantilla.getSelectedRow()+1,plantillaSabado.tablaPlantilla.getSelectedRow()+1);
							
							}else{
								JOptionPane.showMessageDialog(null,"No más filas hacia abajo!","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
						for(int sabado = 0; sabado<plantillaSabado.tablaPlantilla.getRowCount(); sabado++){
							plantillaSabado.tablaPlantilla.setValueAt(sabado+1+"  ", sabado, 0);
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
	
	public void BuscarActividadesCuadrante(){
		tabla_limpiar();
		Obj_Cuadrante cuadrante = new Obj_Cuadrante().buscarCuadrante(getCuadrante());
		
		try {
			String[][] tabla = new Obj_Cuadrante().tabla(getCuadrante());
			for(int i=0; i<tabla.length; i++){
				Dias currentDia = Dias.valueOf(tabla[i][0]);
				
				 switch (currentDia) {
				 	case Domingo:
				 		Object[] dom = new Object[6];
				 		dom[0] = tabla[i][1];
				 		dom[1] = tabla[i][2];
				 		dom[2] = tabla[i][3];
				 		dom[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		dom[4] = tabla[i][5];
				 		dom[5] = tabla[i][6];
				 		modelDomingo.addRow(dom);
				 		 break;
				 	case Lunes:
				 		Object[] lun = new Object[6];
				 		lun[0] = tabla[i][1];
				 		lun[1] = tabla[i][2];
				 		lun[2] = tabla[i][3];
				 		lun[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		lun[4] = tabla[i][5];
				 		lun[5] = tabla[i][6];
				 		modelLunes.addRow(lun);
				 		 break;
				 	case Martes:
				 		Object[] mar = new Object[6];
				 		mar[0] = tabla[i][1];
				 		mar[1] = tabla[i][2];
				 		mar[2] = tabla[i][3];
				 		mar[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		mar[4] = tabla[i][5];
				 		mar[5] = tabla[i][6];
				 		modelMartes.addRow(mar);
				 		 break;
				 	case Miércoles:
				 		Object[] mie = new Object[6];
				 		mie[0] = tabla[i][1];
				 		mie[1] = tabla[i][2];
				 		mie[2] = tabla[i][3];
				 		mie[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
				 		mie[4] = tabla[i][5];
				 		mie[5] = tabla[i][6];
				 		modelMiercoles.addRow(mie);
				 		 break;
		            case Jueves:
		            	Object[] jue = new Object[6];
		            	jue[0] = tabla[i][1];
		            	jue[1] = tabla[i][2];
		            	jue[2] = tabla[i][3];
		            	jue[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	jue[4] = tabla[i][5];
		            	jue[5] = tabla[i][6];
				 		modelJueves.addRow(jue);
		            	 break;
		            case Viernes:
		            	Object[] vie = new Object[6];
		            	vie[0] = tabla[i][1];
		            	vie[1] = tabla[i][2];
		            	vie[2] = tabla[i][3];
		            	vie[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	vie[4] = tabla[i][5];
		            	vie[5] = tabla[i][6];
				 		modelViernes.addRow(vie);
		            	 break;
		            case Sábado:
		            	Object[] sab = new Object[6];
		            	sab[0] = tabla[i][1];
		            	sab[1] = tabla[i][2];
		            	sab[2] = tabla[i][3];
		            	sab[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
		            	sab[4] = tabla[i][5];
		            	sab[5] = tabla[i][6];
				 		modelSabado.addRow(sab);
		            	 break;
				 }
				
			}
			
			txtFolio.setText(String.valueOf(getCuadrante()));
			txtCuadrante.setText(cuadrante.getCuadrante());
			txaDescripcion.setText(cuadrante.getPerfil());
			cmbJefatura.setSelectedItem(cuadrante.getJefatura());
			cmbnivel_jerarquico.setSelectedItem(cuadrante.getNivel_gerarquico());
			cmbEquipo_Trabajo.setSelectedItem(cuadrante.getEquipo_trabajo());
			cmbEstablecimiento.setSelectedItem(cuadrante.getEstablecimiento());
			chDomingo.setSelected(cuadrante.getDomingo() == 1 ? true : false);
			chLunes.setSelected(cuadrante.getLunes() == 1 ? true : false);
			chMartes.setSelected(cuadrante.getMartes() == 1 ? true : false);
			chMiercoles.setSelected(cuadrante.getMiercoles() == 1 ? true : false);
			chJueves.setSelected(cuadrante.getJueves() == 1 ? true : false);
			chViernes.setSelected(cuadrante.getViernes() == 1 ? true : false);
			chSabado.setSelected(cuadrante.getSabado() == 1 ? true : true);
			chbStatus.setSelected(cuadrante.getStatus() == 1 ? true : false);
			
			Orden_de_Actividades();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public class Cat_Filtro_Actividades_Act extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		String dia = "";
		
		Object[][] MatrizFiltro ;
		
		Object[][] getTablaFiltro = getTablaFiltro();
		DefaultTableModel modeloPlantillaFiltro = new DefaultTableModel(getTablaFiltro,
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
		
		JTable tablaFiltro = new JTable(modeloPlantillaFiltro);
	    JScrollPane scroll = new JScrollPane(tablaFiltro);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		JButton btnAgregar = new JButton("Agregar");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public Cat_Filtro_Actividades_Act(String Dia) {
			setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			setTitle("Filtro de Actividades");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Actividades"));
			trsfiltro = new TableRowSorter(modeloPlantillaFiltro); 
			tablaFiltro.setRowSorter(trsfiltro);  
			
			dia = Dia;
			
			campo.add(scroll).setBounds(15,43,994,360);
			
			campo.add(txtFolio).setBounds(15,20,40,20);
			campo.add(txtNombre_Completo).setBounds(56,20,800,20);
			campo.add(btnAgregar).setBounds(930,20,80,20);
			
			cont.add(campo);
			
			tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(40);
			tablaFiltro.getColumnModel().getColumn(0).setMinWidth(40);
			tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(800);
			tablaFiltro.getColumnModel().getColumn(1).setMinWidth(800);
			tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(40);
			tablaFiltro.getColumnModel().getColumn(3).setMinWidth(40);
			
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
							if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(row,3).toString())){
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
							if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(row,3).toString())){
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
			
			setSize(1024,450);
			setResizable(false);
			setLocationRelativeTo(null);
			setModal(true);
			
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
				 		if(plantillaDomingo.modelPantilla.getRowCount()>0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaDom[0] = posicion;
 									filaDom[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaDom[2] = modeloPlantillaFiltro.getValueAt(i, 2);
     								filaDom[3] = Boolean.parseBoolean("false");
 									filaDom[4] = "00:00";
 									filaDom[5] = "00:00";
 									
				 					for(int j=0; j<plantillaDomingo.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaDomingo.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaDomingo.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La .: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									
				 									plantillaDomingo.modelPantilla.addRow(filaDom);
				 									
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaDomingo.modelPantilla.getRowCount() && repetido == 0){
				 								plantillaDomingo.modelPantilla.addRow(filaDom);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaDomingo.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaDomingo.modelPantilla.addRow(filaDom);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaDomingo.modelPantilla.getRowCount() && repetido == 0){
				 								plantillaDomingo.modelPantilla.addRow(filaDom);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaDom[0] = posicion;
				 					filaDom[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaDom[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaDom[3] = Boolean.parseBoolean("false");
				 					filaDom[4] = "00:00";
				 					filaDom[5] = "00:00";
						 			
				 					plantillaDomingo.modelPantilla.addRow(filaDom);
				 				}
				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Lunes:
				 		Object[] filaLun = new Object[6];
				 		if(plantillaLunes.modelPantilla.getRowCount() > 0){
				 							 			
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaLun[0] = posicion;
 									filaLun[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaLun[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaLun[3] = Boolean.parseBoolean("false");
 									filaLun[4] = "00:00";
 									filaLun[5] = "00:00";
				 					for(int j=0; j<plantillaLunes.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaLunes.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaLunes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaLunes.modelPantilla.addRow(filaLun);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaLunes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaLunes.modelPantilla.addRow(filaLun);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaLunes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaLunes.modelPantilla.addRow(filaLun);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaLunes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaLunes.modelPantilla.addRow(filaLun);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaLun[0] = posicion;
				 					filaLun[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaLun[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaLun[3] = Boolean.parseBoolean("false");
				 					filaLun[4] = "00:00";
				 					filaLun[5] = "00:00";
						 			
						 			plantillaLunes.modelPantilla.addRow(filaLun);
				 				}
				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Martes:
				 		Object[] filaMar = new Object[6];
				 		if(plantillaMartes.modelPantilla.getRowCount() > 0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaMar[0] = posicion;
 									filaMar[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaMar[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaMar[3] = Boolean.parseBoolean("false");
 									filaMar[4] = "00:00";
 									filaMar[5] = "00:00";
				 					for(int j=0; j<plantillaMartes.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaMartes.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaMartes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaMartes.modelPantilla.addRow(filaMar);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaMartes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaMartes.modelPantilla.addRow(filaMar);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaMartes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaMartes.modelPantilla.addRow(filaMar);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaMartes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaMartes.modelPantilla.addRow(filaMar);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaMar[0] = posicion;
				 					filaMar[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaMar[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaMar[3] = Boolean.parseBoolean("false");
				 					filaMar[4] = "00:00";
				 					filaMar[5] = "00:00";
						 			
						 			plantillaMartes.modelPantilla.addRow(filaMar);
				 				}

				 			}
				 		}
				 		dispose();
				 		 break;
				 	case Miércoles:
				 		Object[] filaMie = new Object[6];
				 		if(plantillaMiercoles.modelPantilla.getRowCount() > 0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaMie[0] = posicion;
 									filaMie[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaMie[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaMie[3] = Boolean.parseBoolean("false");
 									filaMie[4] = "00:00";
 									filaMie[5] = "00:00";
				 					for(int j=0; j<plantillaMiercoles.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaMiercoles.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaMiercoles.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaMiercoles.modelPantilla.addRow(filaMie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaMiercoles.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaMiercoles.modelPantilla.addRow(filaMie);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaMiercoles.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaMiercoles.modelPantilla.addRow(filaMie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaMiercoles.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaMiercoles.modelPantilla.addRow(filaMie);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaMie[0] = posicion;
				 					filaMie[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaMie[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaMie[3] = Boolean.parseBoolean("false");
				 					filaMie[4] = "00:00";
				 					filaMie[5] = "00:00";
						 			
						 			plantillaMiercoles.modelPantilla.addRow(filaMie);
				 				}

				 			}
				 		}
				 		dispose();
				 		 break;
		            case Jueves:
		            	Object[] filaJue = new Object[6];
				 		if(plantillaJueves.modelPantilla.getRowCount() > 0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaJue[0] = posicion;
 									filaJue[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaJue[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaJue[3] = Boolean.parseBoolean("false");
 									filaJue[4] = "00:00";
 									filaJue[5] = "00:00";
				 					for(int j=0; j<plantillaJueves.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaJueves.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaJueves.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaJueves.modelPantilla.addRow(filaJue);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaJueves.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaJueves.modelPantilla.addRow(filaJue);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaJueves.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaJueves.modelPantilla.addRow(filaJue);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaJueves.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaJueves.modelPantilla.addRow(filaJue);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaJue[0] = posicion;
				 					filaJue[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaJue[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaJue[3] = Boolean.parseBoolean("false");
				 					filaJue[4] = "00:00";
				 					filaJue[5] = "00:00";
						 			
						 			plantillaJueves.modelPantilla.addRow(filaJue);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
		            case Viernes:
		            	Object[] filaVie = new Object[6];
				 		if(plantillaViernes.modelPantilla.getRowCount() > 0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaVie[0] = posicion;
 									filaVie[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaVie[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaVie[3] = Boolean.parseBoolean("false");
 									filaVie[4] = "00:00";
 									filaVie[5] = "00:00";
				 					for(int j=0; j<plantillaViernes.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaViernes.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaViernes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaViernes.modelPantilla.addRow(filaVie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaViernes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaViernes.modelPantilla.addRow(filaVie);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaViernes.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaViernes.modelPantilla.addRow(filaVie);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaViernes.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaViernes.modelPantilla.addRow(filaVie);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaVie[0] = posicion;
				 					filaVie[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaVie[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaVie[3] = Boolean.parseBoolean("false");
				 					filaVie[4] = "00:00";
				 					filaVie[5] = "00:00";
						 			
						 			plantillaViernes.modelPantilla.addRow(filaVie);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
		            case Sábado:
		            	Object[] filaSab = new Object[6];
				 		if(plantillaSabado.modelPantilla.getRowCount() > 0){
				 			for(int i=0; i<modeloPlantillaFiltro.getRowCount(); i++){
				 				int repetido = 0;
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaSab[0] = posicion;
 									filaSab[1] = modeloPlantillaFiltro.getValueAt(i, 1);
 									filaSab[2] = modeloPlantillaFiltro.getValueAt(i, 2);
 									filaSab[3] = Boolean.parseBoolean("false");
 									filaSab[4] = "00:00";
 									filaSab[5] = "00:00";
				 					for(int j=0; j<plantillaSabado.modelPantilla.getRowCount();){
				 						if(Integer.parseInt(modeloPlantillaFiltro.getValueAt(i,0).toString().trim()) == Integer.parseInt(plantillaSabado.modelPantilla.getValueAt(j,0).toString().trim())){
				 							j++;
				 							repetido++;
				 							if(j==plantillaSabado.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaSabado.modelPantilla.addRow(filaSab);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaSabado.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaSabado.modelPantilla.addRow(filaSab);
			 									j++;
				 							}
				 						}else{
				 							j++;
				 							if(j==plantillaSabado.modelPantilla.getRowCount() && repetido > 0){
				 								if(JOptionPane.showConfirmDialog(null, "La actividad: \n \t" + modeloPlantillaFiltro.getValueAt(i, 1) + " \n ya existe ¿Desea volver a agregar?" ) == 0){
				 									plantillaSabado.modelPantilla.addRow(filaSab);
							 						j++;
				 								}else{
				 									j++;
				 								}
				 							}
				 							if(j==plantillaSabado.modelPantilla.getRowCount() && repetido == 0){
			 									plantillaSabado.modelPantilla.addRow(filaSab);
			 									j++;
				 							}
				 						}
				 					}
				 				}
				 			}
				 		}else{
				 			for(int i=0; i<tablaFiltro.getRowCount(); i++){
				 				if(Boolean.parseBoolean(modeloPlantillaFiltro.getValueAt(i, 3).toString()) == true){
				 					int posicion = Integer.parseInt(modeloPlantillaFiltro.getValueAt(i, 0).toString().trim());
				 					filaSab[0] = posicion;
				 					filaSab[1] = modeloPlantillaFiltro.getValueAt(i, 1);
				 					filaSab[2] = modeloPlantillaFiltro.getValueAt(i, 2);
				 					filaSab[3] = Boolean.parseBoolean("false");
				 					filaSab[4] = "00:00";
				 					filaSab[5] = "00:00";
						 			
						 			plantillaSabado.modelPantilla.addRow(filaSab);
				 				}

				 			}
				 		}
				 		dispose();
		            	 break;
				}
				Orden_de_Actividades();
			}
		};
		
		public void Orden_de_Actividades(){
			// REORDENA DOMINGO
			for(int domingo = 0; domingo<plantillaDomingo.tablaPlantilla.getRowCount(); domingo++){
				plantillaDomingo.tablaPlantilla.setValueAt(domingo+1+"  ", domingo,0);
			}
			// REORDENA LUNES
			for(int lunes = 0; lunes<plantillaLunes.tablaPlantilla.getRowCount(); lunes++){
				plantillaLunes.tablaPlantilla.setValueAt(lunes+1+"  ", lunes, 0);
			}
			// REORDENA MARTES
			for(int martes = 0; martes<plantillaMartes.tablaPlantilla.getRowCount(); martes++){
				plantillaMartes.tablaPlantilla.setValueAt(martes+1+"  ", martes, 0);
			}
			// REORDENA MIERCOLES
			for(int miercoles = 0; miercoles<plantillaMiercoles.tablaPlantilla.getRowCount(); miercoles++){
				plantillaMiercoles.tablaPlantilla.setValueAt(miercoles+1+"  ", miercoles, 0);
			}
			// REORDENA JUEVES
			for(int jueves = 0; jueves<plantillaJueves.tablaPlantilla.getRowCount(); jueves++){
				plantillaJueves.tablaPlantilla.setValueAt(jueves+1+"  ", jueves, 0);
			}
			// REORDENA VIERNES
			for(int viernes = 0; viernes<plantillaViernes.tablaPlantilla.getRowCount(); viernes++){
				plantillaViernes.modelPantilla.setValueAt(viernes+1+"  ", viernes, 0);
			}
			// REORDENA SABADO
			for(int sabado = 0; sabado<plantillaSabado.tablaPlantilla.getRowCount(); sabado++){
				plantillaSabado.tablaPlantilla.setValueAt(sabado+1+"  ", sabado, 0);
			}
		}
		
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
			String todos = "exec sp_filtro_tabla_actidad_cuadrante '"+NOMBREENCARGADO+"'";
			Statement s;
			ResultSet rs;
			
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(todos);
				
				MatrizFiltro = new Object[getFilas(todos)][4];
				int i=0;
				while(rs.next()){
					int folio = rs.getInt(1);
					MatrizFiltro[i][0] = folio+"  ";
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
