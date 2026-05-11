package main.mapper;

import main.dto.EmployeeDto;
import main.dto.formDto.EmployeeFormDto;
import main.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    void updateEmployeeFromDto(EmployeeFormDto formDto, @MappingTarget EmployeeDto dto);

    void updateEmployeeFromDto(EmployeeFormDto formDto,@MappingTarget Employee entity);
}
