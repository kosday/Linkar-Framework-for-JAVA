package linkar.functions;

/**
 * Object that works as an argument in Update function and defines the function options.
 */
public class UpdateOptions {
	
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
    
    private boolean _OptimisticLockControl;
	/**
	 * Checks out if the file has not been modified by other user.
	 * <p>
	 * If the OptimisticLock property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
 	 * to use the Optimistic Lock technique. This copy can be obtained from a previous Read operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
	 * @return value
	 */
    public boolean getOptimisticLockControl()
    {
        return _OptimisticLockControl;
    }

	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl 	Checks out if the file has not been modified by other user.
	* @param readAfter 				Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated				Return the resulting values from the calculated dictionaries.
	* @param conversion				Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec				Execute the defined formats in the dictionaries before returning.
	* @param originalRecords 		Return a copy of the records in MV format.
	*/
    public UpdateOptions(boolean optimisticLockControl, boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec, boolean originalRecords)
    {
        this._OptimisticLockControl = optimisticLockControl;

        if (readAfter)
            this._ReadAfterCommonOptions = new ReadAfterCommonOptions(readAfter, calculated, conversion, formatSpec, originalRecords);
        else
            this._ReadAfterCommonOptions = new ReadAfterCommonOptions(readAfter);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl 	Checks out if the file has not been modified by other user.
	* @param readAfter 				Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated				Return the resulting values from the calculated dictionaries.
	* @param conversion				Execute the defined conversions in the dictionaries before returning.
	* @param formatSpec				Execute the defined formats in the dictionaries before returning.
	* @custom.note 					The omitted arguments with following values:
	<pre>
	{@code
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public UpdateOptions(boolean optimisticLockControl, boolean readAfter, boolean calculated, boolean conversion, boolean formatSpec)
    {
    	this(optimisticLockControl, readAfter, calculated, conversion, formatSpec, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl 	Checks out if the file has not been modified by other user.
	* @param readAfter 				Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated				Return the resulting values from the calculated dictionaries.
	* @param conversion				Execute the defined conversions in the dictionaries before returning.
	* @custom.note 					The omitted arguments with following values:
	<pre>
	{@code
	formatSpec = false
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public UpdateOptions(boolean optimisticLockControl, boolean readAfter, boolean calculated, boolean conversion)
    {
    	this(optimisticLockControl, readAfter, calculated, conversion, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl 	Checks out if the file has not been modified by other user.
	* @param readAfter 				Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @param calculated				Return the resulting values from the calculated dictionaries.
	* @custom.note 					The omitted arguments with following values:
	<pre>
	{@code
	conversion = false
	formatSpec = false
	originalRecords = false
	dictionaries = false
	}
	</pre>
	*/
    public UpdateOptions(boolean optimisticLockControl, boolean readAfter, boolean calculated)
    {
    	this(optimisticLockControl, readAfter, calculated, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl 	Checks out if the file has not been modified by other user.
	* @param readAfter 				Reads the record again and returns it after the update. Calculated, dictionaries, conversion, formatSpec and originalRecords will only make effect if this option is true.
	* @custom.note 					The omitted arguments with following values:
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
    public UpdateOptions(boolean optimisticLockControl, boolean readAfter)
    {
    	this(optimisticLockControl, readAfter, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @param optimisticLockControl	Checks out if the file has not been modified by other user.
	* @custom.note 					The omitted arguments with following values:
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
    public UpdateOptions(boolean optimisticLockControl)
    {
    	this(optimisticLockControl, false, false, false, false, false);
    }
    
	/**
	* Constructor. Initializes a new instance of the {@link #UpdateOptions} class.	
	* @custom.note The omitted arguments with following values:
	<pre>
	{@code
	optimisticLockControl = false
	readAfter = false
	calculated = false
	conversion = false
	formatSpec = false
	originalRecords = false
	}
	</pre>
	*/
    public UpdateOptions()
    {
    	this(false, false, false, false, false, false);
    }

    public String GetString()
    {
        String str = (this._OptimisticLockControl ? "1" : "0") + DBMV_Mark.AM_str +
                      this._ReadAfterCommonOptions.GetStringAM();
        return str;
    }
}
