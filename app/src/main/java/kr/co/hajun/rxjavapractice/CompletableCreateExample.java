package kr.co.hajun.rxjavapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompletableCreateExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completable_create_example);

        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                int sum = 0;
                for(int i=0;i<100;i++){
                    sum+=i;
                }
                Log.d("sum","합계: "+sum);

                emitter.onComplete();
            }
        });

        completable.subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("complete","complete");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("error","error");
                    }
                });

        //Lamda 사용
        Completable completable_lamda = Completable.create(emitter -> {
            int sum = 0;
            for(int i=0;i<100;i++){
                sum+=i;
            }
            Log.d("출력","합계: "+ sum);

            emitter.onComplete();
        });

        completable_lamda.subscribeOn(Schedulers.computation())
                .subscribe(
                        () -> Log.d("complete","complete"),
                        error -> Log.d("error","error")
                );
    }
}