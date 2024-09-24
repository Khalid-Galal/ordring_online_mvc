CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_name` VARCHAR(255) NOT NULL,
    `quantity` INT NOT NULL,
--     `room_id` INT NOT NULL,
-- --     `ordered_by` INT NOT NULL,
--     FOREIGN KEY (`room_id`) REFERENCES `rooms`(`room_id`),
--     FOREIGN KEY (`ordered_by`) REFERENCES `users`(`user_id`)
    );
