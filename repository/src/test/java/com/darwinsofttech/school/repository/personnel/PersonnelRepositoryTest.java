package com.darwinsofttech.school.repository.personnel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonnelRepositoryTest {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Test
    public void createTest() {
        Personnel personnel = personnelRepository.save(new Personnel("Darwin", "Address"));
        System.out.println(personnel);
        Assert.assertNotNull(personnel);
    }

    @Test
    public void updateTest() {
        Personnel personnel = personnelRepository.save(new Personnel("aa", "bb"));
        System.out.println(personnel);
        personnel.setName("Update");
        personnelRepository.save(personnel);
        Assert.assertNotNull(personnel);
    }

    @Test
    public void findAllTest() {
        personnelRepository.save(new Personnel("aa", "bb"));
        List<Personnel> personnels = personnelRepository.findAll();
        Assert.assertNotNull(personnels);
        Assert.assertTrue(!personnels.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Personnel personnel1 = personnelRepository.save(new Personnel("aa", "bb"));
        Optional<Personnel> personnel = personnelRepository.findById(personnel1.getId());
        Assert.assertTrue(personnel.isPresent());
        personnel.ifPresent(p -> {
            Assert.assertEquals(p.getName(), "aa");
        });
    }

    @Test
    public void deleteTest() {
        Personnel personnel = personnelRepository.save(new Personnel("aa", "bb"));
        personnelRepository.delete(personnel);
        Assert.assertTrue(!personnelRepository.existsById(personnel.getId()));
    }

    @Test
    public void findAllByNameTest() {
      personnelRepository.save(new Personnel("aa", "bb"));
        List<Personnel> personnels = personnelRepository.findAllByName("aa");
        Assert.assertNotNull(personnels);
        Assert.assertTrue(!personnels.isEmpty());
        personnels.forEach(p -> Assert.assertEquals(p.getName(), "aa"));
    }
}