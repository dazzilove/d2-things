package com.dazzilove.dtwothings.api.todaytask.domain.repository.mongodb;

import com.dazzilove.dtwothings.api.todaytask.domain.TodayTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodayTaskMongodbRepository extends MongoRepository<TodayTask, String> {
}
