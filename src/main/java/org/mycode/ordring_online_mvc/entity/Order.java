package org.mycode.ordring_online_mvc.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false, length = 255)
    private String orderName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column in 'orders' table referencing 'users'
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;
//
//    @ManyToOne
//    @JoinColumn(name = "ordered_by", nullable = false)
//    private User orderedBy;
}
