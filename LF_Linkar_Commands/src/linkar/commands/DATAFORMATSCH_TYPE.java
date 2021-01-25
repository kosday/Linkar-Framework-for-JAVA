package linkar.commands;


/**
* Specify the output formats of LkSchemas and LkProperties operations(For other operations, see {@link DATAFORMAT_TYPE }).
*/
public enum DATAFORMATSCH_TYPE {
	/** Show the results of the operation in MV format. */
	MV((byte)0x01), 
	/** Show the results of the operation in XML format. */
	XML((byte)0x02), 
	/** Show the results of the operation in JSON format. */
	JSON((byte)0x03), 
	/** Show the results of the operation in TABLE format. */
	TABLE((byte)0x04);
	private byte numVal;

	DATAFORMATSCH_TYPE(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
	
	public static String GetString(DATAFORMATSCH_TYPE strType)
	{
		String str = "MV";
		if (strType == DATAFORMATSCH_TYPE.XML)
			str = "XML";
		if (strType == DATAFORMATSCH_TYPE.JSON)
			str = "JSON";
		if (strType == DATAFORMATSCH_TYPE.TABLE)
			str = "TABLE";

		return str;
	}
};
