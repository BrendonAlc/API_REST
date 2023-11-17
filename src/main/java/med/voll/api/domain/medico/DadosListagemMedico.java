package med.voll.api.domain.medico;

public record DadosListagemMedico( Long id,String nome, String email, String crm, Especialidade especialidade ) {

    //Construtor para retornar dados de médicos como List para o MedicoController
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
