package linkar.functions.persistent.json;

import linkar.functions.DATAFORMATCRU_TYPE;

/**
* JSON output formats for Read, Update, New and Select
*/
public enum JSON_FORMAT {
	/** Show the results of the operation in JSON format. */
	JSON((byte)0x03),
	/** Show the results of the operation in JSON_DICT format, using the dictionaries. */
	JSON_DICT((byte)0x07),
	/** Show the results of the operation in JSON_SCH format, using the schema properties. */
	JSON_SCH((byte)0x08);
	private byte numVal;

	JSON_FORMAT(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
	
	public DATAFORMATCRU_TYPE getCRUFormat() {
		switch(this.numVal)
		{
		case (byte)0x03:
			return DATAFORMATCRU_TYPE.JSON;
		
		case (byte)0x07:
			return DATAFORMATCRU_TYPE.JSON_DICT;
		
		case (byte)0x08:
			return DATAFORMATCRU_TYPE.JSON_SCH;
		}
		return null;
	}
	
}
