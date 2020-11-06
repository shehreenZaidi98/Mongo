package com.mongo.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MongoDbRepository extends MongoRepository<MongoDb,String> {
    @Query("{'machine_id' : ?0,'machine_name':'?1'}")
    List<MongoDb> findEmployeeByName(String machine_id, String machine_name);


}
