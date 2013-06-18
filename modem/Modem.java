package modem;

import java.util.List;
import giovynet.nativelink.SerialPort;
import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;

public class Modem {
	@SuppressWarnings({ "deprecation", "unused" })
	public static void main(String args[]){
	
		try{
		SerialPort serialPort = new SerialPort();
		List<String> freeSerialPort = serialPort.getFreeSerialPort();
		
		for (String free : freeSerialPort) 
		{
			System.out.println(free);
		} 

		Parameters param = new Parameters();
		param.setPort("COM3");
		param.setBaudRate(Baud._115200);
		Com com4 = new Com(param);

		param.setMinDelayWrite(100);

		com4.sendString("AT\r");
		com4.sendString("ATZ\r");
//		com4.sendString("ATZ+CSQ\r");
		com4.sendString("AT+CMGF=1\r");
		com4.sendString("AT+CMGS=\"6674769610\"\r");
		com4.sendString("hay compa como va  hacer eso");
		com4.sendString('\u001a'+"");
		System.out.println("SMS ENVIADO ");


		param.setMinDelayRead(100);

		String caracter="";
		String recibido="";
		while(caracter != null){ 
			caracter =com4.receiveSingleString(); 
			recibido += caracter; 

			System.out.println(caracter);
		}

		com4.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
}