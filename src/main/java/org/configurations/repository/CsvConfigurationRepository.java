package org.configurations.repository;

import org.configurations.CsvConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CsvConfigurationRepository extends JpaRepository<CsvConfiguration,Long> {

}
