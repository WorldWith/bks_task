-- fill api_desc table
insert into api_desc (entity_id, app_name, api_version)
	values (1, 'Test Application for BCS', '1.0');

-- fill products table with default products
insert into products (entity_id, product_id, name, max_sum, max_term, rate)
	values (1, '1', 'Кредит Мини', 200000, 1, 6);
	
insert into products (entity_id, product_id, name, max_sum, max_term, rate)
	values (2, '2', 'Кредит Миди', 1000000, 5, 12);
	
insert into products (entity_id, product_id, name, max_sum, max_term, rate)
	values (3, '3', 'Кредит Макси', 5000000, 3, 10.5);
	
insert into products (entity_id, product_id, name, max_sum, max_term, rate)
	values (4, '4', 'Кредит Безлимитный', 0, 0, 15);
	
-- fill rules table with default rules for each product
insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (1, '1', 40000, true, 1);
	
insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (2, '2', 60000, false, 1);

insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (3, '3', 30000, true, 2);
	
insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (4, '4', 35000, true, 3);
	
insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (5, '5', 50000, false, 3);
	
insert into rules (entity_id, rule_id, salary, is_no_debts_rule, product_id)
	values (6, '6', 25000, false, 4);
	
