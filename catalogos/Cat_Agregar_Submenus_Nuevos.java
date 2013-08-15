package catalogos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objetos.Obj_Agregar_Submenus_Nuevos;
import objetos.Obj_Mantenimiento_Base_de_Datos;

@SuppressWarnings("serial")
public class Cat_Agregar_Submenus_Nuevos extends JFrame  {

	Container cont=getContentPane();
	JLayeredPane panel=new JLayeredPane();
	JTextField txtsubmenu =new JTextField () ; 
	JTextField txtcatsubmenu =new JTextField () ; 
	String lista [] = new Obj_Agregar_Submenus_Nuevos().Combo_Menus(); 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox  cmbmenuid =new JComboBox	(lista);
    JButton btn_guardar =new JButton("Guardar");
	
    public Cat_Agregar_Submenus_Nuevos () {
    	  
    	   this.setTitle("Agregar Submenus Nuevos");
    	   
    	    this.panel.add(new JLabel("Nombre de Submenu:")).setBounds(10,50,200,20);
        	this.panel.add(txtsubmenu).setBounds(150,50,200,20);
        	this.txtsubmenu.setToolTipText("Se debe de Poner el nombre del Submenu que pones entre comillas");
        	this.panel.add(new JLabel("Nombre del Catalogo:")).setBounds(10,100,200,20);
		    this.panel.add(txtcatsubmenu).setBounds(150,100,200,20);
		    this.txtcatsubmenu.setToolTipText("Se Debe de Poner el nombre de la variable del Catalogo nuevo sin punto java");
		    this.cont.add(panel);
		    this.setSize(400,400); 
		    this.setResizable(false);
		    this.setLocationRelativeTo(null);
		    this.panel.add(cmbmenuid).setBounds(150,150,200,20);
		    this.panel.add(btn_guardar).setBounds(150,300,80,20);
		    this.btn_guardar.addActionListener(Guardar);
		    
		  
    }
	
ActionListener Guardar =new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			   //aqui se obtiene lo que traiga el texto del textfield txtsubmenu
			
			if(!txtsubmenu.getText().equals("")){
                         				
				   if(!new Obj_Agregar_Submenus_Nuevos().existe(txtsubmenu.getText()) ){
					
						//variable instancia para
						  Obj_Agregar_Submenus_Nuevos agregar= new Obj_Agregar_Submenus_Nuevos();
						  agregar.setNombre(txtsubmenu.getText());
						  agregar.setMenu(cmbmenuid.getSelectedItem().toString());
						  agregar.setCatalogo(txtcatsubmenu.getText());   
						  if(agregar.guardar()){JOptionPane.showMessageDialog(null,"Se Guardo Exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						  
						          if(new Obj_Mantenimiento_Base_de_Datos().agregar_submenus_nuevos()==true ){
							         JOptionPane.showMessageDialog(null,"Se Cargaron Satisfactoriamente","Aviso",JOptionPane.INFORMATION_MESSAGE); 
							         dispose();}
							      else{JOptionPane.showMessageDialog(null,"No se Pudo Ejecutar El Procedimiento","Aviso",JOptionPane.ERROR_MESSAGE);  }
						        }
						     else{JOptionPane.showMessageDialog(null,"No se Puedo Guardar","Aviso",JOptionPane.ERROR_MESSAGE);
						        } 
				   }
				   
				   else{JOptionPane.showMessageDialog(null,"El Registro ya Existe","Aviso",JOptionPane.WARNING_MESSAGE);  }
			   }
			else{JOptionPane.showMessageDialog(null,"El  campo Submenu esta Vacio","Aviso",JOptionPane.WARNING_MESSAGE);				
			}
			
			
			
			
			}
			
			
			
		}
	;
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new Cat_Agregar_Submenus_Nuevos().setVisible(true);
	}

}
