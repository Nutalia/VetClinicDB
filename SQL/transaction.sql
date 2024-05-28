/*Пользователь, который может просматривать таблицу клиентов*/
CREATE USER 'watcher'
DEFAULT ROLE watcher;

GRANT SELECT
ON TABLE Clients
TO 'watcher';

SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
SELECT @@transaction_ISOLATION;

START TRANSACTION;
COMMIT;

INSERT INTO Clients
 (Client_id, Second_name, First_name, Patronymic, Phone_number)
 VALUE (501, 'Алексеев', 'Петр', 'Владимирович', '+7(342)671-73-82');

/*
Начать транзакции от пользователей root и watcher
Сделать INSERT от пользователя root
Посмотреть от пользователя watcher таблицу клиентов:
	SELECT * FROM Clients WHERE Client_id > 490;
*/