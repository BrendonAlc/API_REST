package med.voll.api.domain.paciente;

import java.util.Date;

public record DadosListagemPaciente(Long id, String nome, Date dataNascimento, String email) {

    //Contrutor para retornar dados de médicos como List para o PacientController
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getEmail());
    }
}
