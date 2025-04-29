package it.samusceva.backend.mapper;

import it.samusceva.backend.dto.UserDto;
import it.samusceva.backend.entity.UserEntity;
import it.samusceva.backend.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = AuthorityMapper.class,
        componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity userEntity);

    UserEntity toEntity(User user);

    UserDto toDto(User user);

}