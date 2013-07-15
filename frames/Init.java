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
		this.setTitle("SCOI [Sistema de Control Operativo Izagar]");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/izagar2.png"));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
}
