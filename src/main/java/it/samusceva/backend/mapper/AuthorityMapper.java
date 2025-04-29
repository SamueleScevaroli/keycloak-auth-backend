package it.samusceva.backend.mapper;

import it.samusceva.backend.entity.AuthorityEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface AuthorityMapper {

    String toString(AuthorityEntity authorityEntity);

    @Mapping(source = ".", target = "name")
    AuthorityEntity toEntity(String authorityName);
}