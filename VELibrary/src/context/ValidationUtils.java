package context;

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

    public static boolean isZipcode(String s){
        String regex = "[^\\d](\\d{5})[^\\d]";
        return s.matches(regex);
    }

    public static void main(String[] args) {
        System.out.println(isNumberOnly("hjkjhdsad"));
        System.out.println(isNumberOnly("12387 089080"));
        System.out.println(isValidPhoneNo("9309990934830"));
        System.out.println(isValidPhoneNo("7633393363"));
        System.out.println(isValidPhoneNo("(763)339-3363"));
        System.out.println(isValidText("s424 3 ##%%%* sad"));
        System.out.println(isValidText("s424 sad"));
        System.out.println(isZipcode("323"));
        System.out.println(isZipcode("asdad"));
        System.out.println(isZipcode("52557"));
        System.out.println(isZipcode("3343243"));

    }
}
