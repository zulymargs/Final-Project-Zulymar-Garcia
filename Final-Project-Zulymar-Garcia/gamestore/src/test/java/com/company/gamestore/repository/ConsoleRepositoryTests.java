package com.company.gamestore.repository;

import com.company.gamestore.models.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTests {

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {

        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddConsole() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(3.99));
        console.setQuantity(123);

        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());

        assertEquals(console1.get(), console);
    }

    @Test
    public void shouldGetConsoleById() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(3.99));
        console.setQuantity(123);
        console = consoleRepository.save(console);

        Console console2 = new Console();
        console2.setModel("2022");
        console2.setManufacturer("Google");
        console2.setMemoryAmount("64GB");
        console2.setProcessor("Intel");
        console2.setPrice(BigDecimal.valueOf(1.99));
        console2.setQuantity(15);
        console2 = consoleRepository.save(console2);

        Optional<Console> foundConsole = consoleRepository.findById(console.getId());

        assertEquals(foundConsole.get(), console);
    }

    @Test
    public void shouldGetAllConsoles() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1.99));
        console.setQuantity(123);
        console = consoleRepository.save(console);

        Console console2 = new Console();
        console2.setModel("2022");
        console2.setManufacturer("Google");
        console2.setMemoryAmount("64GB");
        console2.setProcessor("Intel");
        console2.setPrice(BigDecimal.valueOf(3.99));
        console2.setQuantity(15);
        console2 = consoleRepository.save(console2);

        //Act...
        List<Console> cList = consoleRepository.findAll();

        //Assert...
        assertEquals(cList.size(), 2);
    }

    @Test
    public void shouldGetConsoleByManufacturer() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1.99));
        console.setQuantity(123);
        console = consoleRepository.save(console);

        Console console2 = new Console();
        console2.setModel("2022");
        console2.setManufacturer("Google");
        console2.setMemoryAmount("64GB");
        console2.setProcessor("Intel");
        console2.setPrice(BigDecimal.valueOf(3.99));
        console2.setQuantity(15);
        console2 = consoleRepository.save(console2);

        //Act...
        List<Console> cList = consoleRepository.findByManufacturer("Google");

        //Assert...
        assertEquals(cList.size(), 1);
    }

    @Test
    public void shouldUpdateConsole() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1.99));
        console.setQuantity(123);
        console = consoleRepository.save(console);

        console.setModel("2022");
        console.setManufacturer("Google");
        console.setMemoryAmount("64GB");

        consoleRepository.save(console);

        //Act...
        Optional<Console> console1 = consoleRepository.findById(console.getId());

        //Assert...
        assertEquals(console1.get(), console);
    }
    @Test
    public void shouldDeleteConsoleById() {

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1.99));
        console.setQuantity(123);

        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getId());

        assertEquals(console1.get(), console);

        //Act...
        consoleRepository.deleteById(console.getId());

        console1 = consoleRepository.findById(console.getId());

        //Assert...
        assertFalse(console1.isPresent());
    }

}