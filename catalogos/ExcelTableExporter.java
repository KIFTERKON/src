package catalogos;

import java.io.*;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

public class ExcelTableExporter {
	private File file;
	private JTable table;
	private String nombreTab;
	
	public ExcelTableExporter(JTable table,File file,String nombreTab){
	this.file=file;
	this.table=table;
	this.nombreTab=nombreTab;
	}
	public boolean export(){
		try
		{
		//Nuestro flujo de salida para apuntar a donde vamos a escribir
		DataOutputStream out=new DataOutputStream(new FileOutputStream(file));
		//Representa nuestro archivo en excel y necesita un OutputStream para saber donde va locoar los datos
		WritableWorkbook w = Workbook.createWorkbook(out);
		//Como excel tiene muchas hojas esta crea o toma la hoja
		//Coloca el nombre del "tab" y el indice del tab
		WritableSheet s = w.createSheet(nombreTab, 0);
		
				for(int i=0;i< table.getColumnCount();i++){
					for(int j=0;j<table.getRowCount();j++){
						Object objeto=table.getValueAt(j,i);
						s.addCell(new Label(j, i, String.valueOf(objeto)));
					}
				}
				w.write();
				
		//Cerramos el WritableWorkbook y DataOutputStream
		w.close();
		out.close();
		//si todo sale bien salimos de aqui con un true 
		return true;
		}catch(IOException ex){ex.printStackTrace();}
		catch(WriteException ex){ex.printStackTrace();}
		//Si llegamos hasta aqui algo salio mal
		return false;
		}
}
