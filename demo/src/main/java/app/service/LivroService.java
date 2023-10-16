package app.service;

import app.dto.LivroDTO;
import app.entity.Livro;
import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    public LivroRepository repository;

    public LivroDTO buscarPorId(Long id){

        Livro livro = repository.findById(id).orElse(null);

        LivroDTO livroDTO = toLivroDTO(livro);

        return livroDTO;
    }

    public List<LivroDTO> listAll(){
        List<Livro> lista = repository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toLivroDTO(lista.get(i)));

        return listaDTO;
    }

    public LivroDTO save(LivroDTO livroDto){
        Livro livro = this.toLivro(livroDto);

        Livro livroSalvo = repository.save(livro);

        return this.toLivroDTO(livroSalvo);
    }

    public LivroDTO editar(Long id, LivroDTO livroNovoDTO){

        Livro livro = repository.findById(id).orElse(null);

        livro.setTitulo(livroNovoDTO.getTitulo());
        livro.setAutor(livroNovoDTO.getAutor());

        Livro livroSalvo = repository.save(livro);

        return livroNovoDTO = toLivroDTO(livroSalvo);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    private LivroDTO toLivroDTO(Livro livro) {

        LivroDTO livroDto = new LivroDTO();

        livroDto.setId(livro.getId());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAutor(livro.getAutor());
        return livroDto;
    }

    private Livro toLivro(LivroDTO livroDto) {

        Livro livro = new Livro();

        livro.setId(livroDto.getId());
        livro.setTitulo(livroDto.getTitulo());
        livro.setAutor(livroDto.getAutor());

        return livro;
    }
}
