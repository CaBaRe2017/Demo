CREATE TABLE `departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `discounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activated` bit(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `discount_card` varchar(255) DEFAULT NULL,
  `discount_size` int(11) DEFAULT NULL,
  `emitted` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `total_paid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dish_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `expenditure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expenditure_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pay_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `position_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `raw_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `salaries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_salary` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sale_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_type_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stocktake` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actual_quantity` float DEFAULT NULL,
  `stocktake_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contract_number` varchar(255) DEFAULT NULL,
  `contract_type` varchar(255) DEFAULT NULL,
  `deferral` int(11) DEFAULT NULL,
  `end_contract` date DEFAULT NULL,
  `start_contract` date DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `write_off_reasons` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dishes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dish_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb4o7dmpl17soep9keptqnbdj4` (`dish_category_id`),
  CONSTRAINT `FKb4o7dmpl17soep9keptqnbdj4` FOREIGN KEY (`dish_category_id`) REFERENCES `dish_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fired_day` date DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `start_day` date DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  `salary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo1uiovdf54iyqrovb6soq8yl6` (`department_id`),
  KEY `FKbc8rdko9o9n1ri9bpdyxv3x7i` (`position_id`),
  KEY `FK8b81f6ta7qh2doaoi2jpn4owd` (`salary_id`),
  CONSTRAINT `FK8b81f6ta7qh2doaoi2jpn4owd` FOREIGN KEY (`salary_id`) REFERENCES `salaries` (`id`),
  CONSTRAINT `FKbc8rdko9o9n1ri9bpdyxv3x7i` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `FKo1uiovdf54iyqrovb6soq8yl6` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  UNIQUE KEY `UK_7ffk4rmk25rah5piv18v67007` (`role_id`),
  CONSTRAINT `FKo7rvk7ejtx29vru9cyhf7o68a` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKo0j1qho7koleb1op32qsxrmog` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pay_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr2gjaq55jp3fi5tktyh5cibh9` (`supplier_id`),
  CONSTRAINT `FKr2gjaq55jp3fi5tktyh5cibh9` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `write_off_calculations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `write_off_date` date DEFAULT NULL,
  `write_off_quantity` double DEFAULT NULL,
  `write_off_reason_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2h64ew9v2hpv5bycqmx6ul9af` (`write_off_reason_id`),
  CONSTRAINT `FK2h64ew9v2hpv5bycqmx6ul9af` FOREIGN KEY (`write_off_reason_id`) REFERENCES `write_off_reasons` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `write_off_positions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price_of_last_supply` bigint(20) DEFAULT NULL,
  `write_off_date` date DEFAULT NULL,
  `write_off_quantity` float DEFAULT NULL,
  `write_off_reason_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1s2u9du4pyrx371bxpufpowqi` (`write_off_reason_id`),
  CONSTRAINT `FK1s2u9du4pyrx371bxpufpowqi` FOREIGN KEY (`write_off_reason_id`) REFERENCES `write_off_reasons` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `outgo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost_quantity` int(11) DEFAULT NULL,
  `outgo_cost` bigint(20) DEFAULT NULL,
  `outgo_date` date DEFAULT NULL,
  `outgo_title` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `expenditure_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKckatmsgi2lua2d95njljtuous` (`employee_id`),
  KEY `FKoj2ueh1u8d0fr8esx3t9ouw0s` (`expenditure_id`),
  CONSTRAINT `FKoj2ueh1u8d0fr8esx3t9ouw0s` FOREIGN KEY (`expenditure_id`) REFERENCES `expenditure` (`id`),
  CONSTRAINT `FKckatmsgi2lua2d95njljtuous` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `raw_materials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchase_date` date DEFAULT NULL,
  `raw_price` bigint(20) DEFAULT NULL,
  `raw_quantity` float DEFAULT NULL,
  `raw_title` varchar(255) DEFAULT NULL,
  `unit_of_measure` varchar(255) DEFAULT NULL,
  `raw_category_id` bigint(20) DEFAULT NULL,
  `stocktake_id` bigint(20) DEFAULT NULL,
  `write_off_position_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd1v7qrk6m17v1cxt465v9s6vh` (`raw_category_id`),
  KEY `FK5p0x168n6ydqtu90ppgoo5wqy` (`stocktake_id`),
  KEY `FKhf3jj6kcln1i7g6fico19bqn5` (`write_off_position_id`),
  CONSTRAINT `FKhf3jj6kcln1i7g6fico19bqn5` FOREIGN KEY (`write_off_position_id`) REFERENCES `write_off_positions` (`id`),
  CONSTRAINT `FK5p0x168n6ydqtu90ppgoo5wqy` FOREIGN KEY (`stocktake_id`) REFERENCES `stocktake` (`id`),
  CONSTRAINT `FKd1v7qrk6m17v1cxt465v9s6vh` FOREIGN KEY (`raw_category_id`) REFERENCES `raw_categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `raw_material_supplier` (
  `raw_material_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`raw_material_id`,`supplier_id`),
  KEY `FKjox0ptpavgwf5gjrtbaq27urw` (`supplier_id`),
  CONSTRAINT `FK2digxp0fyjvg7lppu832n9uc1` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`),
  CONSTRAINT `FKjox0ptpavgwf5gjrtbaq27urw` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bills (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`bill_date` date DEFAULT NULL,
`bill_number` int(11) DEFAULT NULL,
`number_of_persons` int(11) DEFAULT NULL,
`opened` bit(1) NOT NULL DEFAULT b'1',
`money_paid` bigint(20) DEFAULT NULL,
`table_number` int(11) DEFAULT NULL,
`discount_id` bigint(20) DEFAULT NULL,
`employee_id` bigint(20) DEFAULT NULL,
`pay_status_id` bigint(20) DEFAULT NULL,
`pay_type_id` bigint(20) DEFAULT NULL,
`sale_type_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FKairg2hfy59g3p91rojh6bdnd9` (`discount_id`),
KEY `FKsiblexg86kykrwmg5trslfijl` (`employee_id`),
KEY `FK5tmq2bohku7xvldcg2tp81oqy` (`pay_status_id`),
KEY `FKdyx197op9m7l42i52ng29iyap` (`pay_type_id`),
KEY `FKeeb3ahtb3sjrc07tkuvimhhmh` (`sale_type_id`),
CONSTRAINT `FKeeb3ahtb3sjrc07tkuvimhhmh` FOREIGN KEY (`sale_type_id`) REFERENCES `sale_type` (`id`),
CONSTRAINT `FK5tmq2bohku7xvldcg2tp81oqy` FOREIGN KEY (`pay_status_id`) REFERENCES `pay_status` (`id`),
CONSTRAINT `FKairg2hfy59g3p91rojh6bdnd9` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`),
CONSTRAINT `FKdyx197op9m7l42i52ng29iyap` FOREIGN KEY (`pay_type_id`) REFERENCES `pay_type` (`id`),
CONSTRAINT `FKsiblexg86kykrwmg5trslfijl` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  `dish_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo4d480psixwe3vxsdpf89tcse` (`bill_id`),
  KEY `FKn06bdypik73hotpxvefsrtn77` (`dish_id`),
  CONSTRAINT `FKn06bdypik73hotpxvefsrtn77` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FKo4d480psixwe3vxsdpf89tcse` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `raw_material_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKakf4s4yjxafbcaf4i3fk96l2h` (`raw_material_id`),
  CONSTRAINT `FKakf4s4yjxafbcaf4i3fk96l2h` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `calculation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dish_out` int(11) DEFAULT NULL,
  `dish_price` bigint(20) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `quantity_for_dish` float DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `dish_id` bigint(20) DEFAULT NULL,
  `raw_material_id` bigint(20) DEFAULT NULL,
  `stock_id` bigint(20) DEFAULT NULL,
  `write_off_calculation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7p7iby16k8b37a2bofii7nde1` (`dish_id`),
  KEY `FKt3x805smtuda6imcxvqjgp1ea` (`raw_material_id`),
  KEY `FKtl8n2ach3syusvb3hhbd4nbr4` (`stock_id`),
  KEY `FKdbrox6hvsytaew0m7y4hp5o2j` (`write_off_calculation_id`),
  CONSTRAINT `FKdbrox6hvsytaew0m7y4hp5o2j` FOREIGN KEY (`write_off_calculation_id`) REFERENCES `write_off_calculations` (`id`),
  CONSTRAINT `FK7p7iby16k8b37a2bofii7nde1` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FKt3x805smtuda6imcxvqjgp1ea` FOREIGN KEY (`raw_material_id`) REFERENCES `raw_materials` (`id`),
  CONSTRAINT `FKtl8n2ach3syusvb3hhbd4nbr4` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
