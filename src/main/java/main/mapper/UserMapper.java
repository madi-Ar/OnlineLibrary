package main.mapper;

import main.dto.formDto.UserFormDto;
import main.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    void mapUserFromFormDto(UserFormDto formDto, @MappingTarget User user);
}
