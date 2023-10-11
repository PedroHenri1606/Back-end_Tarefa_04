package app.controller;

import app.dto.CarroDTO;
import app.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    public CarroService service;

    @GetMapping(value = "/buscar")
    public ResponseEntity<CarroDTO> buscarPorId(@RequestParam("id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CarroDTO>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listAll());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CarroDTO> salvar(@RequestBody CarroDTO carroDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(carroDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<CarroDTO> editar(@RequestParam("id") final Long id, @RequestBody final CarroDTO carroNovoDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,carroNovoDto));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deletar(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }
}
