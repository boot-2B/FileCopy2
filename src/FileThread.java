import java.io.*;
import java.io.RandomAccessFile;

public class FileThread implements Runnable {
    private static long point=0;
    private final int LENGTH=10;
    private boolean end;
    private byte[] b;
    private RandomAccessFile raf1;
    private RandomAccessFile raf2;

    public FileThread(File origin,File copy){
        this.b=new byte[this.LENGTH];
        this.end=false;
        try {
            if(!origin.exists()) {
                origin.createNewFile();
            }
            else if(!copy.exists()){
                copy.createNewFile();
            }
            this.raf1=new RandomAccessFile(origin,"r");
            this.raf2=new RandomAccessFile(copy,"rw");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public synchronized long getPoint(){return point++;}

@Override
    public void run(){
        try {
            while (!end) {
                long position;
                this.raf1.seek(position=this.LENGTH*this.getPoint());
                System.out.println(position);
                System.out.println(Thread.currentThread().getName());
                int temp = 0;
                int foot = 0;
                while (foot < this.LENGTH&&(temp = this.raf1.read()) != -1 ) {
                    b[foot] = (byte) temp;
                    foot++;
                }

                this.raf2.seek(position);
                this.raf2.write(b);
                //判断文件尾
                if (temp == -1) {
                    this.end = true;
                }
                //重置数组
                for(int i=0;i<this.LENGTH;i++)
                    this.b[i]=0;
            }
            this.raf1.close();
            this.raf2.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
}

}
