package org.emailreportmanager.configurations.repository;

import org.emailreportmanager.configurations.DataSourceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceConfigurationRepository extends JpaRepository<DataSourceConfiguration,Long> {

}

