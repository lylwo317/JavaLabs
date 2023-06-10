package com.kevin.coroutines;

/**
 * Created by: kevin
 * Date: 2023-06-13
 */
public class UseInJava {
    public static void main(String[] args) throws InterruptedException {

        GetToken getToken = new GetToken();
        getToken.getTokenFuture().thenAccept(s -> {
            System.out.println(s);
        });

        Thread.sleep(2000);
    }
}
