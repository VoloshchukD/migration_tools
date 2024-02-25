ALTER TABLE employee
ADD CONSTRAINT fk_company_id
FOREIGN KEY (company_id) REFERENCES company(id);