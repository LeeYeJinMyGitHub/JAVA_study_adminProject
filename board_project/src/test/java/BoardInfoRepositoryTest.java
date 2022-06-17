package com.example.admin_project.repository;

import com.example.board_project.BoardProjectApplication;
import com.example.board_project.dto.BoardInfoDto;
import com.example.board_project.model.entity.BoardInfo;
import com.example.board_project.repository.BoardInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BoardProjectApplication.class)
public class BoardInfoRepositoryTest extends BoardProjectApplication {

    @Autowired
    private BoardInfoRepository boardInfoRepository;

    @Autowired
    private BoardInfoDto boardInfoDto;
/*
    @Test
    public void create(){

        String title = "COMPUTER";
        String contents = "AdminServer";

        BoardInfoDto boardInfoDto = new BoardInfoDto();
        boardInfoDto.setTitle(title);
        boardInfoDto.setContents(contents);

        BoardInfo newBoardInfo = boardInfoRepository.save(boardInfoDto);

    }

 */


    @Test
    public void read(){

        String type ="COMPUTER";

        Optional<BoardInfo> optionalBoardInfo= boardInfoRepository.findById(1L);

        optionalBoardInfo.ifPresent(c -> {

            Assertions.assertEquals(c.getId(),1);
            System.out.println(c.getTitle());
            System.out.println(c.getContents());
        });

    }
}
