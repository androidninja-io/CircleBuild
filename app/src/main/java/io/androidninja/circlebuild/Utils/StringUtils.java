package io.androidninja.circlebuild.Utils;

public class StringUtils {

    /**
     * Checks if the String is either empty or null
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return (string == null || string.length() == 0);
    }

}
