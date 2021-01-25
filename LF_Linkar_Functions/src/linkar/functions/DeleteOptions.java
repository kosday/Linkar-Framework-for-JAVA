package linkar.functions;


/**
* Object that works as an argument in Delete function and defines the function options.
*/
public class DeleteOptions
{
	private boolean _OptimisticLockControl;
	/**
	 * Checks out if the file has not been modified by other user.
	 * @return value
	 */
    public boolean getOptimisticLockControl()
    {
        return _OptimisticLockControl;
    }
    
	private RecoverIdType _RecoverIdType;
	/**
	 * Indicates that the {@link RecoverIdType} Linkar is enabled.
	 * @return value
	 */
    public boolean getActiveTypeLinkar()
    {
        return this._RecoverIdType.getActiveTypeLinkar();
    }
    /**
     * Indicates that the {@link RecoverIdType} Custom is enabled.
     * @return value
     */
    public boolean getActiveTypeCustom()
    {
        return this._RecoverIdType.getActiveTypeCustom();
    }

    /**
     * A prefix to the code.
     * @return value
     */
    public String getPrefix()
    {
        return this._RecoverIdType.getPrefix();
    }
    /**
     * The separator between the prefix and the code. The allowed separators list is: ! " # $ % &amp; ' ( ) * + , - . / : ; &lt; = &gt; ? @ [ \ ] ^ _ ` { | } ~
     * @return value
     */
    public String getSeparator()
    {
        return this._RecoverIdType.getSeparator();
    }

	/**
	* Constructor. Initializes a new instance of the {@link #DeleteOptions} class.	
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	optimisticLockControl = false
	recoverIdType = null
	}
	</pre>
	*/
    public DeleteOptions()
	{
    	this(false, null);
    }

	/**
	* Constructor. Initializes a new instance of the {@link #DeleteOptions} class.	
	* @param optimisticLockControl	In the execution of the Delete function, before updating the record, checks out if the record has not been modified by other user.
	* @custom.note 					The omitted argument with following value:
	<pre>
	{@code
	recoverIdType = null
	}
	</pre>
	*/
    public DeleteOptions(boolean optimisticLockControl)
	{
    	this(optimisticLockControl, null);
    }

	/**
	* Constructor. Initializes a new instance of the {@link #DeleteOptions} class.	
	* @param optimisticLockControl	In the execution of the Delete function, before updating the record, checks out if the record has not been modified by other user.
	* @param recoverIdType			Specifies the different recovery techniques of deleted codes.
	*/
    public DeleteOptions(boolean optimisticLockControl, RecoverIdType recoverIdType)
	{
        this._OptimisticLockControl = optimisticLockControl;
		if(recoverIdType == null)
			this._RecoverIdType = new RecoverIdType();
		else
			this._RecoverIdType = recoverIdType;
	}

    String GetString()
	{
		/*
		equate LK.OPTIONS.DELETE.OPTIMISTBLOCKCONTROL TO 1;*BOOL
		equate LK.OPTIONS.DELETE.OUTPUTFORMAT TO 2
		equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV TO 3;*BOOL
		equate LK.OPTIONS.DELETE.RECOVERITEMIDCUSTOM TO 4;*BOOL
		
		equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV.PREFIX TO 2;*MULTIVALUE
		equate LK.OPTIONS.DELETE.RECOVERITEMIDLINKARMV.SEPARATOR TO 3;*MULTIVALUE
		*/
		
		String str = (this._OptimisticLockControl ? "1" : "0") + DBMV_Mark.AM_str +
					this._RecoverIdType.GetStringAM();
		
		return str;
    }
}
