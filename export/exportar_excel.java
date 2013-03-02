package export;

import java.io.*;
import java.util.List;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

public class exportar_excel {

	 private File archi;
	    private List<JTable> tabla;
	    private List<String> nom_hoja;

	    public exportar_excel(List<JTable> tab, File ar, List<String> nom) throws Exception {
	        this.archi = ar;
	        this.tabla = tab;
	        this.nom_hoja = nom;
	        if(nom.size()!=tab.size()){
	            throw new Exception("ERROR");
	        }
	    }

	    public boolean export() {
	        try {
	            DataOutputStream out = new DataOutputStream(new FileOutputStream(archi));
	            WritableWorkbook w = Workbook.createWorkbook(out);
	            for (int index=0;index<tabla.size();index++) {
	                JTable table=tabla.get(index);
	                WritableSheet s = w.createSheet(nom_hoja.get(index), 0);

	                for (int i = 0; i < table.getColumnCount(); i++) {
	                    for (int j = 0; j < table.getRowCount(); j++) {
	                        Object objeto = table.getValueAt(j, i);
	                        s.addCell(new Label(i, j, String.valueOf(objeto)));
	                    }
	                }
	            }
	            w.write();
	            w.close();
	            out.close();
	            return true;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } catch (WriteException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    }
	
	
	
}
