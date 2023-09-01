package com.socialmedia.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass// üst sınıf olduğu için database de ayrı bir tablo açmaz..
public class BaseEntity implements Serializable {

    private Long createDate;
    private Long updateDate;
}
