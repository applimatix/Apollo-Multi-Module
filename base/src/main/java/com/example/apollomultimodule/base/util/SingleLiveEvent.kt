package com.example.apollomultimodule.base.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class SingleLiveEvent<T>: LiveData<T>() {

    val pendingEvent = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            throw IllegalStateException("Multiple Observers registered")
        }

        super.observe(owner, Observer {
            if (pendingEvent.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        pendingEvent.set(true)
        super.setValue(value)
    }
}

class MutableSingleLiveEvent<T>: SingleLiveEvent<T>() {

    @MainThread
    public override fun setValue(value: T?) {
        super.setValue(value)
    }
}