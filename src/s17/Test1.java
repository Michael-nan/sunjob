package s17;

import java.io.File;

public class Test1 {

    //递归
    public static void show(File file){
     //  System.out.println("aaa");
        int n=0,k=0,h=0;
        if(file.isFile()){
            System.out.println(file.getName());


            return;
        }
        System.out.println(++n);

        File[] files = file.listFiles();
        for (File f : files) {
            show(f);

        }
    }


    public static void main(String[] args) {
        File fie = new File("C:\\Users\\Administrator.SC-201902011618\\Desktop");
        show(fie);
    }




}
