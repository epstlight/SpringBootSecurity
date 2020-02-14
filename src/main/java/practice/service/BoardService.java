package practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import practice.domain.entity.BoardEntity;
import practice.domain.repository.BoardRepository;
import practice.dto.BoardDto;

@Service
@AllArgsConstructor
public class BoardService {
	private BoardRepository boardRepository;
	
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional
	public List<BoardDto> getBoardlist(){
		List<BoardEntity> beList = boardRepository.findAll();
		List<BoardDto> bdList = new ArrayList<>();
		
		for(BoardEntity be: beList) {
			BoardDto bd = BoardDto.builder()
					.id(be.getId())
					.title(be.getTitle())
					.writer(be.getWriter())
					.content(be.getContent())
					.createdDate(be.getCreatedDate())
					.build();
			
			bdList.add(bd);
		}
		return bdList;
	}
	
	@Transactional
	public BoardDto getPost(Long no) {
		BoardEntity be = boardRepository.findById(no).get();
		BoardDto bd = BoardDto.builder()
				.id(be.getId())
                .title(be.getTitle())
                .content(be.getContent())
                .writer(be.getWriter())
                .createdDate(be.getCreatedDate())
                .build();
		return bd;
	}
	
	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}
	
	public List<BoardDto> searchPosts(String keyword){
		List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		if(boardEntities.isEmpty()) return boardDtoList;
		for (BoardEntity be: boardEntities) {
			BoardDto bd = BoardDto.builder()
					.id(be.getId())
					.title(be.getTitle())
					.writer(be.getWriter())
					.content(be.getContent())
					.createdDate(be.getCreatedDate())
					.build();
			
			boardDtoList.add(bd);
		}
		return boardDtoList;
	}
	
}
