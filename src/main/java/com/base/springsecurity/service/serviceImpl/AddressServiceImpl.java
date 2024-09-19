package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.ResourceNotFoundException;
import com.base.springsecurity.model.dto.catalog.address.AddressDTO;
import com.base.springsecurity.model.entity.Address;
import com.base.springsecurity.repository.AddressRepository;
import com.base.springsecurity.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AddressDTO> getAllAddresses() {
        //DTO -> Entity
        List<Address> listAddressEntity = addressRepository.findAll();
        //Entity -> DTO
        return listAddressEntity.stream()
            .map((address) -> modelMapper.map(address, AddressDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        return  modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO insertAddress(AddressDTO addressDTO) {
        //Map DTO -> Entity
        Address address = modelMapper.map(addressDTO, Address.class);
        //Save Address
        Address savedAddress = addressRepository.save(address);
        //Map entity -> DTO
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO, Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        //Map DTO -> Entity
        address.setFirstName(addressDTO.getFirstName());
        address.setLastName(addressDTO.getLastName());
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setMobile(addressDTO.getMobile());

        Address updateAddress = addressRepository.save(address);
        //Map Entity -> DTO
        return  modelMapper.map(updateAddress, AddressDTO.class);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        addressRepository.delete(address);
    }
}
