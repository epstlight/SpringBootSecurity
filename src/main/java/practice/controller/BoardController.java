package practice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.AllArgsConstructor;
import practice.dto.BoardDto;
import practice.service.BoardService;

@Controller
@AllArgsConstructor
public class BoardController {
	private BoardService boardService;
	
	
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> boardList = boardService.getBoardlist();
		model.addAttribute("boardList", boardList);
		return "board/list.html";
	}
	
	
	@GetMapping("/post")
	public String write() {
		return "/board/writeForm.html";
	}
	
	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/";
	}
	
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") Long no, Model model) {
		BoardDto bd = boardService.getPost(no);
		model.addAttribute("boardDto", bd);
		return "/board/detail.html";
	}
	
	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") Long no, Model model) {
		BoardDto bd = boardService.getPost(no);
		model.addAttribute("boardDto", bd);
		return "/board/update.html";
	}
	
	@PutMapping("/post/edit/{no}")
	public String update(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/";
	}

	
	@DeleteMapping("/post/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.deletePost(no);
		return "redirect:/";
	}
	
	@PostMapping("/post/search")
	public String search(String keyword, Model model) {
		List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
		model.addAttribute("boardList", boardDtoList);
		return "/board/list.html";
	}
	
	
	
	
	
}
