package linkar.commands.direct;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import linkar.CredentialOptions;
import linkar.Linkar;
import linkar.commands.*;

/**
 * These functions perform synchronous direct (without establishing permanent session) operations with any kind of output format type.
 */
public class DirectCommands {
		
	/* COMMANDS */
	
	/**
	 * Allows a variety of direct operations using standard templates (XML, JSON), in a synchronous way.
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param command Content of the operation you want to send.
	 * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
	 * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendCommand(CredentialOptions credentialOptions, String command, ENVELOPE_FORMAT commandFormat, int receiveTimeout) throws Exception
    {
        String customVars = "";
        String options = "";
        String sendCommandArgs = customVars + "\u001F" + options + "\u001F" + command;
        byte opCode;
        if (commandFormat == ENVELOPE_FORMAT.JSON)
            opCode = OPERATION_CODE.COMMAND_JSON.getnumVal();
        else
            opCode = (byte)OPERATION_CODE.COMMAND_XML.getnumVal();
        byte byteInputFormat = (byte)DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = (byte)DATAFORMAT_TYPE.MV.getnumVal();
        String result = Linkar.ExecuteDirectOperation(credentialOptions, opCode, sendCommandArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
	/**
	 * Allows a variety of direct operations using standard templates (XML, JSON), in a synchronous way.
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param command Content of the operation you want to send.
	 * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendCommand(CredentialOptions credentialOptions, String command, ENVELOPE_FORMAT commandFormat) throws Exception
    {
        return SendCommand(credentialOptions, command, commandFormat, 0);
    }   
    
	/**
	 * Allows a variety of direct operations using standard templates (XML, JSON), in a asynchronous way.
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param command Content of the operation you want to send.
	 * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
	 * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendCommandAsync(CredentialOptions credentialOptions, String command, ENVELOPE_FORMAT commandFormat, int receiveTimeout) throws Exception
    { 	
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return SendCommand(credentialOptions,command, commandFormat, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
	/**
	 * Allows a variety of direct operations using standard templates (XML, JSON), in a asynchronous way.
	 * @param credentialOptions Object that defines the necessary data to access to the Linkar Server: Username, Password, EntryPoint, Language, FreeText.
	 * @param command Content of the operation you want to send.
	 * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendCommandAsync(CredentialOptions credentialOptions, String command, ENVELOPE_FORMAT commandFormat) throws Exception
    { 	
    	return SendCommandAsync(credentialOptions, command, commandFormat, 0);
    }
    
    /* JSON */
    
	/**
	 * Allows a variety of direct operations using standard JSON templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"{" +
	 * 			"	\"NAME\" : \"READ\"," +
	 * 			"	\"COMMAND\" :" + 
	 * 			"	{" +
	 * 			"		\"CALCULATED\" : \"True\" ," +
	 * 			"		\"OUTPUT_FORMAT\" : \"JSON_DICT\" ," +
	 * 			"		\"FILE_NAME\" : \"LK.CUSTOMERS\" ," +
	 * 			"		\"RECORDS\" : [" +
	 * 			"			{ \"LKITEMID\" : \"2\" }" +
	 * 			"		]" +
	 * 			"	}" +
	 * 			"}";
	 *			result = DirectCommands.SendJsonCommand(credentials, command, 60);			
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
	 * @param command Content of the operation you want to send.
	 * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendJsonCommand(CredentialOptions credentialOptions, String command, int receiveTimeout) throws Exception
    {
        return SendCommand(credentialOptions, command, ENVELOPE_FORMAT.JSON, receiveTimeout);
    }
    
	/**
	 * Allows a variety of direct operations using standard JSON templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"{" +
	 * 			"	\"NAME\" : \"READ\"," +
	 * 			"	\"COMMAND\" :" + 
	 * 			"	{" +
	 * 			"		\"CALCULATED\" : \"True\" ," +
	 * 			"		\"OUTPUT_FORMAT\" : \"JSON_DICT\" ," +
	 * 			"		\"FILE_NAME\" : \"LK.CUSTOMERS\" ," +
	 * 			"		\"RECORDS\" : [" +
	 * 			"			{ \"LKITEMID\" : \"2\" }" +
	 * 			"		]" +
	 * 			"	}" +
	 * 			"}";
	 *			result = DirectCommands.SendJsonCommand(credentials, command);			
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendJsonCommand(CredentialOptions credentialOptions, String command) throws Exception
    {
        return SendCommand(credentialOptions, command, ENVELOPE_FORMAT.JSON, 0);
    }
    
	/**
	 * Allows a variety of direct operations using standard JSON templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"{" +
	 * 			"	\"NAME\" : \"READ\"," +
	 * 			"	\"COMMAND\" :" + 
	 * 			"	{" +
	 * 			"		\"CALCULATED\" : \"True\" ," +
	 * 			"		\"OUTPUT_FORMAT\" : \"JSON_DICT\" ," +
	 * 			"		\"FILE_NAME\" : \"LK.CUSTOMERS\" ," +
	 * 			"		\"RECORDS\" : [" +
	 * 			"			{ \"LKITEMID\" : \"2\" }" +
	 * 			"		]" +
	 * 			"	}" +
	 * 			"}";
	 *			result = DirectCommands.SendJsonCommandAsync(credentials, command, 60).getNow(result);		
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendJsonCommandAsync(CredentialOptions credentialOptions, String command, int receiveTimeout) throws Exception
    { 	
    	return SendCommandAsync(credentialOptions, command, ENVELOPE_FORMAT.JSON, receiveTimeout);
    }
    
	/**
	 * Allows a variety of direct operations using standard JSON templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"{" +
	 * 			"	\"NAME\" : \"READ\"," +
	 * 			"	\"COMMAND\" :" + 
	 * 			"	{" +
	 * 			"		\"CALCULATED\" : \"True\" ," +
	 * 			"		\"OUTPUT_FORMAT\" : \"JSON_DICT\" ," +
	 * 			"		\"FILE_NAME\" : \"LK.CUSTOMERS\" ," +
	 * 			"		\"RECORDS\" : [" +
	 * 			"			{ \"LKITEMID\" : \"2\" }" +
	 * 			"		]" +
	 * 			"	}" +
	 * 			"}";
	 *			result = DirectCommands.SendJsonCommandAsync(credentials, command).getNow(result);		
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendJsonCommandAsync(CredentialOptions credentialOptions, String command) throws Exception
    { 	
    	return SendJsonCommandAsync(credentialOptions, command, 0);
    }
    
    /* XML */
    
	/**
	 * Allows a variety of direct operations using standard XML templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"&lt;COMMAND NAME=\"READ\"&gt;" +
	 * 			"   &lt;CALCULATED&gt;True&lt;/CALCULATED&gt;" +
	 * 			"   &lt;OUTPUT_FORMAT&gt;XML_DICT&lt;/OUTPUT_FORMAT&gt;" +
	 * 			"   &lt;FILE_NAME&gt;LK.CUSTOMERS&lt;/FILE_NAME&gt;" +
	 * 			"   &lt;RECORDS&gt;" +
	 * 			"       &lt;RECORD&gt;" +
	 * 			"           &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" + 
	 * 			"       &lt;/RECORD&gt;" +
	 * 			"   &lt;/RECORDS&gt;" +
	 * 			"&lt;/COMMAND&gt;";
	 *			result = DirectCommands.SendXmlCommand(credentials, command, 60);			
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
	 * @param command Content of the operation you want to send.
	 * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendXmlCommand(CredentialOptions credentialOptions, String command, int receiveTimeout) throws Exception
    {
        return SendCommand(credentialOptions, command, ENVELOPE_FORMAT.XML, receiveTimeout);
    }
    
	/**
	 * Allows a variety of direct operations using standard XML templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"&lt;COMMAND NAME=\"READ\"&gt;" +
	 * 			"   &lt;CALCULATED&gt;True&lt;/CALCULATED&gt;" +
	 * 			"   &lt;OUTPUT_FORMAT&gt;XML_DICT&lt;/OUTPUT_FORMAT&gt;" +
	 * 			"   &lt;FILE_NAME&gt;LK.CUSTOMERS&lt;/FILE_NAME&gt;" +
	 * 			"   &lt;RECORDS&gt;" +
	 * 			"       &lt;RECORD&gt;" +
	 * 			"           &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" + 
	 * 			"       &lt;/RECORD&gt;" +
	 * 			"   &lt;/RECORDS&gt;" +
	 * 			"&lt;/COMMAND&gt;";
	 *			result = DirectCommands.SendXmlCommand(credentials, command);			
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static String SendXmlCommand(CredentialOptions credentialOptions, String command) throws Exception
    {
        return SendCommand(credentialOptions, command, ENVELOPE_FORMAT.XML, 0);
    }
    
	/**
	 * Allows a variety of direct operations using standard XML templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"&lt;COMMAND NAME=\"READ\"&gt;" +
	 * 			"   &lt;CALCULATED&gt;True&lt;/CALCULATED&gt;" +
	 * 			"   &lt;OUTPUT_FORMAT&gt;XML_DICT&lt;/OUTPUT_FORMAT&gt;" +
	 * 			"   &lt;FILE_NAME&gt;LK.CUSTOMERS&lt;/FILE_NAME&gt;" +
	 * 			"   &lt;RECORDS&gt;" +
	 * 			"       &lt;RECORD&gt;" +
	 * 			"           &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" + 
	 * 			"       &lt;/RECORD&gt;" +
	 * 			"   &lt;/RECORDS&gt;" +
	 * 			"&lt;/COMMAND&gt;";
	 *			result = DirectCommands.SendXmlCommandAsync(credentials, command, 60).getNow(result);			
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendXmlCommandAsync(CredentialOptions credentialOptions, String command, int receiveTimeout) throws Exception
    { 	
    	return SendCommandAsync(credentialOptions, command, ENVELOPE_FORMAT.XML, receiveTimeout);
    }
    
	/**
	 * Allows a variety of direct operations using standard XML templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.direct.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			string command =
	 * 			"&lt;COMMAND NAME=\"READ\"&gt;" +
	 * 			"   &lt;CALCULATED&gt;True&lt;/CALCULATED&gt;" +
	 * 			"   &lt;OUTPUT_FORMAT&gt;XML_DICT&lt;/OUTPUT_FORMAT&gt;" +
	 * 			"   &lt;FILE_NAME&gt;LK.CUSTOMERS&lt;/FILE_NAME&gt;" +
	 * 			"   &lt;RECORDS&gt;" +
	 * 			"       &lt;RECORD&gt;" +
	 * 			"           &lt;LKITEMID&gt;2&lt;/LKITEMID&gt;" + 
	 * 			"       &lt;/RECORD&gt;" +
	 * 			"   &lt;/RECORDS&gt;" +
	 * 			"&lt;/COMMAND&gt;";
	 *			result = DirectCommands.SendXmlCommandAsync(credentials, command).getNow(result);			
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
	 * @param command Content of the operation you want to send.
	 * @return The results of the operation.
	 * @throws Exception
	 */
    public static CompletableFuture<String> SendXmlCommandAsync(CredentialOptions credentialOptions, String command) throws Exception
    { 	
    	return SendXmlCommandAsync(credentialOptions, command, 0);
    }
    
}
