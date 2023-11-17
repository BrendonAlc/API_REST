package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

import java.util.Date;

public record DadosCadastroPacientes(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        //@JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm:ss")
        //@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        Date dataNascimento,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,

        @NotNull
        @Valid //Para validar outro Objeto como um dos atributos de endereço
        DadosEndereco endereco) {

}
