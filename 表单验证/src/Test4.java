import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {

    public static void main(String[] args) {

        String regex = "^\\d{0,5}?[a-z]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("22222sss");

        if(matcher.find()){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }

    }

}
