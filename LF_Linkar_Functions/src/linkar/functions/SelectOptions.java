package linkar.functions;


/**
* Object that works as an argument in Select function and defines the function options.
*/
public class SelectOptions {
	private boolean _OnlyRecordId;
	/**
	 * Returns just the selected records codes.
	 * @return value
	 */
    public boolean getOnlyRecordId()
    {
        return this._OnlyRecordId;
    }
    private boolean _Pagination;
    /**
     * Indicates if pagination is being used or not.
     * @return value
     */
    public boolean getPagination()
    {
        return this._Pagination;
    }
    private int _Pagination_RegPage;
    /**
     * In case of pagination indicates the number of records by page. It must be bigger than 0.
     * @return value
     */
    public int getPagination_RegPage()
    {
        return this._Pagination_RegPage;
    }
    private int _Pagination_NumPage;
    /**
     * In case of pagination it indicates the page number to obtain. Must be greater than 0.
     * @return value
     */
    public int getPagination_NumPage()
    {
        return this._Pagination_NumPage;
    }

    private CommonOptions _CommonOptions;
	/**
	 * Returns the resulting values from the calculated dictionaries.
	 * @return value
	 */
    public boolean getCalculated()
    {
        return _CommonOptions.getCalculated();
    }
	/**
	 * Executes the defined conversions in the dictionaries before returning.
	 * @return value
	 */
    public boolean getConversion()
    {
        return _CommonOptions.getConversion();
    }
	/**
	 * Executes the defined formats in the dictionaries before returning.
	 * @return value
	 */
    public boolean getFormatSpec()
    {
        return _CommonOptions.getFormatSpec();
    }
	/**
	 * Returns a copy of the records in MV format.
	 * @return value
	 */
    public boolean getOriginalRecords()
    {
        return _CommonOptions.getOriginalRecords();
    }

	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec			Execute the defined formats in the dictionaries before returning.
	* @param originalRecords	Return a copy of the records in MV format.	
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage, int numPage, boolean calculated,
    		boolean conversion, boolean formatSpec, boolean originalRecords)
    {
        this._OnlyRecordId = onlyRecordId;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
        this._CommonOptions = new CommonOptions(calculated, conversion, formatSpec, originalRecords);
    }
    

	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec			Execute the defined formats in the dictionaries before returning.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage, int numPage, boolean calculated,
    		boolean conversion, boolean formatSpec)
    {
    	this(onlyRecordId, pagination, regPage, numPage, calculated, conversion, formatSpec, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage, int numPage, boolean calculated,
    		boolean conversion)
    {
    	this(onlyRecordId, pagination, regPage, numPage, calculated, conversion, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage, int numPage, boolean calculated)
    {
    	this(onlyRecordId, pagination, regPage, numPage, calculated, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage, int numPage)
    {
    	this(onlyRecordId, pagination, regPage, numPage, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @param regPage			In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	numPage = 1
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination, int regPage)
    {
    	this(onlyRecordId, pagination, regPage, 1, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @param pagination			Indicates if pagination is being used or not.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	regPage = 10
	numPage = 1
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId, boolean pagination)
    {
    	this(onlyRecordId, pagination, 10, 1, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @param onlyRecordId 		Returns just the selected records codes.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	pagination = false
	regPage = 10
	numPage = 1
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions(boolean onlyRecordId)
    {
    	this(onlyRecordId, false, 10, 1, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #SelectOptions} class.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	onlyRecordId = false
	pagination = false
	regPage = 10
	numPage = 1
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public SelectOptions()
    {
    	this(false, false, 10, 1, false, false, false, false);
    }
    
    String GetString()
    {
        String str = (this._Pagination ? "1" : "0") + DBMV_Mark.VM_str + this._Pagination_RegPage + DBMV_Mark.VM_str + this._Pagination_NumPage + DBMV_Mark.AM_str +
                     (this._OnlyRecordId ? "1" : "0") + DBMV_Mark.AM +
                     this._CommonOptions.GetStringAM();
        return str;
    }
}
