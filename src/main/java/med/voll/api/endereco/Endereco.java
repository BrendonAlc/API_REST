package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter //Anotação lombok
@NoArgsConstructor //Para criar o contructor default que a JPA necessita em todas as entidades
@AllArgsConstructor //Criar constructor que recebe todos os campos
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private Integer numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.cep = endereco.cep();
        this.bairro = endereco.bairro();
        this.complemento = endereco.complemento();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
    }
}
