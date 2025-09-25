INSERT INTO tb_categoria_tarefa (category_name) VALUES ('Trabalho');
INSERT INTO tb_categoria_tarefa (category_name) VALUES ('Estudos');
INSERT INTO tb_categoria_tarefa (category_name) VALUES ('Pessoal');
INSERT INTO tb_categoria_tarefa (category_name) VALUES ('Domesticas');

INSERT INTO tb_pessoa (name, age) VALUES ('João Silva', 25);
INSERT INTO tb_pessoa (name, age) VALUES ('Maria Oliveira', 30);
INSERT INTO tb_pessoa (name, age) VALUES ('Pedro Henrique', 19);
INSERT INTO tb_pessoa (name, age) VALUES ('Ana Souza', 27);
INSERT INTO tb_pessoa (name, age) VALUES ('Lucas Santos', 22);
INSERT INTO tb_pessoa (name, age) VALUES ('Carla Mendes', 35);
INSERT INTO tb_pessoa (name, age) VALUES ('Rafael Lima', 28);
INSERT INTO tb_pessoa (name, age) VALUES ('Fernanda Costa', 26);
INSERT INTO tb_pessoa (name, age) VALUES ('Gustavo Pereira', 24);
INSERT INTO tb_pessoa (name, age) VALUES ('Juliana Martins', 32);

INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Finalizar relatório', 'ALTA', 'PENDENTE', 1, 1);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Reunião com equipe', 'MEDIA', 'CONCLUIDA', 2, 2);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Estudar Spring Boot', 'ALTA', 'PENDENTE', 3, 1);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Organizar planilhas', 'BAIXA', 'CONCLUIDA', 4, 3);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Criar API de tarefas', 'ALTA', 'CONCLUIDA', 5, 1);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Testar endpoints', 'MEDIA', 'PENDENTE', 6, 2);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Atualizar documentação', 'BAIXA', 'PENDENTE', 7, 3);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Deploy no servidor', 'ALTA', 'PENDENTE', 8, 1);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Correção de bugs', 'MEDIA', 'CONCLUIDA', 9, 2);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Implementar autenticação', 'ALTA', 'PENDENTE', 10, 1);
INSERT INTO tb_tarefa (task_name, priority_tarefa, status_tarefa, responsible_id, category_id) VALUES ('Planejar tarefas da semana', 'ALTA', 'PENDENTE', null, 1);
