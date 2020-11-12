package com.tcs.service.repository

import com.tcs.service.model.DeliveryScheduleModel
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class CustomRepositoryImpl(private val mongoTemplate: MongoTemplate) : CustomRepository{


    override fun getAllByDesc(modDesc: String): List<DeliveryScheduleModel>{
        return mongoTemplate.find(Query(Criteria.where("modDesc").`is`(modDesc)),
                DeliveryScheduleModel::class.java)
    }

    override fun findAllByQueryParams(storeNumber: Long?, deliveryStreamNumber: Int?, deliveryStreamName: String?,
                                      schemaName: String?, startDate: String?, endDate: String?,
                                      notes: String?): List<DeliveryScheduleModel>?{


        var queryObject = Query()
        var criteria = Criteria();

//            var orCriterias: ArrayList<Criteria> = []




        if( storeNumber != null) {
//                orCriterias?.add(Criteria.where("storeNumber").isEqualTo(storeNumber))
            var criteria1 = Criteria.where("storeNumber").isEqualTo(storeNumber);
            queryObject.addCriteria(criteria1);
        }

        if( deliveryStreamNumber != null) {
//                orCriterias?.add(Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStreamNumber))
            var criteria1 = Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStreamNumber);
            queryObject.addCriteria(criteria1);
        }

        if( deliveryStreamName != null) {
            var criteria1 = Criteria.where("deliveryStreamName").isEqualTo(deliveryStreamName);
            queryObject.addCriteria(criteria1);
        }

        if( schemaName != null) {
            var criteria1 = Criteria.where("schemaName").isEqualTo(schemaName);
            queryObject.addCriteria(criteria1);
        }

        if( startDate != null) {
            var criteria1 = Criteria.where("startDate").isEqualTo(startDate);
            queryObject.addCriteria(criteria1);
        }

        if( endDate != null) {
            var criteria1 = Criteria.where("endDate").isEqualTo(endDate);
            queryObject.addCriteria(criteria1);
        }

        if( notes != null) {
            var criteria1 = Criteria.where("notes").isEqualTo(notes);
            queryObject.addCriteria(criteria1);
        }

        queryObject.addCriteria(criteria);
        return mongoTemplate.find(queryObject,
                DeliveryScheduleModel::class.java)



    }}