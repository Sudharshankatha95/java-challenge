package jp.co.axa.apidemo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Name is mandatory Field")
	private String name;
	@NotNull(message = "Salary is mandatory Field")
	private Integer salary;
	@NotEmpty(message = "Department is mandatory Field")
	private String department;

}
