package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import objetos.Obj_Main;
import objetos.Obj_Mantenimiento_Base_de_Datos;



public class Cat_Mantenimimiento_Base_de_Datos extends JFrame {
          //se declara la variable global cont el contenedor de la ventana
	Container cont=getContentPane();
	      //se declara la variable global panel Y se crea el panel para agregar los componentes
	JLayeredPane panel=new JLayeredPane();
	      // se declara la variable global boton rbd a este se le puede a agregar texto o una imagen dentro del parentesis
          //JButton btn_rbd=new JButton("Reducir Base de Datos");
          //JButton btn_rbd=new JButton(new ImageIcon("iconos/baja.png"));
	JButton btn_rbd=new JButton("Reducir Log BD ");
	
	      //se declara boton Agregar submenus a usuarios
	JButton btn_asubmenus=new JButton("Agregar Submenus Nuevos a Usuarios");
	
	  //se crea el constructor de la ventana la regla es que el contructor  debe de llamarse igual que la clase
     	public Cat_Mantenimimiento_Base_de_Datos (){
     		   //esto agrega un icono para que no aparezca el icono de java si no el que especifiques
     	      this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Table.png"));
     	      
     		   //se pone el nombre de que aparecera como titulo en la ventana
     		  this.setTitle("Mantenimiento a Base de Datos SCOI");
     		  
     		   //se agregan los componentes a la variable panel de jlayeredpane
     		   //se agrega el boton declarado y se leda la localizacion con el .setbounds(Xposicion,Yposicion ,Xancho, Yalto) todos
     		   //losvalores deben de ser en pixeles     		  
     		  this.panel.add(btn_rbd).setBounds(100,50,200,20);
     		  this.panel.add(btn_asubmenus).setBounds(100,100,200,20);
     		      		  
     		 
     		 //esto agrega el panel al contenedor 
		      this.cont.add(panel);
		     
		      //se liga el boton con la accionlistener 
		      this.btn_rbd.addActionListener(reducirlog);
		      this.btn_asubmenus.addActionListener(agregar_submenus_nuevos);
		      
		     //se da el el tamaño de la ventana
		      this.setSize(400,400); 
		      //para que no se modifique el tamaño
		      this.setResizable(false);
		      //esto te posiciona la venta al centro si no se pone estara al margen superior derecho
		       this.setLocationRelativeTo(null);
     
		      
       	 }
     	//se le agrega la accion al boton
 	 ActionListener reducirlog =new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub se le agrega la accion  lo siguiente seria crear un objeto con instancia
			//para que me devuelva cierto o falso si se hiso la accion
            //pregunta si la variable instancia y el objeto. funcion retornada es igual a verdadero o falso   
			if(new Obj_Mantenimiento_Base_de_Datos().reducir_Log()==true ){
				//este  habre una ventana de mensaje en la cual se
				 JOptionPane.showMessageDialog(null,"Se Redujo el log Satisfactoriamente","Aviso",JOptionPane.INFORMATION_MESSAGE); }
			else{JOptionPane.showMessageDialog(null,"No se Pudo Ejecutar la Reduccion","Aviso",JOptionPane.ERROR_MESSAGE);
			
			if(new Obj_Mantenimiento_Base_de_Datos().agregar_submenus_nuevos()==true ){
			  JOptionPane.showMessageDialog(null,"Se Cargaron Satisfactoriamente","Aviso",JOptionPane.INFORMATION_MESSAGE); }
			else{JOptionPane.showMessageDialog(null,"No se Pudo Ejecutar El Procedimiento","Aviso",JOptionPane.ERROR_MESSAGE);
			
			}
			
			
			
			}
			
			
			
		}
	};
	 ActionListener agregar_submenus_nuevos  =new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Cat_Agregar_Submenus_Nuevos().setVisible(true);				
	
				}
				
			};
		
		
	
	
	public static void main(String[] args) {
		//se  agrega al public static void mainla posiblilidad de una nuevo catalogo que se a visible
		new Cat_Mantenimimiento_Base_de_Datos().setVisible(true);
 
  	}
 	 }

