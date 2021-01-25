package linkar.commands;

/**
 * Command operation codes
 */
public enum OPERATION_CODE
{
    LOGIN((byte)0x01),
    LOGOUT((byte)0x08),
    COMMAND_XML ((byte)0x96),
    COMMAND_JSON ((byte)0x97);
    
	private byte numVal;
	
	OPERATION_CODE(byte numVal) {
		this.numVal = numVal;
	}
	
	public byte getnumVal() {
		return numVal;
	}
};
