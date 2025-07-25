package com.code.ecom.dto;

import com.code.ecom.entity.Address;
import lombok.Data;

@Data
public class AddressDto {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

//    public static Address mapToAddress(Address userAddress, AddressDto address) {
//        userAddress.setStreet(address.getStreet());
//        userAddress.setCity(address.getCity());
//        userAddress.setState(address.getState());
//        userAddress.setCountry(address.getCountry());
//        userAddress.setZipcode(address.getZipcode());
//        return userAddress;
//    }
}
