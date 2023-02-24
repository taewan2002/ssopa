package com.example.demo.Controller;

import com.example.demo.Service.ChatService;
import com.example.demo.dto.chat.ChatMessage;
import com.example.demo.dto.chat.ChatRoom;
import com.example.demo.repository.ChatMessageRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Api(tags = "ChatRoomController : 채팅 관련 컨트롤러")
public class ChatRoomController {
    private final ChatService chatService;



    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    @Operation(summary = "모든 채팅방 목록 반환")
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    @Operation(summary = "채팅방 생성")
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @Operation(summary = "특정 채팅방 조회")
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

    // 특정 채팅방 조회
    @Operation(summary = "채팅방 채팅 내역 불러오기")
    @GetMapping("load/room/{roomId}")
    @ResponseBody
    public List<ChatMessage> loadChat(@PathVariable String roomId) {
        List<ChatMessage> chatMessages = chatService.loadchat(roomId);
        return chatMessages;
    }
}///