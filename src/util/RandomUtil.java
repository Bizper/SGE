package util;

import java.util.Random;

public class RandomUtil {

    private static Random random = new Random();

    public static int getInt() {
        return random.nextInt();
    }

    public static int getInt(int bounds) {
        return random.nextInt(bounds);
    }

    public static boolean getBoolean() {
        return random.nextBoolean();
    }

}
