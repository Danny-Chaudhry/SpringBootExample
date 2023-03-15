
create table SEC_USER
(
  userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userName         VARCHAR(36) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL 
) ;


create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;


create table USER_ROLE
(
  ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);

insert into SEC_User (userName, encryptedPassword, ENABLED) VALUES 
('Jon', '$2a$10$XrXRhhvmGqEWfQSwvTPkseeRXjuTkD2JBlaBai82hxBk/FKwm74nG', 1),
('One', '$2a$10$ofSVcJdZ9aShQNDBlUrO3u.cVTviKtt.aGpyIsF/wuGIrHa9QWSV2', 1),
('Two', '$2a$10$dj8v1I0Wa4h5uZN.KJEh9ORuILoMaL4aM1mwvYJOR1YHg7uccWFGG', 1),
('Three', '$2a$10$9rBFeKMD8YWhzScRqHjp9O2qMQkehiHnbR9BlqYcDc8w0iwdGn.2W', 1),
('Four', '$2a$10$InSTZYDomeXHpdvx10dz7.0Ny19427UQH.1M9CUzkqoxzRva/M5E6', 1),
('Five', '$2a$10$3UQMA2kKXSUVFlPfrpfWn.6aAb4NRO6MBTnv3jHuXifKSfnpPMpq.', 1),
('Six', '$2a$10$YGf53DN8935ppJl/pr/Kx.IODEDWux7rdfHVsIYzt0IpGRHgXQ/3q', 1),
('Seven', '$2a$10$8KVdhXJ.xzU4F2OGfNJHMu1GuxOiH6GJ5MuuMorlMTSjcKU6LOCyS', 1),
('Eight', '$2a$10$h21Ob7boeeaHGLVxjIo/5.yLEvvPzsAomI5un3xF4xpqE1js4q8Lu', 1),
('Nine', '$2a$10$rteoX.71O4dkbKydxq.s8.k.6xEWUw1/jnr1Oc7mOFji1wdTk8JIi', 1),
('Ten', '$2a$10$PC.QB.vAgZrtD/b6JErCd.kJhD4cQaWPbLV7G3v6R6fmfzxQNDj0G', 1);
 
 
INSERT INTO sec_role (roleName) VALUES
('ROLE_PROFESSOR'),
('ROLE_STUDENT');
 
INSERT INTO user_role (userId, roleId) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2);

COMMIT;

