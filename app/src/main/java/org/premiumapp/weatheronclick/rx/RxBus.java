package org.premiumapp.weatheronclick.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

    private final Subject<Object, Object> rxBus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        if (hasObservers()) rxBus.onNext(o);
    }

    public void forceSend(Object o) {
        rxBus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return rxBus;
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }
}
