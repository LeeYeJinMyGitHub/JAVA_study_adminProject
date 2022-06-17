package com.example.board_project.controller.api;

import com.example.board_project.dto.BoardInfoDto;
import com.example.board_project.model.entity.BoardInfo;
import com.example.board_project.repository.BoardInfoRepository;
import com.example.board_project.service.BoardInfoApiLogicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/list")
@ResponseBody
public class BoardInfoApiController {

  private BoardInfoApiLogicService boardInfoApiLogicService;

  @Autowired
  private BoardInfoRepository boardInfoRepository;

  @GetMapping
  public Iterable<BoardInfo> list() {
    return boardInfoRepository.findAll();
  }

  @GetMapping("get-board-list.do")
  public Iterable<BoardInfo> getBoardlist() {
    return boardInfoRepository.findAll();
  }

  @PostMapping("get-board-detail.do/{id}")
  public Optional<BoardInfo> getBoardDetail(@PathVariable Long id) {
    return boardInfoRepository.findById(id);
  }

  @PostMapping("/savePosts")
  public void savePosts(@RequestBody BoardInfoDto boardInfoDto) throws Exception {
    boardInfoApiLogicService.savePosts(boardInfoDto);
  }
}
