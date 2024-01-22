package com.base.springsecurity.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String review;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private User user;

}
