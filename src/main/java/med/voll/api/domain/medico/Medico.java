package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

//Anotações JPA para criar a tabela utilizando a Entidade
@Table(name = "medicos")
@Entity(name = "Medico")

//Anotações do lombok para criar getter e setter
@Getter
@NoArgsConstructor //Para criar o contructor default que a JPA necessita em todas as entidades
@AllArgsConstructor //Criar constructor que recebe todos os campos
@EqualsAndHashCode(of = "id") //Gerar o hash code de acordo com os ID e não de acordo com os atributos

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)//Anotação JPA para digitar a especialidade conforme cadastrado ENUM em medico/Especialidade
    private Especialidade especialidade;
    @Embedded //Para trazer dados da entidade Endereco
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedicos dadosMedicos) {
        this.nome = dadosMedicos.nome();
        this.email = dadosMedicos.email();
        this.telefone = dadosMedicos.telefone();
        this.crm = dadosMedicos.crm();
        this.especialidade = dadosMedicos.especialidade();
        this.endereco = new Endereco(dadosMedicos.endereco());
        this.ativo = true;
    }

    public void AtualizarInformacoes(DadosAtualizacaoMedico dadosMedico) {

        if (dadosMedico.nome() != null) {
            this.nome = dadosMedico.nome();
        }
        if (dadosMedico.telefone() != null) {
            this.telefone = dadosMedico.telefone();
        }
        if (dadosMedico.endereco() != null) {
            this.endereco.AtulizarInformacoes(dadosMedico.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
