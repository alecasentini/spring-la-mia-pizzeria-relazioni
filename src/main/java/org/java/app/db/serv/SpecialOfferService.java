package org.java.app.db.serv;

import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialOfferService {

    private final SpecialOfferRepo specialOfferRepo;

    @Autowired
    public SpecialOfferService(SpecialOfferRepo specialOfferRepo) {
        this.specialOfferRepo = specialOfferRepo;
    }

    public List<SpecialOffer> getAllSpecialOffers() {
        return specialOfferRepo.findAll();
    }

    public SpecialOffer getSpecialOfferById(int id) {
        return specialOfferRepo.findById(id).orElse(null);
    }

    public SpecialOffer createSpecialOffer(SpecialOffer specialOffer) {
        return specialOfferRepo.save(specialOffer);
    }

    public SpecialOffer updateSpecialOffer(SpecialOffer specialOffer) {
        return specialOfferRepo.save(specialOffer);
    }

    public void deleteSpecialOffer(int id) {
        specialOfferRepo.deleteById(id);
    }
}
