delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type)
                values ('FLTO', 'Мучная Тортилья', 'WRAP');
insert into Ingredient (id, name, type)
                values ('COTO', 'Кукурузная Тортилья', 'WRAP');
insert into Ingredient (id, name, type)
                values ('GRBF', 'Говяжий фарш', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('CARN', 'Карнитас', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('TMTO', 'Нарезанные помидоры', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('LETC', 'Салат', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('CHED', 'Чеддер', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('JACK', 'Монтерей джек', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('SLSA', 'Сальса', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('SRCR', 'Сметана', 'SAUCE');