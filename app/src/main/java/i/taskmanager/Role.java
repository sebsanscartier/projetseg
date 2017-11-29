package i.taskmanager;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.Serializable;

/**
 * Created by newuser on 11/20/17.
 */

public class Role implements Serializable{



    private Boolean isAdministrative;
    private String description;


    public Role(){

    }

    public Role(Boolean isAdministrative, String description){
        this.isAdministrative = isAdministrative;
        this.description = description;  
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAdministrative() {
        return isAdministrative;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdministrative(Boolean administrative) {
        isAdministrative = administrative;
    }
}
