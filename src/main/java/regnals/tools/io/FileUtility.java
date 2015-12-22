package regnals.tools.io;

import regnals.tools.common.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author slanger
 * @since 2009-08-31
 */
public class FileUtility implements Constants
{

   /**
    * Checks the existence of a file/folder created from a string
    *
    * @param path
    * @return
    */
   public static boolean exists(String path)
   {
      File file = new File(path);
      boolean exists = exists(file);
      return exists;
   }

   /**
    * Checks the existence of a file/folder
    *
    * @param file
    * @return
    */
   public static boolean exists(File file)
   {
      boolean exists = file.exists();
      return exists;
   }

   /**
    *
    * @param file
    * @param lines
    */
   public static void readLinesFromFile(File file, ArrayList<String> lines)
   {
      try
      {
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         try
         {
            String string = br.readLine();
            while (string != null)
            {
               lines.add(string);
               string = br.readLine();
            }
            fr.close();
         }
         catch (IOException ex)
         {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      catch (FileNotFoundException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    *
    * @param file
    * @param lines
    */
   public static void writeLinesToFile(File file, ArrayList<String> lines)
   {
      writeLinesToFile(file, lines, false);
   }

   /**
    *
    * @param file
    * @param lines
    * @param append
    */
   public static void writeLinesToFile(File file, ArrayList<String> lines, boolean append)
   {
      try
      {
         if (!file.exists())
         {
            if (!file.getParentFile().exists())
            {
               file.getParentFile().mkdirs();
            }
            file.createNewFile();
         }
         FileWriter fw = new FileWriter(file, append);
         BufferedWriter bw = new BufferedWriter(fw);
         for (int i = 0; i < lines.size(); i++)
         {
            String string = lines.get(i);
            bw.write(string);
            bw.newLine();
            if (i / 100 == 0)
            {
               bw.flush();
            }
         }
         bw.flush();
         fw.close();
      }
      catch (IOException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    *
    * @param file
    * @param buffer
    */
   public static void readFromFile(File file, java.lang.StringBuilder buffer)
   {
      FileInputStream fis;
      try
      {
         fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         try
         {
            readFromStream(bis, buffer);
         }
         finally
         {
            fis.close();
         }
      }
      catch (FileNotFoundException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public static void readFromStream(BufferedInputStream bis, java.lang.StringBuilder buffer)
   {
      try
      {
         while (bis.available() > 0)
         {
            buffer.append((char) bis.read());
         }
      }
      catch (IOException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    *
    * @param file
    * @param builder
    */
   public static void writeToFile(File file, StringBuilder builder)
   {
      writeToFile(file, builder, false);
   }

   /**
    *
    * @param file
    * @param builder
    * @param append
    */
   public static void writeToFile(File file, StringBuilder builder, boolean append)
   {
      try
      {
         if (!file.exists())
         {
            if (!file.getParentFile().exists())
            {
               file.getParentFile().mkdirs();
            }
            file.createNewFile();
         }
         FileOutputStream fos = new FileOutputStream(file, append);
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         for (int i = 0; i < builder.length(); i++)
         {
            bos.write(builder.charAt(i));
         }
         bos.flush();
         fos.close();
      }
      catch (IOException ex)
      {
         Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Write tableMOdel data into a MS Excel file.
    *
    * @param file File
    * @param model TableModel
    * @throws IOException
    */
   public static void writeToExcelFile(File file, TableModel model) throws IOException
   {
      if (!file.exists())
      {
         if (!file.getParentFile().exists())
         {
            file.getParentFile().mkdirs();
         }
         file.createNewFile();
      }
      FileWriter out = new FileWriter(file);
      for (int i = 0; i < model.getColumnCount(); i++)
      {
         out.write(model.getColumnName(i) + TAB);
      }
      out.write(NEW_LINE);
      for (int i = 0; i < model.getRowCount(); i++)
      {
         for (int j = 0; j < model.getColumnCount(); j++)
         {
            Object obj = model.getValueAt(i, j);
            out.write(((obj != null) ? obj.toString() : "") + TAB);
         }
         out.write(NEW_LINE);
      }
      out.close();
   }

   /**
    * Copies the contents of srcDir to dstDir
    *
    * @param srcDir Source folder
    * @param dstDir Destination folder
    * @throws IOException
    */
   public static void copyFolder(File srcDir, File dstDir) throws IOException
   {
      File[] srcFiles = srcDir.listFiles();
      for (File file : srcFiles)
      {
         if (file.isDirectory())
         {
            File newDir = new File(dstDir, file.getName());
            if (!newDir.exists())
            {
               newDir.mkdirs();
            }
            copyFolder(file, newDir);
         }
         else
         {
            File newFile = new File(dstDir, file.getName());
            copy(file, newFile);
         }
      }
   }

   /**
    * Copies src file to dst file. If the dst file does not exist, it is created
    *
    * @param src
    * @param dst
    * @throws java.io.IOException
    */
   public static void copy(File src, File dst) throws IOException
   {
      InputStream in = new FileInputStream(src);
      OutputStream out = new FileOutputStream(dst);

      // Transfer bytes from in to out
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0)
      {
         out.write(buf, 0, len);
      }
      in.close();
      out.close();
   }

   /**
    * Deletes all files and subdirectories under dir. Returns true if all
    * deletions were successful. If a deletion fails, the method stops
    * attempting to delete and returns false.
    *
    * @param dir
    * @return true if all deletions were successful
    */
   public static boolean delete(File dir)
   {
      if (dir.isDirectory())
      {
         String[] children = dir.list();
         for (String children1 : children)
         {
            boolean success = delete(new File(dir, children1));
            if (!success)
            {
               return false;
            }
         }
      }

      // The directory is now empty so delete it
      return dir.delete();
   }

   /**
    * Delete can recreate given folder.
    *
    * @param dir
    */
   public static void cleanAndCreate(File dir)
   {
      if (dir.exists())
      {
         File[] listOfTmpFiles = dir.listFiles();
         for (File file : listOfTmpFiles)
         {
            file.delete();
         }
      }
      else
      {
         dir.mkdirs();
      }
   }

   /**
    * Utility method for deleting non empty directories. This will delete its
    * children and then directory.
    *
    * @param dir Directory path to be deleted.
    * @return boolean given directory successfully deleted or not.
    * @throws java.lang.Exception
    */
   public static boolean deleteDir(File dir) throws Exception
   {
      if (dir.isDirectory())
      {
         String[] children = dir.list();
         for (String children1 : children)
         {
            boolean success = deleteDir(new File(dir, children1));
            if (!success)
            {
               return false;
            }
         }
      }
      return dir.delete();
   }
}
