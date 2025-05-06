package org.example.dickseek.controller;

import org.example.dickseek.service.DickSeekService;
import org.example.dickseek.pojo.Ticket;
import org.example.dickseek.utils.Result;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/dickSeek")
public class DickSeekController {

    private final DickSeekService dickSeekService;

    public DickSeekController(DickSeekService dickSeekService) {
        this.dickSeekService = dickSeekService;
    }
@GetMapping("/test")
public String test(){
        System.out.println("test");
        return "test";
}
    @PostMapping("/ask")
    public Mono<Result<String>> askAI(@RequestBody String question) {

        System.out.println("接受到了AI请求，正在返回");
        System.out.println(question);
        return dickSeekService.getAIResponse(question);
    }

}