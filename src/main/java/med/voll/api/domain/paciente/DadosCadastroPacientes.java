package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

import java.util.Date;

public record DadosCadastroPacientes(
        @NotBlank (message = "{nome.obrigatorio}")
        String nome,
        @NotBlank (message = "{cpf.obrigatorio}")
        String cpf,
        @NotBlank (message = "{dataNascimento.obrigatorio}")
        //@JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm:ss")
        //@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        Date dataNascimento,
        @NotBlank (message = "{email.obrigatorio}")
        @Email (message = "{email.invalido}")
        String email,
        @NotBlank (message = "{telefone.obrigatorio}")
        @Pattern(regexp = "\\d{8}")
        String telefone,

        @NotNull (message = "{endereco.obrigatorio}")
        @Valid //Para validar outro Objeto como um dos atributos de endereço
        DadosEndereco endereco) {

}
