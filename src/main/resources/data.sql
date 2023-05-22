delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type)
                values ('1', 'Мучная Тортилья', 'WRAP');
insert into Ingredient (id, name, type)
                values ('2', 'Кукурузная Тортилья', 'WRAP');
insert into Ingredient (id, name, type)
                values ('3', 'Говяжий фарш', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('4', 'Карнитас', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('5', 'Нарезанные помидоры', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('6', 'Салат', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('7', 'Чеддер', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('8', 'Монтерей джек', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('9', 'Сальса', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('10', 'Сметана', 'SAUCE');