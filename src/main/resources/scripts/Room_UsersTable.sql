CREATE TABLE IF NOT EXISTS `room_users` (
                                            `room_id` INT NOT NULL,
                                            `user_id` INT NOT NULL,
                                            `is_room_admin` BOOLEAN NOT NULL DEFAULT FALSE,
                                            PRIMARY KEY (`room_id`, `user_id`),
    FOREIGN KEY (`room_id`) REFERENCES `rooms`(`room_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
    );
