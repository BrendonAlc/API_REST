-->Realizar alter Table para alterar tabela de medicos, adicionando coluna ativo e setando como ativo
alter table medicos add ativo tinyint;
update medicos set ativo = 1;