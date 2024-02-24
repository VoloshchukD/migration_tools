package by.valashchuk.migration_tools.repository;

import by.valashchuk.migration_tools.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findAll();

}
