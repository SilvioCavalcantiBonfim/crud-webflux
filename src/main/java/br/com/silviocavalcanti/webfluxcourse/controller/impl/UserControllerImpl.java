package br.com.silviocavalcanti.webfluxcourse.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.silviocavalcanti.webfluxcourse.controller.UserController;
import br.com.silviocavalcanti.webfluxcourse.mapper.UserMapper;
import br.com.silviocavalcanti.webfluxcourse.model.request.UserRequest;
import br.com.silviocavalcanti.webfluxcourse.model.response.UserResponse;
import br.com.silviocavalcanti.webfluxcourse.service.UserService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerImpl implements UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public ResponseEntity<Mono<Void>> save(UserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(userService.save(request).then());
  }

  @Override
  public ResponseEntity<Mono<UserResponse>> findById(String id) {
    return ResponseEntity.ok().body(userService.findById(id).map(userMapper::toResponse));
  }

  @Override
  public ResponseEntity<Flux<UserResponse>> findAll() {
    return ResponseEntity.ok().body(
      userService.findAll().map(userMapper::toResponse)
    ); 
  }

  @Override
  public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
    return ResponseEntity.ok().body(
      userService.update(id, request).map(userMapper::toResponse)
    );
  }

  @Override
  public ResponseEntity<Mono<UserResponse>> delete(String id) {
    return ResponseEntity.ok().body(
      userService.delete(id).map(userMapper::toResponse)
    );
  }
  
}
