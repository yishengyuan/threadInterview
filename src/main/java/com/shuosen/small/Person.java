package com.shuosen.small;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Person {

    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}
