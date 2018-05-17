package com.randomplayer.infibookmm_app.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableManager {
    private static CompositeDisposable compositeDisposable;

    private static CompositeDisposable getInstance() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    public static void add(Disposable disposable) {
        getInstance().add(disposable);
    }

    public static void dispose() {
        getInstance().dispose();
    }
}
