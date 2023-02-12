package jp.co.axa.apidemo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;

@Component
public class EmployeeConverter {

	/**
	 * This method will Convert the Employee object to EmployeeDTO
	 * 
	 * @param employee
	 * @return employeDTO
	 */
	public EmployeeDTO covertEntityToDto(Employee employee) {

		ModelMapper modelMapper = new ModelMapper();
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		return employeeDTO;
	}

	/**
	 * This method will Convert the EmployeeDTO object to Employee
	 * 
	 * @param employeeDto
	 * @return employee
	 */
	public Employee convertDtoToEntity(EmployeeDTO employeeDto) {
		ModelMapper modelMapper = new ModelMapper();
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		return employee;

	}

}
