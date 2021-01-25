package linkar.data;

import linkar.strings.StringFunctions;

/**
 * Class to management the result of the operations Read, Update, New, Delete, Select and Dictionaries.
 */
public class LkDataCRUD extends LkData {
	
	private int TotalItems;
	/**
	 * Number of the items
	 * @return Total Items
	 */
    public int getTotalItems() { return TotalItems; }
    
    /**
     * LkItem list from the CRUD operation execution.
     */
    public LkItems LkRecords;

    /**
     * Initializes a new instance of the LkDataCRUD class.
     */
    public LkDataCRUD()
    {
    	super("");
        this.LkRecords = new LkItems();
        this.TotalItems = 0;
    }

    /**
     * Initializes a new instance of the LkDataCRUD class.
     * @param crudOperationResult The String result of the CRUD operation execution.
     */
    public LkDataCRUD(String crudOperationResult)
    {
    	super(crudOperationResult);
        this.TotalItems = StringFunctions.ExtractTotalRecords(crudOperationResult);

        String[] lstIdDicts = StringFunctions.ExtractRecordsIdDicts(crudOperationResult);
        String[] lstDictionaries = StringFunctions.ExtractRecordsDicts(crudOperationResult);
        String[] lstCalculatedDicts = StringFunctions.ExtractRecordsCalculatedDicts(crudOperationResult);
        this.LkRecords = new LkItems(lstIdDicts, lstDictionaries, lstCalculatedDicts);

        String[] lstRecords = StringFunctions.ExtractRecords(crudOperationResult);
        String[] lstRecordIds = StringFunctions.ExtractRecordIds(crudOperationResult);
        String[] lstOriginalRecords = StringFunctions.ExtractOriginalRecords(crudOperationResult);
        String[] lstRecordsCalculated = StringFunctions.ExtractRecordsCalculated(crudOperationResult);
        for (int i = 0; i < lstRecordIds.length; i++)
        {
            String record = (lstRecords.length == lstRecordIds.length ? lstRecords[i] : "");
            String originalRecord = (lstOriginalRecords.length == lstRecordIds.length ? lstOriginalRecords[i] : "");
            String calculateds = (lstRecordsCalculated.length == lstRecordIds.length ? lstRecordsCalculated[i] : "");
            LkItem lkRecord = new LkItem(lstRecordIds[i], record, calculateds, originalRecord);
            this.LkRecords.add(lkRecord);
        }
    }
}
