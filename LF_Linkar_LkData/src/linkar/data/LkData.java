package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Abstract class with common properties of all derived class.
 */
public abstract class LkData {
	
	/**
	 * The string that is obtained as result from the operation execution.
	 */
    protected String OperationResult;

    private String[] Errors;
    /**
     * List of the error of the operation execution.
     * @return
     */
    public String[] getErrors() { return Errors; }

    /**
     * Initializes a new instance of the LkData class.
     * @param opResult The string result of the operation execution.
     */
    public LkData(String opResult)
    {
        this.OperationResult = opResult;
        this.Errors = StringFunctions.ExtractErrors(opResult);
    }
    
}
