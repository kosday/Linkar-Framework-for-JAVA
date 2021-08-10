package linkar.functions.direct;

import linkar.CredentialOptions;
import linkar.Linkar;
import linkar.functions.*;

/**
 These functions perform synchronous direct (without establishing permanent session) operations with any kind of output format type.
 */
public class DirectFunctions {

/* READ */
	
	/**
	 * Reads one or several records of a file in synchronous way.
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries, ReadOptions readOptions,
        		DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat, String customVars, int receiveTimeout) throws Exception
        {
            String readArgs = OperationArguments.GetReadArgs(filename, recordIds, dictionaries, readOptions, customVars);
            byte opCode = OPERATION_CODE.READ.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, readArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
    	/**
    	 * Reads one or several records of a file in synchronous way.
    	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
            public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries, ReadOptions readOptions,
            		DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat, String customVars) throws Exception
            {
                return Read(credentialOptions, filename, recordIds, dictionaries, readOptions, inputFormat, outputFormat, customVars, 0);
            }
            
			/**
			 * Reads one or several records of a file in synchronous way.
			 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
			 * @param filename File name to read.
			 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
			 * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
			 * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
			 * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
			 * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
			 * @return The results of the operation.
			 * @throws Exception
			 */
			public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries, ReadOptions readOptions,
					DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
			{
				return Read(credentialOptions, filename, recordIds, dictionaries, readOptions, inputFormat, outputFormat, "");
			}
			
			/**
			 * Reads one or several records of a file in synchronous way.
			 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
			 * @param filename File name to read.
			 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
			 * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
			 * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
			 * @param inputFormat Indicates in what format you wish to send the record ids: MV, XML or JSON.
			 * @return The results of the operation.
			 * @throws Exception
			 */
			public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries, ReadOptions readOptions,
					DATAFORMAT_TYPE inputFormat) throws Exception
			{
				return Read(credentialOptions, filename, recordIds, dictionaries, readOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
			}
			
			/**
			 * Reads one or several records of a file in synchronous way.
			 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
			 * @param filename File name to read.
			 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
			 * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
			 * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
			 * @return The results of the operation.
			 * @throws Exception
			 */
			public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries, ReadOptions readOptions) throws Exception
			{
				return Read(credentialOptions, filename, recordIds, dictionaries, readOptions, DATAFORMAT_TYPE.MV);
			}
			
			/**
			 * Reads one or several records of a file in synchronous way.
			 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
			 * @param filename File name to read.
			 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
			 * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. You may use the format LKFLDx where x is the attribute number.
			 * @return The results of the operation.
			 * @throws Exception
			 */
			public static String Read(CredentialOptions credentialOptions, String filename, String recordIds, String dictionaries) throws Exception
			{
				return Read(credentialOptions, filename, recordIds, dictionaries, null);
			}
			
			/**
			 * Reads one or several records of a file in synchronous way.
			 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
			 * @param filename File name to read.
			 * @param recordIds It's the records codes list to read, separated by the Record Separator character (30). Use StringFunctions.ComposeRecordIds to compose this String
			 * @return The results of the operation.
			 * @throws Exception
			 */
			public static String Read(CredentialOptions credentialOptions, String filename, String recordIds) throws Exception
			{
				return Read(credentialOptions, filename, recordIds, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Update(CredentialOptions credentialOptions, String filename, String records, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String updateArgs = OperationArguments.GetUpdateArgs(filename, records, updateOptions, customVars);
            byte opCode = OPERATION_CODE.UPDATE.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, updateArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Update(CredentialOptions credentialOptions, String filename, String records, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars) throws Exception
        {
            return Update(credentialOptions, filename, records, updateOptions, inputFormat, outputFormat, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Update(CredentialOptions credentialOptions, String filename, String records, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
        {
            return Update(credentialOptions, filename, records, updateOptions, inputFormat, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Update(CredentialOptions credentialOptions, String filename, String records, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat) throws Exception
        {
            return Update(credentialOptions, filename, records, updateOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Update(CredentialOptions credentialOptions, String filename, String records, UpdateOptions updateOptions) throws Exception
        {
            return Update(credentialOptions, filename, records, updateOptions, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Update(CredentialOptions credentialOptions, String filename, String records) throws Exception
        {
            return Update(credentialOptions, filename, records, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String updateArgs = OperationArguments.GetUpdatePartialArgs(filename, records, dictionaries, updateOptions, customVars);
            byte opCode = OPERATION_CODE.UPDATEPARTIAL.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, updateArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, dictionaries, updateOptions, inputFormat, outputFormat, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, dictionaries, updateOptions, inputFormat, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries, UpdateOptions updateOptions,
            DATAFORMAT_TYPE inputFormat) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, dictionaries, updateOptions, inputFormat, DATAFORMATCRU_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
         * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries, UpdateOptions updateOptions) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, dictionaries, updateOptions, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @param dictionaries List of dictionaries to write, separated by space. In MV output format is mandatory. You may use the format LKFLDx where x is the attribute number.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records, String dictionaries) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, dictionaries, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords. Use StringFunctions.ComposeUpdateBuffer function to compose this String.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String UpdatePartial(CredentialOptions credentialOptions, String filename, String records) throws Exception
        {
            return UpdatePartial(credentialOptions, filename, records, "");
        }

/* NEW */
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String New(CredentialOptions credentialOptions, String filename, String records, NewOptions newOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String newArgs = OperationArguments.GetNewArgs(filename, records, newOptions, customVars);
            byte opCode = OPERATION_CODE.NEW.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, newArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
         * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String New(CredentialOptions credentialOptions, String filename, String records, NewOptions newOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat,
            String customVars) throws Exception
        {
            return New(credentialOptions, filename, records, newOptions,inputFormat, outputFormat,customVars, 0);
        }
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
         * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the Read, New, Update and Select operations: MV, XML, XML_DICT, XML_SCH, JSON, JSON_DICT or JSON_SCH.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String New(CredentialOptions credentialOptions, String filename, String records, NewOptions newOptions,
            DATAFORMAT_TYPE inputFormat, DATAFORMATCRU_TYPE outputFormat) throws Exception
        {
            return New(credentialOptions, filename, records, newOptions,inputFormat, outputFormat,"");
        }
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
         * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String New(CredentialOptions credentialOptions, String filename, String records, NewOptions newOptions,
            DATAFORMAT_TYPE inputFormat) throws Exception
        {
            return New(credentialOptions, filename, records, newOptions,inputFormat, DATAFORMATCRU_TYPE.MV);
        }
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
         * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String New(CredentialOptions credentialOptions, String filename, String records, NewOptions newOptions) throws Exception
        {
            return New(credentialOptions, filename, records, newOptions, DATAFORMAT_TYPE.MV);
        }
        
        /**
         * Creates one or several records of a file, in a synchronous way.
         * <p>
         * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where you are going to write.
         * @param records Are the records you want to write. Inside this String are the recordIds, and the records. Use StringFunctions.ComposeNewBuffer function to compose this String.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String New(CredentialOptions credentialOptions, String filename, String records) throws Exception
        {
            return New(credentialOptions, filename, records, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Delete(CredentialOptions credentialOptions, String filename, String records, DeleteOptions deleteOptions,
        	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String deleteArgs = OperationArguments.GetDeleteArgs(filename, records, deleteOptions, customVars);
            byte opCode = OPERATION_CODE.DELETE.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, deleteArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
         * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
         * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Delete(CredentialOptions credentialOptions, String filename, String records, DeleteOptions deleteOptions,
        	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
            return Delete(credentialOptions, filename, records, deleteOptions, inputFormat, outputFormat, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
         * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
         * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Delete(CredentialOptions credentialOptions, String filename, String records, DeleteOptions deleteOptions,
        	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return Delete(credentialOptions, filename, records, deleteOptions, inputFormat, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
         * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
         * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
         * @param inputFormat Indicates in what format you wish to send the resultant writing data: MV, XML or JSON.      
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Delete(CredentialOptions credentialOptions, String filename, String records, DeleteOptions deleteOptions,
        	DATAFORMAT_TYPE inputFormat) throws Exception
        {
            return Delete(credentialOptions, filename, records, deleteOptions, inputFormat, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
         * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.
         * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Delete(CredentialOptions credentialOptions, String filename, String records, DeleteOptions deleteOptions) throws Exception
        {
            return Delete(credentialOptions, filename, records, deleteOptions, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
         * @param records It's the records list to be deleted. Use StringFunctions.ComposeDeleteBuffer function to compose this String.         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Delete(CredentialOptions credentialOptions, String filename, String records) throws Exception
        {
            return Delete(credentialOptions, filename, records, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
            DATAFORMATCRU_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String selectArgs = OperationArguments.GetSelectArgs(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, customVars);
            byte opCode = OPERATION_CODE.SELECT.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, selectArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
            DATAFORMATCRU_TYPE outputFormat,
            String customVars) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, outputFormat, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
            DATAFORMATCRU_TYPE outputFormat) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
         * @param preSelectClause It's an optional statement that will execute before the main Select
         * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, DATAFORMATCRU_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
         * @param preSelectClause It's an optional statement that will execute before the main Select
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause, String preSelectClause) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, dictClause, preSelectClause, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. You may use the format LKFLDx where x is the attribute number.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause, String dictClause) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, dictClause, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause, String sortClause) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, sortClause, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename, String selectClause) throws Exception
        {
        	return Select(credentialOptions, filename, selectClause, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name where the select operation will be perform. For example LK.ORDERS
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Select(CredentialOptions credentialOptions, String filename) throws Exception
        {
        	return Select(credentialOptions, filename, "");
        }
        
/* SUBROUTINE */
        
        /**
         * Executes a subroutine, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String Subroutine(CredentialOptions credentialOptions, String subroutineName, int argsNumber, String arguments,
        	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String subroutineArgs = OperationArguments.GetSubroutineArgs(subroutineName, argsNumber, arguments, customVars);
            byte opCode = OPERATION_CODE.SUBROUTINE.getnumVal();
            byte byteInputFormat = inputFormat.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, subroutineArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Executes a subroutine, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param subroutineName Subroutine name you want to execute.
         * @param argsNumber Number of arguments
         * @param arguments The subroutine arguments list.
         * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Subroutine(CredentialOptions credentialOptions, String subroutineName, int argsNumber, String arguments,
        	DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
        	return Subroutine(credentialOptions, subroutineName, argsNumber, arguments, inputFormat, outputFormat, customVars, 0);
        }
        
        /**
         * Executes a subroutine, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param subroutineName Subroutine name you want to execute.
         * @param argsNumber Number of arguments
         * @param arguments The subroutine arguments list.
         * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Subroutine(CredentialOptions credentialOptions, String subroutineName, int argsNumber, String arguments,
        		DATAFORMAT_TYPE inputFormat, DATAFORMAT_TYPE outputFormat) throws Exception
        {
        	return Subroutine(credentialOptions, subroutineName, argsNumber, arguments, inputFormat, outputFormat, "");
        }
        
        /**
         * Executes a subroutine, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param subroutineName Subroutine name you want to execute.
         * @param argsNumber Number of arguments
         * @param arguments The subroutine arguments list.
         * @param inputFormat Indicates in what format you wish to send the subroutine arguments: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Subroutine(CredentialOptions credentialOptions, String subroutineName, int argsNumber, String arguments,
        		DATAFORMAT_TYPE inputFormat) throws Exception
        {
        	return Subroutine(credentialOptions, subroutineName, argsNumber, arguments, inputFormat, DATAFORMAT_TYPE.MV);
        }
        
        /**
         * Executes a subroutine, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param subroutineName Subroutine name you want to execute.
         * @param argsNumber Number of arguments
         * @param arguments The subroutine arguments list.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Subroutine(CredentialOptions credentialOptions, String subroutineName, int argsNumber, String arguments) throws Exception
        {
        	return Subroutine(credentialOptions, subroutineName, argsNumber, arguments, DATAFORMAT_TYPE.MV);
        }
        
/* CONVERSION */
        
        /**
         * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
         * @param code The conversion code. It will have to obey the Database conversions specifications.
         * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Conversion(CredentialOptions credentialOptions, String expression, String code, CONVERSION_TYPE conversionType,
            DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String conversionArgs = OperationArguments.GetConversionArgs(expression, code, conversionType, customVars);
            byte opCode = OPERATION_CODE.CONVERSION.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, conversionArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
         * @param code The conversion code. It will have to obey the Database conversions specifications.
         * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Conversion(CredentialOptions credentialOptions, String expression, String code, CONVERSION_TYPE conversionType,
            DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
        	return Conversion(credentialOptions, expression, code, conversionType, outputFormat, customVars, 0);
        }
        
        /**
         * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
         * @param code The conversion code. It will have to obey the Database conversions specifications.
         * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Conversion(CredentialOptions credentialOptions, String expression, String code, CONVERSION_TYPE conversionType,
            DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return Conversion(credentialOptions, expression, code, conversionType, outputFormat,"");
        }
        
        /**
         * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
         * @param code The conversion code. It will have to obey the Database conversions specifications.
         * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Conversion(CredentialOptions credentialOptions, String expression, String code, CONVERSION_TYPE conversionType) throws Exception
        {
            return Conversion(credentialOptions, expression, code, conversionType, DATAFORMAT_TYPE.MV);
        }               
        
/* FORMAT */
        
        /**
         * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
         * @param formatSpec Specified format
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Format(CredentialOptions credentialOptions, String expression, String formatSpec,
            DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String formatArgs = OperationArguments.GetFormatArgs(expression, formatSpec, customVars);
            byte opCode = OPERATION_CODE.FORMAT.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, formatArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
         * @param formatSpec Specified format
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Format(CredentialOptions credentialOptions, String expression, String formatSpec,
            DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
            return Format(credentialOptions, expression, formatSpec,outputFormat, customVars, 0);
        }
        
        /**
         * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
         * @param formatSpec Specified format
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.                 
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Format(CredentialOptions credentialOptions, String expression, String formatSpec,
            DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return Format(credentialOptions, expression, formatSpec,outputFormat, "");
        }
        
        /**
         * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
         * @param formatSpec Specified format         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Format(CredentialOptions credentialOptions, String expression, String formatSpec) throws Exception
        {
            return Format(credentialOptions, expression, formatSpec, DATAFORMAT_TYPE.MV);
        }
        
/* DICTIONARIES */
        
        /**
         * Returns all the dictionaries of a file, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Dictionaries(CredentialOptions credentialOptions, String filename,
            DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String dictionariesArgs = OperationArguments.GetDictionariesArgs(filename, customVars);
            byte opCode = OPERATION_CODE.DICTIONARIES.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, dictionariesArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Returns all the dictionaries of a file, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Dictionaries(CredentialOptions credentialOptions, String filename,
            DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
            return Dictionaries(credentialOptions, filename,outputFormat,customVars,0);
        }
        
        /**
         * Returns all the dictionaries of a file, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.         
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Dictionaries(CredentialOptions credentialOptions, String filename,
            DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return Dictionaries(credentialOptions, filename,outputFormat,"");
        }
        
        /**
         * Returns all the dictionaries of a file, in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Dictionaries(CredentialOptions credentialOptions, String filename) throws Exception
        {
            return Dictionaries(credentialOptions, filename,DATAFORMAT_TYPE.MV);
        }
        
/* EXECUTE */
        
        /**
         * Allows the execution of any command from the Database in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param statement The command you want to execute in the Database.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Execute(CredentialOptions credentialOptions, String statement,
            DATAFORMAT_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String executeArgs = OperationArguments.GetExecuteArgs(statement, customVars);
            byte opCode = OPERATION_CODE.EXECUTE.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, executeArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Allows the execution of any command from the Database in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param statement The command you want to execute in the Database.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Execute(CredentialOptions credentialOptions, String statement,
            DATAFORMAT_TYPE outputFormat,
            String customVars) throws Exception
        {
            return Execute(credentialOptions, statement, outputFormat, customVars, 0);
        }
        
        /**
         * Allows the execution of any command from the Database in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param statement The command you want to execute in the Database.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Execute(CredentialOptions credentialOptions, String statement,
            DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return Execute(credentialOptions, statement, outputFormat, "");
        }
        
        /**
         * Allows the execution of any command from the Database in a synchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param statement The command you want to execute in the Database.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String Execute(CredentialOptions credentialOptions, String statement) throws Exception
        {
            return Execute(credentialOptions, statement, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetVersion(CredentialOptions credentialOptions, DATAFORMAT_TYPE outputFormat, int receiveTimeout) throws Exception
        {
            String versionArgs = OperationArguments.GetVersionArgs();
            byte opCode = OPERATION_CODE.VERSION.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, versionArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetVersion(CredentialOptions credentialOptions, DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return GetVersion(credentialOptions, outputFormat, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetVersion(CredentialOptions credentialOptions) throws Exception
        {
            return GetVersion(credentialOptions, DATAFORMAT_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
             DATAFORMATSCH_TYPE outputFormat,
             String customVars, int receiveTimeout) throws Exception
        {
            String lkSchemasArgs = OperationArguments.GetLkSchemasArgs(lkSchemasOptions, customVars);
            byte opCode = OPERATION_CODE.LKSCHEMAS.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, lkSchemasArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
             DATAFORMATSCH_TYPE outputFormat,
             String customVars) throws Exception
        {
            return LkSchemas(credentialOptions, lkSchemasOptions, outputFormat,customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
             DATAFORMATSCH_TYPE outputFormat) throws Exception
        {
            return LkSchemas(credentialOptions, lkSchemasOptions, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions) throws Exception
        {
            return LkSchemas(credentialOptions, lkSchemasOptions, DATAFORMATSCH_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkSchemas(CredentialOptions credentialOptions) throws Exception
        {
            return LkSchemas(credentialOptions, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name to LkProperties
         * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
            DATAFORMATSCH_TYPE outputFormat,
            String customVars, int receiveTimeout) throws Exception
        {
            String lkPropertiesArgs = OperationArguments.GetLkPropertiesArgs(filename, lkPropertiesOptions, customVars);
            byte opCode = OPERATION_CODE.LKPROPERTIES.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, lkPropertiesArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name to LkProperties
         * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
            DATAFORMATSCH_TYPE outputFormat,
            String customVars) throws Exception
        {
            return LkProperties(credentialOptions, filename, lkPropertiesOptions, outputFormat, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name to LkProperties
         * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML, JSON or TABLE.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
            DATAFORMATSCH_TYPE outputFormat) throws Exception
        {
            return LkProperties(credentialOptions, filename, lkPropertiesOptions, outputFormat, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name to LkProperties
         * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
        {
            return LkProperties(credentialOptions, filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.MV);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File name to LkProperties
         * @return The results of the operation.
         * @throws Exception
         */
        public static String LkProperties(CredentialOptions credentialOptions, String filename) throws Exception
        {
            return LkProperties(credentialOptions, filename, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
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
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
            TableOptions tableOptions, String customVars, int receiveTimeout) throws Exception
        {
            String getTableArgs = OperationArguments.GetGetTableArgs(filename, selectClause, dictClause, sortClause, tableOptions, customVars);
            byte opCode = OPERATION_CODE.GETTABLE.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, getTableArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
         * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
            TableOptions tableOptions, String customVars) throws Exception
        {
            return GetTable(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, customVars, 0);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
            TableOptions tableOptions) throws Exception
        {
            return GetTable(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
         * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause) throws Exception
        {
            return GetTable(credentialOptions, filename, selectClause, dictClause, sortClause, null);
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause) throws Exception
        {
            return GetTable(credentialOptions, filename, selectClause, dictClause,"");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause) throws Exception
        {
            return GetTable(credentialOptions, filename, selectClause, "");
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
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
         * @return The results of the operation.
         * @throws Exception
         */
        public static String GetTable(CredentialOptions credentialOptions, String filename) throws Exception
        {
            return GetTable(credentialOptions, filename, "");
        }        
        
/* RESETCOMMONBLOCKS */
        
        /**
         * Resets the COMMON variables with the 100 most used files in a asynchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
         * @return The results of the operation.
         * @throws Exception
         */
        public static String ResetCommonBlocks(CredentialOptions credentialOptions, DATAFORMAT_TYPE outputFormat, int receiveTimeout) throws Exception
        {
            String resetCommonBlocksArgs = OperationArguments.GetResetCommonBlocksArgs();
            byte opCode = OPERATION_CODE.RESETCOMMONBLOCKS.getnumVal();
            byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
            byte byteOutputFormat = outputFormat.getnumVal();
            String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, resetCommonBlocksArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
            return result;
        }
        
        /**
         * Resets the COMMON variables with the 100 most used files in a asynchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @param outputFormat Indicates in what format you want to receive the data resulting from the operation: MV, XML or JSON.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String ResetCommonBlocks(CredentialOptions credentialOptions, DATAFORMAT_TYPE outputFormat) throws Exception
        {
            return ResetCommonBlocks(credentialOptions, outputFormat, 0);
        }
        
        /**
         * Resets the COMMON variables with the 100 most used files in a asynchronous way.
         * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
         * @return The results of the operation.
         * @throws Exception
         */
        public static String ResetCommonBlocks(CredentialOptions credentialOptions) throws Exception
        {
            return ResetCommonBlocks(credentialOptions, DATAFORMAT_TYPE.MV);
        }
    
}
