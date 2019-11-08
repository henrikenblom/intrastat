package com.enblom.intrastat

import org.springframework.orm.jpa.JpaTransactionManager
import javax.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = ["com.enblom.intrastat.gks.repo"])
class GksDbConfig {

    @Primary
    @Bean(name = ["dataSource"])
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean(name = ["entityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("dataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(dataSource)
                .packages("com.enblom.intrastat.gks.domain")
                .persistenceUnit("gks")
                .build()
    }

    @Primary
    @Bean(name = ["transactionManager"])
    fun transactionManager(
            @Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}