package linkar.functions.persistent.table;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import linkar.CredentialOptions;
import linkar.functions.DATAFORMATSCH_TYPE;
import linkar.functions.LkPropertiesOptions;
import linkar.functions.LkSchemasOptions;
import linkar.functions.TableOptions;

public class LinkarClient {
	
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
        this.LinkarClt.Login(credentialOptions, customVars, 0);
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
        this.LinkarClt.Login(credentialOptions, "");
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
        this.LinkarClt.Logout(customVars, 0);
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a synchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @throws Exception
     */
    public void Logout() throws Exception
    {
        this.LinkarClt.Logout("");
    }
    
    /* LKSCHEMAS */

    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
        return this.LinkarClt.LkSchemas(lkSchemasOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.LkProperties(filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
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
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
    {
        return LkProperties(filename, lkPropertiesOptions, "");
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @return The results of the operation.
     * @throws Exception
     */
    public String LkProperties(String filename) throws Exception
    {
        return LkProperties(filename, null);
    }
    
    /* GETTABLE */

    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTable("LK.CUSTOMERS", "", "", "" , options, "", 60);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars, int receiveTimeout) throws Exception
    {
        return this.LinkarClt.GetTable(filename, selectClause, dictClause, sortClause, tableOptions, customVars, receiveTimeout);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTable("LK.CUSTOMERS", "", "", "" , options, "");	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
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
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTable("LK.CUSTOMERS", "", "", "" , options);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, sortClause, tableOptions, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTable("LK.CUSTOMERS", "", "", "");	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause, String sortClause) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, sortClause, null);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTable("LK.CUSTOMERS", "", "");	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause, String dictClause) throws Exception
    {
        return GetTable(filename, selectClause, dictClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTable("LK.CUSTOMERS", "");	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename, String selectClause) throws Exception
    {
        return GetTable(filename, selectClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTable("LK.CUSTOMERS");	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @return The results of the operation.
     * @throws Exception
     */
    public String GetTable(String filename) throws Exception
    {
        return GetTable(filename, "");
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
     * @return
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions, String customVars, int receiveTimeout)
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
     * @return
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions, String customVars)
    {
    	return this.LoginAsync(credentialOptions, customVars, 0);
    }
    
    /**
     * Starts the communication with a server allowing making use of the rest of functions until the Close method is executed or the connection with the server gets lost, in a asynchronous way.
     * <p>
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required - these operations are not related.
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @return
     */
    public CompletableFuture<Void> LoginAsync(CredentialOptions credentialOptions)
    {
    	return this.LoginAsync(credentialOptions, "");
    }
    
    /* LOGOUT */

    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a asynchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return
     */
    public CompletableFuture<Void> LogoutAsync(String customVars, int receiveTimeout)
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
     * @return
     */
    public CompletableFuture<Void> LogoutAsync(String customVars)
    {
    	return this.LogoutAsync(customVars, 0);
    }
    
    /**
     * Closes the communication with the server, that previously has been opened with a Login function, in a asynchronous way.
     * <p>
     * Logout is actually a "virtual" operation which disposes the current Client Session ID. No DBMS logout is performed.
     * @return
     */
    public CompletableFuture<Void> LogoutAsync()
    {
    	return this.LogoutAsync("");
    }
    
    /* LKSCHEMAS */

    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions, String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
			try {
				return this.LinkarClt.LkSchemas(lkSchemasOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
    	});
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions, String customVars)
    {
		return this.LkSchemasAsync(lkSchemasOptions, customVars, 0);
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     */
    public CompletableFuture<String> LkSchemasAsync(LkSchemasOptions lkSchemasOptions)
    {
		return this.LkSchemasAsync(lkSchemasOptions, "");
    }
    
    /**
     * Returns a list of all the Schemas defined in Linkar Schemas, or the EntryPoint account data files, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
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
     */
    public CompletableFuture<String> LkSchemasAsync()
    {
		return this.LkSchemasAsync(null);
    }
    
    
    /* LKPROPERTIES */

    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
			try {
				return this.LinkarClt.LkProperties(filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
    	});
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions, String customVars)
    {
		return this.LkPropertiesAsync(filename, lkPropertiesOptions, customVars, 0);
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename, LkPropertiesOptions lkPropertiesOptions)
    {
		return this.LkPropertiesAsync(filename, lkPropertiesOptions, "");
    }
    
    /**
     * Returns the Schema properties list defined in Linkar Schemas or the file dictionaries, in a asynchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param filename File name to LkProperties.
     * @return The results of the operation.
     */
    public CompletableFuture<String> LkPropertiesAsync(String filename)
    {
		return this.LkPropertiesAsync(filename, null);
    }
    
    /* GETTABLE */

    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "", "", "" , options, "", 60).getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
			try {
				return this.LinkarClt.GetTable(filename, selectClause, dictClause, sortClause, tableOptions, customVars, receiveTimeout);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
    	});
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "", "", "" , options, "").getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions, String customVars)
    {
    	return this.GetTableAsync(filename, selectClause, dictClause, sortClause, tableOptions, customVars, 0);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			TableOptions options = new TableOptions();
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "", "", "" , options).getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause, String dictClause, String sortClause, TableOptions tableOptions)
    {
    	return this.GetTableAsync(filename, selectClause, dictClause, sortClause, tableOptions, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "", "", "").getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause, String dictClause, String sortClause)
    {
    	return this.GetTableAsync(filename, selectClause, dictClause, sortClause, null);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "", "").getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause, String dictClause)
    {
    	return this.GetTableAsync(filename, selectClause, dictClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTableAsync("LK.CUSTOMERS", "").getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename, String selectClause)
    {
    	return this.GetTableAsync(filename, selectClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way with TABLE output format.
     * <p>
	 * TABLE output format uses the defined control characters in <a href="http://kosday.com/Manuals/en_web_linkar/lk_schemas_ep_parameters.html" target="_blank">EntryPoints Parameters</a> Table Row Separator and Column Row Separator.
	 * By default:
	 * <ul>
	 * <li>TAB char (9) for columns.</li>
	 * <li>VT char (11) for rows.</li>
	 * </ul>
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.persistent.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
	 *			result = client.GetTableAsync("LK.CUSTOMERS").getNow(result);	
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
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @return The results of the operation.
     */
    public CompletableFuture<String> GetTableAsync(String filename)
    {
    	return this.GetTableAsync(filename, "");
    }    
}
