-- Добавляем книги
INSERT INTO books (title, author, year_of_publication)
VALUES
    ('Clean Code', 'Robert C. Martin', 2008),
    ('Effective Java', 'Joshua Bloch', 2018);

-- Добавляем пользователей
--password: password
INSERT INTO users (username, age, gender, email, password, role)
VALUES
    ('user1', 22, 'man', 'user1@mail.com', '$2a$10$rOi0KIvKgIdNkHB5/3JNhuS.macnZrtqMXonU6RLexg/b0okRAF96', 'USER'),
    ('user2', 23, 'man', 'user2@mail.com', '$2a$10$rOi0KIvKgIdNkHB5/3JNhuS.macnZrtqMXonU6RLexg/b0okRAF96', 'USER');

-- Добавляем сотрудников
--password: password
INSERT INTO employees (first_name, last_name, phone, email, password, role)
VALUES
    ('John', 'Doe', '+77001112233', 'admin@mail.com', '$2a$10$rOi0KIvKgIdNkHB5/3JNhuS.macnZrtqMXonU6RLexg/b0okRAF96', 'ADMIN'),
    ('Jane', 'Smith', '+77002223344', 'employee@mail.com', '$2a$10$rOi0KIvKgIdNkHB5/3JNhuS.macnZrtqMXonU6RLexg/b0okRAF96', 'EMPLOYEE');

-- Добавляем библиотечные карты
INSERT INTO library_card (date_of_creation)
VALUES
    ('2026-05-01'),
    ('2026-05-02');

-- Привязываем карты к пользователям (по id)
UPDATE users SET card_id = 1 WHERE id = 1;
UPDATE users SET card_id = 2 WHERE id = 2;

-- Добавляем записи о выдаче книг
INSERT INTO borrow_record (card_id, book_id, borrow_date, employee_id)
VALUES
    (1, 1, '2026-05-10', 1),
    (2, 2, '2026-05-11', 2);
