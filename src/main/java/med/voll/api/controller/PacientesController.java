package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes") //Mapeando URL /medicos
public class PacientesController {

    @Autowired //Realizando injeção de dependencia para que o Autowired passe o atributo repository dentro da classe PacientesController
    private PacienteRepository repository;

    @PostMapping
    @Transactional //método de escrita que consiste em um insert no banco de dados

    //Utilizando @RequestBody para enviar para o corpo do json
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacientes dadosPacientes, UriComponentsBuilder uribuilder) {
        var paciente = new Paciente(dadosPacientes);
        repository.save(paciente);

        //utilizando método para criar uri para informar em corpo location, e informar no corpo a representação da requisição 201
        var uri = uribuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    //Listar por paginação dados de paciente, limitando a 10 registros por página e ordenando por nome
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault( size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    //Atualizar informações
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dadosPaciente){

        var paciente = repository.getReferenceById(dadosPaciente.id());
        paciente.AtualizarInformacoes(dadosPaciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
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
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
