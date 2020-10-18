import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String regex = "^1[3456789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("17673245168");
        if(matcher.find()){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }

    }


}
