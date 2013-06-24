package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Cat_Directorios extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre = new JTextField();
	JTextField txtTelefono = new JTextField();
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnLimpiar = new JButton("Limpiar");
	JButton btnSalir = new JButton("Salir");
	
	public Cat_Directorios(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Dial.png"));
		this.setTitle("Número de Teléfono");
		
		int x=30, y=25, z=80;
		
		panel.setBorder(BorderFactory.createTitledBorder("Número de Teléfono"));
		
		this.panel.add(new JLabel("Folio:")).setBounds(x, y, z, 20);
		this.panel.add(txtFolio).setBounds(x+=90, y, z, 20);
		
		this.panel.add(new JLabel("Nombre:")).setBounds(x-=90, y+=25, z, 20);
		this.panel.add(txtNombre).setBounds(x+=90,y,z+=150,20);
		
		this.panel.add(new JLabel("Teléfono Celular:")).setBounds(x-=90, y+=25, z-=80, 20);
		this.panel.add(txtTelefono).setBounds(x+=90, y, z+=80, 20);
		
		this.panel.add(btnSalir).setBounds(x-=60, y+=45, z-=150, 20);
		this.panel.add(btnLimpiar).setBounds(x+=100,y,z,20);
		this.panel.add(btnGuardar).setBounds(x+=100,y,z,20);
		
		this.btnGuardar.addActionListener(Op_Guardar);
		this.btnLimpiar.addActionListener(Op_Limpiar);
		this.btnSalir.addActionListener(Op_Salir);
		
		this.cont.add(panel);
		
		this.setSize(400,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	ActionListener Op_Guardar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	};
	
	ActionListener Op_Limpiar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	};
	
	ActionListener Op_Salir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Directorios().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
}
