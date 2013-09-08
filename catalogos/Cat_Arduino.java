package catalogos;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import objetos.Obj_Arduino;



import ObjetoChecador.Obj_Hora_Sincronizada;


@SuppressWarnings("serial")
public class Cat_Arduino extends JFrame
{
	/**hacer unos ciclos para comparar preguntar cuando el reloj sea igual a lo que trae el spinner**/
	Container cont =getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	
	Obj_Arduino arduino = new Obj_Arduino().Buscar();
	
	JTextField txtMañana = new JTextField();
	JTextField txtMediodia = new JTextField();
	JTextField txtTarde = new JTextField();
	
	
	
	JLabel lblHora = new JLabel();
	segundero segundo =new segundero();
	
	int reconsultar;
	
	public Cat_Arduino()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Arduino");
		this.panel.setBorder(BorderFactory.createTitledBorder("Arduino"));
		
		panel.add(new JLabel("Hora Corte Mañana:")).setBounds(20,20,100,20);
		panel.add(txtMañana).setBounds(130,20,55,20);
		
		panel.add(new JLabel("Hora Corte Mediodia:")).setBounds(20,80,120,20);
		panel.add(txtMediodia).setBounds(130,80,55,20);
		
		panel.add(new JLabel("Hora Corte Tarde:")).setBounds(20,140,100,20);
		panel.add(txtTarde).setBounds(130,140,55,20);
		
		txtMañana.setText(arduino.getMañana());
		txtMediodia.setText(arduino.getMediodia());
		txtTarde.setText(arduino.getTarde());
		
		txtMañana.setEditable(false);
		txtMediodia.setEditable(false);
		txtTarde.setEditable(false);
		
		panel.add(lblHora).setBounds(300,20,200,50);
		lblHora.setFont(new Font("Algerian",Font.BOLD,40));
		
		this.setSize(500,250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont.add(panel);
		segundo.start();
		
	}

	public class segundero extends Thread {
		public void run() {
			
			String h;
			String m;
			String s;
			
			int[] hora = new Obj_Hora_Sincronizada().get_hora_minuto_segundo();
			int bandera = 0;
			while(true){
				
				for(int i=0; i<24; i++){
					for(int j=0; j<60; j++){
						for(int z=0; z<60; z++){
							if(bandera==0){
								i=hora[0];
								j=hora[1];
								z=hora[2]++;
								bandera++;
								
								
								
					            h = i > 9 ? "" + i : "0" + i;
					            m = j > 9 ? "" + j : "0" + j;
					            s = z > 9 ? "" + z : "0" + z;
					            
//								System.out.println("Hora entrada: [ "+h+":"+m+":"+s+" ]");
					            lblHora.setText(h+":"+m+":"+s);
							}else{
							 	h = i > 9 ? "" + i : "0" + i;
					            m = j > 9 ? "" + j : "0" + j;
					            s = z > 9 ? "" + z : "0" + z;
					            
//								System.out.println("Hora entrada: [ "+h+":"+m+":"+s+" ]");
								lblHora.setText(h+":"+m+":"+s);
							}
							
							try {
								
								if(txtMañana.getText().toString().equals(lblHora.getText()))
								{
									System.out.println("la hora concuerda con el campo 1");
									new Cat_Mensajes().setVisible(true);
								}
								
								if(txtMediodia.getText().toString().equals(lblHora.getText())){
									System.out.println("la hora concuerda con el campo 2");
								}
								
								if(txtTarde.getText().toString().equals(lblHora.getText()))
								{
									System.out.println("la hora concuerda con el campo3");
								}
								
								Thread.sleep(1000);
								reconsultar+=1;
								if(reconsultar==300)
								{
									reconsultar=0;
									run();
								}
							} catch (InterruptedException e) {
								System.err.println("Error: "+ e.getMessage());
							}
						}
						}
					}
				}
			}
		}
				
	
	public static void main(String[]a)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Arduino().setVisible(true);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}