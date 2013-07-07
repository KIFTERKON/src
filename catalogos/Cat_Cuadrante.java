package catalogos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import objetos.Obj_Cuadrante;

@SuppressWarnings("serial")
public class Cat_Cuadrante extends Cat_Cuadrante_Base {
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir");
	JButton btnDeshacer = new JButton("Deshacer");
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");
	JButton btnNuevo = new JButton("Nuevo");
	
	public Cat_Cuadrante (){
		int x=35, y=30;
		
		this.panel.add(btnBuscar).setBounds(330,y,32,20);
		this.panel.add(btnNuevo).setBounds(365, y,65, 20);
		
		this.panel.add(btnSalir).setBounds(x, y+=480, 80,20);
		this.panel.add(btnDeshacer).setBounds(x+=105, y, 80, 20);
		this.panel.add(btnEditar).setBounds(x+=105, y, 80, 20);
		this.panel.add(btnGuardar).setBounds(x+=105, y, 80, 20);
		
		this.btnGuardar.addActionListener(opGuardar);
		
	}
	
	ActionListener opGuardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(ValidaError().equals("")){
				if(new Obj_Cuadrante().existe(txtCuadrante.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
						Obj_Cuadrante cuadrante = new Obj_Cuadrante();
						
						cuadrante.setFolio(Integer.parseInt(txtFolio.getText()));
						cuadrante.setCuadrante(txtCuadrante.getText());
						cuadrante.setPerfil(txaDescripcion.getText());
						cuadrante.setJefatura(cmbJefatura.getSelectedItem().toString());
						cuadrante.setNivel_gerarquico(cmbnivel_gerarquico.getSelectedItem().toString());
						cuadrante.setEquipo_trabajo(cmbEquipo_Trabajo.getSelectedItem().toString());
						cuadrante.setEstablecimiento(cmbEstablecimiento.getSelectedItem().toString());
						cuadrante.setDomingo(chDomingo.isSelected() ? 1 : 0);
						cuadrante.setLunes(chLunes.isSelected() ? 1 : 0);
						cuadrante.setMartes(chMartes.isSelected() ? 1 : 0);
						cuadrante.setMiercoles(chMiercoles.isSelected() ? 1 : 0);
						cuadrante.setJueves(chJueves.isSelected() ? 1 : 0);
						cuadrante.setViernes(chViernes.isSelected() ? 1 : 0);
						cuadrante.setSabado(chSabado.isSelected() ? 1 : 0);
						cuadrante.setStatus(chbStatus.isSelected() ? 1 : 0);
						
						if(cuadrante.actualizar(Integer.parseInt(txtFolio.getText()),DiasTablas())){
							JOptionPane.showMessageDialog(null,"El registro se actualizó correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else{
						return;
					}
				}else{
				
					Obj_Cuadrante cuadrante = new Obj_Cuadrante();
					
					cuadrante.setFolio(Integer.parseInt(txtFolio.getText()));
					cuadrante.setCuadrante(txtCuadrante.getText());
					cuadrante.setPerfil(txaDescripcion.getText());
					cuadrante.setJefatura(cmbJefatura.getSelectedItem().toString());
					cuadrante.setNivel_gerarquico(cmbnivel_gerarquico.getSelectedItem().toString());
					cuadrante.setEquipo_trabajo(cmbEquipo_Trabajo.getSelectedItem().toString());
					cuadrante.setEstablecimiento(cmbEstablecimiento.getSelectedItem().toString());
					cuadrante.setDomingo(chDomingo.isSelected() ? 1 : 0);
					cuadrante.setLunes(chLunes.isSelected() ? 1 : 0);
					cuadrante.setMartes(chMartes.isSelected() ? 1 : 0);
					cuadrante.setMiercoles(chMiercoles.isSelected() ? 1 : 0);
					cuadrante.setJueves(chJueves.isSelected() ? 1 : 0);
					cuadrante.setViernes(chViernes.isSelected() ? 1 : 0);
					cuadrante.setSabado(chSabado.isSelected() ? 1 : 0);
					cuadrante.setStatus(chbStatus.isSelected() ? 1 : 0);
					
					if(cuadrante.guardar(DiasTablas())){
						JOptionPane.showMessageDialog(null,"El registro se guardó correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				
				}
					
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos son requeridos"+ValidaError(),"Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	public String[][] DiasTablas(){
		
		int filas = tablaDomingo.getRowCount()+tablaLunes.getRowCount()+tablaMartes.getRowCount()+tablaMiercoles.getRowCount()+tablaJueves.getRowCount()+tablaViernes.getRowCount()+tablaSabado.getRowCount();
		
		String[][] tablas = new String[filas][3];
		
		int renglonesdomingo = tablaDomingo.getRowCount();
		int rengloneslunes = tablaLunes.getRowCount();
		int renglonesMartes = tablaMartes.getRowCount();
		int renglonesMiercoles = tablaMiercoles.getRowCount();
		int renglonesJueves = tablaJueves.getRowCount();
		int renglonesViernes = tablaViernes.getRowCount();
		int renglonesSabado = tablaSabado.getRowCount();
		
		int fila=0;
		int i=0;
		
		while(renglonesdomingo > 0){
				tablas[i][0] = modelDomingo.getValueAt(fila, 0)+"";
				tablas[i][1] = modelDomingo.getValueAt(fila, 1)+"";
				tablas[i][2] = "Domingo";
				i+=1;
				fila+=1;
			renglonesdomingo--;
		}
		fila=0;
		while(rengloneslunes > 0){
				tablas[i][0] = modelLunes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelLunes.getValueAt(fila, 1)+"";
				tablas[i][2] = "Lunes";
				i+=1;
				fila+=1;
				rengloneslunes--;
		}
		
		fila=0;
		while(renglonesMartes > 0){
				tablas[i][0] = modelMartes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelMartes.getValueAt(fila, 1)+"";
				tablas[i][2] = "Martes";
				i+=1;
				fila+=1;
				renglonesMartes--;
		}
		
		fila=0;
		while(renglonesMiercoles > 0){
				tablas[i][0] = modelMiercoles.getValueAt(fila, 0)+"";
				tablas[i][1] = modelMiercoles.getValueAt(fila, 1)+"";
				tablas[i][2] = "Miercoles";
				i+=1;
				fila+=1;
				renglonesMiercoles--;
		}
		
		fila=0;
		while(renglonesJueves > 0){
				tablas[i][0] = modelJueves.getValueAt(fila, 0)+"";
				tablas[i][1] = modelJueves.getValueAt(fila, 1)+"";
				tablas[i][2] = "Jueves";
				i+=1;
				fila+=1;
				renglonesJueves--;
		}
		
		fila=0;
		while(renglonesViernes > 0){
				tablas[i][0] = modelViernes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelViernes.getValueAt(fila, 1)+"";
				tablas[i][2] = "Viernes";
				i+=1;
				fila+=1;
				renglonesViernes--;
		}
		
		fila=0;
		while(renglonesSabado > 0){
				tablas[i][0] = modelSabado.getValueAt(fila, 0)+"";
				tablas[i][1] = modelSabado.getValueAt(fila, 1)+"";
				tablas[i][2] = "Sabado";
				i+=1;
				fila+=1;
				renglonesSabado--;
		}
		
		return tablas;
	}
	
	public String ValidaError(){
		String error ="";
		
			if(txtCuadrante.getText().equals("")) error+="Cuadrante\n";
			if(txaDescripcion.getText().equals("")) error+="Perfil\n";
			if(cmbJefatura.getSelectedIndex()==0) error+="Jefatura\n";
			if(cmbnivel_gerarquico.getSelectedIndex()==0) error+="Nivel Gerarquico\n";
			if(cmbEquipo_Trabajo.getSelectedIndex()==0) error+="Equipo de Trabajo\n";
			if(cmbEstablecimiento.getSelectedIndex()==0) error+="Establecimiento\n";
			if(chDomingo.isSelected() == false &&
			   chLunes.isSelected() == false &&
			   chMartes.isSelected() == false &&
			   chMiercoles.isSelected() == false &&
			   chJueves.isSelected() == false &&
			   chViernes.isSelected() == false &&
			   chSabado.isSelected() == false) error+="Día\n";
			
		return error;
	}
	
	
	public static void main (String arg []){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Cuadrante().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
