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

/*
 * DbeSystemPropertiesDaoTest.java
 * 
 * 2012 ®, Telefónica I+D, all rights reserved
 */
package es.tid.fiware.rss.expenditureLimit.dao.impl.tests;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import es.tid.fiware.rss.common.test.DatabaseLoader;
import es.tid.fiware.rss.dao.DbeSystemPropertiesDao;
import es.tid.fiware.rss.expenditureLimit.dao.DbeExpendControlDao;
import es.tid.fiware.rss.expenditureLimit.model.DbeExpendControl;
import es.tid.fiware.rss.expenditureLimit.model.DbeExpendLimitPK;
import es.tid.fiware.rss.model.BmCurrency;
import es.tid.fiware.rss.model.BmObCountry;
import es.tid.fiware.rss.model.BmObCountryId;
import es.tid.fiware.rss.model.BmService;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:database.xml"})
public class DbeExpendControlDaoTest {

    /**
     * Logging system.
     */
    private static Logger logger = LoggerFactory.getLogger(DbeExpendControlDaoTest.class);

    /**
     * DAO for DBESystemProperties
     */
    @Autowired
    private DbeSystemPropertiesDao dbeSystemPropertiesDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DbeExpendControlDao expLimitDao;

    @Autowired
    private DatabaseLoader databaseLoader;

    @Autowired
    private HibernateTransactionManager transactionManager;

    /**
     * Method to insert data before test.
     * 
     * @throws Exception
     *             from db
     */
    @Before
    public void setUp() throws Exception {
        databaseLoader.cleanInsert("dbunit/CREATE_DATATEST_EXPLIMIT.xml", true);
    }

    @After
    public void tearDown() throws Exception {
        databaseLoader.deleteAll("dbunit/CREATE_DATATEST_EXPLIMIT.xml", true);
    }

    @Test
    public void testGetExpendLimitDataForaUser() {

        BmService bmService = new BmService();
        bmService.setNuServiceId(1);
        BmCurrency bmCurrency = new BmCurrency();
        bmCurrency.setNuCurrencyId(1);
        BmObCountry bmObCountry = new BmObCountry();
        bmObCountry.setId(new BmObCountryId(1, 1));
        List<DbeExpendControl> l = expLimitDao.getExpendDataForUserAppProvCurrencyObCountry("userId01", bmService,
            "app123456", bmCurrency,
            bmObCountry);

        Assert.assertTrue("Elements founds", l != null && l.size() == 3);
        Iterator<DbeExpendControl> it = l.iterator();
        while (it.hasNext()) {
            DbeExpendControl el = it.next();
            if (!el.getId().getTxAppProviderId().equalsIgnoreCase("app123456")) {
                Assert.fail("Application provider invalid: " + el.getId().getTxAppProviderId());
            }
            if (!el.getId().getTxEndUserId().equalsIgnoreCase("userId01")) {
                Assert.fail("User invalid: " + el.getId().getTxEndUserId());
            }
        }
    }

    @Test
    public void testUpdateExpendLimitDataForaUser() {

        BmService bmService = new BmService();
        bmService.setNuServiceId(1);
        BmCurrency bmCurrency = new BmCurrency();
        bmCurrency.setNuCurrencyId(1);
        BmObCountry bmObCountry = new BmObCountry();
        bmObCountry.setId(new BmObCountryId(1, 1));
        List<DbeExpendControl> l = expLimitDao.getExpendDataForUserAppProvCurrencyObCountry("userId01", bmService,
            "app123456", bmCurrency, bmObCountry);

        Assert.assertTrue("Elements founds", l != null && l.size() == 3);
        Iterator<DbeExpendControl> it = l.iterator();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
        TransactionStatus status = transactionManager.getTransaction(def);
        while (it.hasNext()) {
            DbeExpendControl el = it.next();
            if (el.getId().getTxElType().equalsIgnoreCase("daily")) {
                el.setFtExpensedAmount(new BigDecimal("0"));
                Date dt = new Date(new Date().getTime() + 23 * 36000 * 1000);
                el.setDtNextPeriodStart(dt);
                el.setTxNotifications("");
            } else {
                el.setFtExpensedAmount(el.getFtExpensedAmount().add(new BigDecimal("22")));
                el.setTxNotifications(el.getTxNotifications() + ", 50");
            }
            expLimitDao.saveDbeExpendControl(el);
        }
        transactionManager.commit(status);

        l = expLimitDao.getExpendDataForUserAppProvCurrencyObCountry("userId01", bmService,
            "app123456", bmCurrency, bmObCountry);

        it = l.iterator();
        while (it.hasNext()) {
            DbeExpendControl el = it.next();
            if (el.getId().getTxElType().equalsIgnoreCase("daily")) {
                Assert.assertTrue("Daily accumulate: ", el.getFtExpensedAmount().floatValue() == 0);
                Assert.assertTrue("Notifications: ", el.getTxNotifications() == null
                    || el.getTxNotifications().length() == 0);
            } else {
                Assert.assertTrue("Notifications: ", el.getTxNotifications().indexOf("50") > -1);
            }
        }
    }

    @Test
    public void testNewExpendLimitDataForaUser() {

        BmService bmService = new BmService();
        bmService.setNuServiceId(1);
        BmCurrency bmCurrency = new BmCurrency();
        bmCurrency.setNuCurrencyId(1);
        BmObCountry bmObCountry = new BmObCountry();
        bmObCountry.setId(new BmObCountryId(1, 1));
        List<DbeExpendControl> l = expLimitDao.getExpendDataForUserAppProvCurrencyObCountry("userId01", bmService,
            "app123456", bmCurrency, bmObCountry);

        Assert.assertTrue("Elements founds", l != null && l.size() == 3);
        Iterator<DbeExpendControl> it = l.iterator();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
        TransactionStatus status = transactionManager.getTransaction(def);
        while (it.hasNext()) {
            DbeExpendControl el = it.next();
            DbeExpendControl neoEc = new DbeExpendControl();
            neoEc.setId(new DbeExpendLimitPK());
            neoEc.getId().setNuCountryId(el.getId().getNuCountryId());
            neoEc.getId().setNuCurrencyId(el.getId().getNuCountryId());
            neoEc.getId().setNuObId(el.getId().getNuCountryId());
            neoEc.getId().setNuServiceId(el.getId().getNuCountryId());
            neoEc.getId().setTxAppProviderId("123456");
            neoEc.getId().setTxElType(el.getId().getTxElType());
            neoEc.getId().setTxEndUserId("userId101");
            neoEc.setDtNextPeriodStart(el.getDtNextPeriodStart());
            neoEc.setTxNotifications(el.getTxNotifications());
            neoEc.setFtExpensedAmount(el.getFtExpensedAmount());
            expLimitDao.saveDbeExpendControl(neoEc);
        }
        transactionManager.commit(status);
        l = expLimitDao.getExpendDataForUserAppProvCurrencyObCountry("userId101", bmService,
            "123456", bmCurrency, bmObCountry);

        Assert.assertTrue("Elements founds", l != null && l.size() == 3);
        it = l.iterator();
        while (it.hasNext()) {
            DbeExpendControl el = it.next();
            if (!el.getId().getTxAppProviderId().equalsIgnoreCase("123456")) {
                Assert.fail("Application provider invalid: " + el.getId().getTxAppProviderId());
            }
            if (!el.getId().getTxEndUserId().equalsIgnoreCase("userId101")) {
                Assert.fail("User invalid: " + el.getId().getTxEndUserId());
            }
        }
    }
}
