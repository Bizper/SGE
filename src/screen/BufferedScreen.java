package screen;

public class BufferedScreen {

    private static boolean isModified = false;

    private static int size = 2048;

    private static char text[] = new char[size];

    public static void setBufferSize(int size) {
        text = null;
        text = new char[size];
        BufferedScreen.size = size;
        System.gc();
    }

    public static String write(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        if(str.length() > size) {
            setBufferSize(str.length());
        }
        text = str.toCharArray();
        isModified = true;
        return str;
    }

    public static boolean isChange() {
        return isModified ;
    }

    public static String get() {
        if(text == null) return "";
        isModified = false;
        return new String(text);
    }

    public static void clear() {
        text = null;
    }

}