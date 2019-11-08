package com.enblom.intrastat

import org.springframework.orm.jpa.JpaTransactionManager
import javax.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.transaction.PlatformTransactionManager
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
@EnableJpaRepositories(entityManagerFactoryRef = "reportEntityManagerFactory",
        transactionManagerRef = "reportTransactionManager",
        basePackages = ["com.enblom.intrastat.report.repo"])
class ReportDbConfig {

    @Bean(name = ["reportDataSource"])
    @ConfigurationProperties(prefix = "report.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["reportEntityManagerFactory"])
    fun reportEntityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("reportDataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(dataSource)
                .packages("com.enblom.intrastat.report.domain")
                .persistenceUnit("report")
                .build()
    }

    @Bean(name = ["reportTransactionManager"])
    fun reportTransactionManager(
            @Qualifier("reportEntityManagerFactory") reportEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(reportEntityManagerFactory)
    }
}