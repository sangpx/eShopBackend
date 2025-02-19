package com.base.springsecurity.controller;


import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.model.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.model.dto.payload.response.MessageResponse;
import com.base.springsecurity.model.entity.Rating;
import com.base.springsecurity.model.entity.User;
import com.base.springsecurity.security.services.UserDetailsImpl;
import com.base.springsecurity.service.RatingService;
import com.base.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

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
    public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){
        List<Rating> ratings = ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

}
