package catalogos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

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
		this.btnNuevo.addActionListener(opNuevo);
		this.btnSalir.addActionListener(opSalir);
		this.btnDeshacer.addActionListener(opDeshacer);
		this.btnEditar.addActionListener(opEditar);
		this.btnBuscar.addActionListener(opBuscar);
		
		txtFolio.addKeyListener(valida);
		
		enablesTodos(false);
		txtFolio.setEditable(true);
		
	}
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtFolio.getText().equals("")){
				Obj_Cuadrante cuadrante = new Obj_Cuadrante().buscarCuadrante(Integer.parseInt(txtFolio.getText()));
								
				if(cuadrante.getCuadrante().equals("")){
					JOptionPane.showMessageDialog(null,"No existe el registro: "+txtFolio.getText(),"Aviso",JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					try {
						String[][] tabla = new Obj_Cuadrante().tabla(cuadrante.getCuadrante().toUpperCase().trim());
											
						for(int i=0; i<tabla.length; i++){
							
							Dias currentDia = Dias.valueOf(tabla[i][0]);
							
							 switch (currentDia) {
							 	case Domingo:
							 		while(tablaDomingo.getRowCount() > 0){
										modelDomingo.removeRow(0);
									}
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
							 		while(tablaLunes.getRowCount() > 0){
										modelLunes.removeRow(0);
									}
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
							 		while(tablaMartes.getRowCount() > 0){
										modelMartes.removeRow(0);
									}
							 		Object[] mar = new Object[6];
							 		mar[0] = tabla[i][1];
							 		mar[1] = tabla[i][2];
							 		mar[2] = tabla[i][3];
							 		mar[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
							 		mar[4] = tabla[i][5];
							 		mar[5] = tabla[i][6];
							 		modelMartes.addRow(mar);
							 		 break;
							 	case Mi�rcoles:
							 		while(tablaMiercoles.getRowCount() > 0){
										modelMiercoles.removeRow(0);
									}
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
					            	while(tablaJueves.getRowCount() > 0){
										modelJueves.removeRow(0);
									}
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
					            	while(tablaViernes.getRowCount() > 0){
										modelViernes.removeRow(0);
									}
					            	Object[] vie = new Object[6];
					            	vie[0] = tabla[i][1];
					            	vie[1] = tabla[i][2];
					            	vie[2] = tabla[i][3];
					            	vie[3] = Integer.parseInt(tabla[i][4]) == 1 ? true : false;
					            	vie[4] = tabla[i][5];
					            	vie[5] = tabla[i][6];
							 		modelViernes.addRow(vie);
					            	 break;
					            case S�bado:
					            	while(tablaSabado.getRowCount() > 0){
										modelSabado.removeRow(0);
									}
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
						 
						
						txtCuadrante.setText(cuadrante.getCuadrante());
						txaDescripcion.setText(cuadrante.getPerfil());
						cmbJefatura.setSelectedItem(cuadrante.getJefatura());
						cmbnivel_gerarquico.setSelectedItem(cuadrante.getNivel_gerarquico());
						cmbEquipo_Trabajo.setSelectedItem(cuadrante.getEquipo_trabajo());
						cmbEstablecimiento.setSelectedItem(cuadrante.getEstablecimiento());
						chDomingo.setSelected(cuadrante.getDomingo() == 1 ? true : false);
						chLunes.setSelected(cuadrante.getLunes() == 1 ? true : false);
						chMartes.setSelected(cuadrante.getMartes() == 1 ? true : false);
						chMiercoles.setSelected(cuadrante.getMiercoles() == 1 ? true : false);
						chJueves.setSelected(cuadrante.getJueves() == 1 ? true : false);
						chViernes.setSelected(cuadrante.getViernes() == 1 ? true : false);
						chSabado.setSelected(cuadrante.getSabado() == 1 ? true : false);
						chbStatus.setSelected(cuadrante.getStatus() == 1 ? true : false);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
					
					
				}
			
			}else{
				JOptionPane.showMessageDialog(null,"No ha ingresado ning�n folio para buscar","Aviso",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opEditar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null,"No hay ning�n registro que editar","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				enablesTodos(true);
				txtFolio.setEditable(false);
				
				if(chDomingo.isSelected()){
					btnAgregarDomingo.setEnabled(true);
					btnSubirDomingo.setEnabled(true);
					btnBajarDomingo.setEnabled(true);
					btnRemoverDomingo.setEnabled(true);
				}else{
					btnAgregarDomingo.setEnabled(false);
					btnSubirDomingo.setEnabled(false);
					btnBajarDomingo.setEnabled(false);
					btnRemoverDomingo.setEnabled(false);
					
					while(tablaDomingo.getRowCount() > 0){
						modelDomingo.removeRow(0);
					}
				}
				
				if(chLunes.isSelected()){
					btnAgregarLunes.setEnabled(true);
					btnSubirLunes.setEnabled(true);
					btnBajarLunes.setEnabled(true);
					btnRemoverLunes.setEnabled(true);
				}else{
					btnAgregarLunes.setEnabled(false);
					btnSubirLunes.setEnabled(false);
					btnBajarLunes.setEnabled(false);
					btnRemoverLunes.setEnabled(false);
					
					while(tablaLunes.getRowCount() > 0){
						modelLunes.removeRow(0);
					}
				}
				if(chMartes.isSelected()){
					btnAgregarMartes.setEnabled(true);
					btnSubirMartes.setEnabled(true);
					btnBajarMartes.setEnabled(true);
					btnRemoverMartes.setEnabled(true);
				}else{
					btnAgregarMartes.setEnabled(false);
					btnSubirMartes.setEnabled(false);
					btnBajarMartes.setEnabled(false);
					btnRemoverMartes.setEnabled(false);
					
					while(tablaMartes.getRowCount() > 0){
						modelMartes.removeRow(0);
					}
				}
				if(chMiercoles.isSelected()){
					btnAgregarMiercoles.setEnabled(true);
					btnSubirMiercoles.setEnabled(true);
					btnBajarMiercoles.setEnabled(true);
					btnRemoverMiercoles.setEnabled(true);
				}else{
					btnAgregarMiercoles.setEnabled(false);
					btnSubirMiercoles.setEnabled(false);
					btnBajarMiercoles.setEnabled(false);
					btnRemoverMiercoles.setEnabled(false);
					
					while(tablaMiercoles.getRowCount() > 0){
						modelMiercoles.removeRow(0);
					}
				}
				if(chJueves.isSelected()){
					btnAgregarJueves.setEnabled(true);
					btnSubirJueves.setEnabled(true);
					btnBajarJueves.setEnabled(true);
					btnRemoverJueves.setEnabled(true);
				}else{
					btnAgregarJueves.setEnabled(false);
					btnSubirJueves.setEnabled(false);
					btnBajarJueves.setEnabled(false);
					btnRemoverJueves.setEnabled(false);
					
					while(tablaJueves.getRowCount() > 0){
						modelJueves.removeRow(0);
					}
				}
				if(chViernes.isSelected()){
					btnAgregarViernes.setEnabled(true);
					btnSubirViernes.setEnabled(true);
					btnBajarViernes.setEnabled(true);
					btnRemoverViernes.setEnabled(true);
				}else{
					btnAgregarViernes.setEnabled(false);
					btnSubirViernes.setEnabled(false);
					btnBajarViernes.setEnabled(false);
					btnRemoverViernes.setEnabled(false);
					
					while(tablaViernes.getRowCount() > 0){
						modelViernes.removeRow(0);
					}
				}
				if(chSabado.isSelected()){
					btnAgregarSabado.setEnabled(true);
					btnSubirSabado.setEnabled(true);
					btnBajarSabado.setEnabled(true);
					btnRemoverSabado.setEnabled(true);
				}else{
					btnAgregarSabado.setEnabled(false);
					btnSubirSabado.setEnabled(false);
					btnBajarSabado.setEnabled(false);
					btnRemoverSabado.setEnabled(false);
					
					while(tablaSabado.getRowCount() > 0){
						modelSabado.removeRow(0);
					}
				}
			}
		}
	};
	
	public String erroralllenartabla(){
		String error="";
			if(chDomingo.isSelected()== true &&
			   tablaDomingo.getRowCount()==0) error+="Domingo\n";
			if(chLunes.isSelected()== true &&
					   tablaLunes.getRowCount()==0) error+="Lunes\n";
			if(chMartes.isSelected()== true &&
					   tablaMartes.getRowCount()==0) error+="Martes\n";
			if(chMiercoles.isSelected()== true &&
					   tablaMiercoles.getRowCount()==0) error+="Miercoles\n";
			if(chJueves.isSelected()== true &&
					   tablaJueves.getRowCount()==0) error+="Jueves\n";
			if(chViernes.isSelected()== true &&
					   tablaViernes.getRowCount()==0) error+="Viernes\n";
			if(chSabado.isSelected()== true &&
					   tablaSabado.getRowCount()==0) error+="Sabado\n";
			
		return error;
	}
	
	ActionListener opDeshacer = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			limpiar();
		}
	};
	
	ActionListener opNuevo = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			enablesTodos(true);
			txtFolio.setText(new Obj_Cuadrante().nuevo()+"");
			txtFolio.setEditable(false);
			txtCuadrante.requestFocus();
		}
	};
	
	ActionListener opGuardar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(ValidaError().equals("")){
				if(erroralllenartabla().equals("")){
					if(new Obj_Cuadrante().existe(txtCuadrante.getText())){
						if(JOptionPane.showConfirmDialog(null, "El registro existe, �desea actualizarlo?") == 0){
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
								limpiar();
								JOptionPane.showMessageDialog(null,"El registro se actualiz� correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
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
							limpiar();
							JOptionPane.showMessageDialog(null,"El registro se guard� correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null,"Ha ocurrido un error mientras se intentaba guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					
					}
				}else{
					JOptionPane.showMessageDialog(null,"Las siguientes tablas est�n vac�as: \n"+erroralllenartabla(),"Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos son requeridos: \n"+ValidaError(),"Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	public String[][] DiasTablas(){
		
		int filas = tablaDomingo.getRowCount()+tablaLunes.getRowCount()+tablaMartes.getRowCount()+tablaMiercoles.getRowCount()+tablaJueves.getRowCount()+tablaViernes.getRowCount()+tablaSabado.getRowCount();
		
		String[][] tablas = new String[filas][7];
		
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
				tablas[i][2] = modelDomingo.getValueAt(fila, 2)+"";
				tablas[i][3] = modelDomingo.getValueAt(fila, 3)+"";
				tablas[i][4] = modelDomingo.getValueAt(fila, 4)+"";
				tablas[i][5] = modelDomingo.getValueAt(fila, 5)+"";
				tablas[i][6] = "Domingo";
				i+=1;
				fila+=1;
			renglonesdomingo--;
		}
		fila=0;
		while(rengloneslunes > 0){
				tablas[i][0] = modelLunes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelLunes.getValueAt(fila, 1)+"";
				tablas[i][2] = modelLunes.getValueAt(fila, 2)+"";
				tablas[i][3] = modelLunes.getValueAt(fila, 3)+"";
				tablas[i][4] = modelLunes.getValueAt(fila, 4)+"";
				tablas[i][5] = modelLunes.getValueAt(fila, 5)+"";
				tablas[i][6] = "Lunes";
				i+=1;
				fila+=1;
				rengloneslunes--;
		}
		
		fila=0;
		while(renglonesMartes > 0){
				tablas[i][0] = modelMartes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelMartes.getValueAt(fila, 1)+"";
				tablas[i][2] = modelMartes.getValueAt(fila, 2)+"";
				tablas[i][3] = modelMartes.getValueAt(fila, 3)+"";
				tablas[i][4] = modelMartes.getValueAt(fila, 4)+"";
				tablas[i][5] = modelMartes.getValueAt(fila, 5)+"";
				tablas[i][6] = "Martes";
				i+=1;
				fila+=1;
				renglonesMartes--;
		}
		
		fila=0;
		while(renglonesMiercoles > 0){
				tablas[i][0] = modelMiercoles.getValueAt(fila, 0)+"";
				tablas[i][1] = modelMiercoles.getValueAt(fila, 1)+"";
				tablas[i][2] = modelMiercoles.getValueAt(fila, 2)+"";
				tablas[i][3] = modelMiercoles.getValueAt(fila, 3)+"";
				tablas[i][4] = modelMiercoles.getValueAt(fila, 4)+"";
				tablas[i][5] = modelMiercoles.getValueAt(fila, 5)+"";
				tablas[i][6] = "Mi�rcoles";
				i+=1;
				fila+=1;
				renglonesMiercoles--;
		}
		
		fila=0;
		while(renglonesJueves > 0){
				tablas[i][0] = modelJueves.getValueAt(fila, 0)+"";
				tablas[i][1] = modelJueves.getValueAt(fila, 1)+"";
				tablas[i][2] = modelJueves.getValueAt(fila, 2)+"";
				tablas[i][3] = modelJueves.getValueAt(fila, 3)+"";
				tablas[i][4] = modelJueves.getValueAt(fila, 4)+"";
				tablas[i][5] = modelJueves.getValueAt(fila, 5)+"";
				tablas[i][6] = "Jueves";
				i+=1;
				fila+=1;
				renglonesJueves--;
		}
		
		fila=0;
		while(renglonesViernes > 0){
				tablas[i][0] = modelViernes.getValueAt(fila, 0)+"";
				tablas[i][1] = modelViernes.getValueAt(fila, 1)+"";
				tablas[i][2] = modelViernes.getValueAt(fila, 2)+"";
				tablas[i][3] = modelViernes.getValueAt(fila, 3)+"";
				tablas[i][4] = modelViernes.getValueAt(fila, 4)+"";
				tablas[i][5] = modelViernes.getValueAt(fila, 5)+"";
				tablas[i][6] = "Viernes";
				i+=1;
				fila+=1;
				renglonesViernes--;
		}
		
		fila=0;
		while(renglonesSabado > 0){
				tablas[i][0] = modelSabado.getValueAt(fila, 0)+"";
				tablas[i][1] = modelSabado.getValueAt(fila, 1)+"";
				tablas[i][2] = modelSabado.getValueAt(fila, 2)+"";
				tablas[i][3] = modelSabado.getValueAt(fila, 3)+"";
				tablas[i][4] = modelSabado.getValueAt(fila, 4)+"";
				tablas[i][5] = modelSabado.getValueAt(fila, 5)+"";
				tablas[i][6] = "S�bado";
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
			   chSabado.isSelected() == false) error+="D�a\n";
			
		return error;
	}
	
	ActionListener opSalir = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	public void enablesTodos(boolean variable){
		txtFolio.setEditable(variable);
		chbStatus.setEnabled(variable);
		txtCuadrante.setEditable(variable);
		txaDescripcion.setEditable(variable);
		cmbJefatura.setEnabled(variable);
		cmbEstablecimiento.setEnabled(variable);
		cmbnivel_gerarquico.setEnabled(variable);
		cmbEquipo_Trabajo.setEnabled(variable);
		chTodos.setEnabled(variable);
		chDomingo.setEnabled(variable);
		chLunes.setEnabled(variable);
		chMartes.setEnabled(variable);
		chMiercoles.setEnabled(variable);
		chJueves.setEnabled(variable);
		chViernes.setEnabled(variable);
		chSabado.setEnabled(variable);
	
	}
	
	public void limpiar(){
		enablesTodos(false);
		txtFolio.setEditable(true);
		txtFolio.requestFocus();
		txtFolio.setText("");
		txtCuadrante.setText("");
		txaDescripcion.setText("");
		cmbJefatura.setSelectedIndex(0);
		cmbnivel_gerarquico.setSelectedIndex(0);
		cmbEquipo_Trabajo.setSelectedIndex(0);
		cmbEstablecimiento.setSelectedIndex(0);
		
		chTodos.setSelected(false);
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
		
		while(tablaDomingo.getRowCount() > 0){
			modelDomingo.removeRow(0);
		}
		while(tablaLunes.getRowCount() > 0){
			modelLunes.removeRow(0);
		}
		while(tablaMartes.getRowCount() > 0){
			modelMartes.removeRow(0);
		}
		while(tablaMiercoles.getRowCount() > 0){
			modelMiercoles.removeRow(0);
		}
		while(tablaJueves.getRowCount() > 0){
			modelJueves.removeRow(0);
		}
		while(tablaViernes.getRowCount() > 0){
			modelViernes.removeRow(0);
		}
		while(tablaSabado.getRowCount() > 0){
			modelSabado.removeRow(0);
		}
		
	}
	
	KeyListener valida = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();
			int limite=10;

			if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }
				if (txtFolio.getText().length()== limite)
			     e.consume();
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
				txtFolio.requestFocus();
			}
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
