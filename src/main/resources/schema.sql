/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  garla
 * Created: May 8, 2024
 */
DROP TABLE IF EXISTS users;
CREATE TABLE users(
    firstName varchar(25),
    lastName varchar(25),
    email varchar(40) NOT NULL UNIQUE,
    userName varchar(20) primary key,
    password text,
    role varchar(9)
);
