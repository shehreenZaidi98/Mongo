package com.mongo.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MbController {
    @Autowired
    MongoDbRepository mongoDbRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @PostMapping("/insert")
    public int insert(@RequestBody MongoDb mongoDb){
         mongoDbRepository.save(mongoDb);
        return 0;
    }
    @GetMapping("getMachine")
    public List<MongoDb> get(@RequestParam("machine_id") String machine_id,@RequestParam("machine_name")String machine_name){

        return mongoDbRepository.findEmployeeByName(machine_id,machine_name);
    }
    @GetMapping("getAllData")
    public List<MongoDb> getData(){
        return mongoDbRepository.findAll();
    }

    @PostMapping("getUpdated")
    public int update(@RequestBody MongoDb mongoDb){
        Query query=new Query();
        query.addCriteria(Criteria.where("machine_name").is("bsd"));
        query.addCriteria(Criteria.where("machine_id").is("456"));
        Update update = new Update();
        update.set("machine_code",741);
       // mongoTemplate.upsert(query,update,MongoDb.class);
        mongoTemplate.updateMulti(query,update,MongoDb.class);
        return  1;
    }

    @PostMapping("delete")
    public int delete(@RequestBody MongoDb mongoDb){
        Query query=new Query();
        query.addCriteria(Criteria.where("machine_name").is("bsd"));
        query.addCriteria(Criteria.where("machine_id").is("456"));
        mongoTemplate.findAndRemove(query,MongoDb.class);
        return 2;
    }
    @PostMapping("update")
    public int updateData(@RequestBody MongoDb mongoDb){
        Query query=new Query();
        query.addCriteria(Criteria.where("machine_id").is("456"));
        Update update = new Update();
        update.set("machine_name","ujm");
         mongoTemplate.upsert(query,update,MongoDb.class);
         return 2;

    }
}
