package com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb;

import com.dazzilove.dtwothings.api.todaytask.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskMongodbRepository extends MongoRepository<Task, String> {
}
