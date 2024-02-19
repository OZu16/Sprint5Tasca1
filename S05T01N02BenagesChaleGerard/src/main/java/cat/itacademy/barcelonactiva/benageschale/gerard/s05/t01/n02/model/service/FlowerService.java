package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.exceptions.FlowerNotFoundException;

import java.util.List;

public interface FlowerService {

    public FlowerDTO flowerConverter(FlowerEntity flower);
    public void saveFlower(FlowerEntity flower);
    public List<FlowerEntity> findAllBy();
    public FlowerEntity findFlower(long id) throws FlowerNotFoundException;
    public void deleteFlower(long id) throws FlowerNotFoundException;
    public void updateFlower(FlowerEntity flower);

}

