package app.service;

import app.dto.CarroDTO;
import app.entity.Carro;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    public CarroRepository repository;


    public CarroDTO buscarPorId(Long id){

        Carro carro = repository.findById(id).orElse(null);

        CarroDTO carroDTO = toCarroDTO(carro);

        return carroDTO;
    }

    public List<CarroDTO> listAll(){
        List<Carro> lista = repository.findAll();
        List<CarroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toCarroDTO(lista.get(i)));

        return listaDTO;
    }

    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = this.toCarro(carroDTO);

        Carro carroSalvo = repository.save(carro);

        return this.toCarroDTO(carroSalvo);
    }

    public CarroDTO editar(Long id,CarroDTO carroNovoDTO){

        Carro carro = repository.findById(id).orElse(null);

        carro.setMarca(carroNovoDTO.getMarca());
        carro.setModelo(carroNovoDTO.getMarca());

        return carroNovoDTO = toCarroDTO(carro);
    }

    public String deletar(Long id){
        repository.deleteById(id);
        return "Deletado com sucesso!";
    }

    private CarroDTO toCarroDTO(Carro carro) {
        CarroDTO carroDTO = new CarroDTO();

        carroDTO.setId(carro.getId());
        carroDTO.setModelo(carro.getModelo());
        carroDTO.setMarca(carro.getMarca());
        return carroDTO;
    }

    private Carro toCarro(CarroDTO carroDTO) {

        Carro carro = new Carro();

        carro.setId(carroDTO.getId());
        carro.setModelo(carroDTO.getModelo());
        carro.setMarca(carroDTO.getMarca());
        return carro;
    }
}
