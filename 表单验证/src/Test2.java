import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args) {
        String regex = "^\\d{17}[\\dxX]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("430321199501263256");
        if(matcher.find()){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }

    }


}

