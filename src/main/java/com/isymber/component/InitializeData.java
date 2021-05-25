package com.isymber.component;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class InitializeData {

    private final DataSource dataSource;

    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(true, false, "UTF-8", new ClassPathResource("data.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
