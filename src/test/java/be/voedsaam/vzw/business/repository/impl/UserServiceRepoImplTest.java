package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Integration test uses test data brought in via bootstrap package
 */

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserServiceRepoImplTest {
    @Autowired
    private UserServiceRepoImpl classUnderTest;
    private User employee;
    private User volunteer;

    @Before
    public void setUp() throws Exception {
        employee = new Employee("an","loquet","an.loquet@outlook.be","123456");
        employee.setPassword("password");
        employee.setRole(Role.COORDINATOR);
        volunteer = new Volunteer("xander","herman","xander.herman@outlook.be","123456");
        volunteer.setPassword("password");
        volunteer.setRole(Role.VOLUNTEER);


    }
@Test
public void listAll(){
    List<User> users = (List<User>) classUnderTest.listAll();
    assertEquals(8,users.size());
}

    @Test
    public void getById() throws Exception {
        employee = classUnderTest.saveOrUpdate(employee);
        assertEquals(employee,classUnderTest.getById(employee.getId()));
        volunteer = classUnderTest.saveOrUpdate(employee);
        assertEquals(volunteer,classUnderTest.getById(volunteer.getId()));
    }


    @Test
    public void delete() throws Exception {
        User usertoDelete = new Employee("jan","l","jan@email.com","123456");
        usertoDelete = classUnderTest.saveOrUpdate(usertoDelete);
        Long id = usertoDelete.getId();
       classUnderTest.delete(usertoDelete.getId());
        assertNull(classUnderTest.getById(id));
    }

    @Test
    public void findByEmail() throws Exception {
        getById();
        assertEquals(employee, classUnderTest.findByEmail(employee.getEmail()));
        assertEquals(volunteer, classUnderTest.findByEmail(volunteer.getEmail()));
    }

    @Test
    public void listVolunteerByRole() throws Exception {
        assertEquals(1, classUnderTest.listVolunteerByRole(Role.VOLUNTEER).size());
        assertEquals(1, classUnderTest.listVolunteerByRole(Role.DRIVER).size());
        assertEquals(1, classUnderTest.listVolunteerByRole(Role.DEPOTHELP).size());
        assertEquals(1, classUnderTest.listVolunteerByRole(Role.ATTENDEE).size());
    }
    @Test(expected = UnsupportedOperationException.class)
    public void listVolunteerByRoleWrongRoleException() throws Exception {
        assertEquals(1, classUnderTest.listVolunteerByRole(Role.COORDINATOR).size());
    }


    @Test
    public void listEmployeeByRole() throws Exception {
        assertEquals(3, classUnderTest.listEmployeeByRole(Role.COORDINATOR).size());
        assertEquals(1, classUnderTest.listEmployeeByRole(Role.LOGISTICS).size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void listEmployeeByRoleWrongRoleException() throws Exception {
        assertEquals(1, classUnderTest.listEmployeeByRole(Role.DRIVER).size());
    }

    @Test
    public void getVolunteerById() throws Exception {
        volunteer = classUnderTest.saveOrUpdate(volunteer);
        assertEquals(volunteer,classUnderTest.getById(volunteer.getId()));
        assertEquals(volunteer, classUnderTest.getVolunteerById(volunteer.getId()));
    }

    @Test
    public void getEmployeeById() throws Exception {
        employee = classUnderTest.saveOrUpdate(employee);
        assertEquals(employee,classUnderTest.getById(employee.getId()));
        assertEquals(employee, classUnderTest.getEmployeeById(employee.getId()));
    }

    @Test
    public void listAllEmployees() throws Exception {
        List<Employee> employees = classUnderTest.listAllEmployees();
        assertEquals(4, employees.size());
    }

    @Test
    public void listAllVolunteers() throws Exception {
        List<Volunteer> volunteers = classUnderTest.listAllVolunteers();
        assertEquals(4, volunteers.size());
    }

}