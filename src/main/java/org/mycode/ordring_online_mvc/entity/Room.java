package org.mycode.ordring_online_mvc.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String roomPassword;

    // Constructors, getters, and setters
    public Room() {}

    // include getters and setters for all fields
}
