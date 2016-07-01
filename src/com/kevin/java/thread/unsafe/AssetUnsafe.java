package com.kevin.java.thread.unsafe;

/**
 * Created by Administrator on 2016/7/1.
 */
public class AssetUnsafe {

    public MyAsset myAsset = new MyAsset();


    public void creat() {
        myAsset = new MyAsset();
    }

    public void check() {
        myAsset.check();
    }



    public static void main(String[] args) {


        while (true) {

            AssetUnsafe unsafe = new AssetUnsafe();
            Thread thread1=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unsafe.creat();
                }
            });

            Thread thread2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unsafe.check();
                }
            });
            thread2.start();
            thread1.start();
        }


    }

    private static class MyAsset{

        int i;

        public MyAsset(){
            this.i = 1;
        }

        public void check(){
            if (i != i) {
                System.out.println("i != i");
            }
        }
    }

    private static class MyNewInstanceThread implements Runnable {

        private MyAsset myAsset;

        public MyNewInstanceThread(MyAsset asset){
            myAsset = asset;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myAsset = new MyAsset();
        }
    }

    private static class MyCheckInstanceThread implements Runnable {

        private MyAsset myAsset;

        public MyCheckInstanceThread(MyAsset asset){
            myAsset = asset;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myAsset.check();
        }
    }

}
