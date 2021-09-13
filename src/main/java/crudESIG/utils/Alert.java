package crudESIG.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Alert {
	public static void addMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
}
