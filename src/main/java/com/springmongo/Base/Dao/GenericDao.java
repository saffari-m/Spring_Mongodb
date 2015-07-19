package com.springmongo.Base.Dao;

import com.springmongo.Base.BaseModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.config.BeanDefinition;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDao<T extends BaseModel<Long>> extends BaseDao<T> {


}
