
package com.kaab.proyecto.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Flores González Luis Brandon.
 */
@ManagedBean
public class RatingView {
  
    private Integer rating2;
    
    public void onrate(RateEvent rateEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
        "Rate Event", "Tu calificaste con:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }
    
    
}
