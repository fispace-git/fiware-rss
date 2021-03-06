/**
 * Revenue Settlement and Sharing System GE
 * Copyright (C) 2011-2014,  Javier Lucio - lucio@tid.es
 * Telefonica Investigacion y Desarrollo, S.A.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.tid.fiware.rss.model;

// Generated 20-feb-2012 9:51:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * BmOb generated by hbm2java.
 */
@Entity
@Table(name = "bm_ob")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class BmOb implements java.io.Serializable {

    private long nuObId;
    private BmCountry bmCountry;
    private String txName;
    private String txBrand;
    private Set<BmObCountry> bmObCountries = new HashSet<BmObCountry>(0);

    /**
     * Constructor.
     */
    public BmOb() {
    }

    /**
     * Constructor.
     * 
     * @param nuObId
     * @param bmCountry
     * @param txName
     * @param txBrand
     */
    public BmOb(long nuObId, BmCountry bmCountry, String txName, String txBrand) {
        this.nuObId = nuObId;
        this.bmCountry = bmCountry;
        this.txName = txName;
        this.txBrand = txBrand;
    }

    /**
     * Constructor.
     * 
     * @param nuObId
     * @param bmCountry
     * @param txName
     * @param txBrand
     * @param bmObCountries
     */
    public BmOb(long nuObId, BmCountry bmCountry, String txName, String txBrand, Set<BmObCountry> bmObCountries) {
        this.nuObId = nuObId;
        this.bmCountry = bmCountry;
        this.txName = txName;
        this.txBrand = txBrand;
        this.bmObCountries = bmObCountries;
    }

    @Id
    @Column(name = "NU_OB_ID", unique = true, nullable = false, precision = 10, scale = 0)
    public long getNuObId() {
        return this.nuObId;
    }

    public void setNuObId(long nuObId) {
        this.nuObId = nuObId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NU_COUNTRY_ID", nullable = false)
    public BmCountry getBmCountry() {
        return this.bmCountry;
    }

    public void setBmCountry(BmCountry bmCountry) {
        this.bmCountry = bmCountry;
    }

    @Column(name = "TX_NAME", nullable = false, length = 20)
    public String getTxName() {
        return this.txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }

    @Column(name = "TX_BRAND", nullable = false, length = 20)
    public String getTxBrand() {
        return this.txBrand;
    }

    public void setTxBrand(String txBrand) {
        this.txBrand = txBrand;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bmOb")
    public Set<BmObCountry> getBmObCountries() {
        return this.bmObCountries;
    }

    public void setBmObCountries(Set<BmObCountry> bmObCountries) {
        this.bmObCountries = bmObCountries;
    }

}
