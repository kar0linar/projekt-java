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



//@FacesConverter(value="specyConverter", forClass = Specy.class)
//public class SpecyConverter implements Converter {
public class SpecyConverter  {	
	@Inject
	@EJB
	SpecyDAO specyDAO;
	
//	public SpecyConverter() {
//		specyDAO = CDI.current().select(SpecyDAO.class).get();
//	}
//	
//	@Override
//	public Object getAsObject(FacesContext contex, UIComponent component, String id) {
//		return specyDAO.find(Integer.getInteger(id));
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		return value != null ? ((Specy) value).getSpeciesId().toString() : " ";
//	}

}