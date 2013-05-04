package catalogos;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objetos.Obj_Importar_Voucher;

@SuppressWarnings("serial")
public class Cat_Importar_Voucher extends JFrame{
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked", "resource" })
	public Cat_Importar_Voucher(){
		FileDialog file = new FileDialog(new Frame());
		file.setTitle("Selecciona una archivo txt Voucher");
		file.setMode(FileDialog.LOAD);
		file.setVisible(true);
		String ruta = file.getDirectory()+file.getFile();
		
		if(ruta == null){
			JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo", "Error al seleccionar", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			return;
		}else{
			String tipo = ruta.substring(ruta.length()-3, ruta.length()).toLowerCase();
			if(!tipo.equals("txt")){
				JOptionPane.showMessageDialog(null, "El archivo seleccionado no es uno valido \n verifique el tipo de archivo", "Error al abrir", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				
				
				Vector myVector = new Vector();
				FileReader archivo;
				
				try {
					int escape = 0;
					archivo = new FileReader(ruta);
					BufferedReader bufferedWriter = new BufferedReader(archivo);
					String cadena = "";
					while((cadena = bufferedWriter.readLine()) != null){
						if(escape != 0){
							System.out.println("Cadena del txt: " + cadena);
							
							StringTokenizer token = new StringTokenizer(cadena,"|");
							while(token.hasMoreTokens()){
								myVector.add(token.nextToken());
							}
							Obj_Importar_Voucher importar = new Obj_Importar_Voucher();
							
							importar.setContrato(Integer.parseInt(myVector.get(0).toString()));
							importar.setF_transaccion(myVector.get(1).toString());
							importar.setH_transaccion(myVector.get(2).toString());
							importar.setNo_codigo(myVector.get(3).toString());
							importar.setLeyenda(myVector.get(4).toString());
							importar.setImporte(Float.parseFloat(myVector.get(5).toString().replace("$","").replace(",", "")));
							importar.setTerminal(myVector.get(6).toString());
							importar.setCuenta(myVector.get(7).toString());
							importar.setAutorizacion(myVector.get(8).toString());
							importar.setTipo_de_cuenta(myVector.get(9).toString());
							importar.setF_abono(myVector.get(10).toString());
							importar.setReferencia_1(myVector.get(11).toString());
							importar.setReferencia_2(myVector.get(12).toString());
							importar.setReferencia_3(myVector.get(13).toString());
							importar.setQ6(Float.parseFloat(myVector.get(14).toString().replace("$","").replace(",", "")));
							importar.setImporta_cash_back(Float.parseFloat(myVector.get(15).toString().replace("$","").replace(",", "")));
							importar.setEci(Float.parseFloat(myVector.get(16).toString().replace("$","").replace(",", "")));
							importar.setControl_interno_comercio(myVector.get(17).toString());
							importar.setLote1(myVector.get(18).toString());
							importar.setLote2(myVector.get(19).toString());
							
							importar.Guardar();
						
							myVector.clear();
							escape++;
						}else{
							escape++;
						}
				
					}
						
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dispose();
			}
		}
	}

}
