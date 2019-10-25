package ru.abbysoft.rehearsapp.server

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import ru.abbysoft.rehearsapp.server.controller.PriceService

@RunWith(SpringRunner::class)
@SpringBootTest
class PriceServiceTest {

    @Autowired
    lateinit var service: PriceService

    @Test
    fun `price service should generate valid price`() {
        val result = service.calcSlotPrice(500.5f, 100055)

        assert(result > 0)
    }
}