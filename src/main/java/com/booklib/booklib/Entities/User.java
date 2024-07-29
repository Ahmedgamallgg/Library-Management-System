package com.booklib.booklib.Entities;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String name;

	@Column(unique = true)
	public String username;

	public String password;

	public String email;

	@Enumerated(EnumType.STRING)
	public UserRole userRole;

}
