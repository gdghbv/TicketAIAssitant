package org.example.dickseek.pojo;

import lombok.Data;
import java.util.List;

@Data
public class ChatResponse {
 private List<Choice> choices;

 @Data
 public static class Choice {
  private ChatRequest.Message message;
 }
}