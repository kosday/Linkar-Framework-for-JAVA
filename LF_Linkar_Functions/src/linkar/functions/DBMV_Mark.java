package linkar.functions;

/**
* Special ASCII characters used by Multivalued Databases.
*/
public final class DBMV_Mark {
	
	/**
	* Character ASCII 255.IM Multi-value mark.
	*/
    public static final char IM = (char)255;
	/**	
	* Character ASCII 254. AM Multi-value mark.
	*/
    public static final char AM = (char)254;
	/**	
	* Character ASCII 253. VM Multi-value mark.
	*/
    public static final char VM = (char)253;
	/**	
	* Character ASCII 252. SM Multi-value mark.
	*/
    public static final char SM = (char)252;
	/**	
	* Character ASCII 251. TM Multi-value mark.
	*/
    public static final char TM = (char)251;

	/**
	* Character ASCII 255. IM Multi-value mark.
	*/
    public static final String IM_str = "\u00FF";
	/**	
	* Character ASCII 254. AM Multi-value mark.
	*/
    public static final String AM_str = "\u00FE";
	/**	
	* Character ASCII 253. VM Multi-value mark.
	*/
    public static final String VM_str = "\u00FD";
	/**	
	* Character ASCII 252. SM Multi-value mark.
	*/
    public static final String SM_str = "\u00FC";
	/**	
	* Character ASCII 251. TM Multi-value mark.
	*/
    public static final String TM_str = "\u00FB";

    private static final String IM_txt = "[@@IM@]";
    private static final String AM_txt = "[@@AM@]";
    private static final String VM_txt = "[@@VM@]";
    private static final String SM_txt = "[@@SM@]";
    private static final String TM_txt = "[@@TM@]";    
}
