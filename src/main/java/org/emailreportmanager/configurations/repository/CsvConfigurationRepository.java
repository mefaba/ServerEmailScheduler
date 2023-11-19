package org.emailreportmanager.configurations.repository;

import org.emailreportmanager.configurations.CsvConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvConfigurationRepository extends JpaRepository<CsvConfiguration,Long> {

}

