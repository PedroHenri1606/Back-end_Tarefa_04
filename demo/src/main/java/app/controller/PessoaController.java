package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.PessoaDTO;
import app.service.PessoaService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	@GetMapping(value = "/buscar")
	public ResponseEntity<PessoaDTO> buscarPorId(@RequestParam("id") final Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPorId(id));
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error " + e.getMessage());
		}
	}

	@GetMapping(value = "/listar")
	private ResponseEntity<List<PessoaDTO>> listAll(){
		try {		
			List<PessoaDTO> lista = pessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/cadastrar")
	private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
		try {		
			PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/editar")
	public ResponseEntity<PessoaDTO> editar(@RequestParam("id") final Long id, @RequestBody final PessoaDTO pessoaDTO){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pessoaService.editar(id,pessoaDTO));

		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
		}
	}

	@DeleteMapping(value = "/deletar")
	public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
		try {
			pessoaService.deletar(id);
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error" + e.getMessage());
		}
	}
	
	@GetMapping("erro")
	private ResponseEntity<List<PessoaDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
