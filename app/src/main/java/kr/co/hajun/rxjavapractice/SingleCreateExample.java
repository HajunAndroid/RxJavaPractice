package kr.co.hajun.rxjavapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import java.util.Date;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;

public class SingleCreateExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Throwable {
                emitter.onSuccess("test");
            }
        });

        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {
                Log.d("success",s+"");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("error",e+"");
            }
        });

        //람다식 사용
        Single<String> single_lamda = Single.create(emitter -> emitter.onSuccess("test"));

        single_lamda.subscribe(
                data -> Log.d("success",data+""),
                error -> Log.d("error",error+"")
        );

        //just() 사용
        Single.just("test")
                .subscribe(
                        data -> Log.d("success",data+""),
                        error -> Log.d("error",error+"")
                );
    }
}