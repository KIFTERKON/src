package checador;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Cat_Checador_Selecion_Comida extends JDialog{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	JButton btn_comida = new JButton("Comida Normal"); 
	JButton btn_receso15 = new JButton("Break 15 Min"); 
	
	int folio_empleado;
	String tipo_entrada;
	
	int tipo_salida_comer;

	public Cat_Checador_Selecion_Comida (int folio,String TipoDeEntra){
		
		this.setModal(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Selecciona El Tipo de Salida"));
		this.setTitle("Selecciona El Tipo de Salida");
		
		folio_empleado=folio;
		tipo_entrada=TipoDeEntra;
		
		panel.add(btn_comida).setBounds(80,70,150,20);
		panel.add(btn_receso15).setBounds(80,140,150,20);
		
		this.btn_comida.addActionListener(comida2h);
		this.btn_receso15.addActionListener(receso15); 
		
		this.setSize(300,200);
        cont.add(panel);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}

	   ActionListener comida2h =new ActionListener() 
	   {@Override
			public  void actionPerformed(ActionEvent e) {
			tipo_salida_comer=1;
//				Cat_Checador.refresh(folio_empleado,tipo_entrada,tipo_salida_comer);
				Cat_Checador.fila2(folio_empleado,tipo_entrada,tipo_salida_comer);
				dispose();
			}
	   };
	
	ActionListener receso15 =new ActionListener()
	{@Override
		public void actionPerformed(ActionEvent e) {
			tipo_salida_comer=2;
//			Cat_Checador.refresh(folio_empleado,tipo_entrada,tipo_salida_comer);
			Cat_Checador.fila2(folio_empleado,tipo_entrada,tipo_salida_comer);
			dispose();
		}
	};	
}


