package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.EmployeeDto;
import main.dto.formDto.EmployeeFormDto;
import main.entity.Employee;
import main.exceptions.EmployeeException;
import main.mapper.EmployeeMapper;
import main.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto create(EmployeeFormDto formDto){
        Employee employee = new Employee();
        employeeMapper.updateEmployeeFromDto(formDto, employee);
        return EmployeeDto.mapToDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAll(){
        return employeeRepository
                .findAll()
                .stream()
                .map(EmployeeDto::mapToDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto getById(Long id){
        return employeeRepository.findById(id)
                .map(EmployeeDto::mapToDto)
                .orElseThrow(() -> new EmployeeException(id));
    }

    public EmployeeDto update(Long id, EmployeeFormDto formDto){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException(id));
        employeeMapper.updateEmployeeFromDto(formDto, employee);
        return EmployeeDto.mapToDto(employeeRepository.save(employee));
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
