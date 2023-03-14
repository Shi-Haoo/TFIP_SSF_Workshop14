package revision.ssf_workshop14.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import revision.ssf_workshop14.model.Contact;
import revision.ssf_workshop14.service.AddressBookService;

@Controller
@RequestMapping(path="/contact")
public class AddressBookController {

    @Autowired
    AddressBookService adbrkSvc;
    
    @GetMapping
    public String showAddressBook(Model model){
       model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    @PostMapping
    public String saveContact(Model model, @Valid Contact contact, BindingResult binding){
        
        if(binding.hasErrors()){
            return "addressbook";
        }

        adbrkSvc.save(contact);
        //model.addAttribute("contact", contact);
        return "showContact";
    }

    @GetMapping(path="{contactId}")
    public String getContactId(Model model, @PathVariable String contactId){
        
        Contact ctc = adbrkSvc.findById(contactId);
        model.addAttribute("contact", ctc);

        return "showContact";
    }

    @GetMapping(path="/list")
    public String getAllContacts(Model model, @RequestParam(defaultValue = "0")Integer startIndex){

        List<Contact> ctcs = adbrkSvc.findAll(startIndex);
        model.addAttribute("contacts", ctcs);
        
        return "contacts";
    }



}
