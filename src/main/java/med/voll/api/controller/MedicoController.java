package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
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
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    //Atualizar informações
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosMedico){

        var medico = repository.getReferenceById(dadosMedico.id());
        medico.AtualizarInformacoes(dadosMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
       // repository.deleteById(id); //código para apagar do banco de dados
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
