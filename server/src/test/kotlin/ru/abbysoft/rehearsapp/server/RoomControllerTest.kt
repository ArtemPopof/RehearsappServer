package ru.abbysoft.rehearsapp.server

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import ru.abbysoft.rehearsapp.model.Image
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.model.TimeSlot
import ru.abbysoft.rehearsapp.server.controller.RoomController
import ru.abbysoft.rehearsapp.server.data.ImageRepository
import ru.abbysoft.rehearsapp.server.data.RoomRepository
import ru.abbysoft.rehearsapp.server.data.TimeSlotRepository
import kotlin.collections.ArrayList

@RunWith(SpringRunner::class)
@SpringBootTest
class RoomControllerTest {

    @MockBean
    private lateinit var roomRepository: RoomRepository
    @MockBean
    private lateinit var imageRepository: ImageRepository
    @MockBean
    private lateinit var slotRepository: TimeSlotRepository

    @Autowired
    private lateinit var roomController: RoomController

    @Test
    fun `should save room with all it's members correctly`() {
        val roomToSave = generateRoomWithAllData()
        var savedRoom: Room? = null
        val savedImages = ArrayList<Image>(3)
        val savedSlots = ArrayList<TimeSlot>(10)

        given(roomRepository.save(roomToSave)).will {
            savedRoom = roomToSave.apply { id = 5L }

            savedRoom
        }

        given(imageRepository.save(ArgumentMatchers.any(Image::class.java))).will {
            val image = it.arguments[0] as Image
            image.id = 5L
            savedImages.add(image)

            image
        }

        given(slotRepository.save(ArgumentMatchers.any(TimeSlot::class.java))).will {
            val slot = it.arguments[0] as TimeSlot
            slot.id = 5L
            savedSlots.add(slot)

            slot
        }

        val id = roomController.saveRoom(roomToSave)

        assertEquals(id, 5L)
        assert(savedImages.none { it.id != 5L })
        assert(savedSlots.none { it.id != 5L })

        val imagesWithId = roomToSave.images.map { it.apply { it.id = 5L } }
        val slotsWithId = roomToSave.slots.map { it.apply { it.id = 5L } }

        assertEquals(savedImages, imagesWithId)
        assertEquals(savedSlots, slotsWithId)
    }

    private fun generateRoomWithAllData(): Room {
        return Room().apply {
            this.area = 52.4f
            this.name = "room Name"
            this.price = 542.66f
            this.images = generateImages()
            this.slots = generateSlots()
        }
    }

    private fun generateImages(): List<Image> {
        return listOf(
                Image().apply { this.name = "image1" },
                Image().apply { this.name = "image2" },
                Image().apply { this.name = "image3" }
        )
    }

    private fun generateSlots(): List<TimeSlot> {
        return listOf(
                TimeSlot().apply { this.timeStart = 555; this.timeEnd = 333; this.price = 0f; this.bookedBy = 55L },
                TimeSlot().apply { this.timeStart = 555; this.timeEnd = 636; this.price = 45f; this.bookedBy = 45L },
                TimeSlot().apply { this.timeStart = 0; this.timeEnd = 63634; this.price = 555.5f }
        )
    }
}