package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.exceptions.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    FlowerRepository flowerRepository;

    @Override
    public FlowerDTO flowerConverter(FlowerEntity flower) {
        return new FlowerDTO(flower.getId(), flower.getFlowerName(), flower.getFlowerCountry());
    }

    @Override
    public void saveFlower(FlowerEntity flower) {
        flowerRepository.save(flower);
    }

    @Override
    public List<FlowerEntity> findAllBy() {
        return flowerRepository.findAll();
    }

    @Override
    public FlowerEntity findFlower(long id) throws FlowerNotFoundException {
        return flowerRepository.findById(id)
                .orElseThrow(() -> new FlowerNotFoundException("Flower no existent"));
    }

    @Override
    public void deleteFlower(long id) throws FlowerNotFoundException {
        Optional<FlowerEntity> flower = flowerRepository.findById(id);
        if (flower.isPresent()) {
            flowerRepository.deleteById(id);
        } else {
            throw new FlowerNotFoundException("Flower no existent");
        }
    }

    @Override
    public void updateFlower(FlowerEntity flower) {
        flowerRepository.save(flower);
    }

}