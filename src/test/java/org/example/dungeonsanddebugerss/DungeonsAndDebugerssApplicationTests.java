package org.example.dungeonsanddebugerss;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.service.WorldService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
class DungeonsAndDebugerssApplicationTests {

    @Autowired
    WorldService worldService;

    @Test
    void contextLoads() {
    }
  
    @Nested
    class testingFindCountOfMostPopularLanguage{
        @Test
        @DisplayName("Given Afghanistan, the number of people who speak the most popular official language is 11905280 ")
        void givenAfghanistanTheNumberOfPeopleWhoSpeakTheMostPopularOfficialLanguageIs11905280() {
            Assertions.assertEquals(11905280,worldService.findCountOfMostPopularLanguage("Afghanistan"));
        }

        @Test
        @DisplayName("Given a country doesn't exists, return negative 1")
        void givenACountryDoesnTExistsReturnNegative1() {
            Assertions.assertEquals(-1,worldService.findCountOfMostPopularLanguage("BABYLON"));
        }

        @Test
        @DisplayName("Given Aruba, the number of people who speak the most popular official language is ")
        void givenArubaTheNumberOfPeopleWhoSpeakTheMostPopularOfficialLanguageIs() {
            Assertions.assertEquals(5459,worldService.findCountOfMostPopularLanguage("Aruba"));
        }

        @Test
        @DisplayName("Given Angola, country has no official language so it should return -1")
        void givenAngolaCountryHasNoOfficialLanguageSoItShouldReturn1() {
            Assertions.assertEquals(-1,worldService.findCountOfMostPopularLanguage("Angola"));
        }


    }

    @Test
    void checkCountryWithNoHeadOfStateIsCalledSanMarino() {
        Assertions.assertEquals("San Marino", worldService.findCountriesWithNoHeadOfState().getFirst().getName());
    }

    @Test
    @DisplayName("Testing that 5 lowest cities are returned")
    void checkFivesCitiesWithLowestPopulationAreReturned(){

        List<CityEntity> cityEntities = new ArrayList<>();

        CityEntity city1 = new CityEntity();
        city1.setName("City1");
        city1.setDistrict("District1");
        city1.setPopulation(2000);
        cityEntities.add(city1);

        CityEntity city4 = new CityEntity();
        city4.setName("City4");
        city4.setDistrict("District1");
        city4.setPopulation(1000);
        cityEntities.add(city4);

        CityEntity city5 = new CityEntity();
        city5.setName("City5");
        city5.setDistrict("District1");
        city5.setPopulation(5000);
        cityEntities.add(city5);

        CityEntity city6 = new CityEntity();
        city6.setName("City6");
        city6.setDistrict("District1");
        city6.setPopulation(3000);
        cityEntities.add(city6);


        CityEntity city2 = new CityEntity();
        city2.setName("City2");
        city2.setDistrict("District1");
        city2.setPopulation(2000);
        cityEntities.add(city2);

        CityEntity city3 = new CityEntity();
        city3.setName("City3");
        city3.setDistrict("District1");
        city3.setPopulation(500);
        cityEntities.add(city3);


        CityEntityRepository cityEntityRepository = Mockito.mock(CityEntityRepository.class);
        Mockito.when(cityEntityRepository.findAll()).thenReturn(cityEntities);


        List<CityEntity> result = worldService.find5SmallestDistrictsOfCity("District1");

        assertEquals(5, result.size());
        assertEquals("City3", result.get(0).getName());
        assertEquals("City4", result.get(1).getName());
        assertEquals("City1", result.get(2).getName());
        assertEquals("City2", result.get(3).getName());
        assertEquals("City6", result.get(4).getName());

    }
  
    @Test
    @DisplayName("Given invalid country, get percentage returns 0.")
    void givenInvalidCountryGetPercentageReturns0() {
        float expected = 0;
        float result = worldService.findPercentageOfPopulationInLargestCity("Not A Country");
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Given empty country, get percentage returns 0.")
    void givenEmptyCountryGetPercentReturns0() {
        float expected = 0;
        float result = worldService.findPercentageOfPopulationInLargestCity("");
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Given valid country, return the percentage of the population of the highest city pop.")
    void givenValidCountryReturnThePercentageOfThePopulation() {
        float expected = 4.609178f;
        float result = worldService.findPercentageOfPopulationInLargestCity("Netherlands");
        Assertions.assertEquals(expected, result);
    }

    @Test
    void checkCountryWithNoHeadOfStateIsNotMoreThanOne() {
        Assertions.assertEquals(1, worldService.findCountriesWithNoHeadOfState().size());
    }
    @Nested
    class findCountryWithMostCitiesTests {
        @Test
        void checkCountryWithMostCitiesReturnsChina() {
            Assertions.assertEquals("China", worldService.findCountryWithMostCity().getFirst().getName());
        }


        @Test
        void checkCountryWithMostCitiesListsIsNotMoreThanOne() {
            Assertions.assertEquals(1, worldService.findCountryWithMostCity().size());


        }
    }
}
