package kr.co.hajun.rxjavapractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class Day1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RxJava 실습 Day1
        List<Integer> numbers = Arrays.asList(1, 3, 21, 10, 8, 11);

        int sum = 0;
        for(int number:numbers){
            if(number > 6 && (number % 2 != 0)){
                sum += number;
            }
        }

        int sumRxJava = numbers.stream()
                        .filter(number -> number > 6 && (number % 2 != 0))
                        .mapToInt(number -> number)
                        .sum();

    }
}