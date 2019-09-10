package springmybatis.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {

    private Integer id;
    private String name;
    private String country;
}
