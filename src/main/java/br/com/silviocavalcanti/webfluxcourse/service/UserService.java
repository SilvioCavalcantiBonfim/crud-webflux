package br.com.silviocavalcanti.webfluxcourse.service;

import org.springframework.stereotype.Service;

import br.com.silviocavalcanti.webfluxcourse.entity.User;
import br.com.silviocavalcanti.webfluxcourse.mapper.UserMapper;
import br.com.silviocavalcanti.webfluxcourse.model.request.UserRequest;
import br.com.silviocavalcanti.webfluxcourse.repository.UserRepository;
import br.com.silviocavalcanti.webfluxcourse.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Mono<User> save(final UserRequest request) {
    return userRepository.save(userMapper.toEntity(request));
  }

  public Mono<User> findById(final String id) {
    return handleNotFound(userRepository.findById(id), id, User.class);
  }

  public Flux<User> findAll() {
    return userRepository.findAll();
  }

  public Mono<User> update(final String id, final UserRequest userRequest) {
    return findById(id).map(e -> userMapper.toEntity(userRequest, e)).flatMap(userRepository::save);
  }

  public Mono<User> delete(final String id) {
    return handleNotFound(userRepository.findAndRemove(id), id, User.class);
  }

  private <T> Mono<T> handleNotFound(Mono<T> mono, String id, Class<T> clazz){
    return mono.switchIfEmpty(Mono.error(new ObjectNotFoundException(id, clazz.getSimpleName())));
  }
}
