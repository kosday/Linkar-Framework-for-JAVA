package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations Execute.
 */
public class LkDataExecute extends LkData {
	
	private String Capturing;
	/**
	 * Capturing
	 * @return Capturing
	 */
    public String getCapturing() { return Capturing; }
    
    private String Returning;
    /**
     * Returning
     * @return Returning
     */
    public String getReturning() { return Returning; }

    /**
     * Initializes a new instance of the LkDataExecute class.
     * @param executeResult The string result of the Execute operation execution.
     */
    public LkDataExecute(String executeResult)
    {
    	super(executeResult);
        this.Capturing = StringFunctions.ExtractCapturing(executeResult);
        this.Returning = StringFunctions.ExtractReturning(executeResult);
    }
}
