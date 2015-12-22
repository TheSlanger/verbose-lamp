package regnals.tools.ui;

/**
 *
 * @author slanger
 * @since 2010-09-01
 */
public class DialogUtility {

    // Option values
    public static final int DEFAULT_OPTION = javax.swing.JOptionPane.DEFAULT_OPTION;
    public static final int YES_NO_OPTION = javax.swing.JOptionPane.YES_NO_OPTION;
    public static final int YES_NO_CANCEL_OPTION = javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
    public static final int OK_CANCEL_OPTION = javax.swing.JOptionPane.OK_CANCEL_OPTION;
    // Return values
    public static final int YES_OPTION = javax.swing.JOptionPane.YES_OPTION;
    public static final int NO_OPTION = javax.swing.JOptionPane.NO_OPTION;
    public static final int CANCEL_OPTION = javax.swing.JOptionPane.CANCEL_OPTION;
    public static final int OK_OPTION = javax.swing.JOptionPane.OK_OPTION;
    public static final int CLOSED_OPTION = javax.swing.JOptionPane.CLOSED_OPTION;
    // Message values
    public static final int ERROR_MESSAGE = javax.swing.JOptionPane.ERROR_MESSAGE;
    public static final int INFORMATION_MESSAGE = javax.swing.JOptionPane.INFORMATION_MESSAGE;
    public static final int WARNING_MESSAGE = javax.swing.JOptionPane.WARNING_MESSAGE;
    public static final int QUESTION_MESSAGE = javax.swing.JOptionPane.QUESTION_MESSAGE;
    public static final int PLAIN_MESSAGE = javax.swing.JOptionPane.PLAIN_MESSAGE;

    /**
     * Shows information messege.
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     */
    public static void showInformationMessege(String message, String title, java.awt.Component parent) {
        if (parent != null) {
            javax.swing.JOptionPane.showMessageDialog(parent, message, title, INFORMATION_MESSAGE);
        }

    }

//   /**
//    * Shows information messege.
//    * @param message message text to be displayed.
//    * @param title title test of messege box.
//    * @param parent parent component of message box.
//    */
//   public static void showInformationMessage(java.awt.Frame parent, String message, String title)
//   {
//      if (parent != null)
//      {
//         InformationDialog dlg = new InformationDialog(parent, message, title);
//         dlg.setVisible(true);
//      }
//
//   }
//   /**
//    * Shows information messege.
//    * @param message message text to be displayed.
//    * @param title title test of messege box.
//    * @param parent parent component of message box.
//    */
//   public static void showInformationMessage(javax.swing.JDialog parent, String message, String title)
//   {
//      if (parent != null)
//      {
//         InformationDialog dlg = new InformationDialog(parent, message, title);
//         dlg.setVisible(true);
//      }
//
//   }
    /**
     * Shows warning message.
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     */
    public static void showWarningMessege(String message, String title, java.awt.Component parent) {
        if (parent != null) {
            javax.swing.JOptionPane.showMessageDialog(parent, message, title, WARNING_MESSAGE);
        }

    }

    /**
     * Shows error messege.
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     */
    public static void showErrorMessege(String message, String title, java.awt.Component parent) {
        javax.swing.JOptionPane.showMessageDialog(parent, message, title, ERROR_MESSAGE);
    }

    /**
     * Shows question message with options yes/no/cancel
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     * @return int respond from messege box.
     */
    public static int showYesNoCancelDialog(String message, String title, java.awt.Component parent) {
        if (parent != null) {
            return javax.swing.JOptionPane.showConfirmDialog(parent, message, title, YES_NO_CANCEL_OPTION, QUESTION_MESSAGE);
        } else {
            return CANCEL_OPTION;
        }

    }

    /**
     * Shows question message with options yes/no
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     * @return int respond from messege box.
     */
    public static int showYesNoDialog(String message, String title, java.awt.Component parent) {
        if (parent != null) {
            return javax.swing.JOptionPane.showConfirmDialog(parent, message, title, YES_NO_OPTION, QUESTION_MESSAGE);
        } else {
            return CANCEL_OPTION;
        }

    }

    /**
     * Shows information message with options ok/cancel.
     *
     * @param message message text to be displayed.
     * @param title title test of messege box.
     * @param parent parent component of message box.
     * @return int respond from messege box.
     */
    public static int showOkCancelDialog(String message, String title, java.awt.Component parent) {
        if (parent != null) {
            return javax.swing.JOptionPane.showConfirmDialog(parent, message, title, OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        } else {
            return CANCEL_OPTION;
        }
    }

    /**
     * Shows input dialog with options yes/no
     *
     * @param message message text to be displayed.
     * @param title title of messege box.
     * @param parent parent component of message box.
     * @return String respond from messege box.
     */
    public static String showInputDialog(String message, String title, java.awt.Component parent) {
        return javax.swing.JOptionPane.showInputDialog(parent, message, title, OK_CANCEL_OPTION);
    }

    /**
     * Shows input dialog with options yes/no
     *
     * @param message message text to be displayed.
     * @param title title of message box.
     * @param defaultValue default value
     * @param parent parent component of message box.
     * @return String respond from message box.
     */
    public static String showInputDialog(String message, String title, String defaultValue, java.awt.Component parent) {
        return (String)javax.swing.JOptionPane.showInputDialog(parent, message, title, OK_CANCEL_OPTION, null, null, defaultValue);
    }

//   /**
//    * Show an input dialog and returns the result
//    * @param message
//    * @param title
//    * @param parent
//    * @return 
//    */
//   public static String showInputDialog(String message, String title, java.awt.Component parent)
//   {
//      return javax.swing.JOptionPane.showInputDialog(parent, message, title, OK_CANCEL_OPTION);
//   }
}
