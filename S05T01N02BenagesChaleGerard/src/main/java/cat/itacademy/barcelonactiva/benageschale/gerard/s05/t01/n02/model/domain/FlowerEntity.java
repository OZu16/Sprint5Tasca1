package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "flowers")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String flowerName;
    @Column(name = "country")
    private String flowerCountry;

    public FlowerEntity() {
    }

    public FlowerEntity(String flowerName, String flowerCountry) {
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public String getFlowerCountry() {
        return flowerCountry;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public void setFlowerCountry(String flowerCountry) {
        this.flowerCountry = flowerCountry;
    }
}
