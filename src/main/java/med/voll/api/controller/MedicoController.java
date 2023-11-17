package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos") //Mapeando a URL /medicos
public class MedicoController {

    @Autowired //Realizando injeção de dependencia para que o Autowired passe o atributo repository dentro da classe MedicoController
    private MedicoRepository repository;
    @PostMapping
    @Transactional //método de escrita que consiste em um insert no banco de dados
   public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dadosMedicos, UriComponentsBuilder uribuilder) { //Utilizando @RequestBody para enviar para o corpo do json
        var medico = new Medico(dadosMedicos);
        repository.save(medico);

        //utilizando método para criar uri para informar em corpo location, e informar no corpo a representação da requisição 201
        var uri = uribuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    //Listar por paginação dados de medicos, limitando a 10 registros por página e ordenando por nome
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Pageable para realizar páginação quando realizar requisição de listar
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    //Atualizar informações
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosMedico){

        var medico = repository.getReferenceById(dadosMedico.id());
        medico.AtualizarInformacoes(dadosMedico);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
       // repository.deleteById(id); //código para apagar do banco de dados
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
