package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.aspectj.weaver.ast.Not;

public record DadosEndereco(
        @NotBlank (message = "{logradouro.obrigatorio}")
        String logradouro,
        @NotBlank (message = "{bairro.obrigatorio}")
        String bairro,
        @NotBlank (message = "{cep.obrigatorio}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalido}")
        String cep,
        @NotBlank (message = "{cidade.obrigatorio}")
        String cidade,
        @NotBlank (message = "{uf.obrigatorio}")
        @Pattern(regexp = "^[A-Z]{2}$", message = "{uf.invalido}")
        String uf,

        Integer numero,
        String complemento) {
}
