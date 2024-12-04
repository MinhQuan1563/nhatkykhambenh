package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.service.implementation.GoogleCalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class CalendarController {

    private final GoogleCalendarService googleCalendarService = new GoogleCalendarService();

    @GetMapping("users/events")
    public String getEvents(Model model) {
        try {
            model.addAttribute("events", googleCalendarService.getEvents());
        } catch (IOException e) {
            model.addAttribute("error", "Unable to fetch events: " + e.getMessage());
        }
        return "users/events";
    }
}