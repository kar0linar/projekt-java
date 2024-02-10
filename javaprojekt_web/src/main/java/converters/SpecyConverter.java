package converters;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jsf.dao.SpecyDAO;
import com.jsf.entities.Specy;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.inject.spi.CDI;


public class SpecyConverter  {	
	@Inject
	@EJB
	SpecyDAO specyDAO;
	
}