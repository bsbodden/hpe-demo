package com.example.hpedemo;

import com.redis.om.spring.annotations.Indexed;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Role {
  @Indexed
  private String projectId;
  @Indexed private List<String> roles;
  @Indexed private LocalDateTime joinTime;

}
