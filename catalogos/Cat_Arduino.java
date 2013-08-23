package catalogos;

import java.awt.Container;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;

import ObjetoChecador.Obj_Hora_Sincronizada;


@SuppressWarnings("serial")
public class Cat_Arduino extends JFrame
{
	
	/*hacer unos ciclos para comparar preguntar cuando el reloj sea igual a lo que trae el spinner
	 * **/
	Container cont =getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	
	Date date = new Date();
	 
	SpinnerDateModel maña =new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	JSpinner mañana = new JSpinner(maña);
	JSpinner.DateEditor ma = new JSpinner.DateEditor(mañana, "H:mm ");
	
	SpinnerDateModel medio =new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	JSpinner mediodia = new JSpinner(medio);
	JSpinner.DateEditor me = new JSpinner.DateEditor(mediodia, "H:mm");
	
	SpinnerDateModel tar =new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
	JSpinner tarde = new JSpinner(tar);
	JSpinner.DateEditor ta = new JSpinner.DateEditor(tarde, "H:mm");
	
	
	
	JLabel lblHora = new JLabel();
	segundero segundo =new segundero();
	
	int reconsultar;
	
	public Cat_Arduino()
	{
		this.setTitle("Arduino");
		mañana.setEditor(ma);
		mediodia.setEditor(me);
		tarde.setEditor(ta);
		
		panel.add(new JLabel("Hora Mañana:")).setBounds(20,20,80,20);
		panel.add(mañana).setBounds(120,20,70,20);
		
		panel.add(new JLabel("Hora Mediodia:")).setBounds(20,80,90,20);
		panel.add(mediodia).setBounds(120,80,70,20);
		
		panel.add(new JLabel("Hora Tarde:")).setBounds(20,140,80,20);
		panel.add(tarde).setBounds(120,140,70,20);
		
		panel.add(lblHora).setBounds(350,20,120,50);
		lblHora.setFont(new Font("Algerian",Font.BOLD,40));
		
		
		this.setSize(500,250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont.add(panel);
		segundo.start();
		preguntas();
	}
	
	public void preguntas()
	{
		Obj_Hora_Sincronizada sincro = new Obj_Hora_Sincronizada();
		
		if (reconsultar==300) {
			if(mañana.getValue()==sincro.get_hora_minuto_segundo())
			{
				JOptionPane.showMessageDialog(null, "Son iguales");
			}else{
				JOptionPane.showMessageDialog(null, "No Son iguales");
			}
		}
		
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
					            lblHora.setText(h+":"+m/*+":"+s*/);
								
							}else{
							 	h = i > 9 ? "" + i : "0" + i;
					            m = j > 9 ? "" + j : "0" + j;
					            s = z > 9 ? "" + z : "0" + z;
					            
//								System.out.println("Hora entrada: [ "+h+":"+m+":"+s+" ]");
								lblHora.setText(h+":"+m/*+":"+s*/);
							}
							
							try {
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