package revision.ssf_workshop14.repository;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import revision.ssf_workshop14.model.Contact;

@Repository
public class AddressBookRepo {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    private static final String CONTACT_LIST = "contactlist";

    public void save(final Contact ctc){
        redisTemplate.opsForList().leftPush(CONTACT_LIST, ctc.getId());
        redisTemplate.opsForHash().put(CONTACT_LIST + "_Map", ctc.getId(), ctc);
    }

    // public Contact save(final Contact ctc){
    //     System.out.println(ctc.getDateOfBirth());
    //     redisTemplate.opsForList()
    //             .leftPush(CONTACT_LIST, ctc.getId());
    //     redisTemplate.opsForHash()
    //         .put(CONTACT_LIST + "_Map", ctc.getId(), ctc);
    //     return findById(ctc.getId());
    // }

    public Contact findById(final String contactId){
        Contact result = (Contact) redisTemplate.opsForHash()
            .get(CONTACT_LIST + "_Map",
            contactId);
        return result;
    }

    public List<Contact> findAll(int startIndex){
        
        List<Object> obj = redisTemplate.opsForList().range(CONTACT_LIST, startIndex, 
        redisTemplate.opsForList().size(CONTACT_LIST));

        List<Contact> ctcs = redisTemplate.opsForHash().multiGet(CONTACT_LIST, obj)
                                                        .stream()
                                                        .filter(Contact.class::isInstance)
                                                        .map(Contact.class::cast)
                                                        .collect(Collectors.toList());
        
        // Alt Sol instead of using lambda expression
        // List<Object> ctcObj = redisTemplate.opsForHash().multiGet(CONTACT_LIST, obj);
        // List<Contact> ctcs = new ArrayList<>();
        // for(Object ob : ctcObj){
        //     if(ob instanceof Contact){
        //         Contact contact=(Contact)ob;
        //         ctcs.add(contact);
        //     }
        // }

        return ctcs;

    }
}
