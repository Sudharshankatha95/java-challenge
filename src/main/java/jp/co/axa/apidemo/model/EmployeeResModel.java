/**
 * 
 */
package jp.co.axa.apidemo.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Sudharshan R Katha
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResModel extends BaseResModel{

	private static final long serialVersionUID = 1L;

	public List<EmployeeDTO> employeeList;

	public EmployeeResModel(HttpStatus httpsStatus, String error, Boolean success, Boolean warning,
			String successMessage, String errorDescription, Integer hit,List<EmployeeDTO> employeeList) {
		super(httpsStatus, error, success, warning, successMessage, errorDescription, hit);
		this.employeeList = employeeList;
	}
	

}
