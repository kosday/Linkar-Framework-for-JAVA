package linkar.functions.direct.table;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import linkar.CredentialOptions;
import linkar.functions.DATAFORMATSCH_TYPE;
import linkar.functions.LkPropertiesOptions;
import linkar.functions.LkSchemasOptions;
import linkar.functions.TableOptions;
import linkar.functions.direct.DirectFunctions;

public class Functions {

	/* = SYNC = */
	
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemas(credentials, options, "", 60);			
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
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
	 * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
	 * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
         String customVars, int receiveTimeout) throws Exception
    {
        return DirectFunctions.LkSchemas(credentialOptions, lkSchemasOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemas(credentials, options, "");			
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
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
	 * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
         String customVars) throws Exception
    {
        return LkSchemas(credentialOptions, lkSchemasOptions, customVars, 0);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemas(credentials, options);			
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
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String LkSchemas(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions) throws Exception
    {
        return LkSchemas(credentialOptions, lkSchemasOptions, "");
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemas()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.LkSchemas(credentials);			
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkProperties(credentials, "LK.CUSTOMERS",options, "", 60);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
        String customVars, int receiveTimeout) throws Exception
    {
        return DirectFunctions.LkProperties(credentialOptions, filename, lkPropertiesOptions, DATAFORMATSCH_TYPE.TABLE, customVars, receiveTimeout);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkProperties(credentials, "LK.CUSTOMERS",options, "");			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     * @throws Exception
     */
    public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
        String customVars) throws Exception
    {
        return LkProperties(credentialOptions, filename, lkPropertiesOptions, customVars, 0);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkProperties(credentials, "LK.CUSTOMERS",options);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     * @throws Exception
     */
    public static String LkProperties(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions) throws Exception
    {
        return LkProperties(credentialOptions, filename, lkPropertiesOptions, "");
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkProperties()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.LkProperties(credentials, "LK.CUSTOMERS");			
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "", "", "" , options, "", 60);			
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
        return DirectFunctions.GetTable(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, customVars, receiveTimeout);
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "", "", "" , options, "");			
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "", "", "" , options);			
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "", "", "");			
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "", "");			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     * @throws Exception
     */
    public static String GetTable(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause) throws Exception
    {
        return GetTable(credentialOptions, filename, selectClause, dictClause, "");
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS", "");			
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
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.functions.*;
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTable()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTable(credentials, "LK.CUSTOMERS");			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]]
     * @return The results of the operation.
     * @throws Exception
     */
    public static String GetTable(CredentialOptions credentialOptions, String filename) throws Exception
    {
        return GetTable(credentialOptions, filename, "");
    }
      
        
    /* = ASYNC = */
    
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemasAsync(credentials, options, "", 60).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkSchemasAsync(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
         String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return Functions.LkSchemas(credentialOptions, lkSchemasOptions, customVars, receiveTimeout);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemasAsync(credentials, options, "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkSchemasAsync(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions,
         String customVars)
    {
    	return LkSchemasAsync(credentialOptions, lkSchemasOptions, customVars, 0);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkSchemasOptions options = new LkSchemasOptions();
	 *			result = Functions.LkSchemasAsync(credentials, options).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param lkSchemasOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkSchemasAsync(CredentialOptions credentialOptions, LkSchemasOptions lkSchemasOptions)
    {
    	return LkSchemasAsync(credentialOptions, lkSchemasOptions, "");
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkSchemasAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.LkSchemasAsync(credentials).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkSchemasAsync(CredentialOptions credentialOptions)
    {
    	return LkSchemasAsync(credentialOptions, null);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkPropertiesAsync(credentials, "LK.CUSTOMERS",options, "", 60).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkPropertiesAsync(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
        String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return Functions.LkProperties(credentialOptions, filename, lkPropertiesOptions, customVars, receiveTimeout);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkPropertiesAsync(credentials, "LK.CUSTOMERS",options, "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkPropertiesAsync(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions,
        String customVars)
    {
    	return LkPropertiesAsync(credentialOptions, filename, lkPropertiesOptions, customVars, 0);
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			LkPropertiesOptions options = new LkPropertiesOptions();
	 *			result = Functions.LkPropertiesAsync(credentials, "LK.CUSTOMERS",options).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties.
     * @param lkPropertiesOptions This object defines the different options in base of the asked Schema Type: LKSCHEMAS, SQLMODE o DICTIONARIES.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkPropertiesAsync(CredentialOptions credentialOptions, String filename, LkPropertiesOptions lkPropertiesOptions)
    {
    	return LkPropertiesAsync(credentialOptions, filename, lkPropertiesOptions, "");
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyLkPropertiesAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.LkPropertiesAsync(credentials, "LK.CUSTOMERS").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File name to LkProperties.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> LkPropertiesAsync(CredentialOptions credentialOptions, String filename)
    {
    	return LkPropertiesAsync(credentialOptions, filename, null);
    }
    
    /* GETTABLE */

    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "", "", "" , options, "", 60).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
        TableOptions tableOptions, String customVars, int receiveTimeout)
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return Functions.GetTable(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, customVars, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "", "", "" , options, "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @param customVars It's a free text that will travel until the database to make the admin being able to manage additional behaviours in the standard routine SUB.LK.MAIN.CONTROL.CUSTOM. This routine will be called if the argument has content.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
        TableOptions tableOptions, String customVars)
    {
    	return GetTableAsync(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, customVars, 0);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			TableOptions options = new TableOptions();
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "", "", "" , options).getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @param tableOptions Different function options: rowHeaders, rowProperties, onlyVisibe, usePropertyNames, repeatValues, applyConversion, applyFormat, calculated, pagination, regPage, numPage.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause,
        TableOptions tableOptions)
    {
    	return GetTableAsync(credentialOptions, filename, selectClause, dictClause, sortClause, tableOptions, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "", "", "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @param sortClause Fragment of the phrase that indicates the selection order. If there is a selection rule Linkar will execute a SSELECT, otherwise Linkar will execute a SELECT. For example BY CUSTOMER.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause, String sortClause)
    {
    	return GetTableAsync(credentialOptions, filename, selectClause, dictClause, sortClause, null);
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "", "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @param dictClause Is the list of dictionaries to read, separated by space. If dictionaries are not indicated the function will read the complete buffer. For example CUSTOMER DATE ITEM. In NONE mode you may use the format LKFLDx where x is the attribute number.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause, String dictClause)
    {
    	return GetTableAsync(credentialOptions, filename, selectClause, dictClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS", "").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @param selectClause Fragment of the phrase that indicate the selection condition. For example WITH CUSTOMER = '1'.
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename, String selectClause)
    {
    	return GetTableAsync(credentialOptions, filename, selectClause, "");
    }
    
    /**
     * Returns a query result in a table format, in a synchronous way, in a asynchronous way with TABLE output format.
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
	 * import linkar.functions.direct.table.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MyGetTableAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 *			result = Functions.GetTableAsync(credentials, "LK.CUSTOMERS").getNow(result);			
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
     * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
     * @param filename File or table name defined in Linkar Schemas. Table notation is: MainTable[.MVTable[.SVTable]].
     * @return The results of the operation.
     */
    public static CompletableFuture<String> GetTableAsync(CredentialOptions credentialOptions, String filename)
    {
    	return GetTableAsync(credentialOptions, filename, "");
    }    
    
}
