package com.sustcDB2019.DB104.controller;

import com.sustcDB2019.DB104.entity.Movie;
import com.sustcDB2019.DB104.service.MovieService;
import org.apache.maven.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Resource
    private MovieService movService;

    @RequestMapping("/showMovie")
    public Movie toIndex(HttpServletRequest request, Model model) {
        long movId=Long.parseLong(request.getParameter("id"));
        Movie movie = this.movService.getMovById(movId);
        return movie;
    }
    @RequestMapping("/showMovie1")
    public Movie toIndex1(HttpServletRequest request, Model model) {
        long movId=1002;
        Movie movie = this.movService.getMovById(movId);
        return movie;
    }
}