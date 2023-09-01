package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;
import org.hibernate.grammars.hql.HqlParser;

import java.util.Date;

//Anotações JPA para criar a tabela utilizando a Entidade
@Table(name = "pacientes")
@Entity(name = "Paciente")

//Utilizando anotação lombok para criar getter e setter
@Getter
@NoArgsConstructor //Para criar o contructor default que a JPA necessita em todas as entidades
@AllArgsConstructor //Criar constructor que recebe todos os campos
@EqualsAndHashCode(of = "id") //Gerar o hash code de acordo com os ID e não de acordo com os atributos

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    @Embedded
    private Endereco endereco;

    //teste
    public Paciente(DadosCadastroPacientes dadosPacientes) {
        this.nome = dadosPacientes.nome();
        this.cpf = dadosPacientes.cpf();
        this.dataNascimento = new Date();
        this.email = dadosPacientes.email();
        this.endereco = new Endereco(dadosPacientes.endereco());
    }

    public void AtualizarInformacoes(DadosAtualizacaoPaciente dadosPaciente) {
        if (dadosPaciente.nome() != null) {
            this.nome = dadosPaciente.nome();
        }
        if(dadosPaciente.email() != null) {
            this.email = dadosPaciente.email();
        }
        if (dadosPaciente.endereco() != null) {
            this.endereco.AtulizarInformacoes(dadosPaciente.endereco());
        }
    }
}
