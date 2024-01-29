package br.com.silviocavalcanti.webfluxcourse.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.silviocavalcanti.webfluxcourse.entity.User;
import br.com.silviocavalcanti.webfluxcourse.model.request.UserRequest;
import br.com.silviocavalcanti.webfluxcourse.model.response.UserResponse;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = IGNORE,
  nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  User toEntity(final UserRequest request);
  
  @Mapping(target = "id", ignore = true)
  User toEntity(final UserRequest request, @MappingTarget final User entity);

  UserResponse toResponse(final User entity);
}
