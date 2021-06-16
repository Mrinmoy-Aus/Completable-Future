import java.util.*;
import java.util.concurrent.*;

public class Promise {

    public static ForkJoinPool fjp = new ForkJoinPool();

    public static int compute(){
        // System.out.println("Compute: " + Thread.currentThread());
        return 2;
    }

    public static void printIt(int value){
        System.out.println(value);
        // System.out.println("printIt: " + Thread.currentThread());
    }

    public static CompletableFuture<Integer> create(){
        return CompletableFuture.supplyAsync(() -> compute(),fjp);
    }

    public static void main(String[] args){
        // System.out.println("main: "+Thread.currentThread());
        create()
        .thenApply(data -> data*2)//transformation
        .thenApply(data -> data+1)//transformation
        .thenAccept(data -> printIt(data));
    }

    // public static boolean sleep(int ms){
    //     try{
    //         Thread.sleep(ms);
    //         return true;
    //     }catch(InterruptedException e){
    //         return false;
    //     }
    // }
}
