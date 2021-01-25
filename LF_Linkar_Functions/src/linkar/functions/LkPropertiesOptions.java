package linkar.functions;

/**
* Contains the options to obtain the list of Properties of the different types of schemas with the LkProperties function.
*/
public class LkPropertiesOptions {
	
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

	/** Constructor. Initializes a new instance of the {@link #LkPropertiesOptions} class. The object is created with the default values for list of Schema Properties of  LKSCHEMAS type.
	*/
    public LkPropertiesOptions()
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = false;
        this._RowHeader = RowHeaders.TYPE.MAINLABEL;
        this._RowProperties = false;
        this._OnlyVisibles = false;
        this._UsePropertyNames = false;
        this._Pagination = false;
        this._Pagination_RegPage = 10;
        this._Pagination_NumPage = 1;
    }

	/** Constructor. Initializes a new instance of the {@link #LkPropertiesOptions} class. This constructor will be used when you want to obtain a list of Properties of the LKSCHEMAS schema type. It is allowed to specify creation options for obtaining the list of properties of LKSCHEMAS type schemas.	
	* @param rowHeader Include headings in first row <b>MAINLABEL</b> (main headings), <b>SHORTLABEL</b> (short label headings), and <b>NONE</b> (without headings).
	* @param rowProperties First row contains property names.
	* @param onlyVisibles Use only Visible Schemas and Properties.
	* @param usePropertyNames Use Properties and Table names.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/
    public LkPropertiesOptions(RowHeaders.TYPE rowHeader, boolean rowProperties, boolean onlyVisibles, boolean usePropertyNames, boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = false;
        this._RowHeader = rowHeader;
        this._RowProperties = rowProperties;
        this._OnlyVisibles = onlyVisibles;
        this._UsePropertyNames = usePropertyNames;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

	/** Constructor. Initializes a new instance of the {@link #LkPropertiesOptions} class. This constructor will be used when you want to obtain a list of Properties of the SQLMODE schema type. It is allowed to specify creation options for obtaining the list of properties of SQLMODE type schemas.
	* @param onlyVisibles Use only Visible Schemas and Properties.
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/
    public LkPropertiesOptions(boolean onlyVisibles, boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.LKSCHEMAS;
        this._SqlMode = true;
        this._RowHeader = RowHeaders.TYPE.NONE;
        this._RowProperties = true;
        this._OnlyVisibles = onlyVisibles;
        this._UsePropertyNames = true;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

	/** Constructor. Initializes a new instance of the {@link #LkPropertiesOptions} class. This constructor will be used when you want to obtain a list of Properties of the DICTIONARIES schema type. It is allowed to specify creation options for obtaining the list of properties of DICTIONARIES types schemas.
	* @param rowHeader Include headings in first row <b>MAINLABEL</b> (main headings), <b>SHORTLABEL</b> (short label headings), and <b>NONE</b> (without headings).
	* @param pagination Indicates if pagination is being used or not.
	* @param regPage In case of pagination indicates the number of records by page. It must be bigger than 0.
	* @param numPage In case of pagination it indicates the page number to obtain. Must be greater than 0.
	*/
    public LkPropertiesOptions(RowHeaders.TYPE rowHeader, boolean pagination, int regPage, int numPage)
    {
        this._SchemaType = SchemaTypes.TYPE.DICTIONARIES;
        this._SqlMode = false;
        this._RowHeader = rowHeader;
        this._RowProperties = false;
        this._OnlyVisibles = false;
        this._UsePropertyNames = false;
        this._Pagination = pagination;
        this._Pagination_RegPage = regPage;
        this._Pagination_NumPage = numPage;
    }

    String GetString()
    {
        String str = (int)this._SchemaType.getnumVal() + DBMV_Mark.AM_str +
            (this._SqlMode ? "1" : "0") + DBMV_Mark.AM_str +
            (this._UsePropertyNames ? "1" : "0") + DBMV_Mark.AM_str +
            (this._RowProperties ? "1" : "0") + DBMV_Mark.AM_str +
            (this._OnlyVisibles ? "1" : "0") + DBMV_Mark.AM_str +
         (int)this._RowHeader.getnumVal() + DBMV_Mark.AM_str +
         (this._Pagination ? "1" : "0") + DBMV_Mark.VM_str +
         this._Pagination_RegPage + DBMV_Mark.VM_str +
         this._Pagination_NumPage;
        return str;
    }
}
