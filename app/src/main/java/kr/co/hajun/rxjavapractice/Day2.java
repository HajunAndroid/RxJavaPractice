package kr.co.hajun.rxjavapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.processors.PublishProcessor;

public class Day2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day2);
        //RxJava 실습 Day2
        Flowable<Integer> flowable = Flowable.just(1, 3, 5, 7);

        flowable.subscribe(data -> Log.d("구독자","1번"+data));
        flowable.subscribe(data -> Log.d("구독자","2번"+data));

        PublishProcessor<Integer> processor = PublishProcessor.create();
        processor.subscribe(data -> Log.d("구독자","1번"+data));
        processor.onNext(1);
        processor.onNext(3);

        processor.subscribe(data -> Log.d("구독자","2번"+data));
        processor.onNext(5);
        processor.onNext(7);

        processor.onComplete();
    }
}