package com.leopaulmartin.spring.leboncoinecole.web.controllers;

import com.leopaulmartin.spring.leboncoinecole.exceptionhandler.exceptions.RecordNotFoundException;
import com.leopaulmartin.spring.leboncoinecole.persistence.entities.Announcement;
import com.leopaulmartin.spring.leboncoinecole.persistence.entities.Category;
import com.leopaulmartin.spring.leboncoinecole.services.AnnouncementService;
import com.leopaulmartin.spring.leboncoinecole.services.CategoryService;
import com.leopaulmartin.spring.leboncoinecole.web.dto.AnnounceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/my-announces")
public class MyAnnouncesController {
    public static final String REDIRECT = "redirect:/";
    private static final Logger logger = LoggerFactory.getLogger(MyAnnouncesController.class);

    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("announceDto")
    public AnnounceDto announceDto() {
        return new AnnounceDto();
    }
    @ModelAttribute("allCategories")
    public List<Category> populateCategories() {
        return categoryService.getAllCategories();
    }
    @ModelAttribute("allAnnouncements")
    public List<Announcement> populateAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping
    public String showMyAnnounces() {
        return "student/my-announces";
    }

    @GetMapping(path = {"/create", "/edit/{id}"})
    public String showCreateEditAnnouncement(Model model,
                                             @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {

        //TODO: if url path is "create" then set the title page to "Create Announcement" else "Edit"

        if (id.isPresent()) {
            Announcement announcement = announcementService.getAnnouncementById(id.get());
            model.addAttribute("announcement", announcement);
        } else {
            //model.addAttribute("announcement", new Announcement());
        }
        model.addAttribute("allCategories", categoryService.getAllCategories());

        return "student/add-edit-announcement";
    }

    @PostMapping("/createUpdate")
    public String handleCreateUpdateAnnouncementRequest(Model model,
                                                        @ModelAttribute("announceDto") @Valid AnnounceDto announcementDto,
                                                        BindingResult result) {
        logger.debug("handleCreateUpdateAnnouncementRequest: "+announcementDto);
        if(!result.hasErrors()) {
            Announcement createUpdateAnnounce = announcementService.createOrUpdateAnnouncement(announcementDto);
            Long id = createUpdateAnnounce.getAnnouncementId();
            return REDIRECT + "announces/"+id;
        } else {
            //TODO: manage errors
            logger.debug(result.toString());
            //TODO: if url path is "create" then set the title page to "Create Announcement" else "Edit"
            return "student/add-edit-announcement";
        }
    }

    @PostMapping("/delete")
    public String handleDeleteAnnouncementRequest() {
        logger.debug("handleDeleteAnnouncementRequest");
        return REDIRECT + "my-announces";
    }
}
