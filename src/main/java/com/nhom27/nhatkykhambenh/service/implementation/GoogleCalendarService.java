package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.configuration.GoogleCalendarConfig;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleCalendarService {
    public List<Event> getEvents() throws IOException {
        Calendar service = GoogleCalendarConfig.getCalendarService();
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        return events.getItems();
    }
}
