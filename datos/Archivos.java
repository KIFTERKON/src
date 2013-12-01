package datos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import objetos.ObjTicket;



public class Archivos {
	public boolean escribirTicket(ObjTicket ex)
	{
		BufferedWriter bw = null;
		String nomArchivo = "DbTiket/" + "Ticket.txt";
		try
		{
			bw = new BufferedWriter(new FileWriter(nomArchivo));
			
			//escribe
			bw.write("\n\n\n\n\n\n\n\n\n"+ex.getIzagar() + "\n");
			bw.write(ex.getTalon() + "\n\n");
			bw.write(ex.getFolio_corte() + "                ");  bw.write(ex.getFecha()+ "\n\n");
			
//			bw.write(ex.getFolio_emp() + "\n");
			bw.write(ex.getEmpleado() + "\n");
			bw.write(ex.getPuesto() + "\n");
			
			bw.write(ex.getEstablecimineto() + "\n\n");
			
			
			bw.write(ex.getAsignacion().toUpperCase() + "\n\n");
			
			bw.write(ex.getTabla()+"\n");
			bw.write("              "+ex.getCorte_sistema()+"                       ");
			bw.write(ex.getDeposito()+"                 ");
			bw.write(ex.getEfectivo()+"\n\n");
			bw.write(ex.getDiferencia() + "\n\n\n");
			bw.write(ex.getTiempo_aire()+"\n\n");
			bw.write(ex.getResivo_luz()+"\n\n\n");
			bw.write(".");
			
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}finally{
			try{
				if(bw !=null){
					bw.flush();
					bw.close();
				}
			}catch(IOException ee){
				ee.printStackTrace();
			}
		}
		return true;
	}
	
//	@SuppressWarnings("unused")
//	public Obj_Alimentacion_Cortes leerTiket(String archivo) throws IOException {
//		Obj_Alimentacion_Cortes ex = new Obj_Alimentacion_Cortes();
//
//			try{
//				FileReader fr = new FileReader(System.getProperty("user.dir") + "\\DbTiket\\"+ archivo );
//				@SuppressWarnings("resource")
//				BufferedReader bf = new BufferedReader(fr);
//
//			}catch(FileNotFoundException ee){
//				System.out.println(ee.getMessage());
//				return ex=null;
//			}
//			return ex;
//	}
	
}
