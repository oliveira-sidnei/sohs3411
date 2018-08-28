package br.com.ins.services;

/**
 *
 * @author F9067893
 */
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "oneMenuConverter")
public class OneMenuConverter implements Converter {

    private static final Map<Object, String> entities = new WeakHashMap<Object, String>();


    public Object getAsObject(FacesContext arg0, UIComponent component, String uuid) {

        for (Entry<Object, String> entry : entities.entrySet()) {

            if (entry.getValue().equals(uuid)) {
                return entry.getKey();

            }
        }
        return null;
    }


    public String getAsString(FacesContext arg0, UIComponent component, Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);

                return uuid;
            } else {
                return entities.get(entity);
            }
        }
    }

}
