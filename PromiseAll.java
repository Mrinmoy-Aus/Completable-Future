import java.util.*;
import java.util.concurrent.*;

public class PromiseAll {
    
    public static CompletableFuture<Integer> create(int n){
        return CompletableFuture.supplyAsync(()->n);
    }

    public static void main(String[] args) {
        // create(2).thenCombine(create(3) , (a,b)-> a+b)
        // .thenAccept(System.out::println);
        create(2).thenCompose(data-> create(data))
        .thenAccept(System.out::println);
    }

}
