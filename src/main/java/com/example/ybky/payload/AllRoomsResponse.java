package com.example.ybky.payload;

import com.example.ybky.entity.Room;
import java.util.List;

public record AllRoomsResponse(int page,
                               int count,
                               int page_size,
                               List<Room> results
                                ) {
}
