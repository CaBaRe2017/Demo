package com.ua.cabare.models;

import com.ua.cabare.domain.Position;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stuffs")
public class Stuff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "position")
  private Position position;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "stuff")
  private Set<Bill> bills;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "stuff_roles",
      joinColumns = {@JoinColumn(name = "stuff_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private Set<Role> roles;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;
  @Column(name = "start_day")
  private LocalDate startDay;
  @Column(name = "fired_day")
  private LocalDate firedDay;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "salary_id")
  private Salary salary;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
