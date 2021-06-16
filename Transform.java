import java.io.*;
import java.util.concurrent.*;

public class Transform {

    public static int handle(Throwable throwable){
        System.out.println("ERROR: " + throwable.toString());
        //return 100;
        throw new RuntimeException("This is beyond any repair");
    }

    public static void process(CompletableFuture<Integer> future){//making the pipeline for the completable future

        future
        .exceptionally(throwable -> handle(throwable))
        .thenApply(data-> data*2)
        .thenApply(data -> data+1)
        .thenAccept(System.out::println);
    }
    
    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        //future.completeOnTimeout(500,3,TimeUnit.SECONDS);//if we want to repair with something default
        future.orTimeout(3,TimeUnit.SECONDS);//if we dont want to repair at all
        process(future);
        sleep(2000);
        //future.complete(2); // executing the completableFuture
        //future.completeExceptionally(new RuntimeException("something went wrong"));
        sleep(5000);
    }

    public static boolean sleep(int ms){
        try{
            Thread.sleep(ms);
            return true;
        }catch(InterruptedException e){
            return false;
        }
    }
}
