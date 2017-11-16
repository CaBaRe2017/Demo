ALTER TABLE `cabaredb`.`dishes`
ADD COLUMN `start_day` INT NULL DEFAULT 1 AFTER `price`,
ADD COLUMN `end_day` INT NULL DEFAULT 366 AFTER `start_day`;
