package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull (message = "{campo.nulo}")
        Long id,
        String nome,
        String email,
        DadosEndereco endereco) {
}
