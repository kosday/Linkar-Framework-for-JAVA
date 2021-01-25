package linkar.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MvOperations
{

	/**
	* Text Function that counts the delimited substrings inside a string
    * <p>
	* Example:
    * <pre>
	* int result = MvOperations.LkDCount("CUSTOMER UPDATE 2þADDRESS 2þ444", "þ");
	* </pre>
	* @param	str			The string you are going to count.
	* @param 	delimiter	The separator you are going to count.
	* @return				The number of occurrences found.
	*/
	public static int LkDCount(String str, String delimiter)
    {
        if (str == null || str.length() == 0)
            return 0;
        else if (delimiter == null || delimiter.length() == 0)
            return str.length();
        else
        {
            //String[] separator = new String[] { delimiter };
            //String[] parts = str.split(separator, StringSplitOptions.None);
            //String[] parts = str.split(String.valueOf(separator));
        	String[] parts = str.split(delimiter, -1);

            int c = parts.length;
            return c;
        }
    }

	/**
	* Text function that counts the occurrences of a substring inside a string.
    * <p>
	* Example:
    * <pre>
	* int result = MvOperations.LkCount("CUSTOMER UPDATE 2þADDRESS 2þ444", "þ");
	* </pre>
	* @param	str			The string you are going to count.
	* @param	delimiter	The separator you are going to count.
	* @return				The number of occurrences found.
	*/
    public static int LkCount(String str, String delimiter)
    {
    	if (str == null || str.length() == 0)
            return 0;
        else if (delimiter == null || delimiter.length() == 0)
            return str.length();
        else
        {
        	return LkDCount(str, delimiter) - 1;
        }
    }

	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444", 1);
	* </pre>
	* @param	str		The string on which you are going to extract a value.
	* @param	field	The position of the attribute where you want to extract.
	* @return			A new string with the extracted value.
	*/
    public static String LkExtract(String str, int field)
    {
    	return LkExtract(str, field, 0, 0);
    }

	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444", 1, 1);
	* </pre>
	* @param	str		The string on which you are going to extract a value.
	* @param	field	The position of the attribute where you want to extract.
	* @param	value	The multivalue position where you want to extract.
	* @return			A new string with the extracted value.
	*/
    public static String LkExtract(String str, int field, int value)
    {
    	return LkExtract(str, field, value, 0);
    }
    
	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444", 1, 1, 1);
	* </pre>
	* @param	str			The string on which you are going to extract a value.
	* @param	field		The position of the attribute where you want to extract.
	* @param	value		The multivalue position where you want to extract.
	* @param	subvalue	The subvalue position where you want to extract.
	* @return				A new string with the extracted value.
	*/
    public static String LkExtract(String str, int field, int value, int subvalue)
    {
        String aux = "";

        if (field > 0)
        {
            String[] parts = str.split(String.valueOf(DBMV_Mark.AM), -1);
            if (field <= parts.length)
                str = aux = parts[field - 1];
        }

        if (value > 0)
        {
            String[] parts = str.split(String.valueOf(DBMV_Mark.VM), -1);
            if (value <= parts.length)
                str = aux = parts[value - 1];
        }

        if (subvalue > 0)
        {
            String[] parts = str.split(String.valueOf(DBMV_Mark.SM), -1);
            if (subvalue <= parts.length)
                aux = parts[subvalue - 1];
        }

        return aux;
    }
    
	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444","NAMEþADDRþPHONE", 1, 1, 1);
	* </pre>
	* @param	str			The string on which you are going to extract a value.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to extract.
	* @param	value		The multivalue position where you want to extract.
	* @param	subvalue	The subvalue position where you want to extract.
	* @return				A new string with the extracted value.
	*/
    public static String LkExtract(String str, String lstDicts, String field, int value, int subvalue)
    {
        String aux = "";

        int pos = GetDictPos(lstDicts,field);
        if (pos > -1)
        {
            aux = LkExtract(str, pos, value, subvalue);
        }

        return aux;
    }
    
	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444","NAMEþADDRþPHONE", 1, 1);
	* </pre>
	* @param	str			The string on which you are going to extract a value.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to extract.
	* @param	value		The multivalue position where you want to extract.
	* @return				A new string with the extracted value.
	*/
    public static String LkExtract(String str, String lstDicts, String field, int value)
    {
    	return LkExtract(str, lstDicts, field, value, 0);
    }
    
	/**
	* Text Function that extracts a field, value or subvalue from a dynamic array.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkExtract("CUSTOMER UPDATE 2þADDRESS 2þ444","NAMEþADDRþPHONE", 1);
	* </pre>
	* @param	str			The string on which you are going to extract a value.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to extract.
	* @return				A new string with the extracted value.
	*/
    public static String LkExtract(String str, String lstDicts, String field)
    {
    	return LkExtract(str, lstDicts, field, 0, 0);
    }

	/**
	* Text Function that replaces the occurrences of a substring inside a string, by other substring.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkChange("CUSTOMER UPDATE 2þADDRESS 2þ444", "UPDATE", "MYTEXT");
	* </pre>
	* @param 	str			The string on which the value is going to change.
	* @param 	strOld		The value to change.
	* @param	strNew		The new value.
	* @return				A new string with the replaced value.
	*/
    public static String LkChange(String str, String strOld, String strNew)
    {
    	return LkChange(str, strOld, strNew, 0, 0);
    }
    
	/**
	* Text Function that replaces the occurrences of a substring inside a string, by other substring.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkChange("CUSTOMER UPDATE 2þADDRESS 2þ444", "UPDATE", "MYTEXT", 1, 1);
	* </pre>
	* @param 	str			The string on which the value is going to change.
	* @param 	strOld		The value to change.
	* @param	strNew		The new value.
	* @param 	occurrence	The number of times it will change.
	* @param	start		The position from which you are going to start changing values.
	* @return				A new string with the replaced value.
	*/
	public static String LkChange(String str, String strOld, String strNew, int occurrence, int start)
    {
		if (str.length() > 0)
		{    
			if (start < 1)
				start = 1;
			if (occurrence < 0)
				occurrence = 0;
			int index = str.indexOf(strOld);
			if (index >= 0)
			{
				int subindex = 0;
				boolean next = true;
				int count = 0;
				while(next)
				{    
					subindex = str.indexOf(strOld,subindex);
					count++;
					if (subindex == -1 || count >= start)
						next = false;
					else
						subindex = subindex + strOld.length();
				}
				if (subindex >= 0)
				{
					String initstr = str.substring(0,subindex);
					String endstr = str.substring(subindex);
					int maxocc = endstr.split(strOld).length;
					if (occurrence > 0)
					{
						for (int occ = 0; occ < occurrence; occ++)
						{
							if (occ > maxocc)
								break;
							endstr = endstr.replaceFirst(strOld, strNew);
						}
					}
					else
					{
						endstr = endstr.replace(strOld, strNew);
					}
					return initstr + endstr;
				}
				else
				{
					return str;
				}
			}
			else
			{
				return str;
			}

		}
		else 
			return str;
    }

	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	field		The position of the attribute where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, int field)
    {
    	return LkReplace(str, newVal, field, 0, 0);
    }
    
	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", 1, 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	field		The position of the attribute where you want to replace.
	* @param	value		The multivalue position where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, int field, int value)
    {
    	return LkReplace(str, newVal, field, value, 0);
    }
    
	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", 1, 1, 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	field		The position of the attribute where you want to replace.
	* @param	value		The multivalue position where you want to replace.
	* @param	subvalue	The subvalue position where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, int field, int value, int subvalue)
    {
        String result = "";

        int len = str.length();
        int i = 0;

        field--;
        while (field > 0 && len > 0)
        {
            if (str.charAt(i) == DBMV_Mark.AM)
                field--;
            i++;
            len--;
        }
        if (field > 0)
        {
            //str += CSharpMethods.CreateString(DBMV_Mark.AM, field);
        	String createdstr = "";
    		String cstr = String.valueOf(DBMV_Mark.AM);
    		for (int index = 0; index < field; index++)
    			createdstr += cstr;
    		str += createdstr;
            i += field;
        }

        value--;
        while (value > 0 && len > 0)
        {
            if (str.charAt(i) == DBMV_Mark.AM)
                break;

            if (str.charAt(i) == DBMV_Mark.VM)
                value--;
            i++;
            len--;
        }
        if (value > 0)
        {
            //str = str.Insert(i, CSharpMethods.CreateString(DBMV_Mark.VM, value));
        	//str = CSharpMethods.InsertString(i,str,CSharpMethods.CreateString(DBMV_Mark.VM, value));
        	String createdstr = "";
    		String cstr = String.valueOf(DBMV_Mark.VM);
    		for (int index = 0; index < value; index++)
    			createdstr += cstr;
    		str = createdstr;
            i += value;
        }

        subvalue--;
        while (subvalue > 0 && len > 0) //////
        {
            if (str.charAt(i) == DBMV_Mark.VM || str.charAt(i) == DBMV_Mark.AM)
                break;

            if (str.charAt(i) == DBMV_Mark.SM)
                subvalue--;

            i++;
            len--;
        }
        if (subvalue > 0)
        {
            //str = str.Insert(i, CSharpMethods.CreateString(DBMV_Mark.SM, subvalue));
        	//str = CSharpMethods.InsertString(i,str,CSharpMethods.CreateString(DBMV_Mark.SM, subvalue));
        	String createdstr = "";
    		String cstr = String.valueOf(DBMV_Mark.SM);
    		for (int index = 0; index < subvalue; index++)
    			createdstr += cstr;
    		str = createdstr;
            i += subvalue;
        }

        if (i >= str.length())
            result = str + newVal;
        else
        {
            int nextAM = str.indexOf(DBMV_Mark.AM, i);
            if (nextAM == -1)
                nextAM = Integer.MAX_VALUE;
            int nextVM = str.indexOf(DBMV_Mark.VM, i);
            if (nextVM == -1)
                nextVM = Integer.MAX_VALUE;
            int nextSM = str.indexOf(DBMV_Mark.SM, i);
            if (nextSM == -1)
                nextSM = Integer.MAX_VALUE;
            int j = Math.min(nextAM, Math.min(nextVM, nextSM));
            if (j == Integer.MAX_VALUE)
                j = str.length();

            String part1 = str.substring(0, i);
            String part2 = str.substring(j);
            result = part1 + newVal + part2;
        }

        return result;
    }
    
	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", "NAMEþADDRþPHONE", 1, 1, 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to replace.
	* @param	value		The multivalue position where you want to replace.
	* @param	subvalue	The subvalue position where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, String lstDicts, String field, int value, int subvalue)
    {
        String aux = "";

        int pos = GetDictPos(lstDicts, field);
        if (pos > -1)
        {
            aux = LkReplace(str, newVal, pos, value, subvalue);
        }

        return aux;
    }
    
	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", "NAMEþADDRþPHONE", 1, 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to replace.
	* @param	value		The multivalue position where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, String lstDicts, String field, int value)
    {
    	return LkReplace(str, newVal, lstDicts, field, value, 0);
    }
    
	/**
	* Text Function that replaces a field, value or subvalue from a dynamic array, returning the result.
    * <p>
	* Example:
    * <pre>
	* string result = MvOperations.LkReplace("CUSTOMER UPDATE 2þADDRESS 2þ444", "MYTEXT", "NAMEþADDRþPHONE", 1);
	* </pre>
	* @param	str			The string on which you are going to replace a value.
	* @param	newVal		New value that will be replaced in the indicated string.
	* @param	lstDicts	Dictionaries list on which the field specified argument will be searched.
	* @param	field		The dictionary name of the attribute where you want to replace.
	* @return				A new string with the replaced value.
	*/
    public static String LkReplace(String str, String newVal, String lstDicts, String field)
    {
    	return LkReplace(str, newVal, lstDicts, field, 0, 0);
    }
    
    private static int GetDictPos(String lstdicts, String field)
    {
        int pos = -1;
        if (!(lstdicts == null || lstdicts.length() == 0) && !(field == null || field.length() == 0))
        {
            String[] dicts = lstdicts.split(DBMV_Mark.AM_str, -1);

            for (int i = 0; i < dicts.length; i++)
            {
                if (dicts[i].toUpperCase() == field.toUpperCase())
                {
                    pos = i;
                    break;
                }
            }
        }
        return pos;
    }
}