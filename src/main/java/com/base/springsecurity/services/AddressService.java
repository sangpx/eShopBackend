package com.base.springsecurity.services;

import com.base.springsecurity.models.dto.catalog.address.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAllAddresses();
    AddressDTO getAddressById(Long id);
    AddressDTO insertAddress(AddressDTO addressDTO);
    AddressDTO updateAddress(AddressDTO addressDTO, Long id);
    void deleteAddress(Long id);
}
