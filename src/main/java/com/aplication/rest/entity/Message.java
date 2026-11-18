package com.aplication.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Lombo para controladores:
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private int id;
    private String content;

}
