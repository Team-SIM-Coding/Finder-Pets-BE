package com.pet.petproject.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Data
@ToString
@Getter
@Setter
public class User {
    int userid;
    String name;
    String nickname;
    String email;
    String password;
    String img;
    String likeAnimal;
    String likeArea;
    String likeKindvarchar;
    String phone;
    String intro;
}
