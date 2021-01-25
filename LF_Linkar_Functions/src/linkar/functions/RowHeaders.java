package linkar.functions;

public class RowHeaders {
    public enum TYPE
    {
    	MAINLABEL((byte)0x01),
    	SHORTLABEL((byte)0x02),
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
        if (strType == TYPE.MAINLABEL)
            str = "MAINLABEL";
        if (strType == TYPE.SHORTLABEL)
            str = "SHORTLABEL";

        return str;
    }
}
