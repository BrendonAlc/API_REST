package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

import java.util.Date;

public record DadosCadastroPacientes(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        Date dataNascimento,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Valid //Para validar outro Objeto como um dos atributos de endereço
        DadosEndereco endereco) {
}
