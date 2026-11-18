package com.example.qlts.controller;

import com.example.qlts.entity.Room;
import com.example.qlts.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // 1. THÊM MỚI (POST): http://localhost:8080/api/rooms
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // 2. XEM TẤT CẢ (GET): http://localhost:8080/api/rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 3. XEM CHI TIẾT THEO ID (GET): http://localhost:8080/api/rooms/{id}
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID: " + id));
    }

    // 4. CẬP NHẬT (PUT): http://localhost:8080/api/rooms/{id}
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID: " + id));

        room.setName(roomDetails.getName());
        room.setCapacity(roomDetails.getCapacity());
        room.setLocation(roomDetails.getLocation());

        return roomRepository.save(room);
    }

    // 5. XÓA (DELETE): http://localhost:8080/api/rooms/{id}
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID: " + id));
        roomRepository.delete(room);
        return "Đã xóa thành công phòng có ID: " + id;
    }
}
