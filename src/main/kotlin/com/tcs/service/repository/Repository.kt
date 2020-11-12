package com.tcs.service.repository

import com.tcs.service.model.DeliveryScheduleModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : MongoRepository<DeliveryScheduleModel, Int>, CustomRepository {
fun deleteById(id:String)
}