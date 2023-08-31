package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos") //Mapeando a URL /medicos
public class MedicoController {

    @Autowired //Realizando injeção de dependencia para que o Autowired passe o atributo repository dentro da classe MedicoController
    private MedicoRepository repository;
    @PostMapping
    @Transactional //método de escrita que consiste em um insert no banco de dados
   public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dadosMedicos) { //Utilizando @RequestBody para enviar para o corpo do json
        repository.save(new Medico(dadosMedicos));
    }

    @GetMapping
    //Listar por paginação dados de medicos, limitando a 10 registros por página e ordenando por nome
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Pageable para realizar páginação quando realizar requisição de listar
    return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    //Atualizar informações da lista
    public void atualizar(@RequestBody @Valid DadosCadastroMedicos dadosMedico){

    }
}
