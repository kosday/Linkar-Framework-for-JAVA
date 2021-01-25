package linkar.functions;

/**
 * Object that works as an argument in New function and defines the function options.
 */
public class NewOptions
{
	private ReadAfterCommonOptions _ReadAfterCommonOptions;
	
	/**
	 * Reads the record again and returns it after the update or new. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	 * @return value
	 */
    public boolean getReadAfter()
    {
        return _ReadAfterCommonOptions.getReadAfter();
    }
    
	/**
	 * Returns the resulting values from the calculated dictionaries.
	 * @return value
	 */
    public boolean getCalculated()
    {
        return _ReadAfterCommonOptions.getCalculated();
    }
	/**
	 * Executes the defined conversions in the dictionaries before returning.
	 * @return value
	 */
    public boolean getConversion()
    {
        return _ReadAfterCommonOptions.getConversion();
    }
	/**
	 * Executes the defined formats in the dictionaries before returning.
	 * @return value
	 */
    public boolean getFormatSpec()
    {
        return _ReadAfterCommonOptions.getFormatSpec();
    }
	/**
	 * Returns a copy of the records in MV format.
	 * @return value
	 */
    public boolean getOriginalRecords()
    {
        return _ReadAfterCommonOptions.getOriginalRecords();
    }
    
    private RecordIdType _RecordIdType;
    
    /**
     * Indicates that the {@link RecordIdType} Linkar is enabled.
     * @return value
     */
    public boolean getActiveTypeLinkar()
    {
        return this._RecordIdType.getActiveTypeLinkar();
    }

    /**
     * Indicates that the {@link RecordIdType} Random is enabled.
     * @return value
     */
    public boolean getActiveTypeRandom()
    {
        return this._RecordIdType.getActiveTypeRandom();
    }

    /**
     * Indicates that the {@link RecordIdType} Custom is enabled.
     * @return value
     */
    public boolean getActiveTypeCustom()
    {
        return this._RecordIdType.getActiveTypeCustom();
    }

    /**
     * A prefix to the code
     * @return value
     */
    public String getPrefix()
    {
        return this._RecordIdType.getPrefix();
    }

    /**
     * The separator between the prefix and the code. The allowed separators list is: ! " # $ % &amp; ' ( ) * + , - . / : ; &lt; = &gt; ? @ [ \ ] ^ _ ` { | } ~
     * @return value
     */
    public String getSeparator()
    {
        return this._RecordIdType.getSeparator();
    }

    /**
     * The code format, under the Database syntax.
     * @return value
     */
    public String getFormatSpec_RecordId()
    {
        return this._RecordIdType.getFormatSpec_RecordId();
    }

    /**
     * Indicates if the code must be numeric.
     * @return value
     */
    public boolean getNumeric()
    {
        return this._RecordIdType.getNumeric();
    }

    /**
     * Length of the code to create. It must be bigger than 0.
     * @return value
     */
    public int getLength()
    {
        return this.getLength();
    }

	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @param readAfter			Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec			Execute the defined formats in the dictionaries before returning.
	* @param originalRecords	Return a copy of the records in MV format.
	*/
    public NewOptions(RecordIdType recordIdType, boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec, boolean originalRecords)
    {
		if(recordIdType == null)
			this._RecordIdType = new RecordIdType();
		else
			this._RecordIdType = recordIdType;

        if (readAfter)
            this._ReadAfterCommonOptions = new ReadAfterCommonOptions(readAfter, calculated, conversion, formatSpec, originalRecords);
        else
            this._ReadAfterCommonOptions = new ReadAfterCommonOptions(readAfter);
    }

	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @param readAfter			Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated			Return the resulting values from the calculated dictionaries.
	* @param conversion			Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec			Execute the defined formats in the dictionaries before returning.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public NewOptions(RecordIdType recordIdType, boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec)
	{
        this(recordIdType, readAfter, calculated, conversion, formatSpec, false);
    }

    
	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @param readAfter			Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
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
    public NewOptions(RecordIdType recordIdType, boolean readAfter, boolean calculated, boolean conversion)
	{
        this(recordIdType, readAfter, calculated, conversion, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @param readAfter			Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
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
    public NewOptions(RecordIdType recordIdType, boolean readAfter, boolean calculated)
	{
        this(recordIdType, readAfter, calculated, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @param readAfter			Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
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
    public NewOptions(RecordIdType recordIdType, boolean readAfter)
	{
        this(recordIdType, readAfter, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @param recordIdType		Specify the different techniques for generating codes. Mandatory if no registration codes are indicated in the New operations.
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	readAfter = false
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public NewOptions(RecordIdType recordIdType)
	{
        this(recordIdType, false, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #NewOptions} class.	
	* @custom.note The omitted arguments with following values:
	 <pre>
	{@code
	recordIdType = null
	readAfter  =false
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public NewOptions()
	{
    	this(null, false, false, false, false, false);
    }

    String GetString()
	{
		String str = this._RecordIdType.GetStringAM() + DBMV_Mark.AM_str +
					this._ReadAfterCommonOptions.GetStringAM();
		return str;
    }
}
