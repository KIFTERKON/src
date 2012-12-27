package catalogos;

import java.awt.Container;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import SQL.Connexion;


@SuppressWarnings("serial")
public class Cat_Lista_Deudores_Prestamo extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	DefaultTableModel model = new DefaultTableModel(0,8){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(model);
	JScrollPane scroll = new JScrollPane(tabla);
		
	
	public Cat_Lista_Deudores_Prestamo(){
		this.setTitle("Lista De Prestamos");
		
		Etiqueta();

		panel.add(scroll).setBounds(50,100,800,500);
		
		String[][] Tabla = getMatriz();
		Object[] fila = new Object[tabla.getColumnCount()]; 
		for(int i=0; i<Tabla.length; i++){
			model.addRow(fila); 
			for(int j=0; j<3; j++){
				model.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		
		cont.add(panel);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setBounds( 10, 10, 910, 660);
	}
	

	public void Etiqueta(){
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		int a=2;
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(a+=1).setCellRenderer(tcr);
		
		int fila=0;
		tabla.getColumnModel().getColumn(fila).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(60);
		tabla.getColumnModel().getColumn(fila).setMinWidth(60);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Folio Empleado");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(110);
		tabla.getColumnModel().getColumn(fila).setMinWidth(110);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(230);
		tabla.getColumnModel().getColumn(fila).setMinWidth(230);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Establecimiento");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(100);
		tabla.getColumnModel().getColumn(fila).setMinWidth(100);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Fecha In");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(70);
		tabla.getColumnModel().getColumn(fila).setMinWidth(70);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Prestamo");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(70);
		tabla.getColumnModel().getColumn(fila).setMinWidth(70);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Descuento");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(90);
		tabla.getColumnModel().getColumn(fila).setMinWidth(90);
		tabla.getColumnModel().getColumn(fila+=1).setHeaderValue("Saldo");
		tabla.getColumnModel().getColumn(fila).setMaxWidth(70);
		tabla.getColumnModel().getColumn(fila).setMinWidth(70);
		
	
	}
	
	public String[][] getMatriz(){
		String qry = "select tb_fuente_sodas_rh.folio_empleado as Num," + 
        					"tb_fuente_sodas_rh.nombre_completo as Nombre," +
        					"sum(tb_fuente_sodas_rh.cantidad)as Total " +
        					"from tb_fuente_sodas_rh " +
        				"where tb_fuente_sodas_rh.status ='1' " +
        					"group by tb_fuente_sodas_rh.nombre_completo,tb_fuente_sodas_rh.folio_empleado "+
        					"order by tb_fuente_sodas_rh.folio_empleado";
		
		String[][] Matriz = new String[getFilas(qry)][8];
		Connection conn = Connexion.conexion();
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				Matriz[i][1] = rs.getString(1).trim();
				Matriz[i][2] = rs.getString(2).trim();
				Matriz[i][3] = rs.getString(3).trim();

				i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}

	public static int getFilas(String qry){
		int filas=0;
		try {
			Connection conn = Connexion.conexion();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	
	public static void main(String [] args){
		new Cat_Lista_Deudores_Prestamo().setVisible(true);
	}
}
