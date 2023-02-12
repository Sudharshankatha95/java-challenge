package jp.co.axa.apidemo.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axa.apidemo.converter.EmployeeConverter;
import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.BaseResModel;
import jp.co.axa.apidemo.model.EmployeeResModel;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeConverter employeeConverter;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}


	/**
	 * get list of employees from DB.
	 * 
	 * @param employeeId
	 * @return EmployeeResModel
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@Cacheable(value = "employees", key = "#employeeId")
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)
	public EmployeeResModel getEmployee(Long employeeId) throws EmployeeNotFoundException {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getName() + " " + "getEmployee Method");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		List<EmployeeDTO> employeeList = new ArrayList<>();
		employeeList.add(employeeConverter.covertEntityToDto(employee));
		return new EmployeeResModel(HttpStatus.OK, null, true, false, "succeed to execute [getEmployee]", null,
				0, employeeList);
	}

	/**
	 * get the employee Details from DB.
	 * 
	 * @param employeeId
	 * @return EmployeeResModel
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)
	public EmployeeResModel getEmployees() {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getName() + " " + "getEmployees Method");
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> employeeList = employees.stream()
				.map(employee -> employeeConverter.covertEntityToDto(employee))
				.sorted(Comparator.comparing(EmployeeDTO::getId)).collect(Collectors.toList());

		return new EmployeeResModel(HttpStatus.OK, null, true, false, "succeed to execute [getEmployees]", null,
				employeeList.size(),
				employeeList);
	}

	/**
	 * Delete the employee from DB if exist
	 * 
	 * @param employeeId
	 * @return
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@SuppressWarnings("unused")
	@CacheEvict(value = "employees", allEntries = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = { "Exception" })
	public BaseResModel deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getName() + " " + "deleteEmployee Method");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found" + employeeId));
		employeeRepository.deleteById(employeeId);
		LOGGER.info("Employee " + employee.getId() + "is Deleted Successfully");
		return new BaseResModel(HttpStatus.OK, null, true, false, "succeed to execute [deleteEmployee]", null,
				0);
	}

	/**
	 * Update the Employee Details if exist
	 * 
	 * @param employeeDto
	 * @param employeeId
	 * @return BaseResModel
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@CachePut(value = "employee", key = "#employeeId")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = { "Exception" })
	public BaseResModel updateEmployee(EmployeeDTO employeeDto, Long employeeId) throws EmployeeNotFoundException {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getName() + " " + "Update Employee Method");
		Employee emp = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found" + employeeId));
		Employee employee = employeeConverter.convertDtoToEntity(employeeDto);
		employeeRepository.save(employee);
		LOGGER.info("Updated Employee " + employee.getId() + "Successfully");
		return new BaseResModel(HttpStatus.CREATED, null, true, false, "succeed to execute [updateEmployee]", null, 0);
	}

	/**
	 * Save the employee details.
	 * 
	 * @param employee
	 * @param employeeId
	 * @return BaseResModel
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@Override
	@CacheEvict(value = "employees", allEntries = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = { "Exception" })
	public BaseResModel saveEmployee(EmployeeDTO employeeDto) {
		LOGGER.info("Inside " + EmployeeServiceImpl.class.getName() + " " + "Save Employee Method");
		Employee employee = employeeConverter.convertDtoToEntity(employeeDto);
		employeeRepository.save(employee);
		LOGGER.info("Employee Saved Successfully");
		return new BaseResModel(HttpStatus.CREATED, null, true, false, "succeed to execute [saveEmployee]", null, 0);
	}
}