package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import storage.ModelDTOs.ReleaseDto;
import storage.services.ReleaseService;

@RestController
public class ReleaseController {

    private ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @PostMapping("/release/new")
    public String newRelease(@RequestBody ReleaseDto releaseDto){
        releaseService.newRelease(releaseDto);
        return "Release complete";
    }
}
