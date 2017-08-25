package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_1__create_initial_schema implements SpringJdbcMigration {

  @Override
  public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
    jdbcTemplate.execute("CREATE TABLE menu (id bigint(20) NOT NULL AUTO_INCREMENT,"
        + " name varchar(255) DEFAULT NULL,"
        + " price double DEFAULT NULL,"
        + " PRIMARY KEY (id))"
        + " ENGINE=InnoDB DEFAULT CHARSET=utf8;");
  }
}
