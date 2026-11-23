MERGE INTO cliente (id, nome, telefone, cpf) KEY(id) VALUES (1, 'Carlos Silva', '11999998888', '12345678901');
MERGE INTO veiculo (id, placa, modelo, ano, cliente_id) KEY(id) VALUES (1, 'ABC1D23', 'Honda Civic', 2020, 1);
MERGE INTO mecanico (id, nome, especialidade) KEY(id) VALUES (1, 'Roberto Souza', 'Motor e Câmbio');
MERGE INTO servico (id, descricao, valor) KEY(id) VALUES (1, 'Troca de Óleo e Filtros', 250.00);
MERGE INTO ordem_servico (id, veiculo_id, mecanico_id, servico_id, status, data_abertura) KEY(id) VALUES (1, 1, 1, 1, 'Em Andamento', '2026-11-23');