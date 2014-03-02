package catalogos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;

import objetos.Obj_Establecimiento;

@SuppressWarnings("serial")
public class Cat_Plantilla extends JDialog{

	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JButton btngenerar = new JButton("Generar");
	
	public Cat_Plantilla(){
		this.setModal(true);
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/user_icon&16.png"));
		this.setTitle("Plantilla");
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		panel.setBorder(BorderFactory.createTitledBorder("Plantilla"));
		
		panel.add(cmbEstablecimiento).setBounds(20, 20, 200, 20);
		panel.add(btngenerar).setBounds(120, 190, 100, 20);
		
		cont.add(panel);
		
		btngenerar.addActionListener(opGenerar);
		
		this.setSize(245, 260);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opGenerar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(cmbEstablecimiento.getSelectedIndex()==0){
				System.out.println("aqui va el JOptionPane (seleccionar un establecimiento para poder generar el reporte)");
			}else{
				System.out.println("generar Reporte aquii");
			}
		}
	};
	
	public static void main(String [] arg){
		new Cat_Plantilla().setVisible(true);
	}
}
