package app.controller;

import app.dto.LivroDTO;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    public LivroService service;

    @GetMapping(value = "/buscar")
    public ResponseEntity<LivroDTO> buscarPorId(@RequestParam("id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<LivroDTO>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.listAll());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<LivroDTO> salvar(@RequestBody LivroDTO livroDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(livroDTO));

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<LivroDTO> editar(@RequestParam("id") final Long id, @RequestBody final LivroDTO livroNovoDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,livroNovoDto));

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
