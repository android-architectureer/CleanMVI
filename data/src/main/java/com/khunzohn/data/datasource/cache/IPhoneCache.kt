package com.khunzohn.data.datasource.cache

import com.khunzohn.data.database.dao.IPhoneDao
import com.khunzohn.data.mapper.IPhoneMapper
import com.khunzohn.domain.model.Product
import io.reactivex.Observable

class IPhoneCache constructor(
    private val iPhoneDao: IPhoneDao,
    private val iPhoneMapper: IPhoneMapper
) : ProductCache<Product.IPhone> {

    override fun stream(): Observable<List<Product.IPhone>> {
        return iPhoneDao.stream().map { iPhoneMapper.entityToDomain(it) }
    }

    override fun update(values: List<Product.IPhone>) {
        iPhoneDao.insert(*iPhoneMapper.domainToEntity(values).toTypedArray())
    }

    override fun toggleFavourite(value: Product.IPhone) {
        iPhoneDao.updateFavourite(value.id, !value.favourite)
    }

    override fun reset() {
        iPhoneDao.removeAll()
    }
}