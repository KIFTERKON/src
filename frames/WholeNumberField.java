package frames;
import javax.swing.*; 
import javax.swing.text.*; 

import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@SuppressWarnings("serial")
public class WholeNumberField extends JTextField {

    private Toolkit toolkit;
    private NumberFormat integerFormatter;

    public WholeNumberField(int value, int columns) {
        super(columns);
        toolkit = Toolkit.getDefaultToolkit();
        integerFormatter = NumberFormat.getNumberInstance(Locale.US);
        integerFormatter.setParseIntegerOnly(true);
        setValue(value);
    }

    public int getValue() {
        int retVal = 0;
        try {
            retVal = integerFormatter.parse(getText()).intValue();
        } catch (ParseException e) {
            toolkit.beep();
        }
        return retVal;
    }

    public void setValue(int value) {
        setText(integerFormatter.format(value));
    }

    protected Document createDefaultModel() {
        return new WholeNumberDocument();
    }

    protected class WholeNumberDocument extends PlainDocument {

        public void insertString(int offs, String str, AttributeSet a) 
            throws BadLocationException {

            char[] source = str.toCharArray();
            char[] result = new char[source.length];
            int j = 0;

            for (int i = 0; i < result.length; i++) {
            	
            				if(	(source[i] == '0' ) ||
                        		(source[i] == '1' ) ||
                        		(source[i] == '2' ) ||
                        		(source[i] == '3' ) ||
                        		(source[i] == '4' ) ||
                        		(source[i] == '5' ) ||
                        		(source[i] == '6' ) ||
                        		(source[i] == '7' ) ||
                        		(source[i] == '8' ) ||
                        		(source[i] == '9' )) //||
//                			    (source[i] == '.' ))
                        	{result[j++] = source[i];                        	
                        	}else {
                        		toolkit.beep();
                    				JOptionPane.showMessageDialog(null, "Solo puede ingresar numeros enteros","aviso",JOptionPane.WARNING_MESSAGE);
                    				return;                   		
//                        		System.err.println("insertString: " + source[i]); 	                	
                        	}
            	}			
            super.insertString(offs, new String(result, 0, j), a);
        }
    }
}