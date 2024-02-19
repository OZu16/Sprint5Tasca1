package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.service;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.exceptions.SucursalNotFoundException;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public SucursalDTO sucursalConverter(Sucursal sucursal) {
        return new SucursalDTO(sucursal.getId(), sucursal.getSucursalName(), sucursal.getSucursalCountry());
    }

    @Override
    public void saveSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> findAllBy() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findSucursal(long id) throws SucursalNotFoundException {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException("Sucursal no existent"));
    }

    @Override
    public void deleteSucursal(long id) throws SucursalNotFoundException {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if (sucursal.isPresent()) {
            sucursalRepository.deleteById(id);
        } else {
            throw new SucursalNotFoundException("Sucursal no existent");
        }
    }

    @Override
    public void updateSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

}