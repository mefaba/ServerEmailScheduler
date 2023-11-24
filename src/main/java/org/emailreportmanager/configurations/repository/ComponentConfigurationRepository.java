package org.emailreportmanager.configurations.repository;

import org.emailreportmanager.configurations.ComponentConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentConfigurationRepository extends JpaRepository<ComponentConfiguration,Long> {
}
