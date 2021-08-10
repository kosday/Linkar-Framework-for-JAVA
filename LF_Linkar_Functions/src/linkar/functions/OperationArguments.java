package linkar.functions;

/**
 * Auxiliary static class with functions to obtain the 3 items of every LinkarSERVER operation.
 * These items are: CUSTOMVARS, OPTIONS and INPUTDATA.
 * Unit Separator character (31) is used as separator between these items.
 * CUSTOMVARS: String for database custom subroutines.
 * OPTIONS: The options of every operation.
 * INPUTDATA: The necessary data for perform every operation.
 * CUSTOMVARS US_char OPTIONS US_char INPUT
 */
public class OperationArguments {

	/**
	 * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Read operation.
	 * @param filename File name to read.
	 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
	 * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
	 * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
	 * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
	 * @return
	 */
    public static String GetReadArgs(String filename, String recordIds, String dictionaries, ReadOptions readOptions, String customVars)
    {
        if (readOptions == null)
            readOptions = new ReadOptions();

        String options = readOptions.GetString();
        String inputData = filename + DBMV_Mark.AM + recordIds + DBMV_Mark.AM + dictionaries;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items ( CUSTOMVARS, OPTIONS and INPUTDATA) of the Update operation.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetUpdateArgs(String filename, String records, UpdateOptions updateOptions, String customVars)
    {
        if (updateOptions == null)
            updateOptions = new UpdateOptions();

        String options = updateOptions.GetString();
        String inputData = filename + DBMV_Mark.AM + records;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }
    
    /**
     * Compose the 3 items ( CUSTOMVARS, OPTIONS and INPUTDATA) of the Update operation.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetUpdatePartialArgs(String filename, String records, String dictionaries, UpdateOptions updateOptions, String customVars)
    {
        if (updateOptions == null)
            updateOptions = new UpdateOptions();

        String options = updateOptions.GetString();
        String inputData = filename + DBMV_Mark.AM + records + ASCII_Chars.FS_str + dictionaries;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the New operation.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: {@link RecordIdType}, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetNewArgs(String filename, String records, NewOptions newOptions, String customVars)
    {
        if (newOptions == null)
            newOptions = new NewOptions();

        String options = newOptions.GetString();
        String inputData = filename + DBMV_Mark.AM + records;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Delete operation.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, {@link RecoverIdType}.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetDeleteArgs(String filename, String records, DeleteOptions deleteOptions, String customVars)
    {
        if (deleteOptions == null)
            deleteOptions = new DeleteOptions();

        String options = deleteOptions.GetString();
        String inputData = filename + DBMV_Mark.AM + records;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Select operation.
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetSelectArgs(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause,
        SelectOptions selectOptions, String customVars)
    {
        if (selectOptions == null)
            selectOptions = new SelectOptions();

        String options = selectOptions.GetString();
        String inputData = filename + DBMV_Mark.AM +
            selectClause + DBMV_Mark.AM +
            sortClause + DBMV_Mark.AM +
            dictClause + DBMV_Mark.AM +
            preSelectClause;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Subroutine operation.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetSubroutineArgs(String subroutineName, int argsNumber, String arguments, String customVars)
    {
        String options = "";
        String inputData1 = subroutineName + DBMV_Mark.AM_str + argsNumber;
        String inputData2 = arguments;
        String inputData = inputData1 + ASCII_Chars.FS_str + inputData2;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Conversion operation.
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetConversionArgs(String expression, String code, CONVERSION_TYPE conversionType, String customVars)
    {
        String options = (conversionType == CONVERSION_TYPE.INPUT ? "I" : "O");
        String inputData = code + ASCII_Chars.FS_str + expression;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Format operation.
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetFormatArgs(String expression, String formatSpec, String customVars)
    {
        String options = "";
        String inputData = formatSpec + ASCII_Chars.FS_str + expression;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Dictionaries operation.
     * @param filename File name
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetDictionariesArgs(String filename, String customVars)
    {
        String options = "";

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + filename;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Execute operation.
     * @param statement The command you want to execute in the Database.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetExecuteArgs(String statement,String customVars)
    {
        String options = "";

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + statement;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the SendCommand operation.
     * @param command Content of the operation you want to send.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetSendCommandArgs(String command)
    {
        String options = "";

        String customVars = "";
        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + command;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the Version operation.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetVersionArgs()
    {
        String options = "";

        String cmdArgs = "" + ASCII_Chars.US_str + options + ASCII_Chars.US_str + "";
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the LkSchemas operation.
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetLkSchemasArgs(LkSchemasOptions lkSchemasOptions, String customVars)
    {
        if (lkSchemasOptions == null)
        	lkSchemasOptions = new LkSchemasOptions();
        String options = lkSchemasOptions.GetString();

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + "";
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the LkProperties operation.
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetLkPropertiesArgs(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars)
    {
        if (lkPropertiesOptions == null)
        	lkPropertiesOptions = new LkPropertiesOptions();
        String options = lkPropertiesOptions.GetString();

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + filename;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the GetTable operation.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetGetTableArgs(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars)
    {
        if (tableOptions == null)
        	tableOptions = new TableOptions();
        String options = tableOptions.GetString();
        String inputData = filename + DBMV_Mark.AM +
            selectClause + DBMV_Mark.AM +
            dictClause + DBMV_Mark.AM +
            sortClause;

        String cmdArgs = customVars + ASCII_Chars.US_str + options + ASCII_Chars.US_str + inputData;
        return cmdArgs;
    }

    /**
     * Compose the 3 items (CUSTOMVARS, OPTIONS and INPUTDATA) of the ResetCommonBlocks operation.
     * @return A String ready to be used in Linkar.ExecuteDirectOperation and Linkar.ExecutePersistentOperation.
     */
    public static String GetResetCommonBlocksArgs()
    {
        String options = "";

        String cmdArgs = "" + ASCII_Chars.US_str + options + ASCII_Chars.US_str + "";
        return cmdArgs;
    }
}
