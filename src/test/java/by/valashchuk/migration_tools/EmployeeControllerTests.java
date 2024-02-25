package by.valashchuk.migration_tools;

import by.valashchuk.migration_tools.repository.CompanyRepository;
import by.valashchuk.migration_tools.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void testAddEmployeeToCompany() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");

        Company company = new Company();
        company.setId(2L);
        company.setName("ABC Inc");

        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(companyRepository.findById(2L)).thenReturn(Optional.of(company));

        mockMvc.perform(MockMvcRequestBuilders.post("/employees/1?companyId=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company.name", Matchers.is("ABC Inc")));

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(companyRepository, Mockito.times(1)).findById(2L);
    }

}
