package com.cooksys.groupfinal.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue
	private Long id;

  @Embedded
  private Credentials credentials;
	
  @Embedded
  private Profile profile;
	
	private boolean active;
	
	private boolean admin;
	
	private String status = "PENDING";
	
	@OneToMany(mappedBy = "author")
	@EqualsAndHashCode.Exclude
	private Set<Announcement> announcements = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "company_employees",
			joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id")
	)
	@EqualsAndHashCode.Exclude
	private Set<Company> companies = new HashSet<>();
	
	@ManyToMany(mappedBy = "teammates")
	@EqualsAndHashCode.Exclude
	private Set<Team> teams = new HashSet<>();

}
