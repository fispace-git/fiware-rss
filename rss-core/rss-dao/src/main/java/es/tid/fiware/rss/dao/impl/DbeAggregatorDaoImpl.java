/**
 * Revenue Settlement and Sharing System GE
 * Copyright (C) 2011-2014, Javier Lucio - lucio@tid.es
 * Telefonica Investigacion y Desarrollo, S.A.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package es.tid.fiware.rss.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.tid.fiware.rss.dao.DbeAggregatorDao;
import es.tid.fiware.rss.model.DbeAggregator;

/**
 * 
 * 
 */
@Repository
public class DbeAggregatorDaoImpl extends GenericDaoImpl<DbeAggregator, String> implements DbeAggregatorDao {
    /**
     * Variable to print the trace.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbeAppProviderDaoImpl.class);

    /**
     * 
     * @param factory
     *            hibernate session factory
     */
    @Autowired
    public DbeAggregatorDaoImpl(final SessionFactory factory) {
        setSessionFactory(factory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see es.tid.fiware.rss.dao.impl.GenericDaoImpl#getDomainClass()
     */
    @Override
    protected Class<DbeAggregator> getDomainClass() {
        return DbeAggregator.class;
    }

}