package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import objetos.Obj_Auto_Finanzas;

@SuppressWarnings("serial")
public class Cat_Auto_Finanzas extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Obj_Auto_Finanzas autori = new Obj_Auto_Finanzas().buscar();
	boolean autorizar = autori.isAutorizar();
	
	JLabel lblAutorizar = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	
	public Cat_Auto_Finanzas(){
		this.setTitle("Autorización Lista Raya");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Aplicar.png"));
		panel.setBorder(BorderFactory.createTitledBorder("-"));
		
		panel.add(new JLabel("Autorizar Lista Raya")).setBounds(15,30,120,25);
		panel.add(lblAutorizar).setBounds(160,30,20,20);
		
		lblAutorizar.setEnabled(autorizar);
		lblAutorizar.addMouseListener(opAutorizar);
		cont.add(panel);
		
		this.setModal(true);
		this.setSize(240,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	MouseListener opAutorizar = new MouseListener() {
		public void mouseReleased(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
			
			if(autorizar != true){
				if(JOptionPane.showConfirmDialog(null, "¿Desea autorizar la lista de raya?") == 0){
					Obj_Auto_Finanzas prue = new Obj_Auto_Finanzas();
					autorizar = true;
					lblAutorizar.setEnabled(true);
					prue.setAutorizar(autorizar);
					prue.actualizar();
				}else{
					return;
				}
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea no autorizar la lista de raya?") == 0){
					Obj_Auto_Finanzas prue = new Obj_Auto_Finanzas();
					autorizar = false;
					lblAutorizar.setEnabled(false);
					prue.setAutorizar(autorizar);
					prue.actualizar();
				}else{
					return;
				}
				
			}
				
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseClicked(MouseEvent arg0) {
		}
	};

}
