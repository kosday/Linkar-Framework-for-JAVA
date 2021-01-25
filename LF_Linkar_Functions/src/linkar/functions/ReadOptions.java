package linkar.functions;

/**
 * Object that works as an argument in Read function and defines the function options.
 */
public class ReadOptions {
	
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
	* Constructor. Initializes a new instance of the {@link #ReadOptions} class.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec			Execute the defined formats in the dictionaries before returning.
	* @param originalRecords	Return a copy of the records in MV format.
	*/
    public ReadOptions(boolean calculated, boolean conversion, boolean formatSpec, boolean originalRecords)
    {
        this._CommonOptions = new CommonOptions(calculated, conversion, formatSpec, originalRecords);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #ReadOptions} class.
	* @param calculated		Return the resulting values from the calculated dictionaries.
	* @param conversion		Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec		Execute the defined formats in the dictionaries before returning.
	* @custom.note 			The omitted arguments with following values:
	<pre>
	{@code
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public ReadOptions(boolean calculated, boolean conversion, boolean formatSpec)
    {
    	this(calculated, conversion, formatSpec, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #ReadOptions} class.
	* @param calculated		Return the resulting values from the calculated dictionaries.
	* @param conversion		Execute the defined conversions in the dictionaries before returning.
	* @custom.note 			The omitted arguments with following values:
	<pre>
	{@code
	formatSpec = false
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public ReadOptions(boolean calculated, boolean conversion)
    {
    	this(calculated, conversion, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #ReadOptions} class.
	* @param calculated		Return the resulting values from the calculated dictionaries.
	* @custom.note 			The omitted arguments with following values:
	<pre>
	{@code
	conversion = false
	formatSpec = false
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public ReadOptions(boolean calculated)
    {
    	this(calculated, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #ReadOptions} class.
	* @custom.note 	The omitted arguments with following values:
	<pre>
	{@code
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public ReadOptions()
    {
    	this(false, false, false, false);
    }

    String GetString()
    {
        String str = this._CommonOptions.GetStringAM();
        return str;
    }
}
