package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import objetos.Obj_Configuracion_Sistema;

@SuppressWarnings("serial")
public class Cat_Configuracion_Sistema extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JCheckBox chbBono_10_12 = new JCheckBox();
	JCheckBox chbBono_dia_extra = new JCheckBox();
	
	JButton btnAplicar = new JButton("Aplicar");
	public Cat_Configuracion_Sistema(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Application.png"));
		this.setTitle("Configuración de Parámetros");
		panel.setBorder(BorderFactory.createTitledBorder("Configuración de Parámetros"));
		int y = 20;
		panel.add(chbBono_10_12).setBounds(15, y, 20,20);
		panel.add(new JLabel("Activar Descuento por imputualidad y asistencia en bono")).setBounds( 40, y, 380, 20);
		
		panel.add(chbBono_dia_extra).setBounds(15, y+=25, 20,20);
		panel.add(new JLabel("Activar bono en día extra")).setBounds( 40, y, 380, 20);
		
		panel.add(btnAplicar).setBounds(65,100,80,20);
		btnAplicar.addActionListener(opAplicar);
		
		cont.add(panel);
		Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema().buscar();
		Obj_Configuracion_Sistema configs2 = new Obj_Configuracion_Sistema().buscar2();
		if(configs.getCouns() > 0){
			chbBono_10_12.setSelected(configs2.isBono_10_12());
			chbBono_dia_extra.setSelected(configs2.isBono_dia_extra());
		}
		this.setSize(390,190);
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
					configs.actualizar();
				}else{
					return;
				}
			}else{
				configs.setBono_10_12(chbBono_10_12.isSelected());
				configs.setBono_dia_extra(chbBono_dia_extra.isSelected());
				configs.guardar();
			}
		}
	};
	
}
