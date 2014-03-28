package catalogos;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import SQL.BuscarTablasModel;
import SQL.Cargar_Combo;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Lay_Out extends JDialog {

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser fecha = new JDateChooser();
	
	public String[] Combo_Tipo_Banco(){ 
	try {
			return new Cargar_Combo().Tipo_Banco("tb_tipo_banco");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	String TipoBanco[] = Combo_Tipo_Banco();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbTipoBancos = new JComboBox(TipoBanco);
	
	JButton btnGenerer = new JButton("Generar");
	
	public Cat_Lay_Out(){
		this.setModal(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		this.setTitle("LayOut");
		panel.setBorder(BorderFactory.createTitledBorder("Generer LayOut"));
		
		panel.add(new JLabel("Tipo De Banco: ")).setBounds(20, 30, 140, 20);
		panel.add(cmbTipoBancos).setBounds(120, 30, 200, 20);
		
		panel.add(new JLabel("Fecha: ")).setBounds(65, 60, 80, 20);
		panel.add(fecha).setBounds(120, 60, 100, 20);
		panel.add(btnGenerer).setBounds(230, 60, 90, 20);
		
		btnGenerer.addActionListener(opGenerer);
		
		cont.add(panel);
		this.setSize(350, 130);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opGenerer = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
		if(validaCampos()!="") {
			JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			return;
		}else{
			
				String tipo_banco = cmbTipoBancos.getSelectedItem().toString().toUpperCase();
				String fecha_mov = new SimpleDateFormat("dd/MM/yyyy").format(fecha.getDate());
				
			    Object [][] lista_tabla = new BuscarTablasModel().tabla_model_lay_out(tipo_banco,fecha_mov);
			    String[] fila = new String[1];
			    String[] fila2 = new String[1];
		    
			    String nombre = "";
				        fila[0] = lista_tabla[lista_tabla.length-1][0]+"              ";
				        nombre=fila[0].toString().trim();
//				        System.out.println(lista_tabla.length);
				        
					try {
						  File archivo=new File("C:\\"+nombre);
						  FileWriter escribir = new FileWriter(archivo,true);
						  BufferedWriter bw = new BufferedWriter(escribir); 
						
					      fila[0] = lista_tabla[lista_tabla.length-2][0]+"";
//					      System.out.println(fila[0].toString());
					      bw.write(fila[0].toString().trim()+"                                                                             ");// 77 ESPACIOS VACIOS
					      bw.newLine();

					      for(int i=0; i<lista_tabla.length-2; i++){
					    	  fila2[0] = lista_tabla[i][0]+"";
//					    	  System.out.println(fila2[0].toString());
					    	  bw.write(fila2[0].toString().trim()+"                  ");// 18 ESPACIOS VACIOS 
					    	  bw.newLine();
					      }
					    bw.close();
					    
					    dispose();
					    JOptionPane.showMessageDialog(null, "El Archivo Se Generó Exitosamente", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
			            
					} catch (IOException e1) {
						e1.printStackTrace();
						
						JOptionPane.showMessageDialog(null, "El Archivo No Se Genero Correctamente", "Error al Generar Archivo", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}
				}
		}
	};
	
	private String validaCampos(){
		String error="";
		if(cmbTipoBancos.getSelectedIndex()==0)				error+= "* Banco\n";
		if(fecha.getDate()==null)							error+= "* Fecha";
				
		return error;
	}
	
	public static void main(String [] arg){
		new Cat_Lay_Out().setVisible(true);
	}
}
