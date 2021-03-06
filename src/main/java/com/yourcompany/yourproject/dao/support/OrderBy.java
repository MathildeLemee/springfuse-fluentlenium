/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/main/java/project/dao/support/OrderBy.p.vm.java
 */
package com.yourcompany.yourproject.dao.support;

import java.io.Serializable;

import static com.yourcompany.yourproject.dao.support.OrderByDirection.ASC;
import static com.yourcompany.yourproject.dao.support.OrderByDirection.DESC;

import org.apache.commons.lang.Validate;

/**
 * Holder class for search ordering used by the {@link SearchTemplate}  
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String column;
    private OrderByDirection direction;

    public OrderBy(String column, OrderByDirection direction) {
        Validate.notNull(column);
        Validate.notNull(direction);
        this.column = column;
        this.direction = direction;
    }

    public OrderBy(String column) {
        Validate.notNull(column);
        this.column = column;
        this.direction = ASC;
    }

    public String getColumn() {
        return column;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}