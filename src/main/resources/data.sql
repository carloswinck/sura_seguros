insert into categorias (id_categoria, categoria) values (1, 'Limpeza');
insert into categorias (id_categoria, categoria) values (2, 'Papelaria');
insert into categorias (id_categoria, categoria) values (3, 'Frutaria');

insert into clientes (id_cliente, nome) values (1, 'João');
insert into clientes (id_cliente, nome) values (2, 'José');
insert into clientes (id_cliente, nome) values (3, 'Pedro');

insert into produtos (id_produto, produto, preco, descricao, id_categoria) values (1, 'Sabonete', 5, 'Sabonete', 1);
insert into produtos (id_produto, produto, preco, descricao, id_categoria) values (2, 'Caneta', 2, 'Caneta', 2);

insert into usuarios (id, email, senha, usuario) values (1, 'admin@email.com', '$2a$12$7Bjmp3suLZ5sEXu.HKPqhedUCPae6uRlecjPC60SRqaBT1yMaHGs.', 'admin');
insert into usuarios (id, email, senha, usuario) values (2, 'user@email.com', '$2a$12$ihKV/8SVgpsxZiB4e9o7eeZoEn.eak3.yqOWpYUIB6vlnObVttEWS', 'user');
			
insert into usuario_papeis (usuario_id, papeis) values (1, 0);		
insert into usuario_papeis (usuario_id, papeis) values (2, 1);	