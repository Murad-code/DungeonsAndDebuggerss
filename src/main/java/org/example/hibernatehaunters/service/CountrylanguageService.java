package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CountrylanguageEntity;
import org.example.hibernatehaunters.models.entities.CountrylanguageEntityId;
import org.example.hibernatehaunters.models.respositories.CountrylanguageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountrylanguageService {

    private final CountrylanguageEntityRepository countrylanguageEntityRepository;

    @Autowired
    public CountrylanguageService(CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public CountrylanguageEntity createCountryLanguage(CountrylanguageEntity countryLanguage) {
        return countrylanguageEntityRepository.save(countryLanguage);
    }

    public List<CountrylanguageEntity> getAllCountryLanguages() {
        return countrylanguageEntityRepository.findAll();
    }

    public Optional<CountrylanguageEntity> getCountryLanguageById(CountrylanguageEntityId id) {
        return countrylanguageEntityRepository.findById(id);
    }

    public CountrylanguageEntity updateCountryLanguage(CountrylanguageEntityId id, CountrylanguageEntity updatedCountryLanguage) {
        Optional<CountrylanguageEntity> countryLanguageOptional = countrylanguageEntityRepository.findById(id);
        if (countryLanguageOptional.isPresent()) {
            CountrylanguageEntity existingCountryLanguage = countryLanguageOptional.get();
            existingCountryLanguage.setCountryCode(updatedCountryLanguage.getCountryCode());
            existingCountryLanguage.setIsOfficial(updatedCountryLanguage.getIsOfficial());
            existingCountryLanguage.setPercentage(updatedCountryLanguage.getPercentage());

            return countrylanguageEntityRepository.save(existingCountryLanguage);
        } else {
            return null;
        }
    }

    public boolean deleteCountryLanguage(CountrylanguageEntityId id) {
        Optional<CountrylanguageEntity> countryLanguageOptional = countrylanguageEntityRepository.findById(id);
        if (countryLanguageOptional.isPresent()) {
            countrylanguageEntityRepository.delete(countryLanguageOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteCountrylanguageEntitiesByCountryCode(String countryCode) {
        countrylanguageEntityRepository.deleteCountrylanguageEntityByCountryCode_Code(countryCode);
    }
}