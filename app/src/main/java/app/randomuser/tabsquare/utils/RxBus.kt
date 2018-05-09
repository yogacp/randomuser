package app.randomuser.tabsquare.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {
    private val bus = PublishSubject.create<HashMap<String, Any>>()

    fun send(o: HashMap<String, Any>) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<HashMap<String, Any>> {
        return bus
    }

    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }
}