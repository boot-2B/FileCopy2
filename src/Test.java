import java.io.File;

public class Test {

    public static void main(String args[]){
        FileCopy me=new FileCopy("E:"+ File.separator+"test"+File.separator+"csdn.txt","E:"+ File.separator+"test"+File.separator+"csdn2.txt");
        me.copyFile();
    }
}
