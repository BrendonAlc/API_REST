package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DadosListagemMedico> listar() {
    return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
