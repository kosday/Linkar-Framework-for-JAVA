package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations Format.
 */
public class LkDataFormat extends LkData {
	
	private String Format;
	/**
	 * Format
	 * @return Format
	 */
    public String getFormat() { return Format; }

    /**
     * Initializes a new instance of the LkDataCRUD class.
     * @param formatResult The string result of the Format operation execution.
     */
    public LkDataFormat(String formatResult)
    {
    	super(formatResult);
        this.Format = StringFunctions.ExtractFormat(formatResult);
    }
}
