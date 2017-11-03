DROP TABLE `cabaredb`.`employee_role`;

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  KEY `FKo0j1qho7koleb1op32qsxrmog` (`role_id`),
  CONSTRAINT `FKo7rvk7ejtx29vru9cyhf7o68a` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKo0j1qho7koleb1op32qsxrmog` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

