package com.senla.courses.util;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PageCheck {

    public int checkPage(Integer size) {
        if(size > 10) {
            log.log(Level.INFO, "Exceeded value 'size', size = " + size + ", but maximum allowed value = 10");
            size = 10;
        }

        return size;
    }
}
