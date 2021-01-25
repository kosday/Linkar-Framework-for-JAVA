package linkar.functions;

/**
* Object that works as an argument in {@link NewOptions} function and defines the techniques for generating codes.
*/
public class RecordIdType
{
	private boolean _ActiveTypeLinkar;
	/**
     * Indicates that the RecordIdType Linkar is enabled.
     * @return value
     */
    public boolean getActiveTypeLinkar()
    {
        return this._ActiveTypeLinkar;
    }

    private boolean _ActiveTypeRandom;
    /**
     * Indicates that the RecordIdType Random is enabled.
     * @return value
     */
    public boolean getActiveTypeRandom()
    {
        return this._ActiveTypeRandom;
    }

    private boolean _ActiveTypeCustom;
    /**
     * Indicates that the RecordIdType Custom is enabled.
     * @return value
     */
    public boolean getActiveTypeCustom()
    {
        return this._ActiveTypeCustom;
    }

    private String _Prefix;
    /**
     * A prefix to the code
     * @return value
     */
    public String getPrefix()
    {
        return this._Prefix;
    }

    private String _Separator;
    /**
     * The separator between the prefix and the code. The allowed separators list is: ! " # $ % &amp; ' ( ) * + , - . / : ; &lt; = &gt; ? @ [ \ ] ^ _ ` { | } ~
     * @return value
     */
    public String getSeparator()
    {
        return this._Separator;
    }

    private String _FormatSpec;
    /**
     * The code format, under the Database syntax.
     * @return value
     */
    public String getFormatSpec_RecordId()
    {
        return this._FormatSpec;
    }

    private boolean _Numeric;
    /**
     * Indicates if the code must be numeric.
     * @return value
     */
    public boolean getNumeric()
    {
        return this._Numeric;
    }

    private int _Length;
    /**
     * Length of the code to create. It must be bigger than 0.
     * @return value
     */
    public int getLength()
    {
        return this._Length;
    }

	// NONE
	/**
	* No code generation technique will be used. The codes must be supplied in the New operations.
	*/
	public RecordIdType()
	{
		this._ActiveTypeLinkar = false;
		this._ActiveTypeRandom = false;
		this._ActiveTypeCustom = false;
	}
	
	// LINKAR
	/**
	* This constructor defines the options for generating Linkar type codes.
	* @param prefix			Adding a prefix to the code.
	* @param separator		The separator between the prefix and the code. The allowed separators list is: ! " # $ % &amp; ' ( ) * + , - . / : ; &lt; = &gt; ? @ [ \ ] ^ _ ` { | } ~
	* @param formatSpec		The code format, under the Database syntax.
	*/	public RecordIdType(String prefix, String separator, String formatSpec)
	{
		this._ActiveTypeLinkar = true;
		this._ActiveTypeRandom = false;
		this._ActiveTypeCustom = false;
	
		this._Prefix = prefix;
		this._Separator = separator;
		this._FormatSpec = formatSpec;
	}
	
	// RANDOM
	/**
	* This constructor defines the options for generating Random type codes.
	* @param numeric	Indicates if the code must be numeric.
	* @param length		Length of the code to create. It must be bigger than 0.
	*/	public RecordIdType(boolean numeric, int length)
	{
		this._ActiveTypeLinkar = false;
		this._ActiveTypeRandom = true;
		this._ActiveTypeCustom = false;
	
		this._Numeric = numeric;
		this._Length = length;
	}

	// CUSTOM
	/**
	* This constructor defines the options for generating Custom type codes.
	* @param custom		It must have the value "true" so that the generation of personalized codes through the subroutine of the Database SUB.LK.MAIN.NEWRECOVERRECORDID.CUSTOM is used. If the value is "false", no code generation technique will be used. The codes must be supplied in the New operations.
    *			        If the value is "false", no code generation technique will be used. The codes must be supplied in the New operations.
	*/	public RecordIdType(boolean custom)
	{
		this._ActiveTypeLinkar = false;
		this._ActiveTypeRandom = false;
		this._ActiveTypeCustom = true;
	}
	
	String GetStringAM()
	{
		String opLinkar;
		if (this._ActiveTypeLinkar)
			opLinkar = "1" + DBMV_Mark.VM_str + this._Prefix + DBMV_Mark.VM_str + this._Separator + DBMV_Mark.VM_str + this._FormatSpec;
		else
			opLinkar = "0" + DBMV_Mark.VM_str + "" + DBMV_Mark.VM_str + "" + DBMV_Mark.VM_str + "";
	
		String opRamdom;
		if (this._ActiveTypeRandom)
			opRamdom = "1" + DBMV_Mark.VM_str + (this._Numeric ? "1" : "0") + DBMV_Mark.VM_str + this._Length;
		else
			opRamdom = "0" + DBMV_Mark.VM_str + "" + DBMV_Mark.VM_str + "";
	
		String str = opLinkar + DBMV_Mark.AM_str +
					(this._ActiveTypeCustom ? "1" : "0") + DBMV_Mark.AM_str +
					opRamdom;
	
		return str;
	}	
}
