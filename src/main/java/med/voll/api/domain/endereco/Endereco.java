package med.voll.api.domain.endereco;

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

    public void AtulizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.complemento() != null){
            this.complemento = dados.complemento();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
    }
}
