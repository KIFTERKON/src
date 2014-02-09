package frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


@SuppressWarnings("serial")
public class Splash extends JFrame{
	//declaramos un contenedor
	Container cont = getContentPane();
	//declaramos un layered
	JLayeredPane campos = new JLayeredPane();
	//declaramos un label que sera la version de nuestro sistema
	JLabel version = new JLabel("Beta");
	//declaramos otro label el cual contiene la imagen de fondo con el imageicon
	static JLabel fondo = new JLabel(new ImageIcon("Imagen/splashCheck.jpg"));
	
	//declaramos los labels que son puntos suspensivos
	JLabel p1 = new JLabel(".");
	JLabel p2 = new JLabel("..");
	JLabel p3 = new JLabel("...");
	JLabel p4 = new JLabel("....");
	JLabel p5 = new JLabel(".....");
	JLabel p6 = new JLabel("......");
	JLabel p7 = new JLabel(".......");
	JLabel p8 = new JLabel("........");
	JLabel p9 = new JLabel(".........");
	
	//declaramos la variable hilo
	hilo hiloo = new hilo();
	
	public Splash()
	{
		
		//Iniciamos el hilo el cual ejecutara la acción
		hiloo.start();
		//agregamos los puntos al campo
		campos.add(p1).setBounds(110,190,30,20);
		campos.add(p2).setBounds(110,190,40,20);
		campos.add(p3).setBounds(110,190,50,20);
		campos.add(p4).setBounds(110,190,60,20);
		campos.add(p5).setBounds(110,190,70,20);
		campos.add(p6).setBounds(110,190,80,20);
		campos.add(p7).setBounds(110,190,90,20);
		campos.add(p8).setBounds(110,190,100,20);
		campos.add(p9).setBounds(110,190,110,20);
		//agregamos al campo el fondo
		campos.add(fondo).setBounds(10,0,350,220);
		//agregamos el label de la version
		campos.add(version).setBounds(190,68,300,20);
		//ponemos todos los puntos ocultos
		p1.setVisible(false);
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		p5.setVisible(false);
		p6.setVisible(false);
		p7.setVisible(false);
		p8.setVisible(false);
		p9.setVisible(false);
		//añadimos el tipo de fuente que va a llevar la letra de la version y el tamaño
		version.setFont(new Font("Monotype Corsiva", Font.BOLD, 23));
		//le asignamos un color azul al tipo de letra
		version.setForeground(Color.blue);
		//asignmos un tipo de fuente a todos los puntos
		p1.setFont(new Font("Papyrus", Font.BOLD, 30));
		p2.setFont(new Font("Papyrus", Font.BOLD, 30));
		p3.setFont(new Font("Papyrus", Font.BOLD, 30));
		p4.setFont(new Font("Papyrus", Font.BOLD, 30));
		p5.setFont(new Font("Papyrus", Font.BOLD, 30));
		p6.setFont(new Font("Papyrus", Font.BOLD, 30));
		p7.setFont(new Font("Papyrus", Font.BOLD, 30));
		p8.setFont(new Font("Papyrus", Font.BOLD, 30));
		p9.setFont(new Font("Papyrus", Font.BOLD, 30));
		//agregamos al contenedor los campos 
		cont.add(campos);
		//asignamos un titulo a la pantalla
		this.setTitle("Scoi Beta");
		//declaramos la salida de la pantalla
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//eliminamos todo el decorado de la pantalla
		this.setUndecorated(true);
		//asignamos que no se pueda maximizar ni minimizar
		this.setResizable(false);
		//asignamos el tamaño
		this.setSize(370,240);
		//centramos la pantalla con el setLocationRelativeTo(null)
		this.setLocationRelativeTo(null);
	}
	
	//declaramos la clase hilo la cual extenderemos del thread
	public class hilo extends Thread
	{
		//declaramos un run
		public void run()
		{
			//iniciamos un ciclo for
			for (int i = 0; i <=100; i++) 
				{
				//si i==10,20,30,etc... ponemos los puntos en visibles
				if(i==10){p1.setVisible(true);}
				if(i==20){p2.setVisible(true);}
				if(i==30){p3.setVisible(true);}
				if(i==40){p4.setVisible(true);}
				if(i==50){p5.setVisible(true);}
				if(i==60){p6.setVisible(true);}
				if(i==70){p7.setVisible(true);}
				if(i==80){p8.setVisible(true);}
				if(i==90){p9.setVisible(true);}
				//si i==100 cerramos la pantalla y iniciamos el main
				if (i==100)
				{
					dispose();
					new Main().setVisible(true);
				}
				//dentro del try catch asignamos la velocidad del hilo 
					try 
					{
						sleep(100);
					} catch (Exception e) {}
				}
			}
		}

}