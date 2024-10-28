-- Insert sample data into the Users table
INSERT INTO Users (userID, username, password, isAdmin) VALUES
                                                            (1, 'alice', 'pass123', FALSE),
                                                            (2, 'bob', 'pass456', TRUE),
                                                            (3, 'charlie', 'pass789', FALSE);

-- Insert sample data into the Orders table
INSERT INTO Orders (orderID, itemName, quantity, price, note, userID) VALUES
                                                                          (1, 'Laptop', 1, 1200.50, 'Urgent delivery', 1),
                                                                          (2, 'Mouse', 2, 25.75, NULL, 1),
                                                                          (3, 'Keyboard', 1, 45.00, 'Need RGB', 2);

-- Insert sample data into the Rooms table
INSERT INTO Rooms (roomID, name, roomPassword) VALUES
                                                   (1, 'Room101', 'secr3t'),
                                                   (2, 'Room102', 'keepout');

-- Insert sample data into the RoomUsers junction table
-- As Room is not to be connected, no entries are added here as per your request
-- To provide a comprehensive example, the following would be the way to do it if needed:
/*
INSERT INTO RoomUsers (roomID, userID, isAdmin) VALUES
(1, 1, TRUE),
(1, 2, FALSE),
(2, 3, TRUE);
*/

-- Commit changes (Uncomment the following line if your SQL environment requires it)
-- COMMIT;
