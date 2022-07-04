package com.kevin.java.gson.attr;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;


public class RuleEntity {

    /**
     * attr : ptz_cruise_enable
     * on : [{"attr":"ptz_cruise_enable","value":"1"},{"attr":"pets_track_enable","value":"0"},{"attr":"sound_track_enable","value":"0"},{"attr":"humans_track_enable","value":"0"}]
     * off : [{"attr":"ptz_cruise_enable","value":"0"}]
     */

    @SerializedName("attr")
    private String attr;
    @SerializedName("on")
    private List<AttrEntity> on;
    @SerializedName("off")
    private List<AttrEntity> off;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public List<AttrEntity> getOn() {
        return on;
    }

    public void setOn(List<AttrEntity> on) {
        this.on = on;
    }

    public List<AttrEntity> getOff() {
        return off;
    }

    public void setOff(List<AttrEntity> off) {
        this.off = off;
    }

    public static class AttrEntity {
        /**
         * attr : ptz_cruise_enable
         * value : 1
         */

        @SerializedName("attr")
        private String attr;
        @SerializedName("value")
        private String value;

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AttrEntity that = (AttrEntity) o;
            return Objects.equals(attr, that.attr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(attr);
        }

        @Override
        public String toString() {
            return "AttrEntity{" +
                    "attr='" + attr + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
