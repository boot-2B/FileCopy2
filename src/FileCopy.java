import java.io.File;
public class FileCopy {
    private File origin;
    private File copy;
    private FileThread a;
    private FileThread b;
    private FileThread c;

    public FileCopy(String originPath,String copyPath){
        this.origin=new File(originPath);
        this.copy=new File(copyPath);
        this.a=new FileThread(origin,copy);
        this.b=new FileThread(origin,copy);
        this.c=new FileThread(origin,copy);
    }

    public void copyFile(){
        new Thread(this.a,"A").start();
        new Thread(this.b,"B").start();
        new Thread(this.c,"C").start();
    }
}
