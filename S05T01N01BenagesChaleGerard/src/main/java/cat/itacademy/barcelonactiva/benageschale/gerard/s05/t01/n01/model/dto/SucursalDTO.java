package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.dto;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain.Sucursal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SucursalDTO {

    List<String> countriesEU = Arrays.asList("Spain", "France", "Germany", "Italy", "United Kingdom", "Portugal", "Netherlands", "Greece", "Sweden", "Poland", "Austria", "Belgium", "Czech Republic", "Denmark", "Finland", "Hungary", "Ireland", "Luxembourg", "Norway", "Switzerland", "Bulgaria", "Croatia", "Estonia", "Latvia", "Lithuania", "Romania", "Slovakia", "Slovenia", "Cyprus", "Malta");

    private Long id;
    private String sucursalName;
    private String sucursalCountry;
    private String sucursalType;

    public SucursalDTO() {
    }

    public SucursalDTO(Long id, String sucursalName, String sucursalCountry) {
        this.id = id;
        this.sucursalName = sucursalName;
        this.sucursalCountry = sucursalCountry;
        this.sucursalType = countryFromEU();
    }

    public long getId() {
        return id;
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

    public String getSucursalType() {
        return sucursalType;
    }

    public String countryFromEU() {

        Optional<String> country = countriesEU.stream()
                .filter(c -> c.equalsIgnoreCase(this.sucursalCountry))
                .findFirst();

        return (country.isPresent()) ? "EU" : "out of EU";
    }

    @Override
    public String toString() {
        return "SucursalDTO{" +
                "id=" + id +
                ", sucursalName='" + sucursalName + '\'' +
                ", sucursalCountry='" + sucursalCountry + '\'' +
                ", sucursalType='" + sucursalType + '\'' +
                '}';
    }
}