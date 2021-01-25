package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations LkSchemas and LkProperties.
 */
public class LkDataSchProp extends LkDataCRUD {
	
	private String[] RowProperties;
	/**
	 * RowProperties
	 * @return RowProperties
	 */
    public String[] getRowProperties() { return RowProperties; }
    
    private String[] RowHeaders;
    /**
     * RowHeaders
     * @return RowHeaders
     */
    public String[] getRowHeaders() { return RowHeaders; }

    /**
     * Initializes a new instance of the LkDataSchProp class.
     * @param lkSchemasResult The string result of the LkSchemas or LkProperties operation execution.
     */
    public LkDataSchProp(String lkSchemasResult)
    {
    	super(lkSchemasResult);
        this.RowProperties = StringFunctions.ExtractRowProperties(lkSchemasResult);
        this.RowHeaders = StringFunctions.ExtractRowHeaders(lkSchemasResult);
    }
}
