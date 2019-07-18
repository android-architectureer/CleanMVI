package com.khunzohn.data.datasource.remote

import com.khunzohn.data.mapper.IPhoneMapper
import com.khunzohn.data.mapper.MacMapper
import com.khunzohn.data.service.GsonProductService
import com.khunzohn.domain.model.Product
import io.reactivex.Single

class GsonProductRemote constructor(
    private val productService: GsonProductService,
    private val macMapper: MacMapper,
    private val iPhoneMapper: IPhoneMapper
) : ProductRemote {

    override fun getMacs(): Single<List<Product.Mac>> {
        return productService.getMacs().map { macMapper.dataToDomain(it) }
    }

    override fun getIPhones(): Single<List<Product.IPhone>> {
        return productService.getIPhones().map { iPhoneMapper.dataToDomain(it) }
    }

    override fun favouriteMac(macId: String): Single<Any> {
        return productService.favouriteMac(macId)
    }

    override fun unFavouriteMac(macId: String): Single<Any> {
        return productService.unFavouriteMac(macId)
    }

    override fun favouriteIPhone(iPhoneId: String): Single<Any> {
        return productService.favouriteIPhone(iPhoneId)
    }

    override fun unFavouriteIPhone(iPhoneId: String): Single<Any> {
        return productService.unFavouriteIPhone(iPhoneId)
    }
}