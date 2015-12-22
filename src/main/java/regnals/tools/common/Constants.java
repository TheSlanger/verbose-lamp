package regnals.tools.common;

import static regnals.tools.common.StringToolkit.*;
import java.awt.Color;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author fremse
 * @since 2010-06-03
 */
public interface Constants {

    // <editor-fold defaultstate="collapsed" desc="System properties">
    Properties system = System.getProperties();
    String SYSTEM_FILE_SEPARATOR = system.getProperty("file.separator");
    String SYSTEM_JAVA_HOME = system.getProperty("java.home");
    String SYSTEM_JAVA_VENDOR = system.getProperty("java.vendor");
    String SYSTEM_JAVA_VENDOR_URL = system.getProperty("java.vendor.url");
    String SYSTEM_JAVA_VERSION = system.getProperty("java.version");
    String SYSTEM_LINE_SEPARATOR = system.getProperty("line.separator");
    String SYSTEM_OS_ARCH = system.getProperty("os.arch");
    String SYSTEM_OS_NAME = system.getProperty("os.name");
    String SYSTEM_OS_VERSION = system.getProperty("os.version");
    String SYSTEM_PATH_SEPARATOR = system.getProperty("path.separator");
    String SYSTEM_USER_DIR = system.getProperty("user.dir");
    String SYSTEM_USER_NAME = system.getProperty("user.name");
    String SYSTEM_USER_HOME = Tools.getUserHome();
    /**
     * The home folder, used for settings storage and temporary files
     */
    String IFS_DIR = "ifs";
    String HOME_DIR = "svn";
    /**
     * The home folder File object, used for settings storage and temporary
     * files
     */
    String HOME_PATH = format("{0}/{1}/{2}", SYSTEM_USER_HOME, IFS_DIR, HOME_DIR);
    File HOME_FOLDER = new File(HOME_PATH);
    String HOME_PATH_OLD = format("{0}/{1}", SYSTEM_USER_HOME, HOME_DIR);
    File HOME_FOLDER_OLD = new File(HOME_PATH_OLD);
    String DEFAULT_WORKING_FOLDER = "C:\\Projects";
    String DEFAULT_TEMP_FOLDER = format("{0}/temp", HOME_PATH);
    String RECENT_COMMENTS_FILE_NAME = "recentcomments";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="System folders">
    String EXPORT_DIR = format("{0}/export", HOME_PATH);
    String LOG_DIR = format("{0}/logs", HOME_PATH);
    String AUTHZ_DIR = format("{0}/authz", HOME_PATH);
    // <editor-fold defaultstate="collapsed" desc="Profiles">
    String PROFILES_BASE_DIR = format("{0}/profiles", HOME_PATH);
    String PROFILES_BASE_DIR_OLD = format("{0}/profiles", HOME_PATH_OLD);
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Separators">
    String DOUBLE_QUOTE = "\"";
    String SINGLE_QUOTE = "\'";
    String HAT = "^";
    String SEMI_COLON = ";";
    String COLON = ":";
    String FORWARD_SLASH = "/";
    String BACK_SLASH = "\\";
    String ROOT_PATH = "/";
    String WHITE_SPACE = "\\s";
    String NEW_LINE = "\n";
    String TAB = "\t";
    String CARRIAGE_RETURN = "\r";
    String UNDERSCORE = "_";
    String DASH = "-";
    String ROW_SEPARATOR = CARRIAGE_RETURN + NEW_LINE;
    String TEXT_SEPERATOR = " ";
    String INFO_SEPERATOR = " : ";
    String HYPHEN = " - ";
    String DOT = ".";
    String COMMA = ",";
    String EOL_STYLE_WINDOWS = "CRLF";
    String EOL_STYLE_UNIX = "LF";
    String EOL_STYLE_MAC = "CR";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Colors">
    //Console message colours
    final Color DEFAULT_COLOR = Color.BLUE;
    final Color ERROR_COLOR = Color.RED;
    //Update actions
    final Color UPDATE_ADD_COLOR = Color.GREEN.darker();
    final Color UPDATE_COMPLETED_COLOR = Color.BLACK;
    final Color UPDATE_DELETE_COLOR = Color.MAGENTA.darker();
    final Color UPDATE_EXISTS_COLOR = DEFAULT_COLOR;
    final Color UPDATE_EXTERNAL_COLOR = DEFAULT_COLOR;
    final Color UPDATE_REPLACE_COLOR = Color.MAGENTA;
    final Color UPDATE_UPDATE_COLOR = DEFAULT_COLOR;
    //Commit actions.
    final Color COMMIT_ADDED_COLOR = Color.GREEN.darker();
    final Color COMMIT_COMPLETED_COLOR = Color.BLACK;
    final Color COMMIT_DELETED_COLOR = Color.MAGENTA.darker();
    final Color COMMIT_DELTA_SENT_COLOR = DEFAULT_COLOR;
    final Color COMMIT_MODIFIED_COLOR = DEFAULT_COLOR;
    final Color COMMIT_REPLACED_COLOR = Color.MAGENTA;
    //other wc operations
    final Color STATUS_COMPLETED_COLOR = Color.BLACK;
    final Color STATUS_EXTERNAL_COLOR = DEFAULT_COLOR;
    final Color MERGE_BEGIN_COLOR = Color.BLACK;
    final Color MERGE_COMPLETE_COLOR = Color.BLACK;
    final Color RESOLVED_COLOR = DEFAULT_COLOR;
    final Color RESTORE_COLOR = DEFAULT_COLOR;
    final Color COPY_COLOR = DEFAULT_COLOR;
    final Color DELETE_COLOR = Color.MAGENTA.darker();
    final Color REVERT_COLOR = DEFAULT_COLOR.darker();
    final Color ADD_COLOR = Color.GREEN.darker();
    final Color LOCKED_COLOR = DEFAULT_COLOR;
    final Color LOCK_FAILED_COLOR = DEFAULT_COLOR;
    final Color UNLOCKED_COLOR = DEFAULT_COLOR;
    final Color UNLOCK_FAILED_COLOR = DEFAULT_COLOR;
    final Color CONFLICTED_COLOR = Color.RED.brighter();
    final Color TREE_CONFLICTED_COLOR = new Color(255, 203, 0);
    final Color ERROR_MESSAGE_COLOR = Color.RED;
    final Color COLOR_BLACK = Color.BLACK;
    final Color WARNING_MESSAGE_COLOR = Color.MAGENTA;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Defaults">
    String EMPTY_STRING = "";
    String STRING_TRUE = "TRUE";
    String STRING_FALSE = "FALSE";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Misc">
    // Deployment information file
    String DEPLOYMENT_INFO = "deploy.ini";
    // NetBeans
    String NBPROJECT_DIR = "nbproject";
    String NBPROJECT_ZIP_LOCAL = "resources\\admin\\nbproject.zip";
    // Deleted files
    String DELETED_FILES_LIST = "deleted-files.txt";
    // SwingWorker done
    String SWING_WORKER_DONE = "Done";
    // SwingWorker aborted
    String SWING_WORKER_ABORTED = "Aborted";
    // </editor-fold>

    static class Tools {

        private static String getUserHome() {
            Properties system = System.getProperties();
            String userhome = system.getProperty("user.home");
            String username = system.getProperty("user.name");
            String osName = system.getProperty("os.name");
            String WINDOWS7 = "Windows 7";

            CharSequence username_ = username.toLowerCase().subSequence(0, username.length());
            if (userhome.toLowerCase().contains(username_)) {
                return userhome;
            } else {
                java.io.File dir = new java.io.File(userhome);
                if (osName.equalsIgnoreCase(WINDOWS7)) {
                    dir = new java.io.File(dir, format("Users\\{0}", username));
                } else {
                    dir = new java.io.File(dir, format("Documents and Settings\\{0}", username));
                }
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                return dir.getAbsolutePath();
            }
        }
    }
}
