package com.UST.UserBook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="User")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
}
