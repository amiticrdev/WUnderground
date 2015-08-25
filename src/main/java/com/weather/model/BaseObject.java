package com.weather.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseObject
    implements Serializable, Cloneable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3542291918776688461L;

    /**
     * to string.
     * 
     * @return the string
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    /**
     * Uses reflection to generate a hashcode.
     * 
     * @return a hashcode value for the object, minus any transient vars
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    /**
     * Uses reflection to compare objects. Does not include transient vars.
     * 
     * @param obj the object to compare to
     * @return a reflective comparison of the two objects, minus any transients
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    /**
     * Making this public so everybody can clone things.
     * 
     * @return a clone of this object
     * @see java.lang.Object#clone()
     */
    public Object clone()
    {
        return clonePublic();
    }

    /**
     * Making this public so everybody can clone things.
     * 
     * @return a clone of this object
     * @see java.lang.Object#clone()
     */
    public Object clonePublic()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            InternalError e2 = new InternalError("clone failed for " + getClass());
            e2.initCause(e);
            throw e2;
        }
    }

}
