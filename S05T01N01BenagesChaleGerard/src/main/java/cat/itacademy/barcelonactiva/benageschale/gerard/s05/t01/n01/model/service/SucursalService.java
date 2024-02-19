package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.service;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.exceptions.SucursalNotFoundException;

import java.util.List;

public interface SucursalService {

    public SucursalDTO sucursalConverter(Sucursal sucursal);
    public void saveSucursal(Sucursal sucursal);
    public List<Sucursal> findAllBy();
    public Sucursal findSucursal(long id) throws SucursalNotFoundException;
    public void deleteSucursal(long id) throws SucursalNotFoundException;
    public void updateSucursal(Sucursal sucursal);

}

