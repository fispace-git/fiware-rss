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

// Generated 24-abr-2012 17:09:13 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * BmServiceProductType generated by hbm2java.
 */
@Entity
@Table(name = "bm_service_product_type",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "NU_SERVICE_ID", "TX_NAME"
        }))
public class BmServiceProductType implements java.io.Serializable {

    private BmServiceProductTypeId id;
    private BmService bmService;
    private String txName;
    private String txDescription;
    private String tcIsDefaultYn;
    private Set<BmProduct> bmProducts = new HashSet<BmProduct>(0);

    /**
     * Constructor.
     */
    public BmServiceProductType() {
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param bmService
     * @param txName
     */
    public BmServiceProductType(BmServiceProductTypeId id, BmService bmService, String txName) {
        this.id = id;
        this.bmService = bmService;
        this.txName = txName;
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param bmService
     * @param txName
     * @param txDescription
     * @param tcIsDefaultYn
     * @param bmProducts
     * @param bmTaxServprodtypeLandDefs
     */
    public BmServiceProductType(BmServiceProductTypeId id, BmService bmService, String txName, String txDescription,
        String tcIsDefaultYn, Set<BmProduct> bmProducts) {
        this.id = id;
        this.bmService = bmService;
        this.txName = txName;
        this.txDescription = txDescription;
        this.tcIsDefaultYn = tcIsDefaultYn;
        this.bmProducts = bmProducts;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "nuServiceId", column = @Column(name = "NU_SERVICE_ID", nullable = false,
            precision = 10, scale = 0)),
        @AttributeOverride(name = "nuServiceProductTypeId", column = @Column(name = "NU_SERVICE_PRODUCT_TYPE_ID",
            nullable = false, precision = 10, scale = 0))
    })
    public BmServiceProductTypeId getId() {
        return this.id;
    }

    public void setId(BmServiceProductTypeId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NU_SERVICE_ID", nullable = false, insertable = false, updatable = false)
    public BmService getBmService() {
        return this.bmService;
    }

    public void setBmService(BmService bmService) {
        this.bmService = bmService;
    }

    @Column(name = "TX_NAME", nullable = false, length = 20)
    public String getTxName() {
        return this.txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }

    @Column(name = "TX_DESCRIPTION", length = 250)
    public String getTxDescription() {
        return this.txDescription;
    }

    public void setTxDescription(String txDescription) {
        this.txDescription = txDescription;
    }

    @Column(name = "TC_IS_DEFAULT_YN", length = 1)
    public String getTcIsDefaultYn() {
        return this.tcIsDefaultYn;
    }

    public void setTcIsDefaultYn(String tcIsDefaultYn) {
        this.tcIsDefaultYn = tcIsDefaultYn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bmServiceProductType")
    public Set<BmProduct> getBmProducts() {
        return this.bmProducts;
    }

    public void setBmProducts(Set<BmProduct> bmProducts) {
        this.bmProducts = bmProducts;
    }

}
