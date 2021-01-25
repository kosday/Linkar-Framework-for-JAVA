package linkar.functions;

/**
 * Functions operation codes
 */
public enum OPERATION_CODE
{
    LOGIN((byte)0x01), READ((byte)0x02), UPDATE((byte)0x03), NEW((byte)0x04), DELETE((byte)0x05), CONVERSION((byte)0x06), FORMAT((byte)0x07),
    SELECT((byte)0x0A), SUBROUTINE((byte)0x0B), EXECUTE((byte)0x0C), DICTIONARIES((byte)0x0D), LOGOUT((byte)0x08), VERSION((byte)0x09),
    LKSCHEMAS((byte)0x0E), LKPROPERTIES((byte)0x0F), GETTABLE((byte)0x10), RESETCOMMONBLOCKS((byte)0x11);
    
	private byte numVal;
	
	OPERATION_CODE(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
};
