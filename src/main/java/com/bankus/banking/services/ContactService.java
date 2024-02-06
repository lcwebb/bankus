package com.bankus.banking.services;

import com.bankus.banking.dto.ContactDto;
import com.bankus.banking.models.Contact;

import java.util.List;

public interface ContactService extends  AbstractService<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);

}
