package util;

public class CallCheck {

    static Log log = Log.getInstance(CallCheck.class);

    /**
     * 检查调用者相关信息。
     */
    static void checkCaller() {
        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
        String className = elements[3].getClassName();
        log.debug("唤起" + elements[2].getClassName() + "工具的模块为: " + className);
    }

}
