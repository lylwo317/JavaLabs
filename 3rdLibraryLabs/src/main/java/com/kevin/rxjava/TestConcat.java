package com.kevin.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Function;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.internal.observers.LambdaObserver;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by: kevin
 * Date: 2023-02-15
 */
public class TestConcat {
    public static void main(String[] args) {
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

//        Observable.fromPublisher(new Flowable<String>() {
//            @Override
//            protected void subscribeActual(Subscriber<? super  String> s) {
//                s.onNext("A1");
//                s.onNext("A2");
//                s.onNext("A3");
//                s.onNext("B1");
//                s.onNext("B2");
//                s.onNext("B3");
//                s.onComplete();
//            }
//        })
//        Observable.unsafeCreate(new ObservableSource<String>() {
//                    @Override
//                    public void subscribe(Observer<? super String> observer) {
//                        observer.onSubscribe(Disposables.empty());
//                        observer.onNext("Aq");
//                        observer.onComplete();
//                    }
//
//
//                })

        Observable.create(new ObservableOnSubscribe<Observable<String>>(){
                    @Override
                    public void subscribe(ObservableEmitter<Observable<String>> emitter) throws Exception {
                        emitter.onNext(aObservable);
                        emitter.onNext(bObservable);
                    }

        })
                .concatMap(new Function<Observable<String>, ObservableSource<?>>() {
                               @Override
                               public ObservableSource<?> apply(Observable<String> stringObservable) throws Exception {
                                   return stringObservable;
                               }
                           }
                )
                .subscribe(s -> System.out.print(s + ", "));


//        aObservable.concatWith(bObservable)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.print(s + ", ");
//                    }
//                });

//        Observable.concat(aObservable, bObservable)//使用concat操作符将两个被观察者合并
//                .subscribe(s -> System.out.print(s + ", "));
    }

}
