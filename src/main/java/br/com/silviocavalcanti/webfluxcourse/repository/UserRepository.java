package br.com.silviocavalcanti.webfluxcourse.repository;

import java.util.Objects;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.silviocavalcanti.webfluxcourse.entity.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {
  
  private final ReactiveMongoTemplate mongoTemplate;

  public Mono<User> save(final User user){
    if (Objects.nonNull(user)) {
      return mongoTemplate.save(user);
    }
    throw new NullPointerException();
  }

  public Mono<User> findById(final String id) {
    if (Objects.isNull(id)) {
      return null;
    }else{
      return mongoTemplate.findById(id, User.class);
    }
  }

  public Flux<User> findAll(){
    return mongoTemplate.findAll(User.class);
  }

  public Mono<User> findAndRemove(String id) {
    Query query = new Query();
    Criteria where = Criteria.where("id").is(id);
    return mongoTemplate.findAndRemove(query.addCriteria(where), User.class);
  }
  
}
