package com.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Objects;

/***
 * JDK1.7
 */
public class TestHashMap {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(new MyData("haha"),"XXX");
        map.put(new MyData("heh"),"XXX");
        map.put(new MyData("wuwu"),"XXX");
    }
}

@Data
@AllArgsConstructor
class MyData{
    private String info  ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyData myData = (MyData) o;
        return Objects.equals(info, myData.info);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
