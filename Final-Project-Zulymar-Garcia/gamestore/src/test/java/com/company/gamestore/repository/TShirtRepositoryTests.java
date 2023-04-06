package com.company.gamestore.repository;

import com.company.gamestore.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TShirtRepositoryTests {


    @Autowired
    TShirtRepository tshirtRepo;

    @Before
    public void setUp() throws Exception {
        tshirtRepo.deleteAll();
    }

    @Test
    public void createTshirt() {

        TShirt tshirt = new TShirt();
         tshirt.setColor("black");
         tshirt.setPrice(BigDecimal.valueOf(15.99));
         tshirt.setSize("Medium");
         tshirt.setQuantity(300);
          tshirt.setDescription("Leopard print graphics");



        tshirt = tshirtRepo.save(tshirt);

        //Assert...
        Optional<TShirt> tshirt2 = tshirtRepo.findById(tshirt.getId());

        assertEquals(tshirt2.get(), tshirt);
    }

    @Test
    public void updateTShirt() {

        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");

        tshirtRepo.save(tshirt);

        //Act...
        tshirt.setColor("White");

        tshirt.setQuantity(203);


        tshirtRepo.save(tshirt);


        //Assert...
        Optional<TShirt> tshirt2 = tshirtRepo.findById(tshirt.getId());

        assertEquals(tshirt2.get(), tshirt);
    }

    @Test
    public void deleteNote() {

        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);

        tshirtRepo.deleteById(tshirt.getId());

        Optional<TShirt> tshirt2 = tshirtRepo.findById(tshirt.getId());
        assertFalse(tshirt2.isPresent());
    }


    @Test
    public void getTshirtByID() {

        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);


        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);

        Optional<TShirt> foundTshirt = tshirtRepo.findById(tshirt.getId());
        assertEquals(foundTshirt.get(), tshirt);

        Optional<TShirt> foundTshirt2 = tshirtRepo.findById(tshirt2.getId());
        assertEquals(foundTshirt2.get(), tshirt2);

    }

    @Test
    public void getAllTShirts(){
        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);

        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);

        List<TShirt> tList = tshirtRepo.findAll();

        assertEquals(tList.size(), 2);
    }

    @Test
    public void getTShirtsByColor(){
        TShirt tshirt = new TShirt();
        tshirt.setColor("pink");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);

        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);

        TShirt tshirt3 = new TShirt();
        tshirt3.setColor("white");
        tshirt3.setPrice(BigDecimal.valueOf(20.99));
        tshirt3.setSize("Large");
        tshirt3.setQuantity(2000);
        tshirt3.setDescription("Netflix logo on front and back");
        tshirtRepo.save(tshirt3);


        List<TShirt> tList = tshirtRepo.findAllTShirtsByColor("white");
        assertEquals(tList.size(), 2);

        List<TShirt> tList2 = tshirtRepo.findAllTShirtsByColor("pink");
        assertEquals(tList2.size(), 1);

    }


    @Test
    public void getTShirtsBySize(){
        TShirt tshirt = new TShirt();
        tshirt.setColor("pink");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirtRepo.save(tshirt);

        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Large");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");
        tshirtRepo.save(tshirt2);

        TShirt tshirt3 = new TShirt();
        tshirt3.setColor("white");
        tshirt3.setPrice(BigDecimal.valueOf(20.99));
        tshirt3.setSize("Large");
        tshirt3.setQuantity(2000);
        tshirt3.setDescription("Netflix logo on front and back");
        tshirtRepo.save(tshirt3);


        List<TShirt> tList = tshirtRepo.findAllTShirtsBySize("Large");
        assertEquals(tList.size(), 2);

        List<TShirt> tList2 = tshirtRepo.findAllTShirtsBySize("Medium");
        assertEquals(tList2.size(), 1);
    }

}
