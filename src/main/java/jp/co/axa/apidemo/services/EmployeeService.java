package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.BaseResModel;
import jp.co.axa.apidemo.model.EmployeeResModel;

public interface EmployeeService {

	public EmployeeResModel getEmployees();

    public EmployeeResModel getEmployee(Long employeeId) throws EmployeeNotFoundException;

    public BaseResModel saveEmployee(EmployeeDTO employeeDto);

	public BaseResModel deleteEmployee(Long employeeId) throws EmployeeNotFoundException;

	public BaseResModel updateEmployee(EmployeeDTO employee, Long employeeId) throws EmployeeNotFoundException;
}