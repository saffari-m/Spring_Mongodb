package com.springmongo.Base.Dao;

import com.springmongo.Base.BaseModel;
import com.springmongo.Model.Sequence;
import com.springmongo.Model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 7/18/2015.
 */
public abstract class BaseDao<T extends BaseModel<Long>> {
    private Class<T> tclass;
    private MongoOperations mongoTemplate;
    public Class<T> getTclass() {
        return tclass;
    }

    public void setTclass(Class<T> tclass) {
        this.tclass = tclass;
    }

    public MongoOperations getMongoTemplate() {
        return mongoTemplate;
    }
    @Autowired
    public void setMongoTemplate(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(T entity) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"id"));
        query.addCriteria(Criteria.where("id").is("seqId"));
        Update update = new Update();
        update.inc("seq",1);
        Sequence sequence =  mongoTemplate.findAndModify(query, update, Sequence.class);
        if(sequence == null){
            sequence = new Sequence();
            sequence.setSeq(Long.valueOf("0"));
            sequence.setId("seqId");
            mongoTemplate.save(sequence);
        }
        entity.setId(sequence.getSeq());
        mongoTemplate.save(entity);
    }

    public void delete(T entity) {
        mongoTemplate.remove(entity);
    }

    public List<T> getAll() {
        //new mongodb query
        Query query = new Query();
        //sort by date
        query.with(new Sort(Sort.Direction.DESC,"id"));
        //run query
        ArrayList<T> allPostList = (ArrayList<T>) mongoTemplate.find(query, tclass);

        return allPostList;
    }

    public List<T> find(String[] properties , Object[] values){
        ArrayList<T> list = null;
        try {
            Query query = new Query();
            //sort by date
            query.with(new Sort(Sort.Direction.DESC, "id"));
            for(int i=0; i<properties.length;i++){
                Criteria criteria = new Criteria();
                criteria = criteria.where(properties[i]).regex(".*" + values[i]+".*");
                query.addCriteria(criteria);
            }
            //run query
            list = (ArrayList<T>) mongoTemplate.find(query,tclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
