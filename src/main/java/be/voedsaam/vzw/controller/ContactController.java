package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.ContactService;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.TaskService;
import be.voedsaam.vzw.service.dto.ContactDTO;
import be.voedsaam.vzw.service.dto.TaskDTO;
import be.voedsaam.vzw.service.mapper.ContactMapper;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    private ContactMapper contactMapper;


    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @RequestMapping({"/list"})
    public String listArticles(Model model){
        model.addAttribute("contacts", contactMapper.mapToDTO((List<Contact>)contactService.listAll()));

        return "contact/list";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(ContactDTO dto, Model model){
        Contact contact = contactMapper.mapToObj(dto);
        contact = contactService.saveOrUpdate(contact);
        model.addAttribute("success", true);
        model.addAttribute("contact", new ContactDTO());
        return "redirect:/contactSuccess";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping("/new/{id}")
    public String newContact(@PathVariable Integer id) throws UnsupportedOperationException{
        Contact contact = new Contact();
        contact = contactService.saveOrUpdate(contact);
        return "redirect:/contact/edit/" + contact.getId();
    }

    @RequestMapping("/show/{id}")
    public String get(@PathVariable Integer id, Model model){
        Contact contact = contactService.getById(id.longValue());
        model.addAttribute("contact", contactMapper.mapToDTO(contact));
        return "contact/show";
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        contactService.delete(id.longValue());
        return "redirect:/contact/list";
    }

}
