package linkar.commands.persistent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import linkar.ConnectionInfo;
import linkar.CredentialOptions;
import linkar.Linkar;
import linkar.commands.ENVELOPE_FORMAT;
import linkar.commands.DATAFORMAT_TYPE;
import linkar.commands.OPERATION_CODE;

public class LinkarClient {
    private ConnectionInfo ConnectionInfo;
    private int ReceiveTimeout;
    
    /**
     * PublicKey
     * @return The public key used to encrypt transmission data between LinkarCLIENT and LinkarSERVER. This value is set after Login operation.
     */
    public String getPublicKey()
    {
        if (this.ConnectionInfo != null)
            return this.ConnectionInfo.getPublicKey();
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
     * Login is actually a "virtual" operation which creates a new Client Session ID. No DBMS login is performed unless Linkar SERVER determines new Database Sessions are required. These operations are not related.
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
            String loginArgs = customVars + "\u001F" + options;
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
            	String sessionId = "";
                String[] parts = loginResult.split("\u001C");
                if (parts.length >= 1)
                {
                    String[] headersList = parts[0].split("\u00FE");
                    for (int i = 1; i < headersList.length; i++)
                    {
                        if (headersList[i].toUpperCase() == "RECORD_ID")
                        {
                        	sessionId = parts[i];
                            break;
                        }
                    }
                }
                if (sessionId != null && sessionId.length() > 0)
                {
                	this.ConnectionInfo = new ConnectionInfo(sessionId, connectionInfo.getLkConnectionId(), connectionInfo.getPublicKey(), credentialOptions);   
                }
            }
        }
    }
    
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
					this.Login(credentialOptions, customVars, receiveTimeout);
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
					this.Logout(customVars, receiveTimeout);
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

    /* COMMANDS */

    /**
     * Allows a variety of persistent operations using standard templates (XML, JSON), in a synchronous way.
     * @param command Content of the operation you want to send.
     * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendCommand(String command, ENVELOPE_FORMAT commandFormat, int receiveTimeout) throws Exception
    {
    	String customVars = "";
    	String options = "";
    	String sendCommandArgs = customVars + "\u001F" + options + "\u001F" + command;
        byte opCode;
        if (commandFormat == ENVELOPE_FORMAT.JSON)
            opCode = OPERATION_CODE.COMMAND_JSON.getnumVal();
        else
            opCode = OPERATION_CODE.COMMAND_XML.getnumVal();
        byte byteInputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        byte byteOutputFormat = DATAFORMAT_TYPE.MV.getnumVal();
        String result = Linkar.ExecutePersistentOperation(this.ConnectionInfo, opCode, sendCommandArgs, byteInputFormat, byteOutputFormat, receiveTimeout);
        return result;
    }
    
    /**
     * Allows a variety of persistent operations using standard templates (XML, JSON), in a synchronous way.
     * @param command Content of the operation you want to send.
     * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendCommand(String command, ENVELOPE_FORMAT commandFormat) throws Exception
    {
        return SendCommand(command, commandFormat, 0);
    }
    
    /**
     * Allows a variety of persistent operations using standard templates (XML, JSON), in a asynchronous way.
     * @param command Content of the operation you want to send.
     * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendCommandAsync(String command, ENVELOPE_FORMAT commandFormat, int receiveTimeout) throws Exception
    {
    	return CompletableFuture.supplyAsync(() -> {
				try {
					return SendCommand(command, commandFormat, receiveTimeout);
				} catch (Exception e) {
					throw new CompletionException(e);
				}
		});
    }
    
    /**
     * Allows a variety of persistent operations using standard templates (XML, JSON), in a asynchronous way.
     * @param command Content of the operation you want to send.
     * @param commandFormat Indicates in what format you are doing the operation: XML or JSON.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendCommandAsync(String command, ENVELOPE_FORMAT commandFormat) throws Exception
    {
    	return SendCommandAsync(command, commandFormat, 0);
    }
    
    /* JSON */
    
    /**
     * Allows a variety of persistent operations using standard JSON templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendJsonCommand(String command, int receiveTimeout) throws Exception
    {
        return SendCommand(command, ENVELOPE_FORMAT.JSON, receiveTimeout);
    }
    
    /**
     * Allows a variety of persistent operations using standard JSON templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendJsonCommand(String command) throws Exception
    {
        return SendJsonCommand(command, 0);
    }
    
    /**
     * Allows a variety of persistent operations using standard JSON templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendJsonCommandAsync(String command, int receiveTimeout) throws Exception
    {
    	return SendCommandAsync(command, ENVELOPE_FORMAT.JSON, receiveTimeout);
    }
    
    /**
     * Allows a variety of persistent operations using standard JSON templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendJsonCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendJsonCommandAsync(String command) throws Exception
    {
    	return SendJsonCommandAsync(command, 0);
    }
    
    /* XML */
    
    /**
     * Allows a variety of persistent operations using standard XML templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendXmlCommand(String command, int receiveTimeout) throws Exception
    {
        return SendCommand(command, ENVELOPE_FORMAT.XML, receiveTimeout);
    }
    
    /**
     * Allows a variety of persistent operations using standard XML templates, in a synchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommand()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @param receiveTimeout It's the maximum time in seconds that the client will keep waiting the answer by the server. By default 0 (wait indefinitely).
     * @return The results of the operation.
     * @throws Exception
     */
    public String SendXmlCommand(String command) throws Exception
    {
        return SendXmlCommand(command, 0);
    }
    
    /**
     *Allows a variety of persistent operations using standard XML templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendXmlCommandAsync(String command, int receiveTimeout) throws Exception
    {
    	return SendCommandAsync(command, ENVELOPE_FORMAT.XML, receiveTimeout);
    }
    
    /**
     * Allows a variety of persistent operations using standard XML templates, in a asynchronous way.
     * <p>
	 * Example:
     * <pre>
	 * import linkar.*;
	 * import linkar.commands.persistent.*;
	 * 
	 * public class Test {
	 *	
	 *	public String MySendXmlCommandAsync()
	 *	{
	 *		String result = "";
	 *		try{
	 *			CredentialOptions credentials = new CredentialOptions("127.0.0.1", "EPNAME", 11300, "admin", "admin");
	 * 			LinkarClient client = new LinkarClient();
	 * 			client.Login(credentials);
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
     * @param command Content of the operation you want to send.
     * @return The results of the operation.
     * @throws Exception
     */
    public CompletableFuture<String> SendXmlCommandAsync(String command) throws Exception
    {
    	return SendXmlCommandAsync(command, 0);
    }
}
