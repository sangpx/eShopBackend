package com.base.springsecurity.controller;


import com.base.springsecurity.model.dto.catalog.address.AddressDTO;
import com.base.springsecurity.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAllAddresses")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/getAddressById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping("/admin/insertAddress")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> insertAddress(@Valid @RequestBody AddressDTO request) {
        AddressDTO savedAddress = addressService.insertAddress(request);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateAddress/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO request, @PathVariable Long id) {
        AddressDTO savedAddress = addressService.updateAddress(request, id);
        return ResponseEntity.ok(savedAddress);
    }

    @DeleteMapping("/admin/deleteAddress/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("deleted successfully!");
    }
}
