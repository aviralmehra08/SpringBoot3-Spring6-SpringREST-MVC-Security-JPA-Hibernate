package com.luv2code.springboot.crudDemo.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luv2code.springboot.crudDemo.dao.EmployeeRepository;
import com.luv2code.springboot.crudDemo.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    /**
     * Method under test: {@link EmployeeServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualFindAllResult = employeeServiceImpl.findAll();
        assertSame(employeeList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
        when(employeeRepository.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> employeeServiceImpl.findAll());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#findById(int)}
     */
    @Test
    void testFindById() {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertSame(employee, employeeServiceImpl.findById(1));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#findById(int)}
     */
    @Test
    void testFindById2() {
        when(employeeRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> employeeServiceImpl.findById(1));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#findById(int)}
     */
    @Test
    void testFindById3() {
        when(employeeRepository.findById(Mockito.<Integer>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> employeeServiceImpl.findById(1));
        verify(employeeRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#save(Employee)}
     */
    @Test
    void testSave() {
        Employee employee = new Employee();
        employee.setEmail("jane.doe@example.org");
        employee.setFirstName("Jane");
        employee.setId(1);
        employee.setLastName("Doe");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

        Employee theEmployee = new Employee();
        theEmployee.setEmail("jane.doe@example.org");
        theEmployee.setFirstName("Jane");
        theEmployee.setId(1);
        theEmployee.setLastName("Doe");
        assertSame(employee, employeeServiceImpl.save(theEmployee));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#save(Employee)}
     */
    @Test
    void testSave2() {
        when(employeeRepository.save(Mockito.<Employee>any())).thenThrow(new RuntimeException("foo"));

        Employee theEmployee = new Employee();
        theEmployee.setEmail("jane.doe@example.org");
        theEmployee.setFirstName("Jane");
        theEmployee.setId(1);
        theEmployee.setLastName("Doe");
        assertThrows(RuntimeException.class, () -> employeeServiceImpl.save(theEmployee));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteById(int)}
     */
    @Test
    void testDeleteById() {
        doNothing().when(employeeRepository).deleteById(Mockito.<Integer>any());
        employeeServiceImpl.deleteById(1);
        verify(employeeRepository).deleteById(Mockito.<Integer>any());
        assertTrue(employeeServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteById(int)}
     */
    @Test
    void testDeleteById2() {
        doThrow(new RuntimeException("foo")).when(employeeRepository).deleteById(Mockito.<Integer>any());
        assertThrows(RuntimeException.class, () -> employeeServiceImpl.deleteById(1));
        verify(employeeRepository).deleteById(Mockito.<Integer>any());
    }
}

