package com.example.model;

import lombok.*;

import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Timeline extends BaseEntity {
  @Id
  private ObjectId id;
  private Info info;
  private List<Moment> moments;
  private String userId;
  private List<String> tags;

}
