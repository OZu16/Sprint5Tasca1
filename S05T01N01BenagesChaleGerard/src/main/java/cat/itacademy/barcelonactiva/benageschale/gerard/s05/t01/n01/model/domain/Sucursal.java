package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursals")
public class Sucursal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String sucursalName;
    @Column(name = "country")
    private String sucursalCountry;

    public Sucursal() {
    }

    public Sucursal(String sucursalName, String sucursalCountry) {
        this.sucursalName = sucursalName;
        this.sucursalCountry = sucursalCountry;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSucursalName() {
        return sucursalName;
    }

    public String getSucursalCountry() {
        return sucursalCountry;
    }

    public void setSucursalName(String sucursalName) {
        this.sucursalName = sucursalName;
    }

    public void setSucursalCountry(String sucursalCountry) {
        this.sucursalCountry = sucursalCountry;
    }
}
