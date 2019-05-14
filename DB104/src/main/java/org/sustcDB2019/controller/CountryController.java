package com.sustcDB2019.DB104.controller;

import com.sustcDB2019.DB104.entity.Country;
import com.sustcDB2019.DB104.service.CountryService;
import org.apache.maven.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Resource
    private CountryService countryService;

    @RequestMapping("/showCountry")
    public Country toIndex(HttpServletRequest request, Model model) {
        String countryId = request.getParameter("id");
        Country country=this.countryService.getCountryById(countryId);
        return country;
    }
    @RequestMapping("/showCountry1")
    public Country toIndex1(HttpServletRequest request, Model model) {
        String countryId = "cn";
        Country country=this.countryService.getCountryById(countryId);
        return country;
    }
}
