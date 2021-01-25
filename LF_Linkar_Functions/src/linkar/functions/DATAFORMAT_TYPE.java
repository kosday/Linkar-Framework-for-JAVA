package linkar.functions;


/**
* Indicates in what format you want to receive the data resulting from the operation.
*/
public enum DATAFORMAT_TYPE {
	/** Show the results of the operation in MV format. */
	MV((byte)0x01),
	/** Show the results of the operation in XML format. */
	XML((byte)0x02),
	/** Show the results of the operation in JSON format. */
	JSON((byte)0x03);
	private byte numVal;

	DATAFORMAT_TYPE(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
	
	public static String GetString(DATAFORMAT_TYPE strType)
	{
		String str = "MV";
		if (strType == DATAFORMAT_TYPE.XML)
			str = "XML";
		if (strType == DATAFORMAT_TYPE.JSON)
			str = "JSON";

		return str;
	}
}
