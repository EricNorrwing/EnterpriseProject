
INSERT INTO roles (name)
SELECT 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_ADMIN');

INSERT INTO roles (name)
SELECT 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_USER');


INSERT INTO users (username, password)
SELECT 'EricN', '{bcrypt}$2a$12$WilQZWYbRzHEuDCDMsEdneDpTey4Rw/K.b4GiLJUgBeZ8ykTxFINW'
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'EricN');


INSERT INTO user_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE username = 'EricN'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
    WHERE NOT EXISTS (
    SELECT 1 FROM user_roles ur
    JOIN users u ON ur.user_id = u.id
    JOIN roles r ON ur.role_id = r.id
    WHERE u.username = 'EricN' AND r.name = 'ROLE_ADMIN'
);


INSERT INTO user_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE username = 'EricN'),
    (SELECT id FROM roles WHERE name = 'ROLE_USER')
    WHERE NOT EXISTS (
    SELECT 1 FROM user_roles ur
    JOIN users u ON ur.user_id = u.id
    JOIN roles r ON ur.role_id = r.id
    WHERE u.username = 'EricN' AND r.name = 'ROLE_USER'
);
