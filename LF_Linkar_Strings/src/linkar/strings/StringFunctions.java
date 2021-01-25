package linkar.strings;


/**
 * The tag names that may appear in the result String from executing an operation.
 */
public class StringFunctions
{
    public static final String TOTAL_RECORDS_KEY = "TOTAL_RECORDS";
    public static final String RECORD_IDS_KEY = "RECORD_ID";
    public static final String RECORDS_KEY = "RECORD";
    public static final String CALCULATED_KEY = "CALCULATED";
    public static final String RECORD_DICTS_KEY = "RECORD_DICTS";
    public static final String RECORD_ID_DICTS_KEY = "RECORD_ID_DICTS";
    public static final String CALCULATED_DICTS_KEY = "CALCULATED_DICTS";
    public static final String ARGUMENTS_KEY = "ARGUMENTS";
    public static final String ORIGINAL_RECORDS_KEY = "ORIGINALRECORD";
    public static final String FORMAT_KEY = "FORMAT";
    public static final String CONVERSION_KEY = "CONVERSION";
    public static final String CAPTURING_KEY = "CAPTURING";
    public static final String RETURNING_KEY = "RETURNING";
    public static final String ROWHEADERS_KEY = "ROWHEADERS";
    public static final String ROWPROPERTIES_KEY = "ROWPROPERTIES";
    public static final String ERRORS_KEY = "ERRORS";

    public static final char DC4 = '\u0014';
    public static final String DC4_str = "\u0014";
    public static final char FS = '\u001C';
    public static final String FS_str = "\u001C";
    public static final char RS = '\u001E';
    public static final String RS_str = "\u001E";
    public static final char AM = '\u00FE';
    public static final String AM_str = "\u00FE";
    public static final char VM = '\u00FD';
    public static final String VM_str = "\u00FD";

    /* Extraction functions */

    /**
     * Looks for the TOTAL_RECORDS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of TOTAL_RECORDS_KEY tag.
     */
    public static int ExtractTotalRecords(String lkString)
    {
        String block = GetData(lkString, TOTAL_RECORDS_KEY, FS_str, AM_str);
        try {  
        	int result = Integer.parseInt(block);  
            return result;  
         } catch (NumberFormatException e) {  
            return 0;  
         }  
    }

    /**
     * Looks for the RECORD_IDS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RECORD_IDS_KEY tag.
     */
    public static String[] ExtractRecordIds(String lkString)
    {
        String valueTag = GetData(lkString, RECORD_IDS_KEY, FS_str, AM_str);
        return splitArray(valueTag, RS_str);
    }

    /**
     * Looks for the RECORDS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RECORDS_KEY tag.
     */
    public static String[] ExtractRecords(String lkString)
    {
        String valueTag = GetData(lkString, RECORDS_KEY, FS_str, AM_str);
        return splitArray(valueTag, RS_str);
    }

    /**
     * Looks for the ERRORS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of ERRORS_KEY tag.
     */
    public static String[] ExtractErrors(String lkString)
    {
        String valueTag = GetData(lkString, ERRORS_KEY, FS_str, AM_str);
        return splitArray(valueTag, AM_str);
    }

    /**
     * This function formats the message error by split into Error Code and Error Message
     * @param error The value of ERRORS_KEY tag.
     * @return The error formated.
     */
    public static String FormatError(String error)
    {
        String result = error;
        String[] items = error.split(VM_str, -1);
        if(items.length == 2)
            result = "ERROR CODE: " + items[0] + " ERROR MESSAGE: " + items[1];

        return result;
    }

    /**
     * Looks for the CALCULATED_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of CALCULATED_KEY tag.
     */
    public static String[] ExtractRecordsCalculated(String lkString)
    {
        String valueTag = GetData(lkString, CALCULATED_KEY, FS_str, AM_str);
        return splitArray(valueTag, RS_str);
    }

    /**
     * Looks for the RECORD_DICTS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RECORD_DICTS_KEY tag.
     */
    public static String[] ExtractRecordsDicts(String lkString)
    {
        String valueTag = GetData(lkString, RECORD_DICTS_KEY, FS_str, AM_str);
        return splitArray(valueTag, AM_str);
    }

    /**
     * Looks for the CALCULATED_DICTS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of CALCULATED_DICTS_KEY tag.
     */
    public static String[] ExtractRecordsCalculatedDicts(String lkString)
    {
        String valueTag = GetData(lkString, CALCULATED_DICTS_KEY, FS_str, AM_str);
        return splitArray(valueTag, AM_str);
    }

    /**
     * Looks for the RECORD_ID_DICTS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RECORD_ID_DICTS_KEY tag.
     */
    public static String[] ExtractRecordsIdDicts(String lkString)
    {
        String valueTag = GetData(lkString, RECORD_ID_DICTS_KEY, FS_str, AM_str);
        return splitArray(valueTag, AM_str);
    }

    /**
     * Looks for the ORIGINAL_RECORDS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of ORIGINAL_RECORDS_KEY tag.
     */
    public static String[] ExtractOriginalRecords(String lkString)
    {
        String valueTag = GetData(lkString, ORIGINAL_RECORDS_KEY, FS_str, AM_str);
        return splitArray(valueTag, RS_str);
    }

    /**
     * Looks for the RECORDS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RECORDS_KEY tag.
     */
    public static String[] ExtractDictionaries(String lkString)
    {
        String valueTag = GetData(lkString, RECORDS_KEY, FS_str, AM_str);
        return splitArray(valueTag, RS_str);
    }

    /**
     * Looks for the CONVERSION_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of CONVERSION_KEY tag.
     */
    public static String ExtractConversion(String lkString)
    {
        return GetData(lkString, CONVERSION_KEY, FS_str, AM_str);
    }

    /**
     * Looks for the FORMAT_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of FORMAT_KEY tag.
     */
    public static String ExtractFormat(String lkString)
    {
        return GetData(lkString, FORMAT_KEY, FS_str, AM_str);
    }

    /**
     * Looks for the CAPTURING_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of CAPTURING_KEY tag.
     */
    public static String ExtractCapturing(String lkString)
    {
        return GetData(lkString, CAPTURING_KEY, FS_str, AM_str);
    }

    /**
     * Looks for the RETURNING_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of RETURNING_KEY tag.
     */
    public static String ExtractReturning(String lkString)
    {
        return GetData(lkString, RETURNING_KEY, FS_str, AM_str);
    }

    /**
     * Looks for the ARGUMENTS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of ARGUMENTS_KEY tag.
     */
    public static String[] ExtractSubroutineArgs(String lkString)
    {
        String arguments = GetData(lkString, ARGUMENTS_KEY, FS_str, AM_str);
        return splitArray(arguments, DC4_str);
    }

    /**
     * Looks for the ROWPROPERTIES_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of ROWPROPERTIES_KEY tag.
     */
    public static String[] ExtractRowProperties(String lkString)
    {
        String rowProperties = GetData(lkString, ROWPROPERTIES_KEY, FS_str, AM_str);
        return splitArray(rowProperties, AM_str);
    }

    /**
     * Looks for the ROWHEADERS_KEY tag inside "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @return The value of ROWHEADERS_KEY tag.
     */
    public static String[] ExtractRowHeaders(String lkString)
    {
        String rowHeaders = GetData(lkString, ROWHEADERS_KEY, FS_str, AM_str);
        return splitArray(rowHeaders, AM_str);
    }

    /**
     * Looks for the "tag" inside the "lkString", and extracts its value.
     * @param lkString A String obtained as a result of executing an operation.
     * @param tag The tag to looking for
     * @param delimiter Delimiter String of every main items in "lkString"
     * @param delimiterThisList Delimiter String inside the first item of "lkString". The first item of "lkString" is always the header tags (THISLIST).
     * @returnThe value of tag.
     */
    private static String GetData(String lkString, String tag, String delimiter, String delimiterThisList)
    {
        String block = "";
        String[] parts = lkString.split(delimiter, -1);
        if (parts.length >= 1)
        {
            String[] headersList = parts[0].split(delimiterThisList, -1);
            for (int i = 1; i < headersList.length; i++)
            {
                if (tag.toUpperCase().equals(headersList[i].toUpperCase()))
                {
                    block = parts[i];
                    break;
                }
            }
        }
        return block;
    }

    /**
     * Auxiliary function to extract arrays inside a tag value.
     * @param valueTag The String to be split.
     * @param delimiter The String to use for split.
     * @return The array extracted.
     */
    private static String[] splitArray(String valueTag, String delimiter)
    {
        if ((valueTag == null || valueTag.length() == 0))
            return new String[] { };
        else
            return valueTag.split(delimiter, -1);
    }

    /* Composition Functions */

    /**
     * Composes the final String of various "recordsIds". Used by CRUD Operations.
     * @param recordIds Array with the "recordIds" to be joined
     * @return The final String od "recordIds" to be used CRUD Operations.
     */
    public static String ComposeRecordIds(String[] recordIds)
    {
        return JoinArray(recordIds, RS_str);
    }

    /**
     * Composes the final String of various "records". Used by CRUD Operations.
     * @param records Array with the "records" to be joined.
     * @return The final String od "records" to be used CRUD Operations.
     */
    public static String ComposeRecords(String[] records)
    {
        return JoinArray(records, RS_str);
    }
    
    /**
     * Composes the final string of various "originalRecords". Used by CRUD Operations.
     * @param originalRecords Array with the "originalRecords" to be joined.
     * @return The final string of "originalRecords" to be used by CRUD Operations.
     */
    public static String ComposeOriginalRecords(String[] originalRecords)
    {
        return JoinArray(originalRecords, RS_str);
    }

    /**
     * Composes the final String of various "dictionaries". Used by Read and Select Operations.
     * @param dictionaries Array with the "dictionaries" to be joined.
     * @return The final String of "dictionaries" to be used by Read and Select Operations.
     */
    public static String ComposeDictionaries(String[] dictionaries)
    {
        return JoinArray(dictionaries, " ");
    }

    /**
     * Composes the final String of various "expressions". Used by Format and Conversion Opertations.
     * @param expressions Array with the "expressions" to be joined.
     * @return The final String of "expressions" to be used in Format and Conversion operations.
     */
    public static String ComposeExpressions(String[] expressions)
    {
        return JoinArray(expressions, VM_str);
    }

    /**
     * Composes the final String of various "arguments" of a subroutine.
     * @param args Array with the "arguments" to be joined.
     * @return The final String to be used in Subroutine Operations.
     */
    public static String ComposeSubroutineArgs(String[] args)
    {
        return JoinArray(args, DC4_str);
    }

    /**
     * Auxiliary function to compose the final String of multiple items using "delitimer" as separation char.
     * @param items The "items" to be joined.
     * @param delimiter The "delimiter" String between the "items".
     * @return The final String with the different items joined by "delimiter" String.
     */
    private static String JoinArray(String[] items, String delimiter)
    {
        if (items != null && items.length > 0)
            return String.join(delimiter, items);
        else
            return "";
    }

    /**
     * Compose the fully buffer of the Update Operations with the block of "recordIds", "records" and "originalRecords".
     * @param recordIds Block of "recordIds". You can obtain this block with ComposeRecordIds function.
     * @param records Block of "records". You can obtain this block with ComposeRecords function.
     * @param originalRecords Block of "originalRecords". You can obtain this block with ComposeRecords function.
     * @return The buffer to be used by Update Operations.
     */
    public static String ComposeUpdateBuffer(String recordIds, String records, String originalRecords)
    {
        return recordIds + FS + records + FS + originalRecords;
    }
    
    /**
     * Compose the fully buffer of the Update Operations with the block of "recordIds", "records".
     * @param recordIds Block of "recordIds". You can obtain this block with ComposeRecordIds function.
     * @param records Block of "records". You can obtain this block with ComposeRecords function.
     * @return The buffer to be used by Update Operations.
     */
    public static String ComposeUpdateBuffer(String recordIds, String records)
    {
        return ComposeUpdateBuffer(recordIds,records,"");
    }

    /**
     * Compose the fully buffer of the Update Operations with the block of "recordIds", "records" and "originalRecords".
     * @param recordIds Array of "recordIds".
     * @param records Array of "records".
     * @param originalRecords Array of "originalRecords".
     * @return The buffer to be used by Update Operations.
     * @throws Exception
     */
    public static String ComposeUpdateBuffer(String[] recordIds, String[] records, String[] originalRecords) throws Exception
    {
        if ((recordIds.length != records.length && originalRecords == null) ||
            (recordIds.length != originalRecords.length))
            throw new Exception("The arrays must have the same length");

        return ComposeUpdateBuffer(ComposeRecordIds(recordIds), ComposeRecords(records), ComposeRecords(originalRecords));
    }
    
    /**
     * Compose the fully buffer of the Update Operations with the block of "recordIds" and "records".
     * @param recordIds Array of "recordIds".
     * @param records Array of "records".
     * @return The buffer to be used by Update Operations.
     * @throws Exception
     */
    public static String ComposeUpdateBuffer(String[] recordIds, String[] records) throws Exception
    {
        return ComposeUpdateBuffer(recordIds, records, null);
    }

    /**
     * Compose the fully buffer of the New Operations with the block of "recordIds" and "records".
     * @param recordIds Block of "recordIds". You can obtain this block with ComposeRecordIds function.
     * @param records Block of "records". You can obtain this block with ComposeRecords function.
     * @return The buffer to be used by New Operations.
     */
    public static String ComposeNewBuffer(String recordIds, String records)
    {
        return recordIds + FS + records;
    }

    /**
     * Compose the fully buffer of the New Operations with the block of "recordIds" and "records".
     * @param recordIds Array of "recordIds".
     * @param records Array of "records".
     * @return The buffer to be used by New Operations.
     * @throws Exception
     */
    public static String ComposeNewBuffer(String[] recordIds, String[] records) throws Exception
    {
        if (recordIds.length != records.length)
            throw new Exception("The arrays must have the same length");
        return ComposeNewBuffer(ComposeRecordIds(recordIds), ComposeRecords(records));
    }

    /**
     * Compose the fully buffer of the Delete Operations with the block of "recordIds" and "originalRecords".
     * @param recordIds Block of "recordIds". You can obtain this block with ComposeRecordIds function.
     * @param originalRecords Block of "originalRecords". You can obtain this block with ComposeRecords function.
     * @return The buffer to be used by Delete Operations.
     */
    public static String ComposeDeleteBuffer(String recordIds, String originalRecords)
    {
        if (originalRecords != null)
            return recordIds + FS + originalRecords;
        else
            return recordIds;
    }
    
    /**
     * Compose the fully buffer of the Delete Operations with the block of "recordIds".
     * @param recordIds Block of "recordIds". You can obtain this block with ComposeRecordIds function.
     * @return The buffer to be used by Delete Operations.
     */
    public static String ComposeDeleteBuffer(String recordIds)
    {
        return ComposeDeleteBuffer(recordIds, null);
    }

    /**
     * Compose the fully buffer of the Delete Operations with the block of "recordIds" and "originalRecords".
     * @param recordIds Array of "recordIds".
     * @param originalRecords Array of "originalRecords".
     * @return The buffer to be used by Delete Operations.
     * @throws Exception
     */
    public static String ComposeDeleteBuffer(String[] recordIds, String[] originalRecords) throws Exception
    {
        if (originalRecords != null && recordIds.length != originalRecords.length)
            throw new Exception("The arrays must have the same length");
        return ComposeDeleteBuffer(ComposeRecordIds(recordIds), ComposeRecords(originalRecords));
    }
    
    /**
     * Compose the fully buffer of the Delete Operations with the block of "recordIds".
     * @param recordIds Array of "recordIds".
     * @return The buffer to be used by Delete Operations.
     * @throws Exception
     */
    public static String ComposeDeleteBuffer(String[] recordIds) throws Exception
    {
        return ComposeDeleteBuffer(recordIds, null);
    }

}
