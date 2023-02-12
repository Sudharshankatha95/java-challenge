package jp.co.axa.apidemo.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.model.BaseResModel;
import jp.co.axa.apidemo.model.EmployeeResModel;
import jp.co.axa.apidemo.services.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

	}

	@Test
	public void getAllEmployees_success() throws Exception {

		EmployeeDTO emp1 = new EmployeeDTO();

		emp1.setName("Sudharshan R Katha");
		emp1.setSalary(10000);
		emp1.setDepartment("IT");

		EmployeeDTO emp2 = new EmployeeDTO();

		emp2.setName("Jasitha");
		emp2.setSalary(10000);
		emp2.setDepartment("IT");

		List<EmployeeDTO> employees = new ArrayList<>(Arrays.asList(emp1, emp2));

		EmployeeResModel empRes = new EmployeeResModel(HttpStatus.OK, null, true, false,
				"succeed to execute [getEmployee]", null, 0, employees);

		Mockito.when(employeeService.getEmployees()).thenReturn(empRes);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		EmployeeResModel response = objectMapper.readValue(resultContent, EmployeeResModel.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}

	@Test
	public void getEmployee_success() throws Exception {

		EmployeeDTO emp2 = new EmployeeDTO();

		emp2.setName("Jasitha");
		emp2.setSalary(10000);
		emp2.setDepartment("IT");
		emp2.setId((long) 1);

		List<EmployeeDTO> employees = new ArrayList<>(Arrays.asList(emp2));

		EmployeeResModel empRes = new EmployeeResModel(HttpStatus.OK, null, true, false,
				"succeed to execute [getEmployees]", null, employees.size(),
				employees);

		Mockito.when(employeeService.getEmployee((long) 1)).thenReturn(empRes);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/employees/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		EmployeeResModel response = objectMapper.readValue(resultContent, EmployeeResModel.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}

	@Test
	@WithMockUser
	public void addEmployee_success() throws Exception {

		EmployeeDTO employee = new EmployeeDTO();

		BaseResModel baseResModel = new BaseResModel(HttpStatus.CREATED, null, true, false,
				"succeed to execute [saveEmployee]", null, 0);

		employee.setName("Sudharshan R Katha");
		employee.setSalary(10000);
		employee.setDepartment("IT");

		String jsonRequest = objectMapper.writeValueAsString(employee);

		Mockito.when(employeeService.saveEmployee(employee)).thenReturn(baseResModel);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(jsonRequest);

		MvcResult result = mockMvc.perform(mockRequest).andExpect(status().isCreated()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		EmployeeResModel response = objectMapper.readValue(resultContent, EmployeeResModel.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}

	@Test
	public void updateEmployee_success() throws Exception {

		EmployeeDTO employee = new EmployeeDTO();

		employee.setName("Sudharshan R");
		employee.setSalary(10000);
		employee.setDepartment("IT");
		employee.setId((long) 1);

		BaseResModel baseResModel = new BaseResModel(HttpStatus.CREATED, null, true, false,
				"succeed to execute [updateEmployee]", null, 0);

		String jsonRequest = objectMapper.writeValueAsString(employee);

		Mockito.when(employeeService.updateEmployee(employee, employee.getId())).thenReturn(baseResModel);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/employees/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(jsonRequest);

		MvcResult result = mockMvc.perform(mockRequest).andExpect(status().isCreated()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		EmployeeResModel response = objectMapper.readValue(resultContent, EmployeeResModel.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}

	@Test
	public void deleteEmployee_success() throws Exception {

		EmployeeDTO employeeDTO = new EmployeeDTO();

		employeeDTO.setName("Jasitha");
		employeeDTO.setSalary(10000);
		employeeDTO.setDepartment("IT");
		employeeDTO.setId((long) 1);

		BaseResModel resModel = new BaseResModel(HttpStatus.OK, null, true, false,
				"succeed to execute [deleteEmployee]", null, 0);

		Mockito.when(employeeService.deleteEmployee((long) 1)).thenReturn(resModel);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		BaseResModel response = objectMapper.readValue(resultContent, BaseResModel.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}

}
