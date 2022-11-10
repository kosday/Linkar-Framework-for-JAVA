package linkar.functions;


/**
* Specify the output formats of LkSchemas and LkProperties operations(For other operations, see {@link DATAFORMAT_TYPE }).
*/
public enum DATAFORMATSCHPROP_TYPE {
	/** Show the results of the operation in MV format. */
	MV((byte)0x01), 
	/** Show the results of the operation in XML format. */
	XML((byte)0x02), 
	/** Show the results of the operation in JSON format. */
	JSON((byte)0x03), 
	/** Show the results of the operation in TABLE format. */
	TABLE((byte)0x04),
	/** Show the results of the operation in XML_DICT format. */
	XML_DICT((byte)0x05),
	/** Show the results of the operation in XML_SCH format. */
	XML_SCH((byte)0x06),
	/** Show the results of the operation in JSON_DICT format. */
	JSON_DICT((byte)0x07),
	/** Show the results of the operation in JSON_SCH format. */
	JSON_SCH((byte)0x08);
	
	private byte numVal;

	DATAFORMATSCHPROP_TYPE(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
	
	public static String GetString(DATAFORMATSCHPROP_TYPE strType)
	{
		String str = "MV";
		if (strType == DATAFORMATSCHPROP_TYPE.XML)
			str = "XML";
		if (strType == DATAFORMATSCHPROP_TYPE.JSON)
			str = "JSON";
		if (strType == DATAFORMATSCHPROP_TYPE.TABLE)
			str = "TABLE";
		if(strType == DATAFORMATSCHPROP_TYPE.XML_DICT)
			str = "XML_DICT";
		if(strType == DATAFORMATSCHPROP_TYPE.XML_SCH)
			str = "XML_SCH";
		if(strType == DATAFORMATSCHPROP_TYPE.JSON_DICT)
			str = "JSON_DICT";
		if(strType == DATAFORMATSCHPROP_TYPE.JSON_SCH)
			str = "JSON_SCH";

		return str;
	}
};
