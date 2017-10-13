ALTER TABLE `cabaredb`.`order_items`
ADD COLUMN `comments` VARCHAR(400) NULL AFTER `dish_id`,
ADD COLUMN `order_time` DATETIME NULL AFTER `comments`;

ALTER TABLE `cabaredb`.`bills`
CHANGE COLUMN `bill_date` `bill_date` DATETIME NULL DEFAULT NULL ;
