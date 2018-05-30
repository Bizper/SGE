package screen;

public class BufferedScreen {

    private static int size = 1024;

    private static char text[] = new char[size];

    public static void setBufferSize(int size) {
        text = null;
        text = new char[size];
        BufferedScreen.size = size;
    }

    public static String add(String str) {
        if(str.length() < size) text = str.toCharArray();
        return str;
    }

    public static String get() {
        return new String(text);
    }

    public static void clear() {
        text = null;
    }

}
