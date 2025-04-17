package org.example.dickseek.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dickseek.pojo.Ticket;

import java.util.List;

@Mapper
public interface DickSeekMapper {
@Select("SELECT * FROM ticket")
    List<Ticket> getTickets();

}
