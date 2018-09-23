package top.jaylin.mvparch;

import android.text.TextUtils;
import android.util.Log;


/**
 * a convenient log class
 * <p>
 * <p>
 * format is:{class:method:lineNumber}
 */
public class MyLog {
    public static String  tagPrefix = "MyLog";//log前缀
    public static boolean debug     = BuildConfig.DEBUG;
    public static int     INDEX     = 5;

    public static void d(Object o, int index) {
        logger("d", o, index);
    }

    public static void e(Object o, int index) {
        logger("e", o, index);
    }

    public static void i(Object o, int index) {
        logger("i", o, index);
    }


    public static void w(Object o) {
        logger("w", o, INDEX);
    }

    public static void d(Object o) {
        logger("d", o, INDEX);
    }

    public static void e(Object o) {
        logger("e", o, INDEX);
    }

    public static void i(Object o) {
        logger("i", o, INDEX);
    }


    public static void w(Object o, int index) {
        logger("w", o, index);
    }

    /**
     * @param type logger level
     * @param o    logger content
     */
    private static void logger(String type, Object o, int index) {
        if (!debug) {
            return;
        }
        String msg        = o + "";
        String threadName = Thread.currentThread().getName();
        String tag        = getTag(getCallerStackTraceElement(index)) + ":" + threadName;
        switch (type) {
            case "i":
                Log.i(tag, msg);
                break;
            case "d":
                Log.d(tag, msg);
                break;
            case "e":
                Log.e(tag, msg);
                break;
            case "w":
                Log.w(tag, msg);
                break;
        }
    }


    private static String getTag(StackTraceElement element) {
        try {
            String tag             = "%s.%s(Line:%d)"; // format pattern
            String callerClazzName = element.getClassName(); // obtain class name
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
            tag = String.format(tag, callerClazzName, element.getMethodName(), element.getLineNumber()); // format
            tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
            return tag;
        } catch (Exception e) {
            return tagPrefix;
        }
    }

    /**
     * obtain thread state
     *
     * @return
     */
    private static StackTraceElement getCallerStackTraceElement(int index) {
        if (index >= Thread.currentThread().getStackTrace().length) {
            index = Thread.currentThread().getStackTrace().length - 1;
        }
        return Thread.currentThread().getStackTrace()[index];
    }
}