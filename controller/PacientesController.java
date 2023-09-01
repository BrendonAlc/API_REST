package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PacientesController {

    @Autowired //Realizando injeção de dependencia para que o Autowired passe o atributo repository dentro da classe PacientesController
    private PacienteRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPacientes dadosPacientes) {
        repository.save(new Paciente(dadosPacientes));
    }

    @GetMapping
    //Listar por paginação dados de paciente, limitando a 10 registros por página e ordenando por nome
    public Page<DadosListagemPaciente> listar(@PageableDefault( size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    //Atualizar informações
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dadosPaciente){

        var paciente = repository.getReferenceById(dadosPaciente.id());
        paciente.AtualizarInformacoes(dadosPaciente);
    }
}
