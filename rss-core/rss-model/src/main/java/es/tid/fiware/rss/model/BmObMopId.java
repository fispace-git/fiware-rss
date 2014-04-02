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

// Generated 10-feb-2012 11:04:29 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BmObMopId generated by hbm2java.
 */
@Embeddable
public class BmObMopId implements java.io.Serializable {

    private long nuObId;
    private long nuCountryId;
    private long nuMopId;

    /**
     * Constructor.
     */
    public BmObMopId() {
    }

    /**
     * Constructor.
     * 
     * @param nuObId
     * @param nuCountryId
     * @param nuMopId
     */
    public BmObMopId(long nuObId, long nuCountryId, long nuMopId) {
        this.nuObId = nuObId;
        this.nuCountryId = nuCountryId;
        this.nuMopId = nuMopId;
    }

    @Column(name = "NU_OB_ID", nullable = false, precision = 10, scale = 0)
    public long getNuObId() {
        return this.nuObId;
    }

    public void setNuObId(long nuObId) {
        this.nuObId = nuObId;
    }

    @Column(name = "NU_COUNTRY_ID", nullable = false, precision = 10, scale = 0)
    public long getNuCountryId() {
        return this.nuCountryId;
    }

    public void setNuCountryId(long nuCountryId) {
        this.nuCountryId = nuCountryId;
    }

    @Column(name = "NU_MOP_ID", nullable = false, precision = 10, scale = 0)
    public long getNuMopId() {
        return this.nuMopId;
    }

    public void setNuMopId(long nuMopId) {
        this.nuMopId = nuMopId;
    }

    /**
     * Override.
     * 
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BmObMopId)) {
            return false;
        }
        BmObMopId castOther = (BmObMopId) other;

        return (this.getNuObId() == castOther.getNuObId())
            && (this.getNuCountryId() == castOther.getNuCountryId())
            && (this.getNuMopId() == castOther.getNuMopId());
    }

    /**
     * Override.
     * 
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (int) this.getNuObId();
        result = 37 * result + (int) this.getNuCountryId();
        result = 37 * result + (int) this.getNuMopId();
        return result;
    }

}