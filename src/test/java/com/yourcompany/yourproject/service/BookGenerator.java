/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/test/java/manager/ModelGenerator.e.vm.java
 */
package com.yourcompany.yourproject.service;

import java.util.*;

import com.yourcompany.yourproject.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.yourproject.domain.Book;

/**
 * Helper class to create transient entities instance for testing purposes.
 * Simple properties are pre-filled with random values.
 */
@Service
public class BookGenerator {

    /**
     * Returns a new Book instance filled with random values.
     */
    public Book getBook() {
        Book book = new Book();

        // simple attributes follows
        book.setTitle("d");
        book.setNumberOfPages(ValueGenerator.getNumeric(java.lang.Integer.class, "1"));
        return book;
    }

}