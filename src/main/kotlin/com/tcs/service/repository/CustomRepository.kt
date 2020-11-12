package com.tcs.service.repository

import com.tcs.service.model.DeliveryScheduleModel

interface CustomRepository {

    fun getAllByDesc(modDesc: String): List<DeliveryScheduleModel>
    fun findAllByQueryParams(storeNumber: Long?, deliveryStreamNumber: Int?, deliveryStreamName: String?, schemaName: String?, startDate: String?, endDate: String?, notes: String?): List<DeliveryScheduleModel>?
}
