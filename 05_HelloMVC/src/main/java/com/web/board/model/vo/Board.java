package com.web.board.model.vo;

import java.util.Date;
import java.util.List;

import com.web.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor //매개변수있는생성자
@NoArgsConstructor  //기본생성자
public class Board {

   private int boardNo;
   private String boardTitle;
   private String boardWriter;
//   private Member boardWriter;
   private String boardContent;
   private String boardOriginalFilename;
   private String boardRenamedFilename;
   private Date boardDate;
   private int boardReadcount;
//   private List<BoardComment> comments;
}
