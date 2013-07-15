package catalogos;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Cat_Mensaje_Pre_Lista extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	public Cat_Mensaje_Pre_Lista(){
		this.setTitle("Aviso");
		
		int y=15;
		
		this.panel.add(new JLabel("PARA PODER VER EL TOTAL APAGAR ACTUAL")).setBounds(22,15,250,20);
		this.panel.add(new JLabel("   DE LOS CAMBIOS A BANCOS APLICADOS")).setBounds(25,y+=25,250,20);
		this.panel.add(new JLabel("         GUARDAR LA PRE-LISTA DE RAYA.")).setBounds(25,y+=25,250,20);
		
		this.cont.add(panel);
		
		this.setSize(300,150);
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String args[]){
		new Cat_Mensaje_Pre_Lista().setVisible(true);
	}

}
