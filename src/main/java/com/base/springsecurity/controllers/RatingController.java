package com.base.springsecurity.controllers;


import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.models.dto.payload.response.MessageResponse;
import com.base.springsecurity.models.entity.Rating;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.security.jwt.JwtUtils;
import com.base.springsecurity.security.services.UserDetailsImpl;
import com.base.springsecurity.services.RatingService;
import com.base.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/createRating")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO) throws ProductException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Optional<User> userOptional = userService.findByUsername(userDetails.getUsername());
            if(userOptional.isPresent()) {
                User user = userOptional.get();
                Rating rating = ratingService.createRating(ratingDTO, user);
                return ResponseEntity.ok(new MessageResponse("Rating success!", true));
            }else {
                // Xử lý trường hợp không tìm thấy người dùng
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        // Xử lý trường hợp không tìm thấy người dùng
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/productRating/{productId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){
        List<Rating> ratings = ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

}
