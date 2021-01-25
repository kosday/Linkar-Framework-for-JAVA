package linkar.functions;

/**
* Object that works as an argument in {@link DeleteOptions} function and defines the techniques for recovering deleted codes.
*/
public class RecoverIdType
{
	private boolean _ActiveTypeLinkar;
	/**
	 * Indicates that the RecoverIdType Linkar is enabled.
	 * @return value
	 */
    public boolean getActiveTypeLinkar()
    {
        return this._ActiveTypeLinkar;
    }
    private boolean _ActiveTypeCustom;
    /**
     * Indicates that the RecoverIdType Custom is enabled.
     * @return value
     */
    public boolean getActiveTypeCustom()
    {
        return this._ActiveTypeCustom;
    }

    private String _Prefix;
    /**
     * A prefix to the code.
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

    // NONE
	/**
	* No code recovery technique will be used.
	*/
    public RecoverIdType()
	{
        this._ActiveTypeLinkar = false;
        this._ActiveTypeCustom = false;
    }

	// LINKAR
	/**
	* The technique of recovering deleted Linkar type codes will be used.
	* @param prefix		Adding a prefix to the code.	
	* @param separator	The separator between the prefix and the code. The allowed separators list is: ! " # $ % &amp; ' ( ) * + , - . / : ; &lt; = &gt; ? @ [ \ ] ^ _ ` { | } ~
	*/
	public RecoverIdType(String prefix, String separator)
	{
		this._ActiveTypeLinkar = true;
		this._ActiveTypeCustom = false;
	
		this._Prefix = prefix;
		this._Separator = separator;
	}
		
	// CUSTOM
	/**
	* The technique of recovering deleted Custom type codes  will be used.
	* @param custom		It must have the value "true" so that the recovery of deleted codes is used through the subroutine of the Database SUB.LK.MAIN.NEWRECOVERRECORDID.CUSTOM. If the value is "false", no technique to recover deleted codes will be used.
	*					If the value is "false", no technique to recover deleted codes will be used.
	*/
	public RecoverIdType(boolean custom)
	{
		this._ActiveTypeLinkar = false;
		this._ActiveTypeCustom = custom;
	}
	
	String GetStringAM()
	{
        /*
            equate LK.OPTIONS.DELETE.OPTIMISTBLOCKCONTROL TO 1;*BOOL
            equate LK.OPTIONS.DELETE.OUTPUTFORMAT TO 2
            equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV TO 3;*BOOL
            equate LK.OPTIONS.DELETE.RECOVERITEMIDCUSTOM TO 4;*BOOL
            
            equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV.PREFIX TO 2;*MULTIVALUE
            equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV.SEPARATOR TO 3;*MULTIVALUE
        */

        String opLinkar;
        if (this._ActiveTypeLinkar)
            opLinkar = "1" + DBMV_Mark.VM_str + this._Prefix + DBMV_Mark.VM_str + this._Separator;
        else
            opLinkar = "0" + DBMV_Mark.VM_str + "" + DBMV_Mark.VM_str + "";

        String opCustom;
        if (this._ActiveTypeCustom)
            opCustom = "1";
        else
            opCustom = "0";

        String str = opLinkar + DBMV_Mark.AM_str + opCustom;
        return str;
    }	
}
