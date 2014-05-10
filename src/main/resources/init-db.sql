INSERT INTO public.employee (version, login, name, authority, mail, phone)
VALUES (0, 'apradelles', 'Arnaud Pradelles', 'ROLE_ADMIN', 'arnaud.pradelles@sopra.com', '11111');
INSERT INTO public.employee (version, login, name, authority, mail, phone)
VALUES (0, 'jntakpe', 'Jocelyn N''Takpé', 'ROLE_ADMIN', 'jocelyn.ntakpe@sopra.com', '22222');
INSERT INTO public.employee (version, login, name, authority, mail, phone)
VALUES (0, 'jguerrin', 'Julien Guerrin', 'ROLE_USER', 'julien.guerrin@sopra.com', '33333');

INSERT INTO public.project (version, name, description, manager_id)
VALUES (0, 'RDC', 'Regroupement de commandes', (SELECT
                                                  id
                                                FROM employee
                                                WHERE login = 'apradelles'));

INSERT INTO public.project (version, name, description, manager_id)
VALUES (0, 'PF', 'Facturation aux entités', (SELECT
                                               id
                                             FROM employee
                                             WHERE login = 'apradelles'));

INSERT INTO public.project (version, name, description, manager_id)
VALUES (0, '3DS', '3D secure', (SELECT
                                  id
                                FROM employee
                                WHERE login = 'apradelles'));



