package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import objetos.Obj_Configuracion_Sistema;

@SuppressWarnings("serial")
public class Cat_Configuracion_Sistema extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JCheckBox chbBono_10_12 = new JCheckBox();
	JCheckBox chbBono_dia_extra = new JCheckBox();
	
	JCheckBox chbHorario = new JCheckBox();
	JCheckBox chbDepartamento = new JCheckBox();
	
	JTextField txtPorcentaje_fs = new JTextField();
	JDateChooser txtFechaListaDeRaya = new JDateChooser();
	
	JButton btnAplicar = new JButton("Aplicar");
	public Cat_Configuracion_Sistema(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Application.png"));
		this.setTitle("Configuración de Parámetros");
		panel.setBorder(BorderFactory.createTitledBorder("Configuración de Parámetros"));
		
		this.txtFechaListaDeRaya.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		
		int y = 20;
		panel.add(chbBono_10_12).setBounds(15, y, 20,20);
		panel.add(new JLabel("Activar Descuento por imputualidad y asistencia en bono")).setBounds( 40, y, 380, 20);
		
		panel.add(chbBono_dia_extra).setBounds(15, y+=25, 20,20);
		panel.add(new JLabel("Activar bono en día extra")).setBounds( 40, y, 380, 20);
		
		panel.add(chbHorario).setBounds(15, y+=25, 20,20);
		panel.add(new JLabel("Activar alta de horarios")).setBounds( 40, y, 380, 20);
		
		panel.add(chbDepartamento).setBounds(15, y+=25, 20,20);
		panel.add(new JLabel("Activar alta de departamentos")).setBounds( 40, y, 380, 20);
		
		panel.add(new JLabel("Porcentaje fuente de sodas: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtPorcentaje_fs).setBounds(165, y, 30, 20);
		panel.add(new JLabel("%")).setBounds(200, y, 20, 20);
		
		panel.add(new JLabel("Fecha de lista de raya: ")).setBounds(20, y+=25, 140, 20);
		panel.add(txtFechaListaDeRaya).setBounds(135, y, 100, 20);
		
		panel.add(btnAplicar).setBounds(300, y, 80, 20);
		txtPorcentaje_fs.addKeyListener(validaNumerico);
		btnAplicar.addActionListener(opAplicar);
		
		cont.add(panel);
		Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar();
		Obj_Configuracion_Sistema configs2 = new Obj_Configuracion_Sistema().buscar2();
		if(configs.getCouns() > 0){
			chbBono_10_12.setSelected(configs2.isBono_10_12());
			chbBono_dia_extra.setSelected(configs2.isBono_dia_extra());
			chbHorario.setSelected(configs2.isGuardar_horario());
			chbDepartamento.setSelected(configs2.isGuardar_departamento());
			
			txtPorcentaje_fs.setText(configs2.getPorcentaje_fs()+"");
			
			try{
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(configs2.getFechaLR());
				txtFechaListaDeRaya.setDate(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		this.setSize(390,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opAplicar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar();
			if(configs.getCouns() > 0){
				if(JOptionPane.showConfirmDialog(null, "El registro existe, ¿desea actualizarlo?") == 0){
					configs.setBono_10_12(chbBono_10_12.isSelected());
					configs.setBono_dia_extra(chbBono_dia_extra.isSelected());
					configs.setGuardar_horario(chbHorario.isSelected());
					configs.setGuardar_departamento(chbDepartamento.isSelected());
					
					if(txtPorcentaje_fs.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Debe ingresar un porcentaje a fuente de sodas","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						configs.setPorcentaje_fs(Integer.valueOf(txtPorcentaje_fs.getText()));
					}
					
					if(txtFechaListaDeRaya.getDate()==null){
						JOptionPane.showMessageDialog(null,"La fecha es requerida, si no desea realizar cambios\ncierre la ventana ( Configuración de Parámetros )","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						configs.setFechaLR(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaListaDeRaya.getDate()));
					}
					
					configs.actualizar();
				}else{
					return;
				}
			}else{
				configs.setBono_10_12(chbBono_10_12.isSelected());
				configs.setBono_dia_extra(chbBono_dia_extra.isSelected());
				configs.setGuardar_horario(chbHorario.isSelected());
				configs.setGuardar_departamento(chbDepartamento.isSelected());
				
				if(txtPorcentaje_fs.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Debe ingresar un porcentaje a fuente de sodas","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					configs.setPorcentaje_fs(Integer.valueOf(txtPorcentaje_fs.getText()));
				}
				
				if(txtFechaListaDeRaya.getDate()==null){
					JOptionPane.showMessageDialog(null,"La fecha es requerida, si no desea realizar cambios\ncierre la ventana ( Configuración de Parámetros )","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					configs.setFechaLR(new SimpleDateFormat("dd/MM/yyyy").format(txtFechaListaDeRaya.getDate()));
				}
				
				configs.guardar();
			}
		}
	};
	
	KeyListener validaNumerico = new KeyListener() {
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
		    if((caracter < '0') ||	
		    	(caracter > '9')){
		    		e.consume();
		    	}
		}
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
	};
	
}
