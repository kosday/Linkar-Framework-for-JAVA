package linkar.functions.persistent.xml;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import linkar.CredentialOptions;
import linkar.functions.*;
import linkar.functions.persistent.xml.XML_FORMAT;

/**
 * These functions perform synchronous and asynchronous persistent (establishing permanent session) operations with output format type XML.
 */
public class LinkarClient
{
	
    private linkar.functions.persistent.LinkarClient LinkarClt;
    
	/**
	 * Get SessionID of the connection.
	 * @return SessionID of the connection.
	 */
    public String getSessionId()
    {
        return this.LinkarClt.getSessionId();
    }
    
    /**
     * Initializes a new instance of the LinkarClient class.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely). When the receiveTimeout argument is omitted in any operation, the value set here will be applied.
     */
    public LinkarClient(int receiveTimeout)
    {
        this.LinkarClt = new linkar.functions.persistent.LinkarClient(receiveTimeout);
    }
    
    /**
     * Initializes a new instance of the LinkarClient class.     
     */
    public LinkarClient()
    {
        this.LinkarClt = new linkar.functions.persistent.LinkarClient(0);
    }
    
	/* = SYNC = */   

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
        this.LinkarClt.Login(credentialOptions, customVars, receiveTimeout);
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
        this.LinkarClt.Logout(customVars, receiveTimeout);
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
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML, "", 60);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Read(filename, records, dictionaries, readOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
    }
    
    /**
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML, "");
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
        return Read(filename, records, dictionaries, readOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
        return Read(filename, records, dictionaries, readOptions, xmlFormat, "");
    }
    
    /**
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records, String dictionaries, ReadOptions readOptions) throws Exception
    {
        return Read(filename, records, dictionaries, readOptions, XML_FORMAT.XML);
    }
    
    /**
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"");
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records, String dictionaries) throws Exception
    {
        return Read(filename, records, dictionaries, null);
    }
    
    /**
     * Reads one or several records of a file in a synchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyRead()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			result = client.Read("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;");
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Read(String filename, String records) throws Exception
    {
        return Read(filename, records, "");
    }
    
    /* UPDATE */

    /**
     * Update one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdate()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.Update("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Update(filename, records, updateOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdate()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.Update("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
        return Update(filename, records, updateOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdate()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.Update("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
        return Update(filename, records, updateOptions, xmlFormat, "");
    }
    
    /**
     * Update one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdate()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.Update("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records, UpdateOptions updateOptions) throws Exception
    {
        return Update(filename, records, updateOptions, XML_FORMAT.XML);
    }
    
    /**
     * Update one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #Read} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdate()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Update("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Update(String filename, String records) throws Exception
    {
        return Update(filename, records, null);
    }
    
    /* NEW */

    /**
     * Creates one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNew()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.New("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "", 60);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.New(filename, records, newOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNew()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.New("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "");	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
        return New(filename, records, newOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNew()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.New("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
        return New(filename, records, newOptions, xmlFormat, "");
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNew()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.New("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records, NewOptions newOptions) throws Exception
    {
        return New(filename, records, newOptions, XML_FORMAT.XML);
    }
    
    /**
     * Creates one or several records of a file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNew()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			result = client.New("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;");	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @return The results of the operation.
     * @throws Exception
     */
    public String New(String filename, String records) throws Exception
    {
        return New(filename, records, null);
    }
    
    /* DELETE */

    /**
     * Deletes one or several records in file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDelete()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.Delete(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options, "", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
        String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Delete(filename, records, deleteOptions, DATAFORMAT_TYPE.XML, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDelete()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.Delete(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options, "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions,
        String customVars) throws Exception
    {
        return Delete(filename, records, deleteOptions, customVars, 0);
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDelete()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.Delete(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records, DeleteOptions deleteOptions) throws Exception
    {
        return Delete(filename, records, deleteOptions, "");
    }
    
    /**
     * Deletes one or several records in file, in a synchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #Read} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDelete()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Delete(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Delete(String filename, String records) throws Exception
    {
        return Delete(filename, records, null);
    }
    
    /* SELECT */

    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.Select("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML, "", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
    }
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.Select("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML, "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, xmlFormat, customVars, 0);
    }   
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.Select("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, xmlFormat, "");
    }   
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.Select("LK.CUSTOMERS", "","","","", options);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, XML_FORMAT.XML);
    }   
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Select("LK.CUSTOMERS", "","","","");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, preSelectClause, null);
    }   
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Select("LK.CUSTOMERS", "","","");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @return The results of the operation.
     * @throws Exception
     */
    public String Select(String filename, String selectClause, String sortClause, String dictClause) throws Exception
    {
        return Select(filename, selectClause, sortClause, dictClause, "");
    }   
    
    /**
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Select("LK.CUSTOMERS", "","");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
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
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Select("LK.CUSTOMERS", "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
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
     * Executes a Query in the Database, in a synchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelect()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Select("LK.CUSTOMERS");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
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
     * Executes a subroutine, in a synchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutine()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Subroutine("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Subroutine(subroutineName, argsNumber, arguments, DATAFORMAT_TYPE.XML, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Executes a subroutine, in a synchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutine()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Subroutine("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments, String customVars) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, customVars, 0);
    }
    
    /**
     * Executes a subroutine, in a synchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutine()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Subroutine("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Subroutine(String subroutineName, int argsNumber, String arguments) throws Exception
    {
        return Subroutine(subroutineName, argsNumber, arguments, "");
    }
    
    /* CONVERSION */

    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversion()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.Conversion(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-", "", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Conversion(conversionType, expression, code, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversion()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.Conversion(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-", "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code, String customVars) throws Exception
    {
        return Conversion(conversionType, expression, code, customVars, 0);
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversion()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.Conversion(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Conversion(CONVERSION_TYPE conversionType, String expression, String code) throws Exception
    {
        return Conversion(conversionType, expression, code, "");
    }
    
    /* FORMAT */

    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormat()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.Format("1þ2","R#10", "", 60);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Format(expression, formatSpec, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormat()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.Format("1þ2","R#10", "");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec, String customVars) throws Exception
    {
        return Format(expression, formatSpec, customVars, 0);
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormat()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.Format("1þ2","R#10");
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @return The results of the operation.
     * @throws Exception
     */
    public String Format(String expression, String formatSpec) throws Exception
    {
        return Format(expression, formatSpec, "");
    }
    
    /* DICTIONARIES */

    /**
     * Returns all the dictionaries of a file, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionaries()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Dictionaries("LK.CUSTOMERS", "", 60);		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Dictionaries(filename, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Returns all the dictionaries of a file, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionaries()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Dictionaries("LK.CUSTOMERS", "");		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename, String customVars) throws Exception
    {
        return Dictionaries(filename, customVars, 0);
    }
    
    /**
     * Returns all the dictionaries of a file, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionaries()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.Dictionaries("LK.CUSTOMERS");		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @return The results of the operation.
     * @throws Exception
     */
    public String Dictionaries(String filename) throws Exception
    {
        return Dictionaries(filename, "");
    }
    
    /* EXECUTE */

    /**
     * Allows the execution of any command from the Database in a synchronous way with XML output format.
    /**
     * Returns all the dictionaries of a file, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecute()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.Execute("WHO", "", 60);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.Execute(statement, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Allows the execution of any command from the Database in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecute()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.Execute("WHO", "");	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement, String customVars) throws Exception
    {
        return Execute(statement, customVars, 0);
    }
    
    /**
     * Allows the execution of any command from the Database in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecute()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.Execute("WHO");	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @return The results of the operation.
     * @throws Exception
     */
    public String Execute(String statement) throws Exception
    {
        return Execute(statement, "");
    }
    
    /* GETVERSION */

    /**
     * Allows getting the client version.
     * @return Allows getting the client version.
     */
    public static String GetLocalVersion()
    {
        return linkar.functions.persistent.LinkarClient.GetLocalVersion();
    }

    /**
     * Allows getting the server version, in a synchronous way with XML output format.
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetVersion()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");			
	 *			result = client.GetVersion(60);	
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetVersion(int receiveTimeout) throws Exception
    {
        return this.LinkarClt.GetVersion(DATAFORMAT_TYPE.XML, receiveTimeout);
    }
    
    /**
     * Allows getting the server version, in a synchronous way with XML output format.
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetVersion()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");			
	 *			result = client.GetVersion();	
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetVersion() throws Exception
    {
        return GetVersion(0);
    }
    
    /* LKSCHEMAS */

    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemas(options, "", 60);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.LkSchemas(lkSchemasOptions, DATAFORMATSCH_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemas(options, "");	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions, String customVars) throws Exception
    {
        return LkSchemas(lkSchemasOptions, customVars, 0);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemas(options);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas(LkSchemasOptions lkSchemasOptions) throws Exception
    {
        return LkSchemas(lkSchemasOptions, "");
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.LkSchemas();	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkSchemas() throws Exception
    {
        return LkSchemas(null);
    }
    
    /* LKPROPERTIES */

    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkProperties("LK.CUSTOMERS",options, "", 60);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.LkProperties(filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.XML, customVars, receiveTimeout);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkProperties("LK.CUSTOMERS",options, "");	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, customVars, 0);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkProperties("LK.CUSTOMERS",options);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, "");
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.LkProperties("LK.CUSTOMERS");	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename) throws Exception
    {
        return LkProperties(filename, null);
    }    
    
    /* RESETCOMMONBLOCKS */

    /**
     * Resets the COMMON variables with the 100 most used files in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyResetCommonBlocks()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.ResetCommonBlocks(60);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String ResetCommonBlocks(int receiveTimeout) throws Exception
    {
        return this.LinkarClt.ResetCommonBlocks(DATAFORMAT_TYPE.XML, receiveTimeout);
    }
    
    /**
     * Resets the COMMON variables with the 100 most used files in a synchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyResetCommonBlocks()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.ResetCommonBlocks();	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public String ResetCommonBlocks() throws Exception
    {
        return ResetCommonBlocks(0);
    }
    
    
    /* = ASYNC = */
    
    /* LOGIN */

    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a asynchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.runAsync(() -> {
				try {
					this.LinkarClt.Login(credentialOptions, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a asynchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions, String customVars) throws Exception
    {
    	return LoginAsync(credentialOptions, customVars, 0);
    }
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a asynchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions) throws Exception
    {
    	return LoginAsync(credentialOptions, "");
    }
    
    /* LOGOUT */

    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a asynchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LogoutAsync(String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.runAsync(() -> {
				try {
					this.LinkarClt.Logout(customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a asynchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LogoutAsync(String customVars) throws Exception
    {
    	return LogoutAsync(customVars, 0);
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a asynchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<Void> LogoutAsync() throws Exception
    {
    	return LogoutAsync("");
    }
    
    /* READ */

    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML, "", 60).getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Read(filename, records, dictionaries, readOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML, "").getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
    	return ReadAsync(filename, records, dictionaries, readOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options, XML_FORMAT.XML).getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records, String dictionaries, ReadOptions readOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
    	return ReadAsync(filename, records, dictionaries, readOptions, xmlFormat, "");
    }
    
    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			ReadOptions options = new ReadOptions(true);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"",options).getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @param readOptions Object that defines the different reading options of the Function: Calculated, dictClause, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records, String dictionaries, ReadOptions readOptions) throws Exception
    {
    	return ReadAsync(filename, records, dictionaries, readOptions, XML_FORMAT.XML);
    }
    
    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"").getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @param dictionaries List of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records, String dictionaries) throws Exception
    {
    	return ReadAsync(filename, records, dictionaries, null);
    }
    
    /**
     * Reads one or several records of a file in a asynchronous way with XML input and output format.
	 * <p>
	 * Example:
	 * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyReadAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			result = client.ReadAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;").getNow(result);
	 * 			client.Logout();			
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name to read.
     * @param records It's the records codes list to read.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ReadAsync(String filename, String records) throws Exception
    {
    	return ReadAsync(filename, records, "");
    }
    
    /* UPDATE */

    /**
     * Update one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdateAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.UpdateAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> UpdateAsync(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Update(filename, records, updateOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Update one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdateAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.UpdateAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> UpdateAsync(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
    	return UpdateAsync(filename, records, updateOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Update one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdateAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.UpdateAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> UpdateAsync(String filename, String records, UpdateOptions updateOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
    	return UpdateAsync(filename, records, updateOptions, xmlFormat, "");
    }
    
    /**
     * Update one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdateAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			UpdateOptions options = new UpdateOptions();
	 *			result = client.UpdateAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @param updateOptions Object that defines the different writing options of the Function: optimisticLockControl, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> UpdateAsync(String filename, String records, UpdateOptions updateOptions) throws Exception
    {
    	return UpdateAsync(filename, records, updateOptions, XML_FORMAT.XML);
    }
    
    /**
     * Update one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds and the modified records always must be specified. But the originalRecords not always.
	 * When {@link UpdateOptions} argument is specified and the {@link UpdateOptions#getOptimisticLockControl} property is set to true, a copy of the record must be provided before the modification (originalRecords argument)
	 * to use the Optimistic Lock technique. This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the modification, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the modified record is executed.
	 * But if they are not equal, it means that the record has been modified by other user and its modification will not be saved.
	 * The record will have to be read, modified and saved again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyUpdateAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 1300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.UpdateAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to update. Inside this String are the recordIds, the records, and the originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> UpdateAsync(String filename, String records) throws Exception
    {
    	return UpdateAsync(filename, records, null);
    }
    
    /* NEW */

    /**
     * Creates one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNewAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.NewAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "", 60).getNow(result);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> NewAsync(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.New(filename, records, newOptions, DATAFORMAT_TYPE.XML, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Creates one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNewAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.NewAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML, "").getNow(result);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> NewAsync(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
    	return NewAsync(filename, records, newOptions, xmlFormat, customVars, 0);
    }
    
    /**
     * Creates one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNewAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.NewAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options, XML_FORMAT.XML).getNow(result);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> NewAsync(String filename, String records, NewOptions newOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
    	return NewAsync(filename, records, newOptions, xmlFormat, "");
    }
    
    /**
     * Creates one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNewAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			NewOptions options = new NewOptions();
	 * 			result = client.NewAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 *			options).getNow(result);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @param newOptions Object that defines the following writing options of the Function: recordIdType, readAfter, calculated, dictionaries, conversion, formatSpec, originalRecords.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> NewAsync(String filename, String records, NewOptions newOptions) throws Exception
    {
    	return NewAsync(filename, records, newOptions, XML_FORMAT.XML);
    }
    
    /**
     * Creates one or several records of a file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the records always must be specified. But the recordIds only must be specified when {@link NewOptions} argument is null, or when the {@link RecordIdType} argument of the {@link NewOptions} constructor is null.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 * 	
	 * 	public String MyNewAsync()
	 * 	{
	 * 		String result = "";
	 * 		try{
	 * 			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 * 			result = client.NewAsync("LK.CUSTOMERS",
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"      &lt;NAME&gt;CUSTOMER 2&lt;/NAME&gt;" +
	 * 			"      &lt;ADDR&gt;ADDRESS 2&lt;/ADDR&gt;" +
	 * 			"      &lt;PHONE&gt;444&lt;/PHONE&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;").getNow(result);	
	 * 			client.Logout();		
	 * 		}
	 * 		catch (Exception ex)
	 * 		{
	 * 			String error = ex.getMessage();
	 * 			// Do something
	 * 		}
	 * 		return result;
	 * 	}
	 * }
	 * </pre>
     * @param filename File name where you are going to write.
     * @param records Are the records you want to write. Inside this String are the recordIds, and the records.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> NewAsync(String filename, String records) throws Exception
    {
    	return NewAsync(filename, records, null);
    }
    
    /* DELETE */

    /**
     * Deletes one or several records in file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDeleteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.DeleteAsync(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options, "", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DeleteAsync(String filename, String records, DeleteOptions deleteOptions,
        String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Delete(filename, records, deleteOptions, DATAFORMAT_TYPE.XML, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Deletes one or several records in file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDeleteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.DeleteAsync(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options, "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DeleteAsync(String filename, String records, DeleteOptions deleteOptions, String customVars) throws Exception
    {
    	return DeleteAsync(filename, records, deleteOptions, customVars, 0);
    }
    
    /**
     * Deletes one or several records in file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDeleteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			DeleteOptions options = new DeleteOptions();
	 *			result = client.DeleteAsync(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			options).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @param deleteOptions Object that defines the different Function options: optimisticLockControl, recoverRecordIdType.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DeleteAsync(String filename, String records, DeleteOptions deleteOptions) throws Exception
    {
    	return DeleteAsync(filename, records, deleteOptions, "");
    }
    
    /**
     * Deletes one or several records in file, in a asynchronous way with XML input and output format.
     * <p>
	 * Inside the records argument, the recordIds always must be specified. But the originalRecords not always.
	 * When {@link DeleteOptions} argument is specified and the {@link DeleteOptions#getOptimisticLockControl} property is set to true,
	 * a copy of the record must be provided before the deletion (originalRecords argument) to use the Optimistic Lock technique.
	 * This copy can be obtained from a previous {@link #ReadAsync} operation. The database, before executing the deletion, 
	 * reads the record and compares it with the copy in originalRecords, if they are equal the record is deleted.
	 * But if they are not equal, it means that the record has been modified by other user and the record will not be deleted.
	 * The record will have to be read, and deleted again.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDeleteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.DeleteAsync(credentials,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" + 
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;RECORDS&gt;" +
	 * 			"    &lt;RECORD&gt;" +
	 * 			"      &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" +
	 * 			"    &lt;/RECORD&gt;" +
	 * 			"  &lt;/RECORDS&gt;" +
	 * 			"&lt;/LINKAR&gt;").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename It's the file name where the records are going to be deleted. DICT in case of deleting a record that belongs to a dictionary.
     * @param records It's the records list to be deleted.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DeleteAsync(String filename, String records) throws Exception
    {
    	return DeleteAsync(filename, records, null);
    }  
    
    /* SELECT */

    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML, "", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Select(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, xmlFormat.getCRUFormat(), customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML, "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat, String customVars) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions,
    	        xmlFormat, customVars, 0);
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","","", options, XML_FORMAT.XML).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @param xmlFormat Different XML output formats.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions,
        XML_FORMAT xmlFormat) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions,
    	        xmlFormat, "");
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			SelectOptions options = new SelectOptions();
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","","", options).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @param selectOptions Object that defines the different reading options of the Function: calculated, dictionaries, conversion, formatSpec, originalRecords, onlyItemId, pagination, regPage, numPage.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause, SelectOptions selectOptions) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, dictClause, preSelectClause, selectOptions, XML_FORMAT.XML);
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","","").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @param preSelectClause It's an optional statement that will execute before the main Select
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause, String preSelectClause) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, dictClause, preSelectClause, null);
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","","").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause, String dictClause) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, dictClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SelectAsync("LK.CUSTOMERS", "","").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule, Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause, String sortClause) throws Exception
    {
    	return SelectAsync(filename, selectClause, sortClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SelectAsync("LK.CUSTOMERS", "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename, String selectClause) throws Exception
    {
    	return SelectAsync(filename, selectClause, "");
    }
    
    /**
     * Executes a Query in the Database, in a asynchronous way with XML output format.
     * <p>
	 * In the preSelectClause argument these operations can be carried out before executing the Select statement:
	 * <ul>
	 *   <li>Previously call to a saved list with the GET.LIST command to use it in the Main Select input</li>
	 *   <li>Make a previous Select to use the result as the Main Select input, with the SELECT or SSELECT commands.In this case the entire sentence must be indicated in the PreselectClause. For example:SSELECT LK.ORDERS WITH CUSTOMER = '1'</li>
	 *   <li>Exploit a Main File index to use the result as a Main Select input, with the SELECTINDEX command. The syntax for all the databases is SELECTINDEX index.name.value. For example SELECTINDEX ITEM,"101691"</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySelectAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SelectAsync("LK.CUSTOMERS").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name where the select operation will be perform. For example LK.ORDERS
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SelectAsync(String filename) throws Exception
    {
    	return SelectAsync(filename, "");
    }
    
    /* SUBROUTINE */

    /**
     * Executes a subroutine, in a asynchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutineAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SubroutineAsync("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SubroutineAsync(String subroutineName, int argsNumber, String arguments, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Subroutine(subroutineName, argsNumber, arguments, DATAFORMAT_TYPE.XML, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Executes a subroutine, in a asynchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutineAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SubroutineAsync("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;",
	 * 			"").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SubroutineAsync(String subroutineName, int argsNumber, String arguments, String customVars) throws Exception
    {
    	return SubroutineAsync(subroutineName, argsNumber, arguments, customVars, 0);
    }
    
    /**
     * Executes a subroutine, in a asynchronous way with XML input and output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySubroutineAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.SubroutineAsync("SUB.DEMOLINKAR", 3,
	 * 			"&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;" +
	 * 			"&lt;LINKAR&gt;" +
	 * 			"  &lt;ARGUMENTS&gt;" +
	 * 			"    &lt;ARGUMENT&gt;0&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;aaaa&lt;/ARGUMENT&gt;" +
	 * 			"    &lt;ARGUMENT&gt;&lt;/ARGUMENT&gt;" +
	 * 			"  &lt;/ARGUMENTS&gt;" +
	 * 			"&lt;/LINKAR&gt;").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param subroutineName Subroutine name you want to execute.
     * @param argsNumber Number of arguments
     * @param arguments The subroutine arguments list.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SubroutineAsync(String subroutineName, int argsNumber, String arguments) throws Exception
    {
    	return SubroutineAsync(subroutineName, argsNumber, arguments, "");
    }
    
    /* CONVERSION */

    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversionAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.ConversionAsync(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-", "", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ConversionAsync(CONVERSION_TYPE conversionType, String expression, String code, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Conversion(conversionType, expression, code, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversionAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.ConversionAsync(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-", "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ConversionAsync(CONVERSION_TYPE conversionType, String expression, String code, String customVars) throws Exception
    {
    	return ConversionAsync(conversionType, expression, code, customVars, 0);
    }
    
    /**
     * Returns the result of executing ICONV() or OCONV() functions from a expression list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyConversionAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);		
	 *			result = client.ConversionAsync(CONVERSION_TYPE.INPUT,"31-12-2017þ01-01-2018","D2-").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param conversionType Indicates the conversion type, input or output: Input=ICONV(); OUTPUT=OCONV()
     * @param expression The data or expression to convert. It can have MV marks, in which case the conversion will execute in each value obeying the original MV mark.
     * @param code The conversion code. It will have to obey the Database conversions specifications.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ConversionAsync(CONVERSION_TYPE conversionType, String expression, String code) throws Exception
    {
    	return ConversionAsync(conversionType, expression, code, "");
    }
    
    /* FORMAT */

    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormatAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.FormatAsync("1þ2","R#10", "", 60).getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> FormatAsync(String expression, String formatSpec, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Format(expression, formatSpec, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormatAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.FormatAsync("1þ2","R#10", "").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> FormatAsync(String expression, String formatSpec, String customVars) throws Exception
    {
    	return FormatAsync(expression, formatSpec, customVars, 0);
    }
    
    /**
     * Returns the result of executing the FMT function in a expressions list in the Database, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyFormatAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");	
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);	
	 *			result = client.FormatAsync("1þ2","R#10").getNow(result);
	 * 			client.Logout();			
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param expression The data or expression to format. It can contain MV marks, in which case the conversion in each value will be executed according to the original MV mark.
     * @param formatSpec Specified format
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> FormatAsync(String expression, String formatSpec) throws Exception
    {
    	return FormatAsync(expression, formatSpec, "");
    }
    
    /* DICTIONARIES */

    /**
     * Returns all the dictionaries of a file, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionariesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.DictionariesAsync("LK.CUSTOMERS", "", 60).getNow(result);		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DictionariesAsync(String filename, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Dictionaries(filename, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns all the dictionaries of a file, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionariesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.DictionariesAsync("LK.CUSTOMERS", "").getNow(result);		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DictionariesAsync(String filename, String customVars) throws Exception
    {
    	return DictionariesAsync(filename, customVars, 0);
    }
    
    /**
     * Returns all the dictionaries of a file, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyDictionariesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.DictionariesAsync("LK.CUSTOMERS").getNow(result);		
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> DictionariesAsync(String filename) throws Exception
    {
    	return DictionariesAsync(filename, "");
    }
    
    /* EXECUTE */

    /**
     * Allows the execution of any command from the Database in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecuteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.ExecuteAsync("WHO", "", 60).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ExecuteAsync(String statement, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.Execute(statement, DATAFORMAT_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Allows the execution of any command from the Database in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecuteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.ExecuteAsync("WHO", "").getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ExecuteAsync(String statement, String customVars) throws Exception
    {
    	return ExecuteAsync(statement, customVars, 0);
    }
    
    /**
     * Allows the execution of any command from the Database in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyExecuteAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);			
	 *			result = client.ExecuteAsync("WHO").getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param statement The command you want to execute in the Database.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ExecuteAsync(String statement) throws Exception
    {
    	return ExecuteAsync(statement, "");
    }
    
    /* GETVERSION */

    /**
     * Allows getting the server version, in a synchronous way with XML output format.
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetVersionAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");			
	 *			result = client.GetVersionAsync(60).getNow(result);	
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> GetVersionAsync(int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.GetVersion(DATAFORMAT_TYPE.XML, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Allows getting the server version, in a synchronous way with XML output format.
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetVersionAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");			
	 *			result = client.GetVersionAsync().getNow(result);	
	 * 			client.Logout();	
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> GetVersionAsync() throws Exception
    {
    	return GetVersionAsync(0);
    }
    
    /* LKSCHEMAS */

    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemasAsync(options, "", 60).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.LkSchemas(lkSchemasOptions, DATAFORMATSCH_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemasAsync(options, "").getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions, String customVars) throws Exception
    {
    	return LkSchemasAsync(lkSchemasOptions, customVars, 0);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = client.LkSchemasAsync(options).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions) throws Exception
    {
    	return LkSchemasAsync(lkSchemasOptions, "");
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.LkSchemasAsync().getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkSchemasAsync() throws Exception
    {
    	return LkSchemasAsync(null);
    }
    
    /* LKPROPERTIES */

    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkPropertiesAsync("LK.CUSTOMERS",options, "", 60).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.LkProperties(filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.XML, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkPropertiesAsync("LK.CUSTOMERS",options, "").getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars) throws Exception
    {
    	return LkPropertiesAsync(filename, lkPropertiesOptions, customVars, 0);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = client.LkPropertiesAsync("LK.CUSTOMERS",options).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
    {
    	return LkPropertiesAsync(filename, lkPropertiesOptions, "");
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.LkPropertiesAsync("LK.CUSTOMERS").getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param filename File name to LkProperties
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename) throws Exception
    {
    	return LkPropertiesAsync(filename, null);
    }   
    
    /* RESETCOMMONBLOCKS */

    /**
     * Resets the COMMON variables with the 100 most used files in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyResetCommonBlocksAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.ResetCommonBlocksAsync(60).getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ResetCommonBlocksAsync(int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return this.LinkarClt.ResetCommonBlocks(DATAFORMAT_TYPE.XML, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Resets the COMMON variables with the 100 most used files in a asynchronous way with XML output format.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.xml.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyResetCommonBlocksAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = client.ResetCommonBlocksAsync().getNow(result);	
	 * 			client.Logout();		
	 *		}
	 *		catch (Exception ex)
	 *		{
	 *			String error = ex.getMessage();
	 *			// Do something
	 *		}
	 *		return result;
	 *	}
	 * }
	 * </pre>
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> ResetCommonBlocksAsync() throws Exception
    {
    	return ResetCommonBlocksAsync(0);
    }
}
