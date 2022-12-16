package com.newnext.newapp2.entities;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String username;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="authority_id"))	
	private Collection<Authority> authorities = new ArrayList<>();
	public void addAuthority(Authority a) {
		this.authorities.add(a);
//		a.getUsers().add(this);
	}
	public void removetag(Integer tagid) {
		Authority au = authorities.stream().filter(a -> a.getId() == tagid).findFirst().orElse(null);
		if(au != null) {
			this.authorities.remove(au);
//			au.getUsers().remove(this);
		};
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
