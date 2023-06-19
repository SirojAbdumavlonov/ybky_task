# YBKY42_task

 A little project to get data of rooms in co-working center
   
    All available APIs:
1. Get all available rooms:

       GET /api/rooms 
    Parameters:
    
      search: name of room,
      
      type: type of room(focus, team, conference),
      
      page: page number,
      
      page_size: maximum number of results in per page,
      
  
2. Get details of room with this id:

       GET /api/rooms/{id}

3. Get available time of room with this id:

       GET /api/rooms/{id}/availability
    Parameter:
      date: check availability at this date

4. Booking a room with its id:

       POST /api/rooms/{id}/book

   Instructions of date("dd-MM-yyyy HH:mm:ss"),

      example: "06-07-2021 06:35:12"

        {
          "resident": {
            "name": "Anvar Sanayev"
          },
          "start": "05-06-2023 09:00:00",
          "end": "05-06-2023 10:00:00"
        }
