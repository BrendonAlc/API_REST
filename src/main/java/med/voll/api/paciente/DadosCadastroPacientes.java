package med.voll.api.paciente;

import med.voll.api.endereco.DadosEndereco;

import java.util.Date;

public record DadosCadastroPacientes(String nome, String cpf, Date dataNascimento, String email, DadosEndereco endereco) {
}
