package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations Conversion.
 */
public class LkDataConversion extends LkData {
	
	private String Conversion;
	/**
	 * Conversion Code
	 * @return Conversion Code
	 */
    public String getConversion() { return Conversion; }

    /**
     * Initializes a new instance of the LkDataCRUD class.
     * @param conversionResult The string result of the Conversion operation execution.
     */
    public LkDataConversion(String conversionResult)
    {
    	super(conversionResult);
        this.Conversion = StringFunctions.ExtractConversion(conversionResult);
    }
}
