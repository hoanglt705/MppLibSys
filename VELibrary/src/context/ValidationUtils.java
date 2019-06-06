package context;

/**
 * Created by haupham on 6/6/19.
 */
public class ValidationUtils {


    public static boolean isValidText(String s) {
        String regex = "[A-Za-z\\s]+";
        return s.matches(regex);

    }

    public static boolean isValidPhoneNo(String s){
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        return s.matches(regex);
    }

    public static boolean isNumberOnly(String s){
        String regex = "[0-9]+";
        return s.matches(regex);
    }
}
