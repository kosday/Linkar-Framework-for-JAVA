package linkar.data;

import linkar.functions.MvOperations;
import linkar.strings.StringFunctions;

public class LkItem {
    String[] LstDictsId;
    String[] LstDicts;
    String[] LstDictsCalculated;
   
    /**
     * LstDictsId
     * @return Array with the dictionary names for record Ids.
     */
    public String[] getLstDictsId()
    {
    	return LstDictsId;
    }
    
    /**
     * LstDictsId
     * @return Array with the dictionary names for record fields.
     */
    public String[] getLstDicts()
    {
    	return LstDicts;
    }
    
    /**
     * LstDictsCalculated
     * @return Array with the dictionary names for calculated fields of the record.
     */
    public String[] getLstDictsCalculated()
    {
    	return LstDictsCalculated;
    }

    /**
     * The ID of the record.
     */
    public String RecordId;
    
    /**
     * The content of a record from database.
     */
    public String Record;

    /**
     * A copy of the original record to be used in operations where the optimistic lock option is enabled.
     */
    public String OriginalRecord;

    /**
     * The content of calculated fields from database.
     */
    public String Calculated;

    /**
     * Initializes a new instance of the LkItem class.
     */
    public LkItem()
    {
        this.LstDictsId = new String[0];
        this.LstDicts = new String[0];
        this.LstDictsCalculated = new String[0];
        this.RecordId = "";
        this.Record = "";
        this.OriginalRecord = "";
        this.Calculated = "";
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     */
    public LkItem(String recordId)
    {
        this.RecordId = recordId;
        this.Record = "";
        this.OriginalRecord = "";
        this.Calculated = "";
        this.LstDictsId = new String[0];
        this.LstDicts = new String[0];
    	this.LstDictsCalculated = new String[0];
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     */   
    public LkItem(String recordId, String record)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = "";
        this.Calculated = "";
        this.LstDictsId = new String[0];
        this.LstDicts = new String[0];
    	this.LstDictsCalculated = new String[0];
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     * @param calculateds The content of the calculated fields of the records.
     */
    public LkItem(String recordId, String record, String calculateds)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = "";
        this.Calculated = calculateds;
        this.LstDictsId = new String[0];
        this.LstDicts = new String[0];
    	this.LstDictsCalculated = new String[0];
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     * @param calculateds The content of the calculated fields of the records.
     * @param originalRecord A copy of the original record to be used in operations where the optimistic lock option is enabled.
     */
    public LkItem(String recordId, String record, String calculateds, String originalRecord)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = originalRecord;
        this.Calculated = calculateds;
        this.LstDictsId = new String[0];
        this.LstDicts = new String[0];
    	this.LstDictsCalculated = new String[0];
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     * @param calculateds The content of the calculated fields of the records.
     * @param originalRecord A copy of the original record to be used in operations where the optimistic lock option is enabled.
     * @param lstDictsId Optionally, array with the dictionary names for record Ids.
     */
    public LkItem(String recordId, String record, String calculateds, String originalRecord, String[] lstDictsId)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = originalRecord;
        this.Calculated = calculateds;
        if (lstDictsId == null)
            this.LstDictsId = new String[0];
        else
            this.LstDictsId = lstDictsId;
        this.LstDicts = new String[0];
    	this.LstDictsCalculated = new String[0];
    }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     * @param calculateds The content of the calculated fields of the records.
     * @param originalRecord A copy of the original record to be used in operations where the optimistic lock option is enabled.
     * @param lstDictsId Optionally, array with the dictionary names for record Ids.
     * @param lstDicts Optionally, array with the dictionarty names for record fields.
     */
    public LkItem(String recordId, String record, String calculateds, String originalRecord, String[] lstDictsId, String[] lstDicts)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = originalRecord;
        this.Calculated = calculateds;
        if (lstDictsId == null)
            this.LstDictsId = new String[0];
        else
            this.LstDictsId = lstDictsId;
        if (lstDicts == null)
            this.LstDicts = new String[0];
        else
            this.LstDicts = lstDicts;
        this.LstDictsCalculated = new String[0];;
    } 

    /**
     * Initializes a new instance of the LkItem class.
     * @param recordId The ID of the record.
     * @param record The content of a record from database.
     * @param calculateds The content of the calculated fields of the records.
     * @param originalRecord A copy of the original record to be used in operations where the optimistic lock option is enabled.
     * @param lstDictsId Optionally, array with the dictionary names for record Ids.
     * @param lstDicts Optionally, array with the dictionarty names for record fields.
     * @param lstDictsCalculated Optionally, array with the dictionary names for calculated fields of the record.
     */
    public LkItem(String recordId, String record, String calculateds, String originalRecord, String[] lstDictsId, String[] lstDicts, String[] lstDictsCalculated)
    {
        this.RecordId = recordId;
        this.Record = record;
        this.OriginalRecord = originalRecord;
        this.Calculated = calculateds;
        if (lstDictsId == null)
            this.LstDictsId = new String[0];
        else
            this.LstDictsId = lstDictsId;
        if (lstDicts == null)
            this.LstDicts = new String[0];
        else
            this.LstDicts = lstDicts;
        if (lstDictsCalculated == null)
            this.LstDictsCalculated = new String[0];
        else
            this.LstDictsCalculated = lstDictsCalculated;
    }  
    
  
    /**
     * Indexer that get fields, multivalues or subvalues from the record.
     * @param field The field number to get.
     * @param mv The multivalue number to get.
     * @param sv The subvalue number to get.
     * @return The extrated value.
     */
    public String get(int field, int mv, int sv)
    {
    	return MvOperations.LkExtract(this.Record, field, mv, sv);
    }
    
    /**
     * Indexer that get fields, multivalues or subvalues from the record.
     * @param field The field number to get.
     * @param mv The multivalue number to get.
     * @return The extrated value.
     */
    public String get(int field, int mv)
    {
    	return get(field, mv, 0);
    }
    
    /**
     * Indexer that get fields, multivalues or subvalues from the record.
     * @param field The field number to get.
     * @return The extrated value.
     */
    public String get(int field)
    {
    	return get(field, 0, 0);
    }
    
    /**
     * Indexer that set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param field The field number to set.
     * @param mv The multivalue number to set.
     * @param sv The subvalue number to set.
     */
    public void set(String value, int field, int mv, int sv)
    {
    	this.Record = MvOperations.LkReplace(this.Record, value, field, mv, sv);
    }
    
    /**
     * Indexer that set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param field The field number to set.
     * @param mv The multivalue number to set.
     */
    public void set(String value, int field, int mv)
    {
    	set(value, field, mv, 0);
    }
    
    /**
     * Indexer that set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param field The field number to set.
     */
    public void set(String value, int field)
    {
    	set(value, field, 0, 0);
    }
    
    /**
     * Indexer that using the dictionary name can get fields, multivalues or subvalues from the record.
     * @param dictName The dictionary name to get.
     * @param mv The multivalue number to get.
     * @param sv The subvalue number to get.
     * @return The extracted value.
     * @throws Exception
     */
    public String get(String dictName, int mv, int sv) throws Exception
    {
        for (int i = 0; i < this.LstDictsId.length; i++)
            if (this.LstDictsId[i].equals(dictName))
            {
                return this.RecordId;
            }
        for (int i = 0; i < this.LstDicts.length; i++)
            if (this.LstDicts[i].equals(dictName))
            {
                return MvOperations.LkExtract(this.Record, (i + 1), mv, sv);
            }
        for (int i = 0; i < this.LstDictsCalculated.length; i++)
            if (this.LstDictsCalculated[i].equals(dictName))
            {
                return MvOperations.LkExtract(this.Calculated, (i + 1), mv, sv);
            }
        throw new Exception("Dictionary name not found");
    }
    
    /**
     * Indexer that using the dictionary name can get fields, multivalues or subvalues from the record.
     * @param dictName The dictionary name to get.
     * @param mv The multivalue number to get.
     * @return The extracted value.
     * @throws Exception
     */
    public String get(String dictName, int mv) throws Exception
    {
        return get(dictName, mv, 0);
    }
    
    /**
     * Indexer that using the dictionary name can get fields, multivalues or subvalues from the record.
     * @param dictName The dictionary name to get.
     * @return The extracted value.
     * @throws Exception
     */
    public String get(String dictName) throws Exception
    {
        return get(dictName, 0, 0);
    }
    
    /**
     * Indexer that using the dictionary name can set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param dictName The dictionary name to set.
     * @param mv The multivalue number to set.
     * @param sv The subvalue number to set.
     * @throws Exception
     */
    public void set(String value, String dictName, int mv, int sv) throws Exception
    {
        if (this.LstDictsId.length == 0)
            throw new Exception("Dictionaries ID List Empty");
        for (int i = 0; i < this.LstDictsId.length; i++)
            if (this.LstDictsId[i].equals(dictName))
            {
                this.RecordId = value;
                return;
            }
        if (this.LstDictsId.length == 0)
            throw new Exception("Dictionaries List Empty");
        for (int i = 0; i < this.LstDicts.length; i++)
            if (this.LstDicts[i].equals(dictName))
            {
                this.Record = MvOperations.LkReplace(this.Record, value, (i + 1), mv, sv);
                return;
            }
        throw new Exception("Dictionary name not found");
    }
    
    /**
     * Indexer that using the dictionary name can set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param dictName The dictionary name to set.
     * @param mv The multivalue number to set.
     * @throws Exception
     */
    public void set(String value, String dictName, int mv) throws Exception
    {
    	set(value, dictName, mv, 0);
    }
    
    /**
     * Indexer that using the dictionary name can set fields, multivalues or subvalues from the record.
     * @param value The value to set.
     * @param dictName The dictionary name to set.
     * @throws Exception
     */
    public void set(String value, String dictName) throws Exception
    {
    	set(value, dictName, 0, 0);
    }

    /**
     * Composes the final buffer String of the record that will be readed, in MV Read operations, with the RecordId information.
     * @return The final String buffer for MV Read operations.
     */
    public String ComposeReadBuffer()
    {
        return this.RecordId;
    }

    /**
     * Composes the final buffer String of the record that will be updated, in MV Update operations, with the RecordId, the Record, and optionally the OriginalRecord information.
     * @param includeOriginalBuffer Determines if the OriginalRecord must be include or not in the final buffer String.
     * @return The final String buffer for MV Update operations.
     */
    public String ComposeUpdateBuffer(boolean includeOriginalBuffer)
    {
    	if (includeOriginalBuffer)
    		return StringFunctions.ComposeUpdateBuffer(this.RecordId, this.Record, this.OriginalRecord);
    	else
    		return StringFunctions.ComposeUpdateBuffer(this.RecordId, this.Record);
    }
    
    /**
     * Composes the final buffer String of the record that will be updated, in MV Update operations, with the RecordId and the Record.
     * @return The final String buffer for MV Update operations.
     */
    public String ComposeUpdateBuffer()
    {
        return ComposeUpdateBuffer(false);
    }

    /**
     * Composes the final buffer String of the record that will be created, in MV New operations, with the RecordId and the Record information.
     * @return The final String buffer for MV New operations.
     */
    public String ComposeNewBuffer()
    {
        return StringFunctions.ComposeNewBuffer(this.RecordId, this.Record);
    }

    /**
     * Composes the final buffer String of the record that will be deleted, in MV Delete operations, with the RecordId and optionally with the OriginalRecord information.
     * @param includeOriginalBuffer Determines if the OriginalRecord must be include or not in the final buffer String.
     * @return The final String buffer for MV Delete operations.
     */
    public String ComposeDeleteBuffer(boolean includeOriginalBuffer)
    {
        if (includeOriginalBuffer)
            return StringFunctions.ComposeDeleteBuffer(this.RecordId, this.OriginalRecord);
        else
            return StringFunctions.ComposeDeleteBuffer(this.RecordId);
    }
    
    /**
     * Composes the final buffer String of the record that will be deleted, in MV Delete operations, with the RecordId.
     * @return The final String buffer for MV Delete operations.
     */
    public String ComposeDeleteBuffer()
    {
        return ComposeDeleteBuffer(false);
    }
}
