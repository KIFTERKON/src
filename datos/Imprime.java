package datos;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import catalogos.Cat_Filtro_Cortes;

import java.util.*;

@SuppressWarnings("serial")
public class Imprime extends JFrame
{
	Container container = getContentPane();
	private JLabel jLabel1;

//Declarar Imagen para Txa	
	ImageIcon img = new ImageIcon("imagen/logo-ticket.png");
	
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton jButImprime;
	private JPanel contentPane;

	@SuppressWarnings("resource")
	public Imprime()
	{
		jLabel1 = new JLabel();
		
//		Declarar Txa y Asignarle imagen
		jTextArea1 = new JTextArea(){Image image = img.getImage();
			Image grayImage = GrayFilter.createDisabledImage(image);{
				setOpaque(false);
			}
			public void paint(Graphics g){
				g.drawImage(grayImage,10,10,this);
				super.paint(g);
			}
		};
			
		jScrollPane1 = new JScrollPane();
		jButImprime = new JButton();
		contentPane = (JPanel)this.getContentPane();

//		jLabel1.setText("Archivo:");
		jScrollPane1.setViewportView(jTextArea1);
		
		jButImprime.setHorizontalTextPosition(SwingConstants.CENTER);
		jButImprime.setText("Imprimir");
		jButImprime.setToolTipText("Imprimir");
		jButImprime.setVerticalAlignment(SwingConstants.TOP);
		jButImprime.setVerticalTextPosition(SwingConstants.BOTTOM);
		jButImprime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				jButImprime_actionPerformed(e);
			}
		});

		contentPane.setLayout(null);
		
//		jLabel1.setBounds(14,19,487,18);
		jScrollPane1.setBounds(14,44,220,230);
		jButImprime.setBounds(14,285,100,20);
		
		container.add(jLabel1);
		container.add(jScrollPane1);
		container.add(jButImprime);

		this.setTitle("Imprimir Ticket");
		this.setLocation(new Point(280, 170));
		this.setSize(new Dimension(250, 380));
		
		Font font = new Font("",Font.PLAIN,7);
		jTextArea1.setFont(font);
		//LLena el text area Con el archivo ticket
//		System.out.println("\njButAbrir_actionPerformed(ActionEvent e) called.");
	    char[] data;	        
	    File f = new File (System.getProperty("user.dir") + "\\DbTiket\\Ticket.txt");
	        try 
	        {
	          FileReader fin = new FileReader (f);
	          int filesize   = (int)f.length();
	          data 			 = new char[filesize];
	          fin.read (data, 0, filesize);
	        }catch (FileNotFoundException exc){
	          String errorString = "No se Encontro Archivo: "+ System.getProperty("user.dir") + "\\DbTicket\\Ticket.txt";
	          data = errorString.toCharArray ();
	        } 
	        catch (IOException exc){
	          String errorString = "Tipo de Error: " + System.getProperty("user.dir") + "\\DbTiket\\Ticket.txt";
	          data = errorString.toCharArray ();
	        }
//	        jLabel1.setText ("Archivo: " + System.getProperty("user.dir") + "\\DbTiket\\Ticket.txt");
	        jTextArea1.setText (new String (data));
	        setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

	        jTextArea1.setEditable(false);
		this.setVisible(true);
	}
	
	private void jButImprime_actionPerformed(ActionEvent e)
	{
		try{
	 		Properties defaultProps = new Properties();
	 		
			PrintJob print=Toolkit.getDefaultToolkit().getPrintJob(this,"Imprimir",defaultProps);
			Graphics g=print.getGraphics();
			if(g!=null){
				jTextArea1.printAll(g);
			}
			g.dispose();
			print.end();
			dispose();
			new Cat_Filtro_Cortes().setVisible(true);
			
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null,"      Ha ocurrido un error\nNo se encontro La Impresora.");
			return;
		}
	}
}
