package ru.abbysoft.rehearsapp.server.data

import org.springframework.data.repository.CrudRepository
import ru.abbysoft.rehearsapp.model.TimeSlot

interface TimeSlotRepository : CrudRepository<TimeSlot, Long>