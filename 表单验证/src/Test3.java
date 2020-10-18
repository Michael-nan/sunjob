import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {

    public static void main(String[] args) {

        String regex = "\\w{0,5}+\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("aa222222");

        if(matcher.find()){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }

    }

}
