package objetos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Obj_Gen_Code_Bar {
	public void Generar_Code(String code, String name){
		Code128Bean bean = new Code128Bean();
		final int dpi = 150;
		
		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
		
		bean.doQuietZone(false);

		File outputFile = new File(System.getProperty("user.dir")+"/AssetGafete/Codigos_Barras/"+name+".png");
		
		try {
			OutputStream out = new FileOutputStream(outputFile);
			
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvas, code);
			canvas.finish();
			
			out.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error: "+e.getMessage());
		}catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
		}
	}
		
	public boolean Reset_Code(){
		File directoryDelete = new File(System.getProperty("user.dir")+"/AssetGafete/Codigos_Barras/");
		File[] children = directoryDelete.listFiles();  
	    boolean childrenDeleted = true;  
	    for (int i = 0; children != null && i < children.length; i++) {  
	        File child = children[i];  
	        if (child.exists()) {  
	            childrenDeleted = child.delete() && childrenDeleted;  
	        }  
	    }  
	    return childrenDeleted;  
	}
	
	public boolean Reset_Users(){
		File directoryDelete = new File(System.getProperty("user.dir")+"/AssetGafete/Users_Images/");
		File[] children = directoryDelete.listFiles();  
	    boolean childrenDeleted = true;  
	    for (int i = 0; children != null && i < children.length; i++) {  
	        File child = children[i];  
	        if (child.exists()) {  
	            childrenDeleted = child.delete() && childrenDeleted;  
	        }  
	    }  
	    return childrenDeleted;  
	}
}
