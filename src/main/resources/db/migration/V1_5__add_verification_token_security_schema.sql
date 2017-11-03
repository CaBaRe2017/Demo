ALTER TABLE `cabaredb`.`employee`
  ADD COLUMN `enabled` BIT(1) NOT NULL AFTER `email`;

CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VERIFY_EMPLOYEE` (`employee_id`),
  CONSTRAINT `FK_VERIFY_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_privilege` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FK_RP_PRIVILEGE_ID` (`privilege_id`),
  KEY `FK_RP_ROLE_ID` (`role_id`),
  CONSTRAINT `FK_RP_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES roles (`id`),
  CONSTRAINT `FK_RP_PRIVILEGE_ID` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
