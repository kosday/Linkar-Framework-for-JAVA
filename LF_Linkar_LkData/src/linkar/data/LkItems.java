package linkar.data;

import java.util.ArrayList;

import linkar.functions.ASCII_Chars;

public class LkItems extends ArrayList<LkItem> {

    private String[] LstDictsId = new String[0];
    private String[] LstDicts = new String[0];
    private String[] LstDictsCalculated = new String[0];
   
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
     * Initializes a new instance of the LkItem class.
     */
    public LkItems()
    { }
    
    /**
     * Initializes a new instance of the LkItem class.
     * @param lstIdDicts Array with the dictionary names for record Ids. The same array for each LkItem that is stored in the list.
     * @param lstDictionaries Array with the dictionarty names for record fields. The same array for each LkItem that is stored in the list.
     */
    public LkItems(String[] lstIdDicts, String[] lstDictionaries)
    {
        this.LstDictsId = lstIdDicts;
        this.LstDicts = lstDictionaries;
        this.LstDictsCalculated = new String[0];
    }

    /**
     * Initializes a new instance of the LkItem class.
     * @param lstIdDicts Array with the dictionary names for record Ids. The same array for each LkItem that is stored in the list.
     * @param lstDictionaries Array with the dictionarty names for record fields. The same array for each LkItem that is stored in the list.
     * @param lstCalculatedDicts Array with the dictionary names for calculated fields of the record. The same array for each LkItem that is stored in the list.
     */
    public LkItems(String[] lstIdDicts, String[] lstDictionaries, String[] lstCalculatedDicts)
    {
        this.LstDictsId = lstIdDicts;
        this.LstDicts = lstDictionaries;
        if (lstCalculatedDicts == null)
            this.LstDictsCalculated = new String[0];
        else
            this.LstDictsCalculated = lstCalculatedDicts;
    }
    
    /**
     * Indexer to get a LkItem using its RecordId.
     * @param id The record Id of the LkItem
     * @return LkItem The LkItem extracted.
     */
    public LkItem get(String id)
    {
        for (int i = 0; i < this.size(); i++)
            if (super.get(i).RecordId.equals(id))
                return super.get(i);

        return null;
    }
	
    /**
     * Adds a new LkItem to the list. The dictionaries arrays of the list, will be copied to the LkItem added.
     * @param lkItem The LkItem to be added.
     * @return boolean Is correctly added.
     */
	@Override
	public boolean add(LkItem lkItem) {
        if (!(lkItem.RecordId == null || lkItem.RecordId.length() == 0) && !(this.stream().filter(o -> o.RecordId.equals(lkItem.RecordId)).findFirst().isPresent()))
        {
            lkItem.LstDictsId = this.LstDictsId;
            lkItem.LstDicts = this.LstDicts;
            lkItem.LstDictsCalculated = this.LstDictsCalculated;
            super.add(lkItem);
            return true;
        }
        else
        	return false;
	}
	
	/**
	 * Creates and adds LkItem with specific recordIds to the list.
	 * @param recordIds Array with the list of recordIds
	 */
    public void Add(String[] recordIds)
    {
        for (String id:recordIds)
        {
            LkItem lkRecord = new LkItem(id);
            add(lkRecord);
        }
    }

    /**
     * Removes the LkItem specified by its recordID from the list.
     * @param recordId The recordId of the LkItem to be removed.
     */
    public void RemoveId(String recordId)
    {
        LkItem itemToRemove = null;
        for (int i = 0; i < super.size(); i++)
        {
            if (super.get(i).RecordId.equals(recordId))
            {
                itemToRemove = super.get(i);
                break;
            }
        }
        if (itemToRemove != null)
            super.remove(itemToRemove);

    }

    /**
     * Composes the final buffer String of the all record in the list that will be readed, in MV Read operations, with the RecordId information.
     * @return The final String buffer for MV Read operations.
     */
    public String ComposeReadBuffer()
    {
        String buf = "";
        for (int i = 0; i < this.size(); i++)
        {
            if (i > 0)
                buf += ASCII_Chars.RS_chr;
            buf += super.get(i).RecordId;
        }

        return buf;
    }

    /**
     * Composes the final buffer String of the all records in the list that will be updated, in MV Update operations, with the RecordId, the Record, and optionally the OriginalRecord information.
     * @param includeOriginalBuffer Determines if the OriginalRecord must be include or not in the final buffer String.
     * @return The final String buffer for MV Update operations.
     */
    public String ComposeUpdateBuffer(boolean includeOriginalBuffer)
    {
        String buf = "";
        for (int i = 0; i < this.size(); i++)
        {
            if (i > 0)
                buf += ASCII_Chars.RS_chr;
            buf += super.get(i).RecordId;
        }

        buf += ASCII_Chars.FS_chr;

        for (int i = 0; i < this.size(); i++)
        {
            if (i > 0)
                buf += ASCII_Chars.RS_chr;
            buf += super.get(i).Record;
        }

        if (includeOriginalBuffer)
        {
            buf += ASCII_Chars.FS_chr;

            for (int i = 0; i < this.size(); i++)
            {
                if (i > 0)
                    buf += ASCII_Chars.RS_chr;
                buf += super.get(i).OriginalRecord;
            }
        }

        return buf;
    }
    
    /**
     * Composes the final buffer String of the all records in the list that will be updated, in MV Update operations, with the RecordId and the Record.
     * @return The final String buffer for MV Update operations.
     */
    public String ComposeUpdateBuffer()
    {
        return ComposeUpdateBuffer(false);
    }

    /**
     * Composes the final buffer String of the all records that will be created, in MV New operations, with the RecordId and the Record information.
     * @return The final String buffer for MV New operations.
     */
    public String ComposeNewBuffer()
    {
        return ComposeUpdateBuffer(false);
    }

    /**
     * Composes the final buffer String of the all records that will be deleted, in MV Delete operations, with the RecordId and optionally with the OriginalRecord information.
     * @param includeOriginalBuffer Determines if the OriginalRecord must be include or not in the final buffer String.
     * @return The final String buffer for MV Delete operations.
     */
    public String ComposeDeleteBuffer(boolean includeOriginalBuffer)
    {
        String buf = "";
        for (int i = 0; i < this.size(); i++)
        {
            if (i > 0)
                buf += ASCII_Chars.RS_chr;
            buf += super.get(i).RecordId;
        }

        if (includeOriginalBuffer)
        {
            buf += ASCII_Chars.FS_chr;

            for (int i = 0; i < this.size(); i++)
            {
                if (i > 0)
                    buf += ASCII_Chars.RS_chr;
                buf += super.get(i).OriginalRecord;
            }
        }

        return buf;
    }
    
    /**
     * Composes the final buffer String of the all records that will be deleted, in MV Delete operations, with the RecordId.
     * @return The final String buffer for MV Delete operations.
     */
    public String ComposeDeleteBuffer()
    {
        return ComposeDeleteBuffer(false);
    }

}
