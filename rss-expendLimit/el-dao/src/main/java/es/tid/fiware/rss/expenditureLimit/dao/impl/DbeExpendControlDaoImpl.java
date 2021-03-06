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

/**
 * 
 */
package es.tid.fiware.rss.expenditureLimit.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.tid.fiware.rss.dao.impl.GenericDaoImpl;
import es.tid.fiware.rss.expenditureLimit.dao.DbeExpendControlDao;
import es.tid.fiware.rss.expenditureLimit.model.DbeExpendControl;
import es.tid.fiware.rss.expenditureLimit.model.DbeExpendLimitPK;
import es.tid.fiware.rss.model.BmCurrency;
import es.tid.fiware.rss.model.BmObCountry;
import es.tid.fiware.rss.model.BmService;

/**
 * 
 * 
 */
@Repository
public class DbeExpendControlDaoImpl extends GenericDaoImpl<DbeExpendControl, DbeExpendLimitPK> implements
    DbeExpendControlDao {

    private static Logger logger = LoggerFactory.getLogger(DbeExpendControlDaoImpl.class);

    /**
     * 
     * @param factory
     *            hibernate session factory
     */
    @Autowired
    public DbeExpendControlDaoImpl(final SessionFactory factory) {
        setSessionFactory(factory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see es.tid.greta.dbe.dao.impl.GenericDaoImpl#getDomainClass()
     */
    @Override
    protected Class<DbeExpendControl> getDomainClass() {
        return DbeExpendControl.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * es.tid.greta.dbe.expenditureLimit.dao.DbeExpendControlDao#getExpendDataForUserAppProvCurrencyObCountry(java.lang
     * .String, es.tid.greta.dbe.model.BmService, java.lang.String, es.tid.greta.dbe.model.BmCurrency,
     * es.tid.greta.dbe.model.BmObCountry)
     */
    @Override
    public List<DbeExpendControl> getExpendDataForUserAppProvCurrencyObCountry(String urlEndUserId,
        BmService bmService, String appProviderId, BmCurrency bmCurrency, BmObCountry bmObCountry) {
        DbeExpendControlDaoImpl.logger.debug("Entering getExpendDataForUserAppProvCurrencyObCountry...");

        String hql = " from DbeExpendControl el where el.id.txEndUserId = ? and el.id.nuServiceId = ?"
            + " and el.id.nuCurrencyId = ? and el.id.nuCountryId = ? and el.id.nuObId = ? "
            + " and el.id.txAppProviderId = ?";

        @SuppressWarnings("unchecked")
        List<DbeExpendControl> list = getHibernateTemplate().find(hql, urlEndUserId,
            bmService.getNuServiceId(), bmCurrency.getNuCurrencyId(), bmObCountry.getId().getNuCountryId(),
            bmObCountry.getId().getNuObId(), appProviderId);

        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * es.tid.greta.dbe.expenditureLimit.dao.DbeExpendControlDao#saveDbeExpendControl(es.tid.greta.dbe.expenditureLimit
     * .model.DbeExpendControl)
     */
    @Override
    public void saveDbeExpendControl(DbeExpendControl expendData) {
        DbeExpendControlDaoImpl.logger.debug("Entering saveDbeExpendControl...");
        if ((expendData != null)
            && (expendData.getId() != null)
            && (expendData.getId().getTxEndUserId() != null) && (expendData.getId().getTxEndUserId().length() > 0)
            && (expendData.getId().getTxAppProviderId() != null)
            && (expendData.getId().getTxAppProviderId().length() > 0)
            && (expendData.getId().getTxElType() != null) && (expendData.getId().getTxElType().length() > 0)) {
            getHibernateTemplate().saveOrUpdate(expendData);
        }

    }

}
