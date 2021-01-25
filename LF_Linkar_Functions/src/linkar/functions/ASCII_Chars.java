package linkar.functions;

/**
* Some ASCII characters used by Linkar.
*/
public final class ASCII_Chars {
	/*
    Binario		Decimal	Hex	Abreviatura	Repr	AT	Nombre/Significado
    0000 0000	0		00	NUL					^@	Carácter Nulo
    0000 0001	1		01	SOH					^A	Inicio de Encabezado
    0000 0010	2		02	STX					^B	Inicio de Texto
    0000 0011	3		03	ETX					^C	Fin de Texto
    0000 0100	4		04	EOT					^D	Fin de Transmisión
    0000 0101	5		05	ENQ					^E	Enquiry
    0000 0110	6		06	ACK					^F	Acknowledgement
    0000 0111	7		07	BEL					^G	Timbre
    0000 1000	8		08	BS					^H	Retroceso
    0000 1001	9		09	HT					^I	Tabulación horizontal
    0000 1010	10		0A	LF					^J	Line feed
    0000 1011	11		0B	VT					^K	Tabulación Vertical
    0000 1100	12		0C	FF					^L	Form feed
    0000 1101	13		0D	CR					^M	Carriage return
    0000 1110	14		0E	SO					^N	Shift Out
    0000 1111	15		0F	SI					^O	Shift In
    0001 0000	16		10	DLE					^P	Data Link Escape
    0001 0001	17		11	DC1					^Q	Device Control 1 — oft. XON
    0001 0010	18		12	DC2					^R	Device Control 2
    0001 0011	19		13	DC3					^S	Device Control 3 — oft. XOFF
    0001 0100	20		14	DC4					^T	Device Control 4
    0001 0101	21		15	NAK					^U	Negative Acknowledgement
    0001 0110	22		16	SYN					^V	Synchronous Idle
    0001 0111	23		17	ETB					^W	End of Trans. Block
    0001 1000	24		18	CAN					^X	Cancel
    0001 1001	25		19	EM					^Y	End of Medium
    0001 1010	26		1A	SUB					^Z	Substitute
    0001 1011	27		1B	ESC					^[ or ESC	Escape
    0001 1100	28		1C	FS					^\	File Separator
    0001 1101	29		1D	GS					^]	Group Separator
    0001 1110	30		1E	RS					^^	Record Separator
    0001 1111	31		1F	US					^_	Unit Separator
    0111 1111	127		7F	DEL					^?, Delete, or Backspace	Delete         
*/

	//IMPORTANT:
	// Forbiden chars inside the Linkar Packets: SOH, STX, EOT, DC1, DC2, DC3, DC4, FS, SUB, RS, US


    public static final byte US = 0x1F;
    public static final char US_chr = '\u001F';
    public static final String US_str = "\u001F";

	/**
	* ASCII character used as separator of the arguments of a subroutine.
	*/
    public static final byte DC4 = 0x14;
	/**
	* ASCII character used as separator of the arguments of a subroutine.
	*/
    public static final char DC4_chr = '\u0014';
	/**
	* ASCII character used as separator of the arguments of a subroutine.
	*/
    public static final String DC4_str = "\u0014";

	/**
	* When the responses of the operations are of  MV type, this character is used to separate the header (the ThisList property in LkData) from the data.
	*/
    public static final byte FS = 0x1C;
	/**
	* When the responses of the operations are of  MV type, this character is used to separate the header (the ThisList property in LkData) from the data.
	*/
    public static final char FS_chr = '\u001C';
	/**
	* When the responses of the operations are of  MV type, this character is used to separate the header (the ThisList property in LkData) from the data.
	*/
    public static final String FS_str = "\u001C";
    
	/**
	* ASCII character used by Linkar as separator of items in lists. For example, list of records.
	*/
    public static final byte RS = 0x1E;
	/**
	* ASCII character used by Linkar as separator of items in lists. For example, list of records.
	*/
    public static final char RS_chr = '\u001E';
	/**
	* ASCII character used by Linkar as separator of items in lists. For example, list of records.
	*/
    public static final String RS_str = "\u001E";

	/**
	* ASCII character horizontal tab
	*/
    public static final byte TAB = 0x09;
	/**
	* ASCII character horizontal tab
	*/
    public static final char TAB_chr = '\t';
	/**
	* ASCII character horizontal tab
	*/
    public static final String TAB_str = "\t";

	/**
	* ASCII character line break.
	*/
    public static final byte LF = 0x0A;
	/**
	* ASCII character line break.
	*/
    public static final char LF_chr = '\n';
	/**
	* ASCII character line break.
	*/
    public static final String LF_str = "\n";
    
	/**
	* ASCII character carriage return.
	*/
    public static final byte CR = 0x0D;
	/**
	* ASCII character carriage return.
	*/
    public static final char CR_chr = '\r';
	/**
	* ASCII character carriage return.
	*/
    public static final String CR_str = "\r";
}
