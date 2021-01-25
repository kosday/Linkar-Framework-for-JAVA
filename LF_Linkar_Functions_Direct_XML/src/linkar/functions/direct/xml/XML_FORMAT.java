package linkar.functions.direct.xml;

import linkar.functions.DATAFORMATCRU_TYPE;

/**
* XML output formats for Read, Update, New and Select
*/
public enum XML_FORMAT {
	/** Show the results of the operation in XML format. */
	XML((byte)0x02),
	/** Show the results of the operation in XML_DICT format, using the dictionaries. */
	XML_DICT((byte)0x05),
	/** Show the results of the operation in XML_SCH format, using the schema properties. */
	XML_SCH((byte)0x06);
	private byte numVal;

	XML_FORMAT(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
	
	public DATAFORMATCRU_TYPE getCRUFormat() {
		switch(this.numVal)
		{
		case (byte)0x02:
			return DATAFORMATCRU_TYPE.XML;
		
		case (byte)0x05:
			return DATAFORMATCRU_TYPE.XML_DICT;
		
		case (byte)0x06:
			return DATAFORMATCRU_TYPE.XML_SCH;
		}
		return null;
	}
	
}
