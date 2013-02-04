package frames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Frm_Principal extends JComponent{
	JLayeredPane campos = new JLayeredPane();
	DefaultTableModel modelo       = new DefaultTableModel(0,20);
	JTable tabla                   = new JTable(modelo);
	JScrollPane panelScroll        = new JScrollPane(tabla);

public Frm_Principal(){

		tabla.getColumnModel().getColumn(0).setHeaderValue("Nº");
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre");
		tabla.getColumnModel().getColumn(1).setMaxWidth(120);
		tabla.getColumnModel().getColumn(1).setMinWidth(120);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Sueldo");
		tabla.getColumnModel().getColumn(3).setHeaderValue("SP Inicial");
		tabla.getColumnModel().getColumn(4).setHeaderValue("D Prestamo");
		tabla.getColumnModel().getColumn(5).setHeaderValue("Sal. Final");
		tabla.getColumnModel().getColumn(6).setHeaderValue("D FSodas");
		tabla.getColumnModel().getColumn(7).setHeaderValue("D Punt.");
		tabla.getColumnModel().getColumn(8).setHeaderValue("D Falt.");
		tabla.getColumnModel().getColumn(9).setHeaderValue("D Asist.");
		tabla.getColumnModel().getColumn(10).setHeaderValue("D Cortes");
		tabla.getColumnModel().getColumn(11).setHeaderValue("D Infonavit");
		tabla.getColumnModel().getColumn(12).setHeaderValue("D Banorte");
		tabla.getColumnModel().getColumn(13).setHeaderValue("D Banamex");
		tabla.getColumnModel().getColumn(14).setHeaderValue("D Coop");
		tabla.getColumnModel().getColumn(15).setHeaderValue("P Día Ext");
		tabla.getColumnModel().getColumn(16).setHeaderValue("P Bono");
		tabla.getColumnModel().getColumn(17).setHeaderValue("A pagar");
		tabla.getColumnModel().getColumn(18).setHeaderValue("Obs");
		tabla.getColumnModel().getColumn(19).setHeaderValue("Obs2");
		
		campos.add(panelScroll).setBounds(30,80,1230,470);
		
		campos.setBorder(BorderFactory.createTitledBorder("Lista de Raya"));
		
		
	}
	public JComponent getBase(){       
        return campos;
    } 
	
	@SuppressWarnings("unused")
	private JScrollPane getPanelTabla() 
	{		
		new Connexion();
		Connexion conn = new Connexion();

		// Creamos las columnas.
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("apellido");
		
		Statement s;
		ResultSet rs;
		try {
			s = conn.conexion().createStatement();
			rs = s.executeQuery("select folio,nombre,ap_paterno from tb_empleado");
			
			while (rs.next())
			{
			   Object [] fila = new Object[3];
			   for (int i=0;i<3;i++)
			      fila[i] = rs.getObject(i+1);
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
}

