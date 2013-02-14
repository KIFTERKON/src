package frames;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import catalogos.Cat_Bancos;
import catalogos.Cat_Comprobar_Fuente_Sodas_RH;
import catalogos.Cat_Deduccion_Inasistencia;
import catalogos.Cat_Empleado;
import catalogos.Cat_Filtro_Diferiencia_Cortes;
import catalogos.Cat_Filtro_Fue_Soda_Auxf;
import catalogos.Cat_Filtro_Fue_Soda_Rh;
import catalogos.Cat_Filtro_Prestamo;
import catalogos.Cat_Lista_Deudores_Prestamo;
import catalogos.Cat_Lista_Pago;
import catalogos.Cat_Lista_Raya;
import catalogos.Cat_Percepciones_Extra;
import catalogos.Cat_Puesto;
import catalogos.Cat_Sueldo;

@SuppressWarnings("serial")
public class Frm_Principal2 extends JDialog{
		
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	String Inasistencia =("Deduccion por Inasistencia");
		
	JButton lblBanco= new JButton(new ImageIcon("imagen/banco.png"));
	JLabel lblBanco2= new JLabel("Banco");
	JButton lblInasistencia= new JButton(new ImageIcon("imagen/inasistencia.png"));
	JLabel lblInasistencia2= new JLabel("Deduccion por");
	JLabel lblInasistencia3= new JLabel("Inasistencia");
	
	JButton lblCaja= new JButton(new ImageIcon("imagen/caja2.png"));
	JLabel lblCaja2= new JLabel("Diferencia de");
	JLabel lblCaja3= new JLabel("Cortes");
	
	JButton lblFsRH= new JButton(new ImageIcon("imagen/fsRH.png"));
	JLabel lblFsRH2= new JLabel("Fuente de Sodas");
	JLabel lblFsRH3= new JLabel("DH");
	
	JButton lblFsAux= new JButton(new ImageIcon("imagen/fsAux.png"));
	JLabel lblFsAux2= new JLabel("Fuente de Sodas");
	JLabel lblFsAux3= new JLabel("Auxiliar y Finanzas");
	
	JButton lblPExtras= new JButton(new ImageIcon("imagen/PExtra.png"));
	JLabel lblPExtras2= new JLabel("Percepciones");
	JLabel lblPExtras3= new JLabel("Extras");
	
	JButton lblPrestamo= new JButton(new ImageIcon("imagen/prestamo.png"));
	JLabel lblPrestamo2= new JLabel("Prestamos");
	
	JButton lblAltaEmp= new JButton(new ImageIcon("imagen/altaEmp.png"));
	JLabel lblAltaEmp2= new JLabel("Alta");
	JLabel lblAltaEmp3= new JLabel("Empleados");
	
	JButton lblPuesto= new JButton(new ImageIcon("imagen/puesto.png"));
	JLabel lblPuesto2= new JLabel("Puesto");
	JButton lblSueldo= new JButton(new ImageIcon("imagen/sueldo.png"));
	JLabel lblSueldo2= new JLabel("Sueldo");
	
	JButton lblRevicion= new JButton(new ImageIcon("imagen/rebicionTotales.png"));
	JLabel lblRevicion2= new JLabel("Revision de");
	JLabel lblRevicion3= new JLabel("Totales");
	
	JButton lblListaRaya= new JButton(new ImageIcon("imagen/listaR.png"));
	JLabel lblListaRaya2= new JLabel("Lista de");
	JLabel lblListaRaya3= new JLabel("Raya");
	
	JButton lblListaFirma= new JButton(new ImageIcon("imagen/listaF.png"));
	JLabel lblListaFirma2= new JLabel("Lista de");
	JLabel lblListaFirma3= new JLabel("Firmas");
	
	JButton lblListaPrestamo= new JButton(new ImageIcon("imagen/listaP.png"));
	JLabel lblListaPrestamo2= new JLabel("Lista de");
	JLabel lblListaPrestamo3= new JLabel("Prestamos");
	
	JButton lblListaComparacion= new JButton(new ImageIcon("imagen/comparacion.png"));
	JLabel lblListaComparacion2= new JLabel("Lista de");
	JLabel lblListaComparacion3= new JLabel("Comparacion FS");
	
	private Dimension dim; 
	public Frm_Principal2()	{

		campo.add(new JLabel(new ImageIcon("imagen/LogPrincipal.png"))).setBounds(980,0,400,218);
		campo.add(lblBanco).setBounds(30,40,64,64);
		campo.add(lblInasistencia).setBounds(30,164,64,64);
		campo.add(lblCaja).setBounds(30,288,64,64);
		campo.add(lblFsRH).setBounds(30,412,64,64);
		campo.add(lblFsAux).setBounds(30,536,64,64);		
		
		campo.add(lblBanco2).setBounds(124,60,60,20);
		campo.add(lblInasistencia2).setBounds(124,184,120,20);
		campo.add(lblInasistencia3).setBounds(124,194,120,20);
		campo.add(lblCaja2).setBounds(124,308,64,20);
		campo.add(lblCaja3).setBounds(124,318,64,20);
		campo.add(lblFsRH2).setBounds(124,432,90,20);
		campo.add(lblFsRH3).setBounds(124,442,64,20);
		campo.add(lblFsAux2).setBounds(124,556,90,20);
		campo.add(lblFsAux3).setBounds(124,566,90,20);
		
		campo.add(lblPExtras).setBounds(218,40,64,64);
		campo.add(lblPrestamo).setBounds(218,164,64,64);
		
		campo.add(lblPExtras2).setBounds(312,60,64,20);
		campo.add(lblPExtras3).setBounds(312,70,64,20);
		campo.add(lblPrestamo2).setBounds(312,184,64,20);

		campo.add(lblAltaEmp).setBounds(406,40,64,64);
		campo.add(lblPuesto).setBounds(406,164,64,64);
		campo.add(lblSueldo).setBounds(406,288,64,64);
		
		campo.add(lblAltaEmp2).setBounds(500,60,64,20);
		campo.add(lblAltaEmp3).setBounds(500,70,64,20);
		campo.add(lblPuesto2).setBounds(500,184,64,20);
		campo.add(lblSueldo2).setBounds(500,308,64,20);
		
		campo.add(lblListaRaya).setBounds(594,40,64,64);
		campo.add(lblListaFirma).setBounds(594,164,64,64);
		campo.add(lblListaPrestamo).setBounds(594,288,64,64);
		campo.add(lblListaComparacion).setBounds(594,412,64,64);
		campo.add(lblRevicion).setBounds(594,536,64,64);
		
		campo.add(lblListaRaya2).setBounds(688,60,64,20);
		campo.add(lblListaRaya3).setBounds(688,70,64,20);
		campo.add(lblListaFirma2).setBounds(688,184,64,20);
		campo.add(lblListaFirma3).setBounds(688,194,64,20);
		campo.add(lblListaPrestamo2).setBounds(688,308,64,20);
		campo.add(lblListaPrestamo3).setBounds(688,318,64,20);
		campo.add(lblListaComparacion2).setBounds(688,432,64,20);
		campo.add(lblListaComparacion3).setBounds(688,442,90,20);
		campo.add(lblRevicion2).setBounds(688,556,64,20);
		campo.add(lblRevicion3).setBounds(688,566,64,20);
		
		lblBanco.addMouseListener(opBanco);
//		lblBanco2.addMouseListener(opBanco);
		lblInasistencia.addMouseListener(opInasistencia);
//		lblInasistencia2.addMouseListener(opInasistencia);
//		lblInasistencia3.addMouseListener(opInasistencia);
		lblCaja.addMouseListener(opCortes);
//		lblCaja2.addMouseListener(opCortes);
//		lblCaja3.addMouseListener(opCortes);
		lblFsRH.addMouseListener(opFSRH);
//		lblFsRH2.addMouseListener(opFSRH);
//		lblFsRH3.addMouseListener(opFSRH);
		lblFsAux.addMouseListener(opFSAuxF);
//		lblFsAux2.addMouseListener(opFSAuxF);
//		lblFsAux3.addMouseListener(opFSAuxF);
		lblPExtras.addMouseListener(opPersecciones);
//		lblPExtras2.addMouseListener(opPersecciones);
//		lblPExtras3.addMouseListener(opPersecciones);
		lblPrestamo.addMouseListener(opPrestamo);
//		lblPrestamo2.addMouseListener(opPrestamo);
		lblAltaEmp.addMouseListener(opEmpleado);
//		lblAltaEmp2.addMouseListener(opEmpleado);
//		lblAltaEmp3.addMouseListener(opEmpleado);
		lblPuesto.addMouseListener(opPuesto);
//		lblPuesto2.addMouseListener(opPuesto);
		lblSueldo.addMouseListener(opSueldo);
//		lblSueldo2.addMouseListener(opSueldo);
		lblListaRaya.addMouseListener(opLRaya);
//		lblListaRaya2.addMouseListener(opLRaya);
//		lblListaRaya3.addMouseListener(opLRaya);
		lblListaFirma.addMouseListener(opLPago);
//		lblListaFirma2.addMouseListener(opLPago);
//		lblListaFirma3.addMouseListener(opLPago);
		lblListaPrestamo.addMouseListener(opLDeudores);
//		lblListaPrestamo2.addMouseListener(opLDeudores);
//		lblListaPrestamo3.addMouseListener(opLDeudores);
		lblListaComparacion.addMouseListener(opComprobarFS);
//		lblListaComparacion2.addMouseListener(opComprobarFS);
//		lblListaComparacion3.addMouseListener(opComprobarFS);
			
		cont.add(campo);
		
		dim=super.getToolkit().getScreenSize(); 
		this.setSize(dim); 		
	
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	public JComponent getBase(){
		return campo;
	}
	MouseListener opBanco = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Bancos().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opInasistencia = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Deduccion_Inasistencia().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opCortes = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Filtro_Diferiencia_Cortes().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opFSRH = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Filtro_Fue_Soda_Rh().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opFSAuxF = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Filtro_Fue_Soda_Auxf().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};	
	MouseListener opPersecciones = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Percepciones_Extra().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};	
	MouseListener opPrestamo = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Filtro_Prestamo().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};	
	MouseListener opEmpleado = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Empleado().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};	
	MouseListener opPuesto = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Puesto().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opSueldo = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Sueldo().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opLRaya = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Lista_Raya().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opLPago = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Lista_Pago().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opLDeudores = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Lista_Deudores_Prestamo().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	MouseListener opComprobarFS = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			new Cat_Comprobar_Fuente_Sodas_RH().setVisible(true);
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};		
}
