package org.example.dickseek.service;

import org.example.dickseek.pojo.Ticket;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DickSeekService {
Mono<String> getAIResponse(String userQuestion);

}
