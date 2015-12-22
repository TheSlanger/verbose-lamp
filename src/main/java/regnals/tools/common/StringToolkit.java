package regnals.tools.common;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slanger
 */
public final class StringToolkit
{

   private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private final static DateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
   private final static String COMMA = ",";
   private static final Logger LOGGER = Logger.getLogger(StringToolkit.class.getName());

   /**
    * Format the string by adding string arguments to required places({}).
    * @param string Input string
    * @param arg string argument
    * @return Formatted string
    */
   public static String format(String string, String arg)
   {
      return MessageFormat.format(string, arg);
   }

   /**
    * Format the string by adding string arguments to required places({}).
    * @param string Input string
    * @param arg1 string argument
    * @param arg2 string argument
    * @return Formatted string
    */
   public static String format(String string, String arg1, String arg2)
   {
      return MessageFormat.format(string, arg1, arg2);
   }

   /**
    * Format the string by adding string arguments to required places({}).
    * @param string Input string
    * @param args string arguments
    * @return Formatted string
    */
   public static String format(String string, Object... args)
   {
      return MessageFormat.format(string, args);
   }

   /**
    * Format the string by adding int argument to the required place({}).
    * @param string Input string
    * @param arg int arguments
    * @return Formated string
    */
   public static String format(String string, int arg)
   {
      return format(string, Integer.toString(arg));
   }

   /**
    * Format the string by adding long argument to the required place({}).
    * @param string arguments
    * @param arg long argument
    * @return Formated string
    */
   public static String format(String string, long arg)
   {
      return format(string, Long.toString(arg));
   }

   /**
    * Format the string by adding boolean argument to the required place({}).
    * @param string Input string
    * @param arg boolean argument
    * @return Formatted string
    */
   public static String format(String string, boolean arg)
   {
      return format(string, Boolean.toString(arg));
   }

   /**
    * Format the string by adding float argument to required place({}).
    * @param string Input string
    * @param c calender argument
    * @return Formated string
    */
   public static String format(String string, Calendar c)
   {
      return format(string, DATE_FORMAT.format(c.getTime()));
   }

   /**
    * Format the string by adding date argument to required place({})
    * @param string Input string
    * @param d date argument
    * @return Formated string
    */
   public static String format(String string, Date d)
   {
      return format(string, DATE_FORMAT.format(d));
   }

   /**
    * Format the string by adding date argument to required place({})
    * @param string Input string
    * @param d date argument
    * @param dateOnly
    * @return Formated string
    */
   public static String format(String string, Date d, boolean dateOnly)
   {
      if (dateOnly)
      {
         return format(string, DATE_ONLY_FORMAT.format(d));
      }
      return format(string, DATE_FORMAT.format(d));
   }

//   /**
//    * Format the string by adding object type array argument to required places({}).
//    * @param string Input string
//    * @param args object array argument
//    * @return Formated string
//    */
//   public static String format(String string, Object[] args)
//   {
//      return MessageFormat.format(string, args);
//   }
   /**
    * check whether the string is null or empty
    * @param string input
    * @return true if the string is null or empty
    */
   public static boolean isNullOrEmpty(String string)
   {
      try
      {
         if (string == null)
         {
            return true;
         }
         string = string.trim();
         if (string.isEmpty())
         {
            return true;
         }
         if (string.equalsIgnoreCase(""))
         {
            return true;
         }
         if (string.length() == 0)
         {
            return true;
         }
      }
      catch (Exception ex)
      {
         log(ex);
         return true;
      }
      return false;
   }

   /**
    * Check whether the given object is null or empty
    * @param objString string object
    * @return Formated string
    */
   public static boolean isNullOrEmpty(Object objString)
   {
      try
      {
         if (objString == null)
         {
            return true;
         }
         String string = objString.toString();
         if (string == null)
         {
            return true;
         }
         string = string.trim();
         if (string.isEmpty())
         {
            return true;
         }
         if (string.equalsIgnoreCase(""))
         {
            return true;
         }
         if (string.length() == 0)
         {
            return true;
         }
      }
      catch (Exception ex)
      {
         log(ex);
         return true;
      }
      return false;
   }

   /**
    * Convert the given string to initcap string.
    * @param value input string
    * @return Initcap string
    */
   public static String getInitCapString(String value)
   {
      String temp = value.toLowerCase();
      temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
      return temp;
   }

   /**
    * Check whether the string is an integer.
    * @param input input string
    * @return return true if the string is integer
    */
   public static boolean isInteger(String input)
   {
      try
      {
         Integer.parseInt(input);
         return true;
      }
      catch (Exception ex)
      {
         log(ex);
         return false;
      }
   }

   /**
    * Removes non-numeric characters from a string
    * @param with - String with non-numerics
    * @return String without non-numerics
    */
   public static String removeNonNumerics(String with)
   {
      return with.replaceAll( "[^\\d]", "" );
   }

   /**
    * Adds a newline character to the string buffer.
    * @param buffer string buffer
    */
   public static void addNewLine(StringBuilder buffer)
   {
      buffer.append('\n');
   }

   /**
    * Search a given text from an array and returns the array element index.
    * @param array array string array
    * @param string string search key
    * @return array element index
    */
   public static int findInArray(String[] array, String string)
   {
      return findInArray(array, string, false);
   }

   /**
    * Search(Case sensitive) a given text from an array and returns the array element index.
    * @param array string array
    * @param string search key
    * @param ignoreCase set true to ignore case
    * @return array element index
    */
   public static int findInArray(String[] array, String string, boolean ignoreCase)
   {
      try
      {
         if (array == null)
         {
            return -1;
         }
         for (int i = 0; i < array.length; i++)
         {
            String string1 = array[i];
            if (string1 == null)
            {
               continue;
            }
            if (ignoreCase)
            {
               if (string1.equalsIgnoreCase(string))
               {
                  return i;
               }
            }
            if (string1.equals(string))
            {
               return i;
            }
         }
         return -1;
      }
      catch (Exception ex)
      {
         log(ex);
         return -1;
      }
   }

   /**
    * Search a given text from an array and returns the existense
    * @param array array string array
    * @param string string search key
    * @return if the element exists in the array
    */
   public static boolean inArray(String[] array, String string)
   {
      return inArray(array, string, false);
   }

   /**
    * Search(Case sensitive) a given text from an array and returns the existense.
    * @param array string array
    * @param string search key
    * @param ignoreCase set true to ignore case
    * @return if the element exists in the array
    */
   public static boolean inArray(String[] array, String string, boolean ignoreCase)
   {
      return findInArray(array, string, ignoreCase) >= 0;
   }

   /**
    * Split given String by "COMMA" and return list of values.
    * @param string String
    * @return ArrayList<String>
    * @throws java.lang.Exception
    */
   public static ArrayList<String> toList(String string) throws Exception
   {
      return toList(string, COMMA);
   }

   /**
    * Splits given String by given separator.
    * @param string String to split
    * @param sep String separator to split String.
    * @return ArrayList<String>
    * @throws java.lang.Exception
    */
   public static ArrayList<String> toList(String string, String sep) throws Exception
   {
      ArrayList<String> list = new ArrayList<String>();
      if (string == null)
      {
         return list;
      }
      String[] array = string.split(sep);
      for (int i = 0; i < array.length; i++)
      {
         String s = array[i];
         list.add(s);
      }
      return list;
   }

   /**
    *
    * @param list ArrayList<String> list
    * @return String
    * @throws java.lang.Exception
    */
   public static String toString(ArrayList<String> list) throws Exception
   {
      return toString(list, COMMA);
   }

   /**
    * Create a String containing all list values and appends given String sep to end.
    * @param list ArrayList<String>
    * @param sep String
    * @return String
    * @throws java.lang.Exception
    */
   public static String toString(ArrayList<String> list, String sep) throws Exception
   {
      StringBuilder buf = new StringBuilder();
      for (int i = 0; i < list.size(); i++)
      {
         if (buf.length() > 0)
         {
            buf.append(sep);
         }
         String string = list.get(i);
         buf.append(string);
      }
      return buf.toString();
   }

   /**
    *
    * @param array [String] list
    * @return String
    */
   public static String toString(String[] array)
   {
      return toString(array, COMMA);
   }

   /**
    * Create a String containing all list values and appends given String sep to end.
    * @param array String[]
    * @param sep String
    * @return String
    */
   public static String toString(String[] array, String sep)
   {
      StringBuilder buf = new StringBuilder();
      for (int i = 0; i < array.length; i++)
      {
         if (buf.length() > 0)
         {
            buf.append(sep);
         }
         String string = array[i];
         buf.append(string);
      }
      return buf.toString();
   }

   /**
    * Splits a string into whitespace separated elements
    * @param string String to split
    * @return Array of splitted string elements
    * @throws Exception
    */
   public static String[] whitespaceSplit(String string) throws Exception
   {
      String[] array = string.split("\\s");
      ArrayList<String> list = new ArrayList<String>();
      for (int i = 0; i < array.length; i++)
      {
         String element = array[i].trim();
         if (element.length() > 0)
         {
            list.add(element);
         }
      }
      array = list.toArray(array);
      return array;
   }

   /**
    * Sorrounds and returns given String with single quotes
    * @param string String to quote.
    * @return String single quoted string
    */
   public static String quoteSingle(String string)
   {
      return format("{0}{1}{0}", "\'", string);
   }

   /**
    * Sorrounds and returns given String with double quotes
    * @param string String to quote.
    * @return String double quoted String.
    */
   public static String quoteDouble(String string)
   {
      return format("{0}{1}{0}", "\"", string);
   }

   /**
    * Compiles wildcard string to regular expression.
    * @param wildcard String value composed of wildcard characters.
    * @return String regularexpression.
    */
   public static String wildcardToRegex(String wildcard)
   {
      StringBuilder s = new StringBuilder(wildcard.length());
      s.append('^');
      for (int i = 0, is = wildcard.length(); i < is; i++)
      {
         char c = wildcard.charAt(i);
         switch (c)
         {
            case '*':
               s.append(".*");
               break;
            case '?':
               s.append(".");
               break;
            // escape special regexp-characters
            case '(':
            case ')':
            case '[':
            case ']':
            case '$':
            case '^':
            case '.':
            case '{':
            case '}':
            case '|':
            case '\\':
               s.append("\\");
               s.append(c);
               break;
            default:
               s.append(c);
               break;
         }
      }
      s.append('$');
      return (s.toString());
   }

   /**
    * Use this method to remove duplicate elements of String arrayList.
    * @param arrayList ArrayList<String> to remove duplicates.
    * @return ArrayList<String> after removing duplicates
    */
   public static ArrayList<String> removeDuplicateWithOrder(ArrayList<String> arrayList)
   {
      try
      {
         Set<String> set = new HashSet<String>();
         List<String> newList = new ArrayList<String>();
         for (Iterator iter = arrayList.iterator(); iter.hasNext();)
         {
            Object element = iter.next();
            if (set.add(element.toString()))
            {
               newList.add(element.toString());
            }

         }
         return (ArrayList<String>) newList;
      }
      catch (Exception ex)
      {
         log(ex);
         return arrayList;
      }
   }

   /**
    * Loads properties from a String value into a Properties object
    * @param propVal String value
    * @return Properties object
    */
   public static Properties getProperties(String propVal) throws IOException
   {
      Properties props = new Properties();
      if (propVal != null)
      {
         Reader reader = new StringReader(propVal);
         props.load(reader);
      }
      return props;
   }

   private static void log(Exception ex)
   {
      LOGGER.log(Level.SEVERE, null, ex);
   }
}
