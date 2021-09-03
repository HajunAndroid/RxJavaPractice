package kr.co.hajun.rxjavapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;

public class MaybeCreateExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maybe_create_example);

        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Throwable {
                emitter.onSuccess("test");
                //emitter.onComplete();
            }
        });

        maybe.subscribe(new MaybeObserver<String>() {
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

            @Override
            public void onComplete() {
                Log.d("complete","complete");
            }
        });

        //Lamda 사용
        Maybe<String> maybe_lamda = Maybe.create(emitter -> {
            emitter.onSuccess("test");
            //emitter.onComplete();
        });

        maybe_lamda.subscribe(
                data -> Log.d("success",data+""),
                error -> Log.d("error",error+""),
                () -> Log.d("complete","complete")
        );

        //just() 사용
        Maybe.just("teest")
                .subscribe(
                        data -> Log.d("success",data+""),
                        error -> Log.d("error",error+""),
                        () -> Log.d("complete","complete")
                );
        Maybe.empty()
                .subscribe(
                        data -> Log.d("success",data+""),
                        error -> Log.d("error",error+""),
                        () -> Log.d("complete","complete")
                );
    }
}