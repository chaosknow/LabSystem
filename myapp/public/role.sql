create table roles(
    id INT primary Key AUTO_INCREMENT,
    roleName VARCHAR(20) NOT NULL,
    roleType INT NOT NULL,
    rights JSON NOT NULL
);
INSERT INTO
    roles(roleName,roleType,rights)
VALUES
(
    '管理员',
    1,
    '[
        "/index",
        "/user-manage",
        "/user-manage/list",
        "/right-manage",
        "/right-manage/rightlist",
        "/right-manage/rolelist",
        "/lab-manage",
        "/lab-manage/lablist",
        "/lab-manage/addlab",
        "/book-manage/auditlist",
        "/book-manage/booklist",
        "/book-manage"
    ]'
);

INSERT INTO
    roles(roleName,roleType,rights)
VALUES
(
    '教师',
    2,
    '[
        "/index",
        "/book-manage/addbook",
        "/book-manage/booklist",
        "/book-manage"
    ]'
);