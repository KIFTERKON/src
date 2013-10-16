package datos;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class LoadingBar2 extends JDialog
{
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	JProgressBar barra = new JProgressBar();
	JLabel puntos = new JLabel();
	
	int i;
	
	public LoadingBar2()
	{
		setTitle("Generando ticket");
		
		Thread hilo = new Thread(new Hilo());
		hilo.start();
		
		barra.setStringPainted(true);
		
		campo.add(barra).setBounds(20,20,140,20);
		campo.add(new JLabel("Espere")).setBounds(10,55,100,20);
		campo.add(puntos).setBounds(70,55,100,20);
		
		cont.add(campo);
		setSize(200,120);
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	class Hilo implements Runnable
	{
		public void run() {
			for(i=0; i<=100; i+=2)
			{
				barra.setValue(i);
				
				if(i == 20)
				{
					puntos.setText(".");
				}
				if(i == 30)
				{
					puntos.setText(". .");
				}
				if(i == 40)
				{
					puntos.setText(". . .");
				}
				if(i == 60)
				{
					puntos.setText(".");
				}
				if(i == 70)
				{
					puntos.setText(". .");
				}
				if(i == 80)
				{
					puntos.setText(". . .");
				}
				
				
					if(i == 100)
					{
						dispose();
						new Imprime().setVisible(true);
						
					}
				
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
	}
}
