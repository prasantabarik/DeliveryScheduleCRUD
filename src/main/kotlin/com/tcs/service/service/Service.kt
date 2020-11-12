package com.tcs.service.service

import com.tcs.service.constant.ExceptionMessage
import com.tcs.service.error.customexception.DataNotFoundException
import com.tcs.service.model.DeliveryScheduleModel
import com.tcs.service.model.Model
import com.tcs.service.repository.Repository
import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Service


@Service
class Service(private val repository: Repository) {
    val logger = logger()
    fun getById(id: String): Model {
        logger.info("Before Cast")
        return Model(repository.findById(id.toInt()).get() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND))
    }


    fun get(): MutableList<Model>{
        //The below lines of code is for POC on Mongo Template
        //repository.getAllByDesc("Sample").forEach{i -> println(i.modId)}
        var models = mutableListOf<Model>()
        var result = repository.findAll() ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)
        print(result)
        result.forEach { entity -> models.add(Model(data = entity)) }
        print(models)
        return models
    }

    fun getByQueryParam(storeNumber: Long?, deliveryStreamNumber: Int?, deliveryStreamName: String?, schemaName: String?, startDate: String?, endDate: String?, notes: String?): MutableList<Model>{
        //The below lines of code is for POC on Mongo Template
        var models = mutableListOf<Model>()
        //to check if any of the query parameters is null
        when {
            storeNumber == null && deliveryStreamNumber == null && deliveryStreamName == null &&
                    schemaName == null && startDate == null && endDate == null && notes == null -> return get()
        }
        var result = repository.findAllByQueryParams(storeNumber, deliveryStreamNumber,
                deliveryStreamName,
                schemaName,startDate,endDate,notes) ?: throw DataNotFoundException(ExceptionMessage.NO_DATA_FOUND)
        print(result)
        result.forEach { entity -> models.add(Model(data = entity)) }
        print(models)
        return models
    }

    fun save(model:DeliveryScheduleModel)
    {
        repository.save(model)
    }

    fun delete(id: String)
    {
       // repository.delete(repository.findById(id.toInt()).get())
        repository.deleteById(id)
    }

}