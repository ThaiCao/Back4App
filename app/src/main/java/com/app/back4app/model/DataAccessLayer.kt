package com.app.back4app.model

import com.app.back4app.model.observer.DataObserver
import com.app.back4app.model.observer.ProductObserver

class DataAccessLayer( private val dataRepository: DataRepository) {
    private val observers = mutableListOf<DataObserver>()

    fun register(observer: DataObserver) = observers.add(observer)

    fun unregister(observer: DataObserver) = observers.remove(observer)

    private fun notifyProduct(action: (ProductObserver) -> Unit) {
        observers.filterIsInstance<ProductObserver>().onEach { action(it) }
    }

    fun performgetProducts() {
        notifyProduct(ProductObserver::getProducts)
    }
}