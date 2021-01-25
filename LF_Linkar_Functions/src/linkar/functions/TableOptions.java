package linkar.functions;

/**
* It contains the options to perform queries with the GetTable function, using the different types of schemas.
*/
public class TableOptions {
	
    private SchemaTypes.TYPE _SchemaType;
    /**
     * Indicates the type of LkSchemas used
     * @return value
     */
    public SchemaTypes.TYPE getSchemaType()
    {
        return this._SchemaType;
    }
    
    private RowHeaders.TYPE _RowHeader;
    /**
     * Include headings in first row MAINLABEL (main headings), SHORTLABEL (short label headings), and NONE (without headings).
     * @return value
     */
    public RowHeaders.TYPE getRowHeader()
    {
        return this._RowHeader;
    }
    
    private boolean _SqlMode;
    /**
     * SQLMODE type schemas
     * @return value
     */
    public boolean getSqlMode()
    {
        return this._SqlMode;
    }
    
    private boolean _RowProperties;
    /**
     * First row contains property names.
     * @return value
     */
    public boolean getRowProperties()
    {
        return this._RowProperties;
    }
    
    private boolean _OnlyVisibles;
    /**
     * Use only Visible Schemas and Properties.
     * @return value
     */
    public boolean getOnlyVisibles()
    {
        return this._OnlyVisibles;
    }
    
    private boolean _UsePropertyNames;
    /**
     * Use Properties and Table names.
     * @return value
     */
    public boolean getUsePropertiesNames()
    {
        return this._UsePropertyNames;
    }
    
    private boolean _RepeatValues;
    /**
     * Repeat common atributes in MV and SV groups.
     * @return value
     */
    public boolean getRepeatValues()
    {
        return this._RepeatValues;
    }
    
    private boolean _ApplyConversion;
    /**
     * Execute Conversions: With Dictionaries, conversion defined in the dictionary. With Schemas conversions defined in Linkar Schemas.
     * @return value
     */
    public boolean getApplyConversion()
    {
        return this._ApplyConversion;
    }
    
    private boolean _ApplyFormat;
    /**
     * Execute Formats. With Dictionaries, formats defined in the dictionary. With Schemas formats defined in Linkar Schemas.
     * @return value
     */
    public boolean getApplyFormat()
    {
        return this._ApplyFormat;
    }
    
    private boolean _Calculated;
    /**
     * Return the resulting values from the calculated dictionaries.
     * @return value
     */
    public boolean getCalculated()
    {
        return this._Calculated;
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

	/** Constructor. Initializes a new instance of the {@link #TableOptions} class. The object is created with the default values for queries with LKSCHEMAS type schemas.
	*/
    public TableOptions()
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = false;
        this._RowHeader = RowHeaders.TYPE.MAINLABEL;
        this._RowProperties = false;
        this._OnlyVisibles = false;
        this._UsePropertyNames = false;
        this._RepeatValues = false;
        this._ApplyConversion = false;
        this._ApplyFormat = false;
        this._Calculated = false;
        this._Pagination = false;
        this._Pagination_RegPage = 10;
        this._Pagination_NumPage = 1;
    }

	/** Constructor. Initializes a new instance of the {@link #TableOptions} class. This constructor will be used when you want to obtain queries of the LKSCHEMAS schema type. It is allowed to specify options for creating queries of LKSCHEMAS type schemas.
	* @param rowHeader Include headings in first row <b>MAINLABEL</b> (main headings), <b>SHORTLABEL</b> (short label headings), and <b>NONE</b> (without headings).
	* @param rowProperties First row contains property names.
	* @param onlyVisibles Use only Visible Schemas and Properties.
	* @param usePropertyNames Use Properties and Table names.
	* @param repeatValues Repeat common atributes in MV and SV groups.
	* @param applyConversion Execute Conversions: With Dictionaries, conversion defined in the dictionary. With Schemas conversions defined in Linkar Schemas
	* @param applyFormat Execute Formats. With Dictionaries, formats defined in the dictionary. With Schemas formats defined in Linkar Schemas.
	* @param calculated Return the resulting values from the calculated dictionaries.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/
    public TableOptions(RowHeaders.TYPE rowHeader, boolean rowProperties, boolean onlyVisibles, boolean usePropertyNames,
    		boolean repeatValues, boolean applyConversion, boolean applyFormat, boolean calculated,
    		boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = false;
        this._RowHeader = rowHeader;
        this._RowProperties = rowProperties;
        this._OnlyVisibles = onlyVisibles;
        this._UsePropertyNames = usePropertyNames;
        this._RepeatValues = repeatValues;
        this._ApplyConversion = applyConversion;
        this._ApplyFormat = applyFormat;
        this._Calculated = calculated;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

	/** Constructor. Initializes a new instance of the {@link #TableOptions} class. This constructor will be used when you want to perform queries of the SQLMODE type schemas. It is allowed to specify options for creating queries of SQLMODE type schemas.
	* @param onlyVisibles Use only Visible Schemas and Properties.
	* @param applyConversion Execute Conversions: With Dictionaries, conversion defined in the dictionary. With Schemas conversions defined in Linkar Schemas
	* @param applyFormat Execute Formats. With Dictionaries, formats defined in the dictionary. With Schemas formats defined in Linkar Schemas.
	* @param calculated Return the resulting values from the calculated dictionaries.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/
    public TableOptions(boolean onlyVisibles, boolean applyConversion, boolean applyFormat, boolean calculated,
    		boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = true;
        this._RowHeader = RowHeaders.TYPE.NONE;
        this._RowProperties = true;
        this._OnlyVisibles = onlyVisibles;
        this._UsePropertyNames = true;
        this._RepeatValues = true;
        this._ApplyConversion = applyConversion;
        this._ApplyFormat = applyFormat;
        this._Calculated = calculated;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

	/** Constructor. Initializes a new instance of the {@link #TableOptions} class. This constructor will be used when you want to perform queries of the DICTIONARIES type schemas. It is allowed to specify queries creation options of DICTIONARIES type schemas.
	* @param rowHeader Include headings in first row <b>MAINLABEL</b> (main headings), <b>SHORTLABEL</b> (short label headings), and <b>NONE</b> (without headings).
	* @param repeatValues Repeat common atributes in MV and SV groups.
	* @param applyConversion Execute Conversions: With Dictionaries, conversion defined in the dictionary. With Schemas conversions defined in Linkar Schemas
	* @param applyFormat Execute Formats. With Dictionaries, formats defined in the dictionary. With Schemas formats defined in Linkar Schemas.
	* @param calculated Return the resulting values from the calculated dictionaries.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/    
	public TableOptions(RowHeaders.TYPE rowHeader, boolean repeatValues, boolean applyConversion, boolean applyFormat, boolean calculated,
        boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.DICTIONARIES;
        this._SqlMode = false;
        this._RowHeader = rowHeader;
        this._RowProperties = false;
        this._OnlyVisibles = false;
        this._UsePropertyNames = false;
        this._RepeatValues = repeatValues;
        this._ApplyConversion = applyConversion;
        this._ApplyFormat = applyFormat;
        this._Calculated = calculated;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

	/** Constructor. Initializes a new instance of the {@link #TableOptions} class. This constructor will be used when you want to perform queries without schema information. It is allowed to specify queries creation options without any specific type of schemas.
	* @param rowHeader Include headings in first row <b>MAINLABEL</b> (main headings), <b>SHORTLABEL</b> (short label headings), and <b>NONE</b> (without headings).
	* @param repeatValues Repeat common atributes in MV and SV groups.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/    
    public TableOptions(RowHeaders.TYPE rowHeader, boolean repeatValues, 
    		boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.NONE;
        this._SqlMode = false;
        this._RowHeader = rowHeader;
        this._RowProperties = false;
        this._OnlyVisibles = false;
        this._UsePropertyNames = false;
        this._RepeatValues = repeatValues;
        this._ApplyConversion = false;
        this._ApplyFormat = false;
        this._Calculated = false;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

    public String GetString()
    {
         //EQUATE LK.OPTIONS.GETTABLE.SCHEMATYPE        TO 1;*LK.SCHEMAS, DICTIONARIES, NOTHING
         //EQUATE LK.OPTIONS.GETTABLE.SQLMODE           TO 2;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.USEPROPERTYNAMES  TO 3;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.ROWPROPERTIES     TO 4;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.ONLYVISIBLES      TO 5;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.ROWHEADERS        TO 6;*"MAINLABEL","SHORTLABEL","NOTHING"
         //EQUATE LK.OPTIONS.GETTABLE.REPEATVALUES      TO 7;*BOOL                 
         //EQUATE LK.OPTIONS.GETTABLE.APPLYCONVERSION   TO 8;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.APPLYFORMAT       TO 9;*BOOL
         //EQUATE LK.OPTIONS.GETTABLE.CALCULATED        TO 10;*BOOL      
         //EQUATE LK.OPTIONS.GETTABLE.PAGINATION        TO 11;*BOOL

         //EQUATE LK.OPTIONS.GETTABLE.PAGINATION.REGPAGE TO 2;*MULTIVALUE
         //EQUATE LK.OPTIONS.GETTABLE.PAGINATION.NUMPAGE TO 3;*MULTIVALUE

        String str = (int)this._SchemaType.getnumVal() + DBMV_Mark.AM_str +
            (this._SqlMode ? "1" : "0") + DBMV_Mark.AM_str +
            (this._UsePropertyNames ? "1" : "0") + DBMV_Mark.AM_str +
            (this._RowProperties ? "1" : "0") + DBMV_Mark.AM_str +
            (this._OnlyVisibles ? "1" : "0") + DBMV_Mark.AM_str +
         (int)this._RowHeader.getnumVal() + DBMV_Mark.AM_str +
        (this._RepeatValues ? "1" : "0") + DBMV_Mark.AM_str +
        (this._ApplyConversion ? "1" : "0") + DBMV_Mark.AM_str +
        (this._ApplyFormat ? "1" : "0") + DBMV_Mark.AM_str +
        (this._Calculated ? "1" : "0") + DBMV_Mark.AM_str +
         (this._Pagination ? "1" : "0") + DBMV_Mark.VM_str +
         this._Pagination_RegPage + DBMV_Mark.VM_str +
         this._Pagination_NumPage;
        return str;
    }
}
