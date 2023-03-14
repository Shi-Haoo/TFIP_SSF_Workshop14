package revision.ssf_workshop14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import revision.ssf_workshop14.model.Contact;
import revision.ssf_workshop14.repository.AddressBookRepo;

@Service
public class AddressBookService {
    
    @Autowired 
    AddressBookRepo adbrkRepo;

    public void save(final Contact ctc){
        adbrkRepo.save(ctc);
    }

    public Contact findById(final String contactId){
        return adbrkRepo.findById(contactId);
    }

    public List<Contact> findAll(int startIndex){
        return adbrkRepo.findAll(startIndex);
    }
}
