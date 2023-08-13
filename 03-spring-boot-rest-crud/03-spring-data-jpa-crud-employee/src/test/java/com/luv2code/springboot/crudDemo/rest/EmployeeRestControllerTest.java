package com.luv2code.springboot.crudDemo.rest;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.springboot.crudDemo.entity.Employee;
import com.luv2code.springboot.crudDemo.service.EmployeeService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeRestController.class})
@ExtendWith(SpringExtension.class)
class EmployeeRestControllerTest {
    @Autowired
    private EmployeeRestController employeeRestController;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeRestController#addEmployee(Employee)}
     */
    @Test
    void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        when(employeeService.save(Mockito.<Employee>any())).thenReturn(employee);

        Employee employee2 = new Employee();
        employee2.setEmail("jane.doe@example.org");
        employee2.setFirstName("Jane");
        employee2.setId(1);
        employee2.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link EmployeeRestController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees");
        MockMvcBuilders.standaloneSetup(employeeRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EmployeeRestController#deleteEmployee(int)}
     */
    @Test
    void testDeleteEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        doNothing().when(employeeService).deleteById(anyInt());
        when(employeeService.findById(anyInt())).thenReturn(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employees/{employeeId}", 1);
        MockMvcBuilders.standaloneSetup(employeeRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted employee id - 1"));
    }

    /**
     * Method under test: {@link EmployeeRestController#getEmployee(int)}
     */
    @Test
    void testGetEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        when(employeeService.findById(anyInt())).thenReturn(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employees/{employeeId}", 1);
        MockMvcBuilders.standaloneSetup(employeeRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link EmployeeRestController#updateEmployee(Employee)}
     */
    @Test
    void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        when(employeeService.save(Mockito.<Employee>any())).thenReturn(employee);

        Employee employee2 = new Employee();
        employee2.setEmail("jane.doe@example.org");
        employee2.setFirstName("Jane");
        employee2.setId(1);
        employee2.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(employee2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\"}"));
    }
}

