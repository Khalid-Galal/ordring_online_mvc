-- Table for storing users
CREATE TABLE Users (
                       userID BIGINT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       isAdmin BOOLEAN DEFAULT FALSE
);

-- Table for storing orders associated with users
CREATE TABLE Orders (
                        orderID BIGINT PRIMARY KEY,
                        itemName VARCHAR(255) NOT NULL,
                        quantity INT NOT NULL,
                        price DOUBLE PRECISION NOT NULL,
                        note TEXT,
                        userID BIGINT,
                        FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
);

-- Table for storing rooms
CREATE TABLE Rooms (
                       roomID BIGINT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL UNIQUE,
                       roomPassword VARCHAR(255) NOT NULL
);

-- Junction table to establish many-to-many relationship between users and rooms
-- and determine each room's admin
CREATE TABLE RoomUsers (
                           roomID BIGINT,
                           userID BIGINT,
                           isAdmin BOOLEAN DEFAULT FALSE,
                           PRIMARY KEY (roomID, userID),
                           FOREIGN KEY (roomID) REFERENCES Rooms(roomID) ON DELETE CASCADE,
                           FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
                           CONSTRAINT chk_single_admin CHECK (isAdmin = TRUE OR
                                                              (SELECT COUNT(*) FROM RoomUsers WHERE roomID = roomID AND isAdmin = TRUE) <= 1)
);

