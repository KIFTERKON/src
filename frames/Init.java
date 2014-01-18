package frames;

import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Init extends JFrame{

	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	public Init() {
		this.setTitle("SCOI [Sistema de Control Operativo Izagar] V:1.0.6");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/layers_1_icon&16.png"));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
}
