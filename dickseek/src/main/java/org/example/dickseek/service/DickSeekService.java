package org.example.dickseek.service;

import org.example.dickseek.pojo.Ticket;
import org.example.dickseek.utils.Result;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DickSeekService {
    Mono<Result<String>> getAIResponse(String userQuestion);

}
