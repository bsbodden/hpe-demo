package com.example.hpedemo;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Data
@ToString
@Document("User")
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @NotEmpty(message ="User email field value should not be null or empty")
  @Id
  private String userEmail;
  @Indexed
  private List<Role> roles;
  @Indexed private Set<String> projects;
}
