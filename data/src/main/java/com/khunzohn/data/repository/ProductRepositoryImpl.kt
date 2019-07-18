package com.khunzohn.data.repository

import com.khunzohn.data.datasource.cache.IPhoneCache
import com.khunzohn.data.datasource.cache.MacCache
import com.khunzohn.data.datasource.remote.ProductRemote
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.repository.ProductRepository
import io.reactivex.Observable
import io.reactivex.Single

class ProductRepositoryImpl constructor(
    private val productRemote: ProductRemote,
    private val iPhoneCache: IPhoneCache,
    private val macCache: MacCache
) : ProductRepository {

    override fun fetchMacs(): Single<List<Product.Mac>> {
        return productRemote.getMacs()
            .doOnSuccess { macCache.update(it) }
    }

    override fun streamMacs(): Observable<List<Product.Mac>> {
        return macCache.stream()
    }

    override fun toggleFavourite(mac: Product.Mac): Single<Any> {
        return Single.fromCallable {
            macCache.toggleFavourite(mac)
        }
    }

    override fun fetchIPhones(): Single<List<Product.IPhone>> {
        return productRemote.getIPhones()
            .doOnSuccess { iPhoneCache.update(it) }
    }

    override fun streamIPhones(): Observable<List<Product.IPhone>> {
        return iPhoneCache.stream()
    }

    override fun toggleFavourite(iPhone: Product.IPhone): Single<Any> {
        return Single.fromCallable {
            iPhoneCache.toggleFavourite(iPhone)
        }
    }
}