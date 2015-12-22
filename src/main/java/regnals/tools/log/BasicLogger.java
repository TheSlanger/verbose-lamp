package regnals.tools.log;

import regnals.tools.common.Constants;
import java.awt.Color;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import regnals.tools.common.StringToolkit;
import regnals.tools.io.FileUtility;
import regnals.tools.io.Output;
import regnals.tools.ui.DialogUtility;

/**
 *
 * @author slanger
 */
public abstract class BasicLogger implements Constants {

    private final static int LOG_LIMIT = 500000;
    private static String PROMPT = "[default] {0}";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
    private final static DateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    private static JEditorPane console;
    private static AttributeSet defaultAttributeSet;
    private static int fontSize = 12;

    private static AttributeSet getDefaultAttributeSet() {
        if (defaultAttributeSet == null) {
            defaultAttributeSet = StyleContext.getDefaultStyleContext().addAttribute(
                    SimpleAttributeSet.EMPTY,
                    StyleConstants.FontFamily,
                    "Monospaced");
        }

        defaultAttributeSet = StyleContext.getDefaultStyleContext().addAttribute(
                defaultAttributeSet,
                StyleConstants.FontSize,
                fontSize);

        return defaultAttributeSet;
    }

    /**
     * Set font size of the console.
     *
     * @param value int
     */
    public static void setFontSize(int value) {
        fontSize = value;
    }

    /**
     * returns font size.
     *
     * @return int fontSize.
     */
    public static int getFontSize() {
        return fontSize;
    }

    /**
     * Set the JEditorPane object to the BasicLogger
     *
     * @param console - JEditorPane object
     */
    public static void setConsole(JEditorPane console) {
        BasicLogger.console = console;
    }

    /**
     * Get the JEditorPane object from the BasicLogger
     *
     * @return JEditorPane object
     */
    public static JEditorPane getConsole() {
        return console;
    }

    /**
     * Return the current date and time
     *
     * @return thecurrent date and time.
     */
    public static String getDateTime() {
        Date date = new Date();
        return DATE_FORMAT.format(date);
    }

    /**
     * Return the current date and time in log format
     *
     * @return thecurrent date and time.
     */
    public static String getLogDateTime() {
        Date date = new Date();
        return LOG_DATE_FORMAT.format(date);
    }

    /**
     * Get the prompt string from the BasicLogger
     *
     * @return prompt string
     */
    protected static String getPrompt() {
        return PROMPT;
    }

    /**
     * Set the prompt string to the BasicLogger
     *
     * @param prompt - prompt string
     */
    public static void setPrompt(String prompt) {
        PROMPT = prompt;
    }

    /**
     * Print given set of message to the console of the application using given
     * text colour.
     *
     * @param output - Output object containing set of output messages
     * @param textColor - Output text colour
     */
    public static void appendOutput(final Output output, Color textColor) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < output.output.size(); i++) {
            String string = output.output.get(i);
            if (out.length() > 0) {
                out.append(NEW_LINE);
            }
            out.append(string);
        }
        appendOut(out.toString(), textColor);

        StringBuilder err = new StringBuilder();
        for (int i = 0; i < output.error.size(); i++) {
            String string = output.error.get(i);
            if (err.length() > 0) {
                err.append(NEW_LINE);
            }
            err.append(string);
        }
        appendErr(err.toString(), textColor);
    }

    /**
     * Print given message to the console of the application using given text
     * colour.
     *
     * @param message - Output message
     * @param textColor - Output text colour
     */
    protected static void appendOut(final String message, Color textColor) {
        if (textColor == null) {
            textColor = getForeground();
        }
        appendToConsole(message, textColor);
    }

    /**
     * Print given error message to the console of the application using given
     * text colour. If the textColor is null it take RED as default.
     *
     * @param message - Output custom error message
     * @param textColor - Outpot text colour
     */
    protected static void appendErr(final String message, Color textColor) {
        if (textColor == null) {
            textColor = Color.RED;
        }
        appendToConsole(message, textColor);
    }

    /**
     * Print given log message to the console of the application using given
     * text colour.
     *
     * @param message - Output log message
     * @param textColor - Output colour
     */
    public static void appendLog(final String message, Color textColor) {
        if (textColor == null) {
            textColor = getForeground();
        }
        appendToConsole(message, textColor);
    }

    /**
     * Print given error message to the console of the application using given
     * text colour. If the textColor is null it take RED as default.
     *
     * @param ex - Exception
     * @param textColor - Output text colour
     */
    public static void appendError(final Exception ex, Color textColor) {
        if (textColor == null) {
            textColor = Color.RED;
        }
        appendLog(" Error: {0}", ex.getMessage(), textColor);
    }

    /**
     * Print given error message to the console of the application using given
     * text colour. If the textColor is null it take RED as default.
     *
     * @param t - Throwable
     * @param textColor - Output text colour
     */
    public static void appendError(final Throwable t, Color textColor) {
        if (textColor == null) {
            textColor = Color.RED;
        }
        appendLog(" Error: {0}", t.getMessage(), textColor);
    }

    /**
     * Print given log message to the console of the application using given
     * text colour.
     *
     * @param message - Output message
     * @param arg - Arguments containing aditional runtime strings
     * @param textColor - Output text colour
     */
    public static void appendLog(final String message, final String arg, Color textColor) {
        if (textColor == null) {
            textColor = getForeground();
        }
        appendToConsole(StringToolkit.format(message, arg), textColor);
    }

    /**
     * Print given log message to the console of the application using given
     * text colour.
     *
     * @param message - Output message
     * @param arg1 - Arguments containing aditional runtime strings
     * @param arg2 - Arguments containing aditional runtime strings
     * @param textColor - Output text colour
     */
    public static void appendLog(final String message, final String arg1, final String arg2, Color textColor) {
        if (textColor == null) {
            textColor = getForeground();
        }
        appendToConsole(StringToolkit.format(message, arg1, arg2), textColor);
    }

    /**
     * Print given log message to the console of the application using given
     * text colour.
     *
     * @param message - Outpot text message
     * @param args - Arguments array containing aditional runtime strings
     * @param textColor - Outpot text colours
     */
    public static void appendLog(final String message, final Object[] args, Color textColor) {
        if (textColor == null) {
            textColor = getForeground();
        }
        appendToConsole(StringToolkit.format(message, args), textColor);
    }

    /**
     * Print given message to the console of the application using given text
     * colour.
     *
     * @param text - Outpot text message
     * @param textColor - Text colour
     */
    public static void appendToConsole(final String text, Color textColor) {
        if (StringToolkit.isNullOrEmpty(text)) {
            return;
        }
        final Color colorOnText;
        if (textColor == null) {
            colorOnText = getForeground();
        } else {
            colorOnText = textColor;
        }
        final String message = StringToolkit.format(getPrompt(), text, BasicLogger.getDateTime());
        if (BasicLogger.getConsole() != null) {
            SwingUtilities.invokeLater(() -> {
                try {
                    AttributeSet aset = StyleContext.getDefaultStyleContext().addAttribute(
                            BasicLogger.getDefaultAttributeSet(), StyleConstants.Foreground, colorOnText);
                    int len = BasicLogger.getConsole().getDocument().getLength();
                    if (len >= LOG_LIMIT) {
                        len = writeLogToDisc();
                    }
                    BasicLogger.getConsole().getDocument().insertString(len, message + NEW_LINE, aset);
                    BasicLogger.getConsole().setCaretPosition(len);
                } catch (BadLocationException ex) {
                    Logger.getLogger(BasicLogger.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } else {
            System.out.println(message);
        }
    }

    /**
     * Writes console log to a file. Log file will be created in users home
     * directory.
     *
     * @return int Length of the document.
     */
    public static int writeLogToDisc() {
        if (getConsole() == null) {
            return -1;
        }

        // set path to log file
        File path = new File(Constants.LOG_DIR);
        path = new File(path, getLogDateTime() + ".log");

        // get text from console
        StringBuilder logBuilder;
        try {
            logBuilder = new StringBuilder(getConsole().getDocument().getText(0, getConsole().getDocument().getLength()));

            // write console to log file
            FileUtility.writeToFile(path, logBuilder);

            // reset console
            getConsole().setText("");

            // write log message and restore prompt
            String prompt = getPrompt();
            setPrompt(PROMPT);
            Color c = null;
            appendLog("log written to \"{0}\"", path.getAbsolutePath(), c);
            setPrompt(prompt);
        } catch (BadLocationException ex) {
            Logger.getLogger(BasicLogger.class.getName()).log(Level.SEVERE, null, ex);
        }

        // reset and return length variable
        return BasicLogger.getConsole().getDocument().getLength();
    }

    /**
     * Purges all log files from logs directory
     *
     * @param parent
     */
    public static void purgeLogFiles(java.awt.Component parent) {
        if (DialogUtility.showYesNoDialog("This will erase all log files from the log directory.\nDo you want to proceed?", "Delete log files", parent) == DialogUtility.YES_OPTION) {
            File path = new File(Constants.LOG_DIR);
            File[] files = path.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    private static Color getForeground() {
        if (BasicLogger.getConsole() != null) {
            return BasicLogger.getConsole().getForeground();
        } else {
            return Color.BLACK;
        }
    }
}
