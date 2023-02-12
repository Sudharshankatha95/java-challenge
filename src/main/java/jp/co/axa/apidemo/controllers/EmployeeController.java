package jp.co.axa.apidemo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.BaseResModel;
import jp.co.axa.apidemo.model.EmployeeResModel;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * @author sudharshan R Katha
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * This end point is responsible for return the list of the employees.
	 * 
	 * @return EmployeeResModel
	 */
	@GetMapping("/employees")
	public EmployeeResModel getEmployees() {
		return employeeService.getEmployees();
	}

	/**
	 * This end point is responsible for return the employee details.
	 * 
	 * @param employeeId
	 * @return EmployeeResModel
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/employees/{employeeId}")
	public EmployeeResModel getEmployee(@PathVariable(name = "employeeId") Long employeeId)
			throws EmployeeNotFoundException {
		return employeeService.getEmployee(employeeId);
	}

	/**
	 * This end point is responsible for Create the New Employee.
	 * 
	 * @param employeeDto
	 * @return BaseResModel
	 */
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public BaseResModel saveEmployee(@Valid @RequestBody EmployeeDTO employeeDto) {
		return employeeService.saveEmployee(employeeDto);
	}

	/**
	 * This end point is responsible for Delete the employee from DB if exist
	 * 
	 * @param employeeId
	 * @return
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/employees/{employeeId}")
	public BaseResModel deleteEmployee(@PathVariable(name = "employeeId") Long employeeId)
			throws EmployeeNotFoundException {
		return employeeService.deleteEmployee(employeeId);
	}

	/**
	 * This end point is responsible for Update the Employee Details if exist
	 * 
	 * @param employee
	 * @param employeeId
	 * @return BaseResModel
	 * @throws EmployeeNotFoundException
	 * 
	 */
	@PutMapping("/employees/{employeeId}")
	@ResponseStatus(HttpStatus.CREATED)
	public BaseResModel updateEmployee(@RequestBody EmployeeDTO employee,
			@PathVariable(name = "employeeId") Long employeeId) throws EmployeeNotFoundException {
		return employeeService.updateEmployee(employee, employeeId);

	}

}
