CREATE TABLE IF NOT EXISTS `rooms` (
    `room_id` INT AUTO_INCREMENT PRIMARY KEY,
    `room_name` VARCHAR(255) NOT NULL,
    `created_by` INT NOT NULL,
    FOREIGN KEY (`created_by`) REFERENCES `users`(`user_id`)
    );
