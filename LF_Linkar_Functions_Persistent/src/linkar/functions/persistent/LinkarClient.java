package linkar.functions.persistent;

import linkar.ConnectionInfo;
import linkar.CredentialOptions;
import linkar.Linkar;
import linkar.functions.*;

/**
 * These functions perform synchronous persistent (establishing permanent session) operations with any kind of output format type.
 */
public class LinkarClient
{
    private ConnectionInfo ConnectionInfo;
    private int ReceiveTimeout;

    /**
     * SessionId
     * @return A unique Identifier for the stablished session in LinkarSERVER. This value is set after Login operation.
     */
    public String getSessionId()
    {
        if (this.ConnectionInfo != null)
            return this.ConnectionInfo.getSessionId();
        else
            return "";
    }

    /**
     * Initializes a new instance of the LinkarClient class.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely). When the receiveTimeout argument is omitted in any operation, the value set here will be applied.
     */
    public LinkarClient(int receiveTimeout)
    {
        this.ReceiveTimeout = receiveTimeout;
        this.ConnectionInfo = null;
    }
    
    /**
     * Initializes a new instance of the LinkarClient class.
     */
    public LinkarClient()
    {
        this.ReceiveTimeout = 0;
        this.ConnectionInfo = null;
    }

    /* LOGIN */
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a synchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @throws Exception
     */
    public void Login(CredentialOptions credentialOptions, String customVars, int receiveTimeout) throws Exception
    {
        if (this.ConnectionInfo == null)
        {
            String options = "";
            String loginArgs = customVars + ASCII_Chars.US_chr + options;
            byte byteOpCode = OPERATION_CODE.LOGIN.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            if (receiveTimeout <= 0)
            {
                if (this.ReceiveTimeout > 0)
                    receiveTimeout = this.ReceiveTimeout;
            }
            ConnectionInfo connectionInfo = new ConnectionInfo("", "", "", credentialOptions);
            String loginResult = Linkar.ExecutePersistentOperation(connectionInfo, byteOpCode, loginArgs, byteInputFormat, byteOutputFormat, receiveTimeout);

            if (!(loginResult == null || loginResult.length() == 0))
            {
            	String valueTag = "";
                String[] parts = loginResult.split(ASCII_Chars.FS_str);
                if (parts.length >= 1)
                {
                    String[] headersList = parts[0].split(DBMV_Mark.AM_str);
                    for (int i = 1; i < headersList.length; i++)
                    {
                        if ("RECORD_ID".equals(headersList[i].toUpperCase()))
                        {
                        	valueTag = parts[i];
                            break;
                        }
                    }
                }
                String[] records;
                if (valueTag == null || valueTag.length() == 0)
                	records = new String[] { };
                else
                	records =  valueTag.split(ASCII_Chars.RS_str);   	
            	
                if (records.length == 1)
                {
                    String sessionId = records[0];
                    this.ConnectionInfo = new ConnectionInfo(sessionId, connectionInfo.getLkConnectionId(), connectionInfo.getPublicKey(), credentialOptions);                    
                }
            }
        }
    }
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a synchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @throws Exception
     */
    public void Login(CredentialOptions credentialOptions, String customVars) throws Exception
    {
        Login(credentialOptions, customVars, 0);
    }
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a synchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @throws Exception
     */
    public void Login(CredentialOptions credentialOptions) throws Exception
    {
        Login(credentialOptions, "");
    }
    
    /* LOGOUT */

    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a synchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @throws Exception
     */
    public void Logout(String customVars, int receiveTimeout) throws Exception
    {
        String logoutArgs = customVars;

        byte byteOpCode = OPERATION_CODE.LOGOUT.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        if (receiveTimeout <= 0)
        {
            if (this.ReceiveTimeout > 0)
                receiveTimeout = this.ReceiveTimeout;
        }
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, byteOpCode, logoutArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        if (!(result == null || result.length() == 0))
            this.ConnectionInfo = null;
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a synchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @throws Exception
     */
    public void Logout(String customVars) throws Exception
    {
    	Logout(customVars, 0);
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a synchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @throws Exception
     */
    public void Logout() throws Exception
    {
    	Logout("");
    }
    
    /* READ */

    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries, ReadOptions readOptions, 
    		DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        if (this.ConnectionInfo != null)
        {
            String readArgs = OperationArguments.GetReadArgs(filename, recordIds, dictionaries, readOptions, customVars);
            byte opCode = OPERATION_CODE.READ.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, readArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        else
            return "";
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries, ReadOptions readOptions, 
    		DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat, String customVars) throws Exception
    {
        return Read(filename, recordIds, dictionaries, readOptions, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries, ReadOptions readOptions, 
    		DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
    {
        return Read(filename, recordIds, dictionaries, readOptions, inputFormat, outputFormat, "");
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries, ReadOptions readOptions,
    		DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return Read(filename, recordIds, dictionaries, readOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries, ReadOptions readOptions) throws Exception
    {
        return Read(filename, recordIds, dictionaries, readOptions, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds, String dictionaries) throws Exception
    {
        return Read(filename, recordIds, dictionaries, null);
    }
    
    /**
     * Reads one or several records of a file in synchronous way.
     * @param filename File name to read.
     * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String recordIds) throws Exception
    {
        return Read(filename, recordIds, "");
    }
    
    /* UPDATE */

    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars, int receiveTimeout) throws Exception
    {
        String updateArgs = OperationArguments.GetUpdateArgs(filename, records, updateOptions, customVars);
        byte opCode = OPERATION_CODE.UPDATE.getnumVal();
        byte byteInputFormat = inputFormat.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, updateArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars) throws Exception
    {
        return Update(filename, records, updateOptions, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
    {
        return Update(filename, records, updateOptions, inputFormat, outputFormat, "");
    }
    
    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return Update(filename, records, updateOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions) throws Exception
    {
        return Update(filename, records, updateOptions, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records) throws Exception
    {
        return Update(filename, records, null);
    }
    
    /* UPDATEPARTIAL */

    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars, int receiveTimeout) throws Exception
    {
        String updateArgs = OperationArguments.GetUpdatePartialArgs(filename, records, dictionaries, updateOptions, customVars);
        byte opCode = OPERATION_CODE.UPDATEPARTIAL.getnumVal();
        byte byteInputFormat = inputFormat.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, updateArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars) throws Exception
    {
        return UpdatePartial(filename, records, dictionaries, updateOptions, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
    {
        return UpdatePartial(filename, records, dictionaries, updateOptions, inputFormat, outputFormat, "");
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries, UpdateOptions updateOptions,
        DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return UpdatePartial(filename, records, dictionaries, updateOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries, UpdateOptions updateOptions) throws Exception
    {
        return UpdatePartial(filename, records, dictionaries, updateOptions, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records, String dictionaries) throws Exception
    {
        return UpdatePartial(filename, records, dictionaries, null);
    }
    
    /**
     * Update one or more attributes of one or more file records, in a synchronous way.
     * <p>
     * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
     * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
     * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
     * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
     * The record will have to be read, modified and saved again.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
     * @return The results of the operation.
     * @throws Exception
     */
    public String UpdatePartial(String filename, String records) throws Exception
    {
        return UpdatePartial(filename, records, "");
    }
    
    /* NEW */

    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records,  NewOptions newOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars, int receiveTimeout) throws Exception
    {
        String newArgs = OperationArguments.GetNewArgs(filename, records, newOptions, customVars);
        byte opCode = OPERATION_CODE.NEW.getnumVal();
        byte byteInputFormat = inputFormat.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, newArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records,  NewOptions newOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
        String customVars) throws Exception
    {
        return New(filename, records,  newOptions, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records,  NewOptions newOptions,
        DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
    {
        return New(filename, records,  newOptions, inputFormat, outputFormat, "");
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records,  NewOptions newOptions,
        DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return New(filename, records,  newOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records,  NewOptions newOptions) throws Exception
    {
        return New(filename, records,  newOptions, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way.
     * <p>
     * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records) throws Exception
    {
        return New(filename, records,  null);
    }
    
    /* DELETE */

    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
    	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
        String customVars, int receiveTimeout) throws Exception
    {
        String deleteArgs = OperationArguments.GetDeleteArgs(filename, records, deleteOptions, customVars);
        byte opCode = OPERATION_CODE.DELETE.getnumVal();
        byte byteInputFormat = inputFormat.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, deleteArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
    	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
        String customVars) throws Exception
    {
        return Delete(filename, records, deleteOptions, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
    	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Delete(filename, records, deleteOptions, inputFormat, outputFormat, "");
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
    	DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return Delete(filename, records, deleteOptions, inputFormat, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions) throws Exception
    {
        return Delete(filename, records, deleteOptions, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way
     * <p>
     * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
     * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
     * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
     * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
     * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
     * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
     * The record will have to be read, and deleted again.
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records) throws Exception
    {
        return Delete(filename, records, null);
    }
    
    /* SELECT */

    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
     * In the preSelectClause argument these operations can be carried out before executing the Select statement:
     * <ul>
     *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
     *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
     *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
     * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        DATAFORMATCRU_TYPE outputFormat,
        String customVars, int receiveTimeout) throws Exception
    {
        String selectArgs = OperationArguments.GetSelectArgs(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, customVars);
        byte opCode = OPERATION_CODE.SELECT.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, selectArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        DATAFORMATCRU_TYPE outputFormat,
        String customVars) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, outputFormat, customVars, 0);
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        DATAFORMATCRU_TYPE outputFormat) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, outputFormat, "");
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, DATAFORMATCRU_TYPE.MV);
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, null);
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause) throws Exception
    {
        return Select(filename, selectClause, sortClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause) throws Exception
    {
        return Select(filename, selectClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename) throws Exception
    {
        return Select(filename, "");
    }
    
    /* SUBROUTINE */

    /**
     * Executes a subroutine, in a synchronous way.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String subroutineArgs = OperationArguments.GetSubroutineArgs(subroutineName, argsNumber, arguments, customVars);
        byte opCode = OPERATION_CODE.SUBROUTINE.getnumVal();
        byte byteInputFormat = inputFormat.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, subroutineArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Executes a subroutine, in a synchronous way.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat, String customVars) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, inputFormat, outputFormat, customVars, 0);
    }
    
    /**
     * Executes a subroutine, in a synchronous way.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, inputFormat, outputFormat, "");
    }
    
    /**
     * Executes a subroutine, in a synchronous way.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, DATAFORMAT_TYPE inputFormat) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, inputFormat, DATAFORMAT_TYPE.MV);
    }
    
    /**
     * Executes a subroutine, in a synchronous way.
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, DATAFORMAT_TYPE.MV);
    }
    
    /* CONVERSION */

    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code, DATAFORMAT_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String conversionArgs = OperationArguments.GetConversionArgs(expression, code, conversionType, customVars);
        byte opCode = OPERATION_CODE.CONVERSION.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, conversionArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code, DATAFORMAT_TYPE outputFormat, String customVars) throws Exception
    {
        return Conversion(conversionType, expression, code, outputFormat, customVars, 0);
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Conversion(conversionType, expression, code, outputFormat, "");
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code) throws Exception
    {
        return Conversion(conversionType, expression, code, DATAFORMAT_TYPE.MV);
    }
    
    /* FORMAT */

    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec, DATAFORMAT_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String formatArgs = OperationArguments.GetFormatArgs(expression, formatSpec, customVars);
        byte opCode = OPERATION_CODE.FORMAT.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, formatArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec, DATAFORMAT_TYPE outputFormat, String customVars) throws Exception
    {
        return Format(expression, formatSpec, outputFormat, customVars, 0);
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Format(expression, formatSpec, outputFormat, "");
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec) throws Exception
    {
        return Format(expression, formatSpec, DATAFORMAT_TYPE.MV);
    }
    
    /* DICTIONARIES */

    /**
     * Returns all the dictionaries of a file, in a synchronous way.
     * @param filename File name
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename, DATAFORMAT_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String dictionariesArgs = OperationArguments.GetDictionariesArgs(filename, customVars);
        byte opCode = OPERATION_CODE.DICTIONARIES.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, dictionariesArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns all the dictionaries of a file, in a synchronous way.
     * @param filename File name
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename, DATAFORMAT_TYPE outputFormat, String customVars) throws Exception
    {
        return Dictionaries(filename, outputFormat, customVars, 0);
    }
    
    /**
     * Returns all the dictionaries of a file, in a synchronous way.
     * @param filename File name
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Dictionaries(filename, outputFormat, "");
    }
    
    /**
     * Returns all the dictionaries of a file, in a synchronous way.
     * @param filename File name
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename) throws Exception
    {
        return Dictionaries(filename, DATAFORMAT_TYPE.MV);
    }
    
    /* EXECUTE */

    /**
     * Allows the execution of any command from the Database in a synchronous way.
     * @param statement The command you want to execute in the Database.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement, DATAFORMAT_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String executeArgs = OperationArguments.GetExecuteArgs(statement, customVars);
        byte opCode = OPERATION_CODE.EXECUTE.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, executeArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }   
    
    /**
     * Allows the execution of any command from the Database in a synchronous way.
     * @param statement The command you want to execute in the Database.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement, DATAFORMAT_TYPE outputFormat, String customVars) throws Exception
    {
        return Execute(statement, outputFormat, customVars, 0);
    }   
    
    /**
     * Allows the execution of any command from the Database in a synchronous way.
     * @param statement The command you want to execute in the Database.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement, DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return Execute(statement, outputFormat, "");
    } 
    
    /**
     * Allows the execution of any command from the Database in a synchronous way.
     * @param statement The command you want to execute in the Database.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement) throws Exception
    {
        return Execute(statement, DATAFORMAT_TYPE.MV);
    } 
    
    /* GETVERSION */

    /**
     * Allows getting the client version.
     * @return The results of the operation.
     */
    public static String GetLocalVersion()
    {
        return Linkar.getClientVersion();
    }

    /**
     * Allows getting the server version, in a synchronous way.
     * <p>
	 * This function returns the following information
	 * <ul>
	 * <li>LKMVCOMPONENTSVERSION: MV Components version.</li>
	 * <li>LKSERVERVERSION: Linkar SERVER version.</li>
	 * <li>LKCLIENTVERSION: Used client library version.</li>
	 * <li>DATABASE: Database.</li>
	 * <li>OS: Operating system.</li>
	 * <li>DATEZERO: Date zero base in YYYYMMDD format.</li>
	 * <li>DATEOUTPUTCONVERSION: Output conversion for date used by Linkar Schemas.</li>
	 * <li>TIMEOUTPUTCONVERSION: Output conversion for time used by Linkar Schemas.</li>
	 * <li>MVDATETIMESEPARATOR: DateTime used separator used by Linkar Schemas, for instance 18325,23000.</li>
	 * <li>MVBOOLTRUE: Database used char for the Boolean true value used by Linkar Schemas.</li>
	 * <li>MVBOOLFALSE: Database used char for the Boolean false value used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLTRUE: Used char for the Boolean true value out of the database used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLFALSE: Used char for the Boolean false value out of the database used by Linkar Schemas.</li>
	 * <li>MVDECIMALSEPARATOR: Decimal separator in the database. May be point, comma or none when the database does not store decimal numbers. Used by Linkar Schemas.</li>
	 * <li>OTHERLANGUAGES: Languages list separated by commas.</li>
	 * <li>TABLEROWSEPARATOR: It is the decimal char that you use to separate the rows in the output table format. By default 11.</li>
	 * <li>TABLECOLSEPARATOR: It is the decimal char that you use to separate the columns in the output table format. By default 9.</li>
	 * </ul>
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetVersion(DATAFORMAT_TYPE outputFormat, int receiveTimeout) throws Exception
    {
        String versionArgs = OperationArguments.GetVersionArgs();
        byte opCode = OPERATION_CODE.VERSION.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, versionArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Allows getting the server version, in a synchronous way.
     * <p>
	 * This function returns the following information
	 * <ul>
	 * <li>LKMVCOMPONENTSVERSION: MV Components version.</li>
	 * <li>LKSERVERVERSION: Linkar SERVER version.</li>
	 * <li>LKCLIENTVERSION: Used client library version.</li>
	 * <li>DATABASE: Database.</li>
	 * <li>OS: Operating system.</li>
	 * <li>DATEZERO: Date zero base in YYYYMMDD format.</li>
	 * <li>DATEOUTPUTCONVERSION: Output conversion for date used by Linkar Schemas.</li>
	 * <li>TIMEOUTPUTCONVERSION: Output conversion for time used by Linkar Schemas.</li>
	 * <li>MVDATETIMESEPARATOR: DateTime used separator used by Linkar Schemas, for instance 18325,23000.</li>
	 * <li>MVBOOLTRUE: Database used char for the Boolean true value used by Linkar Schemas.</li>
	 * <li>MVBOOLFALSE: Database used char for the Boolean false value used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLTRUE: Used char for the Boolean true value out of the database used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLFALSE: Used char for the Boolean false value out of the database used by Linkar Schemas.</li>
	 * <li>MVDECIMALSEPARATOR: Decimal separator in the database. May be point, comma or none when the database does not store decimal numbers. Used by Linkar Schemas.</li>
	 * <li>OTHERLANGUAGES: Languages list separated by commas.</li>
	 * <li>TABLEROWSEPARATOR: It is the decimal char that you use to separate the rows in the output table format. By default 11.</li>
	 * <li>TABLECOLSEPARATOR: It is the decimal char that you use to separate the columns in the output table format. By default 9.</li>
	 * </ul>
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetVersion(DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return GetVersion(outputFormat, 0);
    }
    
    /**
     * Allows getting the server version, in a synchronous way.
     * <p>
	 * This function returns the following information
	 * <ul>
	 * <li>LKMVCOMPONENTSVERSION: MV Components version.</li>
	 * <li>LKSERVERVERSION: Linkar SERVER version.</li>
	 * <li>LKCLIENTVERSION: Used client library version.</li>
	 * <li>DATABASE: Database.</li>
	 * <li>OS: Operating system.</li>
	 * <li>DATEZERO: Date zero base in YYYYMMDD format.</li>
	 * <li>DATEOUTPUTCONVERSION: Output conversion for date used by Linkar Schemas.</li>
	 * <li>TIMEOUTPUTCONVERSION: Output conversion for time used by Linkar Schemas.</li>
	 * <li>MVDATETIMESEPARATOR: DateTime used separator used by Linkar Schemas, for instance 18325,23000.</li>
	 * <li>MVBOOLTRUE: Database used char for the Boolean true value used by Linkar Schemas.</li>
	 * <li>MVBOOLFALSE: Database used char for the Boolean false value used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLTRUE: Used char for the Boolean true value out of the database used by Linkar Schemas.</li>
	 * <li>OUTPUTBOOLFALSE: Used char for the Boolean false value out of the database used by Linkar Schemas.</li>
	 * <li>MVDECIMALSEPARATOR: Decimal separator in the database. May be point, comma or none when the database does not store decimal numbers. Used by Linkar Schemas.</li>
	 * <li>OTHERLANGUAGES: Languages list separated by commas.</li>
	 * <li>TABLEROWSEPARATOR: It is the decimal char that you use to separate the rows in the output table format. By default 11.</li>
	 * <li>TABLECOLSEPARATOR: It is the decimal char that you use to separate the columns in the output table format. By default 9.</li>
	 * </ul>
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetVersion() throws Exception
    {
        return GetVersion(DATAFORMAT_TYPE.MV);
    }
    
    /* LKSCHEMAS */

    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions, DATAFORMATSCH_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String lkSchemasArgs = OperationArguments.GetLkSchemasArgs(lkSchemasOptions, customVars);
        byte opCode = OPERATION_CODE.LKSCHEMAS.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, lkSchemasArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions, DATAFORMATSCH_TYPE outputFormat, String customVars) throws Exception
    {
        return LkSchemas(lkSchemasOptions, outputFormat, customVars, 0);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions, DATAFORMATSCH_TYPE outputFormat) throws Exception
    {
        return LkSchemas(lkSchemasOptions, outputFormat, "");
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions) throws Exception
    {
        return LkSchemas(lkSchemasOptions, DATAFORMATSCH_TYPE.MV);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas() throws Exception
    {
        return LkSchemas(null);
    }
    
    /* LKPROPERTIES */

    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, DATAFORMATSCH_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
    {
        String lkPropertiesArgs = OperationArguments.GetLkPropertiesArgs(filename, lkPropertiesOptions, customVars);
        byte opCode = OPERATION_CODE.LKPROPERTIES.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, lkPropertiesArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, DATAFORMATSCH_TYPE outputFormat, String customVars) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, outputFormat, customVars, 0);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, DATAFORMATSCH_TYPE outputFormat) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, outputFormat, "");
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.MV);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File name to LkProperties
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename) throws Exception
    {
        return LkProperties(filename, null);
    }
    
    /* GETTABLE */

    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars, int receiveTimeout) throws Exception
    {
        String getTableArgs = OperationArguments.GetGetTableArgs(filename, selectClause, dictClause, sortClause, tableOptions, customVars);
        byte opCode = OPERATION_CODE.GETTABLE.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, getTableArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, sortClause, tableOptions, customVars, 0);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, sortClause, tableOptions, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, sortClause, null);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause) throws Exception
    {
        return GetTable(filename, selectClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename) throws Exception
    {
        return GetTable(filename, "");
    }
    
    /* RESETCOMMONBLOCKS */

    /**
     * Resets the COMMON variables with the 100 most used files in a asynchronous way.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String ResetCommonBlocks(DATAFORMAT_TYPE outputFormat, int receiveTimeout) throws Exception
    {
        String resetCommonBlocksArgs = OperationArguments.GetResetCommonBlocksArgs();
        byte opCode = OPERATION_CODE.RESETCOMMONBLOCKS.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = outputFormat.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, resetCommonBlocksArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Resets the COMMON variables with the 100 most used files in a asynchronous way.
     * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String ResetCommonBlocks(DATAFORMAT_TYPE outputFormat) throws Exception
    {
        return ResetCommonBlocks(outputFormat, 0);
    }
    
    /**
     * Resets the COMMON variables with the 100 most used files in a asynchronous way.
     * @return The results of the operation.
     * @throws Exception
     */
    public String ResetCommonBlocks() throws Exception
    {
        return ResetCommonBlocks(DATAFORMAT_TYPE.MV);
    }
}
