package com.example.myapplication;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test {
    private static final String TAG = "Test";
    //观察者
    private static Observer<String> reader;
    private static Disposable mDisposable;

    public static Observer<String> getReader() {
        if (reader == null) {
            reader = new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    mDisposable = d;
                    Log.e(TAG, "onSubscribe");
                }

                @Override
                public void onNext(String value) {
                    if ("2".equals(value)) {
                        mDisposable.dispose();
                        return;
                    }
                    Log.e(TAG, "onNext:" + value);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError=" + e.getMessage());
                }

                @Override
                public void onComplete() {
                    Log.e(TAG, "onComplete()");
                }
            };

        }

        return reader;
    }
}
