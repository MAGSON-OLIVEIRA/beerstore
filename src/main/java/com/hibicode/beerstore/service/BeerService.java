package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.repository.Beers;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;

import com.hibicode.beerstore.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private final Beers beers;

    public BeerService(@Autowired Beers beers) {
        this.beers = beers;
    }

    public Beer save(final Beer beer){
        Optional<Beer> beerByNameAndType = beers.findByNameAndType(beer.getName(), beer.getType());
        if(beerByNameAndType.isPresent()
                    && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerByNameAndType))){
            throw new BeerAlreadyExistException();
        }
        return beers.save(beer);
    }

    public void delete(final Long id){
        Optional<Beer> findByIdBeer = beers.findById(id);
        beers.delete(findByIdBeer.orElseThrow(() -> new EntityNotFoundException()));
    }


    public Optional<Beer> findByNameAndType(final Beer beer){
        return beers.findAll().stream()
                .filter(b -> b.getName().equals(beer.getName()))
                .filter(b -> b.getType().name().equals(beer.getType().name()))
                .findAny();
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.isExist() && !beerByNameAndType.get().equals(beer);
    }

}
