package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations Subroutine.
 */
public class LkDataSubroutine extends LkData {
	
	private String[] Arguments;
	/**
	 * Argument list of the Subroutine operation execution.
	 * @return Arguments
	 */
    public String[] getArguments() { return Arguments; }

    /**
     * Initializes a new instance of the LkDataCRUD class.
     * @param subroutineResult The string result of the Subroutine operation execution.
     */
    public LkDataSubroutine(String subroutineResult)
    {
    	super(subroutineResult);
        this.Arguments = StringFunctions.ExtractSubroutineArgs(subroutineResult);
    }
}
