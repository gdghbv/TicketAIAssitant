package org.example.dickseek.service.Impl;

import org.apache.http.HttpHeaders;
import org.example.dickseek.mapper.DickSeekMapper;
import org.example.dickseek.pojo.ChatRequest;
import org.example.dickseek.pojo.ChatResponse;
import org.example.dickseek.pojo.Ticket;
import org.example.dickseek.service.DickSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DickSeekServiceImpl implements DickSeekService {

    private final WebClient webClient;

private final DickSeekMapper dickSeekMapper;


 public DickSeekServiceImpl( @Value("${deepseek.api.key}") String apiKey,WebClient.Builder webClientBuilder,DickSeekMapper dickSeekMapper) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.deepseek.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
     this.dickSeekMapper=dickSeekMapper;
    }


    public Mono<String> getAIResponse(String userQuestion) {

    List<Ticket> tickets= dickSeekMapper.getTickets();
    StringBuilder data=new StringBuilder();
    for(Ticket ticket:tickets){
        data.append(String.format(
                "机票Id：%s 飞机Id： %s 起始地：%s 目的地：%s 价格：%s 头等舱：%s 商务舱：%s 经济舱：%s 起飞日期：%s 起飞时间：%s 抵达时间：%s \n",
                ticket.getTicketId(),
                ticket.getAircraftId(),
                ticket.getDeparture(),
                ticket.getDestination(),
                ticket.getPrice(),
                ticket.getFirstSeat(),
                ticket.getSecondSeat(),
                ticket.getThirdSeat(),
                ticket.getDepartureDate(),
                ticket.getDepartureTime(),
                ticket.getArrivalTime()


        ));
    }
        ChatRequest request = new ChatRequest();
        String systemPrompt = """
            你是订票系统网站的专属 AI 助手，仅回答与 **飞机订票、购票、旅游出行** 和 **你自己** 相关的问题。
            你必须要 **结合数据库里面的机票的信息** 来回答用户的问题，这是机票的数据""" +data+"""
            如果用户的问题与我要求的或者出行方案无关，请提示你的身份。
            
            """;
        request.setMessages(List.of(
                new ChatRequest.Message("system", systemPrompt),
                new ChatRequest.Message("user", userQuestion)
        ));

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        code -> code == HttpStatus.UNAUTHORIZED,
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("认证失败: " + errorBody)))
                )
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("API错误: " + errorBody)))
                )
                .bodyToMono(ChatResponse.class)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }


}