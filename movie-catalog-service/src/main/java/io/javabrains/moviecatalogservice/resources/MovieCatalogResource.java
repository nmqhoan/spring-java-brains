package io.javabrains.moviecatalogservice.resources;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.service.MovieInfoService;
import io.javabrains.moviecatalogservice.service.UserRatingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  WebClient.Builder webClientBuilder;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    UserRatingInfoService userRatingInfoService;

    @GetMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

//        Application moveRatingServiceApp = eurekaClient.getApplication("movie-rating-service");
//        System.out.println("service name:" + moveRatingServiceApp.getInstances().get(0).getAppName());
//        System.out.println("host name:" + moveRatingServiceApp.getInstances().get(0).getHostName());
//        System.out.println("port:" + moveRatingServiceApp.getInstances().get(0).getPort());
        UserRating userRating = userRatingInfoService.getUserRating(userId);
        List<CatalogItem> catalogItems = new ArrayList<>();
        for(Rating rating:userRating.getUserRating()){
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();

            catalogItems.add(movieInfoService.getCatalogItem(rating));
        }
        return catalogItems;
    }


    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogItem("No movie","",0));
    }

    @GetMapping("/applications")
    public Applications getApplications() {
        return eurekaClient.getApplications();
    }
}
