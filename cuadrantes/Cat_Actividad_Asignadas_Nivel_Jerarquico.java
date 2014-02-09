package cuadrantes;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import objetos.Obj_Actividad;
import objetos.Obj_Actividad_Asignadas_Nivel_Jerarquico;
import objetos.Obj_Usuario;

@SuppressWarnings("serial")
public class Cat_Actividad_Asignadas_Nivel_Jerarquico extends Cat_Actividad {
	
	public Cat_Actividad_Asignadas_Nivel_Jerarquico(){
		inicializar();
	}
	
	public Cat_Actividad_Asignadas_Nivel_Jerarquico(int Folio){
		inicializar();
		
		Obj_Actividad_Asignadas_Nivel_Jerarquico actividad = new Obj_Actividad_Asignadas_Nivel_Jerarquico().Buscar(Folio);
		
		txtFolio.setText(Folio+"");		
		txaActividad.setText(actividad.getActividad());
		txaDescripcion.setText(actividad.getDescripcion());
		cmbRespuesta.setSelectedItem(actividad.getRespuesta());
		cmbAtributos.setSelectedItem(actividad.getAtributos());
		cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
		cmbTemporada.setSelectedItem(actividad.getTemporada());
		chbCajaDeTrabajo.setSelected(actividad.isCarga());
		spRepetir.setValue(actividad.getRepetir());
		chbStatus.setSelected(actividad.isStatus());
		
		setModal(true);
	}
	
	ActionListener opNuevoActividad = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			panelEnabledTrue();
			panelLimpiar();
			txtFolio.setText(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Nuevo()+"");
			txtFolio.setEditable(false);
			txaActividad.requestFocus();
		}
	};
	
	ActionListener opGuardarActividad = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(validaCampos() !=""){
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				Obj_Usuario user = new Obj_Usuario().LeerSession();
				Obj_Actividad_Asignadas_Nivel_Jerarquico actividad = new Obj_Actividad_Asignadas_Nivel_Jerarquico();
				
				if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe(Integer.parseInt(txtFolio.getText()))){
					if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe_Nombre(procesa_texto(txaActividad.getText()))){
						if(procesa_texto(txaActividad.getText()).equalsIgnoreCase(procesa_texto(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Nombre_Old(Integer.valueOf(txtFolio.getText()))))){
							actividad.setActividad(procesa_texto(txaActividad.getText()));
							actividad.setDescripcion(procesa_texto(txaDescripcion.getText()));

							actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
							actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
							actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
							
							actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
							actividad.setCarga(chbCajaDeTrabajo.isSelected());
							actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
							actividad.setStatus(chbStatus.isSelected());
							
							if(actividad.Actualizar(Integer.parseInt(txtFolio.getText()), procesa_texto(user.getNombre_completo())))
								JOptionPane.showMessageDialog(null, "El registro se actualizó exitosamente!" , "Exito al actualizar!", JOptionPane.INFORMATION_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "Error al tratar de actualizar el registro", "Error al actualizar registro", JOptionPane.WARNING_MESSAGE);
						}else{
							if(new Obj_Actividad().Existe_Nombre(procesa_texto(txaActividad.getText()))){
								JOptionPane.showMessageDialog(null, "El nombre: "+txaActividad.getText() +"\n\n Ya está registrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								actividad.setActividad(procesa_texto(txaActividad.getText()));
								actividad.setDescripcion(procesa_texto(txaDescripcion.getText()));

								actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
								actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
								actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
								
								actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
								actividad.setCarga(chbCajaDeTrabajo.isSelected());
								actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
								actividad.setStatus(chbStatus.isSelected());
								
								if(actividad.Actualizar(Integer.parseInt(txtFolio.getText()), procesa_texto(user.getNombre_completo())))
									JOptionPane.showMessageDialog(null, "El registro se actualizó exitosamente!" , "Exito al actualizar!", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Error al tratar de actualizar el registro", "Error al actualizar registro", JOptionPane.WARNING_MESSAGE);
							}
						}
					}else{
						if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
							actividad.setActividad(procesa_texto(txaActividad.getText()));
							actividad.setDescripcion(procesa_texto(txaDescripcion.getText()));

							actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
							actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
							actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
							
							actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
							actividad.setCarga(chbCajaDeTrabajo.isSelected());
							actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
							actividad.setStatus(chbStatus.isSelected());
							
							if(actividad.Actualizar(Integer.parseInt(txtFolio.getText()), procesa_texto(user.getNombre_completo())))
								JOptionPane.showMessageDialog(null, "El registro se actualizó exitosamente!" , "Exito al actualizar!", JOptionPane.INFORMATION_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "Error al tratar de actualizar el registro", "Error al actualizar registro", JOptionPane.WARNING_MESSAGE);
						}
					}
				}else{
					if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe_Nombre(procesa_texto(txaActividad.getText()))){
						JOptionPane.showMessageDialog(null, "El nombre: "+txaActividad.getText() +"\n\n Ya está registrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						actividad.setFolio(Integer.valueOf(txtFolio.getText()));
						actividad.setActividad(procesa_texto(txaActividad.getText()));
						actividad.setDescripcion(procesa_texto(txaDescripcion.getText()));

						actividad.setRespuesta(cmbRespuesta.getSelectedItem().toString());
						actividad.setAtributos(cmbAtributos.getSelectedItem().toString());
						actividad.setNivel_critico(cmbNivelCritico.getSelectedItem().toString());
						
						actividad.setTemporada(cmbTemporada.getSelectedItem().toString());
						actividad.setCarga(chbCajaDeTrabajo.isSelected());
						actividad.setRepetir(Integer.parseInt(spRepetir.getValue().toString()));
						actividad.setStatus(chbStatus.isSelected());
						
						if(actividad.Guardar(procesa_texto(user.getNombre_completo()))){
							panelLimpiar();
							JOptionPane.showMessageDialog(null, "El registro se guardó exitosamente!" , "Exito al guardar!", JOptionPane.INFORMATION_MESSAGE);
							return;
						}else{
							JOptionPane.showMessageDialog(null, "Error al tratar de guardar el registro", "Error al guardar registro", JOptionPane.WARNING_MESSAGE);
							return;
						}	
					}
				}
			}
		}
	};
	
	public String procesa_texto(String texto) {
        StringTokenizer tokens = new StringTokenizer(texto);
        texto = "";
        while(tokens.hasMoreTokens())
            texto += " "+tokens.nextToken();

        texto = texto.toString();
        texto = texto.trim().toUpperCase();
        return texto;
    }
	
	ActionListener opBuscarActividad = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				new Cat_Filtro_Actividades_Nivel_Jerarquico_cuadrante().setVisible(true);
				dispose();
			}else{
				if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe(Integer.parseInt(txtFolio.getText()))==true) {
					Obj_Actividad_Asignadas_Nivel_Jerarquico actividad = new Obj_Actividad_Asignadas_Nivel_Jerarquico().Buscar(Integer.parseInt(txtFolio.getText()));
					
					txaActividad.setText(actividad.getActividad());
					txaDescripcion.setText(actividad.getDescripcion());
					
					cmbRespuesta.setSelectedItem(actividad.getRespuesta());
					cmbAtributos.setSelectedItem(actividad.getAtributos());
					cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
					
					cmbTemporada.setSelectedItem(actividad.getTemporada());
					chbCajaDeTrabajo.setSelected(actividad.isCarga());
					spRepetir.setValue(actividad.getRepetir());
					chbStatus.setSelected(actividad.isStatus());
					
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();
					
				}else{
					JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}
	};
	
	KeyListener numerico_action_actividad = new KeyListener() {
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
	
	ActionListener op_similar_actividad = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(!txtFolio.getText().equals("")){
				panelEnabledTrue();
				txtFolio.setEditable(false);
				txaActividad.requestFocus();
				txtFolio.setText(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Nuevo()+"");
				
			}else{
				JOptionPane.showMessageDialog(null, "Busque un registro primero antes de hacer un similar", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opLeft_actividad = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El campo de texto de folio está vacío", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				if(txtFolio.getText().equals("1")){
					JOptionPane.showMessageDialog(null, "No hay más registros", "Error al modificar", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe(Integer.parseInt(txtFolio.getText())-1)==false) {
						JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						Obj_Actividad_Asignadas_Nivel_Jerarquico actividad = new Obj_Actividad_Asignadas_Nivel_Jerarquico().Buscar(Integer.parseInt(txtFolio.getText())-1);
						
						txtFolio.setText(Integer.parseInt(txtFolio.getText())-1+"");
						txaActividad.setText(actividad.getActividad());
						txaDescripcion.setText(actividad.getDescripcion());
						
						cmbRespuesta.setSelectedItem(actividad.getRespuesta());
						cmbAtributos.setSelectedItem(actividad.getAtributos());
						cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
						
						cmbTemporada.setSelectedItem(actividad.getTemporada());
						chbCajaDeTrabajo.setSelected(actividad.isCarga());
						spRepetir.setValue(actividad.getRepetir());
						chbStatus.setSelected(actividad.isStatus());
						
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
					}
				}
			}
		}
	};
	
	ActionListener opRigth_actividad = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El campo de texto de folio está vacío", "Error al modificar", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				if(new Obj_Actividad_Asignadas_Nivel_Jerarquico().Existe(Integer.parseInt(txtFolio.getText())+1)==false) {
					JOptionPane.showMessageDialog(null, "El registro no existe", "Error al buscar registro", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					Obj_Actividad_Asignadas_Nivel_Jerarquico actividad = new Obj_Actividad_Asignadas_Nivel_Jerarquico().Buscar(Integer.parseInt(txtFolio.getText())+1);
					
					txtFolio.setText(Integer.parseInt(txtFolio.getText())+1+"");
					txaActividad.setText(actividad.getActividad());
					txaDescripcion.setText(actividad.getDescripcion());
					
					cmbRespuesta.setSelectedItem(actividad.getRespuesta());
					cmbAtributos.setSelectedItem(actividad.getAtributos());
					cmbNivelCritico.setSelectedItem(actividad.getNivel_critico());
					
					cmbTemporada.setSelectedItem(actividad.getTemporada());
					chbCajaDeTrabajo.setSelected(actividad.isCarga());
					spRepetir.setValue(actividad.getRepetir());
					chbStatus.setSelected(actividad.isStatus());
					
					panelEnabledFalse();
					txtFolio.setEditable(true);
					txtFolio.requestFocus();

				}
			}
		}
	};
	
	public void inicializar(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/actividad_icon&16.png"));
		this.setTitle("Actividades de Cuadrante por Nivel Jerarquico");
		this.panel.setBorder(BorderFactory.createTitledBorder("Actividad"));
		
		this.btnNuevo.removeActionListener(opNuevo);
		this.btnGuardar.removeActionListener(opGuardar);
		this.btnBuscar.removeActionListener(opBuscar);
		this.txtFolio.removeKeyListener(numerico_action);
		this.btnSimilar.removeActionListener(op_similar);
		this.btnizquierda.removeActionListener(opLeft);
		this.btnderecha.removeActionListener(opRigth);
		
		this.btnNuevo.addActionListener(opNuevoActividad);
		this.btnGuardar.addActionListener(opGuardarActividad);
		this.btnBuscar.addActionListener(opBuscarActividad);
		this.txtFolio.addKeyListener(numerico_action_actividad);
		this.btnSimilar.addActionListener(op_similar_actividad);
		this.btnizquierda.addActionListener(opLeft_actividad);
		this.btnderecha.addActionListener(opRigth_actividad);
		
	}
	
}
