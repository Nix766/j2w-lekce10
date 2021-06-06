package cz.czechitas.java2webapps.lekce10.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
public class Rodic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String jmeno;
  private String prijmeni;
  private String email;
  private String telefon;

  @ManyToMany
  @JoinTable(
          name = "student_rodic",
          joinColumns = @JoinColumn(name = "rodic_id"),
          inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  @OrderBy(value="prijmeni, jmeno")
  private List<Student> deti;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    this.jmeno = jmeno;
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    this.prijmeni = prijmeni;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefon() {
    return telefon;
  }

  public void setTelefon(String telefon) {
    this.telefon = telefon;
  }

  public List<Student> getDeti() {
    return deti;
  }

  public void setDeti(List<Student> deti) {
    this.deti = deti;
  }

  @Override
  public String toString() {
    return String.format("%s %s ", jmeno, prijmeni) + ", " + email + ", " + telefon;
  }


}
