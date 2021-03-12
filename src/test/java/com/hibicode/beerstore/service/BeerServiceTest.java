package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.repository.Beers;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;
import com.hibicode.beerstore.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private Beers beers;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beers);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void  should_deny_creation_of_beer_that_exists(){
        Beer beer = getNewBeer();
        when(beers.findByNameAndType("Heineken", BeerType.IPA)).thenReturn(Optional.of(getBeer()));
        beerService.save(beer);
    }

    @Test
    public void should_create_new_beer(){
        when(beers.save(getNewBeer())).thenReturn(getNewBeer());
        Beer beer = beerService.save(getNewBeer());
        assertThat(beer.getId(), equalTo(10l));
        assertThat(beer.getName(), equalTo("Heineken"));
        assertThat(beer.getType(), equalTo(BeerType.IPA));
    }


/*    @Test(expected = EntityNotFoundException.class)
    public void should_deny_delete_beer_by_id(){
        when(beers.findById (10l)).thenReturn(Optional.of(getBeer()));
        beerService.delete(10l);
        Mockito.verify(beerService).delete(10l);
    }*/

    private Beer getBeer() {
        return Beer.builder()
                .name("Heineken")
                .type(BeerType.IPA)
                .volume(new BigDecimal("355")).build();
    }

    private Beer getNewBeer() {
        return Beer.builder()
                .id(10l)
                .name("Heineken")
                .type(BeerType.IPA)
                .volume(new BigDecimal("355")).build();
    }
}
