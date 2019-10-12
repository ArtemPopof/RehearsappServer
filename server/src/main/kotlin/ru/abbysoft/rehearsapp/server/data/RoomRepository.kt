package ru.abbysoft.rehearsapp.server.data

import org.springframework.data.repository.CrudRepository
import ru.abbysoft.rehearsapp.model.Room

interface RoomRepository : CrudRepository<Room, Long>