package io.javabrains.movieratingservice.resource;

import io.javabrains.movieratingservice.models.Rating;
import io.javabrains.movieratingservice.models.UserRating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4,instanceId);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratingList = Arrays.asList(
            new Rating("200",4,instanceId),
            new Rating("100",3,instanceId)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratingList);
        return userRating;
    }
}
