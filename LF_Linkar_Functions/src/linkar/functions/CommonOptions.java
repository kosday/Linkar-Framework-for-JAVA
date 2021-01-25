package linkar.functions;

/**
 * Initializes a new instance of the CommonOptions class.
 */
class CommonOptions {	
	
	private boolean _Calculated;
	/**
	 * Returns the resulting values from the calculated dictionaries.
	 * @return value
	 */
    public boolean getCalculated()
    {
        return _Calculated;
    }
    private boolean _Conversion;
	/**
	 * Executes the defined conversions in the dictionaries before returning.
	 * @return value
	 */
    public boolean getConversion()
    {
        return _Conversion;
    }
    private boolean _FormatSpec;
	/**
	 * Executes the defined formats in the dictionaries before returning.
	 * @return value
	 */
    public boolean getFormatSpec()
    {
        return _FormatSpec;
    }
    private boolean _OriginalRecords;
	/**
	 * Returns a copy of the records in MV format.
	 * @return value
	 */
    public boolean getOriginalRecords()
    {
        return _OriginalRecords;
    }

    /**
     * Initializes a new instance of the CommonOptions class.
     * @param calculated Return the resulting values from the calculated dictionaries.
     * @param conversion Execute the defined conversions in the dictionaries before returning.
     * @param formatSpec Execute the defined formats in the dictionaries before returning.
     * @param originalRecords Return a copy of the records in MV format.
     */
    public CommonOptions(boolean calculated, boolean conversion, boolean formatSpec, boolean originalRecords)
    {
        this._Calculated = calculated;
        this._Conversion = conversion;
        this._FormatSpec = formatSpec;
        this._OriginalRecords = originalRecords;
    }
    
    /**
     * Initializes a new instance of the CommonOptions class.
     */
    public CommonOptions()
    {
    	this(false, false, false, false);
    }
    
    /**
     * Initializes a new instance of the CommonOptions class.
     * @param calculated Return the resulting values from the calculated dictionaries.
     */
    public CommonOptions(boolean calculated)
    {
    	this(calculated, false, false, false);
    }
    
    /**
     * Initializes a new instance of the CommonOptions class.
     * @param calculated Return the resulting values from the calculated dictionaries.
     * @param conversion Execute the defined conversions in the dictionaries before returning.
     */
    public CommonOptions(boolean calculated, boolean conversion)
    {
    	this(calculated, conversion, false, false);
    }
    
    /**
     * Initializes a new instance of the CommonOptions class.
     * @param calculated Return the resulting values from the calculated dictionaries.
     * @param conversion Execute the defined conversions in the dictionaries before returning.
     * @param formatSpec Execute the defined formats in the dictionaries before returning.
     */
    public CommonOptions(boolean calculated, boolean conversion, boolean formatSpec)
    {
    	this(calculated, conversion, formatSpec, false);
    }

    /**
     * Composes the CommonOptions options string in the way that need it by ReadOptions, SelectOptions and ReadAfterCommonOptions classes.
     * @return The string ready to be manage by {@link ReadOptions}, {@link SelectOptions} and {@link ReadAfterCommonOptions} classes
     */
    public String GetStringAM()
    {
        String str = (this._Calculated ? "1" : "0") + DBMV_Mark.AM_str +
                     "" + DBMV_Mark.AM_str + // Old dictionaries
                     (this._Conversion ? "1" : "0") + DBMV_Mark.AM_str +
                     (this._FormatSpec ? "1" : "0") + DBMV_Mark.AM_str +
                     (this._OriginalRecords ? "1" : "0");
        return str;
    }
}
