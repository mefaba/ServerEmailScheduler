package org.emailreportmanager.entities.configurations;

import org.emailreportmanager.entities.configurations.DataSourceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceConfigurationRepository extends JpaRepository<DataSourceConfiguration,Long> {

}

