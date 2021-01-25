package linkar.functions;

/**
 * Auxiliary class with the ReadAfters options for UpdateOptions and NewOptions classes
 */
class ReadAfterCommonOptions extends CommonOptions {
	
	private boolean _ReadAfter;
	/**
	 * Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 * @return value
	 */
    public boolean getReadAfter()
    {
        return _ReadAfter;
    }

	/**
	 * Initializes a new instance of the ReadAfterCommonOptions class.
	 * @param readAfter Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 * @param calculated Return the resulting values from the calculated dictionaries.
	 * @param conversion Execute the defined conversions in the dictionaries before returning.
	 * @param formatSpec Execute the defined formats in the dictionaries before returning.
	 * @param originalRecords Return a copy of the records in MV format.
	 */
    public ReadAfterCommonOptions(boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec, boolean originalRecords)
    {
    	super(calculated, conversion, formatSpec, originalRecords);
        this._ReadAfter = readAfter;
    }
    
	/**
	 * Initializes a new instance of the ReadAfterCommonOptions class.
	 * @param readAfter Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 */
    public ReadAfterCommonOptions(boolean readAfter)
    {
    	super();
        this._ReadAfter = readAfter;
    }
    
	/**
	 * Initializes a new instance of the ReadAfterCommonOptions class.
	 * @param readAfter Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 * @param calculated Return the resulting values from the calculated dictionaries.
	 */
    public ReadAfterCommonOptions(boolean readAfter, boolean calculated)
    {
    	super(calculated);
        this._ReadAfter = readAfter;
    }
    
	/**
	 * Initializes a new instance of the ReadAfterCommonOptions class.
	 * @param readAfter Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 * @param calculated Return the resulting values from the calculated dictionaries.
	 * @param conversion Execute the defined conversions in the dictionaries before returning.
	 */
    public ReadAfterCommonOptions(boolean readAfter, boolean calculated, boolean conversion)
    {
    	super(calculated, conversion);
        this._ReadAfter = readAfter;
    }
    
	/**
	 * Initializes a new instance of the ReadAfterCommonOptions class.
	 * @param readAfter Reads the record again and returns it after the update or new. Calculated, Conversion, FormatSpec and OriginalRecords will only make effect if this option is true.
	 * @param calculated Return the resulting values from the calculated dictionaries.
	 * @param conversion Execute the defined conversions in the dictionaries before returning.
	 * @param formatSpec Execute the defined formats in the dictionaries before returning.
	 */
    public ReadAfterCommonOptions(boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec)
    {
    	super(calculated, conversion, formatSpec);
        this._ReadAfter = readAfter;
    }

    /**
     * Composes the ReadAfterCommonOptions options string in the way that need it by UpdateOptions and NewOptions classes.
     * @return The string ready to be manage by {@link UpdateOptions} and {@link NewOptions} classes
     */
    public String GetStringAM()
    {
        String str = (this._ReadAfter ? "1" : "0") + DBMV_Mark.AM_str + super.GetStringAM();
        return str;
    }
}
