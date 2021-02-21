package com.hibicode.beerstore.ressorce;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.repository.Beers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResources {
    @Autowired
    private Beers beers;

    @GetMapping
    public List<String> all(){
        return Arrays.asList("BHAMAR","ORIGINAL");
    }

    @PostMapping
    public Beer create(@RequestBody Beer beer){
        return beers.save(beer);
    }

}
