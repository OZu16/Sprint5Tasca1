package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FlowerDTO {
    List<String> countriesEU = Arrays.asList("Spain", "France", "Germany", "Italy", "United Kingdom", "Portugal", "Netherlands", "Greece", "Sweden", "Poland", "Austria", "Belgium", "Czech Republic", "Denmark", "Finland", "Hungary", "Ireland", "Luxembourg", "Norway", "Switzerland", "Bulgaria", "Croatia", "Estonia", "Latvia", "Lithuania", "Romania", "Slovakia", "Slovenia", "Cyprus", "Malta");

    private Long id;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    public FlowerDTO() {
    }

    public FlowerDTO(Long id, String flowerName, String flowerCountry) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.flowerType = countryFromEU();
    }

    public long getId() {
        return id;
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

    public String getFlowerType() {
        return flowerType;
    }

    public String countryFromEU() {

        Optional<String> country = countriesEU.stream()
                .filter(c -> c.equalsIgnoreCase(this.flowerCountry))
                .findFirst();

        return (country.isPresent()) ? "EU" : "out of EU";
    }

    @Override
    public String toString() {
        return "FlowerDTO{" +
                "id=" + id +
                ", flowerName='" + flowerName + '\'' +
                ", flowerCountry='" + flowerCountry + '\'' +
                ", flowerType='" + flowerType + '\'' +
                '}';
    }
}