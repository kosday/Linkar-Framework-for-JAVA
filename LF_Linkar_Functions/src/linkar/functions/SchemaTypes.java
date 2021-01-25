package linkar.functions;

public class SchemaTypes {
	
    public enum TYPE
    {
        LKSCHEMAS((byte)0x01),
        DICTIONARIES((byte)0x02),
        NONE((byte)0x03);
    	
    	private byte numVal;

    	TYPE(byte numVal) {
    		this.numVal = numVal;
    	}
    	
    	public byte getnumVal() {
			return numVal;
		}
    }

    final static String GetString(TYPE strType)
    {
        String str = "NOTHING";
        if (strType == TYPE.LKSCHEMAS)
            str = "LKSCHEMAS";
        if (strType == TYPE.DICTIONARIES)
            str = "DICTIONARIES";

        return str;
    }
}
